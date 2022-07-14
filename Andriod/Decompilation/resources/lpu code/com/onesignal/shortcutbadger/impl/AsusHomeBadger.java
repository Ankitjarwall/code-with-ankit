package com.onesignal.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.onesignal.shortcutbadger.Badger;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import com.onesignal.shortcutbadger.util.BroadcastHelper;
import java.util.Arrays;
import java.util.List;

public class AsusHomeBadger
  implements Badger
{
  private static final String INTENT_ACTION = "android.intent.action.BADGE_COUNT_UPDATE";
  private static final String INTENT_EXTRA_ACTIVITY_NAME = "badge_count_class_name";
  private static final String INTENT_EXTRA_BADGE_COUNT = "badge_count";
  private static final String INTENT_EXTRA_PACKAGENAME = "badge_count_package_name";
  
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt)
    throws ShortcutBadgeException
  {
    Intent localIntent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
    localIntent.putExtra("badge_count", paramInt);
    localIntent.putExtra("badge_count_package_name", paramComponentName.getPackageName());
    localIntent.putExtra("badge_count_class_name", paramComponentName.getClassName());
    localIntent.putExtra("badge_vip_count", 0);
    if (BroadcastHelper.canResolveBroadcast(paramContext, localIntent))
    {
      paramContext.sendBroadcast(localIntent);
      return;
    }
    throw new ShortcutBadgeException("unable to resolve intent: " + localIntent.toString());
  }
  
  public List<String> getSupportLaunchers()
  {
    return Arrays.asList(new String[] { "com.asus.launcher" });
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\shortcutbadger\impl\AsusHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */