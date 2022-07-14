package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzasa
  implements Parcelable.Creator<zzarz>
{
  static void zza(zzarz paramzzarz, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaZ(paramParcel);
    zzc.zza(paramParcel, 1, paramzzarz.getStatus(), paramInt, false);
    zzc.zzJ(paramParcel, i);
  }
  
  public zzarz zzgU(Parcel paramParcel)
  {
    int i = zzb.zzaY(paramParcel);
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
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zzb.zza(37 + "Overread allowed size end=" + i, paramParcel);
    }
    return new zzarz(localStatus);
  }
  
  public zzarz[] zzkw(int paramInt)
  {
    return new zzarz[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzasa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */