package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzl
  implements Parcelable.Creator<LocationAvailability>
{
  static void zza(LocationAvailability paramLocationAvailability, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaZ(paramParcel);
    zzc.zzc(paramParcel, 1, paramLocationAvailability.zzbjP);
    zzc.zzc(paramParcel, 2, paramLocationAvailability.zzbjQ);
    zzc.zza(paramParcel, 3, paramLocationAvailability.zzbjR);
    zzc.zzc(paramParcel, 4, paramLocationAvailability.zzbjS);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public LocationAvailability zzgL(Parcel paramParcel)
  {
    int m = zzb.zzaY(paramParcel);
    int k = 1000;
    long l = 0L;
    int i = 1;
    int j = 1;
    while (paramParcel.dataPosition() < m)
    {
      int n = zzb.zzaX(paramParcel);
      switch (zzb.zzdc(n))
      {
      default: 
        zzb.zzb(paramParcel, n);
        break;
      case 1: 
        j = zzb.zzg(paramParcel, n);
        break;
      case 2: 
        i = zzb.zzg(paramParcel, n);
        break;
      case 3: 
        l = zzb.zzi(paramParcel, n);
        break;
      case 4: 
        k = zzb.zzg(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zzb.zza(37 + "Overread allowed size end=" + m, paramParcel);
    }
    return new LocationAvailability(k, j, i, l);
  }
  
  public LocationAvailability[] zzkj(int paramInt)
  {
    return new LocationAvailability[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */