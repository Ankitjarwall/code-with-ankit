package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzabv<A extends Api.zzb, TResult>
{
  protected abstract void zza(A paramA, TaskCompletionSource<TResult> paramTaskCompletionSource)
    throws RemoteException;
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzabv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */