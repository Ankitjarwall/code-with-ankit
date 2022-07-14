package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class TelResultParser
  extends ResultParser
{
  public TelParsedResult parse(Result paramResult)
  {
    String str = getMassagedText(paramResult);
    if ((!str.startsWith("tel:")) && (!str.startsWith("TEL:"))) {
      return null;
    }
    int i;
    if (str.startsWith("TEL:"))
    {
      paramResult = "tel:" + str.substring(4);
      i = str.indexOf('?', 4);
      if (i >= 0) {
        break label92;
      }
    }
    label92:
    for (str = str.substring(4);; str = str.substring(4, i))
    {
      return new TelParsedResult(str, paramResult, null);
      paramResult = str;
      break;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\TelResultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */