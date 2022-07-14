package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public final class Code39Reader
  extends OneDReader
{
  static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
  static final int ASTERISK_ENCODING = CHARACTER_ENCODINGS[39];
  static final int[] CHARACTER_ENCODINGS = { 52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, 193, 448, 145, 400, 208, 133, 388, 196, 148, 168, 162, 138, 42 };
  private static final String CHECK_DIGIT_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%";
  private final int[] counters;
  private final StringBuilder decodeRowResult;
  private final boolean extendedMode;
  private final boolean usingCheckDigit;
  
  public Code39Reader()
  {
    this(false);
  }
  
  public Code39Reader(boolean paramBoolean)
  {
    this(paramBoolean, false);
  }
  
  public Code39Reader(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.usingCheckDigit = paramBoolean1;
    this.extendedMode = paramBoolean2;
    this.decodeRowResult = new StringBuilder(20);
    this.counters = new int[9];
  }
  
  private static String decodeExtended(CharSequence paramCharSequence)
    throws FormatException
  {
    int j = paramCharSequence.length();
    StringBuilder localStringBuilder = new StringBuilder(j);
    int i = 0;
    if (i < j)
    {
      char c2 = paramCharSequence.charAt(i);
      int k;
      char c1;
      if ((c2 == '+') || (c2 == '$') || (c2 == '%') || (c2 == '/'))
      {
        k = paramCharSequence.charAt(i + 1);
        c1 = '\000';
        switch (c2)
        {
        default: 
          label116:
          localStringBuilder.append(c1);
          i += 1;
        }
      }
      for (;;)
      {
        i += 1;
        break;
        if ((k >= 65) && (k <= 90))
        {
          c1 = (char)(k + 32);
          break label116;
        }
        throw FormatException.getFormatInstance();
        if ((k >= 65) && (k <= 90))
        {
          c1 = (char)(k - 64);
          break label116;
        }
        throw FormatException.getFormatInstance();
        if ((k >= 65) && (k <= 69))
        {
          c1 = (char)(k - 38);
          break label116;
        }
        if ((k >= 70) && (k <= 87))
        {
          c1 = (char)(k - 11);
          break label116;
        }
        throw FormatException.getFormatInstance();
        if ((k >= 65) && (k <= 79))
        {
          c1 = (char)(k - 32);
          break label116;
        }
        if (k == 90)
        {
          c1 = ':';
          break label116;
        }
        throw FormatException.getFormatInstance();
        localStringBuilder.append(c2);
      }
    }
    return localStringBuilder.toString();
  }
  
  private static int[] findAsteriskPattern(BitArray paramBitArray, int[] paramArrayOfInt)
    throws NotFoundException
  {
    int n = paramBitArray.getSize();
    int m = paramBitArray.getNextSet(0);
    int j = 0;
    int i = m;
    int k = 0;
    int i1 = paramArrayOfInt.length;
    while (m < n) {
      if ((paramBitArray.get(m) ^ k))
      {
        paramArrayOfInt[j] += 1;
        m += 1;
      }
      else
      {
        if (j == i1 - 1)
        {
          if ((toNarrowWidePattern(paramArrayOfInt) == ASTERISK_ENCODING) && (paramBitArray.isRange(Math.max(0, i - (m - i) / 2), i, false))) {
            return new int[] { i, m };
          }
          i += paramArrayOfInt[0] + paramArrayOfInt[1];
          System.arraycopy(paramArrayOfInt, 2, paramArrayOfInt, 0, i1 - 2);
          paramArrayOfInt[(i1 - 2)] = 0;
          paramArrayOfInt[(i1 - 1)] = 0;
          j -= 1;
          label152:
          paramArrayOfInt[j] = 1;
          if (k != 0) {
            break label174;
          }
        }
        label174:
        for (k = 1;; k = 0)
        {
          break;
          j += 1;
          break label152;
        }
      }
    }
    throw NotFoundException.getNotFoundInstance();
  }
  
  private static char patternToChar(int paramInt)
    throws NotFoundException
  {
    int i = 0;
    while (i < CHARACTER_ENCODINGS.length)
    {
      if (CHARACTER_ENCODINGS[i] == paramInt) {
        return "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".charAt(i);
      }
      i += 1;
    }
    throw NotFoundException.getNotFoundInstance();
  }
  
  private static int toNarrowWidePattern(int[] paramArrayOfInt)
  {
    int i4 = paramArrayOfInt.length;
    int k = 0;
    int j;
    label210:
    do
    {
      int i = Integer.MAX_VALUE;
      int i1 = paramArrayOfInt.length;
      j = 0;
      while (j < i1)
      {
        n = paramArrayOfInt[j];
        m = i;
        if (n < i)
        {
          m = i;
          if (n > k) {
            m = n;
          }
        }
        j += 1;
        i = m;
      }
      k = i;
      j = 0;
      int m = 0;
      i = 0;
      int n = 0;
      int i2;
      while (n < i4)
      {
        int i5 = paramArrayOfInt[n];
        int i3 = i;
        i2 = m;
        i1 = j;
        if (i5 > k)
        {
          i3 = i | 1 << i4 - 1 - n;
          i1 = j + 1;
          i2 = m + i5;
        }
        n += 1;
        i = i3;
        m = i2;
        j = i1;
      }
      if (j == 3)
      {
        i1 = 0;
        n = j;
        j = i1;
        for (;;)
        {
          i1 = i;
          if (j < i4)
          {
            i1 = i;
            if (n > 0)
            {
              i2 = paramArrayOfInt[j];
              i1 = n;
              if (i2 <= k) {
                break label210;
              }
              i1 = n - 1;
              if (i2 * 2 < m) {
                break label210;
              }
              i1 = -1;
            }
          }
          return i1;
          j += 1;
          n = i1;
        }
      }
    } while (j > 3);
    return -1;
  }
  
  public Result decodeRow(int paramInt, BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException, ChecksumException, FormatException
  {
    Object localObject1 = this.counters;
    Arrays.fill((int[])localObject1, 0);
    Object localObject2 = this.decodeRowResult;
    ((StringBuilder)localObject2).setLength(0);
    paramMap = findAsteriskPattern(paramBitArray, (int[])localObject1);
    int i = paramBitArray.getNextSet(paramMap[1]);
    int n = paramBitArray.getSize();
    int j;
    char c;
    int m;
    do
    {
      j = i;
      recordPattern(paramBitArray, j, (int[])localObject1);
      i = toNarrowWidePattern((int[])localObject1);
      if (i < 0) {
        throw NotFoundException.getNotFoundInstance();
      }
      c = patternToChar(i);
      ((StringBuilder)localObject2).append(c);
      m = localObject1.length;
      k = 0;
      i = j;
      while (k < m)
      {
        i += localObject1[k];
        k += 1;
      }
      m = paramBitArray.getNextSet(i);
      i = m;
    } while (c != '*');
    ((StringBuilder)localObject2).setLength(((StringBuilder)localObject2).length() - 1);
    i = 0;
    int i1 = localObject1.length;
    int k = 0;
    while (k < i1)
    {
      i += localObject1[k];
      k += 1;
    }
    if ((m != n) && ((m - j - i) * 2 < i)) {
      throw NotFoundException.getNotFoundInstance();
    }
    if (this.usingCheckDigit)
    {
      n = ((StringBuilder)localObject2).length() - 1;
      m = 0;
      k = 0;
      while (k < n)
      {
        m += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(this.decodeRowResult.charAt(k));
        k += 1;
      }
      if (((StringBuilder)localObject2).charAt(n) != "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".charAt(m % 43)) {
        throw ChecksumException.getChecksumInstance();
      }
      ((StringBuilder)localObject2).setLength(n);
    }
    if (((StringBuilder)localObject2).length() == 0) {
      throw NotFoundException.getNotFoundInstance();
    }
    if (this.extendedMode) {}
    for (paramBitArray = decodeExtended((CharSequence)localObject2);; paramBitArray = ((StringBuilder)localObject2).toString())
    {
      float f1 = (paramMap[1] + paramMap[0]) / 2.0F;
      float f2 = j;
      float f3 = i / 2.0F;
      paramMap = new ResultPoint(f1, paramInt);
      localObject1 = new ResultPoint(f2 + f3, paramInt);
      localObject2 = BarcodeFormat.CODE_39;
      return new Result(paramBitArray, null, new ResultPoint[] { paramMap, localObject1 }, (BarcodeFormat)localObject2);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\Code39Reader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */