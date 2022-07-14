package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaf;

public class zzbaw
  extends zza
{
  public static final Parcelable.Creator<zzbaw> CREATOR = new zzbax();
  private final ConnectionResult zzaGE;
  final int zzaiI;
  private final zzaf zzbEx;
  
  public zzbaw(int paramInt)
  {
    this(new ConnectionResult(paramInt, null), null);
  }
  
  zzbaw(int paramInt, ConnectionResult paramConnectionResult, zzaf paramzzaf)
  {
    this.zzaiI = paramInt;
    this.zzaGE = paramConnectionResult;
    this.zzbEx = paramzzaf;
  }
  
  public zzbaw(ConnectionResult paramConnectionResult, zzaf paramzzaf)
  {
    this(1, paramConnectionResult, paramzzaf);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzbax.zza(this, paramParcel, paramInt);
  }
  
  public zzaf zzPW()
  {
    return this.zzbEx;
  }
  
  public ConnectionResult zzyh()
  {
    return this.zzaGE;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzbaw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */