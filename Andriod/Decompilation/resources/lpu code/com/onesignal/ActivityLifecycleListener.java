package com.onesignal;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

class ActivityLifecycleListener
  implements Application.ActivityLifecycleCallbacks
{
  public void onActivityCreated(Activity paramActivity, Bundle paramBundle)
  {
    ActivityLifecycleHandler.onActivityCreated(paramActivity);
  }
  
  public void onActivityDestroyed(Activity paramActivity)
  {
    ActivityLifecycleHandler.onActivityDestroyed(paramActivity);
  }
  
  public void onActivityPaused(Activity paramActivity)
  {
    ActivityLifecycleHandler.onActivityPaused(paramActivity);
  }
  
  public void onActivityResumed(Activity paramActivity)
  {
    ActivityLifecycleHandler.onActivityResumed(paramActivity);
  }
  
  public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
  
  public void onActivityStarted(Activity paramActivity)
  {
    ActivityLifecycleHandler.onActivityStarted(paramActivity);
  }
  
  public void onActivityStopped(Activity paramActivity)
  {
    ActivityLifecycleHandler.onActivityStopped(paramActivity);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\ActivityLifecycleListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */