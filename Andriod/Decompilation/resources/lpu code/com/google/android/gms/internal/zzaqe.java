package com.google.android.gms.internal;

public final class zzaqe
{
  private static zzaqe zzaXl;
  private final zzaqb zzaXm = new zzaqb();
  private final zzaqc zzaXn = new zzaqc();
  
  static
  {
    zza(new zzaqe());
  }
  
  private static zzaqe zzDD()
  {
    try
    {
      zzaqe localzzaqe = zzaXl;
      return localzzaqe;
    }
    finally {}
  }
  
  public static zzaqb zzDE()
  {
    return zzDD().zzaXm;
  }
  
  public static zzaqc zzDF()
  {
    return zzDD().zzaXn;
  }
  
  protected static void zza(zzaqe paramzzaqe)
  {
    try
    {
      zzaXl = paramzzaqe;
      return;
    }
    finally {}
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzaqe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */