package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzd<TResult>
  implements zzf<TResult>
{
  private final Executor zzbFP;
  private OnFailureListener zzbNz;
  private final Object zzrJ = new Object();
  
  public zzd(@NonNull Executor paramExecutor, @NonNull OnFailureListener paramOnFailureListener)
  {
    this.zzbFP = paramExecutor;
    this.zzbNz = paramOnFailureListener;
  }
  
  public void cancel()
  {
    synchronized (this.zzrJ)
    {
      this.zzbNz = null;
      return;
    }
  }
  
  public void onComplete(@NonNull final Task<TResult> paramTask)
  {
    if (!paramTask.isSuccessful()) {
      synchronized (this.zzrJ)
      {
        if (this.zzbNz == null) {
          return;
        }
        this.zzbFP.execute(new Runnable()
        {
          public void run()
          {
            synchronized (zzd.zza(zzd.this))
            {
              if (zzd.zzb(zzd.this) != null) {
                zzd.zzb(zzd.this).onFailure(paramTask.getException());
              }
              return;
            }
          }
        });
        return;
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tasks\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */