package de.appplant.cordova.plugin.notification.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import de.appplant.cordova.plugin.notification.Builder;
import de.appplant.cordova.plugin.notification.Manager;
import de.appplant.cordova.plugin.notification.Notification;

public abstract class AbstractTriggerReceiver
  extends BroadcastReceiver
{
  public abstract Notification buildNotification(Builder paramBuilder, Bundle paramBundle);
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramIntent = paramIntent.getExtras();
    if (paramIntent == null) {}
    do
    {
      do
      {
        return;
        int i = paramIntent.getInt("NOTIFICATION_ID", 0);
        paramContext = Manager.getInstance(paramContext).getOptions(i);
      } while (paramContext == null);
      paramContext = buildNotification(new Builder(paramContext), paramIntent);
    } while (paramContext == null);
    onTrigger(paramContext, paramIntent);
  }
  
  public abstract void onTrigger(Notification paramNotification, Bundle paramBundle);
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\notification\receiver\AbstractTriggerReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */