package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;

public class VivoHomeBadger
  implements Badger
{
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt)
    throws ShortcutBadgeException
  {
    Intent localIntent = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
    localIntent.putExtra("packageName", paramContext.getPackageName());
    localIntent.putExtra("className", paramComponentName.getClassName());
    localIntent.putExtra("notificationNum", paramInt);
    paramContext.sendBroadcast(localIntent);
  }
  
  public List<String> getSupportLaunchers()
  {
    return Arrays.asList(new String[] { "com.vivo.launcher" });
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\me\leolin\shortcutbadger\impl\VivoHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */