package com.google.android.gms.location;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import java.util.ArrayList;

public class zzc
  implements Parcelable.Creator<ActivityRecognitionResult>
{
  static void zza(ActivityRecognitionResult paramActivityRecognitionResult, Parcel paramParcel, int paramInt)
  {
    paramInt = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(paramParcel);
    com.google.android.gms.common.internal.safeparcel.zzc.zzc(paramParcel, 1, paramActivityRecognitionResult.zzbjp, false);
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 2, paramActivityRecognitionResult.zzbjq);
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 3, paramActivityRecognitionResult.zzbjr);
    com.google.android.gms.common.internal.safeparcel.zzc.zzc(paramParcel, 4, paramActivityRecognitionResult.zzbjs);
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 5, paramActivityRecognitionResult.extras, false);
    com.google.android.gms.common.internal.safeparcel.zzc.zzJ(paramParcel, paramInt);
  }
  
  public ActivityRecognitionResult zzgG(Parcel paramParcel)
  {
    int j = zzb.zzaY(paramParcel);
    int i = 0;
    Bundle localBundle = null;
    long l1 = 0L;
    long l2 = 0L;
    ArrayList localArrayList = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zzb.zzaX(paramParcel);
      switch (zzb.zzdc(k))
      {
      default: 
        zzb.zzb(paramParcel, k);
        break;
      case 1: 
        localArrayList = zzb.zzc(paramParcel, k, DetectedActivity.CREATOR);
        break;
      case 2: 
        l2 = zzb.zzi(paramParcel, k);
        break;
      case 3: 
        l1 = zzb.zzi(paramParcel, k);
        break;
      case 4: 
        i = zzb.zzg(paramParcel, k);
        break;
      case 5: 
        localBundle = zzb.zzs(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new ActivityRecognitionResult(localArrayList, l2, l1, i, localBundle);
  }
  
  public ActivityRecognitionResult[] zzkb(int paramInt)
  {
    return new ActivityRecognitionResult[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */