package com.onesignal;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class OSObservable<ObserverType, StateType>
{
  private boolean fireOnMainThread;
  private String methodName;
  private List<Object> observers;
  
  OSObservable(String paramString, boolean paramBoolean)
  {
    this.methodName = paramString;
    this.fireOnMainThread = paramBoolean;
    this.observers = new ArrayList();
  }
  
  void addObserver(ObserverType paramObserverType)
  {
    this.observers.add(new WeakReference(paramObserverType));
  }
  
  void addObserverStrong(ObserverType paramObserverType)
  {
    this.observers.add(paramObserverType);
  }
  
  boolean notifyChange(final StateType paramStateType)
  {
    boolean bool = false;
    Iterator localIterator = this.observers.iterator();
    label138:
    label139:
    for (;;)
    {
      final Object localObject;
      if (localIterator.hasNext())
      {
        localObject = localIterator.next();
        if (!(localObject instanceof WeakReference)) {
          break label138;
        }
        localObject = ((WeakReference)localObject).get();
      }
      for (;;)
      {
        if (localObject == null) {
          break label139;
        }
        try
        {
          final Method localMethod = localObject.getClass().getDeclaredMethod(this.methodName, new Class[] { paramStateType.getClass() });
          localMethod.setAccessible(true);
          if (this.fireOnMainThread) {
            OSUtils.runOnMainUIThread(new Runnable()
            {
              public void run()
              {
                try
                {
                  localMethod.invoke(localObject, new Object[] { paramStateType });
                  return;
                }
                catch (Throwable localThrowable)
                {
                  ThrowableExtension.printStackTrace(localThrowable);
                }
              }
            });
          } else {
            localMethod.invoke(localObject, new Object[] { paramStateType });
          }
        }
        catch (Throwable localThrowable)
        {
          ThrowableExtension.printStackTrace(localThrowable);
        }
        break;
        return bool;
        bool = true;
        break;
      }
    }
  }
  
  void removeObserver(ObserverType paramObserverType)
  {
    int i = 0;
    for (;;)
    {
      if (i < this.observers.size())
      {
        if (((WeakReference)this.observers.get(i)).get().equals(paramObserverType)) {
          this.observers.remove(i);
        }
      }
      else {
        return;
      }
      i += 1;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OSObservable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */