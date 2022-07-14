package me.leolin.shortcutbadger.impl;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import java.util.Collections;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.util.BroadcastHelper;

public class OPPOHomeBader
  implements Badger
{
  private static final String INTENT_ACTION = "com.oppo.unsettledevent";
  private static final String INTENT_EXTRA_BADGEUPGRADE_COUNT = "app_badge_count";
  private static final String INTENT_EXTRA_BADGE_COUNT = "number";
  private static final String INTENT_EXTRA_BADGE_UPGRADENUMBER = "upgradeNumber";
  private static final String INTENT_EXTRA_PACKAGENAME = "pakeageName";
  private static final String PROVIDER_CONTENT_URI = "content://com.android.badge/badge";
  private int mCurrentTotalCount = -1;
  
  private void executeBadgeByBroadcast(Context paramContext, ComponentName paramComponentName, int paramInt)
    throws ShortcutBadgeException
  {
    int i = paramInt;
    if (paramInt == 0) {
      i = -1;
    }
    Intent localIntent = new Intent("com.oppo.unsettledevent");
    localIntent.putExtra("pakeageName", paramComponentName.getPackageName());
    localIntent.putExtra("number", i);
    localIntent.putExtra("upgradeNumber", i);
    BroadcastHelper.sendIntentExplicitly(paramContext, localIntent);
  }
  
  @TargetApi(11)
  private void executeBadgeByContentProvider(Context paramContext, int paramInt)
    throws ShortcutBadgeException
  {
    try
    {
      Bundle localBundle = new Bundle();
      localBundle.putInt("app_badge_count", paramInt);
      paramContext.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", null, localBundle);
      return;
    }
    catch (Throwable paramContext)
    {
      throw new ShortcutBadgeException("Unable to execute Badge By Content Provider");
    }
  }
  
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt)
    throws ShortcutBadgeException
  {
    if (this.mCurrentTotalCount == paramInt) {
      return;
    }
    this.mCurrentTotalCount = paramInt;
    if (Build.VERSION.SDK_INT >= 11)
    {
      executeBadgeByContentProvider(paramContext, paramInt);
      return;
    }
    executeBadgeByBroadcast(paramContext, paramComponentName, paramInt);
  }
  
  public List<String> getSupportLaunchers()
  {
    return Collections.singletonList("com.oppo.launcher");
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\me\leolin\shortcutbadger\impl\OPPOHomeBader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */