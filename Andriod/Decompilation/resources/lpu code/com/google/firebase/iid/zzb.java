package com.google.firebase.iid;

import android.app.Service;
import android.content.BroadcastReceiver.PendingResult;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.google.android.gms.common.stats.zza;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class zzb
  extends Service
{
  @VisibleForTesting
  final ExecutorService zzbtK = Executors.newSingleThreadExecutor();
  private Binder zzckT;
  private int zzckU;
  private int zzckV = 0;
  private final Object zzrJ = new Object();
  
  private void zzC(Intent arg1)
  {
    if (??? != null) {
      WakefulBroadcastReceiver.completeWakefulIntent(???);
    }
    synchronized (this.zzrJ)
    {
      this.zzckV -= 1;
      if (this.zzckV == 0) {
        zzqE(this.zzckU);
      }
      return;
    }
  }
  
  public abstract void handleIntent(Intent paramIntent);
  
  public final IBinder onBind(Intent paramIntent)
  {
    try
    {
      if (Log.isLoggable("EnhancedIntentService", 3)) {
        Log.d("EnhancedIntentService", "Service received bind request");
      }
      if (this.zzckT == null) {
        this.zzckT = new zzb(this);
      }
      paramIntent = this.zzckT;
      return paramIntent;
    }
    finally {}
  }
  
  public final int onStartCommand(final Intent paramIntent, int paramInt1, int paramInt2)
  {
    synchronized (this.zzrJ)
    {
      this.zzckU = paramInt2;
      this.zzckV += 1;
      ??? = zzD(paramIntent);
      if (??? == null)
      {
        zzC(paramIntent);
        return 2;
      }
    }
    if (zzE((Intent)???))
    {
      zzC(paramIntent);
      return 2;
    }
    this.zzbtK.execute(new Runnable()
    {
      public void run()
      {
        zzb.this.handleIntent(localObject);
        zzb.zza(zzb.this, paramIntent);
      }
    });
    return 3;
  }
  
  protected Intent zzD(Intent paramIntent)
  {
    return paramIntent;
  }
  
  public boolean zzE(Intent paramIntent)
  {
    return false;
  }
  
  boolean zzqE(int paramInt)
  {
    return stopSelfResult(paramInt);
  }
  
  static class zza
  {
    final Intent intent;
    private final BroadcastReceiver.PendingResult zzckY;
    private boolean zzckZ = false;
    private final ScheduledFuture<?> zzcla;
    
    zza(final Intent paramIntent, BroadcastReceiver.PendingResult paramPendingResult, ScheduledExecutorService paramScheduledExecutorService)
    {
      this.intent = paramIntent;
      this.zzckY = paramPendingResult;
      this.zzcla = paramScheduledExecutorService.schedule(new Runnable()
      {
        public void run()
        {
          String str = String.valueOf(paramIntent.getAction());
          Log.w("EnhancedIntentService", String.valueOf(str).length() + 61 + "Service took too long to process intent: " + str + " App may get closed.");
          zzb.zza.this.finish();
        }
      }, 9500L, TimeUnit.MILLISECONDS);
    }
    
    void finish()
    {
      try
      {
        if (!this.zzckZ)
        {
          this.zzckY.finish();
          this.zzcla.cancel(false);
          this.zzckZ = true;
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
  
  public static class zzb
    extends Binder
  {
    private final zzb zzclc;
    
    zzb(zzb paramzzb)
    {
      this.zzclc = paramzzb;
    }
    
    public void zza(final zzb.zza paramzza)
    {
      if (Binder.getCallingUid() != Process.myUid()) {
        throw new SecurityException("Binding only allowed within app");
      }
      if (Log.isLoggable("EnhancedIntentService", 3)) {
        Log.d("EnhancedIntentService", "service received new intent via bind strategy");
      }
      if (this.zzclc.zzE(paramzza.intent))
      {
        paramzza.finish();
        return;
      }
      if (Log.isLoggable("EnhancedIntentService", 3)) {
        Log.d("EnhancedIntentService", "intent being queued for bg execution");
      }
      this.zzclc.zzbtK.execute(new Runnable()
      {
        public void run()
        {
          if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "bg processing of the intent starting now");
          }
          zzb.this.handleIntent(paramzza.intent);
          paramzza.finish();
        }
      });
    }
  }
  
  public static class zzc
    implements ServiceConnection
  {
    private final Intent zzclf;
    private final ScheduledExecutorService zzclg;
    private final Queue<zzb.zza> zzclh = new LinkedList();
    private zzb.zzb zzcli;
    private boolean zzclj = false;
    private final Context zzqn;
    
    public zzc(Context paramContext, String paramString)
    {
      this(paramContext, paramString, new ScheduledThreadPoolExecutor(0));
    }
    
    @VisibleForTesting
    zzc(Context paramContext, String paramString, ScheduledExecutorService paramScheduledExecutorService)
    {
      this.zzqn = paramContext.getApplicationContext();
      this.zzclf = new Intent(paramString).setPackage(this.zzqn.getPackageName());
      this.zzclg = paramScheduledExecutorService;
    }
    
    private void zzwH()
    {
      try
      {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
          Log.d("EnhancedIntentService", "flush queue called");
        }
        for (;;)
        {
          if (this.zzclh.isEmpty()) {
            break label190;
          }
          if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "found intent to be delivered");
          }
          if ((this.zzcli == null) || (!this.zzcli.isBinderAlive())) {
            break;
          }
          if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "binder is alive, sending the intent.");
          }
          zzb.zza localzza = (zzb.zza)this.zzclh.poll();
          this.zzcli.zza(localzza);
        }
        if (!Log.isLoggable("EnhancedIntentService", 3)) {
          break label156;
        }
      }
      finally {}
      boolean bool;
      if (!this.zzclj)
      {
        bool = true;
        Log.d("EnhancedIntentService", 39 + "binder is dead. start connection? " + bool);
        label156:
        if (!this.zzclj) {
          this.zzclj = true;
        }
      }
      for (;;)
      {
        try
        {
          bool = zza.zzyJ().zza(this.zzqn, this.zzclf, this, 65);
          if (bool)
          {
            label190:
            return;
            bool = false;
            break;
          }
          Log.e("EnhancedIntentService", "binding to the service failed");
        }
        catch (SecurityException localSecurityException)
        {
          Log.e("EnhancedIntentService", "Exception while binding the service", localSecurityException);
          continue;
        }
        if (!this.zzclh.isEmpty()) {
          ((zzb.zza)this.zzclh.poll()).finish();
        }
      }
    }
    
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      try
      {
        this.zzclj = false;
        this.zzcli = ((zzb.zzb)paramIBinder);
        if (Log.isLoggable("EnhancedIntentService", 3))
        {
          paramComponentName = String.valueOf(paramComponentName);
          Log.d("EnhancedIntentService", String.valueOf(paramComponentName).length() + 20 + "onServiceConnected: " + paramComponentName);
        }
        zzwH();
        return;
      }
      finally {}
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      if (Log.isLoggable("EnhancedIntentService", 3))
      {
        paramComponentName = String.valueOf(paramComponentName);
        Log.d("EnhancedIntentService", String.valueOf(paramComponentName).length() + 23 + "onServiceDisconnected: " + paramComponentName);
      }
      zzwH();
    }
    
    public void zza(Intent paramIntent, BroadcastReceiver.PendingResult paramPendingResult)
    {
      try
      {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
          Log.d("EnhancedIntentService", "new intent queued in the bind-strategy delivery");
        }
        this.zzclh.add(new zzb.zza(paramIntent, paramPendingResult, this.zzclg));
        zzwH();
        return;
      }
      finally {}
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\iid\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */