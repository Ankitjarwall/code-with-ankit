package com.google.firebase.iid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.util.Log;
import java.io.IOException;

public class FirebaseInstanceIdService
  extends zzb
{
  @VisibleForTesting
  static final Object zzclr = new Object();
  @VisibleForTesting
  static boolean zzcls = false;
  private boolean zzclt = false;
  
  private String zzG(Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("subtype");
    paramIntent = str;
    if (str == null) {
      paramIntent = "";
    }
    return paramIntent;
  }
  
  private void zzU(Bundle paramBundle)
  {
    String str = zzf.zzbA(this);
    if (str == null)
    {
      Log.w("FirebaseInstanceId", "Unable to respond to ping due to missing target package");
      return;
    }
    Intent localIntent = new Intent("com.google.android.gcm.intent.SEND");
    localIntent.setPackage(str);
    localIntent.putExtras(paramBundle);
    zzf.zzf(this, localIntent);
    localIntent.putExtra("google.to", "google.com/iid");
    localIntent.putExtra("google.message_id", zzf.zzHn());
    sendOrderedBroadcast(localIntent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
  }
  
  private int zza(Intent paramIntent, boolean paramBoolean)
  {
    int j = 10;
    int i;
    if (paramIntent == null)
    {
      i = 10;
      if ((i >= 10) || (paramBoolean)) {
        break label39;
      }
      j = 30;
    }
    label39:
    while (i < 10)
    {
      return j;
      i = paramIntent.getIntExtra("next_retry_delay_in_seconds", 0);
      break;
    }
    if (i > 28800) {
      return 28800;
    }
    return i;
  }
  
  static void zza(Context paramContext, FirebaseInstanceId paramFirebaseInstanceId)
  {
    synchronized (zzclr)
    {
      if (zzcls) {
        return;
      }
      ??? = paramFirebaseInstanceId.zzabP();
      if ((??? == null) || (((zzh.zza)???).zzjB(zzd.zzbhN)) || (paramFirebaseInstanceId.zzabR().zzabU() != null))
      {
        zzct(paramContext);
        return;
      }
    }
  }
  
  private void zza(Intent paramIntent, boolean paramBoolean1, boolean paramBoolean2)
  {
    String str2;
    for (;;)
    {
      synchronized (zzclr)
      {
        zzcls = false;
        if (zzf.zzbA(this) == null) {
          return;
        }
      }
      ??? = FirebaseInstanceId.getInstance();
      localObject2 = ((FirebaseInstanceId)???).zzabP();
      if ((localObject2 == null) || (((zzh.zza)localObject2).zzjB(zzd.zzbhN))) {
        try
        {
          str2 = ((FirebaseInstanceId)???).zzabQ();
          if (str2 != null)
          {
            if (this.zzclt) {
              Log.d("FirebaseInstanceId", "get master token succeeded");
            }
            zza(this, (FirebaseInstanceId)???);
            if ((!paramBoolean2) && (localObject2 != null) && ((localObject2 == null) || (str2.equals(((zzh.zza)localObject2).zzbxW)))) {
              continue;
            }
            onTokenRefresh();
          }
        }
        catch (IOException localIOException1)
        {
          zzd(paramIntent, localIOException1.getMessage());
          return;
          zzd(paramIntent, "returned token is null");
          return;
        }
        catch (SecurityException paramIntent)
        {
          Log.e("FirebaseInstanceId", "Unable to get master token", paramIntent);
          return;
        }
      }
    }
    Object localObject2 = localIOException1.zzabR();
    String str1 = ((zze)localObject2).zzabU();
    if (str1 != null)
    {
      Object localObject3 = str1.split("!");
      int j;
      if (localObject3.length == 2)
      {
        str2 = localObject3[0];
        localObject3 = localObject3[1];
        j = -1;
      }
      for (;;)
      {
        try
        {
          int k = str2.hashCode();
          int i = j;
          switch (k)
          {
          default: 
            i = j;
          case 84: 
            switch (i)
            {
            default: 
              ((zze)localObject2).zzjx(str1);
              str1 = ((zze)localObject2).zzabU();
            }
            break;
          case 83: 
            i = j;
            if (!str2.equals("S")) {
              continue;
            }
            i = 0;
            break;
          case 85: 
            i = j;
            if (!str2.equals("U")) {
              continue;
            }
            i = 1;
            continue;
            FirebaseInstanceId.getInstance().zzju((String)localObject3);
            if (!this.zzclt) {
              continue;
            }
            Log.d("FirebaseInstanceId", "subscribe operation succeeded");
            continue;
            FirebaseInstanceId.getInstance().zzjv((String)localObject3);
          }
        }
        catch (IOException localIOException2)
        {
          zzd(paramIntent, localIOException2.getMessage());
          return;
        }
        if (this.zzclt) {
          Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
        }
      }
    }
    Log.d("FirebaseInstanceId", "topic sync succeeded");
  }
  
  static void zzct(Context paramContext)
  {
    if (zzf.zzbA(paramContext) == null) {
      return;
    }
    synchronized (zzclr)
    {
      if (!zzcls)
      {
        zzg.zzabW().zzg(paramContext, zzqF(0));
        zzcls = true;
      }
      return;
    }
  }
  
  private static boolean zzcu(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  private void zzd(Intent arg1, String paramString)
  {
    boolean bool = zzcu(this);
    int i = zza(???, bool);
    Log.d("FirebaseInstanceId", String.valueOf(paramString).length() + 47 + "background sync failed: " + paramString + ", retry in " + i + "s");
    synchronized (zzclr)
    {
      zzqG(i);
      zzcls = true;
      if (!bool)
      {
        if (this.zzclt) {
          Log.d("FirebaseInstanceId", "device not connected. Connectivity change received registered");
        }
        zza.zzl(this, i);
      }
      return;
    }
  }
  
  private zzd zzjw(String paramString)
  {
    if (paramString == null) {
      return zzd.zzb(this, null);
    }
    Bundle localBundle = new Bundle();
    localBundle.putString("subtype", paramString);
    return zzd.zzb(this, localBundle);
  }
  
  private static Intent zzqF(int paramInt)
  {
    Intent localIntent = new Intent("ACTION_TOKEN_REFRESH_RETRY");
    localIntent.putExtra("next_retry_delay_in_seconds", paramInt);
    return localIntent;
  }
  
  private void zzqG(int paramInt)
  {
    AlarmManager localAlarmManager = (AlarmManager)getSystemService("alarm");
    PendingIntent localPendingIntent = zzg.zza(this, 0, zzqF(paramInt * 2), 134217728);
    localAlarmManager.set(3, SystemClock.elapsedRealtime() + paramInt * 1000, localPendingIntent);
  }
  
  public void handleIntent(Intent paramIntent)
  {
    String str2 = paramIntent.getAction();
    String str1 = str2;
    if (str2 == null) {
      str1 = "";
    }
    switch (str1.hashCode())
    {
    }
    label40:
    for (int i = -1;; i = 0) {
      switch (i)
      {
      default: 
        zzF(paramIntent);
        return;
        if (!str1.equals("ACTION_TOKEN_REFRESH_RETRY")) {
          break label40;
        }
      }
    }
    zza(paramIntent, false, false);
  }
  
  @WorkerThread
  public void onTokenRefresh() {}
  
  protected Intent zzD(Intent paramIntent)
  {
    return zzg.zzabW().zzabX();
  }
  
  public boolean zzE(Intent paramIntent)
  {
    this.zzclt = Log.isLoggable("FirebaseInstanceId", 3);
    if ((paramIntent.getStringExtra("error") != null) || (paramIntent.getStringExtra("registration_id") != null))
    {
      String str2 = zzG(paramIntent);
      if (this.zzclt)
      {
        str1 = String.valueOf(str2);
        if (str1.length() == 0) {
          break label84;
        }
      }
      label84:
      for (String str1 = "Register result in service ".concat(str1);; str1 = new String("Register result in service "))
      {
        Log.d("FirebaseInstanceId", str1);
        zzjw(str2).zzabT().zzs(paramIntent);
        return true;
      }
    }
    return false;
  }
  
  public void zzF(Intent paramIntent)
  {
    String str2 = zzG(paramIntent);
    zzd localzzd = zzjw(str2);
    String str1 = paramIntent.getStringExtra("CMD");
    Object localObject;
    if (this.zzclt)
    {
      localObject = String.valueOf(paramIntent.getExtras());
      Log.d("FirebaseInstanceId", String.valueOf(str2).length() + 18 + String.valueOf(str1).length() + String.valueOf(localObject).length() + "Service command " + str2 + " " + str1 + " " + (String)localObject);
    }
    if (paramIntent.getStringExtra("unregistered") != null)
    {
      localObject = localzzd.zzabS();
      str1 = str2;
      if (str2 == null) {
        str1 = "";
      }
      ((zzh)localObject).zzeK(str1);
      localzzd.zzabT().zzs(paramIntent);
    }
    do
    {
      do
      {
        return;
        if ("gcm.googleapis.com/refresh".equals(paramIntent.getStringExtra("from")))
        {
          localzzd.zzabS().zzeK(str2);
          zza(paramIntent, false, true);
          return;
        }
        if ("RST".equals(str1))
        {
          localzzd.zzHi();
          zza(paramIntent, true, true);
          return;
        }
        if (!"RST_FULL".equals(str1)) {
          break;
        }
      } while (localzzd.zzabS().isEmpty());
      localzzd.zzHi();
      localzzd.zzabS().zzHo();
      zza(paramIntent, true, true);
      return;
      if ("SYNC".equals(str1))
      {
        localzzd.zzabS().zzeK(str2);
        zza(paramIntent, false, true);
        return;
      }
    } while (!"PING".equals(str1));
    zzU(paramIntent.getExtras());
  }
  
  private static class zza
    extends BroadcastReceiver
  {
    @Nullable
    static BroadcastReceiver receiver;
    final int zzclu;
    
    zza(int paramInt)
    {
      this.zzclu = paramInt;
    }
    
    /* Error */
    static void zzl(Context paramContext, int paramInt)
    {
      // Byte code:
      //   0: ldc 2
      //   2: monitorenter
      //   3: getstatic 24	com/google/firebase/iid/FirebaseInstanceIdService$zza:receiver	Landroid/content/BroadcastReceiver;
      //   6: astore_2
      //   7: aload_2
      //   8: ifnull +7 -> 15
      //   11: ldc 2
      //   13: monitorexit
      //   14: return
      //   15: new 2	com/google/firebase/iid/FirebaseInstanceIdService$zza
      //   18: dup
      //   19: iload_1
      //   20: invokespecial 26	com/google/firebase/iid/FirebaseInstanceIdService$zza:<init>	(I)V
      //   23: putstatic 24	com/google/firebase/iid/FirebaseInstanceIdService$zza:receiver	Landroid/content/BroadcastReceiver;
      //   26: aload_0
      //   27: invokevirtual 32	android/content/Context:getApplicationContext	()Landroid/content/Context;
      //   30: getstatic 24	com/google/firebase/iid/FirebaseInstanceIdService$zza:receiver	Landroid/content/BroadcastReceiver;
      //   33: new 34	android/content/IntentFilter
      //   36: dup
      //   37: ldc 36
      //   39: invokespecial 39	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
      //   42: invokevirtual 43	android/content/Context:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
      //   45: pop
      //   46: goto -35 -> 11
      //   49: astore_0
      //   50: ldc 2
      //   52: monitorexit
      //   53: aload_0
      //   54: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	55	0	paramContext	Context
      //   0	55	1	paramInt	int
      //   6	2	2	localBroadcastReceiver	BroadcastReceiver
      // Exception table:
      //   from	to	target	type
      //   3	7	49	finally
      //   15	46	49	finally
    }
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      try
      {
        if (receiver != this) {
          return;
        }
        if (!FirebaseInstanceIdService.zzcv(paramContext)) {
          return;
        }
      }
      finally {}
      if (Log.isLoggable("FirebaseInstanceId", 3)) {
        Log.d("FirebaseInstanceId", "connectivity changed. starting background sync.");
      }
      paramContext.getApplicationContext().unregisterReceiver(this);
      receiver = null;
      zzg.zzabW().zzg(paramContext, FirebaseInstanceIdService.zzqH(this.zzclu));
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\iid\FirebaseInstanceIdService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */