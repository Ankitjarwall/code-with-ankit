package com.google.android.gms.internal;

import android.os.Process;

class zzadd
  implements Runnable
{
  private final int mPriority;
  private final Runnable zzw;
  
  public zzadd(Runnable paramRunnable, int paramInt)
  {
    this.zzw = paramRunnable;
    this.mPriority = paramInt;
  }
  
  public void run()
  {
    Process.setThreadPriority(this.mPriority);
    this.zzw.run();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzadd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */