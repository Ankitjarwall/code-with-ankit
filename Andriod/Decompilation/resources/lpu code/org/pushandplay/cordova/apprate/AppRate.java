package org.pushandplay.cordova.apprate;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class AppRate
  extends CordovaPlugin
{
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    bool = false;
    try
    {
      paramJSONArray = this.cordova.getActivity().getPackageManager();
      if (paramString.equals("getAppVersion"))
      {
        paramCallbackContext.success(paramJSONArray.getPackageInfo(this.cordova.getActivity().getPackageName(), 0).versionName);
        return true;
      }
      if (paramString.equals("getAppTitle"))
      {
        paramString = paramJSONArray.getApplicationInfo(this.cordova.getActivity().getApplicationContext().getApplicationInfo().packageName, 0);
        if (paramString != null) {}
        for (paramString = paramJSONArray.getApplicationLabel(paramString);; paramString = "Unknown")
        {
          paramCallbackContext.success((String)paramString);
          return true;
        }
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramCallbackContext.success("N/A");
      bool = true;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\pushandplay\cordova\apprate\AppRate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */