package com.onesignal.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.onesignal.shortcutbadger.Badger;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import com.onesignal.shortcutbadger.util.BroadcastHelper;
import java.util.Arrays;
import java.util.List;

public class ApexHomeBadger
  implements Badger
{
  private static final String CLASS = "class";
  private static final String COUNT = "count";
  private static final String INTENT_UPDATE_COUNTER = "com.anddoes.launcher.COUNTER_CHANGED";
  private static final String PACKAGENAME = "package";
  
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt)
    throws ShortcutBadgeException
  {
    Intent localIntent = new Intent("com.anddoes.launcher.COUNTER_CHANGED");
    localIntent.putExtra("package", paramComponentName.getPackageName());
    localIntent.putExtra("count", paramInt);
    localIntent.putExtra("class", paramComponentName.getClassName());
    if (BroadcastHelper.canResolveBroadcast(paramContext, localIntent))
    {
      paramContext.sendBroadcast(localIntent);
      return;
    }
    throw new ShortcutBadgeException("unable to resolve intent: " + localIntent.toString());
  }
  
  public List<String> getSupportLaunchers()
  {
    return Arrays.asList(new String[] { "com.anddoes.launcher" });
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\shortcutbadger\impl\ApexHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */