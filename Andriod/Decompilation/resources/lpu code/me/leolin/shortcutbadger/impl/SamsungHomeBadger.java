package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.util.CloseHelper;

public class SamsungHomeBadger
  implements Badger
{
  private static final String[] CONTENT_PROJECTION = { "_id", "class" };
  private static final String CONTENT_URI = "content://com.sec.badge/apps?notify=true";
  private DefaultBadger defaultBadger;
  
  public SamsungHomeBadger()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      this.defaultBadger = new DefaultBadger();
    }
  }
  
  private ContentValues getContentValues(ComponentName paramComponentName, int paramInt, boolean paramBoolean)
  {
    ContentValues localContentValues = new ContentValues();
    if (paramBoolean)
    {
      localContentValues.put("package", paramComponentName.getPackageName());
      localContentValues.put("class", paramComponentName.getClassName());
    }
    localContentValues.put("badgecount", Integer.valueOf(paramInt));
    return localContentValues;
  }
  
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt)
    throws ShortcutBadgeException
  {
    if ((this.defaultBadger != null) && (this.defaultBadger.isSupported(paramContext)))
    {
      this.defaultBadger.executeBadge(paramContext, paramComponentName, paramInt);
      return;
    }
    Uri localUri = Uri.parse("content://com.sec.badge/apps?notify=true");
    ContentResolver localContentResolver = paramContext.getContentResolver();
    paramContext = null;
    try
    {
      Cursor localCursor = localContentResolver.query(localUri, CONTENT_PROJECTION, "package=?", new String[] { paramComponentName.getPackageName() }, null);
      if (localCursor != null)
      {
        paramContext = localCursor;
        String str = paramComponentName.getClassName();
        int i = 0;
        for (;;)
        {
          paramContext = localCursor;
          if (!localCursor.moveToNext()) {
            break;
          }
          paramContext = localCursor;
          int j = localCursor.getInt(0);
          paramContext = localCursor;
          localContentResolver.update(localUri, getContentValues(paramComponentName, paramInt, false), "_id=?", new String[] { String.valueOf(j) });
          paramContext = localCursor;
          if (str.equals(localCursor.getString(localCursor.getColumnIndex("class")))) {
            i = 1;
          }
        }
        if (i == 0)
        {
          paramContext = localCursor;
          localContentResolver.insert(localUri, getContentValues(paramComponentName, paramInt, true));
        }
      }
      return;
    }
    finally
    {
      CloseHelper.close(paramContext);
    }
  }
  
  public List<String> getSupportLaunchers()
  {
    return Arrays.asList(new String[] { "com.sec.android.app.launcher", "com.sec.android.app.twlauncher" });
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\me\leolin\shortcutbadger\impl\SamsungHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */