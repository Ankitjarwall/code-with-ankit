package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class ITFWriter
  extends OneDimensionalCodeWriter
{
  private static final int[] END_PATTERN = { 3, 1, 1 };
  private static final int[] START_PATTERN = { 1, 1, 1, 1 };
  
  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
    throws WriterException
  {
    if (paramBarcodeFormat != BarcodeFormat.ITF) {
      throw new IllegalArgumentException("Can only encode ITF, but got " + paramBarcodeFormat);
    }
    return super.encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, paramMap);
  }
  
  public boolean[] encode(String paramString)
  {
    int m = paramString.length();
    if (m % 2 != 0) {
      throw new IllegalArgumentException("The length of the input should be even");
    }
    if (m > 80) {
      throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + m);
    }
    boolean[] arrayOfBoolean = new boolean[m * 9 + 9];
    int j = appendPattern(arrayOfBoolean, 0, START_PATTERN, true);
    int i = 0;
    while (i < m)
    {
      int n = Character.digit(paramString.charAt(i), 10);
      int i1 = Character.digit(paramString.charAt(i + 1), 10);
      int[] arrayOfInt = new int[18];
      int k = 0;
      while (k < 5)
      {
        arrayOfInt[(k * 2)] = ITFReader.PATTERNS[n][k];
        arrayOfInt[(k * 2 + 1)] = ITFReader.PATTERNS[i1][k];
        k += 1;
      }
      j += appendPattern(arrayOfBoolean, j, arrayOfInt, true);
      i += 2;
    }
    appendPattern(arrayOfBoolean, j, END_PATTERN, true);
    return arrayOfBoolean;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\ITFWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */