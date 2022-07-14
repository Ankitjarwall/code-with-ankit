package com.onesignal;

import android.annotation.TargetApi;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import java.util.Random;
import org.json.JSONObject;

public class GcmBroadcastReceiver
  extends WakefulBroadcastReceiver
{
  private static final String GCM_RECEIVE_ACTION = "com.google.android.c2dm.intent.RECEIVE";
  private static final String GCM_TYPE = "gcm";
  private static final String MESSAGE_TYPE_EXTRA_KEY = "message_type";
  
  private static boolean isGcmMessage(Intent paramIntent)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ("com.google.android.c2dm.intent.RECEIVE".equals(paramIntent.getAction()))
    {
      paramIntent = paramIntent.getStringExtra("message_type");
      if (paramIntent != null)
      {
        bool1 = bool2;
        if (!"gcm".equals(paramIntent)) {}
      }
      else
      {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private static NotificationBundleProcessor.ProcessedBundleResult processOrderBroadcast(Context paramContext, Intent paramIntent, Bundle paramBundle)
  {
    if (!isGcmMessage(paramIntent)) {
      paramIntent = null;
    }
    NotificationBundleProcessor.ProcessedBundleResult localProcessedBundleResult;
    do
    {
      return paramIntent;
      localProcessedBundleResult = NotificationBundleProcessor.processBundleFromReceiver(paramContext, paramBundle);
      paramIntent = localProcessedBundleResult;
    } while (localProcessedBundleResult.processed());
    startGCMService(paramContext, paramBundle);
    return localProcessedBundleResult;
  }
  
  private void setAbort()
  {
    if (isOrderedBroadcast()) {
      abortBroadcast();
    }
  }
  
  private static BundleCompat setCompatBundleForServer(Bundle paramBundle, BundleCompat paramBundleCompat)
  {
    paramBundleCompat.putString("json_payload", NotificationBundleProcessor.bundleAsJSONObject(paramBundle).toString());
    paramBundleCompat.putLong("timestamp", Long.valueOf(System.currentTimeMillis() / 1000L));
    return paramBundleCompat;
  }
  
  private void setResult(int paramInt)
  {
    if (isOrderedBroadcast()) {
      setResultCode(paramInt);
    }
  }
  
  private static void startGCMService(Context paramContext, Bundle paramBundle)
  {
    if (!NotificationBundleProcessor.hasRemoteResource(paramBundle))
    {
      NotificationBundleProcessor.ProcessFromGCMIntentService(paramContext, setCompatBundleForServer(paramBundle, BundleCompatFactory.getInstance()), null);
      return;
    }
    if (Integer.parseInt(paramBundle.getString("pri", "0")) > 9) {}
    for (int i = 1; (i == 0) && (Build.VERSION.SDK_INT >= 26); i = 0)
    {
      startGCMServiceWithJobScheduler(paramContext, paramBundle);
      return;
    }
    try
    {
      startGCMServiceWithWakefulService(paramContext, paramBundle);
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        startGCMServiceWithJobScheduler(paramContext, paramBundle);
        return;
      }
      throw localIllegalStateException;
    }
  }
  
  @TargetApi(21)
  private static void startGCMServiceWithJobScheduler(Context paramContext, Bundle paramBundle)
  {
    paramBundle = setCompatBundleForServer(paramBundle, BundleCompatFactory.getInstance());
    ComponentName localComponentName = new ComponentName(paramContext.getPackageName(), GcmIntentJobService.class.getName());
    paramBundle = new JobInfo.Builder(new Random().nextInt(), localComponentName).setOverrideDeadline(0L).setExtras((PersistableBundle)paramBundle.getBundle()).build();
    ((JobScheduler)paramContext.getSystemService("jobscheduler")).schedule(paramBundle);
  }
  
  private static void startGCMServiceWithWakefulService(Context paramContext, Bundle paramBundle)
  {
    ComponentName localComponentName = new ComponentName(paramContext.getPackageName(), GcmIntentService.class.getName());
    paramBundle = setCompatBundleForServer(paramBundle, new BundleCompatBundle());
    startWakefulService(paramContext, new Intent().replaceExtras((Bundle)paramBundle.getBundle()).setComponent(localComponentName));
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Bundle localBundle = paramIntent.getExtras();
    if ((localBundle == null) || ("google.com/iid".equals(localBundle.getString("from")))) {
      return;
    }
    paramIntent = processOrderBroadcast(paramContext, paramIntent, localBundle);
    if (paramIntent == null)
    {
      setResult(-1);
      return;
    }
    if ((paramIntent.isDup) || (paramIntent.hasExtenderService))
    {
      setAbort();
      return;
    }
    if ((paramIntent.isOneSignalPayload) && (OneSignal.getFilterOtherGCMReceivers(paramContext)))
    {
      setAbort();
      return;
    }
    setResult(-1);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\GcmBroadcastReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */