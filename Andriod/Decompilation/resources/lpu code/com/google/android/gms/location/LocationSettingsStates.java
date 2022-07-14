package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class LocationSettingsStates
  extends zza
{
  public static final Parcelable.Creator<LocationSettingsStates> CREATOR = new zzs();
  private final boolean zzbki;
  private final boolean zzbkj;
  private final boolean zzbkk;
  private final boolean zzbkl;
  private final boolean zzbkm;
  private final boolean zzbkn;
  
  public LocationSettingsStates(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6)
  {
    this.zzbki = paramBoolean1;
    this.zzbkj = paramBoolean2;
    this.zzbkk = paramBoolean3;
    this.zzbkl = paramBoolean4;
    this.zzbkm = paramBoolean5;
    this.zzbkn = paramBoolean6;
  }
  
  public static LocationSettingsStates fromIntent(Intent paramIntent)
  {
    return (LocationSettingsStates)zzd.zza(paramIntent, "com.google.android.gms.location.LOCATION_SETTINGS_STATES", CREATOR);
  }
  
  public boolean isBlePresent()
  {
    return this.zzbkn;
  }
  
  public boolean isBleUsable()
  {
    return this.zzbkk;
  }
  
  public boolean isGpsPresent()
  {
    return this.zzbkl;
  }
  
  public boolean isGpsUsable()
  {
    return this.zzbki;
  }
  
  public boolean isLocationPresent()
  {
    return (this.zzbkl) || (this.zzbkm);
  }
  
  public boolean isLocationUsable()
  {
    return (this.zzbki) || (this.zzbkj);
  }
  
  public boolean isNetworkLocationPresent()
  {
    return this.zzbkm;
  }
  
  public boolean isNetworkLocationUsable()
  {
    return this.zzbkj;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzs.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\LocationSettingsStates.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */