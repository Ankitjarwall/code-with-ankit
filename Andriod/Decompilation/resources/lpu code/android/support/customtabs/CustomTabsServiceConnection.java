package android.support.customtabs;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

public abstract class CustomTabsServiceConnection
  implements ServiceConnection
{
  public abstract void onCustomTabsServiceConnected(ComponentName paramComponentName, CustomTabsClient paramCustomTabsClient);
  
  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    onCustomTabsServiceConnected(paramComponentName, new CustomTabsClient(ICustomTabsService.Stub.asInterface(paramIBinder), paramComponentName) {});
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\customtabs\CustomTabsServiceConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */