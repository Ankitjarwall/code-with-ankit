package org.apache.cordova.engine;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import org.apache.cordova.CordovaBridge;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewEngine;
import org.apache.cordova.CordovaWebViewEngine.Client;
import org.apache.cordova.ICordovaCookieManager;
import org.apache.cordova.LOG;
import org.apache.cordova.NativeToJsMessageQueue;
import org.apache.cordova.NativeToJsMessageQueue.EvalBridgeMode;
import org.apache.cordova.NativeToJsMessageQueue.OnlineEventsBridgeMode;
import org.apache.cordova.NativeToJsMessageQueue.OnlineEventsBridgeMode.OnlineEventsBridgeModeDelegate;
import org.apache.cordova.PluginManager;

public class SystemWebViewEngine
  implements CordovaWebViewEngine
{
  public static final String TAG = "SystemWebViewEngine";
  protected CordovaBridge bridge;
  protected CordovaWebViewEngine.Client client;
  protected final SystemCookieManager cookieManager;
  protected CordovaInterface cordova;
  protected NativeToJsMessageQueue nativeToJsMessageQueue;
  protected CordovaWebView parentWebView;
  protected PluginManager pluginManager;
  protected CordovaPreferences preferences;
  private BroadcastReceiver receiver;
  protected CordovaResourceApi resourceApi;
  protected final SystemWebView webView;
  
  public SystemWebViewEngine(Context paramContext, CordovaPreferences paramCordovaPreferences)
  {
    this(new SystemWebView(paramContext), paramCordovaPreferences);
  }
  
  public SystemWebViewEngine(SystemWebView paramSystemWebView)
  {
    this(paramSystemWebView, null);
  }
  
  public SystemWebViewEngine(SystemWebView paramSystemWebView, CordovaPreferences paramCordovaPreferences)
  {
    this.preferences = paramCordovaPreferences;
    this.webView = paramSystemWebView;
    this.cookieManager = new SystemCookieManager(paramSystemWebView);
  }
  
  private void enableRemoteDebugging()
  {
    try
    {
      WebView.setWebContentsDebuggingEnabled(true);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      LOG.d("SystemWebViewEngine", "You have one job! To turn on Remote Web Debugging! YOU HAVE FAILED! ");
      ThrowableExtension.printStackTrace(localIllegalArgumentException);
    }
  }
  
  @SuppressLint({"AddJavascriptInterface"})
  private static void exposeJsInterface(WebView paramWebView, CordovaBridge paramCordovaBridge)
  {
    paramWebView.addJavascriptInterface(new SystemExposedJsApi(paramCordovaBridge), "_cordovaNative");
  }
  
  @SuppressLint({"NewApi", "SetJavaScriptEnabled"})
  private void initWebViewSettings()
  {
    this.webView.setInitialScale(0);
    this.webView.setVerticalScrollBarEnabled(false);
    final WebSettings localWebSettings = this.webView.getSettings();
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    localWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
    Object localObject = Build.MANUFACTURER;
    LOG.d("SystemWebViewEngine", "CordovaWebView is running on device made by: " + (String)localObject);
    localWebSettings.setSaveFormData(false);
    localWebSettings.setSavePassword(false);
    localWebSettings.setAllowUniversalAccessFromFileURLs(true);
    localWebSettings.setMediaPlaybackRequiresUserGesture(false);
    localObject = this.webView.getContext().getApplicationContext().getDir("database", 0).getPath();
    localWebSettings.setDatabaseEnabled(true);
    localWebSettings.setDatabasePath((String)localObject);
    if ((this.webView.getContext().getApplicationContext().getApplicationInfo().flags & 0x2) != 0) {
      enableRemoteDebugging();
    }
    localWebSettings.setGeolocationDatabasePath((String)localObject);
    localWebSettings.setDomStorageEnabled(true);
    localWebSettings.setGeolocationEnabled(true);
    localWebSettings.setAppCacheMaxSize(5242880L);
    localWebSettings.setAppCachePath((String)localObject);
    localWebSettings.setAppCacheEnabled(true);
    localObject = localWebSettings.getUserAgentString();
    String str = this.preferences.getString("OverrideUserAgent", null);
    if (str != null) {
      localWebSettings.setUserAgentString(str);
    }
    for (;;)
    {
      localObject = new IntentFilter();
      ((IntentFilter)localObject).addAction("android.intent.action.CONFIGURATION_CHANGED");
      if (this.receiver == null)
      {
        this.receiver = new BroadcastReceiver()
        {
          public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
          {
            localWebSettings.getUserAgentString();
          }
        };
        this.webView.getContext().registerReceiver(this.receiver, (IntentFilter)localObject);
      }
      return;
      str = this.preferences.getString("AppendUserAgent", null);
      if (str != null) {
        localWebSettings.setUserAgentString((String)localObject + " " + str);
      }
    }
  }
  
  public boolean canGoBack()
  {
    return this.webView.canGoBack();
  }
  
  public void clearCache()
  {
    this.webView.clearCache(true);
  }
  
  public void clearHistory()
  {
    this.webView.clearHistory();
  }
  
  public void destroy()
  {
    this.webView.chromeClient.destroyLastDialog();
    this.webView.destroy();
    if (this.receiver != null) {}
    try
    {
      this.webView.getContext().unregisterReceiver(this.receiver);
      return;
    }
    catch (Exception localException)
    {
      LOG.e("SystemWebViewEngine", "Error unregistering configuration receiver: " + localException.getMessage(), localException);
    }
  }
  
  public void evaluateJavascript(String paramString, ValueCallback<String> paramValueCallback)
  {
    this.webView.evaluateJavascript(paramString, paramValueCallback);
  }
  
  public ICordovaCookieManager getCookieManager()
  {
    return this.cookieManager;
  }
  
  public CordovaWebView getCordovaWebView()
  {
    return this.parentWebView;
  }
  
  public String getUrl()
  {
    return this.webView.getUrl();
  }
  
  public View getView()
  {
    return this.webView;
  }
  
  public boolean goBack()
  {
    if (this.webView.canGoBack())
    {
      this.webView.goBack();
      return true;
    }
    return false;
  }
  
  public void init(CordovaWebView paramCordovaWebView, CordovaInterface paramCordovaInterface, CordovaWebViewEngine.Client paramClient, CordovaResourceApi paramCordovaResourceApi, PluginManager paramPluginManager, NativeToJsMessageQueue paramNativeToJsMessageQueue)
  {
    if (this.cordova != null) {
      throw new IllegalStateException();
    }
    if (this.preferences == null) {
      this.preferences = paramCordovaWebView.getPreferences();
    }
    this.parentWebView = paramCordovaWebView;
    this.cordova = paramCordovaInterface;
    this.client = paramClient;
    this.resourceApi = paramCordovaResourceApi;
    this.pluginManager = paramPluginManager;
    this.nativeToJsMessageQueue = paramNativeToJsMessageQueue;
    this.webView.init(this, paramCordovaInterface);
    initWebViewSettings();
    paramNativeToJsMessageQueue.addBridgeMode(new NativeToJsMessageQueue.OnlineEventsBridgeMode(new NativeToJsMessageQueue.OnlineEventsBridgeMode.OnlineEventsBridgeModeDelegate()
    {
      public void runOnUiThread(Runnable paramAnonymousRunnable)
      {
        SystemWebViewEngine.this.cordova.getActivity().runOnUiThread(paramAnonymousRunnable);
      }
      
      public void setNetworkAvailable(boolean paramAnonymousBoolean)
      {
        if (SystemWebViewEngine.this.webView != null) {
          SystemWebViewEngine.this.webView.setNetworkAvailable(paramAnonymousBoolean);
        }
      }
    }));
    paramNativeToJsMessageQueue.addBridgeMode(new NativeToJsMessageQueue.EvalBridgeMode(this, paramCordovaInterface));
    this.bridge = new CordovaBridge(paramPluginManager, paramNativeToJsMessageQueue);
    exposeJsInterface(this.webView, this.bridge);
  }
  
  public void loadUrl(String paramString, boolean paramBoolean)
  {
    this.webView.loadUrl(paramString);
  }
  
  public void setPaused(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.webView.onPause();
      this.webView.pauseTimers();
      return;
    }
    this.webView.onResume();
    this.webView.resumeTimers();
  }
  
  public void stopLoading()
  {
    this.webView.stopLoading();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\engine\SystemWebViewEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */