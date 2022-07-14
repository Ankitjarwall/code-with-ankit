package com.onesignal;

import android.content.Context;
import android.content.Intent;
import com.amazon.device.messaging.ADMMessageHandlerBase;
import com.amazon.device.messaging.ADMMessageReceiver;

public class ADMMessageHandler
  extends ADMMessageHandlerBase
{
  public ADMMessageHandler()
  {
    super("ADMMessageHandler");
  }
  
  protected void onMessage(Intent paramIntent)
  {
    Object localObject = getApplicationContext();
    paramIntent = paramIntent.getExtras();
    if (NotificationBundleProcessor.processBundleFromReceiver((Context)localObject, paramIntent).processed()) {
      return;
    }
    localObject = new NotificationGenerationJob((Context)localObject);
    ((NotificationGenerationJob)localObject).jsonPayload = NotificationBundleProcessor.bundleAsJSONObject(paramIntent);
    NotificationBundleProcessor.ProcessJobForDisplay((NotificationGenerationJob)localObject);
  }
  
  protected void onRegistered(String paramString)
  {
    OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "ADM registration ID: " + paramString);
    PushRegistratorADM.fireCallback(paramString);
  }
  
  protected void onRegistrationError(String paramString)
  {
    OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "ADM:onRegistrationError: " + paramString);
    if ("INVALID_SENDER".equals(paramString)) {
      OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Please double check that you have a matching package name (NOTE: Case Sensitive), api_key.txt, and the apk was signed with the same Keystore and Alias.");
    }
    PushRegistratorADM.fireCallback(null);
  }
  
  protected void onUnregistered(String paramString)
  {
    OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "ADM:onUnregistered: " + paramString);
  }
  
  public static class Receiver
    extends ADMMessageReceiver
  {
    public Receiver()
    {
      super();
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\ADMMessageHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */