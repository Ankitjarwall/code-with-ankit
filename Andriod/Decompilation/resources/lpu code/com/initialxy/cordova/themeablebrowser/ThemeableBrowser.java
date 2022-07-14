package com.initialxy.cordova.themeablebrowser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginManager;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.apache.cordova.Whitelist;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled"})
public class ThemeableBrowser
  extends CordovaPlugin
{
  private static final String ALIGN_LEFT = "left";
  private static final String ALIGN_RIGHT = "right";
  private static final int DISABLED_ALPHA = 127;
  private static final String ERR_CRITICAL = "critical";
  private static final String ERR_LOADFAIL = "loadfail";
  private static final String EVT_ERR = "ThemeableBrowserError";
  private static final String EVT_WRN = "ThemeableBrowserWarning";
  private static final String EXIT_EVENT = "exit";
  private static final String LOAD_ERROR_EVENT = "loaderror";
  private static final String LOAD_START_EVENT = "loadstart";
  private static final String LOAD_STOP_EVENT = "loadstop";
  protected static final String LOG_TAG = "ThemeableBrowser";
  private static final String NULL = "null";
  private static final String SELF = "_self";
  private static final String SYSTEM = "_system";
  private static final int TOOLBAR_DEF_HEIGHT = 44;
  private static final String WRN_UNDEFINED = "undefined";
  private static final String WRN_UNEXPECTED = "unexpected";
  private CallbackContext callbackContext;
  private ThemeableBrowserDialog dialog;
  private EditText edittext;
  private WebView inAppWebView;
  
  private Button createButton(BrowserButton paramBrowserButton, String paramString, View.OnClickListener paramOnClickListener)
  {
    if (paramBrowserButton != null)
    {
      Button localButton = new Button(this.cordova.getActivity());
      localButton.setContentDescription(paramString);
      localButton.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
      setButtonImages(localButton, paramBrowserButton, 127);
      if (paramOnClickListener != null) {
        localButton.setOnClickListener(paramOnClickListener);
      }
      return localButton;
    }
    emitWarning("undefined", String.format("%s is not defined. Button will not be shown.", new Object[] { paramString }));
    return null;
  }
  
  private int dpToPixels(int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, this.cordova.getActivity().getResources().getDisplayMetrics());
  }
  
  private void emitButtonEvent(Event paramEvent, String paramString)
  {
    emitButtonEvent(paramEvent, paramString, null);
  }
  
  private void emitButtonEvent(Event paramEvent, String paramString, Integer paramInteger)
  {
    if ((paramEvent != null) && (paramEvent.event != null)) {}
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("type", paramEvent.event);
      localJSONObject.put("url", paramString);
      if (paramInteger != null) {
        localJSONObject.put("index", paramInteger.intValue());
      }
      sendUpdate(localJSONObject, true);
      return;
    }
    catch (JSONException paramEvent) {}
    emitWarning("undefined", "Button clicked, but event property undefined. No event will be raised.");
    return;
  }
  
  private void emitError(String paramString1, String paramString2)
  {
    emitLog("ThemeableBrowserError", paramString1, paramString2);
  }
  
  private void emitLog(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 != null) {}
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("type", paramString1);
      localJSONObject.put("code", paramString2);
      localJSONObject.put("message", paramString3);
      sendUpdate(localJSONObject, true);
      return;
    }
    catch (JSONException paramString1) {}
  }
  
  private void emitWarning(String paramString1, String paramString2)
  {
    emitLog("ThemeableBrowserWarning", paramString1, paramString2);
  }
  
  private Drawable getImage(String paramString1, String paramString2, double paramDouble)
    throws IOException
  {
    Object localObject = null;
    Resources localResources = this.cordova.getActivity().getResources();
    int i;
    if (paramString1 != null)
    {
      i = localResources.getIdentifier(paramString1, "drawable", this.cordova.getActivity().getPackageName());
      if (Build.VERSION.SDK_INT < 21) {
        paramString1 = localResources.getDrawable(i);
      }
    }
    do
    {
      return paramString1;
      return localResources.getDrawable(i, this.cordova.getActivity().getTheme());
      paramString1 = (String)localObject;
    } while (paramString2 == null);
    paramString2 = new File("www", paramString2);
    paramString1 = null;
    try
    {
      paramString2 = this.cordova.getActivity().getAssets().open(paramString2.getPath());
      paramString1 = paramString2;
      localObject = BitmapFactory.decodeStream(paramString2);
      paramString1 = paramString2;
      ((Bitmap)localObject).setDensity((int)(160.0D * paramDouble));
      paramString1 = paramString2;
      localObject = new BitmapDrawable(localResources, (Bitmap)localObject);
      try
      {
        paramString2.close();
        return (Drawable)localObject;
      }
      catch (Exception paramString1)
      {
        return (Drawable)localObject;
      }
      try
      {
        paramString1.close();
        throw paramString2;
      }
      catch (Exception paramString1)
      {
        for (;;) {}
      }
    }
    finally {}
  }
  
  private ThemeableBrowser getThemeableBrowser()
  {
    return this;
  }
  
  private void goForward()
  {
    if ((this.inAppWebView != null) && (this.inAppWebView.canGoForward())) {
      this.inAppWebView.goForward();
    }
  }
  
  private int hexStringToColor(String paramString)
  {
    int j = 0;
    int i = j;
    if (paramString != null)
    {
      i = j;
      if (!paramString.isEmpty())
      {
        String str = paramString;
        if (paramString.charAt(0) == '#') {
          str = paramString.substring(1);
        }
        paramString = str;
        if (str.length() < 8) {
          paramString = str + "ff";
        }
        i = (int)Long.parseLong(paramString, 16);
        i = i >> 8 & 0xFFFFFF | (i & 0xFF) << 24;
      }
    }
    return i;
  }
  
  private void injectDeferredObject(final String paramString1, String paramString2)
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
          if (ThemeableBrowser.this.inAppWebView != null)
          {
            if (Build.VERSION.SDK_INT < 19) {
              ThemeableBrowser.this.inAppWebView.loadUrl("javascript:" + paramString1);
            }
          }
          else {
            return;
          }
          ThemeableBrowser.this.inAppWebView.evaluateJavascript(paramString1, null);
        }
      });
      return;
    }
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
  
  private Options parseFeature(String paramString)
  {
    Object localObject = null;
    if ((paramString != null) && (!paramString.isEmpty())) {}
    for (;;)
    {
      try
      {
        paramString = (Options)ThemeableBrowserUnmarshaller.JSONToObj(paramString, Options.class);
        localObject = paramString;
        if (paramString == null) {
          localObject = new Options(null);
        }
        ((Options)localObject).location = true;
        return (Options)localObject;
      }
      catch (Exception paramString)
      {
        emitError("critical", String.format("Invalid JSON @s", new Object[] { paramString.toString() }));
        paramString = (String)localObject;
        continue;
      }
      emitWarning("undefined", "No config was given, defaults will be used, which is quite boring.");
      paramString = (String)localObject;
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
  
  private void setBackground(View paramView, Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT < 16)
    {
      paramView.setBackgroundDrawable(paramDrawable);
      return;
    }
    paramView.setBackground(paramDrawable);
  }
  
  private void setButtonImages(View paramView, BrowserButton paramBrowserButton, int paramInt)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    Object localObject1 = null;
    Canvas localCanvas = null;
    Paint localPaint = null;
    CharSequence localCharSequence = paramView.getContentDescription();
    if ((paramBrowserButton.image != null) || (paramBrowserButton.wwwImage != null))
    {
      localObject1 = localObject3;
      try
      {
        localObject3 = getImage(paramBrowserButton.image, paramBrowserButton.wwwImage, paramBrowserButton.wwwImageDensity);
        localObject1 = localObject3;
        localObject2 = localObject3;
        ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
        localObject1 = localObject3;
        localObject2 = localObject3;
        localLayoutParams.width = ((Drawable)localObject3).getIntrinsicWidth();
        localObject1 = localObject3;
        localObject2 = localObject3;
        localLayoutParams.height = ((Drawable)localObject3).getIntrinsicHeight();
        localObject1 = localObject3;
      }
      catch (Resources.NotFoundException localNotFoundException2)
      {
        for (;;)
        {
          emitError("loadfail", String.format("Image for %s, %s, failed to load", new Object[] { localCharSequence, paramBrowserButton.image }));
        }
      }
      catch (IOException localIOException1)
      {
        for (;;)
        {
          emitError("loadfail", String.format("Image for %s, %s, failed to load", new Object[] { localCharSequence, paramBrowserButton.wwwImage }));
          localNotFoundException1 = localNotFoundException2;
        }
      }
      if ((paramBrowserButton.imagePressed == null) && (paramBrowserButton.wwwImagePressed == null)) {
        break label481;
      }
    }
    for (;;)
    {
      try
      {
        localObject2 = getImage(paramBrowserButton.imagePressed, paramBrowserButton.wwwImagePressed, paramBrowserButton.wwwImageDensity);
        paramBrowserButton = (BrowserButton)localObject2;
      }
      catch (Resources.NotFoundException localNotFoundException3)
      {
        Resources.NotFoundException localNotFoundException1;
        emitError("loadfail", String.format("Pressed image for %s, %s, failed to load", new Object[] { localCharSequence, paramBrowserButton.imagePressed }));
        paramBrowserButton = localPaint;
        continue;
      }
      catch (IOException localIOException2)
      {
        emitError("loadfail", String.format("Pressed image for %s, %s, failed to load", new Object[] { localCharSequence, paramBrowserButton.wwwImagePressed }));
        paramBrowserButton = localPaint;
        continue;
      }
      localObject2 = localCanvas;
      if (localObject1 != null)
      {
        localObject2 = ((BitmapDrawable)localObject1).getBitmap();
        localObject3 = Bitmap.createBitmap(((Drawable)localObject1).getIntrinsicWidth(), ((Drawable)localObject1).getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        localCanvas = new Canvas((Bitmap)localObject3);
        localPaint = new Paint();
        localPaint.setAlpha(paramInt);
        localCanvas.drawBitmap((Bitmap)localObject2, 0.0F, 0.0F, localPaint);
        localObject2 = new BitmapDrawable(this.cordova.getActivity().getResources(), (Bitmap)localObject3);
      }
      localObject3 = new StateListDrawable();
      if (paramBrowserButton != null) {
        ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, paramBrowserButton);
      }
      if (localObject1 != null) {
        ((StateListDrawable)localObject3).addState(new int[] { 16842910 }, (Drawable)localObject1);
      }
      if (localObject2 != null) {
        ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject2);
      }
      setBackground(paramView, (Drawable)localObject3);
      return;
      emitWarning("undefined", String.format("Image for %s is not defined. Button will not be shown", new Object[] { localCharSequence }));
      break;
      label481:
      emitWarning("undefined", String.format("Pressed image for %s is not defined.", new Object[] { localCharSequence }));
      paramBrowserButton = localPaint;
    }
  }
  
  public boolean canGoBack()
  {
    return (this.inAppWebView != null) && (this.inAppWebView.canGoBack());
  }
  
  public void closeDialog()
  {
    this.cordova.getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (ThemeableBrowser.this.inAppWebView == null)
        {
          ThemeableBrowser.this.emitWarning("unexpected", "Close called but already closed.");
          return;
        }
        ThemeableBrowser.this.inAppWebView.setWebViewClient(new WebViewClient()
        {
          public void onPageFinished(WebView paramAnonymous2WebView, String paramAnonymous2String)
          {
            if (ThemeableBrowser.this.dialog != null) {
              ThemeableBrowser.this.dialog.dismiss();
            }
            ThemeableBrowser.access$102(ThemeableBrowser.this, null);
            ThemeableBrowser.access$202(ThemeableBrowser.this, null);
            ThemeableBrowser.access$502(ThemeableBrowser.this, null);
            ThemeableBrowser.access$602(ThemeableBrowser.this, null);
          }
        });
        ThemeableBrowser.this.inAppWebView.loadUrl("about:blank");
        try
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("type", "exit");
          ThemeableBrowser.this.sendUpdate(localJSONObject, false);
          return;
        }
        catch (JSONException localJSONException) {}
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
      this.cordova.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          String str2 = "";
          Object localObject2;
          Object localObject1;
          if ("_self".equals(paramString))
          {
            localObject2 = null;
            if (str2.startsWith("javascript:")) {
              localObject2 = Boolean.valueOf(true);
            }
            localObject1 = localObject2;
            if (localObject2 == null) {
              localObject1 = Boolean.valueOf(new Whitelist().isUrlWhiteListed(str2));
            }
            localObject2 = localObject1;
            if (localObject1 != null) {}
          }
          try
          {
            localObject2 = (PluginManager)ThemeableBrowser.this.webView.getClass().getMethod("getPluginManager", new Class[0]).invoke(ThemeableBrowser.this.webView, new Object[0]);
            localObject2 = (Boolean)localObject2.getClass().getMethod("shouldAllowNavigation", new Class[] { String.class }).invoke(localObject2, new Object[] { str2 });
            if (Boolean.TRUE.equals(localObject2))
            {
              ThemeableBrowser.this.webView.loadUrl(str2);
              localObject1 = str2;
            }
            for (;;)
            {
              localObject1 = new PluginResult(PluginResult.Status.OK, (String)localObject1);
              ((PluginResult)localObject1).setKeepCallback(true);
              paramCallbackContext.sendPluginResult((PluginResult)localObject1);
              return;
              if (str2.startsWith("tel:"))
              {
                try
                {
                  localObject1 = new Intent("android.intent.action.DIAL");
                  ((Intent)localObject1).setData(Uri.parse(str2));
                  ThemeableBrowser.this.cordova.getActivity().startActivity((Intent)localObject1);
                  localObject1 = str2;
                }
                catch (ActivityNotFoundException localActivityNotFoundException)
                {
                  ThemeableBrowser.this.emitError("critical", String.format("Error dialing %s: %s", new Object[] { str2, localActivityNotFoundException.toString() }));
                  str1 = str2;
                }
              }
              else
              {
                str1 = ThemeableBrowser.this.showWebPage(str2, paramCordovaArgs);
                continue;
                if ("_system".equals(paramString)) {
                  str1 = ThemeableBrowser.this.openExternal(str2);
                } else {
                  str1 = ThemeableBrowser.this.showWebPage(str2, paramCordovaArgs);
                }
              }
            }
          }
          catch (InvocationTargetException localInvocationTargetException)
          {
            for (;;)
            {
              Object localObject3 = str1;
            }
          }
          catch (IllegalAccessException localIllegalAccessException)
          {
            for (;;)
            {
              Object localObject4 = str1;
            }
          }
          catch (NoSuchMethodException localNoSuchMethodException)
          {
            for (;;)
            {
              String str1;
              Object localObject5 = str1;
            }
          }
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
            paramString = String.format("prompt(JSON.stringify([eval(%%s)]), 'gap-iab://%s')", new Object[] { paramCallbackContext.getCallbackId() });
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
              ThemeableBrowser.this.dialog.show();
            }
          });
          paramString = new PluginResult(PluginResult.Status.OK);
          paramString.setKeepCallback(true);
          this.callbackContext.sendPluginResult(paramString);
          break;
        }
      } while (!paramString.equals("reload"));
      if (this.inAppWebView != null) {
        this.cordova.getActivity().runOnUiThread(new Runnable()
        {
          public void run()
          {
            ThemeableBrowser.this.inAppWebView.reload();
          }
        });
      }
    }
  }
  
  public void goBack()
  {
    if ((this.inAppWebView != null) && (this.inAppWebView.canGoBack())) {
      this.inAppWebView.goBack();
    }
  }
  
  public void onDestroy()
  {
    closeDialog();
  }
  
  public void onReset()
  {
    closeDialog();
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
        Log.d("ThemeableBrowser", "ThemeableBrowser: Error loading url " + paramString + ":" + localActivityNotFoundException1.toString());
      }
      catch (ActivityNotFoundException localActivityNotFoundException1) {}
    }
    catch (ActivityNotFoundException localActivityNotFoundException2)
    {
      for (;;) {}
    }
    return localActivityNotFoundException1.toString();
  }
  
  public String showWebPage(final String paramString, final Options paramOptions)
  {
    paramString = new Runnable()
    {
      @SuppressLint({"NewApi"})
      public void run()
      {
        ThemeableBrowser.access$102(ThemeableBrowser.this, new ThemeableBrowserDialog(ThemeableBrowser.this.cordova.getActivity(), 16973833, paramOptions.hardwareback));
        if (!paramOptions.disableAnimation) {
          ThemeableBrowser.this.dialog.getWindow().getAttributes().windowAnimations = 16973826;
        }
        ThemeableBrowser.this.dialog.requestWindowFeature(1);
        ThemeableBrowser.this.dialog.setCancelable(true);
        ThemeableBrowser.this.dialog.setThemeableBrowser(ThemeableBrowser.this.getThemeableBrowser());
        Object localObject1;
        if (paramOptions.fullscreen) {
          localObject1 = new FrameLayout(ThemeableBrowser.this.cordova.getActivity());
        }
        final Object localObject4;
        FrameLayout localFrameLayout;
        label191:
        LinearLayout localLinearLayout1;
        LinearLayout localLinearLayout2;
        Button localButton3;
        int k;
        int j;
        label1510:
        Object localObject3;
        for (;;)
        {
          localObject4 = paramOptions.toolbar;
          localFrameLayout = new FrameLayout(ThemeableBrowser.this.cordova.getActivity());
          final Object localObject5 = ThemeableBrowser.this;
          Object localObject2;
          if ((localObject4 != null) && (((ThemeableBrowser.Toolbar)localObject4).color != null))
          {
            localObject2 = ((ThemeableBrowser.Toolbar)localObject4).color;
            localFrameLayout.setBackgroundColor(((ThemeableBrowser)localObject5).hexStringToColor((String)localObject2));
            localObject2 = ThemeableBrowser.this;
            if (localObject4 == null) {
              break label1510;
            }
            i = ((ThemeableBrowser.Toolbar)localObject4).height;
            localFrameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, ((ThemeableBrowser)localObject2).dpToPixels(i)));
            if ((localObject4 == null) || ((((ThemeableBrowser.Toolbar)localObject4).image == null) && (((ThemeableBrowser.Toolbar)localObject4).wwwImage == null))) {}
          }
          try
          {
            localObject2 = ThemeableBrowser.this.getImage(((ThemeableBrowser.Toolbar)localObject4).image, ((ThemeableBrowser.Toolbar)localObject4).wwwImage, ((ThemeableBrowser.Toolbar)localObject4).wwwImageDensity);
            ThemeableBrowser.this.setBackground(localFrameLayout, (Drawable)localObject2);
            localLinearLayout1 = new LinearLayout(ThemeableBrowser.this.cordova.getActivity());
            localObject2 = new FrameLayout.LayoutParams(-2, -2);
            ((FrameLayout.LayoutParams)localObject2).gravity = 19;
            localLinearLayout1.setLayoutParams((ViewGroup.LayoutParams)localObject2);
            localLinearLayout1.setVerticalGravity(16);
            localLinearLayout2 = new LinearLayout(ThemeableBrowser.this.cordova.getActivity());
            localObject2 = new FrameLayout.LayoutParams(-2, -2);
            ((FrameLayout.LayoutParams)localObject2).gravity = 21;
            localLinearLayout2.setLayoutParams((ViewGroup.LayoutParams)localObject2);
            localLinearLayout2.setVerticalGravity(16);
            ThemeableBrowser.access$502(ThemeableBrowser.this, new EditText(ThemeableBrowser.this.cordova.getActivity()));
            localObject2 = new RelativeLayout.LayoutParams(-1, -1);
            ((RelativeLayout.LayoutParams)localObject2).addRule(1, 1);
            ((RelativeLayout.LayoutParams)localObject2).addRule(0, 5);
            ThemeableBrowser.this.edittext.setLayoutParams((ViewGroup.LayoutParams)localObject2);
            ThemeableBrowser.this.edittext.setSingleLine(true);
            ThemeableBrowser.this.edittext.setText(paramString);
            ThemeableBrowser.this.edittext.setInputType(16);
            ThemeableBrowser.this.edittext.setImeOptions(2);
            ThemeableBrowser.this.edittext.setInputType(0);
            ThemeableBrowser.this.edittext.setOnKeyListener(new View.OnKeyListener()
            {
              public boolean onKey(View paramAnonymous2View, int paramAnonymous2Int, KeyEvent paramAnonymous2KeyEvent)
              {
                if ((paramAnonymous2KeyEvent.getAction() == 0) && (paramAnonymous2Int == 66))
                {
                  ThemeableBrowser.this.navigate(ThemeableBrowser.this.edittext.getText().toString());
                  return true;
                }
                return false;
              }
            });
            localButton1 = ThemeableBrowser.this.createButton(paramOptions.backButton, "back button", new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                ThemeableBrowser.this.emitButtonEvent(ThemeableBrowser.6.this.val$features.backButton, ThemeableBrowser.this.inAppWebView.getUrl());
                if ((ThemeableBrowser.6.this.val$features.backButtonCanClose) && (!ThemeableBrowser.this.canGoBack()))
                {
                  ThemeableBrowser.this.closeDialog();
                  return;
                }
                ThemeableBrowser.this.goBack();
              }
            });
            if (localButton1 != null) {
              localButton1.setEnabled(paramOptions.backButtonCanClose);
            }
            localButton2 = ThemeableBrowser.this.createButton(paramOptions.forwardButton, "forward button", new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                ThemeableBrowser.this.emitButtonEvent(ThemeableBrowser.6.this.val$features.forwardButton, ThemeableBrowser.this.inAppWebView.getUrl());
                ThemeableBrowser.this.goForward();
              }
            });
            if (localButton1 != null) {
              localButton1.setEnabled(false);
            }
            localButton3 = ThemeableBrowser.this.createButton(paramOptions.closeButton, "close button", new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                ThemeableBrowser.this.emitButtonEvent(ThemeableBrowser.6.this.val$features.closeButton, ThemeableBrowser.this.inAppWebView.getUrl());
                ThemeableBrowser.this.closeDialog();
              }
            });
            if (paramOptions.menu != null)
            {
              localObject2 = new ThemeableBrowser.MenuSpinner(ThemeableBrowser.this, ThemeableBrowser.this.cordova.getActivity());
              if (localObject2 != null)
              {
                ((Spinner)localObject2).setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                ((Spinner)localObject2).setContentDescription("menu button");
                ThemeableBrowser.this.setButtonImages((View)localObject2, paramOptions.menu, 127);
                ((Spinner)localObject2).setOnTouchListener(new View.OnTouchListener()
                {
                  public boolean onTouch(View paramAnonymous2View, MotionEvent paramAnonymous2MotionEvent)
                  {
                    if (paramAnonymous2MotionEvent.getAction() == 1) {
                      ThemeableBrowser.this.emitButtonEvent(ThemeableBrowser.6.this.val$features.menu, ThemeableBrowser.this.inAppWebView.getUrl());
                    }
                    return false;
                  }
                });
                if (paramOptions.menu.items != null)
                {
                  localObject4 = new ThemeableBrowser.HideSelectedAdapter(ThemeableBrowser.this.cordova.getActivity(), 17367048, paramOptions.menu.items);
                  ((ThemeableBrowser.HideSelectedAdapter)localObject4).setDropDownViewResource(17367049);
                  ((Spinner)localObject2).setAdapter((SpinnerAdapter)localObject4);
                  ((Spinner)localObject2).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                  {
                    public void onItemSelected(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long)
                    {
                      if ((ThemeableBrowser.this.inAppWebView != null) && (paramAnonymous2Int < ThemeableBrowser.6.this.val$features.menu.items.length)) {
                        ThemeableBrowser.this.emitButtonEvent(ThemeableBrowser.6.this.val$features.menu.items[paramAnonymous2Int], ThemeableBrowser.this.inAppWebView.getUrl(), Integer.valueOf(paramAnonymous2Int));
                      }
                    }
                    
                    public void onNothingSelected(AdapterView<?> paramAnonymous2AdapterView) {}
                  });
                }
              }
              if (paramOptions.title == null) {
                break label1588;
              }
              localObject4 = new TextView(ThemeableBrowser.this.cordova.getActivity());
              if (localObject4 != null)
              {
                localObject5 = new FrameLayout.LayoutParams(-1, -2);
                ((FrameLayout.LayoutParams)localObject5).gravity = 17;
                ((TextView)localObject4).setLayoutParams((ViewGroup.LayoutParams)localObject5);
                ((TextView)localObject4).setSingleLine();
                ((TextView)localObject4).setEllipsize(TextUtils.TruncateAt.END);
                ((TextView)localObject4).setGravity(17);
                localObject6 = ThemeableBrowser.this;
                if (paramOptions.title.color == null) {
                  break label1594;
                }
                localObject5 = paramOptions.title.color;
                ((TextView)localObject4).setTextColor(((ThemeableBrowser)localObject6).hexStringToColor((String)localObject5));
                if (paramOptions.title.staticText != null) {
                  ((TextView)localObject4).setText(paramOptions.title.staticText);
                }
              }
              ThemeableBrowser.access$202(ThemeableBrowser.this, new WebView(ThemeableBrowser.this.cordova.getActivity()));
              if (!paramOptions.fullscreen) {
                break label1602;
              }
              localObject5 = new FrameLayout.LayoutParams(-1, -1);
              if (!paramOptions.fullscreen) {
                ((LinearLayout.LayoutParams)localObject5).weight = 1.0F;
              }
              ThemeableBrowser.this.inAppWebView.setLayoutParams((ViewGroup.LayoutParams)localObject5);
              ThemeableBrowser.this.inAppWebView.setWebChromeClient(new InAppChromeClient(this.val$thatWebView));
              localObject5 = new ThemeableBrowser.ThemeableBrowserClient(ThemeableBrowser.this, this.val$thatWebView, new ThemeableBrowser.PageLoadListener()
              {
                public void onPageFinished(String paramAnonymous2String, boolean paramAnonymous2Boolean1, boolean paramAnonymous2Boolean2)
                {
                  if ((ThemeableBrowser.this.inAppWebView != null) && (localObject4 != null) && (ThemeableBrowser.6.this.val$features.title != null) && (ThemeableBrowser.6.this.val$features.title.staticText == null) && (ThemeableBrowser.6.this.val$features.title.showPageTitle)) {
                    localObject4.setText(ThemeableBrowser.this.inAppWebView.getTitle());
                  }
                  if (localButton1 != null)
                  {
                    paramAnonymous2String = localButton1;
                    if ((!paramAnonymous2Boolean1) && (!ThemeableBrowser.6.this.val$features.backButtonCanClose)) {
                      break label137;
                    }
                  }
                  label137:
                  for (paramAnonymous2Boolean1 = true;; paramAnonymous2Boolean1 = false)
                  {
                    paramAnonymous2String.setEnabled(paramAnonymous2Boolean1);
                    if (localButton2 != null) {
                      localButton2.setEnabled(paramAnonymous2Boolean2);
                    }
                    return;
                  }
                }
              });
              ThemeableBrowser.this.inAppWebView.setWebViewClient((WebViewClient)localObject5);
              localObject5 = ThemeableBrowser.this.inAppWebView.getSettings();
              ((WebSettings)localObject5).setJavaScriptEnabled(true);
              ((WebSettings)localObject5).setJavaScriptCanOpenWindowsAutomatically(true);
              ((WebSettings)localObject5).setBuiltInZoomControls(paramOptions.zoom);
              ((WebSettings)localObject5).setDisplayZoomControls(false);
              ((WebSettings)localObject5).setPluginState(WebSettings.PluginState.ON);
              localObject6 = ThemeableBrowser.this.cordova.getActivity().getIntent().getExtras();
              if ((localObject6 != null) && (!((Bundle)localObject6).getBoolean("ThemeableBrowserStorageEnabled", true))) {
                break label1616;
              }
              i = 1;
              if (i != 0)
              {
                ((WebSettings)localObject5).setDatabasePath(ThemeableBrowser.this.cordova.getActivity().getApplicationContext().getDir("themeableBrowserDB", 0).getPath());
                ((WebSettings)localObject5).setDatabaseEnabled(true);
              }
              ((WebSettings)localObject5).setDomStorageEnabled(true);
              if (!paramOptions.clearcache) {
                break label1621;
              }
              CookieManager.getInstance().removeAllCookie();
              ThemeableBrowser.this.inAppWebView.loadUrl(paramString);
              ThemeableBrowser.this.inAppWebView.getSettings().setLoadWithOverviewMode(true);
              ThemeableBrowser.this.inAppWebView.getSettings().setUseWideViewPort(true);
              ThemeableBrowser.this.inAppWebView.requestFocus();
              ThemeableBrowser.this.inAppWebView.requestFocusFromTouch();
              k = 0;
              m = 0;
              i = 0;
              j = 0;
              if (paramOptions.customButtons == null) {
                break label1664;
              }
              final int n = 0;
              for (;;)
              {
                k = m;
                i = j;
                if (n >= paramOptions.customButtons.length) {
                  break label1664;
                }
                localObject5 = paramOptions.customButtons[n];
                localObject6 = ThemeableBrowser.this.createButton((ThemeableBrowser.BrowserButton)localObject5, String.format("custom button at %d", new Object[] { Integer.valueOf(n) }), new View.OnClickListener()
                {
                  public void onClick(View paramAnonymous2View)
                  {
                    if (ThemeableBrowser.this.inAppWebView != null) {
                      ThemeableBrowser.this.emitButtonEvent(localObject5, ThemeableBrowser.this.inAppWebView.getUrl(), Integer.valueOf(n));
                    }
                  }
                });
                if (!"right".equals(((ThemeableBrowser.BrowserButton)localObject5).align)) {
                  break;
                }
                localLinearLayout2.addView((View)localObject6);
                j += ((Button)localObject6).getLayoutParams().width;
                n += 1;
              }
              localObject1 = new LinearLayout(ThemeableBrowser.this.cordova.getActivity());
              ((LinearLayout)localObject1).setOrientation(1);
              continue;
              localObject2 = "#ffffffff";
              break label191;
              i = 44;
            }
          }
          catch (Resources.NotFoundException localNotFoundException)
          {
            for (;;)
            {
              ThemeableBrowser.this.emitError("loadfail", String.format("Image for toolbar, %s, failed to load", new Object[] { ((ThemeableBrowser.Toolbar)localObject4).image }));
            }
          }
          catch (IOException localIOException)
          {
            final Button localButton1;
            final Button localButton2;
            for (;;)
            {
              Object localObject6;
              ThemeableBrowser.this.emitError("loadfail", String.format("Image for toolbar, %s, failed to load", new Object[] { ((ThemeableBrowser.Toolbar)localObject4).wwwImage }));
              continue;
              localObject3 = null;
              continue;
              label1588:
              localObject4 = null;
              continue;
              label1594:
              localObject5 = "#000000ff";
              continue;
              label1602:
              localObject5 = new LinearLayout.LayoutParams(-1, 0);
              continue;
              label1616:
              i = 0;
              continue;
              label1621:
              if (paramOptions.clearsessioncache)
              {
                CookieManager.getInstance().removeSessionCookie();
                continue;
                localLinearLayout1.addView((View)localObject6, 0);
                m += ((Button)localObject6).getLayoutParams().width;
              }
            }
            label1664:
            j = k;
            if (localButton2 != null)
            {
              j = k;
              if (paramOptions.forwardButton != null)
              {
                j = k;
                if (!"right".equals(paramOptions.forwardButton.align))
                {
                  localLinearLayout1.addView(localButton2, 0);
                  j = k + localButton2.getLayoutParams().width;
                }
              }
            }
            k = i;
            if (localButton1 != null)
            {
              k = i;
              if (paramOptions.backButton != null)
              {
                k = i;
                if ("right".equals(paramOptions.backButton.align))
                {
                  localLinearLayout2.addView(localButton1);
                  k = i + localButton1.getLayoutParams().width;
                }
              }
            }
            i = j;
            if (localButton1 != null)
            {
              i = j;
              if (paramOptions.backButton != null)
              {
                i = j;
                if (!"right".equals(paramOptions.backButton.align))
                {
                  localLinearLayout1.addView(localButton1, 0);
                  i = j + localButton1.getLayoutParams().width;
                }
              }
            }
            m = k;
            if (localButton2 != null)
            {
              m = k;
              if (paramOptions.forwardButton != null)
              {
                m = k;
                if ("right".equals(paramOptions.forwardButton.align))
                {
                  localLinearLayout2.addView(localButton2);
                  m = k + localButton2.getLayoutParams().width;
                }
              }
            }
            j = i;
            k = m;
            if (localObject3 != null)
            {
              if ((paramOptions.menu == null) || (!"right".equals(paramOptions.menu.align))) {
                break label2229;
              }
              localLinearLayout2.addView((View)localObject3);
              k = m + ((Spinner)localObject3).getLayoutParams().width;
              j = i;
            }
          }
        }
        int i = j;
        int m = k;
        if (localButton3 != null)
        {
          if ((paramOptions.closeButton == null) || (!"right".equals(paramOptions.closeButton.align))) {
            break label2254;
          }
          localLinearLayout2.addView(localButton3);
          m = k + localButton3.getLayoutParams().width;
          i = j;
        }
        for (;;)
        {
          localFrameLayout.addView(localLinearLayout1);
          localFrameLayout.addView(localLinearLayout2);
          if (localObject4 != null)
          {
            i = Math.max(i, m);
            ((FrameLayout.LayoutParams)((TextView)localObject4).getLayoutParams()).setMargins(i, 0, i, 0);
            localFrameLayout.addView((View)localObject4);
          }
          if (paramOptions.fullscreen) {
            ((ViewGroup)localObject1).addView(ThemeableBrowser.this.inAppWebView);
          }
          if (paramOptions.location) {
            ((ViewGroup)localObject1).addView(localFrameLayout);
          }
          if (!paramOptions.fullscreen) {
            ((ViewGroup)localObject1).addView(ThemeableBrowser.this.inAppWebView);
          }
          localObject3 = new WindowManager.LayoutParams();
          ((WindowManager.LayoutParams)localObject3).copyFrom(ThemeableBrowser.this.dialog.getWindow().getAttributes());
          ((WindowManager.LayoutParams)localObject3).width = -1;
          ((WindowManager.LayoutParams)localObject3).height = -1;
          ThemeableBrowser.this.dialog.setContentView((View)localObject1);
          ThemeableBrowser.this.dialog.show();
          ThemeableBrowser.this.dialog.getWindow().setAttributes((WindowManager.LayoutParams)localObject3);
          if (paramOptions.hidden) {
            ThemeableBrowser.this.dialog.hide();
          }
          return;
          label2229:
          localLinearLayout1.addView((View)localObject3, 0);
          j = i + ((Spinner)localObject3).getLayoutParams().width;
          k = m;
          break;
          label2254:
          localLinearLayout1.addView(localButton3, 0);
          i = j + localButton3.getLayoutParams().width;
          m = k;
        }
      }
    };
    this.cordova.getActivity().runOnUiThread(paramString);
    return "";
  }
  
  private static class BrowserButton
    extends ThemeableBrowser.Event
  {
    public String align = "left";
    public String image;
    public String imagePressed;
    public String wwwImage;
    public double wwwImageDensity = 1.0D;
    public String wwwImagePressed;
    
    private BrowserButton()
    {
      super();
    }
  }
  
  private static class BrowserMenu
    extends ThemeableBrowser.BrowserButton
  {
    public ThemeableBrowser.EventLabel[] items;
    
    private BrowserMenu()
    {
      super();
    }
  }
  
  private static class Event
  {
    public String event;
  }
  
  private static class EventLabel
    extends ThemeableBrowser.Event
  {
    public String label;
    
    private EventLabel()
    {
      super();
    }
    
    public String toString()
    {
      return this.label;
    }
  }
  
  private static class HideSelectedAdapter<T>
    extends ArrayAdapter
  {
    public HideSelectedAdapter(Context paramContext, int paramInt, T[] paramArrayOfT)
    {
      super(paramInt, paramArrayOfT);
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramView = super.getView(paramInt, paramView, paramViewGroup);
      paramView.setVisibility(8);
      return paramView;
    }
  }
  
  public class MenuSpinner
    extends Spinner
  {
    private AdapterView.OnItemSelectedListener listener;
    
    public MenuSpinner(Context paramContext)
    {
      super();
    }
    
    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener paramOnItemSelectedListener)
    {
      this.listener = paramOnItemSelectedListener;
    }
    
    public void setSelection(int paramInt)
    {
      super.setSelection(paramInt);
      if (this.listener != null) {
        this.listener.onItemSelected(null, this, paramInt, 0L);
      }
    }
  }
  
  private static class Options
  {
    public ThemeableBrowser.BrowserButton backButton;
    public boolean backButtonCanClose;
    public boolean clearcache = false;
    public boolean clearsessioncache = false;
    public ThemeableBrowser.BrowserButton closeButton;
    public ThemeableBrowser.BrowserButton[] customButtons;
    public boolean disableAnimation;
    public ThemeableBrowser.BrowserButton forwardButton;
    public boolean fullscreen;
    public boolean hardwareback = true;
    public boolean hidden = false;
    public boolean location = true;
    public ThemeableBrowser.BrowserMenu menu;
    public ThemeableBrowser.Title title;
    public ThemeableBrowser.Toolbar toolbar;
    public boolean zoom = true;
  }
  
  public static abstract interface PageLoadListener
  {
    public abstract void onPageFinished(String paramString, boolean paramBoolean1, boolean paramBoolean2);
  }
  
  public class ThemeableBrowserClient
    extends WebViewClient
  {
    ThemeableBrowser.PageLoadListener callback;
    CordovaWebView webView;
    
    public ThemeableBrowserClient(CordovaWebView paramCordovaWebView, ThemeableBrowser.PageLoadListener paramPageLoadListener)
    {
      this.webView = paramCordovaWebView;
      this.callback = paramPageLoadListener;
    }
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      try
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("type", "loadstop");
        localJSONObject.put("url", paramString);
        ThemeableBrowser.this.sendUpdate(localJSONObject, true);
        if (this.callback != null) {
          this.callback.onPageFinished(paramString, paramWebView.canGoBack(), paramWebView.canGoForward());
        }
        return;
      }
      catch (JSONException paramWebView) {}
    }
    
    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      super.onPageStarted(paramWebView, paramString, paramBitmap);
      if ((paramString.startsWith("http:")) || (paramString.startsWith("https:")) || (paramString.startsWith("file:"))) {}
      for (paramWebView = paramString;; paramWebView = "http://" + paramString)
      {
        if (!paramWebView.equals(ThemeableBrowser.this.edittext.getText().toString())) {
          ThemeableBrowser.this.edittext.setText(paramWebView);
        }
        try
        {
          paramString = new JSONObject();
          paramString.put("type", "loadstart");
          paramString.put("url", paramWebView);
          ThemeableBrowser.this.sendUpdate(paramString, true);
          return;
        }
        catch (JSONException paramWebView)
        {
          Log.e("ThemeableBrowser", "URI passed in has caused a JSON error.");
        }
        Log.e("ThemeableBrowser", "Possible Uncaught/Unknown URI");
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
        ThemeableBrowser.this.sendUpdate(paramWebView, true, PluginResult.Status.ERROR);
        return;
      }
      catch (JSONException paramWebView) {}
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      if (paramString.startsWith("tel:")) {
        try
        {
          paramWebView = new Intent("android.intent.action.DIAL");
          paramWebView.setData(Uri.parse(paramString));
          ThemeableBrowser.this.cordova.getActivity().startActivity(paramWebView);
          return true;
        }
        catch (ActivityNotFoundException paramWebView)
        {
          Log.e("ThemeableBrowser", "Error dialing " + paramString + ": " + paramWebView.toString());
        }
      }
      do
      {
        for (;;)
        {
          return false;
          if ((!paramString.startsWith("geo:")) && (!paramString.startsWith("mailto:")) && (!paramString.startsWith("market:"))) {
            break;
          }
          try
          {
            paramWebView = new Intent("android.intent.action.VIEW");
            paramWebView.setData(Uri.parse(paramString));
            ThemeableBrowser.this.cordova.getActivity().startActivity(paramWebView);
            return true;
          }
          catch (ActivityNotFoundException paramWebView)
          {
            Log.e("ThemeableBrowser", "Error with " + paramString + ": " + paramWebView.toString());
          }
        }
      } while (!paramString.startsWith("sms:"));
      for (;;)
      {
        Intent localIntent;
        int i;
        try
        {
          localIntent = new Intent("android.intent.action.VIEW");
          i = paramString.indexOf('?');
          if (i != -1) {
            break label334;
          }
          paramWebView = paramString.substring(4);
          localIntent.setData(Uri.parse("sms:" + paramWebView));
          localIntent.putExtra("address", paramWebView);
          localIntent.setType("vnd.android-dir/mms-sms");
          ThemeableBrowser.this.cordova.getActivity().startActivity(localIntent);
          return true;
        }
        catch (ActivityNotFoundException paramWebView)
        {
          Log.e("ThemeableBrowser", "Error sending sms " + paramString + ":" + paramWebView.toString());
        }
        break;
        label334:
        String str1 = paramString.substring(4, i);
        String str2 = Uri.parse(paramString).getQuery();
        paramWebView = str1;
        if (str2 != null)
        {
          paramWebView = str1;
          if (str2.startsWith("body="))
          {
            localIntent.putExtra("sms_body", str2.substring(5));
            paramWebView = str1;
          }
        }
      }
    }
  }
  
  private static class Title
  {
    public String color;
    public boolean showPageTitle;
    public String staticText;
  }
  
  private static class Toolbar
  {
    public String color;
    public int height = 44;
    public String image;
    public String wwwImage;
    public double wwwImageDensity = 1.0D;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\initialxy\cordova\themeablebrowser\ThemeableBrowser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */