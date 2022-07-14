package com.onesignal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import java.util.concurrent.atomic.AtomicBoolean;

class OneSignalSyncServiceUtils
{
  private static final int SYNC_AFTER_BG_DELAY_MS = 120000;
  private static final int SYNC_TASK_ID = 2071862118;
  private static Long nextScheduledSyncTime = Long.valueOf(0L);
  private static AtomicBoolean runningOnFocusTime = new AtomicBoolean();
  private static Thread syncBgThread;
  
  static void cancelSyncTask(Context paramContext)
  {
    for (;;)
    {
      synchronized (nextScheduledSyncTime)
      {
        nextScheduledSyncTime = Long.valueOf(0L);
        if (LocationGMS.scheduleUpdate(paramContext)) {
          return;
        }
        if (useJob())
        {
          ((JobScheduler)paramContext.getSystemService("jobscheduler")).cancel(2071862118);
          return;
        }
      }
      ((AlarmManager)paramContext.getSystemService("alarm")).cancel(syncServicePendingIntent(paramContext));
    }
  }
  
  static void doBackgroundSync(Context paramContext, SyncRunnable paramSyncRunnable)
  {
    OneSignal.setAppContext(paramContext);
    syncBgThread = new Thread(paramSyncRunnable, "OS_SYNCSRV_BG_SYNC");
    syncBgThread.start();
  }
  
  private static void internalSyncOnFocusTime()
  {
    long l = OneSignal.GetUnsentActiveTime();
    if (l < 60L) {
      return;
    }
    OneSignal.sendOnFocus(l, true);
  }
  
  static void scheduleLocationUpdateTask(Context paramContext, long paramLong)
  {
    OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "scheduleLocationUpdateTask:delayMs: " + paramLong);
    scheduleSyncTask(paramContext, paramLong);
  }
  
  private static void scheduleSyncServiceAsAlarm(Context paramContext, long paramLong)
  {
    OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "scheduleServiceSyncTask:atTime: " + paramLong);
    PendingIntent localPendingIntent = syncServicePendingIntent(paramContext);
    ((AlarmManager)paramContext.getSystemService("alarm")).set(0, System.currentTimeMillis() + paramLong + paramLong, localPendingIntent);
  }
  
  @RequiresApi(21)
  private static void scheduleSyncServiceAsJob(Context paramContext, long paramLong)
  {
    OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "scheduleSyncServiceAsJob:atTime: " + paramLong);
    JobInfo localJobInfo = new JobInfo.Builder(2071862118, new ComponentName(paramContext, SyncJobService.class)).setMinimumLatency(paramLong).setRequiredNetworkType(1).setPersisted(true).build();
    paramContext = (JobScheduler)paramContext.getSystemService("jobscheduler");
    try
    {
      int i = paramContext.schedule(localJobInfo);
      OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "scheduleSyncServiceAsJob:result: " + i);
      return;
    }
    catch (NullPointerException paramContext)
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "scheduleSyncServiceAsJob called JobScheduler.jobScheduler which triggered an internal null Android error. Skipping job.", paramContext);
    }
  }
  
  static void scheduleSyncTask(Context paramContext)
  {
    OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "scheduleSyncTask:SYNC_AFTER_BG_DELAY_MS: 120000");
    scheduleSyncTask(paramContext, 120000L);
  }
  
  private static void scheduleSyncTask(Context paramContext, long paramLong)
  {
    for (;;)
    {
      Object localObject;
      synchronized (nextScheduledSyncTime)
      {
        if ((nextScheduledSyncTime.longValue() == 0L) || (System.currentTimeMillis() + paramLong <= nextScheduledSyncTime.longValue())) {
          break label78;
        }
        return;
        if (useJob())
        {
          scheduleSyncServiceAsJob(paramContext, localObject);
          nextScheduledSyncTime = Long.valueOf(System.currentTimeMillis() + localObject);
          return;
        }
      }
      scheduleSyncServiceAsAlarm(paramContext, localObject);
      continue;
      label78:
      long l = paramLong;
      if (paramLong < 5000L) {
        l = 5000L;
      }
    }
  }
  
  static boolean stopSyncBgThread()
  {
    if (syncBgThread == null) {}
    while (!syncBgThread.isAlive()) {
      return false;
    }
    syncBgThread.interrupt();
    return true;
  }
  
  static void syncOnFocusTime()
  {
    if (runningOnFocusTime.get()) {
      return;
    }
    synchronized (runningOnFocusTime)
    {
      runningOnFocusTime.set(true);
      internalSyncOnFocusTime();
      runningOnFocusTime.set(false);
      return;
    }
  }
  
  private static PendingIntent syncServicePendingIntent(Context paramContext)
  {
    return PendingIntent.getService(paramContext, 2071862118, new Intent(paramContext, SyncService.class), 134217728);
  }
  
  private static boolean useJob()
  {
    return Build.VERSION.SDK_INT >= 21;
  }
  
  static class LegacySyncRunnable
    extends OneSignalSyncServiceUtils.SyncRunnable
  {
    Service callerService;
    
    LegacySyncRunnable(Service paramService)
    {
      this.callerService = paramService;
    }
    
    protected void stopSync()
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "LegacySyncRunnable:Stopped");
      this.callerService.stopSelf();
    }
  }
  
  @RequiresApi(api=21)
  static class LollipopSyncRunnable
    extends OneSignalSyncServiceUtils.SyncRunnable
  {
    private JobParameters jobParameters;
    private JobService jobService;
    
    LollipopSyncRunnable(JobService paramJobService, JobParameters paramJobParameters)
    {
      this.jobService = paramJobService;
      this.jobParameters = paramJobParameters;
    }
    
    protected void stopSync()
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "LollipopSyncRunnable:JobFinished");
      this.jobService.jobFinished(this.jobParameters, false);
    }
  }
  
  static abstract class SyncRunnable
    implements Runnable
  {
    public final void run()
    {
      synchronized (OneSignalSyncServiceUtils.nextScheduledSyncTime)
      {
        OneSignalSyncServiceUtils.access$002(Long.valueOf(0L));
        if (OneSignal.getUserId() == null)
        {
          stopSync();
          return;
        }
      }
      OneSignal.appId = OneSignal.getSavedAppId();
      OneSignalStateSynchronizer.initUserState();
      ??? = new LocationGMS.LocationHandler()
      {
        public void complete(LocationGMS.LocationPoint paramAnonymousLocationPoint)
        {
          if (paramAnonymousLocationPoint != null) {
            OneSignalStateSynchronizer.updateLocation(paramAnonymousLocationPoint);
          }
          OneSignalStateSynchronizer.syncUserState(true);
          OneSignalSyncServiceUtils.syncOnFocusTime();
          OneSignalSyncServiceUtils.SyncRunnable.this.stopSync();
        }
        
        public LocationGMS.CALLBACK_TYPE getType()
        {
          return LocationGMS.CALLBACK_TYPE.SYNC_SERVICE;
        }
      };
      LocationGMS.getLocation(OneSignal.appContext, false, (LocationGMS.LocationHandler)???);
    }
    
    protected abstract void stopSync();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OneSignalSyncServiceUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */