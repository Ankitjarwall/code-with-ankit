package com.ionicframework.cordova.webview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewEngine.Client;
import org.apache.cordova.NativeToJsMessageQueue;
import org.apache.cordova.PluginManager;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewClient;
import org.apache.cordova.engine.SystemWebViewEngine;

public class IonicWebViewEngine
  extends SystemWebViewEngine
{
  private static final String LAST_BINARY_VERSION_CODE = "lastBinaryVersionCode";
  private static final String LAST_BINARY_VERSION_NAME = "lastBinaryVersionName";
  public static final String TAG = "IonicWebViewEngine";
  private String CDV_LOCAL_SERVER;
  private WebViewLocalServer localServer;
  
  public IonicWebViewEngine(Context paramContext, CordovaPreferences paramCordovaPreferences)
  {
    super(new SystemWebView(paramContext), paramCordovaPreferences);
    Log.d("IonicWebViewEngine", "Ionic Web View Engine Starting Right Up 1...");
  }
  
  public IonicWebViewEngine(SystemWebView paramSystemWebView)
  {
    super(paramSystemWebView, null);
    Log.d("IonicWebViewEngine", "Ionic Web View Engine Starting Right Up 2...");
  }
  
  public IonicWebViewEngine(SystemWebView paramSystemWebView, CordovaPreferences paramCordovaPreferences)
  {
    super(paramSystemWebView, paramCordovaPreferences);
    Log.d("IonicWebViewEngine", "Ionic Web View Engine Starting Right Up 3...");
  }
  
  private boolean isDeployDisabled()
  {
    return this.preferences.getBoolean("DisableDeploy", false);
  }
  
  private boolean isNewBinary()
  {
    boolean bool = false;
    Object localObject2 = "";
    Object localObject3 = "";
    SharedPreferences localSharedPreferences = this.cordova.getActivity().getApplicationContext().getSharedPreferences("WebViewSettings", 0);
    String str1 = localSharedPreferences.getString("lastBinaryVersionCode", null);
    String str2 = localSharedPreferences.getString("lastBinaryVersionName", null);
    Object localObject1 = localObject2;
    try
    {
      Object localObject4 = this.cordova.getActivity().getPackageManager().getPackageInfo(this.cordova.getActivity().getPackageName(), 0);
      localObject1 = localObject2;
      localObject2 = Integer.toString(((PackageInfo)localObject4).versionCode);
      localObject1 = localObject2;
      localObject4 = ((PackageInfo)localObject4).versionName;
      localObject3 = localObject4;
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e("IonicWebViewEngine", "Unable to get package info", localException);
      }
    }
    if ((!((String)localObject1).equals(str1)) || (!((String)localObject3).equals(str2)))
    {
      localObject2 = localSharedPreferences.edit();
      ((SharedPreferences.Editor)localObject2).putString("lastBinaryVersionCode", (String)localObject1);
      ((SharedPreferences.Editor)localObject2).putString("lastBinaryVersionName", (String)localObject3);
      ((SharedPreferences.Editor)localObject2).putString("serverBasePath", "");
      ((SharedPreferences.Editor)localObject2).apply();
      bool = true;
    }
    return bool;
  }
  
  public String getServerBasePath()
  {
    return this.localServer.getBasePath();
  }
  
  public void init(CordovaWebView paramCordovaWebView, CordovaInterface paramCordovaInterface, CordovaWebViewEngine.Client paramClient, CordovaResourceApi paramCordovaResourceApi, PluginManager paramPluginManager, NativeToJsMessageQueue paramNativeToJsMessageQueue)
  {
    ConfigXmlParser localConfigXmlParser = new ConfigXmlParser();
    localConfigXmlParser.parse(paramCordovaInterface.getActivity());
    String str = this.preferences.getString("WKPort", "8080");
    this.CDV_LOCAL_SERVER = ("http://localhost:" + str);
    this.localServer = new WebViewLocalServer(paramCordovaInterface.getActivity(), "localhost:" + str, true, localConfigXmlParser);
    this.localServer.hostAssets("www");
    this.webView.setWebViewClient(new ServerClient(this, localConfigXmlParser));
    super.init(paramCordovaWebView, paramCordovaInterface, paramClient, paramCordovaResourceApi, paramPluginManager, paramNativeToJsMessageQueue);
    paramCordovaWebView = paramCordovaInterface.getActivity().getApplicationContext().getSharedPreferences("WebViewSettings", 0).getString("serverBasePath", null);
    if ((!isDeployDisabled()) && (!isNewBinary()) && (paramCordovaWebView != null) && (!paramCordovaWebView.isEmpty())) {
      setServerBasePath(paramCordovaWebView);
    }
  }
  
  public void setServerBasePath(String paramString)
  {
    this.localServer.hostFiles(paramString);
    this.webView.loadUrl(this.CDV_LOCAL_SERVER);
  }
  
  private class ServerClient
    extends SystemWebViewClient
  {
    private ConfigXmlParser parser;
    
    public ServerClient(SystemWebViewEngine paramSystemWebViewEngine, ConfigXmlParser paramConfigXmlParser)
    {
      super();
      this.parser = paramConfigXmlParser;
    }
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      paramWebView.loadUrl("javascript:(function() { window.WEBVIEW_SERVER_URL = '" + IonicWebViewEngine.this.CDV_LOCAL_SERVER + "'})()");
    }
    
    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      super.onPageStarted(paramWebView, paramString, paramBitmap);
      paramBitmap = this.parser.getLaunchUrl();
      if ((!paramBitmap.contains("http")) && (paramString.equals(paramBitmap)))
      {
        paramWebView.stopLoading();
        paramWebView.loadUrl(IonicWebViewEngine.this.CDV_LOCAL_SERVER);
      }
    }
    
    @RequiresApi(21)
    public WebResourceResponse shouldInterceptRequest(WebView paramWebView, WebResourceRequest paramWebResourceRequest)
    {
      return IonicWebViewEngine.this.localServer.shouldInterceptRequest(paramWebResourceRequest.getUrl());
    }
    
    @TargetApi(19)
    public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString)
    {
      return IonicWebViewEngine.this.localServer.shouldInterceptRequest(Uri.parse(paramString));
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\ionicframework\cordova\webview\IonicWebViewEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */