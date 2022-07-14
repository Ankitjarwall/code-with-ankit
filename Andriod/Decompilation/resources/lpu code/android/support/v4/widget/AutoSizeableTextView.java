package android.support.v4.widget;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v4.os.BuildCompat;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public abstract interface AutoSizeableTextView
{
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static final boolean PLATFORM_SUPPORTS_AUTOSIZE = ;
  
  public abstract int getAutoSizeMaxTextSize();
  
  public abstract int getAutoSizeMinTextSize();
  
  public abstract int getAutoSizeStepGranularity();
  
  public abstract int[] getAutoSizeTextAvailableSizes();
  
  public abstract int getAutoSizeTextType();
  
  public abstract void setAutoSizeTextTypeUniformWithConfiguration(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IllegalArgumentException;
  
  public abstract void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] paramArrayOfInt, int paramInt)
    throws IllegalArgumentException;
  
  public abstract void setAutoSizeTextTypeWithDefaults(int paramInt);
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\widget\AutoSizeableTextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */