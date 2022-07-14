package com.google.zxing.pdf417.detector;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class Detector
{
  private static final int BARCODE_MIN_HEIGHT = 10;
  private static final int[] INDEXES_START_PATTERN = { 0, 4, 1, 5 };
  private static final int[] INDEXES_STOP_PATTERN = { 6, 2, 7, 3 };
  private static final float MAX_AVG_VARIANCE = 0.42F;
  private static final float MAX_INDIVIDUAL_VARIANCE = 0.8F;
  private static final int MAX_PATTERN_DRIFT = 5;
  private static final int MAX_PIXEL_DRIFT = 3;
  private static final int ROW_STEP = 5;
  private static final int SKIPPED_ROW_COUNT_MAX = 25;
  private static final int[] START_PATTERN = { 8, 1, 1, 1, 1, 1, 1, 3 };
  private static final int[] STOP_PATTERN = { 7, 1, 1, 3, 1, 1, 1, 2, 1 };
  
  private static void copyToResult(ResultPoint[] paramArrayOfResultPoint1, ResultPoint[] paramArrayOfResultPoint2, int[] paramArrayOfInt)
  {
    int i = 0;
    while (i < paramArrayOfInt.length)
    {
      paramArrayOfResultPoint1[paramArrayOfInt[i]] = paramArrayOfResultPoint2[i];
      i += 1;
    }
  }
  
  public static PDF417DetectorResult detect(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap, boolean paramBoolean)
    throws NotFoundException
  {
    BitMatrix localBitMatrix = paramBinaryBitmap.getBlackMatrix();
    List localList = detect(paramBoolean, localBitMatrix);
    paramMap = localList;
    paramBinaryBitmap = localBitMatrix;
    if (localList.isEmpty())
    {
      paramBinaryBitmap = localBitMatrix.clone();
      paramBinaryBitmap.rotate180();
      paramMap = detect(paramBoolean, paramBinaryBitmap);
    }
    return new PDF417DetectorResult(paramBinaryBitmap, paramMap);
  }
  
  private static List<ResultPoint[]> detect(boolean paramBoolean, BitMatrix paramBitMatrix)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    int j = 0;
    int k = 0;
    for (;;)
    {
      Object localObject;
      if (i < paramBitMatrix.getHeight())
      {
        localObject = findVertices(paramBitMatrix, i, j);
        if ((localObject[0] != null) || (localObject[3] != null)) {
          break label149;
        }
        if (k != 0) {
          break label54;
        }
      }
      label54:
      label149:
      do
      {
        return localArrayList;
        k = 0;
        int m = 0;
        localObject = localArrayList.iterator();
        while (((Iterator)localObject).hasNext())
        {
          ResultPoint[] arrayOfResultPoint = (ResultPoint[])((Iterator)localObject).next();
          j = i;
          if (arrayOfResultPoint[1] != null) {
            j = (int)Math.max(i, arrayOfResultPoint[1].getY());
          }
          i = j;
          if (arrayOfResultPoint[3] != null) {
            i = Math.max(j, (int)arrayOfResultPoint[3].getY());
          }
        }
        i += 5;
        j = m;
        break;
        k = 1;
        localArrayList.add(localObject);
      } while (!paramBoolean);
      if (localObject[2] != null)
      {
        j = (int)localObject[2].getX();
        i = (int)localObject[2].getY();
      }
      else
      {
        j = (int)localObject[4].getX();
        i = (int)localObject[4].getY();
      }
    }
  }
  
  private static int[] findGuardPattern(BitMatrix paramBitMatrix, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    Arrays.fill(paramArrayOfInt2, 0, paramArrayOfInt2.length, 0);
    int i = 0;
    while ((paramBitMatrix.get(paramInt1, paramInt2)) && (paramInt1 > 0) && (i < 3))
    {
      paramInt1 -= 1;
      i += 1;
    }
    int j = paramInt1;
    i = 0;
    int k = paramArrayOfInt1.length;
    while (j < paramInt3) {
      if ((paramBitMatrix.get(j, paramInt2) ^ paramBoolean))
      {
        paramArrayOfInt2[i] += 1;
        j += 1;
      }
      else
      {
        if (i == k - 1)
        {
          if (patternMatchVariance(paramArrayOfInt2, paramArrayOfInt1, 0.8F) < 0.42F) {
            return new int[] { paramInt1, j };
          }
          paramInt1 += paramArrayOfInt2[0] + paramArrayOfInt2[1];
          System.arraycopy(paramArrayOfInt2, 2, paramArrayOfInt2, 0, k - 2);
          paramArrayOfInt2[(k - 2)] = 0;
          paramArrayOfInt2[(k - 1)] = 0;
          i -= 1;
          label180:
          paramArrayOfInt2[i] = 1;
          if (paramBoolean) {
            break label206;
          }
        }
        label206:
        for (paramBoolean = true;; paramBoolean = false)
        {
          break;
          i += 1;
          break label180;
        }
      }
    }
    if ((i == k - 1) && (patternMatchVariance(paramArrayOfInt2, paramArrayOfInt1, 0.8F) < 0.42F)) {
      return new int[] { paramInt1, j - 1 };
    }
    return null;
  }
  
  private static ResultPoint[] findRowsWithPattern(BitMatrix paramBitMatrix, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
  {
    ResultPoint[] arrayOfResultPoint = new ResultPoint[4];
    int k = 0;
    int[] arrayOfInt2 = new int[paramArrayOfInt.length];
    int j = k;
    int i = paramInt3;
    Object localObject;
    int[] arrayOfInt1;
    if (paramInt3 < paramInt1)
    {
      localObject = findGuardPattern(paramBitMatrix, paramInt4, paramInt3, paramInt2, false, paramArrayOfInt, arrayOfInt2);
      if (localObject != null)
      {
        i = paramInt3;
        for (;;)
        {
          paramInt3 = i;
          if (i <= 0) {
            break label98;
          }
          i -= 1;
          arrayOfInt1 = findGuardPattern(paramBitMatrix, paramInt4, i, paramInt2, false, paramArrayOfInt, arrayOfInt2);
          if (arrayOfInt1 == null) {
            break;
          }
          localObject = arrayOfInt1;
        }
        paramInt3 = i + 1;
        label98:
        arrayOfResultPoint[0] = new ResultPoint(localObject[0], paramInt3);
        arrayOfResultPoint[1] = new ResultPoint(localObject[1], paramInt3);
        j = 1;
        i = paramInt3;
      }
    }
    else
    {
      paramInt3 = i + 1;
      paramInt4 = paramInt3;
      if (j == 0) {
        break label328;
      }
      j = 0;
      localObject = new int[2];
      localObject[0] = ((int)arrayOfResultPoint[0].getX());
      localObject[1] = ((int)arrayOfResultPoint[1].getX());
      paramInt4 = paramInt3;
      label188:
      if (paramInt4 >= paramInt1) {
        break label281;
      }
      arrayOfInt1 = findGuardPattern(paramBitMatrix, localObject[0], paramInt4, paramInt2, false, paramArrayOfInt, arrayOfInt2);
      if ((arrayOfInt1 == null) || (Math.abs(localObject[0] - arrayOfInt1[0]) >= 5) || (Math.abs(localObject[1] - arrayOfInt1[1]) >= 5)) {
        break label274;
      }
      localObject = arrayOfInt1;
    }
    for (paramInt3 = 0;; paramInt3 = j + 1)
    {
      paramInt4 += 1;
      j = paramInt3;
      break label188;
      paramInt3 += 5;
      break;
      label274:
      if (j > 25)
      {
        label281:
        paramInt4 -= j + 1;
        arrayOfResultPoint[2] = new ResultPoint(localObject[0], paramInt4);
        arrayOfResultPoint[3] = new ResultPoint(localObject[1], paramInt4);
        label328:
        if (paramInt4 - i >= 10) {
          break label367;
        }
        paramInt1 = 0;
        while (paramInt1 < arrayOfResultPoint.length)
        {
          arrayOfResultPoint[paramInt1] = null;
          paramInt1 += 1;
        }
      }
    }
    label367:
    return arrayOfResultPoint;
  }
  
  private static ResultPoint[] findVertices(BitMatrix paramBitMatrix, int paramInt1, int paramInt2)
  {
    int i = paramBitMatrix.getHeight();
    int j = paramBitMatrix.getWidth();
    ResultPoint[] arrayOfResultPoint = new ResultPoint[8];
    copyToResult(arrayOfResultPoint, findRowsWithPattern(paramBitMatrix, i, j, paramInt1, paramInt2, START_PATTERN), INDEXES_START_PATTERN);
    if (arrayOfResultPoint[4] != null)
    {
      paramInt2 = (int)arrayOfResultPoint[4].getX();
      paramInt1 = (int)arrayOfResultPoint[4].getY();
    }
    copyToResult(arrayOfResultPoint, findRowsWithPattern(paramBitMatrix, i, j, paramInt1, paramInt2, STOP_PATTERN), INDEXES_STOP_PATTERN);
    return arrayOfResultPoint;
  }
  
  private static float patternMatchVariance(int[] paramArrayOfInt1, int[] paramArrayOfInt2, float paramFloat)
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
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\pdf417\detector\Detector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */