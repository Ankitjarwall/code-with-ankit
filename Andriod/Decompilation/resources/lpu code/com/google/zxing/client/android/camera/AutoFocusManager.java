package com.google.zxing.client.android.camera;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.preference.PreferenceManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;

final class AutoFocusManager
  implements Camera.AutoFocusCallback
{
  private static final long AUTO_FOCUS_INTERVAL_MS = 2000L;
  private static final Collection<String> FOCUS_MODES_CALLING_AF;
  private static final String TAG = AutoFocusManager.class.getSimpleName();
  private final Camera camera;
  private boolean focusing;
  private AsyncTask<?, ?, ?> outstandingTask;
  private boolean stopped;
  private final boolean useAutoFocus;
  
  static
  {
    FOCUS_MODES_CALLING_AF = new ArrayList(2);
    FOCUS_MODES_CALLING_AF.add("auto");
    FOCUS_MODES_CALLING_AF.add("macro");
  }
  
  AutoFocusManager(Context paramContext, Camera paramCamera)
  {
    this.camera = paramCamera;
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    paramCamera = paramCamera.getParameters().getFocusMode();
    if ((paramContext.getBoolean("preferences_auto_focus", true)) && (FOCUS_MODES_CALLING_AF.contains(paramCamera))) {}
    for (;;)
    {
      this.useAutoFocus = bool;
      Log.i(TAG, "Current focus mode '" + paramCamera + "'; use auto focus? " + this.useAutoFocus);
      start();
      return;
      bool = false;
    }
  }
  
  /* Error */
  private void autoFocusAgainLater()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 124	com/google/zxing/client/android/camera/AutoFocusManager:stopped	Z
    //   6: ifne +37 -> 43
    //   9: aload_0
    //   10: getfield 126	com/google/zxing/client/android/camera/AutoFocusManager:outstandingTask	Landroid/os/AsyncTask;
    //   13: ifnonnull +30 -> 43
    //   16: new 10	com/google/zxing/client/android/camera/AutoFocusManager$AutoFocusTask
    //   19: dup
    //   20: aload_0
    //   21: aconst_null
    //   22: invokespecial 129	com/google/zxing/client/android/camera/AutoFocusManager$AutoFocusTask:<init>	(Lcom/google/zxing/client/android/camera/AutoFocusManager;Lcom/google/zxing/client/android/camera/AutoFocusManager$1;)V
    //   25: astore_1
    //   26: aload_1
    //   27: getstatic 135	android/os/AsyncTask:THREAD_POOL_EXECUTOR	Ljava/util/concurrent/Executor;
    //   30: iconst_0
    //   31: anewarray 4	java/lang/Object
    //   34: invokevirtual 139	com/google/zxing/client/android/camera/AutoFocusManager$AutoFocusTask:executeOnExecutor	(Ljava/util/concurrent/Executor;[Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   37: pop
    //   38: aload_0
    //   39: aload_1
    //   40: putfield 126	com/google/zxing/client/android/camera/AutoFocusManager:outstandingTask	Landroid/os/AsyncTask;
    //   43: aload_0
    //   44: monitorexit
    //   45: return
    //   46: astore_1
    //   47: getstatic 39	com/google/zxing/client/android/camera/AutoFocusManager:TAG	Ljava/lang/String;
    //   50: ldc -115
    //   52: aload_1
    //   53: invokestatic 145	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   56: pop
    //   57: goto -14 -> 43
    //   60: astore_1
    //   61: aload_0
    //   62: monitorexit
    //   63: aload_1
    //   64: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	65	0	this	AutoFocusManager
    //   25	15	1	localAutoFocusTask	AutoFocusTask
    //   46	7	1	localRejectedExecutionException	java.util.concurrent.RejectedExecutionException
    //   60	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   26	43	46	java/util/concurrent/RejectedExecutionException
    //   2	26	60	finally
    //   26	43	60	finally
    //   47	57	60	finally
  }
  
  private void cancelOutstandingTask()
  {
    try
    {
      if (this.outstandingTask != null)
      {
        if (this.outstandingTask.getStatus() != AsyncTask.Status.FINISHED) {
          this.outstandingTask.cancel(true);
        }
        this.outstandingTask = null;
      }
      return;
    }
    finally {}
  }
  
  public void onAutoFocus(boolean paramBoolean, Camera paramCamera)
  {
    try
    {
      this.focusing = false;
      autoFocusAgainLater();
      return;
    }
    finally
    {
      paramCamera = finally;
      throw paramCamera;
    }
  }
  
  /* Error */
  void start()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 93	com/google/zxing/client/android/camera/AutoFocusManager:useAutoFocus	Z
    //   6: ifeq +37 -> 43
    //   9: aload_0
    //   10: aconst_null
    //   11: putfield 126	com/google/zxing/client/android/camera/AutoFocusManager:outstandingTask	Landroid/os/AsyncTask;
    //   14: aload_0
    //   15: getfield 124	com/google/zxing/client/android/camera/AutoFocusManager:stopped	Z
    //   18: ifne +25 -> 43
    //   21: aload_0
    //   22: getfield 164	com/google/zxing/client/android/camera/AutoFocusManager:focusing	Z
    //   25: istore_1
    //   26: iload_1
    //   27: ifne +16 -> 43
    //   30: aload_0
    //   31: getfield 63	com/google/zxing/client/android/camera/AutoFocusManager:camera	Landroid/hardware/Camera;
    //   34: aload_0
    //   35: invokevirtual 172	android/hardware/Camera:autoFocus	(Landroid/hardware/Camera$AutoFocusCallback;)V
    //   38: aload_0
    //   39: iconst_1
    //   40: putfield 164	com/google/zxing/client/android/camera/AutoFocusManager:focusing	Z
    //   43: aload_0
    //   44: monitorexit
    //   45: return
    //   46: astore_2
    //   47: getstatic 39	com/google/zxing/client/android/camera/AutoFocusManager:TAG	Ljava/lang/String;
    //   50: ldc -82
    //   52: aload_2
    //   53: invokestatic 145	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   56: pop
    //   57: aload_0
    //   58: invokespecial 166	com/google/zxing/client/android/camera/AutoFocusManager:autoFocusAgainLater	()V
    //   61: goto -18 -> 43
    //   64: astore_2
    //   65: aload_0
    //   66: monitorexit
    //   67: aload_2
    //   68: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	69	0	this	AutoFocusManager
    //   25	2	1	bool	boolean
    //   46	7	2	localRuntimeException	RuntimeException
    //   64	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   30	43	46	java/lang/RuntimeException
    //   2	26	64	finally
    //   30	43	64	finally
    //   47	61	64	finally
  }
  
  /* Error */
  void stop()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_1
    //   4: putfield 124	com/google/zxing/client/android/camera/AutoFocusManager:stopped	Z
    //   7: aload_0
    //   8: getfield 93	com/google/zxing/client/android/camera/AutoFocusManager:useAutoFocus	Z
    //   11: ifeq +14 -> 25
    //   14: aload_0
    //   15: invokespecial 177	com/google/zxing/client/android/camera/AutoFocusManager:cancelOutstandingTask	()V
    //   18: aload_0
    //   19: getfield 63	com/google/zxing/client/android/camera/AutoFocusManager:camera	Landroid/hardware/Camera;
    //   22: invokevirtual 180	android/hardware/Camera:cancelAutoFocus	()V
    //   25: aload_0
    //   26: monitorexit
    //   27: return
    //   28: astore_1
    //   29: getstatic 39	com/google/zxing/client/android/camera/AutoFocusManager:TAG	Ljava/lang/String;
    //   32: ldc -74
    //   34: aload_1
    //   35: invokestatic 145	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   38: pop
    //   39: goto -14 -> 25
    //   42: astore_1
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_1
    //   46: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	47	0	this	AutoFocusManager
    //   28	7	1	localRuntimeException	RuntimeException
    //   42	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   18	25	28	java/lang/RuntimeException
    //   2	18	42	finally
    //   18	25	42	finally
    //   29	39	42	finally
  }
  
  private final class AutoFocusTask
    extends AsyncTask<Object, Object, Object>
  {
    private AutoFocusTask() {}
    
    protected Object doInBackground(Object... paramVarArgs)
    {
      try
      {
        Thread.sleep(2000L);
        AutoFocusManager.this.start();
        return null;
      }
      catch (InterruptedException paramVarArgs)
      {
        for (;;) {}
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\camera\AutoFocusManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */