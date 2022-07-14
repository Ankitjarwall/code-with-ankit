package com.google.android.gms.common.stats;

import android.os.PowerManager.WakeLock;
import android.os.Process;
import android.text.TextUtils;
import java.util.List;

public class zzc
{
  static List<String> zzB(List<String> paramList)
  {
    List<String> localList = paramList;
    if (paramList != null)
    {
      localList = paramList;
      if (paramList.size() == 1)
      {
        localList = paramList;
        if ("com.google.android.gms".equals(paramList.get(0))) {
          localList = null;
        }
      }
    }
    return localList;
  }
  
  public static String zza(PowerManager.WakeLock paramWakeLock, String paramString)
  {
    String str = String.valueOf(String.valueOf(Process.myPid() << 32 | System.identityHashCode(paramWakeLock)));
    paramWakeLock = paramString;
    if (TextUtils.isEmpty(paramString)) {
      paramWakeLock = "";
    }
    paramWakeLock = String.valueOf(paramWakeLock);
    if (paramWakeLock.length() != 0) {
      return str.concat(paramWakeLock);
    }
    return new String(str);
  }
  
  static String zzdx(String paramString)
  {
    String str = paramString;
    if ("com.google.android.gms".equals(paramString)) {
      str = null;
    }
    return str;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\stats\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */