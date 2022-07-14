package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;

public class FavaDiagnosticsEntity
  extends com.google.android.gms.common.internal.safeparcel.zza
  implements ReflectedParcelable
{
  public static final Parcelable.Creator<FavaDiagnosticsEntity> CREATOR = new zza();
  public final String zzaGP;
  public final int zzaGQ;
  final int zzaiI;
  
  public FavaDiagnosticsEntity(int paramInt1, String paramString, int paramInt2)
  {
    this.zzaiI = paramInt1;
    this.zzaGP = paramString;
    this.zzaGQ = paramInt2;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\server\FavaDiagnosticsEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */