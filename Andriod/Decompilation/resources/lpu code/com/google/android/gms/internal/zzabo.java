package com.google.android.gms.internal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class zzabo
{
  private static final ExecutorService zzaCm = new ThreadPoolExecutor(0, 4, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new zzadc("GAC_Transform"));
  
  public static ExecutorService zzwv()
  {
    return zzaCm;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzabo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */