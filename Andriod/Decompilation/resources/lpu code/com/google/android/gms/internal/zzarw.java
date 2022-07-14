package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;

public class zzarw
  extends zza
{
  public static final Parcelable.Creator<zzarw> CREATOR = new zzarx();
  public final String packageName;
  public final int uid;
  
  public zzarw(int paramInt, String paramString)
  {
    this.uid = paramInt;
    this.packageName = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof zzarw))) {
        return false;
      }
      paramObject = (zzarw)paramObject;
    } while ((((zzarw)paramObject).uid == this.uid) && (zzaa.equal(((zzarw)paramObject).packageName, this.packageName)));
    return false;
  }
  
  public int hashCode()
  {
    return this.uid;
  }
  
  public String toString()
  {
    return String.format("%d:%s", new Object[] { Integer.valueOf(this.uid), this.packageName });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzarx.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzarw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */