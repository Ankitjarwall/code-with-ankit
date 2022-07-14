package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzh
  implements Parcelable.Creator<DetectedActivity>
{
  static void zza(DetectedActivity paramDetectedActivity, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaZ(paramParcel);
    zzc.zzc(paramParcel, 1, paramDetectedActivity.zzbjA);
    zzc.zzc(paramParcel, 2, paramDetectedActivity.zzbjB);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public DetectedActivity zzgJ(Parcel paramParcel)
  {
    int k = zzb.zzaY(paramParcel);
    int j = 0;
    int i = 0;
    if (paramParcel.dataPosition() < k)
    {
      int m = zzb.zzaX(paramParcel);
      switch (zzb.zzdc(m))
      {
      default: 
        zzb.zzb(paramParcel, m);
      }
      for (;;)
      {
        break;
        i = zzb.zzg(paramParcel, m);
        continue;
        j = zzb.zzg(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zzb.zza(37 + "Overread allowed size end=" + k, paramParcel);
    }
    return new DetectedActivity(i, j);
  }
  
  public DetectedActivity[] zzkg(int paramInt)
  {
    return new DetectedActivity[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */