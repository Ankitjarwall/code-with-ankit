package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.zzf;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirebaseMessaging
{
  public static final String INSTANCE_ID_SCOPE = "FCM";
  private static final Pattern zzclM = Pattern.compile("[a-zA-Z0-9-_.~%]{1,900}");
  private static FirebaseMessaging zzclN;
  private final FirebaseInstanceId zzclk;
  
  private FirebaseMessaging(FirebaseInstanceId paramFirebaseInstanceId)
  {
    this.zzclk = paramFirebaseInstanceId;
  }
  
  public static FirebaseMessaging getInstance()
  {
    try
    {
      if (zzclN == null) {
        zzclN = new FirebaseMessaging(FirebaseInstanceId.getInstance());
      }
      FirebaseMessaging localFirebaseMessaging = zzclN;
      return localFirebaseMessaging;
    }
    finally {}
  }
  
  public void send(RemoteMessage paramRemoteMessage)
  {
    if (TextUtils.isEmpty(paramRemoteMessage.getTo())) {
      throw new IllegalArgumentException("Missing 'to'");
    }
    Context localContext = FirebaseApp.getInstance().getApplicationContext();
    String str = zzf.zzbA(localContext);
    if (str == null)
    {
      Log.e("FirebaseMessaging", "Google Play services package is missing. Impossible to send message");
      return;
    }
    Intent localIntent = new Intent("com.google.android.gcm.intent.SEND");
    zzf.zzf(localContext, localIntent);
    localIntent.setPackage(str);
    paramRemoteMessage.zzK(localIntent);
    localContext.sendOrderedBroadcast(localIntent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
  }
  
  public void subscribeToTopic(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.startsWith("/topics/"))
      {
        Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in subscribeToTopic.");
        str = paramString.substring("/topics/".length());
      }
    }
    if ((str == null) || (!zzclM.matcher(str).matches()))
    {
      paramString = String.valueOf("[a-zA-Z0-9-_.~%]{1,900}");
      throw new IllegalArgumentException(String.valueOf(str).length() + 55 + String.valueOf(paramString).length() + "Invalid topic name: " + str + " does not match the allowed format " + paramString);
    }
    FirebaseInstanceId localFirebaseInstanceId = this.zzclk;
    paramString = String.valueOf("S!");
    str = String.valueOf(str);
    if (str.length() != 0) {}
    for (paramString = paramString.concat(str);; paramString = new String(paramString))
    {
      localFirebaseInstanceId.zzjt(paramString);
      return;
    }
  }
  
  public void unsubscribeFromTopic(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.startsWith("/topics/"))
      {
        Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in unsubscribeFromTopic.");
        str = paramString.substring("/topics/".length());
      }
    }
    if ((str == null) || (!zzclM.matcher(str).matches()))
    {
      paramString = String.valueOf("[a-zA-Z0-9-_.~%]{1,900}");
      throw new IllegalArgumentException(String.valueOf(str).length() + 55 + String.valueOf(paramString).length() + "Invalid topic name: " + str + " does not match the allowed format " + paramString);
    }
    FirebaseInstanceId localFirebaseInstanceId = this.zzclk;
    paramString = String.valueOf("U!");
    str = String.valueOf(str);
    if (str.length() != 0) {}
    for (paramString = paramString.concat(str);; paramString = new String(paramString))
    {
      localFirebaseInstanceId.zzjt(paramString);
      return;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\messaging\FirebaseMessaging.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */