package com.onesignal;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class SyncService
  extends Service
{
  @Nullable
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    OneSignalSyncServiceUtils.doBackgroundSync(this, new OneSignalSyncServiceUtils.LegacySyncRunnable(this));
    return 1;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\SyncService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */