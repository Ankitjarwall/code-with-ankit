package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class LocationResult
  extends zza
  implements ReflectedParcelable
{
  public static final Parcelable.Creator<LocationResult> CREATOR = new zzn();
  static final List<Location> zzbjY = ;
  private final List<Location> zzbjZ;
  
  LocationResult(List<Location> paramList)
  {
    this.zzbjZ = paramList;
  }
  
  public static LocationResult create(List<Location> paramList)
  {
    Object localObject = paramList;
    if (paramList == null) {
      localObject = zzbjY;
    }
    return new LocationResult((List)localObject);
  }
  
  public static LocationResult extractResult(Intent paramIntent)
  {
    if (!hasResult(paramIntent)) {
      return null;
    }
    return (LocationResult)paramIntent.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
  }
  
  public static boolean hasResult(Intent paramIntent)
  {
    if (paramIntent == null) {
      return false;
    }
    return paramIntent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof LocationResult)) {
      return false;
    }
    paramObject = (LocationResult)paramObject;
    if (((LocationResult)paramObject).zzbjZ.size() != this.zzbjZ.size()) {
      return false;
    }
    paramObject = ((LocationResult)paramObject).zzbjZ.iterator();
    Iterator localIterator = this.zzbjZ.iterator();
    while (((Iterator)paramObject).hasNext())
    {
      Location localLocation1 = (Location)localIterator.next();
      Location localLocation2 = (Location)((Iterator)paramObject).next();
      if (localLocation1.getTime() != localLocation2.getTime()) {
        return false;
      }
    }
    return true;
  }
  
  public Location getLastLocation()
  {
    int i = this.zzbjZ.size();
    if (i == 0) {
      return null;
    }
    return (Location)this.zzbjZ.get(i - 1);
  }
  
  @NonNull
  public List<Location> getLocations()
  {
    return this.zzbjZ;
  }
  
  public int hashCode()
  {
    Iterator localIterator = this.zzbjZ.iterator();
    long l;
    for (int i = 17; localIterator.hasNext(); i = (int)(l ^ l >>> 32) + i * 31) {
      l = ((Location)localIterator.next()).getTime();
    }
    return i;
  }
  
  public String toString()
  {
    String str = String.valueOf(this.zzbjZ);
    return String.valueOf(str).length() + 27 + "LocationResult[locations: " + str + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzn.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\LocationResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */