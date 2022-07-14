package com.onesignal;

import android.os.Handler;
import android.os.HandlerThread;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

abstract class UserStateSynchronizer
{
  protected UserState currentUserState;
  private final Object networkHandlerSyncLock = new Object() {};
  HashMap<Integer, NetworkHandlerThread> networkHandlerThreads = new HashMap();
  protected boolean nextSyncIsSession = false;
  private AtomicBoolean runningSyncUserState = new AtomicBoolean();
  private ArrayList<OneSignal.ChangeTagsUpdateHandler> sendTagsHandlers = new ArrayList();
  protected final Object syncLock = new Object() {};
  protected UserState toSyncUserState;
  protected boolean waitingForSessionResponse = false;
  
  private void doCreateOrNewSession(final String paramString, final JSONObject paramJSONObject1, final JSONObject paramJSONObject2)
  {
    if (paramString == null) {}
    for (String str = "players";; str = "players/" + paramString + "/on_session")
    {
      this.waitingForSessionResponse = true;
      addOnSessionOrCreateExtras(paramJSONObject1);
      OneSignalRestClient.postSync(str, paramJSONObject1, new OneSignalRestClient.ResponseHandler()
      {
        void onFailure(int paramAnonymousInt, String paramAnonymousString, Throwable arg3)
        {
          synchronized (UserStateSynchronizer.this.syncLock)
          {
            UserStateSynchronizer.this.waitingForSessionResponse = false;
            OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Failed last request. statusCode: " + paramAnonymousInt + "\nresponse: " + paramAnonymousString);
            if (UserStateSynchronizer.this.response400WithErrorsContaining(paramAnonymousInt, paramAnonymousString, "not a valid device_type"))
            {
              UserStateSynchronizer.this.handlePlayerDeletedFromServer();
              return;
            }
            UserStateSynchronizer.this.handleNetworkFailure();
          }
        }
        
        void onSuccess(String paramAnonymousString)
        {
          synchronized (UserStateSynchronizer.this.syncLock)
          {
            UserStateSynchronizer localUserStateSynchronizer = UserStateSynchronizer.this;
            UserStateSynchronizer.this.waitingForSessionResponse = false;
            localUserStateSynchronizer.nextSyncIsSession = false;
            UserStateSynchronizer.this.currentUserState.persistStateAfterSync(paramJSONObject2, paramJSONObject1);
            try
            {
              paramAnonymousString = new JSONObject(paramAnonymousString);
              if (!paramAnonymousString.has("id")) {
                break label121;
              }
              paramAnonymousString = paramAnonymousString.optString("id");
              UserStateSynchronizer.this.updateIdDependents(paramAnonymousString);
              OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "Device registered, UserId = " + paramAnonymousString);
              OneSignal.updateOnSessionDependents();
              UserStateSynchronizer.this.onSuccessfulSync(paramJSONObject1);
            }
            catch (Throwable paramAnonymousString)
            {
              for (;;)
              {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "ERROR parsing on_session or create JSON Response.", paramAnonymousString);
              }
            }
            return;
            label121:
            OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "session sent, UserId = " + paramString);
          }
        }
      });
      return;
    }
  }
  
  private void doEmailLogout(String paramString)
  {
    paramString = "players/" + paramString + "/email_logout";
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      JSONObject localJSONObject2 = this.currentUserState.dependValues;
      if (localJSONObject2.has("email_auth_hash")) {
        localJSONObject1.put("email_auth_hash", localJSONObject2.optString("email_auth_hash"));
      }
      localJSONObject2 = this.currentUserState.syncValues;
      if (localJSONObject2.has("parent_player_id")) {
        localJSONObject1.put("parent_player_id", localJSONObject2.optString("parent_player_id"));
      }
      localJSONObject1.put("app_id", localJSONObject2.optString("app_id"));
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        ThrowableExtension.printStackTrace(localJSONException);
      }
    }
    OneSignalRestClient.postSync(paramString, localJSONObject1, new OneSignalRestClient.ResponseHandler()
    {
      void onFailure(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Failed last request. statusCode: " + paramAnonymousInt + "\nresponse: " + paramAnonymousString);
        if (UserStateSynchronizer.this.response400WithErrorsContaining(paramAnonymousInt, paramAnonymousString, "already logged out of email"))
        {
          UserStateSynchronizer.this.logoutEmailSyncSuccess();
          return;
        }
        if (UserStateSynchronizer.this.response400WithErrorsContaining(paramAnonymousInt, paramAnonymousString, "not a valid device_type"))
        {
          UserStateSynchronizer.this.handlePlayerDeletedFromServer();
          return;
        }
        UserStateSynchronizer.this.handleNetworkFailure();
      }
      
      void onSuccess(String paramAnonymousString)
      {
        UserStateSynchronizer.this.logoutEmailSyncSuccess();
      }
    });
  }
  
  private void doPutSync(String paramString, final JSONObject paramJSONObject1, final JSONObject paramJSONObject2)
  {
    if (paramString == null)
    {
      paramString = this.sendTagsHandlers.iterator();
      while (paramString.hasNext())
      {
        paramJSONObject1 = (OneSignal.ChangeTagsUpdateHandler)paramString.next();
        if (paramJSONObject1 != null) {
          paramJSONObject1.onFailure(new OneSignal.SendTagsError(-1, "Unable to update tags: the current user is not registered with OneSignal"));
        }
      }
      this.sendTagsHandlers.clear();
      return;
    }
    final ArrayList localArrayList = (ArrayList)this.sendTagsHandlers.clone();
    this.sendTagsHandlers.clear();
    OneSignalRestClient.putSync("players/" + paramString, paramJSONObject1, new OneSignalRestClient.ResponseHandler()
    {
      void onFailure(int paramAnonymousInt, String paramAnonymousString, Throwable arg3)
      {
        OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Failed last request. statusCode: " + paramAnonymousInt + "\nresponse: " + paramAnonymousString);
        synchronized (UserStateSynchronizer.this.syncLock)
        {
          if (UserStateSynchronizer.this.response400WithErrorsContaining(paramAnonymousInt, paramAnonymousString, "No user with this id found"))
          {
            UserStateSynchronizer.this.handlePlayerDeletedFromServer();
            if (paramJSONObject1.has("tags"))
            {
              ??? = localArrayList.iterator();
              while (???.hasNext())
              {
                OneSignal.ChangeTagsUpdateHandler localChangeTagsUpdateHandler = (OneSignal.ChangeTagsUpdateHandler)???.next();
                if (localChangeTagsUpdateHandler != null) {
                  localChangeTagsUpdateHandler.onFailure(new OneSignal.SendTagsError(paramAnonymousInt, paramAnonymousString));
                }
              }
            }
          }
          else
          {
            UserStateSynchronizer.this.handleNetworkFailure();
          }
        }
      }
      
      void onSuccess(String arg1)
      {
        synchronized (UserStateSynchronizer.this.syncLock)
        {
          UserStateSynchronizer.this.currentUserState.persistStateAfterSync(paramJSONObject2, paramJSONObject1);
          UserStateSynchronizer.this.onSuccessfulSync(paramJSONObject1);
          ??? = OneSignalStateSynchronizer.getTags(false).result;
          if ((paramJSONObject1.has("tags")) && (??? != null))
          {
            Iterator localIterator = localArrayList.iterator();
            while (localIterator.hasNext())
            {
              OneSignal.ChangeTagsUpdateHandler localChangeTagsUpdateHandler = (OneSignal.ChangeTagsUpdateHandler)localIterator.next();
              if (localChangeTagsUpdateHandler != null) {
                localChangeTagsUpdateHandler.onSuccess(???);
              }
            }
          }
        }
      }
    });
  }
  
  private void handleNetworkFailure()
  {
    if (getNetworkHandlerThread(Integer.valueOf(0)).doRetry()) {}
    do
    {
      return;
      JSONObject localJSONObject = this.currentUserState.generateJsonDiff(this.toSyncUserState, false);
      if (localJSONObject != null) {
        fireEventsForUpdateFailure(localJSONObject);
      }
    } while (!getToSyncUserState().dependValues.optBoolean("logoutEmail", false));
    OneSignal.handleFailedEmailLogout();
  }
  
  private void handlePlayerDeletedFromServer()
  {
    OneSignal.handleSuccessfulEmailLogout();
    resetCurrentState();
    this.nextSyncIsSession = true;
    scheduleSyncToServer();
  }
  
  private void internalSyncUserState(boolean paramBoolean)
  {
    Object localObject2 = getId();
    if ((syncEmailLogout()) && (localObject2 != null))
    {
      doEmailLogout((String)localObject2);
      return;
    }
    if (this.currentUserState == null) {
      initUserState();
    }
    boolean bool = isSessionCall();
    Object localObject3;
    JSONObject localJSONObject;
    synchronized (this.syncLock)
    {
      localObject3 = this.currentUserState.generateJsonDiff(getToSyncUserState(), bool);
      localJSONObject = generateJsonDiff(this.currentUserState.dependValues, getToSyncUserState().dependValues, null, null);
      if (localObject3 != null) {
        break label169;
      }
      this.currentUserState.persistStateAfterSync(localJSONObject, null);
      localObject2 = this.sendTagsHandlers.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (OneSignal.ChangeTagsUpdateHandler)((Iterator)localObject2).next();
        if (localObject3 != null) {
          ((OneSignal.ChangeTagsUpdateHandler)localObject3).onSuccess(OneSignalStateSynchronizer.getTags(false).result);
        }
      }
    }
    this.sendTagsHandlers.clear();
    return;
    label169:
    getToSyncUserState().persistState();
    if ((!bool) || (paramBoolean))
    {
      doPutSync(str, (JSONObject)localObject3, localJSONObject);
      return;
    }
    doCreateOrNewSession(str, (JSONObject)localObject3, localJSONObject);
  }
  
  private boolean isSessionCall()
  {
    return (this.nextSyncIsSession) && (!this.waitingForSessionResponse);
  }
  
  private void logoutEmailSyncSuccess()
  {
    getToSyncUserState().dependValues.remove("logoutEmail");
    this.toSyncUserState.dependValues.remove("email_auth_hash");
    this.toSyncUserState.syncValues.remove("parent_player_id");
    this.toSyncUserState.persistState();
    this.currentUserState.dependValues.remove("email_auth_hash");
    this.currentUserState.syncValues.remove("parent_player_id");
    String str = this.currentUserState.syncValues.optString("email");
    this.currentUserState.syncValues.remove("email");
    OneSignalStateSynchronizer.setSyncAsNewSessionForEmail();
    OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "Device successfully logged out of email: " + str);
    OneSignal.handleSuccessfulEmailLogout();
  }
  
  private boolean response400WithErrorsContaining(int paramInt, String paramString1, String paramString2)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramInt == 400)
    {
      bool1 = bool2;
      if (paramString1 == null) {}
    }
    try
    {
      paramString1 = new JSONObject(paramString1);
      bool1 = bool2;
      if (paramString1.has("errors"))
      {
        boolean bool3 = paramString1.optString("errors").contains(paramString2);
        bool1 = bool2;
        if (bool3) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Throwable paramString1)
    {
      ThrowableExtension.printStackTrace(paramString1);
    }
    return false;
  }
  
  private boolean syncEmailLogout()
  {
    return getToSyncUserState().dependValues.optBoolean("logoutEmail", false);
  }
  
  protected abstract void addOnSessionOrCreateExtras(JSONObject paramJSONObject);
  
  void clearLocation()
  {
    getToSyncUserState().clearLocation();
    getToSyncUserState().persistState();
  }
  
  protected abstract void fireEventsForUpdateFailure(JSONObject paramJSONObject);
  
  protected JSONObject generateJsonDiff(JSONObject paramJSONObject1, JSONObject paramJSONObject2, JSONObject paramJSONObject3, Set<String> paramSet)
  {
    synchronized (this.syncLock)
    {
      paramJSONObject1 = JSONUtils.generateJsonDiff(paramJSONObject1, paramJSONObject2, paramJSONObject3, paramSet);
      return paramJSONObject1;
    }
  }
  
  protected abstract String getId();
  
  protected NetworkHandlerThread getNetworkHandlerThread(Integer paramInteger)
  {
    synchronized (this.networkHandlerSyncLock)
    {
      if (!this.networkHandlerThreads.containsKey(paramInteger)) {
        this.networkHandlerThreads.put(paramInteger, new NetworkHandlerThread(paramInteger.intValue()));
      }
      paramInteger = (NetworkHandlerThread)this.networkHandlerThreads.get(paramInteger);
      return paramInteger;
    }
  }
  
  String getRegistrationId()
  {
    return getToSyncUserState().syncValues.optString("identifier", null);
  }
  
  abstract boolean getSubscribed();
  
  abstract GetTagsResult getTags(boolean paramBoolean);
  
  protected UserState getToSyncUserState()
  {
    synchronized (this.syncLock)
    {
      if (this.toSyncUserState == null) {
        this.toSyncUserState = newUserState("TOSYNC_STATE", true);
      }
      return this.toSyncUserState;
    }
  }
  
  protected UserState getUserStateForModification()
  {
    if (this.toSyncUserState == null) {
      this.toSyncUserState = this.currentUserState.deepClone("TOSYNC_STATE");
    }
    scheduleSyncToServer();
    return this.toSyncUserState;
  }
  
  public abstract boolean getUserSubscribePreference();
  
  void initUserState()
  {
    synchronized (this.syncLock)
    {
      if (this.currentUserState == null) {
        this.currentUserState = newUserState("CURRENT_STATE", true);
      }
      getToSyncUserState();
      return;
    }
  }
  
  abstract void logoutEmail();
  
  protected abstract UserState newUserState(String paramString, boolean paramBoolean);
  
  protected abstract void onSuccessfulSync(JSONObject paramJSONObject);
  
  boolean persist()
  {
    boolean bool = false;
    if (this.toSyncUserState != null) {
      synchronized (this.syncLock)
      {
        if (this.currentUserState.generateJsonDiff(this.toSyncUserState, isSessionCall()) != null) {
          bool = true;
        }
        this.toSyncUserState.persistState();
        return bool;
      }
    }
    return false;
  }
  
  void resetCurrentState()
  {
    this.currentUserState.syncValues = new JSONObject();
    this.currentUserState.persistState();
  }
  
  protected abstract void scheduleSyncToServer();
  
  void sendTags(JSONObject paramJSONObject, OneSignal.ChangeTagsUpdateHandler paramChangeTagsUpdateHandler)
  {
    this.sendTagsHandlers.add(paramChangeTagsUpdateHandler);
    paramChangeTagsUpdateHandler = getUserStateForModification().syncValues;
    generateJsonDiff(paramChangeTagsUpdateHandler, paramJSONObject, paramChangeTagsUpdateHandler, null);
  }
  
  public abstract void setPermission(boolean paramBoolean);
  
  abstract void setSubscription(boolean paramBoolean);
  
  void setSyncAsNewSession()
  {
    this.nextSyncIsSession = true;
  }
  
  void syncHashedEmail(JSONObject paramJSONObject)
  {
    JSONObject localJSONObject = getUserStateForModification().syncValues;
    generateJsonDiff(localJSONObject, paramJSONObject, localJSONObject, null);
  }
  
  void syncUserState(boolean paramBoolean)
  {
    this.runningSyncUserState.set(true);
    internalSyncUserState(paramBoolean);
    this.runningSyncUserState.set(false);
  }
  
  void updateDeviceInfo(JSONObject paramJSONObject)
  {
    JSONObject localJSONObject = getUserStateForModification().syncValues;
    generateJsonDiff(localJSONObject, paramJSONObject, localJSONObject, null);
  }
  
  abstract void updateIdDependents(String paramString);
  
  void updateLocation(LocationGMS.LocationPoint paramLocationPoint)
  {
    getUserStateForModification().setLocation(paramLocationPoint);
  }
  
  abstract void updateState(JSONObject paramJSONObject);
  
  static class GetTagsResult
  {
    JSONObject result;
    boolean serverSuccess;
    
    GetTagsResult(boolean paramBoolean, JSONObject paramJSONObject)
    {
      this.serverSuccess = paramBoolean;
      this.result = paramJSONObject;
    }
  }
  
  class NetworkHandlerThread
    extends HandlerThread
  {
    static final int MAX_RETRIES = 3;
    static final int NETWORK_CALL_DELAY_TO_BUFFER_MS = 5000;
    protected static final int NETWORK_HANDLER_USERSTATE = 0;
    int currentRetry;
    Handler mHandler = null;
    int mType;
    
    NetworkHandlerThread(int paramInt)
    {
      super();
      this.mType = paramInt;
      start();
      this.mHandler = new Handler(getLooper());
    }
    
    private Runnable getNewRunnable()
    {
      switch (this.mType)
      {
      default: 
        return null;
      }
      new Runnable()
      {
        public void run()
        {
          if (!UserStateSynchronizer.this.runningSyncUserState.get()) {
            UserStateSynchronizer.this.syncUserState(false);
          }
        }
      };
    }
    
    boolean doRetry()
    {
      int i = 0;
      synchronized (this.mHandler)
      {
        if (this.currentRetry < 3) {
          i = 1;
        }
        boolean bool = this.mHandler.hasMessages(0);
        if ((i != 0) && (!bool))
        {
          this.currentRetry += 1;
          this.mHandler.postDelayed(getNewRunnable(), this.currentRetry * 15000);
        }
        bool = this.mHandler.hasMessages(0);
        return bool;
      }
    }
    
    void runNewJobDelayed()
    {
      synchronized (this.mHandler)
      {
        this.currentRetry = 0;
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler.postDelayed(getNewRunnable(), 5000L);
        return;
      }
    }
    
    void stopScheduledRunnable()
    {
      this.mHandler.removeCallbacksAndMessages(null);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\UserStateSynchronizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */