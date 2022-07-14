package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.internal.zzasm;

public abstract interface Geofence
{
  public static final int GEOFENCE_TRANSITION_DWELL = 4;
  public static final int GEOFENCE_TRANSITION_ENTER = 1;
  public static final int GEOFENCE_TRANSITION_EXIT = 2;
  public static final long NEVER_EXPIRE = -1L;
  
  public abstract String getRequestId();
  
  public static final class Builder
  {
    private String zzOV = null;
    private int zzbjC = 0;
    private long zzbjD = Long.MIN_VALUE;
    private short zzbjE = -1;
    private double zzbjF;
    private double zzbjG;
    private float zzbjH;
    private int zzbjI = 0;
    private int zzbjJ = -1;
    
    public Geofence build()
    {
      if (this.zzOV == null) {
        throw new IllegalArgumentException("Request ID not set.");
      }
      if (this.zzbjC == 0) {
        throw new IllegalArgumentException("Transitions types not set.");
      }
      if (((this.zzbjC & 0x4) != 0) && (this.zzbjJ < 0)) {
        throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
      }
      if (this.zzbjD == Long.MIN_VALUE) {
        throw new IllegalArgumentException("Expiration not set.");
      }
      if (this.zzbjE == -1) {
        throw new IllegalArgumentException("Geofence region not set.");
      }
      if (this.zzbjI < 0) {
        throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
      }
      return new zzasm(this.zzOV, this.zzbjC, (short)1, this.zzbjF, this.zzbjG, this.zzbjH, this.zzbjD, this.zzbjI, this.zzbjJ);
    }
    
    public Builder setCircularRegion(double paramDouble1, double paramDouble2, float paramFloat)
    {
      this.zzbjE = 1;
      this.zzbjF = paramDouble1;
      this.zzbjG = paramDouble2;
      this.zzbjH = paramFloat;
      return this;
    }
    
    public Builder setExpirationDuration(long paramLong)
    {
      if (paramLong < 0L)
      {
        this.zzbjD = -1L;
        return this;
      }
      this.zzbjD = (SystemClock.elapsedRealtime() + paramLong);
      return this;
    }
    
    public Builder setLoiteringDelay(int paramInt)
    {
      this.zzbjJ = paramInt;
      return this;
    }
    
    public Builder setNotificationResponsiveness(int paramInt)
    {
      this.zzbjI = paramInt;
      return this;
    }
    
    public Builder setRequestId(String paramString)
    {
      this.zzOV = paramString;
      return this;
    }
    
    public Builder setTransitionTypes(int paramInt)
    {
      this.zzbjC = paramInt;
      return this;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\Geofence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */