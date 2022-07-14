package com.google.android.gms.common;

import android.content.Intent;

public class GooglePlayServicesRepairableException
  extends UserRecoverableException
{
  private final int zzaiH;
  
  GooglePlayServicesRepairableException(int paramInt, String paramString, Intent paramIntent)
  {
    super(paramString, paramIntent);
    this.zzaiH = paramInt;
  }
  
  public int getConnectionStatusCode()
  {
    return this.zzaiH;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\GooglePlayServicesRepairableException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */