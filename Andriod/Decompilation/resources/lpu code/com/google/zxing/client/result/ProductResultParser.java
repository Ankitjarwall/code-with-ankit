package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.oned.UPCEReader;

public final class ProductResultParser
  extends ResultParser
{
  public ProductParsedResult parse(Result paramResult)
  {
    BarcodeFormat localBarcodeFormat = paramResult.getBarcodeFormat();
    if ((localBarcodeFormat != BarcodeFormat.UPC_A) && (localBarcodeFormat != BarcodeFormat.UPC_E) && (localBarcodeFormat != BarcodeFormat.EAN_8) && (localBarcodeFormat != BarcodeFormat.EAN_13)) {}
    String str;
    do
    {
      return null;
      str = getMassagedText(paramResult);
    } while (!isStringOfDigits(str, str.length()));
    if ((localBarcodeFormat == BarcodeFormat.UPC_E) && (str.length() == 8)) {}
    for (paramResult = UPCEReader.convertUPCEtoUPCA(str);; paramResult = str) {
      return new ProductParsedResult(str, paramResult);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\ProductResultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */