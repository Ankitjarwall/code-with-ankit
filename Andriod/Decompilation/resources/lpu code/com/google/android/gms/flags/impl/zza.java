package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import com.google.android.gms.internal.zzaqf;
import java.util.concurrent.Callable;

public abstract class zza<T>
{
  public static class zza
    extends zza<Boolean>
  {
    public static Boolean zza(SharedPreferences paramSharedPreferences, final String paramString, final Boolean paramBoolean)
    {
      (Boolean)zzaqf.zzb(new Callable()
      {
        public Boolean zzbX()
        {
          return Boolean.valueOf(zza.zza.this.getBoolean(paramString, paramBoolean.booleanValue()));
        }
      });
    }
  }
  
  public static class zzb
    extends zza<Integer>
  {
    public static Integer zza(SharedPreferences paramSharedPreferences, final String paramString, final Integer paramInteger)
    {
      (Integer)zzaqf.zzb(new Callable()
      {
        public Integer zzDG()
        {
          return Integer.valueOf(zza.zzb.this.getInt(paramString, paramInteger.intValue()));
        }
      });
    }
  }
  
  public static class zzc
    extends zza<Long>
  {
    public static Long zza(SharedPreferences paramSharedPreferences, final String paramString, final Long paramLong)
    {
      (Long)zzaqf.zzb(new Callable()
      {
        public Long zzDH()
        {
          return Long.valueOf(zza.zzc.this.getLong(paramString, paramLong.longValue()));
        }
      });
    }
  }
  
  public static class zzd
    extends zza<String>
  {
    public static String zza(SharedPreferences paramSharedPreferences, final String paramString1, final String paramString2)
    {
      (String)zzaqf.zzb(new Callable()
      {
        public String zzbY()
        {
          return zza.zzd.this.getString(paramString1, paramString2);
        }
      });
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\flags\impl\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */