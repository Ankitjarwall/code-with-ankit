package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.location.Geofence;
import java.util.Locale;

public class zzasm
  extends zza
  implements Geofence
{
  public static final Parcelable.Creator<zzasm> CREATOR = new zzasn();
  private final String zzOV;
  private final int zzbjC;
  private final short zzbjE;
  private final double zzbjF;
  private final double zzbjG;
  private final float zzbjH;
  private final int zzbjI;
  private final int zzbjJ;
  private final long zzbkT;
  
  public zzasm(String paramString, int paramInt1, short paramShort, double paramDouble1, double paramDouble2, float paramFloat, long paramLong, int paramInt2, int paramInt3)
  {
    zzeT(paramString);
    zzf(paramFloat);
    zza(paramDouble1, paramDouble2);
    paramInt1 = zzkA(paramInt1);
    this.zzbjE = paramShort;
    this.zzOV = paramString;
    this.zzbjF = paramDouble1;
    this.zzbjG = paramDouble2;
    this.zzbjH = paramFloat;
    this.zzbkT = paramLong;
    this.zzbjC = paramInt1;
    this.zzbjI = paramInt2;
    this.zzbjJ = paramInt3;
  }
  
  private static void zza(double paramDouble1, double paramDouble2)
  {
    if ((paramDouble1 > 90.0D) || (paramDouble1 < -90.0D)) {
      throw new IllegalArgumentException(42 + "invalid latitude: " + paramDouble1);
    }
    if ((paramDouble2 > 180.0D) || (paramDouble2 < -180.0D)) {
      throw new IllegalArgumentException(43 + "invalid longitude: " + paramDouble2);
    }
  }
  
  private static void zzeT(String paramString)
  {
    if ((paramString == null) || (paramString.length() > 100))
    {
      paramString = String.valueOf(paramString);
      if (paramString.length() != 0) {}
      for (paramString = "requestId is null or too long: ".concat(paramString);; paramString = new String("requestId is null or too long: ")) {
        throw new IllegalArgumentException(paramString);
      }
    }
  }
  
  private static void zzf(float paramFloat)
  {
    if (paramFloat <= 0.0F) {
      throw new IllegalArgumentException(31 + "invalid radius: " + paramFloat);
    }
  }
  
  private static int zzkA(int paramInt)
  {
    int i = paramInt & 0x7;
    if (i == 0) {
      throw new IllegalArgumentException(46 + "No supported transition specified: " + paramInt);
    }
    return i;
  }
  
  @SuppressLint({"DefaultLocale"})
  private static String zzkB(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    }
    return "CIRCLE";
  }
  
  public static zzasm zzw(byte[] paramArrayOfByte)
  {
    Parcel localParcel = Parcel.obtain();
    localParcel.unmarshall(paramArrayOfByte, 0, paramArrayOfByte.length);
    localParcel.setDataPosition(0);
    paramArrayOfByte = (zzasm)CREATOR.createFromParcel(localParcel);
    localParcel.recycle();
    return paramArrayOfByte;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (!(paramObject instanceof zzasm)) {
        return false;
      }
      paramObject = (zzasm)paramObject;
      if (this.zzbjH != ((zzasm)paramObject).zzbjH) {
        return false;
      }
      if (this.zzbjF != ((zzasm)paramObject).zzbjF) {
        return false;
      }
      if (this.zzbjG != ((zzasm)paramObject).zzbjG) {
        return false;
      }
    } while (this.zzbjE == ((zzasm)paramObject).zzbjE);
    return false;
  }
  
  public long getExpirationTime()
  {
    return this.zzbkT;
  }
  
  public double getLatitude()
  {
    return this.zzbjF;
  }
  
  public double getLongitude()
  {
    return this.zzbjG;
  }
  
  public float getRadius()
  {
    return this.zzbjH;
  }
  
  public String getRequestId()
  {
    return this.zzOV;
  }
  
  public int hashCode()
  {
    long l = Double.doubleToLongBits(this.zzbjF);
    int i = (int)(l ^ l >>> 32);
    l = Double.doubleToLongBits(this.zzbjG);
    return ((((i + 31) * 31 + (int)(l ^ l >>> 32)) * 31 + Float.floatToIntBits(this.zzbjH)) * 31 + this.zzbjE) * 31 + this.zzbjC;
  }
  
  public String toString()
  {
    return String.format(Locale.US, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", new Object[] { zzkB(this.zzbjE), this.zzOV, Integer.valueOf(this.zzbjC), Double.valueOf(this.zzbjF), Double.valueOf(this.zzbjG), Float.valueOf(this.zzbjH), Integer.valueOf(this.zzbjI / 1000), Integer.valueOf(this.zzbjJ), Long.valueOf(this.zzbkT) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzasn.zza(this, paramParcel, paramInt);
  }
  
  public short zzIu()
  {
    return this.zzbjE;
  }
  
  public int zzIv()
  {
    return this.zzbjC;
  }
  
  public int zzIw()
  {
    return this.zzbjI;
  }
  
  public int zzIx()
  {
    return this.zzbjJ;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzasm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */