package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public class zza
  extends Exception
{
  protected final Status zzair;
  
  public zza(@NonNull Status paramStatus)
  {
    super(paramStatus.getStatusMessage());
    this.zzair = paramStatus;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\api\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */