package com.onesignal;

import android.content.Context;
import java.io.IOException;

abstract class PushRegistratorAbstractGoogle
  implements PushRegistrator
{
  private static int REGISTRATION_RETRY_BACKOFF_MS = 10000;
  private static int REGISTRATION_RETRY_COUNT = 5;
  private boolean firedCallback;
  private Thread registerThread;
  private PushRegistrator.RegisteredHandler registeredHandler;
  
  private boolean attemptRegistration(String paramString, int paramInt)
  {
    try
    {
      paramString = getToken(paramString);
      OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "Device registered, push token = " + paramString);
      this.registeredHandler.complete(paramString, 1);
      return true;
    }
    catch (IOException paramString)
    {
      while (!"SERVICE_NOT_AVAILABLE".equals(paramString.getMessage()))
      {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error Getting " + getProviderName() + " Token", paramString);
        if (!this.firedCallback)
        {
          this.registeredHandler.complete(null, -11);
          return true;
        }
      }
      if (paramInt >= REGISTRATION_RETRY_COUNT - 1) {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Retry count of " + REGISTRATION_RETRY_COUNT + " exceed! Could not get a " + getProviderName() + " Token.", paramString);
      }
      do
      {
        return false;
        OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "'Google Play services' returned SERVICE_NOT_AVAILABLE error. Current retry count: " + paramInt, paramString);
      } while (paramInt != 2);
      this.registeredHandler.complete(null, -9);
      this.firedCallback = true;
      return true;
    }
    catch (Throwable paramString)
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Unknown error getting " + getProviderName() + " Token", paramString);
      this.registeredHandler.complete(null, -12);
    }
    return true;
  }
  
  private void internalRegisterForPush(String paramString)
  {
    try
    {
      if (GooglePlayServicesUpgradePrompt.isGMSInstalledAndEnabled())
      {
        registerInBackground(paramString);
        return;
      }
      GooglePlayServicesUpgradePrompt.ShowUpdateGPSDialog();
      OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "'Google Play services' app not installed or disabled on the device.");
      this.registeredHandler.complete(null, -7);
      return;
    }
    catch (Throwable paramString)
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Could not register with " + getProviderName() + " due to an issue with your AndroidManifest.xml or with 'Google Play services'.", paramString);
      this.registeredHandler.complete(null, -8);
    }
  }
  
  private boolean isValidProjectNumber(String paramString, PushRegistrator.RegisteredHandler paramRegisteredHandler)
  {
    try
    {
      Float.parseFloat(paramString);
      i = 1;
    }
    catch (Throwable paramString)
    {
      for (;;)
      {
        int i = 0;
      }
    }
    if (i == 0)
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Missing Google Project number!\nPlease enter a Google Project number / Sender ID on under App Settings > Android > Configuration on the OneSignal dashboard.");
      paramRegisteredHandler.complete(null, -6);
      return false;
    }
    return true;
  }
  
  /* Error */
  private void registerInBackground(final String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 148	com/onesignal/PushRegistratorAbstractGoogle:registerThread	Ljava/lang/Thread;
    //   6: ifnull +18 -> 24
    //   9: aload_0
    //   10: getfield 148	com/onesignal/PushRegistratorAbstractGoogle:registerThread	Ljava/lang/Thread;
    //   13: invokevirtual 153	java/lang/Thread:isAlive	()Z
    //   16: istore_2
    //   17: iload_2
    //   18: ifeq +6 -> 24
    //   21: aload_0
    //   22: monitorexit
    //   23: return
    //   24: aload_0
    //   25: new 150	java/lang/Thread
    //   28: dup
    //   29: new 8	com/onesignal/PushRegistratorAbstractGoogle$1
    //   32: dup
    //   33: aload_0
    //   34: aload_1
    //   35: invokespecial 156	com/onesignal/PushRegistratorAbstractGoogle$1:<init>	(Lcom/onesignal/PushRegistratorAbstractGoogle;Ljava/lang/String;)V
    //   38: invokespecial 159	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   41: putfield 148	com/onesignal/PushRegistratorAbstractGoogle:registerThread	Ljava/lang/Thread;
    //   44: aload_0
    //   45: getfield 148	com/onesignal/PushRegistratorAbstractGoogle:registerThread	Ljava/lang/Thread;
    //   48: invokevirtual 162	java/lang/Thread:start	()V
    //   51: goto -30 -> 21
    //   54: astore_1
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_1
    //   58: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	59	0	this	PushRegistratorAbstractGoogle
    //   0	59	1	paramString	String
    //   16	2	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	17	54	finally
    //   24	51	54	finally
  }
  
  abstract String getProviderName();
  
  abstract String getToken(String paramString)
    throws Throwable;
  
  public void registerForPush(Context paramContext, String paramString, PushRegistrator.RegisteredHandler paramRegisteredHandler)
  {
    this.registeredHandler = paramRegisteredHandler;
    if (isValidProjectNumber(paramString, paramRegisteredHandler)) {
      internalRegisterForPush(paramString);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\PushRegistratorAbstractGoogle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */