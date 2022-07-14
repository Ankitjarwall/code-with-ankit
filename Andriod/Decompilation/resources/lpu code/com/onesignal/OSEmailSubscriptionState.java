package com.onesignal;

import android.support.annotation.NonNull;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONObject;

public class OSEmailSubscriptionState
  implements Cloneable
{
  private String emailAddress;
  private String emailUserId;
  OSObservable<Object, OSEmailSubscriptionState> observable = new OSObservable("changed", false);
  
  OSEmailSubscriptionState(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.emailUserId = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_ONESIGNAL_EMAIL_ID_LAST", null);
      this.emailAddress = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_ONESIGNAL_EMAIL_ADDRESS_LAST", null);
      return;
    }
    this.emailUserId = OneSignal.getEmailId();
    this.emailAddress = OneSignalStateSynchronizer.getEmailStateSynchronizer().getRegistrationId();
  }
  
  void clearEmailAndId()
  {
    if ((this.emailUserId != null) || (this.emailAddress != null)) {}
    for (int i = 1;; i = 0)
    {
      this.emailUserId = null;
      this.emailAddress = null;
      if (i != 0) {
        this.observable.notifyChange(this);
      }
      return;
    }
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
  
  boolean compare(OSEmailSubscriptionState paramOSEmailSubscriptionState)
  {
    String str1;
    String str2;
    if (this.emailUserId != null)
    {
      str1 = this.emailUserId;
      if (paramOSEmailSubscriptionState.emailUserId == null) {
        break label72;
      }
      str2 = paramOSEmailSubscriptionState.emailUserId;
      label24:
      if (str1.equals(str2))
      {
        if (this.emailAddress == null) {
          break label78;
        }
        str1 = this.emailAddress;
        label44:
        if (paramOSEmailSubscriptionState.emailAddress == null) {
          break label84;
        }
      }
    }
    label72:
    label78:
    label84:
    for (paramOSEmailSubscriptionState = paramOSEmailSubscriptionState.emailAddress;; paramOSEmailSubscriptionState = "")
    {
      if (str1.equals(paramOSEmailSubscriptionState)) {
        break label90;
      }
      return true;
      str1 = "";
      break;
      str2 = "";
      break label24;
      str1 = "";
      break label44;
    }
    label90:
    return false;
  }
  
  public String getEmailAddress()
  {
    return this.emailAddress;
  }
  
  public String getEmailUserId()
  {
    return this.emailUserId;
  }
  
  public boolean getSubscribed()
  {
    return (this.emailUserId != null) && (this.emailAddress != null);
  }
  
  void persistAsFrom()
  {
    OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_ONESIGNAL_EMAIL_ID_LAST", this.emailUserId);
    OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_ONESIGNAL_EMAIL_ADDRESS_LAST", this.emailAddress);
  }
  
  void setEmailAddress(@NonNull String paramString)
  {
    if (!paramString.equals(this.emailAddress)) {}
    for (int i = 1;; i = 0)
    {
      this.emailAddress = paramString;
      if (i != 0) {
        this.observable.notifyChange(this);
      }
      return;
    }
  }
  
  void setEmailUserId(@NonNull String paramString)
  {
    if (!paramString.equals(this.emailUserId)) {}
    for (int i = 1;; i = 0)
    {
      this.emailUserId = paramString;
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
        if (this.emailUserId != null)
        {
          localJSONObject.put("emailUserId", this.emailUserId);
          if (this.emailAddress != null)
          {
            localJSONObject.put("emailAddress", this.emailAddress);
            localJSONObject.put("subscribed", getSubscribed());
            return localJSONObject;
          }
        }
        else
        {
          localJSONObject.put("emailUserId", JSONObject.NULL);
          continue;
        }
        localJSONObject.put("emailAddress", JSONObject.NULL);
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


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OSEmailSubscriptionState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */