package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzm
  implements Parcelable.Creator<LocationRequest>
{
  static void zza(LocationRequest paramLocationRequest, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaZ(paramParcel);
    zzc.zzc(paramParcel, 1, paramLocationRequest.mPriority);
    zzc.zza(paramParcel, 2, paramLocationRequest.zzbjT);
    zzc.zza(paramParcel, 3, paramLocationRequest.zzbjU);
    zzc.zza(paramParcel, 4, paramLocationRequest.zzaWy);
    zzc.zza(paramParcel, 5, paramLocationRequest.zzbjD);
    zzc.zzc(paramParcel, 6, paramLocationRequest.zzbjV);
    zzc.zza(paramParcel, 7, paramLocationRequest.zzbjW);
    zzc.zza(paramParcel, 8, paramLocationRequest.zzbjX);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public LocationRequest zzgM(Parcel paramParcel)
  {
    int k = zzb.zzaY(paramParcel);
    int j = 102;
    long l4 = 3600000L;
    long l3 = 600000L;
    boolean bool = false;
    long l2 = Long.MAX_VALUE;
    int i = Integer.MAX_VALUE;
    float f = 0.0F;
    long l1 = 0L;
    while (paramParcel.dataPosition() < k)
    {
      int m = zzb.zzaX(paramParcel);
      switch (zzb.zzdc(m))
      {
      default: 
        zzb.zzb(paramParcel, m);
        break;
      case 1: 
        j = zzb.zzg(paramParcel, m);
        break;
      case 2: 
        l4 = zzb.zzi(paramParcel, m);
        break;
      case 3: 
        l3 = zzb.zzi(paramParcel, m);
        break;
      case 4: 
        bool = zzb.zzc(paramParcel, m);
        break;
      case 5: 
        l2 = zzb.zzi(paramParcel, m);
        break;
      case 6: 
        i = zzb.zzg(paramParcel, m);
        break;
      case 7: 
        f = zzb.zzl(paramParcel, m);
        break;
      case 8: 
        l1 = zzb.zzi(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zzb.zza(37 + "Overread allowed size end=" + k, paramParcel);
    }
    return new LocationRequest(j, l4, l3, bool, l2, i, f, l1);
  }
  
  public LocationRequest[] zzkm(int paramInt)
  {
    return new LocationRequest[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */