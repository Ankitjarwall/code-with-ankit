package com.google.android.youtube.player;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.Uri.Builder;
import com.google.android.youtube.player.internal.z;
import java.util.List;

public final class YouTubeIntents
{
  static Intent a(Intent paramIntent, Context paramContext)
  {
    paramIntent.putExtra("app_package", paramContext.getPackageName()).putExtra("app_version", z.d(paramContext)).putExtra("client_library_version", z.a());
    return paramIntent;
  }
  
  private static Uri a(String paramString)
  {
    String str = String.valueOf("https://www.youtube.com/playlist?list=");
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {}
    for (paramString = str.concat(paramString);; paramString = new String(str)) {
      return Uri.parse(paramString);
    }
  }
  
  private static String a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    if (z.b(paramContext)) {
      return "com.google.android.youtube.tv";
    }
    if (z.a(paramContext)) {
      return "com.google.android.youtube.googletv";
    }
    return "com.google.android.youtube";
  }
  
  private static boolean a(Context paramContext, Intent paramIntent)
  {
    paramContext = paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536);
    return (paramContext != null) && (!paramContext.isEmpty());
  }
  
  private static boolean a(Context paramContext, Uri paramUri)
  {
    return a(paramContext, new Intent("android.intent.action.VIEW", paramUri).setPackage(a(paramContext)));
  }
  
  private static Intent b(Context paramContext, Uri paramUri)
  {
    return a(new Intent("android.intent.action.VIEW", paramUri).setPackage(a(paramContext)), paramContext);
  }
  
  public static boolean canResolveChannelIntent(Context paramContext)
  {
    return a(paramContext, Uri.parse("https://www.youtube.com/channel/"));
  }
  
  public static boolean canResolveOpenPlaylistIntent(Context paramContext)
  {
    return a(paramContext, Uri.parse("https://www.youtube.com/playlist?list="));
  }
  
  public static boolean canResolvePlayPlaylistIntent(Context paramContext)
  {
    int i = getInstalledYouTubeVersionCode(paramContext);
    if (z.a(paramContext.getPackageManager())) {
      if (i >= 4700) {
        i = 1;
      }
    }
    while ((i != 0) && (canResolveOpenPlaylistIntent(paramContext)))
    {
      return true;
      i = 0;
      continue;
      if (i >= 4000) {
        i = 1;
      } else {
        i = 0;
      }
    }
    return false;
  }
  
  public static boolean canResolvePlayVideoIntent(Context paramContext)
  {
    return a(paramContext, Uri.parse("https://www.youtube.com/watch?v="));
  }
  
  public static boolean canResolvePlayVideoIntentWithOptions(Context paramContext)
  {
    int i = getInstalledYouTubeVersionCode(paramContext);
    PackageManager localPackageManager = paramContext.getPackageManager();
    if (z.b(localPackageManager)) {
      i = 1;
    }
    while ((i != 0) && (canResolvePlayVideoIntent(paramContext)))
    {
      return true;
      if (z.a(localPackageManager))
      {
        if (i >= Integer.MAX_VALUE) {
          i = 1;
        } else {
          i = 0;
        }
      }
      else if (i >= 3300) {
        i = 1;
      } else {
        i = 0;
      }
    }
    return false;
  }
  
  public static boolean canResolveSearchIntent(Context paramContext)
  {
    if ((z.a(paramContext.getPackageManager())) && (getInstalledYouTubeVersionCode(paramContext) < 4700)) {
      return false;
    }
    return a(paramContext, new Intent("android.intent.action.SEARCH").setPackage(a(paramContext)));
  }
  
  public static boolean canResolveUploadIntent(Context paramContext)
  {
    return a(paramContext, new Intent("com.google.android.youtube.intent.action.UPLOAD").setPackage(a(paramContext)).setType("video/*"));
  }
  
  public static boolean canResolveUserIntent(Context paramContext)
  {
    return a(paramContext, Uri.parse("https://www.youtube.com/user/"));
  }
  
  public static Intent createChannelIntent(Context paramContext, String paramString)
  {
    String str = String.valueOf("https://www.youtube.com/channel/");
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {}
    for (paramString = str.concat(paramString);; paramString = new String(str)) {
      return b(paramContext, Uri.parse(paramString));
    }
  }
  
  public static Intent createOpenPlaylistIntent(Context paramContext, String paramString)
  {
    return a(b(paramContext, a(paramString)), paramContext);
  }
  
  public static Intent createPlayPlaylistIntent(Context paramContext, String paramString)
  {
    return a(b(paramContext, a(paramString).buildUpon().appendQueryParameter("playnext", "1").build()), paramContext);
  }
  
  public static Intent createPlayVideoIntent(Context paramContext, String paramString)
  {
    return createPlayVideoIntentWithOptions(paramContext, paramString, false, false);
  }
  
  public static Intent createPlayVideoIntentWithOptions(Context paramContext, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    String str = String.valueOf("https://www.youtube.com/watch?v=");
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {}
    for (paramString = str.concat(paramString);; paramString = new String(str)) {
      return b(paramContext, Uri.parse(paramString)).putExtra("force_fullscreen", paramBoolean1).putExtra("finish_on_ended", paramBoolean2);
    }
  }
  
  public static Intent createSearchIntent(Context paramContext, String paramString)
  {
    return a(new Intent("android.intent.action.SEARCH").setPackage(a(paramContext)).putExtra("query", paramString), paramContext);
  }
  
  public static Intent createUploadIntent(Context paramContext, Uri paramUri)
    throws IllegalArgumentException
  {
    if (paramUri == null) {
      throw new IllegalArgumentException("videoUri parameter cannot be null.");
    }
    if (!paramUri.toString().startsWith("content://media/")) {
      throw new IllegalArgumentException("videoUri parameter must be a URI pointing to a valid video file. It must begin with \"content://media/\"");
    }
    return a(new Intent("com.google.android.youtube.intent.action.UPLOAD").setPackage(a(paramContext)).setDataAndType(paramUri, "video/*"), paramContext);
  }
  
  public static Intent createUserIntent(Context paramContext, String paramString)
  {
    String str = String.valueOf("https://www.youtube.com/user/");
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {}
    for (paramString = str.concat(paramString);; paramString = new String(str)) {
      return b(paramContext, Uri.parse(paramString));
    }
  }
  
  public static int getInstalledYouTubeVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(a(paramContext), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return -1;
  }
  
  public static String getInstalledYouTubeVersionName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(a(paramContext), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static boolean isYouTubeInstalled(Context paramContext)
  {
    try
    {
      paramContext.getPackageManager().getApplicationInfo(a(paramContext), 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\youtube\player\YouTubeIntents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */