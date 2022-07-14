package com.google.android.gms.common.internal;

import android.util.Log;

public final class zzq
{
  public static final int zzaGp = 23 - " PII_LOG".length();
  private static final String zzaGq = null;
  private final String zzaGr;
  private final String zzaGs;
  
  public zzq(String paramString)
  {
    this(paramString, null);
  }
  
  public zzq(String paramString1, String paramString2)
  {
    zzac.zzb(paramString1, "log tag cannot be null");
    if (paramString1.length() <= 23) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzb(bool, "tag \"%s\" is longer than the %d character maximum", new Object[] { paramString1, Integer.valueOf(23) });
      this.zzaGr = paramString1;
      if ((paramString2 != null) && (paramString2.length() > 0)) {
        break;
      }
      this.zzaGs = null;
      return;
    }
    this.zzaGs = paramString2;
  }
  
  private String zzdq(String paramString)
  {
    if (this.zzaGs == null) {
      return paramString;
    }
    return this.zzaGs.concat(paramString);
  }
  
  public void zzE(String paramString1, String paramString2)
  {
    if (zzcW(3)) {
      Log.d(paramString1, zzdq(paramString2));
    }
  }
  
  public void zzF(String paramString1, String paramString2)
  {
    if (zzcW(5)) {
      Log.w(paramString1, zzdq(paramString2));
    }
  }
  
  public void zzG(String paramString1, String paramString2)
  {
    if (zzcW(6)) {
      Log.e(paramString1, zzdq(paramString2));
    }
  }
  
  public void zzb(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (zzcW(4)) {
      Log.i(paramString1, zzdq(paramString2), paramThrowable);
    }
  }
  
  public void zzc(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (zzcW(5)) {
      Log.w(paramString1, zzdq(paramString2), paramThrowable);
    }
  }
  
  public boolean zzcW(int paramInt)
  {
    return Log.isLoggable(this.zzaGr, paramInt);
  }
  
  public void zzd(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (zzcW(6)) {
      Log.e(paramString1, zzdq(paramString2), paramThrowable);
    }
  }
  
  public void zze(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (zzcW(7))
    {
      Log.e(paramString1, zzdq(paramString2), paramThrowable);
      Log.wtf(paramString1, zzdq(paramString2), paramThrowable);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */