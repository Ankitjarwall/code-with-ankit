package android.support.v4.view;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;

public final class WindowCompat
{
  public static final int FEATURE_ACTION_BAR = 8;
  public static final int FEATURE_ACTION_BAR_OVERLAY = 9;
  public static final int FEATURE_ACTION_MODE_OVERLAY = 10;
  
  @NonNull
  public static <T extends View> T requireViewById(@NonNull Window paramWindow, @IdRes int paramInt)
  {
    paramWindow = paramWindow.findViewById(paramInt);
    if (paramWindow == null) {
      throw new IllegalArgumentException("ID does not reference a View inside this Window");
    }
    return paramWindow;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\view\WindowCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */