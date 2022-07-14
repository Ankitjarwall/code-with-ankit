package org.apache.cordova;

import android.content.Context;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Field;

public class BuildHelper
{
  private static String TAG = "BuildHelper";
  
  public static Object getBuildConfigValue(Context paramContext, String paramString)
  {
    try
    {
      paramContext = Class.forName(paramContext.getPackageName() + ".BuildConfig").getField(paramString).get(null);
      return paramContext;
    }
    catch (ClassNotFoundException paramContext)
    {
      LOG.d(TAG, "Unable to get the BuildConfig, is this built with ANT?");
      ThrowableExtension.printStackTrace(paramContext);
      return null;
    }
    catch (NoSuchFieldException paramContext)
    {
      LOG.d(TAG, paramString + " is not a valid field. Check your build.gradle");
      return null;
    }
    catch (IllegalAccessException paramContext)
    {
      LOG.d(TAG, "Illegal Access Exception: Let's print a stack trace.");
      ThrowableExtension.printStackTrace(paramContext);
    }
    return null;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\BuildHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */