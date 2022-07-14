package android.support.customtabs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.app.BundleCompat;
import android.util.Log;

public class CustomTabsSessionToken
{
  private static final String TAG = "CustomTabsSessionToken";
  private final CustomTabsCallback mCallback;
  private final ICustomTabsCallback mCallbackBinder;
  
  CustomTabsSessionToken(ICustomTabsCallback paramICustomTabsCallback)
  {
    this.mCallbackBinder = paramICustomTabsCallback;
    this.mCallback = new CustomTabsCallback()
    {
      public void extraCallback(String paramAnonymousString, Bundle paramAnonymousBundle)
      {
        try
        {
          CustomTabsSessionToken.this.mCallbackBinder.extraCallback(paramAnonymousString, paramAnonymousBundle);
          return;
        }
        catch (RemoteException paramAnonymousString)
        {
          Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
        }
      }
      
      public void onMessageChannelReady(Bundle paramAnonymousBundle)
      {
        try
        {
          CustomTabsSessionToken.this.mCallbackBinder.onMessageChannelReady(paramAnonymousBundle);
          return;
        }
        catch (RemoteException paramAnonymousBundle)
        {
          Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
        }
      }
      
      public void onNavigationEvent(int paramAnonymousInt, Bundle paramAnonymousBundle)
      {
        try
        {
          CustomTabsSessionToken.this.mCallbackBinder.onNavigationEvent(paramAnonymousInt, paramAnonymousBundle);
          return;
        }
        catch (RemoteException paramAnonymousBundle)
        {
          Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
        }
      }
      
      public void onPostMessage(String paramAnonymousString, Bundle paramAnonymousBundle)
      {
        try
        {
          CustomTabsSessionToken.this.mCallbackBinder.onPostMessage(paramAnonymousString, paramAnonymousBundle);
          return;
        }
        catch (RemoteException paramAnonymousString)
        {
          Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
        }
      }
      
      public void onRelationshipValidationResult(int paramAnonymousInt, Uri paramAnonymousUri, boolean paramAnonymousBoolean, Bundle paramAnonymousBundle)
      {
        try
        {
          CustomTabsSessionToken.this.mCallbackBinder.onRelationshipValidationResult(paramAnonymousInt, paramAnonymousUri, paramAnonymousBoolean, paramAnonymousBundle);
          return;
        }
        catch (RemoteException paramAnonymousUri)
        {
          Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
        }
      }
    };
  }
  
  @NonNull
  public static CustomTabsSessionToken createMockSessionTokenForTesting()
  {
    return new CustomTabsSessionToken(new MockCallback());
  }
  
  public static CustomTabsSessionToken getSessionTokenFromIntent(Intent paramIntent)
  {
    paramIntent = BundleCompat.getBinder(paramIntent.getExtras(), "android.support.customtabs.extra.SESSION");
    if (paramIntent == null) {
      return null;
    }
    return new CustomTabsSessionToken(ICustomTabsCallback.Stub.asInterface(paramIntent));
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof CustomTabsSessionToken)) {
      return false;
    }
    return ((CustomTabsSessionToken)paramObject).getCallbackBinder().equals(this.mCallbackBinder.asBinder());
  }
  
  public CustomTabsCallback getCallback()
  {
    return this.mCallback;
  }
  
  IBinder getCallbackBinder()
  {
    return this.mCallbackBinder.asBinder();
  }
  
  public int hashCode()
  {
    return getCallbackBinder().hashCode();
  }
  
  public boolean isAssociatedWith(CustomTabsSession paramCustomTabsSession)
  {
    return paramCustomTabsSession.getBinder().equals(this.mCallbackBinder);
  }
  
  static class MockCallback
    extends ICustomTabsCallback.Stub
  {
    public IBinder asBinder()
    {
      return this;
    }
    
    public void extraCallback(String paramString, Bundle paramBundle) {}
    
    public void onMessageChannelReady(Bundle paramBundle) {}
    
    public void onNavigationEvent(int paramInt, Bundle paramBundle) {}
    
    public void onPostMessage(String paramString, Bundle paramBundle) {}
    
    public void onRelationshipValidationResult(int paramInt, Uri paramUri, boolean paramBoolean, Bundle paramBundle) {}
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\customtabs\CustomTabsSessionToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */