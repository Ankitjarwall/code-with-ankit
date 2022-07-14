package com.onesignal.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.onesignal.shortcutbadger.Badger;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import com.onesignal.shortcutbadger.util.BroadcastHelper;
import java.util.Arrays;
import java.util.List;

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
    Intent localIntent1 = new Intent("com.htc.launcher.action.SET_NOTIFICATION");
    localIntent1.putExtra("com.htc.launcher.extra.COMPONENT", paramComponentName.flattenToShortString());
    localIntent1.putExtra("com.htc.launcher.extra.COUNT", paramInt);
    Intent localIntent2 = new Intent("com.htc.launcher.action.UPDATE_SHORTCUT");
    localIntent2.putExtra("packagename", paramComponentName.getPackageName());
    localIntent2.putExtra("count", paramInt);
    if ((BroadcastHelper.canResolveBroadcast(paramContext, localIntent1)) || (BroadcastHelper.canResolveBroadcast(paramContext, localIntent2)))
    {
      paramContext.sendBroadcast(localIntent1);
      paramContext.sendBroadcast(localIntent2);
      return;
    }
    throw new ShortcutBadgeException("unable to resolve intent: " + localIntent2.toString());
  }
  
  public List<String> getSupportLaunchers()
  {
    return Arrays.asList(new String[] { "com.htc.launcher" });
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\shortcutbadger\impl\NewHtcHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */