package org.apache.cordova;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.json.JSONException;

public class PluginManager
{
  private static final int SLOW_EXEC_WARNING_THRESHOLD;
  private static String TAG = "PluginManager";
  private final CordovaWebView app;
  private final CordovaInterface ctx;
  private final LinkedHashMap<String, PluginEntry> entryMap = new LinkedHashMap();
  private boolean isInitialized;
  private CordovaPlugin permissionRequester;
  private final LinkedHashMap<String, CordovaPlugin> pluginMap = new LinkedHashMap();
  
  static
  {
    if (Debug.isDebuggerConnected()) {}
    for (int i = 60;; i = 16)
    {
      SLOW_EXEC_WARNING_THRESHOLD = i;
      return;
    }
  }
  
  public PluginManager(CordovaWebView paramCordovaWebView, CordovaInterface paramCordovaInterface, Collection<PluginEntry> paramCollection)
  {
    this.ctx = paramCordovaInterface;
    this.app = paramCordovaWebView;
    setPluginEntries(paramCollection);
  }
  
  private CordovaPlugin instantiatePlugin(String paramString)
  {
    CordovaPlugin localCordovaPlugin = null;
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString != null) {
      localObject1 = localObject2;
    }
    for (;;)
    {
      try
      {
        if ("".equals(paramString)) {
          break label98;
        }
        localObject1 = Class.forName(paramString);
      }
      catch (Exception localException)
      {
        ThrowableExtension.printStackTrace(localException);
        System.out.println("Error adding plugin " + paramString + ".");
        return null;
      }
      if ((i & CordovaPlugin.class.isAssignableFrom((Class)localObject1)) != 0) {
        localCordovaPlugin = (CordovaPlugin)((Class)localObject1).newInstance();
      }
      return localCordovaPlugin;
      int i = 0;
      continue;
      label98:
      if (localException != null) {
        i = 1;
      }
    }
  }
  
  private void startupPlugins()
  {
    Iterator localIterator = this.entryMap.values().iterator();
    while (localIterator.hasNext())
    {
      PluginEntry localPluginEntry = (PluginEntry)localIterator.next();
      if (localPluginEntry.onload) {
        getPlugin(localPluginEntry.service);
      } else {
        this.pluginMap.put(localPluginEntry.service, null);
      }
    }
  }
  
  public void addService(String paramString1, String paramString2)
  {
    addService(new PluginEntry(paramString1, paramString2, false));
  }
  
  public void addService(PluginEntry paramPluginEntry)
  {
    this.entryMap.put(paramPluginEntry.service, paramPluginEntry);
    if (paramPluginEntry.plugin != null)
    {
      paramPluginEntry.plugin.privateInitialize(paramPluginEntry.service, this.ctx, this.app, this.app.getPreferences());
      this.pluginMap.put(paramPluginEntry.service, paramPluginEntry.plugin);
    }
  }
  
  public void exec(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    CordovaPlugin localCordovaPlugin = getPlugin(paramString1);
    if (localCordovaPlugin == null)
    {
      LOG.d(TAG, "exec() call to unknown plugin: " + paramString1);
      paramString1 = new PluginResult(PluginResult.Status.CLASS_NOT_FOUND_EXCEPTION);
      this.app.sendPluginResult(paramString1, paramString3);
    }
    for (;;)
    {
      return;
      paramString3 = new CallbackContext(paramString3, this.app);
      try
      {
        long l = System.currentTimeMillis();
        boolean bool = localCordovaPlugin.execute(paramString2, paramString4, paramString3);
        l = System.currentTimeMillis() - l;
        if (l > SLOW_EXEC_WARNING_THRESHOLD) {
          LOG.w(TAG, "THREAD WARNING: exec() call to " + paramString1 + "." + paramString2 + " blocked the main thread for " + l + "ms. Plugin should use CordovaInterface.getThreadPool().");
        }
        if (!bool)
        {
          paramString3.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
          return;
        }
      }
      catch (JSONException paramString1)
      {
        paramString3.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
        return;
      }
      catch (Exception paramString1)
      {
        LOG.e(TAG, "Uncaught exception from plugin", paramString1);
        paramString3.error(paramString1.getMessage());
      }
    }
  }
  
  public CordovaPlugin getPlugin(String paramString)
  {
    CordovaPlugin localCordovaPlugin = (CordovaPlugin)this.pluginMap.get(paramString);
    Object localObject = localCordovaPlugin;
    if (localCordovaPlugin == null)
    {
      localObject = (PluginEntry)this.entryMap.get(paramString);
      if (localObject == null) {
        return null;
      }
      if (((PluginEntry)localObject).plugin == null) {
        break label82;
      }
    }
    label82:
    for (localObject = ((PluginEntry)localObject).plugin;; localObject = instantiatePlugin(((PluginEntry)localObject).pluginClass))
    {
      ((CordovaPlugin)localObject).privateInitialize(paramString, this.ctx, this.app, this.app.getPreferences());
      this.pluginMap.put(paramString, localObject);
      return (CordovaPlugin)localObject;
    }
  }
  
  public Collection<PluginEntry> getPluginEntries()
  {
    return this.entryMap.values();
  }
  
  public void init()
  {
    LOG.d(TAG, "init()");
    this.isInitialized = true;
    onPause(false);
    onDestroy();
    this.pluginMap.clear();
    startupPlugins();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    while (localIterator.hasNext())
    {
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)localIterator.next();
      if (localCordovaPlugin != null) {
        localCordovaPlugin.onConfigurationChanged(paramConfiguration);
      }
    }
  }
  
  public void onDestroy()
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    while (localIterator.hasNext())
    {
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)localIterator.next();
      if (localCordovaPlugin != null) {
        localCordovaPlugin.onDestroy();
      }
    }
  }
  
  public void onNewIntent(Intent paramIntent)
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    while (localIterator.hasNext())
    {
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)localIterator.next();
      if (localCordovaPlugin != null) {
        localCordovaPlugin.onNewIntent(paramIntent);
      }
    }
  }
  
  public boolean onOverrideUrlLoading(String paramString)
  {
    Iterator localIterator = this.entryMap.values().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (PluginEntry)localIterator.next();
      localObject = (CordovaPlugin)this.pluginMap.get(((PluginEntry)localObject).service);
      if ((localObject != null) && (((CordovaPlugin)localObject).onOverrideUrlLoading(paramString))) {
        return true;
      }
    }
    return false;
  }
  
  public void onPause(boolean paramBoolean)
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    while (localIterator.hasNext())
    {
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)localIterator.next();
      if (localCordovaPlugin != null) {
        localCordovaPlugin.onPause(paramBoolean);
      }
    }
  }
  
  public boolean onReceivedClientCertRequest(CordovaWebView paramCordovaWebView, ICordovaClientCertRequest paramICordovaClientCertRequest)
  {
    paramCordovaWebView = this.pluginMap.values().iterator();
    while (paramCordovaWebView.hasNext())
    {
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)paramCordovaWebView.next();
      if ((localCordovaPlugin != null) && (localCordovaPlugin.onReceivedClientCertRequest(this.app, paramICordovaClientCertRequest))) {
        return true;
      }
    }
    return false;
  }
  
  public boolean onReceivedHttpAuthRequest(CordovaWebView paramCordovaWebView, ICordovaHttpAuthHandler paramICordovaHttpAuthHandler, String paramString1, String paramString2)
  {
    paramCordovaWebView = this.pluginMap.values().iterator();
    while (paramCordovaWebView.hasNext())
    {
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)paramCordovaWebView.next();
      if ((localCordovaPlugin != null) && (localCordovaPlugin.onReceivedHttpAuthRequest(this.app, paramICordovaHttpAuthHandler, paramString1, paramString2))) {
        return true;
      }
    }
    return false;
  }
  
  public void onReset()
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    while (localIterator.hasNext())
    {
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)localIterator.next();
      if (localCordovaPlugin != null) {
        localCordovaPlugin.onReset();
      }
    }
  }
  
  public void onResume(boolean paramBoolean)
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    while (localIterator.hasNext())
    {
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)localIterator.next();
      if (localCordovaPlugin != null) {
        localCordovaPlugin.onResume(paramBoolean);
      }
    }
  }
  
  public Bundle onSaveInstanceState()
  {
    Bundle localBundle1 = new Bundle();
    Iterator localIterator = this.pluginMap.values().iterator();
    while (localIterator.hasNext())
    {
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)localIterator.next();
      if (localCordovaPlugin != null)
      {
        Bundle localBundle2 = localCordovaPlugin.onSaveInstanceState();
        if (localBundle2 != null) {
          localBundle1.putBundle(localCordovaPlugin.getServiceName(), localBundle2);
        }
      }
    }
    return localBundle1;
  }
  
  public void onStart()
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    while (localIterator.hasNext())
    {
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)localIterator.next();
      if (localCordovaPlugin != null) {
        localCordovaPlugin.onStart();
      }
    }
  }
  
  public void onStop()
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    while (localIterator.hasNext())
    {
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)localIterator.next();
      if (localCordovaPlugin != null) {
        localCordovaPlugin.onStop();
      }
    }
  }
  
  public Object postMessage(String paramString, Object paramObject)
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (CordovaPlugin)localIterator.next();
      if (localObject != null)
      {
        localObject = ((CordovaPlugin)localObject).onMessage(paramString, paramObject);
        if (localObject != null) {
          return localObject;
        }
      }
    }
    return this.ctx.onMessage(paramString, paramObject);
  }
  
  Uri remapUri(Uri paramUri)
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (CordovaPlugin)localIterator.next();
      if (localObject != null)
      {
        localObject = ((CordovaPlugin)localObject).remapUri(paramUri);
        if (localObject != null) {
          return (Uri)localObject;
        }
      }
    }
    return null;
  }
  
  public void setPluginEntries(Collection<PluginEntry> paramCollection)
  {
    if (this.isInitialized)
    {
      onPause(false);
      onDestroy();
      this.pluginMap.clear();
      this.entryMap.clear();
    }
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      addService((PluginEntry)paramCollection.next());
    }
    if (this.isInitialized) {
      startupPlugins();
    }
  }
  
  public boolean shouldAllowBridgeAccess(String paramString)
  {
    Iterator localIterator = this.entryMap.values().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (PluginEntry)localIterator.next();
      localObject = (CordovaPlugin)this.pluginMap.get(((PluginEntry)localObject).service);
      if (localObject != null)
      {
        localObject = ((CordovaPlugin)localObject).shouldAllowBridgeAccess(paramString);
        if (localObject != null) {
          return ((Boolean)localObject).booleanValue();
        }
      }
    }
    return paramString.startsWith("file://");
  }
  
  public boolean shouldAllowNavigation(String paramString)
  {
    Iterator localIterator = this.entryMap.values().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (PluginEntry)localIterator.next();
      localObject = (CordovaPlugin)this.pluginMap.get(((PluginEntry)localObject).service);
      if (localObject != null)
      {
        localObject = ((CordovaPlugin)localObject).shouldAllowNavigation(paramString);
        if (localObject != null) {
          return ((Boolean)localObject).booleanValue();
        }
      }
    }
    return (paramString.startsWith("file://")) || (paramString.startsWith("about:blank"));
  }
  
  public boolean shouldAllowRequest(String paramString)
  {
    boolean bool2 = true;
    Iterator localIterator = this.entryMap.values().iterator();
    boolean bool1;
    while (localIterator.hasNext())
    {
      Object localObject = (PluginEntry)localIterator.next();
      localObject = (CordovaPlugin)this.pluginMap.get(((PluginEntry)localObject).service);
      if (localObject != null)
      {
        localObject = ((CordovaPlugin)localObject).shouldAllowRequest(paramString);
        if (localObject != null) {
          bool1 = ((Boolean)localObject).booleanValue();
        }
      }
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return bool1;
              bool1 = bool2;
            } while (paramString.startsWith("blob:"));
            bool1 = bool2;
          } while (paramString.startsWith("data:"));
          bool1 = bool2;
        } while (paramString.startsWith("about:blank"));
        bool1 = bool2;
      } while (paramString.startsWith("https://ssl.gstatic.com/accessibility/javascript/android/"));
      if (!paramString.startsWith("file://")) {
        break;
      }
      bool1 = bool2;
    } while (!paramString.contains("/app_webview/"));
    return false;
    return false;
  }
  
  public Boolean shouldOpenExternalUrl(String paramString)
  {
    Iterator localIterator = this.entryMap.values().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (PluginEntry)localIterator.next();
      localObject = (CordovaPlugin)this.pluginMap.get(((PluginEntry)localObject).service);
      if (localObject != null)
      {
        localObject = ((CordovaPlugin)localObject).shouldOpenExternalUrl(paramString);
        if (localObject != null) {
          return (Boolean)localObject;
        }
      }
    }
    return Boolean.valueOf(false);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\PluginManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */