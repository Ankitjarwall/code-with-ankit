package com.onesignal;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

abstract class UserState
{
  static final int DEVICE_TYPE_ANDROID = 1;
  static final int DEVICE_TYPE_FIREOS = 2;
  private static final String[] LOCATION_FIELDS = { "lat", "long", "loc_acc", "loc_type", "loc_bg", "loc_time_stamp", "ad_id" };
  private static final Set<String> LOCATION_FIELDS_SET = new HashSet(Arrays.asList(LOCATION_FIELDS));
  static final int PUSH_STATUS_FIREBASE_FCM_ERROR_IOEXCEPTION = -11;
  static final int PUSH_STATUS_FIREBASE_FCM_ERROR_MISC_EXCEPTION = -12;
  static final int PUSH_STATUS_FIREBASE_FCM_ERROR_SERVICE_NOT_AVAILABLE = -9;
  static final int PUSH_STATUS_FIREBASE_FCM_INIT_ERROR = -8;
  static final int PUSH_STATUS_INVALID_FCM_SENDER_ID = -6;
  static final int PUSH_STATUS_MISSING_ANDROID_SUPPORT_LIBRARY = -3;
  static final int PUSH_STATUS_MISSING_FIREBASE_FCM_LIBRARY = -4;
  static final int PUSH_STATUS_NO_PERMISSION = 0;
  static final int PUSH_STATUS_OUTDATED_ANDROID_SUPPORT_LIBRARY = -5;
  static final int PUSH_STATUS_OUTDATED_GOOGLE_PLAY_SERVICES_APP = -7;
  static final int PUSH_STATUS_SUBSCRIBED = 1;
  static final int PUSH_STATUS_UNSUBSCRIBE = -2;
  private static final Object syncLock = new Object() {};
  JSONObject dependValues;
  private String persistKey;
  JSONObject syncValues;
  
  UserState(String paramString, boolean paramBoolean)
  {
    this.persistKey = paramString;
    if (paramBoolean)
    {
      loadState();
      return;
    }
    this.dependValues = new JSONObject();
    this.syncValues = new JSONObject();
  }
  
  private static JSONObject generateJsonDiff(JSONObject paramJSONObject1, JSONObject paramJSONObject2, JSONObject paramJSONObject3, Set<String> paramSet)
  {
    synchronized (syncLock)
    {
      paramJSONObject1 = JSONUtils.generateJsonDiff(paramJSONObject1, paramJSONObject2, paramJSONObject3, paramSet);
      return paramJSONObject1;
    }
  }
  
  private Set<String> getGroupChangeFields(UserState paramUserState)
  {
    try
    {
      if (this.dependValues.optLong("loc_time_stamp") != paramUserState.dependValues.getLong("loc_time_stamp"))
      {
        paramUserState.syncValues.put("loc_bg", paramUserState.dependValues.opt("loc_bg"));
        paramUserState.syncValues.put("loc_time_stamp", paramUserState.dependValues.opt("loc_time_stamp"));
        paramUserState = LOCATION_FIELDS_SET;
        return paramUserState;
      }
    }
    catch (Throwable paramUserState) {}
    return null;
  }
  
  private void loadState()
  {
    String str = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_USERSTATE_DEPENDVALYES_" + this.persistKey, null);
    if (str == null)
    {
      this.dependValues = new JSONObject();
      bool = true;
    }
    try
    {
      if (!this.persistKey.equals("CURRENT_STATE")) {
        break label167;
      }
      i = OneSignalPrefs.getInt(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_SUBSCRIPTION", 1);
    }
    catch (JSONException localJSONException3)
    {
      for (;;)
      {
        int i;
        continue;
        int j = i;
        if (i == -2)
        {
          j = 1;
          bool = false;
        }
      }
    }
    this.dependValues.put("subscribableStatus", j);
    this.dependValues.put("userSubscribePref", bool);
    for (;;)
    {
      str = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_USERSTATE_SYNCVALYES_" + this.persistKey, null);
      if (str != null) {
        break;
      }
      try
      {
        this.syncValues = new JSONObject();
        str = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_REGISTRATION_ID", null);
        this.syncValues.put("identifier", str);
        return;
      }
      catch (JSONException localJSONException2)
      {
        label167:
        ThrowableExtension.printStackTrace(localJSONException2);
        return;
      }
      i = OneSignalPrefs.getInt(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_SYNCED_SUBSCRIPTION", 1);
      break label233;
      try
      {
        this.dependValues = new JSONObject(str);
      }
      catch (JSONException localJSONException1)
      {
        ThrowableExtension.printStackTrace(localJSONException1);
      }
    }
    this.syncValues = new JSONObject(localJSONException1);
  }
  
  private void modifySyncValuesJsonArray(String paramString)
  {
    label300:
    for (;;)
    {
      try
      {
        JSONArray localJSONArray1;
        int i;
        if (this.syncValues.has(paramString))
        {
          localJSONArray1 = this.syncValues.getJSONArray(paramString);
          JSONArray localJSONArray3 = new JSONArray();
          if (!this.syncValues.has(paramString + "_d")) {
            break label300;
          }
          String str = JSONUtils.toStringNE(this.syncValues.getJSONArray(paramString + "_d"));
          i = 0;
          localJSONArray2 = localJSONArray3;
          if (i < localJSONArray1.length())
          {
            if (str.contains(localJSONArray1.getString(i))) {
              continue;
            }
            localJSONArray3.put(localJSONArray1.get(i));
            continue;
          }
        }
        else
        {
          localJSONArray1 = new JSONArray();
          continue;
        }
        if (this.syncValues.has(paramString + "_a"))
        {
          localJSONArray1 = this.syncValues.getJSONArray(paramString + "_a");
          i = 0;
          if (i < localJSONArray1.length())
          {
            localJSONArray2.put(localJSONArray1.get(i));
            i += 1;
            continue;
          }
        }
        this.syncValues.put(paramString, localJSONArray2);
        this.syncValues.remove(paramString + "_a");
        this.syncValues.remove(paramString + "_d");
        return;
        i += 1;
        continue;
        JSONArray localJSONArray2 = localJSONArray1;
      }
      catch (Throwable paramString)
      {
        return;
      }
    }
  }
  
  protected abstract void addDependFields();
  
  void clearLocation()
  {
    try
    {
      this.syncValues.put("lat", null);
      this.syncValues.put("long", null);
      this.syncValues.put("loc_acc", null);
      this.syncValues.put("loc_type", null);
      this.syncValues.put("loc_bg", null);
      this.syncValues.put("loc_time_stamp", null);
      this.dependValues.put("loc_bg", null);
      this.dependValues.put("loc_time_stamp", null);
      return;
    }
    catch (JSONException localJSONException)
    {
      ThrowableExtension.printStackTrace(localJSONException);
    }
  }
  
  UserState deepClone(String paramString)
  {
    paramString = newInstance(paramString);
    try
    {
      paramString.dependValues = new JSONObject(this.dependValues.toString());
      paramString.syncValues = new JSONObject(this.syncValues.toString());
      return paramString;
    }
    catch (JSONException localJSONException)
    {
      ThrowableExtension.printStackTrace(localJSONException);
    }
    return paramString;
  }
  
  JSONObject generateJsonDiff(UserState paramUserState, boolean paramBoolean)
  {
    addDependFields();
    paramUserState.addDependFields();
    Object localObject = getGroupChangeFields(paramUserState);
    localObject = generateJsonDiff(this.syncValues, paramUserState.syncValues, null, (Set)localObject);
    if ((!paramBoolean) && (((JSONObject)localObject).toString().equals("{}"))) {
      paramUserState = null;
    }
    for (;;)
    {
      return paramUserState;
      try
      {
        if (!((JSONObject)localObject).has("app_id")) {
          ((JSONObject)localObject).put("app_id", this.syncValues.optString("app_id"));
        }
        paramUserState = (UserState)localObject;
        if (this.syncValues.has("email_auth_hash"))
        {
          ((JSONObject)localObject).put("email_auth_hash", this.syncValues.optString("email_auth_hash"));
          return (JSONObject)localObject;
        }
      }
      catch (JSONException paramUserState)
      {
        ThrowableExtension.printStackTrace(paramUserState);
      }
    }
    return (JSONObject)localObject;
  }
  
  abstract boolean isSubscribed();
  
  void mergeTags(JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    for (;;)
    {
      JSONObject localJSONObject2;
      synchronized (syncLock)
      {
        String str;
        if (paramJSONObject1.has("tags"))
        {
          boolean bool = this.syncValues.has("tags");
          if (!bool) {}
        }
        else
        {
          try
          {
            JSONObject localJSONObject1 = new JSONObject(this.syncValues.optString("tags"));
            paramJSONObject1 = paramJSONObject1.optJSONObject("tags");
            Iterator localIterator = paramJSONObject1.keys();
            try
            {
              if (!localIterator.hasNext()) {
                break label181;
              }
              str = (String)localIterator.next();
              if (!"".equals(paramJSONObject1.optString(str))) {
                continue;
              }
              localJSONObject1.remove(str);
              continue;
            }
            catch (Throwable paramJSONObject1) {}
            return;
          }
          catch (JSONException localJSONException)
          {
            localJSONObject2 = new JSONObject();
            continue;
          }
        }
        localJSONObject2 = new JSONObject();
        continue;
        if ((paramJSONObject2 != null) && (paramJSONObject2.has(str))) {
          continue;
        }
        localJSONObject2.put(str, paramJSONObject1.optString(str));
      }
      label181:
      if (localJSONObject2.toString().equals("{}")) {
        this.syncValues.remove("tags");
      } else {
        this.syncValues.put("tags", localJSONObject2);
      }
    }
  }
  
  abstract UserState newInstance(String paramString);
  
  void persistState()
  {
    synchronized (syncLock)
    {
      modifySyncValuesJsonArray("pkgs");
      OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_USERSTATE_SYNCVALYES_" + this.persistKey, this.syncValues.toString());
      OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_USERSTATE_DEPENDVALYES_" + this.persistKey, this.dependValues.toString());
      return;
    }
  }
  
  void persistStateAfterSync(JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    if (paramJSONObject1 != null) {
      generateJsonDiff(this.dependValues, paramJSONObject1, this.dependValues, null);
    }
    if (paramJSONObject2 != null)
    {
      generateJsonDiff(this.syncValues, paramJSONObject2, this.syncValues, null);
      mergeTags(paramJSONObject2, null);
    }
    if ((paramJSONObject1 != null) || (paramJSONObject2 != null)) {
      persistState();
    }
  }
  
  void set(String paramString, Object paramObject)
  {
    try
    {
      this.syncValues.put(paramString, paramObject);
      return;
    }
    catch (JSONException paramString)
    {
      ThrowableExtension.printStackTrace(paramString);
    }
  }
  
  void setLocation(LocationGMS.LocationPoint paramLocationPoint)
  {
    try
    {
      this.syncValues.put("lat", paramLocationPoint.lat);
      this.syncValues.put("long", paramLocationPoint.log);
      this.syncValues.put("loc_acc", paramLocationPoint.accuracy);
      this.syncValues.put("loc_type", paramLocationPoint.type);
      this.dependValues.put("loc_bg", paramLocationPoint.bg);
      this.dependValues.put("loc_time_stamp", paramLocationPoint.timeStamp);
      return;
    }
    catch (JSONException paramLocationPoint)
    {
      ThrowableExtension.printStackTrace(paramLocationPoint);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\UserState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */