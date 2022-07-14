package de.appplant.cordova.plugin.notification.action;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class ActionGroup
{
  private static final String GENERAL_ACTION_GROUP = "DEFAULT_GROUP";
  private static final Map<String, ActionGroup> groups = new HashMap();
  private final Action[] actions;
  private final String id;
  
  private ActionGroup(String paramString, Action[] paramArrayOfAction)
  {
    this.id = paramString;
    this.actions = paramArrayOfAction;
  }
  
  public static ActionGroup lookup(String paramString)
  {
    return (ActionGroup)groups.get(paramString);
  }
  
  public static ActionGroup parse(Context paramContext, JSONObject paramJSONObject)
  {
    String str1 = paramJSONObject.optString("actionGroupId", "DEFAULT_GROUP");
    paramJSONObject = paramJSONObject.optJSONArray("actions");
    if ((paramJSONObject == null) || (paramJSONObject.length() == 0)) {}
    ArrayList localArrayList;
    do
    {
      return null;
      localArrayList = new ArrayList(paramJSONObject.length());
      int i = 0;
      if (i < paramJSONObject.length())
      {
        JSONObject localJSONObject = paramJSONObject.optJSONObject(i);
        String str2 = localJSONObject.optString("type", "button");
        if ((str2.equals("input")) && (Build.VERSION.SDK_INT < 24)) {
          Log.w("Action", "Type input is not supported");
        }
        for (;;)
        {
          i += 1;
          break;
          if ((!str2.equals("button")) && (!str2.equals("input"))) {
            Log.w("Action", "Unknown type: " + str2);
          } else {
            localArrayList.add(new Action(paramContext, localJSONObject));
          }
        }
      }
    } while (localArrayList.isEmpty());
    return new ActionGroup(str1, (Action[])localArrayList.toArray(new Action[localArrayList.size()]));
  }
  
  public static void register(ActionGroup paramActionGroup)
  {
    if (!paramActionGroup.getId().equalsIgnoreCase("DEFAULT_GROUP")) {
      groups.put(paramActionGroup.getId(), paramActionGroup);
    }
  }
  
  public Action[] getActions()
  {
    return this.actions;
  }
  
  public String getId()
  {
    return this.id;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\notification\action\ActionGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */