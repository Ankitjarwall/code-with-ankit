package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzarx
  implements Parcelable.Creator<zzarw>
{
  static void zza(zzarw paramzzarw, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaZ(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzarw.uid);
    zzc.zza(paramParcel, 2, paramzzarw.packageName, false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzarw zzgT(Parcel paramParcel)
  {
    int j = zzb.zzaY(paramParcel);
    int i = 0;
    String str = null;
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
        str = zzb.zzq(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzarw(i, str);
  }
  
  public zzarw[] zzkv(int paramInt)
  {
    return new zzarw[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzarx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */