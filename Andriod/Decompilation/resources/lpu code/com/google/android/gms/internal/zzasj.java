package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.location.LocationRequest;
import java.util.List;

public class zzasj
  implements Parcelable.Creator<zzasi>
{
  static void zza(zzasi paramzzasi, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaZ(paramParcel);
    zzc.zza(paramParcel, 1, paramzzasi.zzaWw, paramInt, false);
    zzc.zzc(paramParcel, 5, paramzzasi.zzbjw, false);
    zzc.zza(paramParcel, 6, paramzzasi.mTag, false);
    zzc.zza(paramParcel, 7, paramzzasi.zzbkM);
    zzc.zza(paramParcel, 8, paramzzasi.zzbkN);
    zzc.zzJ(paramParcel, i);
  }
  
  public zzasi zzgV(Parcel paramParcel)
  {
    int i = zzb.zzaY(paramParcel);
    Object localObject = zzasi.zzbkL;
    boolean bool1 = false;
    boolean bool2 = false;
    String str = null;
    LocationRequest localLocationRequest = null;
    while (paramParcel.dataPosition() < i)
    {
      int j = zzb.zzaX(paramParcel);
      switch (zzb.zzdc(j))
      {
      case 2: 
      case 3: 
      case 4: 
      default: 
        zzb.zzb(paramParcel, j);
        break;
      case 1: 
        localLocationRequest = (LocationRequest)zzb.zza(paramParcel, j, LocationRequest.CREATOR);
        break;
      case 5: 
        localObject = zzb.zzc(paramParcel, j, zzarw.CREATOR);
        break;
      case 6: 
        str = zzb.zzq(paramParcel, j);
        break;
      case 7: 
        bool2 = zzb.zzc(paramParcel, j);
        break;
      case 8: 
        bool1 = zzb.zzc(paramParcel, j);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zzb.zza(37 + "Overread allowed size end=" + i, paramParcel);
    }
    return new zzasi(localLocationRequest, (List)localObject, str, bool2, bool1);
  }
  
  public zzasi[] zzky(int paramInt)
  {
    return new zzasi[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzasj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */