package com.google.android.youtube.player.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import java.util.ArrayList;

public abstract class r<T extends IInterface>
  implements t
{
  final Handler a;
  private final Context b;
  private T c;
  private ArrayList<t.a> d;
  private final ArrayList<t.a> e = new ArrayList();
  private boolean f = false;
  private ArrayList<t.b> g;
  private boolean h = false;
  private final ArrayList<b<?>> i = new ArrayList();
  private ServiceConnection j;
  private boolean k = false;
  
  protected r(Context paramContext, t.a parama, t.b paramb)
  {
    if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
      throw new IllegalStateException("Clients must be created on the UI thread.");
    }
    this.b = ((Context)ab.a(paramContext));
    this.d = new ArrayList();
    this.d.add(ab.a(parama));
    this.g = new ArrayList();
    this.g.add(ab.a(paramb));
    this.a = new a();
  }
  
  private void a()
  {
    if (this.j != null) {}
    try
    {
      this.b.unbindService(this.j);
      this.c = null;
      this.j = null;
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        Log.w("YouTubeClient", "Unexpected error from unbindService()", localIllegalArgumentException);
      }
    }
  }
  
  private static YouTubeInitializationResult b(String paramString)
  {
    try
    {
      paramString = YouTubeInitializationResult.valueOf(paramString);
      return paramString;
    }
    catch (IllegalArgumentException paramString)
    {
      return YouTubeInitializationResult.UNKNOWN_ERROR;
    }
    catch (NullPointerException paramString) {}
    return YouTubeInitializationResult.UNKNOWN_ERROR;
  }
  
  protected abstract T a(IBinder paramIBinder);
  
  protected final void a(YouTubeInitializationResult paramYouTubeInitializationResult)
  {
    this.a.removeMessages(4);
    for (;;)
    {
      int m;
      synchronized (this.g)
      {
        this.h = true;
        ArrayList localArrayList2 = this.g;
        int n = localArrayList2.size();
        m = 0;
        if (m < n)
        {
          if (!this.k) {
            return;
          }
          if (this.g.contains(localArrayList2.get(m))) {
            ((t.b)localArrayList2.get(m)).a(paramYouTubeInitializationResult);
          }
        }
        else
        {
          this.h = false;
          return;
        }
      }
      m += 1;
    }
  }
  
  protected abstract void a(i parami, d paramd)
    throws RemoteException;
  
  protected abstract String b();
  
  protected final void b(IBinder paramIBinder)
  {
    try
    {
      a(i.a.a(paramIBinder), new d());
      return;
    }
    catch (RemoteException paramIBinder)
    {
      Log.w("YouTubeClient", "service died");
    }
  }
  
  protected abstract String c();
  
  public void d()
  {
    h();
    this.k = false;
    synchronized (this.i)
    {
      int n = this.i.size();
      int m = 0;
      while (m < n)
      {
        ((b)this.i.get(m)).b();
        m += 1;
      }
      this.i.clear();
      a();
      return;
    }
  }
  
  public final void e()
  {
    this.k = true;
    Object localObject = YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this.b);
    if (localObject != YouTubeInitializationResult.SUCCESS) {
      this.a.sendMessage(this.a.obtainMessage(3, localObject));
    }
    do
    {
      return;
      localObject = new Intent(c()).setPackage(z.a(this.b));
      if (this.j != null)
      {
        Log.e("YouTubeClient", "Calling connect() while still connected, missing disconnect().");
        a();
      }
      this.j = new e();
    } while (this.b.bindService((Intent)localObject, this.j, 129));
    this.a.sendMessage(this.a.obtainMessage(3, YouTubeInitializationResult.ERROR_CONNECTING_TO_SERVICE));
  }
  
  public final boolean f()
  {
    return this.c != null;
  }
  
  protected final void g()
  {
    boolean bool2 = true;
    for (;;)
    {
      int m;
      synchronized (this.d)
      {
        if (this.f) {
          break label152;
        }
        bool1 = true;
        ab.a(bool1);
        this.a.removeMessages(4);
        this.f = true;
        if (this.e.size() != 0) {
          break label157;
        }
        bool1 = bool2;
        ab.a(bool1);
        ArrayList localArrayList2 = this.d;
        int n = localArrayList2.size();
        m = 0;
        if ((m < n) && (this.k) && (f()))
        {
          if (!this.e.contains(localArrayList2.get(m))) {
            ((t.a)localArrayList2.get(m)).a();
          }
        }
        else
        {
          this.e.clear();
          this.f = false;
          return;
        }
      }
      m += 1;
      continue;
      label152:
      boolean bool1 = false;
      continue;
      label157:
      bool1 = false;
    }
  }
  
  protected final void h()
  {
    this.a.removeMessages(4);
    for (;;)
    {
      int m;
      synchronized (this.d)
      {
        this.f = true;
        ArrayList localArrayList2 = this.d;
        int n = localArrayList2.size();
        m = 0;
        if ((m < n) && (this.k))
        {
          if (this.d.contains(localArrayList2.get(m))) {
            ((t.a)localArrayList2.get(m)).b();
          }
        }
        else
        {
          this.f = false;
          return;
        }
      }
      m += 1;
    }
  }
  
  protected final void i()
  {
    if (!f()) {
      throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
    }
  }
  
  protected final T j()
  {
    i();
    return this.c;
  }
  
  final class a
    extends Handler
  {
    a() {}
    
    public final void handleMessage(Message paramMessage)
    {
      if (paramMessage.what == 3) {
        r.this.a((YouTubeInitializationResult)paramMessage.obj);
      }
      do
      {
        return;
        if (paramMessage.what == 4) {
          synchronized (r.a(r.this))
          {
            if ((r.b(r.this)) && (r.this.f()) && (r.a(r.this).contains(paramMessage.obj))) {
              ((t.a)paramMessage.obj).a();
            }
            return;
          }
        }
      } while (((paramMessage.what == 2) && (!r.this.f())) || ((paramMessage.what != 2) && (paramMessage.what != 1)));
      ((r.b)paramMessage.obj).a();
    }
  }
  
  protected abstract class b<TListener>
  {
    private TListener b;
    
    public b()
    {
      this.b = ???;
      synchronized (r.c(r.this))
      {
        r.c(r.this).add(this);
        return;
      }
    }
    
    public final void a()
    {
      try
      {
        Object localObject1 = this.b;
        a(localObject1);
        return;
      }
      finally {}
    }
    
    protected abstract void a(TListener paramTListener);
    
    public final void b()
    {
      try
      {
        this.b = null;
        return;
      }
      finally {}
    }
  }
  
  protected final class c
    extends r.b<Boolean>
  {
    public final YouTubeInitializationResult b;
    public final IBinder c;
    
    public c(String paramString, IBinder paramIBinder)
    {
      super(Boolean.valueOf(true));
      this.b = r.a(paramString);
      this.c = paramIBinder;
    }
  }
  
  protected final class d
    extends c.a
  {
    protected d() {}
    
    public final void a(String paramString, IBinder paramIBinder)
    {
      r.this.a.sendMessage(r.this.a.obtainMessage(1, new r.c(r.this, paramString, paramIBinder)));
    }
  }
  
  final class e
    implements ServiceConnection
  {
    e() {}
    
    public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      r.this.b(paramIBinder);
    }
    
    public final void onServiceDisconnected(ComponentName paramComponentName)
    {
      r.a(r.this, null);
      r.this.h();
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\youtube\player\internal\r.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */