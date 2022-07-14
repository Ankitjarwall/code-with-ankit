package com.google.android.gms.common.api;

import android.app.Activity;
import android.content.IntentSender.SendIntentException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;

public abstract class ResolvingResultCallbacks<R extends Result>
  extends ResultCallbacks<R>
{
  private final Activity mActivity;
  private final int zzazu;
  
  protected ResolvingResultCallbacks(@NonNull Activity paramActivity, int paramInt)
  {
    this.mActivity = ((Activity)zzac.zzb(paramActivity, "Activity must not be null"));
    this.zzazu = paramInt;
  }
  
  public final void onFailure(@NonNull Status paramStatus)
  {
    if (paramStatus.hasResolution()) {
      try
      {
        paramStatus.startResolutionForResult(this.mActivity, this.zzazu);
        return;
      }
      catch (IntentSender.SendIntentException paramStatus)
      {
        Log.e("ResolvingResultCallback", "Failed to start resolution", paramStatus);
        onUnresolvableFailure(new Status(8));
        return;
      }
    }
    onUnresolvableFailure(paramStatus);
  }
  
  public abstract void onSuccess(@NonNull R paramR);
  
  public abstract void onUnresolvableFailure(@NonNull Status paramStatus);
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\api\ResolvingResultCallbacks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */