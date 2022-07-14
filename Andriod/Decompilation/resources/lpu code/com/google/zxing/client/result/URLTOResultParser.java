package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class URLTOResultParser
  extends ResultParser
{
  public URIParsedResult parse(Result paramResult)
  {
    Object localObject = null;
    String str = getMassagedText(paramResult);
    if ((!str.startsWith("urlto:")) && (!str.startsWith("URLTO:"))) {}
    int i;
    do
    {
      return null;
      i = str.indexOf(':', 6);
    } while (i < 0);
    if (i <= 6) {}
    for (paramResult = (Result)localObject;; paramResult = str.substring(6, i)) {
      return new URIParsedResult(str.substring(i + 1), paramResult);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\URLTOResultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */