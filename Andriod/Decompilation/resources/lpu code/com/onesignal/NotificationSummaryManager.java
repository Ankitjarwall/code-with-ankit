package com.onesignal;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.json.JSONException;
import org.json.JSONObject;

class NotificationSummaryManager
{
  private static Integer getSummaryNotificationId(SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
    Integer localInteger2 = null;
    localObject = null;
    localSQLiteDatabase = null;
    localInteger1 = localInteger2;
    try
    {
      paramSQLiteDatabase = paramSQLiteDatabase.query("notification", new String[] { "android_notification_id" }, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 1", new String[] { paramString }, null, null, null);
      localInteger1 = localInteger2;
      localSQLiteDatabase = paramSQLiteDatabase;
      localObject = paramSQLiteDatabase;
      if (!paramSQLiteDatabase.moveToFirst())
      {
        localInteger1 = localInteger2;
        localSQLiteDatabase = paramSQLiteDatabase;
        localObject = paramSQLiteDatabase;
        paramSQLiteDatabase.close();
        if ((paramSQLiteDatabase != null) && (!paramSQLiteDatabase.isClosed())) {
          paramSQLiteDatabase.close();
        }
        return null;
      }
      localInteger1 = localInteger2;
      localSQLiteDatabase = paramSQLiteDatabase;
      localObject = paramSQLiteDatabase;
      localInteger2 = Integer.valueOf(paramSQLiteDatabase.getInt(paramSQLiteDatabase.getColumnIndex("android_notification_id")));
      localInteger1 = localInteger2;
      localSQLiteDatabase = paramSQLiteDatabase;
      localObject = paramSQLiteDatabase;
      paramSQLiteDatabase.close();
      paramString = localInteger2;
      if (paramSQLiteDatabase != null)
      {
        paramString = localInteger2;
        if (!paramSQLiteDatabase.isClosed())
        {
          paramSQLiteDatabase.close();
          paramString = localInteger2;
        }
      }
    }
    catch (Throwable paramSQLiteDatabase)
    {
      for (;;)
      {
        localObject = localSQLiteDatabase;
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error getting android notification id for summary notification group: " + paramString, paramSQLiteDatabase);
        paramString = localInteger1;
        if (localSQLiteDatabase != null)
        {
          paramString = localInteger1;
          if (!localSQLiteDatabase.isClosed())
          {
            localSQLiteDatabase.close();
            paramString = localInteger1;
          }
        }
      }
    }
    finally
    {
      if ((localObject == null) || (((Cursor)localObject).isClosed())) {
        break label244;
      }
      ((Cursor)localObject).close();
    }
    return paramString;
  }
  
  private static Cursor internalUpdateSummaryNotificationAfterChildRemoved(Context paramContext, SQLiteDatabase paramSQLiteDatabase, String paramString, boolean paramBoolean)
  {
    Cursor localCursor = paramSQLiteDatabase.query("notification", new String[] { "android_notification_id", "created_time" }, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", new String[] { paramString }, null, null, "_id DESC");
    int i = localCursor.getCount();
    if (i == 0)
    {
      localCursor.close();
      paramString = getSummaryNotificationId(paramSQLiteDatabase, paramString);
      if (paramString != null) {}
    }
    for (;;)
    {
      return localCursor;
      ((NotificationManager)paramContext.getSystemService("notification")).cancel(paramString.intValue());
      ContentValues localContentValues = new ContentValues();
      if (paramBoolean) {}
      for (paramContext = "dismissed";; paramContext = "opened")
      {
        localContentValues.put(paramContext, Integer.valueOf(1));
        paramSQLiteDatabase.update("notification", localContentValues, "android_notification_id = " + paramString, null);
        return localCursor;
      }
      if (i == 1)
      {
        localCursor.close();
        if (getSummaryNotificationId(paramSQLiteDatabase, paramString) != null)
        {
          restoreSummary(paramContext, paramString);
          return localCursor;
        }
      }
      else
      {
        try
        {
          localCursor.moveToFirst();
          long l = localCursor.getLong(localCursor.getColumnIndex("created_time"));
          localCursor.close();
          if (getSummaryNotificationId(paramSQLiteDatabase, paramString) != null)
          {
            paramContext = new NotificationGenerationJob(paramContext);
            paramContext.restoring = true;
            paramContext.shownTimeStamp = Long.valueOf(l);
            paramSQLiteDatabase = new JSONObject();
            paramSQLiteDatabase.put("grp", paramString);
            paramContext.jsonPayload = paramSQLiteDatabase;
            GenerateNotification.updateSummaryNotification(paramContext);
            return localCursor;
          }
        }
        catch (JSONException paramContext) {}
      }
    }
    return localCursor;
  }
  
  private static void restoreSummary(Context paramContext, String paramString)
  {
    OneSignalDbHelper localOneSignalDbHelper = OneSignalDbHelper.getInstance(paramContext);
    Object localObject = null;
    String str = null;
    try
    {
      paramString = localOneSignalDbHelper.getReadableDbWithRetries().query("notification", NotificationRestorer.COLUMNS_FOR_RESTORE, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", new String[] { paramString }, null, null, null);
      str = paramString;
      localObject = paramString;
      NotificationRestorer.showNotifications(paramContext, paramString, 0);
      if ((paramString != null) && (!paramString.isClosed())) {
        paramString.close();
      }
      return;
    }
    catch (Throwable paramContext)
    {
      do
      {
        localObject = str;
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error restoring notification records! ", paramContext);
      } while ((str == null) || (str.isClosed()));
      str.close();
      return;
    }
    finally
    {
      if ((localObject != null) && (!((Cursor)localObject).isClosed())) {
        ((Cursor)localObject).close();
      }
    }
  }
  
  static void updatePossibleDependentSummaryOnDismiss(Context paramContext, SQLiteDatabase paramSQLiteDatabase, int paramInt)
  {
    Object localObject = "android_notification_id = " + paramInt;
    localObject = paramSQLiteDatabase.query("notification", new String[] { "group_id" }, (String)localObject, null, null, null, null);
    if (((Cursor)localObject).moveToFirst())
    {
      String str = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("group_id"));
      ((Cursor)localObject).close();
      if (str != null) {
        updateSummaryNotificationAfterChildRemoved(paramContext, paramSQLiteDatabase, str, true);
      }
      return;
    }
    ((Cursor)localObject).close();
  }
  
  static void updateSummaryNotificationAfterChildRemoved(Context paramContext, SQLiteDatabase paramSQLiteDatabase, String paramString, boolean paramBoolean)
  {
    try
    {
      paramContext = internalUpdateSummaryNotificationAfterChildRemoved(paramContext, paramSQLiteDatabase, paramString, paramBoolean);
      if ((paramContext != null) && (!paramContext.isClosed())) {
        paramContext.close();
      }
      return;
    }
    catch (Throwable paramContext)
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error running updateSummaryNotificationAfterChildRemoved!", paramContext);
    }
    finally
    {
      if (0 != 0) {
        throw new NullPointerException();
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\NotificationSummaryManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */