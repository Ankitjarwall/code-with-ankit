package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzg.zza;
import com.google.android.gms.internal.zzaaa;
import com.google.android.gms.internal.zzaad.zza;
import com.google.android.gms.internal.zzaag;
import com.google.android.gms.internal.zzaat;
import com.google.android.gms.internal.zzabd;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzabq;
import com.google.android.gms.internal.zzabx;
import com.google.android.gms.internal.zzbah;
import com.google.android.gms.internal.zzbai;
import com.google.android.gms.internal.zzbaj;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GoogleApiClient
{
  public static final int SIGN_IN_MODE_OPTIONAL = 2;
  public static final int SIGN_IN_MODE_REQUIRED = 1;
  private static final Set<GoogleApiClient> zzazc = Collections.newSetFromMap(new WeakHashMap());
  
  public static void dumpAll(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    synchronized (zzazc)
    {
      String str = String.valueOf(paramString).concat("  ");
      Iterator localIterator = zzazc.iterator();
      int i = 0;
      while (localIterator.hasNext())
      {
        GoogleApiClient localGoogleApiClient = (GoogleApiClient)localIterator.next();
        paramPrintWriter.append(paramString).append("GoogleApiClient#").println(i);
        localGoogleApiClient.dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
        i += 1;
      }
      return;
    }
  }
  
  public static Set<GoogleApiClient> zzvm()
  {
    synchronized (zzazc)
    {
      Set localSet2 = zzazc;
      return localSet2;
    }
  }
  
  public abstract ConnectionResult blockingConnect();
  
  public abstract ConnectionResult blockingConnect(long paramLong, @NonNull TimeUnit paramTimeUnit);
  
  public abstract PendingResult<Status> clearDefaultAccountAndReconnect();
  
  public abstract void connect();
  
  public void connect(int paramInt)
  {
    throw new UnsupportedOperationException();
  }
  
  public abstract void disconnect();
  
  public abstract void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
  
  @NonNull
  public abstract ConnectionResult getConnectionResult(@NonNull Api<?> paramApi);
  
  public Context getContext()
  {
    throw new UnsupportedOperationException();
  }
  
  public Looper getLooper()
  {
    throw new UnsupportedOperationException();
  }
  
  public abstract boolean hasConnectedApi(@NonNull Api<?> paramApi);
  
  public abstract boolean isConnected();
  
  public abstract boolean isConnecting();
  
  public abstract boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public abstract void reconnect();
  
  public abstract void registerConnectionCallbacks(@NonNull ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract void registerConnectionFailedListener(@NonNull OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public abstract void stopAutoManage(@NonNull FragmentActivity paramFragmentActivity);
  
  public abstract void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener paramOnConnectionFailedListener);
  
  @NonNull
  public <C extends Api.zze> C zza(@NonNull Api.zzc<C> paramzzc)
  {
    throw new UnsupportedOperationException();
  }
  
  public <A extends Api.zzb, R extends Result, T extends zzaad.zza<R, A>> T zza(@NonNull T paramT)
  {
    throw new UnsupportedOperationException();
  }
  
  public void zza(zzabx paramzzabx)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean zza(@NonNull Api<?> paramApi)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean zza(zzabq paramzzabq)
  {
    throw new UnsupportedOperationException();
  }
  
  public <A extends Api.zzb, T extends zzaad.zza<? extends Result, A>> T zzb(@NonNull T paramT)
  {
    throw new UnsupportedOperationException();
  }
  
  public void zzb(zzabx paramzzabx)
  {
    throw new UnsupportedOperationException();
  }
  
  public <L> zzabh<L> zzr(@NonNull L paramL)
  {
    throw new UnsupportedOperationException();
  }
  
  public void zzvn()
  {
    throw new UnsupportedOperationException();
  }
  
  public static final class Builder
  {
    private final Context mContext;
    private Account zzahh;
    private String zzaiq;
    private final Set<Scope> zzazd = new HashSet();
    private final Set<Scope> zzaze = new HashSet();
    private int zzazf;
    private View zzazg;
    private String zzazh;
    private final Map<Api<?>, zzg.zza> zzazi = new ArrayMap();
    private final Map<Api<?>, Api.ApiOptions> zzazj = new ArrayMap();
    private zzabd zzazk;
    private int zzazl = -1;
    private GoogleApiClient.OnConnectionFailedListener zzazm;
    private GoogleApiAvailability zzazn = GoogleApiAvailability.getInstance();
    private Api.zza<? extends zzbai, zzbaj> zzazo = zzbah.zzaie;
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zzazp = new ArrayList();
    private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zzazq = new ArrayList();
    private boolean zzazr = false;
    private Looper zzrs;
    
    public Builder(@NonNull Context paramContext)
    {
      this.mContext = paramContext;
      this.zzrs = paramContext.getMainLooper();
      this.zzaiq = paramContext.getPackageName();
      this.zzazh = paramContext.getClass().getName();
    }
    
    public Builder(@NonNull Context paramContext, @NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, @NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      this(paramContext);
      zzac.zzb(paramConnectionCallbacks, "Must provide a connected listener");
      this.zzazp.add(paramConnectionCallbacks);
      zzac.zzb(paramOnConnectionFailedListener, "Must provide a connection failed listener");
      this.zzazq.add(paramOnConnectionFailedListener);
    }
    
    private static <C extends Api.zze, O> C zza(Api.zza<C, O> paramzza, Object paramObject, Context paramContext, Looper paramLooper, zzg paramzzg, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      return paramzza.zza(paramContext, paramLooper, paramzzg, paramObject, paramConnectionCallbacks, paramOnConnectionFailedListener);
    }
    
    private Builder zza(@NonNull zzabd paramzzabd, int paramInt, @Nullable GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      if (paramInt >= 0) {}
      for (boolean bool = true;; bool = false)
      {
        zzac.zzb(bool, "clientId must be non-negative");
        this.zzazl = paramInt;
        this.zzazm = paramOnConnectionFailedListener;
        this.zzazk = paramzzabd;
        return this;
      }
    }
    
    private <O extends Api.ApiOptions> void zza(Api<O> paramApi, O paramO, Scope... paramVarArgs)
    {
      paramO = new HashSet(paramApi.zzve().zzp(paramO));
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        paramO.add(paramVarArgs[i]);
        i += 1;
      }
      this.zzazi.put(paramApi, new zzg.zza(paramO));
    }
    
    private void zzf(GoogleApiClient paramGoogleApiClient)
    {
      zzaaa.zza(this.zzazk).zza(this.zzazl, paramGoogleApiClient, this.zzazm);
    }
    
    private GoogleApiClient zzvq()
    {
      zzg localzzg = zzvp();
      Object localObject1 = null;
      Map localMap = localzzg.zzxN();
      ArrayMap localArrayMap1 = new ArrayMap();
      ArrayMap localArrayMap2 = new ArrayMap();
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = this.zzazj.keySet().iterator();
      int i = 0;
      Api localApi;
      Object localObject2;
      boolean bool;
      label109:
      Object localObject3;
      if (localIterator.hasNext())
      {
        localApi = (Api)localIterator.next();
        localObject2 = this.zzazj.get(localApi);
        if (localMap.get(localApi) != null)
        {
          bool = true;
          localArrayMap1.put(localApi, Boolean.valueOf(bool));
          localObject3 = new zzaag(localApi, bool);
          localArrayList.add(localObject3);
          Api.zza localzza = localApi.zzvf();
          localObject3 = zza(localzza, localObject2, this.mContext, this.zzrs, localzzg, (GoogleApiClient.ConnectionCallbacks)localObject3, (GoogleApiClient.OnConnectionFailedListener)localObject3);
          localArrayMap2.put(localApi.zzvg(), localObject3);
          if (localzza.getPriority() != 1) {
            break label498;
          }
          if (localObject2 == null) {
            break label297;
          }
          i = 1;
        }
      }
      label297:
      label305:
      label493:
      label498:
      for (;;)
      {
        if (((Api.zze)localObject3).zzrr())
        {
          localObject2 = localApi;
          if (localObject1 == null) {
            break label305;
          }
          localObject2 = String.valueOf(localApi.getName());
          localObject1 = String.valueOf(((Api)localObject1).getName());
          throw new IllegalStateException(String.valueOf(localObject2).length() + 21 + String.valueOf(localObject1).length() + (String)localObject2 + " cannot be used with " + (String)localObject1);
          bool = false;
          break label109;
          i = 0;
          continue;
        }
        localObject2 = localObject1;
        localObject1 = localObject2;
        break;
        if (localObject1 != null)
        {
          if (i != 0)
          {
            localObject1 = String.valueOf(((Api)localObject1).getName());
            throw new IllegalStateException(String.valueOf(localObject1).length() + 82 + "With using " + (String)localObject1 + ", GamesOptions can only be specified within GoogleSignInOptions.Builder");
          }
          if (this.zzahh != null) {
            break label493;
          }
        }
        for (bool = true;; bool = false)
        {
          zzac.zza(bool, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", new Object[] { ((Api)localObject1).getName() });
          zzac.zza(this.zzazd.equals(this.zzaze), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", new Object[] { ((Api)localObject1).getName() });
          i = zzaat.zza(localArrayMap2.values(), true);
          return new zzaat(this.mContext, new ReentrantLock(), this.zzrs, localzzg, this.zzazn, this.zzazo, localArrayMap1, this.zzazp, this.zzazq, localArrayMap2, this.zzazl, i, localArrayList, false);
        }
      }
    }
    
    public Builder addApi(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> paramApi)
    {
      zzac.zzb(paramApi, "Api must not be null");
      this.zzazj.put(paramApi, null);
      paramApi = paramApi.zzve().zzp(null);
      this.zzaze.addAll(paramApi);
      this.zzazd.addAll(paramApi);
      return this;
    }
    
    public <O extends Api.ApiOptions.HasOptions> Builder addApi(@NonNull Api<O> paramApi, @NonNull O paramO)
    {
      zzac.zzb(paramApi, "Api must not be null");
      zzac.zzb(paramO, "Null options are not permitted for this Api");
      this.zzazj.put(paramApi, paramO);
      paramApi = paramApi.zzve().zzp(paramO);
      this.zzaze.addAll(paramApi);
      this.zzazd.addAll(paramApi);
      return this;
    }
    
    public <O extends Api.ApiOptions.HasOptions> Builder addApiIfAvailable(@NonNull Api<O> paramApi, @NonNull O paramO, Scope... paramVarArgs)
    {
      zzac.zzb(paramApi, "Api must not be null");
      zzac.zzb(paramO, "Null options are not permitted for this Api");
      this.zzazj.put(paramApi, paramO);
      zza(paramApi, paramO, paramVarArgs);
      return this;
    }
    
    public Builder addApiIfAvailable(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> paramApi, Scope... paramVarArgs)
    {
      zzac.zzb(paramApi, "Api must not be null");
      this.zzazj.put(paramApi, null);
      zza(paramApi, null, paramVarArgs);
      return this;
    }
    
    public Builder addConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
    {
      zzac.zzb(paramConnectionCallbacks, "Listener must not be null");
      this.zzazp.add(paramConnectionCallbacks);
      return this;
    }
    
    public Builder addOnConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      zzac.zzb(paramOnConnectionFailedListener, "Listener must not be null");
      this.zzazq.add(paramOnConnectionFailedListener);
      return this;
    }
    
    public Builder addScope(@NonNull Scope paramScope)
    {
      zzac.zzb(paramScope, "Scope must not be null");
      this.zzazd.add(paramScope);
      return this;
    }
    
    public GoogleApiClient build()
    {
      boolean bool;
      if (!this.zzazj.isEmpty()) {
        bool = true;
      }
      for (;;)
      {
        zzac.zzb(bool, "must call addApi() to add at least one API");
        GoogleApiClient localGoogleApiClient = zzvq();
        synchronized (GoogleApiClient.zzvo())
        {
          GoogleApiClient.zzvo().add(localGoogleApiClient);
          if (this.zzazl >= 0) {
            zzf(localGoogleApiClient);
          }
          return localGoogleApiClient;
          bool = false;
        }
      }
    }
    
    public Builder enableAutoManage(@NonNull FragmentActivity paramFragmentActivity, int paramInt, @Nullable GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      return zza(new zzabd(paramFragmentActivity), paramInt, paramOnConnectionFailedListener);
    }
    
    public Builder enableAutoManage(@NonNull FragmentActivity paramFragmentActivity, @Nullable GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      return enableAutoManage(paramFragmentActivity, 0, paramOnConnectionFailedListener);
    }
    
    public Builder setAccountName(String paramString)
    {
      if (paramString == null) {}
      for (paramString = null;; paramString = new Account(paramString, "com.google"))
      {
        this.zzahh = paramString;
        return this;
      }
    }
    
    public Builder setGravityForPopups(int paramInt)
    {
      this.zzazf = paramInt;
      return this;
    }
    
    public Builder setHandler(@NonNull Handler paramHandler)
    {
      zzac.zzb(paramHandler, "Handler must not be null");
      this.zzrs = paramHandler.getLooper();
      return this;
    }
    
    public Builder setViewForPopups(@NonNull View paramView)
    {
      zzac.zzb(paramView, "View must not be null");
      this.zzazg = paramView;
      return this;
    }
    
    public Builder useDefaultAccount()
    {
      return setAccountName("<<default account>>");
    }
    
    public Builder zze(Account paramAccount)
    {
      this.zzahh = paramAccount;
      return this;
    }
    
    public zzg zzvp()
    {
      zzbaj localzzbaj = zzbaj.zzbEl;
      if (this.zzazj.containsKey(zzbah.API)) {
        localzzbaj = (zzbaj)this.zzazj.get(zzbah.API);
      }
      return new zzg(this.zzahh, this.zzazd, this.zzazi, this.zzazf, this.zzazg, this.zzaiq, this.zzazh, localzzbaj);
    }
  }
  
  public static abstract interface ConnectionCallbacks
  {
    public static final int CAUSE_NETWORK_LOST = 2;
    public static final int CAUSE_SERVICE_DISCONNECTED = 1;
    
    public abstract void onConnected(@Nullable Bundle paramBundle);
    
    public abstract void onConnectionSuspended(int paramInt);
  }
  
  public static abstract interface OnConnectionFailedListener
  {
    public abstract void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\api\GoogleApiClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */