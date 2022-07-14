package de.appplant.cordova.plugin.localnotification;

import android.os.Bundle;
import de.appplant.cordova.plugin.notification.Notification;
import de.appplant.cordova.plugin.notification.receiver.AbstractClearReceiver;

public class ClearReceiver
  extends AbstractClearReceiver
{
  public void onClear(Notification paramNotification, Bundle paramBundle)
  {
    if (paramBundle.getBoolean("NOTIFICATION_LAST", false)) {
      paramNotification.cancel();
    }
    for (;;)
    {
      LocalNotification.fireEvent("clear", paramNotification);
      return;
      paramNotification.clear();
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\localnotification\ClearReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */