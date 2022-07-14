package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;

public class ZTEHomeBadger
  implements Badger
{
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt)
    throws ShortcutBadgeException
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("app_badge_count", paramInt);
    localBundle.putString("app_badge_component_name", paramComponentName.flattenToString());
    if (Build.VERSION.SDK_INT >= 11) {
      paramContext.getContentResolver().call(Uri.parse("content://com.android.launcher3.cornermark.unreadbadge"), "setAppUnreadCount", null, localBundle);
    }
  }
  
  public List<String> getSupportLaunchers()
  {
    return new ArrayList(0);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\me\leolin\shortcutbadger\impl\ZTEHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */