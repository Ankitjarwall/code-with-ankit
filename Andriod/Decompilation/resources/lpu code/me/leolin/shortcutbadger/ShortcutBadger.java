package me.leolin.shortcutbadger;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import me.leolin.shortcutbadger.impl.AdwHomeBadger;
import me.leolin.shortcutbadger.impl.ApexHomeBadger;
import me.leolin.shortcutbadger.impl.AsusHomeBadger;
import me.leolin.shortcutbadger.impl.DefaultBadger;
import me.leolin.shortcutbadger.impl.EverythingMeHomeBadger;
import me.leolin.shortcutbadger.impl.HuaweiHomeBadger;
import me.leolin.shortcutbadger.impl.NewHtcHomeBadger;
import me.leolin.shortcutbadger.impl.NovaHomeBadger;
import me.leolin.shortcutbadger.impl.OPPOHomeBader;
import me.leolin.shortcutbadger.impl.SamsungHomeBadger;
import me.leolin.shortcutbadger.impl.SonyHomeBadger;
import me.leolin.shortcutbadger.impl.VivoHomeBadger;
import me.leolin.shortcutbadger.impl.ZTEHomeBadger;
import me.leolin.shortcutbadger.impl.ZukHomeBadger;

public final class ShortcutBadger
{
  private static final List<Class<? extends Badger>> BADGERS = new LinkedList();
  private static final String LOG_TAG = "ShortcutBadger";
  private static final int SUPPORTED_CHECK_ATTEMPTS = 3;
  private static ComponentName sComponentName;
  private static final Object sCounterSupportedLock = new Object();
  private static volatile Boolean sIsBadgeCounterSupported;
  private static Badger sShortcutBadger;
  
  static
  {
    BADGERS.add(AdwHomeBadger.class);
    BADGERS.add(ApexHomeBadger.class);
    BADGERS.add(DefaultBadger.class);
    BADGERS.add(NewHtcHomeBadger.class);
    BADGERS.add(NovaHomeBadger.class);
    BADGERS.add(SonyHomeBadger.class);
    BADGERS.add(AsusHomeBadger.class);
    BADGERS.add(HuaweiHomeBadger.class);
    BADGERS.add(OPPOHomeBader.class);
    BADGERS.add(SamsungHomeBadger.class);
    BADGERS.add(ZukHomeBadger.class);
    BADGERS.add(VivoHomeBadger.class);
    BADGERS.add(ZTEHomeBadger.class);
    BADGERS.add(EverythingMeHomeBadger.class);
  }
  
  public static boolean applyCount(Context paramContext, int paramInt)
  {
    try
    {
      applyCountOrThrow(paramContext, paramInt);
      return true;
    }
    catch (ShortcutBadgeException paramContext)
    {
      if (Log.isLoggable("ShortcutBadger", 3)) {
        Log.d("ShortcutBadger", "Unable to execute badge", paramContext);
      }
    }
    return false;
  }
  
  public static void applyCountOrThrow(Context paramContext, int paramInt)
    throws ShortcutBadgeException
  {
    if ((sShortcutBadger == null) && (!initBadger(paramContext))) {
      throw new ShortcutBadgeException("No default launcher available");
    }
    try
    {
      sShortcutBadger.executeBadge(paramContext, sComponentName, paramInt);
      return;
    }
    catch (Exception paramContext)
    {
      throw new ShortcutBadgeException("Unable to execute badge", paramContext);
    }
  }
  
  public static void applyNotification(Context paramContext, Notification paramNotification, int paramInt)
  {
    if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {}
    try
    {
      paramContext = paramNotification.getClass().getDeclaredField("extraNotification").get(paramNotification);
      paramContext.getClass().getDeclaredMethod("setMessageCount", new Class[] { Integer.TYPE }).invoke(paramContext, new Object[] { Integer.valueOf(paramInt) });
      return;
    }
    catch (Exception paramContext)
    {
      while (!Log.isLoggable("ShortcutBadger", 3)) {}
      Log.d("ShortcutBadger", "Unable to execute badge", paramContext);
    }
  }
  
  private static boolean initBadger(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    if (localObject == null)
    {
      Log.e("ShortcutBadger", "Unable to find launch intent for package " + paramContext.getPackageName());
      return false;
    }
    sComponentName = ((Intent)localObject).getComponent();
    localObject = new Intent("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    Iterator localIterator1 = paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 65536).iterator();
    do
    {
      if (!localIterator1.hasNext()) {
        break;
      }
      String str = ((ResolveInfo)localIterator1.next()).activityInfo.packageName;
      Iterator localIterator2 = BADGERS.iterator();
      while (localIterator2.hasNext())
      {
        localObject = (Class)localIterator2.next();
        paramContext = null;
        try
        {
          localObject = (Badger)((Class)localObject).newInstance();
          paramContext = (Context)localObject;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
        if ((paramContext != null) && (paramContext.getSupportLaunchers().contains(str))) {
          sShortcutBadger = paramContext;
        }
      }
    } while (sShortcutBadger == null);
    if (sShortcutBadger == null)
    {
      if (!Build.MANUFACTURER.equalsIgnoreCase("ZUK")) {
        break label213;
      }
      sShortcutBadger = new ZukHomeBadger();
    }
    for (;;)
    {
      return true;
      label213:
      if (Build.MANUFACTURER.equalsIgnoreCase("OPPO")) {
        sShortcutBadger = new OPPOHomeBader();
      } else if (Build.MANUFACTURER.equalsIgnoreCase("VIVO")) {
        sShortcutBadger = new VivoHomeBadger();
      } else if (Build.MANUFACTURER.equalsIgnoreCase("ZTE")) {
        sShortcutBadger = new ZTEHomeBadger();
      } else {
        sShortcutBadger = new DefaultBadger();
      }
    }
  }
  
  public static boolean isBadgeCounterSupported(Context paramContext)
  {
    if (sIsBadgeCounterSupported == null) {}
    synchronized (sCounterSupportedLock)
    {
      Object localObject1 = sIsBadgeCounterSupported;
      int i;
      if (localObject1 == null)
      {
        localObject1 = null;
        i = 0;
        if (i >= 3) {}
      }
      try
      {
        Log.i("ShortcutBadger", "Checking if platform supports badge counters, attempt " + String.format("%d/%d.", new Object[] { Integer.valueOf(i + 1), Integer.valueOf(3) }));
        if (initBadger(paramContext))
        {
          sShortcutBadger.executeBadge(paramContext, sComponentName, 0);
          sIsBadgeCounterSupported = Boolean.valueOf(true);
          Log.i("ShortcutBadger", "Badge counter is supported in this platform.");
          if (sIsBadgeCounterSupported == null)
          {
            Log.w("ShortcutBadger", "Badge counter seems not supported for this platform: " + (String)localObject1);
            sIsBadgeCounterSupported = Boolean.valueOf(false);
          }
          return sIsBadgeCounterSupported.booleanValue();
        }
        localObject1 = "Failed to initialize the badge counter.";
      }
      catch (Exception localException)
      {
        for (;;)
        {
          String str = localException.getMessage();
        }
      }
      i += 1;
    }
  }
  
  public static boolean removeCount(Context paramContext)
  {
    return applyCount(paramContext, 0);
  }
  
  public static void removeCountOrThrow(Context paramContext)
    throws ShortcutBadgeException
  {
    applyCountOrThrow(paramContext, 0);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\me\leolin\shortcutbadger\ShortcutBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */