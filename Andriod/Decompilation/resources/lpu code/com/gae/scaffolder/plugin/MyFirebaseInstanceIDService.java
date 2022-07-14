package com.gae.scaffolder.plugin;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService
  extends FirebaseInstanceIdService
{
  private static final String TAG = "FCMPlugin";
  
  public void onTokenRefresh()
  {
    String str = FirebaseInstanceId.getInstance().getToken();
    Log.d("FCMPlugin", "Refreshed token: " + str);
    FCMPlugin.sendTokenRefresh(str);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\gae\scaffolder\plugin\MyFirebaseInstanceIDService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */