package de.appplant.cordova.plugin.badge;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import me.leolin.shortcutbadger.ShortcutBadger;
import org.json.JSONException;
import org.json.JSONObject;

public final class BadgeImpl
{
  private static final String BADGE_KEY = "badge";
  private static final String CONFIG_KEY = "badge.config";
  private final Context ctx;
  private final boolean isSupported;
  
  public BadgeImpl(Context paramContext)
  {
    if (ShortcutBadger.isBadgeCounterSupported(paramContext)) {
      this.ctx = paramContext;
    }
    for (this.isSupported = true;; this.isSupported = ShortcutBadger.isBadgeCounterSupported(this.ctx))
    {
      ShortcutBadger.applyCount(this.ctx, getBadge());
      return;
      this.ctx = paramContext.getApplicationContext();
    }
  }
  
  private SharedPreferences getPrefs()
  {
    return this.ctx.getSharedPreferences("badge", 0);
  }
  
  private void saveBadge(int paramInt)
  {
    SharedPreferences.Editor localEditor = getPrefs().edit();
    localEditor.putInt("badge", paramInt);
    localEditor.apply();
  }
  
  public void clearBadge()
  {
    saveBadge(0);
    ShortcutBadger.removeCount(this.ctx);
  }
  
  public int getBadge()
  {
    return getPrefs().getInt("badge", 0);
  }
  
  public boolean isSupported()
  {
    return this.isSupported;
  }
  
  public JSONObject loadConfig()
  {
    Object localObject = getPrefs().getString("badge.config", "{}");
    try
    {
      localObject = new JSONObject((String)localObject);
      return (JSONObject)localObject;
    }
    catch (JSONException localJSONException) {}
    return new JSONObject();
  }
  
  public void saveConfig(JSONObject paramJSONObject)
  {
    SharedPreferences.Editor localEditor = getPrefs().edit();
    localEditor.putString("badge.config", paramJSONObject.toString());
    localEditor.apply();
  }
  
  public void setBadge(int paramInt)
  {
    saveBadge(paramInt);
    ShortcutBadger.applyCount(this.ctx, paramInt);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\badge\BadgeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */