package android.arch.lifecycle;

class ReflectiveGenericLifecycleObserver
  implements GenericLifecycleObserver
{
  private final ClassesInfoCache.CallbackInfo mInfo;
  private final Object mWrapped;
  
  ReflectiveGenericLifecycleObserver(Object paramObject)
  {
    this.mWrapped = paramObject;
    this.mInfo = ClassesInfoCache.sInstance.getInfo(this.mWrapped.getClass());
  }
  
  public void onStateChanged(LifecycleOwner paramLifecycleOwner, Lifecycle.Event paramEvent)
  {
    this.mInfo.invokeCallbacks(paramLifecycleOwner, paramEvent, this.mWrapped);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\arch\lifecycle\ReflectiveGenericLifecycleObserver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */