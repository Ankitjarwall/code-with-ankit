package com.onesignal;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build.VERSION;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import java.util.ArrayList;

class NotificationRestorer
{
  static final String[] COLUMNS_FOR_RESTORE = { "android_notification_id", "full_data", "created_time" };
  private static final int RESTORE_KICKOFF_REQUEST_CODE = 2071862120;
  private static final int RESTORE_NOTIFICATIONS_DELAY_MS = 15000;
  public static boolean restored;
  
  private static Intent addRestoreExtras(Intent paramIntent, Cursor paramCursor)
  {
    int i = paramCursor.getInt(paramCursor.getColumnIndex("android_notification_id"));
    String str = paramCursor.getString(paramCursor.getColumnIndex("full_data"));
    long l = paramCursor.getLong(paramCursor.getColumnIndex("created_time"));
    paramIntent.putExtra("json_payload", str).putExtra("android_notif_id", i).putExtra("restoring", true).putExtra("timestamp", Long.valueOf(l));
    return paramIntent;
  }
  
  static void asyncRestore(Context paramContext)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        Thread.currentThread().setPriority(10);
        NotificationRestorer.restore(this.val$context);
      }
    }, "OS_RESTORE_NOTIFS").start();
  }
  
  /* Error */
  @android.support.annotation.WorkerThread
  public static void restore(Context paramContext)
  {
    // Byte code:
    //   0: getstatic 100	com/onesignal/NotificationRestorer:restored	Z
    //   3: ifeq +4 -> 7
    //   6: return
    //   7: iconst_1
    //   8: putstatic 100	com/onesignal/NotificationRestorer:restored	Z
    //   11: getstatic 106	com/onesignal/OneSignal$LOG_LEVEL:INFO	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   14: ldc 108
    //   16: invokestatic 114	com/onesignal/OneSignal:Log	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;)V
    //   19: aload_0
    //   20: invokestatic 120	com/onesignal/OneSignalDbHelper:getInstance	(Landroid/content/Context;)Lcom/onesignal/OneSignalDbHelper;
    //   23: astore 6
    //   25: aconst_null
    //   26: astore 4
    //   28: aconst_null
    //   29: astore_3
    //   30: aload 6
    //   32: invokevirtual 124	com/onesignal/OneSignalDbHelper:getWritableDbWithRetries	()Landroid/database/sqlite/SQLiteDatabase;
    //   35: astore 5
    //   37: aload 5
    //   39: astore_3
    //   40: aload 5
    //   42: astore 4
    //   44: aload 5
    //   46: invokevirtual 129	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   49: aload 5
    //   51: astore_3
    //   52: aload 5
    //   54: astore 4
    //   56: aload 5
    //   58: invokestatic 135	com/onesignal/NotificationBundleProcessor:deleteOldNotifications	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   61: aload 5
    //   63: astore_3
    //   64: aload 5
    //   66: astore 4
    //   68: aload 5
    //   70: invokevirtual 138	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   73: aload 5
    //   75: ifnull +8 -> 83
    //   78: aload 5
    //   80: invokevirtual 141	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   83: invokestatic 147	java/lang/System:currentTimeMillis	()J
    //   86: ldc2_w 148
    //   89: ldiv
    //   90: lstore_1
    //   91: new 151	java/lang/StringBuilder
    //   94: dup
    //   95: new 151	java/lang/StringBuilder
    //   98: dup
    //   99: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   102: ldc -102
    //   104: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: lload_1
    //   108: ldc2_w 159
    //   111: lsub
    //   112: invokevirtual 163	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   115: ldc -91
    //   117: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: ldc -89
    //   122: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: ldc -87
    //   127: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: ldc -85
    //   132: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: ldc -87
    //   137: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: ldc -83
    //   142: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: ldc -81
    //   147: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: invokevirtual 179	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   153: invokespecial 182	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   156: astore 5
    //   158: aload_0
    //   159: aload 5
    //   161: invokestatic 186	com/onesignal/NotificationRestorer:skipVisibleNotifications	(Landroid/content/Context;Ljava/lang/StringBuilder;)V
    //   164: getstatic 106	com/onesignal/OneSignal$LOG_LEVEL:INFO	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   167: new 151	java/lang/StringBuilder
    //   170: dup
    //   171: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   174: ldc -68
    //   176: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: aload 5
    //   181: invokevirtual 179	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   184: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: invokevirtual 179	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   190: invokestatic 114	com/onesignal/OneSignal:Log	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;)V
    //   193: aconst_null
    //   194: astore 4
    //   196: aconst_null
    //   197: astore_3
    //   198: aload 6
    //   200: invokevirtual 191	com/onesignal/OneSignalDbHelper:getReadableDbWithRetries	()Landroid/database/sqlite/SQLiteDatabase;
    //   203: ldc -63
    //   205: getstatic 27	com/onesignal/NotificationRestorer:COLUMNS_FOR_RESTORE	[Ljava/lang/String;
    //   208: aload 5
    //   210: invokevirtual 179	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   213: aconst_null
    //   214: aconst_null
    //   215: aconst_null
    //   216: ldc -61
    //   218: invokevirtual 199	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   221: astore 5
    //   223: aload 5
    //   225: astore_3
    //   226: aload 5
    //   228: astore 4
    //   230: aload_0
    //   231: aload 5
    //   233: bipush 100
    //   235: invokestatic 203	com/onesignal/NotificationRestorer:showNotifications	(Landroid/content/Context;Landroid/database/Cursor;I)V
    //   238: aload 5
    //   240: ifnull -234 -> 6
    //   243: aload 5
    //   245: invokeinterface 207 1 0
    //   250: ifne -244 -> 6
    //   253: aload 5
    //   255: invokeinterface 210 1 0
    //   260: return
    //   261: astore_3
    //   262: getstatic 213	com/onesignal/OneSignal$LOG_LEVEL:ERROR	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   265: ldc -41
    //   267: aload_3
    //   268: invokestatic 218	com/onesignal/OneSignal:Log	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   271: goto -188 -> 83
    //   274: astore 5
    //   276: aload_3
    //   277: astore 4
    //   279: getstatic 213	com/onesignal/OneSignal$LOG_LEVEL:ERROR	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   282: ldc -36
    //   284: aload 5
    //   286: invokestatic 218	com/onesignal/OneSignal:Log	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   289: aload_3
    //   290: ifnull -207 -> 83
    //   293: aload_3
    //   294: invokevirtual 141	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   297: goto -214 -> 83
    //   300: astore_3
    //   301: getstatic 213	com/onesignal/OneSignal$LOG_LEVEL:ERROR	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   304: ldc -41
    //   306: aload_3
    //   307: invokestatic 218	com/onesignal/OneSignal:Log	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   310: goto -227 -> 83
    //   313: astore_0
    //   314: aload 4
    //   316: ifnull +8 -> 324
    //   319: aload 4
    //   321: invokevirtual 141	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   324: aload_0
    //   325: athrow
    //   326: astore_3
    //   327: getstatic 213	com/onesignal/OneSignal$LOG_LEVEL:ERROR	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   330: ldc -41
    //   332: aload_3
    //   333: invokestatic 218	com/onesignal/OneSignal:Log	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   336: goto -12 -> 324
    //   339: astore_0
    //   340: aload_3
    //   341: astore 4
    //   343: getstatic 213	com/onesignal/OneSignal$LOG_LEVEL:ERROR	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   346: ldc -34
    //   348: aload_0
    //   349: invokestatic 218	com/onesignal/OneSignal:Log	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   352: aload_3
    //   353: ifnull -347 -> 6
    //   356: aload_3
    //   357: invokeinterface 207 1 0
    //   362: ifne -356 -> 6
    //   365: aload_3
    //   366: invokeinterface 210 1 0
    //   371: return
    //   372: astore_0
    //   373: aload 4
    //   375: ifnull +20 -> 395
    //   378: aload 4
    //   380: invokeinterface 207 1 0
    //   385: ifne +10 -> 395
    //   388: aload 4
    //   390: invokeinterface 210 1 0
    //   395: aload_0
    //   396: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	397	0	paramContext	Context
    //   90	18	1	l	long
    //   29	197	3	localObject1	Object
    //   261	33	3	localThrowable1	Throwable
    //   300	7	3	localThrowable2	Throwable
    //   326	40	3	localThrowable3	Throwable
    //   26	363	4	localObject2	Object
    //   35	219	5	localObject3	Object
    //   274	11	5	localThrowable4	Throwable
    //   23	176	6	localOneSignalDbHelper	OneSignalDbHelper
    // Exception table:
    //   from	to	target	type
    //   78	83	261	java/lang/Throwable
    //   30	37	274	java/lang/Throwable
    //   44	49	274	java/lang/Throwable
    //   56	61	274	java/lang/Throwable
    //   68	73	274	java/lang/Throwable
    //   293	297	300	java/lang/Throwable
    //   30	37	313	finally
    //   44	49	313	finally
    //   56	61	313	finally
    //   68	73	313	finally
    //   279	289	313	finally
    //   319	324	326	java/lang/Throwable
    //   198	223	339	java/lang/Throwable
    //   230	238	339	java/lang/Throwable
    //   198	223	372	finally
    //   230	238	372	finally
    //   343	352	372	finally
  }
  
  static void showNotifications(Context paramContext, Cursor paramCursor, int paramInt)
  {
    if (!paramCursor.moveToFirst()) {
      return;
    }
    int i;
    Intent localIntent;
    if (NotificationExtenderService.getIntent(paramContext) != null)
    {
      i = 1;
      if (i == 0) {
        break label72;
      }
      localIntent = NotificationExtenderService.getIntent(paramContext);
      addRestoreExtras(localIntent, paramCursor);
      NotificationExtenderService.enqueueWork(paramContext, localIntent.getComponent(), 2071862121, localIntent);
    }
    for (;;)
    {
      if (paramInt > 0) {
        OSUtils.sleep(paramInt);
      }
      if (paramCursor.moveToNext()) {
        break;
      }
      return;
      i = 0;
      break;
      label72:
      localIntent = addRestoreExtras(new Intent(), paramCursor);
      RestoreJobService.enqueueWork(paramContext, new ComponentName(paramContext, RestoreJobService.class), 2071862122, localIntent);
    }
  }
  
  private static void skipVisibleNotifications(Context paramContext, StringBuilder paramStringBuilder)
  {
    if (Build.VERSION.SDK_INT < 23) {}
    for (;;)
    {
      return;
      paramContext = (NotificationManager)paramContext.getSystemService("notification");
      if (paramContext != null) {
        try
        {
          paramContext = paramContext.getActiveNotifications();
          if (paramContext.length != 0)
          {
            ArrayList localArrayList = new ArrayList();
            int j = paramContext.length;
            int i = 0;
            while (i < j)
            {
              localArrayList.add(Integer.valueOf(paramContext[i].getId()));
              i += 1;
            }
            paramStringBuilder.append(" AND android_notification_id NOT IN (").append(TextUtils.join(",", localArrayList)).append(")");
            return;
          }
        }
        catch (Throwable paramContext) {}
      }
    }
  }
  
  static void startDelayedRestoreTaskFromReceiver(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "scheduleRestoreKickoffJob");
      localObject = new JobInfo.Builder(2071862120, new ComponentName(paramContext, RestoreKickoffJobService.class)).setOverrideDeadline(15000L).setMinimumLatency(15000L).build();
      ((JobScheduler)paramContext.getSystemService("jobscheduler")).schedule((JobInfo)localObject);
      return;
    }
    OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "scheduleRestoreKickoffAlarmTask");
    Object localObject = new Intent();
    ((Intent)localObject).setComponent(new ComponentName(paramContext.getPackageName(), NotificationRestoreService.class.getName()));
    localObject = PendingIntent.getService(paramContext, 2071862120, (Intent)localObject, 268435456);
    long l = System.currentTimeMillis();
    ((AlarmManager)paramContext.getSystemService("alarm")).set(1, l + 15000L, (PendingIntent)localObject);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\NotificationRestorer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */