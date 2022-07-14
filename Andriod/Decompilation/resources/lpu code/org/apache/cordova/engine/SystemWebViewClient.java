package org.apache.cordova.engine;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import org.apache.cordova.AuthenticationToken;
import org.apache.cordova.CordovaBridge;
import org.apache.cordova.CordovaClientCertRequest;
import org.apache.cordova.CordovaHttpAuthHandler;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaResourceApi.OpenForReadResult;
import org.apache.cordova.CordovaWebViewEngine.Client;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginManager;

public class SystemWebViewClient
  extends WebViewClient
{
  private static final String TAG = "SystemWebViewClient";
  private Hashtable<String, AuthenticationToken> authenticationTokens = new Hashtable();
  private boolean doClearHistory = false;
  boolean isCurrentlyLoading;
  protected final SystemWebViewEngine parentEngine;
  
  public SystemWebViewClient(SystemWebViewEngine paramSystemWebViewEngine)
  {
    this.parentEngine = paramSystemWebViewEngine;
  }
  
  private static boolean needsKitKatContentUrlFix(Uri paramUri)
  {
    return "content".equals(paramUri.getScheme());
  }
  
  private static boolean needsSpecialsInAssetUrlFix(Uri paramUri)
  {
    if (CordovaResourceApi.getUriType(paramUri) != 1) {}
    do
    {
      return false;
      if ((paramUri.getQuery() != null) || (paramUri.getFragment() != null)) {
        return true;
      }
    } while (paramUri.toString().contains("%"));
    return false;
  }
  
  public void clearAuthenticationTokens()
  {
    this.authenticationTokens.clear();
  }
  
  public AuthenticationToken getAuthenticationToken(String paramString1, String paramString2)
  {
    AuthenticationToken localAuthenticationToken = (AuthenticationToken)this.authenticationTokens.get(paramString1.concat(paramString2));
    Object localObject = localAuthenticationToken;
    if (localAuthenticationToken == null)
    {
      localObject = (AuthenticationToken)this.authenticationTokens.get(paramString1);
      paramString1 = (String)localObject;
      if (localObject == null) {
        paramString1 = (AuthenticationToken)this.authenticationTokens.get(paramString2);
      }
      localObject = paramString1;
      if (paramString1 == null) {
        localObject = (AuthenticationToken)this.authenticationTokens.get("");
      }
    }
    return (AuthenticationToken)localObject;
  }
  
  public void onPageFinished(WebView paramWebView, String paramString)
  {
    super.onPageFinished(paramWebView, paramString);
    if ((!this.isCurrentlyLoading) && (!paramString.startsWith("about:"))) {
      return;
    }
    this.isCurrentlyLoading = false;
    if (this.doClearHistory)
    {
      paramWebView.clearHistory();
      this.doClearHistory = false;
    }
    this.parentEngine.client.onPageFinishedLoading(paramString);
  }
  
  public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    super.onPageStarted(paramWebView, paramString, paramBitmap);
    this.isCurrentlyLoading = true;
    this.parentEngine.bridge.reset();
    this.parentEngine.client.onPageStarted(paramString);
  }
  
  @TargetApi(21)
  public void onReceivedClientCertRequest(WebView paramWebView, ClientCertRequest paramClientCertRequest)
  {
    PluginManager localPluginManager = this.parentEngine.pluginManager;
    if ((localPluginManager != null) && (localPluginManager.onReceivedClientCertRequest(null, new CordovaClientCertRequest(paramClientCertRequest))))
    {
      this.parentEngine.client.clearLoadTimeoutTimer();
      return;
    }
    super.onReceivedClientCertRequest(paramWebView, paramClientCertRequest);
  }
  
  public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    if (!this.isCurrentlyLoading) {
      return;
    }
    LOG.d("SystemWebViewClient", "CordovaWebViewClient.onReceivedError: Error code=%s Description=%s URL=%s", new Object[] { Integer.valueOf(paramInt), paramString1, paramString2 });
    if (paramInt == -10)
    {
      this.parentEngine.client.clearLoadTimeoutTimer();
      if (paramWebView.canGoBack())
      {
        paramWebView.goBack();
        return;
      }
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
    }
    this.parentEngine.client.onReceivedError(paramInt, paramString1, paramString2);
  }
  
  public void onReceivedHttpAuthRequest(WebView paramWebView, HttpAuthHandler paramHttpAuthHandler, String paramString1, String paramString2)
  {
    Object localObject = getAuthenticationToken(paramString1, paramString2);
    if (localObject != null)
    {
      paramHttpAuthHandler.proceed(((AuthenticationToken)localObject).getUserName(), ((AuthenticationToken)localObject).getPassword());
      return;
    }
    localObject = this.parentEngine.pluginManager;
    if ((localObject != null) && (((PluginManager)localObject).onReceivedHttpAuthRequest(null, new CordovaHttpAuthHandler(paramHttpAuthHandler), paramString1, paramString2)))
    {
      this.parentEngine.client.clearLoadTimeoutTimer();
      return;
    }
    super.onReceivedHttpAuthRequest(paramWebView, paramHttpAuthHandler, paramString1, paramString2);
  }
  
  public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
  {
    String str = this.parentEngine.cordova.getActivity().getPackageName();
    PackageManager localPackageManager = this.parentEngine.cordova.getActivity().getPackageManager();
    try
    {
      if ((localPackageManager.getApplicationInfo(str, 128).flags & 0x2) != 0)
      {
        paramSslErrorHandler.proceed();
        return;
      }
      super.onReceivedSslError(paramWebView, paramSslErrorHandler, paramSslError);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      super.onReceivedSslError(paramWebView, paramSslErrorHandler, paramSslError);
    }
  }
  
  public AuthenticationToken removeAuthenticationToken(String paramString1, String paramString2)
  {
    return (AuthenticationToken)this.authenticationTokens.remove(paramString1.concat(paramString2));
  }
  
  public void setAuthenticationToken(AuthenticationToken paramAuthenticationToken, String paramString1, String paramString2)
  {
    String str = paramString1;
    if (paramString1 == null) {
      str = "";
    }
    paramString1 = paramString2;
    if (paramString2 == null) {
      paramString1 = "";
    }
    this.authenticationTokens.put(str.concat(paramString1), paramAuthenticationToken);
  }
  
  public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString)
  {
    try
    {
      if (!this.parentEngine.pluginManager.shouldAllowRequest(paramString))
      {
        LOG.w("SystemWebViewClient", "URL blocked by whitelist: " + paramString);
        return new WebResourceResponse("text/plain", "UTF-8", null);
      }
      paramWebView = this.parentEngine.resourceApi;
      paramString = Uri.parse(paramString);
      Uri localUri = paramWebView.remapUri(paramString);
      if ((!paramString.equals(localUri)) || (needsSpecialsInAssetUrlFix(paramString)) || (needsKitKatContentUrlFix(paramString)))
      {
        paramWebView = paramWebView.openForRead(localUri, true);
        paramWebView = new WebResourceResponse(paramWebView.mimeType, "UTF-8", paramWebView.inputStream);
        return paramWebView;
      }
    }
    catch (IOException paramWebView)
    {
      if (!(paramWebView instanceof FileNotFoundException)) {
        LOG.e("SystemWebViewClient", "Error occurred while loading a file (returning a 404).", paramWebView);
      }
      return new WebResourceResponse("text/plain", "UTF-8", null);
    }
    return null;
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    return this.parentEngine.client.onNavigationAttempt(paramString);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\engine\SystemWebViewClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */