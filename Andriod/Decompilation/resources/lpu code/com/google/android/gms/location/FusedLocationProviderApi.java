package com.google.android.gms.location;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public abstract interface FusedLocationProviderApi
{
  @Deprecated
  public static final String KEY_LOCATION_CHANGED = "com.google.android.location.LOCATION";
  public static final String KEY_MOCK_LOCATION = "mockLocation";
  
  public abstract PendingResult<Status> flushLocations(GoogleApiClient paramGoogleApiClient);
  
  @RequiresPermission(anyOf={"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public abstract Location getLastLocation(GoogleApiClient paramGoogleApiClient);
  
  @RequiresPermission(anyOf={"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public abstract LocationAvailability getLocationAvailability(GoogleApiClient paramGoogleApiClient);
  
  public abstract PendingResult<Status> removeLocationUpdates(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent);
  
  public abstract PendingResult<Status> removeLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationCallback paramLocationCallback);
  
  public abstract PendingResult<Status> removeLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationListener paramLocationListener);
  
  @RequiresPermission(anyOf={"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public abstract PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationRequest paramLocationRequest, PendingIntent paramPendingIntent);
  
  @RequiresPermission(anyOf={"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public abstract PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationRequest paramLocationRequest, LocationCallback paramLocationCallback, Looper paramLooper);
  
  @RequiresPermission(anyOf={"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public abstract PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationRequest paramLocationRequest, LocationListener paramLocationListener);
  
  @RequiresPermission(anyOf={"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public abstract PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationRequest paramLocationRequest, LocationListener paramLocationListener, Looper paramLooper);
  
  @RequiresPermission(anyOf={"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public abstract PendingResult<Status> setMockLocation(GoogleApiClient paramGoogleApiClient, Location paramLocation);
  
  @RequiresPermission(anyOf={"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public abstract PendingResult<Status> setMockMode(GoogleApiClient paramGoogleApiClient, boolean paramBoolean);
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\FusedLocationProviderApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */