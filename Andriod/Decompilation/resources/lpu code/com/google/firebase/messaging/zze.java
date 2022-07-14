package com.google.firebase.messaging;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zze
  implements Parcelable.Creator<RemoteMessage>
{
  static void zza(RemoteMessage paramRemoteMessage, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaZ(paramParcel);
    zzc.zza(paramParcel, 2, paramRemoteMessage.zzaic, false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public RemoteMessage zzlS(Parcel paramParcel)
  {
    int i = zzb.zzaY(paramParcel);
    Bundle localBundle = null;
    while (paramParcel.dataPosition() < i)
    {
      int j = zzb.zzaX(paramParcel);
      switch (zzb.zzdc(j))
      {
      default: 
        zzb.zzb(paramParcel, j);
        break;
      case 2: 
        localBundle = zzb.zzs(paramParcel, j);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zzb.zza(37 + "Overread allowed size end=" + i, paramParcel);
    }
    return new RemoteMessage(localBundle);
  }
  
  public RemoteMessage[] zzqI(int paramInt)
  {
    return new RemoteMessage[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\messaging\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */