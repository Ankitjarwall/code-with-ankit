package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.internal.zzaad.zza;
import com.google.android.gms.internal.zzaam;
import com.google.android.gms.internal.zzaax;
import com.google.android.gms.internal.zzaax.zza;
import com.google.android.gms.internal.zzaay;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzabh.zzb;
import com.google.android.gms.internal.zzabi;
import com.google.android.gms.internal.zzabm;
import com.google.android.gms.internal.zzabr;
import com.google.android.gms.internal.zzabs;
import com.google.android.gms.internal.zzabv;
import com.google.android.gms.internal.zzabz;
import com.google.android.gms.internal.zzzy;
import com.google.android.gms.internal.zzzz;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzc<O extends Api.ApiOptions>
{
  private final Context mContext;
  private final int mId;
  private final Account zzahh;
  private final Api<O> zzaxf;
  private final O zzayT;
  private final zzzz<O> zzayU;
  private final GoogleApiClient zzayV;
  private final zzabs zzayW;
  protected final zzaax zzayX;
  private final Looper zzrs;
  
  @Deprecated
  @MainThread
  public zzc(@NonNull Activity paramActivity, Api<O> paramApi, O paramO, Looper paramLooper, zzabs paramzzabs)
  {
    this(paramActivity, paramApi, paramO, new zzc.zza.zza().zzb(paramLooper).zza(paramzzabs).zzvk());
  }
  
  @MainThread
  public zzc(@NonNull Activity paramActivity, Api<O> paramApi, O paramO, zza paramzza)
  {
    zzac.zzb(paramActivity, "Null activity is not permitted.");
    zzac.zzb(paramApi, "Api must not be null.");
    zzac.zzb(paramzza, "Settings must not be null; use Settings.createDefault() instead.");
    this.mContext = paramActivity.getApplicationContext();
    this.zzaxf = paramApi;
    this.zzayT = paramO;
    this.zzrs = paramzza.zzaza;
    this.zzayU = zzzz.zza(this.zzaxf, this.zzayT);
    this.zzayV = new zzaay(this);
    this.zzayX = zzaax.zzaP(this.mContext);
    this.mId = this.zzayX.zzwz();
    this.zzayW = paramzza.zzayZ;
    this.zzahh = paramzza.account;
    zzaam.zza(paramActivity, this.zzayX, this.zzayU);
    this.zzayX.zzb(this);
  }
  
  @Deprecated
  public zzc(@NonNull Activity paramActivity, Api<O> paramApi, O paramO, zzabs paramzzabs)
  {
    this(paramActivity, paramApi, paramO, new zzc.zza.zza().zza(paramzzabs).zzb(paramActivity.getMainLooper()).zzvk());
  }
  
  protected zzc(@NonNull Context paramContext, Api<O> paramApi, Looper paramLooper)
  {
    zzac.zzb(paramContext, "Null context is not permitted.");
    zzac.zzb(paramApi, "Api must not be null.");
    zzac.zzb(paramLooper, "Looper must not be null.");
    this.mContext = paramContext.getApplicationContext();
    this.zzaxf = paramApi;
    this.zzayT = null;
    this.zzrs = paramLooper;
    this.zzayU = zzzz.zzb(paramApi);
    this.zzayV = new zzaay(this);
    this.zzayX = zzaax.zzaP(this.mContext);
    this.mId = this.zzayX.zzwz();
    this.zzayW = new zzzy();
    this.zzahh = null;
  }
  
  @Deprecated
  public zzc(@NonNull Context paramContext, Api<O> paramApi, O paramO, Looper paramLooper, zzabs paramzzabs)
  {
    this(paramContext, paramApi, paramO, new zzc.zza.zza().zzb(paramLooper).zza(paramzzabs).zzvk());
  }
  
  public zzc(@NonNull Context paramContext, Api<O> paramApi, O paramO, zza paramzza)
  {
    zzac.zzb(paramContext, "Null context is not permitted.");
    zzac.zzb(paramApi, "Api must not be null.");
    zzac.zzb(paramzza, "Settings must not be null; use Settings.createDefault() instead.");
    this.mContext = paramContext.getApplicationContext();
    this.zzaxf = paramApi;
    this.zzayT = paramO;
    this.zzrs = paramzza.zzaza;
    this.zzayU = zzzz.zza(this.zzaxf, this.zzayT);
    this.zzayV = new zzaay(this);
    this.zzayX = zzaax.zzaP(this.mContext);
    this.mId = this.zzayX.zzwz();
    this.zzayW = paramzza.zzayZ;
    this.zzahh = paramzza.account;
    this.zzayX.zzb(this);
  }
  
  @Deprecated
  public zzc(@NonNull Context paramContext, Api<O> paramApi, O paramO, zzabs paramzzabs)
  {
    this(paramContext, paramApi, paramO, new zzc.zza.zza().zza(paramzzabs).zzvk());
  }
  
  private <A extends Api.zzb, T extends zzaad.zza<? extends Result, A>> T zza(int paramInt, @NonNull T paramT)
  {
    paramT.zzvI();
    this.zzayX.zza(this, paramInt, paramT);
    return paramT;
  }
  
  private <TResult, A extends Api.zzb> Task<TResult> zza(int paramInt, @NonNull zzabv<A, TResult> paramzzabv)
  {
    TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    this.zzayX.zza(this, paramInt, paramzzabv, localTaskCompletionSource, this.zzayW);
    return localTaskCompletionSource.getTask();
  }
  
  public GoogleApiClient asGoogleApiClient()
  {
    return this.zzayV;
  }
  
  @WorkerThread
  public Api.zze buildApiClient(Looper paramLooper, zzaax.zza<O> paramzza)
  {
    zzg localzzg = new GoogleApiClient.Builder(this.mContext).zze(this.zzahh).zzvp();
    return this.zzaxf.zzvf().zza(this.mContext, paramLooper, localzzg, this.zzayT, paramzza, paramzza);
  }
  
  public zzabr createSignInCoordinator(Context paramContext, Handler paramHandler)
  {
    return new zzabr(paramContext, paramHandler);
  }
  
  public <A extends Api.zzb, T extends zzaad.zza<? extends Result, A>> T doBestEffortWrite(@NonNull T paramT)
  {
    return zza(2, paramT);
  }
  
  public <TResult, A extends Api.zzb> Task<TResult> doBestEffortWrite(zzabv<A, TResult> paramzzabv)
  {
    return zza(2, paramzzabv);
  }
  
  public <A extends Api.zzb, T extends zzaad.zza<? extends Result, A>> T doRead(@NonNull T paramT)
  {
    return zza(0, paramT);
  }
  
  public <TResult, A extends Api.zzb> Task<TResult> doRead(zzabv<A, TResult> paramzzabv)
  {
    return zza(0, paramzzabv);
  }
  
  public <A extends Api.zzb, T extends zzabm<A, ?>, U extends zzabz<A, ?>> Task<Void> doRegisterEventListener(@NonNull T paramT, U paramU)
  {
    zzac.zzw(paramT);
    zzac.zzw(paramU);
    zzac.zzb(paramT.zzwW(), "Listener has already been released.");
    zzac.zzb(paramU.zzwW(), "Listener has already been released.");
    zzac.zzb(paramT.zzwW().equals(paramU.zzwW()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
    return this.zzayX.zza(this, paramT, paramU);
  }
  
  public Task<Void> doUnregisterEventListener(@NonNull zzabh.zzb<?> paramzzb)
  {
    zzac.zzb(paramzzb, "Listener key cannot be null.");
    return this.zzayX.zza(this, paramzzb);
  }
  
  public <A extends Api.zzb, T extends zzaad.zza<? extends Result, A>> T doWrite(@NonNull T paramT)
  {
    return zza(1, paramT);
  }
  
  public <TResult, A extends Api.zzb> Task<TResult> doWrite(zzabv<A, TResult> paramzzabv)
  {
    return zza(1, paramzzabv);
  }
  
  public Api<O> getApi()
  {
    return this.zzaxf;
  }
  
  public zzzz<O> getApiKey()
  {
    return this.zzayU;
  }
  
  public O getApiOptions()
  {
    return this.zzayT;
  }
  
  public Context getApplicationContext()
  {
    return this.mContext;
  }
  
  public int getInstanceId()
  {
    return this.mId;
  }
  
  public Looper getLooper()
  {
    return this.zzrs;
  }
  
  public <L> zzabh<L> registerListener(@NonNull L paramL, String paramString)
  {
    return zzabi.zzb(paramL, this.zzrs, paramString);
  }
  
  public static class zza
  {
    public static final zza zzayY = new zza().zzvk();
    public final Account account;
    public final zzabs zzayZ;
    public final Looper zzaza;
    
    private zza(zzabs paramzzabs, Account paramAccount, Looper paramLooper)
    {
      this.zzayZ = paramzzabs;
      this.account = paramAccount;
      this.zzaza = paramLooper;
    }
    
    public static class zza
    {
      private zzabs zzayW;
      private Looper zzrs;
      
      public zza zza(zzabs paramzzabs)
      {
        zzac.zzb(paramzzabs, "StatusExceptionMapper must not be null.");
        this.zzayW = paramzzabs;
        return this;
      }
      
      public zza zzb(Looper paramLooper)
      {
        zzac.zzb(paramLooper, "Looper must not be null.");
        this.zzrs = paramLooper;
        return this;
      }
      
      public zzc.zza zzvk()
      {
        if (this.zzayW == null) {
          this.zzayW = new zzzy();
        }
        if (this.zzrs == null) {
          if (Looper.myLooper() == null) {
            break label56;
          }
        }
        label56:
        for (this.zzrs = Looper.myLooper();; this.zzrs = Looper.getMainLooper()) {
          return new zzc.zza(this.zzayW, null, this.zzrs, null);
        }
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\api\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */