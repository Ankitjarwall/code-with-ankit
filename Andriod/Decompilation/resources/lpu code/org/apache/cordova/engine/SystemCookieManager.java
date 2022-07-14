package org.apache.cordova.engine;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.webkit.CookieManager;
import android.webkit.WebView;
import org.apache.cordova.ICordovaCookieManager;

class SystemCookieManager
  implements ICordovaCookieManager
{
  private final CookieManager cookieManager;
  protected final WebView webView;
  
  @TargetApi(21)
  public SystemCookieManager(WebView paramWebView)
  {
    this.webView = paramWebView;
    this.cookieManager = CookieManager.getInstance();
    paramWebView = this.cookieManager;
    CookieManager.setAcceptFileSchemeCookies(true);
    if (Build.VERSION.SDK_INT >= 21) {
      this.cookieManager.setAcceptThirdPartyCookies(this.webView, true);
    }
  }
  
  public void clearCookies()
  {
    this.cookieManager.removeAllCookie();
  }
  
  public void flush()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      this.cookieManager.flush();
    }
  }
  
  public String getCookie(String paramString)
  {
    return this.cookieManager.getCookie(paramString);
  }
  
  public void setCookie(String paramString1, String paramString2)
  {
    this.cookieManager.setCookie(paramString1, paramString2);
  }
  
  public void setCookiesEnabled(boolean paramBoolean)
  {
    this.cookieManager.setAcceptCookie(paramBoolean);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\engine\SystemCookieManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */