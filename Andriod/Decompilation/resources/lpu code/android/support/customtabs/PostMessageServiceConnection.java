package android.support.customtabs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public abstract class PostMessageServiceConnection
  implements ServiceConnection
{
  private final Object mLock = new Object();
  private IPostMessageService mService;
  private final ICustomTabsCallback mSessionBinder;
  
  public PostMessageServiceConnection(CustomTabsSessionToken paramCustomTabsSessionToken)
  {
    this.mSessionBinder = ICustomTabsCallback.Stub.asInterface(paramCustomTabsSessionToken.getCallbackBinder());
  }
  
  public boolean bindSessionToPostMessageService(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setClassName(paramString, PostMessageService.class.getName());
    return paramContext.bindService(localIntent, this, 1);
  }
  
  public final boolean notifyMessageChannelReady(Bundle paramBundle)
  {
    if (this.mService == null) {
      return false;
    }
    synchronized (this.mLock)
    {
      try
      {
        this.mService.onMessageChannelReady(this.mSessionBinder, paramBundle);
        return true;
      }
      catch (RemoteException paramBundle)
      {
        return false;
      }
    }
  }
  
  public void onPostMessageServiceConnected() {}
  
  public void onPostMessageServiceDisconnected() {}
  
  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    this.mService = IPostMessageService.Stub.asInterface(paramIBinder);
    onPostMessageServiceConnected();
  }
  
  public final void onServiceDisconnected(ComponentName paramComponentName)
  {
    this.mService = null;
    onPostMessageServiceDisconnected();
  }
  
  public final boolean postMessage(String paramString, Bundle paramBundle)
  {
    if (this.mService == null) {
      return false;
    }
    synchronized (this.mLock)
    {
      try
      {
        this.mService.onPostMessage(this.mSessionBinder, paramString, paramBundle);
        return true;
      }
      catch (RemoteException paramString)
      {
        return false;
      }
    }
  }
  
  public void unbindFromContext(Context paramContext)
  {
    paramContext.unbindService(this);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\customtabs\PostMessageServiceConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */