package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class EAN13Reader
  extends UPCEANReader
{
  static final int[] FIRST_DIGIT_ENCODINGS = { 0, 11, 13, 14, 19, 25, 28, 21, 22, 26 };
  private final int[] decodeMiddleCounters = new int[4];
  
  private static void determineFirstDigit(StringBuilder paramStringBuilder, int paramInt)
    throws NotFoundException
  {
    int i = 0;
    while (i < 10)
    {
      if (paramInt == FIRST_DIGIT_ENCODINGS[i])
      {
        paramStringBuilder.insert(0, (char)(i + 48));
        return;
      }
      i += 1;
    }
    throw NotFoundException.getNotFoundInstance();
  }
  
  protected int decodeMiddle(BitArray paramBitArray, int[] paramArrayOfInt, StringBuilder paramStringBuilder)
    throws NotFoundException
  {
    int[] arrayOfInt = this.decodeMiddleCounters;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    int n = paramBitArray.getSize();
    int k = paramArrayOfInt[1];
    int j = 0;
    int i = 0;
    int m;
    while ((i < 6) && (k < n))
    {
      int i1 = decodeDigit(paramBitArray, arrayOfInt, k, L_AND_G_PATTERNS);
      paramStringBuilder.append((char)(i1 % 10 + 48));
      int i2 = arrayOfInt.length;
      m = 0;
      while (m < i2)
      {
        k += arrayOfInt[m];
        m += 1;
      }
      m = j;
      if (i1 >= 10) {
        m = j | 1 << 5 - i;
      }
      i += 1;
      j = m;
    }
    determineFirstDigit(paramStringBuilder, j);
    j = findGuardPattern(paramBitArray, k, true, MIDDLE_PATTERN)[1];
    i = 0;
    while ((i < 6) && (j < n))
    {
      paramStringBuilder.append((char)(decodeDigit(paramBitArray, arrayOfInt, j, L_PATTERNS) + 48));
      m = arrayOfInt.length;
      k = 0;
      while (k < m)
      {
        j += arrayOfInt[k];
        k += 1;
      }
      i += 1;
    }
    return j;
  }
  
  BarcodeFormat getBarcodeFormat()
  {
    return BarcodeFormat.EAN_13;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\EAN13Reader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */