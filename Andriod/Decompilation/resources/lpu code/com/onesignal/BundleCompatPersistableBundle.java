package com.onesignal;

import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;

@RequiresApi(api=22)
class BundleCompatPersistableBundle
  implements BundleCompat<PersistableBundle>
{
  private PersistableBundle mBundle;
  
  BundleCompatPersistableBundle()
  {
    this.mBundle = new PersistableBundle();
  }
  
  BundleCompatPersistableBundle(PersistableBundle paramPersistableBundle)
  {
    this.mBundle = paramPersistableBundle;
  }
  
  public boolean containsKey(String paramString)
  {
    return this.mBundle.containsKey(paramString);
  }
  
  public boolean getBoolean(String paramString)
  {
    return this.mBundle.getBoolean(paramString);
  }
  
  public boolean getBoolean(String paramString, boolean paramBoolean)
  {
    return this.mBundle.getBoolean(paramString, paramBoolean);
  }
  
  public PersistableBundle getBundle()
  {
    return this.mBundle;
  }
  
  public Integer getInt(String paramString)
  {
    return Integer.valueOf(this.mBundle.getInt(paramString));
  }
  
  public Long getLong(String paramString)
  {
    return Long.valueOf(this.mBundle.getLong(paramString));
  }
  
  public String getString(String paramString)
  {
    return this.mBundle.getString(paramString);
  }
  
  public void putBoolean(String paramString, Boolean paramBoolean)
  {
    this.mBundle.putBoolean(paramString, paramBoolean.booleanValue());
  }
  
  public void putInt(String paramString, Integer paramInteger)
  {
    this.mBundle.putInt(paramString, paramInteger.intValue());
  }
  
  public void putLong(String paramString, Long paramLong)
  {
    this.mBundle.putLong(paramString, paramLong.longValue());
  }
  
  public void putString(String paramString1, String paramString2)
  {
    this.mBundle.putString(paramString1, paramString2);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\BundleCompatPersistableBundle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */