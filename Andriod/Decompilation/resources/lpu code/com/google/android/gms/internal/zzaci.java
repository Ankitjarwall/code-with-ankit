package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class zzaci<R extends Result>
  extends zzaad.zza<R, zzacj>
{
  public zzaci(GoogleApiClient paramGoogleApiClient)
  {
    super(zzacf.API, paramGoogleApiClient);
  }
  
  static abstract class zza
    extends zzaci<Status>
  {
    public zza(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public Status zzb(Status paramStatus)
    {
      return paramStatus;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzaci.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */