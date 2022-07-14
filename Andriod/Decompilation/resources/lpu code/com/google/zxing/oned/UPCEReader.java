package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class UPCEReader
  extends UPCEANReader
{
  static final int[] CHECK_DIGIT_ENCODINGS = { 56, 52, 50, 49, 44, 38, 35, 42, 41, 37 };
  private static final int[] MIDDLE_END_PATTERN = { 1, 1, 1, 1, 1, 1 };
  private static final int[][] NUMSYS_AND_CHECK_DIGIT_PATTERNS = { { 56, 52, 50, 49, 44, 38, 35, 42, 41, 37 }, { 7, 11, 13, 14, 19, 25, 28, 21, 22, 26 } };
  private final int[] decodeMiddleCounters = new int[4];
  
  public static String convertUPCEtoUPCA(String paramString)
  {
    char[] arrayOfChar = new char[6];
    paramString.getChars(1, 7, arrayOfChar, 0);
    StringBuilder localStringBuilder = new StringBuilder(12);
    localStringBuilder.append(paramString.charAt(0));
    char c = arrayOfChar[5];
    switch (c)
    {
    default: 
      localStringBuilder.append(arrayOfChar, 0, 5);
      localStringBuilder.append("0000");
      localStringBuilder.append(c);
    }
    for (;;)
    {
      localStringBuilder.append(paramString.charAt(7));
      return localStringBuilder.toString();
      localStringBuilder.append(arrayOfChar, 0, 2);
      localStringBuilder.append(c);
      localStringBuilder.append("0000");
      localStringBuilder.append(arrayOfChar, 2, 3);
      continue;
      localStringBuilder.append(arrayOfChar, 0, 3);
      localStringBuilder.append("00000");
      localStringBuilder.append(arrayOfChar, 3, 2);
      continue;
      localStringBuilder.append(arrayOfChar, 0, 4);
      localStringBuilder.append("00000");
      localStringBuilder.append(arrayOfChar[4]);
    }
  }
  
  private static void determineNumSysAndCheckDigit(StringBuilder paramStringBuilder, int paramInt)
    throws NotFoundException
  {
    int i = 0;
    while (i <= 1)
    {
      int j = 0;
      while (j < 10)
      {
        if (paramInt == NUMSYS_AND_CHECK_DIGIT_PATTERNS[i][j])
        {
          paramStringBuilder.insert(0, (char)(i + 48));
          paramStringBuilder.append((char)(j + 48));
          return;
        }
        j += 1;
      }
      i += 1;
    }
    throw NotFoundException.getNotFoundInstance();
  }
  
  protected boolean checkChecksum(String paramString)
    throws FormatException
  {
    return super.checkChecksum(convertUPCEtoUPCA(paramString));
  }
  
  protected int[] decodeEnd(BitArray paramBitArray, int paramInt)
    throws NotFoundException
  {
    return findGuardPattern(paramBitArray, paramInt, true, MIDDLE_END_PATTERN);
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
    while ((i < 6) && (k < n))
    {
      int i1 = decodeDigit(paramBitArray, arrayOfInt, k, L_AND_G_PATTERNS);
      paramStringBuilder.append((char)(i1 % 10 + 48));
      int i2 = arrayOfInt.length;
      int m = 0;
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
    determineNumSysAndCheckDigit(paramStringBuilder, j);
    return k;
  }
  
  BarcodeFormat getBarcodeFormat()
  {
    return BarcodeFormat.UPC_E;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\UPCEReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */