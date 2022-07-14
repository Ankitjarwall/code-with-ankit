package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;

public class zzarv
  extends zzl<zzase>
{
  private final String zzbkr;
  protected final zzaso<zzase> zzbks = new zzaso()
  {
    public zzase zzIn()
      throws DeadObjectException
    {
      return (zzase)zzarv.this.zzxD();
    }
    
    public void zzxC()
    {
      zzarv.zza(zzarv.this);
    }
  };
  
  public zzarv(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, zzg paramzzg)
  {
    super(paramContext, paramLooper, 23, paramzzg, paramConnectionCallbacks, paramOnConnectionFailedListener);
    this.zzbkr = paramString;
  }
  
  protected zzase zzdf(IBinder paramIBinder)
  {
    return zzase.zza.zzdi(paramIBinder);
  }
  
  protected String zzeA()
  {
    return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
  }
  
  protected String zzez()
  {
    return "com.google.android.location.internal.GoogleLocationManagerService.START";
  }
  
  protected Bundle zzqL()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("client_name", this.zzbkr);
    return localBundle;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzarv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */