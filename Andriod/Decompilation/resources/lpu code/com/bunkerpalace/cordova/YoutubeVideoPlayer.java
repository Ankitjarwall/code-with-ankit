package com.bunkerpalace.cordova;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import com.google.android.youtube.player.YouTubeIntents;
import com.keyes.youtube.OpenYouTubePlayerActivity;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaPreferences;
import org.json.JSONArray;
import org.json.JSONException;

public class YoutubeVideoPlayer
  extends CordovaPlugin
{
  private CallbackContext callbackContext;
  
  private Intent createYoutubeIntent(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      Activity localActivity = this.cordova.getActivity();
      if ((YouTubeIntents.getInstalledYouTubeVersionName(localActivity).startsWith("11.16")) && (YouTubeIntents.canResolvePlayVideoIntent(localActivity))) {
        return YouTubeIntents.createPlayVideoIntent(localActivity, paramString);
      }
      if (YouTubeIntents.canResolvePlayVideoIntentWithOptions(localActivity)) {
        return YouTubeIntents.createPlayVideoIntentWithOptions(localActivity, paramString, true, true);
      }
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.youtube.com/watch?v=" + paramString), localActivity, YouTubeActivity.class);
      localIntent.putExtra("videoId", paramString);
      paramString = new ConfigXmlParser();
      paramString.parse(localActivity);
      localIntent.putExtra("YouTubeApiId", paramString.getPreferences().getString("YouTubeDataApiKey", "YOUTUBE_API_KEY"));
      return localIntent;
    }
    return new Intent(null, Uri.parse("ytv://" + paramString), this.cordova.getActivity(), OpenYouTubePlayerActivity.class);
  }
  
  private void openVideo(String paramString)
  {
    paramString = createYoutubeIntent(paramString);
    this.cordova.startActivityForResult(this, paramString, 242);
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    boolean bool = false;
    if (paramString.equals("openVideo"))
    {
      openVideo(paramJSONArray.getString(0));
      this.callbackContext = paramCallbackContext;
      bool = true;
    }
    return bool;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 242)
    {
      this.cordova.getActivity();
      if (paramInt2 == -1) {
        this.callbackContext.success();
      }
    }
    else
    {
      return;
    }
    this.callbackContext.error("Error");
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\bunkerpalace\cordova\YoutubeVideoPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */