package android.support.v4.app;

import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings.Secure;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class NotificationManagerCompat
{
  public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
  private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
  public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
  public static final int IMPORTANCE_DEFAULT = 3;
  public static final int IMPORTANCE_HIGH = 4;
  public static final int IMPORTANCE_LOW = 2;
  public static final int IMPORTANCE_MAX = 5;
  public static final int IMPORTANCE_MIN = 1;
  public static final int IMPORTANCE_NONE = 0;
  public static final int IMPORTANCE_UNSPECIFIED = -1000;
  static final int MAX_SIDE_CHANNEL_SDK_VERSION = 19;
  private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
  private static final String SETTING_ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
  private static final int SIDE_CHANNEL_RETRY_BASE_INTERVAL_MS = 1000;
  private static final int SIDE_CHANNEL_RETRY_MAX_COUNT = 6;
  private static final String TAG = "NotifManCompat";
  @GuardedBy("sEnabledNotificationListenersLock")
  private static Set<String> sEnabledNotificationListenerPackages = new HashSet();
  @GuardedBy("sEnabledNotificationListenersLock")
  private static String sEnabledNotificationListeners;
  private static final Object sEnabledNotificationListenersLock = new Object();
  private static final Object sLock = new Object();
  @GuardedBy("sLock")
  private static SideChannelManager sSideChannelManager;
  private final Context mContext;
  private final NotificationManager mNotificationManager;
  
  private NotificationManagerCompat(Context paramContext)
  {
    this.mContext = paramContext;
    this.mNotificationManager = ((NotificationManager)this.mContext.getSystemService("notification"));
  }
  
  @NonNull
  public static NotificationManagerCompat from(@NonNull Context paramContext)
  {
    return new NotificationManagerCompat(paramContext);
  }
  
  @NonNull
  public static Set<String> getEnabledListenerPackages(@NonNull Context paramContext)
  {
    Object localObject1 = Settings.Secure.getString(paramContext.getContentResolver(), "enabled_notification_listeners");
    paramContext = sEnabledNotificationListenersLock;
    if (localObject1 != null) {}
    for (;;)
    {
      int i;
      try
      {
        if (!((String)localObject1).equals(sEnabledNotificationListeners))
        {
          String[] arrayOfString = ((String)localObject1).split(":");
          HashSet localHashSet = new HashSet(arrayOfString.length);
          int j = arrayOfString.length;
          i = 0;
          if (i < j)
          {
            ComponentName localComponentName = ComponentName.unflattenFromString(arrayOfString[i]);
            if (localComponentName != null) {
              localHashSet.add(localComponentName.getPackageName());
            }
          }
          else
          {
            sEnabledNotificationListenerPackages = localHashSet;
            sEnabledNotificationListeners = (String)localObject1;
          }
        }
        else
        {
          localObject1 = sEnabledNotificationListenerPackages;
          return (Set<String>)localObject1;
        }
      }
      finally {}
      i += 1;
    }
  }
  
  private void pushSideChannelQueue(Task paramTask)
  {
    synchronized (sLock)
    {
      if (sSideChannelManager == null) {
        sSideChannelManager = new SideChannelManager(this.mContext.getApplicationContext());
      }
      sSideChannelManager.queueTask(paramTask);
      return;
    }
  }
  
  private static boolean useSideChannelForNotification(Notification paramNotification)
  {
    paramNotification = NotificationCompat.getExtras(paramNotification);
    return (paramNotification != null) && (paramNotification.getBoolean("android.support.useSideChannel"));
  }
  
  public boolean areNotificationsEnabled()
  {
    boolean bool = true;
    if (Build.VERSION.SDK_INT >= 24) {
      bool = this.mNotificationManager.areNotificationsEnabled();
    }
    while (Build.VERSION.SDK_INT < 19) {
      return bool;
    }
    AppOpsManager localAppOpsManager = (AppOpsManager)this.mContext.getSystemService("appops");
    Object localObject = this.mContext.getApplicationInfo();
    String str = this.mContext.getApplicationContext().getPackageName();
    int i = ((ApplicationInfo)localObject).uid;
    try
    {
      localObject = Class.forName(AppOpsManager.class.getName());
      i = ((Integer)((Class)localObject).getMethod("checkOpNoThrow", new Class[] { Integer.TYPE, Integer.TYPE, String.class }).invoke(localAppOpsManager, new Object[] { Integer.valueOf(((Integer)((Class)localObject).getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), str })).intValue();
      if (i == 0) {}
      for (bool = true;; bool = false) {
        return bool;
      }
      return true;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      return true;
    }
    catch (RuntimeException localRuntimeException)
    {
      return true;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      return true;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      return true;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      return true;
    }
    catch (InvocationTargetException localInvocationTargetException) {}
  }
  
  public void cancel(int paramInt)
  {
    cancel(null, paramInt);
  }
  
  public void cancel(@Nullable String paramString, int paramInt)
  {
    this.mNotificationManager.cancel(paramString, paramInt);
    if (Build.VERSION.SDK_INT <= 19) {
      pushSideChannelQueue(new CancelTask(this.mContext.getPackageName(), paramInt, paramString));
    }
  }
  
  public void cancelAll()
  {
    this.mNotificationManager.cancelAll();
    if (Build.VERSION.SDK_INT <= 19) {
      pushSideChannelQueue(new CancelTask(this.mContext.getPackageName()));
    }
  }
  
  public int getImportance()
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return this.mNotificationManager.getImportance();
    }
    return 64536;
  }
  
  public void notify(int paramInt, @NonNull Notification paramNotification)
  {
    notify(null, paramInt, paramNotification);
  }
  
  public void notify(@Nullable String paramString, int paramInt, @NonNull Notification paramNotification)
  {
    if (useSideChannelForNotification(paramNotification))
    {
      pushSideChannelQueue(new NotifyTask(this.mContext.getPackageName(), paramInt, paramString, paramNotification));
      this.mNotificationManager.cancel(paramString, paramInt);
      return;
    }
    this.mNotificationManager.notify(paramString, paramInt, paramNotification);
  }
  
  private static class CancelTask
    implements NotificationManagerCompat.Task
  {
    final boolean all;
    final int id;
    final String packageName;
    final String tag;
    
    CancelTask(String paramString)
    {
      this.packageName = paramString;
      this.id = 0;
      this.tag = null;
      this.all = true;
    }
    
    CancelTask(String paramString1, int paramInt, String paramString2)
    {
      this.packageName = paramString1;
      this.id = paramInt;
      this.tag = paramString2;
      this.all = false;
    }
    
    public void send(INotificationSideChannel paramINotificationSideChannel)
      throws RemoteException
    {
      if (this.all)
      {
        paramINotificationSideChannel.cancelAll(this.packageName);
        return;
      }
      paramINotificationSideChannel.cancel(this.packageName, this.id, this.tag);
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder("CancelTask[");
      localStringBuilder.append("packageName:").append(this.packageName);
      localStringBuilder.append(", id:").append(this.id);
      localStringBuilder.append(", tag:").append(this.tag);
      localStringBuilder.append(", all:").append(this.all);
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }
  
  private static class NotifyTask
    implements NotificationManagerCompat.Task
  {
    final int id;
    final Notification notif;
    final String packageName;
    final String tag;
    
    NotifyTask(String paramString1, int paramInt, String paramString2, Notification paramNotification)
    {
      this.packageName = paramString1;
      this.id = paramInt;
      this.tag = paramString2;
      this.notif = paramNotification;
    }
    
    public void send(INotificationSideChannel paramINotificationSideChannel)
      throws RemoteException
    {
      paramINotificationSideChannel.notify(this.packageName, this.id, this.tag, this.notif);
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder("NotifyTask[");
      localStringBuilder.append("packageName:").append(this.packageName);
      localStringBuilder.append(", id:").append(this.id);
      localStringBuilder.append(", tag:").append(this.tag);
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }
  
  private static class ServiceConnectedEvent
  {
    final ComponentName componentName;
    final IBinder iBinder;
    
    ServiceConnectedEvent(ComponentName paramComponentName, IBinder paramIBinder)
    {
      this.componentName = paramComponentName;
      this.iBinder = paramIBinder;
    }
  }
  
  private static class SideChannelManager
    implements Handler.Callback, ServiceConnection
  {
    private static final int MSG_QUEUE_TASK = 0;
    private static final int MSG_RETRY_LISTENER_QUEUE = 3;
    private static final int MSG_SERVICE_CONNECTED = 1;
    private static final int MSG_SERVICE_DISCONNECTED = 2;
    private Set<String> mCachedEnabledPackages = new HashSet();
    private final Context mContext;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;
    private final Map<ComponentName, ListenerRecord> mRecordMap = new HashMap();
    
    SideChannelManager(Context paramContext)
    {
      this.mContext = paramContext;
      this.mHandlerThread = new HandlerThread("NotificationManagerCompat");
      this.mHandlerThread.start();
      this.mHandler = new Handler(this.mHandlerThread.getLooper(), this);
    }
    
    private boolean ensureServiceBound(ListenerRecord paramListenerRecord)
    {
      if (paramListenerRecord.bound) {
        return true;
      }
      Intent localIntent = new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(paramListenerRecord.componentName);
      paramListenerRecord.bound = this.mContext.bindService(localIntent, this, 33);
      if (paramListenerRecord.bound) {
        paramListenerRecord.retryCount = 0;
      }
      for (;;)
      {
        return paramListenerRecord.bound;
        Log.w("NotifManCompat", "Unable to bind to listener " + paramListenerRecord.componentName);
        this.mContext.unbindService(this);
      }
    }
    
    private void ensureServiceUnbound(ListenerRecord paramListenerRecord)
    {
      if (paramListenerRecord.bound)
      {
        this.mContext.unbindService(this);
        paramListenerRecord.bound = false;
      }
      paramListenerRecord.service = null;
    }
    
    private void handleQueueTask(NotificationManagerCompat.Task paramTask)
    {
      updateListenerMap();
      Iterator localIterator = this.mRecordMap.values().iterator();
      while (localIterator.hasNext())
      {
        ListenerRecord localListenerRecord = (ListenerRecord)localIterator.next();
        localListenerRecord.taskQueue.add(paramTask);
        processListenerQueue(localListenerRecord);
      }
    }
    
    private void handleRetryListenerQueue(ComponentName paramComponentName)
    {
      paramComponentName = (ListenerRecord)this.mRecordMap.get(paramComponentName);
      if (paramComponentName != null) {
        processListenerQueue(paramComponentName);
      }
    }
    
    private void handleServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      paramComponentName = (ListenerRecord)this.mRecordMap.get(paramComponentName);
      if (paramComponentName != null)
      {
        paramComponentName.service = INotificationSideChannel.Stub.asInterface(paramIBinder);
        paramComponentName.retryCount = 0;
        processListenerQueue(paramComponentName);
      }
    }
    
    private void handleServiceDisconnected(ComponentName paramComponentName)
    {
      paramComponentName = (ListenerRecord)this.mRecordMap.get(paramComponentName);
      if (paramComponentName != null) {
        ensureServiceUnbound(paramComponentName);
      }
    }
    
    private void processListenerQueue(ListenerRecord paramListenerRecord)
    {
      if (Log.isLoggable("NotifManCompat", 3)) {
        Log.d("NotifManCompat", "Processing component " + paramListenerRecord.componentName + ", " + paramListenerRecord.taskQueue.size() + " queued tasks");
      }
      if (paramListenerRecord.taskQueue.isEmpty()) {}
      do
      {
        return;
        if ((!ensureServiceBound(paramListenerRecord)) || (paramListenerRecord.service == null))
        {
          scheduleListenerRetry(paramListenerRecord);
          return;
        }
        try
        {
          Object localObject;
          do
          {
            if (Log.isLoggable("NotifManCompat", 3)) {
              Log.d("NotifManCompat", "Sending task " + localObject);
            }
            ((NotificationManagerCompat.Task)localObject).send(paramListenerRecord.service);
            paramListenerRecord.taskQueue.remove();
            localObject = (NotificationManagerCompat.Task)paramListenerRecord.taskQueue.peek();
          } while (localObject != null);
        }
        catch (DeadObjectException localDeadObjectException)
        {
          for (;;)
          {
            if (Log.isLoggable("NotifManCompat", 3)) {
              Log.d("NotifManCompat", "Remote service has died: " + paramListenerRecord.componentName);
            }
          }
        }
        catch (RemoteException localRemoteException)
        {
          for (;;)
          {
            Log.w("NotifManCompat", "RemoteException communicating with " + paramListenerRecord.componentName, localRemoteException);
          }
        }
      } while (paramListenerRecord.taskQueue.isEmpty());
      scheduleListenerRetry(paramListenerRecord);
    }
    
    private void scheduleListenerRetry(ListenerRecord paramListenerRecord)
    {
      if (this.mHandler.hasMessages(3, paramListenerRecord.componentName)) {
        return;
      }
      paramListenerRecord.retryCount += 1;
      if (paramListenerRecord.retryCount > 6)
      {
        Log.w("NotifManCompat", "Giving up on delivering " + paramListenerRecord.taskQueue.size() + " tasks to " + paramListenerRecord.componentName + " after " + paramListenerRecord.retryCount + " retries");
        paramListenerRecord.taskQueue.clear();
        return;
      }
      int i = (1 << paramListenerRecord.retryCount - 1) * 1000;
      if (Log.isLoggable("NotifManCompat", 3)) {
        Log.d("NotifManCompat", "Scheduling retry for " + i + " ms");
      }
      paramListenerRecord = this.mHandler.obtainMessage(3, paramListenerRecord.componentName);
      this.mHandler.sendMessageDelayed(paramListenerRecord, i);
    }
    
    private void updateListenerMap()
    {
      Object localObject1 = NotificationManagerCompat.getEnabledListenerPackages(this.mContext);
      if (((Set)localObject1).equals(this.mCachedEnabledPackages)) {}
      for (;;)
      {
        return;
        this.mCachedEnabledPackages = ((Set)localObject1);
        Object localObject2 = this.mContext.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
        HashSet localHashSet = new HashSet();
        localObject2 = ((List)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject2).next();
          if (((Set)localObject1).contains(localResolveInfo.serviceInfo.packageName))
          {
            ComponentName localComponentName = new ComponentName(localResolveInfo.serviceInfo.packageName, localResolveInfo.serviceInfo.name);
            if (localResolveInfo.serviceInfo.permission != null) {
              Log.w("NotifManCompat", "Permission present on component " + localComponentName + ", not adding listener record.");
            } else {
              localHashSet.add(localComponentName);
            }
          }
        }
        localObject1 = localHashSet.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (ComponentName)((Iterator)localObject1).next();
          if (!this.mRecordMap.containsKey(localObject2))
          {
            if (Log.isLoggable("NotifManCompat", 3)) {
              Log.d("NotifManCompat", "Adding listener record for " + localObject2);
            }
            this.mRecordMap.put(localObject2, new ListenerRecord((ComponentName)localObject2));
          }
        }
        localObject1 = this.mRecordMap.entrySet().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Map.Entry)((Iterator)localObject1).next();
          if (!localHashSet.contains(((Map.Entry)localObject2).getKey()))
          {
            if (Log.isLoggable("NotifManCompat", 3)) {
              Log.d("NotifManCompat", "Removing listener record for " + ((Map.Entry)localObject2).getKey());
            }
            ensureServiceUnbound((ListenerRecord)((Map.Entry)localObject2).getValue());
            ((Iterator)localObject1).remove();
          }
        }
      }
    }
    
    public boolean handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default: 
        return false;
      case 0: 
        handleQueueTask((NotificationManagerCompat.Task)paramMessage.obj);
        return true;
      case 1: 
        paramMessage = (NotificationManagerCompat.ServiceConnectedEvent)paramMessage.obj;
        handleServiceConnected(paramMessage.componentName, paramMessage.iBinder);
        return true;
      case 2: 
        handleServiceDisconnected((ComponentName)paramMessage.obj);
        return true;
      }
      handleRetryListenerQueue((ComponentName)paramMessage.obj);
      return true;
    }
    
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      if (Log.isLoggable("NotifManCompat", 3)) {
        Log.d("NotifManCompat", "Connected to service " + paramComponentName);
      }
      this.mHandler.obtainMessage(1, new NotificationManagerCompat.ServiceConnectedEvent(paramComponentName, paramIBinder)).sendToTarget();
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      if (Log.isLoggable("NotifManCompat", 3)) {
        Log.d("NotifManCompat", "Disconnected from service " + paramComponentName);
      }
      this.mHandler.obtainMessage(2, paramComponentName).sendToTarget();
    }
    
    public void queueTask(NotificationManagerCompat.Task paramTask)
    {
      this.mHandler.obtainMessage(0, paramTask).sendToTarget();
    }
    
    private static class ListenerRecord
    {
      boolean bound = false;
      final ComponentName componentName;
      int retryCount = 0;
      INotificationSideChannel service;
      ArrayDeque<NotificationManagerCompat.Task> taskQueue = new ArrayDeque();
      
      ListenerRecord(ComponentName paramComponentName)
      {
        this.componentName = paramComponentName;
      }
    }
  }
  
  private static abstract interface Task
  {
    public abstract void send(INotificationSideChannel paramINotificationSideChannel)
      throws RemoteException;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\app\NotificationManagerCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */