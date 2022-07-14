package de.appplant.cordova.plugin.localnotification;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import de.appplant.cordova.plugin.notification.Builder;
import de.appplant.cordova.plugin.notification.Manager;
import de.appplant.cordova.plugin.notification.Notification;
import de.appplant.cordova.plugin.notification.Options;
import de.appplant.cordova.plugin.notification.Request;
import de.appplant.cordova.plugin.notification.receiver.AbstractTriggerReceiver;

public class TriggerReceiver
  extends AbstractTriggerReceiver
{
  private void wakeUp(Context paramContext)
  {
    paramContext = (PowerManager)paramContext.getSystemService("power");
    if (paramContext == null) {
      return;
    }
    paramContext = paramContext.newWakeLock(268435462, "LocalNotification");
    paramContext.setReferenceCounted(false);
    paramContext.acquire(1000L);
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramContext.release(1);
      return;
    }
    paramContext.release();
  }
  
  public Notification buildNotification(Builder paramBuilder, Bundle paramBundle)
  {
    return paramBuilder.setClickActivity(ClickReceiver.class).setClearReceiver(ClearReceiver.class).setExtras(paramBundle).build();
  }
  
  public void onTrigger(Notification paramNotification, Bundle paramBundle)
  {
    boolean bool = paramBundle.getBoolean("NOTIFICATION_UPDATE", false);
    paramBundle = paramNotification.getContext();
    Options localOptions = paramNotification.getOptions();
    Manager localManager = Manager.getInstance(paramBundle);
    int i = localOptions.getBadgeNumber();
    if (i > 0) {
      localManager.setBadge(i);
    }
    if (localOptions.shallWakeUp()) {
      wakeUp(paramBundle);
    }
    paramNotification.show();
    if (localOptions.isInfiniteTrigger()) {
      localManager.schedule(new Request(localOptions), getClass());
    }
    if (!bool) {
      LocalNotification.fireEvent("trigger", paramNotification);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\localnotification\TriggerReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */