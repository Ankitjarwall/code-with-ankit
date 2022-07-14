package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

@Deprecated
public class zzan
  extends zza
{
  public static final Parcelable.Creator<zzan> CREATOR = new zzao();
  final int zzaiI;
  
  zzan(int paramInt)
  {
    this.zzaiI = paramInt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzao.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\zzan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */