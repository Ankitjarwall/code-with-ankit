package com.onesignal;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.support.annotation.RequiresApi;

@RequiresApi(api=21)
public class SyncJobService
  extends JobService
{
  public boolean onStartJob(JobParameters paramJobParameters)
  {
    OneSignalSyncServiceUtils.doBackgroundSync(this, new OneSignalSyncServiceUtils.LollipopSyncRunnable(this, paramJobParameters));
    return true;
  }
  
  public boolean onStopJob(JobParameters paramJobParameters)
  {
    return OneSignalSyncServiceUtils.stopSyncBgThread();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\SyncJobService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */