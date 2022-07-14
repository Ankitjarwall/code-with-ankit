package com.google.firebase.messaging;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.R.string;
import com.google.android.gms.common.util.zzt;
import com.google.firebase.iid.zzg;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

class zza
{
  static zza zzclI;
  private final Context mContext;
  private Bundle zzaDS;
  private Method zzclJ;
  private Method zzclK;
  private final AtomicInteger zzclL = new AtomicInteger((int)SystemClock.elapsedRealtime());
  
  private zza(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
  }
  
  static boolean zzE(Bundle paramBundle)
  {
    return ("1".equals(zzf(paramBundle, "gcm.n.e"))) || (zzf(paramBundle, "gcm.n.icon") != null);
  }
  
  private int zzGP()
  {
    return this.zzclL.incrementAndGet();
  }
  
  private Notification zzH(Bundle paramBundle)
  {
    Object localObject2 = zzk(paramBundle, "gcm.n.title");
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      localObject1 = this.mContext.getApplicationInfo().loadLabel(this.mContext.getPackageManager());
    }
    String str = zzk(paramBundle, "gcm.n.body");
    int i = zzeB(zzf(paramBundle, "gcm.n.icon"));
    Integer localInteger = zzjD(zzf(paramBundle, "gcm.n.color"));
    Uri localUri = zzeC(zzW(paramBundle));
    PendingIntent localPendingIntent2 = zzI(paramBundle);
    PendingIntent localPendingIntent1 = null;
    localObject2 = localPendingIntent2;
    if (FirebaseMessagingService.zzZ(paramBundle))
    {
      localObject2 = zza(paramBundle, localPendingIntent2);
      localPendingIntent1 = zzY(paramBundle);
    }
    if ((zzt.zzzq()) && (this.mContext.getApplicationInfo().targetSdkVersion > 25)) {
      return zza((CharSequence)localObject1, str, i, localInteger, localUri, (PendingIntent)localObject2, localPendingIntent1, zzjE(zzf(paramBundle, "gcm.n.android_channel_id")));
    }
    return zza((CharSequence)localObject1, str, i, localInteger, localUri, (PendingIntent)localObject2, localPendingIntent1);
  }
  
  private PendingIntent zzI(Bundle paramBundle)
  {
    Intent localIntent = zzX(paramBundle);
    if (localIntent == null) {
      return null;
    }
    localIntent.addFlags(67108864);
    paramBundle = new Bundle(paramBundle);
    FirebaseMessagingService.zzD(paramBundle);
    localIntent.putExtras(paramBundle);
    paramBundle = paramBundle.keySet().iterator();
    while (paramBundle.hasNext())
    {
      String str = (String)paramBundle.next();
      if ((str.startsWith("gcm.n.")) || (str.startsWith("gcm.notification."))) {
        localIntent.removeExtra(str);
      }
    }
    return PendingIntent.getActivity(this.mContext, zzGP(), localIntent, 1073741824);
  }
  
  @Nullable
  static Uri zzV(@NonNull Bundle paramBundle)
  {
    String str2 = zzf(paramBundle, "gcm.n.link_android");
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = zzf(paramBundle, "gcm.n.link");
    }
    if (!TextUtils.isEmpty(str1)) {
      return Uri.parse(str1);
    }
    return null;
  }
  
  static String zzW(Bundle paramBundle)
  {
    String str2 = zzf(paramBundle, "gcm.n.sound2");
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = zzf(paramBundle, "gcm.n.sound");
    }
    return str1;
  }
  
  private Intent zzX(Bundle paramBundle)
  {
    Object localObject = zzf(paramBundle, "gcm.n.click_action");
    if (!TextUtils.isEmpty((CharSequence)localObject))
    {
      paramBundle = new Intent((String)localObject);
      paramBundle.setPackage(this.mContext.getPackageName());
      paramBundle.setFlags(268435456);
    }
    do
    {
      return paramBundle;
      paramBundle = zzV(paramBundle);
      if (paramBundle != null)
      {
        localObject = new Intent("android.intent.action.VIEW");
        ((Intent)localObject).setPackage(this.mContext.getPackageName());
        ((Intent)localObject).setData(paramBundle);
        return (Intent)localObject;
      }
      localObject = this.mContext.getPackageManager().getLaunchIntentForPackage(this.mContext.getPackageName());
      paramBundle = (Bundle)localObject;
    } while (localObject != null);
    Log.w("FirebaseMessaging", "No activity found to launch app");
    return (Intent)localObject;
  }
  
  private PendingIntent zzY(Bundle paramBundle)
  {
    Intent localIntent = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
    zza(localIntent, paramBundle);
    return zzg.zzb(this.mContext, zzGP(), localIntent, 1073741824);
  }
  
  private Notification zza(CharSequence paramCharSequence, String paramString, int paramInt, Integer paramInteger, Uri paramUri, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2)
  {
    NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(this.mContext).setAutoCancel(true).setSmallIcon(paramInt);
    if (!TextUtils.isEmpty(paramCharSequence)) {
      localBuilder.setContentTitle(paramCharSequence);
    }
    if (!TextUtils.isEmpty(paramString))
    {
      localBuilder.setContentText(paramString);
      localBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(paramString));
    }
    if (paramInteger != null) {
      localBuilder.setColor(paramInteger.intValue());
    }
    if (paramUri != null) {
      localBuilder.setSound(paramUri);
    }
    if (paramPendingIntent1 != null) {
      localBuilder.setContentIntent(paramPendingIntent1);
    }
    if (paramPendingIntent2 != null) {
      localBuilder.setDeleteIntent(paramPendingIntent2);
    }
    return localBuilder.build();
  }
  
  @TargetApi(26)
  private Notification zza(CharSequence paramCharSequence, String paramString1, int paramInt, Integer paramInteger, Uri paramUri, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2, String paramString2)
  {
    Notification.Builder localBuilder = new Notification.Builder(this.mContext).setAutoCancel(true).setSmallIcon(paramInt);
    if (!TextUtils.isEmpty(paramCharSequence)) {
      localBuilder.setContentTitle(paramCharSequence);
    }
    if (!TextUtils.isEmpty(paramString1))
    {
      localBuilder.setContentText(paramString1);
      localBuilder.setStyle(new Notification.BigTextStyle().bigText(paramString1));
    }
    if (paramInteger != null) {
      localBuilder.setColor(paramInteger.intValue());
    }
    if (paramUri != null) {
      localBuilder.setSound(paramUri);
    }
    if (paramPendingIntent1 != null) {
      localBuilder.setContentIntent(paramPendingIntent1);
    }
    if (paramPendingIntent2 != null) {
      localBuilder.setDeleteIntent(paramPendingIntent2);
    }
    if (paramString2 != null) {}
    try
    {
      if (this.zzclJ == null) {
        this.zzclJ = localBuilder.getClass().getMethod("setChannel", new Class[] { String.class });
      }
      this.zzclJ.invoke(localBuilder, new Object[] { paramString2 });
    }
    catch (NoSuchMethodException paramCharSequence)
    {
      for (;;)
      {
        Log.e("FirebaseMessaging", "Error while setting the notification channel", paramCharSequence);
      }
    }
    catch (IllegalAccessException paramCharSequence)
    {
      for (;;)
      {
        Log.e("FirebaseMessaging", "Error while setting the notification channel", paramCharSequence);
      }
    }
    catch (InvocationTargetException paramCharSequence)
    {
      for (;;)
      {
        Log.e("FirebaseMessaging", "Error while setting the notification channel", paramCharSequence);
      }
    }
    catch (SecurityException paramCharSequence)
    {
      for (;;)
      {
        Log.e("FirebaseMessaging", "Error while setting the notification channel", paramCharSequence);
      }
    }
    catch (IllegalArgumentException paramCharSequence)
    {
      for (;;)
      {
        Log.e("FirebaseMessaging", "Error while setting the notification channel", paramCharSequence);
      }
    }
    catch (LinkageError paramCharSequence)
    {
      for (;;)
      {
        Log.e("FirebaseMessaging", "Error while setting the notification channel", paramCharSequence);
      }
    }
    return localBuilder.build();
  }
  
  private PendingIntent zza(Bundle paramBundle, PendingIntent paramPendingIntent)
  {
    Intent localIntent = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
    zza(localIntent, paramBundle);
    localIntent.putExtra("pending_intent", paramPendingIntent);
    return zzg.zzb(this.mContext, zzGP(), localIntent, 1073741824);
  }
  
  private void zza(Intent paramIntent, Bundle paramBundle)
  {
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if ((str.startsWith("google.c.a.")) || (str.equals("from"))) {
        paramIntent.putExtra(str, paramBundle.getString(str));
      }
    }
  }
  
  private void zza(String paramString, Notification paramNotification)
  {
    if (Log.isLoggable("FirebaseMessaging", 3)) {
      Log.d("FirebaseMessaging", "Showing notification");
    }
    NotificationManager localNotificationManager = (NotificationManager)this.mContext.getSystemService("notification");
    String str = paramString;
    if (TextUtils.isEmpty(paramString))
    {
      long l = SystemClock.uptimeMillis();
      str = 37 + "FCM-Notification:" + l;
    }
    localNotificationManager.notify(str, 0, paramNotification);
  }
  
  private boolean zzacc()
  {
    if (((KeyguardManager)this.mContext.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {}
    int i;
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      Object localObject;
      while (!((Iterator)localObject).hasNext())
      {
        do
        {
          return false;
          if (!zzt.zzzn()) {
            SystemClock.sleep(10L);
          }
          i = Process.myPid();
          localObject = ((ActivityManager)this.mContext.getSystemService("activity")).getRunningAppProcesses();
        } while (localObject == null);
        localObject = ((List)localObject).iterator();
      }
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
    } while (localRunningAppProcessInfo.pid != i);
    if (localRunningAppProcessInfo.importance == 100) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private Bundle zzacd()
  {
    if (this.zzaDS != null) {
      return this.zzaDS;
    }
    Object localObject = null;
    try
    {
      ApplicationInfo localApplicationInfo = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 128);
      localObject = localApplicationInfo;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    if ((localObject != null) && (((ApplicationInfo)localObject).metaData != null))
    {
      this.zzaDS = ((ApplicationInfo)localObject).metaData;
      return this.zzaDS;
    }
    return Bundle.EMPTY;
  }
  
  static zza zzcy(Context paramContext)
  {
    try
    {
      if (zzclI == null) {
        zzclI = new zza(paramContext);
      }
      paramContext = zzclI;
      return paramContext;
    }
    finally {}
  }
  
  private static String zzeA(String paramString)
  {
    return paramString.substring("gcm.n.".length());
  }
  
  private int zzeB(String paramString)
  {
    Resources localResources;
    int i;
    if (!TextUtils.isEmpty(paramString))
    {
      localResources = this.mContext.getResources();
      i = localResources.getIdentifier(paramString, "drawable", this.mContext.getPackageName());
      if (i == 0) {}
    }
    int j;
    do
    {
      do
      {
        return i;
        j = localResources.getIdentifier(paramString, "mipmap", this.mContext.getPackageName());
        i = j;
      } while (j != 0);
      Log.w("FirebaseMessaging", String.valueOf(paramString).length() + 61 + "Icon resource " + paramString + " not found. Notification will use default icon.");
      i = zzacd().getInt("com.google.firebase.messaging.default_notification_icon", 0);
      j = i;
      if (i == 0) {
        j = this.mContext.getApplicationInfo().icon;
      }
      i = j;
    } while (j != 0);
    return 17301651;
  }
  
  private Uri zzeC(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    if ((!"default".equals(paramString)) && (this.mContext.getResources().getIdentifier(paramString, "raw", this.mContext.getPackageName()) != 0))
    {
      String str1 = String.valueOf("android.resource://");
      String str2 = String.valueOf(this.mContext.getPackageName());
      return Uri.parse(String.valueOf(str1).length() + 5 + String.valueOf(str2).length() + String.valueOf(paramString).length() + str1 + str2 + "/raw/" + paramString);
    }
    return RingtoneManager.getDefaultUri(2);
  }
  
  static String zzf(Bundle paramBundle, String paramString)
  {
    String str2 = paramBundle.getString(paramString);
    String str1 = str2;
    if (str2 == null) {
      str1 = paramBundle.getString(paramString.replace("gcm.n.", "gcm.notification."));
    }
    return str1;
  }
  
  static String zzi(Bundle paramBundle, String paramString)
  {
    paramString = String.valueOf(paramString);
    String str = String.valueOf("_loc_key");
    if (str.length() != 0) {}
    for (paramString = paramString.concat(str);; paramString = new String(paramString)) {
      return zzf(paramBundle, paramString);
    }
  }
  
  static Object[] zzj(Bundle paramBundle, String paramString)
  {
    Object localObject = String.valueOf(paramString);
    String str = String.valueOf("_loc_args");
    if (str.length() != 0)
    {
      localObject = ((String)localObject).concat(str);
      str = zzf(paramBundle, (String)localObject);
      if (!TextUtils.isEmpty(str)) {
        break label59;
      }
      paramBundle = null;
    }
    for (;;)
    {
      return paramBundle;
      localObject = new String((String)localObject);
      break;
      try
      {
        label59:
        JSONArray localJSONArray = new JSONArray(str);
        localObject = new String[localJSONArray.length()];
        int i = 0;
        for (;;)
        {
          paramBundle = (Bundle)localObject;
          if (i >= localObject.length) {
            break;
          }
          localObject[i] = localJSONArray.opt(i);
          i += 1;
        }
        paramBundle = paramBundle.concat(paramString);
      }
      catch (JSONException paramBundle)
      {
        paramBundle = String.valueOf(paramString);
        paramString = String.valueOf("_loc_args");
        if (paramString.length() == 0) {}
      }
    }
    for (;;)
    {
      paramBundle = String.valueOf(zzeA(paramBundle));
      Log.w("FirebaseMessaging", String.valueOf(paramBundle).length() + 41 + String.valueOf(str).length() + "Malformed " + paramBundle + ": " + str + "  Default value will be used.");
      return null;
      paramBundle = new String(paramBundle);
    }
  }
  
  private Integer zzjD(String paramString)
  {
    if (Build.VERSION.SDK_INT < 21) {}
    int i;
    do
    {
      return null;
      if (!TextUtils.isEmpty(paramString)) {
        try
        {
          i = Color.parseColor(paramString);
          return Integer.valueOf(i);
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          Log.w("FirebaseMessaging", String.valueOf(paramString).length() + 54 + "Color " + paramString + " not valid. Notification will use default color.");
        }
      }
      i = zzacd().getInt("com.google.firebase.messaging.default_notification_color", 0);
    } while (i == 0);
    try
    {
      i = ContextCompat.getColor(this.mContext, i);
      return Integer.valueOf(i);
    }
    catch (Resources.NotFoundException paramString)
    {
      Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
    }
    return null;
  }
  
  @TargetApi(26)
  private String zzjE(String paramString)
  {
    Object localObject;
    if (!zzt.zzzq()) {
      localObject = null;
    }
    for (;;)
    {
      return (String)localObject;
      NotificationManager localNotificationManager = (NotificationManager)this.mContext.getSystemService(NotificationManager.class);
      try
      {
        if (this.zzclK == null) {
          this.zzclK = localNotificationManager.getClass().getMethod("getNotificationChannel", new Class[] { String.class });
        }
        if (!TextUtils.isEmpty(paramString))
        {
          localObject = paramString;
          if (this.zzclK.invoke(localNotificationManager, new Object[] { paramString }) != null) {
            continue;
          }
          Log.w("FirebaseMessaging", String.valueOf(paramString).length() + 122 + "Notification Channel requested (" + paramString + ") has not been created by the app. Manifest configuration, or default, value will be used.");
        }
        paramString = zzacd().getString("com.google.firebase.messaging.default_notification_channel_id");
        if (!TextUtils.isEmpty(paramString))
        {
          localObject = paramString;
          if (this.zzclK.invoke(localNotificationManager, new Object[] { paramString }) != null) {
            continue;
          }
          Log.w("FirebaseMessaging", "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
        }
        while (this.zzclK.invoke(localNotificationManager, new Object[] { "fcm_fallback_notification_channel" }) == null)
        {
          paramString = Class.forName("android.app.NotificationChannel");
          localObject = paramString.getConstructor(new Class[] { String.class, CharSequence.class, Integer.TYPE }).newInstance(new Object[] { "fcm_fallback_notification_channel", this.mContext.getString(R.string.fcm_fallback_notification_channel_label), Integer.valueOf(3) });
          localNotificationManager.getClass().getMethod("createNotificationChannel", new Class[] { paramString }).invoke(localNotificationManager, new Object[] { localObject });
          break;
          Log.w("FirebaseMessaging", "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
        }
        return "fcm_fallback_notification_channel";
      }
      catch (InstantiationException paramString)
      {
        Log.e("FirebaseMessaging", "Error while setting the notification channel", paramString);
        return null;
      }
      catch (InvocationTargetException paramString)
      {
        for (;;)
        {
          Log.e("FirebaseMessaging", "Error while setting the notification channel", paramString);
        }
      }
      catch (NoSuchMethodException paramString)
      {
        for (;;)
        {
          Log.e("FirebaseMessaging", "Error while setting the notification channel", paramString);
        }
      }
      catch (IllegalAccessException paramString)
      {
        for (;;)
        {
          Log.e("FirebaseMessaging", "Error while setting the notification channel", paramString);
        }
      }
      catch (ClassNotFoundException paramString)
      {
        for (;;)
        {
          Log.e("FirebaseMessaging", "Error while setting the notification channel", paramString);
        }
      }
      catch (SecurityException paramString)
      {
        for (;;)
        {
          Log.e("FirebaseMessaging", "Error while setting the notification channel", paramString);
        }
      }
      catch (IllegalArgumentException paramString)
      {
        for (;;)
        {
          Log.e("FirebaseMessaging", "Error while setting the notification channel", paramString);
        }
      }
      catch (LinkageError paramString)
      {
        for (;;)
        {
          Log.e("FirebaseMessaging", "Error while setting the notification channel", paramString);
        }
      }
    }
  }
  
  private String zzk(Bundle paramBundle, String paramString)
  {
    String str = zzf(paramBundle, paramString);
    if (!TextUtils.isEmpty(str)) {
      return str;
    }
    str = zzi(paramBundle, paramString);
    if (TextUtils.isEmpty(str)) {
      return null;
    }
    Resources localResources = this.mContext.getResources();
    int i = localResources.getIdentifier(str, "string", this.mContext.getPackageName());
    if (i == 0)
    {
      paramBundle = String.valueOf(paramString);
      paramString = String.valueOf("_loc_key");
      if (paramString.length() != 0) {}
      for (paramBundle = paramBundle.concat(paramString);; paramBundle = new String(paramBundle))
      {
        paramBundle = String.valueOf(zzeA(paramBundle));
        Log.w("FirebaseMessaging", String.valueOf(paramBundle).length() + 49 + String.valueOf(str).length() + paramBundle + " resource not found: " + str + " Default value will be used.");
        return null;
      }
    }
    paramBundle = zzj(paramBundle, paramString);
    if (paramBundle == null) {
      return localResources.getString(i);
    }
    try
    {
      paramString = localResources.getString(i, paramBundle);
      return paramString;
    }
    catch (MissingFormatArgumentException paramString)
    {
      paramBundle = String.valueOf(Arrays.toString(paramBundle));
      Log.w("FirebaseMessaging", String.valueOf(str).length() + 58 + String.valueOf(paramBundle).length() + "Missing format argument for " + str + ": " + paramBundle + " Default value will be used.", paramString);
    }
    return null;
  }
  
  boolean zzG(Bundle paramBundle)
  {
    if ("1".equals(zzf(paramBundle, "gcm.n.noui"))) {
      return true;
    }
    if (zzacc()) {
      return false;
    }
    Notification localNotification = zzH(paramBundle);
    zza(zzf(paramBundle, "gcm.n.tag"), localNotification);
    return true;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\messaging\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */