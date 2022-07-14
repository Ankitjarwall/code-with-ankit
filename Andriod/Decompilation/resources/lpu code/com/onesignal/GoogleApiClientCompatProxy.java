package com.onesignal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Method;

class GoogleApiClientCompatProxy
{
  private final GoogleApiClient googleApiClient;
  private final Class googleApiClientListenerClass;
  
  GoogleApiClientCompatProxy(GoogleApiClient paramGoogleApiClient)
  {
    this.googleApiClient = paramGoogleApiClient;
    this.googleApiClientListenerClass = paramGoogleApiClient.getClass();
  }
  
  void connect()
  {
    try
    {
      this.googleApiClientListenerClass.getMethod("connect", new Class[0]).invoke(this.googleApiClient, new Object[0]);
      return;
    }
    catch (Throwable localThrowable)
    {
      ThrowableExtension.printStackTrace(localThrowable);
    }
  }
  
  void disconnect()
  {
    try
    {
      this.googleApiClientListenerClass.getMethod("disconnect", new Class[0]).invoke(this.googleApiClient, new Object[0]);
      return;
    }
    catch (Throwable localThrowable)
    {
      ThrowableExtension.printStackTrace(localThrowable);
    }
  }
  
  GoogleApiClient realInstance()
  {
    return this.googleApiClient;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\GoogleApiClientCompatProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */