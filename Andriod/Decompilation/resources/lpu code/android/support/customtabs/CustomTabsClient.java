package android.support.customtabs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomTabsClient
{
  private final ICustomTabsService mService;
  private final ComponentName mServiceComponentName;
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  CustomTabsClient(ICustomTabsService paramICustomTabsService, ComponentName paramComponentName)
  {
    this.mService = paramICustomTabsService;
    this.mServiceComponentName = paramComponentName;
  }
  
  public static boolean bindCustomTabsService(Context paramContext, String paramString, CustomTabsServiceConnection paramCustomTabsServiceConnection)
  {
    Intent localIntent = new Intent("android.support.customtabs.action.CustomTabsService");
    if (!TextUtils.isEmpty(paramString)) {
      localIntent.setPackage(paramString);
    }
    return paramContext.bindService(localIntent, paramCustomTabsServiceConnection, 33);
  }
  
  public static boolean connectAndInitialize(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return false;
    }
    paramContext = paramContext.getApplicationContext();
    CustomTabsServiceConnection local1 = new CustomTabsServiceConnection()
    {
      public final void onCustomTabsServiceConnected(ComponentName paramAnonymousComponentName, CustomTabsClient paramAnonymousCustomTabsClient)
      {
        paramAnonymousCustomTabsClient.warmup(0L);
        this.val$applicationContext.unbindService(this);
      }
      
      public final void onServiceDisconnected(ComponentName paramAnonymousComponentName) {}
    };
    try
    {
      boolean bool = bindCustomTabsService(paramContext, paramString, local1);
      return bool;
    }
    catch (SecurityException paramContext) {}
    return false;
  }
  
  public static String getPackageName(Context paramContext, @Nullable List<String> paramList)
  {
    return getPackageName(paramContext, paramList, false);
  }
  
  public static String getPackageName(Context paramContext, @Nullable List<String> paramList, boolean paramBoolean)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    if (paramList == null) {}
    for (paramContext = new ArrayList();; paramContext = paramList)
    {
      Object localObject2 = new Intent("android.intent.action.VIEW", Uri.parse("http://"));
      Object localObject1 = paramContext;
      if (!paramBoolean)
      {
        localObject2 = localPackageManager.resolveActivity((Intent)localObject2, 0);
        localObject1 = paramContext;
        if (localObject2 != null)
        {
          localObject2 = ((ResolveInfo)localObject2).activityInfo.packageName;
          localObject1 = new ArrayList(paramContext.size() + 1);
          ((List)localObject1).add(localObject2);
          if (paramList != null) {
            ((List)localObject1).addAll(paramList);
          }
        }
      }
      paramContext = new Intent("android.support.customtabs.action.CustomTabsService");
      paramList = ((List)localObject1).iterator();
      do
      {
        if (!paramList.hasNext()) {
          break;
        }
        localObject1 = (String)paramList.next();
        paramContext.setPackage((String)localObject1);
      } while (localPackageManager.resolveService(paramContext, 0) == null);
      return (String)localObject1;
    }
    return null;
  }
  
  public Bundle extraCommand(String paramString, Bundle paramBundle)
  {
    try
    {
      paramString = this.mService.extraCommand(paramString, paramBundle);
      return paramString;
    }
    catch (RemoteException paramString) {}
    return null;
  }
  
  public CustomTabsSession newSession(final CustomTabsCallback paramCustomTabsCallback)
  {
    paramCustomTabsCallback = new ICustomTabsCallback.Stub()
    {
      private Handler mHandler = new Handler(Looper.getMainLooper());
      
      public void extraCallback(final String paramAnonymousString, final Bundle paramAnonymousBundle)
        throws RemoteException
      {
        if (paramCustomTabsCallback == null) {
          return;
        }
        this.mHandler.post(new Runnable()
        {
          public void run()
          {
            CustomTabsClient.2.this.val$callback.extraCallback(paramAnonymousString, paramAnonymousBundle);
          }
        });
      }
      
      public void onMessageChannelReady(final Bundle paramAnonymousBundle)
        throws RemoteException
      {
        if (paramCustomTabsCallback == null) {
          return;
        }
        this.mHandler.post(new Runnable()
        {
          public void run()
          {
            CustomTabsClient.2.this.val$callback.onMessageChannelReady(paramAnonymousBundle);
          }
        });
      }
      
      public void onNavigationEvent(final int paramAnonymousInt, final Bundle paramAnonymousBundle)
      {
        if (paramCustomTabsCallback == null) {
          return;
        }
        this.mHandler.post(new Runnable()
        {
          public void run()
          {
            CustomTabsClient.2.this.val$callback.onNavigationEvent(paramAnonymousInt, paramAnonymousBundle);
          }
        });
      }
      
      public void onPostMessage(final String paramAnonymousString, final Bundle paramAnonymousBundle)
        throws RemoteException
      {
        if (paramCustomTabsCallback == null) {
          return;
        }
        this.mHandler.post(new Runnable()
        {
          public void run()
          {
            CustomTabsClient.2.this.val$callback.onPostMessage(paramAnonymousString, paramAnonymousBundle);
          }
        });
      }
      
      public void onRelationshipValidationResult(final int paramAnonymousInt, final Uri paramAnonymousUri, final boolean paramAnonymousBoolean, @Nullable final Bundle paramAnonymousBundle)
        throws RemoteException
      {
        if (paramCustomTabsCallback == null) {
          return;
        }
        this.mHandler.post(new Runnable()
        {
          public void run()
          {
            CustomTabsClient.2.this.val$callback.onRelationshipValidationResult(paramAnonymousInt, paramAnonymousUri, paramAnonymousBoolean, paramAnonymousBundle);
          }
        });
      }
    };
    try
    {
      boolean bool = this.mService.newSession(paramCustomTabsCallback);
      if (!bool) {
        return null;
      }
    }
    catch (RemoteException paramCustomTabsCallback)
    {
      return null;
    }
    return new CustomTabsSession(this.mService, paramCustomTabsCallback, this.mServiceComponentName);
  }
  
  public boolean warmup(long paramLong)
  {
    try
    {
      boolean bool = this.mService.warmup(paramLong);
      return bool;
    }
    catch (RemoteException localRemoteException) {}
    return false;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\customtabs\CustomTabsClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */