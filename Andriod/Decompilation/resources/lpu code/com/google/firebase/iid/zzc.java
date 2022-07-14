package com.google.firebase.iid;

import android.support.annotation.Nullable;

@Deprecated
public class zzc
{
  private final FirebaseInstanceId zzclk;
  
  private zzc(FirebaseInstanceId paramFirebaseInstanceId)
  {
    this.zzclk = paramFirebaseInstanceId;
  }
  
  public static zzc zzabN()
  {
    return new zzc(FirebaseInstanceId.getInstance());
  }
  
  public String getId()
  {
    return this.zzclk.getId();
  }
  
  @Nullable
  public String getToken()
  {
    return this.zzclk.getToken();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\iid\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */