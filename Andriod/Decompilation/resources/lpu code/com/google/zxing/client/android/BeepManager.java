package com.google.zxing.client.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import barcodescanner.xservices.nl.barcodescanner.R.raw;
import java.io.Closeable;
import java.io.IOException;

final class BeepManager
  implements MediaPlayer.OnErrorListener, Closeable
{
  private static final float BEEP_VOLUME = 0.1F;
  private static final String TAG = BeepManager.class.getSimpleName();
  private static final long VIBRATE_DURATION = 200L;
  private final Activity activity;
  private MediaPlayer mediaPlayer;
  private boolean playBeep;
  private boolean vibrate;
  
  BeepManager(Activity paramActivity)
  {
    this.activity = paramActivity;
    this.mediaPlayer = null;
    updatePrefs();
  }
  
  private MediaPlayer buildMediaPlayer(Context paramContext)
  {
    MediaPlayer localMediaPlayer = new MediaPlayer();
    try
    {
      paramContext = paramContext.getResources().openRawResourceFd(R.raw.beep);
      try
      {
        localMediaPlayer.setDataSource(paramContext.getFileDescriptor(), paramContext.getStartOffset(), paramContext.getLength());
        paramContext.close();
        localMediaPlayer.setOnErrorListener(this);
        localMediaPlayer.setAudioStreamType(3);
        localMediaPlayer.setLooping(false);
        localMediaPlayer.setVolume(0.1F, 0.1F);
        return localMediaPlayer;
      }
      finally
      {
        paramContext.close();
      }
      return null;
    }
    catch (IOException paramContext)
    {
      Log.w(TAG, paramContext);
      localMediaPlayer.release();
    }
  }
  
  private static boolean shouldBeep(SharedPreferences paramSharedPreferences, Context paramContext)
  {
    boolean bool2 = paramSharedPreferences.getBoolean("preferences_play_beep", true);
    boolean bool1 = bool2;
    if (bool2)
    {
      bool1 = bool2;
      if (((AudioManager)paramContext.getSystemService("audio")).getRingerMode() != 2) {
        bool1 = false;
      }
    }
    return bool1;
  }
  
  public void close()
  {
    try
    {
      if (this.mediaPlayer != null)
      {
        this.mediaPlayer.release();
        this.mediaPlayer = null;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    if (paramInt1 == 100) {}
    for (;;)
    {
      try
      {
        this.activity.finish();
        return true;
      }
      finally {}
      close();
      updatePrefs();
    }
  }
  
  void playBeepSoundAndVibrate()
  {
    try
    {
      if ((this.playBeep) && (this.mediaPlayer != null)) {
        this.mediaPlayer.start();
      }
      if (this.vibrate) {
        ((Vibrator)this.activity.getSystemService("vibrator")).vibrate(200L);
      }
      return;
    }
    finally {}
  }
  
  void updatePrefs()
  {
    try
    {
      SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.activity);
      this.playBeep = shouldBeep(localSharedPreferences, this.activity);
      this.vibrate = localSharedPreferences.getBoolean("preferences_vibrate", false);
      if ((this.playBeep) && (this.mediaPlayer == null))
      {
        this.activity.setVolumeControlStream(3);
        this.mediaPlayer = buildMediaPlayer(this.activity);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\BeepManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */