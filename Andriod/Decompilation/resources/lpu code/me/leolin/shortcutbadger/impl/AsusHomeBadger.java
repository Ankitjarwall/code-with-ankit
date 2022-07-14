package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.util.BroadcastHelper;

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
    BroadcastHelper.sendDefaultIntentExplicitly(paramContext, localIntent);
  }
  
  public List<String> getSupportLaunchers()
  {
    return Arrays.asList(new String[] { "com.asus.launcher" });
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\me\leolin\shortcutbadger\impl\AsusHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */