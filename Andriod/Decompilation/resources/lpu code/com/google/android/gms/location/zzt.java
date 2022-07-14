package com.google.android.gms.location;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;
import java.util.Collections;
import java.util.List;

public class zzt
  extends zza
{
  public static final Parcelable.Creator<zzt> CREATOR = new zzu();
  private final PendingIntent mPendingIntent;
  private final String mTag;
  private final List<String> zzbko;
  
  zzt(@Nullable List<String> paramList, @Nullable PendingIntent paramPendingIntent, String paramString)
  {
    if (paramList == null) {}
    for (paramList = Collections.emptyList();; paramList = Collections.unmodifiableList(paramList))
    {
      this.zzbko = paramList;
      this.mPendingIntent = paramPendingIntent;
      this.mTag = paramString;
      return;
    }
  }
  
  public static zzt zzE(List<String> paramList)
  {
    zzac.zzb(paramList, "geofence can't be null.");
    if (!paramList.isEmpty()) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzb(bool, "Geofences must contains at least one id.");
      return new zzt(paramList, null, "");
    }
  }
  
  public static zzt zzb(PendingIntent paramPendingIntent)
  {
    zzac.zzb(paramPendingIntent, "PendingIntent can not be null.");
    return new zzt(null, paramPendingIntent, "");
  }
  
  public String getTag()
  {
    return this.mTag;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzu.zza(this, paramParcel, paramInt);
  }
  
  public List<String> zzIm()
  {
    return this.zzbko;
  }
  
  public PendingIntent zzvu()
  {
    return this.mPendingIntent;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\zzt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */