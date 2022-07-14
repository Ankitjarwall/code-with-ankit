package com.onesignal;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONException;
import org.json.JSONObject;

class UserStateEmailSynchronizer
  extends UserStateSynchronizer
{
  protected void addOnSessionOrCreateExtras(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("device_type", 11);
      paramJSONObject.putOpt("device_player_id", OneSignal.getUserId());
      return;
    }
    catch (JSONException paramJSONObject)
    {
      ThrowableExtension.printStackTrace(paramJSONObject);
    }
  }
  
  protected void fireEventsForUpdateFailure(JSONObject paramJSONObject)
  {
    if (paramJSONObject.has("identifier")) {
      OneSignal.fireEmailUpdateFailure();
    }
  }
  
  protected String getId()
  {
    return OneSignal.getEmailId();
  }
  
  boolean getSubscribed()
  {
    return false;
  }
  
  UserStateSynchronizer.GetTagsResult getTags(boolean paramBoolean)
  {
    return null;
  }
  
  public boolean getUserSubscribePreference()
  {
    return false;
  }
  
  void logoutEmail()
  {
    OneSignal.saveEmailId("");
    resetCurrentState();
    getToSyncUserState().syncValues.remove("identifier");
    this.toSyncUserState.syncValues.remove("email_auth_hash");
    this.toSyncUserState.syncValues.remove("device_player_id");
    this.toSyncUserState.persistState();
    OneSignal.getPermissionSubscriptionState().emailSubscriptionStatus.clearEmailAndId();
  }
  
  protected UserState newUserState(String paramString, boolean paramBoolean)
  {
    return new UserStateEmail(paramString, paramBoolean);
  }
  
  protected void onSuccessfulSync(JSONObject paramJSONObject)
  {
    if (paramJSONObject.has("identifier")) {
      OneSignal.fireEmailUpdateSuccess();
    }
  }
  
  void refresh()
  {
    scheduleSyncToServer();
  }
  
  protected void scheduleSyncToServer()
  {
    if ((getId() == null) && (getRegistrationId() == null)) {}
    for (int i = 1; (i != 0) || (OneSignal.getUserId() == null); i = 0) {
      return;
    }
    getNetworkHandlerThread(Integer.valueOf(0)).runNewJobDelayed();
  }
  
  void setEmail(String paramString1, String paramString2)
  {
    JSONObject localJSONObject = getUserStateForModification().syncValues;
    Object localObject;
    if (paramString1.equals(localJSONObject.optString("identifier")))
    {
      localObject = localJSONObject.optString("email_auth_hash");
      if (paramString2 == null)
      {
        str = "";
        if (!((String)localObject).equals(str)) {
          break label66;
        }
      }
    }
    label66:
    for (int i = 1;; i = 0)
    {
      if (i == 0) {
        break label71;
      }
      OneSignal.fireEmailUpdateSuccess();
      return;
      str = paramString2;
      break;
    }
    label71:
    String str = localJSONObject.optString("identifier", null);
    if (str == null) {
      setSyncAsNewSession();
    }
    try
    {
      localObject = new JSONObject();
      ((JSONObject)localObject).put("identifier", paramString1);
      if (paramString2 != null) {
        ((JSONObject)localObject).put("email_auth_hash", paramString2);
      }
      if ((paramString2 == null) && (str != null) && (!str.equals(paramString1)))
      {
        OneSignal.saveEmailId("");
        resetCurrentState();
        setSyncAsNewSession();
      }
      generateJsonDiff(localJSONObject, (JSONObject)localObject, localJSONObject, null);
      scheduleSyncToServer();
      return;
    }
    catch (JSONException paramString1)
    {
      ThrowableExtension.printStackTrace(paramString1);
    }
  }
  
  public void setPermission(boolean paramBoolean) {}
  
  void setSubscription(boolean paramBoolean) {}
  
  void updateIdDependents(String paramString)
  {
    OneSignal.updateEmailIdDependents(paramString);
  }
  
  void updateState(JSONObject paramJSONObject) {}
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\UserStateEmailSynchronizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */