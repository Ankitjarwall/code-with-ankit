package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzbyv
  implements zzbza
{
  private final zzbza zzcxY;
  
  public void close()
    throws IOException
  {
    this.zzcxY.close();
  }
  
  public void flush()
    throws IOException
  {
    this.zzcxY.flush();
  }
  
  public String toString()
  {
    return getClass().getSimpleName() + "(" + this.zzcxY.toString() + ")";
  }
  
  public void write(zzbyr paramzzbyr, long paramLong)
    throws IOException
  {
    this.zzcxY.write(paramzzbyr, paramLong);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzbyv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */