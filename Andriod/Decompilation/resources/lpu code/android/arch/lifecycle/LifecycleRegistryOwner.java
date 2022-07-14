package android.arch.lifecycle;

import android.support.annotation.NonNull;

@Deprecated
public abstract interface LifecycleRegistryOwner
  extends LifecycleOwner
{
  @NonNull
  public abstract LifecycleRegistry getLifecycle();
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\arch\lifecycle\LifecycleRegistryOwner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */