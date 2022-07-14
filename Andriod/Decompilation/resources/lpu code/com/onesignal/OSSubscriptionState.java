package com.onesignal;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONObject;

public class OSSubscriptionState
  implements Cloneable
{
  private boolean accepted;
  OSObservable<Object, OSSubscriptionState> observable = new OSObservable("changed", false);
  private String pushToken;
  private String userId;
  private boolean userSubscriptionSetting;
  
  OSSubscriptionState(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean1)
    {
      this.userSubscriptionSetting = OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_SUBSCRIPTION_LAST", false);
      this.userId = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_PLAYER_ID_LAST", null);
      this.pushToken = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_PUSH_TOKEN_LAST", null);
      this.accepted = OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_PERMISSION_ACCEPTED_LAST", false);
      return;
    }
    this.userSubscriptionSetting = OneSignalStateSynchronizer.getUserSubscribePreference();
    this.userId = OneSignal.getUserId();
    this.pushToken = OneSignalStateSynchronizer.getRegistrationId();
    this.accepted = paramBoolean2;
  }
  
  private void setAccepted(boolean paramBoolean)
  {
    boolean bool = getSubscribed();
    this.accepted = paramBoolean;
    if (bool != getSubscribed()) {
      this.observable.notifyChange(this);
    }
  }
  
  void changed(OSPermissionState paramOSPermissionState)
  {
    setAccepted(paramOSPermissionState.getEnabled());
  }
  
  protected Object clone()
  {
    try
    {
      Object localObject = super.clone();
      return localObject;
    }
    catch (Throwable localThrowable) {}
    return null;
  }
  
  boolean compare(OSSubscriptionState paramOSSubscriptionState)
  {
    String str1;
    if (this.userSubscriptionSetting == paramOSSubscriptionState.userSubscriptionSetting)
    {
      if (this.userId == null) {
        break label88;
      }
      str1 = this.userId;
      if (paramOSSubscriptionState.userId == null) {
        break label94;
      }
      str2 = paramOSSubscriptionState.userId;
      label35:
      if (str1.equals(str2))
      {
        if (this.pushToken == null) {
          break label100;
        }
        str1 = this.pushToken;
        label55:
        if (paramOSSubscriptionState.pushToken == null) {
          break label106;
        }
      }
    }
    label88:
    label94:
    label100:
    label106:
    for (String str2 = paramOSSubscriptionState.pushToken;; str2 = "")
    {
      if ((str1.equals(str2)) && (this.accepted == paramOSSubscriptionState.accepted)) {
        break label112;
      }
      return true;
      str1 = "";
      break;
      str2 = "";
      break label35;
      str1 = "";
      break label55;
    }
    label112:
    return false;
  }
  
  public String getPushToken()
  {
    return this.pushToken;
  }
  
  public boolean getSubscribed()
  {
    return (this.userId != null) && (this.pushToken != null) && (this.userSubscriptionSetting) && (this.accepted);
  }
  
  public String getUserId()
  {
    return this.userId;
  }
  
  public boolean getUserSubscriptionSetting()
  {
    return this.userSubscriptionSetting;
  }
  
  void persistAsFrom()
  {
    OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_SUBSCRIPTION_LAST", this.userSubscriptionSetting);
    OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_PLAYER_ID_LAST", this.userId);
    OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_PUSH_TOKEN_LAST", this.pushToken);
    OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_PERMISSION_ACCEPTED_LAST", this.accepted);
  }
  
  void setPushToken(String paramString)
  {
    if (paramString == null) {
      return;
    }
    if (!paramString.equals(this.pushToken)) {}
    for (int i = 1;; i = 0)
    {
      this.pushToken = paramString;
      if (i == 0) {
        break;
      }
      this.observable.notifyChange(this);
      return;
    }
  }
  
  void setUserId(String paramString)
  {
    if (!paramString.equals(this.userId)) {}
    for (int i = 1;; i = 0)
    {
      this.userId = paramString;
      if (i != 0) {
        this.observable.notifyChange(this);
      }
      return;
    }
  }
  
  void setUserSubscriptionSetting(boolean paramBoolean)
  {
    if (this.userSubscriptionSetting != paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      this.userSubscriptionSetting = paramBoolean;
      if (i != 0) {
        this.observable.notifyChange(this);
      }
      return;
    }
  }
  
  public JSONObject toJSONObject()
  {
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        if (this.userId != null)
        {
          localJSONObject.put("userId", this.userId);
          if (this.pushToken != null)
          {
            localJSONObject.put("pushToken", this.pushToken);
            localJSONObject.put("userSubscriptionSetting", this.userSubscriptionSetting);
            localJSONObject.put("subscribed", getSubscribed());
            return localJSONObject;
          }
        }
        else
        {
          localJSONObject.put("userId", JSONObject.NULL);
          continue;
        }
        localJSONObject.put("pushToken", JSONObject.NULL);
      }
      catch (Throwable localThrowable)
      {
        ThrowableExtension.printStackTrace(localThrowable);
        return localJSONObject;
      }
    }
  }
  
  public String toString()
  {
    return toJSONObject().toString();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OSSubscriptionState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */