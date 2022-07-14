package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.util.LinkedList;
import java.util.Queue;

public class zzg
{
  private static zzg zzclC;
  private final SimpleArrayMap<String, String> zzclD = new SimpleArrayMap();
  private Boolean zzclE = null;
  @VisibleForTesting
  final Queue<Intent> zzclF = new LinkedList();
  @VisibleForTesting
  final Queue<Intent> zzclG = new LinkedList();
  
  public static PendingIntent zza(Context paramContext, int paramInt1, Intent paramIntent, int paramInt2)
  {
    return zza(paramContext, paramInt1, "com.google.firebase.INSTANCE_ID_EVENT", paramIntent, paramInt2);
  }
  
  private static PendingIntent zza(Context paramContext, int paramInt1, String paramString, Intent paramIntent, int paramInt2)
  {
    Intent localIntent = new Intent(paramContext, FirebaseInstanceIdInternalReceiver.class);
    localIntent.setAction(paramString);
    localIntent.putExtra("wrapped_intent", paramIntent);
    return PendingIntent.getBroadcast(paramContext, paramInt1, localIntent, paramInt2);
  }
  
  public static zzg zzabW()
  {
    try
    {
      if (zzclC == null) {
        zzclC = new zzg();
      }
      zzg localzzg = zzclC;
      return localzzg;
    }
    finally {}
  }
  
  public static PendingIntent zzb(Context paramContext, int paramInt1, Intent paramIntent, int paramInt2)
  {
    return zza(paramContext, paramInt1, "com.google.firebase.MESSAGING_EVENT", paramIntent, paramInt2);
  }
  
  private boolean zzcw(Context paramContext)
  {
    if (this.zzclE == null) {
      if (paramContext.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") != 0) {
        break label34;
      }
    }
    label34:
    for (boolean bool = true;; bool = false)
    {
      this.zzclE = Boolean.valueOf(bool);
      return this.zzclE.booleanValue();
    }
  }
  
  private void zze(Context paramContext, Intent paramIntent)
  {
    synchronized (this.zzclD)
    {
      ??? = (String)this.zzclD.get(paramIntent.getAction());
      ??? = ???;
      if (??? != null) {
        break label237;
      }
      ??? = paramContext.getPackageManager().resolveService(paramIntent, 0);
      if ((??? == null) || (((ResolveInfo)???).serviceInfo == null))
      {
        Log.e("FirebaseInstanceId", "Failed to resolve target intent service, skipping classname enforcement");
        return;
      }
    }
    ??? = ((ResolveInfo)???).serviceInfo;
    if ((!paramContext.getPackageName().equals(((ServiceInfo)???).packageName)) || (((ServiceInfo)???).name == null))
    {
      paramContext = String.valueOf(((ServiceInfo)???).packageName);
      paramIntent = String.valueOf(((ServiceInfo)???).name);
      Log.e("FirebaseInstanceId", String.valueOf(paramContext).length() + 94 + String.valueOf(paramIntent).length() + "Error resolving target intent service, skipping classname enforcement. Resolved service was: " + paramContext + "/" + paramIntent);
      return;
    }
    ??? = ((ServiceInfo)???).name;
    ??? = ???;
    if (((String)???).startsWith("."))
    {
      ??? = String.valueOf(paramContext.getPackageName());
      ??? = String.valueOf(???);
      if (((String)???).length() == 0) {
        break label288;
      }
      ??? = ((String)???).concat((String)???);
    }
    for (;;)
    {
      synchronized (this.zzclD)
      {
        this.zzclD.put(paramIntent.getAction(), ???);
        label237:
        if (Log.isLoggable("FirebaseInstanceId", 3))
        {
          ??? = String.valueOf(???);
          if (((String)???).length() != 0)
          {
            ??? = "Restricting intent to a specific service: ".concat((String)???);
            Log.d("FirebaseInstanceId", (String)???);
          }
        }
        else
        {
          paramIntent.setClassName(paramContext.getPackageName(), (String)???);
          return;
          label288:
          ??? = new String((String)???);
        }
      }
      ??? = new String("Restricting intent to a specific service: ");
    }
  }
  
  private int zzh(Context paramContext, Intent paramIntent)
  {
    zze(paramContext, paramIntent);
    try
    {
      if (zzcw(paramContext)) {
        paramContext = WakefulBroadcastReceiver.startWakefulService(paramContext, paramIntent);
      }
      while (paramContext == null)
      {
        Log.e("FirebaseInstanceId", "Error while delivering the message: ServiceIntent not found.");
        return 404;
        paramContext = paramContext.startService(paramIntent);
        Log.d("FirebaseInstanceId", "Missing wake lock permission, service start may be delayed");
      }
      return 402;
    }
    catch (SecurityException paramContext)
    {
      Log.e("FirebaseInstanceId", "Error while delivering the message to the serviceIntent", paramContext);
      return 401;
      return -1;
    }
    catch (IllegalStateException paramContext)
    {
      paramContext = String.valueOf(paramContext);
      Log.e("FirebaseInstanceId", String.valueOf(paramContext).length() + 45 + "Failed to start service while in background: " + paramContext);
    }
  }
  
  public Intent zzabX()
  {
    return (Intent)this.zzclF.poll();
  }
  
  public Intent zzabY()
  {
    return (Intent)this.zzclG.poll();
  }
  
  public int zzb(Context paramContext, String paramString, Intent paramIntent)
  {
    int i = -1;
    switch (paramString.hashCode())
    {
    default: 
      switch (i)
      {
      default: 
        paramContext = String.valueOf(paramString);
        if (paramContext.length() == 0) {}
        break;
      }
      break;
    }
    for (paramContext = "Unknown service action: ".concat(paramContext);; paramContext = new String("Unknown service action: "))
    {
      Log.w("FirebaseInstanceId", paramContext);
      return 500;
      if (!paramString.equals("com.google.firebase.INSTANCE_ID_EVENT")) {
        break;
      }
      i = 0;
      break;
      if (!paramString.equals("com.google.firebase.MESSAGING_EVENT")) {
        break;
      }
      i = 1;
      break;
      this.zzclF.offer(paramIntent);
      for (;;)
      {
        paramString = new Intent(paramString);
        paramString.setPackage(paramContext.getPackageName());
        return zzh(paramContext, paramString);
        this.zzclG.offer(paramIntent);
      }
    }
  }
  
  public void zzg(Context paramContext, Intent paramIntent)
  {
    zzb(paramContext, "com.google.firebase.INSTANCE_ID_EVENT", paramIntent);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\iid\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */