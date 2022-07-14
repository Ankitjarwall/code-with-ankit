package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzb<TResult, TContinuationResult>
  implements OnFailureListener, OnSuccessListener<TContinuationResult>, zzf<TResult>
{
  private final Executor zzbFP;
  private final Continuation<TResult, Task<TContinuationResult>> zzbNs;
  private final zzh<TContinuationResult> zzbNt;
  
  public zzb(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation, @NonNull zzh<TContinuationResult> paramzzh)
  {
    this.zzbFP = paramExecutor;
    this.zzbNs = paramContinuation;
    this.zzbNt = paramzzh;
  }
  
  public void cancel()
  {
    throw new UnsupportedOperationException();
  }
  
  public void onComplete(@NonNull final Task<TResult> paramTask)
  {
    this.zzbFP.execute(new Runnable()
    {
      public void run()
      {
        try
        {
          Task localTask = (Task)zzb.zza(zzb.this).then(paramTask);
          if (localTask == null)
          {
            zzb.this.onFailure(new NullPointerException("Continuation returned null"));
            return;
          }
        }
        catch (RuntimeExecutionException localRuntimeExecutionException)
        {
          if ((localRuntimeExecutionException.getCause() instanceof Exception))
          {
            zzb.zzb(zzb.this).setException((Exception)localRuntimeExecutionException.getCause());
            return;
          }
          zzb.zzb(zzb.this).setException(localRuntimeExecutionException);
          return;
        }
        catch (Exception localException)
        {
          zzb.zzb(zzb.this).setException(localException);
          return;
        }
        localException.addOnSuccessListener(TaskExecutors.zzbNG, zzb.this);
        localException.addOnFailureListener(TaskExecutors.zzbNG, zzb.this);
      }
    });
  }
  
  public void onFailure(@NonNull Exception paramException)
  {
    this.zzbNt.setException(paramException);
  }
  
  public void onSuccess(TContinuationResult paramTContinuationResult)
  {
    this.zzbNt.setResult(paramTContinuationResult);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tasks\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */