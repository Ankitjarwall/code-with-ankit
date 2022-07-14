package com.onesignal.shortcutbadger.impl;

import android.annotation.TargetApi;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import com.onesignal.shortcutbadger.Badger;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import com.onesignal.shortcutbadger.util.BroadcastHelper;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Deprecated
public class XiaomiHomeBadger
  implements Badger
{
  public static final String EXTRA_UPDATE_APP_COMPONENT_NAME = "android.intent.extra.update_application_component_name";
  public static final String EXTRA_UPDATE_APP_MSG_TEXT = "android.intent.extra.update_application_message_text";
  public static final String INTENT_ACTION = "android.intent.action.APPLICATION_MESSAGE_UPDATE";
  private ResolveInfo resolveInfo;
  
  @TargetApi(16)
  private void tryNewMiuiBadge(Context paramContext, int paramInt)
    throws ShortcutBadgeException
  {
    Object localObject1;
    if (this.resolveInfo == null)
    {
      localObject1 = new Intent("android.intent.action.MAIN");
      ((Intent)localObject1).addCategory("android.intent.category.HOME");
      this.resolveInfo = paramContext.getPackageManager().resolveActivity((Intent)localObject1, 65536);
    }
    if (this.resolveInfo != null)
    {
      localObject1 = (NotificationManager)paramContext.getSystemService("notification");
      paramContext = new Notification.Builder(paramContext).setContentTitle("").setContentText("").setSmallIcon(this.resolveInfo.getIconResource()).build();
    }
    try
    {
      Object localObject2 = paramContext.getClass().getDeclaredField("extraNotification").get(paramContext);
      localObject2.getClass().getDeclaredMethod("setMessageCount", new Class[] { Integer.TYPE }).invoke(localObject2, new Object[] { Integer.valueOf(paramInt) });
      ((NotificationManager)localObject1).notify(0, paramContext);
      return;
    }
    catch (Exception paramContext)
    {
      throw new ShortcutBadgeException("not able to set badge", paramContext);
    }
  }
  
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt)
    throws ShortcutBadgeException
  {
    Intent localIntent;
    try
    {
      Object localObject2 = Class.forName("android.app.MiuiNotification").newInstance();
      Field localField = localObject2.getClass().getDeclaredField("messageCount");
      localField.setAccessible(true);
      Object localObject1;
      if (paramInt == 0) {
        localObject1 = "";
      }
      try
      {
        for (;;)
        {
          localField.set(localObject2, String.valueOf(localObject1));
          if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
            tryNewMiuiBadge(paramContext, paramInt);
          }
          return;
          localObject1 = Integer.valueOf(paramInt);
        }
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          localField.set(localObject2, Integer.valueOf(paramInt));
        }
      }
      paramComponentName = "";
    }
    catch (Exception localException2)
    {
      localIntent = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
      localIntent.putExtra("android.intent.extra.update_application_component_name", paramComponentName.getPackageName() + "/" + paramComponentName.getClassName());
      if (paramInt != 0) {}
    }
    for (;;)
    {
      localIntent.putExtra("android.intent.extra.update_application_message_text", String.valueOf(paramComponentName));
      if (!BroadcastHelper.canResolveBroadcast(paramContext, localIntent)) {
        break;
      }
      paramContext.sendBroadcast(localIntent);
      break;
      paramComponentName = Integer.valueOf(paramInt);
    }
  }
  
  public List<String> getSupportLaunchers()
  {
    return Arrays.asList(new String[] { "com.miui.miuilite", "com.miui.home", "com.miui.miuihome", "com.miui.miuihome2", "com.miui.mihome", "com.miui.mihome2", "com.i.miui.launcher" });
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\shortcutbadger\impl\XiaomiHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */