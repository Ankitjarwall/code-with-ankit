package com.onesignal.shortcutbadger.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import java.util.List;

public class BroadcastHelper
{
  public static boolean canResolveBroadcast(Context paramContext, Intent paramIntent)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().queryBroadcastReceivers(paramIntent, 0);
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramContext.size() > 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\shortcutbadger\util\BroadcastHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */