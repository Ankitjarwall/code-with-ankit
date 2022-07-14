package org.apache.cordova;

import android.webkit.HttpAuthHandler;

public class CordovaHttpAuthHandler
  implements ICordovaHttpAuthHandler
{
  private final HttpAuthHandler handler;
  
  public CordovaHttpAuthHandler(HttpAuthHandler paramHttpAuthHandler)
  {
    this.handler = paramHttpAuthHandler;
  }
  
  public void cancel()
  {
    this.handler.cancel();
  }
  
  public void proceed(String paramString1, String paramString2)
  {
    this.handler.proceed(paramString1, paramString2);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\CordovaHttpAuthHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */