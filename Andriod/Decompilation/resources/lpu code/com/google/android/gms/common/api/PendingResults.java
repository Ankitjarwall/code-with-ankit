package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzaaf;
import com.google.android.gms.internal.zzabk;
import com.google.android.gms.internal.zzabt;

public final class PendingResults
{
  public static PendingResult<Status> canceledPendingResult()
  {
    zzabt localzzabt = new zzabt(Looper.getMainLooper());
    localzzabt.cancel();
    return localzzabt;
  }
  
  public static <R extends Result> PendingResult<R> canceledPendingResult(R paramR)
  {
    zzac.zzb(paramR, "Result must not be null");
    if (paramR.getStatus().getStatusCode() == 16) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzb(bool, "Status code must be CommonStatusCodes.CANCELED");
      paramR = new zza(paramR);
      paramR.cancel();
      return paramR;
    }
  }
  
  public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R paramR)
  {
    zzac.zzb(paramR, "Result must not be null");
    zzc localzzc = new zzc(null);
    localzzc.zzb(paramR);
    return new zzabk(localzzc);
  }
  
  public static PendingResult<Status> immediatePendingResult(Status paramStatus)
  {
    zzac.zzb(paramStatus, "Result must not be null");
    zzabt localzzabt = new zzabt(Looper.getMainLooper());
    localzzabt.zzb(paramStatus);
    return localzzabt;
  }
  
  public static <R extends Result> PendingResult<R> zza(R paramR, GoogleApiClient paramGoogleApiClient)
  {
    zzac.zzb(paramR, "Result must not be null");
    if (!paramR.getStatus().isSuccess()) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzb(bool, "Status code must not be SUCCESS");
      paramGoogleApiClient = new zzb(paramGoogleApiClient, paramR);
      paramGoogleApiClient.zzb(paramR);
      return paramGoogleApiClient;
    }
  }
  
  public static PendingResult<Status> zza(Status paramStatus, GoogleApiClient paramGoogleApiClient)
  {
    zzac.zzb(paramStatus, "Result must not be null");
    paramGoogleApiClient = new zzabt(paramGoogleApiClient);
    paramGoogleApiClient.zzb(paramStatus);
    return paramGoogleApiClient;
  }
  
  public static <R extends Result> OptionalPendingResult<R> zzb(R paramR, GoogleApiClient paramGoogleApiClient)
  {
    zzac.zzb(paramR, "Result must not be null");
    paramGoogleApiClient = new zzc(paramGoogleApiClient);
    paramGoogleApiClient.zzb(paramR);
    return new zzabk(paramGoogleApiClient);
  }
  
  private static final class zza<R extends Result>
    extends zzaaf<R>
  {
    private final R zzazs;
    
    public zza(R paramR)
    {
      super();
      this.zzazs = paramR;
    }
    
    protected R zzc(Status paramStatus)
    {
      if (paramStatus.getStatusCode() != this.zzazs.getStatus().getStatusCode()) {
        throw new UnsupportedOperationException("Creating failed results is not supported");
      }
      return this.zzazs;
    }
  }
  
  private static final class zzb<R extends Result>
    extends zzaaf<R>
  {
    private final R zzazt;
    
    public zzb(GoogleApiClient paramGoogleApiClient, R paramR)
    {
      super();
      this.zzazt = paramR;
    }
    
    protected R zzc(Status paramStatus)
    {
      return this.zzazt;
    }
  }
  
  private static final class zzc<R extends Result>
    extends zzaaf<R>
  {
    public zzc(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    protected R zzc(Status paramStatus)
    {
      throw new UnsupportedOperationException("Creating failed results is not supported");
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\api\PendingResults.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */