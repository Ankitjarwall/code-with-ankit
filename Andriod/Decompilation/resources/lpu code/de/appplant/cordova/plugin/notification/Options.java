package de.appplant.cordova.plugin.notification;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat.MessagingStyle.Message;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.MediaSessionCompat.Token;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import de.appplant.cordova.plugin.notification.action.Action;
import de.appplant.cordova.plugin.notification.action.ActionGroup;
import de.appplant.cordova.plugin.notification.util.AssetUtil;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class Options
{
  private static final String DEFAULT_ICON = "res://icon";
  public static final String EXTRA_LAUNCH = "NOTIFICATION_LAUNCH";
  static final String EXTRA_SOUND = "NOTIFICATION_SOUND";
  private final AssetUtil assets;
  private final Context context;
  private final JSONObject options;
  
  Options(Context paramContext, JSONObject paramJSONObject)
  {
    this.context = paramContext;
    this.options = paramJSONObject;
    this.assets = AssetUtil.getInstance(paramContext);
  }
  
  public Options(JSONObject paramJSONObject)
  {
    this.options = paramJSONObject;
    this.context = null;
    this.assets = null;
  }
  
  private boolean isWithDefaultLights()
  {
    Object localObject = this.options.opt("led");
    return (localObject != null) && (localObject.equals(Boolean.valueOf(true)));
  }
  
  private boolean isWithDefaultSound()
  {
    Object localObject = this.options.opt("sound");
    return (localObject != null) && (localObject.equals(Boolean.valueOf(true)));
  }
  
  private boolean isWithVibration()
  {
    return this.options.optBoolean("vibrate", true);
  }
  
  private boolean isWithoutLights()
  {
    boolean bool = false;
    Object localObject = this.options.opt("led");
    if ((localObject == null) || (localObject.equals(Boolean.valueOf(false)))) {
      bool = true;
    }
    return bool;
  }
  
  private boolean isWithoutSound()
  {
    boolean bool = false;
    Object localObject = this.options.opt("sound");
    if ((localObject == null) || (localObject.equals(Boolean.valueOf(false)))) {
      bool = true;
    }
    return bool;
  }
  
  private String stripHex(String paramString)
  {
    String str = paramString;
    if (paramString.charAt(0) == '#') {
      str = paramString.substring(1);
    }
    return str;
  }
  
  Action[] getActions()
  {
    Object localObject3 = null;
    String str = this.options.optString("actionGroupId", null);
    JSONArray localJSONArray = this.options.optJSONArray("actions");
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (localJSONArray != null)
    {
      localObject1 = localObject2;
      if (localJSONArray.length() > 0) {
        localObject1 = ActionGroup.parse(this.context, this.options);
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null)
    {
      localObject2 = localObject1;
      if (str != null) {
        localObject2 = ActionGroup.lookup(str);
      }
    }
    localObject1 = localObject3;
    if (localObject2 != null)
    {
      ActionGroup.register((ActionGroup)localObject2);
      localObject1 = ((ActionGroup)localObject2).getActions();
    }
    return (Action[])localObject1;
  }
  
  List<Bitmap> getAttachments()
  {
    JSONArray localJSONArray = this.options.optJSONArray("attachments");
    ArrayList localArrayList = new ArrayList();
    if (localJSONArray == null) {
      return localArrayList;
    }
    int i = 0;
    label26:
    Uri localUri;
    if (i < localJSONArray.length())
    {
      localUri = this.assets.parse(localJSONArray.optString(i));
      if (localUri != Uri.EMPTY) {
        break label63;
      }
    }
    for (;;)
    {
      i += 1;
      break label26;
      break;
      try
      {
        label63:
        localArrayList.add(this.assets.getIconFromUri(localUri));
        return localArrayList;
      }
      catch (IOException localIOException)
      {
        ThrowableExtension.printStackTrace(localIOException);
      }
    }
  }
  
  public int getBadgeNumber()
  {
    return this.options.optInt("badge", 0);
  }
  
  String getChannel()
  {
    return this.options.optString("channel", "default-channel-id");
  }
  
  public int getColor()
  {
    String str = this.options.optString("color", null);
    if (str == null) {
      return 0;
    }
    try
    {
      str = stripHex(str);
      if (str.matches("[^0-9]*")) {
        return Color.class.getDeclaredField(str.toUpperCase()).getInt(null);
      }
      int i = Integer.parseInt(str, 16);
      return -16777216 + i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      ThrowableExtension.printStackTrace(localNumberFormatException);
      return 0;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      ThrowableExtension.printStackTrace(localNoSuchFieldException);
      return 0;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      ThrowableExtension.printStackTrace(localIllegalAccessException);
    }
    return 0;
  }
  
  public Context getContext()
  {
    return this.context;
  }
  
  int getDefaults()
  {
    int i = this.options.optInt("defaults", 0);
    int j;
    if (isWithVibration())
    {
      j = i | 0x2;
      if (!isWithDefaultSound()) {
        break label53;
      }
      i = j | 0x1;
      label33:
      if (!isWithDefaultLights()) {
        break label69;
      }
      j = i | 0x4;
    }
    label53:
    label69:
    do
    {
      return j;
      j = i & 0x2;
      break;
      i = j;
      if (!isWithoutSound()) {
        break label33;
      }
      i = j & 0x1;
      break label33;
      j = i;
    } while (!isWithoutLights());
    return i & 0x4;
  }
  
  public JSONObject getDict()
  {
    return this.options;
  }
  
  String getGroup()
  {
    return this.options.optString("group", null);
  }
  
  boolean getGroupSummary()
  {
    return this.options.optBoolean("groupSummary", false);
  }
  
  public Integer getId()
  {
    return Integer.valueOf(this.options.optInt("id", 0));
  }
  
  String getIdentifier()
  {
    return getId().toString();
  }
  
  Bitmap getLargeIcon()
  {
    Object localObject = this.options.optString("icon", null);
    localObject = this.assets.parse((String)localObject);
    try
    {
      localObject = this.assets.getIconFromUri((Uri)localObject);
      return (Bitmap)localObject;
    }
    catch (Exception localException)
    {
      ThrowableExtension.printStackTrace(localException);
    }
    return null;
  }
  
  int getLedColor()
  {
    Object localObject = this.options.opt("led");
    String str = null;
    if ((localObject instanceof String)) {
      str = this.options.optString("led");
    }
    while (str == null)
    {
      return 0;
      if ((localObject instanceof JSONArray)) {
        str = this.options.optJSONArray("led").optString(0);
      } else if ((localObject instanceof JSONObject)) {
        str = this.options.optJSONObject("led").optString("color");
      }
    }
    try
    {
      int i = Integer.parseInt(stripHex(str), 16);
      return -16777216 + i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      ThrowableExtension.printStackTrace(localNumberFormatException);
    }
    return 0;
  }
  
  int getLedOff()
  {
    Object localObject = this.options.opt("led");
    int i = 1000;
    if ((localObject instanceof JSONArray)) {
      i = this.options.optJSONArray("led").optInt(2, 1000);
    }
    while (!(localObject instanceof JSONObject)) {
      return i;
    }
    return this.options.optJSONObject("led").optInt("off", 1000);
  }
  
  int getLedOn()
  {
    Object localObject = this.options.opt("led");
    int i = 1000;
    if ((localObject instanceof JSONArray)) {
      i = this.options.optJSONArray("led").optInt(1, 1000);
    }
    while (!(localObject instanceof JSONObject)) {
      return i;
    }
    return this.options.optJSONObject("led").optInt("on", 1000);
  }
  
  MediaSessionCompat.Token getMediaSessionToken()
  {
    String str = this.options.optString("mediaSession", null);
    if (str == null) {
      return null;
    }
    return new MediaSessionCompat(this.context, str).getSessionToken();
  }
  
  NotificationCompat.MessagingStyle.Message[] getMessages()
  {
    Object localObject = this.options.opt("text");
    if ((localObject == null) || ((localObject instanceof String)))
    {
      localObject = null;
      return (NotificationCompat.MessagingStyle.Message[])localObject;
    }
    JSONArray localJSONArray = (JSONArray)localObject;
    if (localJSONArray.length() == 0) {
      return null;
    }
    NotificationCompat.MessagingStyle.Message[] arrayOfMessage = new NotificationCompat.MessagingStyle.Message[localJSONArray.length()];
    long l = new Date().getTime();
    int i = 0;
    for (;;)
    {
      localObject = arrayOfMessage;
      if (i >= arrayOfMessage.length) {
        break;
      }
      localObject = localJSONArray.optJSONObject(i);
      arrayOfMessage[i] = new NotificationCompat.MessagingStyle.Message(((JSONObject)localObject).optString("message"), ((JSONObject)localObject).optLong("date", l), ((JSONObject)localObject).optString("person", null));
      i += 1;
    }
  }
  
  public int getNumber()
  {
    return this.options.optInt("number", 0);
  }
  
  int getPriority()
  {
    return Math.min(Math.max(this.options.optInt("priority"), -2), 2);
  }
  
  int getProgressMaxValue()
  {
    return this.options.optJSONObject("progressBar").optInt("maxValue", 100);
  }
  
  int getProgressValue()
  {
    return this.options.optJSONObject("progressBar").optInt("value", 0);
  }
  
  boolean getShowWhen()
  {
    return this.options.optBoolean("showWhen", true);
  }
  
  int getSmallIcon()
  {
    String str = this.options.optString("smallIcon", "res://icon");
    int j = this.assets.getResId(str);
    int i = j;
    if (j == 0) {
      i = this.assets.getResId("res://icon");
    }
    j = i;
    if (i == 0) {
      j = this.context.getApplicationInfo().icon;
    }
    i = j;
    if (j == 0) {
      i = 17301598;
    }
    return i;
  }
  
  Uri getSound()
  {
    return this.assets.parse(this.options.optString("sound", null));
  }
  
  String getSummary()
  {
    return this.options.optString("summary", null);
  }
  
  public String getText()
  {
    Object localObject = this.options.opt("text");
    if ((localObject instanceof String)) {
      return (String)localObject;
    }
    return "";
  }
  
  public String getTitle()
  {
    String str2 = this.options.optString("title", "");
    String str1 = str2;
    if (str2.isEmpty()) {
      str1 = this.context.getApplicationInfo().loadLabel(this.context.getPackageManager()).toString();
    }
    return str1;
  }
  
  public JSONObject getTrigger()
  {
    return this.options.optJSONObject("trigger");
  }
  
  int getVisibility()
  {
    if (this.options.optBoolean("lockscreen", true)) {
      return 1;
    }
    return -1;
  }
  
  boolean hasLargeIcon()
  {
    return this.options.optString("icon", null) != null;
  }
  
  Boolean isAutoClear()
  {
    return Boolean.valueOf(this.options.optBoolean("autoClear", false));
  }
  
  boolean isIndeterminateProgress()
  {
    return this.options.optJSONObject("progressBar").optBoolean("indeterminate", false);
  }
  
  public boolean isInfiniteTrigger()
  {
    JSONObject localJSONObject = this.options.optJSONObject("trigger");
    return (localJSONObject.has("every")) && (localJSONObject.optInt("count", -1) < 0);
  }
  
  boolean isLaunchingApp()
  {
    return this.options.optBoolean("launch", true);
  }
  
  boolean isSilent()
  {
    return this.options.optBoolean("silent", false);
  }
  
  public Boolean isSticky()
  {
    return Boolean.valueOf(this.options.optBoolean("sticky", false));
  }
  
  boolean isWithProgressBar()
  {
    return this.options.optJSONObject("progressBar").optBoolean("enabled", false);
  }
  
  public boolean shallWakeUp()
  {
    return this.options.optBoolean("wakeup", true);
  }
  
  public String toString()
  {
    return this.options.toString();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\notification\Options.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */