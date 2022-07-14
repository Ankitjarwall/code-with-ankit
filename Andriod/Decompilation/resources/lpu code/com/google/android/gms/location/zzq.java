package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzq
  implements Parcelable.Creator<LocationSettingsRequest>
{
  static void zza(LocationSettingsRequest paramLocationSettingsRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaZ(paramParcel);
    zzc.zzc(paramParcel, 1, paramLocationSettingsRequest.zzDf(), false);
    zzc.zza(paramParcel, 2, paramLocationSettingsRequest.zzIj());
    zzc.zza(paramParcel, 3, paramLocationSettingsRequest.zzIk());
    zzc.zza(paramParcel, 5, paramLocationSettingsRequest.zzIl(), paramInt, false);
    zzc.zzJ(paramParcel, i);
  }
  
  public LocationSettingsRequest zzgP(Parcel paramParcel)
  {
    int i = zzb.zzaY(paramParcel);
    zzo localzzo = null;
    boolean bool2 = false;
    boolean bool1 = false;
    ArrayList localArrayList = null;
    while (paramParcel.dataPosition() < i)
    {
      int j = zzb.zzaX(paramParcel);
      switch (zzb.zzdc(j))
      {
      case 4: 
      default: 
        zzb.zzb(paramParcel, j);
        break;
      case 1: 
        localArrayList = zzb.zzc(paramParcel, j, LocationRequest.CREATOR);
        break;
      case 2: 
        bool1 = zzb.zzc(paramParcel, j);
        break;
      case 3: 
        bool2 = zzb.zzc(paramParcel, j);
        break;
      case 5: 
        localzzo = (zzo)zzb.zza(paramParcel, j, zzo.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zzb.zza(37 + "Overread allowed size end=" + i, paramParcel);
    }
    return new LocationSettingsRequest(localArrayList, bool1, bool2, localzzo);
  }
  
  public LocationSettingsRequest[] zzkp(int paramInt)
  {
    return new LocationSettingsRequest[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */