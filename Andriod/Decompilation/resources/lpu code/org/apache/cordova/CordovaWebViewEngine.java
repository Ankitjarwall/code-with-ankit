package org.apache.cordova;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;

public abstract interface CordovaWebViewEngine
{
  public abstract boolean canGoBack();
  
  public abstract void clearCache();
  
  public abstract void clearHistory();
  
  public abstract void destroy();
  
  public abstract void evaluateJavascript(String paramString, ValueCallback<String> paramValueCallback);
  
  public abstract ICordovaCookieManager getCookieManager();
  
  public abstract CordovaWebView getCordovaWebView();
  
  public abstract String getUrl();
  
  public abstract View getView();
  
  public abstract boolean goBack();
  
  public abstract void init(CordovaWebView paramCordovaWebView, CordovaInterface paramCordovaInterface, Client paramClient, CordovaResourceApi paramCordovaResourceApi, PluginManager paramPluginManager, NativeToJsMessageQueue paramNativeToJsMessageQueue);
  
  public abstract void loadUrl(String paramString, boolean paramBoolean);
  
  public abstract void setPaused(boolean paramBoolean);
  
  public abstract void stopLoading();
  
  public static abstract interface Client
  {
    public abstract void clearLoadTimeoutTimer();
    
    public abstract Boolean onDispatchKeyEvent(KeyEvent paramKeyEvent);
    
    public abstract boolean onNavigationAttempt(String paramString);
    
    public abstract void onPageFinishedLoading(String paramString);
    
    public abstract void onPageStarted(String paramString);
    
    public abstract void onReceivedError(int paramInt, String paramString1, String paramString2);
  }
  
  public static abstract interface EngineView
  {
    public abstract CordovaWebView getCordovaWebView();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\CordovaWebViewEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */