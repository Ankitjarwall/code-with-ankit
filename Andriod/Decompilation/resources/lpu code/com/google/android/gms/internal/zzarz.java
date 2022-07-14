package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class zzarz
  extends zza
  implements Result
{
  public static final Parcelable.Creator<zzarz> CREATOR = new zzasa();
  public static final zzarz zzbkA = new zzarz(Status.zzazx);
  private final Status zzair;
  
  public zzarz(Status paramStatus)
  {
    this.zzair = paramStatus;
  }
  
  public Status getStatus()
  {
    return this.zzair;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzasa.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzarz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */