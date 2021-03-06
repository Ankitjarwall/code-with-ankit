package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zze
  implements Parcelable.Creator<zzd>
{
  static void zza(zzd paramzzd, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaZ(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzd.zzaiI);
    zzc.zza(paramParcel, 2, paramzzd.zzaEW, false);
    zzc.zza(paramParcel, 3, paramzzd.zzaEX, paramInt, false);
    zzc.zza(paramParcel, 4, paramzzd.zzaEY, false);
    zzc.zza(paramParcel, 5, paramzzd.zzaEZ, false);
    zzc.zzJ(paramParcel, i);
  }
  
  public zzd zzaQ(Parcel paramParcel)
  {
    int j = zzb.zzaY(paramParcel);
    int i = 0;
    Integer localInteger1 = null;
    Integer localInteger2 = null;
    Scope[] arrayOfScope = null;
    IBinder localIBinder = null;
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
        localIBinder = zzb.zzr(paramParcel, k);
        break;
      case 3: 
        arrayOfScope = (Scope[])zzb.zzb(paramParcel, k, Scope.CREATOR);
        break;
      case 4: 
        localInteger2 = zzb.zzh(paramParcel, k);
        break;
      case 5: 
        localInteger1 = zzb.zzh(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzd(i, localIBinder, arrayOfScope, localInteger2, localInteger1);
  }
  
  public zzd[] zzcR(int paramInt)
  {
    return new zzd[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */