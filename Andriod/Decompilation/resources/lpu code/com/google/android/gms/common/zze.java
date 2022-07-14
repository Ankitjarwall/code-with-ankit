package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.internal.zzadf;
import com.google.android.gms.internal.zzadg;

public class zze
{
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zzg.GOOGLE_PLAY_SERVICES_VERSION_CODE;
  private static final zze zzayo = new zze();
  
  static String zzB(@Nullable Context paramContext, @Nullable String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("gcore_");
    localStringBuilder.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
    localStringBuilder.append("-");
    if (!TextUtils.isEmpty(paramString)) {
      localStringBuilder.append(paramString);
    }
    localStringBuilder.append("-");
    if (paramContext != null) {
      localStringBuilder.append(paramContext.getPackageName());
    }
    localStringBuilder.append("-");
    if (paramContext != null) {}
    try
    {
      localStringBuilder.append(zzadg.zzbi(paramContext).getPackageInfo(paramContext.getPackageName(), 0).versionCode);
      return localStringBuilder.toString();
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
  }
  
  public static zze zzuY()
  {
    return zzayo;
  }
  
  @Nullable
  public PendingIntent getErrorResolutionPendingIntent(Context paramContext, int paramInt1, int paramInt2)
  {
    return zza(paramContext, paramInt1, paramInt2, null);
  }
  
  public String getErrorString(int paramInt)
  {
    return zzg.getErrorString(paramInt);
  }
  
  @Nullable
  public String getOpenSourceSoftwareLicenseInfo(Context paramContext)
  {
    return zzg.getOpenSourceSoftwareLicenseInfo(paramContext);
  }
  
  public int isGooglePlayServicesAvailable(Context paramContext)
  {
    int j = zzg.isGooglePlayServicesAvailable(paramContext);
    int i = j;
    if (zzg.zzd(paramContext, j)) {
      i = 18;
    }
    return i;
  }
  
  public boolean isUserResolvableError(int paramInt)
  {
    return zzg.isUserRecoverableError(paramInt);
  }
  
  public boolean zzA(Context paramContext, String paramString)
  {
    return zzg.zzA(paramContext, paramString);
  }
  
  @Nullable
  public PendingIntent zza(Context paramContext, int paramInt1, int paramInt2, @Nullable String paramString)
  {
    paramString = zzb(paramContext, paramInt1, paramString);
    if (paramString == null) {
      return null;
    }
    return PendingIntent.getActivity(paramContext, paramInt2, paramString, 268435456);
  }
  
  public int zzaC(Context paramContext)
  {
    return zzg.zzaC(paramContext);
  }
  
  public void zzaE(Context paramContext)
    throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
  {
    zzg.zzaq(paramContext);
  }
  
  public void zzaF(Context paramContext)
  {
    zzg.zzaF(paramContext);
  }
  
  @Nullable
  public Intent zzb(Context paramContext, int paramInt, @Nullable String paramString)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 1: 
    case 2: 
      if ((paramContext != null) && (zzj.zzba(paramContext))) {
        return zzp.zzyb();
      }
      return zzp.zzD("com.google.android.gms", zzB(paramContext, paramString));
    }
    return zzp.zzdp("com.google.android.gms");
  }
  
  @Deprecated
  @Nullable
  public Intent zzcw(int paramInt)
  {
    return zzb(null, paramInt, null);
  }
  
  public boolean zzd(Context paramContext, int paramInt)
  {
    return zzg.zzd(paramContext, paramInt);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */