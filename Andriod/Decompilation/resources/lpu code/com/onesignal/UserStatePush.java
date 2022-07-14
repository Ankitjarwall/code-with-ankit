package com.onesignal;

import org.json.JSONException;
import org.json.JSONObject;

class UserStatePush
  extends UserState
{
  UserStatePush(String paramString, boolean paramBoolean)
  {
    super(paramString, paramBoolean);
  }
  
  private int getNotificationTypes()
  {
    int i = this.dependValues.optInt("subscribableStatus", 1);
    if (i < -2) {
      return i;
    }
    if (!this.dependValues.optBoolean("androidPermission", true)) {
      return 0;
    }
    if (!this.dependValues.optBoolean("userSubscribePref", true)) {
      return -2;
    }
    return 1;
  }
  
  protected void addDependFields()
  {
    try
    {
      this.syncValues.put("notification_types", getNotificationTypes());
      return;
    }
    catch (JSONException localJSONException) {}
  }
  
  boolean isSubscribed()
  {
    return getNotificationTypes() > 0;
  }
  
  UserState newInstance(String paramString)
  {
    return new UserStatePush(paramString, false);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\UserStatePush.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */