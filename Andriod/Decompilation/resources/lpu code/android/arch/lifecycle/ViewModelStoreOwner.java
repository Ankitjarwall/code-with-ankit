package android.arch.lifecycle;

import android.support.annotation.NonNull;

public abstract interface ViewModelStoreOwner
{
  @NonNull
  public abstract ViewModelStore getViewModelStore();
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\arch\lifecycle\ViewModelStoreOwner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */