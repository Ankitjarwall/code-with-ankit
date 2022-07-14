package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.google.android.gms.common.util.zzt;

public final class FirebaseInstanceIdInternalReceiver
  extends WakefulBroadcastReceiver
{
  private static boolean zzbgs = false;
  private static zzb.zzc zzclp;
  private static zzb.zzc zzclq;
  
  /* Error */
  static zzb.zzc zzL(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: ldc 21
    //   5: aload_1
    //   6: invokevirtual 27	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   9: ifeq +30 -> 39
    //   12: getstatic 29	com/google/firebase/iid/FirebaseInstanceIdInternalReceiver:zzclq	Lcom/google/firebase/iid/zzb$zzc;
    //   15: ifnonnull +15 -> 30
    //   18: new 31	com/google/firebase/iid/zzb$zzc
    //   21: dup
    //   22: aload_0
    //   23: aload_1
    //   24: invokespecial 34	com/google/firebase/iid/zzb$zzc:<init>	(Landroid/content/Context;Ljava/lang/String;)V
    //   27: putstatic 29	com/google/firebase/iid/FirebaseInstanceIdInternalReceiver:zzclq	Lcom/google/firebase/iid/zzb$zzc;
    //   30: getstatic 29	com/google/firebase/iid/FirebaseInstanceIdInternalReceiver:zzclq	Lcom/google/firebase/iid/zzb$zzc;
    //   33: astore_0
    //   34: ldc 2
    //   36: monitorexit
    //   37: aload_0
    //   38: areturn
    //   39: getstatic 36	com/google/firebase/iid/FirebaseInstanceIdInternalReceiver:zzclp	Lcom/google/firebase/iid/zzb$zzc;
    //   42: ifnonnull +15 -> 57
    //   45: new 31	com/google/firebase/iid/zzb$zzc
    //   48: dup
    //   49: aload_0
    //   50: aload_1
    //   51: invokespecial 34	com/google/firebase/iid/zzb$zzc:<init>	(Landroid/content/Context;Ljava/lang/String;)V
    //   54: putstatic 36	com/google/firebase/iid/FirebaseInstanceIdInternalReceiver:zzclp	Lcom/google/firebase/iid/zzb$zzc;
    //   57: getstatic 36	com/google/firebase/iid/FirebaseInstanceIdInternalReceiver:zzclp	Lcom/google/firebase/iid/zzb$zzc;
    //   60: astore_0
    //   61: goto -27 -> 34
    //   64: astore_0
    //   65: ldc 2
    //   67: monitorexit
    //   68: aload_0
    //   69: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	70	0	paramContext	Context
    //   0	70	1	paramString	String
    // Exception table:
    //   from	to	target	type
    //   3	30	64	finally
    //   30	34	64	finally
    //   39	57	64	finally
    //   57	61	64	finally
  }
  
  static boolean zzcs(Context paramContext)
  {
    if (!zzt.zzzq()) {}
    while (paramContext.getApplicationInfo().targetSdkVersion <= 25) {
      return false;
    }
    return true;
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent == null) {
      return;
    }
    Object localObject = paramIntent.getParcelableExtra("wrapped_intent");
    if (!(localObject instanceof Intent))
    {
      Log.e("FirebaseInstanceId", "Missing or invalid wrapped intent");
      return;
    }
    localObject = (Intent)localObject;
    if (zzcs(paramContext))
    {
      zzL(paramContext, paramIntent.getAction()).zza((Intent)localObject, goAsync());
      return;
    }
    zzg.zzabW().zzb(paramContext, paramIntent.getAction(), (Intent)localObject);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\iid\FirebaseInstanceIdInternalReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */