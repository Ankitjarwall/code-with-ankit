package com.onesignal;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONObject;

public class OSEmailSubscriptionStateChanges
{
  OSEmailSubscriptionState from;
  OSEmailSubscriptionState to;
  
  public OSEmailSubscriptionState getFrom()
  {
    return this.from;
  }
  
  public OSEmailSubscriptionState getTo()
  {
    return this.to;
  }
  
  public JSONObject toJSONObject()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("from", this.from.toJSONObject());
      localJSONObject.put("to", this.to.toJSONObject());
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


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OSEmailSubscriptionStateChanges.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */