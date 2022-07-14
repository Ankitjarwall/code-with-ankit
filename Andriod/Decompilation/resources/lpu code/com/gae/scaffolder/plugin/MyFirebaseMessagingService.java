package com.gae.scaffolder.plugin;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.messaging.RemoteMessage.Notification;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MyFirebaseMessagingService
  extends FirebaseMessagingService
{
  private static final String TAG = "FCMPlugin";
  
  private void sendNotification(String paramString1, String paramString2, Map<String, Object> paramMap)
  {
    Object localObject = new Intent(this, FCMPluginActivity.class);
    ((Intent)localObject).addFlags(67108864);
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      ((Intent)localObject).putExtra(str, paramMap.get(str).toString());
    }
    paramMap = PendingIntent.getActivity(this, 0, (Intent)localObject, 1073741824);
    localObject = RingtoneManager.getDefaultUri(2);
    paramString1 = new NotificationCompat.Builder(this).setSmallIcon(getApplicationInfo().icon).setContentTitle(paramString1).setContentText(paramString2).setAutoCancel(true).setSound((Uri)localObject).setContentIntent(paramMap);
    ((NotificationManager)getSystemService("notification")).notify(0, paramString1.build());
  }
  
  public void onMessageReceived(RemoteMessage paramRemoteMessage)
  {
    Log.d("FCMPlugin", "==> MyFirebaseMessagingService onMessageReceived");
    if (paramRemoteMessage.getNotification() != null)
    {
      Log.d("FCMPlugin", "\tNotification Title: " + paramRemoteMessage.getNotification().getTitle());
      Log.d("FCMPlugin", "\tNotification Message: " + paramRemoteMessage.getNotification().getBody());
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("wasTapped", Boolean.valueOf(false));
    Iterator localIterator = paramRemoteMessage.getData().keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject = paramRemoteMessage.getData().get(str);
      Log.d("FCMPlugin", "\tKey: " + str + " Value: " + localObject);
      localHashMap.put(str, localObject);
    }
    Log.d("FCMPlugin", "\tNotification Data: " + localHashMap.toString());
    FCMPlugin.sendPushPayload(localHashMap);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\gae\scaffolder\plugin\MyFirebaseMessagingService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */