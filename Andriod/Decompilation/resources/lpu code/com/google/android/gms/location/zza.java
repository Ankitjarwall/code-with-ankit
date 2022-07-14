package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.WorkSource;
import android.support.annotation.Nullable;

public class zza
  extends com.google.android.gms.common.internal.safeparcel.zza
{
  public static final Parcelable.Creator<zza> CREATOR = new zzb();
  @Nullable
  private String mTag;
  @Nullable
  private String zzaiu;
  private long zzbjj;
  private boolean zzbjk;
  @Nullable
  private WorkSource zzbjl;
  @Nullable
  private int[] zzbjm;
  @Nullable
  private boolean zzbjn;
  private final long zzbjo;
  
  zza(long paramLong1, boolean paramBoolean1, @Nullable WorkSource paramWorkSource, @Nullable String paramString1, @Nullable int[] paramArrayOfInt, boolean paramBoolean2, @Nullable String paramString2, long paramLong2)
  {
    this.zzbjj = paramLong1;
    this.zzbjk = paramBoolean1;
    this.zzbjl = paramWorkSource;
    this.mTag = paramString1;
    this.zzbjm = paramArrayOfInt;
    this.zzbjn = paramBoolean2;
    this.zzaiu = paramString2;
    this.zzbjo = paramLong2;
  }
  
  @Nullable
  public String getAccountName()
  {
    return this.zzaiu;
  }
  
  public long getIntervalMillis()
  {
    return this.zzbjj;
  }
  
  @Nullable
  public String getTag()
  {
    return this.mTag;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public boolean zzHX()
  {
    return this.zzbjk;
  }
  
  @Nullable
  public WorkSource zzHY()
  {
    return this.zzbjl;
  }
  
  @Nullable
  public int[] zzHZ()
  {
    return this.zzbjm;
  }
  
  public boolean zzIa()
  {
    return this.zzbjn;
  }
  
  public long zzIb()
  {
    return this.zzbjo;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */