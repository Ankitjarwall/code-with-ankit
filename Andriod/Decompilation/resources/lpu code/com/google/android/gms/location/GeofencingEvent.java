package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import com.google.android.gms.internal.zzasm;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeofencingEvent
{
  private final int zzPY;
  private final int zzbjK;
  private final List<Geofence> zzbjL;
  private final Location zzbjM;
  
  private GeofencingEvent(int paramInt1, int paramInt2, List<Geofence> paramList, Location paramLocation)
  {
    this.zzPY = paramInt1;
    this.zzbjK = paramInt2;
    this.zzbjL = paramList;
    this.zzbjM = paramLocation;
  }
  
  public static GeofencingEvent fromIntent(Intent paramIntent)
  {
    if (paramIntent == null) {
      return null;
    }
    return new GeofencingEvent(paramIntent.getIntExtra("gms_error_code", -1), zzx(paramIntent), zzy(paramIntent), (Location)paramIntent.getParcelableExtra("com.google.android.location.intent.extra.triggering_location"));
  }
  
  private static int zzx(Intent paramIntent)
  {
    int i = paramIntent.getIntExtra("com.google.android.location.intent.extra.transition", -1);
    if (i == -1) {}
    while ((i != 1) && (i != 2) && (i != 4)) {
      return -1;
    }
    return i;
  }
  
  private static List<Geofence> zzy(Intent paramIntent)
  {
    Object localObject = (ArrayList)paramIntent.getSerializableExtra("com.google.android.location.intent.extra.geofence_list");
    if (localObject == null) {
      return null;
    }
    paramIntent = new ArrayList(((ArrayList)localObject).size());
    localObject = ((ArrayList)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramIntent.add(zzasm.zzw((byte[])((Iterator)localObject).next()));
    }
    return paramIntent;
  }
  
  public int getErrorCode()
  {
    return this.zzPY;
  }
  
  public int getGeofenceTransition()
  {
    return this.zzbjK;
  }
  
  public List<Geofence> getTriggeringGeofences()
  {
    return this.zzbjL;
  }
  
  public Location getTriggeringLocation()
  {
    return this.zzbjM;
  }
  
  public boolean hasError()
  {
    return this.zzPY != -1;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\GeofencingEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */