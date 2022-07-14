package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import java.io.IOException;
import java.security.KeyPair;
import java.util.Map;

public class zzd
{
  static Map<String, zzd> zzbhH = new ArrayMap();
  static String zzbhN;
  private static zzh zzclv;
  private static zzf zzclw;
  Context mContext;
  KeyPair zzbhK;
  String zzbhL = "";
  
  protected zzd(Context paramContext, String paramString, Bundle paramBundle)
  {
    this.mContext = paramContext.getApplicationContext();
    this.zzbhL = paramString;
  }
  
  public static zzd zzb(Context paramContext, Bundle paramBundle)
  {
    String str;
    if (paramBundle == null) {
      str = "";
    }
    for (;;)
    {
      try
      {
        Context localContext = paramContext.getApplicationContext();
        if (zzclv == null)
        {
          zzclv = new zzh(localContext);
          zzclw = new zzf(localContext);
        }
        zzbhN = Integer.toString(FirebaseInstanceId.zzcr(localContext));
        zzd localzzd = (zzd)zzbhH.get(str);
        paramContext = localzzd;
        if (localzzd == null)
        {
          paramContext = new zzd(localContext, str, paramBundle);
          zzbhH.put(str, paramContext);
        }
        return paramContext;
      }
      finally {}
      str = paramBundle.getString("subtype");
      while (str != null) {
        break;
      }
      str = "";
    }
  }
  
  public long getCreationTime()
  {
    return zzclv.zzjy(this.zzbhL);
  }
  
  public String getToken(String paramString1, String paramString2, Bundle paramBundle)
    throws IOException
  {
    if (Looper.getMainLooper() == Looper.myLooper()) {
      throw new IOException("MAIN_THREAD");
    }
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    int j = 1;
    int i;
    if ((localBundle.getString("ttl") != null) || ("jwt".equals(localBundle.getString("type")))) {
      i = 0;
    }
    do
    {
      do
      {
        paramBundle = zzc(paramString1, paramString2, localBundle);
        if ((paramBundle != null) && (i != 0)) {
          zzclv.zza(this.zzbhL, paramString1, paramString2, paramBundle, zzbhN);
        }
        return paramBundle;
        paramBundle = zzclv.zzu(this.zzbhL, paramString1, paramString2);
        i = j;
      } while (paramBundle == null);
      i = j;
    } while (paramBundle.zzjB(zzbhN));
    return paramBundle.zzbxW;
  }
  
  KeyPair zzHh()
  {
    if (this.zzbhK == null) {
      this.zzbhK = zzclv.zzeI(this.zzbhL);
    }
    if (this.zzbhK == null) {
      this.zzbhK = zzclv.zzjz(this.zzbhL);
    }
    return this.zzbhK;
  }
  
  public void zzHi()
  {
    zzclv.zzeJ(this.zzbhL);
    this.zzbhK = null;
  }
  
  public zzh zzabS()
  {
    return zzclv;
  }
  
  public zzf zzabT()
  {
    return zzclw;
  }
  
  public void zzb(String paramString1, String paramString2, Bundle paramBundle)
    throws IOException
  {
    if (Looper.getMainLooper() == Looper.myLooper()) {
      throw new IOException("MAIN_THREAD");
    }
    zzclv.zzi(this.zzbhL, paramString1, paramString2);
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    localBundle.putString("delete", "1");
    zzc(paramString1, paramString2, localBundle);
  }
  
  public String zzc(String paramString1, String paramString2, Bundle paramBundle)
    throws IOException
  {
    if (paramString2 != null) {
      paramBundle.putString("scope", paramString2);
    }
    paramBundle.putString("sender", paramString1);
    if ("".equals(this.zzbhL)) {}
    for (;;)
    {
      paramBundle.putString("subtype", paramString1);
      paramBundle.putString("X-subtype", paramString1);
      paramString1 = zzclw.zza(paramBundle, zzHh());
      return zzclw.zzq(paramString1);
      paramString1 = this.zzbhL;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\iid\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */