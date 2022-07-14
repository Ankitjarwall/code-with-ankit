package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzr
  implements Parcelable.Creator<LocationSettingsResult>
{
  static void zza(LocationSettingsResult paramLocationSettingsResult, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaZ(paramParcel);
    zzc.zza(paramParcel, 1, paramLocationSettingsResult.getStatus(), paramInt, false);
    zzc.zza(paramParcel, 2, paramLocationSettingsResult.getLocationSettingsStates(), paramInt, false);
    zzc.zzJ(paramParcel, i);
  }
  
  public LocationSettingsResult zzgQ(Parcel paramParcel)
  {
    int i = zzb.zzaY(paramParcel);
    LocationSettingsStates localLocationSettingsStates = null;
    Status localStatus = null;
    while (paramParcel.dataPosition() < i)
    {
      int j = zzb.zzaX(paramParcel);
      switch (zzb.zzdc(j))
      {
      default: 
        zzb.zzb(paramParcel, j);
        break;
      case 1: 
        localStatus = (Status)zzb.zza(paramParcel, j, Status.CREATOR);
        break;
      case 2: 
        localLocationSettingsStates = (LocationSettingsStates)zzb.zza(paramParcel, j, LocationSettingsStates.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zzb.zza(37 + "Overread allowed size end=" + i, paramParcel);
    }
    return new LocationSettingsResult(localStatus, localLocationSettingsStates);
  }
  
  public LocationSettingsResult[] zzkq(int paramInt)
  {
    return new LocationSettingsResult[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\zzr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */