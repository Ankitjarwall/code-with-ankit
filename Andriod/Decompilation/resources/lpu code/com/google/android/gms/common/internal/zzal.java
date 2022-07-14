package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.api.Api.zzg;

public class zzal<T extends IInterface>
  extends zzl<T>
{
  private final Api.zzg<T> zzaGJ;
  
  protected String zzeA()
  {
    return this.zzaGJ.zzeA();
  }
  
  protected String zzez()
  {
    return this.zzaGJ.zzez();
  }
  
  protected T zzh(IBinder paramIBinder)
  {
    return this.zzaGJ.zzh(paramIBinder);
  }
  
  public Api.zzg<T> zzyn()
  {
    return this.zzaGJ;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\zzal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */