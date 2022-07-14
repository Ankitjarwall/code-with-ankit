package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.internal.zzaad.zza;
import com.google.android.gms.internal.zzary;
import com.google.android.gms.internal.zzasb;
import com.google.android.gms.internal.zzash;
import com.google.android.gms.internal.zzasp;

public class LocationServices
{
  public static final Api<Api.ApiOptions.NoOptions> API = new Api("LocationServices.API", zzaie, zzaid);
  public static final FusedLocationProviderApi FusedLocationApi = new zzary();
  public static final GeofencingApi GeofencingApi = new zzasb();
  public static final SettingsApi SettingsApi = new zzasp();
  private static final Api.zzf<zzash> zzaid = new Api.zzf();
  private static final Api.zza<zzash, Api.ApiOptions.NoOptions> zzaie = new Api.zza()
  {
    public zzash zzq(Context paramAnonymousContext, Looper paramAnonymousLooper, zzg paramAnonymouszzg, Api.ApiOptions.NoOptions paramAnonymousNoOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      return new zzash(paramAnonymousContext, paramAnonymousLooper, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener, "locationServices", paramAnonymouszzg);
    }
  };
  
  public static zzash zzj(GoogleApiClient paramGoogleApiClient)
  {
    boolean bool2 = true;
    if (paramGoogleApiClient != null)
    {
      bool1 = true;
      zzac.zzb(bool1, "GoogleApiClient parameter is required.");
      paramGoogleApiClient = (zzash)paramGoogleApiClient.zza(zzaid);
      if (paramGoogleApiClient == null) {
        break label44;
      }
    }
    label44:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzac.zza(bool1, "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
      return paramGoogleApiClient;
      bool1 = false;
      break;
    }
  }
  
  public static abstract class zza<R extends Result>
    extends zzaad.zza<R, zzash>
  {
    public zza(GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\LocationServices.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */