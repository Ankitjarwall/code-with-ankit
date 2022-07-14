package com.plugin.gcm;

import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.onesignal.OSEmailSubscriptionObserver;
import com.onesignal.OSEmailSubscriptionStateChanges;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OSPermissionObserver;
import com.onesignal.OSPermissionStateChanges;
import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OSSubscriptionObserver;
import com.onesignal.OSSubscriptionStateChanges;
import com.onesignal.OneSignal;
import com.onesignal.OneSignal.Builder;
import com.onesignal.OneSignal.EmailUpdateError;
import com.onesignal.OneSignal.EmailUpdateHandler;
import com.onesignal.OneSignal.GetTagsHandler;
import com.onesignal.OneSignal.IdsAvailableHandler;
import com.onesignal.OneSignal.NotificationOpenedHandler;
import com.onesignal.OneSignal.NotificationReceivedHandler;
import com.onesignal.OneSignal.PostNotificationResponseHandler;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OneSignalPush
  extends CordovaPlugin
{
  private static final String ADD_EMAIL_SUBSCRIPTION_OBSERVER = "addEmailSubscriptionObserver";
  private static final String ADD_PERMISSION_OBSERVER = "addPermissionObserver";
  private static final String ADD_SUBSCRIPTION_OBSERVER = "addSubscriptionObserver";
  private static final String CLEAR_ONESIGNAL_NOTIFICATIONS = "clearOneSignalNotifications";
  private static final String DELETE_TAGS = "deleteTags";
  private static final String ENABLE_SOUND = "enableSound";
  private static final String ENABLE_VIBRATE = "enableVibrate";
  private static final String GET_IDS = "getIds";
  private static final String GET_PERMISSION_SUBCRIPTION_STATE = "getPermissionSubscriptionState";
  private static final String GET_TAGS = "getTags";
  private static final String GRANT_CONSENT = "provideUserConsent";
  private static final String INIT = "init";
  private static final String LOGOUT_EMAIL = "logoutEmail";
  private static final String POST_NOTIFICATION = "postNotification";
  private static final String PROMPT_LOCATION = "promptLocation";
  private static final String REGISTER_FOR_PUSH_NOTIFICATIONS = "registerForPushNotifications";
  private static final String SEND_TAGS = "sendTags";
  private static final String SET_EMAIL = "setEmail";
  private static final String SET_IN_FOCUS_DISPLAYING = "setInFocusDisplaying";
  private static final String SET_LOCATION_SHARED = "setLocationShared";
  private static final String SET_LOG_LEVEL = "setLogLevel";
  private static final String SET_NOTIFICATION_OPENED_HANDLER = "setNotificationOpenedHandler";
  private static final String SET_NOTIFICATION_RECEIVED_HANDLER = "setNotificationReceivedHandler";
  private static final String SET_REQUIRES_CONSENT = "setRequiresUserPrivacyConsent";
  private static final String SET_SUBSCRIPTION = "setSubscription";
  private static final String SET_UNAUTHENTICATED_EMAIL = "setUnauthenticatedEmail";
  private static final String SYNC_HASHED_EMAIL = "syncHashedEmail";
  private static final String TAG = "OneSignalPush";
  private static final String USER_PROVIDED_CONSENT = "userProvidedPrivacyConsent";
  private static OSEmailSubscriptionObserver emailSubscriptionObserver;
  private static CallbackContext jsEmailSubscriptionObserverCallBack;
  private static CallbackContext jsPermissionObserverCallBack;
  private static CallbackContext jsSubscriptionObserverCallBack;
  private static CallbackContext notifOpenedCallbackContext;
  private static CallbackContext notifReceivedCallbackContext;
  private static OSPermissionObserver permissionObserver;
  private static OSSubscriptionObserver subscriptionObserver;
  
  private static void callbackError(CallbackContext paramCallbackContext, String paramString)
  {
    paramString = new PluginResult(PluginResult.Status.ERROR, paramString);
    paramString.setKeepCallback(true);
    paramCallbackContext.sendPluginResult(paramString);
  }
  
  private static void callbackError(CallbackContext paramCallbackContext, JSONObject paramJSONObject)
  {
    JSONObject localJSONObject = paramJSONObject;
    if (paramJSONObject == null) {
      localJSONObject = new JSONObject();
    }
    paramJSONObject = new PluginResult(PluginResult.Status.ERROR, localJSONObject);
    paramJSONObject.setKeepCallback(true);
    paramCallbackContext.sendPluginResult(paramJSONObject);
  }
  
  private static void callbackSuccess(CallbackContext paramCallbackContext, JSONObject paramJSONObject)
  {
    JSONObject localJSONObject = paramJSONObject;
    if (paramJSONObject == null) {
      localJSONObject = new JSONObject();
    }
    paramJSONObject = new PluginResult(PluginResult.Status.OK, localJSONObject);
    paramJSONObject.setKeepCallback(true);
    paramCallbackContext.sendPluginResult(paramJSONObject);
  }
  
  private static void callbackSuccessBoolean(CallbackContext paramCallbackContext, boolean paramBoolean)
  {
    PluginResult localPluginResult = new PluginResult(PluginResult.Status.OK, paramBoolean);
    localPluginResult.setKeepCallback(true);
    paramCallbackContext.sendPluginResult(localPluginResult);
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, final CallbackContext paramCallbackContext)
  {
    if ("setNotificationReceivedHandler".equals(paramString))
    {
      notifReceivedCallbackContext = paramCallbackContext;
      return true;
    }
    if ("setNotificationOpenedHandler".equals(paramString))
    {
      notifOpenedCallbackContext = paramCallbackContext;
      return true;
    }
    if ("init".equals(paramString)) {
      try
      {
        paramString = paramJSONArray.getString(0);
        paramCallbackContext = paramJSONArray.getString(1);
        OneSignal.sdkType = "cordova";
        OneSignal.Builder localBuilder = OneSignal.getCurrentOrNewInitBuilder();
        localBuilder.unsubscribeWhenNotificationsAreDisabled(true);
        localBuilder.filterOtherGCMReceivers(true);
        OneSignal.init(this.cordova.getActivity(), paramCallbackContext, paramString, new CordovaNotificationOpenedHandler(notifOpenedCallbackContext), new CordovaNotificationReceivedHandler(notifReceivedCallbackContext));
        OneSignal.setInFocusDisplaying(paramJSONArray.getInt(3));
        return true;
      }
      catch (JSONException paramString)
      {
        Log.e("OneSignalPush", "execute: Got JSON Exception " + paramString.getMessage());
        return false;
      }
    }
    if ("setInFocusDisplaying".equals(paramString)) {
      try
      {
        OneSignal.setInFocusDisplaying(paramJSONArray.getInt(0));
        return true;
      }
      catch (JSONException paramString)
      {
        Log.e("OneSignalPush", "execute: Got JSON Exception " + paramString.getMessage());
        return false;
      }
    }
    if ("addPermissionObserver".equals(paramString))
    {
      jsPermissionObserverCallBack = paramCallbackContext;
      if (permissionObserver == null)
      {
        permissionObserver = new OSPermissionObserver()
        {
          public void onOSPermissionChanged(OSPermissionStateChanges paramAnonymousOSPermissionStateChanges)
          {
            OneSignalPush.callbackSuccess(OneSignalPush.jsPermissionObserverCallBack, paramAnonymousOSPermissionStateChanges.toJSONObject());
          }
        };
        OneSignal.addPermissionObserver(permissionObserver);
      }
      return true;
    }
    if ("addSubscriptionObserver".equals(paramString))
    {
      jsSubscriptionObserverCallBack = paramCallbackContext;
      if (subscriptionObserver == null)
      {
        subscriptionObserver = new OSSubscriptionObserver()
        {
          public void onOSSubscriptionChanged(OSSubscriptionStateChanges paramAnonymousOSSubscriptionStateChanges)
          {
            OneSignalPush.callbackSuccess(OneSignalPush.jsSubscriptionObserverCallBack, paramAnonymousOSSubscriptionStateChanges.toJSONObject());
          }
        };
        OneSignal.addSubscriptionObserver(subscriptionObserver);
      }
      return true;
    }
    if ("addEmailSubscriptionObserver".equals(paramString))
    {
      jsEmailSubscriptionObserverCallBack = paramCallbackContext;
      if (emailSubscriptionObserver == null)
      {
        emailSubscriptionObserver = new OSEmailSubscriptionObserver()
        {
          public void onOSEmailSubscriptionChanged(OSEmailSubscriptionStateChanges paramAnonymousOSEmailSubscriptionStateChanges)
          {
            OneSignalPush.callbackSuccess(OneSignalPush.jsEmailSubscriptionObserverCallBack, paramAnonymousOSEmailSubscriptionStateChanges.toJSONObject());
          }
        };
        OneSignal.addEmailSubscriptionObserver(emailSubscriptionObserver);
      }
      return true;
    }
    if ("getTags".equals(paramString))
    {
      OneSignal.getTags(new OneSignal.GetTagsHandler()
      {
        public void tagsAvailable(JSONObject paramAnonymousJSONObject)
        {
          OneSignalPush.callbackSuccess(paramCallbackContext, paramAnonymousJSONObject);
        }
      });
      return true;
    }
    if ("getPermissionSubscriptionState".equals(paramString))
    {
      callbackSuccess(paramCallbackContext, OneSignal.getPermissionSubscriptionState().toJSONObject());
      return true;
    }
    if ("getIds".equals(paramString))
    {
      OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler()
      {
        public void idsAvailable(String paramAnonymousString1, String paramAnonymousString2)
        {
          JSONObject localJSONObject = new JSONObject();
          try
          {
            localJSONObject.put("userId", paramAnonymousString1);
            if (paramAnonymousString2 != null) {
              localJSONObject.put("pushToken", paramAnonymousString2);
            }
            for (;;)
            {
              OneSignalPush.callbackSuccess(paramCallbackContext, localJSONObject);
              return;
              localJSONObject.put("pushToken", "");
            }
            return;
          }
          catch (Throwable paramAnonymousString1)
          {
            ThrowableExtension.printStackTrace(paramAnonymousString1);
          }
        }
      });
      return true;
    }
    if ("sendTags".equals(paramString)) {
      try
      {
        OneSignal.sendTags(paramJSONArray.getJSONObject(0));
        return true;
      }
      catch (Throwable paramString)
      {
        for (;;)
        {
          ThrowableExtension.printStackTrace(paramString);
        }
      }
    }
    if ("deleteTags".equals(paramString)) {
      try
      {
        paramString = new ArrayList();
        int i = 0;
        while (i < paramJSONArray.length())
        {
          paramString.add(paramJSONArray.get(i).toString());
          i += 1;
        }
        OneSignal.deleteTags(paramString);
        return true;
      }
      catch (Throwable paramString)
      {
        ThrowableExtension.printStackTrace(paramString);
        return false;
      }
    }
    if ("registerForPushNotifications".equals(paramString)) {
      return true;
    }
    if ("enableVibrate".equals(paramString)) {
      try
      {
        OneSignal.enableVibrate(paramJSONArray.getBoolean(0));
        return true;
      }
      catch (Throwable paramString)
      {
        ThrowableExtension.printStackTrace(paramString);
        return false;
      }
    }
    if ("enableSound".equals(paramString)) {
      try
      {
        OneSignal.enableSound(paramJSONArray.getBoolean(0));
        return true;
      }
      catch (Throwable paramString)
      {
        ThrowableExtension.printStackTrace(paramString);
        return false;
      }
    }
    if ("setSubscription".equals(paramString)) {
      try
      {
        OneSignal.setSubscription(paramJSONArray.getBoolean(0));
        return true;
      }
      catch (Throwable paramString)
      {
        ThrowableExtension.printStackTrace(paramString);
        return false;
      }
    }
    if ("postNotification".equals(paramString)) {
      try
      {
        OneSignal.postNotification(paramJSONArray.getJSONObject(0), new OneSignal.PostNotificationResponseHandler()
        {
          public void onFailure(JSONObject paramAnonymousJSONObject)
          {
            OneSignalPush.callbackError(paramCallbackContext, paramAnonymousJSONObject);
          }
          
          public void onSuccess(JSONObject paramAnonymousJSONObject)
          {
            OneSignalPush.callbackSuccess(paramCallbackContext, paramAnonymousJSONObject);
          }
        });
        return true;
      }
      catch (Throwable paramString)
      {
        ThrowableExtension.printStackTrace(paramString);
        return false;
      }
    }
    if ("promptLocation".equals(paramString))
    {
      OneSignal.promptLocation();
      return false;
    }
    if ("syncHashedEmail".equals(paramString)) {
      try
      {
        OneSignal.syncHashedEmail(paramJSONArray.getString(0));
        return false;
      }
      catch (Throwable paramString)
      {
        ThrowableExtension.printStackTrace(paramString);
        return false;
      }
    }
    if ("setLogLevel".equals(paramString)) {
      try
      {
        paramString = paramJSONArray.getJSONObject(0);
        OneSignal.setLogLevel(paramString.optInt("logLevel", 0), paramString.optInt("visualLevel", 0));
        return false;
      }
      catch (Throwable paramString)
      {
        ThrowableExtension.printStackTrace(paramString);
        return false;
      }
    }
    if ("clearOneSignalNotifications".equals(paramString)) {
      try
      {
        OneSignal.clearOneSignalNotifications();
        return true;
      }
      catch (Throwable paramString)
      {
        ThrowableExtension.printStackTrace(paramString);
        return false;
      }
    }
    if ("setEmail".equals(paramString)) {
      try
      {
        OneSignal.setEmail(paramJSONArray.getString(0), paramJSONArray.getString(1), new OneSignal.EmailUpdateHandler()
        {
          public void onFailure(OneSignal.EmailUpdateError paramAnonymousEmailUpdateError)
          {
            try
            {
              paramAnonymousEmailUpdateError = new JSONObject("{'error' : '" + paramAnonymousEmailUpdateError.getMessage() + "'}");
              OneSignalPush.callbackError(paramCallbackContext, paramAnonymousEmailUpdateError);
              return;
            }
            catch (JSONException paramAnonymousEmailUpdateError)
            {
              ThrowableExtension.printStackTrace(paramAnonymousEmailUpdateError);
            }
          }
          
          public void onSuccess()
          {
            OneSignalPush.callbackSuccess(paramCallbackContext, null);
          }
        });
        return true;
      }
      catch (Throwable paramString)
      {
        ThrowableExtension.printStackTrace(paramString);
        return false;
      }
    }
    if ("setUnauthenticatedEmail".equals(paramString)) {
      try
      {
        OneSignal.setEmail(paramJSONArray.getString(0), null, new OneSignal.EmailUpdateHandler()
        {
          public void onFailure(OneSignal.EmailUpdateError paramAnonymousEmailUpdateError)
          {
            try
            {
              paramAnonymousEmailUpdateError = new JSONObject("{'error' : '" + paramAnonymousEmailUpdateError.getMessage() + "'}");
              OneSignalPush.callbackError(paramCallbackContext, paramAnonymousEmailUpdateError);
              return;
            }
            catch (JSONException paramAnonymousEmailUpdateError)
            {
              ThrowableExtension.printStackTrace(paramAnonymousEmailUpdateError);
            }
          }
          
          public void onSuccess()
          {
            OneSignalPush.callbackSuccess(paramCallbackContext, null);
          }
        });
        return true;
      }
      catch (Throwable paramString)
      {
        ThrowableExtension.printStackTrace(paramString);
        return false;
      }
    }
    if ("logoutEmail".equals(paramString))
    {
      OneSignal.logoutEmail(new OneSignal.EmailUpdateHandler()
      {
        public void onFailure(OneSignal.EmailUpdateError paramAnonymousEmailUpdateError)
        {
          try
          {
            paramAnonymousEmailUpdateError = new JSONObject("{'error' : '" + paramAnonymousEmailUpdateError.getMessage() + "'}");
            OneSignalPush.callbackError(paramCallbackContext, paramAnonymousEmailUpdateError);
            return;
          }
          catch (JSONException paramAnonymousEmailUpdateError)
          {
            ThrowableExtension.printStackTrace(paramAnonymousEmailUpdateError);
          }
        }
        
        public void onSuccess()
        {
          OneSignalPush.callbackSuccess(paramCallbackContext, null);
        }
      });
      return true;
    }
    if ("setLocationShared".equals(paramString)) {
      try
      {
        OneSignal.setLocationShared(paramJSONArray.getBoolean(0));
        return false;
      }
      catch (JSONException paramString)
      {
        ThrowableExtension.printStackTrace(paramString);
        return false;
      }
    }
    if ("userProvidedPrivacyConsent".equals(paramString))
    {
      callbackSuccessBoolean(paramCallbackContext, OneSignal.userProvidedPrivacyConsent());
      return true;
    }
    if ("setRequiresUserPrivacyConsent".equals(paramString)) {
      try
      {
        OneSignal.setRequiresUserPrivacyConsent(paramJSONArray.getBoolean(0));
        return true;
      }
      catch (JSONException paramString)
      {
        ThrowableExtension.printStackTrace(paramString);
        return false;
      }
    }
    if ("provideUserConsent".equals(paramString)) {
      try
      {
        OneSignal.provideUserConsent(paramJSONArray.getBoolean(0));
        return true;
      }
      catch (JSONException paramString)
      {
        ThrowableExtension.printStackTrace(paramString);
        return false;
      }
    }
    Log.e("OneSignalPush", "Invalid action : " + paramString);
    callbackError(paramCallbackContext, "Invalid action : " + paramString);
    return false;
  }
  
  public void onDestroy()
  {
    OneSignal.removeNotificationOpenedHandler();
    OneSignal.removeNotificationReceivedHandler();
  }
  
  private class CordovaNotificationOpenedHandler
    implements OneSignal.NotificationOpenedHandler
  {
    private CallbackContext jsNotificationOpenedCallBack;
    
    public CordovaNotificationOpenedHandler(CallbackContext paramCallbackContext)
    {
      this.jsNotificationOpenedCallBack = paramCallbackContext;
    }
    
    public void notificationOpened(OSNotificationOpenResult paramOSNotificationOpenResult)
    {
      try
      {
        OneSignalPush.callbackSuccess(this.jsNotificationOpenedCallBack, new JSONObject(paramOSNotificationOpenResult.stringify()));
        return;
      }
      catch (Throwable paramOSNotificationOpenResult)
      {
        ThrowableExtension.printStackTrace(paramOSNotificationOpenResult);
      }
    }
  }
  
  private class CordovaNotificationReceivedHandler
    implements OneSignal.NotificationReceivedHandler
  {
    private CallbackContext jsNotificationReceivedCallBack;
    
    public CordovaNotificationReceivedHandler(CallbackContext paramCallbackContext)
    {
      this.jsNotificationReceivedCallBack = paramCallbackContext;
    }
    
    public void notificationReceived(OSNotification paramOSNotification)
    {
      try
      {
        OneSignalPush.callbackSuccess(this.jsNotificationReceivedCallBack, new JSONObject(paramOSNotification.stringify()));
        return;
      }
      catch (Throwable paramOSNotification)
      {
        ThrowableExtension.printStackTrace(paramOSNotification);
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\plugin\gcm\OneSignalPush.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */