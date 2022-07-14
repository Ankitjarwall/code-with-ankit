package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import com.google.android.gms.common.zze;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzf<T extends IInterface>
{
  public static final String[] zzaFs = { "service_esmobile", "service_googleme" };
  private final Context mContext;
  final Handler mHandler;
  private final zze zzaAQ;
  private int zzaFa;
  private long zzaFb;
  private long zzaFc;
  private int zzaFd;
  private long zzaFe;
  private final zzn zzaFf;
  private final Object zzaFg = new Object();
  private zzv zzaFh;
  protected zzf zzaFi;
  private T zzaFj;
  private final ArrayList<zze<?>> zzaFk = new ArrayList();
  private zzh zzaFl;
  private int zzaFm = 1;
  private final zzb zzaFn;
  private final zzc zzaFo;
  private final int zzaFp;
  private final String zzaFq;
  protected AtomicInteger zzaFr = new AtomicInteger(0);
  private final Object zzrJ = new Object();
  private final Looper zzrs;
  
  protected zzf(Context paramContext, Looper paramLooper, int paramInt, zzb paramzzb, zzc paramzzc, String paramString)
  {
    this(paramContext, paramLooper, zzn.zzaU(paramContext), zze.zzuY(), paramInt, (zzb)zzac.zzw(paramzzb), (zzc)zzac.zzw(paramzzc), paramString);
  }
  
  protected zzf(Context paramContext, Looper paramLooper, zzn paramzzn, zze paramzze, int paramInt, zzb paramzzb, zzc paramzzc, String paramString)
  {
    this.mContext = ((Context)zzac.zzb(paramContext, "Context must not be null"));
    this.zzrs = ((Looper)zzac.zzb(paramLooper, "Looper must not be null"));
    this.zzaFf = ((zzn)zzac.zzb(paramzzn, "Supervisor must not be null"));
    this.zzaAQ = ((zze)zzac.zzb(paramzze, "API availability must not be null"));
    this.mHandler = new zzd(paramLooper);
    this.zzaFp = paramInt;
    this.zzaFn = paramzzb;
    this.zzaFo = paramzzc;
    this.zzaFq = paramString;
  }
  
  private void zza(int paramInt, T paramT)
  {
    boolean bool = true;
    int i;
    int j;
    if (paramInt == 3)
    {
      i = 1;
      if (paramT == null) {
        break label116;
      }
      j = 1;
      label17:
      if (i != j) {
        break label122;
      }
    }
    for (;;)
    {
      zzac.zzaw(bool);
      for (;;)
      {
        synchronized (this.zzrJ)
        {
          this.zzaFm = paramInt;
          this.zzaFj = paramT;
          switch (paramInt)
          {
          case 2: 
            return;
            zzxx();
          }
        }
        zza(paramT);
        continue;
        zzxy();
      }
      i = 0;
      break;
      label116:
      j = 0;
      break label17;
      label122:
      bool = false;
    }
  }
  
  private boolean zza(int paramInt1, int paramInt2, T paramT)
  {
    synchronized (this.zzrJ)
    {
      if (this.zzaFm != paramInt1) {
        return false;
      }
      zza(paramInt2, paramT);
      return true;
    }
  }
  
  private void zzxx()
  {
    String str1;
    String str2;
    if (this.zzaFl != null)
    {
      str1 = String.valueOf(zzez());
      str2 = String.valueOf(zzxv());
      Log.e("GmsClient", String.valueOf(str1).length() + 70 + String.valueOf(str2).length() + "Calling connect() while still connected, missing disconnect() for " + str1 + " on " + str2);
      this.zzaFf.zzb(zzez(), zzxv(), this.zzaFl, zzxw());
      this.zzaFr.incrementAndGet();
    }
    this.zzaFl = new zzh(this.zzaFr.get());
    if (!this.zzaFf.zza(zzez(), zzxv(), this.zzaFl, zzxw()))
    {
      str1 = String.valueOf(zzez());
      str2 = String.valueOf(zzxv());
      Log.e("GmsClient", String.valueOf(str1).length() + 34 + String.valueOf(str2).length() + "unable to connect to service: " + str1 + " on " + str2);
      zza(16, null, this.zzaFr.get());
    }
  }
  
  private void zzxy()
  {
    if (this.zzaFl != null)
    {
      this.zzaFf.zzb(zzez(), zzxv(), this.zzaFl, zzxw());
      this.zzaFl = null;
    }
  }
  
  public void disconnect()
  {
    this.zzaFr.incrementAndGet();
    synchronized (this.zzaFk)
    {
      int j = this.zzaFk.size();
      int i = 0;
      while (i < j)
      {
        ((zze)this.zzaFk.get(i)).zzxI();
        i += 1;
      }
      this.zzaFk.clear();
    }
    synchronized (this.zzaFg)
    {
      this.zzaFh = null;
      zza(1, null);
      return;
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] arg4)
  {
    int i;
    synchronized (this.zzrJ)
    {
      i = this.zzaFm;
      paramFileDescriptor = this.zzaFj;
    }
    for (;;)
    {
      Object localObject;
      synchronized (this.zzaFg)
      {
        localObject = this.zzaFh;
        paramPrintWriter.append(paramString).append("mConnectState=");
        switch (i)
        {
        default: 
          paramPrintWriter.print("UNKNOWN");
          paramPrintWriter.append(" mService=");
          if (paramFileDescriptor != null) {
            break label529;
          }
          paramPrintWriter.append("null");
          paramPrintWriter.append(" mServiceBroker=");
          if (localObject != null) {
            break label562;
          }
          paramPrintWriter.println("null");
          paramFileDescriptor = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
          long l;
          if (this.zzaFc > 0L)
          {
            ??? = paramPrintWriter.append(paramString).append("lastConnectedTime=");
            l = this.zzaFc;
            localObject = String.valueOf(paramFileDescriptor.format(new Date(this.zzaFc)));
            ???.println(String.valueOf(localObject).length() + 21 + l + " " + (String)localObject);
          }
          if (this.zzaFb > 0L) {
            paramPrintWriter.append(paramString).append("lastSuspendedCause=");
          }
          switch (this.zzaFa)
          {
          default: 
            paramPrintWriter.append(String.valueOf(this.zzaFa));
            ??? = paramPrintWriter.append(" lastSuspendedTime=");
            l = this.zzaFb;
            localObject = String.valueOf(paramFileDescriptor.format(new Date(this.zzaFb)));
            ???.println(String.valueOf(localObject).length() + 21 + l + " " + (String)localObject);
            if (this.zzaFe > 0L)
            {
              paramPrintWriter.append(paramString).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzaFd));
              paramString = paramPrintWriter.append(" lastFailedTime=");
              l = this.zzaFe;
              paramFileDescriptor = String.valueOf(paramFileDescriptor.format(new Date(this.zzaFe)));
              paramString.println(String.valueOf(paramFileDescriptor).length() + 21 + l + " " + paramFileDescriptor);
            }
            return;
            paramString = finally;
            throw paramString;
          }
          break;
        }
      }
      paramPrintWriter.print("CONNECTING");
      continue;
      paramPrintWriter.print("CONNECTED");
      continue;
      paramPrintWriter.print("DISCONNECTING");
      continue;
      paramPrintWriter.print("DISCONNECTED");
      continue;
      label529:
      paramPrintWriter.append(zzeA()).append("@").append(Integer.toHexString(System.identityHashCode(paramFileDescriptor.asBinder())));
      continue;
      label562:
      paramPrintWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(((zzv)localObject).asBinder())));
      continue;
      paramPrintWriter.append("CAUSE_SERVICE_DISCONNECTED");
      continue;
      paramPrintWriter.append("CAUSE_NETWORK_LOST");
    }
  }
  
  public Account getAccount()
  {
    return null;
  }
  
  public final Context getContext()
  {
    return this.mContext;
  }
  
  public final Looper getLooper()
  {
    return this.zzrs;
  }
  
  public boolean isConnected()
  {
    for (;;)
    {
      synchronized (this.zzrJ)
      {
        if (this.zzaFm == 3)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public boolean isConnecting()
  {
    for (;;)
    {
      synchronized (this.zzrJ)
      {
        if (this.zzaFm == 2)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  @CallSuper
  protected void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    this.zzaFd = paramConnectionResult.getErrorCode();
    this.zzaFe = System.currentTimeMillis();
  }
  
  @CallSuper
  protected void onConnectionSuspended(int paramInt)
  {
    this.zzaFa = paramInt;
    this.zzaFb = System.currentTimeMillis();
  }
  
  protected void zza(int paramInt1, @Nullable Bundle paramBundle, int paramInt2)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(5, paramInt2, -1, new zzk(paramInt1, paramBundle)));
  }
  
  protected void zza(int paramInt1, IBinder paramIBinder, Bundle paramBundle, int paramInt2)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(1, paramInt2, -1, new zzj(paramInt1, paramIBinder, paramBundle)));
  }
  
  @CallSuper
  protected void zza(@NonNull T paramT)
  {
    this.zzaFc = System.currentTimeMillis();
  }
  
  public void zza(@NonNull zzf paramzzf)
  {
    this.zzaFi = ((zzf)zzac.zzb(paramzzf, "Connection progress callbacks cannot be null."));
    zza(2, null);
  }
  
  public void zza(@NonNull zzf paramzzf, int paramInt, @Nullable PendingIntent paramPendingIntent)
  {
    this.zzaFi = ((zzf)zzac.zzb(paramzzf, "Connection progress callbacks cannot be null."));
    this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzaFr.get(), paramInt, paramPendingIntent));
  }
  
  /* Error */
  @android.support.annotation.WorkerThread
  public void zza(zzr arg1, Set<Scope> paramSet)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 462	com/google/android/gms/common/internal/zzf:zzqL	()Landroid/os/Bundle;
    //   4: astore_3
    //   5: new 464	com/google/android/gms/common/internal/zzj
    //   8: dup
    //   9: aload_0
    //   10: getfield 166	com/google/android/gms/common/internal/zzf:zzaFp	I
    //   13: invokespecial 465	com/google/android/gms/common/internal/zzj:<init>	(I)V
    //   16: aload_0
    //   17: getfield 145	com/google/android/gms/common/internal/zzf:mContext	Landroid/content/Context;
    //   20: invokevirtual 468	android/content/Context:getPackageName	()Ljava/lang/String;
    //   23: invokevirtual 472	com/google/android/gms/common/internal/zzj:zzdm	(Ljava/lang/String;)Lcom/google/android/gms/common/internal/zzj;
    //   26: aload_3
    //   27: invokevirtual 476	com/google/android/gms/common/internal/zzj:zzp	(Landroid/os/Bundle;)Lcom/google/android/gms/common/internal/zzj;
    //   30: astore_3
    //   31: aload_2
    //   32: ifnull +9 -> 41
    //   35: aload_3
    //   36: aload_2
    //   37: invokevirtual 479	com/google/android/gms/common/internal/zzj:zzf	(Ljava/util/Collection;)Lcom/google/android/gms/common/internal/zzj;
    //   40: pop
    //   41: aload_0
    //   42: invokevirtual 482	com/google/android/gms/common/internal/zzf:zzrd	()Z
    //   45: ifeq +67 -> 112
    //   48: aload_3
    //   49: aload_0
    //   50: invokevirtual 485	com/google/android/gms/common/internal/zzf:zzxB	()Landroid/accounts/Account;
    //   53: invokevirtual 488	com/google/android/gms/common/internal/zzj:zzf	(Landroid/accounts/Account;)Lcom/google/android/gms/common/internal/zzj;
    //   56: aload_1
    //   57: invokevirtual 491	com/google/android/gms/common/internal/zzj:zzb	(Lcom/google/android/gms/common/internal/zzr;)Lcom/google/android/gms/common/internal/zzj;
    //   60: pop
    //   61: aload_3
    //   62: aload_0
    //   63: invokevirtual 495	com/google/android/gms/common/internal/zzf:zzxA	()[Lcom/google/android/gms/common/zzc;
    //   66: invokevirtual 498	com/google/android/gms/common/internal/zzj:zza	([Lcom/google/android/gms/common/zzc;)Lcom/google/android/gms/common/internal/zzj;
    //   69: pop
    //   70: aload_0
    //   71: getfield 122	com/google/android/gms/common/internal/zzf:zzaFg	Ljava/lang/Object;
    //   74: astore_1
    //   75: aload_1
    //   76: monitorenter
    //   77: aload_0
    //   78: getfield 175	com/google/android/gms/common/internal/zzf:zzaFh	Lcom/google/android/gms/common/internal/zzv;
    //   81: ifnull +50 -> 131
    //   84: aload_0
    //   85: getfield 175	com/google/android/gms/common/internal/zzf:zzaFh	Lcom/google/android/gms/common/internal/zzv;
    //   88: new 25	com/google/android/gms/common/internal/zzf$zzg
    //   91: dup
    //   92: aload_0
    //   93: aload_0
    //   94: getfield 136	com/google/android/gms/common/internal/zzf:zzaFr	Ljava/util/concurrent/atomic/AtomicInteger;
    //   97: invokevirtual 256	java/util/concurrent/atomic/AtomicInteger:get	()I
    //   100: invokespecial 499	com/google/android/gms/common/internal/zzf$zzg:<init>	(Lcom/google/android/gms/common/internal/zzf;I)V
    //   103: aload_3
    //   104: invokeinterface 502 3 0
    //   109: aload_1
    //   110: monitorexit
    //   111: return
    //   112: aload_0
    //   113: invokevirtual 505	com/google/android/gms/common/internal/zzf:zzxE	()Z
    //   116: ifeq -55 -> 61
    //   119: aload_3
    //   120: aload_0
    //   121: invokevirtual 507	com/google/android/gms/common/internal/zzf:getAccount	()Landroid/accounts/Account;
    //   124: invokevirtual 488	com/google/android/gms/common/internal/zzj:zzf	(Landroid/accounts/Account;)Lcom/google/android/gms/common/internal/zzj;
    //   127: pop
    //   128: goto -67 -> 61
    //   131: ldc -36
    //   133: ldc_w 509
    //   136: invokestatic 512	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   139: pop
    //   140: goto -31 -> 109
    //   143: astore_2
    //   144: aload_1
    //   145: monitorexit
    //   146: aload_2
    //   147: athrow
    //   148: astore_1
    //   149: ldc -36
    //   151: ldc_w 514
    //   154: aload_1
    //   155: invokestatic 517	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   158: pop
    //   159: aload_0
    //   160: iconst_1
    //   161: invokevirtual 520	com/google/android/gms/common/internal/zzf:zzcS	(I)V
    //   164: return
    //   165: astore_1
    //   166: aload_1
    //   167: athrow
    //   168: astore_1
    //   169: ldc -36
    //   171: ldc_w 514
    //   174: aload_1
    //   175: invokestatic 517	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   178: pop
    //   179: aload_0
    //   180: bipush 8
    //   182: aconst_null
    //   183: aconst_null
    //   184: aload_0
    //   185: getfield 136	com/google/android/gms/common/internal/zzf:zzaFr	Ljava/util/concurrent/atomic/AtomicInteger;
    //   188: invokevirtual 256	java/util/concurrent/atomic/AtomicInteger:get	()I
    //   191: invokevirtual 522	com/google/android/gms/common/internal/zzf:zza	(ILandroid/os/IBinder;Landroid/os/Bundle;I)V
    //   194: return
    //   195: astore_1
    //   196: goto -27 -> 169
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	199	0	this	zzf
    //   0	199	2	paramSet	Set<Scope>
    //   4	116	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   77	109	143	finally
    //   109	111	143	finally
    //   131	140	143	finally
    //   144	146	143	finally
    //   70	77	148	android/os/DeadObjectException
    //   146	148	148	android/os/DeadObjectException
    //   70	77	165	java/lang/SecurityException
    //   146	148	165	java/lang/SecurityException
    //   70	77	168	android/os/RemoteException
    //   146	148	168	android/os/RemoteException
    //   70	77	195	java/lang/RuntimeException
    //   146	148	195	java/lang/RuntimeException
  }
  
  public void zzcS(int paramInt)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(4, this.zzaFr.get(), paramInt));
  }
  
  @NonNull
  protected abstract String zzeA();
  
  @NonNull
  protected abstract String zzez();
  
  @Nullable
  protected abstract T zzh(IBinder paramIBinder);
  
  protected Bundle zzqL()
  {
    return new Bundle();
  }
  
  public boolean zzrd()
  {
    return false;
  }
  
  public boolean zzrr()
  {
    return false;
  }
  
  public Intent zzrs()
  {
    throw new UnsupportedOperationException("Not a sign in API");
  }
  
  public Bundle zzuB()
  {
    return null;
  }
  
  public boolean zzvh()
  {
    return true;
  }
  
  @Nullable
  public IBinder zzvi()
  {
    synchronized (this.zzaFg)
    {
      if (this.zzaFh == null) {
        return null;
      }
      IBinder localIBinder = this.zzaFh.asBinder();
      return localIBinder;
    }
  }
  
  public zzc[] zzxA()
  {
    return new zzc[0];
  }
  
  public final Account zzxB()
  {
    if (getAccount() != null) {
      return getAccount();
    }
    return new Account("<<default account>>", "com.google");
  }
  
  protected final void zzxC()
  {
    if (!isConnected()) {
      throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
    }
  }
  
  public final T zzxD()
    throws DeadObjectException
  {
    synchronized (this.zzrJ)
    {
      if (this.zzaFm == 4) {
        throw new DeadObjectException();
      }
    }
    zzxC();
    if (this.zzaFj != null) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zza(bool, "Client is connected but service is null");
      IInterface localIInterface = this.zzaFj;
      return localIInterface;
    }
  }
  
  public boolean zzxE()
  {
    return false;
  }
  
  protected Set<Scope> zzxF()
  {
    return Collections.EMPTY_SET;
  }
  
  protected String zzxv()
  {
    return "com.google.android.gms";
  }
  
  @Nullable
  protected final String zzxw()
  {
    if (this.zzaFq == null) {
      return this.mContext.getClass().getName();
    }
    return this.zzaFq;
  }
  
  public void zzxz()
  {
    int i = this.zzaAQ.isGooglePlayServicesAvailable(this.mContext);
    if (i != 0)
    {
      zza(1, null);
      zza(new zzi(), i, null);
      return;
    }
    zza(new zzi());
  }
  
  private abstract class zza
    extends zzf.zze<Boolean>
  {
    public final int statusCode;
    public final Bundle zzaFt;
    
    @BinderThread
    protected zza(int paramInt, Bundle paramBundle)
    {
      super(Boolean.valueOf(true));
      this.statusCode = paramInt;
      this.zzaFt = paramBundle;
    }
    
    protected void zzb(Boolean paramBoolean)
    {
      Object localObject = null;
      if (paramBoolean == null) {
        zzf.zza(zzf.this, 1, null);
      }
      do
      {
        return;
        switch (this.statusCode)
        {
        default: 
          zzf.zza(zzf.this, 1, null);
          paramBoolean = (Boolean)localObject;
          if (this.zzaFt != null) {
            paramBoolean = (PendingIntent)this.zzaFt.getParcelable("pendingIntent");
          }
          zzm(new ConnectionResult(this.statusCode, paramBoolean));
          return;
        }
      } while (zzxG());
      zzf.zza(zzf.this, 1, null);
      zzm(new ConnectionResult(8, null));
      return;
      zzf.zza(zzf.this, 1, null);
      throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
    }
    
    protected abstract void zzm(ConnectionResult paramConnectionResult);
    
    protected abstract boolean zzxG();
  }
  
  public static abstract interface zzb
  {
    public abstract void onConnected(@Nullable Bundle paramBundle);
    
    public abstract void onConnectionSuspended(int paramInt);
  }
  
  public static abstract interface zzc
  {
    public abstract void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult);
  }
  
  final class zzd
    extends Handler
  {
    public zzd(Looper paramLooper)
    {
      super();
    }
    
    private void zza(Message paramMessage)
    {
      ((zzf.zze)paramMessage.obj).unregister();
    }
    
    private boolean zzb(Message paramMessage)
    {
      return (paramMessage.what == 2) || (paramMessage.what == 1) || (paramMessage.what == 5);
    }
    
    public void handleMessage(Message paramMessage)
    {
      PendingIntent localPendingIntent = null;
      if (zzf.this.zzaFr.get() != paramMessage.arg1)
      {
        if (zzb(paramMessage)) {
          zza(paramMessage);
        }
        return;
      }
      if (((paramMessage.what == 1) || (paramMessage.what == 5)) && (!zzf.this.isConnecting()))
      {
        zza(paramMessage);
        return;
      }
      if (paramMessage.what == 3)
      {
        if ((paramMessage.obj instanceof PendingIntent)) {
          localPendingIntent = (PendingIntent)paramMessage.obj;
        }
        paramMessage = new ConnectionResult(paramMessage.arg2, localPendingIntent);
        zzf.this.zzaFi.zzg(paramMessage);
        zzf.this.onConnectionFailed(paramMessage);
        return;
      }
      if (paramMessage.what == 4)
      {
        zzf.zza(zzf.this, 4, null);
        if (zzf.zzb(zzf.this) != null) {
          zzf.zzb(zzf.this).onConnectionSuspended(paramMessage.arg2);
        }
        zzf.this.onConnectionSuspended(paramMessage.arg2);
        zzf.zza(zzf.this, 4, 1, null);
        return;
      }
      if ((paramMessage.what == 2) && (!zzf.this.isConnected()))
      {
        zza(paramMessage);
        return;
      }
      if (zzb(paramMessage))
      {
        ((zzf.zze)paramMessage.obj).zzxH();
        return;
      }
      int i = paramMessage.what;
      Log.wtf("GmsClient", 45 + "Don't know how to handle message: " + i, new Exception());
    }
  }
  
  protected abstract class zze<TListener>
  {
    private TListener mListener;
    private boolean zzaFv;
    
    public zze()
    {
      Object localObject;
      this.mListener = localObject;
      this.zzaFv = false;
    }
    
    public void unregister()
    {
      zzxI();
      synchronized (zzf.zzc(zzf.this))
      {
        zzf.zzc(zzf.this).remove(this);
        return;
      }
    }
    
    protected abstract void zzu(TListener paramTListener);
    
    /* Error */
    public void zzxH()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 24	com/google/android/gms/common/internal/zzf$zze:mListener	Ljava/lang/Object;
      //   6: astore_1
      //   7: aload_0
      //   8: getfield 26	com/google/android/gms/common/internal/zzf$zze:zzaFv	Z
      //   11: ifeq +48 -> 59
      //   14: aload_0
      //   15: invokestatic 54	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
      //   18: astore_2
      //   19: ldc 56
      //   21: new 58	java/lang/StringBuilder
      //   24: dup
      //   25: aload_2
      //   26: invokestatic 54	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
      //   29: invokevirtual 62	java/lang/String:length	()I
      //   32: bipush 47
      //   34: iadd
      //   35: invokespecial 65	java/lang/StringBuilder:<init>	(I)V
      //   38: ldc 67
      //   40: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   43: aload_2
      //   44: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   47: ldc 73
      //   49: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   52: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   55: invokestatic 83	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   58: pop
      //   59: aload_0
      //   60: monitorexit
      //   61: aload_1
      //   62: ifnull +8 -> 70
      //   65: aload_0
      //   66: aload_1
      //   67: invokevirtual 85	com/google/android/gms/common/internal/zzf$zze:zzu	(Ljava/lang/Object;)V
      //   70: aload_0
      //   71: monitorenter
      //   72: aload_0
      //   73: iconst_1
      //   74: putfield 26	com/google/android/gms/common/internal/zzf$zze:zzaFv	Z
      //   77: aload_0
      //   78: monitorexit
      //   79: aload_0
      //   80: invokevirtual 87	com/google/android/gms/common/internal/zzf$zze:unregister	()V
      //   83: return
      //   84: astore_1
      //   85: aload_0
      //   86: monitorexit
      //   87: aload_1
      //   88: athrow
      //   89: astore_1
      //   90: aload_1
      //   91: athrow
      //   92: astore_1
      //   93: aload_0
      //   94: monitorexit
      //   95: aload_1
      //   96: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	97	0	this	zze
      //   6	61	1	localObject1	Object
      //   84	4	1	localObject2	Object
      //   89	2	1	localRuntimeException	RuntimeException
      //   92	4	1	localObject3	Object
      //   18	26	2	str	String
      // Exception table:
      //   from	to	target	type
      //   2	59	84	finally
      //   59	61	84	finally
      //   85	87	84	finally
      //   65	70	89	java/lang/RuntimeException
      //   72	79	92	finally
      //   93	95	92	finally
    }
    
    public void zzxI()
    {
      try
      {
        this.mListener = null;
        return;
      }
      finally {}
    }
  }
  
  public static abstract interface zzf
  {
    public abstract void zzg(@NonNull ConnectionResult paramConnectionResult);
  }
  
  public static final class zzg
    extends zzu.zza
  {
    private zzf zzaFw;
    private final int zzaFx;
    
    public zzg(@NonNull zzf paramzzf, int paramInt)
    {
      this.zzaFw = paramzzf;
      this.zzaFx = paramInt;
    }
    
    private void zzxJ()
    {
      this.zzaFw = null;
    }
    
    @BinderThread
    public void zza(int paramInt, @NonNull IBinder paramIBinder, @Nullable Bundle paramBundle)
    {
      zzac.zzb(this.zzaFw, "onPostInitComplete can be called only once per call to getRemoteService");
      this.zzaFw.zza(paramInt, paramIBinder, paramBundle, this.zzaFx);
      zzxJ();
    }
    
    @BinderThread
    public void zzb(int paramInt, @Nullable Bundle paramBundle)
    {
      Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
    }
  }
  
  public final class zzh
    implements ServiceConnection
  {
    private final int zzaFx;
    
    public zzh(int paramInt)
    {
      this.zzaFx = paramInt;
    }
    
    public void onServiceConnected(ComponentName arg1, IBinder paramIBinder)
    {
      if (paramIBinder == null)
      {
        zzf.this.zza(8, null, this.zzaFx);
        return;
      }
      synchronized (zzf.zza(zzf.this))
      {
        zzf.zza(zzf.this, zzv.zza.zzbu(paramIBinder));
        zzf.this.zza(0, null, this.zzaFx);
        return;
      }
    }
    
    public void onServiceDisconnected(ComponentName arg1)
    {
      synchronized (zzf.zza(zzf.this))
      {
        zzf.zza(zzf.this, null);
        zzf.this.mHandler.sendMessage(zzf.this.mHandler.obtainMessage(4, this.zzaFx, 1));
        return;
      }
    }
  }
  
  protected class zzi
    implements zzf.zzf
  {
    public zzi() {}
    
    public void zzg(@NonNull ConnectionResult paramConnectionResult)
    {
      if (paramConnectionResult.isSuccess()) {
        zzf.this.zza(null, zzf.this.zzxF());
      }
      while (zzf.zzd(zzf.this) == null) {
        return;
      }
      zzf.zzd(zzf.this).onConnectionFailed(paramConnectionResult);
    }
  }
  
  protected final class zzj
    extends zzf.zza
  {
    public final IBinder zzaFy;
    
    @BinderThread
    public zzj(int paramInt, IBinder paramIBinder, Bundle paramBundle)
    {
      super(paramInt, paramBundle);
      this.zzaFy = paramIBinder;
    }
    
    protected void zzm(ConnectionResult paramConnectionResult)
    {
      if (zzf.zzd(zzf.this) != null) {
        zzf.zzd(zzf.this).onConnectionFailed(paramConnectionResult);
      }
      zzf.this.onConnectionFailed(paramConnectionResult);
    }
    
    protected boolean zzxG()
    {
      do
      {
        try
        {
          String str1 = this.zzaFy.getInterfaceDescriptor();
          if (!zzf.this.zzeA().equals(str1))
          {
            String str2 = String.valueOf(zzf.this.zzeA());
            Log.e("GmsClient", String.valueOf(str2).length() + 34 + String.valueOf(str1).length() + "service descriptor mismatch: " + str2 + " vs. " + str1);
            return false;
          }
        }
        catch (RemoteException localRemoteException)
        {
          Log.w("GmsClient", "service probably died");
          return false;
        }
        localObject = zzf.this.zzh(this.zzaFy);
      } while ((localObject == null) || (!zzf.zza(zzf.this, 2, 3, (IInterface)localObject)));
      Object localObject = zzf.this.zzuB();
      if (zzf.zzb(zzf.this) != null) {
        zzf.zzb(zzf.this).onConnected((Bundle)localObject);
      }
      return true;
    }
  }
  
  protected final class zzk
    extends zzf.zza
  {
    @BinderThread
    public zzk(int paramInt, @Nullable Bundle paramBundle)
    {
      super(paramInt, paramBundle);
    }
    
    protected void zzm(ConnectionResult paramConnectionResult)
    {
      zzf.this.zzaFi.zzg(paramConnectionResult);
      zzf.this.onConnectionFailed(paramConnectionResult);
    }
    
    protected boolean zzxG()
    {
      zzf.this.zzaFi.zzg(ConnectionResult.zzayj);
      return true;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */