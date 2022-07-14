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
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.internal.zzaad.zza;
import com.google.android.gms.internal.zzaru;
import com.google.android.gms.internal.zzash;

public class ActivityRecognition
{
  public static final Api<Api.ApiOptions.NoOptions> API = new Api("ActivityRecognition.API", zzaie, zzaid);
  public static final ActivityRecognitionApi ActivityRecognitionApi = new zzaru();
  public static final String CLIENT_NAME = "activity_recognition";
  private static final Api.zzf<zzash> zzaid = new Api.zzf();
  private static final Api.zza<zzash, Api.ApiOptions.NoOptions> zzaie = new Api.zza()
  {
    public zzash zzq(Context paramAnonymousContext, Looper paramAnonymousLooper, zzg paramAnonymouszzg, Api.ApiOptions.NoOptions paramAnonymousNoOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      return new zzash(paramAnonymousContext, paramAnonymousLooper, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener, "activity_recognition");
    }
  };
  
  public static abstract class zza<R extends Result>
    extends zzaad.zza<R, zzash>
  {
    public zza(GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\ActivityRecognition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */