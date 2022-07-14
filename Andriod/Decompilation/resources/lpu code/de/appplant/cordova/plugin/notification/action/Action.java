package de.appplant.cordova.plugin.notification.action;

import android.content.Context;
import android.support.v4.app.RemoteInput;
import android.support.v4.app.RemoteInput.Builder;
import de.appplant.cordova.plugin.notification.util.AssetUtil;
import org.json.JSONArray;
import org.json.JSONObject;

public final class Action
{
  public static final String CLICK_ACTION_ID = "click";
  public static final String EXTRA_ID = "NOTIFICATION_ACTION_ID";
  private final Context context;
  private final JSONObject options;
  
  Action(Context paramContext, JSONObject paramJSONObject)
  {
    this.context = paramContext;
    this.options = paramJSONObject;
  }
  
  private String[] getChoices()
  {
    JSONArray localJSONArray = this.options.optJSONArray("choices");
    Object localObject;
    if (localJSONArray == null)
    {
      localObject = null;
      return (String[])localObject;
    }
    String[] arrayOfString = new String[localJSONArray.length()];
    int i = 0;
    for (;;)
    {
      localObject = arrayOfString;
      if (i >= arrayOfString.length) {
        break;
      }
      arrayOfString[i] = localJSONArray.optString(i);
      i += 1;
    }
  }
  
  public int getIcon()
  {
    int j = AssetUtil.getInstance(this.context).getResId(this.options.optString("icon"));
    int i = j;
    if (j == 0) {
      i = 17301656;
    }
    return i;
  }
  
  public String getId()
  {
    return this.options.optString("id", getTitle());
  }
  
  public RemoteInput getInput()
  {
    return new RemoteInput.Builder(getId()).setLabel(this.options.optString("emptyText")).setAllowFreeFormInput(this.options.optBoolean("editable", true)).setChoices(getChoices()).build();
  }
  
  public String getTitle()
  {
    return this.options.optString("title", "unknown");
  }
  
  public boolean isLaunchingApp()
  {
    return this.options.optBoolean("launch", false);
  }
  
  public boolean isWithInput()
  {
    return this.options.optString("type").equals("input");
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\notification\action\Action.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */