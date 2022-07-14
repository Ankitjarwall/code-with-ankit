package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;
import com.google.android.gms.R.drawable;
import com.google.android.gms.R.string;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.common.internal.zzi;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.internal.zzaaz;
import com.google.android.gms.internal.zzaaz.zza;
import com.google.android.gms.internal.zzabf;
import com.google.android.gms.internal.zzabj;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.atomic.AtomicBoolean;

public class GoogleApiAvailability
  extends zze
{
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zze.GOOGLE_PLAY_SERVICES_VERSION_CODE;
  private static final GoogleApiAvailability zzaym = new GoogleApiAvailability();
  
  public static GoogleApiAvailability getInstance()
  {
    return zzaym;
  }
  
  public Dialog getErrorDialog(Activity paramActivity, int paramInt1, int paramInt2)
  {
    return getErrorDialog(paramActivity, paramInt1, paramInt2, null);
  }
  
  public Dialog getErrorDialog(Activity paramActivity, int paramInt1, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    return zza(paramActivity, paramInt1, zzi.zza(paramActivity, zzb(paramActivity, paramInt1, "d"), paramInt2), paramOnCancelListener);
  }
  
  @Nullable
  public PendingIntent getErrorResolutionPendingIntent(Context paramContext, int paramInt1, int paramInt2)
  {
    return super.getErrorResolutionPendingIntent(paramContext, paramInt1, paramInt2);
  }
  
  @Nullable
  public PendingIntent getErrorResolutionPendingIntent(Context paramContext, ConnectionResult paramConnectionResult)
  {
    if (paramConnectionResult.hasResolution()) {
      return paramConnectionResult.getResolution();
    }
    return getErrorResolutionPendingIntent(paramContext, paramConnectionResult.getErrorCode(), 0);
  }
  
  public final String getErrorString(int paramInt)
  {
    return super.getErrorString(paramInt);
  }
  
  @Nullable
  public String getOpenSourceSoftwareLicenseInfo(Context paramContext)
  {
    return super.getOpenSourceSoftwareLicenseInfo(paramContext);
  }
  
  public int isGooglePlayServicesAvailable(Context paramContext)
  {
    return super.isGooglePlayServicesAvailable(paramContext);
  }
  
  public final boolean isUserResolvableError(int paramInt)
  {
    return super.isUserResolvableError(paramInt);
  }
  
  @MainThread
  public Task<Void> makeGooglePlayServicesAvailable(Activity paramActivity)
  {
    zzac.zzdj("makeGooglePlayServicesAvailable must be called from the main thread");
    int i = isGooglePlayServicesAvailable(paramActivity);
    if (i == 0) {
      return Tasks.forResult(null);
    }
    paramActivity = zzabj.zzu(paramActivity);
    paramActivity.zzk(new ConnectionResult(i, null));
    return paramActivity.getTask();
  }
  
  public boolean showErrorDialogFragment(Activity paramActivity, int paramInt1, int paramInt2)
  {
    return showErrorDialogFragment(paramActivity, paramInt1, paramInt2, null);
  }
  
  public boolean showErrorDialogFragment(Activity paramActivity, int paramInt1, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    Dialog localDialog = getErrorDialog(paramActivity, paramInt1, paramInt2, paramOnCancelListener);
    if (localDialog == null) {
      return false;
    }
    zza(paramActivity, localDialog, "GooglePlayServicesErrorDialog", paramOnCancelListener);
    return true;
  }
  
  public void showErrorNotification(Context paramContext, int paramInt)
  {
    zza(paramContext, paramInt, null);
  }
  
  public void showErrorNotification(Context paramContext, ConnectionResult paramConnectionResult)
  {
    PendingIntent localPendingIntent = getErrorResolutionPendingIntent(paramContext, paramConnectionResult);
    zza(paramContext, paramConnectionResult.getErrorCode(), null, localPendingIntent);
  }
  
  public Dialog zza(Activity paramActivity, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    Object localObject = new ProgressBar(paramActivity, null, 16842874);
    ((ProgressBar)localObject).setIndeterminate(true);
    ((ProgressBar)localObject).setVisibility(0);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity);
    localBuilder.setView((View)localObject);
    localBuilder.setMessage(zzh.zzi(paramActivity, 18));
    localBuilder.setPositiveButton("", null);
    localObject = localBuilder.create();
    zza(paramActivity, (Dialog)localObject, "GooglePlayServicesUpdatingDialog", paramOnCancelListener);
    return (Dialog)localObject;
  }
  
  Dialog zza(Context paramContext, int paramInt, zzi paramzzi, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    AlertDialog.Builder localBuilder = null;
    if (paramInt == 0) {
      return null;
    }
    Object localObject = new TypedValue();
    paramContext.getTheme().resolveAttribute(16843529, (TypedValue)localObject, true);
    if ("Theme.Dialog.Alert".equals(paramContext.getResources().getResourceEntryName(((TypedValue)localObject).resourceId))) {
      localBuilder = new AlertDialog.Builder(paramContext, 5);
    }
    localObject = localBuilder;
    if (localBuilder == null) {
      localObject = new AlertDialog.Builder(paramContext);
    }
    ((AlertDialog.Builder)localObject).setMessage(zzh.zzi(paramContext, paramInt));
    if (paramOnCancelListener != null) {
      ((AlertDialog.Builder)localObject).setOnCancelListener(paramOnCancelListener);
    }
    paramOnCancelListener = zzh.zzk(paramContext, paramInt);
    if (paramOnCancelListener != null) {
      ((AlertDialog.Builder)localObject).setPositiveButton(paramOnCancelListener, paramzzi);
    }
    paramContext = zzh.zzg(paramContext, paramInt);
    if (paramContext != null) {
      ((AlertDialog.Builder)localObject).setTitle(paramContext);
    }
    return ((AlertDialog.Builder)localObject).create();
  }
  
  @Nullable
  public PendingIntent zza(Context paramContext, int paramInt1, int paramInt2, @Nullable String paramString)
  {
    return super.zza(paramContext, paramInt1, paramInt2, paramString);
  }
  
  @Nullable
  public zzaaz zza(Context paramContext, zzaaz.zza paramzza)
  {
    Object localObject = new IntentFilter("android.intent.action.PACKAGE_ADDED");
    ((IntentFilter)localObject).addDataScheme("package");
    zzaaz localzzaaz = new zzaaz(paramzza);
    paramContext.registerReceiver(localzzaaz, (IntentFilter)localObject);
    localzzaaz.setContext(paramContext);
    localObject = localzzaaz;
    if (!zzA(paramContext, "com.google.android.gms"))
    {
      paramzza.zzvE();
      localzzaaz.unregister();
      localObject = null;
    }
    return (zzaaz)localObject;
  }
  
  void zza(Activity paramActivity, Dialog paramDialog, String paramString, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    try
    {
      bool = paramActivity instanceof FragmentActivity;
      if (bool)
      {
        paramActivity = ((FragmentActivity)paramActivity).getSupportFragmentManager();
        SupportErrorDialogFragment.newInstance(paramDialog, paramOnCancelListener).show(paramActivity, paramString);
        return;
      }
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      for (;;)
      {
        boolean bool = false;
      }
      paramActivity = paramActivity.getFragmentManager();
      ErrorDialogFragment.newInstance(paramDialog, paramOnCancelListener).show(paramActivity, paramString);
    }
  }
  
  public void zza(Context paramContext, int paramInt, String paramString)
  {
    zza(paramContext, paramInt, paramString, zza(paramContext, paramInt, 0, "n"));
  }
  
  @TargetApi(20)
  void zza(Context paramContext, int paramInt, String paramString, PendingIntent paramPendingIntent)
  {
    if (paramInt == 18) {
      zzaD(paramContext);
    }
    do
    {
      return;
      if (paramPendingIntent != null) {
        break;
      }
    } while (paramInt != 6);
    Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
    return;
    String str1 = zzh.zzh(paramContext, paramInt);
    String str2 = zzh.zzj(paramContext, paramInt);
    Resources localResources = paramContext.getResources();
    if (zzj.zzba(paramContext))
    {
      zzac.zzav(zzt.zzzm());
      paramPendingIntent = new Notification.Builder(paramContext).setSmallIcon(paramContext.getApplicationInfo().icon).setPriority(2).setAutoCancel(true).setContentTitle(str1).setStyle(new Notification.BigTextStyle().bigText(str2)).addAction(R.drawable.common_full_open_on_phone, localResources.getString(R.string.common_open_on_phone), paramPendingIntent).build();
      switch (paramInt)
      {
      }
    }
    for (paramInt = 39789;; paramInt = 10436)
    {
      paramContext = (NotificationManager)paramContext.getSystemService("notification");
      if (paramString != null) {
        break label278;
      }
      paramContext.notify(paramInt, paramPendingIntent);
      return;
      paramPendingIntent = new NotificationCompat.Builder(paramContext).setSmallIcon(17301642).setTicker(localResources.getString(R.string.common_google_play_services_notification_ticker)).setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentIntent(paramPendingIntent).setContentTitle(str1).setContentText(str2).setLocalOnly(true).setStyle(new NotificationCompat.BigTextStyle().bigText(str2)).build();
      break;
      zzg.zzayB.set(false);
    }
    label278:
    paramContext.notify(paramString, paramInt, paramPendingIntent);
  }
  
  public boolean zza(Activity paramActivity, @NonNull zzabf paramzzabf, int paramInt1, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    paramzzabf = zza(paramActivity, paramInt1, zzi.zza(paramzzabf, zzb(paramActivity, paramInt1, "d"), paramInt2), paramOnCancelListener);
    if (paramzzabf == null) {
      return false;
    }
    zza(paramActivity, paramzzabf, "GooglePlayServicesErrorDialog", paramOnCancelListener);
    return true;
  }
  
  public boolean zza(Context paramContext, ConnectionResult paramConnectionResult, int paramInt)
  {
    PendingIntent localPendingIntent = getErrorResolutionPendingIntent(paramContext, paramConnectionResult);
    if (localPendingIntent != null)
    {
      zza(paramContext, paramConnectionResult.getErrorCode(), null, GoogleApiActivity.zza(paramContext, localPendingIntent, paramInt));
      return true;
    }
    return false;
  }
  
  public int zzaC(Context paramContext)
  {
    return super.zzaC(paramContext);
  }
  
  void zzaD(Context paramContext)
  {
    new zza(paramContext).sendEmptyMessageDelayed(1, 120000L);
  }
  
  @Nullable
  public Intent zzb(Context paramContext, int paramInt, @Nullable String paramString)
  {
    return super.zzb(paramContext, paramInt, paramString);
  }
  
  @Deprecated
  @Nullable
  public Intent zzcw(int paramInt)
  {
    return super.zzcw(paramInt);
  }
  
  public boolean zzd(Context paramContext, int paramInt)
  {
    return super.zzd(paramContext, paramInt);
  }
  
  @SuppressLint({"HandlerLeak"})
  private class zza
    extends Handler
  {
    private final Context zzwi;
    
    public zza(Context paramContext) {}
    
    public void handleMessage(Message paramMessage)
    {
      int i;
      switch (paramMessage.what)
      {
      default: 
        i = paramMessage.what;
        Log.w("GoogleApiAvailability", 50 + "Don't know how to handle this message: " + i);
      }
      do
      {
        return;
        i = GoogleApiAvailability.this.isGooglePlayServicesAvailable(this.zzwi);
      } while (!GoogleApiAvailability.this.isUserResolvableError(i));
      GoogleApiAvailability.this.showErrorNotification(this.zzwi, i);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\GoogleApiAvailability.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */