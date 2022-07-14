package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.zzj;
import com.google.android.gms.location.zzj.zza;
import com.google.android.gms.location.zzk;
import com.google.android.gms.location.zzk.zza;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class zzasg
{
  private final Context mContext;
  private final Map<zzabh.zzb<LocationListener>, zzb> zzaWg = new HashMap();
  private ContentProviderClient zzbkD = null;
  private boolean zzbkE = false;
  private final Map<zzabh.zzb<LocationCallback>, zza> zzbkF = new HashMap();
  private final zzaso<zzase> zzbks;
  
  public zzasg(Context paramContext, zzaso<zzase> paramzzaso)
  {
    this.mContext = paramContext;
    this.zzbks = paramzzaso;
  }
  
  private zzb zzf(zzabh<LocationListener> paramzzabh)
  {
    synchronized (this.zzaWg)
    {
      zzb localzzb2 = (zzb)this.zzaWg.get(paramzzabh.zzwW());
      zzb localzzb1 = localzzb2;
      if (localzzb2 == null) {
        localzzb1 = new zzb(paramzzabh);
      }
      this.zzaWg.put(paramzzabh.zzwW(), localzzb1);
      return localzzb1;
    }
  }
  
  private zza zzg(zzabh<LocationCallback> paramzzabh)
  {
    synchronized (this.zzbkF)
    {
      zza localzza2 = (zza)this.zzbkF.get(paramzzabh.zzwW());
      zza localzza1 = localzza2;
      if (localzza2 == null) {
        localzza1 = new zza(paramzzabh);
      }
      this.zzbkF.put(paramzzabh.zzwW(), localzza1);
      return localzza1;
    }
  }
  
  public Location getLastLocation()
  {
    this.zzbks.zzxC();
    try
    {
      Location localLocation = ((zzase)this.zzbks.zzxD()).zzeR(this.mContext.getPackageName());
      return localLocation;
    }
    catch (RemoteException localRemoteException)
    {
      throw new IllegalStateException(localRemoteException);
    }
  }
  
  public void removeAllListeners()
  {
    Object localObject3;
    try
    {
      synchronized (this.zzaWg)
      {
        Iterator localIterator1 = this.zzaWg.values().iterator();
        while (localIterator1.hasNext())
        {
          localObject3 = (zzb)localIterator1.next();
          if (localObject3 != null) {
            ((zzase)this.zzbks.zzxD()).zza(zzask.zza((zzk)localObject3, null));
          }
        }
      }
      this.zzaWg.clear();
    }
    catch (RemoteException localRemoteException)
    {
      throw new IllegalStateException(localRemoteException);
    }
    synchronized (this.zzbkF)
    {
      Iterator localIterator2 = this.zzbkF.values().iterator();
      while (localIterator2.hasNext())
      {
        localObject3 = (zza)localIterator2.next();
        if (localObject3 != null) {
          ((zzase)this.zzbks.zzxD()).zza(zzask.zza((zzj)localObject3, null));
        }
      }
    }
    this.zzbkF.clear();
  }
  
  public LocationAvailability zzIp()
  {
    this.zzbks.zzxC();
    try
    {
      LocationAvailability localLocationAvailability = ((zzase)this.zzbks.zzxD()).zzeS(this.mContext.getPackageName());
      return localLocationAvailability;
    }
    catch (RemoteException localRemoteException)
    {
      throw new IllegalStateException(localRemoteException);
    }
  }
  
  public void zzIq()
  {
    if (this.zzbkE) {}
    try
    {
      zzaG(false);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new IllegalStateException(localRemoteException);
    }
  }
  
  public void zza(PendingIntent paramPendingIntent, zzasc paramzzasc)
    throws RemoteException
  {
    this.zzbks.zzxC();
    ((zzase)this.zzbks.zzxD()).zza(zzask.zzb(paramPendingIntent, paramzzasc));
  }
  
  public void zza(zzabh.zzb<LocationListener> paramzzb, zzasc paramzzasc)
    throws RemoteException
  {
    this.zzbks.zzxC();
    zzac.zzb(paramzzb, "Invalid null listener key");
    synchronized (this.zzaWg)
    {
      paramzzb = (zzb)this.zzaWg.remove(paramzzb);
      if (paramzzb != null)
      {
        paramzzb.release();
        ((zzase)this.zzbks.zzxD()).zza(zzask.zza(paramzzb, paramzzasc));
      }
      return;
    }
  }
  
  public void zza(zzasc paramzzasc)
    throws RemoteException
  {
    this.zzbks.zzxC();
    ((zzase)this.zzbks.zzxD()).zza(paramzzasc);
  }
  
  public void zza(zzasi paramzzasi, zzabh<LocationCallback> paramzzabh, zzasc paramzzasc)
    throws RemoteException
  {
    this.zzbks.zzxC();
    paramzzabh = zzg(paramzzabh);
    ((zzase)this.zzbks.zzxD()).zza(zzask.zza(paramzzasi, paramzzabh, paramzzasc));
  }
  
  public void zza(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent, zzasc paramzzasc)
    throws RemoteException
  {
    this.zzbks.zzxC();
    ((zzase)this.zzbks.zzxD()).zza(zzask.zza(zzasi.zzb(paramLocationRequest), paramPendingIntent, paramzzasc));
  }
  
  public void zza(LocationRequest paramLocationRequest, zzabh<LocationListener> paramzzabh, zzasc paramzzasc)
    throws RemoteException
  {
    this.zzbks.zzxC();
    paramzzabh = zzf(paramzzabh);
    ((zzase)this.zzbks.zzxD()).zza(zzask.zza(zzasi.zzb(paramLocationRequest), paramzzabh, paramzzasc));
  }
  
  public void zzaG(boolean paramBoolean)
    throws RemoteException
  {
    this.zzbks.zzxC();
    ((zzase)this.zzbks.zzxD()).zzaG(paramBoolean);
    this.zzbkE = paramBoolean;
  }
  
  public void zzb(zzabh.zzb<LocationCallback> paramzzb, zzasc paramzzasc)
    throws RemoteException
  {
    this.zzbks.zzxC();
    zzac.zzb(paramzzb, "Invalid null listener key");
    synchronized (this.zzbkF)
    {
      paramzzb = (zza)this.zzbkF.remove(paramzzb);
      if (paramzzb != null)
      {
        paramzzb.release();
        ((zzase)this.zzbks.zzxD()).zza(zzask.zza(paramzzb, paramzzasc));
      }
      return;
    }
  }
  
  public void zzd(Location paramLocation)
    throws RemoteException
  {
    this.zzbks.zzxC();
    ((zzase)this.zzbks.zzxD()).zzd(paramLocation);
  }
  
  private static class zza
    extends zzj.zza
  {
    private final zzabh<LocationCallback> zzaDf;
    
    zza(zzabh<LocationCallback> paramzzabh)
    {
      this.zzaDf = paramzzabh;
    }
    
    public void onLocationAvailability(final LocationAvailability paramLocationAvailability)
    {
      this.zzaDf.zza(new zzabh.zzc()
      {
        public void zza(LocationCallback paramAnonymousLocationCallback)
        {
          paramAnonymousLocationCallback.onLocationAvailability(paramLocationAvailability);
        }
        
        public void zzwc() {}
      });
    }
    
    public void onLocationResult(final LocationResult paramLocationResult)
    {
      this.zzaDf.zza(new zzabh.zzc()
      {
        public void zza(LocationCallback paramAnonymousLocationCallback)
        {
          paramAnonymousLocationCallback.onLocationResult(paramLocationResult);
        }
        
        public void zzwc() {}
      });
    }
    
    public void release()
    {
      try
      {
        this.zzaDf.clear();
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
  }
  
  private static class zzb
    extends zzk.zza
  {
    private final zzabh<LocationListener> zzaDf;
    
    zzb(zzabh<LocationListener> paramzzabh)
    {
      this.zzaDf = paramzzabh;
    }
    
    public void onLocationChanged(final Location paramLocation)
    {
      try
      {
        this.zzaDf.zza(new zzabh.zzc()
        {
          public void zza(LocationListener paramAnonymousLocationListener)
          {
            paramAnonymousLocationListener.onLocationChanged(paramLocation);
          }
          
          public void zzwc() {}
        });
        return;
      }
      finally
      {
        paramLocation = finally;
        throw paramLocation;
      }
    }
    
    public void release()
    {
      try
      {
        this.zzaDf.clear();
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzasg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */