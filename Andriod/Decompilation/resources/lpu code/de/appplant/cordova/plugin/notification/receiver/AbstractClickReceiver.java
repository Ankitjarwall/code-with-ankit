package de.appplant.cordova.plugin.notification.receiver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import de.appplant.cordova.plugin.notification.Manager;
import de.appplant.cordova.plugin.notification.Notification;

public abstract class AbstractClickReceiver
  extends Activity
{
  protected String getAction()
  {
    return getIntent().getExtras().getString("NOTIFICATION_ACTION_ID", "click");
  }
  
  protected void launchApp()
  {
    Context localContext = getApplicationContext();
    Object localObject = localContext.getPackageName();
    localObject = localContext.getPackageManager().getLaunchIntentForPackage((String)localObject);
    if (localObject == null) {
      return;
    }
    ((Intent)localObject).addFlags(537001984);
    localContext.startActivity((Intent)localObject);
  }
  
  public abstract void onClick(Notification paramNotification, Bundle paramBundle);
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent().getExtras();
    Object localObject = getApplicationContext();
    if (paramBundle == null) {}
    do
    {
      return;
      int i = paramBundle.getInt("NOTIFICATION_ID");
      localObject = Manager.getInstance((Context)localObject).get(i);
    } while (localObject == null);
    onClick((Notification)localObject, paramBundle);
  }
  
  protected void onResume()
  {
    super.onResume();
    finish();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\notification\receiver\AbstractClickReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */