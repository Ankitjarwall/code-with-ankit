package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzac;
import java.util.concurrent.TimeUnit;

public final class BatchResult
  implements Result
{
  private final Status zzair;
  private final PendingResult<?>[] zzayP;
  
  BatchResult(Status paramStatus, PendingResult<?>[] paramArrayOfPendingResult)
  {
    this.zzair = paramStatus;
    this.zzayP = paramArrayOfPendingResult;
  }
  
  public Status getStatus()
  {
    return this.zzair;
  }
  
  public <R extends Result> R take(BatchResultToken<R> paramBatchResultToken)
  {
    if (paramBatchResultToken.mId < this.zzayP.length) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzb(bool, "The result token does not belong to this batch");
      return this.zzayP[paramBatchResultToken.mId].await(0L, TimeUnit.MILLISECONDS);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\api\BatchResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */