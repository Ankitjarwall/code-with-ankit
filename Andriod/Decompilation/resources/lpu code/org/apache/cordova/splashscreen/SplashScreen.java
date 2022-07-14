package org.apache.cordova.splashscreen;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.lang.reflect.Method;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

public class SplashScreen
  extends CordovaPlugin
{
  private static final int DEFAULT_FADE_DURATION = 500;
  private static final int DEFAULT_SPLASHSCREEN_DURATION = 3000;
  private static final boolean HAS_BUILT_IN_SPLASH_SCREEN;
  private static final String LOG_TAG = "SplashScreen";
  private static boolean firstShow = true;
  private static boolean lastHideAfterDelay;
  private static ProgressDialog spinnerDialog;
  private static Dialog splashDialog;
  private int orientation;
  private ImageView splashImageView;
  
  static
  {
    boolean bool = false;
    if (Integer.valueOf("7.1.4".split("\\.")[0]).intValue() < 4) {
      bool = true;
    }
    HAS_BUILT_IN_SPLASH_SCREEN = bool;
  }
  
  private int getFadeDuration()
  {
    if (this.preferences.getBoolean("FadeSplashScreen", true)) {}
    for (int i = this.preferences.getInteger("FadeSplashScreenDuration", 500);; i = 0)
    {
      int j = i;
      if (i < 30) {
        j = i * 1000;
      }
      return j;
    }
  }
  
  private int getSplashId()
  {
    int i = 0;
    String str = this.preferences.getString("SplashScreen", "screen");
    if (str != null)
    {
      int j = this.cordova.getActivity().getResources().getIdentifier(str, "drawable", this.cordova.getActivity().getClass().getPackage().getName());
      i = j;
      if (j == 0) {
        i = this.cordova.getActivity().getResources().getIdentifier(str, "drawable", this.cordova.getActivity().getPackageName());
      }
    }
    return i;
  }
  
  private View getView()
  {
    try
    {
      View localView = (View)this.webView.getClass().getMethod("getView", new Class[0]).invoke(this.webView, new Object[0]);
      return localView;
    }
    catch (Exception localException) {}
    return (View)this.webView;
  }
  
  private boolean isMaintainAspectRatio()
  {
    return this.preferences.getBoolean("SplashMaintainAspectRatio", false);
  }
  
  private void removeSplashScreen(final boolean paramBoolean)
  {
    this.cordova.getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        if ((SplashScreen.splashDialog != null) && (SplashScreen.splashDialog.isShowing()))
        {
          int i = SplashScreen.this.getFadeDuration();
          if ((i > 0) && (!paramBoolean))
          {
            AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.0F);
            localAlphaAnimation.setInterpolator(new DecelerateInterpolator());
            localAlphaAnimation.setDuration(i);
            SplashScreen.this.splashImageView.setAnimation(localAlphaAnimation);
            SplashScreen.this.splashImageView.startAnimation(localAlphaAnimation);
            localAlphaAnimation.setAnimationListener(new Animation.AnimationListener()
            {
              public void onAnimationEnd(Animation paramAnonymous2Animation)
              {
                if ((SplashScreen.splashDialog != null) && (SplashScreen.splashDialog.isShowing()))
                {
                  SplashScreen.splashDialog.dismiss();
                  SplashScreen.access$102(null);
                  SplashScreen.access$302(SplashScreen.this, null);
                }
              }
              
              public void onAnimationRepeat(Animation paramAnonymous2Animation) {}
              
              public void onAnimationStart(Animation paramAnonymous2Animation)
              {
                SplashScreen.this.spinnerStop();
              }
            });
          }
        }
        else
        {
          return;
        }
        SplashScreen.this.spinnerStop();
        SplashScreen.splashDialog.dismiss();
        SplashScreen.access$102(null);
        SplashScreen.access$302(SplashScreen.this, null);
      }
    });
  }
  
  private void showSplashScreen(final boolean paramBoolean)
  {
    int i = this.preferences.getInteger("SplashScreenDelay", 3000);
    final int j = getSplashId();
    final int k = Math.max(0, i - getFadeDuration());
    lastHideAfterDelay = paramBoolean;
    if (this.cordova.getActivity().isFinishing()) {}
    while (((splashDialog != null) && (splashDialog.isShowing())) || (j == 0) || ((i <= 0) && (paramBoolean))) {
      return;
    }
    this.cordova.getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        Display localDisplay = SplashScreen.this.cordova.getActivity().getWindowManager().getDefaultDisplay();
        Context localContext = SplashScreen.this.webView.getContext();
        SplashScreen.access$302(SplashScreen.this, new ImageView(localContext));
        SplashScreen.this.splashImageView.setImageResource(j);
        LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
        SplashScreen.this.splashImageView.setLayoutParams(localLayoutParams);
        SplashScreen.this.splashImageView.setMinimumHeight(localDisplay.getHeight());
        SplashScreen.this.splashImageView.setMinimumWidth(localDisplay.getWidth());
        SplashScreen.this.splashImageView.setBackgroundColor(SplashScreen.this.preferences.getInteger("backgroundColor", -16777216));
        if (SplashScreen.this.isMaintainAspectRatio()) {
          SplashScreen.this.splashImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        for (;;)
        {
          SplashScreen.access$102(new Dialog(localContext, 16973840));
          if ((SplashScreen.this.cordova.getActivity().getWindow().getAttributes().flags & 0x400) == 1024) {
            SplashScreen.splashDialog.getWindow().setFlags(1024, 1024);
          }
          SplashScreen.splashDialog.setContentView(SplashScreen.this.splashImageView);
          SplashScreen.splashDialog.setCancelable(false);
          SplashScreen.splashDialog.show();
          if (SplashScreen.this.preferences.getBoolean("ShowSplashScreenSpinner", true)) {
            SplashScreen.this.spinnerStart();
          }
          if (paramBoolean) {
            new Handler().postDelayed(new Runnable()
            {
              public void run()
              {
                if (SplashScreen.lastHideAfterDelay) {
                  SplashScreen.this.removeSplashScreen(false);
                }
              }
            }, k);
          }
          return;
          SplashScreen.this.splashImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
      }
    });
  }
  
  private void spinnerStart()
  {
    this.cordova.getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        SplashScreen.this.spinnerStop();
        SplashScreen.access$1102(new ProgressDialog(SplashScreen.this.webView.getContext()));
        SplashScreen.spinnerDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
          public void onCancel(DialogInterface paramAnonymous2DialogInterface)
          {
            SplashScreen.access$1102(null);
          }
        });
        SplashScreen.spinnerDialog.setCancelable(false);
        SplashScreen.spinnerDialog.setIndeterminate(true);
        RelativeLayout localRelativeLayout = new RelativeLayout(SplashScreen.this.cordova.getActivity());
        localRelativeLayout.setGravity(17);
        localRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        ProgressBar localProgressBar = new ProgressBar(SplashScreen.this.webView.getContext());
        Object localObject = new RelativeLayout.LayoutParams(-2, -2);
        ((RelativeLayout.LayoutParams)localObject).addRule(13, -1);
        localProgressBar.setLayoutParams((ViewGroup.LayoutParams)localObject);
        if (Build.VERSION.SDK_INT >= 21)
        {
          localObject = SplashScreen.this.preferences.getString("SplashScreenSpinnerColor", null);
          if (localObject != null)
          {
            int[] arrayOfInt = { -16842910 };
            int i = Color.parseColor((String)localObject);
            localProgressBar.setIndeterminateTintList(new ColorStateList(new int[][] { { 16842910 }, arrayOfInt, { -16842912 }, { 16842919 } }, new int[] { i, i, i, i }));
          }
        }
        localRelativeLayout.addView(localProgressBar);
        SplashScreen.spinnerDialog.getWindow().clearFlags(2);
        SplashScreen.spinnerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        SplashScreen.spinnerDialog.show();
        SplashScreen.spinnerDialog.setContentView(localRelativeLayout);
      }
    });
  }
  
  private void spinnerStop()
  {
    this.cordova.getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        if ((SplashScreen.spinnerDialog != null) && (SplashScreen.spinnerDialog.isShowing()))
        {
          SplashScreen.spinnerDialog.dismiss();
          SplashScreen.access$1102(null);
        }
      }
    });
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    if (paramString.equals("hide")) {
      this.cordova.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          SplashScreen.this.webView.postMessage("splashscreen", "hide");
        }
      });
    }
    for (;;)
    {
      paramCallbackContext.success();
      return true;
      if (!paramString.equals("show")) {
        break;
      }
      this.cordova.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          SplashScreen.this.webView.postMessage("splashscreen", "show");
        }
      });
    }
    return false;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (paramConfiguration.orientation != this.orientation)
    {
      this.orientation = paramConfiguration.orientation;
      if (this.splashImageView != null)
      {
        int i = getSplashId();
        if (i != 0) {
          this.splashImageView.setImageDrawable(this.cordova.getActivity().getResources().getDrawable(i));
        }
      }
    }
  }
  
  public void onDestroy()
  {
    if (HAS_BUILT_IN_SPLASH_SCREEN) {
      return;
    }
    removeSplashScreen(true);
  }
  
  public Object onMessage(String paramString, Object paramObject)
  {
    if (HAS_BUILT_IN_SPLASH_SCREEN) {}
    do
    {
      do
      {
        return null;
        if ("splashscreen".equals(paramString))
        {
          if ("hide".equals(paramObject.toString()))
          {
            removeSplashScreen(false);
            return null;
          }
          showSplashScreen(false);
          return null;
        }
        if (!"spinner".equals(paramString)) {
          break;
        }
      } while (!"stop".equals(paramObject.toString()));
      getView().setVisibility(0);
      return null;
    } while (!"onReceivedError".equals(paramString));
    spinnerStop();
    return null;
  }
  
  public void onPause(boolean paramBoolean)
  {
    if (HAS_BUILT_IN_SPLASH_SCREEN) {
      return;
    }
    removeSplashScreen(true);
  }
  
  protected void pluginInitialize()
  {
    if (HAS_BUILT_IN_SPLASH_SCREEN) {}
    do
    {
      return;
      this.cordova.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          SplashScreen.this.getView().setVisibility(4);
        }
      });
      getSplashId();
      this.orientation = this.cordova.getActivity().getResources().getConfiguration().orientation;
      if (firstShow) {
        showSplashScreen(this.preferences.getBoolean("AutoHideSplashScreen", true));
      }
    } while (!this.preferences.getBoolean("SplashShowOnlyFirstTime", true));
    firstShow = false;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\splashscreen\SplashScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */