package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InterruptedIOException;

public class zzbyq
  extends zzbzc
{
  protected IOException newTimeoutException(IOException paramIOException)
  {
    InterruptedIOException localInterruptedIOException = new InterruptedIOException("timeout");
    if (paramIOException != null) {
      localInterruptedIOException.initCause(paramIOException);
    }
    return localInterruptedIOException;
  }
  
  protected void timedOut() {}
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzbyq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */