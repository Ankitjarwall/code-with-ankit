package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface zzaf
{
  public static final class zza
    extends zzbyd<zza>
  {
    public String stackTrace = null;
    public String zzaS = null;
    public Long zzaT = null;
    public String zzaU = null;
    public String zzaV = null;
    public Long zzaW = null;
    public Long zzaX = null;
    public String zzaY = null;
    public Long zzaZ = null;
    public String zzba = null;
    
    public zza()
    {
      this.zzcwL = -1;
    }
    
    public void zza(zzbyc paramzzbyc)
      throws IOException
    {
      if (this.zzaS != null) {
        paramzzbyc.zzq(1, this.zzaS);
      }
      if (this.zzaT != null) {
        paramzzbyc.zzb(2, this.zzaT.longValue());
      }
      if (this.stackTrace != null) {
        paramzzbyc.zzq(3, this.stackTrace);
      }
      if (this.zzaU != null) {
        paramzzbyc.zzq(4, this.zzaU);
      }
      if (this.zzaV != null) {
        paramzzbyc.zzq(5, this.zzaV);
      }
      if (this.zzaW != null) {
        paramzzbyc.zzb(6, this.zzaW.longValue());
      }
      if (this.zzaX != null) {
        paramzzbyc.zzb(7, this.zzaX.longValue());
      }
      if (this.zzaY != null) {
        paramzzbyc.zzq(8, this.zzaY);
      }
      if (this.zzaZ != null) {
        paramzzbyc.zzb(9, this.zzaZ.longValue());
      }
      if (this.zzba != null) {
        paramzzbyc.zzq(10, this.zzba);
      }
      super.zza(paramzzbyc);
    }
    
    public zza zze(zzbyb paramzzbyb)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzbyb.zzaeW();
        switch (i)
        {
        default: 
          if (super.zza(paramzzbyb, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          this.zzaS = paramzzbyb.readString();
          break;
        case 16: 
          this.zzaT = Long.valueOf(paramzzbyb.zzaeZ());
          break;
        case 26: 
          this.stackTrace = paramzzbyb.readString();
          break;
        case 34: 
          this.zzaU = paramzzbyb.readString();
          break;
        case 42: 
          this.zzaV = paramzzbyb.readString();
          break;
        case 48: 
          this.zzaW = Long.valueOf(paramzzbyb.zzaeZ());
          break;
        case 56: 
          this.zzaX = Long.valueOf(paramzzbyb.zzaeZ());
          break;
        case 66: 
          this.zzaY = paramzzbyb.readString();
          break;
        case 72: 
          this.zzaZ = Long.valueOf(paramzzbyb.zzaeZ());
          break;
        case 82: 
          this.zzba = paramzzbyb.readString();
        }
      }
    }
    
    protected int zzu()
    {
      int j = super.zzu();
      int i = j;
      if (this.zzaS != null) {
        i = j + zzbyc.zzr(1, this.zzaS);
      }
      j = i;
      if (this.zzaT != null) {
        j = i + zzbyc.zzf(2, this.zzaT.longValue());
      }
      i = j;
      if (this.stackTrace != null) {
        i = j + zzbyc.zzr(3, this.stackTrace);
      }
      j = i;
      if (this.zzaU != null) {
        j = i + zzbyc.zzr(4, this.zzaU);
      }
      i = j;
      if (this.zzaV != null) {
        i = j + zzbyc.zzr(5, this.zzaV);
      }
      j = i;
      if (this.zzaW != null) {
        j = i + zzbyc.zzf(6, this.zzaW.longValue());
      }
      i = j;
      if (this.zzaX != null) {
        i = j + zzbyc.zzf(7, this.zzaX.longValue());
      }
      j = i;
      if (this.zzaY != null) {
        j = i + zzbyc.zzr(8, this.zzaY);
      }
      i = j;
      if (this.zzaZ != null) {
        i = j + zzbyc.zzf(9, this.zzaZ.longValue());
      }
      j = i;
      if (this.zzba != null) {
        j = i + zzbyc.zzr(10, this.zzba);
      }
      return j;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzaf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */