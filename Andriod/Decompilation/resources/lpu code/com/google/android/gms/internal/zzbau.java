package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzad;

public class zzbau
  extends zza
{
  public static final Parcelable.Creator<zzbau> CREATOR = new zzbav();
  final int zzaiI;
  final zzad zzbEw;
  
  zzbau(int paramInt, zzad paramzzad)
  {
    this.zzaiI = paramInt;
    this.zzbEw = paramzzad;
  }
  
  public zzbau(zzad paramzzad)
  {
    this(1, paramzzad);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzbav.zza(this, paramParcel, paramInt);
  }
  
  public zzad zzPV()
  {
    return this.zzbEw;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzbau.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */