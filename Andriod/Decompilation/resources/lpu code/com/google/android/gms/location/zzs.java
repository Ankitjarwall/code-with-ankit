package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzs
  implements Parcelable.Creator<LocationSettingsStates>
{
  static void zza(LocationSettingsStates paramLocationSettingsStates, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaZ(paramParcel);
    zzc.zza(paramParcel, 1, paramLocationSettingsStates.isGpsUsable());
    zzc.zza(paramParcel, 2, paramLocationSettingsStates.isNetworkLocationUsable());
    zzc.zza(paramParcel, 3, paramLocationSettingsStates.isBleUsable());
    zzc.zza(paramParcel, 4, paramLocationSettingsStates.isGpsPresent());
    zzc.zza(paramParcel, 5, paramLocationSettingsStates.isNetworkLocationPresent());
    zzc.zza(paramParcel, 6, paramLocationSettingsStates.isBlePresent());
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public LocationSettingsStates zzgR(Parcel paramParcel)
  {
    int i = zzb.zzaY(paramParcel);
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    boolean bool5 = false;
    boolean bool6 = false;
    while (paramParcel.dataPosition() < i)
    {
      int j = zzb.zzaX(paramParcel);
      switch (zzb.zzdc(j))
      {
      default: 
        zzb.zzb(paramParcel, j);
        break;
      case 1: 
        bool6 = zzb.zzc(paramParcel, j);
        break;
      case 2: 
        bool5 = zzb.zzc(paramParcel, j);
        break;
      case 3: 
        bool4 = zzb.zzc(paramParcel, j);
        break;
      case 4: 
        bool3 = zzb.zzc(paramParcel, j);
        break;
      case 5: 
        bool2 = zzb.zzc(paramParcel, j);
        break;
      case 6: 
        bool1 = zzb.zzc(paramParcel, j);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zzb.zza(37 + "Overread allowed size end=" + i, paramParcel);
    }
    return new LocationSettingsStates(bool6, bool5, bool4, bool3, bool2, bool1);
  }
  
  public LocationSettingsStates[] zzkr(int paramInt)
  {
    return new LocationSettingsStates[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\zzs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */