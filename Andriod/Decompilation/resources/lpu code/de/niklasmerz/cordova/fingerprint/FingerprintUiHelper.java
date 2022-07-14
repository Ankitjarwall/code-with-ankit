package de.niklasmerz.cordova.fingerprint;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintManager.AuthenticationCallback;
import android.hardware.fingerprint.FingerprintManager.AuthenticationResult;
import android.hardware.fingerprint.FingerprintManager.CryptoObject;
import android.os.CancellationSignal;
import android.widget.ImageView;
import android.widget.TextView;

@TargetApi(23)
public class FingerprintUiHelper
  extends FingerprintManager.AuthenticationCallback
{
  static final long ERROR_TIMEOUT_MILLIS = 1600L;
  static final long SUCCESS_DELAY_MILLIS = 1300L;
  private final Callback mCallback;
  private CancellationSignal mCancellationSignal;
  private final Context mContext;
  private final TextView mErrorTextView;
  private final FingerprintManager mFingerprintManager;
  private final ImageView mIcon;
  Runnable mResetErrorTextRunnable = new Runnable()
  {
    public void run()
    {
      int i = FingerprintUiHelper.this.mContext.getResources().getIdentifier("hint_color", "color", Fingerprint.packageName);
      FingerprintUiHelper.this.mErrorTextView.setTextColor(FingerprintUiHelper.this.mErrorTextView.getResources().getColor(i, null));
      i = FingerprintUiHelper.this.mContext.getResources().getIdentifier("fingerprint_hint", "string", Fingerprint.packageName);
      FingerprintUiHelper.this.mErrorTextView.setText(FingerprintUiHelper.this.mErrorTextView.getResources().getString(i));
      i = FingerprintUiHelper.this.mContext.getResources().getIdentifier("ic_fp_40px", "drawable", Fingerprint.packageName);
      FingerprintUiHelper.this.mIcon.setImageResource(i);
    }
  };
  boolean mSelfCancelled;
  
  private FingerprintUiHelper(Context paramContext, FingerprintManager paramFingerprintManager, ImageView paramImageView, TextView paramTextView, Callback paramCallback)
  {
    this.mFingerprintManager = paramFingerprintManager;
    this.mIcon = paramImageView;
    this.mErrorTextView = paramTextView;
    this.mCallback = paramCallback;
    this.mContext = paramContext;
  }
  
  private void showError(CharSequence paramCharSequence)
  {
    int i = this.mContext.getResources().getIdentifier("ic_fingerprint_error", "drawable", Fingerprint.packageName);
    this.mIcon.setImageResource(i);
    this.mErrorTextView.setText(paramCharSequence);
    i = this.mContext.getResources().getIdentifier("warning_color", "color", Fingerprint.packageName);
    this.mErrorTextView.setTextColor(this.mErrorTextView.getResources().getColor(i, null));
    this.mErrorTextView.removeCallbacks(this.mResetErrorTextRunnable);
    this.mErrorTextView.postDelayed(this.mResetErrorTextRunnable, 1600L);
  }
  
  public boolean isFingerprintAuthAvailable()
  {
    return (this.mFingerprintManager.isHardwareDetected()) && (this.mFingerprintManager.hasEnrolledFingerprints());
  }
  
  public void onAuthenticationError(int paramInt, CharSequence paramCharSequence)
  {
    if (!this.mSelfCancelled)
    {
      showError(paramCharSequence);
      this.mIcon.postDelayed(new Runnable()
      {
        public void run()
        {
          FingerprintUiHelper.this.mCallback.onError();
        }
      }, 1600L);
    }
  }
  
  public void onAuthenticationFailed()
  {
    int i = this.mContext.getResources().getIdentifier("fingerprint_not_recognized", "string", Fingerprint.packageName);
    showError(this.mIcon.getResources().getString(i));
  }
  
  public void onAuthenticationHelp(int paramInt, CharSequence paramCharSequence)
  {
    showError(paramCharSequence);
  }
  
  public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult paramAuthenticationResult)
  {
    this.mErrorTextView.removeCallbacks(this.mResetErrorTextRunnable);
    int i = this.mContext.getResources().getIdentifier("ic_fingerprint_success", "drawable", Fingerprint.packageName);
    this.mIcon.setImageResource(i);
    i = this.mContext.getResources().getIdentifier("success_color", "color", Fingerprint.packageName);
    this.mErrorTextView.setTextColor(this.mErrorTextView.getResources().getColor(i, null));
    i = this.mContext.getResources().getIdentifier("fingerprint_success", "string", Fingerprint.packageName);
    this.mErrorTextView.setText(this.mErrorTextView.getResources().getString(i));
    this.mIcon.postDelayed(new Runnable()
    {
      public void run()
      {
        FingerprintUiHelper.this.mCallback.onAuthenticated();
      }
    }, 1300L);
  }
  
  public void startListening(FingerprintManager.CryptoObject paramCryptoObject)
  {
    if (!isFingerprintAuthAvailable()) {
      return;
    }
    this.mCancellationSignal = new CancellationSignal();
    this.mSelfCancelled = false;
    this.mFingerprintManager.authenticate(paramCryptoObject, this.mCancellationSignal, 0, this, null);
    int i = this.mContext.getResources().getIdentifier("ic_fp_40px", "drawable", Fingerprint.packageName);
    this.mIcon.setImageResource(i);
  }
  
  public void stopListening()
  {
    if (this.mCancellationSignal != null)
    {
      this.mSelfCancelled = true;
      this.mCancellationSignal.cancel();
      this.mCancellationSignal = null;
    }
  }
  
  public static abstract interface Callback
  {
    public abstract void onAuthenticated();
    
    public abstract void onError();
  }
  
  public static class FingerprintUiHelperBuilder
  {
    private final Context mContext;
    private final FingerprintManager mFingerPrintManager;
    
    public FingerprintUiHelperBuilder(Context paramContext, FingerprintManager paramFingerprintManager)
    {
      this.mFingerPrintManager = paramFingerprintManager;
      this.mContext = paramContext;
    }
    
    public FingerprintUiHelper build(ImageView paramImageView, TextView paramTextView, FingerprintUiHelper.Callback paramCallback)
    {
      return new FingerprintUiHelper(this.mContext, this.mFingerPrintManager, paramImageView, paramTextView, paramCallback, null);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\niklasmerz\cordova\fingerprint\FingerprintUiHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */