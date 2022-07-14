package android.arch.lifecycle;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ViewModelStore
{
  private final HashMap<String, ViewModel> mMap = new HashMap();
  
  public final void clear()
  {
    Iterator localIterator = this.mMap.values().iterator();
    while (localIterator.hasNext()) {
      ((ViewModel)localIterator.next()).onCleared();
    }
    this.mMap.clear();
  }
  
  final ViewModel get(String paramString)
  {
    return (ViewModel)this.mMap.get(paramString);
  }
  
  final void put(String paramString, ViewModel paramViewModel)
  {
    ViewModel localViewModel = (ViewModel)this.mMap.get(paramString);
    if (localViewModel != null) {
      localViewModel.onCleared();
    }
    this.mMap.put(paramString, paramViewModel);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\arch\lifecycle\ViewModelStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */