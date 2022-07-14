package com.onesignal.shortcutbadger.impl;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.onesignal.shortcutbadger.Badger;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import java.util.Collections;
import java.util.List;

public class ZukHomeBadger
  implements Badger
{
  private final Uri CONTENT_URI = Uri.parse("content://com.android.badge/badge");
  
  @TargetApi(11)
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt)
    throws ShortcutBadgeException
  {
    paramComponentName = new Bundle();
    paramComponentName.putInt("app_badge_count", paramInt);
    paramContext.getContentResolver().call(this.CONTENT_URI, "setAppBadgeCount", null, paramComponentName);
  }
  
  public List<String> getSupportLaunchers()
  {
    return Collections.singletonList("com.zui.launcher");
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\shortcutbadger\impl\ZukHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */