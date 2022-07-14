package com.onesignal.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.onesignal.shortcutbadger.Badger;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import com.onesignal.shortcutbadger.util.BroadcastHelper;
import java.util.Arrays;
import java.util.List;

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
    if (BroadcastHelper.canResolveBroadcast(paramContext, localIntent))
    {
      paramContext.sendBroadcast(localIntent);
      return;
    }
    throw new ShortcutBadgeException("unable to resolve intent: " + localIntent.toString());
  }
  
  public List<String> getSupportLaunchers()
  {
    return Arrays.asList(new String[] { "org.adw.launcher", "org.adwfreak.launcher" });
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\shortcutbadger\impl\AdwHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */