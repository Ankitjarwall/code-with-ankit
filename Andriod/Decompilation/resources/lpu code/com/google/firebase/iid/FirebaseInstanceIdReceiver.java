package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Base64;
import android.util.Log;

public final class FirebaseInstanceIdReceiver
  extends WakefulBroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramIntent.setComponent(null);
    paramIntent.setPackage(paramContext.getPackageName());
    if (Build.VERSION.SDK_INT <= 18) {
      paramIntent.removeCategory(paramContext.getPackageName());
    }
    String str = paramIntent.getStringExtra("gcm.rawData64");
    if (str != null)
    {
      paramIntent.putExtra("rawData", Base64.decode(str, 0));
      paramIntent.removeExtra("gcm.rawData64");
    }
    str = paramIntent.getStringExtra("from");
    if (("google.com/iid".equals(str)) || ("gcm.googleapis.com/refresh".equals(str))) {
      str = "com.google.firebase.INSTANCE_ID_EVENT";
    }
    for (;;)
    {
      int i = -1;
      if (str != null) {
        i = zza(paramContext, str, paramIntent);
      }
      if (isOrderedBroadcast()) {
        setResultCode(i);
      }
      return;
      if ("com.google.android.c2dm.intent.RECEIVE".equals(paramIntent.getAction()))
      {
        str = "com.google.firebase.MESSAGING_EVENT";
      }
      else
      {
        Log.d("FirebaseInstanceId", "Unexpected intent");
        str = null;
      }
    }
  }
  
  public int zza(Context paramContext, String paramString, Intent paramIntent)
  {
    if (FirebaseInstanceIdInternalReceiver.zzcs(paramContext))
    {
      if (isOrderedBroadcast()) {
        setResultCode(-1);
      }
      FirebaseInstanceIdInternalReceiver.zzL(paramContext, paramString).zza(paramIntent, goAsync());
      return -1;
    }
    return zzg.zzabW().zzb(paramContext, paramString, paramIntent);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\iid\FirebaseInstanceIdReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */