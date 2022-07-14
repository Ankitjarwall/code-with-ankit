package com.google.android.gms.common.api;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzzz;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class zzb
  extends Exception
{
  private final ArrayMap<zzzz<?>, ConnectionResult> zzayL;
  
  public zzb(ArrayMap<zzzz<?>, ConnectionResult> paramArrayMap)
  {
    this.zzayL = paramArrayMap;
  }
  
  public String getMessage()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = this.zzayL.keySet().iterator();
    int i = 1;
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject3 = (zzzz)((Iterator)localObject1).next();
      Object localObject2 = (ConnectionResult)this.zzayL.get(localObject3);
      if (((ConnectionResult)localObject2).isSuccess()) {
        i = 0;
      }
      localObject3 = String.valueOf(((zzzz)localObject3).zzvw());
      localObject2 = String.valueOf(localObject2);
      localArrayList.add(String.valueOf(localObject3).length() + 2 + String.valueOf(localObject2).length() + (String)localObject3 + ": " + (String)localObject2);
    }
    localObject1 = new StringBuilder();
    if (i != 0) {
      ((StringBuilder)localObject1).append("None of the queried APIs are available. ");
    }
    for (;;)
    {
      ((StringBuilder)localObject1).append(TextUtils.join("; ", localArrayList));
      return ((StringBuilder)localObject1).toString();
      ((StringBuilder)localObject1).append("Some of the queried APIs are unavailable. ");
    }
  }
  
  public ConnectionResult zza(zzc<? extends Api.ApiOptions> paramzzc)
  {
    paramzzc = paramzzc.getApiKey();
    if (this.zzayL.get(paramzzc) != null) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzb(bool, "The given API was not part of the availability request.");
      return (ConnectionResult)this.zzayL.get(paramzzc);
    }
  }
  
  public ArrayMap<zzzz<?>, ConnectionResult> zzvj()
  {
    return this.zzayL;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\api\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */