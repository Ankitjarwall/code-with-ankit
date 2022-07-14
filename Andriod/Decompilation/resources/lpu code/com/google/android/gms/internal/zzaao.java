package com.google.android.gms.internal;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public abstract class zzaao
  implements Releasable, Result
{
  protected final DataHolder zzaBi;
  protected final Status zzair;
  
  protected zzaao(DataHolder paramDataHolder, Status paramStatus)
  {
    this.zzair = paramStatus;
    this.zzaBi = paramDataHolder;
  }
  
  public Status getStatus()
  {
    return this.zzair;
  }
  
  public void release()
  {
    if (this.zzaBi != null) {
      this.zzaBi.close();
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzaao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */