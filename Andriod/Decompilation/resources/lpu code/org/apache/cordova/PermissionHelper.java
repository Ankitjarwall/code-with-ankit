package org.apache.cordova;

import java.util.Arrays;
import org.json.JSONException;

public class PermissionHelper
{
  private static final String LOG_TAG = "CordovaPermissionHelper";
  
  private static void deliverPermissionResult(CordovaPlugin paramCordovaPlugin, int paramInt, String[] paramArrayOfString)
  {
    int[] arrayOfInt = new int[paramArrayOfString.length];
    Arrays.fill(arrayOfInt, 0);
    try
    {
      paramCordovaPlugin.onRequestPermissionResult(paramInt, paramArrayOfString, arrayOfInt);
      return;
    }
    catch (JSONException paramCordovaPlugin)
    {
      LOG.e("CordovaPermissionHelper", "JSONException when delivering permissions results", paramCordovaPlugin);
    }
  }
  
  public static boolean hasPermission(CordovaPlugin paramCordovaPlugin, String paramString)
  {
    return paramCordovaPlugin.cordova.hasPermission(paramString);
  }
  
  public static void requestPermission(CordovaPlugin paramCordovaPlugin, int paramInt, String paramString)
  {
    requestPermissions(paramCordovaPlugin, paramInt, new String[] { paramString });
  }
  
  public static void requestPermissions(CordovaPlugin paramCordovaPlugin, int paramInt, String[] paramArrayOfString)
  {
    paramCordovaPlugin.cordova.requestPermissions(paramCordovaPlugin, paramInt, paramArrayOfString);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\PermissionHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */