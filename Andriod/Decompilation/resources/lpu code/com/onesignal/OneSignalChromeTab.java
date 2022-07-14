package com.onesignal;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import java.util.Random;

class OneSignalChromeTab
{
  private static boolean opened;
  
  static void setup(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (opened) {}
    while ((OneSignal.mEnterp) || (paramString2 == null)) {
      return;
    }
    try
    {
      Class.forName("android.support.customtabs.CustomTabsServiceConnection");
      paramString2 = "?app_id=" + paramString1 + "&user_id=" + paramString2;
      paramString1 = paramString2;
      if (paramString3 != null) {
        paramString1 = paramString2 + "&ad_id=" + paramString3;
      }
      opened = CustomTabsClient.bindCustomTabsService(paramContext, "com.android.chrome", new OneSignalCustomTabsServiceConnection(paramContext, paramString1 + "&cbs_id=" + new Random().nextInt(Integer.MAX_VALUE)));
      return;
    }
    catch (ClassNotFoundException paramContext) {}
  }
  
  private static class OneSignalCustomTabsServiceConnection
    extends CustomTabsServiceConnection
  {
    private Context mContext;
    private String mParams;
    
    OneSignalCustomTabsServiceConnection(Context paramContext, String paramString)
    {
      this.mContext = paramContext;
      this.mParams = paramString;
    }
    
    public void onCustomTabsServiceConnected(ComponentName paramComponentName, CustomTabsClient paramCustomTabsClient)
    {
      if (paramCustomTabsClient == null) {}
      do
      {
        return;
        paramCustomTabsClient.warmup(0L);
        paramComponentName = paramCustomTabsClient.newSession(new CustomTabsCallback()
        {
          public void extraCallback(String paramAnonymousString, Bundle paramAnonymousBundle)
          {
            super.extraCallback(paramAnonymousString, paramAnonymousBundle);
          }
          
          public void onNavigationEvent(int paramAnonymousInt, Bundle paramAnonymousBundle)
          {
            super.onNavigationEvent(paramAnonymousInt, paramAnonymousBundle);
          }
        });
      } while (paramComponentName == null);
      paramComponentName.mayLaunchUrl(Uri.parse("https://onesignal.com/android_frame.html" + this.mParams), null, null);
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName) {}
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OneSignalChromeTab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */