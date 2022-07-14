package com.google.android.gms.common.api;

import com.google.android.gms.internal.zzaaf;
import java.util.ArrayList;
import java.util.List;

public final class Batch
  extends zzaaf<BatchResult>
{
  private int zzayM;
  private boolean zzayN;
  private boolean zzayO;
  private final PendingResult<?>[] zzayP;
  private final Object zzrJ = new Object();
  
  private Batch(List<PendingResult<?>> paramList, GoogleApiClient paramGoogleApiClient)
  {
    super(paramGoogleApiClient);
    this.zzayM = paramList.size();
    this.zzayP = new PendingResult[this.zzayM];
    if (paramList.isEmpty()) {
      zzb(new BatchResult(Status.zzazx, this.zzayP));
    }
    for (;;)
    {
      return;
      int i = 0;
      while (i < paramList.size())
      {
        paramGoogleApiClient = (PendingResult)paramList.get(i);
        this.zzayP[i] = paramGoogleApiClient;
        paramGoogleApiClient.zza(new PendingResult.zza()
        {
          public void zzy(Status paramAnonymousStatus)
          {
            for (;;)
            {
              synchronized (Batch.zza(Batch.this))
              {
                if (Batch.this.isCanceled()) {
                  return;
                }
                if (paramAnonymousStatus.isCanceled())
                {
                  Batch.zza(Batch.this, true);
                  Batch.zzb(Batch.this);
                  if (Batch.zzc(Batch.this) == 0)
                  {
                    if (!Batch.zzd(Batch.this)) {
                      break;
                    }
                    Batch.zze(Batch.this);
                  }
                  return;
                }
              }
              if (!paramAnonymousStatus.isSuccess()) {
                Batch.zzb(Batch.this, true);
              }
            }
            if (Batch.zzf(Batch.this)) {}
            for (paramAnonymousStatus = new Status(13);; paramAnonymousStatus = Status.zzazx)
            {
              Batch.this.zzb(new BatchResult(paramAnonymousStatus, Batch.zzg(Batch.this)));
              break;
            }
          }
        });
        i += 1;
      }
    }
  }
  
  public void cancel()
  {
    super.cancel();
    PendingResult[] arrayOfPendingResult = this.zzayP;
    int j = arrayOfPendingResult.length;
    int i = 0;
    while (i < j)
    {
      arrayOfPendingResult[i].cancel();
      i += 1;
    }
  }
  
  public BatchResult createFailedResult(Status paramStatus)
  {
    return new BatchResult(paramStatus, this.zzayP);
  }
  
  public static final class Builder
  {
    private GoogleApiClient zzanE;
    private List<PendingResult<?>> zzayR = new ArrayList();
    
    public Builder(GoogleApiClient paramGoogleApiClient)
    {
      this.zzanE = paramGoogleApiClient;
    }
    
    public <R extends Result> BatchResultToken<R> add(PendingResult<R> paramPendingResult)
    {
      BatchResultToken localBatchResultToken = new BatchResultToken(this.zzayR.size());
      this.zzayR.add(paramPendingResult);
      return localBatchResultToken;
    }
    
    public Batch build()
    {
      return new Batch(this.zzayR, this.zzanE, null);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\api\Batch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */