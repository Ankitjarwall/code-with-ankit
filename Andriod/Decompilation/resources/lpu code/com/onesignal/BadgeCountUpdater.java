package com.onesignal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.onesignal.shortcutbadger.ShortcutBadger;

class BadgeCountUpdater
{
  private static int badgesEnabled = -1;
  
  private static boolean areBadgeSettingsEnabled(Context paramContext)
  {
    if (badgesEnabled != -1) {
      return badgesEnabled == 1;
    }
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData;
        if (paramContext == null) {
          continue;
        }
        if (!"DISABLE".equals(paramContext.getString("com.onesignal.BadgeCount"))) {
          continue;
        }
        i = 0;
        badgesEnabled = i;
      }
      catch (Throwable paramContext)
      {
        int i;
        badgesEnabled = 0;
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error reading meta-data tag 'com.onesignal.BadgeCount'. Disabling badge setting.", paramContext);
        continue;
      }
      if (badgesEnabled == 1) {
        break;
      }
      return false;
      i = 1;
      continue;
      badgesEnabled = 1;
    }
  }
  
  private static boolean areBadgesEnabled(Context paramContext)
  {
    return (areBadgeSettingsEnabled(paramContext)) && (OSUtils.areNotificationsEnabled(paramContext));
  }
  
  static void update(SQLiteDatabase paramSQLiteDatabase, Context paramContext)
  {
    if (!areBadgesEnabled(paramContext)) {
      return;
    }
    paramSQLiteDatabase = paramSQLiteDatabase.query("notification", null, "dismissed = 0 AND opened = 0 AND is_summary = 0 ", null, null, null, null);
    updateCount(paramSQLiteDatabase.getCount(), paramContext);
    paramSQLiteDatabase.close();
  }
  
  static void updateCount(int paramInt, Context paramContext)
  {
    if (!areBadgeSettingsEnabled(paramContext)) {
      return;
    }
    try
    {
      ShortcutBadger.applyCountOrThrow(paramContext, paramInt);
      return;
    }
    catch (Throwable paramContext) {}
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\BadgeCountUpdater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */