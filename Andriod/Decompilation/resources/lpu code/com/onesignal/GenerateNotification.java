package com.onesignal;

import android.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.RemoteViews;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class GenerateNotification
{
  private static Resources contextResources = null;
  private static Context currentContext = null;
  private static Class<?> notificationOpenedClass;
  private static boolean openerIsBroadcast;
  private static String packageName = null;
  
  private static void addAlertButtons(Context paramContext, JSONObject paramJSONObject, List<String> paramList1, List<String> paramList2)
  {
    try
    {
      addCustomAlertButtons(paramContext, paramJSONObject, paramList1, paramList2);
      if ((paramList1.size() == 0) || (paramList1.size() < 3))
      {
        paramList1.add(OSUtils.getResourceString(paramContext, "onesignal_in_app_alert_ok_button_text", "Ok"));
        paramList2.add("__DEFAULT__");
      }
      return;
    }
    catch (Throwable paramJSONObject)
    {
      for (;;)
      {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Failed to parse JSON for custom buttons for alert dialog.", paramJSONObject);
      }
    }
  }
  
  private static void addBackgroundImage(JSONObject paramJSONObject, NotificationCompat.Builder paramBuilder)
    throws Throwable
  {
    if (Build.VERSION.SDK_INT < 16) {}
    JSONObject localJSONObject;
    Object localObject2;
    do
    {
      return;
      localObject1 = null;
      localJSONObject = null;
      localObject2 = paramJSONObject.optString("bg_img", null);
      if (localObject2 != null)
      {
        localJSONObject = new JSONObject((String)localObject2);
        localObject1 = getBitmap(localJSONObject.optString("img", null));
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = getBitmapFromAssetsOrResourceName("onesignal_bgimage_default_image");
      }
    } while (localObject2 == null);
    Object localObject1 = new RemoteViews(currentContext.getPackageName(), R.layout.onesignal_bgimage_notif_layout);
    ((RemoteViews)localObject1).setTextViewText(R.id.os_bgimage_notif_title, getTitle(paramJSONObject));
    ((RemoteViews)localObject1).setTextViewText(R.id.os_bgimage_notif_body, paramJSONObject.optString("alert"));
    setTextColor((RemoteViews)localObject1, localJSONObject, R.id.os_bgimage_notif_title, "tc", "onesignal_bgimage_notif_title_color");
    setTextColor((RemoteViews)localObject1, localJSONObject, R.id.os_bgimage_notif_body, "bc", "onesignal_bgimage_notif_body_color");
    paramJSONObject = null;
    if ((localJSONObject != null) && (localJSONObject.has("img_align")))
    {
      paramJSONObject = localJSONObject.getString("img_align");
      if (!"right".equals(paramJSONObject)) {
        break label251;
      }
      ((RemoteViews)localObject1).setViewPadding(R.id.os_bgimage_notif_bgimage_align_layout, 60536, 0, 0, 0);
      ((RemoteViews)localObject1).setImageViewBitmap(R.id.os_bgimage_notif_bgimage_right_aligned, (Bitmap)localObject2);
      ((RemoteViews)localObject1).setViewVisibility(R.id.os_bgimage_notif_bgimage_right_aligned, 0);
      ((RemoteViews)localObject1).setViewVisibility(R.id.os_bgimage_notif_bgimage, 2);
    }
    for (;;)
    {
      paramBuilder.setContent((RemoteViews)localObject1);
      paramBuilder.setStyle(null);
      return;
      int i = contextResources.getIdentifier("onesignal_bgimage_notif_image_align", "string", packageName);
      if (i == 0) {
        break;
      }
      paramJSONObject = contextResources.getString(i);
      break;
      label251:
      ((RemoteViews)localObject1).setImageViewBitmap(R.id.os_bgimage_notif_bgimage, (Bitmap)localObject2);
    }
  }
  
  private static void addCustomAlertButtons(Context paramContext, JSONObject paramJSONObject, List<String> paramList1, List<String> paramList2)
    throws JSONException
  {
    paramContext = new JSONObject(paramJSONObject.optString("custom"));
    if (!paramContext.has("a")) {}
    for (;;)
    {
      return;
      paramContext = paramContext.getJSONObject("a");
      if (paramContext.has("actionButtons"))
      {
        paramContext = paramContext.optJSONArray("actionButtons");
        int i = 0;
        while (i < paramContext.length())
        {
          paramJSONObject = paramContext.getJSONObject(i);
          paramList1.add(paramJSONObject.optString("text"));
          paramList2.add(paramJSONObject.optString("id"));
          i += 1;
        }
      }
    }
  }
  
  private static void addNotificationActionButtons(JSONObject paramJSONObject, NotificationCompat.Builder paramBuilder, int paramInt, String paramString)
  {
    try
    {
      Object localObject1 = new JSONObject(paramJSONObject.optString("custom"));
      if (!((JSONObject)localObject1).has("a")) {
        return;
      }
      localObject1 = ((JSONObject)localObject1).getJSONObject("a");
      if (((JSONObject)localObject1).has("actionButtons"))
      {
        localObject1 = ((JSONObject)localObject1).getJSONArray("actionButtons");
        int i = 0;
        if (i < ((JSONArray)localObject1).length())
        {
          JSONObject localJSONObject = ((JSONArray)localObject1).optJSONObject(i);
          Object localObject2 = new JSONObject(paramJSONObject.toString());
          Intent localIntent = getNewBaseIntent(paramInt);
          localIntent.setAction("" + i);
          localIntent.putExtra("action_button", true);
          ((JSONObject)localObject2).put("actionSelected", localJSONObject.optString("id"));
          localIntent.putExtra("onesignal_data", ((JSONObject)localObject2).toString());
          if (paramString != null) {
            localIntent.putExtra("summary", paramString);
          }
          for (;;)
          {
            localObject2 = getNewActionPendingIntent(paramInt, localIntent);
            int j = 0;
            if (localJSONObject.has("icon")) {
              j = getResourceIcon(localJSONObject.optString("icon"));
            }
            paramBuilder.addAction(j, localJSONObject.optString("text"), (PendingIntent)localObject2);
            i += 1;
            break;
            if (paramJSONObject.has("grp")) {
              localIntent.putExtra("grp", paramJSONObject.optString("grp"));
            }
          }
        }
      }
      return;
    }
    catch (Throwable paramJSONObject)
    {
      ThrowableExtension.printStackTrace(paramJSONObject);
    }
  }
  
  private static void addXiaomiSettings(OneSignalNotificationBuilder paramOneSignalNotificationBuilder, Notification paramNotification)
  {
    if (!paramOneSignalNotificationBuilder.hasLargeIcon) {
      return;
    }
    try
    {
      paramOneSignalNotificationBuilder = Class.forName("android.app.MiuiNotification").newInstance();
      Field localField = paramOneSignalNotificationBuilder.getClass().getDeclaredField("customizedIcon");
      localField.setAccessible(true);
      localField.set(paramOneSignalNotificationBuilder, Boolean.valueOf(true));
      localField = paramNotification.getClass().getField("extraNotification");
      localField.setAccessible(true);
      localField.set(paramNotification, paramOneSignalNotificationBuilder);
      return;
    }
    catch (Throwable paramOneSignalNotificationBuilder) {}
  }
  
  private static void applyNotificationExtender(NotificationGenerationJob paramNotificationGenerationJob, NotificationCompat.Builder paramBuilder)
  {
    if ((paramNotificationGenerationJob.overrideSettings == null) || (paramNotificationGenerationJob.overrideSettings.extender == null)) {}
    for (;;)
    {
      return;
      try
      {
        Object localObject1 = NotificationCompat.Builder.class.getDeclaredField("mNotification");
        ((Field)localObject1).setAccessible(true);
        Object localObject2 = (Notification)((Field)localObject1).get(paramBuilder);
        paramNotificationGenerationJob.orgFlags = Integer.valueOf(((Notification)localObject2).flags);
        paramNotificationGenerationJob.orgSound = ((Notification)localObject2).sound;
        paramBuilder.extend(paramNotificationGenerationJob.overrideSettings.extender);
        localObject1 = (Notification)((Field)localObject1).get(paramBuilder);
        localObject2 = NotificationCompat.Builder.class.getDeclaredField("mContentText");
        ((Field)localObject2).setAccessible(true);
        localObject2 = (CharSequence)((Field)localObject2).get(paramBuilder);
        Field localField = NotificationCompat.Builder.class.getDeclaredField("mContentTitle");
        localField.setAccessible(true);
        paramBuilder = (CharSequence)localField.get(paramBuilder);
        paramNotificationGenerationJob.overriddenBodyFromExtender = ((CharSequence)localObject2);
        paramNotificationGenerationJob.overriddenTitleFromExtender = paramBuilder;
        if (!paramNotificationGenerationJob.restoring)
        {
          paramNotificationGenerationJob.overriddenFlags = Integer.valueOf(((Notification)localObject1).flags);
          paramNotificationGenerationJob.overriddenSound = ((Notification)localObject1).sound;
          return;
        }
      }
      catch (Throwable paramNotificationGenerationJob)
      {
        ThrowableExtension.printStackTrace(paramNotificationGenerationJob);
      }
    }
  }
  
  private static Intent createBaseSummaryIntent(int paramInt, JSONObject paramJSONObject, String paramString)
  {
    return getNewBaseIntent(paramInt).putExtra("onesignal_data", paramJSONObject.toString()).putExtra("summary", paramString);
  }
  
  private static Notification createSingleNotificationBeforeSummaryBuilder(NotificationGenerationJob paramNotificationGenerationJob, NotificationCompat.Builder paramBuilder)
  {
    if ((Build.VERSION.SDK_INT > 17) && (Build.VERSION.SDK_INT < 24) && (!paramNotificationGenerationJob.restoring)) {}
    for (int i = 1;; i = 0)
    {
      if ((i != 0) && (paramNotificationGenerationJob.overriddenSound != null) && (!paramNotificationGenerationJob.overriddenSound.equals(paramNotificationGenerationJob.orgSound))) {
        paramBuilder.setSound(null);
      }
      Notification localNotification = paramBuilder.build();
      if (i != 0) {
        paramBuilder.setSound(paramNotificationGenerationJob.overriddenSound);
      }
      return localNotification;
    }
  }
  
  private static void createSummaryIdDatabaseEntry(OneSignalDbHelper paramOneSignalDbHelper, String paramString, int paramInt)
  {
    Object localObject = null;
    OneSignalDbHelper localOneSignalDbHelper = null;
    for (;;)
    {
      try
      {
        paramOneSignalDbHelper = paramOneSignalDbHelper.getWritableDbWithRetries();
        localOneSignalDbHelper = paramOneSignalDbHelper;
        localObject = paramOneSignalDbHelper;
        paramOneSignalDbHelper.beginTransaction();
        localOneSignalDbHelper = paramOneSignalDbHelper;
        localObject = paramOneSignalDbHelper;
        ContentValues localContentValues = new ContentValues();
        localOneSignalDbHelper = paramOneSignalDbHelper;
        localObject = paramOneSignalDbHelper;
        localContentValues.put("android_notification_id", Integer.valueOf(paramInt));
        localOneSignalDbHelper = paramOneSignalDbHelper;
        localObject = paramOneSignalDbHelper;
        localContentValues.put("group_id", paramString);
        localOneSignalDbHelper = paramOneSignalDbHelper;
        localObject = paramOneSignalDbHelper;
        localContentValues.put("is_summary", Integer.valueOf(1));
        localOneSignalDbHelper = paramOneSignalDbHelper;
        localObject = paramOneSignalDbHelper;
        paramOneSignalDbHelper.insertOrThrow("notification", null, localContentValues);
        localOneSignalDbHelper = paramOneSignalDbHelper;
        localObject = paramOneSignalDbHelper;
        paramOneSignalDbHelper.setTransactionSuccessful();
      }
      catch (Throwable paramOneSignalDbHelper)
      {
        localObject = localOneSignalDbHelper;
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error adding summary notification record! ", paramOneSignalDbHelper);
        if (localOneSignalDbHelper == null) {
          continue;
        }
        try
        {
          localOneSignalDbHelper.endTransaction();
          return;
        }
        catch (Throwable paramOneSignalDbHelper)
        {
          OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", paramOneSignalDbHelper);
          return;
        }
      }
      finally
      {
        if (localObject == null) {
          break label173;
        }
      }
      try
      {
        paramOneSignalDbHelper.endTransaction();
        return;
      }
      catch (Throwable paramOneSignalDbHelper)
      {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", paramOneSignalDbHelper);
        return;
      }
    }
    try
    {
      ((SQLiteDatabase)localObject).endTransaction();
      label173:
      throw paramOneSignalDbHelper;
    }
    catch (Throwable paramString)
    {
      for (;;)
      {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", paramString);
      }
    }
  }
  
  /* Error */
  private static void createSummaryNotification(NotificationGenerationJob paramNotificationGenerationJob, OneSignalNotificationBuilder paramOneSignalNotificationBuilder)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 438	com/onesignal/NotificationGenerationJob:restoring	Z
    //   4: istore 4
    //   6: aload_0
    //   7: getfield 509	com/onesignal/NotificationGenerationJob:jsonPayload	Lorg/json/JSONObject;
    //   10: astore 12
    //   12: aload 12
    //   14: ldc_w 323
    //   17: aconst_null
    //   18: invokevirtual 110	org/json/JSONObject:optString	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   21: astore 15
    //   23: new 511	java/util/Random
    //   26: dup
    //   27: invokespecial 512	java/util/Random:<init>	()V
    //   30: astore 17
    //   32: aload 17
    //   34: invokevirtual 515	java/util/Random:nextInt	()I
    //   37: iconst_0
    //   38: invokestatic 518	com/onesignal/GenerateNotification:getNewBaseDeleteIntent	(I)Landroid/content/Intent;
    //   41: ldc_w 307
    //   44: aload 15
    //   46: invokevirtual 305	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   49: invokestatic 311	com/onesignal/GenerateNotification:getNewActionPendingIntent	(ILandroid/content/Intent;)Landroid/app/PendingIntent;
    //   52: astore 16
    //   54: aconst_null
    //   55: astore 8
    //   57: aconst_null
    //   58: astore 13
    //   60: aconst_null
    //   61: astore 11
    //   63: aconst_null
    //   64: astore 14
    //   66: getstatic 28	com/onesignal/GenerateNotification:currentContext	Landroid/content/Context;
    //   69: invokestatic 522	com/onesignal/OneSignalDbHelper:getInstance	(Landroid/content/Context;)Lcom/onesignal/OneSignalDbHelper;
    //   72: astore 18
    //   74: aconst_null
    //   75: astore 9
    //   77: aload 9
    //   79: astore 7
    //   81: aload 18
    //   83: invokevirtual 525	com/onesignal/OneSignalDbHelper:getReadableDbWithRetries	()Landroid/database/sqlite/SQLiteDatabase;
    //   86: astore 10
    //   88: ldc_w 527
    //   91: astore 7
    //   93: aload 7
    //   95: astore 6
    //   97: iload 4
    //   99: ifne +57 -> 156
    //   102: aload 7
    //   104: astore 6
    //   106: aload 9
    //   108: astore 7
    //   110: aload_0
    //   111: invokevirtual 531	com/onesignal/NotificationGenerationJob:getAndroidId	()Ljava/lang/Integer;
    //   114: invokevirtual 534	java/lang/Integer:intValue	()I
    //   117: iconst_m1
    //   118: if_icmpeq +38 -> 156
    //   121: aload 9
    //   123: astore 7
    //   125: new 271	java/lang/StringBuilder
    //   128: dup
    //   129: invokespecial 272	java/lang/StringBuilder:<init>	()V
    //   132: ldc_w 527
    //   135: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: ldc_w 536
    //   141: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: aload_0
    //   145: invokevirtual 531	com/onesignal/NotificationGenerationJob:getAndroidId	()Ljava/lang/Integer;
    //   148: invokevirtual 539	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   151: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   154: astore 6
    //   156: aload 9
    //   158: astore 7
    //   160: aload 10
    //   162: ldc_w 489
    //   165: iconst_5
    //   166: anewarray 182	java/lang/String
    //   169: dup
    //   170: iconst_0
    //   171: ldc_w 477
    //   174: aastore
    //   175: dup
    //   176: iconst_1
    //   177: ldc_w 541
    //   180: aastore
    //   181: dup
    //   182: iconst_2
    //   183: ldc_w 487
    //   186: aastore
    //   187: dup
    //   188: iconst_3
    //   189: ldc_w 543
    //   192: aastore
    //   193: dup
    //   194: iconst_4
    //   195: ldc_w 545
    //   198: aastore
    //   199: aload 6
    //   201: iconst_1
    //   202: anewarray 182	java/lang/String
    //   205: dup
    //   206: iconst_0
    //   207: aload 15
    //   209: aastore
    //   210: aconst_null
    //   211: aconst_null
    //   212: ldc_w 547
    //   215: invokevirtual 551	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   218: astore 9
    //   220: aload 12
    //   222: astore 10
    //   224: aload 14
    //   226: astore 6
    //   228: aload 9
    //   230: astore 7
    //   232: aload 9
    //   234: invokeinterface 557 1 0
    //   239: ifeq +118 -> 357
    //   242: aload 9
    //   244: astore 7
    //   246: new 559	java/util/ArrayList
    //   249: dup
    //   250: invokespecial 560	java/util/ArrayList:<init>	()V
    //   253: astore 6
    //   255: aload 13
    //   257: astore 8
    //   259: aload 11
    //   261: astore 10
    //   263: aload 9
    //   265: aload 9
    //   267: ldc_w 487
    //   270: invokeinterface 563 2 0
    //   275: invokeinterface 567 2 0
    //   280: iconst_1
    //   281: if_icmpne +495 -> 776
    //   284: aload 9
    //   286: aload 9
    //   288: ldc_w 477
    //   291: invokeinterface 563 2 0
    //   296: invokeinterface 567 2 0
    //   301: invokestatic 407	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   304: astore 7
    //   306: aload 10
    //   308: astore 11
    //   310: aload 9
    //   312: invokeinterface 570 1 0
    //   317: istore 5
    //   319: aload 11
    //   321: astore 10
    //   323: aload 7
    //   325: astore 8
    //   327: iload 5
    //   329: ifne -66 -> 263
    //   332: iload 4
    //   334: ifeq +629 -> 963
    //   337: aload 11
    //   339: ifnull +624 -> 963
    //   342: new 106	org/json/JSONObject
    //   345: dup
    //   346: aload 11
    //   348: invokespecial 113	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   351: astore 10
    //   353: aload 7
    //   355: astore 8
    //   357: aload 9
    //   359: ifnull +20 -> 379
    //   362: aload 9
    //   364: invokeinterface 573 1 0
    //   369: ifne +10 -> 379
    //   372: aload 9
    //   374: invokeinterface 576 1 0
    //   379: aload 8
    //   381: astore 7
    //   383: aload 8
    //   385: ifnonnull +25 -> 410
    //   388: aload 17
    //   390: invokevirtual 515	java/util/Random:nextInt	()I
    //   393: invokestatic 407	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   396: astore 7
    //   398: aload 18
    //   400: aload 15
    //   402: aload 7
    //   404: invokevirtual 534	java/lang/Integer:intValue	()I
    //   407: invokestatic 578	com/onesignal/GenerateNotification:createSummaryIdDatabaseEntry	(Lcom/onesignal/OneSignalDbHelper;Ljava/lang/String;I)V
    //   410: aload 17
    //   412: invokevirtual 515	java/util/Random:nextInt	()I
    //   415: aload 7
    //   417: invokevirtual 534	java/lang/Integer:intValue	()I
    //   420: aload 10
    //   422: aload 15
    //   424: invokestatic 580	com/onesignal/GenerateNotification:createBaseSummaryIntent	(ILorg/json/JSONObject;Ljava/lang/String;)Landroid/content/Intent;
    //   427: invokestatic 311	com/onesignal/GenerateNotification:getNewActionPendingIntent	(ILandroid/content/Intent;)Landroid/app/PendingIntent;
    //   430: astore 8
    //   432: aload 6
    //   434: ifnull +704 -> 1138
    //   437: iload 4
    //   439: ifeq +14 -> 453
    //   442: aload 6
    //   444: invokeinterface 583 1 0
    //   449: iconst_1
    //   450: if_icmpgt +18 -> 468
    //   453: iload 4
    //   455: ifne +683 -> 1138
    //   458: aload 6
    //   460: invokeinterface 583 1 0
    //   465: ifle +673 -> 1138
    //   468: aload 6
    //   470: invokeinterface 583 1 0
    //   475: istore_3
    //   476: iload 4
    //   478: ifeq +521 -> 999
    //   481: iconst_0
    //   482: istore_2
    //   483: iload_3
    //   484: iload_2
    //   485: iadd
    //   486: istore_2
    //   487: aload 10
    //   489: ldc_w 585
    //   492: aconst_null
    //   493: invokevirtual 110	org/json/JSONObject:optString	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   496: astore_1
    //   497: aload_1
    //   498: ifnonnull +506 -> 1004
    //   501: new 271	java/lang/StringBuilder
    //   504: dup
    //   505: invokespecial 272	java/lang/StringBuilder:<init>	()V
    //   508: iload_2
    //   509: invokevirtual 281	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   512: ldc_w 587
    //   515: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   518: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   521: astore_1
    //   522: aload_0
    //   523: invokestatic 591	com/onesignal/GenerateNotification:getBaseOneSignalNotificationBuilder	(Lcom/onesignal/NotificationGenerationJob;)Lcom/onesignal/GenerateNotification$OneSignalNotificationBuilder;
    //   526: getfield 595	com/onesignal/GenerateNotification$OneSignalNotificationBuilder:compatBuilder	Landroid/support/v4/app/NotificationCompat$Builder;
    //   529: astore 9
    //   531: iload 4
    //   533: ifeq +502 -> 1035
    //   536: aload 9
    //   538: invokestatic 599	com/onesignal/GenerateNotification:removeNotifyOptions	(Landroid/support/v4/app/NotificationCompat$Builder;)V
    //   541: aload 9
    //   543: aload 8
    //   545: invokevirtual 603	android/support/v4/app/NotificationCompat$Builder:setContentIntent	(Landroid/app/PendingIntent;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   548: aload 16
    //   550: invokevirtual 606	android/support/v4/app/NotificationCompat$Builder:setDeleteIntent	(Landroid/app/PendingIntent;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   553: getstatic 28	com/onesignal/GenerateNotification:currentContext	Landroid/content/Context;
    //   556: invokevirtual 610	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   559: getstatic 28	com/onesignal/GenerateNotification:currentContext	Landroid/content/Context;
    //   562: invokevirtual 614	android/content/Context:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   565: invokevirtual 620	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   568: invokevirtual 624	android/support/v4/app/NotificationCompat$Builder:setContentTitle	(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   571: aload_1
    //   572: invokevirtual 627	android/support/v4/app/NotificationCompat$Builder:setContentText	(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   575: iload_2
    //   576: invokevirtual 631	android/support/v4/app/NotificationCompat$Builder:setNumber	(I)Landroid/support/v4/app/NotificationCompat$Builder;
    //   579: invokestatic 634	com/onesignal/GenerateNotification:getDefaultSmallIconId	()I
    //   582: invokevirtual 637	android/support/v4/app/NotificationCompat$Builder:setSmallIcon	(I)Landroid/support/v4/app/NotificationCompat$Builder;
    //   585: invokestatic 641	com/onesignal/GenerateNotification:getDefaultLargeIcon	()Landroid/graphics/Bitmap;
    //   588: invokevirtual 645	android/support/v4/app/NotificationCompat$Builder:setLargeIcon	(Landroid/graphics/Bitmap;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   591: iload 4
    //   593: invokevirtual 649	android/support/v4/app/NotificationCompat$Builder:setOnlyAlertOnce	(Z)Landroid/support/v4/app/NotificationCompat$Builder;
    //   596: aload 15
    //   598: invokevirtual 653	android/support/v4/app/NotificationCompat$Builder:setGroup	(Ljava/lang/String;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   601: iconst_1
    //   602: invokevirtual 656	android/support/v4/app/NotificationCompat$Builder:setGroupSummary	(Z)Landroid/support/v4/app/NotificationCompat$Builder;
    //   605: pop
    //   606: aload 9
    //   608: iconst_1
    //   609: invokevirtual 659	android/support/v4/app/NotificationCompat$Builder:setGroupAlertBehavior	(I)Landroid/support/v4/app/NotificationCompat$Builder;
    //   612: pop
    //   613: iload 4
    //   615: ifne +10 -> 625
    //   618: aload 9
    //   620: aload_1
    //   621: invokevirtual 662	android/support/v4/app/NotificationCompat$Builder:setTicker	(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   624: pop
    //   625: new 664	android/support/v4/app/NotificationCompat$InboxStyle
    //   628: dup
    //   629: invokespecial 665	android/support/v4/app/NotificationCompat$InboxStyle:<init>	()V
    //   632: astore 10
    //   634: iload 4
    //   636: ifne +105 -> 741
    //   639: aconst_null
    //   640: astore 8
    //   642: aload_0
    //   643: invokevirtual 668	com/onesignal/NotificationGenerationJob:getTitle	()Ljava/lang/CharSequence;
    //   646: ifnull +14 -> 660
    //   649: aload_0
    //   650: invokevirtual 668	com/onesignal/NotificationGenerationJob:getTitle	()Ljava/lang/CharSequence;
    //   653: invokeinterface 669 1 0
    //   658: astore 8
    //   660: aload 8
    //   662: ifnonnull +413 -> 1075
    //   665: ldc_w 274
    //   668: astore 8
    //   670: aload_0
    //   671: invokevirtual 672	com/onesignal/NotificationGenerationJob:getBody	()Ljava/lang/CharSequence;
    //   674: invokeinterface 669 1 0
    //   679: astore_0
    //   680: new 674	android/text/SpannableString
    //   683: dup
    //   684: new 271	java/lang/StringBuilder
    //   687: dup
    //   688: invokespecial 272	java/lang/StringBuilder:<init>	()V
    //   691: aload 8
    //   693: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   696: aload_0
    //   697: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   700: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   703: invokespecial 677	android/text/SpannableString:<init>	(Ljava/lang/CharSequence;)V
    //   706: astore_0
    //   707: aload 8
    //   709: invokevirtual 678	java/lang/String:length	()I
    //   712: ifle +22 -> 734
    //   715: aload_0
    //   716: new 680	android/text/style/StyleSpan
    //   719: dup
    //   720: iconst_1
    //   721: invokespecial 683	android/text/style/StyleSpan:<init>	(I)V
    //   724: iconst_0
    //   725: aload 8
    //   727: invokevirtual 678	java/lang/String:length	()I
    //   730: iconst_0
    //   731: invokevirtual 687	android/text/SpannableString:setSpan	(Ljava/lang/Object;III)V
    //   734: aload 10
    //   736: aload_0
    //   737: invokevirtual 691	android/support/v4/app/NotificationCompat$InboxStyle:addLine	(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$InboxStyle;
    //   740: pop
    //   741: aload 6
    //   743: invokeinterface 695 1 0
    //   748: astore_0
    //   749: aload_0
    //   750: invokeinterface 700 1 0
    //   755: ifeq +346 -> 1101
    //   758: aload 10
    //   760: aload_0
    //   761: invokeinterface 703 1 0
    //   766: checkcast 674	android/text/SpannableString
    //   769: invokevirtual 691	android/support/v4/app/NotificationCompat$InboxStyle:addLine	(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$InboxStyle;
    //   772: pop
    //   773: goto -24 -> 749
    //   776: aload 9
    //   778: aload 9
    //   780: ldc_w 543
    //   783: invokeinterface 563 2 0
    //   788: invokeinterface 704 2 0
    //   793: astore 7
    //   795: aload 7
    //   797: ifnonnull +133 -> 930
    //   800: ldc_w 274
    //   803: astore 7
    //   805: aload 9
    //   807: aload 9
    //   809: ldc_w 545
    //   812: invokeinterface 563 2 0
    //   817: invokeinterface 704 2 0
    //   822: astore 11
    //   824: new 674	android/text/SpannableString
    //   827: dup
    //   828: new 271	java/lang/StringBuilder
    //   831: dup
    //   832: invokespecial 272	java/lang/StringBuilder:<init>	()V
    //   835: aload 7
    //   837: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   840: aload 11
    //   842: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   845: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   848: invokespecial 677	android/text/SpannableString:<init>	(Ljava/lang/CharSequence;)V
    //   851: astore 11
    //   853: aload 7
    //   855: invokevirtual 678	java/lang/String:length	()I
    //   858: ifle +23 -> 881
    //   861: aload 11
    //   863: new 680	android/text/style/StyleSpan
    //   866: dup
    //   867: iconst_1
    //   868: invokespecial 683	android/text/style/StyleSpan:<init>	(I)V
    //   871: iconst_0
    //   872: aload 7
    //   874: invokevirtual 678	java/lang/String:length	()I
    //   877: iconst_0
    //   878: invokevirtual 687	android/text/SpannableString:setSpan	(Ljava/lang/Object;III)V
    //   881: aload 6
    //   883: aload 11
    //   885: invokeinterface 705 2 0
    //   890: pop
    //   891: aload 10
    //   893: astore 11
    //   895: aload 8
    //   897: astore 7
    //   899: aload 10
    //   901: ifnonnull -591 -> 310
    //   904: aload 9
    //   906: aload 9
    //   908: ldc_w 541
    //   911: invokeinterface 563 2 0
    //   916: invokeinterface 704 2 0
    //   921: astore 11
    //   923: aload 8
    //   925: astore 7
    //   927: goto -617 -> 310
    //   930: new 271	java/lang/StringBuilder
    //   933: dup
    //   934: invokespecial 272	java/lang/StringBuilder:<init>	()V
    //   937: aload 7
    //   939: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   942: ldc_w 707
    //   945: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   948: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   951: astore 7
    //   953: goto -148 -> 805
    //   956: astore 8
    //   958: aload 8
    //   960: invokestatic 329	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   963: aload 12
    //   965: astore 10
    //   967: aload 7
    //   969: astore 8
    //   971: goto -614 -> 357
    //   974: astore_0
    //   975: aload 7
    //   977: ifnull +20 -> 997
    //   980: aload 7
    //   982: invokeinterface 573 1 0
    //   987: ifne +10 -> 997
    //   990: aload 7
    //   992: invokeinterface 576 1 0
    //   997: aload_0
    //   998: athrow
    //   999: iconst_1
    //   1000: istore_2
    //   1001: goto -518 -> 483
    //   1004: aload_1
    //   1005: ldc_w 709
    //   1008: new 271	java/lang/StringBuilder
    //   1011: dup
    //   1012: invokespecial 272	java/lang/StringBuilder:<init>	()V
    //   1015: ldc_w 274
    //   1018: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1021: iload_2
    //   1022: invokevirtual 281	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1025: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1028: invokevirtual 713	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   1031: astore_1
    //   1032: goto -510 -> 522
    //   1035: aload_0
    //   1036: getfield 444	com/onesignal/NotificationGenerationJob:overriddenSound	Landroid/net/Uri;
    //   1039: ifnull +13 -> 1052
    //   1042: aload 9
    //   1044: aload_0
    //   1045: getfield 444	com/onesignal/NotificationGenerationJob:overriddenSound	Landroid/net/Uri;
    //   1048: invokevirtual 455	android/support/v4/app/NotificationCompat$Builder:setSound	(Landroid/net/Uri;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   1051: pop
    //   1052: aload_0
    //   1053: getfield 441	com/onesignal/NotificationGenerationJob:overriddenFlags	Ljava/lang/Integer;
    //   1056: ifnull -515 -> 541
    //   1059: aload 9
    //   1061: aload_0
    //   1062: getfield 441	com/onesignal/NotificationGenerationJob:overriddenFlags	Ljava/lang/Integer;
    //   1065: invokevirtual 534	java/lang/Integer:intValue	()I
    //   1068: invokevirtual 716	android/support/v4/app/NotificationCompat$Builder:setDefaults	(I)Landroid/support/v4/app/NotificationCompat$Builder;
    //   1071: pop
    //   1072: goto -531 -> 541
    //   1075: new 271	java/lang/StringBuilder
    //   1078: dup
    //   1079: invokespecial 272	java/lang/StringBuilder:<init>	()V
    //   1082: aload 8
    //   1084: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1087: ldc_w 707
    //   1090: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1093: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1096: astore 8
    //   1098: goto -428 -> 670
    //   1101: aload 10
    //   1103: aload_1
    //   1104: invokevirtual 719	android/support/v4/app/NotificationCompat$InboxStyle:setBigContentTitle	(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$InboxStyle;
    //   1107: pop
    //   1108: aload 9
    //   1110: aload 10
    //   1112: invokevirtual 216	android/support/v4/app/NotificationCompat$Builder:setStyle	(Landroid/support/v4/app/NotificationCompat$Style;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   1115: pop
    //   1116: aload 9
    //   1118: invokevirtual 459	android/support/v4/app/NotificationCompat$Builder:build	()Landroid/app/Notification;
    //   1121: astore_0
    //   1122: getstatic 28	com/onesignal/GenerateNotification:currentContext	Landroid/content/Context;
    //   1125: invokestatic 725	android/support/v4/app/NotificationManagerCompat:from	(Landroid/content/Context;)Landroid/support/v4/app/NotificationManagerCompat;
    //   1128: aload 7
    //   1130: invokevirtual 534	java/lang/Integer:intValue	()I
    //   1133: aload_0
    //   1134: invokevirtual 729	android/support/v4/app/NotificationManagerCompat:notify	(ILandroid/app/Notification;)V
    //   1137: return
    //   1138: aload_1
    //   1139: getfield 595	com/onesignal/GenerateNotification$OneSignalNotificationBuilder:compatBuilder	Landroid/support/v4/app/NotificationCompat$Builder;
    //   1142: astore_0
    //   1143: aload_0
    //   1144: getfield 733	android/support/v4/app/NotificationCompat$Builder:mActions	Ljava/util/ArrayList;
    //   1147: invokevirtual 736	java/util/ArrayList:clear	()V
    //   1150: aload 10
    //   1152: aload_0
    //   1153: aload 7
    //   1155: invokevirtual 534	java/lang/Integer:intValue	()I
    //   1158: aload 15
    //   1160: invokestatic 738	com/onesignal/GenerateNotification:addNotificationActionButtons	(Lorg/json/JSONObject;Landroid/support/v4/app/NotificationCompat$Builder;ILjava/lang/String;)V
    //   1163: aload_0
    //   1164: aload 8
    //   1166: invokevirtual 603	android/support/v4/app/NotificationCompat$Builder:setContentIntent	(Landroid/app/PendingIntent;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   1169: aload 16
    //   1171: invokevirtual 606	android/support/v4/app/NotificationCompat$Builder:setDeleteIntent	(Landroid/app/PendingIntent;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   1174: iload 4
    //   1176: invokevirtual 649	android/support/v4/app/NotificationCompat$Builder:setOnlyAlertOnce	(Z)Landroid/support/v4/app/NotificationCompat$Builder;
    //   1179: aload 15
    //   1181: invokevirtual 653	android/support/v4/app/NotificationCompat$Builder:setGroup	(Ljava/lang/String;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   1184: iconst_1
    //   1185: invokevirtual 656	android/support/v4/app/NotificationCompat$Builder:setGroupSummary	(Z)Landroid/support/v4/app/NotificationCompat$Builder;
    //   1188: pop
    //   1189: aload_0
    //   1190: iconst_1
    //   1191: invokevirtual 659	android/support/v4/app/NotificationCompat$Builder:setGroupAlertBehavior	(I)Landroid/support/v4/app/NotificationCompat$Builder;
    //   1194: pop
    //   1195: aload_0
    //   1196: invokevirtual 459	android/support/v4/app/NotificationCompat$Builder:build	()Landroid/app/Notification;
    //   1199: astore_0
    //   1200: aload_1
    //   1201: aload_0
    //   1202: invokestatic 740	com/onesignal/GenerateNotification:addXiaomiSettings	(Lcom/onesignal/GenerateNotification$OneSignalNotificationBuilder;Landroid/app/Notification;)V
    //   1205: goto -83 -> 1122
    //   1208: astore 8
    //   1210: goto -597 -> 613
    //   1213: astore 6
    //   1215: goto -20 -> 1195
    //   1218: astore_0
    //   1219: aload 9
    //   1221: astore 7
    //   1223: goto -248 -> 975
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1226	0	paramNotificationGenerationJob	NotificationGenerationJob
    //   0	1226	1	paramOneSignalNotificationBuilder	OneSignalNotificationBuilder
    //   482	540	2	i	int
    //   475	11	3	j	int
    //   4	1171	4	bool1	boolean
    //   317	11	5	bool2	boolean
    //   95	787	6	localObject1	Object
    //   1213	1	6	localThrowable1	Throwable
    //   79	1143	7	localObject2	Object
    //   55	869	8	localObject3	Object
    //   956	3	8	localJSONException	JSONException
    //   969	196	8	localObject4	Object
    //   1208	1	8	localThrowable2	Throwable
    //   75	1145	9	localObject5	Object
    //   86	1065	10	localObject6	Object
    //   61	861	11	localObject7	Object
    //   10	954	12	localJSONObject	JSONObject
    //   58	198	13	localObject8	Object
    //   64	161	14	localObject9	Object
    //   21	1159	15	str	String
    //   52	1118	16	localPendingIntent	PendingIntent
    //   30	381	17	localRandom	Random
    //   72	327	18	localOneSignalDbHelper	OneSignalDbHelper
    // Exception table:
    //   from	to	target	type
    //   342	353	956	org/json/JSONException
    //   81	88	974	finally
    //   110	121	974	finally
    //   125	156	974	finally
    //   160	220	974	finally
    //   232	242	974	finally
    //   246	255	974	finally
    //   606	613	1208	java/lang/Throwable
    //   1189	1195	1213	java/lang/Throwable
    //   263	306	1218	finally
    //   310	319	1218	finally
    //   342	353	1218	finally
    //   776	795	1218	finally
    //   805	881	1218	finally
    //   881	891	1218	finally
    //   904	923	1218	finally
    //   930	953	1218	finally
    //   958	963	1218	finally
  }
  
  static void fromJsonPayload(NotificationGenerationJob paramNotificationGenerationJob)
  {
    setStatics(paramNotificationGenerationJob.context);
    if ((!paramNotificationGenerationJob.restoring) && (paramNotificationGenerationJob.showAsAlert) && (ActivityLifecycleHandler.curActivity != null))
    {
      showNotificationAsAlert(paramNotificationGenerationJob.jsonPayload, ActivityLifecycleHandler.curActivity, paramNotificationGenerationJob.getAndroidId().intValue());
      return;
    }
    showNotification(paramNotificationGenerationJob);
  }
  
  private static BigInteger getAccentColor(JSONObject paramJSONObject)
  {
    try
    {
      if (paramJSONObject.has("bgac"))
      {
        paramJSONObject = new BigInteger(paramJSONObject.optString("bgac", null), 16);
        return paramJSONObject;
      }
    }
    catch (Throwable paramJSONObject)
    {
      try
      {
        paramJSONObject = OSUtils.getManifestMeta(currentContext, "com.onesignal.NotificationAccentColor.DEFAULT");
        if (paramJSONObject != null)
        {
          paramJSONObject = new BigInteger(paramJSONObject, 16);
          return paramJSONObject;
        }
      }
      catch (Throwable paramJSONObject) {}
    }
    return null;
  }
  
  /* Error */
  private static OneSignalNotificationBuilder getBaseOneSignalNotificationBuilder(NotificationGenerationJob paramNotificationGenerationJob)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 509	com/onesignal/NotificationGenerationJob:jsonPayload	Lorg/json/JSONObject;
    //   4: astore 4
    //   6: new 12	com/onesignal/GenerateNotification$OneSignalNotificationBuilder
    //   9: dup
    //   10: aconst_null
    //   11: invokespecial 781	com/onesignal/GenerateNotification$OneSignalNotificationBuilder:<init>	(Lcom/onesignal/GenerateNotification$1;)V
    //   14: astore 5
    //   16: aload_0
    //   17: invokestatic 787	com/onesignal/NotificationChannelManager:createNotificationChannel	(Lcom/onesignal/NotificationGenerationJob;)Ljava/lang/String;
    //   20: astore_3
    //   21: new 208	android/support/v4/app/NotificationCompat$Builder
    //   24: dup
    //   25: getstatic 28	com/onesignal/GenerateNotification:currentContext	Landroid/content/Context;
    //   28: aload_3
    //   29: invokespecial 790	android/support/v4/app/NotificationCompat$Builder:<init>	(Landroid/content/Context;Ljava/lang/String;)V
    //   32: astore_3
    //   33: aload 4
    //   35: ldc -102
    //   37: aconst_null
    //   38: invokevirtual 110	org/json/JSONObject:optString	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   41: astore 6
    //   43: aload_3
    //   44: iconst_1
    //   45: invokevirtual 793	android/support/v4/app/NotificationCompat$Builder:setAutoCancel	(Z)Landroid/support/v4/app/NotificationCompat$Builder;
    //   48: aload 4
    //   50: invokestatic 797	com/onesignal/GenerateNotification:getSmallIconId	(Lorg/json/JSONObject;)I
    //   53: invokevirtual 637	android/support/v4/app/NotificationCompat$Builder:setSmallIcon	(I)Landroid/support/v4/app/NotificationCompat$Builder;
    //   56: new 799	android/support/v4/app/NotificationCompat$BigTextStyle
    //   59: dup
    //   60: invokespecial 800	android/support/v4/app/NotificationCompat$BigTextStyle:<init>	()V
    //   63: aload 6
    //   65: invokevirtual 804	android/support/v4/app/NotificationCompat$BigTextStyle:bigText	(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$BigTextStyle;
    //   68: invokevirtual 216	android/support/v4/app/NotificationCompat$Builder:setStyle	(Landroid/support/v4/app/NotificationCompat$Style;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   71: aload 6
    //   73: invokevirtual 627	android/support/v4/app/NotificationCompat$Builder:setContentText	(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   76: aload 6
    //   78: invokevirtual 662	android/support/v4/app/NotificationCompat$Builder:setTicker	(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   81: pop
    //   82: getstatic 102	android/os/Build$VERSION:SDK_INT	I
    //   85: bipush 24
    //   87: if_icmplt +20 -> 107
    //   90: aload 4
    //   92: ldc_w 543
    //   95: invokevirtual 157	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   98: ldc_w 274
    //   101: invokevirtual 185	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   104: ifne +13 -> 117
    //   107: aload_3
    //   108: aload 4
    //   110: invokestatic 41	com/onesignal/GenerateNotification:getTitle	(Lorg/json/JSONObject;)Ljava/lang/CharSequence;
    //   113: invokevirtual 624	android/support/v4/app/NotificationCompat$Builder:setContentTitle	(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   116: pop
    //   117: iconst_0
    //   118: istore_2
    //   119: iload_2
    //   120: istore_1
    //   121: getstatic 28	com/onesignal/GenerateNotification:currentContext	Landroid/content/Context;
    //   124: invokestatic 808	com/onesignal/OneSignal:getVibrate	(Landroid/content/Context;)Z
    //   127: ifeq +52 -> 179
    //   130: iload_2
    //   131: istore_1
    //   132: aload 4
    //   134: ldc_w 810
    //   137: iconst_1
    //   138: invokevirtual 814	org/json/JSONObject:optInt	(Ljava/lang/String;I)I
    //   141: iconst_1
    //   142: if_icmpne +37 -> 179
    //   145: aload 4
    //   147: ldc_w 816
    //   150: invokevirtual 175	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   153: ifeq +300 -> 453
    //   156: aload 4
    //   158: invokestatic 820	com/onesignal/OSUtils:parseVibrationPattern	(Lorg/json/JSONObject;)[J
    //   161: astore 7
    //   163: iload_2
    //   164: istore_1
    //   165: aload 7
    //   167: ifnull +12 -> 179
    //   170: aload_3
    //   171: aload 7
    //   173: invokevirtual 824	android/support/v4/app/NotificationCompat$Builder:setVibrate	([J)Landroid/support/v4/app/NotificationCompat$Builder;
    //   176: pop
    //   177: iload_2
    //   178: istore_1
    //   179: aload 4
    //   181: ldc_w 826
    //   184: invokevirtual 175	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   187: ifeq +280 -> 467
    //   190: aload 4
    //   192: ldc_w 828
    //   195: iconst_1
    //   196: invokevirtual 814	org/json/JSONObject:optInt	(Ljava/lang/String;I)I
    //   199: iconst_1
    //   200: if_icmpne +267 -> 467
    //   203: aload_3
    //   204: new 771	java/math/BigInteger
    //   207: dup
    //   208: aload 4
    //   210: ldc_w 826
    //   213: invokevirtual 157	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   216: bipush 16
    //   218: invokespecial 772	java/math/BigInteger:<init>	(Ljava/lang/String;I)V
    //   221: invokevirtual 829	java/math/BigInteger:intValue	()I
    //   224: sipush 2000
    //   227: sipush 5000
    //   230: invokevirtual 833	android/support/v4/app/NotificationCompat$Builder:setLights	(III)Landroid/support/v4/app/NotificationCompat$Builder;
    //   233: pop
    //   234: aload_0
    //   235: getfield 837	com/onesignal/NotificationGenerationJob:shownTimeStamp	Ljava/lang/Long;
    //   238: ifnull +19 -> 257
    //   241: aload_3
    //   242: aload_0
    //   243: getfield 837	com/onesignal/NotificationGenerationJob:shownTimeStamp	Ljava/lang/Long;
    //   246: invokevirtual 843	java/lang/Long:longValue	()J
    //   249: ldc2_w 844
    //   252: lmul
    //   253: invokevirtual 849	android/support/v4/app/NotificationCompat$Builder:setWhen	(J)Landroid/support/v4/app/NotificationCompat$Builder;
    //   256: pop
    //   257: aload 4
    //   259: invokestatic 851	com/onesignal/GenerateNotification:getAccentColor	(Lorg/json/JSONObject;)Ljava/math/BigInteger;
    //   262: astore_0
    //   263: aload_0
    //   264: ifnull +12 -> 276
    //   267: aload_3
    //   268: aload_0
    //   269: invokevirtual 829	java/math/BigInteger:intValue	()I
    //   272: invokevirtual 854	android/support/v4/app/NotificationCompat$Builder:setColor	(I)Landroid/support/v4/app/NotificationCompat$Builder;
    //   275: pop
    //   276: iconst_1
    //   277: istore_2
    //   278: aload 4
    //   280: ldc_w 856
    //   283: invokevirtual 175	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   286: ifeq +15 -> 301
    //   289: aload 4
    //   291: ldc_w 856
    //   294: invokevirtual 157	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   297: invokestatic 859	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   300: istore_2
    //   301: aload_3
    //   302: iload_2
    //   303: invokevirtual 862	android/support/v4/app/NotificationCompat$Builder:setVisibility	(I)Landroid/support/v4/app/NotificationCompat$Builder;
    //   306: pop
    //   307: aload 4
    //   309: invokestatic 866	com/onesignal/GenerateNotification:getLargeIcon	(Lorg/json/JSONObject;)Landroid/graphics/Bitmap;
    //   312: astore_0
    //   313: aload_0
    //   314: ifnull +15 -> 329
    //   317: aload 5
    //   319: iconst_1
    //   320: putfield 334	com/onesignal/GenerateNotification$OneSignalNotificationBuilder:hasLargeIcon	Z
    //   323: aload_3
    //   324: aload_0
    //   325: invokevirtual 645	android/support/v4/app/NotificationCompat$Builder:setLargeIcon	(Landroid/graphics/Bitmap;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   328: pop
    //   329: aload 4
    //   331: ldc_w 868
    //   334: aconst_null
    //   335: invokevirtual 110	org/json/JSONObject:optString	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   338: invokestatic 119	com/onesignal/GenerateNotification:getBitmap	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   341: astore_0
    //   342: aload_0
    //   343: ifnull +24 -> 367
    //   346: aload_3
    //   347: new 870	android/support/v4/app/NotificationCompat$BigPictureStyle
    //   350: dup
    //   351: invokespecial 871	android/support/v4/app/NotificationCompat$BigPictureStyle:<init>	()V
    //   354: aload_0
    //   355: invokevirtual 875	android/support/v4/app/NotificationCompat$BigPictureStyle:bigPicture	(Landroid/graphics/Bitmap;)Landroid/support/v4/app/NotificationCompat$BigPictureStyle;
    //   358: aload 6
    //   360: invokevirtual 879	android/support/v4/app/NotificationCompat$BigPictureStyle:setSummaryText	(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$BigPictureStyle;
    //   363: invokevirtual 216	android/support/v4/app/NotificationCompat$Builder:setStyle	(Landroid/support/v4/app/NotificationCompat$Style;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   366: pop
    //   367: iload_1
    //   368: istore_2
    //   369: aload 4
    //   371: invokestatic 883	com/onesignal/GenerateNotification:isSoundEnabled	(Lorg/json/JSONObject;)Z
    //   374: ifeq +31 -> 405
    //   377: getstatic 28	com/onesignal/GenerateNotification:currentContext	Landroid/content/Context;
    //   380: aload 4
    //   382: ldc_w 884
    //   385: aconst_null
    //   386: invokevirtual 110	org/json/JSONObject:optString	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   389: invokestatic 888	com/onesignal/OSUtils:getSoundUri	(Landroid/content/Context;Ljava/lang/String;)Landroid/net/Uri;
    //   392: astore_0
    //   393: aload_0
    //   394: ifnull +80 -> 474
    //   397: aload_3
    //   398: aload_0
    //   399: invokevirtual 455	android/support/v4/app/NotificationCompat$Builder:setSound	(Landroid/net/Uri;)Landroid/support/v4/app/NotificationCompat$Builder;
    //   402: pop
    //   403: iload_1
    //   404: istore_2
    //   405: aload_3
    //   406: iload_2
    //   407: invokevirtual 716	android/support/v4/app/NotificationCompat$Builder:setDefaults	(I)Landroid/support/v4/app/NotificationCompat$Builder;
    //   410: pop
    //   411: aload_3
    //   412: aload 4
    //   414: ldc_w 890
    //   417: bipush 6
    //   419: invokevirtual 814	org/json/JSONObject:optInt	(Ljava/lang/String;I)I
    //   422: invokestatic 893	com/onesignal/GenerateNotification:osPriorityToAndroidPriority	(I)I
    //   425: invokevirtual 896	android/support/v4/app/NotificationCompat$Builder:setPriority	(I)Landroid/support/v4/app/NotificationCompat$Builder;
    //   428: pop
    //   429: aload 5
    //   431: aload_3
    //   432: putfield 595	com/onesignal/GenerateNotification$OneSignalNotificationBuilder:compatBuilder	Landroid/support/v4/app/NotificationCompat$Builder;
    //   435: aload 5
    //   437: areturn
    //   438: astore_3
    //   439: new 208	android/support/v4/app/NotificationCompat$Builder
    //   442: dup
    //   443: getstatic 28	com/onesignal/GenerateNotification:currentContext	Landroid/content/Context;
    //   446: invokespecial 898	android/support/v4/app/NotificationCompat$Builder:<init>	(Landroid/content/Context;)V
    //   449: astore_3
    //   450: goto -417 -> 33
    //   453: iconst_2
    //   454: istore_1
    //   455: goto -276 -> 179
    //   458: astore 7
    //   460: iload_1
    //   461: iconst_4
    //   462: ior
    //   463: istore_1
    //   464: goto -230 -> 234
    //   467: iload_1
    //   468: iconst_4
    //   469: ior
    //   470: istore_1
    //   471: goto -237 -> 234
    //   474: iload_1
    //   475: iconst_1
    //   476: ior
    //   477: istore_2
    //   478: goto -73 -> 405
    //   481: astore_0
    //   482: goto -175 -> 307
    //   485: astore_0
    //   486: goto -210 -> 276
    //   489: astore_0
    //   490: goto -233 -> 257
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	493	0	paramNotificationGenerationJob	NotificationGenerationJob
    //   120	357	1	i	int
    //   118	360	2	j	int
    //   20	412	3	localObject	Object
    //   438	1	3	localThrowable1	Throwable
    //   449	1	3	localBuilder	NotificationCompat.Builder
    //   4	409	4	localJSONObject	JSONObject
    //   14	422	5	localOneSignalNotificationBuilder	OneSignalNotificationBuilder
    //   41	318	6	str	String
    //   161	11	7	arrayOfLong	long[]
    //   458	1	7	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   16	33	438	java/lang/Throwable
    //   203	234	458	java/lang/Throwable
    //   278	301	481	java/lang/Throwable
    //   301	307	481	java/lang/Throwable
    //   257	263	485	java/lang/Throwable
    //   267	276	485	java/lang/Throwable
    //   241	257	489	java/lang/Throwable
  }
  
  private static Bitmap getBitmap(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    String str = paramString.trim();
    if ((str.startsWith("http://")) || (str.startsWith("https://"))) {
      return getBitmapFromURL(str);
    }
    return getBitmapFromAssetsOrResourceName(paramString);
  }
  
  /* Error */
  private static Bitmap getBitmapFromAssetsOrResourceName(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: getstatic 28	com/onesignal/GenerateNotification:currentContext	Landroid/content/Context;
    //   5: invokevirtual 915	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   8: aload_0
    //   9: invokevirtual 921	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   12: invokestatic 927	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   15: astore_3
    //   16: aload_3
    //   17: astore_2
    //   18: aload_2
    //   19: ifnull +5 -> 24
    //   22: aload_2
    //   23: areturn
    //   24: iconst_5
    //   25: anewarray 182	java/lang/String
    //   28: dup
    //   29: iconst_0
    //   30: ldc_w 929
    //   33: aastore
    //   34: dup
    //   35: iconst_1
    //   36: ldc_w 931
    //   39: aastore
    //   40: dup
    //   41: iconst_2
    //   42: ldc_w 933
    //   45: aastore
    //   46: dup
    //   47: iconst_3
    //   48: ldc_w 935
    //   51: aastore
    //   52: dup
    //   53: iconst_4
    //   54: ldc_w 937
    //   57: aastore
    //   58: invokestatic 943	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   61: invokeinterface 944 1 0
    //   66: astore 4
    //   68: aload 4
    //   70: invokeinterface 700 1 0
    //   75: ifeq +53 -> 128
    //   78: aload 4
    //   80: invokeinterface 703 1 0
    //   85: checkcast 182	java/lang/String
    //   88: astore_3
    //   89: getstatic 28	com/onesignal/GenerateNotification:currentContext	Landroid/content/Context;
    //   92: invokevirtual 915	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   95: new 271	java/lang/StringBuilder
    //   98: dup
    //   99: invokespecial 272	java/lang/StringBuilder:<init>	()V
    //   102: aload_0
    //   103: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: aload_3
    //   107: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   113: invokevirtual 921	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   116: invokestatic 927	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   119: astore_3
    //   120: aload_3
    //   121: astore_2
    //   122: aload_3
    //   123: ifnull -55 -> 68
    //   126: aload_3
    //   127: areturn
    //   128: aload_0
    //   129: invokestatic 317	com/onesignal/GenerateNotification:getResourceIcon	(Ljava/lang/String;)I
    //   132: istore_1
    //   133: iload_1
    //   134: ifeq +14 -> 148
    //   137: getstatic 32	com/onesignal/GenerateNotification:contextResources	Landroid/content/res/Resources;
    //   140: iload_1
    //   141: invokestatic 948	android/graphics/BitmapFactory:decodeResource	(Landroid/content/res/Resources;I)Landroid/graphics/Bitmap;
    //   144: astore_0
    //   145: aload_0
    //   146: areturn
    //   147: astore_0
    //   148: aconst_null
    //   149: areturn
    //   150: astore_3
    //   151: aload_2
    //   152: astore_3
    //   153: goto -33 -> 120
    //   156: astore_3
    //   157: goto -139 -> 18
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	160	0	paramString	String
    //   132	9	1	i	int
    //   1	151	2	localObject1	Object
    //   15	112	3	localObject2	Object
    //   150	1	3	localThrowable1	Throwable
    //   152	1	3	localObject3	Object
    //   156	1	3	localThrowable2	Throwable
    //   66	13	4	localIterator	java.util.Iterator
    // Exception table:
    //   from	to	target	type
    //   24	68	147	java/lang/Throwable
    //   68	89	147	java/lang/Throwable
    //   128	133	147	java/lang/Throwable
    //   137	145	147	java/lang/Throwable
    //   89	120	150	java/lang/Throwable
    //   2	16	156	java/lang/Throwable
  }
  
  private static Bitmap getBitmapFromURL(String paramString)
  {
    try
    {
      paramString = BitmapFactory.decodeStream(new URL(paramString).openConnection().getInputStream());
      return paramString;
    }
    catch (Throwable paramString)
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Could not download image!", paramString);
    }
    return null;
  }
  
  private static Bitmap getDefaultLargeIcon()
  {
    return resizeBitmapForLargeIconArea(getBitmapFromAssetsOrResourceName("ic_onesignal_large_icon_default"));
  }
  
  private static int getDefaultSmallIconId()
  {
    int i = getDrawableId("ic_stat_onesignal_default");
    if (i != 0) {}
    int j;
    do
    {
      do
      {
        return i;
        j = getDrawableId("corona_statusbar_icon_default");
        i = j;
      } while (j != 0);
      j = getDrawableId("ic_os_notification_fallback_white_24dp");
      i = j;
    } while (j != 0);
    return 17301598;
  }
  
  private static int getDrawableId(String paramString)
  {
    return contextResources.getIdentifier(paramString, "drawable", packageName);
  }
  
  private static Bitmap getLargeIcon(JSONObject paramJSONObject)
  {
    Bitmap localBitmap = getBitmap(paramJSONObject.optString("licon"));
    paramJSONObject = localBitmap;
    if (localBitmap == null) {
      paramJSONObject = getBitmapFromAssetsOrResourceName("ic_onesignal_large_icon_default");
    }
    if (paramJSONObject == null) {
      return null;
    }
    return resizeBitmapForLargeIconArea(paramJSONObject);
  }
  
  private static PendingIntent getNewActionPendingIntent(int paramInt, Intent paramIntent)
  {
    if (openerIsBroadcast) {
      return PendingIntent.getBroadcast(currentContext, paramInt, paramIntent, 134217728);
    }
    return PendingIntent.getActivity(currentContext, paramInt, paramIntent, 134217728);
  }
  
  private static Intent getNewBaseDeleteIntent(int paramInt)
  {
    Intent localIntent = new Intent(currentContext, notificationOpenedClass).putExtra("notificationId", paramInt).putExtra("dismissed", true);
    if (openerIsBroadcast) {
      return localIntent;
    }
    return localIntent.addFlags(402718720);
  }
  
  private static Intent getNewBaseIntent(int paramInt)
  {
    Intent localIntent = new Intent(currentContext, notificationOpenedClass).putExtra("notificationId", paramInt);
    if (openerIsBroadcast) {
      return localIntent;
    }
    return localIntent.addFlags(603979776);
  }
  
  private static int getResourceIcon(String paramString)
  {
    int i;
    if (paramString == null) {
      i = 0;
    }
    int j;
    do
    {
      return i;
      String str = paramString.trim();
      if (!OSUtils.isValidResourceName(str)) {
        return 0;
      }
      j = getDrawableId(str);
      i = j;
    } while (j != 0);
    try
    {
      i = R.drawable.class.getField(paramString).getInt(null);
      return i;
    }
    catch (Throwable paramString) {}
    return 0;
  }
  
  private static int getSmallIconId(JSONObject paramJSONObject)
  {
    int i = getResourceIcon(paramJSONObject.optString("sicon", null));
    if (i != 0) {
      return i;
    }
    return getDefaultSmallIconId();
  }
  
  private static CharSequence getTitle(JSONObject paramJSONObject)
  {
    paramJSONObject = paramJSONObject.optString("title", null);
    if (paramJSONObject != null) {
      return paramJSONObject;
    }
    return currentContext.getPackageManager().getApplicationLabel(currentContext.getApplicationInfo());
  }
  
  private static boolean isSoundEnabled(JSONObject paramJSONObject)
  {
    paramJSONObject = paramJSONObject.optString("sound", null);
    if (("null".equals(paramJSONObject)) || ("nil".equals(paramJSONObject))) {
      return false;
    }
    return OneSignal.getSoundEnabled(currentContext);
  }
  
  private static int osPriorityToAndroidPriority(int paramInt)
  {
    if (paramInt > 9) {
      return 2;
    }
    if (paramInt > 7) {
      return 1;
    }
    if (paramInt > 5) {
      return 0;
    }
    if (paramInt > 3) {
      return -1;
    }
    return -2;
  }
  
  private static void removeNotifyOptions(NotificationCompat.Builder paramBuilder)
  {
    paramBuilder.setOnlyAlertOnce(true).setDefaults(0).setSound(null).setVibrate(null).setTicker(null);
  }
  
  private static Bitmap resizeBitmapForLargeIconArea(Bitmap paramBitmap)
  {
    Bitmap localBitmap;
    if (paramBitmap == null) {
      localBitmap = null;
    }
    for (;;)
    {
      return localBitmap;
      try
      {
        int j = (int)contextResources.getDimension(17104902);
        int i = (int)contextResources.getDimension(17104901);
        int m = paramBitmap.getHeight();
        int n = paramBitmap.getWidth();
        if (n <= i)
        {
          localBitmap = paramBitmap;
          if (m <= j) {
            continue;
          }
        }
        float f;
        int k;
        if (m > n)
        {
          f = n / m;
          k = (int)(j * f);
        }
        for (;;)
        {
          localBitmap = Bitmap.createScaledBitmap(paramBitmap, k, j, true);
          return localBitmap;
          k = i;
          if (n > m)
          {
            f = m / n;
            j = (int)(i * f);
            k = i;
          }
        }
        return paramBitmap;
      }
      catch (Throwable localThrowable) {}
    }
  }
  
  private static Integer safeGetColorFromHex(JSONObject paramJSONObject, String paramString)
  {
    if (paramJSONObject != null) {
      try
      {
        if (paramJSONObject.has(paramString))
        {
          int i = new BigInteger(paramJSONObject.optString(paramString), 16).intValue();
          return Integer.valueOf(i);
        }
      }
      catch (Throwable paramJSONObject) {}
    }
    return null;
  }
  
  private static void setStatics(Context paramContext)
  {
    currentContext = paramContext;
    packageName = currentContext.getPackageName();
    contextResources = currentContext.getResources();
    paramContext = currentContext.getPackageManager();
    Intent localIntent = new Intent(currentContext, NotificationOpenedReceiver.class);
    localIntent.setPackage(currentContext.getPackageName());
    if (paramContext.queryBroadcastReceivers(localIntent, 0).size() > 0)
    {
      openerIsBroadcast = true;
      notificationOpenedClass = NotificationOpenedReceiver.class;
      return;
    }
    notificationOpenedClass = NotificationOpenedActivity.class;
  }
  
  private static void setTextColor(RemoteViews paramRemoteViews, JSONObject paramJSONObject, int paramInt, String paramString1, String paramString2)
  {
    paramJSONObject = safeGetColorFromHex(paramJSONObject, paramString1);
    if (paramJSONObject != null) {
      paramRemoteViews.setTextColor(paramInt, paramJSONObject.intValue());
    }
    int i;
    do
    {
      return;
      i = contextResources.getIdentifier(paramString2, "color", packageName);
    } while (i == 0);
    paramRemoteViews.setTextColor(paramInt, AndroidSupportV4Compat.ContextCompat.getColor(currentContext, i));
  }
  
  private static void showNotification(NotificationGenerationJob paramNotificationGenerationJob)
  {
    Random localRandom = new Random();
    int i = paramNotificationGenerationJob.getAndroidId().intValue();
    JSONObject localJSONObject = paramNotificationGenerationJob.jsonPayload;
    String str = localJSONObject.optString("grp", null);
    OneSignalNotificationBuilder localOneSignalNotificationBuilder = getBaseOneSignalNotificationBuilder(paramNotificationGenerationJob);
    Object localObject = localOneSignalNotificationBuilder.compatBuilder;
    addNotificationActionButtons(localJSONObject, (NotificationCompat.Builder)localObject, i, null);
    try
    {
      addBackgroundImage(localJSONObject, (NotificationCompat.Builder)localObject);
      applyNotificationExtender(paramNotificationGenerationJob, (NotificationCompat.Builder)localObject);
      if (paramNotificationGenerationJob.restoring) {
        removeNotifyOptions((NotificationCompat.Builder)localObject);
      }
      if (str != null)
      {
        ((NotificationCompat.Builder)localObject).setContentIntent(getNewActionPendingIntent(localRandom.nextInt(), getNewBaseIntent(i).putExtra("onesignal_data", localJSONObject.toString()).putExtra("grp", str)));
        ((NotificationCompat.Builder)localObject).setDeleteIntent(getNewActionPendingIntent(localRandom.nextInt(), getNewBaseDeleteIntent(i).putExtra("grp", str)));
        ((NotificationCompat.Builder)localObject).setGroup(str);
      }
    }
    catch (Throwable localThrowable2)
    {
      try
      {
        ((NotificationCompat.Builder)localObject).setGroupAlertBehavior(1);
        localObject = createSingleNotificationBeforeSummaryBuilder(paramNotificationGenerationJob, (NotificationCompat.Builder)localObject);
        createSummaryNotification(paramNotificationGenerationJob, localOneSignalNotificationBuilder);
        for (paramNotificationGenerationJob = (NotificationGenerationJob)localObject;; paramNotificationGenerationJob = ((NotificationCompat.Builder)localObject).build())
        {
          if ((str == null) || (Build.VERSION.SDK_INT > 17))
          {
            addXiaomiSettings(localOneSignalNotificationBuilder, paramNotificationGenerationJob);
            NotificationManagerCompat.from(currentContext).notify(i, paramNotificationGenerationJob);
          }
          return;
          localThrowable2 = localThrowable2;
          OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Could not set background notification image!", localThrowable2);
          break;
          ((NotificationCompat.Builder)localObject).setContentIntent(getNewActionPendingIntent(localRandom.nextInt(), getNewBaseIntent(i).putExtra("onesignal_data", localJSONObject.toString())));
          ((NotificationCompat.Builder)localObject).setDeleteIntent(getNewActionPendingIntent(localRandom.nextInt(), getNewBaseDeleteIntent(i)));
        }
      }
      catch (Throwable localThrowable1)
      {
        for (;;) {}
      }
    }
  }
  
  private static void showNotificationAsAlert(final JSONObject paramJSONObject, Activity paramActivity, final int paramInt)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Object localObject1 = new AlertDialog.Builder(this.val$activity);
        ((AlertDialog.Builder)localObject1).setTitle(GenerateNotification.getTitle(paramJSONObject));
        ((AlertDialog.Builder)localObject1).setMessage(paramJSONObject.optString("alert"));
        ArrayList localArrayList = new ArrayList();
        final Object localObject2 = new ArrayList();
        GenerateNotification.addAlertButtons(this.val$activity, paramJSONObject, localArrayList, (List)localObject2);
        final Intent localIntent = GenerateNotification.getNewBaseIntent(paramInt);
        localIntent.putExtra("action_button", true);
        localIntent.putExtra("from_alert", true);
        localIntent.putExtra("onesignal_data", paramJSONObject.toString());
        if (paramJSONObject.has("grp")) {
          localIntent.putExtra("grp", paramJSONObject.optString("grp"));
        }
        localObject2 = new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            if (localObject2.size() > 1) {}
            try
            {
              paramAnonymous2DialogInterface = new JSONObject(GenerateNotification.1.this.val$gcmJson.toString());
              paramAnonymous2DialogInterface.put("actionSelected", localObject2.get(paramAnonymous2Int + 3));
              localIntent.putExtra("onesignal_data", paramAnonymous2DialogInterface.toString());
              NotificationOpenedProcessor.processIntent(GenerateNotification.1.this.val$activity, localIntent);
              return;
            }
            catch (Throwable paramAnonymous2DialogInterface) {}
            NotificationOpenedProcessor.processIntent(GenerateNotification.1.this.val$activity, localIntent);
            return;
          }
        };
        ((AlertDialog.Builder)localObject1).setOnCancelListener(new DialogInterface.OnCancelListener()
        {
          public void onCancel(DialogInterface paramAnonymous2DialogInterface)
          {
            NotificationOpenedProcessor.processIntent(GenerateNotification.1.this.val$activity, localIntent);
          }
        });
        int i = 0;
        if (i < localArrayList.size())
        {
          if (i == 0) {
            ((AlertDialog.Builder)localObject1).setNeutralButton((CharSequence)localArrayList.get(i), (DialogInterface.OnClickListener)localObject2);
          }
          for (;;)
          {
            i += 1;
            break;
            if (i == 1) {
              ((AlertDialog.Builder)localObject1).setNegativeButton((CharSequence)localArrayList.get(i), (DialogInterface.OnClickListener)localObject2);
            } else if (i == 2) {
              ((AlertDialog.Builder)localObject1).setPositiveButton((CharSequence)localArrayList.get(i), (DialogInterface.OnClickListener)localObject2);
            }
          }
        }
        localObject1 = ((AlertDialog.Builder)localObject1).create();
        ((AlertDialog)localObject1).setCanceledOnTouchOutside(false);
        ((AlertDialog)localObject1).show();
      }
    });
  }
  
  static void updateSummaryNotification(NotificationGenerationJob paramNotificationGenerationJob)
  {
    setStatics(paramNotificationGenerationJob.context);
    createSummaryNotification(paramNotificationGenerationJob, null);
  }
  
  private static class OneSignalNotificationBuilder
  {
    NotificationCompat.Builder compatBuilder;
    boolean hasLargeIcon;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\GenerateNotification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */