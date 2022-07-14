package de.appplant.cordova.plugin.localnotification;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.Context;
import android.util.Pair;
import android.view.View;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import de.appplant.cordova.plugin.notification.Manager;
import de.appplant.cordova.plugin.notification.Notification;
import de.appplant.cordova.plugin.notification.Notification.Type;
import de.appplant.cordova.plugin.notification.Options;
import de.appplant.cordova.plugin.notification.Request;
import de.appplant.cordova.plugin.notification.action.ActionGroup;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocalNotification
  extends CordovaPlugin
{
  private static Boolean deviceready = Boolean.valueOf(false);
  private static ArrayList<String> eventQueue = new ArrayList();
  private static Pair<Integer, String> launchDetails;
  private static WeakReference<CordovaWebView> webView = null;
  
  private void actions(JSONObject paramJSONObject)
  {
    paramJSONObject = ActionGroup.parse(this.cordova.getActivity(), paramJSONObject);
    if (paramJSONObject != null) {
      ActionGroup.register(paramJSONObject);
    }
  }
  
  private void cancel(JSONArray paramJSONArray)
  {
    int i = 0;
    if (i < paramJSONArray.length())
    {
      int j = paramJSONArray.optInt(i, 0);
      Notification localNotification = getNotMgr().cancel(j);
      if (localNotification == null) {}
      for (;;)
      {
        i += 1;
        break;
        fireEvent("cancel", localNotification);
      }
    }
  }
  
  private void cancelAll()
  {
    getNotMgr().cancelAll();
    fireEvent("cancelall");
  }
  
  private void check(CallbackContext paramCallbackContext)
  {
    boolean bool = getNotMgr().hasPermission();
    paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, bool));
  }
  
  private void clear(JSONArray paramJSONArray)
  {
    int i = 0;
    if (i < paramJSONArray.length())
    {
      int j = paramJSONArray.optInt(i, 0);
      Notification localNotification = getNotMgr().clear(j);
      if (localNotification == null) {}
      for (;;)
      {
        i += 1;
        break;
        fireEvent("clear", localNotification);
      }
    }
  }
  
  private void clearAll()
  {
    getNotMgr().clearAll();
    fireEvent("clearall");
  }
  
  private static void deviceready()
  {
    try
    {
      deviceready = Boolean.valueOf(true);
      Iterator localIterator = eventQueue.iterator();
      while (localIterator.hasNext()) {
        sendJavascript((String)localIterator.next());
      }
      eventQueue.clear();
    }
    finally {}
  }
  
  private void fireEvent(String paramString)
  {
    fireEvent(paramString, null, new JSONObject());
  }
  
  static void fireEvent(String paramString, Notification paramNotification)
  {
    fireEvent(paramString, paramNotification, new JSONObject());
  }
  
  static void fireEvent(String paramString, Notification paramNotification, JSONObject paramJSONObject)
  {
    for (;;)
    {
      try
      {
        paramJSONObject.put("event", paramString);
        paramJSONObject.put("foreground", isInForeground());
        if (deviceready.booleanValue()) {
          continue;
        }
        bool = true;
        paramJSONObject.put("queued", bool);
        if (paramNotification != null) {
          paramJSONObject.put("notification", paramNotification.getId());
        }
      }
      catch (JSONException localJSONException)
      {
        boolean bool;
        ThrowableExtension.printStackTrace(localJSONException);
        continue;
        paramJSONObject = paramJSONObject.toString();
        continue;
      }
      if (paramNotification == null) {
        continue;
      }
      paramJSONObject = paramNotification.toString() + "," + paramJSONObject.toString();
      paramJSONObject = "cordova.plugins.notification.local.core.fireEvent(\"" + paramString + "\"," + paramJSONObject + ")";
      if ((launchDetails == null) && (!deviceready.booleanValue()) && (paramNotification != null)) {
        launchDetails = new Pair(Integer.valueOf(paramNotification.getId()), paramString);
      }
      sendJavascript(paramJSONObject);
      return;
      bool = false;
    }
  }
  
  private Manager getNotMgr()
  {
    return Manager.getInstance(this.cordova.getActivity());
  }
  
  private void ids(CallbackContext paramCallbackContext)
  {
    paramCallbackContext.success(new JSONArray(getNotMgr().getIds()));
  }
  
  private static boolean isInForeground()
  {
    if ((!deviceready.booleanValue()) || (webView == null)) {}
    CordovaWebView localCordovaWebView;
    KeyguardManager localKeyguardManager;
    do
    {
      return false;
      localCordovaWebView = (CordovaWebView)webView.get();
      localKeyguardManager = (KeyguardManager)localCordovaWebView.getContext().getSystemService("keyguard");
    } while (((localKeyguardManager != null) && (localKeyguardManager.isKeyguardLocked())) || (localCordovaWebView.getView().getWindowVisibility() != 0));
    return true;
  }
  
  @SuppressLint({"DefaultLocale"})
  private void launch(CallbackContext paramCallbackContext)
  {
    if (launchDetails == null) {
      return;
    }
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("id", launchDetails.first);
      localJSONObject.put("action", launchDetails.second);
      paramCallbackContext.success(localJSONObject);
      launchDetails = null;
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        ThrowableExtension.printStackTrace(localJSONException);
      }
    }
  }
  
  private void notification(int paramInt, CallbackContext paramCallbackContext)
  {
    Options localOptions = getNotMgr().getOptions(paramInt);
    if (localOptions != null)
    {
      paramCallbackContext.success(localOptions.getDict());
      return;
    }
    paramCallbackContext.success();
  }
  
  private void notifications(JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    if (paramJSONArray.length() == 0) {}
    for (paramJSONArray = getNotMgr().getOptions();; paramJSONArray = getNotMgr().getOptionsById(toList(paramJSONArray)))
    {
      paramCallbackContext.success(new JSONArray(paramJSONArray));
      return;
    }
  }
  
  private void request(CallbackContext paramCallbackContext)
  {
    check(paramCallbackContext);
  }
  
  private void schedule(JSONArray paramJSONArray)
  {
    Manager localManager = getNotMgr();
    int i = 0;
    while (i < paramJSONArray.length())
    {
      Notification localNotification = localManager.schedule(new Request(new Options(paramJSONArray.optJSONObject(i))), TriggerReceiver.class);
      if (localNotification != null) {
        fireEvent("add", localNotification);
      }
      i += 1;
    }
  }
  
  private void scheduledIds(CallbackContext paramCallbackContext)
  {
    paramCallbackContext.success(new JSONArray(getNotMgr().getIdsByType(Notification.Type.SCHEDULED)));
  }
  
  private void scheduledNotifications(CallbackContext paramCallbackContext)
  {
    paramCallbackContext.success(new JSONArray(getNotMgr().getOptionsByType(Notification.Type.SCHEDULED)));
  }
  
  /* Error */
  private static void sendJavascript(final String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 33	de/appplant/cordova/plugin/localnotification/LocalNotification:deviceready	Ljava/lang/Boolean;
    //   6: invokevirtual 246	java/lang/Boolean:booleanValue	()Z
    //   9: ifeq +9 -> 18
    //   12: getstatic 25	de/appplant/cordova/plugin/localnotification/LocalNotification:webView	Ljava/lang/ref/WeakReference;
    //   15: ifnonnull +15 -> 30
    //   18: getstatic 40	de/appplant/cordova/plugin/localnotification/LocalNotification:eventQueue	Ljava/util/ArrayList;
    //   21: aload_0
    //   22: invokevirtual 417	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   25: pop
    //   26: ldc 2
    //   28: monitorexit
    //   29: return
    //   30: getstatic 25	de/appplant/cordova/plugin/localnotification/LocalNotification:webView	Ljava/lang/ref/WeakReference;
    //   33: invokevirtual 315	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   36: checkcast 317	org/apache/cordova/CordovaWebView
    //   39: astore_1
    //   40: aload_1
    //   41: invokeinterface 321 1 0
    //   46: checkcast 419	android/app/Activity
    //   49: checkcast 419	android/app/Activity
    //   52: new 8	de/appplant/cordova/plugin/localnotification/LocalNotification$2
    //   55: dup
    //   56: aload_1
    //   57: aload_0
    //   58: invokespecial 422	de/appplant/cordova/plugin/localnotification/LocalNotification$2:<init>	(Lorg/apache/cordova/CordovaWebView;Ljava/lang/String;)V
    //   61: invokevirtual 426	android/app/Activity:runOnUiThread	(Ljava/lang/Runnable;)V
    //   64: goto -38 -> 26
    //   67: astore_0
    //   68: ldc 2
    //   70: monitorexit
    //   71: aload_0
    //   72: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	73	0	paramString	String
    //   39	18	1	localCordovaWebView	CordovaWebView
    // Exception table:
    //   from	to	target	type
    //   3	18	67	finally
    //   18	26	67	finally
    //   30	64	67	finally
  }
  
  private List<Integer> toList(JSONArray paramJSONArray)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramJSONArray.length())
    {
      localArrayList.add(Integer.valueOf(paramJSONArray.optInt(i)));
      i += 1;
    }
    return localArrayList;
  }
  
  private void triggeredIds(CallbackContext paramCallbackContext)
  {
    paramCallbackContext.success(new JSONArray(getNotMgr().getIdsByType(Notification.Type.TRIGGERED)));
  }
  
  private void triggeredNotifications(CallbackContext paramCallbackContext)
  {
    paramCallbackContext.success(new JSONArray(getNotMgr().getOptionsByType(Notification.Type.TRIGGERED)));
  }
  
  private void type(int paramInt, CallbackContext paramCallbackContext)
  {
    Notification localNotification = getNotMgr().get(paramInt);
    if (localNotification == null)
    {
      paramCallbackContext.success("unknown");
      return;
    }
    switch (localNotification.getType())
    {
    default: 
      paramCallbackContext.success("unknown");
      return;
    case ???: 
      paramCallbackContext.success("scheduled");
      return;
    }
    paramCallbackContext.success("triggered");
  }
  
  private void update(JSONArray paramJSONArray)
  {
    int i = 0;
    if (i < paramJSONArray.length())
    {
      Object localObject = paramJSONArray.optJSONObject(i);
      int j = ((JSONObject)localObject).optInt("id", 0);
      localObject = getNotMgr().update(j, (JSONObject)localObject, TriggerReceiver.class);
      if (localObject == null) {}
      for (;;)
      {
        i += 1;
        break;
        fireEvent("update", (Notification)localObject);
      }
    }
  }
  
  public boolean execute(final String paramString, final JSONArray paramJSONArray, final CallbackContext paramCallbackContext)
    throws JSONException
  {
    if (paramString.equals("launch"))
    {
      launch(paramCallbackContext);
      return true;
    }
    this.cordova.getThreadPool().execute(new Runnable()
    {
      public void run()
      {
        if (paramString.equals("ready")) {
          LocalNotification.access$000();
        }
        do
        {
          return;
          if (paramString.equalsIgnoreCase("check"))
          {
            LocalNotification.this.check(paramCallbackContext);
            return;
          }
          if (paramString.equalsIgnoreCase("request"))
          {
            LocalNotification.this.request(paramCallbackContext);
            return;
          }
          if (paramString.equalsIgnoreCase("actions"))
          {
            LocalNotification.this.actions(paramJSONArray.optJSONObject(0));
            paramCallbackContext.success();
            return;
          }
          if (paramString.equalsIgnoreCase("schedule"))
          {
            LocalNotification.this.schedule(paramJSONArray);
            LocalNotification.this.check(paramCallbackContext);
            return;
          }
          if (paramString.equals("update"))
          {
            LocalNotification.this.update(paramJSONArray);
            LocalNotification.this.check(paramCallbackContext);
            return;
          }
          if (paramString.equals("cancel"))
          {
            LocalNotification.this.cancel(paramJSONArray);
            paramCallbackContext.success();
            return;
          }
          if (paramString.equals("cancelAll"))
          {
            LocalNotification.this.cancelAll();
            paramCallbackContext.success();
            return;
          }
          if (paramString.equals("clear"))
          {
            LocalNotification.this.clear(paramJSONArray);
            paramCallbackContext.success();
            return;
          }
          if (paramString.equals("clearAll"))
          {
            LocalNotification.this.clearAll();
            paramCallbackContext.success();
            return;
          }
          if (paramString.equals("type"))
          {
            LocalNotification.this.type(paramJSONArray.optInt(0), paramCallbackContext);
            return;
          }
          if (paramString.equals("ids"))
          {
            LocalNotification.this.ids(paramCallbackContext);
            return;
          }
          if (paramString.equals("scheduledIds"))
          {
            LocalNotification.this.scheduledIds(paramCallbackContext);
            return;
          }
          if (paramString.equals("triggeredIds"))
          {
            LocalNotification.this.triggeredIds(paramCallbackContext);
            return;
          }
          if (paramString.equals("notification"))
          {
            LocalNotification.this.notification(paramJSONArray.optInt(0), paramCallbackContext);
            return;
          }
          if (paramString.equals("notifications"))
          {
            LocalNotification.this.notifications(paramJSONArray, paramCallbackContext);
            return;
          }
          if (paramString.equals("scheduledNotifications"))
          {
            LocalNotification.this.scheduledNotifications(paramCallbackContext);
            return;
          }
        } while (!paramString.equals("triggeredNotifications"));
        LocalNotification.this.triggeredNotifications(paramCallbackContext);
      }
    });
    return true;
  }
  
  public void initialize(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView)
  {
    webView = new WeakReference(paramCordovaWebView);
  }
  
  public void onDestroy()
  {
    deviceready = Boolean.valueOf(false);
  }
  
  public void onResume(boolean paramBoolean)
  {
    super.onResume(paramBoolean);
    deviceready();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\localnotification\LocalNotification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */