package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzm
  implements Handler.Callback
{
  private final Handler mHandler;
  private final zza zzaFU;
  private final ArrayList<GoogleApiClient.ConnectionCallbacks> zzaFV = new ArrayList();
  final ArrayList<GoogleApiClient.ConnectionCallbacks> zzaFW = new ArrayList();
  private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zzaFX = new ArrayList();
  private volatile boolean zzaFY = false;
  private final AtomicInteger zzaFZ = new AtomicInteger(0);
  private boolean zzaGa = false;
  private final Object zzrJ = new Object();
  
  public zzm(Looper paramLooper, zza paramzza)
  {
    this.zzaFU = paramzza;
    this.mHandler = new Handler(paramLooper, this);
  }
  
  public boolean handleMessage(Message arg1)
  {
    if (???.what == 1)
    {
      GoogleApiClient.ConnectionCallbacks localConnectionCallbacks = (GoogleApiClient.ConnectionCallbacks)???.obj;
      synchronized (this.zzrJ)
      {
        if ((this.zzaFY) && (this.zzaFU.isConnected()) && (this.zzaFV.contains(localConnectionCallbacks))) {
          localConnectionCallbacks.onConnected(this.zzaFU.zzuB());
        }
        return true;
      }
    }
    int i = ???.what;
    Log.wtf("GmsClientEvents", 45 + "Don't know how to handle message: " + i, new Exception());
    return false;
  }
  
  public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    zzac.zzw(paramConnectionCallbacks);
    synchronized (this.zzrJ)
    {
      boolean bool = this.zzaFV.contains(paramConnectionCallbacks);
      return bool;
    }
  }
  
  public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zzac.zzw(paramOnConnectionFailedListener);
    synchronized (this.zzrJ)
    {
      boolean bool = this.zzaFX.contains(paramOnConnectionFailedListener);
      return bool;
    }
  }
  
  public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    zzac.zzw(paramConnectionCallbacks);
    synchronized (this.zzrJ)
    {
      if (this.zzaFV.contains(paramConnectionCallbacks))
      {
        String str = String.valueOf(paramConnectionCallbacks);
        Log.w("GmsClientEvents", String.valueOf(str).length() + 62 + "registerConnectionCallbacks(): listener " + str + " is already registered");
        if (this.zzaFU.isConnected()) {
          this.mHandler.sendMessage(this.mHandler.obtainMessage(1, paramConnectionCallbacks));
        }
        return;
      }
      this.zzaFV.add(paramConnectionCallbacks);
    }
  }
  
  public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zzac.zzw(paramOnConnectionFailedListener);
    synchronized (this.zzrJ)
    {
      if (this.zzaFX.contains(paramOnConnectionFailedListener))
      {
        paramOnConnectionFailedListener = String.valueOf(paramOnConnectionFailedListener);
        Log.w("GmsClientEvents", String.valueOf(paramOnConnectionFailedListener).length() + 67 + "registerConnectionFailedListener(): listener " + paramOnConnectionFailedListener + " is already registered");
        return;
      }
      this.zzaFX.add(paramOnConnectionFailedListener);
    }
  }
  
  public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    zzac.zzw(paramConnectionCallbacks);
    synchronized (this.zzrJ)
    {
      if (!this.zzaFV.remove(paramConnectionCallbacks))
      {
        paramConnectionCallbacks = String.valueOf(paramConnectionCallbacks);
        Log.w("GmsClientEvents", String.valueOf(paramConnectionCallbacks).length() + 52 + "unregisterConnectionCallbacks(): listener " + paramConnectionCallbacks + " not found");
      }
      while (!this.zzaGa) {
        return;
      }
      this.zzaFW.add(paramConnectionCallbacks);
    }
  }
  
  public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zzac.zzw(paramOnConnectionFailedListener);
    synchronized (this.zzrJ)
    {
      if (!this.zzaFX.remove(paramOnConnectionFailedListener))
      {
        paramOnConnectionFailedListener = String.valueOf(paramOnConnectionFailedListener);
        Log.w("GmsClientEvents", String.valueOf(paramOnConnectionFailedListener).length() + 57 + "unregisterConnectionFailedListener(): listener " + paramOnConnectionFailedListener + " not found");
      }
      return;
    }
  }
  
  public void zzcV(int paramInt)
  {
    boolean bool = false;
    if (Looper.myLooper() == this.mHandler.getLooper()) {
      bool = true;
    }
    zzac.zza(bool, "onUnintentionalDisconnection must only be called on the Handler thread");
    this.mHandler.removeMessages(1);
    synchronized (this.zzrJ)
    {
      this.zzaGa = true;
      Object localObject2 = new ArrayList(this.zzaFV);
      int i = this.zzaFZ.get();
      localObject2 = ((ArrayList)localObject2).iterator();
      GoogleApiClient.ConnectionCallbacks localConnectionCallbacks;
      do
      {
        if (((Iterator)localObject2).hasNext())
        {
          localConnectionCallbacks = (GoogleApiClient.ConnectionCallbacks)((Iterator)localObject2).next();
          if ((this.zzaFY) && (this.zzaFZ.get() == i)) {}
        }
        else
        {
          this.zzaFW.clear();
          this.zzaGa = false;
          return;
        }
      } while (!this.zzaFV.contains(localConnectionCallbacks));
      localConnectionCallbacks.onConnectionSuspended(paramInt);
    }
  }
  
  public void zzn(ConnectionResult paramConnectionResult)
  {
    if (Looper.myLooper() == this.mHandler.getLooper()) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zza(bool, "onConnectionFailure must only be called on the Handler thread");
      this.mHandler.removeMessages(1);
      synchronized (this.zzrJ)
      {
        Object localObject2 = new ArrayList(this.zzaFX);
        int i = this.zzaFZ.get();
        localObject2 = ((ArrayList)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          GoogleApiClient.OnConnectionFailedListener localOnConnectionFailedListener = (GoogleApiClient.OnConnectionFailedListener)((Iterator)localObject2).next();
          if ((!this.zzaFY) || (this.zzaFZ.get() != i)) {
            return;
          }
          if (this.zzaFX.contains(localOnConnectionFailedListener)) {
            localOnConnectionFailedListener.onConnectionFailed(paramConnectionResult);
          }
        }
      }
      return;
    }
  }
  
  public void zzq(Bundle paramBundle)
  {
    boolean bool2 = true;
    boolean bool1;
    if (Looper.myLooper() == this.mHandler.getLooper())
    {
      bool1 = true;
      zzac.zza(bool1, "onConnectionSuccess must only be called on the Handler thread");
    }
    for (;;)
    {
      synchronized (this.zzrJ)
      {
        if (this.zzaGa) {
          break label206;
        }
        bool1 = true;
        zzac.zzav(bool1);
        this.mHandler.removeMessages(1);
        this.zzaGa = true;
        if (this.zzaFW.size() != 0) {
          break label211;
        }
        bool1 = bool2;
        zzac.zzav(bool1);
        Object localObject2 = new ArrayList(this.zzaFV);
        int i = this.zzaFZ.get();
        localObject2 = ((ArrayList)localObject2).iterator();
        GoogleApiClient.ConnectionCallbacks localConnectionCallbacks;
        if (((Iterator)localObject2).hasNext())
        {
          localConnectionCallbacks = (GoogleApiClient.ConnectionCallbacks)((Iterator)localObject2).next();
          if ((this.zzaFY) && (this.zzaFU.isConnected()) && (this.zzaFZ.get() == i)) {}
        }
        else
        {
          this.zzaFW.clear();
          this.zzaGa = false;
          return;
        }
        if (this.zzaFW.contains(localConnectionCallbacks)) {
          continue;
        }
        localConnectionCallbacks.onConnected(paramBundle);
      }
      bool1 = false;
      break;
      label206:
      bool1 = false;
      continue;
      label211:
      bool1 = false;
    }
  }
  
  public void zzxX()
  {
    this.zzaFY = false;
    this.zzaFZ.incrementAndGet();
  }
  
  public void zzxY()
  {
    this.zzaFY = true;
  }
  
  public static abstract interface zza
  {
    public abstract boolean isConnected();
    
    public abstract Bundle zzuB();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */