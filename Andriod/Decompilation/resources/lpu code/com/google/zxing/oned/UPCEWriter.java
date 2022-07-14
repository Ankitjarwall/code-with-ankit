package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class UPCEWriter
  extends UPCEANWriter
{
  private static final int CODE_WIDTH = 51;
  
  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
    throws WriterException
  {
    if (paramBarcodeFormat != BarcodeFormat.UPC_E) {
      throw new IllegalArgumentException("Can only encode UPC_E, but got " + paramBarcodeFormat);
    }
    return super.encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, paramMap);
  }
  
  public boolean[] encode(String paramString)
  {
    if (paramString.length() != 8) {
      throw new IllegalArgumentException("Requested contents should be 8 digits long, but got " + paramString.length());
    }
    int i = Integer.parseInt(paramString.substring(7, 8));
    int n = UPCEReader.CHECK_DIGIT_ENCODINGS[i];
    boolean[] arrayOfBoolean = new boolean[51];
    int j = 0 + appendPattern(arrayOfBoolean, 0, UPCEANReader.START_END_PATTERN, true);
    i = 1;
    while (i <= 6)
    {
      int m = Integer.parseInt(paramString.substring(i, i + 1));
      int k = m;
      if ((n >> 6 - i & 0x1) == 1) {
        k = m + 10;
      }
      j += appendPattern(arrayOfBoolean, j, UPCEANReader.L_AND_G_PATTERNS[k], false);
      i += 1;
    }
    appendPattern(arrayOfBoolean, j, UPCEANReader.END_PATTERN, false);
    return arrayOfBoolean;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\UPCEWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */