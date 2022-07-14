package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public class zzg<T>
  extends zzb<T>
{
  private T zzaEe;
  
  public zzg(DataBuffer<T> paramDataBuffer)
  {
    super(paramDataBuffer);
  }
  
  public T next()
  {
    if (!hasNext())
    {
      int i = this.zzaDJ;
      throw new NoSuchElementException(46 + "Cannot advance the iterator beyond " + i);
    }
    this.zzaDJ += 1;
    if (this.zzaDJ == 0)
    {
      this.zzaEe = this.zzaDI.get(0);
      if (!(this.zzaEe instanceof zzc))
      {
        String str = String.valueOf(this.zzaEe.getClass());
        throw new IllegalStateException(String.valueOf(str).length() + 44 + "DataBuffer reference of type " + str + " is not movable");
      }
    }
    else
    {
      ((zzc)this.zzaEe).zzcG(this.zzaDJ);
    }
    return (T)this.zzaEe;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\data\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */