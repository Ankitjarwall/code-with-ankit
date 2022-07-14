package com.onesignal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class OneSignalPrefs
{
  static final String PREFS_EXISTING_PURCHASES = "ExistingPurchases";
  static final String PREFS_GT_APP_ID = "GT_APP_ID";
  static final String PREFS_GT_DO_NOT_SHOW_MISSING_GPS = "GT_DO_NOT_SHOW_MISSING_GPS";
  static final String PREFS_GT_FIREBASE_TRACKING_ENABLED = "GT_FIREBASE_TRACKING_ENABLED";
  static final String PREFS_GT_PLAYER_ID = "GT_PLAYER_ID";
  static final String PREFS_GT_REGISTRATION_ID = "GT_REGISTRATION_ID";
  static final String PREFS_GT_SOUND_ENABLED = "GT_SOUND_ENABLED";
  static final String PREFS_GT_UNSENT_ACTIVE_TIME = "GT_UNSENT_ACTIVE_TIME";
  static final String PREFS_GT_VIBRATE_ENABLED = "GT_VIBRATE_ENABLED";
  public static final String PREFS_ONESIGNAL = OneSignal.class.getSimpleName();
  static final String PREFS_ONESIGNAL_ACCEPTED_NOTIFICATION_LAST = "ONESIGNAL_ACCEPTED_NOTIFICATION_LAST";
  static final String PREFS_ONESIGNAL_EMAIL_ADDRESS_LAST = "PREFS_ONESIGNAL_EMAIL_ADDRESS_LAST";
  static final String PREFS_ONESIGNAL_EMAIL_ID_LAST = "PREFS_ONESIGNAL_EMAIL_ID_LAST";
  static final String PREFS_ONESIGNAL_PERMISSION_ACCEPTED_LAST = "ONESIGNAL_PERMISSION_ACCEPTED_LAST";
  static final String PREFS_ONESIGNAL_PLAYER_ID_LAST = "ONESIGNAL_PLAYER_ID_LAST";
  static final String PREFS_ONESIGNAL_PUSH_TOKEN_LAST = "ONESIGNAL_PUSH_TOKEN_LAST";
  static final String PREFS_ONESIGNAL_SUBSCRIPTION = "ONESIGNAL_SUBSCRIPTION";
  static final String PREFS_ONESIGNAL_SUBSCRIPTION_LAST = "ONESIGNAL_SUBSCRIPTION_LAST";
  static final String PREFS_ONESIGNAL_SYNCED_SUBSCRIPTION = "ONESIGNAL_SYNCED_SUBSCRIPTION";
  static final String PREFS_ONESIGNAL_USERSTATE_DEPENDVALYES_ = "ONESIGNAL_USERSTATE_DEPENDVALYES_";
  static final String PREFS_ONESIGNAL_USERSTATE_SYNCVALYES_ = "ONESIGNAL_USERSTATE_SYNCVALYES_";
  static final String PREFS_ONESIGNAL_USER_PROVIDED_CONSENT = "ONESIGNAL_USER_PROVIDED_CONSENT";
  static final String PREFS_OS_EMAIL_ID = "OS_EMAIL_ID";
  static final String PREFS_OS_FILTER_OTHER_GCM_RECEIVERS = "OS_FILTER_OTHER_GCM_RECEIVERS";
  static final String PREFS_OS_LAST_LOCATION_TIME = "OS_LAST_LOCATION_TIME";
  static final String PREFS_OS_LAST_SESSION_TIME = "OS_LAST_SESSION_TIME";
  static final String PREFS_PLAYER_PURCHASES = "GTPlayerPurchases";
  static final String PREFS_PURCHASE_TOKENS = "purchaseTokens";
  public static WritePrefHandlerThread prefsHandler;
  static HashMap<String, HashMap<String, Object>> prefsToApply;
  
  static
  {
    initializePool();
  }
  
  private static Object get(String paramString1, String paramString2, Class paramClass, Object paramObject)
  {
    synchronized ((HashMap)prefsToApply.get(paramString1))
    {
      if ((paramClass.equals(Object.class)) && (((HashMap)???).containsKey(paramString2))) {
        return Boolean.valueOf(true);
      }
      Object localObject2 = ((HashMap)???).get(paramString2);
      if ((localObject2 != null) || (((HashMap)???).containsKey(paramString2))) {
        return localObject2;
      }
      ??? = getSharedPrefsByName(paramString1);
      paramString1 = (String)paramObject;
      if (??? == null) {
        return paramString1;
      }
      if (paramClass.equals(String.class)) {
        return ((SharedPreferences)???).getString(paramString2, (String)paramObject);
      }
    }
    if (paramClass.equals(Boolean.class)) {
      return Boolean.valueOf(((SharedPreferences)???).getBoolean(paramString2, ((Boolean)paramObject).booleanValue()));
    }
    if (paramClass.equals(Integer.class)) {
      return Integer.valueOf(((SharedPreferences)???).getInt(paramString2, ((Integer)paramObject).intValue()));
    }
    if (paramClass.equals(Long.class)) {
      return Long.valueOf(((SharedPreferences)???).getLong(paramString2, ((Long)paramObject).longValue()));
    }
    if (paramClass.equals(Object.class)) {
      return Boolean.valueOf(((SharedPreferences)???).contains(paramString2));
    }
    paramString1 = null;
    return paramString1;
  }
  
  static boolean getBool(String paramString1, String paramString2, boolean paramBoolean)
  {
    return ((Boolean)get(paramString1, paramString2, Boolean.class, Boolean.valueOf(paramBoolean))).booleanValue();
  }
  
  static int getInt(String paramString1, String paramString2, int paramInt)
  {
    return ((Integer)get(paramString1, paramString2, Integer.class, Integer.valueOf(paramInt))).intValue();
  }
  
  static long getLong(String paramString1, String paramString2, long paramLong)
  {
    return ((Long)get(paramString1, paramString2, Long.class, Long.valueOf(paramLong))).longValue();
  }
  
  /* Error */
  private static SharedPreferences getSharedPrefsByName(String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 197	com/onesignal/OneSignal:appContext	Landroid/content/Context;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnonnull +10 -> 18
    //   11: aconst_null
    //   12: astore_0
    //   13: ldc 2
    //   15: monitorexit
    //   16: aload_0
    //   17: areturn
    //   18: getstatic 197	com/onesignal/OneSignal:appContext	Landroid/content/Context;
    //   21: aload_0
    //   22: iconst_0
    //   23: invokevirtual 203	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   26: astore_0
    //   27: goto -14 -> 13
    //   30: astore_0
    //   31: ldc 2
    //   33: monitorexit
    //   34: aload_0
    //   35: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	36	0	paramString	String
    //   6	2	1	localContext	android.content.Context
    // Exception table:
    //   from	to	target	type
    //   3	7	30	finally
    //   18	27	30	finally
  }
  
  static String getString(String paramString1, String paramString2, String paramString3)
  {
    return (String)get(paramString1, paramString2, String.class, paramString3);
  }
  
  public static void initializePool()
  {
    prefsToApply = new HashMap();
    prefsToApply.put(PREFS_ONESIGNAL, new HashMap());
    prefsToApply.put("GTPlayerPurchases", new HashMap());
    prefsHandler = new WritePrefHandlerThread();
  }
  
  private static void save(String arg0, String paramString2, Object paramObject)
  {
    synchronized ((HashMap)prefsToApply.get(???))
    {
      ???.put(paramString2, paramObject);
      startDelayedWrite();
      return;
    }
  }
  
  public static void saveBool(String paramString1, String paramString2, boolean paramBoolean)
  {
    save(paramString1, paramString2, Boolean.valueOf(paramBoolean));
  }
  
  public static void saveInt(String paramString1, String paramString2, int paramInt)
  {
    save(paramString1, paramString2, Integer.valueOf(paramInt));
  }
  
  public static void saveLong(String paramString1, String paramString2, long paramLong)
  {
    save(paramString1, paramString2, Long.valueOf(paramLong));
  }
  
  public static void saveString(String paramString1, String paramString2, String paramString3)
  {
    save(paramString1, paramString2, paramString3);
  }
  
  public static void startDelayedWrite()
  {
    prefsHandler.startDelayedWrite();
  }
  
  public static class WritePrefHandlerThread
    extends HandlerThread
  {
    private static final int WRITE_CALL_DELAY_TO_BUFFER_MS = 200;
    private long lastSyncTime = 0L;
    public Handler mHandler;
    
    WritePrefHandlerThread()
    {
      super();
      start();
      this.mHandler = new Handler(getLooper());
    }
    
    private void flushBufferToDisk()
    {
      if (OneSignal.appContext == null) {
        return;
      }
      Iterator localIterator1 = OneSignalPrefs.prefsToApply.keySet().iterator();
      while (localIterator1.hasNext())
      {
        ??? = (String)localIterator1.next();
        SharedPreferences.Editor localEditor = OneSignalPrefs.getSharedPrefsByName((String)???).edit();
        for (;;)
        {
          String str;
          Object localObject3;
          synchronized ((HashMap)OneSignalPrefs.prefsToApply.get(???))
          {
            Iterator localIterator2 = ((HashMap)???).keySet().iterator();
            if (!localIterator2.hasNext()) {
              break;
            }
            str = (String)localIterator2.next();
            localObject3 = ((HashMap)???).get(str);
            if ((localObject3 instanceof String)) {
              localEditor.putString(str, (String)localObject3);
            }
          }
          if ((localObject3 instanceof Boolean)) {
            localEditor.putBoolean(str, ((Boolean)localObject3).booleanValue());
          } else if ((localObject3 instanceof Integer)) {
            localEditor.putInt(str, ((Integer)localObject3).intValue());
          } else if ((localObject3 instanceof Long)) {
            localEditor.putLong(str, ((Long)localObject3).longValue());
          }
        }
        ((HashMap)???).clear();
        localEditor.apply();
      }
      this.lastSyncTime = System.currentTimeMillis();
    }
    
    private Runnable getNewRunnable()
    {
      new Runnable()
      {
        public void run()
        {
          OneSignalPrefs.WritePrefHandlerThread.this.flushBufferToDisk();
        }
      };
    }
    
    void startDelayedWrite()
    {
      synchronized (this.mHandler)
      {
        this.mHandler.removeCallbacksAndMessages(null);
        if (this.lastSyncTime == 0L) {
          this.lastSyncTime = System.currentTimeMillis();
        }
        long l1 = this.lastSyncTime;
        long l2 = System.currentTimeMillis();
        this.mHandler.postDelayed(getNewRunnable(), l1 - l2 + 200L);
        return;
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OneSignalPrefs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */