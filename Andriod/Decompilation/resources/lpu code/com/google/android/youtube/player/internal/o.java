package com.google.android.youtube.player.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;

public final class o
  extends r<l>
  implements b
{
  private final String b;
  private final String c;
  private final String d;
  private boolean e;
  
  public o(Context paramContext, String paramString1, String paramString2, String paramString3, t.a parama, t.b paramb)
  {
    super(paramContext, parama, paramb);
    this.b = ((String)ab.a(paramString1));
    this.c = ab.a(paramString2, "callingPackage cannot be null or empty");
    this.d = ab.a(paramString3, "callingAppVersion cannot be null or empty");
  }
  
  private final void k()
  {
    i();
    if (this.e) {
      throw new IllegalStateException("Connection client has been released");
    }
  }
  
  public final IBinder a()
  {
    k();
    try
    {
      IBinder localIBinder = ((l)j()).a();
      return localIBinder;
    }
    catch (RemoteException localRemoteException)
    {
      throw new IllegalStateException(localRemoteException);
    }
  }
  
  public final k a(j paramj)
  {
    k();
    try
    {
      paramj = ((l)j()).a(paramj);
      return paramj;
    }
    catch (RemoteException paramj)
    {
      throw new IllegalStateException(paramj);
    }
  }
  
  protected final void a(i parami, r.d paramd)
    throws RemoteException
  {
    parami.a(paramd, 1202, this.c, this.d, this.b, null);
  }
  
  public final void a(boolean paramBoolean)
  {
    if (f()) {}
    try
    {
      ((l)j()).a(paramBoolean);
      this.e = true;
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;) {}
    }
  }
  
  protected final String b()
  {
    return "com.google.android.youtube.player.internal.IYouTubeService";
  }
  
  protected final String c()
  {
    return "com.google.android.youtube.api.service.START";
  }
  
  public final void d()
  {
    if (!this.e) {
      a(true);
    }
    super.d();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\youtube\player\internal\o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */