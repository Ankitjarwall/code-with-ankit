package com.onesignal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NotificationManagerCompat;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONArray;
import org.json.JSONObject;

class NotificationOpenedProcessor
{
  private static void addChildNotifications(JSONArray paramJSONArray, String paramString, SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase = paramSQLiteDatabase.query("notification", new String[] { "full_data" }, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", new String[] { paramString }, null, null, null);
    if (paramSQLiteDatabase.getCount() > 1) {
      paramSQLiteDatabase.moveToFirst();
    }
    try
    {
      do
      {
        paramJSONArray.put(new JSONObject(paramSQLiteDatabase.getString(paramSQLiteDatabase.getColumnIndex("full_data"))));
      } while (paramSQLiteDatabase.moveToNext());
      paramSQLiteDatabase.close();
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Could not parse JSON of sub notification in group: " + paramString);
      }
    }
  }
  
  private static void handleDismissFromActionButtonPress(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getBooleanExtra("action_button", false))
    {
      NotificationManagerCompat.from(paramContext).cancel(paramIntent.getIntExtra("notificationId", 0));
      paramContext.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
    }
  }
  
  private static boolean isOneSignalIntent(Intent paramIntent)
  {
    return (paramIntent.hasExtra("onesignal_data")) || (paramIntent.hasExtra("summary")) || (paramIntent.hasExtra("notificationId"));
  }
  
  private static void markNotificationsConsumed(Context paramContext, Intent paramIntent, SQLiteDatabase paramSQLiteDatabase)
  {
    String str2 = paramIntent.getStringExtra("summary");
    String[] arrayOfString = null;
    String str1;
    if (str2 != null)
    {
      str1 = "group_id = ?";
      arrayOfString = new String[1];
      arrayOfString[0] = str2;
    }
    for (;;)
    {
      paramSQLiteDatabase.update("notification", newContentValuesWithConsumed(paramIntent), str1, arrayOfString);
      BadgeCountUpdater.update(paramSQLiteDatabase, paramContext);
      return;
      str1 = "android_notification_id = " + paramIntent.getIntExtra("notificationId", 0);
    }
  }
  
  private static ContentValues newContentValuesWithConsumed(Intent paramIntent)
  {
    ContentValues localContentValues = new ContentValues();
    if (paramIntent.getBooleanExtra("dismissed", false))
    {
      localContentValues.put("dismissed", Integer.valueOf(1));
      return localContentValues;
    }
    localContentValues.put("opened", Integer.valueOf(1));
    return localContentValues;
  }
  
  static void processFromContext(Context paramContext, Intent paramIntent)
  {
    if (!isOneSignalIntent(paramIntent)) {
      return;
    }
    handleDismissFromActionButtonPress(paramContext, paramIntent);
    processIntent(paramContext, paramIntent);
  }
  
  static void processIntent(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("summary");
    boolean bool = paramIntent.getBooleanExtra("dismissed", false);
    Object localObject1 = null;
    Object localObject3 = localObject1;
    if (!bool) {}
    try
    {
      localObject2 = new JSONObject(paramIntent.getStringExtra("onesignal_data"));
      ((JSONObject)localObject2).put("notificationId", paramIntent.getIntExtra("notificationId", 0));
      paramIntent.putExtra("onesignal_data", ((JSONObject)localObject2).toString());
      localObject3 = NotificationBundleProcessor.newJsonArray(new JSONObject(paramIntent.getStringExtra("onesignal_data")));
      localObject4 = OneSignalDbHelper.getInstance(paramContext);
      localObject2 = null;
      localObject1 = null;
    }
    catch (Throwable localThrowable3)
    {
      for (;;)
      {
        try
        {
          localObject4 = ((OneSignalDbHelper)localObject4).getWritableDbWithRetries();
          localObject1 = localObject4;
          Object localObject2 = localObject4;
          ((SQLiteDatabase)localObject4).beginTransaction();
          if ((!bool) && (str != null))
          {
            localObject1 = localObject4;
            localObject2 = localObject4;
            addChildNotifications((JSONArray)localObject3, str, (SQLiteDatabase)localObject4);
          }
          localObject1 = localObject4;
          localObject2 = localObject4;
          markNotificationsConsumed(paramContext, paramIntent, (SQLiteDatabase)localObject4);
          if (str == null)
          {
            localObject1 = localObject4;
            localObject2 = localObject4;
            str = paramIntent.getStringExtra("grp");
            if (str != null)
            {
              localObject1 = localObject4;
              localObject2 = localObject4;
              NotificationSummaryManager.updateSummaryNotificationAfterChildRemoved(paramContext, (SQLiteDatabase)localObject4, str, bool);
            }
          }
          localObject1 = localObject4;
          localObject2 = localObject4;
          ((SQLiteDatabase)localObject4).setTransactionSuccessful();
        }
        catch (Exception localException)
        {
          Object localObject4;
          localThrowable4 = localThrowable1;
          OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error processing notification open or dismiss record! ", localException);
          if (localThrowable1 == null) {
            continue;
          }
          try
          {
            localThrowable1.endTransaction();
          }
          catch (Throwable localThrowable2)
          {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", localThrowable2);
          }
          continue;
        }
        finally
        {
          if (localThrowable4 == null) {
            break label311;
          }
        }
        try
        {
          ((SQLiteDatabase)localObject4).endTransaction();
          if (!bool) {
            OneSignal.handleNotificationOpen(paramContext, (JSONArray)localObject3, paramIntent.getBooleanExtra("from_alert", false));
          }
          return;
          localThrowable3 = localThrowable3;
          ThrowableExtension.printStackTrace(localThrowable3);
          localObject3 = localObject1;
        }
        catch (Throwable localThrowable1)
        {
          OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", localThrowable1);
        }
      }
    }
    try
    {
      Throwable localThrowable4;
      localThrowable4.endTransaction();
      label311:
      throw paramContext;
    }
    catch (Throwable paramIntent)
    {
      for (;;)
      {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", paramIntent);
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\NotificationOpenedProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */