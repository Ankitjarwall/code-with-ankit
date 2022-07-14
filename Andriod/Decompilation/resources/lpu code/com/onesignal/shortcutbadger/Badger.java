package com.onesignal.shortcutbadger;

import android.content.ComponentName;
import android.content.Context;
import java.util.List;

public abstract interface Badger
{
  public abstract void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt)
    throws ShortcutBadgeException;
  
  public abstract List<String> getSupportLaunchers();
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\shortcutbadger\Badger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */