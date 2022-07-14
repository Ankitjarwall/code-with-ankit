package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.Queue;

class zzg<TResult>
{
  private Queue<zzf<TResult>> zzbND;
  private boolean zzbNE;
  private final Object zzrJ = new Object();
  
  public void zza(@NonNull Task<TResult> paramTask)
  {
    for (;;)
    {
      zzf localzzf;
      synchronized (this.zzrJ)
      {
        if ((this.zzbND == null) || (this.zzbNE)) {
          return;
        }
        this.zzbNE = true;
        synchronized (this.zzrJ)
        {
          localzzf = (zzf)this.zzbND.poll();
          if (localzzf == null)
          {
            this.zzbNE = false;
            return;
          }
        }
      }
      localzzf.onComplete(paramTask);
    }
  }
  
  public void zza(@NonNull zzf<TResult> paramzzf)
  {
    synchronized (this.zzrJ)
    {
      if (this.zzbND == null) {
        this.zzbND = new ArrayDeque();
      }
      this.zzbND.add(paramzzf);
      return;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tasks\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */