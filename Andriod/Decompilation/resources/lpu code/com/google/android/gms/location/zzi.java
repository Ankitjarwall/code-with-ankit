package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zzasm;
import java.util.ArrayList;

public class zzi
  implements Parcelable.Creator<GeofencingRequest>
{
  static void zza(GeofencingRequest paramGeofencingRequest, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaZ(paramParcel);
    zzc.zzc(paramParcel, 1, paramGeofencingRequest.zzIf(), false);
    zzc.zzc(paramParcel, 2, paramGeofencingRequest.getInitialTrigger());
    zzc.zza(paramParcel, 3, paramGeofencingRequest.getTag(), false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public GeofencingRequest zzgK(Parcel paramParcel)
  {
    int j = zzb.zzaY(paramParcel);
    ArrayList localArrayList = null;
    int i = 0;
    String str = "";
    while (paramParcel.dataPosition() < j)
    {
      int k = zzb.zzaX(paramParcel);
      switch (zzb.zzdc(k))
      {
      default: 
        zzb.zzb(paramParcel, k);
        break;
      case 1: 
        localArrayList = zzb.zzc(paramParcel, k, zzasm.CREATOR);
        break;
      case 2: 
        i = zzb.zzg(paramParcel, k);
        break;
      case 3: 
        str = zzb.zzq(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new GeofencingRequest(localArrayList, i, str);
  }
  
  public GeofencingRequest[] zzki(int paramInt)
  {
    return new GeofencingRequest[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */