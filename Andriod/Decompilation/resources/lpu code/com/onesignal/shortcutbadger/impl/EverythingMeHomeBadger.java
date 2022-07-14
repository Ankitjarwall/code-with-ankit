package com.onesignal.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import com.onesignal.shortcutbadger.Badger;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import java.util.Arrays;
import java.util.List;

public class EverythingMeHomeBadger
  implements Badger
{
  private static final String COLUMN_ACTIVITY_NAME = "activity_name";
  private static final String COLUMN_COUNT = "count";
  private static final String COLUMN_PACKAGE_NAME = "package_name";
  private static final String CONTENT_URI = "content://me.everything.badger/apps";
  
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt)
    throws ShortcutBadgeException
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("package_name", paramComponentName.getPackageName());
    localContentValues.put("activity_name", paramComponentName.getClassName());
    localContentValues.put("count", Integer.valueOf(paramInt));
    paramContext.getContentResolver().insert(Uri.parse("content://me.everything.badger/apps"), localContentValues);
  }
  
  public List<String> getSupportLaunchers()
  {
    return Arrays.asList(new String[] { "me.everything.launcher" });
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\shortcutbadger\impl\EverythingMeHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */