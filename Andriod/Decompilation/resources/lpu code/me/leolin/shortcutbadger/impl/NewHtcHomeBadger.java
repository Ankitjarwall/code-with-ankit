package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Collections;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.util.BroadcastHelper;

public class NewHtcHomeBadger
  implements Badger
{
  public static final String COUNT = "count";
  public static final String EXTRA_COMPONENT = "com.htc.launcher.extra.COMPONENT";
  public static final String EXTRA_COUNT = "com.htc.launcher.extra.COUNT";
  public static final String INTENT_SET_NOTIFICATION = "com.htc.launcher.action.SET_NOTIFICATION";
  public static final String INTENT_UPDATE_SHORTCUT = "com.htc.launcher.action.UPDATE_SHORTCUT";
  public static final String PACKAGENAME = "packagename";
  
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt)
    throws ShortcutBadgeException
  {
    Intent localIntent2 = new Intent("com.htc.launcher.action.SET_NOTIFICATION");
    localIntent2.putExtra("com.htc.launcher.extra.COMPONENT", paramComponentName.flattenToShortString());
    localIntent2.putExtra("com.htc.launcher.extra.COUNT", paramInt);
    Intent localIntent1 = new Intent("com.htc.launcher.action.UPDATE_SHORTCUT");
    localIntent1.putExtra("packagename", paramComponentName.getPackageName());
    localIntent1.putExtra("count", paramInt);
    int i;
    try
    {
      BroadcastHelper.sendIntentExplicitly(paramContext, localIntent2);
      paramInt = 1;
    }
    catch (ShortcutBadgeException paramComponentName)
    {
      for (;;)
      {
        label81:
        paramInt = 0;
      }
    }
    try
    {
      BroadcastHelper.sendIntentExplicitly(paramContext, localIntent1);
      i = 1;
    }
    catch (ShortcutBadgeException paramContext)
    {
      i = 0;
      break label81;
    }
    if ((paramInt == 0) && (i == 0)) {
      throw new ShortcutBadgeException("unable to resolve intent: " + localIntent1.toString());
    }
  }
  
  public List<String> getSupportLaunchers()
  {
    return Collections.singletonList("com.htc.launcher");
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\me\leolin\shortcutbadger\impl\NewHtcHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */