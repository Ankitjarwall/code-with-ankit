package com.onesignal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions.Builder;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

class PushRegistratorFCM
  extends PushRegistratorAbstractGoogle
{
  private static final String FCM_APP_NAME = "ONESIGNAL_SDK_FCM_APP_NAME";
  private FirebaseApp firebaseApp;
  
  static void disableFirebaseInstanceIdService(Context paramContext)
  {
    int i = 1;
    if (OSUtils.getResourceString(paramContext, "gcm_defaultSenderId", null) == null) {
      i = 2;
    }
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      localPackageManager.setComponentEnabledSetting(new ComponentName(paramContext, FirebaseInstanceIdService.class), i, 1);
      return;
    }
    catch (NoClassDefFoundError paramContext) {}
  }
  
  private void initFirebaseApp(String paramString)
  {
    if (this.firebaseApp != null) {
      return;
    }
    paramString = new FirebaseOptions.Builder().setGcmSenderId(paramString).setApplicationId("OMIT_ID").setApiKey("OMIT_KEY").build();
    this.firebaseApp = FirebaseApp.initializeApp(OneSignal.appContext, paramString, "ONESIGNAL_SDK_FCM_APP_NAME");
  }
  
  String getProviderName()
  {
    return "FCM";
  }
  
  String getToken(String paramString)
    throws Throwable
  {
    initFirebaseApp(paramString);
    return FirebaseInstanceId.getInstance(this.firebaseApp).getToken(paramString, "FCM");
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\PushRegistratorFCM.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */