package com.onesignal;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

public class GcmIntentService
  extends IntentService
{
  public GcmIntentService()
  {
    super("GcmIntentService");
    setIntentRedelivery(true);
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    Bundle localBundle = paramIntent.getExtras();
    if (localBundle == null) {
      return;
    }
    NotificationBundleProcessor.ProcessFromGCMIntentService(this, new BundleCompatBundle(localBundle), null);
    GcmBroadcastReceiver.completeWakefulIntent(paramIntent);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\GcmIntentService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */