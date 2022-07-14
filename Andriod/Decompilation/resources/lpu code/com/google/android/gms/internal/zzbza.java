package com.google.android.gms.internal;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public abstract interface zzbza
  extends Closeable, Flushable
{
  public abstract void close()
    throws IOException;
  
  public abstract void flush()
    throws IOException;
  
  public abstract void write(zzbyr paramzzbyr, long paramLong)
    throws IOException;
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzbza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */