package com.google.android.youtube.player;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import java.util.ArrayList;
import java.util.List;

public final class YouTubeStandalonePlayer
{
  private static Intent a(Intent paramIntent, Activity paramActivity, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramString = YouTubeIntents.a(paramIntent, paramActivity).putExtra("developer_key", paramString).putExtra("autoplay", paramBoolean1).putExtra("lightbox_mode", paramBoolean2).putExtra("start_time_millis", paramInt);
    if ((paramActivity.getWindow().getAttributes().flags & 0x400) == 0) {}
    for (paramBoolean1 = true;; paramBoolean1 = false)
    {
      paramString.putExtra("window_has_status_bar", paramBoolean1);
      return paramIntent;
    }
  }
  
  public static Intent createPlaylistIntent(Activity paramActivity, String paramString1, String paramString2)
  {
    return createPlaylistIntent(paramActivity, paramString1, paramString2, 0, 0, false, false);
  }
  
  public static Intent createPlaylistIntent(Activity paramActivity, String paramString1, String paramString2, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramString2 == null) {
      throw new NullPointerException("The playlistId cannot be null");
    }
    if (paramString1 == null) {
      throw new NullPointerException("The developerKey cannot be null");
    }
    return a(new Intent("com.google.android.youtube.api.StandalonePlayerActivity.START").putExtra("playlist_id", paramString2).putExtra("current_index", paramInt1), paramActivity, paramString1, paramInt2, paramBoolean1, paramBoolean2);
  }
  
  public static Intent createVideoIntent(Activity paramActivity, String paramString1, String paramString2)
  {
    return createVideoIntent(paramActivity, paramString1, paramString2, 0, false, false);
  }
  
  public static Intent createVideoIntent(Activity paramActivity, String paramString1, String paramString2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramString2 == null) {
      throw new NullPointerException("The videoId cannot be null");
    }
    if (paramString1 == null) {
      throw new NullPointerException("The developerKey cannot be null");
    }
    return a(new Intent("com.google.android.youtube.api.StandalonePlayerActivity.START").putExtra("video_id", paramString2), paramActivity, paramString1, paramInt, paramBoolean1, paramBoolean2);
  }
  
  public static Intent createVideosIntent(Activity paramActivity, String paramString, List<String> paramList)
  {
    return createVideosIntent(paramActivity, paramString, paramList, 0, 0, false, false);
  }
  
  public static Intent createVideosIntent(Activity paramActivity, String paramString, List<String> paramList, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramList == null) {
      throw new NullPointerException("The list of videoIds cannot be null");
    }
    if (paramList.isEmpty()) {
      throw new IllegalStateException("The list of videoIds cannot be empty");
    }
    if (paramString == null) {
      throw new NullPointerException("The developerKey cannot be null");
    }
    return a(new Intent("com.google.android.youtube.api.StandalonePlayerActivity.START").putStringArrayListExtra("video_ids", new ArrayList(paramList)).putExtra("current_index", paramInt1), paramActivity, paramString, paramInt2, paramBoolean1, paramBoolean2);
  }
  
  public static YouTubeInitializationResult getReturnedInitializationResult(Intent paramIntent)
  {
    paramIntent = paramIntent.getExtras().getString("initialization_result");
    try
    {
      paramIntent = YouTubeInitializationResult.valueOf(paramIntent);
      return paramIntent;
    }
    catch (IllegalArgumentException paramIntent)
    {
      return YouTubeInitializationResult.UNKNOWN_ERROR;
    }
    catch (NullPointerException paramIntent) {}
    return YouTubeInitializationResult.UNKNOWN_ERROR;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\youtube\player\YouTubeStandalonePlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */