package com.onesignal;

class OSSubscriptionChangedInternalObserver
{
  static void fireChangesToPublicObserver(OSSubscriptionState paramOSSubscriptionState)
  {
    OSSubscriptionStateChanges localOSSubscriptionStateChanges = new OSSubscriptionStateChanges();
    localOSSubscriptionStateChanges.from = OneSignal.lastSubscriptionState;
    localOSSubscriptionStateChanges.to = ((OSSubscriptionState)paramOSSubscriptionState.clone());
    if (OneSignal.getSubscriptionStateChangesObserver().notifyChange(localOSSubscriptionStateChanges))
    {
      OneSignal.lastSubscriptionState = (OSSubscriptionState)paramOSSubscriptionState.clone();
      OneSignal.lastSubscriptionState.persistAsFrom();
    }
  }
  
  public void changed(OSSubscriptionState paramOSSubscriptionState)
  {
    fireChangesToPublicObserver(paramOSSubscriptionState);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OSSubscriptionChangedInternalObserver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */