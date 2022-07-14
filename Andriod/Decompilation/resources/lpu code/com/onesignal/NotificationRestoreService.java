package com.onesignal;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class NotificationRestoreService
  extends IntentService
{
  public NotificationRestoreService()
  {
    super("NotificationRestoreService");
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    if (paramIntent == null) {
      return;
    }
    Thread.currentThread().setPriority(10);
    NotificationRestorer.restore(this);
    WakefulBroadcastReceiver.completeWakefulIntent(paramIntent);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\NotificationRestoreService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */