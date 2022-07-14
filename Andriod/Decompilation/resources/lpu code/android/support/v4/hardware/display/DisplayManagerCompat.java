package android.support.v4.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.WindowManager;
import java.util.WeakHashMap;

public abstract class DisplayManagerCompat
{
  public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
  private static final WeakHashMap<Context, DisplayManagerCompat> sInstances = new WeakHashMap();
  
  @NonNull
  public static DisplayManagerCompat getInstance(@NonNull Context paramContext)
  {
    synchronized (sInstances)
    {
      DisplayManagerCompat localDisplayManagerCompat = (DisplayManagerCompat)sInstances.get(paramContext);
      Object localObject = localDisplayManagerCompat;
      if (localDisplayManagerCompat == null)
      {
        if (Build.VERSION.SDK_INT >= 17)
        {
          localObject = new DisplayManagerCompatApi17Impl(paramContext);
          sInstances.put(paramContext, localObject);
        }
      }
      else {
        return (DisplayManagerCompat)localObject;
      }
      localObject = new DisplayManagerCompatApi14Impl(paramContext);
    }
  }
  
  @Nullable
  public abstract Display getDisplay(int paramInt);
  
  @NonNull
  public abstract Display[] getDisplays();
  
  @NonNull
  public abstract Display[] getDisplays(String paramString);
  
  private static class DisplayManagerCompatApi14Impl
    extends DisplayManagerCompat
  {
    private final WindowManager mWindowManager;
    
    DisplayManagerCompatApi14Impl(Context paramContext)
    {
      this.mWindowManager = ((WindowManager)paramContext.getSystemService("window"));
    }
    
    public Display getDisplay(int paramInt)
    {
      Display localDisplay = this.mWindowManager.getDefaultDisplay();
      if (localDisplay.getDisplayId() == paramInt) {
        return localDisplay;
      }
      return null;
    }
    
    public Display[] getDisplays()
    {
      return new Display[] { this.mWindowManager.getDefaultDisplay() };
    }
    
    public Display[] getDisplays(String paramString)
    {
      if (paramString == null) {
        return getDisplays();
      }
      return new Display[0];
    }
  }
  
  @RequiresApi(17)
  private static class DisplayManagerCompatApi17Impl
    extends DisplayManagerCompat
  {
    private final DisplayManager mDisplayManager;
    
    DisplayManagerCompatApi17Impl(Context paramContext)
    {
      this.mDisplayManager = ((DisplayManager)paramContext.getSystemService("display"));
    }
    
    public Display getDisplay(int paramInt)
    {
      return this.mDisplayManager.getDisplay(paramInt);
    }
    
    public Display[] getDisplays()
    {
      return this.mDisplayManager.getDisplays();
    }
    
    public Display[] getDisplays(String paramString)
    {
      return this.mDisplayManager.getDisplays(paramString);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\hardware\display\DisplayManagerCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */