package com.google.firebase;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzabs;

public class zza
  implements zzabs
{
  public Exception zzA(Status paramStatus)
  {
    if (paramStatus.getStatusCode() == 8) {
      return new FirebaseException(paramStatus.zzvv());
    }
    return new FirebaseApiNotAvailableException(paramStatus.zzvv());
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */