package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.zzg;

public abstract class zzf<T>
{
  private final String zzaRL;
  private T zzaRM;
  
  protected zzf(String paramString)
  {
    this.zzaRL = paramString;
  }
  
  protected final T zzbl(Context paramContext)
    throws zzf.zza
  {
    if (this.zzaRM == null)
    {
      zzac.zzw(paramContext);
      paramContext = zzg.getRemoteContext(paramContext);
      if (paramContext == null) {
        throw new zza("Could not get remote context.");
      }
      paramContext = paramContext.getClassLoader();
    }
    try
    {
      this.zzaRM = zzc((IBinder)paramContext.loadClass(this.zzaRL).newInstance());
      return (T)this.zzaRM;
    }
    catch (ClassNotFoundException paramContext)
    {
      throw new zza("Could not load creator class.", paramContext);
    }
    catch (InstantiationException paramContext)
    {
      throw new zza("Could not instantiate creator.", paramContext);
    }
    catch (IllegalAccessException paramContext)
    {
      throw new zza("Could not access creator.", paramContext);
    }
  }
  
  protected abstract T zzc(IBinder paramIBinder);
  
  public static class zza
    extends Exception
  {
    public zza(String paramString)
    {
      super();
    }
    
    public zza(String paramString, Throwable paramThrowable)
    {
      super(paramThrowable);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\dynamic\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */