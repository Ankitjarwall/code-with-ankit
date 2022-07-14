package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzbar
  extends zza
{
  public static final Parcelable.Creator<zzbar> CREATOR = new zzbas();
  private final Account zzahh;
  final int zzaiI;
  private final String zzajw;
  private final Scope[] zzbEu;
  
  zzbar(int paramInt, Account paramAccount, Scope[] paramArrayOfScope, String paramString)
  {
    this.zzaiI = paramInt;
    this.zzahh = paramAccount;
    this.zzbEu = paramArrayOfScope;
    this.zzajw = paramString;
  }
  
  public Account getAccount()
  {
    return this.zzahh;
  }
  
  public String getServerClientId()
  {
    return this.zzajw;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzbas.zza(this, paramParcel, paramInt);
  }
  
  public Scope[] zzPT()
  {
    return this.zzbEu;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzbar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */