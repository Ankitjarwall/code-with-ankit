package android.arch.lifecycle;

import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class CompositeGeneratedAdaptersObserver
  implements GenericLifecycleObserver
{
  private final GeneratedAdapter[] mGeneratedAdapters;
  
  CompositeGeneratedAdaptersObserver(GeneratedAdapter[] paramArrayOfGeneratedAdapter)
  {
    this.mGeneratedAdapters = paramArrayOfGeneratedAdapter;
  }
  
  public void onStateChanged(LifecycleOwner paramLifecycleOwner, Lifecycle.Event paramEvent)
  {
    int j = 0;
    MethodCallsLogger localMethodCallsLogger = new MethodCallsLogger();
    GeneratedAdapter[] arrayOfGeneratedAdapter = this.mGeneratedAdapters;
    int k = arrayOfGeneratedAdapter.length;
    int i = 0;
    while (i < k)
    {
      arrayOfGeneratedAdapter[i].callMethods(paramLifecycleOwner, paramEvent, false, localMethodCallsLogger);
      i += 1;
    }
    arrayOfGeneratedAdapter = this.mGeneratedAdapters;
    k = arrayOfGeneratedAdapter.length;
    i = j;
    while (i < k)
    {
      arrayOfGeneratedAdapter[i].callMethods(paramLifecycleOwner, paramEvent, true, localMethodCallsLogger);
      i += 1;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\arch\lifecycle\CompositeGeneratedAdaptersObserver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */