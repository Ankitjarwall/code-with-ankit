package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.zzac;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class zzb<T>
  implements Iterator<T>
{
  protected final DataBuffer<T> zzaDI;
  protected int zzaDJ;
  
  public zzb(DataBuffer<T> paramDataBuffer)
  {
    this.zzaDI = ((DataBuffer)zzac.zzw(paramDataBuffer));
    this.zzaDJ = -1;
  }
  
  public boolean hasNext()
  {
    return this.zzaDJ < this.zzaDI.getCount() - 1;
  }
  
  public T next()
  {
    if (!hasNext())
    {
      i = this.zzaDJ;
      throw new NoSuchElementException(46 + "Cannot advance the iterator beyond " + i);
    }
    DataBuffer localDataBuffer = this.zzaDI;
    int i = this.zzaDJ + 1;
    this.zzaDJ = i;
    return (T)localDataBuffer.get(i);
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\data\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */