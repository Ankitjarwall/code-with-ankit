package org.apache.cordova.engine;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import java.util.Arrays;
import org.apache.cordova.CordovaBridge;
import org.apache.cordova.CordovaDialogsHelper;
import org.apache.cordova.CordovaDialogsHelper.Result;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginManager;

public class SystemWebChromeClient
  extends WebChromeClient
{
  private static final int FILECHOOSER_RESULTCODE = 5173;
  private static final String LOG_TAG = "SystemWebChromeClient";
  private long MAX_QUOTA = 104857600L;
  private Context appContext;
  private CordovaDialogsHelper dialogsHelper;
  private View mCustomView;
  private WebChromeClient.CustomViewCallback mCustomViewCallback;
  private View mVideoProgressView;
  protected final SystemWebViewEngine parentEngine;
  
  public SystemWebChromeClient(SystemWebViewEngine paramSystemWebViewEngine)
  {
    this.parentEngine = paramSystemWebViewEngine;
    this.appContext = paramSystemWebViewEngine.webView.getContext();
    this.dialogsHelper = new CordovaDialogsHelper(this.appContext);
  }
  
  public void destroyLastDialog()
  {
    this.dialogsHelper.destroyLastDialog();
  }
  
  public View getVideoLoadingProgressView()
  {
    if (this.mVideoProgressView == null)
    {
      LinearLayout localLinearLayout = new LinearLayout(this.parentEngine.getView().getContext());
      localLinearLayout.setOrientation(1);
      Object localObject = new RelativeLayout.LayoutParams(-2, -2);
      ((RelativeLayout.LayoutParams)localObject).addRule(13);
      localLinearLayout.setLayoutParams((ViewGroup.LayoutParams)localObject);
      localObject = new ProgressBar(this.parentEngine.getView().getContext());
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
      localLayoutParams.gravity = 17;
      ((ProgressBar)localObject).setLayoutParams(localLayoutParams);
      localLinearLayout.addView((View)localObject);
      this.mVideoProgressView = localLinearLayout;
    }
    return this.mVideoProgressView;
  }
  
  public boolean onConsoleMessage(ConsoleMessage paramConsoleMessage)
  {
    if (paramConsoleMessage.message() != null) {
      LOG.d("SystemWebChromeClient", "%s: Line %d : %s", new Object[] { paramConsoleMessage.sourceId(), Integer.valueOf(paramConsoleMessage.lineNumber()), paramConsoleMessage.message() });
    }
    return super.onConsoleMessage(paramConsoleMessage);
  }
  
  public void onExceededDatabaseQuota(String paramString1, String paramString2, long paramLong1, long paramLong2, long paramLong3, WebStorage.QuotaUpdater paramQuotaUpdater)
  {
    LOG.d("SystemWebChromeClient", "onExceededDatabaseQuota estimatedSize: %d  currentQuota: %d  totalUsedQuota: %d", new Object[] { Long.valueOf(paramLong2), Long.valueOf(paramLong1), Long.valueOf(paramLong3) });
    paramQuotaUpdater.updateQuota(this.MAX_QUOTA);
  }
  
  public void onGeolocationPermissionsShowPrompt(String paramString, GeolocationPermissions.Callback paramCallback)
  {
    super.onGeolocationPermissionsShowPrompt(paramString, paramCallback);
    paramCallback.invoke(paramString, true, false);
    paramString = this.parentEngine.pluginManager.getPlugin("Geolocation");
    if ((paramString != null) && (!paramString.hasPermisssion())) {
      paramString.requestPermissions(0);
    }
  }
  
  public void onHideCustomView()
  {
    this.parentEngine.getCordovaWebView().hideCustomView();
  }
  
  public boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, final JsResult paramJsResult)
  {
    this.dialogsHelper.showAlert(paramString2, new CordovaDialogsHelper.Result()
    {
      public void gotResult(boolean paramAnonymousBoolean, String paramAnonymousString)
      {
        if (paramAnonymousBoolean)
        {
          paramJsResult.confirm();
          return;
        }
        paramJsResult.cancel();
      }
    });
    return true;
  }
  
  public boolean onJsConfirm(WebView paramWebView, String paramString1, String paramString2, final JsResult paramJsResult)
  {
    this.dialogsHelper.showConfirm(paramString2, new CordovaDialogsHelper.Result()
    {
      public void gotResult(boolean paramAnonymousBoolean, String paramAnonymousString)
      {
        if (paramAnonymousBoolean)
        {
          paramJsResult.confirm();
          return;
        }
        paramJsResult.cancel();
      }
    });
    return true;
  }
  
  public boolean onJsPrompt(WebView paramWebView, String paramString1, String paramString2, String paramString3, final JsPromptResult paramJsPromptResult)
  {
    paramWebView = this.parentEngine.bridge.promptOnJsPrompt(paramString1, paramString2, paramString3);
    if (paramWebView != null) {
      paramJsPromptResult.confirm(paramWebView);
    }
    for (;;)
    {
      return true;
      this.dialogsHelper.showPrompt(paramString2, paramString3, new CordovaDialogsHelper.Result()
      {
        public void gotResult(boolean paramAnonymousBoolean, String paramAnonymousString)
        {
          if (paramAnonymousBoolean)
          {
            paramJsPromptResult.confirm(paramAnonymousString);
            return;
          }
          paramJsPromptResult.cancel();
        }
      });
    }
  }
  
  @TargetApi(21)
  public void onPermissionRequest(PermissionRequest paramPermissionRequest)
  {
    LOG.d("SystemWebChromeClient", "onPermissionRequest: " + Arrays.toString(paramPermissionRequest.getResources()));
    paramPermissionRequest.grant(paramPermissionRequest.getResources());
  }
  
  public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
  {
    this.parentEngine.getCordovaWebView().showCustomView(paramView, paramCustomViewCallback);
  }
  
  @TargetApi(21)
  public boolean onShowFileChooser(WebView paramWebView, final ValueCallback<Uri[]> paramValueCallback, WebChromeClient.FileChooserParams paramFileChooserParams)
  {
    paramWebView = paramFileChooserParams.createIntent();
    try
    {
      this.parentEngine.cordova.startActivityForResult(new CordovaPlugin()
      {
        public void onActivityResult(int paramAnonymousInt1, int paramAnonymousInt2, Intent paramAnonymousIntent)
        {
          paramAnonymousIntent = WebChromeClient.FileChooserParams.parseResult(paramAnonymousInt2, paramAnonymousIntent);
          LOG.d("SystemWebChromeClient", "Receive file chooser URL: " + paramAnonymousIntent);
          paramValueCallback.onReceiveValue(paramAnonymousIntent);
        }
      }, paramWebView, 5173);
      return true;
    }
    catch (ActivityNotFoundException paramWebView)
    {
      for (;;)
      {
        LOG.w("No activity found to handle file chooser intent.", paramWebView);
        paramValueCallback.onReceiveValue(null);
      }
    }
  }
  
  public void openFileChooser(ValueCallback<Uri> paramValueCallback)
  {
    openFileChooser(paramValueCallback, "*/*");
  }
  
  public void openFileChooser(ValueCallback<Uri> paramValueCallback, String paramString)
  {
    openFileChooser(paramValueCallback, paramString, null);
  }
  
  public void openFileChooser(final ValueCallback<Uri> paramValueCallback, String paramString1, String paramString2)
  {
    paramString1 = new Intent("android.intent.action.GET_CONTENT");
    paramString1.addCategory("android.intent.category.OPENABLE");
    paramString1.setType("*/*");
    this.parentEngine.cordova.startActivityForResult(new CordovaPlugin()
    {
      public void onActivityResult(int paramAnonymousInt1, int paramAnonymousInt2, Intent paramAnonymousIntent)
      {
        if ((paramAnonymousIntent == null) || (paramAnonymousInt2 != -1)) {}
        for (paramAnonymousIntent = null;; paramAnonymousIntent = paramAnonymousIntent.getData())
        {
          LOG.d("SystemWebChromeClient", "Receive file chooser URL: " + paramAnonymousIntent);
          paramValueCallback.onReceiveValue(paramAnonymousIntent);
          return;
        }
      }
    }, paramString1, 5173);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\engine\SystemWebChromeClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */