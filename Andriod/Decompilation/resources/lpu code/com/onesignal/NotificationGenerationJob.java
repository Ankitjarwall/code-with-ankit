package com.onesignal;

import android.content.Context;
import android.net.Uri;
import java.util.Random;
import org.json.JSONObject;

class NotificationGenerationJob
{
  Context context;
  JSONObject jsonPayload;
  Integer orgFlags;
  Uri orgSound;
  CharSequence overriddenBodyFromExtender;
  Integer overriddenFlags;
  Uri overriddenSound;
  CharSequence overriddenTitleFromExtender;
  NotificationExtenderService.OverrideSettings overrideSettings;
  boolean restoring;
  boolean showAsAlert;
  Long shownTimeStamp;
  
  NotificationGenerationJob(Context paramContext)
  {
    this.context = paramContext;
  }
  
  Integer getAndroidId()
  {
    if (this.overrideSettings == null) {
      this.overrideSettings = new NotificationExtenderService.OverrideSettings();
    }
    if (this.overrideSettings.androidNotificationId == null) {
      this.overrideSettings.androidNotificationId = Integer.valueOf(new Random().nextInt());
    }
    return this.overrideSettings.androidNotificationId;
  }
  
  int getAndroidIdWithoutCreate()
  {
    if ((this.overrideSettings == null) || (this.overrideSettings.androidNotificationId == null)) {
      return -1;
    }
    return this.overrideSettings.androidNotificationId.intValue();
  }
  
  CharSequence getBody()
  {
    if (this.overriddenBodyFromExtender != null) {
      return this.overriddenBodyFromExtender;
    }
    return this.jsonPayload.optString("alert", null);
  }
  
  CharSequence getTitle()
  {
    if (this.overriddenTitleFromExtender != null) {
      return this.overriddenTitleFromExtender;
    }
    return this.jsonPayload.optString("title", null);
  }
  
  boolean hasExtender()
  {
    return (this.overrideSettings != null) && (this.overrideSettings.extender != null);
  }
  
  void setAndroidIdWithOutOverriding(Integer paramInteger)
  {
    if (paramInteger == null) {}
    while ((this.overrideSettings != null) && (this.overrideSettings.androidNotificationId != null)) {
      return;
    }
    if (this.overrideSettings == null) {
      this.overrideSettings = new NotificationExtenderService.OverrideSettings();
    }
    this.overrideSettings.androidNotificationId = paramInteger;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\NotificationGenerationJob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */