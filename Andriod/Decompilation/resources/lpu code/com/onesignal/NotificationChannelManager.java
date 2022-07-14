package com.onesignal;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class NotificationChannelManager
{
  private static final String DEFAULT_CHANNEL_ID = "fcm_fallback_notification_channel";
  private static final String RESTORE_CHANNEL_ID = "restored_OS_notifications";
  
  @RequiresApi(api=26)
  private static String createChannel(Context paramContext, NotificationManager paramNotificationManager, JSONObject paramJSONObject)
    throws JSONException
  {
    Object localObject1 = paramJSONObject.opt("chnl");
    Object localObject2;
    Object localObject4;
    if ((localObject1 instanceof String))
    {
      localObject1 = new JSONObject((String)localObject1);
      Object localObject3 = ((JSONObject)localObject1).optString("id", "fcm_fallback_notification_channel");
      localObject2 = localObject3;
      if (((String)localObject3).equals("miscellaneous")) {
        localObject2 = "fcm_fallback_notification_channel";
      }
      localObject4 = localObject1;
      localObject3 = localObject4;
      if (((JSONObject)localObject1).has("langs"))
      {
        JSONObject localJSONObject = ((JSONObject)localObject1).getJSONObject("langs");
        String str = OSUtils.getCorrectedLanguage();
        localObject3 = localObject4;
        if (localJSONObject.has(str)) {
          localObject3 = localJSONObject.optJSONObject(str);
        }
      }
      localObject4 = new NotificationChannel((String)localObject2, ((JSONObject)localObject3).optString("nm", "Miscellaneous"), priorityToImportance(paramJSONObject.optInt("pri", 6)));
      ((NotificationChannel)localObject4).setDescription(((JSONObject)localObject3).optString("dscr", null));
      if (((JSONObject)localObject1).has("grp_id"))
      {
        localObject1 = ((JSONObject)localObject1).optString("grp_id");
        paramNotificationManager.createNotificationChannelGroup(new NotificationChannelGroup((String)localObject1, ((JSONObject)localObject3).optString("grp_nm")));
        ((NotificationChannel)localObject4).setGroup((String)localObject1);
      }
      if (paramJSONObject.has("ledc")) {
        ((NotificationChannel)localObject4).setLightColor(new BigInteger(paramJSONObject.optString("ledc"), 16).intValue());
      }
      if (paramJSONObject.optInt("led", 1) != 1) {
        break label406;
      }
      bool = true;
      label249:
      ((NotificationChannel)localObject4).enableLights(bool);
      if (paramJSONObject.has("vib_pt"))
      {
        localObject1 = OSUtils.parseVibrationPattern(paramJSONObject);
        if (localObject1 != null) {
          ((NotificationChannel)localObject4).setVibrationPattern((long[])localObject1);
        }
      }
      if (paramJSONObject.optInt("vib", 1) != 1) {
        break label411;
      }
      bool = true;
      label295:
      ((NotificationChannel)localObject4).enableVibration(bool);
      if (paramJSONObject.has("sound"))
      {
        localObject1 = paramJSONObject.optString("sound", null);
        paramContext = OSUtils.getSoundUri(paramContext, (String)localObject1);
        if (paramContext == null) {
          break label416;
        }
        ((NotificationChannel)localObject4).setSound(paramContext, null);
      }
      label337:
      ((NotificationChannel)localObject4).setLockscreenVisibility(paramJSONObject.optInt("vis", 0));
      if (paramJSONObject.optInt("bdg", 1) != 1) {
        break label446;
      }
      bool = true;
      label362:
      ((NotificationChannel)localObject4).setShowBadge(bool);
      if (paramJSONObject.optInt("bdnd", 0) != 1) {
        break label451;
      }
    }
    label406:
    label411:
    label416:
    label446:
    label451:
    for (boolean bool = true;; bool = false)
    {
      ((NotificationChannel)localObject4).setBypassDnd(bool);
      paramNotificationManager.createNotificationChannel((NotificationChannel)localObject4);
      return (String)localObject2;
      localObject1 = (JSONObject)localObject1;
      break;
      bool = false;
      break label249;
      bool = false;
      break label295;
      if ((!"null".equals(localObject1)) && (!"nil".equals(localObject1))) {
        break label337;
      }
      ((NotificationChannel)localObject4).setSound(null, null);
      break label337;
      bool = false;
      break label362;
    }
  }
  
  @RequiresApi(api=26)
  private static String createDefaultChannel(NotificationManager paramNotificationManager)
  {
    NotificationChannel localNotificationChannel = new NotificationChannel("fcm_fallback_notification_channel", "Miscellaneous", 3);
    localNotificationChannel.enableLights(true);
    localNotificationChannel.enableVibration(true);
    paramNotificationManager.createNotificationChannel(localNotificationChannel);
    return "fcm_fallback_notification_channel";
  }
  
  static String createNotificationChannel(NotificationGenerationJob paramNotificationGenerationJob)
  {
    if (Build.VERSION.SDK_INT < 26) {
      paramNotificationGenerationJob = "fcm_fallback_notification_channel";
    }
    Context localContext;
    JSONObject localJSONObject;
    NotificationManager localNotificationManager;
    String str;
    do
    {
      return paramNotificationGenerationJob;
      localContext = paramNotificationGenerationJob.context;
      localJSONObject = paramNotificationGenerationJob.jsonPayload;
      localNotificationManager = (NotificationManager)localContext.getSystemService("notification");
      if (paramNotificationGenerationJob.restoring) {
        return createRestoreChannel(localNotificationManager);
      }
      if (!localJSONObject.has("oth_chnl")) {
        break;
      }
      str = localJSONObject.optString("oth_chnl");
      paramNotificationGenerationJob = str;
    } while (localNotificationManager.getNotificationChannel(str) != null);
    if (!localJSONObject.has("chnl")) {
      return createDefaultChannel(localNotificationManager);
    }
    try
    {
      paramNotificationGenerationJob = createChannel(localContext, localNotificationManager, localJSONObject);
      return paramNotificationGenerationJob;
    }
    catch (JSONException paramNotificationGenerationJob)
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Could not create notification channel due to JSON payload error!", paramNotificationGenerationJob);
    }
    return "fcm_fallback_notification_channel";
  }
  
  @RequiresApi(api=26)
  private static String createRestoreChannel(NotificationManager paramNotificationManager)
  {
    paramNotificationManager.createNotificationChannel(new NotificationChannel("restored_OS_notifications", "Restored", 2));
    return "restored_OS_notifications";
  }
  
  private static int priorityToImportance(int paramInt)
  {
    if (paramInt > 9) {
      return 5;
    }
    if (paramInt > 7) {
      return 4;
    }
    if (paramInt > 5) {
      return 3;
    }
    if (paramInt > 3) {
      return 2;
    }
    if (paramInt > 1) {
      return 1;
    }
    return 0;
  }
  
  static void processChannelList(Context paramContext, JSONObject paramJSONObject)
  {
    if (Build.VERSION.SDK_INT < 26) {}
    for (;;)
    {
      return;
      if (paramJSONObject.has("chnl_lst"))
      {
        NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
        HashSet localHashSet = new HashSet();
        paramJSONObject = paramJSONObject.optJSONArray("chnl_lst");
        int j = paramJSONObject.length();
        int i = 0;
        for (;;)
        {
          if (i < j) {
            try
            {
              localHashSet.add(createChannel(paramContext, localNotificationManager, paramJSONObject.getJSONObject(i)));
              i += 1;
            }
            catch (JSONException localJSONException)
            {
              for (;;)
              {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Could not create notification channel due to JSON payload error!", localJSONException);
              }
            }
          }
        }
        paramContext = localNotificationManager.getNotificationChannels().iterator();
        while (paramContext.hasNext())
        {
          paramJSONObject = ((NotificationChannel)paramContext.next()).getId();
          if ((paramJSONObject.startsWith("OS_")) && (!localHashSet.contains(paramJSONObject))) {
            localNotificationManager.deleteNotificationChannel(paramJSONObject);
          }
        }
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\NotificationChannelManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */