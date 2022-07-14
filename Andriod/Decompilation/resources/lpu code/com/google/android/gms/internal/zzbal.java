package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzbal
  implements Parcelable.Creator<zzbak>
{
  static void zza(zzbak paramzzbak, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaZ(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzbak.zzaiI);
    zzc.zzc(paramParcel, 2, paramzzbak.zzPR());
    zzc.zza(paramParcel, 3, paramzzbak.zzPS(), paramInt, false);
    zzc.zzJ(paramParcel, i);
  }
  
  public zzbak zzju(Parcel paramParcel)
  {
    int k = zzb.zzaY(paramParcel);
    Intent localIntent = null;
    int j = 0;
    int i = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = zzb.zzaX(paramParcel);
      switch (zzb.zzdc(m))
      {
      default: 
        zzb.zzb(paramParcel, m);
        break;
      case 1: 
        i = zzb.zzg(paramParcel, m);
        break;
      case 2: 
        j = zzb.zzg(paramParcel, m);
        break;
      case 3: 
        localIntent = (Intent)zzb.zza(paramParcel, m, Intent.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zzb.zza(37 + "Overread allowed size end=" + k, paramParcel);
    }
    return new zzbak(i, j, localIntent);
  }
  
  public zzbak[] zznt(int paramInt)
  {
    return new zzbak[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzbal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */