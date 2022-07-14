package de.appplant.cordova.plugin.badge;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Badge
  extends CordovaPlugin
{
  private BadgeImpl impl;
  
  private void checkSupport(final CallbackContext paramCallbackContext)
  {
    this.cordova.getThreadPool().execute(new Runnable()
    {
      public void run()
      {
        boolean bool = Badge.this.impl.isSupported();
        PluginResult localPluginResult = new PluginResult(PluginResult.Status.OK, bool);
        paramCallbackContext.sendPluginResult(localPluginResult);
      }
    });
  }
  
  private void clearBadge(final CallbackContext paramCallbackContext)
  {
    this.cordova.getThreadPool().execute(new Runnable()
    {
      public void run()
      {
        Badge.this.impl.clearBadge();
        int i = Badge.this.impl.getBadge();
        paramCallbackContext.success(i);
      }
    });
  }
  
  private void getBadge(final CallbackContext paramCallbackContext)
  {
    this.cordova.getThreadPool().execute(new Runnable()
    {
      public void run()
      {
        int i = Badge.this.impl.getBadge();
        paramCallbackContext.success(i);
      }
    });
  }
  
  private Context getContext()
  {
    return this.cordova.getActivity();
  }
  
  private void loadConfig(final CallbackContext paramCallbackContext)
  {
    this.cordova.getThreadPool().execute(new Runnable()
    {
      public void run()
      {
        JSONObject localJSONObject = Badge.this.impl.loadConfig();
        paramCallbackContext.success(localJSONObject);
      }
    });
  }
  
  private void saveConfig(final JSONObject paramJSONObject)
  {
    this.cordova.getThreadPool().execute(new Runnable()
    {
      public void run()
      {
        Badge.this.impl.saveConfig(paramJSONObject);
      }
    });
  }
  
  private void setBadge(final JSONArray paramJSONArray, final CallbackContext paramCallbackContext)
  {
    this.cordova.getThreadPool().execute(new Runnable()
    {
      public void run()
      {
        Badge.this.impl.clearBadge();
        Badge.this.impl.setBadge(paramJSONArray.optInt(0));
        int i = Badge.this.impl.getBadge();
        paramCallbackContext.success(i);
      }
    });
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    if (paramString.equalsIgnoreCase("load"))
    {
      loadConfig(paramCallbackContext);
      return true;
    }
    if (paramString.equalsIgnoreCase("save"))
    {
      saveConfig(paramJSONArray.getJSONObject(0));
      return true;
    }
    if (paramString.equalsIgnoreCase("clear"))
    {
      clearBadge(paramCallbackContext);
      return true;
    }
    if (paramString.equalsIgnoreCase("get"))
    {
      getBadge(paramCallbackContext);
      return true;
    }
    if (paramString.equalsIgnoreCase("set"))
    {
      setBadge(paramJSONArray, paramCallbackContext);
      return true;
    }
    if (paramString.equalsIgnoreCase("check"))
    {
      checkSupport(paramCallbackContext);
      return true;
    }
    return false;
  }
  
  protected void pluginInitialize()
  {
    this.impl = new BadgeImpl(getContext());
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\badge\Badge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */