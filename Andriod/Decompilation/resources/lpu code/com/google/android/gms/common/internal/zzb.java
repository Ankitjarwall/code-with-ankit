package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.common.api.zzd;

public class zzb
{
  @NonNull
  public static zza zzG(@NonNull Status paramStatus)
  {
    if (paramStatus.hasResolution()) {
      return new zzd(paramStatus);
    }
    return new zza(paramStatus);
  }
  
  @NonNull
  public static zza zzl(@NonNull ConnectionResult paramConnectionResult)
  {
    return zzG(new Status(paramConnectionResult.getErrorCode(), paramConnectionResult.getErrorMessage(), paramConnectionResult.getResolution()));
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */