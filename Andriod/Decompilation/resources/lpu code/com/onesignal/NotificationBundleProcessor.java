package com.onesignal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class NotificationBundleProcessor
{
  static final String DEFAULT_ACTION = "__DEFAULT__";
  
  static OSNotificationPayload OSNotificationPayloadFrom(JSONObject paramJSONObject)
  {
    localOSNotificationPayload = new OSNotificationPayload();
    try
    {
      Object localObject = new JSONObject(paramJSONObject.optString("custom"));
      localOSNotificationPayload.notificationID = ((JSONObject)localObject).optString("i");
      localOSNotificationPayload.templateId = ((JSONObject)localObject).optString("ti");
      localOSNotificationPayload.templateName = ((JSONObject)localObject).optString("tn");
      localOSNotificationPayload.rawPayload = paramJSONObject.toString();
      localOSNotificationPayload.additionalData = ((JSONObject)localObject).optJSONObject("a");
      localOSNotificationPayload.launchURL = ((JSONObject)localObject).optString("u", null);
      localOSNotificationPayload.body = paramJSONObject.optString("alert", null);
      localOSNotificationPayload.title = paramJSONObject.optString("title", null);
      localOSNotificationPayload.smallIcon = paramJSONObject.optString("sicon", null);
      localOSNotificationPayload.bigPicture = paramJSONObject.optString("bicon", null);
      localOSNotificationPayload.largeIcon = paramJSONObject.optString("licon", null);
      localOSNotificationPayload.sound = paramJSONObject.optString("sound", null);
      localOSNotificationPayload.groupKey = paramJSONObject.optString("grp", null);
      localOSNotificationPayload.groupMessage = paramJSONObject.optString("grp_msg", null);
      localOSNotificationPayload.smallIconAccentColor = paramJSONObject.optString("bgac", null);
      localOSNotificationPayload.ledColor = paramJSONObject.optString("ledc", null);
      localObject = paramJSONObject.optString("vis", null);
      if (localObject != null) {
        localOSNotificationPayload.lockScreenVisibility = Integer.parseInt((String)localObject);
      }
      localOSNotificationPayload.fromProjectNumber = paramJSONObject.optString("from", null);
      localOSNotificationPayload.priority = paramJSONObject.optInt("pri", 0);
      localObject = paramJSONObject.optString("collapse_key", null);
      if (!"do_not_collapse".equals(localObject)) {
        localOSNotificationPayload.collapseId = ((String)localObject);
      }
      try
      {
        setActionButtons(localOSNotificationPayload);
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          try
          {
            setBackgroundImageLayout(localOSNotificationPayload, paramJSONObject);
            return localOSNotificationPayload;
          }
          catch (Throwable paramJSONObject)
          {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error assigning OSNotificationPayload.backgroundImageLayout values!", paramJSONObject);
          }
          localThrowable = localThrowable;
          OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error assigning OSNotificationPayload.actionButtons values!", localThrowable);
        }
      }
      return localOSNotificationPayload;
    }
    catch (Throwable paramJSONObject)
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error assigning OSNotificationPayload values!", paramJSONObject);
      return localOSNotificationPayload;
    }
  }
  
  static void ProcessFromGCMIntentService(Context paramContext, BundleCompat paramBundleCompat, NotificationExtenderService.OverrideSettings paramOverrideSettings)
  {
    try
    {
      String str = paramBundleCompat.getString("json_payload");
      if (str == null)
      {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "json_payload key is nonexistent from mBundle passed to ProcessFromGCMIntentService: " + paramBundleCompat);
        return;
      }
      NotificationGenerationJob localNotificationGenerationJob = new NotificationGenerationJob(paramContext);
      localNotificationGenerationJob.restoring = paramBundleCompat.getBoolean("restoring", false);
      localNotificationGenerationJob.shownTimeStamp = paramBundleCompat.getLong("timestamp");
      localNotificationGenerationJob.jsonPayload = new JSONObject(str);
      if ((localNotificationGenerationJob.restoring) || (!OneSignal.notValidOrDuplicated(paramContext, localNotificationGenerationJob.jsonPayload)))
      {
        paramContext = paramOverrideSettings;
        if (paramBundleCompat.containsKey("android_notif_id"))
        {
          paramContext = paramOverrideSettings;
          if (paramOverrideSettings == null) {
            paramContext = new NotificationExtenderService.OverrideSettings();
          }
          paramContext.androidNotificationId = paramBundleCompat.getInt("android_notif_id");
        }
        localNotificationGenerationJob.overrideSettings = paramContext;
        ProcessJobForDisplay(localNotificationGenerationJob);
        if (localNotificationGenerationJob.restoring)
        {
          OSUtils.sleep(100);
          return;
        }
      }
    }
    catch (JSONException paramContext)
    {
      ThrowableExtension.printStackTrace(paramContext);
    }
  }
  
  static int ProcessJobForDisplay(NotificationGenerationJob paramNotificationGenerationJob)
  {
    boolean bool;
    if ((OneSignal.getInAppAlertNotificationEnabled()) && (OneSignal.isAppActive())) {
      bool = true;
    }
    for (;;)
    {
      paramNotificationGenerationJob.showAsAlert = bool;
      processCollapseKey(paramNotificationGenerationJob);
      int i;
      if ((paramNotificationGenerationJob.hasExtender()) || (shouldDisplay(paramNotificationGenerationJob.jsonPayload.optString("alert"))))
      {
        i = 1;
        if (i != 0) {
          GenerateNotification.fromJsonPayload(paramNotificationGenerationJob);
        }
        if (!paramNotificationGenerationJob.restoring) {
          saveNotification(paramNotificationGenerationJob, false);
        }
      }
      try
      {
        JSONObject localJSONObject = new JSONObject(paramNotificationGenerationJob.jsonPayload.toString());
        localJSONObject.put("notificationId", paramNotificationGenerationJob.getAndroidId());
        OneSignal.handleNotificationReceived(newJsonArray(localJSONObject), true, paramNotificationGenerationJob.showAsAlert);
        return paramNotificationGenerationJob.getAndroidId().intValue();
        bool = false;
        continue;
        i = 0;
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
    }
  }
  
  static JSONObject bundleAsJSONObject(Bundle paramBundle)
  {
    JSONObject localJSONObject = new JSONObject();
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      try
      {
        localJSONObject.put(str, paramBundle.get(str));
      }
      catch (JSONException localJSONException)
      {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "bundleAsJSONObject error for key: " + str, localJSONException);
      }
    }
    return localJSONObject;
  }
  
  private static JSONArray bundleAsJsonArray(Bundle paramBundle)
  {
    JSONArray localJSONArray = new JSONArray();
    localJSONArray.put(bundleAsJSONObject(paramBundle));
    return localJSONArray;
  }
  
  static void deleteOldNotifications(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.delete("notification", "created_time < " + (System.currentTimeMillis() / 1000L - 604800L), null);
  }
  
  static boolean hasRemoteResource(Bundle paramBundle)
  {
    return (isBuildKeyRemote(paramBundle, "licon")) || (isBuildKeyRemote(paramBundle, "bicon")) || (paramBundle.getString("bg_img", null) != null);
  }
  
  private static boolean isBuildKeyRemote(Bundle paramBundle, String paramString)
  {
    paramBundle = paramBundle.getString(paramString, "").trim();
    return (paramBundle.startsWith("http://")) || (paramBundle.startsWith("https://"));
  }
  
  static void markRestoredNotificationAsDismissed(NotificationGenerationJob paramNotificationGenerationJob)
  {
    if (paramNotificationGenerationJob.getAndroidIdWithoutCreate() == -1) {}
    Object localObject2;
    for (;;)
    {
      return;
      String str = "android_notification_id = " + paramNotificationGenerationJob.getAndroidIdWithoutCreate();
      Object localObject3 = OneSignalDbHelper.getInstance(paramNotificationGenerationJob.context);
      localObject2 = null;
      Object localObject1 = null;
      try
      {
        localObject3 = ((OneSignalDbHelper)localObject3).getWritableDbWithRetries();
        localObject1 = localObject3;
        localObject2 = localObject3;
        ((SQLiteDatabase)localObject3).beginTransaction();
        localObject1 = localObject3;
        localObject2 = localObject3;
        ContentValues localContentValues = new ContentValues();
        localObject1 = localObject3;
        localObject2 = localObject3;
        localContentValues.put("dismissed", Integer.valueOf(1));
        localObject1 = localObject3;
        localObject2 = localObject3;
        ((SQLiteDatabase)localObject3).update("notification", localContentValues, str, null);
        localObject1 = localObject3;
        localObject2 = localObject3;
        BadgeCountUpdater.update((SQLiteDatabase)localObject3, paramNotificationGenerationJob.context);
        localObject1 = localObject3;
        localObject2 = localObject3;
        ((SQLiteDatabase)localObject3).setTransactionSuccessful();
        if (localObject3 != null) {
          try
          {
            ((SQLiteDatabase)localObject3).endTransaction();
            return;
          }
          catch (Throwable paramNotificationGenerationJob)
          {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", paramNotificationGenerationJob);
            return;
          }
        }
      }
      catch (Exception paramNotificationGenerationJob)
      {
        localObject2 = localObject1;
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error saving notification record! ", paramNotificationGenerationJob);
        if (localObject1 != null) {
          try
          {
            ((SQLiteDatabase)localObject1).endTransaction();
            return;
          }
          catch (Throwable paramNotificationGenerationJob)
          {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", paramNotificationGenerationJob);
            return;
          }
        }
      }
      finally
      {
        if (localObject2 == null) {}
      }
    }
    try
    {
      ((SQLiteDatabase)localObject2).endTransaction();
      throw paramNotificationGenerationJob;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", localThrowable);
      }
    }
  }
  
  static JSONArray newJsonArray(JSONObject paramJSONObject)
  {
    JSONArray localJSONArray = new JSONArray();
    localJSONArray.put(paramJSONObject);
    return localJSONArray;
  }
  
  static ProcessedBundleResult processBundleFromReceiver(Context paramContext, Bundle paramBundle)
  {
    ProcessedBundleResult localProcessedBundleResult = new ProcessedBundleResult();
    if (OneSignal.getNotificationIdFromGCMBundle(paramBundle) == null) {}
    do
    {
      do
      {
        return localProcessedBundleResult;
        localProcessedBundleResult.isOneSignalPayload = true;
        unMinifyBundle(paramBundle);
      } while (startExtenderService(paramContext, paramBundle, localProcessedBundleResult));
      localProcessedBundleResult.isDup = OneSignal.notValidOrDuplicated(paramContext, bundleAsJSONObject(paramBundle));
    } while ((localProcessedBundleResult.isDup) || (shouldDisplay(paramBundle.getString("alert"))));
    saveNotification(paramContext, paramBundle, true, -1);
    new Thread(new Runnable()
    {
      public void run()
      {
        OneSignal.handleNotificationReceived(NotificationBundleProcessor.bundleAsJsonArray(this.val$bundle), false, false);
      }
    }, "OS_PROC_BUNDLE").start();
    return localProcessedBundleResult;
  }
  
  private static void processCollapseKey(NotificationGenerationJob paramNotificationGenerationJob)
  {
    if (paramNotificationGenerationJob.restoring) {}
    for (;;)
    {
      return;
      if ((!paramNotificationGenerationJob.jsonPayload.has("collapse_key")) || ("do_not_collapse".equals(paramNotificationGenerationJob.jsonPayload.optString("collapse_key")))) {
        continue;
      }
      Object localObject3 = paramNotificationGenerationJob.jsonPayload.optString("collapse_key");
      OneSignalDbHelper localOneSignalDbHelper = OneSignalDbHelper.getInstance(paramNotificationGenerationJob.context);
      Object localObject2 = null;
      Object localObject1 = null;
      try
      {
        localObject3 = localOneSignalDbHelper.getReadableDbWithRetries().query("notification", new String[] { "android_notification_id" }, "collapse_id = ? AND dismissed = 0 AND opened = 0 ", new String[] { localObject3 }, null, null, null);
        localObject1 = localObject3;
        localObject2 = localObject3;
        if (((Cursor)localObject3).moveToFirst())
        {
          localObject1 = localObject3;
          localObject2 = localObject3;
          paramNotificationGenerationJob.setAndroidIdWithOutOverriding(Integer.valueOf(((Cursor)localObject3).getInt(((Cursor)localObject3).getColumnIndex("android_notification_id"))));
        }
        return;
      }
      catch (Throwable paramNotificationGenerationJob)
      {
        localObject2 = localObject1;
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Could not read DB to find existing collapse_key!", paramNotificationGenerationJob);
        return;
      }
      finally
      {
        if ((localObject2 != null) && (!((Cursor)localObject2).isClosed())) {
          ((Cursor)localObject2).close();
        }
      }
    }
  }
  
  private static void saveNotification(Context paramContext, Bundle paramBundle, boolean paramBoolean, int paramInt)
  {
    paramContext = new NotificationGenerationJob(paramContext);
    paramContext.jsonPayload = bundleAsJSONObject(paramBundle);
    paramContext.overrideSettings = new NotificationExtenderService.OverrideSettings();
    paramContext.overrideSettings.androidNotificationId = Integer.valueOf(paramInt);
    saveNotification(paramContext, paramBoolean);
  }
  
  static void saveNotification(NotificationGenerationJob paramNotificationGenerationJob, boolean paramBoolean)
  {
    int i = 1;
    Context localContext = paramNotificationGenerationJob.context;
    JSONObject localJSONObject1 = paramNotificationGenerationJob.jsonPayload;
    for (;;)
    {
      try
      {
        localJSONObject2 = new JSONObject(paramNotificationGenerationJob.jsonPayload.optString("custom"));
        localObject3 = OneSignalDbHelper.getInstance(paramNotificationGenerationJob.context);
        localObject2 = null;
        localObject1 = null;
      }
      catch (JSONException paramNotificationGenerationJob)
      {
        JSONObject localJSONObject2;
        Object localObject3;
        Object localObject4;
        ThrowableExtension.printStackTrace(paramNotificationGenerationJob);
        return;
      }
      try
      {
        localObject3 = ((OneSignalDbHelper)localObject3).getWritableDbWithRetries();
        localObject1 = localObject3;
        localObject2 = localObject3;
        ((SQLiteDatabase)localObject3).beginTransaction();
        localObject1 = localObject3;
        localObject2 = localObject3;
        deleteOldNotifications((SQLiteDatabase)localObject3);
        localObject1 = localObject3;
        localObject2 = localObject3;
        if (paramNotificationGenerationJob.getAndroidIdWithoutCreate() != -1)
        {
          localObject1 = localObject3;
          localObject2 = localObject3;
          localObject4 = "android_notification_id = " + paramNotificationGenerationJob.getAndroidIdWithoutCreate();
          localObject1 = localObject3;
          localObject2 = localObject3;
          ContentValues localContentValues = new ContentValues();
          localObject1 = localObject3;
          localObject2 = localObject3;
          localContentValues.put("dismissed", Integer.valueOf(1));
          localObject1 = localObject3;
          localObject2 = localObject3;
          ((SQLiteDatabase)localObject3).update("notification", localContentValues, (String)localObject4, null);
          localObject1 = localObject3;
          localObject2 = localObject3;
          BadgeCountUpdater.update((SQLiteDatabase)localObject3, localContext);
        }
        localObject1 = localObject3;
        localObject2 = localObject3;
        localObject4 = new ContentValues();
        localObject1 = localObject3;
        localObject2 = localObject3;
        ((ContentValues)localObject4).put("notification_id", localJSONObject2.optString("i"));
        localObject1 = localObject3;
        localObject2 = localObject3;
        if (localJSONObject1.has("grp"))
        {
          localObject1 = localObject3;
          localObject2 = localObject3;
          ((ContentValues)localObject4).put("group_id", localJSONObject1.optString("grp"));
        }
        localObject1 = localObject3;
        localObject2 = localObject3;
        if (!localJSONObject1.has("collapse_key")) {
          break label620;
        }
        localObject1 = localObject3;
        localObject2 = localObject3;
        if ("do_not_collapse".equals(localJSONObject1.optString("collapse_key"))) {
          break label620;
        }
        localObject1 = localObject3;
        localObject2 = localObject3;
        ((ContentValues)localObject4).put("collapse_id", localJSONObject1.optString("collapse_key"));
      }
      catch (Exception paramNotificationGenerationJob)
      {
        localObject2 = localObject1;
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error saving notification record! ", paramNotificationGenerationJob);
        if (localObject1 == null) {
          continue;
        }
        try
        {
          ((SQLiteDatabase)localObject1).endTransaction();
          return;
        }
        catch (Throwable paramNotificationGenerationJob)
        {
          OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", paramNotificationGenerationJob);
          return;
        }
      }
      finally
      {
        if (localObject2 == null) {
          break label604;
        }
        try
        {
          ((SQLiteDatabase)localObject2).endTransaction();
          throw paramNotificationGenerationJob;
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", localThrowable);
          }
        }
        if (!paramBoolean) {
          continue;
        }
        continue;
      }
      localObject1 = localObject3;
      localObject2 = localObject3;
      ((ContentValues)localObject4).put("opened", Integer.valueOf(i));
      if (!paramBoolean)
      {
        localObject1 = localObject3;
        localObject2 = localObject3;
        ((ContentValues)localObject4).put("android_notification_id", Integer.valueOf(paramNotificationGenerationJob.getAndroidIdWithoutCreate()));
      }
      localObject1 = localObject3;
      localObject2 = localObject3;
      if (paramNotificationGenerationJob.getTitle() != null)
      {
        localObject1 = localObject3;
        localObject2 = localObject3;
        ((ContentValues)localObject4).put("title", paramNotificationGenerationJob.getTitle().toString());
      }
      localObject1 = localObject3;
      localObject2 = localObject3;
      if (paramNotificationGenerationJob.getBody() != null)
      {
        localObject1 = localObject3;
        localObject2 = localObject3;
        ((ContentValues)localObject4).put("message", paramNotificationGenerationJob.getBody().toString());
      }
      localObject1 = localObject3;
      localObject2 = localObject3;
      ((ContentValues)localObject4).put("full_data", localJSONObject1.toString());
      localObject1 = localObject3;
      localObject2 = localObject3;
      ((SQLiteDatabase)localObject3).insertOrThrow("notification", null, (ContentValues)localObject4);
      if (!paramBoolean)
      {
        localObject1 = localObject3;
        localObject2 = localObject3;
        BadgeCountUpdater.update((SQLiteDatabase)localObject3, localContext);
      }
      localObject1 = localObject3;
      localObject2 = localObject3;
      ((SQLiteDatabase)localObject3).setTransactionSuccessful();
      if (localObject3 != null) {}
      try
      {
        ((SQLiteDatabase)localObject3).endTransaction();
        return;
      }
      catch (Throwable paramNotificationGenerationJob)
      {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", paramNotificationGenerationJob);
        return;
      }
      i = 0;
    }
  }
  
  private static void setActionButtons(OSNotificationPayload paramOSNotificationPayload)
    throws Throwable
  {
    if ((paramOSNotificationPayload.additionalData != null) && (paramOSNotificationPayload.additionalData.has("actionButtons")))
    {
      JSONArray localJSONArray = paramOSNotificationPayload.additionalData.getJSONArray("actionButtons");
      paramOSNotificationPayload.actionButtons = new ArrayList();
      int i = 0;
      while (i < localJSONArray.length())
      {
        JSONObject localJSONObject = localJSONArray.getJSONObject(i);
        OSNotificationPayload.ActionButton localActionButton = new OSNotificationPayload.ActionButton();
        localActionButton.id = localJSONObject.optString("id", null);
        localActionButton.text = localJSONObject.optString("text", null);
        localActionButton.icon = localJSONObject.optString("icon", null);
        paramOSNotificationPayload.actionButtons.add(localActionButton);
        i += 1;
      }
      paramOSNotificationPayload.additionalData.remove("actionSelected");
      paramOSNotificationPayload.additionalData.remove("actionButtons");
    }
  }
  
  private static void setBackgroundImageLayout(OSNotificationPayload paramOSNotificationPayload, JSONObject paramJSONObject)
    throws Throwable
  {
    paramJSONObject = paramJSONObject.optString("bg_img", null);
    if (paramJSONObject != null)
    {
      paramJSONObject = new JSONObject(paramJSONObject);
      paramOSNotificationPayload.backgroundImageLayout = new OSNotificationPayload.BackgroundImageLayout();
      paramOSNotificationPayload.backgroundImageLayout.image = paramJSONObject.optString("img");
      paramOSNotificationPayload.backgroundImageLayout.titleTextColor = paramJSONObject.optString("tc");
      paramOSNotificationPayload.backgroundImageLayout.bodyTextColor = paramJSONObject.optString("bc");
    }
  }
  
  static boolean shouldDisplay(String paramString)
  {
    if ((paramString != null) && (!"".equals(paramString))) {}
    for (int i = 1;; i = 0)
    {
      boolean bool1 = OneSignal.getInAppAlertNotificationEnabled();
      boolean bool2 = OneSignal.isAppActive();
      if ((i == 0) || ((!OneSignal.getNotificationsWhenActiveEnabled()) && (!bool1) && (bool2))) {
        break;
      }
      return true;
    }
    return false;
  }
  
  private static boolean startExtenderService(Context paramContext, Bundle paramBundle, ProcessedBundleResult paramProcessedBundleResult)
  {
    Intent localIntent = NotificationExtenderService.getIntent(paramContext);
    if (localIntent == null) {
      return false;
    }
    localIntent.putExtra("json_payload", bundleAsJSONObject(paramBundle).toString());
    localIntent.putExtra("timestamp", System.currentTimeMillis() / 1000L);
    if (Build.VERSION.SDK_INT >= 21) {
      NotificationExtenderService.enqueueWork(paramContext, localIntent.getComponent(), 2071862121, localIntent);
    }
    for (;;)
    {
      paramProcessedBundleResult.hasExtenderService = true;
      return true;
      paramContext.startService(localIntent);
    }
  }
  
  private static void unMinifyBundle(Bundle paramBundle)
  {
    if (!paramBundle.containsKey("o")) {
      return;
    }
    for (;;)
    {
      int i;
      String str2;
      try
      {
        JSONObject localJSONObject2 = new JSONObject(paramBundle.getString("custom"));
        JSONObject localJSONObject1;
        JSONArray localJSONArray;
        if (localJSONObject2.has("a"))
        {
          localJSONObject1 = localJSONObject2.getJSONObject("a");
          localJSONArray = new JSONArray(paramBundle.getString("o"));
          paramBundle.remove("o");
          i = 0;
          if (i < localJSONArray.length())
          {
            JSONObject localJSONObject3 = localJSONArray.getJSONObject(i);
            str2 = localJSONObject3.getString("n");
            localJSONObject3.remove("n");
            if (!localJSONObject3.has("i")) {
              break label267;
            }
            str1 = localJSONObject3.getString("i");
            localJSONObject3.remove("i");
            localJSONObject3.put("id", str1);
            localJSONObject3.put("text", str2);
            if (!localJSONObject3.has("p")) {
              break label260;
            }
            localJSONObject3.put("icon", localJSONObject3.getString("p"));
            localJSONObject3.remove("p");
            break label260;
          }
        }
        else
        {
          localJSONObject1 = new JSONObject();
          continue;
        }
        localJSONObject1.put("actionButtons", localJSONArray);
        localJSONObject1.put("actionSelected", "__DEFAULT__");
        if (!localJSONObject2.has("a")) {
          localJSONObject2.put("a", localJSONObject1);
        }
        paramBundle.putString("custom", localJSONObject2.toString());
        return;
      }
      catch (JSONException paramBundle)
      {
        ThrowableExtension.printStackTrace(paramBundle);
        return;
      }
      label260:
      i += 1;
      continue;
      label267:
      String str1 = str2;
    }
  }
  
  static class ProcessedBundleResult
  {
    boolean hasExtenderService;
    boolean isDup;
    boolean isOneSignalPayload;
    
    boolean processed()
    {
      return (!this.isOneSignalPayload) || (this.hasExtenderService) || (this.isDup);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\NotificationBundleProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */