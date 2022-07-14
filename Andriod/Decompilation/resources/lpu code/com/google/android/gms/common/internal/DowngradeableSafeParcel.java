package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.zza;

public abstract class DowngradeableSafeParcel
  extends zza
  implements ReflectedParcelable
{
  private static final Object zzaFG = new Object();
  private static ClassLoader zzaFH = null;
  private static Integer zzaFI = null;
  private boolean zzaFJ = false;
  
  protected static boolean zzdl(String paramString)
  {
    zzxU();
    return true;
  }
  
  protected static ClassLoader zzxU()
  {
    synchronized (zzaFG)
    {
      return null;
    }
  }
  
  protected static Integer zzxV()
  {
    synchronized (zzaFG)
    {
      return null;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\DowngradeableSafeParcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */