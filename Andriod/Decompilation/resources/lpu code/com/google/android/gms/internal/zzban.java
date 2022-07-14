package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.List;

public class zzban
  extends zza
{
  public static final Parcelable.Creator<zzban> CREATOR = new zzbao();
  final int zzaiI;
  final boolean zzbEs;
  final List<Scope> zzbEt;
  
  zzban(int paramInt, boolean paramBoolean, List<Scope> paramList)
  {
    this.zzaiI = paramInt;
    this.zzbEs = paramBoolean;
    this.zzbEt = paramList;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzbao.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzban.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */