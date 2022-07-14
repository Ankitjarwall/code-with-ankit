package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzasn
  implements Parcelable.Creator<zzasm>
{
  static void zza(zzasm paramzzasm, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaZ(paramParcel);
    zzc.zza(paramParcel, 1, paramzzasm.getRequestId(), false);
    zzc.zza(paramParcel, 2, paramzzasm.getExpirationTime());
    zzc.zza(paramParcel, 3, paramzzasm.zzIu());
    zzc.zza(paramParcel, 4, paramzzasm.getLatitude());
    zzc.zza(paramParcel, 5, paramzzasm.getLongitude());
    zzc.zza(paramParcel, 6, paramzzasm.getRadius());
    zzc.zzc(paramParcel, 7, paramzzasm.zzIv());
    zzc.zzc(paramParcel, 8, paramzzasm.zzIw());
    zzc.zzc(paramParcel, 9, paramzzasm.zzIx());
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzasm zzgX(Parcel paramParcel)
  {
    int m = zzb.zzaY(paramParcel);
    String str = null;
    int k = 0;
    short s = 0;
    double d2 = 0.0D;
    double d1 = 0.0D;
    float f = 0.0F;
    long l = 0L;
    int j = 0;
    int i = -1;
    while (paramParcel.dataPosition() < m)
    {
      int n = zzb.zzaX(paramParcel);
      switch (zzb.zzdc(n))
      {
      default: 
        zzb.zzb(paramParcel, n);
        break;
      case 1: 
        str = zzb.zzq(paramParcel, n);
        break;
      case 2: 
        l = zzb.zzi(paramParcel, n);
        break;
      case 3: 
        s = zzb.zzf(paramParcel, n);
        break;
      case 4: 
        d2 = zzb.zzn(paramParcel, n);
        break;
      case 5: 
        d1 = zzb.zzn(paramParcel, n);
        break;
      case 6: 
        f = zzb.zzl(paramParcel, n);
        break;
      case 7: 
        k = zzb.zzg(paramParcel, n);
        break;
      case 8: 
        j = zzb.zzg(paramParcel, n);
        break;
      case 9: 
        i = zzb.zzg(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zzb.zza(37 + "Overread allowed size end=" + m, paramParcel);
    }
    return new zzasm(str, k, s, d2, d1, f, l, j, i);
  }
  
  public zzasm[] zzkC(int paramInt)
  {
    return new zzasm[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzasn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */