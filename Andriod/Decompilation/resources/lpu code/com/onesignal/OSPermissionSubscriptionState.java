package com.onesignal;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONObject;

public class OSPermissionSubscriptionState
{
  OSEmailSubscriptionState emailSubscriptionStatus;
  OSPermissionState permissionStatus;
  OSSubscriptionState subscriptionStatus;
  
  public OSEmailSubscriptionState getEmailSubscriptionStatus()
  {
    return this.emailSubscriptionStatus;
  }
  
  public OSPermissionState getPermissionStatus()
  {
    return this.permissionStatus;
  }
  
  public OSSubscriptionState getSubscriptionStatus()
  {
    return this.subscriptionStatus;
  }
  
  public JSONObject toJSONObject()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("permissionStatus", this.permissionStatus.toJSONObject());
      localJSONObject.put("subscriptionStatus", this.subscriptionStatus.toJSONObject());
      localJSONObject.put("emailSubscriptionStatus", this.emailSubscriptionStatus.toJSONObject());
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


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OSPermissionSubscriptionState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */