package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class EAN8Reader
  extends UPCEANReader
{
  private final int[] decodeMiddleCounters = new int[4];
  
  protected int decodeMiddle(BitArray paramBitArray, int[] paramArrayOfInt, StringBuilder paramStringBuilder)
    throws NotFoundException
  {
    int[] arrayOfInt = this.decodeMiddleCounters;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    int m = paramBitArray.getSize();
    int j = paramArrayOfInt[1];
    int i = 0;
    int n;
    int k;
    while ((i < 4) && (j < m))
    {
      paramStringBuilder.append((char)(decodeDigit(paramBitArray, arrayOfInt, j, L_PATTERNS) + 48));
      n = arrayOfInt.length;
      k = 0;
      while (k < n)
      {
        j += arrayOfInt[k];
        k += 1;
      }
      i += 1;
    }
    j = findGuardPattern(paramBitArray, j, true, MIDDLE_PATTERN)[1];
    i = 0;
    while ((i < 4) && (j < m))
    {
      paramStringBuilder.append((char)(decodeDigit(paramBitArray, arrayOfInt, j, L_PATTERNS) + 48));
      n = arrayOfInt.length;
      k = 0;
      while (k < n)
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
    return BarcodeFormat.EAN_8;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\EAN8Reader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */