package com.onesignal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootUpReceiver
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    NotificationRestorer.startDelayedRestoreTaskFromReceiver(paramContext);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\BootUpReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */