package org.apache.cordova;

import android.os.Bundle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CordovaPreferences
{
  private Bundle preferencesBundleExtras;
  private HashMap<String, String> prefs = new HashMap(20);
  
  public boolean contains(String paramString)
  {
    return getString(paramString, null) != null;
  }
  
  public Map<String, String> getAll()
  {
    return this.prefs;
  }
  
  public boolean getBoolean(String paramString, boolean paramBoolean)
  {
    paramString = paramString.toLowerCase(Locale.ENGLISH);
    paramString = (String)this.prefs.get(paramString);
    if (paramString != null) {
      paramBoolean = Boolean.parseBoolean(paramString);
    }
    return paramBoolean;
  }
  
  public double getDouble(String paramString, double paramDouble)
  {
    paramString = paramString.toLowerCase(Locale.ENGLISH);
    paramString = (String)this.prefs.get(paramString);
    if (paramString != null) {
      paramDouble = Double.valueOf(paramString).doubleValue();
    }
    return paramDouble;
  }
  
  public int getInteger(String paramString, int paramInt)
  {
    paramString = paramString.toLowerCase(Locale.ENGLISH);
    paramString = (String)this.prefs.get(paramString);
    if (paramString != null) {
      paramInt = (int)Long.decode(paramString).longValue();
    }
    return paramInt;
  }
  
  public String getString(String paramString1, String paramString2)
  {
    paramString1 = paramString1.toLowerCase(Locale.ENGLISH);
    paramString1 = (String)this.prefs.get(paramString1);
    if (paramString1 != null) {
      return paramString1;
    }
    return paramString2;
  }
  
  public void set(String paramString, double paramDouble)
  {
    set(paramString, "" + paramDouble);
  }
  
  public void set(String paramString, int paramInt)
  {
    set(paramString, "" + paramInt);
  }
  
  public void set(String paramString1, String paramString2)
  {
    this.prefs.put(paramString1.toLowerCase(Locale.ENGLISH), paramString2);
  }
  
  public void set(String paramString, boolean paramBoolean)
  {
    set(paramString, "" + paramBoolean);
  }
  
  public void setPreferencesBundle(Bundle paramBundle)
  {
    this.preferencesBundleExtras = paramBundle;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\CordovaPreferences.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */