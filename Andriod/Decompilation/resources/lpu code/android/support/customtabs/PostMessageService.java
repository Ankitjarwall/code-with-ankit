package android.support.customtabs;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class PostMessageService
  extends Service
{
  private IPostMessageService.Stub mBinder = new IPostMessageService.Stub()
  {
    public void onMessageChannelReady(ICustomTabsCallback paramAnonymousICustomTabsCallback, Bundle paramAnonymousBundle)
      throws RemoteException
    {
      paramAnonymousICustomTabsCallback.onMessageChannelReady(paramAnonymousBundle);
    }
    
    public void onPostMessage(ICustomTabsCallback paramAnonymousICustomTabsCallback, String paramAnonymousString, Bundle paramAnonymousBundle)
      throws RemoteException
    {
      paramAnonymousICustomTabsCallback.onPostMessage(paramAnonymousString, paramAnonymousBundle);
    }
  };
  
  public IBinder onBind(Intent paramIntent)
  {
    return this.mBinder;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\customtabs\PostMessageService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */