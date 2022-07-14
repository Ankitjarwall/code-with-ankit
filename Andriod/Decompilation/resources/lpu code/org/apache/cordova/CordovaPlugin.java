package org.apache.cordova;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;

public class CordovaPlugin
{
  public CordovaInterface cordova;
  protected CordovaPreferences preferences;
  private String serviceName;
  public CordovaWebView webView;
  
  static
  {
    if (!CordovaPlugin.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public boolean execute(String paramString1, String paramString2, CallbackContext paramCallbackContext)
    throws JSONException
  {
    return execute(paramString1, new JSONArray(paramString2), paramCallbackContext);
  }
  
  public boolean execute(String paramString, CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext)
    throws JSONException
  {
    return false;
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    return execute(paramString, new CordovaArgs(paramJSONArray), paramCallbackContext);
  }
  
  protected Uri fromPluginUri(Uri paramUri)
  {
    return Uri.parse(paramUri.getQueryParameter("origUri"));
  }
  
  public String getServiceName()
  {
    return this.serviceName;
  }
  
  public CordovaResourceApi.OpenForReadResult handleOpenForRead(Uri paramUri)
    throws IOException
  {
    throw new FileNotFoundException("Plugin can't handle uri: " + paramUri);
  }
  
  public boolean hasPermisssion()
  {
    return true;
  }
  
  public void initialize(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView) {}
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  public void onConfigurationChanged(Configuration paramConfiguration) {}
  
  public void onDestroy() {}
  
  public Object onMessage(String paramString, Object paramObject)
  {
    return null;
  }
  
  public void onNewIntent(Intent paramIntent) {}
  
  public boolean onOverrideUrlLoading(String paramString)
  {
    return false;
  }
  
  public void onPause(boolean paramBoolean) {}
  
  public boolean onReceivedClientCertRequest(CordovaWebView paramCordovaWebView, ICordovaClientCertRequest paramICordovaClientCertRequest)
  {
    return false;
  }
  
  public boolean onReceivedHttpAuthRequest(CordovaWebView paramCordovaWebView, ICordovaHttpAuthHandler paramICordovaHttpAuthHandler, String paramString1, String paramString2)
  {
    return false;
  }
  
  public void onRequestPermissionResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
    throws JSONException
  {}
  
  public void onReset() {}
  
  public void onRestoreStateForActivityResult(Bundle paramBundle, CallbackContext paramCallbackContext) {}
  
  public void onResume(boolean paramBoolean) {}
  
  public Bundle onSaveInstanceState()
  {
    return null;
  }
  
  public void onStart() {}
  
  public void onStop() {}
  
  protected void pluginInitialize() {}
  
  public final void privateInitialize(String paramString, CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView, CordovaPreferences paramCordovaPreferences)
  {
    assert (this.cordova == null);
    this.serviceName = paramString;
    this.cordova = paramCordovaInterface;
    this.webView = paramCordovaWebView;
    this.preferences = paramCordovaPreferences;
    initialize(paramCordovaInterface, paramCordovaWebView);
    pluginInitialize();
  }
  
  public Uri remapUri(Uri paramUri)
  {
    return null;
  }
  
  public void requestPermissions(int paramInt) {}
  
  public Boolean shouldAllowBridgeAccess(String paramString)
  {
    return shouldAllowNavigation(paramString);
  }
  
  public Boolean shouldAllowNavigation(String paramString)
  {
    return null;
  }
  
  public Boolean shouldAllowRequest(String paramString)
  {
    return null;
  }
  
  public Boolean shouldOpenExternalUrl(String paramString)
  {
    return null;
  }
  
  protected Uri toPluginUri(Uri paramUri)
  {
    return new Uri.Builder().scheme("cdvplugin").authority(this.serviceName).appendQueryParameter("origUri", paramUri.toString()).build();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\CordovaPlugin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */