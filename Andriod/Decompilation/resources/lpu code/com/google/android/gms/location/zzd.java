package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;

public class zzd
  extends zza
{
  public static final Parcelable.Creator<zzd> CREATOR = new zze();
  private final int zzaSu;
  private final int zzbjt;
  
  zzd(int paramInt1, int paramInt2)
  {
    this.zzaSu = paramInt1;
    this.zzbjt = paramInt2;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof zzd)) {
        return false;
      }
      paramObject = (zzd)paramObject;
    } while ((this.zzaSu == ((zzd)paramObject).zzaSu) && (this.zzbjt == ((zzd)paramObject).zzbjt));
    return false;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { Integer.valueOf(this.zzaSu), Integer.valueOf(this.zzbjt) });
  }
  
  public String toString()
  {
    int i = this.zzaSu;
    int j = this.zzbjt;
    return 75 + "ActivityTransition [mActivityType=" + i + ", mTransitionType=" + j + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze.zza(this, paramParcel, paramInt);
  }
  
  public int zzBW()
  {
    return this.zzaSu;
  }
  
  public int zzIc()
  {
    return this.zzbjt;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */