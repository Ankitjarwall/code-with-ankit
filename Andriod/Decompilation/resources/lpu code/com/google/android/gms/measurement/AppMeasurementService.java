package com.google.android.gms.measurement;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.MainThread;
import com.google.android.gms.internal.zzaum;
import com.google.android.gms.internal.zzaum.zza;

public final class AppMeasurementService
  extends Service
  implements zzaum.zza
{
  private zzaum zzbqj;
  
  private zzaum zzJT()
  {
    if (this.zzbqj == null) {
      this.zzbqj = new zzaum(this);
    }
    return this.zzbqj;
  }
  
  public boolean callServiceStopSelfResult(int paramInt)
  {
    return stopSelfResult(paramInt);
  }
  
  public Context getContext()
  {
    return this;
  }
  
  @MainThread
  public IBinder onBind(Intent paramIntent)
  {
    return zzJT().onBind(paramIntent);
  }
  
  @MainThread
  public void onCreate()
  {
    super.onCreate();
    zzJT().onCreate();
  }
  
  @MainThread
  public void onDestroy()
  {
    zzJT().onDestroy();
    super.onDestroy();
  }
  
  @MainThread
  public void onRebind(Intent paramIntent)
  {
    zzJT().onRebind(paramIntent);
  }
  
  @MainThread
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    zzJT().onStartCommand(paramIntent, paramInt1, paramInt2);
    AppMeasurementReceiver.completeWakefulIntent(paramIntent);
    return 2;
  }
  
  @MainThread
  public boolean onUnbind(Intent paramIntent)
  {
    return zzJT().onUnbind(paramIntent);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\measurement\AppMeasurementService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */