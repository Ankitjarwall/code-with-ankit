package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

public abstract class AbstractDataBuffer<T>
  implements DataBuffer<T>
{
  protected final DataHolder zzaBi;
  
  protected AbstractDataBuffer(DataHolder paramDataHolder)
  {
    this.zzaBi = paramDataHolder;
  }
  
  @Deprecated
  public final void close()
  {
    release();
  }
  
  public abstract T get(int paramInt);
  
  public int getCount()
  {
    if (this.zzaBi == null) {
      return 0;
    }
    return this.zzaBi.getCount();
  }
  
  @Deprecated
  public boolean isClosed()
  {
    return (this.zzaBi == null) || (this.zzaBi.isClosed());
  }
  
  public Iterator<T> iterator()
  {
    return new zzb(this);
  }
  
  public void release()
  {
    if (this.zzaBi != null) {
      this.zzaBi.close();
    }
  }
  
  public Iterator<T> singleRefIterator()
  {
    return new zzg(this);
  }
  
  public Bundle zzxf()
  {
    return this.zzaBi.zzxf();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\data\AbstractDataBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */