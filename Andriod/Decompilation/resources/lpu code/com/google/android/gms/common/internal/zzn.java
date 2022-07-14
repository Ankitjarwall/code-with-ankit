package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

public abstract class zzn
{
  private static final Object zzaGb = new Object();
  private static zzn zzaGc;
  
  public static zzn zzaU(Context paramContext)
  {
    synchronized (zzaGb)
    {
      if (zzaGc == null) {
        zzaGc = new zzo(paramContext.getApplicationContext());
      }
      return zzaGc;
    }
  }
  
  public boolean zza(ComponentName paramComponentName, ServiceConnection paramServiceConnection, String paramString)
  {
    return zza(new zza(paramComponentName), paramServiceConnection, paramString);
  }
  
  protected abstract boolean zza(zza paramzza, ServiceConnection paramServiceConnection, String paramString);
  
  public boolean zza(String paramString1, String paramString2, ServiceConnection paramServiceConnection, String paramString3)
  {
    return zza(new zza(paramString1, paramString2), paramServiceConnection, paramString3);
  }
  
  public void zzb(ComponentName paramComponentName, ServiceConnection paramServiceConnection, String paramString)
  {
    zzb(new zza(paramComponentName), paramServiceConnection, paramString);
  }
  
  protected abstract void zzb(zza paramzza, ServiceConnection paramServiceConnection, String paramString);
  
  public void zzb(String paramString1, String paramString2, ServiceConnection paramServiceConnection, String paramString3)
  {
    zzb(new zza(paramString1, paramString2), paramServiceConnection, paramString3);
  }
  
  protected static final class zza
  {
    private final String zzaGd;
    private final ComponentName zzaGe;
    private final String zzadb;
    
    public zza(ComponentName paramComponentName)
    {
      this.zzadb = null;
      this.zzaGd = null;
      this.zzaGe = ((ComponentName)zzac.zzw(paramComponentName));
    }
    
    public zza(String paramString1, String paramString2)
    {
      this.zzadb = zzac.zzdr(paramString1);
      this.zzaGd = zzac.zzdr(paramString2);
      this.zzaGe = null;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof zza)) {
          return false;
        }
        paramObject = (zza)paramObject;
      } while ((zzaa.equal(this.zzadb, ((zza)paramObject).zzadb)) && (zzaa.equal(this.zzaGe, ((zza)paramObject).zzaGe)));
      return false;
    }
    
    public ComponentName getComponentName()
    {
      return this.zzaGe;
    }
    
    public String getPackage()
    {
      return this.zzaGd;
    }
    
    public int hashCode()
    {
      return zzaa.hashCode(new Object[] { this.zzadb, this.zzaGe });
    }
    
    public String toString()
    {
      if (this.zzadb == null) {
        return this.zzaGe.flattenToString();
      }
      return this.zzadb;
    }
    
    public Intent zzxZ()
    {
      if (this.zzadb != null) {
        return new Intent(this.zzadb).setPackage(this.zzaGd);
      }
      return new Intent().setComponent(this.zzaGe);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */