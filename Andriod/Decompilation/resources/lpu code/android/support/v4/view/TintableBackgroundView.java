package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.Nullable;

public abstract interface TintableBackgroundView
{
  @Nullable
  public abstract ColorStateList getSupportBackgroundTintList();
  
  @Nullable
  public abstract PorterDuff.Mode getSupportBackgroundTintMode();
  
  public abstract void setSupportBackgroundTintList(@Nullable ColorStateList paramColorStateList);
  
  public abstract void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode paramMode);
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\view\TintableBackgroundView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */