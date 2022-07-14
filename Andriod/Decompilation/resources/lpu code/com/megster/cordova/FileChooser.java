package com.megster.cordova;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FileChooser
  extends CordovaPlugin
{
  private static final String ACTION_OPEN = "open";
  public static final String MIME = "mime";
  private static final int PICK_FILE_REQUEST = 1;
  private static final String TAG = "FileChooser";
  CallbackContext callback;
  
  public void chooseFile(JSONObject paramJSONObject, CallbackContext paramCallbackContext)
  {
    if (paramJSONObject.has("mime")) {}
    for (paramJSONObject = paramJSONObject.optString("mime");; paramJSONObject = "*/*")
    {
      Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
      localIntent.setType(paramJSONObject);
      localIntent.addCategory("android.intent.category.OPENABLE");
      localIntent.putExtra("android.intent.extra.LOCAL_ONLY", true);
      paramJSONObject = Intent.createChooser(localIntent, "Select File");
      this.cordova.startActivityForResult(this, paramJSONObject, 1);
      paramJSONObject = new PluginResult(PluginResult.Status.NO_RESULT);
      paramJSONObject.setKeepCallback(true);
      this.callback = paramCallbackContext;
      paramCallbackContext.sendPluginResult(paramJSONObject);
      return;
    }
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    boolean bool = false;
    if (paramString.equals("open"))
    {
      chooseFile(paramJSONArray.optJSONObject(0), paramCallbackContext);
      bool = true;
    }
    return bool;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 1) && (this.callback != null))
    {
      if (paramInt2 != -1) {
        break label58;
      }
      paramIntent = paramIntent.getData();
      if (paramIntent != null)
      {
        Log.w("FileChooser", paramIntent.toString());
        this.callback.success(paramIntent.toString());
      }
    }
    else
    {
      return;
    }
    this.callback.error("File uri was null");
    return;
    label58:
    if (paramInt2 == 0)
    {
      this.callback.error("User canceled.");
      return;
    }
    this.callback.error(paramInt2);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\megster\cordova\FileChooser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */