package android.arch.lifecycle;

abstract interface FullLifecycleObserver
  extends LifecycleObserver
{
  public abstract void onCreate(LifecycleOwner paramLifecycleOwner);
  
  public abstract void onDestroy(LifecycleOwner paramLifecycleOwner);
  
  public abstract void onPause(LifecycleOwner paramLifecycleOwner);
  
  public abstract void onResume(LifecycleOwner paramLifecycleOwner);
  
  public abstract void onStart(LifecycleOwner paramLifecycleOwner);
  
  public abstract void onStop(LifecycleOwner paramLifecycleOwner);
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\arch\lifecycle\FullLifecycleObserver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */