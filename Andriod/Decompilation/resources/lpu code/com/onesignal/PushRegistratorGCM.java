package com.onesignal;

import com.google.android.gms.gcm.GoogleCloudMessaging;

class PushRegistratorGCM
  extends PushRegistratorAbstractGoogle
{
  String getProviderName()
  {
    return "GCM";
  }
  
  String getToken(String paramString)
    throws Throwable
  {
    return GoogleCloudMessaging.getInstance(OneSignal.appContext).register(new String[] { paramString });
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\PushRegistratorGCM.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */