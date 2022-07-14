package com.google.zxing.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class AlignmentPatternFinder
{
  private final int[] crossCheckStateCount;
  private final int height;
  private final BitMatrix image;
  private final float moduleSize;
  private final List<AlignmentPattern> possibleCenters;
  private final ResultPointCallback resultPointCallback;
  private final int startX;
  private final int startY;
  private final int width;
  
  AlignmentPatternFinder(BitMatrix paramBitMatrix, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat, ResultPointCallback paramResultPointCallback)
  {
    this.image = paramBitMatrix;
    this.possibleCenters = new ArrayList(5);
    this.startX = paramInt1;
    this.startY = paramInt2;
    this.width = paramInt3;
    this.height = paramInt4;
    this.moduleSize = paramFloat;
    this.crossCheckStateCount = new int[3];
    this.resultPointCallback = paramResultPointCallback;
  }
  
  private static float centerFromEnd(int[] paramArrayOfInt, int paramInt)
  {
    return paramInt - paramArrayOfInt[2] - paramArrayOfInt[1] / 2.0F;
  }
  
  private float crossCheckVertical(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    BitMatrix localBitMatrix = this.image;
    int j = localBitMatrix.getHeight();
    int[] arrayOfInt = this.crossCheckStateCount;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    int i = paramInt1;
    while ((i >= 0) && (localBitMatrix.get(paramInt2, i)) && (arrayOfInt[1] <= paramInt3))
    {
      arrayOfInt[1] += 1;
      i -= 1;
    }
    if ((i < 0) || (arrayOfInt[1] > paramInt3)) {}
    do
    {
      do
      {
        do
        {
          return NaN.0F;
          while ((i >= 0) && (!localBitMatrix.get(paramInt2, i)) && (arrayOfInt[0] <= paramInt3))
          {
            arrayOfInt[0] += 1;
            i -= 1;
          }
        } while (arrayOfInt[0] > paramInt3);
        paramInt1 += 1;
        while ((paramInt1 < j) && (localBitMatrix.get(paramInt2, paramInt1)) && (arrayOfInt[1] <= paramInt3))
        {
          arrayOfInt[1] += 1;
          paramInt1 += 1;
        }
      } while ((paramInt1 == j) || (arrayOfInt[1] > paramInt3));
      while ((paramInt1 < j) && (!localBitMatrix.get(paramInt2, paramInt1)) && (arrayOfInt[2] <= paramInt3))
      {
        arrayOfInt[2] += 1;
        paramInt1 += 1;
      }
    } while ((arrayOfInt[2] > paramInt3) || (Math.abs(arrayOfInt[0] + arrayOfInt[1] + arrayOfInt[2] - paramInt4) * 5 >= paramInt4 * 2) || (!foundPatternCross(arrayOfInt)));
    return centerFromEnd(arrayOfInt, paramInt1);
  }
  
  private boolean foundPatternCross(int[] paramArrayOfInt)
  {
    float f1 = this.moduleSize;
    float f2 = f1 / 2.0F;
    int i = 0;
    while (i < 3)
    {
      if (Math.abs(f1 - paramArrayOfInt[i]) >= f2) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private AlignmentPattern handlePossibleCenter(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int i = paramArrayOfInt[0];
    int j = paramArrayOfInt[1];
    int k = paramArrayOfInt[2];
    float f1 = centerFromEnd(paramArrayOfInt, paramInt2);
    float f2 = crossCheckVertical(paramInt1, (int)f1, paramArrayOfInt[1] * 2, i + j + k);
    if (!Float.isNaN(f2))
    {
      float f3 = (paramArrayOfInt[0] + paramArrayOfInt[1] + paramArrayOfInt[2]) / 3.0F;
      paramArrayOfInt = this.possibleCenters.iterator();
      while (paramArrayOfInt.hasNext())
      {
        AlignmentPattern localAlignmentPattern = (AlignmentPattern)paramArrayOfInt.next();
        if (localAlignmentPattern.aboutEquals(f3, f2, f1)) {
          return localAlignmentPattern.combineEstimate(f2, f1, f3);
        }
      }
      paramArrayOfInt = new AlignmentPattern(f1, f2, f3);
      this.possibleCenters.add(paramArrayOfInt);
      if (this.resultPointCallback != null) {
        this.resultPointCallback.foundPossibleResultPoint(paramArrayOfInt);
      }
    }
    return null;
  }
  
  AlignmentPattern find()
    throws NotFoundException
  {
    int n = this.startX;
    int i1 = this.height;
    int i2 = n + this.width;
    int i3 = this.startY;
    int i4 = i1 / 2;
    int[] arrayOfInt = new int[3];
    int k = 0;
    while (k < i1)
    {
      if ((k & 0x1) == 0) {}
      int i5;
      for (int i = (k + 1) / 2;; i = -((k + 1) / 2))
      {
        i5 = i3 + i4 + i;
        arrayOfInt[0] = 0;
        arrayOfInt[1] = 0;
        arrayOfInt[2] = 0;
        i = n;
        while ((i < i2) && (!this.image.get(i, i5))) {
          i += 1;
        }
      }
      int j = 0;
      int m = i;
      i = j;
      Object localObject;
      for (;;)
      {
        if (m < i2) {
          if (this.image.get(m, i5))
          {
            if (i == 1)
            {
              arrayOfInt[1] += 1;
              m += 1;
              continue;
            }
            if (i == 2) {
              if (foundPatternCross(arrayOfInt))
              {
                localObject = handlePossibleCenter(arrayOfInt, i5, m);
                if (localObject == null) {
                  break;
                }
              }
            }
          }
        }
      }
      AlignmentPattern localAlignmentPattern;
      do
      {
        return (AlignmentPattern)localObject;
        arrayOfInt[0] = arrayOfInt[2];
        arrayOfInt[1] = 1;
        arrayOfInt[2] = 0;
        i = 1;
        break;
        i += 1;
        arrayOfInt[i] += 1;
        break;
        j = i;
        if (i == 1) {
          j = i + 1;
        }
        arrayOfInt[j] += 1;
        i = j;
        break;
        if (!foundPatternCross(arrayOfInt)) {
          break label303;
        }
        localAlignmentPattern = handlePossibleCenter(arrayOfInt, i5, i2);
        localObject = localAlignmentPattern;
      } while (localAlignmentPattern != null);
      label303:
      k += 1;
    }
    if (!this.possibleCenters.isEmpty()) {
      return (AlignmentPattern)this.possibleCenters.get(0);
    }
    throw NotFoundException.getNotFoundInstance();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\qrcode\detector\AlignmentPatternFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */