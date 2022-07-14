package org.apache.cordova;

public abstract interface ICordovaCookieManager
{
  public abstract void clearCookies();
  
  public abstract void flush();
  
  public abstract String getCookie(String paramString);
  
  public abstract void setCookie(String paramString1, String paramString2);
  
  public abstract void setCookiesEnabled(boolean paramBoolean);
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\ICordovaCookieManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */