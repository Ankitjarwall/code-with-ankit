package me.leolin.shortcutbadger.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import me.leolin.shortcutbadger.ShortcutBadgeException;

public class BroadcastHelper
{
  public static List<ResolveInfo> resolveBroadcast(Context paramContext, Intent paramIntent)
  {
    paramContext = paramContext.getPackageManager().queryBroadcastReceivers(paramIntent, 0);
    if (paramContext != null) {
      return paramContext;
    }
    return Collections.emptyList();
  }
  
  public static void sendDefaultIntentExplicitly(Context paramContext, Intent paramIntent)
    throws ShortcutBadgeException
  {
    int i = 0;
    Intent localIntent;
    if (Build.VERSION.SDK_INT >= 26)
    {
      localIntent = new Intent(paramIntent);
      localIntent.setAction("me.leolin.shortcutbadger.BADGE_COUNT_UPDATE");
    }
    try
    {
      sendIntentExplicitly(paramContext, localIntent);
      i = 1;
    }
    catch (ShortcutBadgeException localShortcutBadgeException)
    {
      for (;;)
      {
        i = 0;
      }
      sendIntentExplicitly(paramContext, paramIntent);
    }
    if (i != 0) {
      return;
    }
  }
  
  public static void sendIntentExplicitly(Context paramContext, Intent paramIntent)
    throws ShortcutBadgeException
  {
    Object localObject = resolveBroadcast(paramContext, paramIntent);
    if (((List)localObject).size() == 0) {
      throw new ShortcutBadgeException("unable to resolve intent: " + paramIntent.toString());
    }
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      Intent localIntent = new Intent(paramIntent);
      if (localResolveInfo != null)
      {
        localIntent.setPackage(localResolveInfo.resolvePackageName);
        paramContext.sendBroadcast(localIntent);
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\me\leolin\shortcutbadger\util\BroadcastHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */