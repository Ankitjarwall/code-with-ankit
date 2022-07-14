package com.google.android.gms.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.List;

public class zzn
  implements Parcelable.Creator<LocationResult>
{
  static void zza(LocationResult paramLocationResult, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaZ(paramParcel);
    zzc.zzc(paramParcel, 1, paramLocationResult.getLocations(), false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public LocationResult zzgN(Parcel paramParcel)
  {
    int i = zzb.zzaY(paramParcel);
    Object localObject = LocationResult.zzbjY;
    while (paramParcel.dataPosition() < i)
    {
      int j = zzb.zzaX(paramParcel);
      switch (zzb.zzdc(j))
      {
      default: 
        zzb.zzb(paramParcel, j);
        break;
      case 1: 
        localObject = zzb.zzc(paramParcel, j, Location.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zzb.zza(37 + "Overread allowed size end=" + i, paramParcel);
    }
    return new LocationResult((List)localObject);
  }
  
  public LocationResult[] zzkn(int paramInt)
  {
    return new LocationResult[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */