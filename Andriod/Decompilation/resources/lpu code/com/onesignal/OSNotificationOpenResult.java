package com.onesignal;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONException;
import org.json.JSONObject;

public class OSNotificationOpenResult
{
  public OSNotificationAction action;
  public OSNotification notification;
  
  @Deprecated
  public String stringify()
  {
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.put("actionID", this.action.actionID);
      localJSONObject2.put("type", this.action.type.ordinal());
      localJSONObject1.put("action", localJSONObject2);
      localJSONObject1.put("notification", new JSONObject(this.notification.stringify()));
      return localJSONObject1.toString();
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        ThrowableExtension.printStackTrace(localJSONException);
      }
    }
  }
  
  public JSONObject toJSONObject()
  {
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.put("actionID", this.action.actionID);
      localJSONObject2.put("type", this.action.type.ordinal());
      localJSONObject1.put("action", localJSONObject2);
      localJSONObject1.put("notification", this.notification.toJSONObject());
      return localJSONObject1;
    }
    catch (JSONException localJSONException)
    {
      ThrowableExtension.printStackTrace(localJSONException);
    }
    return localJSONObject1;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OSNotificationOpenResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */