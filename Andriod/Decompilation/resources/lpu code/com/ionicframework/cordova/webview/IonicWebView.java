package com.ionicframework.cordova.webview;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

public class IonicWebView
  extends CordovaPlugin
{
  public static final String CDV_SERVER_PATH = "serverBasePath";
  public static final String WEBVIEW_PREFS_NAME = "WebViewSettings";
  
  public boolean execute(final String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    if (paramString.equals("setServerBasePath"))
    {
      paramString = paramJSONArray.getString(0);
      this.cordova.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          ((IonicWebViewEngine)IonicWebView.this.webView.getEngine()).setServerBasePath(paramString);
        }
      });
      return true;
    }
    if (paramString.equals("getServerBasePath"))
    {
      paramCallbackContext.success(((IonicWebViewEngine)this.webView.getEngine()).getServerBasePath());
      return true;
    }
    if (paramString.equals("persistServerBasePath"))
    {
      paramString = ((IonicWebViewEngine)this.webView.getEngine()).getServerBasePath();
      paramJSONArray = this.cordova.getActivity().getApplicationContext().getSharedPreferences("WebViewSettings", 0).edit();
      paramJSONArray.putString("serverBasePath", paramString);
      paramJSONArray.apply();
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\ionicframework\cordova\webview\IonicWebView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */