package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class LocationSettingsResult
  extends zza
  implements Result
{
  public static final Parcelable.Creator<LocationSettingsResult> CREATOR = new zzr();
  private final Status zzair;
  private final LocationSettingsStates zzbkh;
  
  public LocationSettingsResult(Status paramStatus)
  {
    this(paramStatus, null);
  }
  
  public LocationSettingsResult(Status paramStatus, LocationSettingsStates paramLocationSettingsStates)
  {
    this.zzair = paramStatus;
    this.zzbkh = paramLocationSettingsStates;
  }
  
  public LocationSettingsStates getLocationSettingsStates()
  {
    return this.zzbkh;
  }
  
  public Status getStatus()
  {
    return this.zzair;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzr.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\LocationSettingsResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */