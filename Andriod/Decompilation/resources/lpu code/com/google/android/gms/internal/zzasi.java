package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

public class zzasi
  extends zza
{
  public static final Parcelable.Creator<zzasi> CREATOR = new zzasj();
  static final List<zzarw> zzbkL = ;
  @Nullable
  String mTag;
  LocationRequest zzaWw;
  boolean zzbjk = true;
  List<zzarw> zzbjw;
  boolean zzbkM;
  boolean zzbkN;
  
  zzasi(LocationRequest paramLocationRequest, List<zzarw> paramList, @Nullable String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.zzaWw = paramLocationRequest;
    this.zzbjw = paramList;
    this.mTag = paramString;
    this.zzbkM = paramBoolean1;
    this.zzbkN = paramBoolean2;
  }
  
  public static zzasi zza(@Nullable String paramString, LocationRequest paramLocationRequest)
  {
    return new zzasi(paramLocationRequest, zzbkL, paramString, false, false);
  }
  
  @Deprecated
  public static zzasi zzb(LocationRequest paramLocationRequest)
  {
    return zza(null, paramLocationRequest);
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof zzasi)) {}
    do
    {
      return false;
      paramObject = (zzasi)paramObject;
    } while ((!zzaa.equal(this.zzaWw, ((zzasi)paramObject).zzaWw)) || (!zzaa.equal(this.zzbjw, ((zzasi)paramObject).zzbjw)) || (!zzaa.equal(this.mTag, ((zzasi)paramObject).mTag)) || (this.zzbkM != ((zzasi)paramObject).zzbkM) || (this.zzbkN != ((zzasi)paramObject).zzbkN));
    return true;
  }
  
  public int hashCode()
  {
    return this.zzaWw.hashCode();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.zzaWw.toString());
    if (this.mTag != null) {
      localStringBuilder.append(" tag=").append(this.mTag);
    }
    localStringBuilder.append(" hideAppOps=").append(this.zzbkM);
    localStringBuilder.append(" clients=").append(this.zzbjw);
    localStringBuilder.append(" forceCoarseLocation=").append(this.zzbkN);
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzasj.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzasi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */