package com.google.android.gms.location;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzu
  implements Parcelable.Creator<zzt>
{
  static void zza(zzt paramzzt, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaZ(paramParcel);
    zzc.zzb(paramParcel, 1, paramzzt.zzIm(), false);
    zzc.zza(paramParcel, 2, paramzzt.zzvu(), paramInt, false);
    zzc.zza(paramParcel, 3, paramzzt.getTag(), false);
    zzc.zzJ(paramParcel, i);
  }
  
  public zzt zzgS(Parcel paramParcel)
  {
    int i = zzb.zzaY(paramParcel);
    String str = "";
    PendingIntent localPendingIntent = null;
    ArrayList localArrayList = null;
    while (paramParcel.dataPosition() < i)
    {
      int j = zzb.zzaX(paramParcel);
      switch (zzb.zzdc(j))
      {
      default: 
        zzb.zzb(paramParcel, j);
        break;
      case 1: 
        localArrayList = zzb.zzE(paramParcel, j);
        break;
      case 2: 
        localPendingIntent = (PendingIntent)zzb.zza(paramParcel, j, PendingIntent.CREATOR);
        break;
      case 3: 
        str = zzb.zzq(paramParcel, j);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zzb.zza(37 + "Overread allowed size end=" + i, paramParcel);
    }
    return new zzt(localArrayList, localPendingIntent, str);
  }
  
  public zzt[] zzku(int paramInt)
  {
    return new zzt[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\zzu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */