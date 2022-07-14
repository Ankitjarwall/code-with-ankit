package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationServices.zza;

public class zzary
  implements FusedLocationProviderApi
{
  public PendingResult<Status> flushLocations(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzash paramAnonymouszzash)
        throws RemoteException
      {
        paramAnonymouszzash.zza(new zzary.zzb(this));
      }
    });
  }
  
  public Location getLastLocation(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient = LocationServices.zzj(paramGoogleApiClient);
    try
    {
      paramGoogleApiClient = paramGoogleApiClient.getLastLocation();
      return paramGoogleApiClient;
    }
    catch (Exception paramGoogleApiClient) {}
    return null;
  }
  
  public LocationAvailability getLocationAvailability(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient = LocationServices.zzj(paramGoogleApiClient);
    try
    {
      paramGoogleApiClient = paramGoogleApiClient.zzIp();
      return paramGoogleApiClient;
    }
    catch (Exception paramGoogleApiClient) {}
    return null;
  }
  
  public PendingResult<Status> removeLocationUpdates(GoogleApiClient paramGoogleApiClient, final PendingIntent paramPendingIntent)
  {
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzash paramAnonymouszzash)
        throws RemoteException
      {
        zzary.zzb localzzb = new zzary.zzb(this);
        paramAnonymouszzash.zza(paramPendingIntent, localzzb);
      }
    });
  }
  
  public PendingResult<Status> removeLocationUpdates(GoogleApiClient paramGoogleApiClient, final LocationCallback paramLocationCallback)
  {
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzash paramAnonymouszzash)
        throws RemoteException
      {
        paramAnonymouszzash.zzb(zzabi.zza(paramLocationCallback, LocationCallback.class.getSimpleName()), new zzary.zzb(this));
      }
    });
  }
  
  public PendingResult<Status> removeLocationUpdates(GoogleApiClient paramGoogleApiClient, final LocationListener paramLocationListener)
  {
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzash paramAnonymouszzash)
        throws RemoteException
      {
        paramAnonymouszzash.zza(zzabi.zza(paramLocationListener, LocationListener.class.getSimpleName()), new zzary.zzb(this));
      }
    });
  }
  
  public PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, final LocationRequest paramLocationRequest, final PendingIntent paramPendingIntent)
  {
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzash paramAnonymouszzash)
        throws RemoteException
      {
        zzary.zzb localzzb = new zzary.zzb(this);
        paramAnonymouszzash.zza(paramLocationRequest, paramPendingIntent, localzzb);
      }
    });
  }
  
  public PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, final LocationRequest paramLocationRequest, final LocationCallback paramLocationCallback, final Looper paramLooper)
  {
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzash paramAnonymouszzash)
        throws RemoteException
      {
        zzary.zzb localzzb = new zzary.zzb(this);
        paramAnonymouszzash.zza(zzasi.zzb(paramLocationRequest), zzabi.zzb(paramLocationCallback, zzata.zzc(paramLooper), LocationCallback.class.getSimpleName()), localzzb);
      }
    });
  }
  
  public PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, final LocationRequest paramLocationRequest, final LocationListener paramLocationListener)
  {
    zzac.zzb(Looper.myLooper(), "Calling thread must be a prepared Looper thread.");
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzash paramAnonymouszzash)
        throws RemoteException
      {
        zzary.zzb localzzb = new zzary.zzb(this);
        paramAnonymouszzash.zza(paramLocationRequest, zzabi.zzb(paramLocationListener, zzata.zzJl(), LocationListener.class.getSimpleName()), localzzb);
      }
    });
  }
  
  public PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, final LocationRequest paramLocationRequest, final LocationListener paramLocationListener, final Looper paramLooper)
  {
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzash paramAnonymouszzash)
        throws RemoteException
      {
        zzary.zzb localzzb = new zzary.zzb(this);
        paramAnonymouszzash.zza(paramLocationRequest, zzabi.zzb(paramLocationListener, zzata.zzc(paramLooper), LocationListener.class.getSimpleName()), localzzb);
      }
    });
  }
  
  public PendingResult<Status> setMockLocation(GoogleApiClient paramGoogleApiClient, final Location paramLocation)
  {
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzash paramAnonymouszzash)
        throws RemoteException
      {
        paramAnonymouszzash.zzd(paramLocation);
        zzb(Status.zzazx);
      }
    });
  }
  
  public PendingResult<Status> setMockMode(GoogleApiClient paramGoogleApiClient, final boolean paramBoolean)
  {
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzash paramAnonymouszzash)
        throws RemoteException
      {
        paramAnonymouszzash.zzaG(paramBoolean);
        zzb(Status.zzazx);
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
  
  private static class zzb
    extends zzasc.zza
  {
    private final zzaad.zzb<Status> zzaGN;
    
    public zzb(zzaad.zzb<Status> paramzzb)
    {
      this.zzaGN = paramzzb;
    }
    
    public void zza(zzarz paramzzarz)
    {
      this.zzaGN.setResult(paramzzarz.getStatus());
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */