package com.onesignal;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;

public class PermissionsActivity
  extends Activity
{
  private static final int REQUEST_LOCATION = 2;
  private static ActivityLifecycleHandler.ActivityAvailableListener activityAvailableListener;
  static boolean answered;
  static boolean waiting;
  
  private void requestPermission()
  {
    if (Build.VERSION.SDK_INT < 23) {
      finish();
    }
    while (waiting) {
      return;
    }
    waiting = true;
    AndroidSupportV4Compat.ActivityCompat.requestPermissions(this, new String[] { LocationGMS.requestPermission }, 2);
  }
  
  static void startPrompt()
  {
    if ((waiting) || (answered)) {
      return;
    }
    activityAvailableListener = new ActivityLifecycleHandler.ActivityAvailableListener()
    {
      public void available(Activity paramAnonymousActivity)
      {
        if (!paramAnonymousActivity.getClass().equals(PermissionsActivity.class))
        {
          Intent localIntent = new Intent(paramAnonymousActivity, PermissionsActivity.class);
          localIntent.setFlags(131072);
          paramAnonymousActivity.startActivity(localIntent);
        }
      }
    };
    ActivityLifecycleHandler.setActivityAvailableListener(activityAvailableListener);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((paramBundle != null) && (paramBundle.getBoolean("android:hasCurrentPermissionsRequest", false)))
    {
      waiting = true;
      return;
    }
    requestPermission();
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    if (OneSignal.initDone) {
      requestPermission();
    }
  }
  
  public void onRequestPermissionsResult(int paramInt, @NonNull String[] paramArrayOfString, @NonNull int[] paramArrayOfInt)
  {
    answered = true;
    waiting = false;
    if (paramInt == 2)
    {
      if ((paramArrayOfInt.length <= 0) || (paramArrayOfInt[0] != 0)) {
        break label38;
      }
      LocationGMS.startGetLocation();
    }
    for (;;)
    {
      ActivityLifecycleHandler.removeActivityAvailableListener(activityAvailableListener);
      finish();
      return;
      label38:
      LocationGMS.fireFailedComplete();
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\PermissionsActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */