package com.onesignal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;

class AdvertisingIdProviderGPS
  implements AdvertisingIdentifierProvider
{
  private static String lastValue;
  
  static String getLastValue()
  {
    return lastValue;
  }
  
  public String getIdentifier(Context paramContext)
  {
    try
    {
      paramContext = AdvertisingIdClient.getAdvertisingIdInfo(paramContext);
      if (paramContext.isLimitAdTrackingEnabled()) {}
      for (lastValue = "OptedOut";; lastValue = paramContext.getId()) {
        return lastValue;
      }
      return null;
    }
    catch (Throwable paramContext)
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "Error getting Google Ad id: ", paramContext);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\AdvertisingIdProviderGPS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */