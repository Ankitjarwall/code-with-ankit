package com.google.android.gms.common.util;

import android.content.Context;
import android.os.DropBoxManager;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;

public final class zzg
{
  private static final String[] zzaHT = { "android.", "com.android.", "dalvik.", "java.", "javax." };
  private static DropBoxManager zzaHU = null;
  private static boolean zzaHV = false;
  private static int zzaHW = -1;
  private static int zzaHX = 0;
  
  public static boolean zza(Context paramContext, Throwable paramThrowable)
  {
    try
    {
      zzac.zzw(paramContext);
      zzac.zzw(paramThrowable);
      return false;
    }
    catch (Exception paramContext)
    {
      try
      {
        bool = zzzb();
        if (bool) {
          throw paramContext;
        }
      }
      catch (Exception paramThrowable)
      {
        for (;;)
        {
          Log.e("CrashUtils", "Error determining which process we're running in!", paramThrowable);
          boolean bool = false;
        }
        Log.e("CrashUtils", "Error adding exception to DropBox!", paramContext);
      }
    }
    return false;
  }
  
  private static boolean zzzb()
  {
    return false;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\util\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */