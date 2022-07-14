package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.iid.MessengerCompat;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class zzf
{
  static PendingIntent zzbgG;
  static String zzbhQ = null;
  static boolean zzbhR = false;
  static int zzbhS = 0;
  static int zzbhT = 0;
  static int zzbhU = 0;
  static BroadcastReceiver zzbhV = null;
  Messenger zzbgK;
  Messenger zzbhX;
  MessengerCompat zzbhY;
  long zzbhZ;
  long zzbia;
  int zzbib;
  int zzbic;
  long zzbid;
  private final SimpleArrayMap<String, zzb> zzcly = new SimpleArrayMap();
  Context zzqn;
  
  public zzf(Context paramContext)
  {
    this.zzqn = paramContext;
  }
  
  public static String zzHn()
  {
    try
    {
      int i = zzbhU;
      zzbhU = i + 1;
      String str = Integer.toString(i);
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  static String zza(KeyPair paramKeyPair, String... paramVarArgs)
  {
    // Byte code:
    //   0: ldc 83
    //   2: aload_1
    //   3: invokestatic 89	android/text/TextUtils:join	(Ljava/lang/CharSequence;[Ljava/lang/Object;)Ljava/lang/String;
    //   6: ldc 91
    //   8: invokevirtual 97	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   11: astore_1
    //   12: aload_0
    //   13: invokevirtual 103	java/security/KeyPair:getPrivate	()Ljava/security/PrivateKey;
    //   16: astore_2
    //   17: aload_2
    //   18: instanceof 105
    //   21: ifeq +43 -> 64
    //   24: ldc 107
    //   26: astore_0
    //   27: aload_0
    //   28: invokestatic 113	java/security/Signature:getInstance	(Ljava/lang/String;)Ljava/security/Signature;
    //   31: astore_0
    //   32: aload_0
    //   33: aload_2
    //   34: invokevirtual 117	java/security/Signature:initSign	(Ljava/security/PrivateKey;)V
    //   37: aload_0
    //   38: aload_1
    //   39: invokevirtual 121	java/security/Signature:update	([B)V
    //   42: aload_0
    //   43: invokevirtual 125	java/security/Signature:sign	()[B
    //   46: invokestatic 131	com/google/firebase/iid/FirebaseInstanceId:zzv	([B)Ljava/lang/String;
    //   49: astore_0
    //   50: aload_0
    //   51: areturn
    //   52: astore_0
    //   53: ldc -123
    //   55: ldc -121
    //   57: aload_0
    //   58: invokestatic 141	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   61: pop
    //   62: aconst_null
    //   63: areturn
    //   64: ldc -113
    //   66: astore_0
    //   67: goto -40 -> 27
    //   70: astore_0
    //   71: ldc -123
    //   73: ldc -111
    //   75: aload_0
    //   76: invokestatic 141	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   79: pop
    //   80: aconst_null
    //   81: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	82	0	paramKeyPair	KeyPair
    //   0	82	1	paramVarArgs	String[]
    //   16	18	2	localPrivateKey	java.security.PrivateKey
    // Exception table:
    //   from	to	target	type
    //   0	12	52	java/io/UnsupportedEncodingException
    //   12	24	70	java/security/GeneralSecurityException
    //   27	50	70	java/security/GeneralSecurityException
  }
  
  private static boolean zza(PackageManager paramPackageManager)
  {
    Iterator localIterator = paramPackageManager.queryIntentServices(new Intent("com.google.android.c2dm.intent.REGISTER"), 0).iterator();
    while (localIterator.hasNext()) {
      if (zza(paramPackageManager, ((ResolveInfo)localIterator.next()).serviceInfo.packageName, "com.google.android.c2dm.intent.REGISTER"))
      {
        zzbhR = false;
        return true;
      }
    }
    return false;
  }
  
  private static boolean zza(PackageManager paramPackageManager, String paramString1, String paramString2)
  {
    if (paramPackageManager.checkPermission("com.google.android.c2dm.permission.SEND", paramString1) == 0) {
      return zzb(paramPackageManager, paramString1);
    }
    Log.w("InstanceID/Rpc", String.valueOf(paramString1).length() + 56 + String.valueOf(paramString2).length() + "Possible malicious package " + paramString1 + " declares " + paramString2 + " without permission");
    return false;
  }
  
  private void zzay(String paramString1, String paramString2)
  {
    SimpleArrayMap localSimpleArrayMap = this.zzcly;
    int i;
    if (paramString1 == null) {
      i = 0;
    }
    for (;;)
    {
      try
      {
        if (i < this.zzcly.size())
        {
          ((zzb)this.zzcly.valueAt(i)).onError(paramString2);
          i += 1;
          continue;
        }
        this.zzcly.clear();
        return;
      }
      finally {}
      zzb localzzb = (zzb)this.zzcly.remove(paramString1);
      if (localzzb == null)
      {
        paramString1 = String.valueOf(paramString1);
        if (paramString1.length() != 0)
        {
          paramString1 = "Missing callback for ".concat(paramString1);
          Log.w("InstanceID/Rpc", paramString1);
          return;
        }
        paramString1 = new String("Missing callback for ");
        continue;
      }
      localzzb.onError(paramString2);
    }
  }
  
  /* Error */
  private Intent zzb(Bundle arg1, KeyPair paramKeyPair)
    throws IOException
  {
    // Byte code:
    //   0: invokestatic 257	com/google/firebase/iid/zzf:zzHn	()Ljava/lang/String;
    //   3: astore_3
    //   4: new 10	com/google/firebase/iid/zzf$zza
    //   7: dup
    //   8: aconst_null
    //   9: invokespecial 260	com/google/firebase/iid/zzf$zza:<init>	(Lcom/google/firebase/iid/zzf$1;)V
    //   12: astore 5
    //   14: aload_0
    //   15: getfield 66	com/google/firebase/iid/zzf:zzcly	Landroid/support/v4/util/SimpleArrayMap;
    //   18: astore 4
    //   20: aload 4
    //   22: monitorenter
    //   23: aload_0
    //   24: getfield 66	com/google/firebase/iid/zzf:zzcly	Landroid/support/v4/util/SimpleArrayMap;
    //   27: aload_3
    //   28: aload 5
    //   30: invokevirtual 264	android/support/v4/util/SimpleArrayMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   33: pop
    //   34: aload 4
    //   36: monitorexit
    //   37: aload_0
    //   38: aload_1
    //   39: aload_2
    //   40: aload_3
    //   41: invokevirtual 267	com/google/firebase/iid/zzf:zza	(Landroid/os/Bundle;Ljava/security/KeyPair;Ljava/lang/String;)V
    //   44: aload 5
    //   46: invokevirtual 271	com/google/firebase/iid/zzf$zza:zzabV	()Landroid/content/Intent;
    //   49: astore_2
    //   50: aload_0
    //   51: getfield 66	com/google/firebase/iid/zzf:zzcly	Landroid/support/v4/util/SimpleArrayMap;
    //   54: astore_1
    //   55: aload_1
    //   56: monitorenter
    //   57: aload_0
    //   58: getfield 66	com/google/firebase/iid/zzf:zzcly	Landroid/support/v4/util/SimpleArrayMap;
    //   61: aload_3
    //   62: invokevirtual 245	android/support/v4/util/SimpleArrayMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   65: pop
    //   66: aload_1
    //   67: monitorexit
    //   68: aload_2
    //   69: areturn
    //   70: astore_1
    //   71: aload 4
    //   73: monitorexit
    //   74: aload_1
    //   75: athrow
    //   76: astore_2
    //   77: aload_1
    //   78: monitorexit
    //   79: aload_2
    //   80: athrow
    //   81: astore_2
    //   82: aload_0
    //   83: getfield 66	com/google/firebase/iid/zzf:zzcly	Landroid/support/v4/util/SimpleArrayMap;
    //   86: astore_1
    //   87: aload_1
    //   88: monitorenter
    //   89: aload_0
    //   90: getfield 66	com/google/firebase/iid/zzf:zzcly	Landroid/support/v4/util/SimpleArrayMap;
    //   93: aload_3
    //   94: invokevirtual 245	android/support/v4/util/SimpleArrayMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   97: pop
    //   98: aload_1
    //   99: monitorexit
    //   100: aload_2
    //   101: athrow
    //   102: astore_2
    //   103: aload_1
    //   104: monitorexit
    //   105: aload_2
    //   106: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	107	0	this	zzf
    //   0	107	2	paramKeyPair	KeyPair
    //   3	91	3	str	String
    //   18	54	4	localSimpleArrayMap	SimpleArrayMap
    //   12	33	5	localzza	zza
    // Exception table:
    //   from	to	target	type
    //   23	37	70	finally
    //   71	74	70	finally
    //   57	68	76	finally
    //   77	79	76	finally
    //   44	50	81	finally
    //   89	100	102	finally
    //   103	105	102	finally
  }
  
  private void zzb(String paramString, Intent paramIntent)
  {
    zzb localzzb;
    synchronized (this.zzcly)
    {
      localzzb = (zzb)this.zzcly.remove(paramString);
      if (localzzb == null)
      {
        paramString = String.valueOf(paramString);
        if (paramString.length() != 0)
        {
          paramString = "Missing callback for ".concat(paramString);
          Log.w("InstanceID/Rpc", paramString);
          return;
        }
        paramString = new String("Missing callback for ");
      }
    }
    localzzb.zzH(paramIntent);
  }
  
  private static boolean zzb(PackageManager paramPackageManager)
  {
    Iterator localIterator = paramPackageManager.queryBroadcastReceivers(new Intent("com.google.iid.TOKEN_REQUEST"), 0).iterator();
    while (localIterator.hasNext()) {
      if (zza(paramPackageManager, ((ResolveInfo)localIterator.next()).activityInfo.packageName, "com.google.iid.TOKEN_REQUEST"))
      {
        zzbhR = true;
        return true;
      }
    }
    return false;
  }
  
  private static boolean zzb(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      paramPackageManager = paramPackageManager.getApplicationInfo(paramString, 0);
      zzbhQ = paramPackageManager.packageName;
      zzbhT = paramPackageManager.uid;
      return true;
    }
    catch (PackageManager.NameNotFoundException paramPackageManager) {}
    return false;
  }
  
  public static String zzbA(Context paramContext)
  {
    if (zzbhQ != null) {
      return zzbhQ;
    }
    zzbhS = Process.myUid();
    paramContext = paramContext.getPackageManager();
    if (zzb(paramContext)) {
      return zzbhQ;
    }
    if ((!zzt.zzzq()) && (zza(paramContext))) {
      return zzbhQ;
    }
    Log.w("InstanceID/Rpc", "Failed to resolve IID implementation package, falling back");
    if (zzb(paramContext, "com.google.android.gms"))
    {
      zzbhR = zzt.zzzq();
      return zzbhQ;
    }
    if ((!zzt.zzzo()) && (zzb(paramContext, "com.google.android.gsf")))
    {
      zzbhR = false;
      return zzbhQ;
    }
    Log.w("InstanceID/Rpc", "Google Play services is missing, unable to get tokens");
    return null;
  }
  
  private void zzeF(String paramString)
  {
    if (!"com.google.android.gsf".equals(zzbhQ)) {}
    do
    {
      return;
      this.zzbib += 1;
    } while (this.zzbib < 3);
    if (this.zzbib == 3) {
      this.zzbic = (new Random().nextInt(1000) + 1000);
    }
    this.zzbic *= 2;
    this.zzbid = (SystemClock.elapsedRealtime() + this.zzbic);
    int i = this.zzbic;
    Log.w("InstanceID/Rpc", String.valueOf(paramString).length() + 31 + "Backoff due to " + paramString + " for " + i);
  }
  
  public static void zzf(Context paramContext, Intent paramIntent)
  {
    try
    {
      if (zzbgG == null)
      {
        Intent localIntent = new Intent();
        localIntent.setPackage("com.google.example.invalidpackage");
        zzbgG = PendingIntent.getBroadcast(paramContext, 0, localIntent, 0);
      }
      paramIntent.putExtra("app", zzbgG);
      return;
    }
    finally {}
  }
  
  void zzHl()
  {
    if (this.zzbgK != null) {
      return;
    }
    zzbA(this.zzqn);
    this.zzbgK = new Messenger(new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        zzf.this.zze(paramAnonymousMessage);
      }
    });
  }
  
  void zzHm()
  {
    try
    {
      if (zzbhV == null)
      {
        zzbhV = new BroadcastReceiver()
        {
          public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
          {
            if (Log.isLoggable("InstanceID/Rpc", 3))
            {
              paramAnonymousContext = String.valueOf(paramAnonymousIntent.getExtras());
              Log.d("InstanceID/Rpc", String.valueOf(paramAnonymousContext).length() + 44 + "Received GSF callback via dynamic receiver: " + paramAnonymousContext);
            }
            zzf.this.zzs(paramAnonymousIntent);
          }
        };
        if (Log.isLoggable("InstanceID/Rpc", 3)) {
          Log.d("InstanceID/Rpc", "Registered GSF callback receiver");
        }
        IntentFilter localIntentFilter = new IntentFilter("com.google.android.c2dm.intent.REGISTRATION");
        localIntentFilter.addCategory(this.zzqn.getPackageName());
        this.zzqn.registerReceiver(zzbhV, localIntentFilter, "com.google.android.c2dm.permission.SEND", null);
      }
      return;
    }
    finally {}
  }
  
  Intent zza(Bundle paramBundle, KeyPair paramKeyPair)
    throws IOException
  {
    Intent localIntent = zzb(paramBundle, paramKeyPair);
    Object localObject = localIntent;
    if (localIntent != null)
    {
      localObject = localIntent;
      if (localIntent.hasExtra("google.messenger"))
      {
        paramBundle = zzb(paramBundle, paramKeyPair);
        localObject = paramBundle;
        if (paramBundle != null)
        {
          localObject = paramBundle;
          if (paramBundle.hasExtra("google.messenger")) {
            localObject = null;
          }
        }
      }
    }
    return (Intent)localObject;
  }
  
  public void zza(Bundle paramBundle, KeyPair paramKeyPair, String paramString)
    throws IOException
  {
    long l1 = SystemClock.elapsedRealtime();
    if ((this.zzbid != 0L) && (l1 <= this.zzbid))
    {
      long l2 = this.zzbid;
      int i = this.zzbic;
      Log.w("InstanceID/Rpc", 78 + "Backoff mode, next request attempt: " + (l2 - l1) + " interval: " + i);
      throw new IOException("RETRY_LATER");
    }
    zzHl();
    if (zzbhQ == null) {
      throw new IOException("MISSING_INSTANCEID_SERVICE");
    }
    this.zzbhZ = SystemClock.elapsedRealtime();
    if (zzbhR) {}
    for (Object localObject = "com.google.iid.TOKEN_REQUEST";; localObject = "com.google.android.c2dm.intent.REGISTER")
    {
      localObject = new Intent((String)localObject);
      ((Intent)localObject).setPackage(zzbhQ);
      paramBundle.putString("gmsv", Integer.toString(FirebaseInstanceId.zzS(this.zzqn, zzbA(this.zzqn))));
      paramBundle.putString("osv", Integer.toString(Build.VERSION.SDK_INT));
      paramBundle.putString("app_ver", Integer.toString(FirebaseInstanceId.zzcr(this.zzqn)));
      paramBundle.putString("app_ver_name", FirebaseInstanceId.zzbx(this.zzqn));
      paramBundle.putString("cliv", "fiid-10260000");
      paramBundle.putString("appid", FirebaseInstanceId.zza(paramKeyPair));
      String str = FirebaseInstanceId.zzv(paramKeyPair.getPublic().getEncoded());
      paramBundle.putString("pub2", str);
      paramBundle.putString("sig", zza(paramKeyPair, new String[] { this.zzqn.getPackageName(), str }));
      ((Intent)localObject).putExtras(paramBundle);
      zzf(this.zzqn, (Intent)localObject);
      zzb((Intent)localObject, paramString);
      return;
    }
  }
  
  protected void zzb(Intent paramIntent, String paramString)
  {
    this.zzbhZ = SystemClock.elapsedRealtime();
    paramIntent.putExtra("kid", String.valueOf(paramString).length() + 5 + "|ID|" + paramString + "|");
    paramIntent.putExtra("X-kid", String.valueOf(paramString).length() + 5 + "|ID|" + paramString + "|");
    boolean bool = "com.google.android.gsf".equals(zzbhQ);
    if (Log.isLoggable("InstanceID/Rpc", 3))
    {
      paramString = String.valueOf(paramIntent.getExtras());
      Log.d("InstanceID/Rpc", String.valueOf(paramString).length() + 8 + "Sending " + paramString);
    }
    if (bool)
    {
      zzHm();
      this.zzqn.startService(paramIntent);
      return;
    }
    paramIntent.putExtra("google.messenger", this.zzbgK);
    if ((this.zzbhX != null) || (this.zzbhY != null))
    {
      paramString = Message.obtain();
      paramString.obj = paramIntent;
      try
      {
        if (this.zzbhX == null) {
          break label259;
        }
        this.zzbhX.send(paramString);
        return;
      }
      catch (RemoteException paramString)
      {
        if (Log.isLoggable("InstanceID/Rpc", 3)) {
          Log.d("InstanceID/Rpc", "Messenger failed, fallback to startService");
        }
      }
    }
    else
    {
      if (!zzbhR) {
        break label268;
      }
      this.zzqn.sendBroadcast(paramIntent);
      return;
    }
    label259:
    this.zzbhY.send(paramString);
    return;
    label268:
    this.zzqn.startService(paramIntent);
  }
  
  void zze(Message paramMessage)
  {
    if (paramMessage == null) {
      return;
    }
    if ((paramMessage.obj instanceof Intent))
    {
      Object localObject = (Intent)paramMessage.obj;
      ((Intent)localObject).setExtrasClassLoader(MessengerCompat.class.getClassLoader());
      if (((Intent)localObject).hasExtra("google.messenger"))
      {
        localObject = ((Intent)localObject).getParcelableExtra("google.messenger");
        if ((localObject instanceof MessengerCompat)) {
          this.zzbhY = ((MessengerCompat)localObject);
        }
        if ((localObject instanceof Messenger)) {
          this.zzbhX = ((Messenger)localObject);
        }
      }
      zzs((Intent)paramMessage.obj);
      return;
    }
    Log.w("InstanceID/Rpc", "Dropping invalid message");
  }
  
  String zzq(Intent paramIntent)
    throws IOException
  {
    if (paramIntent == null) {
      throw new IOException("SERVICE_NOT_AVAILABLE");
    }
    String str2 = paramIntent.getStringExtra("registration_id");
    String str1 = str2;
    if (str2 == null) {
      str1 = paramIntent.getStringExtra("unregistered");
    }
    if (str1 == null)
    {
      str1 = paramIntent.getStringExtra("error");
      if (str1 != null) {
        throw new IOException(str1);
      }
      paramIntent = String.valueOf(paramIntent.getExtras());
      Log.w("InstanceID/Rpc", String.valueOf(paramIntent).length() + 29 + "Unexpected response from GCM " + paramIntent, new Throwable());
      throw new IOException("SERVICE_NOT_AVAILABLE");
    }
    return str1;
  }
  
  void zzr(Intent paramIntent)
  {
    String str3 = paramIntent.getStringExtra("error");
    if (str3 == null)
    {
      paramIntent = String.valueOf(paramIntent.getExtras());
      Log.w("InstanceID/Rpc", String.valueOf(paramIntent).length() + 49 + "Unexpected response, no error or registration id " + paramIntent);
      return;
    }
    String str1;
    label93:
    label160:
    String str2;
    if (Log.isLoggable("InstanceID/Rpc", 3))
    {
      str1 = String.valueOf(str3);
      if (str1.length() != 0)
      {
        str1 = "Received InstanceID error ".concat(str1);
        Log.d("InstanceID/Rpc", str1);
      }
    }
    else
    {
      if (!str3.startsWith("|")) {
        break label376;
      }
      String[] arrayOfString = str3.split("\\|");
      if (!"ID".equals(arrayOfString[1]))
      {
        str1 = String.valueOf(str3);
        if (str1.length() == 0) {
          break label318;
        }
        str1 = "Unexpected structured response ".concat(str1);
        Log.w("InstanceID/Rpc", str1);
      }
      if (arrayOfString.length <= 2) {
        break label333;
      }
      str2 = arrayOfString[2];
      str1 = arrayOfString[3];
      if (!str1.startsWith(":")) {
        break label373;
      }
      str1 = str1.substring(1);
      label206:
      paramIntent.putExtra("error", str1);
    }
    for (;;)
    {
      zzay(str2, str1);
      long l = paramIntent.getLongExtra("Retry-After", 0L);
      if (l > 0L)
      {
        this.zzbia = SystemClock.elapsedRealtime();
        this.zzbic = ((int)l * 1000);
        this.zzbid = (SystemClock.elapsedRealtime() + this.zzbic);
        int i = this.zzbic;
        Log.w("InstanceID/Rpc", 52 + "Explicit request from server to backoff: " + i);
        return;
        str1 = new String("Received InstanceID error ");
        break label93;
        label318:
        str1 = new String("Unexpected structured response ");
        break label160;
        label333:
        str1 = "UNKNOWN";
        str2 = null;
        break label206;
      }
      if ((!"SERVICE_NOT_AVAILABLE".equals(str1)) && (!"AUTHENTICATION_FAILED".equals(str1))) {
        break;
      }
      zzeF(str1);
      return;
      label373:
      break label206;
      label376:
      str2 = null;
      str1 = str3;
    }
  }
  
  void zzs(Intent paramIntent)
  {
    if (paramIntent == null) {
      if (Log.isLoggable("InstanceID/Rpc", 3)) {
        Log.d("InstanceID/Rpc", "Unexpected response: null");
      }
    }
    do
    {
      return;
      if ("com.google.android.c2dm.intent.REGISTRATION".equals(paramIntent.getAction())) {
        break;
      }
    } while (!Log.isLoggable("InstanceID/Rpc", 3));
    paramIntent = String.valueOf(paramIntent.getAction());
    if (paramIntent.length() != 0) {}
    for (paramIntent = "Unexpected response ".concat(paramIntent);; paramIntent = new String("Unexpected response "))
    {
      Log.d("InstanceID/Rpc", paramIntent);
      return;
    }
    String str = paramIntent.getStringExtra("registration_id");
    Object localObject1 = str;
    if (str == null) {
      localObject1 = paramIntent.getStringExtra("unregistered");
    }
    if (localObject1 == null)
    {
      zzr(paramIntent);
      return;
    }
    this.zzbhZ = SystemClock.elapsedRealtime();
    this.zzbid = 0L;
    this.zzbib = 0;
    this.zzbic = 0;
    Object localObject2;
    if (((String)localObject1).startsWith("|"))
    {
      localObject2 = ((String)localObject1).split("\\|");
      if (!"ID".equals(localObject2[1]))
      {
        localObject1 = String.valueOf(localObject1);
        if (((String)localObject1).length() != 0)
        {
          localObject1 = "Unexpected structured response ".concat((String)localObject1);
          label196:
          Log.w("InstanceID/Rpc", (String)localObject1);
        }
      }
      else
      {
        str = localObject2[2];
        if (localObject2.length > 4)
        {
          if (!"SYNC".equals(localObject2[3])) {
            break label314;
          }
          FirebaseInstanceId.zzby(this.zzqn);
        }
        label235:
        localObject2 = localObject2[(localObject2.length - 1)];
        localObject1 = localObject2;
        if (((String)localObject2).startsWith(":")) {
          localObject1 = ((String)localObject2).substring(1);
        }
        paramIntent.putExtra("registration_id", (String)localObject1);
      }
    }
    for (localObject1 = str;; localObject1 = null)
    {
      if (localObject1 == null)
      {
        if (!Log.isLoggable("InstanceID/Rpc", 3)) {
          break;
        }
        Log.d("InstanceID/Rpc", "Ignoring response without a request ID");
        return;
        localObject1 = new String("Unexpected structured response ");
        break label196;
        label314:
        if (!"RST".equals(localObject2[3])) {
          break label235;
        }
        FirebaseInstanceId.zza(this.zzqn, zzd.zzb(this.zzqn, null).zzabS());
        paramIntent.removeExtra("registration_id");
        zzb(str, paramIntent);
        return;
      }
      zzb((String)localObject1, paramIntent);
      return;
    }
  }
  
  private static class zza
    implements zzf.zzb
  {
    private Intent intent;
    private final ConditionVariable zzclA = new ConditionVariable();
    private String zzclB;
    
    public void onError(String paramString)
    {
      this.zzclB = paramString;
      this.zzclA.open();
    }
    
    public void zzH(Intent paramIntent)
    {
      this.intent = paramIntent;
      this.zzclA.open();
    }
    
    public Intent zzabV()
      throws IOException
    {
      if (!this.zzclA.block(30000L))
      {
        Log.w("InstanceID/Rpc", "No response");
        throw new IOException("TIMEOUT");
      }
      if (this.zzclB != null) {
        throw new IOException(this.zzclB);
      }
      return this.intent;
    }
  }
  
  private static abstract interface zzb
  {
    public abstract void onError(String paramString);
    
    public abstract void zzH(Intent paramIntent);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\iid\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */