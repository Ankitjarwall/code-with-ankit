package de.appplant.cordova.plugin.notification.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import de.appplant.cordova.plugin.notification.Manager;
import de.appplant.cordova.plugin.notification.Notification;

public abstract class AbstractClearReceiver
  extends BroadcastReceiver
{
  public abstract void onClear(Notification paramNotification, Bundle paramBundle);
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramIntent = paramIntent.getExtras();
    if (paramIntent == null) {}
    do
    {
      return;
      int i = paramIntent.getInt("NOTIFICATION_ID");
      paramContext = Manager.getInstance(paramContext).get(i);
    } while (paramContext == null);
    onClear(paramContext, paramIntent);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\notification\receiver\AbstractClearReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */