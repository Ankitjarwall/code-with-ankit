package com.google.android.youtube.player.internal;

import android.util.Log;

public final class y
{
  public static void a(String paramString, Throwable paramThrowable)
  {
    Log.e("YouTubeAndroidPlayerAPI", paramString, paramThrowable);
  }
  
  public static void a(String paramString, Object... paramVarArgs)
  {
    Log.w("YouTubeAndroidPlayerAPI", String.format(paramString, paramVarArgs));
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\youtube\player\internal\y.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */