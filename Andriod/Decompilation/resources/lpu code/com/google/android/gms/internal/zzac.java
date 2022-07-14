package com.google.android.gms.internal;

import java.io.UnsupportedEncodingException;

public class zzac
  extends zzl<String>
{
  private final zzn.zzb<String> zzaG;
  
  public zzac(int paramInt, String paramString, zzn.zzb<String> paramzzb, zzn.zza paramzza)
  {
    super(paramInt, paramString, paramzza);
    this.zzaG = paramzzb;
  }
  
  protected zzn<String> zza(zzj paramzzj)
  {
    try
    {
      String str1 = new String(paramzzj.data, zzy.zza(paramzzj.zzz));
      return zzn.zza(str1, zzy.zzb(paramzzj));
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;)
      {
        String str2 = new String(paramzzj.data);
      }
    }
  }
  
  protected void zzi(String paramString)
  {
    this.zzaG.zzb(paramString);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */