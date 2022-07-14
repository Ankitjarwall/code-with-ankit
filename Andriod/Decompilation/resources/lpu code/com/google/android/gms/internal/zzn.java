package com.google.android.gms.internal;

public class zzn<T>
{
  public final T result;
  public final zzb.zza zzaf;
  public final zzs zzag;
  public boolean zzah = false;
  
  private zzn(zzs paramzzs)
  {
    this.result = null;
    this.zzaf = null;
    this.zzag = paramzzs;
  }
  
  private zzn(T paramT, zzb.zza paramzza)
  {
    this.result = paramT;
    this.zzaf = paramzza;
    this.zzag = null;
  }
  
  public static <T> zzn<T> zza(T paramT, zzb.zza paramzza)
  {
    return new zzn(paramT, paramzza);
  }
  
  public static <T> zzn<T> zzd(zzs paramzzs)
  {
    return new zzn(paramzzs);
  }
  
  public boolean isSuccess()
  {
    return this.zzag == null;
  }
  
  public static abstract interface zza
  {
    public abstract void zze(zzs paramzzs);
  }
  
  public static abstract interface zzb<T>
  {
    public abstract void zzb(T paramT);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */