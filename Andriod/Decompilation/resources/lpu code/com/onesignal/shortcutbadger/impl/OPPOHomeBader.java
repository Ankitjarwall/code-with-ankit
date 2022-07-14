package com.onesignal.shortcutbadger.impl;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.onesignal.shortcutbadger.Badger;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import com.onesignal.shortcutbadger.util.BroadcastHelper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

public class OPPOHomeBader
  implements Badger
{
  private static final String INTENT_ACTION = "com.oppo.unsettledevent";
  private static final String INTENT_EXTRA_BADGEUPGRADE_COUNT = "app_badge_count";
  private static final String INTENT_EXTRA_BADGE_COUNT = "number";
  private static final String INTENT_EXTRA_BADGE_UPGRADENUMBER = "upgradeNumber";
  private static final String INTENT_EXTRA_PACKAGENAME = "pakeageName";
  private static final String PROVIDER_CONTENT_URI = "content://com.android.badge/badge";
  private static int ROMVERSION = -1;
  
  private boolean checkObjExists(Object paramObject)
  {
    return (paramObject == null) || (paramObject.toString().equals("")) || (paramObject.toString().trim().equals("null"));
  }
  
  private Object executeClassLoad(Class paramClass, String paramString, Class[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramClass != null)
    {
      localObject1 = localObject2;
      if (!checkObjExists(paramString))
      {
        paramClass = getMethod(paramClass, paramString, paramArrayOfClass);
        localObject1 = localObject2;
        if (paramClass != null) {
          paramClass.setAccessible(true);
        }
      }
    }
    try
    {
      localObject1 = paramClass.invoke(null, paramArrayOfObject);
      return localObject1;
    }
    catch (IllegalAccessException paramClass)
    {
      ThrowableExtension.printStackTrace(paramClass);
      return null;
    }
    catch (InvocationTargetException paramClass)
    {
      ThrowableExtension.printStackTrace(paramClass);
    }
    return null;
  }
  
  private Class getClass(String paramString)
  {
    try
    {
      paramString = Class.forName(paramString);
      return paramString;
    }
    catch (ClassNotFoundException paramString) {}
    return null;
  }
  
  private Method getMethod(Class paramClass, String paramString, Class[] paramArrayOfClass)
  {
    if ((paramClass == null) || (checkObjExists(paramString))) {}
    do
    {
      return null;
      try
      {
        paramClass.getMethods();
        paramClass.getDeclaredMethods();
        Method localMethod1 = paramClass.getDeclaredMethod(paramString, paramArrayOfClass);
        return localMethod1;
      }
      catch (Exception localException1)
      {
        try
        {
          Method localMethod2 = paramClass.getMethod(paramString, paramArrayOfClass);
          return localMethod2;
        }
        catch (Exception localException2) {}
      }
    } while (paramClass.getSuperclass() == null);
    return getMethod(paramClass.getSuperclass(), paramString, paramArrayOfClass);
  }
  
  private int getSupportVersion()
  {
    if (ROMVERSION >= 0) {
      return ROMVERSION;
    }
    try
    {
      i = ((Integer)executeClassLoad(getClass("com.color.os.ColorBuild"), "getColorOSVERSION", null, null)).intValue();
      if (i == 0) {
        try
        {
          String str = getSystemProperty("ro.build.version.opporom");
          if (str.startsWith("V1.4")) {
            return 3;
          }
          if (str.startsWith("V2.0")) {
            return 4;
          }
          boolean bool = str.startsWith("V2.1");
          if (bool) {
            return 5;
          }
        }
        catch (Exception localException1) {}
      }
      ROMVERSION = i;
      return ROMVERSION;
    }
    catch (Exception localException2)
    {
      for (;;)
      {
        int i = 0;
      }
    }
  }
  
  /* Error */
  private String getSystemProperty(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore_2
    //   4: new 144	java/io/BufferedReader
    //   7: dup
    //   8: new 146	java/io/InputStreamReader
    //   11: dup
    //   12: invokestatic 152	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   15: new 154	java/lang/StringBuilder
    //   18: dup
    //   19: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   22: ldc -99
    //   24: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: aload_1
    //   28: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   34: invokevirtual 166	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   37: invokevirtual 172	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   40: invokespecial 175	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   43: sipush 1024
    //   46: invokespecial 178	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   49: astore_1
    //   50: aload_1
    //   51: invokevirtual 181	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   54: astore_2
    //   55: aload_1
    //   56: invokevirtual 184	java/io/BufferedReader:close	()V
    //   59: aload_1
    //   60: invokestatic 190	com/onesignal/shortcutbadger/util/CloseHelper:closeQuietly	(Ljava/io/Closeable;)V
    //   63: aload_2
    //   64: areturn
    //   65: astore_1
    //   66: aload_2
    //   67: astore_1
    //   68: aload_1
    //   69: invokestatic 190	com/onesignal/shortcutbadger/util/CloseHelper:closeQuietly	(Ljava/io/Closeable;)V
    //   72: aconst_null
    //   73: areturn
    //   74: astore_2
    //   75: aload_3
    //   76: astore_1
    //   77: aload_1
    //   78: invokestatic 190	com/onesignal/shortcutbadger/util/CloseHelper:closeQuietly	(Ljava/io/Closeable;)V
    //   81: aload_2
    //   82: athrow
    //   83: astore_2
    //   84: goto -7 -> 77
    //   87: astore_2
    //   88: goto -20 -> 68
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	this	OPPOHomeBader
    //   0	91	1	paramString	String
    //   3	64	2	str	String
    //   74	8	2	localObject1	Object
    //   83	1	2	localObject2	Object
    //   87	1	2	localIOException	java.io.IOException
    //   1	75	3	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   4	50	65	java/io/IOException
    //   4	50	74	finally
    //   50	59	83	finally
    //   50	59	87	java/io/IOException
  }
  
  @TargetApi(11)
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt)
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
    if (BroadcastHelper.canResolveBroadcast(paramContext, localIntent)) {
      paramContext.sendBroadcast(localIntent);
    }
    while (getSupportVersion() != 6) {
      return;
    }
    try
    {
      paramComponentName = new Bundle();
      paramComponentName.putInt("app_badge_count", i);
      paramContext.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", null, paramComponentName);
      return;
    }
    catch (Throwable paramContext)
    {
      throw new ShortcutBadgeException("unable to resolve intent: " + localIntent.toString());
    }
  }
  
  public List<String> getSupportLaunchers()
  {
    return Collections.singletonList("com.oppo.launcher");
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\shortcutbadger\impl\OPPOHomeBader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */