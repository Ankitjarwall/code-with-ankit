package android.support.v4.media;

import android.media.browse.MediaBrowser.MediaItem;
import android.support.annotation.RequiresApi;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RequiresApi(21)
class ParceledListSliceAdapterApi21
{
  private static Constructor sConstructor;
  
  static
  {
    try
    {
      sConstructor = Class.forName("android.content.pm.ParceledListSlice").getConstructor(new Class[] { List.class });
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      ThrowableExtension.printStackTrace(localClassNotFoundException);
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;) {}
    }
  }
  
  static Object newInstance(List<MediaBrowser.MediaItem> paramList)
  {
    try
    {
      paramList = sConstructor.newInstance(new Object[] { paramList });
      return paramList;
    }
    catch (InstantiationException paramList)
    {
      ThrowableExtension.printStackTrace(paramList);
      return null;
    }
    catch (IllegalAccessException paramList)
    {
      for (;;) {}
    }
    catch (InvocationTargetException paramList)
    {
      for (;;) {}
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\media\ParceledListSliceAdapterApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */