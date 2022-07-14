package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.zzg;

public class zza
  extends zzr.zza
{
  int zzaEV;
  
  public static Account zza(zzr paramzzr)
  {
    Account localAccount = null;
    long l;
    if (paramzzr != null) {
      l = Binder.clearCallingIdentity();
    }
    try
    {
      localAccount = paramzzr.getAccount();
      return localAccount;
    }
    catch (RemoteException paramzzr)
    {
      Log.w("AccountAccessor", "Remote account accessor probably died");
      return null;
    }
    finally
    {
      Binder.restoreCallingIdentity(l);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zza)) {
      return false;
    }
    throw new NullPointerException();
  }
  
  public Account getAccount()
  {
    int i = Binder.getCallingUid();
    if (i == this.zzaEV) {
      return null;
    }
    if (zzg.zzf(null, i))
    {
      this.zzaEV = i;
      return null;
    }
    throw new SecurityException("Caller is not GooglePlayServices");
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */