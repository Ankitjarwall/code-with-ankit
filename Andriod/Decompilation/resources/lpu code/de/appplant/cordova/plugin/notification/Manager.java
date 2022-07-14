package de.appplant.cordova.plugin.notification;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationManagerCompat;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import de.appplant.cordova.plugin.badge.BadgeImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public final class Manager
{
  static final String CHANNEL_ID = "default-channel-id";
  private static final CharSequence CHANNEL_NAME = "Default channel";
  private Context context;
  
  private Manager(Context paramContext)
  {
    this.context = paramContext;
    createDefaultChannel();
  }
  
  @SuppressLint({"WrongConstant"})
  private void createDefaultChannel()
  {
    NotificationManager localNotificationManager = getNotMgr();
    if (Build.VERSION.SDK_INT < 26) {}
    while (localNotificationManager.getNotificationChannel("default-channel-id") != null) {
      return;
    }
    localNotificationManager.createNotificationChannel(new NotificationChannel("default-channel-id", CHANNEL_NAME, 3));
  }
  
  private List<Notification> getByIds(List<Integer> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Notification localNotification = get(((Integer)paramList.next()).intValue());
      if (localNotification != null) {
        localArrayList.add(localNotification);
      }
    }
    return localArrayList;
  }
  
  private List<Notification> getByType(Notification.Type paramType)
  {
    if (paramType == Notification.Type.ALL) {
      return getAll();
    }
    return getByIds(getIdsByType(paramType));
  }
  
  public static Manager getInstance(Context paramContext)
  {
    return new Manager(paramContext);
  }
  
  private NotificationManagerCompat getNotCompMgr()
  {
    return NotificationManagerCompat.from(this.context);
  }
  
  private NotificationManager getNotMgr()
  {
    return (NotificationManager)this.context.getSystemService("notification");
  }
  
  private SharedPreferences getPrefs()
  {
    return this.context.getSharedPreferences("NOTIFICATION_ID", 0);
  }
  
  public Notification cancel(int paramInt)
  {
    Notification localNotification = get(paramInt);
    if (localNotification != null) {
      localNotification.cancel();
    }
    return localNotification;
  }
  
  public void cancelAll()
  {
    Iterator localIterator = getAll().iterator();
    while (localIterator.hasNext()) {
      ((Notification)localIterator.next()).cancel();
    }
    getNotCompMgr().cancelAll();
    setBadge(0);
  }
  
  public Notification clear(int paramInt)
  {
    Notification localNotification = get(paramInt);
    if (localNotification != null) {
      localNotification.clear();
    }
    return localNotification;
  }
  
  public void clearAll()
  {
    Iterator localIterator = getByType(Notification.Type.TRIGGERED).iterator();
    while (localIterator.hasNext()) {
      ((Notification)localIterator.next()).clear();
    }
    getNotCompMgr().cancelAll();
    setBadge(0);
  }
  
  public Notification get(int paramInt)
  {
    Options localOptions = getOptions(paramInt);
    if (localOptions == null) {
      return null;
    }
    return new Notification(this.context, localOptions);
  }
  
  StatusBarNotification[] getActiveNotifications()
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return getNotMgr().getActiveNotifications();
    }
    return new StatusBarNotification[0];
  }
  
  public List<Notification> getAll()
  {
    return getByIds(getIds());
  }
  
  public List<Integer> getIds()
  {
    Object localObject = getPrefs().getAll().keySet();
    ArrayList localArrayList = new ArrayList();
    localObject = ((Set)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      try
      {
        localArrayList.add(Integer.valueOf(Integer.parseInt(str)));
      }
      catch (NumberFormatException localNumberFormatException)
      {
        ThrowableExtension.printStackTrace(localNumberFormatException);
      }
    }
    return localArrayList;
  }
  
  public List<Integer> getIdsByType(Notification.Type paramType)
  {
    Object localObject;
    if (paramType == Notification.Type.ALL) {
      localObject = getIds();
    }
    ArrayList localArrayList;
    do
    {
      return (List<Integer>)localObject;
      localObject = getActiveNotifications();
      localArrayList = new ArrayList();
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        localArrayList.add(Integer.valueOf(localObject[i].getId()));
        i += 1;
      }
      localObject = localArrayList;
    } while (paramType == Notification.Type.TRIGGERED);
    paramType = getIds();
    paramType.removeAll(localArrayList);
    return paramType;
  }
  
  public Options getOptions(int paramInt)
  {
    Object localObject = getPrefs();
    String str = Integer.toString(paramInt);
    if (!((SharedPreferences)localObject).contains(str)) {
      return null;
    }
    try
    {
      localObject = new JSONObject(((SharedPreferences)localObject).getString(str, null));
      localObject = new Options(this.context, (JSONObject)localObject);
      return (Options)localObject;
    }
    catch (JSONException localJSONException)
    {
      ThrowableExtension.printStackTrace(localJSONException);
    }
    return null;
  }
  
  public List<JSONObject> getOptions()
  {
    return getOptionsById(getIds());
  }
  
  public List<JSONObject> getOptionsById(List<Integer> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Options localOptions = getOptions(((Integer)paramList.next()).intValue());
      if (localOptions != null) {
        localArrayList.add(localOptions.getDict());
      }
    }
    return localArrayList;
  }
  
  public List<JSONObject> getOptionsByType(Notification.Type paramType)
  {
    ArrayList localArrayList = new ArrayList();
    paramType = getByType(paramType).iterator();
    while (paramType.hasNext()) {
      localArrayList.add(((Notification)paramType.next()).getOptions().getDict());
    }
    return localArrayList;
  }
  
  public boolean hasPermission()
  {
    return getNotCompMgr().areNotificationsEnabled();
  }
  
  public Notification schedule(Request paramRequest, Class<?> paramClass)
  {
    Object localObject = paramRequest.getOptions();
    localObject = new Notification(this.context, (Options)localObject);
    ((Notification)localObject).schedule(paramRequest, paramClass);
    return (Notification)localObject;
  }
  
  public void setBadge(int paramInt)
  {
    if (paramInt == 0)
    {
      new BadgeImpl(this.context).clearBadge();
      return;
    }
    new BadgeImpl(this.context).setBadge(paramInt);
  }
  
  public Notification update(int paramInt, JSONObject paramJSONObject, Class<?> paramClass)
  {
    Notification localNotification = get(paramInt);
    if (localNotification == null) {
      return null;
    }
    localNotification.update(paramJSONObject, paramClass);
    return localNotification;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\notification\Manager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */