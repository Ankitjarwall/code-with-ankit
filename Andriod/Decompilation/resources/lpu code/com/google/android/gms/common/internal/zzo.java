package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.stats.zza;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

final class zzo
  extends zzn
  implements Handler.Callback
{
  private final Handler mHandler;
  private final HashMap<zzn.zza, zza> zzaGf = new HashMap();
  private final zza zzaGg;
  private final long zzaGh;
  private final long zzaGi;
  private final Context zzwi;
  
  zzo(Context paramContext)
  {
    this.zzwi = paramContext.getApplicationContext();
    this.mHandler = new Handler(paramContext.getMainLooper(), this);
    this.zzaGg = zza.zzyJ();
    this.zzaGh = 5000L;
    this.zzaGi = 300000L;
  }
  
  public boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default: 
      return false;
    case 0: 
      synchronized (this.zzaGf)
      {
        paramMessage = (zzn.zza)paramMessage.obj;
        ??? = (zza)this.zzaGf.get(paramMessage);
        if ((??? != null) && (((zza)???).zzya()))
        {
          if (((zza)???).isBound()) {
            ((zza)???).zzdo("GmsClientSupervisor");
          }
          this.zzaGf.remove(paramMessage);
        }
        return true;
      }
    }
    for (;;)
    {
      synchronized (this.zzaGf)
      {
        zzn.zza localzza1 = (zzn.zza)paramMessage.obj;
        zza localzza = (zza)this.zzaGf.get(localzza1);
        if ((localzza != null) && (localzza.getState() == 3))
        {
          paramMessage = String.valueOf(localzza1);
          Log.wtf("GmsClientSupervisor", String.valueOf(paramMessage).length() + 47 + "Timeout waiting for ServiceConnection callback " + paramMessage, new Exception());
          ??? = localzza.getComponentName();
          paramMessage = (Message)???;
          if (??? == null) {
            paramMessage = localzza1.getComponentName();
          }
          if (paramMessage == null)
          {
            paramMessage = new ComponentName(localzza1.getPackage(), "unknown");
            localzza.onServiceDisconnected(paramMessage);
          }
        }
        else
        {
          return true;
        }
      }
    }
  }
  
  protected boolean zza(zzn.zza paramzza, ServiceConnection paramServiceConnection, String paramString)
  {
    zzac.zzb(paramServiceConnection, "ServiceConnection must not be null");
    for (;;)
    {
      zza localzza;
      synchronized (this.zzaGf)
      {
        localzza = (zza)this.zzaGf.get(paramzza);
        if (localzza == null)
        {
          localzza = new zza(paramzza);
          localzza.zza(paramServiceConnection, paramString);
          localzza.zzdn(paramString);
          this.zzaGf.put(paramzza, localzza);
          paramzza = localzza;
          boolean bool = paramzza.isBound();
          return bool;
        }
        this.mHandler.removeMessages(0, paramzza);
        if (localzza.zza(paramServiceConnection))
        {
          paramzza = String.valueOf(paramzza);
          throw new IllegalStateException(String.valueOf(paramzza).length() + 81 + "Trying to bind a GmsServiceConnection that was already connected before.  config=" + paramzza);
        }
      }
      localzza.zza(paramServiceConnection, paramString);
      switch (localzza.getState())
      {
      case 1: 
        paramServiceConnection.onServiceConnected(localzza.getComponentName(), localzza.getBinder());
        paramzza = localzza;
        break;
      case 2: 
        localzza.zzdn(paramString);
        paramzza = localzza;
        break;
      default: 
        paramzza = localzza;
      }
    }
  }
  
  protected void zzb(zzn.zza paramzza, ServiceConnection paramServiceConnection, String paramString)
  {
    zzac.zzb(paramServiceConnection, "ServiceConnection must not be null");
    zza localzza;
    synchronized (this.zzaGf)
    {
      localzza = (zza)this.zzaGf.get(paramzza);
      if (localzza == null)
      {
        paramzza = String.valueOf(paramzza);
        throw new IllegalStateException(String.valueOf(paramzza).length() + 50 + "Nonexistent connection status for service config: " + paramzza);
      }
    }
    if (!localzza.zza(paramServiceConnection))
    {
      paramzza = String.valueOf(paramzza);
      throw new IllegalStateException(String.valueOf(paramzza).length() + 76 + "Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + paramzza);
    }
    localzza.zzb(paramServiceConnection, paramString);
    if (localzza.zzya())
    {
      paramzza = this.mHandler.obtainMessage(0, paramzza);
      this.mHandler.sendMessageDelayed(paramzza, this.zzaGh);
    }
  }
  
  private final class zza
    implements ServiceConnection
  {
    private int mState;
    private IBinder zzaFz;
    private ComponentName zzaGe;
    private final Set<ServiceConnection> zzaGj;
    private boolean zzaGk;
    private final zzn.zza zzaGl;
    
    public zza(zzn.zza paramzza)
    {
      this.zzaGl = paramzza;
      this.zzaGj = new HashSet();
      this.mState = 2;
    }
    
    public IBinder getBinder()
    {
      return this.zzaFz;
    }
    
    public ComponentName getComponentName()
    {
      return this.zzaGe;
    }
    
    public int getState()
    {
      return this.mState;
    }
    
    public boolean isBound()
    {
      return this.zzaGk;
    }
    
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      synchronized (zzo.zza(zzo.this))
      {
        zzo.zzb(zzo.this).removeMessages(1, this.zzaGl);
        this.zzaFz = paramIBinder;
        this.zzaGe = paramComponentName;
        Iterator localIterator = this.zzaGj.iterator();
        if (localIterator.hasNext()) {
          ((ServiceConnection)localIterator.next()).onServiceConnected(paramComponentName, paramIBinder);
        }
      }
      this.mState = 1;
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      synchronized (zzo.zza(zzo.this))
      {
        zzo.zzb(zzo.this).removeMessages(1, this.zzaGl);
        this.zzaFz = null;
        this.zzaGe = paramComponentName;
        Iterator localIterator = this.zzaGj.iterator();
        if (localIterator.hasNext()) {
          ((ServiceConnection)localIterator.next()).onServiceDisconnected(paramComponentName);
        }
      }
      this.mState = 2;
    }
    
    public void zza(ServiceConnection paramServiceConnection, String paramString)
    {
      zzo.zzd(zzo.this).zza(zzo.zzc(zzo.this), paramServiceConnection, paramString, this.zzaGl.zzxZ());
      this.zzaGj.add(paramServiceConnection);
    }
    
    public boolean zza(ServiceConnection paramServiceConnection)
    {
      return this.zzaGj.contains(paramServiceConnection);
    }
    
    public void zzb(ServiceConnection paramServiceConnection, String paramString)
    {
      zzo.zzd(zzo.this).zzb(zzo.zzc(zzo.this), paramServiceConnection);
      this.zzaGj.remove(paramServiceConnection);
    }
    
    public void zzdn(String paramString)
    {
      this.mState = 3;
      this.zzaGk = zzo.zzd(zzo.this).zza(zzo.zzc(zzo.this), paramString, this.zzaGl.zzxZ(), this, 129);
      if (this.zzaGk)
      {
        paramString = zzo.zzb(zzo.this).obtainMessage(1, this.zzaGl);
        zzo.zzb(zzo.this).sendMessageDelayed(paramString, zzo.zze(zzo.this));
        return;
      }
      this.mState = 2;
      try
      {
        zzo.zzd(zzo.this).zza(zzo.zzc(zzo.this), this);
        return;
      }
      catch (IllegalArgumentException paramString) {}
    }
    
    public void zzdo(String paramString)
    {
      zzo.zzb(zzo.this).removeMessages(1, this.zzaGl);
      zzo.zzd(zzo.this).zza(zzo.zzc(zzo.this), this);
      this.zzaGk = false;
      this.mState = 2;
    }
    
    public boolean zzya()
    {
      return this.zzaGj.isEmpty();
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */