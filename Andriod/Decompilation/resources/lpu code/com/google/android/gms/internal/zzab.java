package com.google.android.gms.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class zzab
  extends ByteArrayOutputStream
{
  private final zzv zzaq;
  
  public zzab(zzv paramzzv, int paramInt)
  {
    this.zzaq = paramzzv;
    this.buf = this.zzaq.zzb(Math.max(paramInt, 256));
  }
  
  private void zzd(int paramInt)
  {
    if (this.count + paramInt <= this.buf.length) {
      return;
    }
    byte[] arrayOfByte = this.zzaq.zzb((this.count + paramInt) * 2);
    System.arraycopy(this.buf, 0, arrayOfByte, 0, this.count);
    this.zzaq.zza(this.buf);
    this.buf = arrayOfByte;
  }
  
  public void close()
    throws IOException
  {
    this.zzaq.zza(this.buf);
    this.buf = null;
    super.close();
  }
  
  public void finalize()
  {
    this.zzaq.zza(this.buf);
  }
  
  public void write(int paramInt)
  {
    try
    {
      zzd(1);
      super.write(paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      zzd(paramInt2);
      super.write(paramArrayOfByte, paramInt1, paramInt2);
      return;
    }
    finally
    {
      paramArrayOfByte = finally;
      throw paramArrayOfByte;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */