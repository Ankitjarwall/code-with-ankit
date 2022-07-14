package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingApi;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.GeofencingRequest.Builder;
import com.google.android.gms.location.LocationServices.zza;
import com.google.android.gms.location.zzt;
import java.util.List;

public class zzasb
  implements GeofencingApi
{
  public PendingResult<Status> addGeofences(GoogleApiClient paramGoogleApiClient, final GeofencingRequest paramGeofencingRequest, final PendingIntent paramPendingIntent)
  {
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzash paramAnonymouszzash)
        throws RemoteException
      {
        paramAnonymouszzash.zza(paramGeofencingRequest, paramPendingIntent, this);
      }
    });
  }
  
  @Deprecated
  public PendingResult<Status> addGeofences(GoogleApiClient paramGoogleApiClient, List<Geofence> paramList, PendingIntent paramPendingIntent)
  {
    GeofencingRequest.Builder localBuilder = new GeofencingRequest.Builder();
    localBuilder.addGeofences(paramList);
    localBuilder.setInitialTrigger(5);
    return addGeofences(paramGoogleApiClient, localBuilder.build(), paramPendingIntent);
  }
  
  public PendingResult<Status> removeGeofences(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent)
  {
    return zza(paramGoogleApiClient, zzt.zzb(paramPendingIntent));
  }
  
  public PendingResult<Status> removeGeofences(GoogleApiClient paramGoogleApiClient, List<String> paramList)
  {
    return zza(paramGoogleApiClient, zzt.zzE(paramList));
  }
  
  public PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, final zzt paramzzt)
  {
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzash paramAnonymouszzash)
        throws RemoteException
      {
        paramAnonymouszzash.zza(paramzzt, this);
      }
    });
  }
  
  private static abstract class zza
    extends LocationServices.zza<Status>
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


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzasb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */