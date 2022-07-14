package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.stats.zzc;
import com.google.android.gms.common.stats.zze;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.common.util.zzw;
import com.google.android.gms.common.util.zzz;

public class zzbay
{
  private static boolean DEBUG = false;
  private static String TAG = "WakeLock";
  private static String zzbEy = "*gcore*:";
  private final Context mContext;
  private final String zzaHF;
  private final String zzaHH;
  private final int zzbEA;
  private final String zzbEB;
  private boolean zzbEC = true;
  private int zzbED;
  private int zzbEE;
  private final PowerManager.WakeLock zzbEz;
  private WorkSource zzbjl;
  
  public zzbay(Context paramContext, int paramInt, String paramString) {}
  
  @SuppressLint({"UnwrappedWakeLock"})
  public zzbay(Context paramContext, int paramInt, String paramString1, String paramString2, String paramString3)
  {
    this(paramContext, paramInt, paramString1, paramString2, paramString3, null);
  }
  
  @SuppressLint({"UnwrappedWakeLock"})
  public zzbay(Context paramContext, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    zzac.zzh(paramString1, "Wake lock name can NOT be empty");
    this.zzbEA = paramInt;
    this.zzbEB = paramString2;
    this.zzaHH = paramString4;
    this.mContext = paramContext.getApplicationContext();
    if (!"com.google.android.gms".equals(paramContext.getPackageName()))
    {
      paramString2 = String.valueOf(zzbEy);
      paramString4 = String.valueOf(paramString1);
      if (paramString4.length() != 0) {
        paramString2 = paramString2.concat(paramString4);
      }
    }
    for (this.zzaHF = paramString2;; this.zzaHF = paramString1)
    {
      this.zzbEz = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(paramInt, paramString1);
      if (zzz.zzbf(this.mContext))
      {
        paramString1 = paramString3;
        if (zzw.zzdz(paramString3)) {
          paramString1 = paramContext.getPackageName();
        }
        this.zzbjl = zzz.zzG(paramContext, paramString1);
        zzc(this.zzbjl);
      }
      return;
      paramString2 = new String(paramString2);
      break;
    }
  }
  
  private void zzd(WorkSource paramWorkSource)
  {
    try
    {
      this.zzbEz.setWorkSource(paramWorkSource);
      return;
    }
    catch (IllegalArgumentException paramWorkSource)
    {
      Log.wtf(TAG, paramWorkSource.toString());
    }
  }
  
  private void zzgM(String paramString)
  {
    boolean bool = zzgN(paramString);
    paramString = zzo(paramString, bool);
    try
    {
      if (this.zzbEC)
      {
        int i = this.zzbED - 1;
        this.zzbED = i;
        if ((i == 0) || (bool)) {}
      }
      else
      {
        if ((this.zzbEC) || (this.zzbEE != 1)) {
          break label107;
        }
      }
      zze.zzyX().zza(this.mContext, zzc.zza(this.zzbEz, paramString), 8, this.zzaHF, paramString, this.zzaHH, this.zzbEA, zzz.zzb(this.zzbjl));
      this.zzbEE -= 1;
      label107:
      return;
    }
    finally {}
  }
  
  private boolean zzgN(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (!paramString.equals(this.zzbEB));
  }
  
  private String zzo(String paramString, boolean paramBoolean)
  {
    if (this.zzbEC)
    {
      if (paramBoolean) {
        return paramString;
      }
      return this.zzbEB;
    }
    return this.zzbEB;
  }
  
  private void zzo(String paramString, long paramLong)
  {
    boolean bool = zzgN(paramString);
    paramString = zzo(paramString, bool);
    try
    {
      if (this.zzbEC)
      {
        int i = this.zzbED;
        this.zzbED = (i + 1);
        if ((i == 0) || (bool)) {}
      }
      else
      {
        if ((this.zzbEC) || (this.zzbEE != 0)) {
          break label113;
        }
      }
      zze.zzyX().zza(this.mContext, zzc.zza(this.zzbEz, paramString), 7, this.zzaHF, paramString, this.zzaHH, this.zzbEA, zzz.zzb(this.zzbjl), paramLong);
      this.zzbEE += 1;
      label113:
      return;
    }
    finally {}
  }
  
  public void acquire(long paramLong)
  {
    zzt.zzzg();
    zzo(null, paramLong);
    this.zzbEz.acquire(paramLong);
  }
  
  public boolean isHeld()
  {
    return this.zzbEz.isHeld();
  }
  
  public void release()
  {
    zzgM(null);
    this.zzbEz.release();
  }
  
  public void setReferenceCounted(boolean paramBoolean)
  {
    this.zzbEz.setReferenceCounted(paramBoolean);
    this.zzbEC = paramBoolean;
  }
  
  public void zzc(WorkSource paramWorkSource)
  {
    if ((paramWorkSource != null) && (zzz.zzbf(this.mContext)))
    {
      if (this.zzbjl == null) {
        break label39;
      }
      this.zzbjl.add(paramWorkSource);
    }
    for (;;)
    {
      zzd(this.zzbjl);
      return;
      label39:
      this.zzbjl = paramWorkSource;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzbay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */