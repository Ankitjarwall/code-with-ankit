package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class SMTPResultParser
  extends ResultParser
{
  public EmailAddressParsedResult parse(Result paramResult)
  {
    paramResult = getMassagedText(paramResult);
    if ((!paramResult.startsWith("smtp:")) && (!paramResult.startsWith("SMTP:"))) {
      return null;
    }
    String str3 = paramResult.substring(5);
    paramResult = null;
    Object localObject2 = null;
    int i = str3.indexOf(':');
    Object localObject1 = localObject2;
    String str1 = str3;
    if (i >= 0)
    {
      String str2 = str3.substring(i + 1);
      str3 = str3.substring(0, i);
      i = str2.indexOf(':');
      paramResult = str2;
      localObject1 = localObject2;
      str1 = str3;
      if (i >= 0)
      {
        localObject1 = str2.substring(i + 1);
        paramResult = str2.substring(0, i);
        str1 = str3;
      }
    }
    return new EmailAddressParsedResult(new String[] { str1 }, null, null, paramResult, (String)localObject1);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\SMTPResultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */