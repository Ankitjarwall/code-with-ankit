package com.google.firebase.iid;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public class zze
{
  private static final Object zztX = new Object();
  private final zzh zzclx;
  
  zze(zzh paramzzh)
  {
    this.zzclx = paramzzh;
  }
  
  @Nullable
  String zzabU()
  {
    synchronized (zztX)
    {
      Object localObject2 = this.zzclx.zzabZ().getString("topic_operaion_queue", null);
      if (localObject2 != null)
      {
        localObject2 = ((String)localObject2).split(",");
        if ((localObject2.length > 1) && (!TextUtils.isEmpty(localObject2[1])))
        {
          localObject2 = localObject2[1];
          return (String)localObject2;
        }
      }
      return null;
    }
  }
  
  void zzjt(String paramString)
  {
    synchronized (zztX)
    {
      String str1 = this.zzclx.zzabZ().getString("topic_operaion_queue", "");
      String str2 = String.valueOf(",");
      paramString = String.valueOf(str1).length() + String.valueOf(str2).length() + String.valueOf(paramString).length() + str1 + str2 + paramString;
      this.zzclx.zzabZ().edit().putString("topic_operaion_queue", paramString).apply();
      return;
    }
  }
  
  boolean zzjx(String paramString)
  {
    for (;;)
    {
      String str1;
      synchronized (zztX)
      {
        String str2 = this.zzclx.zzabZ().getString("topic_operaion_queue", "");
        str1 = String.valueOf(",");
        String str3 = String.valueOf(paramString);
        if (str3.length() != 0)
        {
          str1 = str1.concat(str3);
          if (!str2.startsWith(str1)) {
            break;
          }
          str1 = String.valueOf(",");
          paramString = String.valueOf(paramString);
          if (paramString.length() != 0)
          {
            paramString = str1.concat(paramString);
            paramString = str2.substring(paramString.length());
            this.zzclx.zzabZ().edit().putString("topic_operaion_queue", paramString).apply();
            return true;
          }
        }
        else
        {
          str1 = new String(str1);
        }
      }
      paramString = new String(str1);
    }
    return false;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\iid\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */