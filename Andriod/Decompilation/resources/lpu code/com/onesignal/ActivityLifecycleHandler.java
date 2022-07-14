package com.onesignal;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

class ActivityLifecycleHandler
{
  static Activity curActivity;
  static FocusHandlerThread focusHandlerThread = new FocusHandlerThread();
  private static ActivityAvailableListener mActivityAvailableListener;
  static boolean nextResumeIsFirstActivity;
  
  private static void handleFocus()
  {
    if ((focusHandlerThread.hasBackgrounded()) || (nextResumeIsFirstActivity))
    {
      nextResumeIsFirstActivity = false;
      focusHandlerThread.resetBackgroundState();
      OneSignal.onAppFocus();
      return;
    }
    focusHandlerThread.stopScheduledRunnable();
  }
  
  private static void handleLostFocus()
  {
    focusHandlerThread.runRunnable(new AppFocusRunnable(null));
  }
  
  private static void logCurActivity()
  {
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.DEBUG;
    StringBuilder localStringBuilder = new StringBuilder().append("curActivity is NOW: ");
    if (curActivity != null) {}
    for (String str = "" + curActivity.getClass().getName() + ":" + curActivity;; str = "null")
    {
      OneSignal.Log(localLOG_LEVEL, str);
      return;
    }
  }
  
  static void onActivityCreated(Activity paramActivity) {}
  
  static void onActivityDestroyed(Activity paramActivity)
  {
    OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "onActivityDestroyed: " + paramActivity.getClass().getName());
    if (paramActivity == curActivity)
    {
      curActivity = null;
      handleLostFocus();
    }
    logCurActivity();
  }
  
  static void onActivityPaused(Activity paramActivity)
  {
    if (paramActivity == curActivity)
    {
      curActivity = null;
      handleLostFocus();
    }
    logCurActivity();
  }
  
  static void onActivityResumed(Activity paramActivity)
  {
    setCurActivity(paramActivity);
    logCurActivity();
    handleFocus();
  }
  
  static void onActivityStarted(Activity paramActivity) {}
  
  static void onActivityStopped(Activity paramActivity)
  {
    OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "onActivityStopped: " + paramActivity.getClass().getName());
    if (paramActivity == curActivity)
    {
      curActivity = null;
      handleLostFocus();
    }
    logCurActivity();
  }
  
  public static void removeActivityAvailableListener(ActivityAvailableListener paramActivityAvailableListener)
  {
    mActivityAvailableListener = null;
  }
  
  static void setActivityAvailableListener(ActivityAvailableListener paramActivityAvailableListener)
  {
    if (curActivity != null)
    {
      paramActivityAvailableListener.available(curActivity);
      mActivityAvailableListener = paramActivityAvailableListener;
      return;
    }
    mActivityAvailableListener = paramActivityAvailableListener;
  }
  
  private static void setCurActivity(Activity paramActivity)
  {
    curActivity = paramActivity;
    if (mActivityAvailableListener != null) {
      mActivityAvailableListener.available(curActivity);
    }
  }
  
  static abstract interface ActivityAvailableListener
  {
    public abstract void available(Activity paramActivity);
  }
  
  private static class AppFocusRunnable
    implements Runnable
  {
    private boolean backgrounded;
    private boolean completed;
    
    public void run()
    {
      if (ActivityLifecycleHandler.curActivity != null) {
        return;
      }
      this.backgrounded = true;
      OneSignal.onAppLostFocus();
      this.completed = true;
    }
  }
  
  static class FocusHandlerThread
    extends HandlerThread
  {
    private ActivityLifecycleHandler.AppFocusRunnable appFocusRunnable;
    Handler mHandler = null;
    
    FocusHandlerThread()
    {
      super();
      start();
      this.mHandler = new Handler(getLooper());
    }
    
    Looper getHandlerLooper()
    {
      return this.mHandler.getLooper();
    }
    
    boolean hasBackgrounded()
    {
      return (this.appFocusRunnable != null) && (this.appFocusRunnable.backgrounded);
    }
    
    void resetBackgroundState()
    {
      if (this.appFocusRunnable != null) {
        ActivityLifecycleHandler.AppFocusRunnable.access$102(this.appFocusRunnable, false);
      }
    }
    
    void runRunnable(ActivityLifecycleHandler.AppFocusRunnable paramAppFocusRunnable)
    {
      if ((this.appFocusRunnable != null) && (this.appFocusRunnable.backgrounded) && (!this.appFocusRunnable.completed)) {
        return;
      }
      this.appFocusRunnable = paramAppFocusRunnable;
      this.mHandler.removeCallbacksAndMessages(null);
      this.mHandler.postDelayed(paramAppFocusRunnable, 2000L);
    }
    
    void stopScheduledRunnable()
    {
      this.mHandler.removeCallbacksAndMessages(null);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\ActivityLifecycleHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */