package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class UPCAWriter
  implements Writer
{
  private final EAN13Writer subWriter = new EAN13Writer();
  
  private static String preencode(String paramString)
  {
    int i = paramString.length();
    String str;
    if (i == 11)
    {
      int j = 0;
      i = 0;
      if (i < 11)
      {
        int m = paramString.charAt(i);
        if (i % 2 == 0) {}
        for (int k = 3;; k = 1)
        {
          j += k * (m - 48);
          i += 1;
          break;
        }
      }
      str = paramString + (1000 - j) % 10;
    }
    do
    {
      return '0' + str;
      str = paramString;
    } while (i == 12);
    throw new IllegalArgumentException("Requested contents should be 11 or 12 digits long, but got " + paramString.length());
  }
  
  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2)
    throws WriterException
  {
    return encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, null);
  }
  
  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
    throws WriterException
  {
    if (paramBarcodeFormat != BarcodeFormat.UPC_A) {
      throw new IllegalArgumentException("Can only encode UPC-A, but got " + paramBarcodeFormat);
    }
    return this.subWriter.encode(preencode(paramString), BarcodeFormat.EAN_13, paramInt1, paramInt2, paramMap);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\UPCAWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */