package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class LocationSettingsRequest
  extends zza
{
  public static final Parcelable.Creator<LocationSettingsRequest> CREATOR = new zzq();
  private final List<LocationRequest> zzaWt;
  private final boolean zzbkd;
  private final boolean zzbke;
  private zzo zzbkf;
  
  LocationSettingsRequest(List<LocationRequest> paramList, boolean paramBoolean1, boolean paramBoolean2, zzo paramzzo)
  {
    this.zzaWt = paramList;
    this.zzbkd = paramBoolean1;
    this.zzbke = paramBoolean2;
    this.zzbkf = paramzzo;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzq.zza(this, paramParcel, paramInt);
  }
  
  public List<LocationRequest> zzDf()
  {
    return Collections.unmodifiableList(this.zzaWt);
  }
  
  public boolean zzIj()
  {
    return this.zzbkd;
  }
  
  public boolean zzIk()
  {
    return this.zzbke;
  }
  
  @Nullable
  public zzo zzIl()
  {
    return this.zzbkf;
  }
  
  public static final class Builder
  {
    private boolean zzbkd = false;
    private boolean zzbke = false;
    private zzo zzbkf = null;
    private final ArrayList<LocationRequest> zzbkg = new ArrayList();
    
    public Builder addAllLocationRequests(Collection<LocationRequest> paramCollection)
    {
      this.zzbkg.addAll(paramCollection);
      return this;
    }
    
    public Builder addLocationRequest(LocationRequest paramLocationRequest)
    {
      this.zzbkg.add(paramLocationRequest);
      return this;
    }
    
    public LocationSettingsRequest build()
    {
      return new LocationSettingsRequest(this.zzbkg, this.zzbkd, this.zzbke, null);
    }
    
    public Builder setAlwaysShow(boolean paramBoolean)
    {
      this.zzbkd = paramBoolean;
      return this;
    }
    
    public Builder setNeedBle(boolean paramBoolean)
    {
      this.zzbke = paramBoolean;
      return this;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\LocationSettingsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */