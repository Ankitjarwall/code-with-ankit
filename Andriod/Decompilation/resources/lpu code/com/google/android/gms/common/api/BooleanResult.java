package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzac;

public class BooleanResult
  implements Result
{
  private final Status zzair;
  private final boolean zzayS;
  
  public BooleanResult(Status paramStatus, boolean paramBoolean)
  {
    this.zzair = ((Status)zzac.zzb(paramStatus, "Status must not be null"));
    this.zzayS = paramBoolean;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof BooleanResult)) {
        return false;
      }
      paramObject = (BooleanResult)paramObject;
    } while ((this.zzair.equals(((BooleanResult)paramObject).zzair)) && (this.zzayS == ((BooleanResult)paramObject).zzayS));
    return false;
  }
  
  public Status getStatus()
  {
    return this.zzair;
  }
  
  public boolean getValue()
  {
    return this.zzayS;
  }
  
  public final int hashCode()
  {
    int j = this.zzair.hashCode();
    if (this.zzayS) {}
    for (int i = 1;; i = 0) {
      return i + (j + 527) * 31;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\api\BooleanResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */