package com.google.android.gms.common.util;

import android.database.CharArrayBuffer;
import android.text.TextUtils;

public final class zzh
{
  public static void zzb(String paramString, CharArrayBuffer paramCharArrayBuffer)
  {
    if (TextUtils.isEmpty(paramString)) {
      paramCharArrayBuffer.sizeCopied = 0;
    }
    for (;;)
    {
      paramCharArrayBuffer.sizeCopied = paramString.length();
      return;
      if ((paramCharArrayBuffer.data == null) || (paramCharArrayBuffer.data.length < paramString.length())) {
        paramCharArrayBuffer.data = paramString.toCharArray();
      } else {
        paramString.getChars(0, paramString.length(), paramCharArrayBuffer.data, 0);
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\util\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */