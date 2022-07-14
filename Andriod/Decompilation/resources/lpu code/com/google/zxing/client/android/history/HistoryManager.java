package com.google.zxing.client.android.history;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.client.android.result.ResultHandler;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class HistoryManager
{
  private static final String[] COLUMNS = { "text", "display", "format", "timestamp", "details" };
  private static final String[] COUNT_COLUMN = { "COUNT(1)" };
  private static final String[] ID_COL_PROJECTION = { "id" };
  private static final String[] ID_DETAIL_COL_PROJECTION = { "id", "details" };
  private static final int MAX_ITEMS = 2000;
  private static final String TAG = HistoryManager.class.getSimpleName();
  private final Activity activity;
  private final boolean enableHistory;
  
  public HistoryManager(Activity paramActivity)
  {
    this.activity = paramActivity;
    this.enableHistory = PreferenceManager.getDefaultSharedPreferences(paramActivity).getBoolean("preferences_history", true);
  }
  
  private static void close(Cursor paramCursor, SQLiteDatabase paramSQLiteDatabase)
  {
    if (paramCursor != null) {
      paramCursor.close();
    }
    if (paramSQLiteDatabase != null) {
      paramSQLiteDatabase.close();
    }
  }
  
  private void deletePrevious(String paramString)
  {
    Object localObject2 = new DBHelper(this.activity);
    Object localObject1 = null;
    try
    {
      localObject2 = ((SQLiteOpenHelper)localObject2).getWritableDatabase();
      localObject1 = localObject2;
      ((SQLiteDatabase)localObject2).delete("history", "text=?", new String[] { paramString });
      return;
    }
    finally
    {
      close(null, (SQLiteDatabase)localObject1);
    }
  }
  
  private static String massageHistoryField(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    return paramString.replace("\"", "\"\"");
  }
  
  /* Error */
  static android.net.Uri saveHistory(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 125	java/io/File
    //   5: dup
    //   6: new 125	java/io/File
    //   9: dup
    //   10: invokestatic 131	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   13: ldc -123
    //   15: invokespecial 136	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   18: ldc -118
    //   20: invokespecial 136	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   23: astore_1
    //   24: aload_1
    //   25: invokevirtual 142	java/io/File:exists	()Z
    //   28: ifne +40 -> 68
    //   31: aload_1
    //   32: invokevirtual 145	java/io/File:mkdirs	()Z
    //   35: ifne +33 -> 68
    //   38: getstatic 28	com/google/zxing/client/android/history/HistoryManager:TAG	Ljava/lang/String;
    //   41: new 147	java/lang/StringBuilder
    //   44: dup
    //   45: invokespecial 148	java/lang/StringBuilder:<init>	()V
    //   48: ldc -106
    //   50: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: aload_1
    //   54: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   57: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   60: invokestatic 166	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   63: pop
    //   64: aload_3
    //   65: astore_1
    //   66: aload_1
    //   67: areturn
    //   68: new 125	java/io/File
    //   71: dup
    //   72: aload_1
    //   73: new 147	java/lang/StringBuilder
    //   76: dup
    //   77: invokespecial 148	java/lang/StringBuilder:<init>	()V
    //   80: ldc -88
    //   82: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: invokestatic 174	java/lang/System:currentTimeMillis	()J
    //   88: invokevirtual 177	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   91: ldc -77
    //   93: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   99: invokespecial 136	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   102: astore 5
    //   104: aconst_null
    //   105: astore_1
    //   106: aconst_null
    //   107: astore 4
    //   109: new 181	java/io/OutputStreamWriter
    //   112: dup
    //   113: new 183	java/io/FileOutputStream
    //   116: dup
    //   117: aload 5
    //   119: invokespecial 186	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   122: ldc -68
    //   124: invokestatic 194	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   127: invokespecial 197	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/nio/charset/Charset;)V
    //   130: astore_2
    //   131: aload_2
    //   132: aload_0
    //   133: invokevirtual 200	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   136: new 147	java/lang/StringBuilder
    //   139: dup
    //   140: invokespecial 148	java/lang/StringBuilder:<init>	()V
    //   143: ldc -54
    //   145: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: aload 5
    //   150: invokevirtual 205	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   153: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   159: invokestatic 210	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   162: astore_0
    //   163: aload_0
    //   164: astore_1
    //   165: aload_2
    //   166: ifnull -100 -> 66
    //   169: aload_2
    //   170: invokevirtual 211	java/io/OutputStreamWriter:close	()V
    //   173: aload_0
    //   174: areturn
    //   175: astore_1
    //   176: aload_0
    //   177: areturn
    //   178: astore_2
    //   179: aload 4
    //   181: astore_0
    //   182: aload_0
    //   183: astore_1
    //   184: getstatic 28	com/google/zxing/client/android/history/HistoryManager:TAG	Ljava/lang/String;
    //   187: new 147	java/lang/StringBuilder
    //   190: dup
    //   191: invokespecial 148	java/lang/StringBuilder:<init>	()V
    //   194: ldc -43
    //   196: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: aload 5
    //   201: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   204: ldc -41
    //   206: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   209: aload_2
    //   210: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   213: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   216: invokestatic 166	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   219: pop
    //   220: aload_3
    //   221: astore_1
    //   222: aload_0
    //   223: ifnull -157 -> 66
    //   226: aload_0
    //   227: invokevirtual 211	java/io/OutputStreamWriter:close	()V
    //   230: aconst_null
    //   231: areturn
    //   232: astore_0
    //   233: aconst_null
    //   234: areturn
    //   235: astore_0
    //   236: aload_1
    //   237: ifnull +7 -> 244
    //   240: aload_1
    //   241: invokevirtual 211	java/io/OutputStreamWriter:close	()V
    //   244: aload_0
    //   245: athrow
    //   246: astore_1
    //   247: goto -3 -> 244
    //   250: astore_0
    //   251: aload_2
    //   252: astore_1
    //   253: goto -17 -> 236
    //   256: astore_1
    //   257: aload_2
    //   258: astore_0
    //   259: aload_1
    //   260: astore_2
    //   261: goto -79 -> 182
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	264	0	paramString	String
    //   23	142	1	localObject1	Object
    //   175	1	1	localIOException1	java.io.IOException
    //   183	58	1	localObject2	Object
    //   246	1	1	localIOException2	java.io.IOException
    //   252	1	1	localObject3	Object
    //   256	4	1	localIOException3	java.io.IOException
    //   130	40	2	localOutputStreamWriter	java.io.OutputStreamWriter
    //   178	80	2	localIOException4	java.io.IOException
    //   260	1	2	localIOException5	java.io.IOException
    //   1	220	3	localObject4	Object
    //   107	73	4	localObject5	Object
    //   102	98	5	localFile	java.io.File
    // Exception table:
    //   from	to	target	type
    //   169	173	175	java/io/IOException
    //   109	131	178	java/io/IOException
    //   226	230	232	java/io/IOException
    //   109	131	235	finally
    //   184	220	235	finally
    //   240	244	246	java/io/IOException
    //   131	163	250	finally
    //   131	163	256	java/io/IOException
  }
  
  public void addHistoryItem(Result paramResult, ResultHandler paramResultHandler)
  {
    if ((!this.activity.getIntent().getBooleanExtra("SAVE_HISTORY", true)) || (paramResultHandler.areContentsSecure()) || (!this.enableHistory)) {
      return;
    }
    if (!PreferenceManager.getDefaultSharedPreferences(this.activity).getBoolean("preferences_remember_duplicates", false)) {
      deletePrevious(paramResult.getText());
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("text", paramResult.getText());
    localContentValues.put("format", paramResult.getBarcodeFormat().toString());
    localContentValues.put("display", paramResultHandler.getDisplayContents().toString());
    localContentValues.put("timestamp", Long.valueOf(System.currentTimeMillis()));
    paramResultHandler = new DBHelper(this.activity);
    paramResult = null;
    try
    {
      paramResultHandler = paramResultHandler.getWritableDatabase();
      paramResult = paramResultHandler;
      paramResultHandler.insert("history", "timestamp", localContentValues);
      return;
    }
    finally
    {
      close(null, paramResult);
    }
  }
  
  public void addHistoryItemDetails(String paramString1, String paramString2)
  {
    Object localObject3 = new DBHelper(this.activity);
    Object localObject1 = null;
    String str = null;
    Object localObject2 = str;
    for (;;)
    {
      try
      {
        localObject3 = ((SQLiteOpenHelper)localObject3).getWritableDatabase();
        localObject1 = localObject3;
        localObject2 = str;
        localCursor = ((SQLiteDatabase)localObject3).query("history", ID_DETAIL_COL_PROJECTION, "text=?", new String[] { paramString1 }, null, null, "timestamp DESC", "1");
        str = null;
        paramString1 = null;
        localObject1 = localObject3;
        localObject2 = localCursor;
        if (!localCursor.moveToNext()) {
          break label257;
        }
        localObject1 = localObject3;
        localObject2 = localCursor;
        str = localCursor.getString(0);
        localObject1 = localObject3;
        localObject2 = localCursor;
        paramString1 = localCursor.getString(1);
      }
      finally
      {
        Cursor localCursor;
        close((Cursor)localObject2, (SQLiteDatabase)localObject1);
      }
      if (paramString1 != null)
      {
        localObject1 = localObject3;
        localObject2 = localCursor;
        paramString2 = new ContentValues();
        localObject1 = localObject3;
        localObject2 = localCursor;
        paramString2.put("details", paramString1);
        localObject1 = localObject3;
        localObject2 = localCursor;
        ((SQLiteDatabase)localObject3).update("history", paramString2, "id=?", new String[] { str });
      }
      close(localCursor, (SQLiteDatabase)localObject3);
      return;
      localObject1 = localObject3;
      localObject2 = localCursor;
      if (paramString1.contains(paramString2))
      {
        paramString1 = null;
      }
      else
      {
        localObject1 = localObject3;
        localObject2 = localCursor;
        paramString1 = paramString1 + " : " + paramString2;
        continue;
        label257:
        if (str != null) {
          if (paramString1 == null) {
            paramString1 = paramString2;
          }
        }
      }
    }
  }
  
  CharSequence buildHistory()
  {
    Object localObject2 = new DBHelper(this.activity);
    Object localObject1 = null;
    Cursor localCursor2 = null;
    Cursor localCursor1 = localCursor2;
    StringBuilder localStringBuilder;
    try
    {
      localObject2 = ((SQLiteOpenHelper)localObject2).getWritableDatabase();
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      localCursor2 = ((SQLiteDatabase)localObject2).query("history", COLUMNS, null, null, null, null, "timestamp DESC");
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      DateFormat localDateFormat = DateFormat.getDateTimeInstance(2, 2);
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      localStringBuilder = new StringBuilder(1000);
      for (;;)
      {
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        if (!localCursor2.moveToNext()) {
          break;
        }
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        localStringBuilder.append('"').append(massageHistoryField(localCursor2.getString(0))).append("\",");
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        localStringBuilder.append('"').append(massageHistoryField(localCursor2.getString(1))).append("\",");
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        localStringBuilder.append('"').append(massageHistoryField(localCursor2.getString(2))).append("\",");
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        localStringBuilder.append('"').append(massageHistoryField(localCursor2.getString(3))).append("\",");
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        long l = localCursor2.getLong(3);
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        localStringBuilder.append('"').append(massageHistoryField(localDateFormat.format(new Date(l)))).append("\",");
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        localStringBuilder.append('"').append(massageHistoryField(localCursor2.getString(4))).append("\"\r\n");
      }
      close(localCursor2, localSQLiteDatabase);
    }
    finally
    {
      close(localCursor1, (SQLiteDatabase)localObject1);
    }
    return localStringBuilder;
  }
  
  public HistoryItem buildHistoryItem(int paramInt)
  {
    Object localObject2 = new DBHelper(this.activity);
    Object localObject1 = null;
    Cursor localCursor2 = null;
    Cursor localCursor1 = localCursor2;
    try
    {
      localObject2 = ((SQLiteOpenHelper)localObject2).getReadableDatabase();
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      localCursor2 = ((SQLiteDatabase)localObject2).query("history", COLUMNS, null, null, null, null, "timestamp DESC");
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      localCursor2.move(paramInt + 1);
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      Object localObject4 = localCursor2.getString(0);
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      String str1 = localCursor2.getString(1);
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      String str2 = localCursor2.getString(2);
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      long l = localCursor2.getLong(3);
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      String str3 = localCursor2.getString(4);
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      localObject4 = new HistoryItem(new Result((String)localObject4, null, null, BarcodeFormat.valueOf(str2), l), str1, str3);
      return (HistoryItem)localObject4;
    }
    finally
    {
      close(localCursor1, (SQLiteDatabase)localObject1);
    }
  }
  
  public List<HistoryItem> buildHistoryItems()
  {
    Object localObject2 = new DBHelper(this.activity);
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = null;
    Cursor localCursor2 = null;
    Cursor localCursor1 = localCursor2;
    try
    {
      localObject2 = ((SQLiteOpenHelper)localObject2).getReadableDatabase();
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      localCursor2 = ((SQLiteDatabase)localObject2).query("history", COLUMNS, null, null, null, null, "timestamp DESC");
      for (;;)
      {
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        if (!localCursor2.moveToNext()) {
          break;
        }
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        String str1 = localCursor2.getString(0);
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        String str2 = localCursor2.getString(1);
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        String str3 = localCursor2.getString(2);
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        long l = localCursor2.getLong(3);
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        String str4 = localCursor2.getString(4);
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        localArrayList.add(new HistoryItem(new Result(str1, null, null, BarcodeFormat.valueOf(str3), l), str2, str4));
      }
      close(localCursor2, localSQLiteDatabase);
    }
    finally
    {
      close(localCursor1, (SQLiteDatabase)localObject1);
    }
    return localArrayList;
  }
  
  void clearHistory()
  {
    Object localObject2 = new DBHelper(this.activity);
    Object localObject1 = null;
    try
    {
      localObject2 = ((SQLiteOpenHelper)localObject2).getWritableDatabase();
      localObject1 = localObject2;
      ((SQLiteDatabase)localObject2).delete("history", null, null);
      return;
    }
    finally
    {
      close(null, (SQLiteDatabase)localObject1);
    }
  }
  
  public void deleteHistoryItem(int paramInt)
  {
    Object localObject3 = new DBHelper(this.activity);
    Object localObject1 = null;
    Cursor localCursor2 = null;
    Cursor localCursor1 = localCursor2;
    try
    {
      localObject3 = ((SQLiteOpenHelper)localObject3).getWritableDatabase();
      localObject1 = localObject3;
      localCursor1 = localCursor2;
      localCursor2 = ((SQLiteDatabase)localObject3).query("history", ID_COL_PROJECTION, null, null, null, null, "timestamp DESC");
      localObject1 = localObject3;
      localCursor1 = localCursor2;
      localCursor2.move(paramInt + 1);
      localObject1 = localObject3;
      localCursor1 = localCursor2;
      ((SQLiteDatabase)localObject3).delete("history", "id=" + localCursor2.getString(0), null);
      return;
    }
    finally
    {
      close(localCursor1, (SQLiteDatabase)localObject1);
    }
  }
  
  /* Error */
  public boolean hasHistoryItems()
  {
    // Byte code:
    //   0: new 88	com/google/zxing/client/android/history/DBHelper
    //   3: dup
    //   4: aload_0
    //   5: getfield 59	com/google/zxing/client/android/history/HistoryManager:activity	Landroid/app/Activity;
    //   8: invokespecial 91	com/google/zxing/client/android/history/DBHelper:<init>	(Landroid/content/Context;)V
    //   11: astore 6
    //   13: aconst_null
    //   14: astore_3
    //   15: aconst_null
    //   16: astore 5
    //   18: aload 5
    //   20: astore 4
    //   22: aload 6
    //   24: invokevirtual 345	android/database/sqlite/SQLiteOpenHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   27: astore 6
    //   29: aload 6
    //   31: astore_3
    //   32: aload 5
    //   34: astore 4
    //   36: aload 6
    //   38: ldc 99
    //   40: getstatic 46	com/google/zxing/client/android/history/HistoryManager:COUNT_COLUMN	[Ljava/lang/String;
    //   43: aconst_null
    //   44: aconst_null
    //   45: aconst_null
    //   46: aconst_null
    //   47: aconst_null
    //   48: invokevirtual 310	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   51: astore 5
    //   53: aload 6
    //   55: astore_3
    //   56: aload 5
    //   58: astore 4
    //   60: aload 5
    //   62: invokeinterface 381 1 0
    //   67: pop
    //   68: aload 6
    //   70: astore_3
    //   71: aload 5
    //   73: astore 4
    //   75: aload 5
    //   77: iconst_0
    //   78: invokeinterface 385 2 0
    //   83: istore_1
    //   84: iload_1
    //   85: ifle +14 -> 99
    //   88: iconst_1
    //   89: istore_2
    //   90: aload 5
    //   92: aload 6
    //   94: invokestatic 107	com/google/zxing/client/android/history/HistoryManager:close	(Landroid/database/Cursor;Landroid/database/sqlite/SQLiteDatabase;)V
    //   97: iload_2
    //   98: ireturn
    //   99: iconst_0
    //   100: istore_2
    //   101: goto -11 -> 90
    //   104: astore 5
    //   106: aload 4
    //   108: aload_3
    //   109: invokestatic 107	com/google/zxing/client/android/history/HistoryManager:close	(Landroid/database/Cursor;Landroid/database/sqlite/SQLiteDatabase;)V
    //   112: aload 5
    //   114: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	115	0	this	HistoryManager
    //   83	2	1	i	int
    //   89	12	2	bool	boolean
    //   14	95	3	localObject1	Object
    //   20	87	4	localCursor1	Cursor
    //   16	75	5	localCursor2	Cursor
    //   104	9	5	localObject2	Object
    //   11	82	6	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   22	29	104	finally
    //   36	53	104	finally
    //   60	68	104	finally
    //   75	84	104	finally
  }
  
  /* Error */
  public void trimHistory()
  {
    // Byte code:
    //   0: new 88	com/google/zxing/client/android/history/DBHelper
    //   3: dup
    //   4: aload_0
    //   5: getfield 59	com/google/zxing/client/android/history/HistoryManager:activity	Landroid/app/Activity;
    //   8: invokespecial 91	com/google/zxing/client/android/history/DBHelper:<init>	(Landroid/content/Context;)V
    //   11: astore 5
    //   13: aconst_null
    //   14: astore_3
    //   15: aconst_null
    //   16: astore_1
    //   17: aconst_null
    //   18: astore 7
    //   20: aconst_null
    //   21: astore 6
    //   23: aload 6
    //   25: astore_2
    //   26: aload 7
    //   28: astore 4
    //   30: aload 5
    //   32: invokevirtual 97	android/database/sqlite/SQLiteOpenHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   35: astore 5
    //   37: aload 5
    //   39: astore_1
    //   40: aload 6
    //   42: astore_2
    //   43: aload 5
    //   45: astore_3
    //   46: aload 7
    //   48: astore 4
    //   50: aload 5
    //   52: ldc 99
    //   54: getstatic 50	com/google/zxing/client/android/history/HistoryManager:ID_COL_PROJECTION	[Ljava/lang/String;
    //   57: aconst_null
    //   58: aconst_null
    //   59: aconst_null
    //   60: aconst_null
    //   61: ldc_w 281
    //   64: invokevirtual 310	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   67: astore 6
    //   69: aload 5
    //   71: astore_1
    //   72: aload 6
    //   74: astore_2
    //   75: aload 5
    //   77: astore_3
    //   78: aload 6
    //   80: astore 4
    //   82: aload 6
    //   84: sipush 2000
    //   87: invokeinterface 349 2 0
    //   92: pop
    //   93: aload 5
    //   95: astore_1
    //   96: aload 6
    //   98: astore_2
    //   99: aload 5
    //   101: astore_3
    //   102: aload 6
    //   104: astore 4
    //   106: aload 6
    //   108: invokeinterface 290 1 0
    //   113: ifeq +135 -> 248
    //   116: aload 5
    //   118: astore_1
    //   119: aload 6
    //   121: astore_2
    //   122: aload 5
    //   124: astore_3
    //   125: aload 6
    //   127: astore 4
    //   129: aload 6
    //   131: iconst_0
    //   132: invokeinterface 294 2 0
    //   137: astore 7
    //   139: aload 5
    //   141: astore_1
    //   142: aload 6
    //   144: astore_2
    //   145: aload 5
    //   147: astore_3
    //   148: aload 6
    //   150: astore 4
    //   152: getstatic 28	com/google/zxing/client/android/history/HistoryManager:TAG	Ljava/lang/String;
    //   155: new 147	java/lang/StringBuilder
    //   158: dup
    //   159: invokespecial 148	java/lang/StringBuilder:<init>	()V
    //   162: ldc_w 390
    //   165: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   168: aload 7
    //   170: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   176: invokestatic 393	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   179: pop
    //   180: aload 5
    //   182: astore_1
    //   183: aload 6
    //   185: astore_2
    //   186: aload 5
    //   188: astore_3
    //   189: aload 6
    //   191: astore 4
    //   193: aload 5
    //   195: ldc 99
    //   197: new 147	java/lang/StringBuilder
    //   200: dup
    //   201: invokespecial 148	java/lang/StringBuilder:<init>	()V
    //   204: ldc_w 377
    //   207: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   210: aload 7
    //   212: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   218: aconst_null
    //   219: invokevirtual 105	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   222: pop
    //   223: goto -130 -> 93
    //   226: astore 5
    //   228: aload_1
    //   229: astore_3
    //   230: aload_2
    //   231: astore 4
    //   233: getstatic 28	com/google/zxing/client/android/history/HistoryManager:TAG	Ljava/lang/String;
    //   236: aload 5
    //   238: invokestatic 396	android/util/Log:w	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   241: pop
    //   242: aload_2
    //   243: aload_1
    //   244: invokestatic 107	com/google/zxing/client/android/history/HistoryManager:close	(Landroid/database/Cursor;Landroid/database/sqlite/SQLiteDatabase;)V
    //   247: return
    //   248: aload 6
    //   250: aload 5
    //   252: invokestatic 107	com/google/zxing/client/android/history/HistoryManager:close	(Landroid/database/Cursor;Landroid/database/sqlite/SQLiteDatabase;)V
    //   255: return
    //   256: astore_1
    //   257: aload 4
    //   259: aload_3
    //   260: invokestatic 107	com/google/zxing/client/android/history/HistoryManager:close	(Landroid/database/Cursor;Landroid/database/sqlite/SQLiteDatabase;)V
    //   263: aload_1
    //   264: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	265	0	this	HistoryManager
    //   16	228	1	localObject1	Object
    //   256	8	1	localObject2	Object
    //   25	218	2	localCursor1	Cursor
    //   14	246	3	localObject3	Object
    //   28	230	4	localObject4	Object
    //   11	183	5	localObject5	Object
    //   226	25	5	localSQLiteException	android.database.sqlite.SQLiteException
    //   21	228	6	localCursor2	Cursor
    //   18	193	7	str	String
    // Exception table:
    //   from	to	target	type
    //   30	37	226	android/database/sqlite/SQLiteException
    //   50	69	226	android/database/sqlite/SQLiteException
    //   82	93	226	android/database/sqlite/SQLiteException
    //   106	116	226	android/database/sqlite/SQLiteException
    //   129	139	226	android/database/sqlite/SQLiteException
    //   152	180	226	android/database/sqlite/SQLiteException
    //   193	223	226	android/database/sqlite/SQLiteException
    //   30	37	256	finally
    //   50	69	256	finally
    //   82	93	256	finally
    //   106	116	256	finally
    //   129	139	256	finally
    //   152	180	256	finally
    //   193	223	256	finally
    //   233	242	256	finally
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\history\HistoryManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */