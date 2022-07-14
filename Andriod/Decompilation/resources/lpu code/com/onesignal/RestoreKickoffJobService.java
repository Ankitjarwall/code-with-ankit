package com.onesignal;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.support.annotation.RequiresApi;

@RequiresApi(api=21)
public class RestoreKickoffJobService
  extends OneSignalJobServiceBase
{
  void startProcessing(JobService paramJobService, JobParameters paramJobParameters)
  {
    Thread.currentThread().setPriority(10);
    NotificationRestorer.restore(getApplicationContext());
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\RestoreKickoffJobService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */