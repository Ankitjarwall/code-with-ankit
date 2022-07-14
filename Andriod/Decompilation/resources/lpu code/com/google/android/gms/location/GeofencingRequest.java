package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzasm;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeofencingRequest
  extends zza
{
  public static final Parcelable.Creator<GeofencingRequest> CREATOR = new zzi();
  public static final int INITIAL_TRIGGER_DWELL = 4;
  public static final int INITIAL_TRIGGER_ENTER = 1;
  public static final int INITIAL_TRIGGER_EXIT = 2;
  private final String mTag;
  private final List<zzasm> zzbjN;
  private final int zzbjO;
  
  GeofencingRequest(List<zzasm> paramList, int paramInt, String paramString)
  {
    this.zzbjN = paramList;
    this.zzbjO = paramInt;
    this.mTag = paramString;
  }
  
  public List<Geofence> getGeofences()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(this.zzbjN);
    return localArrayList;
  }
  
  public int getInitialTrigger()
  {
    return this.zzbjO;
  }
  
  public String getTag()
  {
    return this.mTag;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzi.zza(this, paramParcel, paramInt);
  }
  
  public List<zzasm> zzIf()
  {
    return this.zzbjN;
  }
  
  public static final class Builder
  {
    private String mTag = "";
    private final List<zzasm> zzbjN = new ArrayList();
    private int zzbjO = 5;
    
    public static int zzkh(int paramInt)
    {
      return paramInt & 0x7;
    }
    
    public Builder addGeofence(Geofence paramGeofence)
    {
      zzac.zzb(paramGeofence, "geofence can't be null.");
      zzac.zzb(paramGeofence instanceof zzasm, "Geofence must be created using Geofence.Builder.");
      this.zzbjN.add((zzasm)paramGeofence);
      return this;
    }
    
    public Builder addGeofences(List<Geofence> paramList)
    {
      if ((paramList == null) || (paramList.isEmpty())) {}
      for (;;)
      {
        return this;
        paramList = paramList.iterator();
        while (paramList.hasNext())
        {
          Geofence localGeofence = (Geofence)paramList.next();
          if (localGeofence != null) {
            addGeofence(localGeofence);
          }
        }
      }
    }
    
    public GeofencingRequest build()
    {
      if (!this.zzbjN.isEmpty()) {}
      for (boolean bool = true;; bool = false)
      {
        zzac.zzb(bool, "No geofence has been added to this request.");
        return new GeofencingRequest(this.zzbjN, this.zzbjO, this.mTag);
      }
    }
    
    public Builder setInitialTrigger(int paramInt)
    {
      this.zzbjO = zzkh(paramInt);
      return this;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\GeofencingRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */