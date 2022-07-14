package com.onesignal;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONException;
import org.json.JSONObject;

class UserStatePushSynchronizer
  extends UserStateSynchronizer
{
  private static boolean serverSuccess;
  
  protected void addOnSessionOrCreateExtras(JSONObject paramJSONObject) {}
  
  protected void fireEventsForUpdateFailure(JSONObject paramJSONObject)
  {
    if (paramJSONObject.has("email")) {
      OneSignal.fireEmailUpdateFailure();
    }
  }
  
  protected String getId()
  {
    return OneSignal.getUserId();
  }
  
  boolean getSubscribed()
  {
    return getToSyncUserState().isSubscribed();
  }
  
  UserStateSynchronizer.GetTagsResult getTags(boolean paramBoolean)
  {
    Object localObject2;
    if (paramBoolean)
    {
      ??? = OneSignal.getUserId();
      localObject2 = OneSignal.getSavedAppId();
      OneSignalRestClient.getSync("players/" + (String)??? + "?app_id=" + (String)localObject2, new OneSignalRestClient.ResponseHandler()
      {
        void onSuccess(String arg1)
        {
          UserStatePushSynchronizer.access$002(true);
          try
          {
            JSONObject localJSONObject1 = new JSONObject(???);
            if (localJSONObject1.has("tags")) {
              synchronized (UserStatePushSynchronizer.this.syncLock)
              {
                JSONObject localJSONObject2 = UserStatePushSynchronizer.this.generateJsonDiff(UserStatePushSynchronizer.this.currentUserState.syncValues.optJSONObject("tags"), UserStatePushSynchronizer.this.getToSyncUserState().syncValues.optJSONObject("tags"), null, null);
                UserStatePushSynchronizer.this.currentUserState.syncValues.put("tags", localJSONObject1.optJSONObject("tags"));
                UserStatePushSynchronizer.this.currentUserState.persistState();
                UserStatePushSynchronizer.this.getToSyncUserState().mergeTags(localJSONObject1, localJSONObject2);
                UserStatePushSynchronizer.this.getToSyncUserState().persistState();
                return;
              }
            }
            return;
          }
          catch (JSONException ???)
          {
            ThrowableExtension.printStackTrace(???);
          }
        }
      });
    }
    synchronized (this.syncLock)
    {
      localObject2 = new UserStateSynchronizer.GetTagsResult(serverSuccess, JSONUtils.getJSONObjectWithoutBlankValues(this.toSyncUserState.syncValues, "tags"));
      return (UserStateSynchronizer.GetTagsResult)localObject2;
    }
  }
  
  public boolean getUserSubscribePreference()
  {
    return getToSyncUserState().dependValues.optBoolean("userSubscribePref", true);
  }
  
  void logoutEmail()
  {
    try
    {
      getUserStateForModification().dependValues.put("logoutEmail", true);
      return;
    }
    catch (JSONException localJSONException)
    {
      ThrowableExtension.printStackTrace(localJSONException);
    }
  }
  
  protected UserState newUserState(String paramString, boolean paramBoolean)
  {
    return new UserStatePush(paramString, paramBoolean);
  }
  
  protected void onSuccessfulSync(JSONObject paramJSONObject)
  {
    if (paramJSONObject.has("email")) {
      OneSignal.fireEmailUpdateSuccess();
    }
    if (paramJSONObject.has("identifier")) {
      OneSignal.fireIdsAvailableCallback();
    }
  }
  
  protected void scheduleSyncToServer()
  {
    getNetworkHandlerThread(Integer.valueOf(0)).runNewJobDelayed();
  }
  
  void setEmail(String paramString1, String paramString2)
  {
    try
    {
      UserState localUserState = getUserStateForModification();
      localUserState.dependValues.put("email_auth_hash", paramString2);
      paramString2 = localUserState.syncValues;
      generateJsonDiff(paramString2, new JSONObject().put("email", paramString1), paramString2, null);
      return;
    }
    catch (JSONException paramString1)
    {
      ThrowableExtension.printStackTrace(paramString1);
    }
  }
  
  public void setPermission(boolean paramBoolean)
  {
    try
    {
      getUserStateForModification().dependValues.put("androidPermission", paramBoolean);
      return;
    }
    catch (JSONException localJSONException)
    {
      ThrowableExtension.printStackTrace(localJSONException);
    }
  }
  
  void setSubscription(boolean paramBoolean)
  {
    try
    {
      getUserStateForModification().dependValues.put("userSubscribePref", paramBoolean);
      return;
    }
    catch (JSONException localJSONException)
    {
      ThrowableExtension.printStackTrace(localJSONException);
    }
  }
  
  void updateIdDependents(String paramString)
  {
    OneSignal.updateUserIdDependents(paramString);
  }
  
  void updateState(JSONObject paramJSONObject)
  {
    try
    {
      JSONObject localJSONObject1 = new JSONObject();
      localJSONObject1.putOpt("identifier", paramJSONObject.optString("identifier", null));
      if (paramJSONObject.has("device_type")) {
        localJSONObject1.put("device_type", paramJSONObject.optInt("device_type"));
      }
      localJSONObject1.putOpt("parent_player_id", paramJSONObject.optString("parent_player_id", null));
      JSONObject localJSONObject2 = getUserStateForModification().syncValues;
      generateJsonDiff(localJSONObject2, localJSONObject1, localJSONObject2, null);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        try
        {
          localJSONObject1 = new JSONObject();
          if (paramJSONObject.has("subscribableStatus")) {
            localJSONObject1.put("subscribableStatus", paramJSONObject.optInt("subscribableStatus"));
          }
          if (paramJSONObject.has("androidPermission")) {
            localJSONObject1.put("androidPermission", paramJSONObject.optBoolean("androidPermission"));
          }
          paramJSONObject = getUserStateForModification().dependValues;
          generateJsonDiff(paramJSONObject, localJSONObject1, paramJSONObject, null);
          return;
        }
        catch (JSONException paramJSONObject)
        {
          ThrowableExtension.printStackTrace(paramJSONObject);
        }
        localJSONException = localJSONException;
        ThrowableExtension.printStackTrace(localJSONException);
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\UserStatePushSynchronizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */