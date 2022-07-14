package com.onesignal;

import android.content.Intent;
import android.os.Bundle;

public class RestoreJobService
  extends JobIntentService
{
  static final int RESTORE_SERVICE_JOB_ID = 2071862122;
  
  protected final void onHandleWork(Intent paramIntent)
  {
    if (paramIntent == null) {
      return;
    }
    paramIntent = new Bundle(paramIntent.getExtras());
    NotificationBundleProcessor.ProcessFromGCMIntentService(getApplicationContext(), new BundleCompatBundle(paramIntent), null);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\RestoreJobService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */