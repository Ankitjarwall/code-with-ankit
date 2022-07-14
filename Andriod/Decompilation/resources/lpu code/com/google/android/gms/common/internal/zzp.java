package com.google.android.gms.common.internal;

import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public class zzp
{
  private static final Uri zzaGn = Uri.parse("https://plus.google.com/");
  private static final Uri zzaGo = zzaGn.buildUpon().appendPath("circles").appendPath("find").build();
  
  private static Uri zzC(String paramString1, @Nullable String paramString2)
  {
    paramString1 = Uri.parse("market://details").buildUpon().appendQueryParameter("id", paramString1);
    if (!TextUtils.isEmpty(paramString2)) {
      paramString1.appendQueryParameter("pcampaignid", paramString2);
    }
    return paramString1.build();
  }
  
  public static Intent zzD(String paramString1, @Nullable String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(zzC(paramString1, paramString2));
    localIntent.setPackage("com.android.vending");
    localIntent.addFlags(524288);
    return localIntent;
  }
  
  public static Intent zzdp(String paramString)
  {
    paramString = Uri.fromParts("package", paramString, null);
    Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
    localIntent.setData(paramString);
    return localIntent;
  }
  
  public static Intent zzyb()
  {
    Intent localIntent = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
    localIntent.setPackage("com.google.android.wearable.app");
    return localIntent;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */