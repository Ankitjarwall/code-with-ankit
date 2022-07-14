package com.google.android.gms.common.stats;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import com.google.android.gms.common.util.zzd;
import java.util.Collections;
import java.util.List;

public class zza
{
  private static final Object zzaGb = new Object();
  private static zza zzaHq;
  private final List<String> zzaHr = Collections.EMPTY_LIST;
  private final List<String> zzaHs = Collections.EMPTY_LIST;
  private final List<String> zzaHt = Collections.EMPTY_LIST;
  private final List<String> zzaHu = Collections.EMPTY_LIST;
  
  private boolean zzc(Context paramContext, Intent paramIntent)
  {
    paramIntent = paramIntent.getComponent();
    if (paramIntent == null) {
      return false;
    }
    return zzd.zzF(paramContext, paramIntent.getPackageName());
  }
  
  public static zza zzyJ()
  {
    synchronized (zzaGb)
    {
      if (zzaHq == null) {
        zzaHq = new zza();
      }
      return zzaHq;
    }
  }
  
  @SuppressLint({"UntrackedBindService"})
  public void zza(Context paramContext, ServiceConnection paramServiceConnection)
  {
    paramContext.unbindService(paramServiceConnection);
  }
  
  public void zza(Context paramContext, ServiceConnection paramServiceConnection, String paramString, Intent paramIntent) {}
  
  public boolean zza(Context paramContext, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt)
  {
    return zza(paramContext, paramContext.getClass().getName(), paramIntent, paramServiceConnection, paramInt);
  }
  
  @SuppressLint({"UntrackedBindService"})
  public boolean zza(Context paramContext, String paramString, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt)
  {
    if (zzc(paramContext, paramIntent))
    {
      Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
      return false;
    }
    return paramContext.bindService(paramIntent, paramServiceConnection, paramInt);
  }
  
  public void zzb(Context paramContext, ServiceConnection paramServiceConnection) {}
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\stats\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */