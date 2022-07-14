package com.onesignal;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.support.annotation.RequiresApi;

@RequiresApi(api=22)
public class GcmIntentJobService
  extends OneSignalJobServiceBase
{
  void startProcessing(JobService paramJobService, JobParameters paramJobParameters)
  {
    NotificationBundleProcessor.ProcessFromGCMIntentService(paramJobService, new BundleCompatPersistableBundle(paramJobParameters.getExtras()), null);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\GcmIntentJobService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */