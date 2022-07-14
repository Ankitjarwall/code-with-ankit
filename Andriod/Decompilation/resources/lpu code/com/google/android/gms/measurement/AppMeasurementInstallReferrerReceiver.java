package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;
import com.google.android.gms.internal.zzaub;
import com.google.android.gms.internal.zzaub.zza;

public final class AppMeasurementInstallReferrerReceiver
  extends BroadcastReceiver
  implements zzaub.zza
{
  private zzaub zzbqi;
  
  private zzaub zzJS()
  {
    if (this.zzbqi == null) {
      this.zzbqi = new zzaub(this);
    }
    return this.zzbqi;
  }
  
  public void doStartService(Context paramContext, Intent paramIntent) {}
  
  @MainThread
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    zzJS().onReceive(paramContext, paramIntent);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\measurement\AppMeasurementInstallReferrerReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */