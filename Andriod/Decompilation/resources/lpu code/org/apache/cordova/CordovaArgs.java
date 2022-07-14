package org.apache.cordova;

import android.util.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaArgs
{
  private JSONArray baseArgs;
  
  public CordovaArgs(JSONArray paramJSONArray)
  {
    this.baseArgs = paramJSONArray;
  }
  
  public Object get(int paramInt)
    throws JSONException
  {
    return this.baseArgs.get(paramInt);
  }
  
  public byte[] getArrayBuffer(int paramInt)
    throws JSONException
  {
    return Base64.decode(this.baseArgs.getString(paramInt), 0);
  }
  
  public boolean getBoolean(int paramInt)
    throws JSONException
  {
    return this.baseArgs.getBoolean(paramInt);
  }
  
  public double getDouble(int paramInt)
    throws JSONException
  {
    return this.baseArgs.getDouble(paramInt);
  }
  
  public int getInt(int paramInt)
    throws JSONException
  {
    return this.baseArgs.getInt(paramInt);
  }
  
  public JSONArray getJSONArray(int paramInt)
    throws JSONException
  {
    return this.baseArgs.getJSONArray(paramInt);
  }
  
  public JSONObject getJSONObject(int paramInt)
    throws JSONException
  {
    return this.baseArgs.getJSONObject(paramInt);
  }
  
  public long getLong(int paramInt)
    throws JSONException
  {
    return this.baseArgs.getLong(paramInt);
  }
  
  public String getString(int paramInt)
    throws JSONException
  {
    return this.baseArgs.getString(paramInt);
  }
  
  public boolean isNull(int paramInt)
  {
    return this.baseArgs.isNull(paramInt);
  }
  
  public Object opt(int paramInt)
  {
    return this.baseArgs.opt(paramInt);
  }
  
  public boolean optBoolean(int paramInt)
  {
    return this.baseArgs.optBoolean(paramInt);
  }
  
  public double optDouble(int paramInt)
  {
    return this.baseArgs.optDouble(paramInt);
  }
  
  public int optInt(int paramInt)
  {
    return this.baseArgs.optInt(paramInt);
  }
  
  public JSONArray optJSONArray(int paramInt)
  {
    return this.baseArgs.optJSONArray(paramInt);
  }
  
  public JSONObject optJSONObject(int paramInt)
  {
    return this.baseArgs.optJSONObject(paramInt);
  }
  
  public long optLong(int paramInt)
  {
    return this.baseArgs.optLong(paramInt);
  }
  
  public String optString(int paramInt)
  {
    return this.baseArgs.optString(paramInt);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\CordovaArgs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */