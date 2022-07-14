package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzad
  extends zza
{
  public static final Parcelable.Creator<zzad> CREATOR = new zzae();
  private final int zzaGC;
  private final GoogleSignInAccount zzaGD;
  private final Account zzahh;
  final int zzaiI;
  
  zzad(int paramInt1, Account paramAccount, int paramInt2, GoogleSignInAccount paramGoogleSignInAccount)
  {
    this.zzaiI = paramInt1;
    this.zzahh = paramAccount;
    this.zzaGC = paramInt2;
    this.zzaGD = paramGoogleSignInAccount;
  }
  
  public zzad(Account paramAccount, int paramInt, GoogleSignInAccount paramGoogleSignInAccount)
  {
    this(2, paramAccount, paramInt, paramGoogleSignInAccount);
  }
  
  public Account getAccount()
  {
    return this.zzahh;
  }
  
  public int getSessionId()
  {
    return this.zzaGC;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzae.zza(this, paramParcel, paramInt);
  }
  
  @Nullable
  public GoogleSignInAccount zzyf()
  {
    return this.zzaGD;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\zzad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */