package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzp
  implements Parcelable.Creator<zzo>
{
  static void zza(zzo paramzzo, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaZ(paramParcel);
    zzc.zza(paramParcel, 1, paramzzo.zzIg(), false);
    zzc.zza(paramParcel, 2, paramzzo.zzIh(), false);
    zzc.zzc(paramParcel, 3, paramzzo.zzIi());
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzo zzgO(Parcel paramParcel)
  {
    int j = zzb.zzaY(paramParcel);
    String str1 = "";
    String str2 = "";
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = zzb.zzaX(paramParcel);
      switch (zzb.zzdc(k))
      {
      default: 
        zzb.zzb(paramParcel, k);
        break;
      case 1: 
        str1 = zzb.zzq(paramParcel, k);
        break;
      case 2: 
        str2 = zzb.zzq(paramParcel, k);
        break;
      case 3: 
        i = zzb.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzo(str1, str2, i);
  }
  
  public zzo[] zzko(int paramInt)
  {
    return new zzo[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */