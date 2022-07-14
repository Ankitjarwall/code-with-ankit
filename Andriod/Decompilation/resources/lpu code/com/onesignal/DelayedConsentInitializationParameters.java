package com.onesignal;

import android.content.Context;

class DelayedConsentInitializationParameters
{
  public String appId;
  public Context context;
  public String googleProjectNumber;
  public OneSignal.NotificationOpenedHandler openedHandler;
  public OneSignal.NotificationReceivedHandler receivedHandler;
  
  DelayedConsentInitializationParameters(Context paramContext, String paramString1, String paramString2, OneSignal.NotificationOpenedHandler paramNotificationOpenedHandler, OneSignal.NotificationReceivedHandler paramNotificationReceivedHandler)
  {
    this.context = paramContext;
    this.googleProjectNumber = paramString1;
    this.appId = paramString2;
    this.openedHandler = paramNotificationOpenedHandler;
    this.receivedHandler = paramNotificationReceivedHandler;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\DelayedConsentInitializationParameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */