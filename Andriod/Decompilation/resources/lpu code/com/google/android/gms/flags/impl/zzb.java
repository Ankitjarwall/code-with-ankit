package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.internal.zzaqf;
import java.util.concurrent.Callable;

public class zzb
{
  private static SharedPreferences zzaXu = null;
  
  public static SharedPreferences zzn(Context paramContext)
  {
    try
    {
      if (zzaXu == null) {
        zzaXu = (SharedPreferences)zzaqf.zzb(new Callable()
        {
          public SharedPreferences zzDI()
          {
            return zzb.this.getSharedPreferences("google_sdk_flags", 1);
          }
        });
      }
      paramContext = zzaXu;
      return paramContext;
    }
    finally {}
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\flags\impl\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */