package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;

public abstract class zzaan<L>
  implements zzabh.zzc<L>
{
  private final DataHolder zzaBi;
  
  protected zzaan(DataHolder paramDataHolder)
  {
    this.zzaBi = paramDataHolder;
  }
  
  protected abstract void zza(L paramL, DataHolder paramDataHolder);
  
  public final void zzs(L paramL)
  {
    zza(paramL, this.zzaBi);
  }
  
  public void zzwc()
  {
    if (this.zzaBi != null) {
      this.zzaBi.close();
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzaan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */