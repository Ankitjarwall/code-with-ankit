package android.arch.lifecycle;

import android.support.annotation.Nullable;

public abstract interface Observer<T>
{
  public abstract void onChanged(@Nullable T paramT);
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\arch\lifecycle\Observer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */