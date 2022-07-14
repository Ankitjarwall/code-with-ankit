package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public class TaskCompletionSource<TResult>
{
  private final zzh<TResult> zzbNF = new zzh();
  
  @NonNull
  public Task<TResult> getTask()
  {
    return this.zzbNF;
  }
  
  public void setException(@NonNull Exception paramException)
  {
    this.zzbNF.setException(paramException);
  }
  
  public void setResult(TResult paramTResult)
  {
    this.zzbNF.setResult(paramTResult);
  }
  
  public boolean trySetException(@NonNull Exception paramException)
  {
    return this.zzbNF.trySetException(paramException);
  }
  
  public boolean trySetResult(TResult paramTResult)
  {
    return this.zzbNF.trySetResult(paramTResult);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tasks\TaskCompletionSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */