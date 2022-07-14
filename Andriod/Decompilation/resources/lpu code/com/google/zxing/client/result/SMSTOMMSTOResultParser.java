package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class SMSTOMMSTOResultParser
  extends ResultParser
{
  public SMSParsedResult parse(Result paramResult)
  {
    paramResult = getMassagedText(paramResult);
    if ((!paramResult.startsWith("smsto:")) && (!paramResult.startsWith("SMSTO:")) && (!paramResult.startsWith("mmsto:")) && (!paramResult.startsWith("MMSTO:"))) {
      return null;
    }
    String str2 = paramResult.substring(6);
    String str1 = null;
    int i = str2.indexOf(':');
    paramResult = str2;
    if (i >= 0)
    {
      str1 = str2.substring(i + 1);
      paramResult = str2.substring(0, i);
    }
    return new SMSParsedResult(paramResult, null, null, str1);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\SMSTOMMSTOResultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */