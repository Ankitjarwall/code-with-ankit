package com.google.android.gms.internal;

abstract class zzauh
  extends zzaug
{
  private boolean zzadP;
  
  zzauh(zzaue paramzzaue)
  {
    super(paramzzaue);
    this.zzbqb.zzb(this);
  }
  
  public final void initialize()
  {
    if (this.zzadP) {
      throw new IllegalStateException("Can't initialize twice");
    }
    zzmS();
    this.zzbqb.zzMM();
    this.zzadP = true;
  }
  
  boolean isInitialized()
  {
    return this.zzadP;
  }
  
  protected abstract void zzmS();
  
  protected void zzob()
  {
    if (!isInitialized()) {
      throw new IllegalStateException("Not initialized");
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzauh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */