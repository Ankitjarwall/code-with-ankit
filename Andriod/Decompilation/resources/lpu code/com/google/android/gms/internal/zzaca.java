package com.google.android.gms.internal;

public abstract class zzaca<T>
{
  private static String READ_PERMISSION = "com.google.android.providers.gsf.permission.READ_GSERVICES";
  private static zza zzaDC;
  private static int zzaDD;
  private static final Object zztX = new Object();
  protected final String zzAX;
  protected final T zzAY;
  private T zzaDE = null;
  
  static
  {
    zzaDC = null;
    zzaDD = 0;
  }
  
  protected zzaca(String paramString, T paramT)
  {
    this.zzAX = paramString;
    this.zzAY = paramT;
  }
  
  public static zzaca<String> zzB(String paramString1, String paramString2)
  {
    new zzaca(paramString1, paramString2) {};
  }
  
  public static zzaca<Float> zza(String paramString, Float paramFloat)
  {
    new zzaca(paramString, paramFloat) {};
  }
  
  public static zzaca<Integer> zza(String paramString, Integer paramInteger)
  {
    new zzaca(paramString, paramInteger) {};
  }
  
  public static zzaca<Long> zza(String paramString, Long paramLong)
  {
    new zzaca(paramString, paramLong) {};
  }
  
  public static zzaca<Boolean> zzj(String paramString, boolean paramBoolean)
  {
    new zzaca(paramString, Boolean.valueOf(paramBoolean)) {};
  }
  
  private static abstract interface zza {}
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzaca.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */