package com.sarriaroman.PhotoViewer;

import android.app.Activity;
import android.content.Intent;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

public class PhotoViewer
  extends CordovaPlugin
{
  public static final int PERMISSION_DENIED_ERROR = 20;
  public static final String READ = "android.permission.READ_EXTERNAL_STORAGE";
  public static final int REQ_CODE = 0;
  public static final String WRITE = "android.permission.WRITE_EXTERNAL_STORAGE";
  protected JSONArray args;
  protected CallbackContext callbackContext;
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    if (paramString.equals("show"))
    {
      this.args = paramJSONArray;
      this.callbackContext = paramCallbackContext;
      if ((this.cordova.hasPermission("android.permission.READ_EXTERNAL_STORAGE")) && (this.cordova.hasPermission("android.permission.WRITE_EXTERNAL_STORAGE"))) {
        launchActivity();
      }
      for (;;)
      {
        return true;
        getPermission();
      }
    }
    return false;
  }
  
  protected void getPermission()
  {
    this.cordova.requestPermissions(this, 0, new String[] { "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE" });
  }
  
  protected void launchActivity()
    throws JSONException
  {
    Intent localIntent = new Intent(this.cordova.getActivity(), PhotoActivity.class);
    PhotoActivity.mArgs = this.args;
    this.cordova.getActivity().startActivity(localIntent);
    this.callbackContext.success("");
  }
  
  public void onRequestPermissionResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
    throws JSONException
  {
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      if (paramArrayOfInt[i] == -1)
      {
        this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, 20));
        return;
      }
      i += 1;
    }
    switch (paramInt)
    {
    default: 
      return;
    }
    launchActivity();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\sarriaroman\PhotoViewer\PhotoViewer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */