package com.onesignal;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class OSNotificationPayload
{
  public List<ActionButton> actionButtons;
  public JSONObject additionalData;
  public BackgroundImageLayout backgroundImageLayout;
  public String bigPicture;
  public String body;
  public String collapseId;
  public String fromProjectNumber;
  public String groupKey;
  public String groupMessage;
  public String largeIcon;
  public String launchURL;
  public String ledColor;
  public int lockScreenVisibility = 1;
  public String notificationID;
  public int priority;
  public String rawPayload;
  public String smallIcon;
  public String smallIconAccentColor;
  public String sound;
  public String templateId;
  public String templateName;
  public String title;
  
  public OSNotificationPayload() {}
  
  public OSNotificationPayload(JSONObject paramJSONObject)
  {
    this.notificationID = paramJSONObject.optString("notificationID");
    this.title = paramJSONObject.optString("title");
    this.body = paramJSONObject.optString("body");
    this.additionalData = paramJSONObject.optJSONObject("additionalData");
    this.smallIcon = paramJSONObject.optString("smallIcon");
    this.largeIcon = paramJSONObject.optString("largeIcon");
    this.bigPicture = paramJSONObject.optString("bigPicture");
    this.smallIconAccentColor = paramJSONObject.optString("smallIconAccentColor");
    this.launchURL = paramJSONObject.optString("launchURL");
    this.sound = paramJSONObject.optString("sound");
    this.ledColor = paramJSONObject.optString("ledColor");
    this.lockScreenVisibility = paramJSONObject.optInt("lockScreenVisibility");
    this.groupKey = paramJSONObject.optString("groupKey");
    this.groupMessage = paramJSONObject.optString("groupMessage");
    if (paramJSONObject.has("actionButtons"))
    {
      this.actionButtons = new ArrayList();
      JSONArray localJSONArray = paramJSONObject.optJSONArray("actionButtons");
      int i = 0;
      while (i < localJSONArray.length())
      {
        this.actionButtons.add(new ActionButton(localJSONArray.optJSONObject(i)));
        i += 1;
      }
    }
    this.fromProjectNumber = paramJSONObject.optString("fromProjectNumber");
    this.collapseId = paramJSONObject.optString("collapseId");
    this.priority = paramJSONObject.optInt("priority");
    this.rawPayload = paramJSONObject.optString("rawPayload");
  }
  
  public JSONObject toJSONObject()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("notificationID", this.notificationID);
      localJSONObject.put("title", this.title);
      localJSONObject.put("body", this.body);
      if (this.additionalData != null) {
        localJSONObject.put("additionalData", this.additionalData);
      }
      localJSONObject.put("smallIcon", this.smallIcon);
      localJSONObject.put("largeIcon", this.largeIcon);
      localJSONObject.put("bigPicture", this.bigPicture);
      localJSONObject.put("smallIconAccentColor", this.smallIconAccentColor);
      localJSONObject.put("launchURL", this.launchURL);
      localJSONObject.put("sound", this.sound);
      localJSONObject.put("ledColor", this.ledColor);
      localJSONObject.put("lockScreenVisibility", this.lockScreenVisibility);
      localJSONObject.put("groupKey", this.groupKey);
      localJSONObject.put("groupMessage", this.groupMessage);
      if (this.actionButtons != null)
      {
        JSONArray localJSONArray = new JSONArray();
        Iterator localIterator = this.actionButtons.iterator();
        while (localIterator.hasNext()) {
          localJSONArray.put(((ActionButton)localIterator.next()).toJSONObject());
        }
        localJSONObject.put("actionButtons", localThrowable);
      }
    }
    catch (Throwable localThrowable)
    {
      ThrowableExtension.printStackTrace(localThrowable);
      return localJSONObject;
    }
    localJSONObject.put("fromProjectNumber", this.fromProjectNumber);
    localJSONObject.put("collapseId", this.collapseId);
    localJSONObject.put("priority", this.priority);
    localJSONObject.put("rawPayload", this.rawPayload);
    return localJSONObject;
  }
  
  public static class ActionButton
  {
    public String icon;
    public String id;
    public String text;
    
    public ActionButton() {}
    
    public ActionButton(JSONObject paramJSONObject)
    {
      this.id = paramJSONObject.optString("id");
      this.text = paramJSONObject.optString("text");
      this.icon = paramJSONObject.optString("icon");
    }
    
    public JSONObject toJSONObject()
    {
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("id", this.id);
        localJSONObject.put("text", this.text);
        localJSONObject.put("icon", this.icon);
        return localJSONObject;
      }
      catch (Throwable localThrowable)
      {
        ThrowableExtension.printStackTrace(localThrowable);
      }
      return localJSONObject;
    }
  }
  
  public static class BackgroundImageLayout
  {
    public String bodyTextColor;
    public String image;
    public String titleTextColor;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OSNotificationPayload.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */