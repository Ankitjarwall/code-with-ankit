package org.apache.cordova;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient.CustomViewCallback;
import java.util.List;
import java.util.Map;

public abstract interface CordovaWebView
{
  public static final String CORDOVA_VERSION = "7.1.4";
  
  public abstract boolean backHistory();
  
  public abstract boolean canGoBack();
  
  public abstract void clearCache();
  
  @Deprecated
  public abstract void clearCache(boolean paramBoolean);
  
  public abstract void clearHistory();
  
  public abstract Context getContext();
  
  public abstract ICordovaCookieManager getCookieManager();
  
  public abstract CordovaWebViewEngine getEngine();
  
  public abstract PluginManager getPluginManager();
  
  public abstract CordovaPreferences getPreferences();
  
  public abstract CordovaResourceApi getResourceApi();
  
  public abstract String getUrl();
  
  public abstract View getView();
  
  public abstract void handleDestroy();
  
  public abstract void handlePause(boolean paramBoolean);
  
  public abstract void handleResume(boolean paramBoolean);
  
  public abstract void handleStart();
  
  public abstract void handleStop();
  
  @Deprecated
  public abstract void hideCustomView();
  
  public abstract void init(CordovaInterface paramCordovaInterface, List<PluginEntry> paramList, CordovaPreferences paramCordovaPreferences);
  
  public abstract boolean isButtonPlumbedToJs(int paramInt);
  
  @Deprecated
  public abstract boolean isCustomViewShowing();
  
  public abstract boolean isInitialized();
  
  public abstract void loadUrl(String paramString);
  
  public abstract void loadUrlIntoView(String paramString, boolean paramBoolean);
  
  public abstract void onNewIntent(Intent paramIntent);
  
  public abstract Object postMessage(String paramString, Object paramObject);
  
  @Deprecated
  public abstract void sendJavascript(String paramString);
  
  public abstract void sendPluginResult(PluginResult paramPluginResult, String paramString);
  
  public abstract void setButtonPlumbedToJs(int paramInt, boolean paramBoolean);
  
  @Deprecated
  public abstract void showCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback);
  
  public abstract void showWebPage(String paramString, boolean paramBoolean1, boolean paramBoolean2, Map<String, Object> paramMap);
  
  public abstract void stopLoading();
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\CordovaWebView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */