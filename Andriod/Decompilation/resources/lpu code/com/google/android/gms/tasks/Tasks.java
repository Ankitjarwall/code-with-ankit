package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class Tasks
{
  public static <TResult> TResult await(@NonNull Task<TResult> paramTask)
    throws ExecutionException, InterruptedException
  {
    zzac.zzye();
    zzac.zzb(paramTask, "Task must not be null");
    if (paramTask.isComplete()) {
      return (TResult)zzb(paramTask);
    }
    zza localzza = new zza(null);
    zza(paramTask, localzza);
    localzza.await();
    return (TResult)zzb(paramTask);
  }
  
  public static <TResult> TResult await(@NonNull Task<TResult> paramTask, long paramLong, @NonNull TimeUnit paramTimeUnit)
    throws ExecutionException, InterruptedException, TimeoutException
  {
    zzac.zzye();
    zzac.zzb(paramTask, "Task must not be null");
    zzac.zzb(paramTimeUnit, "TimeUnit must not be null");
    if (paramTask.isComplete()) {
      return (TResult)zzb(paramTask);
    }
    zza localzza = new zza(null);
    zza(paramTask, localzza);
    if (!localzza.await(paramLong, paramTimeUnit)) {
      throw new TimeoutException("Timed out waiting for Task");
    }
    return (TResult)zzb(paramTask);
  }
  
  public static <TResult> Task<TResult> call(@NonNull Callable<TResult> paramCallable)
  {
    return call(TaskExecutors.MAIN_THREAD, paramCallable);
  }
  
  public static <TResult> Task<TResult> call(@NonNull Executor paramExecutor, @NonNull final Callable<TResult> paramCallable)
  {
    zzac.zzb(paramExecutor, "Executor must not be null");
    zzac.zzb(paramCallable, "Callback must not be null");
    zzh localzzh = new zzh();
    paramExecutor.execute(new Runnable()
    {
      public void run()
      {
        try
        {
          Tasks.this.setResult(paramCallable.call());
          return;
        }
        catch (Exception localException)
        {
          Tasks.this.setException(localException);
        }
      }
    });
    return localzzh;
  }
  
  public static <TResult> Task<TResult> forException(@NonNull Exception paramException)
  {
    zzh localzzh = new zzh();
    localzzh.setException(paramException);
    return localzzh;
  }
  
  public static <TResult> Task<TResult> forResult(TResult paramTResult)
  {
    zzh localzzh = new zzh();
    localzzh.setResult(paramTResult);
    return localzzh;
  }
  
  public static Task<Void> whenAll(Collection<? extends Task<?>> paramCollection)
  {
    if (paramCollection.isEmpty()) {
      return forResult(null);
    }
    Object localObject = paramCollection.iterator();
    while (((Iterator)localObject).hasNext()) {
      if ((Task)((Iterator)localObject).next() == null) {
        throw new NullPointerException("null tasks are not accepted");
      }
    }
    localObject = new zzh();
    zzc localzzc = new zzc(paramCollection.size(), (zzh)localObject);
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      zza((Task)paramCollection.next(), localzzc);
    }
    return (Task<Void>)localObject;
  }
  
  public static Task<Void> whenAll(Task<?>... paramVarArgs)
  {
    if (paramVarArgs.length == 0) {
      return forResult(null);
    }
    return whenAll(Arrays.asList(paramVarArgs));
  }
  
  private static void zza(Task<?> paramTask, zzb paramzzb)
  {
    paramTask.addOnSuccessListener(TaskExecutors.zzbNG, paramzzb);
    paramTask.addOnFailureListener(TaskExecutors.zzbNG, paramzzb);
  }
  
  private static <TResult> TResult zzb(Task<TResult> paramTask)
    throws ExecutionException
  {
    if (paramTask.isSuccessful()) {
      return (TResult)paramTask.getResult();
    }
    throw new ExecutionException(paramTask.getException());
  }
  
  private static final class zza
    implements Tasks.zzb
  {
    private final CountDownLatch zztj = new CountDownLatch(1);
    
    public void await()
      throws InterruptedException
    {
      this.zztj.await();
    }
    
    public boolean await(long paramLong, TimeUnit paramTimeUnit)
      throws InterruptedException
    {
      return this.zztj.await(paramLong, paramTimeUnit);
    }
    
    public void onFailure(@NonNull Exception paramException)
    {
      this.zztj.countDown();
    }
    
    public void onSuccess(Object paramObject)
    {
      this.zztj.countDown();
    }
  }
  
  static abstract interface zzb
    extends OnFailureListener, OnSuccessListener<Object>
  {}
  
  private static final class zzc
    implements Tasks.zzb
  {
    private final zzh<Void> zzbNF;
    private Exception zzbNK;
    private final int zzbNM;
    private int zzbNN;
    private int zzbNO;
    private final Object zzrJ = new Object();
    
    public zzc(int paramInt, zzh<Void> paramzzh)
    {
      this.zzbNM = paramInt;
      this.zzbNF = paramzzh;
    }
    
    private void zzTL()
    {
      if (this.zzbNN + this.zzbNO == this.zzbNM)
      {
        if (this.zzbNK == null) {
          this.zzbNF.setResult(null);
        }
      }
      else {
        return;
      }
      zzh localzzh = this.zzbNF;
      int i = this.zzbNO;
      int j = this.zzbNM;
      localzzh.setException(new ExecutionException(54 + i + " out of " + j + " underlying tasks failed", this.zzbNK));
    }
    
    public void onFailure(@NonNull Exception paramException)
    {
      synchronized (this.zzrJ)
      {
        this.zzbNO += 1;
        this.zzbNK = paramException;
        zzTL();
        return;
      }
    }
    
    public void onSuccess(Object arg1)
    {
      synchronized (this.zzrJ)
      {
        this.zzbNN += 1;
        zzTL();
        return;
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tasks\Tasks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */