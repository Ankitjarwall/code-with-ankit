package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageInstaller.SessionInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import com.google.android.gms.R.string;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.common.util.zzm;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.common.util.zzy;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class zzg
{
  @Deprecated
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  @Deprecated
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 10260000;
  public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
  private static boolean zzayA = false;
  static final AtomicBoolean zzayB = new AtomicBoolean();
  private static final AtomicBoolean zzayC = new AtomicBoolean();
  public static boolean zzayx = false;
  public static boolean zzayy = false;
  static boolean zzayz = false;
  
  @Deprecated
  public static PendingIntent getErrorPendingIntent(int paramInt1, Context paramContext, int paramInt2)
  {
    return zze.zzuY().getErrorResolutionPendingIntent(paramContext, paramInt1, paramInt2);
  }
  
  @Deprecated
  public static String getErrorString(int paramInt)
  {
    return ConnectionResult.getStatusString(paramInt);
  }
  
  @Deprecated
  public static String getOpenSourceSoftwareLicenseInfo(Context paramContext)
  {
    Object localObject = new Uri.Builder().scheme("android.resource").authority("com.google.android.gms").appendPath("raw").appendPath("oss_notice").build();
    try
    {
      InputStream localInputStream = paramContext.getContentResolver().openInputStream((Uri)localObject);
      try
      {
        paramContext = new Scanner(localInputStream).useDelimiter("\\A").next();
        localObject = paramContext;
        if (localInputStream != null)
        {
          localInputStream.close();
          return paramContext;
        }
      }
      catch (NoSuchElementException paramContext)
      {
        paramContext = paramContext;
        if (localInputStream == null) {
          break label97;
        }
        localInputStream.close();
        break label97;
      }
      finally
      {
        paramContext = finally;
        if (localInputStream != null) {
          localInputStream.close();
        }
        throw paramContext;
      }
      return (String)localObject;
    }
    catch (Exception paramContext)
    {
      localObject = null;
    }
    label97:
    return null;
  }
  
  public static Context getRemoteContext(Context paramContext)
  {
    try
    {
      paramContext = paramContext.createPackageContext("com.google.android.gms", 3);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static Resources getRemoteResource(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getResourcesForApplication("com.google.android.gms");
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  @Deprecated
  public static int isGooglePlayServicesAvailable(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    PackageInfo localPackageInfo;
    try
    {
      paramContext.getResources().getString(R.string.common_google_play_services_unknown_issue);
      if (!"com.google.android.gms".equals(paramContext.getPackageName())) {
        zzaH(paramContext);
      }
      if ((!zzj.zzba(paramContext)) && (!zzj.zzbc(paramContext)))
      {
        i = 1;
        localObject = null;
        if (i == 0) {}
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        try
        {
          localObject = localPackageManager.getPackageInfo("com.android.vending", 8256);
        }
        catch (PackageManager.NameNotFoundException paramContext)
        {
          Object localObject;
          Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
          return 9;
        }
        try
        {
          localPackageInfo = localPackageManager.getPackageInfo("com.google.android.gms", 64);
          paramContext = zzh.zzaN(paramContext);
          if (i == 0) {
            break label178;
          }
          localObject = paramContext.zza((PackageInfo)localObject, zzf.zzd.zzayw);
          if (localObject != null) {
            break label150;
          }
          Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
          return 9;
        }
        catch (PackageManager.NameNotFoundException paramContext)
        {
          Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
          return 1;
        }
        localThrowable = localThrowable;
        Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        continue;
        i = 0;
      }
    }
    label150:
    if (paramContext.zza(localPackageInfo, new zzf.zza[] { localThrowable }) == null)
    {
      Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
      return 9;
      label178:
      if (paramContext.zza(localPackageInfo, zzf.zzd.zzayw) == null)
      {
        Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
        return 9;
      }
    }
    int i = zzm.zzdp(GOOGLE_PLAY_SERVICES_VERSION_CODE);
    if (zzm.zzdp(localPackageInfo.versionCode) < i)
    {
      i = GOOGLE_PLAY_SERVICES_VERSION_CODE;
      int j = localPackageInfo.versionCode;
      Log.w("GooglePlayServicesUtil", 77 + "Google Play services out of date.  Requires " + i + " but found " + j);
      return 2;
    }
    ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
    paramContext = localApplicationInfo;
    if (localApplicationInfo == null) {}
    try
    {
      paramContext = localPackageManager.getApplicationInfo("com.google.android.gms", 0);
      if (!paramContext.enabled) {
        return 3;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", paramContext);
      return 1;
    }
    return 0;
  }
  
  @Deprecated
  public static boolean isUserRecoverableError(int paramInt)
  {
    switch (paramInt)
    {
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    default: 
      return false;
    }
    return true;
  }
  
  @TargetApi(21)
  static boolean zzA(Context paramContext, String paramString)
  {
    boolean bool = paramString.equals("com.google.android.gms");
    if (zzt.zzzo())
    {
      localObject = paramContext.getPackageManager().getPackageInstaller().getAllSessions().iterator();
      while (((Iterator)localObject).hasNext()) {
        if (paramString.equals(((PackageInstaller.SessionInfo)((Iterator)localObject).next()).getAppPackageName())) {
          return true;
        }
      }
    }
    Object localObject = paramContext.getPackageManager();
    try
    {
      paramString = ((PackageManager)localObject).getApplicationInfo(paramString, 8192);
      if (bool) {
        return paramString.enabled;
      }
      if (paramString.enabled)
      {
        bool = zzaK(paramContext);
        if (bool) {}
      }
      for (bool = true;; bool = false) {
        return bool;
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
  }
  
  @Deprecated
  public static int zzaC(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.google.android.gms", 0);
      return paramContext.versionCode;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
    }
    return 0;
  }
  
  @Deprecated
  public static void zzaF(Context paramContext)
  {
    if (zzayB.getAndSet(true)) {}
    for (;;)
    {
      return;
      try
      {
        paramContext = (NotificationManager)paramContext.getSystemService("notification");
        if (paramContext != null)
        {
          paramContext.cancel(10436);
          return;
        }
      }
      catch (SecurityException paramContext) {}
    }
  }
  
  private static void zzaH(Context paramContext)
  {
    if (zzayC.get()) {}
    int i;
    do
    {
      return;
      i = zzz.zzaW(paramContext);
      if (i == 0) {
        throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
      }
    } while (i == GOOGLE_PLAY_SERVICES_VERSION_CODE);
    int j = GOOGLE_PLAY_SERVICES_VERSION_CODE;
    paramContext = String.valueOf("com.google.android.gms.version");
    throw new IllegalStateException(String.valueOf(paramContext).length() + 290 + "The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected " + j + " but found " + i + ".  You must have the following declaration within the <application> element:     <meta-data android:name=\"" + paramContext + "\" android:value=\"@integer/google_play_services_version\" />");
  }
  
  public static boolean zzaI(Context paramContext)
  {
    zzaL(paramContext);
    return zzayz;
  }
  
  public static boolean zzaJ(Context paramContext)
  {
    return (zzaI(paramContext)) || (!zzvd());
  }
  
  @TargetApi(18)
  public static boolean zzaK(Context paramContext)
  {
    if (zzt.zzzk())
    {
      paramContext = ((UserManager)paramContext.getSystemService("user")).getApplicationRestrictions(paramContext.getPackageName());
      if ((paramContext != null) && ("true".equals(paramContext.getString("restricted_profile")))) {
        return true;
      }
    }
    return false;
  }
  
  private static void zzaL(Context paramContext)
  {
    if (!zzayA) {
      zzaM(paramContext);
    }
  }
  
  /* Error */
  private static void zzaM(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 415	com/google/android/gms/internal/zzadg:zzbi	(Landroid/content/Context;)Lcom/google/android/gms/internal/zzadf;
    //   4: ldc 8
    //   6: bipush 64
    //   8: invokevirtual 418	com/google/android/gms/internal/zzadf:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   11: astore_1
    //   12: aload_1
    //   13: ifnull +35 -> 48
    //   16: aload_0
    //   17: invokestatic 197	com/google/android/gms/common/zzh:zzaN	(Landroid/content/Context;)Lcom/google/android/gms/common/zzh;
    //   20: aload_1
    //   21: iconst_1
    //   22: anewarray 228	com/google/android/gms/common/zzf$zza
    //   25: dup
    //   26: iconst_0
    //   27: getstatic 203	com/google/android/gms/common/zzf$zzd:zzayw	[Lcom/google/android/gms/common/zzf$zza;
    //   30: iconst_1
    //   31: aaload
    //   32: aastore
    //   33: invokevirtual 207	com/google/android/gms/common/zzh:zza	(Landroid/content/pm/PackageInfo;[Lcom/google/android/gms/common/zzf$zza;)Lcom/google/android/gms/common/zzf$zza;
    //   36: ifnull +12 -> 48
    //   39: iconst_1
    //   40: putstatic 33	com/google/android/gms/common/zzg:zzayz	Z
    //   43: iconst_1
    //   44: putstatic 35	com/google/android/gms/common/zzg:zzayA	Z
    //   47: return
    //   48: iconst_0
    //   49: putstatic 33	com/google/android/gms/common/zzg:zzayz	Z
    //   52: goto -9 -> 43
    //   55: astore_0
    //   56: ldc -47
    //   58: ldc_w 420
    //   61: aload_0
    //   62: invokestatic 422	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   65: pop
    //   66: iconst_1
    //   67: putstatic 35	com/google/android/gms/common/zzg:zzayA	Z
    //   70: return
    //   71: astore_0
    //   72: iconst_1
    //   73: putstatic 35	com/google/android/gms/common/zzg:zzayA	Z
    //   76: aload_0
    //   77: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	78	0	paramContext	Context
    //   11	10	1	localPackageInfo	PackageInfo
    // Exception table:
    //   from	to	target	type
    //   0	12	55	android/content/pm/PackageManager$NameNotFoundException
    //   16	43	55	android/content/pm/PackageManager$NameNotFoundException
    //   48	52	55	android/content/pm/PackageManager$NameNotFoundException
    //   0	12	71	finally
    //   16	43	71	finally
    //   48	52	71	finally
    //   56	66	71	finally
  }
  
  @Deprecated
  public static void zzaq(Context paramContext)
    throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
  {
    int i = zze.zzuY().isGooglePlayServicesAvailable(paramContext);
    if (i != 0)
    {
      paramContext = zze.zzuY().zzb(paramContext, i, "e");
      Log.e("GooglePlayServicesUtil", 57 + "GooglePlayServices not available due to error " + i);
      if (paramContext == null) {
        throw new GooglePlayServicesNotAvailableException(i);
      }
      throw new GooglePlayServicesRepairableException(i, "Google Play Services not available", paramContext);
    }
  }
  
  @Deprecated
  @TargetApi(19)
  public static boolean zzc(Context paramContext, int paramInt, String paramString)
  {
    return zzy.zzc(paramContext, paramInt, paramString);
  }
  
  @Deprecated
  public static boolean zzd(Context paramContext, int paramInt)
  {
    if (paramInt == 18) {
      return true;
    }
    if (paramInt == 1) {
      return zzA(paramContext, "com.google.android.gms");
    }
    return false;
  }
  
  @Deprecated
  public static boolean zze(Context paramContext, int paramInt)
  {
    if (paramInt == 9) {
      return zzA(paramContext, "com.android.vending");
    }
    return false;
  }
  
  @Deprecated
  public static boolean zzf(Context paramContext, int paramInt)
  {
    return zzy.zzf(paramContext, paramInt);
  }
  
  @Deprecated
  public static boolean zzvd()
  {
    return zzj.zzzd();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */