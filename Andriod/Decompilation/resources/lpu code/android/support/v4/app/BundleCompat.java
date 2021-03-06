package android.support.v4.app;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class BundleCompat
{
  @Nullable
  public static IBinder getBinder(@NonNull Bundle paramBundle, @Nullable String paramString)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      return paramBundle.getBinder(paramString);
    }
    return BundleCompatBaseImpl.getBinder(paramBundle, paramString);
  }
  
  public static void putBinder(@NonNull Bundle paramBundle, @Nullable String paramString, @Nullable IBinder paramIBinder)
  {
    if (Build.VERSION.SDK_INT >= 18)
    {
      paramBundle.putBinder(paramString, paramIBinder);
      return;
    }
    BundleCompatBaseImpl.putBinder(paramBundle, paramString, paramIBinder);
  }
  
  static class BundleCompatBaseImpl
  {
    private static final String TAG = "BundleCompatBaseImpl";
    private static Method sGetIBinderMethod;
    private static boolean sGetIBinderMethodFetched;
    private static Method sPutIBinderMethod;
    private static boolean sPutIBinderMethodFetched;
    
    public static IBinder getBinder(Bundle paramBundle, String paramString)
    {
      if (!sGetIBinderMethodFetched) {}
      try
      {
        sGetIBinderMethod = Bundle.class.getMethod("getIBinder", new Class[] { String.class });
        sGetIBinderMethod.setAccessible(true);
        sGetIBinderMethodFetched = true;
        if (sGetIBinderMethod == null) {}
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        for (;;)
        {
          try
          {
            paramBundle = (IBinder)sGetIBinderMethod.invoke(paramBundle, new Object[] { paramString });
            return paramBundle;
          }
          catch (IllegalAccessException paramBundle)
          {
            Log.i("BundleCompatBaseImpl", "Failed to invoke getIBinder via reflection", paramBundle);
            sGetIBinderMethod = null;
            return null;
          }
          catch (IllegalArgumentException paramBundle)
          {
            continue;
          }
          catch (InvocationTargetException paramBundle)
          {
            continue;
          }
          localNoSuchMethodException = localNoSuchMethodException;
          Log.i("BundleCompatBaseImpl", "Failed to retrieve getIBinder method", localNoSuchMethodException);
        }
      }
    }
    
    public static void putBinder(Bundle paramBundle, String paramString, IBinder paramIBinder)
    {
      if (!sPutIBinderMethodFetched) {}
      try
      {
        sPutIBinderMethod = Bundle.class.getMethod("putIBinder", new Class[] { String.class, IBinder.class });
        sPutIBinderMethod.setAccessible(true);
        sPutIBinderMethodFetched = true;
        if (sPutIBinderMethod == null) {}
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        for (;;)
        {
          try
          {
            sPutIBinderMethod.invoke(paramBundle, new Object[] { paramString, paramIBinder });
            return;
          }
          catch (IllegalAccessException paramBundle)
          {
            Log.i("BundleCompatBaseImpl", "Failed to invoke putIBinder via reflection", paramBundle);
            sPutIBinderMethod = null;
            return;
          }
          catch (IllegalArgumentException paramBundle)
          {
            continue;
          }
          catch (InvocationTargetException paramBundle)
          {
            continue;
          }
          localNoSuchMethodException = localNoSuchMethodException;
          Log.i("BundleCompatBaseImpl", "Failed to retrieve putIBinder method", localNoSuchMethodException);
        }
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\app\BundleCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */