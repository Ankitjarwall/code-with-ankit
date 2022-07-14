package com.onesignal;

import android.content.Context;
import android.os.Bundle;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicLong;

class TrackFirebaseAnalytics
{
  private static final String EVENT_NOTIFICATION_INFLUENCE_OPEN = "os_notification_influence_open";
  private static final String EVENT_NOTIFICATION_OPENED = "os_notification_opened";
  private static final String EVENT_NOTIFICATION_RECEIVED = "os_notification_received";
  private static Class<?> FirebaseAnalyticsClass;
  private static AtomicLong lastOpenedTime;
  private static OSNotificationPayload lastReceivedPayload;
  private static AtomicLong lastReceivedTime;
  private Context appContext;
  private Object mFirebaseAnalyticsInstance;
  
  TrackFirebaseAnalytics(Context paramContext)
  {
    this.appContext = paramContext;
  }
  
  static boolean CanTrack()
  {
    try
    {
      FirebaseAnalyticsClass = Class.forName("com.google.firebase.analytics.FirebaseAnalytics");
      return true;
    }
    catch (Throwable localThrowable) {}
    return false;
  }
  
  private String getCampaignNameFromPayload(OSNotificationPayload paramOSNotificationPayload)
  {
    if ((!paramOSNotificationPayload.templateName.isEmpty()) && (!paramOSNotificationPayload.templateId.isEmpty())) {
      return paramOSNotificationPayload.templateName + " - " + paramOSNotificationPayload.templateId;
    }
    if (paramOSNotificationPayload.title != null) {
      return paramOSNotificationPayload.title.substring(0, Math.min(10, paramOSNotificationPayload.title.length()));
    }
    return "";
  }
  
  private Object getFirebaseAnalyticsInstance(Context paramContext)
  {
    Method localMethod;
    if (this.mFirebaseAnalyticsInstance == null) {
      localMethod = getInstanceMethod(FirebaseAnalyticsClass);
    }
    try
    {
      this.mFirebaseAnalyticsInstance = localMethod.invoke(null, new Object[] { paramContext });
      return this.mFirebaseAnalyticsInstance;
    }
    catch (Throwable paramContext)
    {
      ThrowableExtension.printStackTrace(paramContext);
    }
    return null;
  }
  
  private static Method getInstanceMethod(Class paramClass)
  {
    try
    {
      paramClass = paramClass.getMethod("getInstance", new Class[] { Context.class });
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
      ThrowableExtension.printStackTrace(paramClass);
    }
    return null;
  }
  
  private static Method getTrackMethod(Class paramClass)
  {
    try
    {
      paramClass = paramClass.getMethod("logEvent", new Class[] { String.class, Bundle.class });
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
      ThrowableExtension.printStackTrace(paramClass);
    }
    return null;
  }
  
  void trackInfluenceOpenEvent()
  {
    if ((lastReceivedTime == null) || (lastReceivedPayload == null)) {}
    long l;
    do
    {
      return;
      l = System.currentTimeMillis();
    } while ((l - lastReceivedTime.get() > 120000L) || ((lastOpenedTime != null) && (l - lastOpenedTime.get() < 30000L)));
    try
    {
      Object localObject = getFirebaseAnalyticsInstance(this.appContext);
      Method localMethod = getTrackMethod(FirebaseAnalyticsClass);
      Bundle localBundle = new Bundle();
      localBundle.putString("source", "OneSignal");
      localBundle.putString("medium", "notification");
      localBundle.putString("notification_id", lastReceivedPayload.notificationID);
      localBundle.putString("campaign", getCampaignNameFromPayload(lastReceivedPayload));
      localMethod.invoke(localObject, new Object[] { "os_notification_influence_open", localBundle });
      return;
    }
    catch (Throwable localThrowable)
    {
      ThrowableExtension.printStackTrace(localThrowable);
    }
  }
  
  void trackOpenedEvent(OSNotificationOpenResult paramOSNotificationOpenResult)
  {
    if (lastOpenedTime == null) {
      lastOpenedTime = new AtomicLong();
    }
    lastOpenedTime.set(System.currentTimeMillis());
    try
    {
      Object localObject = getFirebaseAnalyticsInstance(this.appContext);
      Method localMethod = getTrackMethod(FirebaseAnalyticsClass);
      Bundle localBundle = new Bundle();
      localBundle.putString("source", "OneSignal");
      localBundle.putString("medium", "notification");
      localBundle.putString("notification_id", paramOSNotificationOpenResult.notification.payload.notificationID);
      localBundle.putString("campaign", getCampaignNameFromPayload(paramOSNotificationOpenResult.notification.payload));
      localMethod.invoke(localObject, new Object[] { "os_notification_opened", localBundle });
      return;
    }
    catch (Throwable paramOSNotificationOpenResult)
    {
      ThrowableExtension.printStackTrace(paramOSNotificationOpenResult);
    }
  }
  
  void trackReceivedEvent(OSNotificationOpenResult paramOSNotificationOpenResult)
  {
    try
    {
      Object localObject = getFirebaseAnalyticsInstance(this.appContext);
      Method localMethod = getTrackMethod(FirebaseAnalyticsClass);
      Bundle localBundle = new Bundle();
      localBundle.putString("source", "OneSignal");
      localBundle.putString("medium", "notification");
      localBundle.putString("notification_id", paramOSNotificationOpenResult.notification.payload.notificationID);
      localBundle.putString("campaign", getCampaignNameFromPayload(paramOSNotificationOpenResult.notification.payload));
      localMethod.invoke(localObject, new Object[] { "os_notification_received", localBundle });
      if (lastReceivedTime == null) {
        lastReceivedTime = new AtomicLong();
      }
      lastReceivedTime.set(System.currentTimeMillis());
      lastReceivedPayload = paramOSNotificationOpenResult.notification.payload;
      return;
    }
    catch (Throwable paramOSNotificationOpenResult)
    {
      ThrowableExtension.printStackTrace(paramOSNotificationOpenResult);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\TrackFirebaseAnalytics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */