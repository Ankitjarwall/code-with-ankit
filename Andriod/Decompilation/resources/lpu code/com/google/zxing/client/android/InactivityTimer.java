package com.google.zxing.client.android;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

final class InactivityTimer
{
  private static final long INACTIVITY_DELAY_MS = 300000L;
  private static final String TAG = InactivityTimer.class.getSimpleName();
  private final Activity activity;
  private AsyncTask<Object, Object, Object> inactivityTask;
  private final BroadcastReceiver powerStatusReceiver;
  private boolean registered;
  
  InactivityTimer(Activity paramActivity)
  {
    this.activity = paramActivity;
    this.powerStatusReceiver = new PowerStatusReceiver(null);
    this.registered = false;
    onActivity();
  }
  
  private void cancel()
  {
    try
    {
      AsyncTask localAsyncTask = this.inactivityTask;
      if (localAsyncTask != null)
      {
        localAsyncTask.cancel(true);
        this.inactivityTask = null;
      }
      return;
    }
    finally {}
  }
  
  void onActivity()
  {
    try
    {
      cancel();
      this.inactivityTask = new InactivityAsyncTask(null);
      this.inactivityTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  void onPause()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 59	com/google/zxing/client/android/InactivityTimer:cancel	()V
    //   6: aload_0
    //   7: getfield 51	com/google/zxing/client/android/InactivityTimer:registered	Z
    //   10: ifeq +22 -> 32
    //   13: aload_0
    //   14: getfield 44	com/google/zxing/client/android/InactivityTimer:activity	Landroid/app/Activity;
    //   17: aload_0
    //   18: getfield 49	com/google/zxing/client/android/InactivityTimer:powerStatusReceiver	Landroid/content/BroadcastReceiver;
    //   21: invokevirtual 85	android/app/Activity:unregisterReceiver	(Landroid/content/BroadcastReceiver;)V
    //   24: aload_0
    //   25: iconst_0
    //   26: putfield 51	com/google/zxing/client/android/InactivityTimer:registered	Z
    //   29: aload_0
    //   30: monitorexit
    //   31: return
    //   32: getstatic 37	com/google/zxing/client/android/InactivityTimer:TAG	Ljava/lang/String;
    //   35: ldc 87
    //   37: invokestatic 93	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   40: pop
    //   41: goto -12 -> 29
    //   44: astore_1
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_1
    //   48: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	49	0	this	InactivityTimer
    //   44	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	29	44	finally
    //   32	41	44	finally
  }
  
  /* Error */
  void onResume()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 51	com/google/zxing/client/android/InactivityTimer:registered	Z
    //   6: ifeq +19 -> 25
    //   9: getstatic 37	com/google/zxing/client/android/InactivityTimer:TAG	Ljava/lang/String;
    //   12: ldc 96
    //   14: invokestatic 93	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   17: pop
    //   18: aload_0
    //   19: invokevirtual 54	com/google/zxing/client/android/InactivityTimer:onActivity	()V
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: aload_0
    //   26: getfield 44	com/google/zxing/client/android/InactivityTimer:activity	Landroid/app/Activity;
    //   29: aload_0
    //   30: getfield 49	com/google/zxing/client/android/InactivityTimer:powerStatusReceiver	Landroid/content/BroadcastReceiver;
    //   33: new 98	android/content/IntentFilter
    //   36: dup
    //   37: ldc 100
    //   39: invokespecial 103	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   42: invokevirtual 107	android/app/Activity:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   45: pop
    //   46: aload_0
    //   47: iconst_1
    //   48: putfield 51	com/google/zxing/client/android/InactivityTimer:registered	Z
    //   51: goto -33 -> 18
    //   54: astore_1
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_1
    //   58: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	59	0	this	InactivityTimer
    //   54	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	18	54	finally
    //   18	22	54	finally
    //   25	51	54	finally
  }
  
  void shutdown()
  {
    cancel();
  }
  
  private final class InactivityAsyncTask
    extends AsyncTask<Object, Object, Object>
  {
    private InactivityAsyncTask() {}
    
    protected Object doInBackground(Object... paramVarArgs)
    {
      try
      {
        Thread.sleep(300000L);
        Log.i(InactivityTimer.TAG, "Finishing activity due to inactivity");
        InactivityTimer.this.activity.finish();
        return null;
      }
      catch (InterruptedException paramVarArgs)
      {
        for (;;) {}
      }
    }
  }
  
  private final class PowerStatusReceiver
    extends BroadcastReceiver
  {
    private PowerStatusReceiver() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ("android.intent.action.BATTERY_CHANGED".equals(paramIntent.getAction())) {
        if (paramIntent.getIntExtra("plugged", -1) > 0) {
          break label36;
        }
      }
      label36:
      for (int i = 1; i != 0; i = 0)
      {
        InactivityTimer.this.onActivity();
        return;
      }
      InactivityTimer.this.cancel();
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\InactivityTimer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */