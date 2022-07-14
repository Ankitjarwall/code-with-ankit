package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzah
  extends zza
{
  public static final Parcelable.Creator<zzah> CREATOR = new zzai();
  @Deprecated
  private final Scope[] zzaEX;
  private final int zzaGG;
  private final int zzaGH;
  final int zzaiI;
  
  zzah(int paramInt1, int paramInt2, int paramInt3, Scope[] paramArrayOfScope)
  {
    this.zzaiI = paramInt1;
    this.zzaGG = paramInt2;
    this.zzaGH = paramInt3;
    this.zzaEX = paramArrayOfScope;
  }
  
  public zzah(int paramInt1, int paramInt2, Scope[] paramArrayOfScope)
  {
    this(1, paramInt1, paramInt2, null);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzai.zza(this, paramParcel, paramInt);
  }
  
  public int zzyk()
  {
    return this.zzaGG;
  }
  
  public int zzyl()
  {
    return this.zzaGH;
  }
  
  @Deprecated
  public Scope[] zzym()
  {
    return this.zzaEX;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\zzah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */