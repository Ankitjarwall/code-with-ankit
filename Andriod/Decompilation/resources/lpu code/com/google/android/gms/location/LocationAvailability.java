package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;

public final class LocationAvailability
  extends zza
  implements ReflectedParcelable
{
  public static final Parcelable.Creator<LocationAvailability> CREATOR = new zzl();
  int zzbjP;
  int zzbjQ;
  long zzbjR;
  int zzbjS;
  
  LocationAvailability(int paramInt1, int paramInt2, int paramInt3, long paramLong)
  {
    this.zzbjS = paramInt1;
    this.zzbjP = paramInt2;
    this.zzbjQ = paramInt3;
    this.zzbjR = paramLong;
  }
  
  public static LocationAvailability extractLocationAvailability(Intent paramIntent)
  {
    if (!hasLocationAvailability(paramIntent)) {
      return null;
    }
    return (LocationAvailability)paramIntent.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
  }
  
  public static boolean hasLocationAvailability(Intent paramIntent)
  {
    if (paramIntent == null) {
      return false;
    }
    return paramIntent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof LocationAvailability)) {}
    do
    {
      return false;
      paramObject = (LocationAvailability)paramObject;
    } while ((this.zzbjS != ((LocationAvailability)paramObject).zzbjS) || (this.zzbjP != ((LocationAvailability)paramObject).zzbjP) || (this.zzbjQ != ((LocationAvailability)paramObject).zzbjQ) || (this.zzbjR != ((LocationAvailability)paramObject).zzbjR));
    return true;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { Integer.valueOf(this.zzbjS), Integer.valueOf(this.zzbjP), Integer.valueOf(this.zzbjQ), Long.valueOf(this.zzbjR) });
  }
  
  public boolean isLocationAvailable()
  {
    return this.zzbjS < 1000;
  }
  
  public String toString()
  {
    boolean bool = isLocationAvailable();
    return 48 + "LocationAvailability[isLocationAvailable: " + bool + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzl.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\LocationAvailability.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */