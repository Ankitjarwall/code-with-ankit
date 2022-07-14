package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;

public final class LocationRequest
  extends zza
  implements ReflectedParcelable
{
  public static final Parcelable.Creator<LocationRequest> CREATOR = new zzm();
  public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
  public static final int PRIORITY_HIGH_ACCURACY = 100;
  public static final int PRIORITY_LOW_POWER = 104;
  public static final int PRIORITY_NO_POWER = 105;
  int mPriority;
  boolean zzaWy;
  long zzbjD;
  long zzbjT;
  long zzbjU;
  int zzbjV;
  float zzbjW;
  long zzbjX;
  
  public LocationRequest()
  {
    this.mPriority = 102;
    this.zzbjT = 3600000L;
    this.zzbjU = 600000L;
    this.zzaWy = false;
    this.zzbjD = Long.MAX_VALUE;
    this.zzbjV = Integer.MAX_VALUE;
    this.zzbjW = 0.0F;
    this.zzbjX = 0L;
  }
  
  LocationRequest(int paramInt1, long paramLong1, long paramLong2, boolean paramBoolean, long paramLong3, int paramInt2, float paramFloat, long paramLong4)
  {
    this.mPriority = paramInt1;
    this.zzbjT = paramLong1;
    this.zzbjU = paramLong2;
    this.zzaWy = paramBoolean;
    this.zzbjD = paramLong3;
    this.zzbjV = paramInt2;
    this.zzbjW = paramFloat;
    this.zzbjX = paramLong4;
  }
  
  public static LocationRequest create()
  {
    return new LocationRequest();
  }
  
  private static void zzV(long paramLong)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException(38 + "invalid interval: " + paramLong);
    }
  }
  
  private static void zze(float paramFloat)
  {
    if (paramFloat < 0.0F) {
      throw new IllegalArgumentException(37 + "invalid displacement: " + paramFloat);
    }
  }
  
  private static void zzkk(int paramInt)
  {
    switch (paramInt)
    {
    case 101: 
    case 103: 
    default: 
      throw new IllegalArgumentException(28 + "invalid quality: " + paramInt);
    }
  }
  
  public static String zzkl(int paramInt)
  {
    switch (paramInt)
    {
    case 101: 
    case 103: 
    default: 
      return "???";
    case 100: 
      return "PRIORITY_HIGH_ACCURACY";
    case 102: 
      return "PRIORITY_BALANCED_POWER_ACCURACY";
    case 104: 
      return "PRIORITY_LOW_POWER";
    }
    return "PRIORITY_NO_POWER";
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof LocationRequest)) {
        return false;
      }
      paramObject = (LocationRequest)paramObject;
    } while ((this.mPriority == ((LocationRequest)paramObject).mPriority) && (this.zzbjT == ((LocationRequest)paramObject).zzbjT) && (this.zzbjU == ((LocationRequest)paramObject).zzbjU) && (this.zzaWy == ((LocationRequest)paramObject).zzaWy) && (this.zzbjD == ((LocationRequest)paramObject).zzbjD) && (this.zzbjV == ((LocationRequest)paramObject).zzbjV) && (this.zzbjW == ((LocationRequest)paramObject).zzbjW) && (getMaxWaitTime() == ((LocationRequest)paramObject).getMaxWaitTime()));
    return false;
  }
  
  public long getExpirationTime()
  {
    return this.zzbjD;
  }
  
  public long getFastestInterval()
  {
    return this.zzbjU;
  }
  
  public long getInterval()
  {
    return this.zzbjT;
  }
  
  public long getMaxWaitTime()
  {
    long l2 = this.zzbjX;
    long l1 = l2;
    if (l2 < this.zzbjT) {
      l1 = this.zzbjT;
    }
    return l1;
  }
  
  public int getNumUpdates()
  {
    return this.zzbjV;
  }
  
  public int getPriority()
  {
    return this.mPriority;
  }
  
  public float getSmallestDisplacement()
  {
    return this.zzbjW;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { Integer.valueOf(this.mPriority), Long.valueOf(this.zzbjT), Float.valueOf(this.zzbjW), Long.valueOf(this.zzbjX) });
  }
  
  public LocationRequest setExpirationDuration(long paramLong)
  {
    long l = SystemClock.elapsedRealtime();
    if (paramLong > Long.MAX_VALUE - l) {}
    for (this.zzbjD = Long.MAX_VALUE;; this.zzbjD = (l + paramLong))
    {
      if (this.zzbjD < 0L) {
        this.zzbjD = 0L;
      }
      return this;
    }
  }
  
  public LocationRequest setExpirationTime(long paramLong)
  {
    this.zzbjD = paramLong;
    if (this.zzbjD < 0L) {
      this.zzbjD = 0L;
    }
    return this;
  }
  
  public LocationRequest setFastestInterval(long paramLong)
  {
    zzV(paramLong);
    this.zzaWy = true;
    this.zzbjU = paramLong;
    return this;
  }
  
  public LocationRequest setInterval(long paramLong)
  {
    zzV(paramLong);
    this.zzbjT = paramLong;
    if (!this.zzaWy) {
      this.zzbjU = ((this.zzbjT / 6.0D));
    }
    return this;
  }
  
  public LocationRequest setMaxWaitTime(long paramLong)
  {
    zzV(paramLong);
    this.zzbjX = paramLong;
    return this;
  }
  
  public LocationRequest setNumUpdates(int paramInt)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException(31 + "invalid numUpdates: " + paramInt);
    }
    this.zzbjV = paramInt;
    return this;
  }
  
  public LocationRequest setPriority(int paramInt)
  {
    zzkk(paramInt);
    this.mPriority = paramInt;
    return this;
  }
  
  public LocationRequest setSmallestDisplacement(float paramFloat)
  {
    zze(paramFloat);
    this.zzbjW = paramFloat;
    return this;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Request[").append(zzkl(this.mPriority));
    if (this.mPriority != 105)
    {
      localStringBuilder.append(" requested=");
      localStringBuilder.append(this.zzbjT).append("ms");
    }
    localStringBuilder.append(" fastest=");
    localStringBuilder.append(this.zzbjU).append("ms");
    if (this.zzbjX > this.zzbjT)
    {
      localStringBuilder.append(" maxWait=");
      localStringBuilder.append(this.zzbjX).append("ms");
    }
    if (this.zzbjW > 0.0F)
    {
      localStringBuilder.append(" smallestDisplacement=");
      localStringBuilder.append(this.zzbjW).append("m");
    }
    if (this.zzbjD != Long.MAX_VALUE)
    {
      long l1 = this.zzbjD;
      long l2 = SystemClock.elapsedRealtime();
      localStringBuilder.append(" expireIn=");
      localStringBuilder.append(l1 - l2).append("ms");
    }
    if (this.zzbjV != Integer.MAX_VALUE) {
      localStringBuilder.append(" num=").append(this.zzbjV);
    }
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzm.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\LocationRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */