package com.onesignal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OneSignalDbHelper
  extends SQLiteOpenHelper
{
  private static final String COMMA_SEP = ",";
  private static final String DATABASE_NAME = "OneSignal.db";
  private static final int DATABASE_VERSION = 2;
  private static final int DB_OPEN_RETRY_BACKOFF = 400;
  private static final int DB_OPEN_RETRY_MAX = 5;
  private static final String INT_TYPE = " INTEGER";
  private static final String SQL_CREATE_ENTRIES = "CREATE TABLE notification (_id INTEGER PRIMARY KEY,notification_id TEXT,android_notification_id INTEGER,group_id TEXT,collapse_id TEXT,is_summary INTEGER DEFAULT 0,opened INTEGER DEFAULT 0,dismissed INTEGER DEFAULT 0,title TEXT,message TEXT,full_data TEXT,created_time TIMESTAMP DEFAULT (strftime('%s', 'now')));";
  private static final String SQL_INDEX_ENTRIES = "CREATE INDEX notification_notification_id_idx ON notification(notification_id); CREATE INDEX notification_android_notification_id_idx ON notification(android_notification_id); CREATE INDEX notification_group_id_idx ON notification(group_id); CREATE INDEX notification_collapse_id_idx ON notification(collapse_id); CREATE INDEX notification_created_time_idx ON notification(created_time); ";
  private static final String TEXT_TYPE = " TEXT";
  private static OneSignalDbHelper sInstance;
  
  private OneSignalDbHelper(Context paramContext)
  {
    super(paramContext, "OneSignal.db", null, 2);
  }
  
  public static OneSignalDbHelper getInstance(Context paramContext)
  {
    try
    {
      if (sInstance == null) {
        sInstance = new OneSignalDbHelper(paramContext.getApplicationContext());
      }
      paramContext = sInstance;
      return paramContext;
    }
    finally {}
  }
  
  private static void internalOnUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    if (paramInt1 < 2)
    {
      paramSQLiteDatabase.execSQL("ALTER TABLE notification ADD COLUMN collapse_id TEXT;");
      paramSQLiteDatabase.execSQL("CREATE INDEX notification_group_id_idx ON notification(group_id); ");
    }
  }
  
  SQLiteDatabase getReadableDbWithRetries()
  {
    int i = 0;
    for (;;)
    {
      try
      {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        return localSQLiteDatabase;
      }
      catch (Throwable localThrowable)
      {
        i += 1;
        if (i >= 5) {
          throw localThrowable;
        }
      }
      finally {}
      long l = i * 400;
      SystemClock.sleep(l);
    }
  }
  
  SQLiteDatabase getWritableDbWithRetries()
  {
    int i = 0;
    for (;;)
    {
      try
      {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        return localSQLiteDatabase;
      }
      catch (Throwable localThrowable)
      {
        i += 1;
        if (i >= 5) {
          throw localThrowable;
        }
      }
      finally {}
      long l = i * 400;
      SystemClock.sleep(l);
    }
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE notification (_id INTEGER PRIMARY KEY,notification_id TEXT,android_notification_id INTEGER,group_id TEXT,collapse_id TEXT,is_summary INTEGER DEFAULT 0,opened INTEGER DEFAULT 0,dismissed INTEGER DEFAULT 0,title TEXT,message TEXT,full_data TEXT,created_time TIMESTAMP DEFAULT (strftime('%s', 'now')));");
    paramSQLiteDatabase.execSQL("CREATE INDEX notification_notification_id_idx ON notification(notification_id); CREATE INDEX notification_android_notification_id_idx ON notification(android_notification_id); CREATE INDEX notification_group_id_idx ON notification(group_id); CREATE INDEX notification_collapse_id_idx ON notification(collapse_id); CREATE INDEX notification_created_time_idx ON notification(created_time); ");
  }
  
  public void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "SDK version rolled back! Clearing OneSignal.db as it could be in an unexpected state.");
    Cursor localCursor = paramSQLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
    Object localObject;
    try
    {
      localObject = new ArrayList(localCursor.getCount());
      while (localCursor.moveToNext()) {
        ((List)localObject).add(localCursor.getString(0));
      }
      localObject = ((List)localObject).iterator();
    }
    finally
    {
      localCursor.close();
    }
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      if (!str.startsWith("sqlite_")) {
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
      }
    }
    localCursor.close();
    onCreate(paramSQLiteDatabase);
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    try
    {
      internalOnUpgrade(paramSQLiteDatabase, paramInt1, paramInt2);
      return;
    }
    catch (SQLiteException paramSQLiteDatabase)
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error in upgrade, migration may have already run! Skipping!", paramSQLiteDatabase);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OneSignalDbHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */