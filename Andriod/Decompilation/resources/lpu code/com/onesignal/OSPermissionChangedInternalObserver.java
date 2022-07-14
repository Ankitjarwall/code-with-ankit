package com.onesignal;

class OSPermissionChangedInternalObserver
{
  static void fireChangesToPublicObserver(OSPermissionState paramOSPermissionState)
  {
    OSPermissionStateChanges localOSPermissionStateChanges = new OSPermissionStateChanges();
    localOSPermissionStateChanges.from = OneSignal.lastPermissionState;
    localOSPermissionStateChanges.to = ((OSPermissionState)paramOSPermissionState.clone());
    if (OneSignal.getPermissionStateChangesObserver().notifyChange(localOSPermissionStateChanges))
    {
      OneSignal.lastPermissionState = (OSPermissionState)paramOSPermissionState.clone();
      OneSignal.lastPermissionState.persistAsFrom();
    }
  }
  
  static void handleInternalChanges(OSPermissionState paramOSPermissionState)
  {
    if (!paramOSPermissionState.getEnabled()) {
      BadgeCountUpdater.updateCount(0, OneSignal.appContext);
    }
    OneSignalStateSynchronizer.setPermission(OneSignal.areNotificationsEnabledForSubscribedState());
  }
  
  void changed(OSPermissionState paramOSPermissionState)
  {
    handleInternalChanges(paramOSPermissionState);
    fireChangesToPublicObserver(paramOSPermissionState);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OSPermissionChangedInternalObserver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */