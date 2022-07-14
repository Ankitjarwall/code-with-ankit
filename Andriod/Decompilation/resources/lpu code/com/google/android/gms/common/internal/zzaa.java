package com.google.android.gms.common.internal;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class zzaa
{
  public static boolean equal(@Nullable Object paramObject1, @Nullable Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }
  
  public static int hashCode(Object... paramVarArgs)
  {
    return Arrays.hashCode(paramVarArgs);
  }
  
  public static zza zzv(Object paramObject)
  {
    return new zza(paramObject, null);
  }
  
  public static final class zza
  {
    private final Object zzYG;
    private final List<String> zzaGv;
    
    private zza(Object paramObject)
    {
      this.zzYG = zzac.zzw(paramObject);
      this.zzaGv = new ArrayList();
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(100).append(this.zzYG.getClass().getSimpleName()).append('{');
      int j = this.zzaGv.size();
      int i = 0;
      while (i < j)
      {
        localStringBuilder.append((String)this.zzaGv.get(i));
        if (i < j - 1) {
          localStringBuilder.append(", ");
        }
        i += 1;
      }
      return '}';
    }
    
    public zza zzg(String paramString, Object paramObject)
    {
      List localList = this.zzaGv;
      paramString = (String)zzac.zzw(paramString);
      paramObject = String.valueOf(String.valueOf(paramObject));
      localList.add(String.valueOf(paramString).length() + 1 + String.valueOf(paramObject).length() + paramString + "=" + (String)paramObject);
      return this;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\zzaa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */