package com.google.android.gms.common.util;

import android.support.v4.util.ArrayMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class zza<E>
  extends AbstractSet<E>
{
  private final ArrayMap<E, E> zzaHS;
  
  public zza()
  {
    this.zzaHS = new ArrayMap();
  }
  
  public zza(int paramInt)
  {
    this.zzaHS = new ArrayMap(paramInt);
  }
  
  public zza(Collection<E> paramCollection)
  {
    this(paramCollection.size());
    addAll(paramCollection);
  }
  
  public boolean add(E paramE)
  {
    if (this.zzaHS.containsKey(paramE)) {
      return false;
    }
    this.zzaHS.put(paramE, paramE);
    return true;
  }
  
  public boolean addAll(Collection<? extends E> paramCollection)
  {
    if ((paramCollection instanceof zza)) {
      return zza((zza)paramCollection);
    }
    return super.addAll(paramCollection);
  }
  
  public void clear()
  {
    this.zzaHS.clear();
  }
  
  public boolean contains(Object paramObject)
  {
    return this.zzaHS.containsKey(paramObject);
  }
  
  public Iterator<E> iterator()
  {
    return this.zzaHS.keySet().iterator();
  }
  
  public boolean remove(Object paramObject)
  {
    if (!this.zzaHS.containsKey(paramObject)) {
      return false;
    }
    this.zzaHS.remove(paramObject);
    return true;
  }
  
  public int size()
  {
    return this.zzaHS.size();
  }
  
  public boolean zza(zza<? extends E> paramzza)
  {
    int i = size();
    this.zzaHS.putAll(paramzza.zzaHS);
    return size() > i;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\util\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */