package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices.zza;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.SettingsApi;

public class zzasp
  implements SettingsApi
{
  public PendingResult<LocationSettingsResult> checkLocationSettings(GoogleApiClient paramGoogleApiClient, LocationSettingsRequest paramLocationSettingsRequest)
  {
    return zza(paramGoogleApiClient, paramLocationSettingsRequest, null);
  }
  
  public PendingResult<LocationSettingsResult> zza(GoogleApiClient paramGoogleApiClient, final LocationSettingsRequest paramLocationSettingsRequest, final String paramString)
  {
    paramGoogleApiClient.zza(new LocationServices.zza(paramGoogleApiClient)
    {
      protected void zza(zzash paramAnonymouszzash)
        throws RemoteException
      {
        paramAnonymouszzash.zza(paramLocationSettingsRequest, this, paramString);
      }
      
      public LocationSettingsResult zzbp(Status paramAnonymousStatus)
      {
        return new LocationSettingsResult(paramAnonymousStatus);
      }
    });
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzasp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */