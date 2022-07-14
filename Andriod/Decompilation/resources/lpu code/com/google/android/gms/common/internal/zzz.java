package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.internal.zzadf;
import com.google.android.gms.internal.zzadg;

public class zzz
{
  private static boolean zzQm;
  private static String zzaGt;
  private static int zzaGu;
  private static Object zztX = new Object();
  
  public static String zzaV(Context paramContext)
  {
    zzaX(paramContext);
    return zzaGt;
  }
  
  public static int zzaW(Context paramContext)
  {
    zzaX(paramContext);
    return zzaGu;
  }
  
  private static void zzaX(Context paramContext)
  {
    String str;
    synchronized (zztX)
    {
      if (zzQm) {
        return;
      }
      zzQm = true;
      str = paramContext.getPackageName();
      paramContext = zzadg.zzbi(paramContext);
    }
    try
    {
      paramContext = paramContext.getApplicationInfo(str, 128).metaData;
      if (paramContext == null)
      {
        return;
        paramContext = finally;
        throw paramContext;
      }
      zzaGt = paramContext.getString("com.google.app.id");
      zzaGu = paramContext.getInt("com.google.android.gms.version");
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        Log.wtf("MetadataValueReader", "This should never happen.", paramContext);
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\zzz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */