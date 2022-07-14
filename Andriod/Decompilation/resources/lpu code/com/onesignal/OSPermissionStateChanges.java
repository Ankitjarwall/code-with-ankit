package com.onesignal;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONObject;

public class OSPermissionStateChanges
{
  OSPermissionState from;
  OSPermissionState to;
  
  public OSPermissionState getFrom()
  {
    return this.from;
  }
  
  public OSPermissionState getTo()
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


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OSPermissionStateChanges.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */