package com.bunkerpalace.cordova;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class YouTubeActivity
  extends YouTubeBaseActivity
  implements YouTubePlayer.OnInitializedListener, YouTubePlayer.PlayerStateChangeListener
{
  private static final int RECOVERY_REQUEST = 1;
  private String apiKey;
  private String videoId;
  private YouTubePlayerView youTubeView;
  
  private void updateLog(String paramString)
  {
    Log.d("YouTubeActivity", paramString);
  }
  
  public void onAdStarted() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent();
    this.videoId = paramBundle.getStringExtra("videoId");
    this.apiKey = paramBundle.getStringExtra("YouTubeApiId");
    this.youTubeView = new YouTubePlayerView(this);
    this.youTubeView.initialize(this.apiKey, this);
    setContentView(this.youTubeView);
  }
  
  public void onError(YouTubePlayer.ErrorReason paramErrorReason)
  {
    updateLog("onError(): " + paramErrorReason.toString());
    finish();
  }
  
  public void onInitializationFailure(YouTubePlayer.Provider paramProvider, YouTubeInitializationResult paramYouTubeInitializationResult)
  {
    if (paramYouTubeInitializationResult.isUserRecoverableError())
    {
      paramYouTubeInitializationResult.getErrorDialog(this, 1).show();
      return;
    }
    Toast.makeText(this, String.format("Error initializing YouTube player", new Object[] { paramYouTubeInitializationResult.toString() }), 1).show();
  }
  
  public void onInitializationSuccess(YouTubePlayer.Provider paramProvider, YouTubePlayer paramYouTubePlayer, boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      paramYouTubePlayer.loadVideo(this.videoId);
      paramYouTubePlayer.setPlayerStateChangeListener(this);
    }
  }
  
  public void onLoaded(String paramString) {}
  
  public void onLoading() {}
  
  public void onVideoEnded()
  {
    setResult(-1);
    finish();
  }
  
  public void onVideoStarted() {}
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\bunkerpalace\cordova\YouTubeActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */