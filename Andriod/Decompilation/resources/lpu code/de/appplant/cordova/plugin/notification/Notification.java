package de.appplant.cordova.plugin.notification;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.util.ArraySet;
import android.support.v4.util.Pair;
import android.util.SparseArray;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public final class Notification
{
  public static final String EXTRA_ID = "NOTIFICATION_ID";
  public static final String EXTRA_UPDATE = "NOTIFICATION_UPDATE";
  static final String PREF_KEY_ID = "NOTIFICATION_ID";
  private static final String PREF_KEY_PID = "NOTIFICATION_PID";
  private static SparseArray<NotificationCompat.Builder> cache = null;
  private final NotificationCompat.Builder builder;
  private final Context context;
  private final Options options;
  
  public Notification(Context paramContext, Options paramOptions)
  {
    this.context = paramContext;
    this.options = paramOptions;
    this.builder = null;
  }
  
  Notification(Context paramContext, Options paramOptions, NotificationCompat.Builder paramBuilder)
  {
    this.context = paramContext;
    this.options = paramOptions;
    this.builder = paramBuilder;
  }
  
  private void cacheBuilder()
  {
    if (cache == null) {
      cache = new SparseArray();
    }
    cache.put(getId(), this.builder);
  }
  
  private void cancelScheduledAlarms()
  {
    Object localObject1 = getPrefs("NOTIFICATION_PID").getStringSet(this.options.getIdentifier(), null);
    if (localObject1 == null) {}
    for (;;)
    {
      return;
      localObject1 = ((Set)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = new Intent((String)((Iterator)localObject1).next());
        localObject2 = PendingIntent.getBroadcast(this.context, 0, (Intent)localObject2, 0);
        if (localObject2 != null) {
          getAlarmMgr().cancel((PendingIntent)localObject2);
        }
      }
    }
  }
  
  private void clearCache()
  {
    if (cache != null) {
      cache.delete(getId());
    }
  }
  
  private AlarmManager getAlarmMgr()
  {
    return (AlarmManager)this.context.getSystemService("alarm");
  }
  
  static NotificationCompat.Builder getCachedBuilder(int paramInt)
  {
    if (cache != null) {
      return (NotificationCompat.Builder)cache.get(paramInt);
    }
    return null;
  }
  
  private NotificationManager getNotMgr()
  {
    return (NotificationManager)this.context.getSystemService("notification");
  }
  
  private SharedPreferences getPrefs(String paramString)
  {
    return this.context.getSharedPreferences(paramString, 0);
  }
  
  private void grantPermissionToPlaySoundFromExternal()
  {
    if (this.builder == null) {
      return;
    }
    Uri localUri = Uri.parse(this.builder.getExtras().getString("NOTIFICATION_SOUND"));
    this.context.grantUriPermission("com.android.systemui", localUri, 1);
  }
  
  private boolean isRepeating()
  {
    return getOptions().getTrigger().has("every");
  }
  
  private void mergeJSONObjects(JSONObject paramJSONObject)
  {
    JSONObject localJSONObject = this.options.getDict();
    Iterator localIterator = paramJSONObject.keys();
    while (localIterator.hasNext()) {
      try
      {
        String str = (String)localIterator.next();
        localJSONObject.put(str, paramJSONObject.opt(str));
      }
      catch (JSONException localJSONException)
      {
        ThrowableExtension.printStackTrace(localJSONException);
      }
    }
  }
  
  private void persist(Set<String> paramSet)
  {
    String str = this.options.getIdentifier();
    SharedPreferences.Editor localEditor = getPrefs("NOTIFICATION_ID").edit();
    localEditor.putString(str, this.options.toString());
    localEditor.apply();
    if (paramSet == null) {
      return;
    }
    localEditor = getPrefs("NOTIFICATION_PID").edit();
    localEditor.putStringSet(str, paramSet);
    localEditor.apply();
  }
  
  private boolean trigger(Intent paramIntent, Class<?> paramClass)
  {
    try
    {
      paramClass = (BroadcastReceiver)paramClass.newInstance();
      paramClass.onReceive(this.context, paramIntent);
      return true;
    }
    catch (InstantiationException paramIntent)
    {
      return false;
    }
    catch (IllegalAccessException paramIntent) {}
    return false;
  }
  
  private void unpersist()
  {
    int i = 0;
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "NOTIFICATION_ID";
    arrayOfString[1] = "NOTIFICATION_PID";
    String str = this.options.getIdentifier();
    int j = arrayOfString.length;
    while (i < j)
    {
      SharedPreferences.Editor localEditor = getPrefs(arrayOfString[i]).edit();
      localEditor.remove(str);
      localEditor.apply();
      i += 1;
    }
  }
  
  public void cancel()
  {
    cancelScheduledAlarms();
    unpersist();
    getNotMgr().cancel(getId());
    clearCache();
  }
  
  public void clear()
  {
    getNotMgr().cancel(getId());
    if (isRepeating()) {
      return;
    }
    unpersist();
  }
  
  public Context getContext()
  {
    return this.context;
  }
  
  public int getId()
  {
    return this.options.getId().intValue();
  }
  
  public Options getOptions()
  {
    return this.options;
  }
  
  public Type getType()
  {
    StatusBarNotification[] arrayOfStatusBarNotification = Manager.getInstance(this.context).getActiveNotifications();
    int j = getId();
    int k = arrayOfStatusBarNotification.length;
    int i = 0;
    while (i < k)
    {
      if (arrayOfStatusBarNotification[i].getId() == j) {
        return Type.TRIGGERED;
      }
      i += 1;
    }
    return Type.SCHEDULED;
  }
  
  void schedule(Request paramRequest, Class<?> paramClass)
  {
    Object localObject1 = new ArrayList();
    Object localObject2 = new ArraySet();
    AlarmManager localAlarmManager = getAlarmMgr();
    cancelScheduledAlarms();
    for (;;)
    {
      Date localDate = paramRequest.getTriggerDate();
      if (localDate == null) {}
      while (!paramRequest.moveNext())
      {
        if (!((List)localObject1).isEmpty()) {
          break label157;
        }
        unpersist();
        return;
        break label214;
        label60:
        Intent localIntent = new Intent(this.context, paramClass).setAction("NOTIFICATION_ID" + paramRequest.getIdentifier()).putExtra("NOTIFICATION_ID", this.options.getId()).putExtra("NOTIFICATION_OCCURRENCE", paramRequest.getOccurrence());
        ((Set)localObject2).add(localIntent.getAction());
        ((List)localObject1).add(new Pair(localDate, localIntent));
      }
    }
    label157:
    persist((Set)localObject2);
    if (!this.options.isInfiniteTrigger()) {
      ((Intent)((Pair)((List)localObject1).get(((List)localObject1).size() - 1)).second).putExtra("NOTIFICATION_LAST", true);
    }
    paramRequest = ((List)localObject1).iterator();
    for (;;)
    {
      label214:
      if (!paramRequest.hasNext()) {
        break label60;
      }
      localObject2 = (Pair)paramRequest.next();
      localObject1 = (Date)((Pair)localObject2).first;
      long l = ((Date)localObject1).getTime();
      localObject2 = (Intent)((Pair)localObject2).second;
      if ((!((Date)localObject1).after(new Date())) && (trigger((Intent)localObject2, paramClass))) {
        break;
      }
      localObject1 = PendingIntent.getBroadcast(this.context, 0, (Intent)localObject2, 268435456);
      try
      {
        switch (this.options.getPriority())
        {
        }
        for (;;)
        {
          localAlarmManager.setExact(0, l, (PendingIntent)localObject1);
          break;
          localAlarmManager.setExact(1, l, (PendingIntent)localObject1);
          break;
          if (Build.VERSION.SDK_INT >= 23)
          {
            localAlarmManager.setExactAndAllowWhileIdle(0, l, (PendingIntent)localObject1);
            break;
          }
          localAlarmManager.setExact(1, l, (PendingIntent)localObject1);
          break;
        }
      }
      catch (Exception localException) {}
    }
  }
  
  public void show()
  {
    if (this.builder == null) {
      return;
    }
    if (this.options.isWithProgressBar()) {
      cacheBuilder();
    }
    grantPermissionToPlaySoundFromExternal();
    getNotMgr().notify(getId(), this.builder.build());
  }
  
  public String toString()
  {
    JSONObject localJSONObject2 = this.options.getDict();
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      localJSONObject2 = new JSONObject(localJSONObject2.toString());
      localJSONObject1 = localJSONObject2;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        ThrowableExtension.printStackTrace(localJSONException);
      }
    }
    return localJSONObject1.toString();
  }
  
  void update(JSONObject paramJSONObject, Class<?> paramClass)
  {
    mergeJSONObjects(paramJSONObject);
    persist(null);
    if (getType() != Type.TRIGGERED) {
      return;
    }
    trigger(new Intent(this.context, paramClass).setAction("NOTIFICATION_ID" + this.options.getId()).putExtra("NOTIFICATION_ID", this.options.getId()).putExtra("NOTIFICATION_UPDATE", true), paramClass);
  }
  
  public static enum Type
  {
    ALL,  SCHEDULED,  TRIGGERED;
    
    private Type() {}
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\notification\Notification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */