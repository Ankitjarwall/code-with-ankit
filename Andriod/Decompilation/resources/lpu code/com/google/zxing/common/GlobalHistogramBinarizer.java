package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;

public class GlobalHistogramBinarizer
  extends Binarizer
{
  private static final byte[] EMPTY = new byte[0];
  private static final int LUMINANCE_BITS = 5;
  private static final int LUMINANCE_BUCKETS = 32;
  private static final int LUMINANCE_SHIFT = 3;
  private final int[] buckets = new int[32];
  private byte[] luminances = EMPTY;
  
  public GlobalHistogramBinarizer(LuminanceSource paramLuminanceSource)
  {
    super(paramLuminanceSource);
  }
  
  private static int estimateBlackPoint(int[] paramArrayOfInt)
    throws NotFoundException
  {
    int i3 = paramArrayOfInt.length;
    int m = 0;
    int i = 0;
    int n = 0;
    int j = 0;
    int i1;
    while (j < i3)
    {
      k = n;
      if (paramArrayOfInt[j] > n)
      {
        i = j;
        k = paramArrayOfInt[j];
      }
      i1 = m;
      if (paramArrayOfInt[j] > m) {
        i1 = paramArrayOfInt[j];
      }
      j += 1;
      n = k;
      m = i1;
    }
    j = 0;
    n = 0;
    int k = 0;
    while (k < i3)
    {
      i1 = k - i;
      i2 = paramArrayOfInt[k] * i1 * i1;
      i1 = n;
      if (i2 > n)
      {
        j = k;
        i1 = i2;
      }
      k += 1;
      n = i1;
    }
    n = i;
    k = j;
    if (i > j)
    {
      k = i;
      n = j;
    }
    if (k - n <= i3 / 16) {
      throw NotFoundException.getNotFoundInstance();
    }
    int i2 = k - 1;
    j = -1;
    i = k - 1;
    while (i > n)
    {
      i1 = i - n;
      i3 = i1 * i1 * (k - i) * (m - paramArrayOfInt[i]);
      i1 = j;
      if (i3 > j)
      {
        i2 = i;
        i1 = i3;
      }
      i -= 1;
      j = i1;
    }
    return i2 << 3;
  }
  
  private void initArrays(int paramInt)
  {
    if (this.luminances.length < paramInt) {
      this.luminances = new byte[paramInt];
    }
    paramInt = 0;
    while (paramInt < 32)
    {
      this.buckets[paramInt] = 0;
      paramInt += 1;
    }
  }
  
  public Binarizer createBinarizer(LuminanceSource paramLuminanceSource)
  {
    return new GlobalHistogramBinarizer(paramLuminanceSource);
  }
  
  public BitMatrix getBlackMatrix()
    throws NotFoundException
  {
    Object localObject = getLuminanceSource();
    int k = ((LuminanceSource)localObject).getWidth();
    int m = ((LuminanceSource)localObject).getHeight();
    BitMatrix localBitMatrix = new BitMatrix(k, m);
    initArrays(k);
    int[] arrayOfInt = this.buckets;
    int i = 1;
    int j;
    while (i < 5)
    {
      byte[] arrayOfByte = ((LuminanceSource)localObject).getRow(m * i / 5, this.luminances);
      n = k * 4 / 5;
      j = k / 5;
      while (j < n)
      {
        int i1 = (arrayOfByte[j] & 0xFF) >> 3;
        arrayOfInt[i1] += 1;
        j += 1;
      }
      i += 1;
    }
    int n = estimateBlackPoint(arrayOfInt);
    localObject = ((LuminanceSource)localObject).getMatrix();
    i = 0;
    while (i < m)
    {
      j = 0;
      while (j < k)
      {
        if ((localObject[(i * k + j)] & 0xFF) < n) {
          localBitMatrix.set(j, i);
        }
        j += 1;
      }
      i += 1;
    }
    return localBitMatrix;
  }
  
  public BitArray getBlackRow(int paramInt, BitArray paramBitArray)
    throws NotFoundException
  {
    Object localObject = getLuminanceSource();
    int m = ((LuminanceSource)localObject).getWidth();
    if ((paramBitArray == null) || (paramBitArray.getSize() < m)) {
      paramBitArray = new BitArray(m);
    }
    int[] arrayOfInt;
    for (;;)
    {
      initArrays(m);
      localObject = ((LuminanceSource)localObject).getRow(paramInt, this.luminances);
      arrayOfInt = this.buckets;
      paramInt = 0;
      while (paramInt < m)
      {
        i = (localObject[paramInt] & 0xFF) >> 3;
        arrayOfInt[i] += 1;
        paramInt += 1;
      }
      paramBitArray.clear();
    }
    int n = estimateBlackPoint(arrayOfInt);
    if (m < 3)
    {
      paramInt = 0;
      while (paramInt < m)
      {
        if ((localObject[paramInt] & 0xFF) < n) {
          paramBitArray.set(paramInt);
        }
        paramInt += 1;
      }
    }
    int j = localObject[0] & 0xFF;
    paramInt = localObject[1] & 0xFF;
    int i = 1;
    while (i < m - 1)
    {
      int k = localObject[(i + 1)] & 0xFF;
      if ((paramInt * 4 - j - k) / 2 < n) {
        paramBitArray.set(i);
      }
      j = paramInt;
      paramInt = k;
      i += 1;
    }
    return paramBitArray;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\common\GlobalHistogramBinarizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */