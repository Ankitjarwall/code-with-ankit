package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.location.zzj;
import com.google.android.gms.location.zzj.zza;
import com.google.android.gms.location.zzk;
import com.google.android.gms.location.zzk.zza;

public class zzask
  extends zza
{
  public static final Parcelable.Creator<zzask> CREATOR = new zzasl();
  PendingIntent mPendingIntent;
  int zzbkO;
  zzasi zzbkP;
  zzk zzbkQ;
  zzj zzbkR;
  zzasc zzbkS;
  
  zzask(int paramInt, zzasi paramzzasi, IBinder paramIBinder1, PendingIntent paramPendingIntent, IBinder paramIBinder2, IBinder paramIBinder3)
  {
    this.zzbkO = paramInt;
    this.zzbkP = paramzzasi;
    if (paramIBinder1 == null)
    {
      paramzzasi = null;
      this.zzbkQ = paramzzasi;
      this.mPendingIntent = paramPendingIntent;
      if (paramIBinder2 != null) {
        break label68;
      }
      paramzzasi = null;
      label41:
      this.zzbkR = paramzzasi;
      if (paramIBinder3 != null) {
        break label77;
      }
    }
    label68:
    label77:
    for (paramzzasi = (zzasi)localObject;; paramzzasi = zzasc.zza.zzdg(paramIBinder3))
    {
      this.zzbkS = paramzzasi;
      return;
      paramzzasi = zzk.zza.zzde(paramIBinder1);
      break;
      paramzzasi = zzj.zza.zzdd(paramIBinder2);
      break label41;
    }
  }
  
  public static zzask zza(zzasi paramzzasi, PendingIntent paramPendingIntent, @Nullable zzasc paramzzasc)
  {
    if (paramzzasc != null) {}
    for (paramzzasc = paramzzasc.asBinder();; paramzzasc = null) {
      return new zzask(1, paramzzasi, null, paramPendingIntent, null, paramzzasc);
    }
  }
  
  public static zzask zza(zzasi paramzzasi, zzj paramzzj, @Nullable zzasc paramzzasc)
  {
    IBinder localIBinder = paramzzj.asBinder();
    if (paramzzasc != null) {}
    for (paramzzj = paramzzasc.asBinder();; paramzzj = null) {
      return new zzask(1, paramzzasi, null, null, localIBinder, paramzzj);
    }
  }
  
  public static zzask zza(zzasi paramzzasi, zzk paramzzk, @Nullable zzasc paramzzasc)
  {
    IBinder localIBinder = paramzzk.asBinder();
    if (paramzzasc != null) {}
    for (paramzzk = paramzzasc.asBinder();; paramzzk = null) {
      return new zzask(1, paramzzasi, localIBinder, null, null, paramzzk);
    }
  }
  
  public static zzask zza(zzj paramzzj, @Nullable zzasc paramzzasc)
  {
    IBinder localIBinder = paramzzj.asBinder();
    if (paramzzasc != null) {}
    for (paramzzj = paramzzasc.asBinder();; paramzzj = null) {
      return new zzask(2, null, null, null, localIBinder, paramzzj);
    }
  }
  
  public static zzask zza(zzk paramzzk, @Nullable zzasc paramzzasc)
  {
    IBinder localIBinder = paramzzk.asBinder();
    if (paramzzasc != null) {}
    for (paramzzk = paramzzasc.asBinder();; paramzzk = null) {
      return new zzask(2, null, localIBinder, null, null, paramzzk);
    }
  }
  
  public static zzask zzb(PendingIntent paramPendingIntent, @Nullable zzasc paramzzasc)
  {
    if (paramzzasc != null) {}
    for (paramzzasc = paramzzasc.asBinder();; paramzzasc = null) {
      return new zzask(2, null, null, paramPendingIntent, null, paramzzasc);
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzasl.zza(this, paramParcel, paramInt);
  }
  
  IBinder zzIr()
  {
    if (this.zzbkQ == null) {
      return null;
    }
    return this.zzbkQ.asBinder();
  }
  
  IBinder zzIs()
  {
    if (this.zzbkR == null) {
      return null;
    }
    return this.zzbkR.asBinder();
  }
  
  IBinder zzIt()
  {
    if (this.zzbkS == null) {
      return null;
    }
    return this.zzbkS.asBinder();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */