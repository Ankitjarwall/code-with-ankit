package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class zzo
  extends zza
{
  public static final Parcelable.Creator<zzo> CREATOR = new zzp();
  private final String zzbka;
  private final String zzbkb;
  private final int zzbkc;
  
  zzo(String paramString1, String paramString2, int paramInt)
  {
    this.zzbka = paramString1;
    this.zzbkb = paramString2;
    this.zzbkc = paramInt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzp.zza(this, paramParcel, paramInt);
  }
  
  public String zzIg()
  {
    return this.zzbka;
  }
  
  public String zzIh()
  {
    return this.zzbkb;
  }
  
  public int zzIi()
  {
    return this.zzbkc;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */