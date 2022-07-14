package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.util.BroadcastHelper;

public class AdwHomeBadger
  implements Badger
{
  public static final String CLASSNAME = "CNAME";
  public static final String COUNT = "COUNT";
  public static final String INTENT_UPDATE_COUNTER = "org.adw.launcher.counter.SEND";
  public static final String PACKAGENAME = "PNAME";
  
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt)
    throws ShortcutBadgeException
  {
    Intent localIntent = new Intent("org.adw.launcher.counter.SEND");
    localIntent.putExtra("PNAME", paramComponentName.getPackageName());
    localIntent.putExtra("CNAME", paramComponentName.getClassName());
    localIntent.putExtra("COUNT", paramInt);
    BroadcastHelper.sendIntentExplicitly(paramContext, localIntent);
  }
  
  public List<String> getSupportLaunchers()
  {
    return Arrays.asList(new String[] { "org.adw.launcher", "org.adwfreak.launcher" });
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\me\leolin\shortcutbadger\impl\AdwHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */