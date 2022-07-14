package com.google.android.gms.location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public abstract interface SettingsApi
{
  public abstract PendingResult<LocationSettingsResult> checkLocationSettings(GoogleApiClient paramGoogleApiClient, LocationSettingsRequest paramLocationSettingsRequest);
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\SettingsApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */