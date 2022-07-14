package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface zzae
{
  public static final class zza
    extends zzbyd<zza>
  {
    public zzae.zzb zzaK;
    public zzae.zzc zzaL;
    
    public zza()
    {
      this.zzcwL = -1;
    }
    
    public static zza zzc(byte[] paramArrayOfByte)
      throws zzbyi
    {
      return (zza)zzbyj.zza(new zza(), paramArrayOfByte);
    }
    
    public zza zza(zzbyb paramzzbyb)
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
          if (this.zzaK == null) {
            this.zzaK = new zzae.zzb();
          }
          paramzzbyb.zza(this.zzaK);
          break;
        case 18: 
          if (this.zzaL == null) {
            this.zzaL = new zzae.zzc();
          }
          paramzzbyb.zza(this.zzaL);
        }
      }
    }
    
    public void zza(zzbyc paramzzbyc)
      throws IOException
    {
      if (this.zzaK != null) {
        paramzzbyc.zza(1, this.zzaK);
      }
      if (this.zzaL != null) {
        paramzzbyc.zza(2, this.zzaL);
      }
      super.zza(paramzzbyc);
    }
    
    protected int zzu()
    {
      int j = super.zzu();
      int i = j;
      if (this.zzaK != null) {
        i = j + zzbyc.zzc(1, this.zzaK);
      }
      j = i;
      if (this.zzaL != null) {
        j = i + zzbyc.zzc(2, this.zzaL);
      }
      return j;
    }
  }
  
  public static final class zzb
    extends zzbyd<zzb>
  {
    public Integer zzaM = null;
    
    public zzb()
    {
      this.zzcwL = -1;
    }
    
    public void zza(zzbyc paramzzbyc)
      throws IOException
    {
      if (this.zzaM != null) {
        paramzzbyc.zzJ(27, this.zzaM.intValue());
      }
      super.zza(paramzzbyc);
    }
    
    public zzb zzc(zzbyb paramzzbyb)
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
        case 216: 
          i = paramzzbyb.zzafa();
          switch (i)
          {
          default: 
            break;
          case 0: 
          case 1: 
          case 2: 
          case 3: 
          case 4: 
            this.zzaM = Integer.valueOf(i);
          }
          break;
        }
      }
    }
    
    protected int zzu()
    {
      int j = super.zzu();
      int i = j;
      if (this.zzaM != null) {
        i = j + zzbyc.zzL(27, this.zzaM.intValue());
      }
      return i;
    }
  }
  
  public static final class zzc
    extends zzbyd<zzc>
  {
    public String zzaN = null;
    public String zzaO = null;
    public String zzaP = null;
    public String zzaQ = null;
    public String zzaR = null;
    
    public zzc()
    {
      this.zzcwL = -1;
    }
    
    public void zza(zzbyc paramzzbyc)
      throws IOException
    {
      if (this.zzaN != null) {
        paramzzbyc.zzq(1, this.zzaN);
      }
      if (this.zzaO != null) {
        paramzzbyc.zzq(2, this.zzaO);
      }
      if (this.zzaP != null) {
        paramzzbyc.zzq(3, this.zzaP);
      }
      if (this.zzaQ != null) {
        paramzzbyc.zzq(4, this.zzaQ);
      }
      if (this.zzaR != null) {
        paramzzbyc.zzq(5, this.zzaR);
      }
      super.zza(paramzzbyc);
    }
    
    public zzc zzd(zzbyb paramzzbyb)
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
          this.zzaN = paramzzbyb.readString();
          break;
        case 18: 
          this.zzaO = paramzzbyb.readString();
          break;
        case 26: 
          this.zzaP = paramzzbyb.readString();
          break;
        case 34: 
          this.zzaQ = paramzzbyb.readString();
          break;
        case 42: 
          this.zzaR = paramzzbyb.readString();
        }
      }
    }
    
    protected int zzu()
    {
      int j = super.zzu();
      int i = j;
      if (this.zzaN != null) {
        i = j + zzbyc.zzr(1, this.zzaN);
      }
      j = i;
      if (this.zzaO != null) {
        j = i + zzbyc.zzr(2, this.zzaO);
      }
      i = j;
      if (this.zzaP != null) {
        i = j + zzbyc.zzr(3, this.zzaP);
      }
      j = i;
      if (this.zzaQ != null) {
        j = i + zzbyc.zzr(4, this.zzaQ);
      }
      i = j;
      if (this.zzaR != null) {
        i = j + zzbyc.zzr(5, this.zzaR);
      }
      return i;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzae.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */