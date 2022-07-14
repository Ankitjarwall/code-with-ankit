package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class EAN8Writer
  extends UPCEANWriter
{
  private static final int CODE_WIDTH = 67;
  
  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
    throws WriterException
  {
    if (paramBarcodeFormat != BarcodeFormat.EAN_8) {
      throw new IllegalArgumentException("Can only encode EAN_8, but got " + paramBarcodeFormat);
    }
    return super.encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, paramMap);
  }
  
  public boolean[] encode(String paramString)
  {
    if (paramString.length() != 8) {
      throw new IllegalArgumentException("Requested contents should be 8 digits long, but got " + paramString.length());
    }
    boolean[] arrayOfBoolean = new boolean[67];
    int j = 0 + appendPattern(arrayOfBoolean, 0, UPCEANReader.START_END_PATTERN, true);
    int i = 0;
    int k;
    while (i <= 3)
    {
      k = Integer.parseInt(paramString.substring(i, i + 1));
      j += appendPattern(arrayOfBoolean, j, UPCEANReader.L_PATTERNS[k], false);
      i += 1;
    }
    j += appendPattern(arrayOfBoolean, j, UPCEANReader.MIDDLE_PATTERN, false);
    i = 4;
    while (i <= 7)
    {
      k = Integer.parseInt(paramString.substring(i, i + 1));
      j += appendPattern(arrayOfBoolean, j, UPCEANReader.L_PATTERNS[k], true);
      i += 1;
    }
    appendPattern(arrayOfBoolean, j, UPCEANReader.START_END_PATTERN, true);
    return arrayOfBoolean;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\EAN8Writer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */