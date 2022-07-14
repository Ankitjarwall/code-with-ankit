package com.google.zxing.client.android.history;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

final class DBHelper
  extends SQLiteOpenHelper
{
  private static final String DB_NAME = "barcode_scanner_history.db";
  private static final int DB_VERSION = 5;
  static final String DETAILS_COL = "details";
  static final String DISPLAY_COL = "display";
  static final String FORMAT_COL = "format";
  static final String ID_COL = "id";
  static final String TABLE_NAME = "history";
  static final String TEXT_COL = "text";
  static final String TIMESTAMP_COL = "timestamp";
  
  DBHelper(Context paramContext)
  {
    super(paramContext, "barcode_scanner_history.db", null, 5);
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE history (id INTEGER PRIMARY KEY, text TEXT, format TEXT, display TEXT, timestamp INTEGER, details TEXT);");
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS history");
    onCreate(paramSQLiteDatabase);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\history\DBHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */