package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;

public class NovaHomeBadger
  implements Badger
{
  private static final String CONTENT_URI = "content://com.teslacoilsw.notifier/unread_count";
  private static final String COUNT = "count";
  private static final String TAG = "tag";
  
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt)
    throws ShortcutBadgeException
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("tag", paramComponentName.getPackageName() + "/" + paramComponentName.getClassName());
    localContentValues.put("count", Integer.valueOf(paramInt));
    paramContext.getContentResolver().insert(Uri.parse("content://com.teslacoilsw.notifier/unread_count"), localContentValues);
  }
  
  public List<String> getSupportLaunchers()
  {
    return Arrays.asList(new String[] { "com.teslacoilsw.launcher" });
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\me\leolin\shortcutbadger\impl\NovaHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */