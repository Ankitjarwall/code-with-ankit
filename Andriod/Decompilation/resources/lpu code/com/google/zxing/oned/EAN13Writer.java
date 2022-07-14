package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class EAN13Writer
  extends UPCEANWriter
{
  private static final int CODE_WIDTH = 95;
  
  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
    throws WriterException
  {
    if (paramBarcodeFormat != BarcodeFormat.EAN_13) {
      throw new IllegalArgumentException("Can only encode EAN_13, but got " + paramBarcodeFormat);
    }
    return super.encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, paramMap);
  }
  
  public boolean[] encode(String paramString)
  {
    if (paramString.length() != 13) {
      throw new IllegalArgumentException("Requested contents should be 13 digits long, but got " + paramString.length());
    }
    try
    {
      if (!UPCEANReader.checkStandardUPCEANChecksum(paramString)) {
        throw new IllegalArgumentException("Contents do not pass checksum");
      }
    }
    catch (FormatException paramString)
    {
      throw new IllegalArgumentException("Illegal contents");
    }
    int i = Integer.parseInt(paramString.substring(0, 1));
    int n = EAN13Reader.FIRST_DIGIT_ENCODINGS[i];
    boolean[] arrayOfBoolean = new boolean[95];
    int j = 0 + appendPattern(arrayOfBoolean, 0, UPCEANReader.START_END_PATTERN, true);
    i = 1;
    int k;
    while (i <= 6)
    {
      int m = Integer.parseInt(paramString.substring(i, i + 1));
      k = m;
      if ((n >> 6 - i & 0x1) == 1) {
        k = m + 10;
      }
      j += appendPattern(arrayOfBoolean, j, UPCEANReader.L_AND_G_PATTERNS[k], false);
      i += 1;
    }
    j += appendPattern(arrayOfBoolean, j, UPCEANReader.MIDDLE_PATTERN, false);
    i = 7;
    while (i <= 12)
    {
      k = Integer.parseInt(paramString.substring(i, i + 1));
      j += appendPattern(arrayOfBoolean, j, UPCEANReader.L_PATTERNS[k], true);
      i += 1;
    }
    appendPattern(arrayOfBoolean, j, UPCEANReader.START_END_PATTERN, true);
    return arrayOfBoolean;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\EAN13Writer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */