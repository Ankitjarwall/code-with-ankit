package com.onesignal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Extender;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class NotificationExtenderService
  extends JobIntentService
{
  static final int EXTENDER_SERVICE_JOB_ID = 2071862121;
  private OverrideSettings currentBaseOverrideSettings = null;
  private JSONObject currentJsonPayload;
  private boolean currentlyRestoring;
  private OSNotificationDisplayedResult osNotificationDisplayedResult;
  private Long restoreTimestamp;
  
  private NotificationGenerationJob createNotifJobFromCurrent()
  {
    NotificationGenerationJob localNotificationGenerationJob = new NotificationGenerationJob(this);
    localNotificationGenerationJob.restoring = this.currentlyRestoring;
    localNotificationGenerationJob.jsonPayload = this.currentJsonPayload;
    localNotificationGenerationJob.shownTimeStamp = this.restoreTimestamp;
    localNotificationGenerationJob.overrideSettings = this.currentBaseOverrideSettings;
    return localNotificationGenerationJob;
  }
  
  static Intent getIntent(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    Intent localIntent = new Intent().setAction("com.onesignal.NotificationExtender").setPackage(paramContext.getPackageName());
    localObject = ((PackageManager)localObject).queryIntentServices(localIntent, 128);
    if (((List)localObject).size() < 1) {
      return null;
    }
    localIntent.setComponent(new ComponentName(paramContext, ((ResolveInfo)((List)localObject).get(0)).serviceInfo.name));
    return localIntent;
  }
  
  private void processIntent(Intent paramIntent)
  {
    Bundle localBundle = paramIntent.getExtras();
    if (localBundle == null) {
      OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "No extras sent to NotificationExtenderService in its Intent!\n" + paramIntent);
    }
    for (;;)
    {
      return;
      paramIntent = localBundle.getString("json_payload");
      if (paramIntent == null)
      {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "json_payload key is nonexistent from bundle passed to NotificationExtenderService: " + localBundle);
        return;
      }
      try
      {
        this.currentJsonPayload = new JSONObject(paramIntent);
        this.currentlyRestoring = localBundle.getBoolean("restoring", false);
        if (localBundle.containsKey("android_notif_id"))
        {
          this.currentBaseOverrideSettings = new OverrideSettings();
          this.currentBaseOverrideSettings.androidNotificationId = Integer.valueOf(localBundle.getInt("android_notif_id"));
        }
        if ((this.currentlyRestoring) || (!OneSignal.notValidOrDuplicated(this, this.currentJsonPayload)))
        {
          this.restoreTimestamp = Long.valueOf(localBundle.getLong("timestamp"));
          processJsonObject(this.currentJsonPayload, this.currentlyRestoring);
          return;
        }
      }
      catch (JSONException paramIntent)
      {
        ThrowableExtension.printStackTrace(paramIntent);
      }
    }
  }
  
  protected final OSNotificationDisplayedResult displayNotification(OverrideSettings paramOverrideSettings)
  {
    if ((this.osNotificationDisplayedResult != null) || (paramOverrideSettings == null)) {
      return null;
    }
    paramOverrideSettings.override(this.currentBaseOverrideSettings);
    this.osNotificationDisplayedResult = new OSNotificationDisplayedResult();
    NotificationGenerationJob localNotificationGenerationJob = createNotifJobFromCurrent();
    localNotificationGenerationJob.overrideSettings = paramOverrideSettings;
    this.osNotificationDisplayedResult.androidNotificationId = NotificationBundleProcessor.ProcessJobForDisplay(localNotificationGenerationJob);
    return this.osNotificationDisplayedResult;
  }
  
  protected final void onHandleWork(Intent paramIntent)
  {
    if (paramIntent == null) {
      return;
    }
    processIntent(paramIntent);
    GcmBroadcastReceiver.completeWakefulIntent(paramIntent);
  }
  
  protected abstract boolean onNotificationProcessing(OSNotificationReceivedResult paramOSNotificationReceivedResult);
  
  void processJsonObject(JSONObject paramJSONObject, boolean paramBoolean)
  {
    Object localObject = new OSNotificationReceivedResult();
    ((OSNotificationReceivedResult)localObject).payload = NotificationBundleProcessor.OSNotificationPayloadFrom(paramJSONObject);
    ((OSNotificationReceivedResult)localObject).restoring = paramBoolean;
    ((OSNotificationReceivedResult)localObject).isAppInFocus = OneSignal.isAppActive();
    this.osNotificationDisplayedResult = null;
    int j = 0;
    try
    {
      boolean bool = onNotificationProcessing((OSNotificationReceivedResult)localObject);
      j = bool;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        int i;
        if (this.osNotificationDisplayedResult == null)
        {
          OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "onNotificationProcessing throw an exception. Displaying normal OneSignal notification.", localThrowable);
        }
        else
        {
          OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "onNotificationProcessing throw an exception. Extended notification displayed but custom processing did not finish.", localThrowable);
          continue;
          i = 0;
          continue;
          if (this.currentBaseOverrideSettings != null)
          {
            NotificationBundleProcessor.markRestoredNotificationAsDismissed(createNotifJobFromCurrent());
            continue;
            NotificationBundleProcessor.ProcessJobForDisplay(createNotifJobFromCurrent());
          }
        }
      }
    }
    if (this.osNotificationDisplayedResult == null)
    {
      if ((j != 0) || (!NotificationBundleProcessor.shouldDisplay(paramJSONObject.optString("alert")))) {
        break label189;
      }
      i = 1;
      if (i != 0) {
        break label211;
      }
      if (paramBoolean) {
        break label194;
      }
      localObject = new NotificationGenerationJob(this);
      ((NotificationGenerationJob)localObject).jsonPayload = paramJSONObject;
      ((NotificationGenerationJob)localObject).overrideSettings = new OverrideSettings();
      ((NotificationGenerationJob)localObject).overrideSettings.androidNotificationId = Integer.valueOf(-1);
      NotificationBundleProcessor.saveNotification((NotificationGenerationJob)localObject, true);
      OneSignal.handleNotificationReceived(NotificationBundleProcessor.newJsonArray(paramJSONObject), false, false);
      if (paramBoolean) {
        OSUtils.sleep(100);
      }
    }
  }
  
  public static class OverrideSettings
  {
    public Integer androidNotificationId;
    public NotificationCompat.Extender extender;
    
    void override(OverrideSettings paramOverrideSettings)
    {
      if (paramOverrideSettings == null) {}
      while (paramOverrideSettings.androidNotificationId == null) {
        return;
      }
      this.androidNotificationId = paramOverrideSettings.androidNotificationId;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\NotificationExtenderService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */