package com.google.android.youtube.player.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;

public final class z
{
  private static final Uri a = Uri.parse("http://play.google.com/store/apps/details");
  private static final String[] b = { "com.google.android.youtube", "com.google.android.youtube.tv", "com.google.android.youtube.googletv", "com.google.android.gms", null };
  
  public static Intent a(String paramString)
  {
    paramString = Uri.fromParts("package", paramString, null);
    Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
    localIntent.setData(paramString);
    return localIntent;
  }
  
  public static String a()
  {
    return 35 + 1 + ".2.2";
  }
  
  public static String a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    String[] arrayOfString = b;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = arrayOfString[i];
      localObject = paramContext.resolveService(new Intent("com.google.android.youtube.api.service.START").setPackage((String)localObject), 0);
      if ((localObject != null) && (((ResolveInfo)localObject).serviceInfo != null) && (((ResolveInfo)localObject).serviceInfo.packageName != null)) {
        return ((ResolveInfo)localObject).serviceInfo.packageName;
      }
      i += 1;
    }
    if (paramContext.hasSystemFeature("android.software.leanback")) {
      return "com.google.android.youtube.tv";
    }
    if (paramContext.hasSystemFeature("com.google.android.tv")) {
      return "com.google.android.youtube.googletv";
    }
    return "com.google.android.youtube";
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    try
    {
      Resources localResources = paramContext.getPackageManager().getResourcesForApplication(paramString);
      paramContext = paramString;
      if (paramString.equals("com.google.android.youtube.googletvdev")) {
        paramContext = "com.google.android.youtube.googletv";
      }
      int i = localResources.getIdentifier("youtube_api_version_code", "integer", paramContext);
      if (i == 0) {}
      while (12 > localResources.getInteger(i) / 100) {
        return true;
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return true;
  }
  
  public static boolean a(PackageManager paramPackageManager)
  {
    return paramPackageManager.hasSystemFeature("com.google.android.tv");
  }
  
  public static Context b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.createPackageContext(a(paramContext), 3);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static Intent b(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(a.buildUpon().appendQueryParameter("id", paramString).build());
    localIntent.setPackage("com.android.vending");
    localIntent.addFlags(524288);
    return localIntent;
  }
  
  public static boolean b(PackageManager paramPackageManager)
  {
    return paramPackageManager.hasSystemFeature("android.software.leanback");
  }
  
  public static int c(Context paramContext)
  {
    Context localContext = b(paramContext);
    int i = 0;
    if (localContext != null) {
      i = localContext.getResources().getIdentifier("clientTheme", "style", a(paramContext));
    }
    int j = i;
    if (i == 0)
    {
      if (Build.VERSION.SDK_INT >= 14) {
        j = 16974120;
      }
    }
    else {
      return j;
    }
    if (Build.VERSION.SDK_INT >= 11) {
      return 16973931;
    }
    return 16973829;
  }
  
  public static String d(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw new IllegalStateException("Cannot retrieve calling Context's PackageInfo", paramContext);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\youtube\player\internal\z.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */