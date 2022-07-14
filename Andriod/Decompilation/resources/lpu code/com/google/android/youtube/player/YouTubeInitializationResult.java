package com.google.android.youtube.player;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.google.android.youtube.player.internal.ab;
import com.google.android.youtube.player.internal.m;
import com.google.android.youtube.player.internal.y;
import com.google.android.youtube.player.internal.z;

public enum YouTubeInitializationResult
{
  static
  {
    INTERNAL_ERROR = new YouTubeInitializationResult("INTERNAL_ERROR", 1);
    UNKNOWN_ERROR = new YouTubeInitializationResult("UNKNOWN_ERROR", 2);
    SERVICE_MISSING = new YouTubeInitializationResult("SERVICE_MISSING", 3);
    SERVICE_VERSION_UPDATE_REQUIRED = new YouTubeInitializationResult("SERVICE_VERSION_UPDATE_REQUIRED", 4);
    SERVICE_DISABLED = new YouTubeInitializationResult("SERVICE_DISABLED", 5);
    SERVICE_INVALID = new YouTubeInitializationResult("SERVICE_INVALID", 6);
    ERROR_CONNECTING_TO_SERVICE = new YouTubeInitializationResult("ERROR_CONNECTING_TO_SERVICE", 7);
    CLIENT_LIBRARY_UPDATE_REQUIRED = new YouTubeInitializationResult("CLIENT_LIBRARY_UPDATE_REQUIRED", 8);
    NETWORK_ERROR = new YouTubeInitializationResult("NETWORK_ERROR", 9);
  }
  
  private YouTubeInitializationResult() {}
  
  public final Dialog getErrorDialog(Activity paramActivity, int paramInt)
  {
    return getErrorDialog(paramActivity, paramInt, null);
  }
  
  public final Dialog getErrorDialog(Activity paramActivity, int paramInt, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity);
    if (paramOnCancelListener != null) {
      localBuilder.setOnCancelListener(paramOnCancelListener);
    }
    switch (1.a[ordinal()])
    {
    default: 
      paramOnCancelListener = null;
      paramOnCancelListener = new a(paramActivity, paramOnCancelListener, paramInt);
      paramActivity = new m(paramActivity);
      switch (1.a[ordinal()])
      {
      default: 
        paramActivity = String.valueOf(name());
        if (paramActivity.length() == 0) {}
        break;
      }
      break;
    }
    for (paramActivity = "Unexpected errorReason: ".concat(paramActivity);; paramActivity = new String("Unexpected errorReason: "))
    {
      throw new IllegalArgumentException(paramActivity);
      paramOnCancelListener = z.b(z.a(paramActivity));
      break;
      paramOnCancelListener = z.a(z.a(paramActivity));
      break;
      return localBuilder.setTitle(paramActivity.b).setMessage(paramActivity.c).setPositiveButton(paramActivity.d, paramOnCancelListener).create();
      return localBuilder.setTitle(paramActivity.e).setMessage(paramActivity.f).setPositiveButton(paramActivity.g, paramOnCancelListener).create();
      return localBuilder.setTitle(paramActivity.h).setMessage(paramActivity.i).setPositiveButton(paramActivity.j, paramOnCancelListener).create();
    }
  }
  
  public final boolean isUserRecoverableError()
  {
    switch (1.a[ordinal()])
    {
    default: 
      return false;
    }
    return true;
  }
  
  private static final class a
    implements DialogInterface.OnClickListener
  {
    private final Activity a;
    private final Intent b;
    private final int c;
    
    public a(Activity paramActivity, Intent paramIntent, int paramInt)
    {
      this.a = ((Activity)ab.a(paramActivity));
      this.b = ((Intent)ab.a(paramIntent));
      this.c = ((Integer)ab.a(Integer.valueOf(paramInt))).intValue();
    }
    
    public final void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      try
      {
        this.a.startActivityForResult(this.b, this.c);
        paramDialogInterface.dismiss();
        return;
      }
      catch (ActivityNotFoundException paramDialogInterface)
      {
        y.a("Can't perform resolution for YouTubeInitalizationError", paramDialogInterface);
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\youtube\player\YouTubeInitializationResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */