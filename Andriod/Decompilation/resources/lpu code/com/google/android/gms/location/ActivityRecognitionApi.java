package com.google.android.gms.location;

import android.app.PendingIntent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public abstract interface ActivityRecognitionApi
{
  @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
  public abstract PendingResult<Status> removeActivityUpdates(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent);
  
  @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
  public abstract PendingResult<Status> requestActivityUpdates(GoogleApiClient paramGoogleApiClient, long paramLong, PendingIntent paramPendingIntent);
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\ActivityRecognitionApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */