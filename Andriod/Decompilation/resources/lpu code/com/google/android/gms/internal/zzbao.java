package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzbao
  implements Parcelable.Creator<zzban>
{
  static void zza(zzban paramzzban, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaZ(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzban.zzaiI);
    zzc.zza(paramParcel, 2, paramzzban.zzbEs);
    zzc.zzc(paramParcel, 3, paramzzban.zzbEt, false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzban zzjv(Parcel paramParcel)
  {
    int j = zzb.zzaY(paramParcel);
    ArrayList localArrayList = null;
    boolean bool = false;
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = zzb.zzaX(paramParcel);
      switch (zzb.zzdc(k))
      {
      default: 
        zzb.zzb(paramParcel, k);
        break;
      case 1: 
        i = zzb.zzg(paramParcel, k);
        break;
      case 2: 
        bool = zzb.zzc(paramParcel, k);
        break;
      case 3: 
        localArrayList = zzb.zzc(paramParcel, k, Scope.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzban(i, bool, localArrayList);
  }
  
  public zzban[] zznu(int paramInt)
  {
    return new zzban[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzbao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */