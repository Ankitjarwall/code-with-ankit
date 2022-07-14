package com.google.zxing.pdf417.decoder;

import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.pdf417.PDF417Common;
import java.lang.reflect.Array;

final class PDF417CodewordDecoder
{
  private static final float[][] RATIOS_TABLE;
  
  static
  {
    int i = PDF417Common.SYMBOL_TABLE.length;
    RATIOS_TABLE = (float[][])Array.newInstance(Float.TYPE, new int[] { i, 8 });
    i = 0;
    while (i < PDF417Common.SYMBOL_TABLE.length)
    {
      int m = PDF417Common.SYMBOL_TABLE[i];
      int k = m & 0x1;
      int j = 0;
      while (j < 8)
      {
        float f = 0.0F;
        while ((m & 0x1) == k)
        {
          f += 1.0F;
          m >>= 1;
        }
        k = m & 0x1;
        RATIOS_TABLE[i][(8 - j - 1)] = (f / 17.0F);
        j += 1;
      }
      i += 1;
    }
  }
  
  private static int getBitValue(int[] paramArrayOfInt)
  {
    long l = 0L;
    int i = 0;
    while (i < paramArrayOfInt.length)
    {
      int j = 0;
      if (j < paramArrayOfInt[i])
      {
        if (i % 2 == 0) {}
        for (int k = 1;; k = 0)
        {
          l = l << 1 | k;
          j += 1;
          break;
        }
      }
      i += 1;
    }
    return (int)l;
  }
  
  private static int getClosestDecodedValue(int[] paramArrayOfInt)
  {
    int j = MathUtils.sum(paramArrayOfInt);
    float[] arrayOfFloat = new float[8];
    int i = 0;
    while (i < arrayOfFloat.length)
    {
      arrayOfFloat[i] = (paramArrayOfInt[i] / j);
      i += 1;
    }
    float f3 = Float.MAX_VALUE;
    j = -1;
    i = 0;
    if (i < RATIOS_TABLE.length)
    {
      float f1 = 0.0F;
      paramArrayOfInt = RATIOS_TABLE[i];
      int k = 0;
      for (;;)
      {
        float f2 = f1;
        if (k < 8)
        {
          f2 = paramArrayOfInt[k] - arrayOfFloat[k];
          f1 += f2 * f2;
          if (f1 >= f3) {
            f2 = f1;
          }
        }
        else
        {
          f1 = f3;
          if (f2 < f3)
          {
            f1 = f2;
            j = PDF417Common.SYMBOL_TABLE[i];
          }
          i += 1;
          f3 = f1;
          break;
        }
        k += 1;
      }
    }
    return j;
  }
  
  private static int getDecodedCodewordValue(int[] paramArrayOfInt)
  {
    int j = getBitValue(paramArrayOfInt);
    int i = j;
    if (PDF417Common.getCodeword(j) == -1) {
      i = -1;
    }
    return i;
  }
  
  static int getDecodedValue(int[] paramArrayOfInt)
  {
    int i = getDecodedCodewordValue(sampleBitCounts(paramArrayOfInt));
    if (i != -1) {
      return i;
    }
    return getClosestDecodedValue(paramArrayOfInt);
  }
  
  private static int[] sampleBitCounts(int[] paramArrayOfInt)
  {
    float f1 = MathUtils.sum(paramArrayOfInt);
    int[] arrayOfInt = new int[8];
    int j = 0;
    int k = 0;
    int i = 0;
    while (i < 17)
    {
      float f2 = f1 / 34.0F;
      float f3 = i * f1 / 17.0F;
      int n = j;
      int m = k;
      if (paramArrayOfInt[j] + k <= f2 + f3)
      {
        m = k + paramArrayOfInt[j];
        n = j + 1;
      }
      arrayOfInt[n] += 1;
      i += 1;
      j = n;
      k = m;
    }
    return arrayOfInt;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\pdf417\decoder\PDF417CodewordDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */