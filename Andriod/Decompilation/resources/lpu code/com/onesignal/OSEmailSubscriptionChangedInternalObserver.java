package com.onesignal;

class OSEmailSubscriptionChangedInternalObserver
{
  static void fireChangesToPublicObserver(OSEmailSubscriptionState paramOSEmailSubscriptionState)
  {
    OSEmailSubscriptionStateChanges localOSEmailSubscriptionStateChanges = new OSEmailSubscriptionStateChanges();
    localOSEmailSubscriptionStateChanges.from = OneSignal.lastEmailSubscriptionState;
    localOSEmailSubscriptionStateChanges.to = ((OSEmailSubscriptionState)paramOSEmailSubscriptionState.clone());
    if (OneSignal.getEmailSubscriptionStateChangesObserver().notifyChange(localOSEmailSubscriptionStateChanges))
    {
      OneSignal.lastEmailSubscriptionState = (OSEmailSubscriptionState)paramOSEmailSubscriptionState.clone();
      OneSignal.lastEmailSubscriptionState.persistAsFrom();
    }
  }
  
  void changed(OSEmailSubscriptionState paramOSEmailSubscriptionState)
  {
    fireChangesToPublicObserver(paramOSEmailSubscriptionState);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OSEmailSubscriptionChangedInternalObserver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */