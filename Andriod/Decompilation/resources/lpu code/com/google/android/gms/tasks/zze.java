package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zze<TResult>
  implements zzf<TResult>
{
  private final Executor zzbFP;
  private OnSuccessListener<? super TResult> zzbNB;
  private final Object zzrJ = new Object();
  
  public zze(@NonNull Executor paramExecutor, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    this.zzbFP = paramExecutor;
    this.zzbNB = paramOnSuccessListener;
  }
  
  public void cancel()
  {
    synchronized (this.zzrJ)
    {
      this.zzbNB = null;
      return;
    }
  }
  
  public void onComplete(@NonNull final Task<TResult> paramTask)
  {
    if (paramTask.isSuccessful()) {
      synchronized (this.zzrJ)
      {
        if (this.zzbNB == null) {
          return;
        }
        this.zzbFP.execute(new Runnable()
        {
          public void run()
          {
            synchronized (zze.zza(zze.this))
            {
              if (zze.zzb(zze.this) != null) {
                zze.zzb(zze.this).onSuccess(paramTask.getResult());
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


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tasks\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */