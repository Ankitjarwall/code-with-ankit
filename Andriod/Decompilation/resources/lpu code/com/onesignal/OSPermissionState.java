package com.onesignal;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONObject;

public class OSPermissionState
  implements Cloneable
{
  private boolean enabled;
  OSObservable<Object, OSPermissionState> observable = new OSObservable("changed", false);
  
  OSPermissionState(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.enabled = OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_ACCEPTED_NOTIFICATION_LAST", false);
      return;
    }
    refreshAsTo();
  }
  
  private void setEnabled(boolean paramBoolean)
  {
    if (this.enabled != paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      this.enabled = paramBoolean;
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
  
  boolean compare(OSPermissionState paramOSPermissionState)
  {
    return this.enabled != paramOSPermissionState.enabled;
  }
  
  public boolean getEnabled()
  {
    return this.enabled;
  }
  
  void persistAsFrom()
  {
    OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_ACCEPTED_NOTIFICATION_LAST", this.enabled);
  }
  
  void refreshAsTo()
  {
    setEnabled(OSUtils.areNotificationsEnabled(OneSignal.appContext));
  }
  
  public JSONObject toJSONObject()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("enabled", this.enabled);
      return localJSONObject;
    }
    catch (Throwable localThrowable)
    {
      ThrowableExtension.printStackTrace(localThrowable);
    }
    return localJSONObject;
  }
  
  public String toString()
  {
    return toJSONObject().toString();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OSPermissionState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */