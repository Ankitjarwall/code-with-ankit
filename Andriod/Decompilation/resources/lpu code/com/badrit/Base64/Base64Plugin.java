package com.badrit.Base64;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Base64;
import java.io.File;
import java.io.FileInputStream;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Base64Plugin
  extends CordovaPlugin
{
  private String encodeFile(String paramString)
  {
    String str2 = "";
    String str1 = str2;
    try
    {
      paramString = Uri.parse(paramString);
      if (paramString != null)
      {
        str1 = str2;
        if ("content".equals(paramString.getScheme()))
        {
          str1 = str2;
          localObject = this.cordova.getActivity().getContentResolver().query(paramString, new String[] { "_data" }, null, null, null);
          str1 = str2;
          ((Cursor)localObject).moveToFirst();
          str1 = str2;
          paramString = ((Cursor)localObject).getString(0);
          str1 = str2;
          ((Cursor)localObject).close();
        }
      }
      for (;;)
      {
        str1 = str2;
        paramString = new File(paramString);
        str1 = str2;
        if (paramString.exists()) {
          break;
        }
        return "";
        str1 = str2;
        paramString = paramString.getPath();
      }
      str1 = str2;
      Object localObject = new byte[(int)paramString.length()];
      str1 = str2;
      new FileInputStream(paramString).read((byte[])localObject);
      str1 = str2;
      paramString = Base64.encodeToString((byte[])localObject, 0);
      str1 = paramString;
      paramString = "data:image/*;charset=utf-8;base64," + paramString;
      return paramString;
    }
    catch (Exception paramString) {}
    return str1;
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    boolean bool = false;
    if ("encodeFile".equals(paramString)) {}
    try
    {
      paramString = paramJSONArray.getJSONObject(0);
      if (paramString != null) {
        paramCallbackContext.success(encodeFile(paramString.getString("filePath")));
      }
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    bool = true;
    return bool;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\badrit\Base64\Base64Plugin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */