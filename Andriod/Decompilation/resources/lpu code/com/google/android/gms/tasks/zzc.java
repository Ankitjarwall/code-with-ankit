package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzc<TResult>
  implements zzf<TResult>
{
  private final Executor zzbFP;
  private OnCompleteListener<TResult> zzbNx;
  private final Object zzrJ = new Object();
  
  public zzc(@NonNull Executor paramExecutor, @NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    this.zzbFP = paramExecutor;
    this.zzbNx = paramOnCompleteListener;
  }
  
  public void cancel()
  {
    synchronized (this.zzrJ)
    {
      this.zzbNx = null;
      return;
    }
  }
  
  public void onComplete(@NonNull final Task<TResult> paramTask)
  {
    synchronized (this.zzrJ)
    {
      if (this.zzbNx == null) {
        return;
      }
      this.zzbFP.execute(new Runnable()
      {
        public void run()
        {
          synchronized (zzc.zza(zzc.this))
          {
            if (zzc.zzb(zzc.this) != null) {
              zzc.zzb(zzc.this).onComplete(paramTask);
            }
            return;
          }
        }
      });
      return;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tasks\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */