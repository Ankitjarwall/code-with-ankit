package com.onesignal;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONException;
import org.json.JSONObject;

class OneSignalStateSynchronizer
{
  private static UserStateEmailSynchronizer userStateEmailSynchronizer;
  private static UserStatePushSynchronizer userStatePushSynchronizer;
  
  static void clearLocation()
  {
    getPushStateSynchronizer().clearLocation();
    getEmailStateSynchronizer().clearLocation();
  }
  
  static UserStateEmailSynchronizer getEmailStateSynchronizer()
  {
    if (userStateEmailSynchronizer == null) {
      userStateEmailSynchronizer = new UserStateEmailSynchronizer();
    }
    return userStateEmailSynchronizer;
  }
  
  static UserStatePushSynchronizer getPushStateSynchronizer()
  {
    if (userStatePushSynchronizer == null) {
      userStatePushSynchronizer = new UserStatePushSynchronizer();
    }
    return userStatePushSynchronizer;
  }
  
  static String getRegistrationId()
  {
    return getPushStateSynchronizer().getRegistrationId();
  }
  
  static boolean getSubscribed()
  {
    return getPushStateSynchronizer().getSubscribed();
  }
  
  static UserStateSynchronizer.GetTagsResult getTags(boolean paramBoolean)
  {
    return getPushStateSynchronizer().getTags(paramBoolean);
  }
  
  static boolean getUserSubscribePreference()
  {
    return getPushStateSynchronizer().getUserSubscribePreference();
  }
  
  static void initUserState()
  {
    getPushStateSynchronizer().initUserState();
    getEmailStateSynchronizer().initUserState();
  }
  
  static void logoutEmail()
  {
    getPushStateSynchronizer().logoutEmail();
    getEmailStateSynchronizer().logoutEmail();
  }
  
  static boolean persist()
  {
    boolean bool2 = false;
    boolean bool4 = getPushStateSynchronizer().persist();
    boolean bool3 = getEmailStateSynchronizer().persist();
    boolean bool1 = bool3;
    if (bool3) {
      if (getEmailStateSynchronizer().getRegistrationId() == null) {
        break label45;
      }
    }
    label45:
    for (bool1 = true;; bool1 = false)
    {
      if ((bool4) || (bool1)) {
        bool2 = true;
      }
      return bool2;
    }
  }
  
  static void refreshEmailState()
  {
    getEmailStateSynchronizer().refresh();
  }
  
  static void resetCurrentState()
  {
    getPushStateSynchronizer().resetCurrentState();
    getEmailStateSynchronizer().resetCurrentState();
    OneSignal.saveUserId(null);
    OneSignal.saveEmailId(null);
    OneSignal.setLastSessionTime(-3660L);
  }
  
  static void sendTags(JSONObject paramJSONObject, OneSignal.ChangeTagsUpdateHandler paramChangeTagsUpdateHandler)
  {
    try
    {
      paramJSONObject = new JSONObject().put("tags", paramJSONObject);
      getPushStateSynchronizer().sendTags(paramJSONObject, paramChangeTagsUpdateHandler);
      getEmailStateSynchronizer().sendTags(paramJSONObject, paramChangeTagsUpdateHandler);
      return;
    }
    catch (JSONException paramJSONObject)
    {
      paramChangeTagsUpdateHandler.onFailure(new OneSignal.SendTagsError(-1, "Encountered an error attempting to serialize your tags into JSON: " + paramJSONObject.getMessage() + "\n" + paramJSONObject.getStackTrace()));
      ThrowableExtension.printStackTrace(paramJSONObject);
    }
  }
  
  static void setEmail(String paramString1, String paramString2)
  {
    getPushStateSynchronizer().setEmail(paramString1, paramString2);
    getEmailStateSynchronizer().setEmail(paramString1, paramString2);
  }
  
  static void setPermission(boolean paramBoolean)
  {
    getPushStateSynchronizer().setPermission(paramBoolean);
  }
  
  static void setSubscription(boolean paramBoolean)
  {
    getPushStateSynchronizer().setSubscription(paramBoolean);
  }
  
  static void setSyncAsNewSession()
  {
    getPushStateSynchronizer().setSyncAsNewSession();
    getEmailStateSynchronizer().setSyncAsNewSession();
  }
  
  static void setSyncAsNewSessionForEmail()
  {
    getEmailStateSynchronizer().setSyncAsNewSession();
  }
  
  static void syncHashedEmail(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("em_m", OSUtils.hexDigest(paramString, "MD5"));
      localJSONObject.put("em_s", OSUtils.hexDigest(paramString, "SHA-1"));
      getPushStateSynchronizer().syncHashedEmail(localJSONObject);
      return;
    }
    catch (Throwable paramString)
    {
      ThrowableExtension.printStackTrace(paramString);
    }
  }
  
  static void syncUserState(boolean paramBoolean)
  {
    getPushStateSynchronizer().syncUserState(paramBoolean);
    getEmailStateSynchronizer().syncUserState(paramBoolean);
  }
  
  static void updateDeviceInfo(JSONObject paramJSONObject)
  {
    getPushStateSynchronizer().updateDeviceInfo(paramJSONObject);
    getEmailStateSynchronizer().updateDeviceInfo(paramJSONObject);
  }
  
  static void updateLocation(LocationGMS.LocationPoint paramLocationPoint)
  {
    getPushStateSynchronizer().updateLocation(paramLocationPoint);
    getEmailStateSynchronizer().updateLocation(paramLocationPoint);
  }
  
  static void updatePushState(JSONObject paramJSONObject)
  {
    getPushStateSynchronizer().updateState(paramJSONObject);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OneSignalStateSynchronizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */