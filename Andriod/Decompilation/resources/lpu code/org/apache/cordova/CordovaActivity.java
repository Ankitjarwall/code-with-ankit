package org.apache.cordova;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout.LayoutParams;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.ArrayList;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaActivity
  extends Activity
{
  private static int ACTIVITY_EXITING = 2;
  private static int ACTIVITY_RUNNING;
  private static int ACTIVITY_STARTING;
  public static String TAG = "CordovaActivity";
  protected CordovaWebView appView;
  protected CordovaInterfaceImpl cordovaInterface;
  protected boolean immersiveMode;
  protected boolean keepRunning = true;
  protected String launchUrl;
  protected ArrayList<PluginEntry> pluginEntries;
  protected CordovaPreferences preferences;
  
  static
  {
    ACTIVITY_STARTING = 0;
    ACTIVITY_RUNNING = 1;
  }
  
  protected void createViews()
  {
    this.appView.getView().setId(100);
    this.appView.getView().setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    setContentView(this.appView.getView());
    if (this.preferences.contains("BackgroundColor")) {}
    try
    {
      int i = this.preferences.getInteger("BackgroundColor", -16777216);
      this.appView.getView().setBackgroundColor(i);
      this.appView.getView().requestFocusFromTouch();
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        ThrowableExtension.printStackTrace(localNumberFormatException);
      }
    }
  }
  
  public void displayError(final String paramString1, final String paramString2, final String paramString3, final boolean paramBoolean)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          AlertDialog.Builder localBuilder = new AlertDialog.Builder(jdField_this);
          localBuilder.setMessage(paramString2);
          localBuilder.setTitle(paramString1);
          localBuilder.setCancelable(false);
          localBuilder.setPositiveButton(paramString3, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              paramAnonymous2DialogInterface.dismiss();
              if (CordovaActivity.4.this.val$exit) {
                CordovaActivity.this.finish();
              }
            }
          });
          localBuilder.create();
          localBuilder.show();
          return;
        }
        catch (Exception localException)
        {
          CordovaActivity.this.finish();
        }
      }
    });
  }
  
  protected void init()
  {
    this.appView = makeWebView();
    createViews();
    if (!this.appView.isInitialized()) {
      this.appView.init(this.cordovaInterface, this.pluginEntries, this.preferences);
    }
    this.cordovaInterface.onCordovaInit(this.appView.getPluginManager());
    if ("media".equals(this.preferences.getString("DefaultVolumeStream", "").toLowerCase(Locale.ENGLISH))) {
      setVolumeControlStream(3);
    }
  }
  
  protected void loadConfig()
  {
    ConfigXmlParser localConfigXmlParser = new ConfigXmlParser();
    localConfigXmlParser.parse(this);
    this.preferences = localConfigXmlParser.getPreferences();
    this.preferences.setPreferencesBundle(getIntent().getExtras());
    this.launchUrl = localConfigXmlParser.getLaunchUrl();
    this.pluginEntries = localConfigXmlParser.getPluginEntries();
    Config.parser = localConfigXmlParser;
  }
  
  public void loadUrl(String paramString)
  {
    if (this.appView == null) {
      init();
    }
    this.keepRunning = this.preferences.getBoolean("KeepRunning", true);
    this.appView.loadUrlIntoView(paramString, true);
  }
  
  protected CordovaInterfaceImpl makeCordovaInterface()
  {
    new CordovaInterfaceImpl(this)
    {
      public Object onMessage(String paramAnonymousString, Object paramAnonymousObject)
      {
        return CordovaActivity.this.onMessage(paramAnonymousString, paramAnonymousObject);
      }
    };
  }
  
  protected CordovaWebView makeWebView()
  {
    return new CordovaWebViewImpl(makeWebViewEngine());
  }
  
  protected CordovaWebViewEngine makeWebViewEngine()
  {
    return CordovaWebViewImpl.createEngine(this, this.preferences);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    LOG.d(TAG, "Incoming Result. Request code = " + paramInt1);
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    this.cordovaInterface.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (this.appView == null) {}
    PluginManager localPluginManager;
    do
    {
      return;
      localPluginManager = this.appView.getPluginManager();
    } while (localPluginManager == null);
    localPluginManager.onConfigurationChanged(paramConfiguration);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    loadConfig();
    LOG.setLogLevel(this.preferences.getString("loglevel", "ERROR"));
    LOG.i(TAG, "Apache Cordova native platform version 7.1.4 is starting");
    LOG.d(TAG, "CordovaActivity.onCreate()");
    if (!this.preferences.getBoolean("ShowTitle", false)) {
      getWindow().requestFeature(1);
    }
    if (this.preferences.getBoolean("SetFullscreen", false))
    {
      LOG.d(TAG, "The SetFullscreen configuration is deprecated in favor of Fullscreen, and will be removed in a future version.");
      this.preferences.set("Fullscreen", true);
    }
    if (this.preferences.getBoolean("Fullscreen", false)) {
      if (!this.preferences.getBoolean("FullscreenNotImmersive", false)) {
        this.immersiveMode = true;
      }
    }
    for (;;)
    {
      super.onCreate(paramBundle);
      this.cordovaInterface = makeCordovaInterface();
      if (paramBundle != null) {
        this.cordovaInterface.restoreInstanceState(paramBundle);
      }
      return;
      getWindow().setFlags(1024, 1024);
      continue;
      getWindow().setFlags(2048, 2048);
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    if (this.appView != null) {
      this.appView.getPluginManager().postMessage("onCreateOptionsMenu", paramMenu);
    }
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public void onDestroy()
  {
    LOG.d(TAG, "CordovaActivity.onDestroy()");
    super.onDestroy();
    if (this.appView != null) {
      this.appView.handleDestroy();
    }
  }
  
  public Object onMessage(String paramString, Object paramObject)
  {
    if ("onReceivedError".equals(paramString)) {
      paramString = (JSONObject)paramObject;
    }
    for (;;)
    {
      try
      {
        onReceivedError(paramString.getInt("errorCode"), paramString.getString("description"), paramString.getString("url"));
        return null;
      }
      catch (JSONException paramString)
      {
        ThrowableExtension.printStackTrace(paramString);
        continue;
      }
      if ("exit".equals(paramString)) {
        finish();
      }
    }
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    if (this.appView != null) {
      this.appView.onNewIntent(paramIntent);
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (this.appView != null) {
      this.appView.getPluginManager().postMessage("onOptionsItemSelected", paramMenuItem);
    }
    return true;
  }
  
  protected void onPause()
  {
    super.onPause();
    LOG.d(TAG, "Paused the activity.");
    if (this.appView != null) {
      if ((!this.keepRunning) && (this.cordovaInterface.activityResultCallback == null)) {
        break label50;
      }
    }
    label50:
    for (boolean bool = true;; bool = false)
    {
      this.appView.handlePause(bool);
      return;
    }
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    if (this.appView != null) {
      this.appView.getPluginManager().postMessage("onPrepareOptionsMenu", paramMenu);
    }
    return true;
  }
  
  public void onReceivedError(int paramInt, final String paramString1, final String paramString2)
  {
    final String str = this.preferences.getString("errorUrl", null);
    if ((str != null) && (!paramString2.equals(str)) && (this.appView != null))
    {
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          jdField_this.appView.showWebPage(str, false, true, null);
        }
      });
      return;
    }
    if (paramInt != -2) {}
    for (final boolean bool = true;; bool = false)
    {
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (bool)
          {
            jdField_this.appView.getView().setVisibility(8);
            jdField_this.displayError("Application Error", paramString1 + " (" + paramString2 + ")", "OK", bool);
          }
        }
      });
      return;
    }
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    try
    {
      this.cordovaInterface.onRequestPermissionResult(paramInt, paramArrayOfString, paramArrayOfInt);
      return;
    }
    catch (JSONException paramArrayOfString)
    {
      LOG.d(TAG, "JSONException: Parameters fed into the method are not valid");
      ThrowableExtension.printStackTrace(paramArrayOfString);
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    LOG.d(TAG, "Resumed the activity.");
    if (this.appView == null) {
      return;
    }
    getWindow().getDecorView().requestFocus();
    this.appView.handleResume(this.keepRunning);
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    this.cordovaInterface.onSaveInstanceState(paramBundle);
    super.onSaveInstanceState(paramBundle);
  }
  
  protected void onStart()
  {
    super.onStart();
    LOG.d(TAG, "Started the activity.");
    if (this.appView == null) {
      return;
    }
    this.appView.handleStart();
  }
  
  protected void onStop()
  {
    super.onStop();
    LOG.d(TAG, "Stopped the activity.");
    if (this.appView == null) {
      return;
    }
    this.appView.handleStop();
  }
  
  @SuppressLint({"InlinedApi"})
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if ((paramBoolean) && (this.immersiveMode)) {
      getWindow().getDecorView().setSystemUiVisibility(5894);
    }
  }
  
  @SuppressLint({"NewApi"})
  public void startActivityForResult(Intent paramIntent, int paramInt, Bundle paramBundle)
  {
    this.cordovaInterface.setActivityResultRequestCode(paramInt);
    super.startActivityForResult(paramIntent, paramInt, paramBundle);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\CordovaActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */