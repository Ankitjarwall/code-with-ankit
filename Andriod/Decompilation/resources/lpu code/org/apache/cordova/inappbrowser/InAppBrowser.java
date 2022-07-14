package org.apache.cordova.inappbrowser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled"})
public class InAppBrowser
  extends CordovaPlugin
{
  private static final String CLEAR_ALL_CACHE = "clearcache";
  private static final String CLEAR_SESSION_CACHE = "clearsessioncache";
  private static final String CLOSE_BUTTON_CAPTION = "closebuttoncaption";
  private static final String CLOSE_BUTTON_COLOR = "closebuttoncolor";
  private static final Boolean DEFAULT_HARDWARE_BACK = Boolean.valueOf(true);
  private static final String EXIT_EVENT = "exit";
  private static final int FILECHOOSER_REQUESTCODE = 1;
  private static final int FILECHOOSER_REQUESTCODE_LOLLIPOP = 2;
  private static final String FOOTER = "footer";
  private static final String FOOTER_COLOR = "footercolor";
  private static final String HARDWARE_BACK_BUTTON = "hardwareback";
  private static final String HIDDEN = "hidden";
  private static final String HIDE_NAVIGATION = "hidenavigationbuttons";
  private static final String HIDE_URL = "hideurlbar";
  private static final String LOAD_ERROR_EVENT = "loaderror";
  private static final String LOAD_START_EVENT = "loadstart";
  private static final String LOAD_STOP_EVENT = "loadstop";
  private static final String LOCATION = "location";
  protected static final String LOG_TAG = "InAppBrowser";
  private static final String MEDIA_PLAYBACK_REQUIRES_USER_ACTION = "mediaPlaybackRequiresUserAction";
  private static final String NAVIGATION_COLOR = "navigationbuttoncolor";
  private static final String NULL = "null";
  private static final String SELF = "_self";
  private static final String SHOULD_PAUSE = "shouldPauseOnSuspend";
  private static final String SYSTEM = "_system";
  private static final String TOOLBAR_COLOR = "toolbarcolor";
  private static final String USER_WIDE_VIEW_PORT = "useWideViewPort";
  private static final String ZOOM = "zoom";
  private static final List customizableOptions = Arrays.asList(new String[] { "closebuttoncaption", "toolbarcolor", "navigationbuttoncolor", "closebuttoncolor", "footercolor" });
  private String[] allowedSchemes;
  private CallbackContext callbackContext;
  private boolean clearAllCache = false;
  private boolean clearSessionCache = false;
  private String closeButtonCaption = "";
  private String closeButtonColor = "";
  private InAppBrowserDialog dialog;
  private EditText edittext;
  private String footerColor = "";
  private boolean hadwareBackButton = true;
  private boolean hideNavigationButtons = false;
  private boolean hideUrlBar = false;
  private WebView inAppWebView;
  private ValueCallback<Uri> mUploadCallback;
  private ValueCallback<Uri[]> mUploadCallbackLollipop;
  private boolean mediaPlaybackRequiresUserGesture = false;
  private String navigationButtonColor = "";
  private boolean openWindowHidden = false;
  private boolean shouldPauseInAppBrowser = false;
  private boolean showFooter = false;
  private boolean showLocationBar = true;
  private boolean showZoomControls = true;
  private int toolbarColor = -3355444;
  private boolean useWideViewPort = true;
  
  private InAppBrowser getInAppBrowser()
  {
    return this;
  }
  
  private boolean getShowLocationBar()
  {
    return this.showLocationBar;
  }
  
  private void goForward()
  {
    if (this.inAppWebView.canGoForward()) {
      this.inAppWebView.goForward();
    }
  }
  
  private void injectDeferredObject(final String paramString1, String paramString2)
  {
    if (this.inAppWebView != null)
    {
      if (paramString2 != null)
      {
        JSONArray localJSONArray = new JSONArray();
        localJSONArray.put(paramString1);
        paramString1 = localJSONArray.toString();
        paramString1 = String.format(paramString2, new Object[] { paramString1.substring(1, paramString1.length() - 1) });
      }
      for (;;)
      {
        this.cordova.getActivity().runOnUiThread(new Runnable()
        {
          @SuppressLint({"NewApi"})
          public void run()
          {
            if (Build.VERSION.SDK_INT < 19)
            {
              InAppBrowser.this.inAppWebView.loadUrl("javascript:" + paramString1);
              return;
            }
            InAppBrowser.this.inAppWebView.evaluateJavascript(paramString1, null);
          }
        });
        return;
      }
    }
    LOG.d("InAppBrowser", "Can't inject code into the system browser");
  }
  
  private void navigate(String paramString)
  {
    ((InputMethodManager)this.cordova.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.edittext.getWindowToken(), 0);
    if ((!paramString.startsWith("http")) && (!paramString.startsWith("file:"))) {
      this.inAppWebView.loadUrl("http://" + paramString);
    }
    for (;;)
    {
      this.inAppWebView.requestFocus();
      return;
      this.inAppWebView.loadUrl(paramString);
    }
  }
  
  private HashMap<String, String> parseFeature(String paramString)
  {
    if (paramString.equals("null"))
    {
      paramString = null;
      return paramString;
    }
    HashMap localHashMap = new HashMap();
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ",");
    do
    {
      paramString = localHashMap;
      if (!localStringTokenizer.hasMoreElements()) {
        break;
      }
      paramString = new StringTokenizer(localStringTokenizer.nextToken(), "=");
    } while (!paramString.hasMoreElements());
    String str2 = paramString.nextToken();
    String str1 = paramString.nextToken();
    paramString = str1;
    if (!customizableOptions.contains(str2))
    {
      paramString = str1;
      if (!str1.equals("yes")) {
        if (!str1.equals("no")) {
          break label128;
        }
      }
    }
    label128:
    for (paramString = str1;; paramString = "yes")
    {
      localHashMap.put(str2, paramString);
      break;
    }
  }
  
  private void sendUpdate(JSONObject paramJSONObject, boolean paramBoolean)
  {
    sendUpdate(paramJSONObject, paramBoolean, PluginResult.Status.OK);
  }
  
  private void sendUpdate(JSONObject paramJSONObject, boolean paramBoolean, PluginResult.Status paramStatus)
  {
    if (this.callbackContext != null)
    {
      paramJSONObject = new PluginResult(paramStatus, paramJSONObject);
      paramJSONObject.setKeepCallback(paramBoolean);
      this.callbackContext.sendPluginResult(paramJSONObject);
      if (!paramBoolean) {
        this.callbackContext = null;
      }
    }
  }
  
  public boolean canGoBack()
  {
    return this.inAppWebView.canGoBack();
  }
  
  public void closeDialog()
  {
    this.cordova.getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        Object localObject = InAppBrowser.this.inAppWebView;
        if (localObject == null) {
          return;
        }
        ((WebView)localObject).setWebViewClient(new WebViewClient()
        {
          public void onPageFinished(WebView paramAnonymous2WebView, String paramAnonymous2String)
          {
            if (InAppBrowser.this.dialog != null)
            {
              InAppBrowser.this.dialog.dismiss();
              InAppBrowser.access$002(InAppBrowser.this, null);
            }
          }
        });
        ((WebView)localObject).loadUrl("about:blank");
        try
        {
          localObject = new JSONObject();
          ((JSONObject)localObject).put("type", "exit");
          InAppBrowser.this.sendUpdate((JSONObject)localObject, false);
          return;
        }
        catch (JSONException localJSONException)
        {
          LOG.d("InAppBrowser", "Should never happen");
        }
      }
    });
  }
  
  public boolean execute(final String paramString, final CordovaArgs paramCordovaArgs, final CallbackContext paramCallbackContext)
    throws JSONException
  {
    boolean bool = false;
    if (paramString.equals("open"))
    {
      this.callbackContext = paramCallbackContext;
      final String str2 = paramCordovaArgs.getString(0);
      String str1 = paramCordovaArgs.optString(1);
      if ((str1 != null) && (!str1.equals("")))
      {
        paramString = str1;
        if (!str1.equals("null")) {}
      }
      else
      {
        paramString = "_self";
      }
      paramCordovaArgs = parseFeature(paramCordovaArgs.optString(2));
      LOG.d("InAppBrowser", "target = " + paramString);
      this.cordova.getActivity().runOnUiThread(new Runnable()
      {
        /* Error */
        public void run()
        {
          // Byte code:
          //   0: ldc 47
          //   2: astore_3
          //   3: ldc 49
          //   5: aload_0
          //   6: getfield 26	org/apache/cordova/inappbrowser/InAppBrowser$1:val$target	Ljava/lang/String;
          //   9: invokevirtual 55	java/lang/String:equals	(Ljava/lang/Object;)Z
          //   12: ifeq +434 -> 446
          //   15: ldc 57
          //   17: ldc 59
          //   19: invokestatic 65	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
          //   22: aconst_null
          //   23: astore_2
          //   24: aload_0
          //   25: getfield 28	org/apache/cordova/inappbrowser/InAppBrowser$1:val$url	Ljava/lang/String;
          //   28: ldc 67
          //   30: invokevirtual 71	java/lang/String:startsWith	(Ljava/lang/String;)Z
          //   33: ifeq +8 -> 41
          //   36: iconst_1
          //   37: invokestatic 77	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
          //   40: astore_2
          //   41: aload_2
          //   42: astore_1
          //   43: aload_2
          //   44: ifnonnull +38 -> 82
          //   47: ldc 79
          //   49: ldc 81
          //   51: iconst_1
          //   52: anewarray 83	java/lang/Class
          //   55: dup
          //   56: iconst_0
          //   57: ldc 51
          //   59: aastore
          //   60: invokevirtual 87	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
          //   63: aconst_null
          //   64: iconst_1
          //   65: anewarray 4	java/lang/Object
          //   68: dup
          //   69: iconst_0
          //   70: aload_0
          //   71: getfield 28	org/apache/cordova/inappbrowser/InAppBrowser$1:val$url	Ljava/lang/String;
          //   74: aastore
          //   75: invokevirtual 93	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
          //   78: checkcast 73	java/lang/Boolean
          //   81: astore_1
          //   82: aload_1
          //   83: astore_2
          //   84: aload_1
          //   85: ifnonnull +77 -> 162
          //   88: aload_0
          //   89: getfield 24	org/apache/cordova/inappbrowser/InAppBrowser$1:this$0	Lorg/apache/cordova/inappbrowser/InAppBrowser;
          //   92: getfield 97	org/apache/cordova/inappbrowser/InAppBrowser:webView	Lorg/apache/cordova/CordovaWebView;
          //   95: invokevirtual 101	java/lang/Object:getClass	()Ljava/lang/Class;
          //   98: ldc 103
          //   100: iconst_0
          //   101: anewarray 83	java/lang/Class
          //   104: invokevirtual 87	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
          //   107: aload_0
          //   108: getfield 24	org/apache/cordova/inappbrowser/InAppBrowser$1:this$0	Lorg/apache/cordova/inappbrowser/InAppBrowser;
          //   111: getfield 97	org/apache/cordova/inappbrowser/InAppBrowser:webView	Lorg/apache/cordova/CordovaWebView;
          //   114: iconst_0
          //   115: anewarray 4	java/lang/Object
          //   118: invokevirtual 93	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
          //   121: checkcast 105	org/apache/cordova/PluginManager
          //   124: astore_2
          //   125: aload_2
          //   126: invokevirtual 101	java/lang/Object:getClass	()Ljava/lang/Class;
          //   129: ldc 107
          //   131: iconst_1
          //   132: anewarray 83	java/lang/Class
          //   135: dup
          //   136: iconst_0
          //   137: ldc 51
          //   139: aastore
          //   140: invokevirtual 87	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
          //   143: aload_2
          //   144: iconst_1
          //   145: anewarray 4	java/lang/Object
          //   148: dup
          //   149: iconst_0
          //   150: aload_0
          //   151: getfield 28	org/apache/cordova/inappbrowser/InAppBrowser$1:val$url	Ljava/lang/String;
          //   154: aastore
          //   155: invokevirtual 93	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
          //   158: checkcast 73	java/lang/Boolean
          //   161: astore_2
          //   162: getstatic 111	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
          //   165: aload_2
          //   166: invokevirtual 112	java/lang/Boolean:equals	(Ljava/lang/Object;)Z
          //   169: ifeq +144 -> 313
          //   172: ldc 57
          //   174: ldc 114
          //   176: invokestatic 65	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
          //   179: aload_0
          //   180: getfield 24	org/apache/cordova/inappbrowser/InAppBrowser$1:this$0	Lorg/apache/cordova/inappbrowser/InAppBrowser;
          //   183: getfield 97	org/apache/cordova/inappbrowser/InAppBrowser:webView	Lorg/apache/cordova/CordovaWebView;
          //   186: aload_0
          //   187: getfield 28	org/apache/cordova/inappbrowser/InAppBrowser$1:val$url	Ljava/lang/String;
          //   190: invokeinterface 120 2 0
          //   195: aload_3
          //   196: astore_1
          //   197: new 122	org/apache/cordova/PluginResult
          //   200: dup
          //   201: getstatic 128	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   204: aload_1
          //   205: invokespecial 131	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Ljava/lang/String;)V
          //   208: astore_1
          //   209: aload_1
          //   210: iconst_1
          //   211: invokevirtual 135	org/apache/cordova/PluginResult:setKeepCallback	(Z)V
          //   214: aload_0
          //   215: getfield 32	org/apache/cordova/inappbrowser/InAppBrowser$1:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   218: aload_1
          //   219: invokevirtual 141	org/apache/cordova/CallbackContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   222: return
          //   223: astore_1
          //   224: ldc 57
          //   226: aload_1
          //   227: invokevirtual 145	java/lang/NoSuchMethodException:getLocalizedMessage	()Ljava/lang/String;
          //   230: invokestatic 65	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
          //   233: aload_2
          //   234: astore_1
          //   235: goto -153 -> 82
          //   238: astore_1
          //   239: ldc 57
          //   241: aload_1
          //   242: invokevirtual 146	java/lang/IllegalAccessException:getLocalizedMessage	()Ljava/lang/String;
          //   245: invokestatic 65	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
          //   248: aload_2
          //   249: astore_1
          //   250: goto -168 -> 82
          //   253: astore_1
          //   254: ldc 57
          //   256: aload_1
          //   257: invokevirtual 147	java/lang/reflect/InvocationTargetException:getLocalizedMessage	()Ljava/lang/String;
          //   260: invokestatic 65	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
          //   263: aload_2
          //   264: astore_1
          //   265: goto -183 -> 82
          //   268: astore_2
          //   269: ldc 57
          //   271: aload_2
          //   272: invokevirtual 145	java/lang/NoSuchMethodException:getLocalizedMessage	()Ljava/lang/String;
          //   275: invokestatic 65	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
          //   278: aload_1
          //   279: astore_2
          //   280: goto -118 -> 162
          //   283: astore_2
          //   284: ldc 57
          //   286: aload_2
          //   287: invokevirtual 146	java/lang/IllegalAccessException:getLocalizedMessage	()Ljava/lang/String;
          //   290: invokestatic 65	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
          //   293: aload_1
          //   294: astore_2
          //   295: goto -133 -> 162
          //   298: astore_2
          //   299: ldc 57
          //   301: aload_2
          //   302: invokevirtual 147	java/lang/reflect/InvocationTargetException:getLocalizedMessage	()Ljava/lang/String;
          //   305: invokestatic 65	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
          //   308: aload_1
          //   309: astore_2
          //   310: goto -148 -> 162
          //   313: aload_0
          //   314: getfield 28	org/apache/cordova/inappbrowser/InAppBrowser$1:val$url	Ljava/lang/String;
          //   317: ldc -107
          //   319: invokevirtual 71	java/lang/String:startsWith	(Ljava/lang/String;)Z
          //   322: ifeq +98 -> 420
          //   325: ldc 57
          //   327: ldc -105
          //   329: invokestatic 65	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
          //   332: new 153	android/content/Intent
          //   335: dup
          //   336: ldc -101
          //   338: invokespecial 157	android/content/Intent:<init>	(Ljava/lang/String;)V
          //   341: astore_1
          //   342: aload_1
          //   343: aload_0
          //   344: getfield 28	org/apache/cordova/inappbrowser/InAppBrowser$1:val$url	Ljava/lang/String;
          //   347: invokestatic 163	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
          //   350: invokevirtual 167	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
          //   353: pop
          //   354: aload_0
          //   355: getfield 24	org/apache/cordova/inappbrowser/InAppBrowser$1:this$0	Lorg/apache/cordova/inappbrowser/InAppBrowser;
          //   358: getfield 171	org/apache/cordova/inappbrowser/InAppBrowser:cordova	Lorg/apache/cordova/CordovaInterface;
          //   361: invokeinterface 177 1 0
          //   366: aload_1
          //   367: invokevirtual 183	android/app/Activity:startActivity	(Landroid/content/Intent;)V
          //   370: aload_3
          //   371: astore_1
          //   372: goto -175 -> 197
          //   375: astore_1
          //   376: ldc 57
          //   378: new 185	java/lang/StringBuilder
          //   381: dup
          //   382: invokespecial 186	java/lang/StringBuilder:<init>	()V
          //   385: ldc -68
          //   387: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   390: aload_0
          //   391: getfield 28	org/apache/cordova/inappbrowser/InAppBrowser$1:val$url	Ljava/lang/String;
          //   394: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   397: ldc -62
          //   399: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   402: aload_1
          //   403: invokevirtual 197	android/content/ActivityNotFoundException:toString	()Ljava/lang/String;
          //   406: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   409: invokevirtual 198	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   412: invokestatic 201	org/apache/cordova/LOG:e	(Ljava/lang/String;Ljava/lang/String;)V
          //   415: aload_3
          //   416: astore_1
          //   417: goto -220 -> 197
          //   420: ldc 57
          //   422: ldc -53
          //   424: invokestatic 65	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
          //   427: aload_0
          //   428: getfield 24	org/apache/cordova/inappbrowser/InAppBrowser$1:this$0	Lorg/apache/cordova/inappbrowser/InAppBrowser;
          //   431: aload_0
          //   432: getfield 28	org/apache/cordova/inappbrowser/InAppBrowser$1:val$url	Ljava/lang/String;
          //   435: aload_0
          //   436: getfield 30	org/apache/cordova/inappbrowser/InAppBrowser$1:val$features	Ljava/util/HashMap;
          //   439: invokevirtual 207	org/apache/cordova/inappbrowser/InAppBrowser:showWebPage	(Ljava/lang/String;Ljava/util/HashMap;)Ljava/lang/String;
          //   442: astore_1
          //   443: goto -246 -> 197
          //   446: ldc -47
          //   448: aload_0
          //   449: getfield 26	org/apache/cordova/inappbrowser/InAppBrowser$1:val$target	Ljava/lang/String;
          //   452: invokevirtual 55	java/lang/String:equals	(Ljava/lang/Object;)Z
          //   455: ifeq +25 -> 480
          //   458: ldc 57
          //   460: ldc -45
          //   462: invokestatic 65	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
          //   465: aload_0
          //   466: getfield 24	org/apache/cordova/inappbrowser/InAppBrowser$1:this$0	Lorg/apache/cordova/inappbrowser/InAppBrowser;
          //   469: aload_0
          //   470: getfield 28	org/apache/cordova/inappbrowser/InAppBrowser$1:val$url	Ljava/lang/String;
          //   473: invokevirtual 215	org/apache/cordova/inappbrowser/InAppBrowser:openExternal	(Ljava/lang/String;)Ljava/lang/String;
          //   476: astore_1
          //   477: goto -280 -> 197
          //   480: ldc 57
          //   482: ldc -39
          //   484: invokestatic 65	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
          //   487: aload_0
          //   488: getfield 24	org/apache/cordova/inappbrowser/InAppBrowser$1:this$0	Lorg/apache/cordova/inappbrowser/InAppBrowser;
          //   491: aload_0
          //   492: getfield 28	org/apache/cordova/inappbrowser/InAppBrowser$1:val$url	Ljava/lang/String;
          //   495: aload_0
          //   496: getfield 30	org/apache/cordova/inappbrowser/InAppBrowser$1:val$features	Ljava/util/HashMap;
          //   499: invokevirtual 207	org/apache/cordova/inappbrowser/InAppBrowser:showWebPage	(Ljava/lang/String;Ljava/util/HashMap;)Ljava/lang/String;
          //   502: astore_1
          //   503: goto -306 -> 197
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	506	0	this	1
          //   42	177	1	localObject1	Object
          //   223	4	1	localNoSuchMethodException1	NoSuchMethodException
          //   234	1	1	localObject2	Object
          //   238	4	1	localIllegalAccessException1	IllegalAccessException
          //   249	1	1	localObject3	Object
          //   253	4	1	localInvocationTargetException1	java.lang.reflect.InvocationTargetException
          //   264	108	1	localObject4	Object
          //   375	28	1	localActivityNotFoundException	ActivityNotFoundException
          //   416	87	1	str1	String
          //   23	241	2	localObject5	Object
          //   268	4	2	localNoSuchMethodException2	NoSuchMethodException
          //   279	1	2	localObject6	Object
          //   283	4	2	localIllegalAccessException2	IllegalAccessException
          //   294	1	2	localObject7	Object
          //   298	4	2	localInvocationTargetException2	java.lang.reflect.InvocationTargetException
          //   309	1	2	localObject8	Object
          //   2	414	3	str2	String
          // Exception table:
          //   from	to	target	type
          //   47	82	223	java/lang/NoSuchMethodException
          //   47	82	238	java/lang/IllegalAccessException
          //   47	82	253	java/lang/reflect/InvocationTargetException
          //   88	162	268	java/lang/NoSuchMethodException
          //   88	162	283	java/lang/IllegalAccessException
          //   88	162	298	java/lang/reflect/InvocationTargetException
          //   325	370	375	android/content/ActivityNotFoundException
        }
      });
    }
    for (;;)
    {
      bool = true;
      do
      {
        return bool;
        if (paramString.equals("close"))
        {
          closeDialog();
          break;
        }
        if (paramString.equals("injectScriptCode"))
        {
          paramString = null;
          if (paramCordovaArgs.getBoolean(1)) {
            paramString = String.format("(function(){prompt(JSON.stringify([eval(%%s)]), 'gap-iab://%s')})()", new Object[] { paramCallbackContext.getCallbackId() });
          }
          injectDeferredObject(paramCordovaArgs.getString(0), paramString);
          break;
        }
        if (paramString.equals("injectScriptFile"))
        {
          if (paramCordovaArgs.getBoolean(1)) {}
          for (paramString = String.format("(function(d) { var c = d.createElement('script'); c.src = %%s; c.onload = function() { prompt('', 'gap-iab://%s'); }; d.body.appendChild(c); })(document)", new Object[] { paramCallbackContext.getCallbackId() });; paramString = "(function(d) { var c = d.createElement('script'); c.src = %s; d.body.appendChild(c); })(document)")
          {
            injectDeferredObject(paramCordovaArgs.getString(0), paramString);
            break;
          }
        }
        if (paramString.equals("injectStyleCode"))
        {
          if (paramCordovaArgs.getBoolean(1)) {}
          for (paramString = String.format("(function(d) { var c = d.createElement('style'); c.innerHTML = %%s; d.body.appendChild(c); prompt('', 'gap-iab://%s');})(document)", new Object[] { paramCallbackContext.getCallbackId() });; paramString = "(function(d) { var c = d.createElement('style'); c.innerHTML = %s; d.body.appendChild(c); })(document)")
          {
            injectDeferredObject(paramCordovaArgs.getString(0), paramString);
            break;
          }
        }
        if (paramString.equals("injectStyleFile"))
        {
          if (paramCordovaArgs.getBoolean(1)) {}
          for (paramString = String.format("(function(d) { var c = d.createElement('link'); c.rel='stylesheet'; c.type='text/css'; c.href = %%s; d.head.appendChild(c); prompt('', 'gap-iab://%s');})(document)", new Object[] { paramCallbackContext.getCallbackId() });; paramString = "(function(d) { var c = d.createElement('link'); c.rel='stylesheet'; c.type='text/css'; c.href = %s; d.head.appendChild(c); })(document)")
          {
            injectDeferredObject(paramCordovaArgs.getString(0), paramString);
            break;
          }
        }
        if (paramString.equals("show"))
        {
          this.cordova.getActivity().runOnUiThread(new Runnable()
          {
            public void run()
            {
              InAppBrowser.this.dialog.show();
            }
          });
          paramString = new PluginResult(PluginResult.Status.OK);
          paramString.setKeepCallback(true);
          this.callbackContext.sendPluginResult(paramString);
          break;
        }
      } while (!paramString.equals("hide"));
      this.cordova.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          InAppBrowser.this.dialog.hide();
        }
      });
      paramString = new PluginResult(PluginResult.Status.OK);
      paramString.setKeepCallback(true);
      this.callbackContext.sendPluginResult(paramString);
    }
  }
  
  public void goBack()
  {
    if (this.inAppWebView.canGoBack()) {
      this.inAppWebView.goBack();
    }
  }
  
  public boolean hardwareBack()
  {
    return this.hadwareBackButton;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      LOG.d("InAppBrowser", "onActivityResult (For Android >= 5.0)");
      if ((paramInt1 != 2) || (this.mUploadCallbackLollipop == null)) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
      }
    }
    do
    {
      return;
      this.mUploadCallbackLollipop.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(paramInt2, paramIntent));
      this.mUploadCallbackLollipop = null;
      return;
      LOG.d("InAppBrowser", "onActivityResult (For Android < 5.0)");
      if ((paramInt1 != 1) || (this.mUploadCallback == null))
      {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        return;
      }
    } while (this.mUploadCallback == null);
    if (paramIntent != null)
    {
      this.cordova.getActivity();
      if (paramInt2 == -1) {
        break label128;
      }
    }
    label128:
    for (paramIntent = null;; paramIntent = paramIntent.getData())
    {
      this.mUploadCallback.onReceiveValue(paramIntent);
      this.mUploadCallback = null;
      return;
    }
  }
  
  public void onDestroy()
  {
    closeDialog();
  }
  
  public void onPause(boolean paramBoolean)
  {
    if (this.shouldPauseInAppBrowser) {
      this.inAppWebView.onPause();
    }
  }
  
  public void onReset()
  {
    closeDialog();
  }
  
  public void onResume(boolean paramBoolean)
  {
    if (this.shouldPauseInAppBrowser) {
      this.inAppWebView.onResume();
    }
  }
  
  public String openExternal(String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      try
      {
        Uri localUri = Uri.parse(paramString);
        if ("file".equals(localUri.getScheme())) {
          localIntent.setDataAndType(localUri, this.webView.getResourceApi().getMimeType(localUri));
        }
        for (;;)
        {
          localIntent.putExtra("com.android.browser.application_id", this.cordova.getActivity().getPackageName());
          this.cordova.getActivity().startActivity(localIntent);
          return "";
          localIntent.setData(localUri);
        }
        LOG.d("InAppBrowser", "InAppBrowser: Error loading url " + paramString + ":" + localRuntimeException1.toString());
      }
      catch (RuntimeException localRuntimeException1) {}
    }
    catch (RuntimeException localRuntimeException2)
    {
      for (;;) {}
    }
    return localRuntimeException1.toString();
  }
  
  public String showWebPage(final String paramString, HashMap<String, String> paramHashMap)
  {
    this.showLocationBar = true;
    this.showZoomControls = true;
    this.openWindowHidden = false;
    this.mediaPlaybackRequiresUserGesture = false;
    String str1;
    if (paramHashMap != null)
    {
      str1 = (String)paramHashMap.get("location");
      if (str1 != null)
      {
        if (!str1.equals("yes")) {
          break label545;
        }
        bool = true;
        this.showLocationBar = bool;
      }
      if (this.showLocationBar)
      {
        str1 = (String)paramHashMap.get("hidenavigationbuttons");
        String str2 = (String)paramHashMap.get("hideurlbar");
        if (str1 != null)
        {
          if (!str1.equals("yes")) {
            break label550;
          }
          bool = true;
          label105:
          this.hideNavigationButtons = bool;
        }
        if (str2 != null)
        {
          if (!str2.equals("yes")) {
            break label555;
          }
          bool = true;
          label128:
          this.hideUrlBar = bool;
        }
      }
      str1 = (String)paramHashMap.get("zoom");
      if (str1 != null)
      {
        if (!str1.equals("yes")) {
          break label560;
        }
        bool = true;
        label162:
        this.showZoomControls = bool;
      }
      str1 = (String)paramHashMap.get("hidden");
      if (str1 != null)
      {
        if (!str1.equals("yes")) {
          break label565;
        }
        bool = true;
        label196:
        this.openWindowHidden = bool;
      }
      str1 = (String)paramHashMap.get("hardwareback");
      if (str1 == null) {
        break label575;
      }
      if (!str1.equals("yes")) {
        break label570;
      }
      bool = true;
      label230:
      this.hadwareBackButton = bool;
      label235:
      str1 = (String)paramHashMap.get("mediaPlaybackRequiresUserAction");
      if (str1 != null)
      {
        if (!str1.equals("yes")) {
          break label588;
        }
        bool = true;
        label264:
        this.mediaPlaybackRequiresUserGesture = bool;
      }
      str1 = (String)paramHashMap.get("clearcache");
      if (str1 == null) {
        break label598;
      }
      if (!str1.equals("yes")) {
        break label593;
      }
      bool = true;
      label298:
      this.clearAllCache = bool;
      label303:
      str1 = (String)paramHashMap.get("shouldPauseOnSuspend");
      if (str1 != null)
      {
        if (!str1.equals("yes")) {
          break label640;
        }
        bool = true;
        label332:
        this.shouldPauseInAppBrowser = bool;
      }
      str1 = (String)paramHashMap.get("useWideViewPort");
      if (str1 != null)
      {
        if (!str1.equals("yes")) {
          break label645;
        }
        bool = true;
        label366:
        this.useWideViewPort = bool;
      }
      str1 = (String)paramHashMap.get("closebuttoncaption");
      if (str1 != null) {
        this.closeButtonCaption = str1;
      }
      str1 = (String)paramHashMap.get("closebuttoncolor");
      if (str1 != null) {
        this.closeButtonColor = str1;
      }
      str1 = (String)paramHashMap.get("toolbarcolor");
      if (str1 != null) {
        this.toolbarColor = Color.parseColor(str1);
      }
      str1 = (String)paramHashMap.get("navigationbuttoncolor");
      if (str1 != null) {
        this.navigationButtonColor = str1;
      }
      str1 = (String)paramHashMap.get("footer");
      if (str1 != null) {
        if (!str1.equals("yes")) {
          break label650;
        }
      }
    }
    label545:
    label550:
    label555:
    label560:
    label565:
    label570:
    label575:
    label588:
    label593:
    label598:
    label640:
    label645:
    label650:
    for (boolean bool = true;; bool = false)
    {
      this.showFooter = bool;
      paramHashMap = (String)paramHashMap.get("footercolor");
      if (paramHashMap != null) {
        this.footerColor = paramHashMap;
      }
      paramString = new Runnable()
      {
        private View createCloseButton(int paramAnonymousInt)
        {
          Object localObject2 = InAppBrowser.this.cordova.getActivity().getResources();
          Object localObject1;
          if (InAppBrowser.this.closeButtonCaption != "")
          {
            localObject1 = new TextView(InAppBrowser.this.cordova.getActivity());
            ((TextView)localObject1).setText(InAppBrowser.this.closeButtonCaption);
            ((TextView)localObject1).setTextSize(20.0F);
            if (InAppBrowser.this.closeButtonColor != "") {
              ((TextView)localObject1).setTextColor(Color.parseColor(InAppBrowser.this.closeButtonColor));
            }
            ((TextView)localObject1).setGravity(16);
            ((TextView)localObject1).setPadding(dpToPixels(10), 0, dpToPixels(10), 0);
            localObject2 = new RelativeLayout.LayoutParams(-2, -1);
            ((RelativeLayout.LayoutParams)localObject2).addRule(11);
            ((View)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
            if (Build.VERSION.SDK_INT < 16) {
              break label283;
            }
            ((View)localObject1).setBackground(null);
          }
          for (;;)
          {
            ((View)localObject1).setContentDescription("Close Button");
            ((View)localObject1).setId(Integer.valueOf(paramAnonymousInt).intValue());
            ((View)localObject1).setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                InAppBrowser.this.closeDialog();
              }
            });
            return (View)localObject1;
            localObject1 = new ImageButton(InAppBrowser.this.cordova.getActivity());
            localObject2 = ((Resources)localObject2).getDrawable(((Resources)localObject2).getIdentifier("ic_action_remove", "drawable", InAppBrowser.this.cordova.getActivity().getPackageName()));
            if (InAppBrowser.this.closeButtonColor != "") {
              ((ImageButton)localObject1).setColorFilter(Color.parseColor(InAppBrowser.this.closeButtonColor));
            }
            ((ImageButton)localObject1).setImageDrawable((Drawable)localObject2);
            ((ImageButton)localObject1).setScaleType(ImageView.ScaleType.FIT_CENTER);
            if (Build.VERSION.SDK_INT >= 16) {
              ((ImageButton)localObject1).getAdjustViewBounds();
            }
            break;
            label283:
            ((View)localObject1).setBackgroundDrawable(null);
          }
        }
        
        private int dpToPixels(int paramAnonymousInt)
        {
          return (int)TypedValue.applyDimension(1, paramAnonymousInt, InAppBrowser.this.cordova.getActivity().getResources().getDisplayMetrics());
        }
        
        @SuppressLint({"NewApi"})
        public void run()
        {
          if (InAppBrowser.this.dialog != null) {
            InAppBrowser.this.dialog.dismiss();
          }
          InAppBrowser.access$002(InAppBrowser.this, new InAppBrowserDialog(InAppBrowser.this.cordova.getActivity(), 16973830));
          InAppBrowser.this.dialog.getWindow().getAttributes().windowAnimations = 16973826;
          InAppBrowser.this.dialog.requestWindowFeature(1);
          InAppBrowser.this.dialog.setCancelable(true);
          InAppBrowser.this.dialog.setInAppBroswer(InAppBrowser.this.getInAppBrowser());
          LinearLayout localLinearLayout = new LinearLayout(InAppBrowser.this.cordova.getActivity());
          localLinearLayout.setOrientation(1);
          Object localObject1 = new RelativeLayout(InAppBrowser.this.cordova.getActivity());
          ((RelativeLayout)localObject1).setBackgroundColor(InAppBrowser.this.toolbarColor);
          ((RelativeLayout)localObject1).setLayoutParams(new RelativeLayout.LayoutParams(-1, dpToPixels(44)));
          ((RelativeLayout)localObject1).setPadding(dpToPixels(2), dpToPixels(2), dpToPixels(2), dpToPixels(2));
          ((RelativeLayout)localObject1).setHorizontalGravity(3);
          ((RelativeLayout)localObject1).setVerticalGravity(48);
          RelativeLayout localRelativeLayout = new RelativeLayout(InAppBrowser.this.cordova.getActivity());
          localRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
          localRelativeLayout.setHorizontalGravity(3);
          localRelativeLayout.setVerticalGravity(16);
          localRelativeLayout.setId(Integer.valueOf(1).intValue());
          ImageButton localImageButton = new ImageButton(InAppBrowser.this.cordova.getActivity());
          Object localObject2 = new RelativeLayout.LayoutParams(-2, -1);
          ((RelativeLayout.LayoutParams)localObject2).addRule(5);
          localImageButton.setLayoutParams((ViewGroup.LayoutParams)localObject2);
          localImageButton.setContentDescription("Back Button");
          localImageButton.setId(Integer.valueOf(2).intValue());
          Object localObject3 = InAppBrowser.this.cordova.getActivity().getResources();
          localObject2 = ((Resources)localObject3).getDrawable(((Resources)localObject3).getIdentifier("ic_action_previous_item", "drawable", InAppBrowser.this.cordova.getActivity().getPackageName()));
          if (InAppBrowser.this.navigationButtonColor != "") {
            localImageButton.setColorFilter(Color.parseColor(InAppBrowser.this.navigationButtonColor));
          }
          label641:
          int i;
          label914:
          Object localObject5;
          boolean bool;
          if (Build.VERSION.SDK_INT >= 16)
          {
            localImageButton.setBackground(null);
            localImageButton.setImageDrawable((Drawable)localObject2);
            localImageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
            localImageButton.setPadding(0, dpToPixels(10), 0, dpToPixels(10));
            if (Build.VERSION.SDK_INT >= 16) {
              localImageButton.getAdjustViewBounds();
            }
            localImageButton.setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                InAppBrowser.this.goBack();
              }
            });
            localObject2 = new ImageButton(InAppBrowser.this.cordova.getActivity());
            Object localObject4 = new RelativeLayout.LayoutParams(-2, -1);
            ((RelativeLayout.LayoutParams)localObject4).addRule(1, 2);
            ((ImageButton)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject4);
            ((ImageButton)localObject2).setContentDescription("Forward Button");
            ((ImageButton)localObject2).setId(Integer.valueOf(3).intValue());
            localObject3 = ((Resources)localObject3).getDrawable(((Resources)localObject3).getIdentifier("ic_action_next_item", "drawable", InAppBrowser.this.cordova.getActivity().getPackageName()));
            if (InAppBrowser.this.navigationButtonColor != "") {
              ((ImageButton)localObject2).setColorFilter(Color.parseColor(InAppBrowser.this.navigationButtonColor));
            }
            if (Build.VERSION.SDK_INT < 16) {
              break label1709;
            }
            ((ImageButton)localObject2).setBackground(null);
            ((ImageButton)localObject2).setImageDrawable((Drawable)localObject3);
            ((ImageButton)localObject2).setScaleType(ImageView.ScaleType.FIT_CENTER);
            ((ImageButton)localObject2).setPadding(0, dpToPixels(10), 0, dpToPixels(10));
            if (Build.VERSION.SDK_INT >= 16) {
              ((ImageButton)localObject2).getAdjustViewBounds();
            }
            ((ImageButton)localObject2).setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                InAppBrowser.this.goForward();
              }
            });
            InAppBrowser.access$902(InAppBrowser.this, new EditText(InAppBrowser.this.cordova.getActivity()));
            localObject3 = new RelativeLayout.LayoutParams(-1, -1);
            ((RelativeLayout.LayoutParams)localObject3).addRule(1, 1);
            ((RelativeLayout.LayoutParams)localObject3).addRule(0, 5);
            InAppBrowser.this.edittext.setLayoutParams((ViewGroup.LayoutParams)localObject3);
            InAppBrowser.this.edittext.setId(Integer.valueOf(4).intValue());
            InAppBrowser.this.edittext.setSingleLine(true);
            InAppBrowser.this.edittext.setText(paramString);
            InAppBrowser.this.edittext.setInputType(16);
            InAppBrowser.this.edittext.setImeOptions(2);
            InAppBrowser.this.edittext.setInputType(0);
            InAppBrowser.this.edittext.setOnKeyListener(new View.OnKeyListener()
            {
              public boolean onKey(View paramAnonymous2View, int paramAnonymous2Int, KeyEvent paramAnonymous2KeyEvent)
              {
                if ((paramAnonymous2KeyEvent.getAction() == 0) && (paramAnonymous2Int == 66))
                {
                  InAppBrowser.this.navigate(InAppBrowser.this.edittext.getText().toString());
                  return true;
                }
                return false;
              }
            });
            ((RelativeLayout)localObject1).addView(createCloseButton(5));
            localObject3 = new RelativeLayout(InAppBrowser.this.cordova.getActivity());
            if (InAppBrowser.this.footerColor == "") {
              break label1718;
            }
            i = Color.parseColor(InAppBrowser.this.footerColor);
            ((RelativeLayout)localObject3).setBackgroundColor(i);
            localObject4 = new RelativeLayout.LayoutParams(-1, dpToPixels(44));
            ((RelativeLayout.LayoutParams)localObject4).addRule(12, -1);
            ((RelativeLayout)localObject3).setLayoutParams((ViewGroup.LayoutParams)localObject4);
            if (InAppBrowser.this.closeButtonCaption != "") {
              ((RelativeLayout)localObject3).setPadding(dpToPixels(8), dpToPixels(8), dpToPixels(8), dpToPixels(8));
            }
            ((RelativeLayout)localObject3).setHorizontalGravity(3);
            ((RelativeLayout)localObject3).setVerticalGravity(80);
            ((RelativeLayout)localObject3).addView(createCloseButton(7));
            InAppBrowser.access$102(InAppBrowser.this, new WebView(InAppBrowser.this.cordova.getActivity()));
            InAppBrowser.this.inAppWebView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            InAppBrowser.this.inAppWebView.setId(Integer.valueOf(6).intValue());
            InAppBrowser.this.inAppWebView.setWebChromeClient(new InAppChromeClient(this.val$thatWebView)
            {
              public boolean onShowFileChooser(WebView paramAnonymous2WebView, ValueCallback<Uri[]> paramAnonymous2ValueCallback, WebChromeClient.FileChooserParams paramAnonymous2FileChooserParams)
              {
                LOG.d("InAppBrowser", "File Chooser 5.0+");
                if (InAppBrowser.this.mUploadCallbackLollipop != null) {
                  InAppBrowser.this.mUploadCallbackLollipop.onReceiveValue(null);
                }
                InAppBrowser.access$1202(InAppBrowser.this, paramAnonymous2ValueCallback);
                paramAnonymous2WebView = new Intent("android.intent.action.GET_CONTENT");
                paramAnonymous2WebView.addCategory("android.intent.category.OPENABLE");
                paramAnonymous2WebView.setType("*/*");
                InAppBrowser.this.cordova.startActivityForResult(InAppBrowser.this, Intent.createChooser(paramAnonymous2WebView, "Select File"), 2);
                return true;
              }
              
              public void openFileChooser(ValueCallback<Uri> paramAnonymous2ValueCallback, String paramAnonymous2String)
              {
                LOG.d("InAppBrowser", "File Chooser 3.0+");
                InAppBrowser.access$1302(InAppBrowser.this, paramAnonymous2ValueCallback);
                paramAnonymous2ValueCallback = new Intent("android.intent.action.GET_CONTENT");
                paramAnonymous2ValueCallback.addCategory("android.intent.category.OPENABLE");
                InAppBrowser.this.cordova.startActivityForResult(InAppBrowser.this, Intent.createChooser(paramAnonymous2ValueCallback, "Select File"), 1);
              }
              
              public void openFileChooser(ValueCallback<Uri> paramAnonymous2ValueCallback, String paramAnonymous2String1, String paramAnonymous2String2)
              {
                LOG.d("InAppBrowser", "File Chooser 4.1+");
                openFileChooser(paramAnonymous2ValueCallback, paramAnonymous2String1);
              }
            });
            localObject4 = new InAppBrowser.InAppBrowserClient(InAppBrowser.this, this.val$thatWebView, InAppBrowser.this.edittext);
            InAppBrowser.this.inAppWebView.setWebViewClient((WebViewClient)localObject4);
            localObject4 = InAppBrowser.this.inAppWebView.getSettings();
            ((WebSettings)localObject4).setJavaScriptEnabled(true);
            ((WebSettings)localObject4).setJavaScriptCanOpenWindowsAutomatically(true);
            ((WebSettings)localObject4).setBuiltInZoomControls(InAppBrowser.this.showZoomControls);
            ((WebSettings)localObject4).setPluginState(WebSettings.PluginState.ON);
            if (Build.VERSION.SDK_INT >= 17) {
              ((WebSettings)localObject4).setMediaPlaybackRequiresUserGesture(InAppBrowser.this.mediaPlaybackRequiresUserGesture);
            }
            localObject5 = InAppBrowser.this.preferences.getString("OverrideUserAgent", null);
            String str = InAppBrowser.this.preferences.getString("AppendUserAgent", null);
            if (localObject5 != null) {
              ((WebSettings)localObject4).setUserAgentString((String)localObject5);
            }
            if (str != null) {
              ((WebSettings)localObject4).setUserAgentString(((WebSettings)localObject4).getUserAgentString() + str);
            }
            localObject5 = InAppBrowser.this.cordova.getActivity().getIntent().getExtras();
            if (localObject5 != null) {
              break label1725;
            }
            bool = true;
            label1306:
            if (bool)
            {
              ((WebSettings)localObject4).setDatabasePath(InAppBrowser.this.cordova.getActivity().getApplicationContext().getDir("inAppBrowserDB", 0).getPath());
              ((WebSettings)localObject4).setDatabaseEnabled(true);
            }
            ((WebSettings)localObject4).setDomStorageEnabled(true);
            if (!InAppBrowser.this.clearAllCache) {
              break label1738;
            }
            CookieManager.getInstance().removeAllCookie();
          }
          for (;;)
          {
            if (Build.VERSION.SDK_INT >= 21) {
              CookieManager.getInstance().setAcceptThirdPartyCookies(InAppBrowser.this.inAppWebView, true);
            }
            InAppBrowser.this.inAppWebView.loadUrl(paramString);
            InAppBrowser.this.inAppWebView.setId(Integer.valueOf(6).intValue());
            InAppBrowser.this.inAppWebView.getSettings().setLoadWithOverviewMode(true);
            InAppBrowser.this.inAppWebView.getSettings().setUseWideViewPort(InAppBrowser.this.useWideViewPort);
            InAppBrowser.this.inAppWebView.requestFocus();
            InAppBrowser.this.inAppWebView.requestFocusFromTouch();
            localRelativeLayout.addView(localImageButton);
            localRelativeLayout.addView((View)localObject2);
            if (!InAppBrowser.this.hideNavigationButtons) {
              ((RelativeLayout)localObject1).addView(localRelativeLayout);
            }
            if (!InAppBrowser.this.hideUrlBar) {
              ((RelativeLayout)localObject1).addView(InAppBrowser.this.edittext);
            }
            if (InAppBrowser.this.getShowLocationBar()) {
              localLinearLayout.addView((View)localObject1);
            }
            localObject1 = new RelativeLayout(InAppBrowser.this.cordova.getActivity());
            ((RelativeLayout)localObject1).addView(InAppBrowser.this.inAppWebView);
            localLinearLayout.addView((View)localObject1);
            if (InAppBrowser.this.showFooter) {
              ((RelativeLayout)localObject1).addView((View)localObject3);
            }
            localObject1 = new WindowManager.LayoutParams();
            ((WindowManager.LayoutParams)localObject1).copyFrom(InAppBrowser.this.dialog.getWindow().getAttributes());
            ((WindowManager.LayoutParams)localObject1).width = -1;
            ((WindowManager.LayoutParams)localObject1).height = -1;
            InAppBrowser.this.dialog.setContentView(localLinearLayout);
            InAppBrowser.this.dialog.show();
            InAppBrowser.this.dialog.getWindow().setAttributes((WindowManager.LayoutParams)localObject1);
            if (InAppBrowser.this.openWindowHidden) {
              InAppBrowser.this.dialog.hide();
            }
            return;
            localImageButton.setBackgroundDrawable(null);
            break;
            label1709:
            ((ImageButton)localObject2).setBackgroundDrawable(null);
            break label641;
            label1718:
            i = -3355444;
            break label914;
            label1725:
            bool = ((Bundle)localObject5).getBoolean("InAppBrowserStorageEnabled", true);
            break label1306;
            label1738:
            if (InAppBrowser.this.clearSessionCache) {
              CookieManager.getInstance().removeSessionCookie();
            }
          }
        }
      };
      this.cordova.getActivity().runOnUiThread(paramString);
      return "";
      bool = false;
      break;
      bool = false;
      break label105;
      bool = false;
      break label128;
      bool = false;
      break label162;
      bool = false;
      break label196;
      bool = false;
      break label230;
      this.hadwareBackButton = DEFAULT_HARDWARE_BACK.booleanValue();
      break label235;
      bool = false;
      break label264;
      bool = false;
      break label298;
      str1 = (String)paramHashMap.get("clearsessioncache");
      if (str1 == null) {
        break label303;
      }
      if (str1.equals("yes")) {}
      for (bool = true;; bool = false)
      {
        this.clearSessionCache = bool;
        break;
      }
      bool = false;
      break label332;
      bool = false;
      break label366;
    }
  }
  
  public class InAppBrowserClient
    extends WebViewClient
  {
    EditText edittext;
    CordovaWebView webView;
    
    public InAppBrowserClient(CordovaWebView paramCordovaWebView, EditText paramEditText)
    {
      this.webView = paramCordovaWebView;
      this.edittext = paramEditText;
    }
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      if (Build.VERSION.SDK_INT >= 21) {
        CookieManager.getInstance().flush();
      }
      for (;;)
      {
        paramWebView.clearFocus();
        paramWebView.requestFocus();
        try
        {
          paramWebView = new JSONObject();
          paramWebView.put("type", "loadstop");
          paramWebView.put("url", paramString);
          InAppBrowser.this.sendUpdate(paramWebView, true);
          return;
        }
        catch (JSONException paramWebView)
        {
          LOG.d("InAppBrowser", "Should never happen");
        }
        CookieSyncManager.getInstance().sync();
      }
    }
    
    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      super.onPageStarted(paramWebView, paramString, paramBitmap);
      if ((paramString.startsWith("http:")) || (paramString.startsWith("https:")) || (paramString.startsWith("file:"))) {}
      for (paramWebView = paramString;; paramWebView = "http://" + paramString)
      {
        if (!paramWebView.equals(this.edittext.getText().toString())) {
          this.edittext.setText(paramWebView);
        }
        try
        {
          paramString = new JSONObject();
          paramString.put("type", "loadstart");
          paramString.put("url", paramWebView);
          InAppBrowser.this.sendUpdate(paramString, true);
          return;
        }
        catch (JSONException paramWebView)
        {
          LOG.e("InAppBrowser", "URI passed in has caused a JSON error.");
        }
        LOG.e("InAppBrowser", "Possible Uncaught/Unknown URI");
      }
    }
    
    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
      try
      {
        paramWebView = new JSONObject();
        paramWebView.put("type", "loaderror");
        paramWebView.put("url", paramString2);
        paramWebView.put("code", paramInt);
        paramWebView.put("message", paramString1);
        InAppBrowser.this.sendUpdate(paramWebView, true, PluginResult.Status.ERROR);
        return;
      }
      catch (JSONException paramWebView)
      {
        LOG.d("InAppBrowser", "Should never happen");
      }
    }
    
    /* Error */
    public void onReceivedHttpAuthRequest(WebView paramWebView, android.webkit.HttpAuthHandler paramHttpAuthHandler, String paramString1, String paramString2)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 5
      //   3: aload_0
      //   4: getfield 22	org/apache/cordova/inappbrowser/InAppBrowser$InAppBrowserClient:webView	Lorg/apache/cordova/CordovaWebView;
      //   7: invokevirtual 182	java/lang/Object:getClass	()Ljava/lang/Class;
      //   10: ldc -72
      //   12: iconst_0
      //   13: anewarray 186	java/lang/Class
      //   16: invokevirtual 190	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   19: aload_0
      //   20: getfield 22	org/apache/cordova/inappbrowser/InAppBrowser$InAppBrowserClient:webView	Lorg/apache/cordova/CordovaWebView;
      //   23: iconst_0
      //   24: anewarray 114	java/lang/Object
      //   27: invokevirtual 196	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   30: checkcast 198	org/apache/cordova/PluginManager
      //   33: astore 6
      //   35: aload 6
      //   37: astore 5
      //   39: aload 5
      //   41: astore 6
      //   43: aload 5
      //   45: ifnonnull +27 -> 72
      //   48: aload_0
      //   49: getfield 22	org/apache/cordova/inappbrowser/InAppBrowser$InAppBrowserClient:webView	Lorg/apache/cordova/CordovaWebView;
      //   52: invokevirtual 182	java/lang/Object:getClass	()Ljava/lang/Class;
      //   55: ldc -56
      //   57: invokevirtual 204	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   60: aload_0
      //   61: getfield 22	org/apache/cordova/inappbrowser/InAppBrowser$InAppBrowserClient:webView	Lorg/apache/cordova/CordovaWebView;
      //   64: invokevirtual 210	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   67: checkcast 198	org/apache/cordova/PluginManager
      //   70: astore 6
      //   72: aload 6
      //   74: ifnull +110 -> 184
      //   77: aload 6
      //   79: aload_0
      //   80: getfield 22	org/apache/cordova/inappbrowser/InAppBrowser$InAppBrowserClient:webView	Lorg/apache/cordova/CordovaWebView;
      //   83: new 212	org/apache/cordova/CordovaHttpAuthHandler
      //   86: dup
      //   87: aload_2
      //   88: invokespecial 215	org/apache/cordova/CordovaHttpAuthHandler:<init>	(Landroid/webkit/HttpAuthHandler;)V
      //   91: aload_3
      //   92: aload 4
      //   94: invokevirtual 218	org/apache/cordova/PluginManager:onReceivedHttpAuthRequest	(Lorg/apache/cordova/CordovaWebView;Lorg/apache/cordova/ICordovaHttpAuthHandler;Ljava/lang/String;Ljava/lang/String;)Z
      //   97: ifeq +87 -> 184
      //   100: return
      //   101: astore 6
      //   103: ldc 82
      //   105: aload 6
      //   107: invokevirtual 221	java/lang/NoSuchMethodException:getLocalizedMessage	()Ljava/lang/String;
      //   110: invokestatic 90	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   113: goto -74 -> 39
      //   116: astore 6
      //   118: ldc 82
      //   120: aload 6
      //   122: invokevirtual 222	java/lang/IllegalAccessException:getLocalizedMessage	()Ljava/lang/String;
      //   125: invokestatic 90	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   128: goto -89 -> 39
      //   131: astore 6
      //   133: ldc 82
      //   135: aload 6
      //   137: invokevirtual 223	java/lang/reflect/InvocationTargetException:getLocalizedMessage	()Ljava/lang/String;
      //   140: invokestatic 90	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   143: goto -104 -> 39
      //   146: astore 6
      //   148: ldc 82
      //   150: aload 6
      //   152: invokevirtual 224	java/lang/NoSuchFieldException:getLocalizedMessage	()Ljava/lang/String;
      //   155: invokestatic 90	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   158: aload 5
      //   160: astore 6
      //   162: goto -90 -> 72
      //   165: astore 6
      //   167: ldc 82
      //   169: aload 6
      //   171: invokevirtual 222	java/lang/IllegalAccessException:getLocalizedMessage	()Ljava/lang/String;
      //   174: invokestatic 90	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   177: aload 5
      //   179: astore 6
      //   181: goto -109 -> 72
      //   184: aload_0
      //   185: aload_1
      //   186: aload_2
      //   187: aload_3
      //   188: aload 4
      //   190: invokespecial 226	android/webkit/WebViewClient:onReceivedHttpAuthRequest	(Landroid/webkit/WebView;Landroid/webkit/HttpAuthHandler;Ljava/lang/String;Ljava/lang/String;)V
      //   193: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	194	0	this	InAppBrowserClient
      //   0	194	1	paramWebView	WebView
      //   0	194	2	paramHttpAuthHandler	android.webkit.HttpAuthHandler
      //   0	194	3	paramString1	String
      //   0	194	4	paramString2	String
      //   1	177	5	localObject1	Object
      //   33	45	6	localObject2	Object
      //   101	5	6	localNoSuchMethodException	NoSuchMethodException
      //   116	5	6	localIllegalAccessException1	IllegalAccessException
      //   131	5	6	localInvocationTargetException	java.lang.reflect.InvocationTargetException
      //   146	5	6	localNoSuchFieldException	NoSuchFieldException
      //   160	1	6	localObject3	Object
      //   165	5	6	localIllegalAccessException2	IllegalAccessException
      //   179	1	6	localObject4	Object
      // Exception table:
      //   from	to	target	type
      //   3	35	101	java/lang/NoSuchMethodException
      //   3	35	116	java/lang/IllegalAccessException
      //   3	35	131	java/lang/reflect/InvocationTargetException
      //   48	72	146	java/lang/NoSuchFieldException
      //   48	72	165	java/lang/IllegalAccessException
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      if (paramString.startsWith("tel:")) {
        try
        {
          paramWebView = new Intent("android.intent.action.DIAL");
          paramWebView.setData(Uri.parse(paramString));
          InAppBrowser.this.cordova.getActivity().startActivity(paramWebView);
          return true;
        }
        catch (ActivityNotFoundException paramWebView)
        {
          LOG.e("InAppBrowser", "Error dialing " + paramString + ": " + paramWebView.toString());
        }
      }
      for (;;)
      {
        return false;
        if ((paramString.startsWith("geo:")) || (paramString.startsWith("mailto:")) || (paramString.startsWith("market:")) || (paramString.startsWith("intent:")))
        {
          try
          {
            paramWebView = new Intent("android.intent.action.VIEW");
            paramWebView.setData(Uri.parse(paramString));
            InAppBrowser.this.cordova.getActivity().startActivity(paramWebView);
            return true;
          }
          catch (ActivityNotFoundException paramWebView)
          {
            LOG.e("InAppBrowser", "Error with " + paramString + ": " + paramWebView.toString());
          }
        }
        else
        {
          int i;
          Object localObject;
          if (paramString.startsWith("sms:"))
          {
            try
            {
              Intent localIntent = new Intent("android.intent.action.VIEW");
              i = paramString.indexOf('?');
              if (i == -1) {
                paramWebView = paramString.substring(4);
              }
              for (;;)
              {
                localIntent.setData(Uri.parse("sms:" + paramWebView));
                localIntent.putExtra("address", paramWebView);
                localIntent.setType("vnd.android-dir/mms-sms");
                InAppBrowser.this.cordova.getActivity().startActivity(localIntent);
                return true;
                localObject = paramString.substring(4, i);
                String str = Uri.parse(paramString).getQuery();
                paramWebView = (WebView)localObject;
                if (str != null)
                {
                  paramWebView = (WebView)localObject;
                  if (str.startsWith("body="))
                  {
                    localIntent.putExtra("sms_body", str.substring(5));
                    paramWebView = (WebView)localObject;
                  }
                }
              }
            }
            catch (ActivityNotFoundException paramWebView)
            {
              LOG.e("InAppBrowser", "Error sending sms " + paramString + ":" + paramWebView.toString());
            }
          }
          else if ((!paramString.startsWith("http:")) && (!paramString.startsWith("https:")) && (paramString.matches("^[a-z]*://.*?$")))
          {
            if (InAppBrowser.this.allowedSchemes == null)
            {
              paramWebView = InAppBrowser.this.preferences.getString("AllowedSchemes", "");
              InAppBrowser.access$2602(InAppBrowser.this, paramWebView.split(","));
            }
            if (InAppBrowser.this.allowedSchemes != null)
            {
              paramWebView = InAppBrowser.this.allowedSchemes;
              int j = paramWebView.length;
              i = 0;
              while (i < j)
              {
                if (paramString.startsWith(paramWebView[i])) {
                  try
                  {
                    localObject = new JSONObject();
                    ((JSONObject)localObject).put("type", "customscheme");
                    ((JSONObject)localObject).put("url", paramString);
                    InAppBrowser.this.sendUpdate((JSONObject)localObject, true);
                    return true;
                  }
                  catch (JSONException localJSONException)
                  {
                    LOG.e("InAppBrowser", "Custom Scheme URI passed in has caused a JSON error.");
                  }
                }
                i += 1;
              }
            }
          }
        }
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\inappbrowser\InAppBrowser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */