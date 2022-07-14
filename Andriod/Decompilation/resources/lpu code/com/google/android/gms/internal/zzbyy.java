package com.google.android.gms.internal;

final class zzbyy
{
  static zzbyx zzcyc;
  static long zzcye;
  
  static zzbyx zzafZ()
  {
    try
    {
      if (zzcyc != null)
      {
        zzbyx localzzbyx = zzcyc;
        zzcyc = localzzbyx.zzcyc;
        localzzbyx.zzcyc = null;
        zzcye -= 8192L;
        return localzzbyx;
      }
      return new zzbyx();
    }
    finally {}
  }
  
  static void zzb(zzbyx paramzzbyx)
  {
    if ((paramzzbyx.zzcyc != null) || (paramzzbyx.zzcyd != null)) {
      throw new IllegalArgumentException();
    }
    if (paramzzbyx.zzcya) {
      return;
    }
    try
    {
      if (zzcye + 8192L > 65536L) {
        return;
      }
    }
    finally {}
    zzcye += 8192L;
    paramzzbyx.zzcyc = zzcyc;
    paramzzbyx.limit = 0;
    paramzzbyx.pos = 0;
    zzcyc = paramzzbyx;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzbyy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */