package com.onesignal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.JobIntentService;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.telephony.TelephonyManager;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.firebase.messaging.FirebaseMessaging;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class OSUtils
{
  static final int UNINITIALIZABLE_STATUS = -999;
  
  static boolean areNotificationsEnabled(Context paramContext)
  {
    try
    {
      boolean bool = NotificationManagerCompat.from(OneSignal.appContext).areNotificationsEnabled();
      return bool;
    }
    catch (Throwable paramContext) {}
    return true;
  }
  
  private Integer checkAndroidSupportLibrary(Context paramContext)
  {
    boolean bool1 = hasWakefulBroadcastReceiver();
    boolean bool2 = hasNotificationManagerCompat();
    if ((!bool1) && (!bool2))
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "Could not find the Android Support Library. Please make sure it has been correctly added to your project.");
      return Integer.valueOf(-3);
    }
    if ((!bool1) || (!bool2))
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "The included Android Support Library is to old or incomplete. Please update to the 26.0.0 revision or newer.");
      return Integer.valueOf(-5);
    }
    if ((Build.VERSION.SDK_INT >= 26) && (getTargetSdkVersion(paramContext) >= 26) && (!hasJobIntentService()))
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "The included Android Support Library is to old or incomplete. Please update to the 26.0.0 revision or newer.");
      return Integer.valueOf(-5);
    }
    return null;
  }
  
  static String getCorrectedLanguage()
  {
    String str2 = Locale.getDefault().getLanguage();
    String str1;
    if (str2.equals("iw")) {
      str1 = "he";
    }
    do
    {
      return str1;
      if (str2.equals("in")) {
        return "id";
      }
      if (str2.equals("ji")) {
        return "yi";
      }
      str1 = str2;
    } while (!str2.equals("zh"));
    return str2 + "-" + Locale.getDefault().getCountry();
  }
  
  static String getManifestMeta(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.getString(paramString);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "", paramContext);
    }
    return null;
  }
  
  static String getResourceString(Context paramContext, String paramString1, String paramString2)
  {
    Resources localResources = paramContext.getResources();
    int i = localResources.getIdentifier(paramString1, "string", paramContext.getPackageName());
    if (i != 0) {
      paramString2 = localResources.getString(i);
    }
    return paramString2;
  }
  
  static Uri getSoundUri(Context paramContext, String paramString)
  {
    Resources localResources = paramContext.getResources();
    paramContext = paramContext.getPackageName();
    if (isValidResourceName(paramString))
    {
      i = localResources.getIdentifier(paramString, "raw", paramContext);
      if (i != 0) {
        return Uri.parse("android.resource://" + paramContext + "/" + i);
      }
    }
    int i = localResources.getIdentifier("onesignal_default_sound", "raw", paramContext);
    if (i != 0) {
      return Uri.parse("android.resource://" + paramContext + "/" + i);
    }
    return null;
  }
  
  static int getTargetSdkVersion(Context paramContext)
  {
    String str = paramContext.getPackageName();
    paramContext = paramContext.getPackageManager();
    try
    {
      int i = paramContext.getApplicationInfo(str, 0).targetSdkVersion;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      ThrowableExtension.printStackTrace(paramContext);
    }
    return 15;
  }
  
  static boolean hasFCMLibrary()
  {
    boolean bool = false;
    if (FirebaseMessaging.class != null) {
      bool = true;
    }
    return bool;
  }
  
  private static boolean hasGCMLibrary()
  {
    boolean bool = false;
    if (GoogleCloudMessaging.class != null) {
      bool = true;
    }
    return bool;
  }
  
  private static boolean hasJobIntentService()
  {
    boolean bool = false;
    if (JobIntentService.class != null) {
      bool = true;
    }
    return bool;
  }
  
  private static boolean hasNotificationManagerCompat()
  {
    boolean bool = false;
    if (NotificationManagerCompat.class != null) {
      bool = true;
    }
    return bool;
  }
  
  private static boolean hasWakefulBroadcastReceiver()
  {
    boolean bool = false;
    if (WakefulBroadcastReceiver.class != null) {
      bool = true;
    }
    return bool;
  }
  
  static String hexDigest(String paramString1, String paramString2)
    throws Throwable
  {
    paramString2 = MessageDigest.getInstance(paramString2);
    paramString2.update(paramString1.getBytes("UTF-8"));
    paramString2 = paramString2.digest();
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramString2.length;
    int i = 0;
    while (i < j)
    {
      for (paramString1 = Integer.toHexString(paramString2[i] & 0xFF); paramString1.length() < 2; paramString1 = "0" + paramString1) {}
      localStringBuilder.append(paramString1);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  static boolean isValidEmail(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    return Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$").matcher(paramString).matches();
  }
  
  static boolean isValidResourceName(String paramString)
  {
    return (paramString != null) && (!paramString.matches("^[0-9]"));
  }
  
  static long[] parseVibrationPattern(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject = paramJSONObject.opt("vib_pt");
      if ((paramJSONObject instanceof String)) {}
      long[] arrayOfLong1;
      for (paramJSONObject = new JSONArray((String)paramJSONObject);; paramJSONObject = (JSONArray)paramJSONObject)
      {
        long[] arrayOfLong2 = new long[paramJSONObject.length()];
        int i = 0;
        for (;;)
        {
          arrayOfLong1 = arrayOfLong2;
          if (i >= paramJSONObject.length()) {
            break;
          }
          arrayOfLong2[i] = paramJSONObject.optLong(i);
          i += 1;
        }
      }
      return arrayOfLong1;
    }
    catch (JSONException paramJSONObject)
    {
      arrayOfLong1 = null;
    }
  }
  
  static void runOnMainUIThread(Runnable paramRunnable)
  {
    if (Looper.getMainLooper().getThread() == Thread.currentThread())
    {
      paramRunnable.run();
      return;
    }
    new Handler(Looper.getMainLooper()).post(paramRunnable);
  }
  
  static void sleep(int paramInt)
  {
    long l = paramInt;
    try
    {
      Thread.sleep(l);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      ThrowableExtension.printStackTrace(localInterruptedException);
    }
  }
  
  Integer checkForGooglePushLibrary()
  {
    boolean bool1 = hasFCMLibrary();
    boolean bool2 = hasGCMLibrary();
    if ((!bool1) && (!bool2))
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "The Firebase FCM library is missing! Please make sure to include it in your project.");
      return Integer.valueOf(-4);
    }
    if ((bool2) && (!bool1)) {
      OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "GCM Library detected, please upgrade to Firebase FCM library as GCM is deprecated!");
    }
    if ((bool2) && (bool1)) {
      OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Both GCM & FCM Libraries detected! Please remove the deprecated GCM library.");
    }
    return null;
  }
  
  String getCarrierName()
  {
    try
    {
      String str = ((TelephonyManager)OneSignal.appContext.getSystemService("phone")).getNetworkOperatorName();
      boolean bool = "".equals(str);
      if (bool) {
        str = null;
      }
      return str;
    }
    catch (Throwable localThrowable)
    {
      ThrowableExtension.printStackTrace(localThrowable);
    }
    return null;
  }
  
  int getDeviceType()
  {
    try
    {
      Class.forName("com.amazon.device.messaging.ADM");
      return 2;
    }
    catch (ClassNotFoundException localClassNotFoundException) {}
    return 1;
  }
  
  Integer getNetType()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)OneSignal.appContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo != null)
    {
      int i = localNetworkInfo.getType();
      if ((i == 1) || (i == 9)) {
        return Integer.valueOf(0);
      }
      return Integer.valueOf(1);
    }
    return null;
  }
  
  int initializationChecker(Context paramContext, int paramInt, String paramString)
  {
    int j = 1;
    try
    {
      UUID.fromString(paramString);
      if (("b2f7f966-d8cc-11e4-bed1-df8f05be55ba".equals(paramString)) || ("5eb5a37e-b458-11e3-ac11-000c2940e62c".equals(paramString))) {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal Example AppID detected, please update to your app's id found on OneSignal.com");
      }
      int i = j;
      if (paramInt == 1)
      {
        paramString = checkForGooglePushLibrary();
        i = j;
        if (paramString != null) {
          i = paramString.intValue();
        }
      }
      paramContext = checkAndroidSupportLibrary(paramContext);
      if (paramContext != null) {
        i = paramContext.intValue();
      }
      return i;
    }
    catch (Throwable paramContext)
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "OneSignal AppId format is invalid.\nExample: 'b2f7f966-d8cc-11e4-bed1-df8f05be55ba'\n", paramContext);
    }
    return 64537;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OSUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */