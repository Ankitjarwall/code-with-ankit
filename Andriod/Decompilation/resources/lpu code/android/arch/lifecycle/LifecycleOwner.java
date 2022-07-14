package android.arch.lifecycle;

import android.support.annotation.NonNull;

public abstract interface LifecycleOwner
{
  @NonNull
  public abstract Lifecycle getLifecycle();
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\arch\lifecycle\LifecycleOwner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */