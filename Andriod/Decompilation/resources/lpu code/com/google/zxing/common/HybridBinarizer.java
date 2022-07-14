package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import java.lang.reflect.Array;

public final class HybridBinarizer
  extends GlobalHistogramBinarizer
{
  private static final int BLOCK_SIZE = 8;
  private static final int BLOCK_SIZE_MASK = 7;
  private static final int BLOCK_SIZE_POWER = 3;
  private static final int MINIMUM_DIMENSION = 40;
  private static final int MIN_DYNAMIC_RANGE = 24;
  private BitMatrix matrix;
  
  public HybridBinarizer(LuminanceSource paramLuminanceSource)
  {
    super(paramLuminanceSource);
  }
  
  private static int[][] calculateBlackPoints(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int[][] arrayOfInt = (int[][])Array.newInstance(Integer.TYPE, new int[] { paramInt2, paramInt1 });
    int n = 0;
    while (n < paramInt2)
    {
      int i = n << 3;
      int j = paramInt4 - 8;
      int i1 = i;
      if (i > j) {
        i1 = j;
      }
      int i2 = 0;
      while (i2 < paramInt1)
      {
        j = i2 << 3;
        int k = paramInt3 - 8;
        i = j;
        if (j > k) {
          i = k;
        }
        j = 0;
        int i4 = 255;
        int i3 = 0;
        k = 0;
        int m = i1 * paramInt3 + i;
        i = j;
        j = m;
        while (k < 8)
        {
          m = 0;
          while (m < 8)
          {
            i5 = paramArrayOfByte[(j + m)] & 0xFF;
            i6 = i + i5;
            i = i4;
            if (i5 < i4) {
              i = i5;
            }
            i4 = i3;
            if (i5 > i3) {
              i4 = i5;
            }
            m += 1;
            i3 = i4;
            i4 = i;
            i = i6;
          }
          int i5 = j;
          m = i;
          int i6 = k;
          if (i3 - i4 > 24)
          {
            k += 1;
            m = j + paramInt3;
            j = k;
            k = i;
            i = m;
            for (;;)
            {
              i5 = i;
              m = k;
              i6 = j;
              if (j >= 8) {
                break;
              }
              m = 0;
              while (m < 8)
              {
                k += (paramArrayOfByte[(i + m)] & 0xFF);
                m += 1;
              }
              j += 1;
              i += paramInt3;
            }
          }
          k = i6 + 1;
          j = i5 + paramInt3;
          i = m;
        }
        i >>= 6;
        if (i3 - i4 <= 24)
        {
          j = i4 / 2;
          i = j;
          if (n > 0)
          {
            i = j;
            if (i2 > 0)
            {
              k = (arrayOfInt[(n - 1)][i2] + arrayOfInt[n][(i2 - 1)] * 2 + arrayOfInt[(n - 1)][(i2 - 1)]) / 4;
              i = j;
              if (i4 < k) {
                i = k;
              }
            }
          }
        }
        arrayOfInt[n][i2] = i;
        i2 += 1;
      }
      n += 1;
    }
    return arrayOfInt;
  }
  
  private static void calculateThresholdForBlock(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[][] paramArrayOfInt, BitMatrix paramBitMatrix)
  {
    int i = 0;
    while (i < paramInt2)
    {
      int k = i << 3;
      int m = paramInt4 - 8;
      int j = k;
      if (k > m) {
        j = m;
      }
      k = 0;
      while (k < paramInt1)
      {
        int n = k << 3;
        int i1 = paramInt3 - 8;
        m = n;
        if (n > i1) {
          m = i1;
        }
        int i2 = cap(k, 2, paramInt1 - 3);
        int i3 = cap(i, 2, paramInt2 - 3);
        i1 = 0;
        n = -2;
        while (n <= 2)
        {
          int[] arrayOfInt = paramArrayOfInt[(i3 + n)];
          i1 += arrayOfInt[(i2 - 2)] + arrayOfInt[(i2 - 1)] + arrayOfInt[i2] + arrayOfInt[(i2 + 1)] + arrayOfInt[(i2 + 2)];
          n += 1;
        }
        thresholdBlock(paramArrayOfByte, m, j, i1 / 25, paramInt3, paramBitMatrix);
        k += 1;
      }
      i += 1;
    }
  }
  
  private static int cap(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 < paramInt2) {
      return paramInt2;
    }
    if (paramInt1 > paramInt3) {
      return paramInt3;
    }
    return paramInt1;
  }
  
  private static void thresholdBlock(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BitMatrix paramBitMatrix)
  {
    int j = 0;
    int i = paramInt2 * paramInt4 + paramInt1;
    while (j < 8)
    {
      int k = 0;
      while (k < 8)
      {
        if ((paramArrayOfByte[(i + k)] & 0xFF) <= paramInt3) {
          paramBitMatrix.set(paramInt1 + k, paramInt2 + j);
        }
        k += 1;
      }
      j += 1;
      i += paramInt4;
    }
  }
  
  public Binarizer createBinarizer(LuminanceSource paramLuminanceSource)
  {
    return new HybridBinarizer(paramLuminanceSource);
  }
  
  public BitMatrix getBlackMatrix()
    throws NotFoundException
  {
    if (this.matrix != null) {
      return this.matrix;
    }
    Object localObject = getLuminanceSource();
    int m = ((LuminanceSource)localObject).getWidth();
    int n = ((LuminanceSource)localObject).getHeight();
    BitMatrix localBitMatrix;
    if ((m >= 40) && (n >= 40))
    {
      localObject = ((LuminanceSource)localObject).getMatrix();
      int j = m >> 3;
      int i = j;
      if ((m & 0x7) != 0) {
        i = j + 1;
      }
      int k = n >> 3;
      j = k;
      if ((n & 0x7) != 0) {
        j = k + 1;
      }
      int[][] arrayOfInt = calculateBlackPoints((byte[])localObject, i, j, m, n);
      localBitMatrix = new BitMatrix(m, n);
      calculateThresholdForBlock((byte[])localObject, i, j, m, n, arrayOfInt, localBitMatrix);
    }
    for (this.matrix = localBitMatrix;; this.matrix = super.getBlackMatrix()) {
      return this.matrix;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\common\HybridBinarizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */