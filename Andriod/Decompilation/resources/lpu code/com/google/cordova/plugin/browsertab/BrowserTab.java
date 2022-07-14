package com.google.cordova.plugin.browsertab;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsIntent.Builder;
import android.util.Log;
import java.util.Iterator;
import java.util.List;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

public class BrowserTab
  extends CordovaPlugin
{
  private static final String ACTION_CUSTOM_TABS_CONNECTION = "android.support.customtabs.action.CustomTabsService";
  private static final String LOG_TAG = "BrowserTab";
  public static final int RC_OPEN_URL = 101;
  private String mCustomTabsBrowser;
  private boolean mFindCalled = false;
  
  private String findCustomTabBrowser()
  {
    if (this.mFindCalled) {
      return this.mCustomTabsBrowser;
    }
    PackageManager localPackageManager = this.cordova.getActivity().getPackageManager();
    Iterator localIterator = localPackageManager.queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com")), 64).iterator();
    while (localIterator.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)localIterator.next();
      if ((isFullBrowser(localResolveInfo)) && (hasCustomTabWarmupService(localPackageManager, localResolveInfo.activityInfo.packageName))) {
        this.mCustomTabsBrowser = localResolveInfo.activityInfo.packageName;
      }
    }
    this.mFindCalled = true;
    return this.mCustomTabsBrowser;
  }
  
  private boolean hasCustomTabWarmupService(PackageManager paramPackageManager, String paramString)
  {
    boolean bool = false;
    Intent localIntent = new Intent();
    localIntent.setAction("android.support.customtabs.action.CustomTabsService");
    localIntent.setPackage(paramString);
    if (paramPackageManager.resolveService(localIntent, 0) != null) {
      bool = true;
    }
    return bool;
  }
  
  private void isAvailable(CallbackContext paramCallbackContext)
  {
    String str = findCustomTabBrowser();
    Log.d("BrowserTab", "browser package: " + str);
    PluginResult.Status localStatus = PluginResult.Status.OK;
    if (str != null) {}
    for (boolean bool = true;; bool = false)
    {
      paramCallbackContext.sendPluginResult(new PluginResult(localStatus, bool));
      return;
    }
  }
  
  private boolean isFullBrowser(ResolveInfo paramResolveInfo)
  {
    if ((!paramResolveInfo.filter.hasAction("android.intent.action.VIEW")) || (!paramResolveInfo.filter.hasCategory("android.intent.category.BROWSABLE")) || (paramResolveInfo.filter.schemesIterator() == null)) {}
    boolean bool2;
    do
    {
      boolean bool1;
      do
      {
        while (!paramResolveInfo.hasNext())
        {
          do
          {
            return false;
          } while (paramResolveInfo.filter.authoritiesIterator() != null);
          j = 0;
          i = 0;
          paramResolveInfo = paramResolveInfo.filter.schemesIterator();
        }
        String str = (String)paramResolveInfo.next();
        bool1 = j | "http".equals(str);
        bool2 = i | "https".equals(str);
        j = bool1;
        i = bool2;
      } while (!bool1);
      int j = bool1;
      int i = bool2;
    } while (!bool2);
    return true;
  }
  
  private void openUrl(JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    if (paramJSONArray.length() < 1)
    {
      Log.d("BrowserTab", "openUrl: no url argument received");
      paramCallbackContext.error("URL argument missing");
      return;
    }
    try
    {
      paramJSONArray = paramJSONArray.getString(0);
      if (findCustomTabBrowser() == null)
      {
        Log.d("BrowserTab", "openUrl: no in app browser tab available");
        paramCallbackContext.error("no in app browser tab implementation available");
      }
      Intent localIntent = new CustomTabsIntent.Builder().build().intent;
      localIntent.setData(Uri.parse(paramJSONArray));
      localIntent.setPackage(this.mCustomTabsBrowser);
      this.cordova.getActivity().startActivity(localIntent);
      Log.d("BrowserTab", "in app browser call dispatched");
      paramCallbackContext.success();
      return;
    }
    catch (JSONException paramJSONArray)
    {
      Log.d("BrowserTab", "openUrl: failed to parse url argument");
      paramCallbackContext.error("URL argument is not a string");
    }
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    Log.d("BrowserTab", "executing " + paramString);
    if ("isAvailable".equals(paramString)) {
      isAvailable(paramCallbackContext);
    }
    do
    {
      return true;
      if ("openUrl".equals(paramString))
      {
        openUrl(paramJSONArray, paramCallbackContext);
        return true;
      }
    } while ("close".equals(paramString));
    return false;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\cordova\plugin\browsertab\BrowserTab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */