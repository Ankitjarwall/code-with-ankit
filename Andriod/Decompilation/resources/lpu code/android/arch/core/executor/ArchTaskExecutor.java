package android.arch.core.executor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.util.concurrent.Executor;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class ArchTaskExecutor
  extends TaskExecutor
{
  @NonNull
  private static final Executor sIOThreadExecutor = new Executor()
  {
    public void execute(Runnable paramAnonymousRunnable)
    {
      ArchTaskExecutor.getInstance().executeOnDiskIO(paramAnonymousRunnable);
    }
  };
  private static volatile ArchTaskExecutor sInstance;
  @NonNull
  private static final Executor sMainThreadExecutor = new Executor()
  {
    public void execute(Runnable paramAnonymousRunnable)
    {
      ArchTaskExecutor.getInstance().postToMainThread(paramAnonymousRunnable);
    }
  };
  @NonNull
  private TaskExecutor mDefaultTaskExecutor = new DefaultTaskExecutor();
  @NonNull
  private TaskExecutor mDelegate = this.mDefaultTaskExecutor;
  
  @NonNull
  public static Executor getIOThreadExecutor()
  {
    return sIOThreadExecutor;
  }
  
  @NonNull
  public static ArchTaskExecutor getInstance()
  {
    if (sInstance != null) {
      return sInstance;
    }
    try
    {
      if (sInstance == null) {
        sInstance = new ArchTaskExecutor();
      }
      return sInstance;
    }
    finally {}
  }
  
  @NonNull
  public static Executor getMainThreadExecutor()
  {
    return sMainThreadExecutor;
  }
  
  public void executeOnDiskIO(Runnable paramRunnable)
  {
    this.mDelegate.executeOnDiskIO(paramRunnable);
  }
  
  public boolean isMainThread()
  {
    return this.mDelegate.isMainThread();
  }
  
  public void postToMainThread(Runnable paramRunnable)
  {
    this.mDelegate.postToMainThread(paramRunnable);
  }
  
  public void setDelegate(@Nullable TaskExecutor paramTaskExecutor)
  {
    TaskExecutor localTaskExecutor = paramTaskExecutor;
    if (paramTaskExecutor == null) {
      localTaskExecutor = this.mDefaultTaskExecutor;
    }
    this.mDelegate = localTaskExecutor;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\arch\core\executor\ArchTaskExecutor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */