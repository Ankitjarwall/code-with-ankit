package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

public final class ISBNResultParser
  extends ResultParser
{
  public ISBNParsedResult parse(Result paramResult)
  {
    if (paramResult.getBarcodeFormat() != BarcodeFormat.EAN_13) {}
    do
    {
      return null;
      paramResult = getMassagedText(paramResult);
    } while ((paramResult.length() != 13) || ((!paramResult.startsWith("978")) && (!paramResult.startsWith("979"))));
    return new ISBNParsedResult(paramResult);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\ISBNResultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */