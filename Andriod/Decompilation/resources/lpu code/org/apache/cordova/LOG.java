package org.apache.cordova;

import android.util.Log;

public class LOG
{
  public static final int DEBUG = 3;
  public static final int ERROR = 6;
  public static final int INFO = 4;
  public static int LOGLEVEL = 6;
  public static final int VERBOSE = 2;
  public static final int WARN = 5;
  
  public static void d(String paramString1, String paramString2)
  {
    if (3 >= LOGLEVEL) {
      Log.d(paramString1, paramString2);
    }
  }
  
  public static void d(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (3 >= LOGLEVEL) {
      Log.d(paramString1, paramString2, paramThrowable);
    }
  }
  
  public static void d(String paramString1, String paramString2, Object... paramVarArgs)
  {
    if (3 >= LOGLEVEL) {
      Log.d(paramString1, String.format(paramString2, paramVarArgs));
    }
  }
  
  public static void e(String paramString1, String paramString2)
  {
    if (6 >= LOGLEVEL) {
      Log.e(paramString1, paramString2);
    }
  }
  
  public static void e(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (6 >= LOGLEVEL) {
      Log.e(paramString1, paramString2, paramThrowable);
    }
  }
  
  public static void e(String paramString1, String paramString2, Object... paramVarArgs)
  {
    if (6 >= LOGLEVEL) {
      Log.e(paramString1, String.format(paramString2, paramVarArgs));
    }
  }
  
  public static void i(String paramString1, String paramString2)
  {
    if (4 >= LOGLEVEL) {
      Log.i(paramString1, paramString2);
    }
  }
  
  public static void i(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (4 >= LOGLEVEL) {
      Log.i(paramString1, paramString2, paramThrowable);
    }
  }
  
  public static void i(String paramString1, String paramString2, Object... paramVarArgs)
  {
    if (4 >= LOGLEVEL) {
      Log.i(paramString1, String.format(paramString2, paramVarArgs));
    }
  }
  
  public static boolean isLoggable(int paramInt)
  {
    return paramInt >= LOGLEVEL;
  }
  
  public static void setLogLevel(int paramInt)
  {
    LOGLEVEL = paramInt;
    Log.i("CordovaLog", "Changing log level to " + paramInt);
  }
  
  public static void setLogLevel(String paramString)
  {
    if ("VERBOSE".equals(paramString)) {
      LOGLEVEL = 2;
    }
    for (;;)
    {
      Log.i("CordovaLog", "Changing log level to " + paramString + "(" + LOGLEVEL + ")");
      return;
      if ("DEBUG".equals(paramString)) {
        LOGLEVEL = 3;
      } else if ("INFO".equals(paramString)) {
        LOGLEVEL = 4;
      } else if ("WARN".equals(paramString)) {
        LOGLEVEL = 5;
      } else if ("ERROR".equals(paramString)) {
        LOGLEVEL = 6;
      }
    }
  }
  
  public static void v(String paramString1, String paramString2)
  {
    if (2 >= LOGLEVEL) {
      Log.v(paramString1, paramString2);
    }
  }
  
  public static void v(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (2 >= LOGLEVEL) {
      Log.v(paramString1, paramString2, paramThrowable);
    }
  }
  
  public static void v(String paramString1, String paramString2, Object... paramVarArgs)
  {
    if (2 >= LOGLEVEL) {
      Log.v(paramString1, String.format(paramString2, paramVarArgs));
    }
  }
  
  public static void w(String paramString1, String paramString2)
  {
    if (5 >= LOGLEVEL) {
      Log.w(paramString1, paramString2);
    }
  }
  
  public static void w(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (5 >= LOGLEVEL) {
      Log.w(paramString1, paramString2, paramThrowable);
    }
  }
  
  public static void w(String paramString1, String paramString2, Object... paramVarArgs)
  {
    if (5 >= LOGLEVEL) {
      Log.w(paramString1, String.format(paramString2, paramVarArgs));
    }
  }
  
  public static void w(String paramString, Throwable paramThrowable)
  {
    if (5 >= LOGLEVEL) {
      Log.w(paramString, paramThrowable);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\LOG.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */