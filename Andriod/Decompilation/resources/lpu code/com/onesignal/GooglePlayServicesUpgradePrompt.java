package com.onesignal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

class GooglePlayServicesUpgradePrompt
{
  private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
  
  private static void OpenPlayStoreToApp(Activity paramActivity)
  {
    try
    {
      GoogleApiAvailability localGoogleApiAvailability = GoogleApiAvailability.getInstance();
      localGoogleApiAvailability.getErrorResolutionPendingIntent(paramActivity, localGoogleApiAvailability.isGooglePlayServicesAvailable(OneSignal.appContext), 9000).send();
      return;
    }
    catch (Throwable paramActivity)
    {
      ThrowableExtension.printStackTrace(paramActivity);
      return;
    }
    catch (PendingIntent.CanceledException paramActivity) {}
  }
  
  static void ShowUpdateGPSDialog()
  {
    if ((isGMSInstalledAndEnabled()) || (!isGooglePlayStoreInstalled())) {}
    while (OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_DO_NOT_SHOW_MISSING_GPS", false)) {
      return;
    }
    OSUtils.runOnMainUIThread(new Runnable()
    {
      public void run()
      {
        final Activity localActivity = ActivityLifecycleHandler.curActivity;
        if ((localActivity == null) || (OneSignal.mInitBuilder.mDisableGmsMissingPrompt)) {
          return;
        }
        String str1 = OSUtils.getResourceString(localActivity, "onesignal_gms_missing_alert_text", "To receive push notifications please press 'Update' to enable 'Google Play services'.");
        String str2 = OSUtils.getResourceString(localActivity, "onesignal_gms_missing_alert_button_update", "Update");
        String str3 = OSUtils.getResourceString(localActivity, "onesignal_gms_missing_alert_button_skip", "Skip");
        String str4 = OSUtils.getResourceString(localActivity, "onesignal_gms_missing_alert_button_close", "Close");
        new AlertDialog.Builder(localActivity).setMessage(str1).setPositiveButton(str2, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            GooglePlayServicesUpgradePrompt.OpenPlayStoreToApp(localActivity);
          }
        }).setNegativeButton(str3, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_DO_NOT_SHOW_MISSING_GPS", true);
          }
        }).setNeutralButton(str4, null).create().show();
      }
    });
  }
  
  static boolean isGMSInstalledAndEnabled()
  {
    try
    {
      boolean bool = OneSignal.appContext.getPackageManager().getPackageInfo("com.google.android.gms", 1).applicationInfo.enabled;
      return bool;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return false;
  }
  
  private static boolean isGooglePlayStoreInstalled()
  {
    try
    {
      Object localObject = OneSignal.appContext.getPackageManager();
      localObject = (String)((PackageManager)localObject).getPackageInfo("com.google.android.gms", 1).applicationInfo.loadLabel((PackageManager)localObject);
      if (localObject != null)
      {
        boolean bool = ((String)localObject).equals("Market");
        if (!bool) {
          return true;
        }
      }
      return false;
    }
    catch (Throwable localThrowable) {}
    return false;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\GooglePlayServicesUpgradePrompt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */