package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;

public final class Encoder
{
  public static final int DEFAULT_AZTEC_LAYERS = 0;
  public static final int DEFAULT_EC_PERCENT = 33;
  private static final int MAX_NB_BITS = 32;
  private static final int MAX_NB_BITS_COMPACT = 4;
  private static final int[] WORD_SIZE = { 4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12 };
  
  private static int[] bitsToWords(BitArray paramBitArray, int paramInt1, int paramInt2)
  {
    int[] arrayOfInt = new int[paramInt2];
    paramInt2 = 0;
    int m = paramBitArray.getSize() / paramInt1;
    while (paramInt2 < m)
    {
      int j = 0;
      int i = 0;
      if (i < paramInt1)
      {
        if (paramBitArray.get(paramInt2 * paramInt1 + i)) {}
        for (int k = 1 << paramInt1 - i - 1;; k = 0)
        {
          j |= k;
          i += 1;
          break;
        }
      }
      arrayOfInt[paramInt2] = j;
      paramInt2 += 1;
    }
    return arrayOfInt;
  }
  
  private static void drawBullsEye(BitMatrix paramBitMatrix, int paramInt1, int paramInt2)
  {
    int i = 0;
    while (i < paramInt2)
    {
      int j = paramInt1 - i;
      while (j <= paramInt1 + i)
      {
        paramBitMatrix.set(j, paramInt1 - i);
        paramBitMatrix.set(j, paramInt1 + i);
        paramBitMatrix.set(paramInt1 - i, j);
        paramBitMatrix.set(paramInt1 + i, j);
        j += 1;
      }
      i += 2;
    }
    paramBitMatrix.set(paramInt1 - paramInt2, paramInt1 - paramInt2);
    paramBitMatrix.set(paramInt1 - paramInt2 + 1, paramInt1 - paramInt2);
    paramBitMatrix.set(paramInt1 - paramInt2, paramInt1 - paramInt2 + 1);
    paramBitMatrix.set(paramInt1 + paramInt2, paramInt1 - paramInt2);
    paramBitMatrix.set(paramInt1 + paramInt2, paramInt1 - paramInt2 + 1);
    paramBitMatrix.set(paramInt1 + paramInt2, paramInt1 + paramInt2 - 1);
  }
  
  private static void drawModeMessage(BitMatrix paramBitMatrix, boolean paramBoolean, int paramInt, BitArray paramBitArray)
  {
    int i = paramInt / 2;
    int j;
    if (paramBoolean)
    {
      paramInt = 0;
      while (paramInt < 7)
      {
        j = i - 3 + paramInt;
        if (paramBitArray.get(paramInt)) {
          paramBitMatrix.set(j, i - 5);
        }
        if (paramBitArray.get(paramInt + 7)) {
          paramBitMatrix.set(i + 5, j);
        }
        if (paramBitArray.get(20 - paramInt)) {
          paramBitMatrix.set(j, i + 5);
        }
        if (paramBitArray.get(27 - paramInt)) {
          paramBitMatrix.set(i - 5, j);
        }
        paramInt += 1;
      }
    }
    paramInt = 0;
    while (paramInt < 10)
    {
      j = i - 5 + paramInt + paramInt / 5;
      if (paramBitArray.get(paramInt)) {
        paramBitMatrix.set(j, i - 7);
      }
      if (paramBitArray.get(paramInt + 10)) {
        paramBitMatrix.set(i + 7, j);
      }
      if (paramBitArray.get(29 - paramInt)) {
        paramBitMatrix.set(j, i + 7);
      }
      if (paramBitArray.get(39 - paramInt)) {
        paramBitMatrix.set(i - 7, j);
      }
      paramInt += 1;
    }
  }
  
  public static AztecCode encode(byte[] paramArrayOfByte)
  {
    return encode(paramArrayOfByte, 33, 0);
  }
  
  public static AztecCode encode(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    BitArray localBitArray = new HighLevelEncoder(paramArrayOfByte).encode();
    int n = localBitArray.getSize() * paramInt1 / 100 + 11;
    int m = localBitArray.getSize();
    boolean bool1;
    label95:
    label101:
    boolean bool2;
    int i;
    if (paramInt2 != 0)
    {
      if (paramInt2 < 0)
      {
        bool1 = true;
        j = Math.abs(paramInt2);
        if (!bool1) {
          break label95;
        }
      }
      for (paramInt1 = 4;; paramInt1 = 32)
      {
        if (j <= paramInt1) {
          break label101;
        }
        throw new IllegalArgumentException(String.format("Illegal value %s for layers", new Object[] { Integer.valueOf(paramInt2) }));
        bool1 = false;
        break;
      }
      k = totalBitsInLayer(j, bool1);
      m = WORD_SIZE[j];
      localObject = stuffBits(localBitArray, m);
      if (((BitArray)localObject).getSize() + n > k - k % m) {
        throw new IllegalArgumentException("Data to large for user specified layer");
      }
      bool2 = bool1;
      i = j;
      paramArrayOfByte = (byte[])localObject;
      paramInt2 = k;
      paramInt1 = m;
      if (bool1)
      {
        bool2 = bool1;
        i = j;
        paramArrayOfByte = (byte[])localObject;
        paramInt2 = k;
        paramInt1 = m;
        if (((BitArray)localObject).getSize() > m * 64) {
          throw new IllegalArgumentException("Data to large for user specified layer");
        }
      }
    }
    else
    {
      j = 0;
      paramArrayOfByte = null;
      paramInt2 = 0;
      if (paramInt2 > 32) {
        throw new IllegalArgumentException("Data too large for an Aztec code");
      }
      if (paramInt2 <= 3)
      {
        bool1 = true;
        label247:
        if (!bool1) {
          break label293;
        }
        i = paramInt2 + 1;
        label256:
        k = totalBitsInLayer(i, bool1);
        if (m + n <= k) {
          break label298;
        }
        localObject = paramArrayOfByte;
      }
      label293:
      label298:
      label346:
      do
      {
        do
        {
          paramInt2 += 1;
          paramArrayOfByte = (byte[])localObject;
          break;
          bool1 = false;
          break label247;
          i = paramInt2;
          break label256;
          paramInt1 = j;
          if (j != WORD_SIZE[i])
          {
            paramInt1 = WORD_SIZE[i];
            paramArrayOfByte = stuffBits(localBitArray, paramInt1);
          }
          if (!bool1) {
            break label346;
          }
          localObject = paramArrayOfByte;
          j = paramInt1;
        } while (paramArrayOfByte.getSize() > paramInt1 * 64);
        localObject = paramArrayOfByte;
        j = paramInt1;
      } while (paramArrayOfByte.getSize() + n > k - k % paramInt1);
      paramInt2 = k;
      bool2 = bool1;
    }
    Object localObject = generateCheckWords(paramArrayOfByte, paramInt2, paramInt1);
    int i1 = paramArrayOfByte.getSize() / paramInt1;
    localBitArray = generateModeMessage(bool2, i, i1);
    if (bool2) {}
    int[] arrayOfInt;
    for (paramInt1 = 11;; paramInt1 = 14)
    {
      n = paramInt1 + i * 4;
      arrayOfInt = new int[n];
      if (!bool2) {
        break;
      }
      paramInt2 = n;
      j = 0;
      for (;;)
      {
        paramInt1 = paramInt2;
        if (j >= arrayOfInt.length) {
          break;
        }
        arrayOfInt[j] = j;
        j += 1;
      }
    }
    int j = n + 1 + (n / 2 - 1) / 15 * 2;
    int k = n / 2;
    m = j / 2;
    paramInt2 = 0;
    for (;;)
    {
      paramInt1 = j;
      if (paramInt2 >= k) {
        break;
      }
      paramInt1 = paramInt2 + paramInt2 / 15;
      arrayOfInt[(k - paramInt2 - 1)] = (m - paramInt1 - 1);
      arrayOfInt[(k + paramInt2)] = (m + paramInt1 + 1);
      paramInt2 += 1;
    }
    paramArrayOfByte = new BitMatrix(paramInt1);
    paramInt2 = 0;
    j = 0;
    while (paramInt2 < i)
    {
      int i2;
      if (bool2)
      {
        k = 9;
        i2 = (i - paramInt2) * 4 + k;
        k = 0;
      }
      for (;;)
      {
        if (k >= i2) {
          break label825;
        }
        int i3 = k * 2;
        m = 0;
        for (;;)
        {
          if (m < 2)
          {
            if (((BitArray)localObject).get(j + i3 + m)) {
              paramArrayOfByte.set(arrayOfInt[(paramInt2 * 2 + m)], arrayOfInt[(paramInt2 * 2 + k)]);
            }
            if (((BitArray)localObject).get(i2 * 2 + j + i3 + m)) {
              paramArrayOfByte.set(arrayOfInt[(paramInt2 * 2 + k)], arrayOfInt[(n - 1 - paramInt2 * 2 - m)]);
            }
            if (((BitArray)localObject).get(i2 * 4 + j + i3 + m)) {
              paramArrayOfByte.set(arrayOfInt[(n - 1 - paramInt2 * 2 - m)], arrayOfInt[(n - 1 - paramInt2 * 2 - k)]);
            }
            if (((BitArray)localObject).get(i2 * 6 + j + i3 + m)) {
              paramArrayOfByte.set(arrayOfInt[(n - 1 - paramInt2 * 2 - k)], arrayOfInt[(paramInt2 * 2 + m)]);
            }
            m += 1;
            continue;
            k = 12;
            break;
          }
        }
        k += 1;
      }
      label825:
      j += i2 * 8;
      paramInt2 += 1;
    }
    drawModeMessage(paramArrayOfByte, bool2, paramInt1, localBitArray);
    if (bool2) {
      drawBullsEye(paramArrayOfByte, paramInt1 / 2, 5);
    }
    for (;;)
    {
      localObject = new AztecCode();
      ((AztecCode)localObject).setCompact(bool2);
      ((AztecCode)localObject).setSize(paramInt1);
      ((AztecCode)localObject).setLayers(i);
      ((AztecCode)localObject).setCodeWords(i1);
      ((AztecCode)localObject).setMatrix(paramArrayOfByte);
      return (AztecCode)localObject;
      drawBullsEye(paramArrayOfByte, paramInt1 / 2, 7);
      j = 0;
      paramInt2 = 0;
      while (j < n / 2 - 1)
      {
        k = paramInt1 / 2 & 0x1;
        while (k < paramInt1)
        {
          paramArrayOfByte.set(paramInt1 / 2 - paramInt2, k);
          paramArrayOfByte.set(paramInt1 / 2 + paramInt2, k);
          paramArrayOfByte.set(k, paramInt1 / 2 - paramInt2);
          paramArrayOfByte.set(k, paramInt1 / 2 + paramInt2);
          k += 2;
        }
        j += 15;
        paramInt2 += 16;
      }
    }
  }
  
  private static BitArray generateCheckWords(BitArray paramBitArray, int paramInt1, int paramInt2)
  {
    int i = 0;
    int j = paramBitArray.getSize() / paramInt2;
    Object localObject = new ReedSolomonEncoder(getGF(paramInt2));
    int k = paramInt1 / paramInt2;
    paramBitArray = bitsToWords(paramBitArray, paramInt2, k);
    ((ReedSolomonEncoder)localObject).encode(paramBitArray, k - j);
    localObject = new BitArray();
    ((BitArray)localObject).appendBits(0, paramInt1 % paramInt2);
    j = paramBitArray.length;
    paramInt1 = i;
    while (paramInt1 < j)
    {
      ((BitArray)localObject).appendBits(paramBitArray[paramInt1], paramInt2);
      paramInt1 += 1;
    }
    return (BitArray)localObject;
  }
  
  static BitArray generateModeMessage(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    BitArray localBitArray = new BitArray();
    if (paramBoolean)
    {
      localBitArray.appendBits(paramInt1 - 1, 2);
      localBitArray.appendBits(paramInt2 - 1, 6);
      return generateCheckWords(localBitArray, 28, 4);
    }
    localBitArray.appendBits(paramInt1 - 1, 5);
    localBitArray.appendBits(paramInt2 - 1, 11);
    return generateCheckWords(localBitArray, 40, 4);
  }
  
  private static GenericGF getGF(int paramInt)
  {
    switch (paramInt)
    {
    case 5: 
    case 7: 
    case 9: 
    case 11: 
    default: 
      throw new IllegalArgumentException("Unsupported word size " + paramInt);
    case 4: 
      return GenericGF.AZTEC_PARAM;
    case 6: 
      return GenericGF.AZTEC_DATA_6;
    case 8: 
      return GenericGF.AZTEC_DATA_8;
    case 10: 
      return GenericGF.AZTEC_DATA_10;
    }
    return GenericGF.AZTEC_DATA_12;
  }
  
  static BitArray stuffBits(BitArray paramBitArray, int paramInt)
  {
    BitArray localBitArray = new BitArray();
    int n = paramBitArray.getSize();
    int i1 = (1 << paramInt) - 2;
    int i = 0;
    if (i < n)
    {
      int k = 0;
      int j = 0;
      while (j < paramInt)
      {
        int m;
        if (i + j < n)
        {
          m = k;
          if (!paramBitArray.get(i + j)) {}
        }
        else
        {
          m = k | 1 << paramInt - 1 - j;
        }
        j += 1;
        k = m;
      }
      if ((k & i1) == i1)
      {
        localBitArray.appendBits(k & i1, paramInt);
        i -= 1;
      }
      for (;;)
      {
        i += paramInt;
        break;
        if ((k & i1) == 0)
        {
          localBitArray.appendBits(k | 0x1, paramInt);
          i -= 1;
        }
        else
        {
          localBitArray.appendBits(k, paramInt);
        }
      }
    }
    return localBitArray;
  }
  
  private static int totalBitsInLayer(int paramInt, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 88;; i = 112) {
      return (i + paramInt * 16) * paramInt;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\aztec\encoder\Encoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */