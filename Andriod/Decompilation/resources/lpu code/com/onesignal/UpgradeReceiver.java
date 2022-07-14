package com.onesignal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;

public class UpgradeReceiver
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (Build.VERSION.SDK_INT == 24) {
      return;
    }
    NotificationRestorer.startDelayedRestoreTaskFromReceiver(paramContext);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\UpgradeReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */