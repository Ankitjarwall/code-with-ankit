package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;

class zzauo
{
  private long zzLp;
  private final zze zzuP;
  
  public zzauo(zze paramzze)
  {
    zzac.zzw(paramzze);
    this.zzuP = paramzze;
  }
  
  public void clear()
  {
    this.zzLp = 0L;
  }
  
  public void start()
  {
    this.zzLp = this.zzuP.elapsedRealtime();
  }
  
  public boolean zzA(long paramLong)
  {
    if (this.zzLp == 0L) {}
    while (this.zzuP.elapsedRealtime() - this.zzLp >= paramLong) {
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzauo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */