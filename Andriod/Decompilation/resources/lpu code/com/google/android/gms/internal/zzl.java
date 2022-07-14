package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.Collections;
import java.util.Map;

public abstract class zzl<T>
  implements Comparable<zzl<T>>
{
  private final zzt.zza zzC;
  private final int zzD;
  private final String zzE;
  private final int zzF;
  private final zzn.zza zzG;
  private Integer zzH;
  private zzm zzI;
  private boolean zzJ;
  private boolean zzK;
  private boolean zzL;
  private boolean zzM;
  private zzp zzN;
  private zzb.zza zzO;
  
  public zzl(int paramInt, String paramString, zzn.zza paramzza)
  {
    if (zzt.zza.zzaj) {}
    for (zzt.zza localzza = new zzt.zza();; localzza = null)
    {
      this.zzC = localzza;
      this.zzJ = true;
      this.zzK = false;
      this.zzL = false;
      this.zzM = false;
      this.zzO = null;
      this.zzD = paramInt;
      this.zzE = paramString;
      this.zzG = paramzza;
      zza(new zze());
      this.zzF = zzb(paramString);
      return;
    }
  }
  
  private static int zzb(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      paramString = Uri.parse(paramString);
      if (paramString != null)
      {
        paramString = paramString.getHost();
        if (paramString != null) {
          return paramString.hashCode();
        }
      }
    }
    return 0;
  }
  
  public Map<String, String> getHeaders()
    throws zza
  {
    return Collections.emptyMap();
  }
  
  public int getMethod()
  {
    return this.zzD;
  }
  
  public String getUrl()
  {
    return this.zzE;
  }
  
  public String toString()
  {
    String str = "0x" + Integer.toHexString(zzf());
    return "[ ] " + getUrl() + " " + str + " " + zzo() + " " + this.zzH;
  }
  
  public final zzl<?> zza(int paramInt)
  {
    this.zzH = Integer.valueOf(paramInt);
    return this;
  }
  
  public zzl<?> zza(zzb.zza paramzza)
  {
    this.zzO = paramzza;
    return this;
  }
  
  public zzl<?> zza(zzm paramzzm)
  {
    this.zzI = paramzzm;
    return this;
  }
  
  public zzl<?> zza(zzp paramzzp)
  {
    this.zzN = paramzzp;
    return this;
  }
  
  protected abstract zzn<T> zza(zzj paramzzj);
  
  protected abstract void zza(T paramT);
  
  protected zzs zzb(zzs paramzzs)
  {
    return paramzzs;
  }
  
  public int zzc(zzl<T> paramzzl)
  {
    zza localzza1 = zzo();
    zza localzza2 = paramzzl.zzo();
    if (localzza1 == localzza2) {
      return this.zzH.intValue() - paramzzl.zzH.intValue();
    }
    return localzza2.ordinal() - localzza1.ordinal();
  }
  
  public void zzc(zzs paramzzs)
  {
    if (this.zzG != null) {
      this.zzG.zze(paramzzs);
    }
  }
  
  public void zzc(String paramString)
  {
    if (zzt.zza.zzaj) {
      this.zzC.zza(paramString, Thread.currentThread().getId());
    }
  }
  
  void zzd(final String paramString)
  {
    if (this.zzI != null) {
      this.zzI.zzf(this);
    }
    final long l;
    if (zzt.zza.zzaj)
    {
      l = Thread.currentThread().getId();
      if (Looper.myLooper() != Looper.getMainLooper()) {
        new Handler(Looper.getMainLooper()).post(new Runnable()
        {
          public void run()
          {
            zzl.zzd(zzl.this).zza(paramString, l);
            zzl.zzd(zzl.this).zzd(toString());
          }
        });
      }
    }
    else
    {
      return;
    }
    this.zzC.zza(paramString, l);
    this.zzC.zzd(toString());
  }
  
  public int zzf()
  {
    return this.zzF;
  }
  
  public String zzg()
  {
    return getUrl();
  }
  
  public zzb.zza zzh()
  {
    return this.zzO;
  }
  
  @Deprecated
  public String zzi()
  {
    return zzl();
  }
  
  @Deprecated
  public byte[] zzj()
    throws zza
  {
    return null;
  }
  
  protected String zzk()
  {
    return "UTF-8";
  }
  
  public String zzl()
  {
    return "application/x-www-form-urlencoded; charset=" + zzk();
  }
  
  public byte[] zzm()
    throws zza
  {
    return null;
  }
  
  public final boolean zzn()
  {
    return this.zzJ;
  }
  
  public zza zzo()
  {
    return zza.zzT;
  }
  
  public final int zzp()
  {
    return this.zzN.zzc();
  }
  
  public zzp zzq()
  {
    return this.zzN;
  }
  
  public void zzr()
  {
    this.zzL = true;
  }
  
  public boolean zzs()
  {
    return this.zzL;
  }
  
  public static enum zza
  {
    private zza() {}
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */