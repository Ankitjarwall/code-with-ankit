package org.apache.cordova.geolocation;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PermissionHelper;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

public class Geolocation
  extends CordovaPlugin
{
  String TAG = "GeolocationPlugin";
  CallbackContext context;
  String[] permissions = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" };
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    LOG.d(this.TAG, "We are entering execute");
    this.context = paramCallbackContext;
    if (paramString.equals("getPermission"))
    {
      if (hasPermisssion())
      {
        paramString = new PluginResult(PluginResult.Status.OK);
        this.context.sendPluginResult(paramString);
        return true;
      }
      PermissionHelper.requestPermissions(this, 0, this.permissions);
      return true;
    }
    return false;
  }
  
  public boolean hasPermisssion()
  {
    String[] arrayOfString = this.permissions;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (!PermissionHelper.hasPermission(this, arrayOfString[i])) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public void onRequestPermissionResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
    throws JSONException
  {
    int i;
    if (this.context != null)
    {
      i = paramArrayOfInt.length;
      paramInt = 0;
    }
    while (paramInt < i)
    {
      if (paramArrayOfInt[paramInt] == -1)
      {
        LOG.d(this.TAG, "Permission Denied!");
        paramArrayOfString = new PluginResult(PluginResult.Status.ILLEGAL_ACCESS_EXCEPTION);
        this.context.sendPluginResult(paramArrayOfString);
        return;
      }
      paramInt += 1;
    }
    paramArrayOfString = new PluginResult(PluginResult.Status.OK);
    this.context.sendPluginResult(paramArrayOfString);
  }
  
  public void requestPermissions(int paramInt)
  {
    PermissionHelper.requestPermissions(this, paramInt, this.permissions);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\geolocation\Geolocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */