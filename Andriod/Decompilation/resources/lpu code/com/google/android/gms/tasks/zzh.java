package com.google.android.gms.tasks;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzabe;
import com.google.android.gms.internal.zzabf;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

final class zzh<TResult>
  extends Task<TResult>
{
  private final zzg<TResult> zzbNH = new zzg();
  private boolean zzbNI;
  private TResult zzbNJ;
  private Exception zzbNK;
  private final Object zzrJ = new Object();
  
  private void zzTI()
  {
    zzac.zza(this.zzbNI, "Task is not yet complete");
  }
  
  private void zzTJ()
  {
    if (!this.zzbNI) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zza(bool, "Task is already complete");
      return;
    }
  }
  
  private void zzTK()
  {
    synchronized (this.zzrJ)
    {
      if (!this.zzbNI) {
        return;
      }
      this.zzbNH.zza(this);
      return;
    }
  }
  
  @NonNull
  public Task<TResult> addOnCompleteListener(@NonNull Activity paramActivity, @NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    paramOnCompleteListener = new zzc(TaskExecutors.MAIN_THREAD, paramOnCompleteListener);
    this.zzbNH.zza(paramOnCompleteListener);
    zza.zzw(paramActivity).zzb(paramOnCompleteListener);
    zzTK();
    return this;
  }
  
  @NonNull
  public Task<TResult> addOnCompleteListener(@NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    return addOnCompleteListener(TaskExecutors.MAIN_THREAD, paramOnCompleteListener);
  }
  
  @NonNull
  public Task<TResult> addOnCompleteListener(@NonNull Executor paramExecutor, @NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    this.zzbNH.zza(new zzc(paramExecutor, paramOnCompleteListener));
    zzTK();
    return this;
  }
  
  @NonNull
  public Task<TResult> addOnFailureListener(@NonNull Activity paramActivity, @NonNull OnFailureListener paramOnFailureListener)
  {
    paramOnFailureListener = new zzd(TaskExecutors.MAIN_THREAD, paramOnFailureListener);
    this.zzbNH.zza(paramOnFailureListener);
    zza.zzw(paramActivity).zzb(paramOnFailureListener);
    zzTK();
    return this;
  }
  
  @NonNull
  public Task<TResult> addOnFailureListener(@NonNull OnFailureListener paramOnFailureListener)
  {
    return addOnFailureListener(TaskExecutors.MAIN_THREAD, paramOnFailureListener);
  }
  
  @NonNull
  public Task<TResult> addOnFailureListener(@NonNull Executor paramExecutor, @NonNull OnFailureListener paramOnFailureListener)
  {
    this.zzbNH.zza(new zzd(paramExecutor, paramOnFailureListener));
    zzTK();
    return this;
  }
  
  @NonNull
  public Task<TResult> addOnSuccessListener(@NonNull Activity paramActivity, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    paramOnSuccessListener = new zze(TaskExecutors.MAIN_THREAD, paramOnSuccessListener);
    this.zzbNH.zza(paramOnSuccessListener);
    zza.zzw(paramActivity).zzb(paramOnSuccessListener);
    zzTK();
    return this;
  }
  
  @NonNull
  public Task<TResult> addOnSuccessListener(@NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    return addOnSuccessListener(TaskExecutors.MAIN_THREAD, paramOnSuccessListener);
  }
  
  @NonNull
  public Task<TResult> addOnSuccessListener(@NonNull Executor paramExecutor, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    this.zzbNH.zza(new zze(paramExecutor, paramOnSuccessListener));
    zzTK();
    return this;
  }
  
  @NonNull
  public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Continuation<TResult, TContinuationResult> paramContinuation)
  {
    return continueWith(TaskExecutors.MAIN_THREAD, paramContinuation);
  }
  
  @NonNull
  public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, TContinuationResult> paramContinuation)
  {
    zzh localzzh = new zzh();
    this.zzbNH.zza(new zza(paramExecutor, paramContinuation, localzzh));
    zzTK();
    return localzzh;
  }
  
  @NonNull
  public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation)
  {
    return continueWithTask(TaskExecutors.MAIN_THREAD, paramContinuation);
  }
  
  @NonNull
  public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation)
  {
    zzh localzzh = new zzh();
    this.zzbNH.zza(new zzb(paramExecutor, paramContinuation, localzzh));
    zzTK();
    return localzzh;
  }
  
  @Nullable
  public Exception getException()
  {
    synchronized (this.zzrJ)
    {
      Exception localException = this.zzbNK;
      return localException;
    }
  }
  
  public TResult getResult()
  {
    synchronized (this.zzrJ)
    {
      zzTI();
      if (this.zzbNK != null) {
        throw new RuntimeExecutionException(this.zzbNK);
      }
    }
    Object localObject3 = this.zzbNJ;
    return (TResult)localObject3;
  }
  
  public <X extends Throwable> TResult getResult(@NonNull Class<X> paramClass)
    throws Throwable
  {
    synchronized (this.zzrJ)
    {
      zzTI();
      if (paramClass.isInstance(this.zzbNK)) {
        throw ((Throwable)paramClass.cast(this.zzbNK));
      }
    }
    if (this.zzbNK != null) {
      throw new RuntimeExecutionException(this.zzbNK);
    }
    paramClass = this.zzbNJ;
    return paramClass;
  }
  
  public boolean isComplete()
  {
    synchronized (this.zzrJ)
    {
      boolean bool = this.zzbNI;
      return bool;
    }
  }
  
  public boolean isSuccessful()
  {
    for (;;)
    {
      synchronized (this.zzrJ)
      {
        if ((this.zzbNI) && (this.zzbNK == null))
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public void setException(@NonNull Exception paramException)
  {
    zzac.zzb(paramException, "Exception must not be null");
    synchronized (this.zzrJ)
    {
      zzTJ();
      this.zzbNI = true;
      this.zzbNK = paramException;
      this.zzbNH.zza(this);
      return;
    }
  }
  
  public void setResult(TResult paramTResult)
  {
    synchronized (this.zzrJ)
    {
      zzTJ();
      this.zzbNI = true;
      this.zzbNJ = paramTResult;
      this.zzbNH.zza(this);
      return;
    }
  }
  
  public boolean trySetException(@NonNull Exception paramException)
  {
    zzac.zzb(paramException, "Exception must not be null");
    synchronized (this.zzrJ)
    {
      if (this.zzbNI) {
        return false;
      }
      this.zzbNI = true;
      this.zzbNK = paramException;
      this.zzbNH.zza(this);
      return true;
    }
  }
  
  public boolean trySetResult(TResult paramTResult)
  {
    synchronized (this.zzrJ)
    {
      if (this.zzbNI) {
        return false;
      }
      this.zzbNI = true;
      this.zzbNJ = paramTResult;
      this.zzbNH.zza(this);
      return true;
    }
  }
  
  private static class zza
    extends zzabe
  {
    private final List<WeakReference<zzf<?>>> mListeners = new ArrayList();
    
    private zza(zzabf paramzzabf)
    {
      super();
      this.zzaCR.zza("TaskOnStopCallback", this);
    }
    
    public static zza zzw(Activity paramActivity)
    {
      zzabf localzzabf = zzs(paramActivity);
      zza localzza = (zza)localzzabf.zza("TaskOnStopCallback", zza.class);
      paramActivity = localzza;
      if (localzza == null) {
        paramActivity = new zza(localzzabf);
      }
      return paramActivity;
    }
    
    @MainThread
    public void onStop()
    {
      synchronized (this.mListeners)
      {
        Iterator localIterator = this.mListeners.iterator();
        while (localIterator.hasNext())
        {
          zzf localzzf = (zzf)((WeakReference)localIterator.next()).get();
          if (localzzf != null) {
            localzzf.cancel();
          }
        }
      }
      this.mListeners.clear();
    }
    
    public <T> void zzb(zzf<T> paramzzf)
    {
      synchronized (this.mListeners)
      {
        this.mListeners.add(new WeakReference(paramzzf));
        return;
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tasks\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */