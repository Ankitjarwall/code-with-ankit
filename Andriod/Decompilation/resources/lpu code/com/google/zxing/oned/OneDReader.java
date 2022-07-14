package com.google.zxing.oned;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public abstract class OneDReader
  implements Reader
{
  private Result doDecode(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException
  {
    int i1 = paramBinaryBitmap.getWidth();
    int m = paramBinaryBitmap.getHeight();
    Object localObject1 = new BitArray(i1);
    int i;
    int j;
    label49:
    int i2;
    label67:
    int n;
    if ((paramMap != null) && (paramMap.containsKey(DecodeHintType.TRY_HARDER)))
    {
      i = 1;
      if (i == 0) {
        break label136;
      }
      j = 8;
      i2 = Math.max(1, m >> j);
      if (i == 0) {
        break label142;
      }
      i = m;
      j = 0;
      if (j < i)
      {
        n = (j + 1) / 2;
        if ((j & 0x1) != 0) {
          break label148;
        }
        k = 1;
        label94:
        if (k == 0) {
          break label154;
        }
      }
    }
    label136:
    label142:
    label148:
    label154:
    for (int k = n;; k = -n)
    {
      n = (m >> 1) + i2 * k;
      if ((n >= 0) && (n < m)) {
        break label162;
      }
      throw NotFoundException.getNotFoundInstance();
      i = 0;
      break;
      j = 5;
      break label49;
      i = 15;
      break label67;
      k = 0;
      break label94;
    }
    for (;;)
    {
      try
      {
        label162:
        localObject2 = paramBinaryBitmap.getBlackRow(n, (BitArray)localObject1);
        localObject1 = localObject2;
        k = 0;
        localObject3 = localObject1;
        localObject2 = paramMap;
        if (k < 2)
        {
          localObject2 = paramMap;
          if (k == 1)
          {
            ((BitArray)localObject1).reverse();
            localObject2 = paramMap;
            if (paramMap != null)
            {
              localObject2 = paramMap;
              if (paramMap.containsKey(DecodeHintType.NEED_RESULT_POINT_CALLBACK))
              {
                localObject2 = new EnumMap(DecodeHintType.class);
                ((Map)localObject2).putAll(paramMap);
                ((Map)localObject2).remove(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
              }
            }
          }
        }
      }
      catch (NotFoundException localNotFoundException)
      {
        try
        {
          Object localObject2;
          paramMap = decodeRow(n, (BitArray)localObject1, (Map)localObject2);
          if (k == 1)
          {
            paramMap.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(180));
            localObject3 = paramMap.getResultPoints();
            if (localObject3 != null)
            {
              localObject3[0] = new ResultPoint(i1 - localObject3[0].getX() - 1.0F, localObject3[0].getY());
              localObject3[1] = new ResultPoint(i1 - localObject3[1].getX() - 1.0F, localObject3[1].getY());
            }
          }
          return paramMap;
        }
        catch (ReaderException paramMap)
        {
          Object localObject3;
          Map<DecodeHintType, ?> localMap;
          k += 1;
          paramMap = localMap;
        }
        localNotFoundException = localNotFoundException;
        localMap = paramMap;
        localObject3 = localObject1;
        j += 1;
        localObject1 = localObject3;
        paramMap = localMap;
      }
    }
  }
  
  protected static float patternMatchVariance(int[] paramArrayOfInt1, int[] paramArrayOfInt2, float paramFloat)
  {
    int m = paramArrayOfInt1.length;
    int i = 0;
    int k = 0;
    int j = 0;
    while (j < m)
    {
      i += paramArrayOfInt1[j];
      k += paramArrayOfInt2[j];
      j += 1;
    }
    if (i < k) {}
    float f1;
    label144:
    for (;;)
    {
      return Float.POSITIVE_INFINITY;
      float f3 = i / k;
      f1 = 0.0F;
      j = 0;
      if (j >= m) {
        break;
      }
      k = paramArrayOfInt1[j];
      float f2 = paramArrayOfInt2[j] * f3;
      if (k > f2) {}
      for (f2 = k - f2;; f2 -= k)
      {
        if (f2 > paramFloat * f3) {
          break label144;
        }
        f1 += f2;
        j += 1;
        break;
      }
    }
    return f1 / i;
  }
  
  protected static void recordPattern(BitArray paramBitArray, int paramInt, int[] paramArrayOfInt)
    throws NotFoundException
  {
    int m = paramArrayOfInt.length;
    Arrays.fill(paramArrayOfInt, 0, m, 0);
    int n = paramBitArray.getSize();
    if (paramInt >= n) {
      throw NotFoundException.getNotFoundInstance();
    }
    if (!paramBitArray.get(paramInt)) {}
    int j;
    for (int i = 1;; i = 0)
    {
      k = 0;
      j = i;
      i = paramInt;
      for (paramInt = k;; paramInt = k)
      {
        k = paramInt;
        if (i >= n) {
          break label107;
        }
        if (!(paramBitArray.get(i) ^ j)) {
          break;
        }
        paramArrayOfInt[paramInt] += 1;
        k = paramInt;
        i += 1;
      }
    }
    int k = paramInt + 1;
    if (k == m)
    {
      label107:
      if ((k != m) && ((k != m - 1) || (i != n))) {
        throw NotFoundException.getNotFoundInstance();
      }
    }
    else
    {
      paramArrayOfInt[k] = 1;
      if (j == 0) {}
      for (paramInt = 1;; paramInt = 0)
      {
        j = paramInt;
        break;
      }
    }
  }
  
  protected static void recordPatternInReverse(BitArray paramBitArray, int paramInt, int[] paramArrayOfInt)
    throws NotFoundException
  {
    int i = paramArrayOfInt.length;
    boolean bool = paramBitArray.get(paramInt);
    while ((paramInt > 0) && (i >= 0))
    {
      int j = paramInt - 1;
      paramInt = j;
      if (paramBitArray.get(j) != bool)
      {
        i -= 1;
        if (!bool) {}
        for (bool = true;; bool = false)
        {
          paramInt = j;
          break;
        }
      }
    }
    if (i >= 0) {
      throw NotFoundException.getNotFoundInstance();
    }
    recordPattern(paramBitArray, paramInt + 1, paramArrayOfInt);
  }
  
  public Result decode(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException, FormatException
  {
    return decode(paramBinaryBitmap, null);
  }
  
  public Result decode(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException, FormatException
  {
    try
    {
      Result localResult = doDecode(paramBinaryBitmap, paramMap);
      paramBinaryBitmap = localResult;
    }
    catch (NotFoundException localNotFoundException)
    {
      if ((paramMap == null) || (!paramMap.containsKey(DecodeHintType.TRY_HARDER))) {
        break label194;
      }
      BinaryBitmap localBinaryBitmap;
      label194:
      for (int i = 1;; i = 0)
      {
        if ((i == 0) || (!paramBinaryBitmap.isRotateSupported())) {
          break label199;
        }
        localBinaryBitmap = paramBinaryBitmap.rotateCounterClockwise();
        paramMap = doDecode(localBinaryBitmap, paramMap);
        paramBinaryBitmap = paramMap.getResultMetadata();
        int j = 270;
        i = j;
        if (paramBinaryBitmap != null)
        {
          i = j;
          if (paramBinaryBitmap.containsKey(ResultMetadataType.ORIENTATION)) {
            i = (((Integer)paramBinaryBitmap.get(ResultMetadataType.ORIENTATION)).intValue() + 270) % 360;
          }
        }
        paramMap.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(i));
        ResultPoint[] arrayOfResultPoint = paramMap.getResultPoints();
        paramBinaryBitmap = paramMap;
        if (arrayOfResultPoint == null) {
          break;
        }
        j = localBinaryBitmap.getHeight();
        i = 0;
        for (;;)
        {
          paramBinaryBitmap = paramMap;
          if (i >= arrayOfResultPoint.length) {
            break;
          }
          arrayOfResultPoint[i] = new ResultPoint(j - arrayOfResultPoint[i].getY() - 1.0F, arrayOfResultPoint[i].getX());
          i += 1;
        }
      }
      label199:
      throw localBinaryBitmap;
    }
    return paramBinaryBitmap;
  }
  
  public abstract Result decodeRow(int paramInt, BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException, ChecksumException, FormatException;
  
  public void reset() {}
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\OneDReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */