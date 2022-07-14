package com.keyes.youtube;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.VideoView;

public class OpenYouTubePlayerActivity
  extends Activity
{
  public static final String MSG_DETECT = "com.keyes.video.msg.detect";
  public static final String MSG_ERROR_MSG = "com.keyes.video.msg.error.msg";
  public static final String MSG_ERROR_TITLE = "com.keyes.video.msg.error.title";
  public static final String MSG_HI_BAND = "com.keyes.video.msg.hiband";
  public static final String MSG_INIT = "com.keyes.video.msg.init";
  public static final String MSG_LO_BAND = "com.keyes.video.msg.loband";
  public static final String MSG_PLAYLIST = "com.keyes.video.msg.playlist";
  public static final String MSG_TOKEN = "com.keyes.video.msg.token";
  public static final String SCHEME_YOUTUBE_PLAYLIST = "ytpl";
  public static final String SCHEME_YOUTUBE_VIDEO = "ytv";
  static final String YOUTUBE_PLAYLIST_ATOM_FEED_URL = "http://gdata.youtube.com/feeds/api/playlists/";
  static final String YOUTUBE_VIDEO_INFORMATION_URL = "http://www.youtube.com/get_video_info?&video_id=";
  protected String mMsgDetect = "Detecting Bandwidth";
  protected String mMsgError = "An error occurred during the retrieval of the video.  This could be due to network issues or YouTube protocols.  Please try again later.";
  protected String mMsgErrorTitle = "Communications Error";
  protected String mMsgHiBand = "Buffering High-bandwidth Video";
  protected String mMsgInit = "Initializing";
  protected String mMsgLowBand = "Buffering Low-bandwidth Video";
  protected String mMsgPlaylist = "Determining Latest Video in YouTube Playlist";
  protected String mMsgToken = "Retrieving YouTube Video Token";
  protected ProgressBar mProgressBar;
  protected TextView mProgressMessage;
  protected QueryYouTubeTask mQueryYouTubeTask;
  protected String mVideoId = null;
  protected VideoView mVideoView;
  
  private void extractMessages()
  {
    Object localObject = getIntent();
    String str = ((Intent)localObject).getStringExtra("com.keyes.video.msg.init");
    if (str != null) {
      this.mMsgInit = str;
    }
    str = ((Intent)localObject).getStringExtra("com.keyes.video.msg.detect");
    if (str != null) {
      this.mMsgDetect = str;
    }
    str = ((Intent)localObject).getStringExtra("com.keyes.video.msg.playlist");
    if (str != null) {
      this.mMsgPlaylist = str;
    }
    str = ((Intent)localObject).getStringExtra("com.keyes.video.msg.token");
    if (str != null) {
      this.mMsgToken = str;
    }
    str = ((Intent)localObject).getStringExtra("com.keyes.video.msg.loband");
    if (str != null) {
      this.mMsgLowBand = str;
    }
    str = ((Intent)localObject).getStringExtra("com.keyes.video.msg.hiband");
    if (str != null) {
      this.mMsgHiBand = str;
    }
    str = ((Intent)localObject).getStringExtra("com.keyes.video.msg.error.title");
    if (str != null) {
      this.mMsgErrorTitle = str;
    }
    localObject = ((Intent)localObject).getStringExtra("com.keyes.video.msg.error.msg");
    if (localObject != null) {
      this.mMsgError = ((String)localObject);
    }
  }
  
  private void setupView()
  {
    Object localObject = new LinearLayout(this);
    ((LinearLayout)localObject).setId(1);
    ((LinearLayout)localObject).setOrientation(1);
    ((LinearLayout)localObject).setGravity(17);
    ((LinearLayout)localObject).setBackgroundColor(-16777216);
    ((LinearLayout)localObject).setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    setContentView((View)localObject);
    RelativeLayout localRelativeLayout = new RelativeLayout(this);
    localRelativeLayout.setId(2);
    localRelativeLayout.setGravity(17);
    localRelativeLayout.setBackgroundColor(-16777216);
    localRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    ((LinearLayout)localObject).addView(localRelativeLayout);
    this.mVideoView = new VideoView(this);
    this.mVideoView.setId(3);
    localObject = new RelativeLayout.LayoutParams(-1, -1);
    ((RelativeLayout.LayoutParams)localObject).addRule(13);
    this.mVideoView.setLayoutParams((ViewGroup.LayoutParams)localObject);
    localRelativeLayout.addView(this.mVideoView);
    this.mProgressBar = new ProgressBar(this);
    this.mProgressBar.setIndeterminate(true);
    this.mProgressBar.setVisibility(0);
    this.mProgressBar.setEnabled(true);
    this.mProgressBar.setId(4);
    localObject = new RelativeLayout.LayoutParams(-2, -2);
    ((RelativeLayout.LayoutParams)localObject).addRule(13);
    this.mProgressBar.setLayoutParams((ViewGroup.LayoutParams)localObject);
    localRelativeLayout.addView(this.mProgressBar);
    this.mProgressMessage = new TextView(this);
    this.mProgressMessage.setId(5);
    localObject = new RelativeLayout.LayoutParams(-2, -2);
    ((RelativeLayout.LayoutParams)localObject).addRule(14);
    ((RelativeLayout.LayoutParams)localObject).addRule(3, 4);
    this.mProgressMessage.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this.mProgressMessage.setTextColor(-3355444);
    this.mProgressMessage.setTextSize(2, 12.0F);
    this.mProgressMessage.setText("...");
    localRelativeLayout.addView(this.mProgressMessage);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setupView();
    extractMessages();
    getWindow().setFlags(128, 128);
    this.mProgressBar.bringToFront();
    this.mProgressBar.setVisibility(0);
    this.mProgressMessage.setText(this.mMsgInit);
    paramBundle = getIntent().getData();
    if (paramBundle == null)
    {
      Log.i(getClass().getSimpleName(), "No video ID was specified in the intent.  Closing video activity.");
      finish();
    }
    String str = paramBundle.getScheme();
    paramBundle = paramBundle.getEncodedSchemeSpecificPart();
    if (paramBundle == null)
    {
      Log.i(getClass().getSimpleName(), "No video ID was specified in the intent.  Closing video activity.");
      finish();
    }
    Object localObject1 = paramBundle;
    Object localObject2;
    if (paramBundle.startsWith("//"))
    {
      if (paramBundle.length() > 2) {
        localObject1 = paramBundle.substring(2);
      }
    }
    else
    {
      localObject2 = null;
      if ((str == null) || (!str.equalsIgnoreCase("ytpl"))) {
        break label259;
      }
      paramBundle = new PlaylistId((String)localObject1);
    }
    for (;;)
    {
      if (paramBundle == null)
      {
        Log.i(getClass().getSimpleName(), "Unable to extract video ID from the intent.  Closing video activity.");
        finish();
      }
      this.mQueryYouTubeTask = ((QueryYouTubeTask)new QueryYouTubeTask(null).execute(new YouTubeId[] { paramBundle }));
      return;
      Log.i(getClass().getSimpleName(), "No video ID was specified in the intent.  Closing video activity.");
      finish();
      localObject1 = paramBundle;
      break;
      label259:
      paramBundle = (Bundle)localObject2;
      if (str != null)
      {
        paramBundle = (Bundle)localObject2;
        if (str.equalsIgnoreCase("ytv")) {
          paramBundle = new VideoId((String)localObject1);
        }
      }
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    YouTubeUtility.markVideoAsViewed(this, this.mVideoId);
    if (this.mQueryYouTubeTask != null) {
      this.mQueryYouTubeTask.cancel(true);
    }
    if (this.mVideoView != null) {
      this.mVideoView.stopPlayback();
    }
    getWindow().clearFlags(128);
    this.mQueryYouTubeTask = null;
    this.mVideoView = null;
  }
  
  protected void onStart()
  {
    super.onStart();
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  public void updateProgress(String paramString)
  {
    try
    {
      this.mProgressMessage.setText(paramString);
      return;
    }
    catch (Exception paramString)
    {
      Log.e(getClass().getSimpleName(), "Error updating video status!", paramString);
    }
  }
  
  private class ProgressUpdateInfo
  {
    public String mMsg;
    
    public ProgressUpdateInfo(String paramString)
    {
      this.mMsg = paramString;
    }
  }
  
  private class QueryYouTubeTask
    extends AsyncTask<YouTubeId, OpenYouTubePlayerActivity.ProgressUpdateInfo, Uri>
  {
    private boolean mShowedError = false;
    
    private QueryYouTubeTask() {}
    
    private void showErrorAlert()
    {
      try
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(OpenYouTubePlayerActivity.this);
        localBuilder.setTitle(OpenYouTubePlayerActivity.this.mMsgErrorTitle);
        localBuilder.setCancelable(false);
        localBuilder.setMessage(OpenYouTubePlayerActivity.this.mMsgError);
        localBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            OpenYouTubePlayerActivity.this.finish();
          }
        });
        localBuilder.create().show();
        return;
      }
      catch (Exception localException)
      {
        Log.e(getClass().getSimpleName(), "Problem showing error dialog.", localException);
      }
    }
    
    protected Uri doInBackground(YouTubeId... paramVarArgs)
    {
      Object localObject3 = null;
      String str2 = "17";
      String str1 = null;
      if (isCancelled()) {
        return null;
      }
      Object localObject1 = localObject3;
      for (;;)
      {
        Object localObject2;
        try
        {
          publishProgress(new OpenYouTubePlayerActivity.ProgressUpdateInfo[] { new OpenYouTubePlayerActivity.ProgressUpdateInfo(OpenYouTubePlayerActivity.this, OpenYouTubePlayerActivity.this.mMsgDetect) });
          localObject1 = localObject3;
          localObject2 = (WifiManager)OpenYouTubePlayerActivity.this.getSystemService("wifi");
          localObject1 = localObject3;
          TelephonyManager localTelephonyManager = (TelephonyManager)OpenYouTubePlayerActivity.this.getSystemService("phone");
          localObject1 = localObject3;
          if (((WifiManager)localObject2).isWifiEnabled())
          {
            localObject1 = localObject3;
            if (((WifiManager)localObject2).getConnectionInfo() != null)
            {
              localObject1 = localObject3;
              if (((WifiManager)localObject2).getConnectionInfo().getIpAddress() != 0) {
                break label486;
              }
            }
          }
          localObject1 = localObject3;
          if (localTelephonyManager.getNetworkType() != 3)
          {
            localObject1 = localObject3;
            if (localTelephonyManager.getNetworkType() != 9)
            {
              localObject1 = localObject3;
              if (localTelephonyManager.getNetworkType() != 10)
              {
                localObject1 = localObject3;
                if (localTelephonyManager.getNetworkType() != 8)
                {
                  localObject1 = localObject3;
                  if (localTelephonyManager.getNetworkType() != 5)
                  {
                    localObject2 = str2;
                    localObject1 = localObject3;
                    if (localTelephonyManager.getNetworkType() != 6) {
                      continue;
                    }
                  }
                }
              }
            }
          }
          localObject2 = str2;
          localObject1 = localObject3;
          if (localTelephonyManager.getDataState() == 2) {
            break label486;
          }
          localObject1 = localObject3;
          if ((paramVarArgs[0] instanceof PlaylistId))
          {
            localObject1 = localObject3;
            publishProgress(new OpenYouTubePlayerActivity.ProgressUpdateInfo[] { new OpenYouTubePlayerActivity.ProgressUpdateInfo(OpenYouTubePlayerActivity.this, OpenYouTubePlayerActivity.this.mMsgPlaylist) });
            localObject1 = localObject3;
            str1 = YouTubeUtility.queryLatestPlaylistVideo((PlaylistId)paramVarArgs[0]);
            localObject1 = localObject3;
            OpenYouTubePlayerActivity.this.mVideoId = str1;
            localObject1 = localObject3;
            publishProgress(new OpenYouTubePlayerActivity.ProgressUpdateInfo[] { new OpenYouTubePlayerActivity.ProgressUpdateInfo(OpenYouTubePlayerActivity.this, OpenYouTubePlayerActivity.this.mMsgToken) });
            localObject1 = localObject3;
            if (isCancelled()) {
              return null;
            }
          }
          else
          {
            localObject1 = localObject3;
            if (!(paramVarArgs[0] instanceof VideoId)) {
              continue;
            }
            localObject1 = localObject3;
            str1 = paramVarArgs[0].getId();
            continue;
          }
          localObject1 = localObject3;
          paramVarArgs = YouTubeUtility.calculateYouTubeUrl((String)localObject2, true, str1);
          localObject1 = paramVarArgs;
          if (isCancelled()) {
            return null;
          }
          localObject1 = paramVarArgs;
          if (!((String)localObject2).equals("17")) {
            continue;
          }
          localObject1 = paramVarArgs;
          publishProgress(new OpenYouTubePlayerActivity.ProgressUpdateInfo[] { new OpenYouTubePlayerActivity.ProgressUpdateInfo(OpenYouTubePlayerActivity.this, OpenYouTubePlayerActivity.this.mMsgLowBand) });
        }
        catch (Exception paramVarArgs)
        {
          Log.e(getClass().getSimpleName(), "Error occurred while retrieving information from YouTube.", paramVarArgs);
          paramVarArgs = (YouTubeId[])localObject1;
          continue;
        }
        if (paramVarArgs != null)
        {
          return Uri.parse(paramVarArgs);
          localObject1 = paramVarArgs;
          publishProgress(new OpenYouTubePlayerActivity.ProgressUpdateInfo[] { new OpenYouTubePlayerActivity.ProgressUpdateInfo(OpenYouTubePlayerActivity.this, OpenYouTubePlayerActivity.this.mMsgHiBand) });
        }
        else
        {
          return null;
          label486:
          localObject2 = "18";
        }
      }
    }
    
    protected void onPostExecute(Uri paramUri)
    {
      super.onPostExecute(paramUri);
      try
      {
        if (isCancelled()) {
          return;
        }
        if (paramUri == null) {
          throw new RuntimeException("Invalid NULL Url.");
        }
      }
      catch (Exception paramUri)
      {
        Log.e(getClass().getSimpleName(), "Error playing video!", paramUri);
        if (!this.mShowedError)
        {
          showErrorAlert();
          return;
          OpenYouTubePlayerActivity.this.mVideoView.setVideoURI(paramUri);
          if (!isCancelled())
          {
            OpenYouTubePlayerActivity.this.mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
            {
              public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
              {
                if (OpenYouTubePlayerActivity.QueryYouTubeTask.this.isCancelled()) {
                  return;
                }
                OpenYouTubePlayerActivity.this.finish();
              }
            });
            if (!isCancelled())
            {
              paramUri = new MediaController(OpenYouTubePlayerActivity.this);
              OpenYouTubePlayerActivity.this.mVideoView.setMediaController(paramUri);
              paramUri.show(0);
              OpenYouTubePlayerActivity.this.mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
              {
                public void onPrepared(MediaPlayer paramAnonymousMediaPlayer)
                {
                  if (OpenYouTubePlayerActivity.QueryYouTubeTask.this.isCancelled()) {
                    return;
                  }
                  OpenYouTubePlayerActivity.this.mProgressBar.setVisibility(8);
                  OpenYouTubePlayerActivity.this.mProgressMessage.setVisibility(8);
                }
              });
              if (!isCancelled())
              {
                OpenYouTubePlayerActivity.this.mVideoView.requestFocus();
                OpenYouTubePlayerActivity.this.mVideoView.start();
              }
            }
          }
        }
      }
    }
    
    protected void onProgressUpdate(OpenYouTubePlayerActivity.ProgressUpdateInfo... paramVarArgs)
    {
      super.onProgressUpdate(paramVarArgs);
      OpenYouTubePlayerActivity.this.updateProgress(paramVarArgs[0].mMsg);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\keyes\youtube\OpenYouTubePlayerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */