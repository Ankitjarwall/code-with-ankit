package com.google.firebase.auth;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import com.google.firebase.FirebaseException;

public class FirebaseAuthException
  extends FirebaseException
{
  private final String zzbXS;
  
  public FirebaseAuthException(@NonNull String paramString1, @NonNull String paramString2)
  {
    super(paramString2);
    this.zzbXS = zzac.zzdr(paramString1);
  }
  
  @NonNull
  public String getErrorCode()
  {
    return this.zzbXS;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\auth\FirebaseAuthException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */