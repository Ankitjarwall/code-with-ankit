package com.onesignal;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.support.annotation.RequiresApi;

@RequiresApi(api=21)
abstract class OneSignalJobServiceBase
  extends JobService
{
  public boolean onStartJob(final JobParameters paramJobParameters)
  {
    if (paramJobParameters.getExtras() == null) {
      return false;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        OneSignalJobServiceBase.this.startProcessing(jdField_this, paramJobParameters);
        OneSignalJobServiceBase.this.jobFinished(paramJobParameters, false);
      }
    }, "OS_JOBSERVICE_BASE").start();
    return true;
  }
  
  public boolean onStopJob(JobParameters paramJobParameters)
  {
    return true;
  }
  
  abstract void startProcessing(JobService paramJobService, JobParameters paramJobParameters);
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OneSignalJobServiceBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */