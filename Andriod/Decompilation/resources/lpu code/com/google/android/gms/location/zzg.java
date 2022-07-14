package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zzarw;
import java.util.ArrayList;

public class zzg
  implements Parcelable.Creator<zzf>
{
  static void zza(zzf paramzzf, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaZ(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzf.zzId(), false);
    zzc.zza(paramParcel, 2, paramzzf.getTag(), false);
    zzc.zzc(paramParcel, 3, paramzzf.zzIe(), false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzf zzgI(Parcel paramParcel)
  {
    int i = zzb.zzaY(paramParcel);
    ArrayList localArrayList2 = null;
    String str = null;
    ArrayList localArrayList1 = null;
    while (paramParcel.dataPosition() < i)
    {
      int j = zzb.zzaX(paramParcel);
      switch (zzb.zzdc(j))
      {
      default: 
        zzb.zzb(paramParcel, j);
        break;
      case 1: 
        localArrayList1 = zzb.zzc(paramParcel, j, zzd.CREATOR);
        break;
      case 2: 
        str = zzb.zzq(paramParcel, j);
        break;
      case 3: 
        localArrayList2 = zzb.zzc(paramParcel, j, zzarw.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zzb.zza(37 + "Overread allowed size end=" + i, paramParcel);
    }
    return new zzf(localArrayList1, str, localArrayList2);
  }
  
  public zzf[] zzkd(int paramInt)
  {
    return new zzf[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */