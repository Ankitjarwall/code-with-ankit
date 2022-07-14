package com.onesignal;

import android.content.Context;
import com.amazon.device.messaging.ADM;

public class PushRegistratorADM
  implements PushRegistrator
{
  private static boolean callbackSuccessful = false;
  private static PushRegistrator.RegisteredHandler registeredCallback;
  
  public static void fireCallback(String paramString)
  {
    if (registeredCallback == null) {
      return;
    }
    callbackSuccessful = true;
    registeredCallback.complete(paramString, 1);
  }
  
  public void registerForPush(final Context paramContext, String paramString, final PushRegistrator.RegisteredHandler paramRegisteredHandler)
  {
    registeredCallback = paramRegisteredHandler;
    new Thread(new Runnable()
    {
      public void run()
      {
        ADM localADM = new ADM(paramContext);
        String str = localADM.getRegistrationId();
        if (str == null) {
          localADM.startRegister();
        }
        try
        {
          for (;;)
          {
            Thread.sleep(30000L);
            if (!PushRegistratorADM.callbackSuccessful)
            {
              OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "com.onesignal.ADMMessageHandler timed out, please check that your have the receiver, service, and your package name matches(NOTE: Case Sensitive) per the OneSignal instructions.");
              PushRegistratorADM.fireCallback(null);
            }
            return;
            OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "ADM Already registered with ID:" + str);
            paramRegisteredHandler.complete(str, 1);
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          for (;;) {}
        }
      }
    }).start();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\PushRegistratorADM.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */