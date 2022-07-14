package com.onesignal;

public abstract interface BundleCompat<T>
{
  public abstract boolean containsKey(String paramString);
  
  public abstract boolean getBoolean(String paramString);
  
  public abstract boolean getBoolean(String paramString, boolean paramBoolean);
  
  public abstract T getBundle();
  
  public abstract Integer getInt(String paramString);
  
  public abstract Long getLong(String paramString);
  
  public abstract String getString(String paramString);
  
  public abstract void putBoolean(String paramString, Boolean paramBoolean);
  
  public abstract void putInt(String paramString, Integer paramInteger);
  
  public abstract void putLong(String paramString, Long paramLong);
  
  public abstract void putString(String paramString1, String paramString2);
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\BundleCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */