package com.onesignal.shortcutbadger.util;

import android.database.Cursor;
import java.io.Closeable;
import java.io.IOException;

public class CloseHelper
{
  public static void close(Cursor paramCursor)
  {
    if ((paramCursor != null) && (!paramCursor.isClosed())) {
      paramCursor.close();
    }
  }
  
  public static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable) {}
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\shortcutbadger\util\CloseHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */