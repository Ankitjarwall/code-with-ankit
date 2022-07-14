package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationStatusCodes;
import com.google.android.gms.location.zzt;

public class zzash
  extends zzarv
{
  private final zzasg zzbkJ = new zzasg(paramContext, this.zzbks);
  
  public zzash(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString)
  {
    this(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramString, zzg.zzaS(paramContext));
  }
  
  public zzash(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, zzg paramzzg)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramString, paramzzg);
  }
  
  public void disconnect()
  {
    synchronized (this.zzbkJ)
    {
      boolean bool = isConnected();
      if (bool) {}
      try
      {
        this.zzbkJ.removeAllListeners();
        this.zzbkJ.zzIq();
        super.disconnect();
        return;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", localException);
        }
      }
    }
  }
  
  public Location getLastLocation()
  {
    return this.zzbkJ.getLastLocation();
  }
  
  public LocationAvailability zzIp()
  {
    return this.zzbkJ.zzIp();
  }
  
  public void zza(long paramLong, PendingIntent paramPendingIntent)
    throws RemoteException
  {
    zzxC();
    zzac.zzw(paramPendingIntent);
    if (paramLong >= 0L) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzb(bool, "detectionIntervalMillis must be >= 0");
      ((zzase)zzxD()).zza(paramLong, true, paramPendingIntent);
      return;
    }
  }
  
  public void zza(PendingIntent paramPendingIntent, zzasc paramzzasc)
    throws RemoteException
  {
    this.zzbkJ.zza(paramPendingIntent, paramzzasc);
  }
  
  public void zza(zzabh.zzb<LocationListener> paramzzb, zzasc paramzzasc)
    throws RemoteException
  {
    this.zzbkJ.zza(paramzzb, paramzzasc);
  }
  
  public void zza(zzasc paramzzasc)
    throws RemoteException
  {
    this.zzbkJ.zza(paramzzasc);
  }
  
  public void zza(zzasi paramzzasi, zzabh<LocationCallback> paramzzabh, zzasc paramzzasc)
    throws RemoteException
  {
    synchronized (this.zzbkJ)
    {
      this.zzbkJ.zza(paramzzasi, paramzzabh, paramzzasc);
      return;
    }
  }
  
  public void zza(GeofencingRequest paramGeofencingRequest, PendingIntent paramPendingIntent, zzaad.zzb<Status> paramzzb)
    throws RemoteException
  {
    zzxC();
    zzac.zzb(paramGeofencingRequest, "geofencingRequest can't be null.");
    zzac.zzb(paramPendingIntent, "PendingIntent must be specified.");
    zzac.zzb(paramzzb, "ResultHolder not provided.");
    paramzzb = new zza(paramzzb);
    ((zzase)zzxD()).zza(paramGeofencingRequest, paramPendingIntent, paramzzb);
  }
  
  public void zza(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent, zzasc paramzzasc)
    throws RemoteException
  {
    this.zzbkJ.zza(paramLocationRequest, paramPendingIntent, paramzzasc);
  }
  
  public void zza(LocationRequest paramLocationRequest, zzabh<LocationListener> paramzzabh, zzasc paramzzasc)
    throws RemoteException
  {
    synchronized (this.zzbkJ)
    {
      this.zzbkJ.zza(paramLocationRequest, paramzzabh, paramzzasc);
      return;
    }
  }
  
  public void zza(LocationSettingsRequest paramLocationSettingsRequest, zzaad.zzb<LocationSettingsResult> paramzzb, String paramString)
    throws RemoteException
  {
    boolean bool2 = true;
    zzxC();
    if (paramLocationSettingsRequest != null)
    {
      bool1 = true;
      zzac.zzb(bool1, "locationSettingsRequest can't be null nor empty.");
      if (paramzzb == null) {
        break label67;
      }
    }
    label67:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzac.zzb(bool1, "listener can't be null.");
      paramzzb = new zzc(paramzzb);
      ((zzase)zzxD()).zza(paramLocationSettingsRequest, paramzzb, paramString);
      return;
      bool1 = false;
      break;
    }
  }
  
  public void zza(zzt paramzzt, zzaad.zzb<Status> paramzzb)
    throws RemoteException
  {
    zzxC();
    zzac.zzb(paramzzt, "removeGeofencingRequest can't be null.");
    zzac.zzb(paramzzb, "ResultHolder not provided.");
    paramzzb = new zzb(paramzzb);
    ((zzase)zzxD()).zza(paramzzt, paramzzb);
  }
  
  public void zzaG(boolean paramBoolean)
    throws RemoteException
  {
    this.zzbkJ.zzaG(paramBoolean);
  }
  
  public void zzb(zzabh.zzb<LocationCallback> paramzzb, zzasc paramzzasc)
    throws RemoteException
  {
    this.zzbkJ.zzb(paramzzb, paramzzasc);
  }
  
  public void zzc(PendingIntent paramPendingIntent)
    throws RemoteException
  {
    zzxC();
    zzac.zzw(paramPendingIntent);
    ((zzase)zzxD()).zzc(paramPendingIntent);
  }
  
  public void zzd(Location paramLocation)
    throws RemoteException
  {
    this.zzbkJ.zzd(paramLocation);
  }
  
  private static final class zza
    extends zzasd.zza
  {
    private zzaad.zzb<Status> zzbkK;
    
    public zza(zzaad.zzb<Status> paramzzb)
    {
      this.zzbkK = paramzzb;
    }
    
    public void zza(int paramInt, PendingIntent paramPendingIntent)
    {
      Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByPendingIntentResult");
    }
    
    public void zza(int paramInt, String[] paramArrayOfString)
    {
      if (this.zzbkK == null)
      {
        Log.wtf("LocationClientImpl", "onAddGeofenceResult called multiple times");
        return;
      }
      paramArrayOfString = LocationStatusCodes.zzkt(LocationStatusCodes.zzks(paramInt));
      this.zzbkK.setResult(paramArrayOfString);
      this.zzbkK = null;
    }
    
    public void zzb(int paramInt, String[] paramArrayOfString)
    {
      Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByRequestIdsResult");
    }
  }
  
  private static final class zzb
    extends zzasd.zza
  {
    private zzaad.zzb<Status> zzbkK;
    
    public zzb(zzaad.zzb<Status> paramzzb)
    {
      this.zzbkK = paramzzb;
    }
    
    private void zzkx(int paramInt)
    {
      if (this.zzbkK == null)
      {
        Log.wtf("LocationClientImpl", "onRemoveGeofencesResult called multiple times");
        return;
      }
      Status localStatus = LocationStatusCodes.zzkt(LocationStatusCodes.zzks(paramInt));
      this.zzbkK.setResult(localStatus);
      this.zzbkK = null;
    }
    
    public void zza(int paramInt, PendingIntent paramPendingIntent)
    {
      zzkx(paramInt);
    }
    
    public void zza(int paramInt, String[] paramArrayOfString)
    {
      Log.wtf("LocationClientImpl", "Unexpected call to onAddGeofencesResult");
    }
    
    public void zzb(int paramInt, String[] paramArrayOfString)
    {
      zzkx(paramInt);
    }
  }
  
  private static final class zzc
    extends zzasf.zza
  {
    private zzaad.zzb<LocationSettingsResult> zzbkK;
    
    public zzc(zzaad.zzb<LocationSettingsResult> paramzzb)
    {
      if (paramzzb != null) {}
      for (boolean bool = true;; bool = false)
      {
        zzac.zzb(bool, "listener can't be null.");
        this.zzbkK = paramzzb;
        return;
      }
    }
    
    public void zza(LocationSettingsResult paramLocationSettingsResult)
      throws RemoteException
    {
      this.zzbkK.setResult(paramLocationSettingsResult);
      this.zzbkK = null;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */