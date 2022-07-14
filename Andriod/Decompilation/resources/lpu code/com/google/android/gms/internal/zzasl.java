package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzasl
  implements Parcelable.Creator<zzask>
{
  static void zza(zzask paramzzask, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaZ(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzask.zzbkO);
    zzc.zza(paramParcel, 2, paramzzask.zzbkP, paramInt, false);
    zzc.zza(paramParcel, 3, paramzzask.zzIr(), false);
    zzc.zza(paramParcel, 4, paramzzask.mPendingIntent, paramInt, false);
    zzc.zza(paramParcel, 5, paramzzask.zzIs(), false);
    zzc.zza(paramParcel, 6, paramzzask.zzIt(), false);
    zzc.zzJ(paramParcel, i);
  }
  
  public zzask zzgW(Parcel paramParcel)
  {
    int j = zzb.zzaY(paramParcel);
    int i = 1;
    IBinder localIBinder1 = null;
    IBinder localIBinder2 = null;
    PendingIntent localPendingIntent = null;
    IBinder localIBinder3 = null;
    zzasi localzzasi = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zzb.zzaX(paramParcel);
      switch (zzb.zzdc(k))
      {
      default: 
        zzb.zzb(paramParcel, k);
        break;
      case 1: 
        i = zzb.zzg(paramParcel, k);
        break;
      case 2: 
        localzzasi = (zzasi)zzb.zza(paramParcel, k, zzasi.CREATOR);
        break;
      case 3: 
        localIBinder3 = zzb.zzr(paramParcel, k);
        break;
      case 4: 
        localPendingIntent = (PendingIntent)zzb.zza(paramParcel, k, PendingIntent.CREATOR);
        break;
      case 5: 
        localIBinder2 = zzb.zzr(paramParcel, k);
        break;
      case 6: 
        localIBinder1 = zzb.zzr(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzask(i, localzzasi, localIBinder3, localPendingIntent, localIBinder2, localIBinder1);
  }
  
  public zzask[] zzkz(int paramInt)
  {
    return new zzask[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzasl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */