package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzadf;
import com.google.android.gms.internal.zzadg;

public class zzh
{
  private static zzh zzayD;
  private final Context mContext;
  private final zzadf zzayE;
  
  private zzh(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
    this.zzayE = zzadg.zzbi(this.mContext);
  }
  
  public static zzh zzaN(Context paramContext)
  {
    zzac.zzw(paramContext);
    try
    {
      if (zzayD == null)
      {
        zzf.zzaG(paramContext);
        zzayD = new zzh(paramContext);
      }
      return zzayD;
    }
    finally {}
  }
  
  zzf.zza zza(PackageInfo paramPackageInfo, zzf.zza... paramVarArgs)
  {
    int i = 0;
    if (paramPackageInfo.signatures == null) {
      return null;
    }
    if (paramPackageInfo.signatures.length != 1)
    {
      Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
      return null;
    }
    paramPackageInfo = new zzf.zzb(paramPackageInfo.signatures[0].toByteArray());
    while (i < paramVarArgs.length)
    {
      if (paramVarArgs[i].equals(paramPackageInfo)) {
        return paramVarArgs[i];
      }
      i += 1;
    }
    return null;
  }
  
  public boolean zza(PackageInfo paramPackageInfo)
  {
    boolean bool1 = false;
    if (paramPackageInfo == null) {}
    boolean bool2;
    do
    {
      do
      {
        return bool1;
        if (zzg.zzaJ(this.mContext)) {
          return zzb(paramPackageInfo, true);
        }
        bool2 = zzb(paramPackageInfo, false);
        bool1 = bool2;
      } while (bool2);
      bool1 = bool2;
    } while (!zzb(paramPackageInfo, true));
    Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
    return bool2;
  }
  
  public boolean zza(PackageInfo paramPackageInfo, boolean paramBoolean)
  {
    if ((paramPackageInfo != null) && (paramPackageInfo.signatures != null))
    {
      if (paramBoolean) {}
      for (paramPackageInfo = zza(paramPackageInfo, zzf.zzd.zzayw); paramPackageInfo != null; paramPackageInfo = zza(paramPackageInfo, new zzf.zza[] { zzf.zzd.zzayw[0] })) {
        return true;
      }
    }
    return false;
  }
  
  @Deprecated
  public boolean zza(PackageManager paramPackageManager, int paramInt)
  {
    return zzcx(paramInt);
  }
  
  @Deprecated
  public boolean zza(PackageManager paramPackageManager, PackageInfo paramPackageInfo)
  {
    return zzb(paramPackageInfo);
  }
  
  public boolean zzb(PackageInfo paramPackageInfo)
  {
    if (paramPackageInfo == null) {}
    do
    {
      return false;
      if (zza(paramPackageInfo, false)) {
        return true;
      }
    } while (!zza(paramPackageInfo, true));
    if (zzg.zzaJ(this.mContext)) {
      return true;
    }
    Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
    return false;
  }
  
  boolean zzb(PackageInfo paramPackageInfo, boolean paramBoolean)
  {
    boolean bool2 = false;
    if (paramPackageInfo.signatures.length != 1)
    {
      Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
      return bool2;
    }
    zzf.zzb localzzb = new zzf.zzb(paramPackageInfo.signatures[0].toByteArray());
    paramPackageInfo = paramPackageInfo.packageName;
    if (paramBoolean) {}
    for (boolean bool1 = zzf.zzb(paramPackageInfo, localzzb);; bool1 = zzf.zza(paramPackageInfo, localzzb))
    {
      bool2 = bool1;
      if (bool1) {
        break;
      }
      Log.d("GoogleSignatureVerifier", 27 + "Cert not in list. atk=" + paramBoolean);
      return bool1;
    }
  }
  
  public boolean zzcx(int paramInt)
  {
    String[] arrayOfString = this.zzayE.getPackagesForUid(paramInt);
    if ((arrayOfString == null) || (arrayOfString.length == 0)) {}
    for (;;)
    {
      return false;
      int i = arrayOfString.length;
      paramInt = 0;
      while (paramInt < i)
      {
        if (zzdd(arrayOfString[paramInt])) {
          return true;
        }
        paramInt += 1;
      }
    }
  }
  
  public boolean zzdd(String paramString)
  {
    try
    {
      boolean bool = zza(this.zzayE.getPackageInfo(paramString, 64));
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */