package com.gae.scaffolder.plugin;

import android.app.Activity;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FCMPlugin
  extends CordovaPlugin
{
  private static final String TAG = "FCMPlugin";
  public static CordovaWebView gWebView;
  public static Map<String, Object> lastPush = null;
  public static String notificationCallBack = "FCMPlugin.onNotificationReceived";
  public static Boolean notificationCallBackReady;
  public static String tokenRefreshCallBack = "FCMPlugin.onTokenRefreshReceived";
  
  static
  {
    notificationCallBackReady = Boolean.valueOf(false);
  }
  
  public static void sendPushPayload(Map<String, Object> paramMap)
  {
    Log.d("FCMPlugin", "==> FCMPlugin sendPushPayload");
    Log.d("FCMPlugin", "\tnotificationCallBackReady: " + notificationCallBackReady);
    Log.d("FCMPlugin", "\tgWebView: " + gWebView);
    try
    {
      JSONObject localJSONObject = new JSONObject();
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str2 = (String)localIterator.next();
        localJSONObject.put(str2, paramMap.get(str2));
        Log.d("FCMPlugin", "\tpayload: " + str2 + " => " + paramMap.get(str2));
      }
      str1 = "javascript:" + notificationCallBack + "(" + localException.toString() + ")";
    }
    catch (Exception localException)
    {
      Log.d("FCMPlugin", "\tERROR sendPushToView. SAVED NOTIFICATION: " + localException.getMessage());
      lastPush = paramMap;
      return;
    }
    String str1;
    if ((notificationCallBackReady.booleanValue()) && (gWebView != null))
    {
      Log.d("FCMPlugin", "\tSent PUSH to view: " + str1);
      gWebView.sendJavascript(str1);
      return;
    }
    Log.d("FCMPlugin", "\tView not ready. SAVED NOTIFICATION: " + str1);
    lastPush = paramMap;
  }
  
  public static void sendTokenRefresh(String paramString)
  {
    Log.d("FCMPlugin", "==> FCMPlugin sendRefreshToken");
    try
    {
      paramString = "javascript:" + tokenRefreshCallBack + "('" + paramString + "')";
      gWebView.sendJavascript(paramString);
      return;
    }
    catch (Exception paramString)
    {
      Log.d("FCMPlugin", "\tERROR sendRefreshToken: " + paramString.getMessage());
    }
  }
  
  public boolean execute(String paramString, final JSONArray paramJSONArray, final CallbackContext paramCallbackContext)
    throws JSONException
  {
    Log.d("FCMPlugin", "==> FCMPlugin execute: " + paramString);
    try
    {
      if (paramString.equals("ready")) {
        paramCallbackContext.success();
      } else if (paramString.equals("getToken")) {
        this.cordova.getActivity().runOnUiThread(new Runnable()
        {
          public void run()
          {
            try
            {
              String str = FirebaseInstanceId.getInstance().getToken();
              paramCallbackContext.success(FirebaseInstanceId.getInstance().getToken());
              Log.d("FCMPlugin", "\tToken: " + str);
              return;
            }
            catch (Exception localException)
            {
              Log.d("FCMPlugin", "\tError retrieving token");
            }
          }
        });
      }
    }
    catch (Exception paramString)
    {
      Log.d("FCMPlugin", "ERROR: onPluginAction: " + paramString.getMessage());
      paramCallbackContext.error(paramString.getMessage());
      return false;
    }
    if (paramString.equals("registerNotification"))
    {
      notificationCallBackReady = Boolean.valueOf(true);
      this.cordova.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (FCMPlugin.lastPush != null) {
            FCMPlugin.sendPushPayload(FCMPlugin.lastPush);
          }
          FCMPlugin.lastPush = null;
          paramCallbackContext.success();
        }
      });
    }
    else if (paramString.equals("subscribeToTopic"))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          try
          {
            FirebaseMessaging.getInstance().subscribeToTopic(paramJSONArray.getString(0));
            paramCallbackContext.success();
            return;
          }
          catch (Exception localException)
          {
            paramCallbackContext.error(localException.getMessage());
          }
        }
      });
    }
    else if (paramString.equals("unsubscribeFromTopic"))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          try
          {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(paramJSONArray.getString(0));
            paramCallbackContext.success();
            return;
          }
          catch (Exception localException)
          {
            paramCallbackContext.error(localException.getMessage());
          }
        }
      });
    }
    else
    {
      paramCallbackContext.error("Method not found");
      return false;
    }
    return true;
  }
  
  public void initialize(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView)
  {
    super.initialize(paramCordovaInterface, paramCordovaWebView);
    gWebView = paramCordovaWebView;
    Log.d("FCMPlugin", "==> FCMPlugin initialize");
    FirebaseMessaging.getInstance().subscribeToTopic("android");
    FirebaseMessaging.getInstance().subscribeToTopic("all");
  }
  
  public void onDestroy()
  {
    gWebView = null;
    notificationCallBackReady = Boolean.valueOf(false);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\gae\scaffolder\plugin\FCMPlugin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */