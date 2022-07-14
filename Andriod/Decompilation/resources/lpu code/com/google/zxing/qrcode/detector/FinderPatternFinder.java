package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FinderPatternFinder
{
  private static final int CENTER_QUORUM = 2;
  protected static final int MAX_MODULES = 57;
  protected static final int MIN_SKIP = 3;
  private final int[] crossCheckStateCount;
  private boolean hasSkipped;
  private final BitMatrix image;
  private final List<FinderPattern> possibleCenters;
  private final ResultPointCallback resultPointCallback;
  
  public FinderPatternFinder(BitMatrix paramBitMatrix)
  {
    this(paramBitMatrix, null);
  }
  
  public FinderPatternFinder(BitMatrix paramBitMatrix, ResultPointCallback paramResultPointCallback)
  {
    this.image = paramBitMatrix;
    this.possibleCenters = new ArrayList();
    this.crossCheckStateCount = new int[5];
    this.resultPointCallback = paramResultPointCallback;
  }
  
  private static float centerFromEnd(int[] paramArrayOfInt, int paramInt)
  {
    return paramInt - paramArrayOfInt[4] - paramArrayOfInt[3] - paramArrayOfInt[2] / 2.0F;
  }
  
  private boolean crossCheckDiagonal(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int[] arrayOfInt = getCrossCheckStateCount();
    int i = 0;
    while ((paramInt1 >= i) && (paramInt2 >= i) && (this.image.get(paramInt2 - i, paramInt1 - i)))
    {
      arrayOfInt[2] += 1;
      i += 1;
    }
    int j;
    if (paramInt1 >= i)
    {
      j = i;
      if (paramInt2 >= i) {}
    }
    else
    {
      return false;
    }
    while ((paramInt1 >= j) && (paramInt2 >= j) && (!this.image.get(paramInt2 - j, paramInt1 - j)) && (arrayOfInt[1] <= paramInt3))
    {
      arrayOfInt[1] += 1;
      j += 1;
    }
    if ((paramInt1 < j) || (paramInt2 < j) || (arrayOfInt[1] > paramInt3)) {
      return false;
    }
    while ((paramInt1 >= j) && (paramInt2 >= j) && (this.image.get(paramInt2 - j, paramInt1 - j)) && (arrayOfInt[0] <= paramInt3))
    {
      arrayOfInt[0] += 1;
      j += 1;
    }
    if (arrayOfInt[0] > paramInt3) {
      return false;
    }
    int k = this.image.getHeight();
    int m = this.image.getWidth();
    i = 1;
    while ((paramInt1 + i < k) && (paramInt2 + i < m) && (this.image.get(paramInt2 + i, paramInt1 + i)))
    {
      arrayOfInt[2] += 1;
      i += 1;
    }
    if (paramInt1 + i < k)
    {
      j = i;
      if (paramInt2 + i < m) {}
    }
    else
    {
      return false;
    }
    while ((paramInt1 + j < k) && (paramInt2 + j < m) && (!this.image.get(paramInt2 + j, paramInt1 + j)) && (arrayOfInt[3] < paramInt3))
    {
      arrayOfInt[3] += 1;
      j += 1;
    }
    if ((paramInt1 + j >= k) || (paramInt2 + j >= m) || (arrayOfInt[3] >= paramInt3)) {
      return false;
    }
    while ((paramInt1 + j < k) && (paramInt2 + j < m) && (this.image.get(paramInt2 + j, paramInt1 + j)) && (arrayOfInt[4] < paramInt3))
    {
      arrayOfInt[4] += 1;
      j += 1;
    }
    if (arrayOfInt[4] >= paramInt3) {
      return false;
    }
    return (Math.abs(arrayOfInt[0] + arrayOfInt[1] + arrayOfInt[2] + arrayOfInt[3] + arrayOfInt[4] - paramInt4) < paramInt4 * 2) && (foundPatternCross(arrayOfInt));
  }
  
  private float crossCheckHorizontal(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    BitMatrix localBitMatrix = this.image;
    int k = localBitMatrix.getWidth();
    int[] arrayOfInt = getCrossCheckStateCount();
    int i = paramInt1;
    while ((i >= 0) && (localBitMatrix.get(i, paramInt2)))
    {
      arrayOfInt[2] += 1;
      i -= 1;
    }
    int j = i;
    if (i < 0) {
      return NaN.0F;
    }
    while ((j >= 0) && (!localBitMatrix.get(j, paramInt2)) && (arrayOfInt[1] <= paramInt3))
    {
      arrayOfInt[1] += 1;
      j -= 1;
    }
    if ((j < 0) || (arrayOfInt[1] > paramInt3)) {
      return NaN.0F;
    }
    while ((j >= 0) && (localBitMatrix.get(j, paramInt2)) && (arrayOfInt[0] <= paramInt3))
    {
      arrayOfInt[0] += 1;
      j -= 1;
    }
    if (arrayOfInt[0] > paramInt3) {
      return NaN.0F;
    }
    paramInt1 += 1;
    while ((paramInt1 < k) && (localBitMatrix.get(paramInt1, paramInt2)))
    {
      arrayOfInt[2] += 1;
      paramInt1 += 1;
    }
    i = paramInt1;
    if (paramInt1 == k) {
      return NaN.0F;
    }
    while ((i < k) && (!localBitMatrix.get(i, paramInt2)) && (arrayOfInt[3] < paramInt3))
    {
      arrayOfInt[3] += 1;
      i += 1;
    }
    if ((i == k) || (arrayOfInt[3] >= paramInt3)) {
      return NaN.0F;
    }
    while ((i < k) && (localBitMatrix.get(i, paramInt2)) && (arrayOfInt[4] < paramInt3))
    {
      arrayOfInt[4] += 1;
      i += 1;
    }
    if (arrayOfInt[4] >= paramInt3) {
      return NaN.0F;
    }
    if (Math.abs(arrayOfInt[0] + arrayOfInt[1] + arrayOfInt[2] + arrayOfInt[3] + arrayOfInt[4] - paramInt4) * 5 >= paramInt4) {
      return NaN.0F;
    }
    if (foundPatternCross(arrayOfInt)) {
      return centerFromEnd(arrayOfInt, i);
    }
    return NaN.0F;
  }
  
  private float crossCheckVertical(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    BitMatrix localBitMatrix = this.image;
    int k = localBitMatrix.getHeight();
    int[] arrayOfInt = getCrossCheckStateCount();
    int i = paramInt1;
    while ((i >= 0) && (localBitMatrix.get(paramInt2, i)))
    {
      arrayOfInt[2] += 1;
      i -= 1;
    }
    int j = i;
    if (i < 0) {
      return NaN.0F;
    }
    while ((j >= 0) && (!localBitMatrix.get(paramInt2, j)) && (arrayOfInt[1] <= paramInt3))
    {
      arrayOfInt[1] += 1;
      j -= 1;
    }
    if ((j < 0) || (arrayOfInt[1] > paramInt3)) {
      return NaN.0F;
    }
    while ((j >= 0) && (localBitMatrix.get(paramInt2, j)) && (arrayOfInt[0] <= paramInt3))
    {
      arrayOfInt[0] += 1;
      j -= 1;
    }
    if (arrayOfInt[0] > paramInt3) {
      return NaN.0F;
    }
    paramInt1 += 1;
    while ((paramInt1 < k) && (localBitMatrix.get(paramInt2, paramInt1)))
    {
      arrayOfInt[2] += 1;
      paramInt1 += 1;
    }
    i = paramInt1;
    if (paramInt1 == k) {
      return NaN.0F;
    }
    while ((i < k) && (!localBitMatrix.get(paramInt2, i)) && (arrayOfInt[3] < paramInt3))
    {
      arrayOfInt[3] += 1;
      i += 1;
    }
    if ((i == k) || (arrayOfInt[3] >= paramInt3)) {
      return NaN.0F;
    }
    while ((i < k) && (localBitMatrix.get(paramInt2, i)) && (arrayOfInt[4] < paramInt3))
    {
      arrayOfInt[4] += 1;
      i += 1;
    }
    if (arrayOfInt[4] >= paramInt3) {
      return NaN.0F;
    }
    if (Math.abs(arrayOfInt[0] + arrayOfInt[1] + arrayOfInt[2] + arrayOfInt[3] + arrayOfInt[4] - paramInt4) * 5 >= paramInt4 * 2) {
      return NaN.0F;
    }
    if (foundPatternCross(arrayOfInt)) {
      return centerFromEnd(arrayOfInt, i);
    }
    return NaN.0F;
  }
  
  private int findRowSkip()
  {
    if (this.possibleCenters.size() <= 1) {}
    Object localObject;
    FinderPattern localFinderPattern;
    for (;;)
    {
      return 0;
      localObject = null;
      Iterator localIterator = this.possibleCenters.iterator();
      while (localIterator.hasNext())
      {
        localFinderPattern = (FinderPattern)localIterator.next();
        if (localFinderPattern.getCount() >= 2)
        {
          if (localObject != null) {
            break label63;
          }
          localObject = localFinderPattern;
        }
      }
    }
    label63:
    this.hasSkipped = true;
    return (int)(Math.abs(((ResultPoint)localObject).getX() - localFinderPattern.getX()) - Math.abs(((ResultPoint)localObject).getY() - localFinderPattern.getY())) / 2;
  }
  
  protected static boolean foundPatternCross(int[] paramArrayOfInt)
  {
    boolean bool = true;
    int j = 0;
    int i = 0;
    if (i < 5)
    {
      k = paramArrayOfInt[i];
      if (k != 0) {}
    }
    while (j < 7)
    {
      int k;
      return false;
      j += k;
      i += 1;
      break;
    }
    float f1 = j / 7.0F;
    float f2 = f1 / 2.0F;
    if ((Math.abs(f1 - paramArrayOfInt[0]) < f2) && (Math.abs(f1 - paramArrayOfInt[1]) < f2) && (Math.abs(3.0F * f1 - paramArrayOfInt[2]) < 3.0F * f2) && (Math.abs(f1 - paramArrayOfInt[3]) < f2) && (Math.abs(f1 - paramArrayOfInt[4]) < f2)) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  private int[] getCrossCheckStateCount()
  {
    this.crossCheckStateCount[0] = 0;
    this.crossCheckStateCount[1] = 0;
    this.crossCheckStateCount[2] = 0;
    this.crossCheckStateCount[3] = 0;
    this.crossCheckStateCount[4] = 0;
    return this.crossCheckStateCount;
  }
  
  private boolean haveMultiplyConfirmedCenters()
  {
    int i = 0;
    float f1 = 0.0F;
    int j = this.possibleCenters.size();
    Iterator localIterator = this.possibleCenters.iterator();
    while (localIterator.hasNext())
    {
      FinderPattern localFinderPattern = (FinderPattern)localIterator.next();
      if (localFinderPattern.getCount() >= 2)
      {
        i += 1;
        f1 += localFinderPattern.getEstimatedModuleSize();
      }
    }
    if (i < 3) {}
    float f2;
    do
    {
      return false;
      float f3 = f1 / j;
      f2 = 0.0F;
      localIterator = this.possibleCenters.iterator();
      while (localIterator.hasNext()) {
        f2 += Math.abs(((FinderPattern)localIterator.next()).getEstimatedModuleSize() - f3);
      }
    } while (f2 > 0.05F * f1);
    return true;
  }
  
  private FinderPattern[] selectBestPatterns()
    throws NotFoundException
  {
    int i = this.possibleCenters.size();
    if (i < 3) {
      throw NotFoundException.getNotFoundInstance();
    }
    float f1;
    Iterator localIterator;
    if (i > 3)
    {
      float f2 = 0.0F;
      f1 = 0.0F;
      localIterator = this.possibleCenters.iterator();
      while (localIterator.hasNext())
      {
        float f3 = ((FinderPattern)localIterator.next()).getEstimatedModuleSize();
        f2 += f3;
        f1 += f3 * f3;
      }
      f2 /= i;
      f1 = (float)Math.sqrt(f1 / i - f2 * f2);
      Collections.sort(this.possibleCenters, new FurthestFromAverageComparator(f2, null));
      f1 = Math.max(0.2F * f2, f1);
      int j;
      for (i = 0; (i < this.possibleCenters.size()) && (this.possibleCenters.size() > 3); i = j + 1)
      {
        j = i;
        if (Math.abs(((FinderPattern)this.possibleCenters.get(i)).getEstimatedModuleSize() - f2) > f1)
        {
          this.possibleCenters.remove(i);
          j = i - 1;
        }
      }
    }
    if (this.possibleCenters.size() > 3)
    {
      f1 = 0.0F;
      localIterator = this.possibleCenters.iterator();
      while (localIterator.hasNext()) {
        f1 += ((FinderPattern)localIterator.next()).getEstimatedModuleSize();
      }
      f1 /= this.possibleCenters.size();
      Collections.sort(this.possibleCenters, new CenterComparator(f1, null));
      this.possibleCenters.subList(3, this.possibleCenters.size()).clear();
    }
    return new FinderPattern[] { (FinderPattern)this.possibleCenters.get(0), (FinderPattern)this.possibleCenters.get(1), (FinderPattern)this.possibleCenters.get(2) };
  }
  
  final FinderPatternInfo find(Map<DecodeHintType, ?> paramMap)
    throws NotFoundException
  {
    int i;
    boolean bool3;
    label37:
    int i1;
    int i2;
    int m;
    boolean bool1;
    int k;
    if ((paramMap != null) && (paramMap.containsKey(DecodeHintType.TRY_HARDER)))
    {
      i = 1;
      if ((paramMap == null) || (!paramMap.containsKey(DecodeHintType.PURE_BARCODE))) {
        break label186;
      }
      bool3 = true;
      i1 = this.image.getHeight();
      i2 = this.image.getWidth();
      m = i1 * 3 / 228;
      if ((m < 3) || (i != 0)) {
        m = 3;
      }
      bool1 = false;
      paramMap = new int[5];
      k = m - 1;
    }
    for (;;)
    {
      if ((k >= i1) || (bool1)) {
        break label488;
      }
      paramMap[0] = 0;
      paramMap[1] = 0;
      paramMap[2] = 0;
      paramMap[3] = 0;
      paramMap[4] = 0;
      i = 0;
      int j = 0;
      label127:
      if (j < i2)
      {
        int n;
        if (this.image.get(j, k))
        {
          n = i;
          if ((i & 0x1) == 1) {
            n = i + 1;
          }
          paramMap[n] += 1;
          i = n;
        }
        for (;;)
        {
          j += 1;
          break label127;
          i = 0;
          break;
          label186:
          bool3 = false;
          break label37;
          if ((i & 0x1) == 0)
          {
            if (i == 4)
            {
              if (foundPatternCross(paramMap))
              {
                if (handlePossibleCenter(paramMap, k, j, bool3))
                {
                  n = 2;
                  if (this.hasSkipped)
                  {
                    bool2 = haveMultiplyConfirmedCenters();
                    m = k;
                  }
                  for (;;)
                  {
                    i = 0;
                    paramMap[0] = 0;
                    paramMap[1] = 0;
                    paramMap[2] = 0;
                    paramMap[3] = 0;
                    paramMap[4] = 0;
                    bool1 = bool2;
                    k = m;
                    m = n;
                    break;
                    i = findRowSkip();
                    bool2 = bool1;
                    m = k;
                    if (i > paramMap[2])
                    {
                      m = k + (i - paramMap[2] - 2);
                      j = i2 - 1;
                      bool2 = bool1;
                    }
                  }
                }
                paramMap[0] = paramMap[2];
                paramMap[1] = paramMap[3];
                paramMap[2] = paramMap[4];
                paramMap[3] = 1;
                paramMap[4] = 0;
                i = 3;
              }
              else
              {
                paramMap[0] = paramMap[2];
                paramMap[1] = paramMap[3];
                paramMap[2] = paramMap[4];
                paramMap[3] = 1;
                paramMap[4] = 0;
                i = 3;
              }
            }
            else
            {
              i += 1;
              paramMap[i] += 1;
            }
          }
          else {
            paramMap[i] += 1;
          }
        }
      }
      boolean bool2 = bool1;
      i = m;
      if (foundPatternCross(paramMap))
      {
        bool2 = bool1;
        i = m;
        if (handlePossibleCenter(paramMap, k, i2, bool3))
        {
          j = paramMap[0];
          bool2 = bool1;
          i = j;
          if (this.hasSkipped)
          {
            bool2 = haveMultiplyConfirmedCenters();
            i = j;
          }
        }
      }
      k += i;
      bool1 = bool2;
      m = i;
    }
    label488:
    paramMap = selectBestPatterns();
    ResultPoint.orderBestPatterns(paramMap);
    return new FinderPatternInfo(paramMap);
  }
  
  protected final BitMatrix getImage()
  {
    return this.image;
  }
  
  protected final List<FinderPattern> getPossibleCenters()
  {
    return this.possibleCenters;
  }
  
  protected final boolean handlePossibleCenter(int[] paramArrayOfInt, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int i = paramArrayOfInt[0] + paramArrayOfInt[1] + paramArrayOfInt[2] + paramArrayOfInt[3] + paramArrayOfInt[4];
    float f2 = centerFromEnd(paramArrayOfInt, paramInt2);
    float f1 = crossCheckVertical(paramInt1, (int)f2, paramArrayOfInt[2], i);
    if (!Float.isNaN(f1))
    {
      f2 = crossCheckHorizontal((int)f2, (int)f1, paramArrayOfInt[2], i);
      if ((!Float.isNaN(f2)) && ((!paramBoolean) || (crossCheckDiagonal((int)f1, (int)f2, paramArrayOfInt[2], i))))
      {
        float f3 = i / 7.0F;
        i = 0;
        paramInt1 = 0;
        for (;;)
        {
          paramInt2 = i;
          if (paramInt1 < this.possibleCenters.size())
          {
            paramArrayOfInt = (FinderPattern)this.possibleCenters.get(paramInt1);
            if (paramArrayOfInt.aboutEquals(f3, f1, f2))
            {
              this.possibleCenters.set(paramInt1, paramArrayOfInt.combineEstimate(f1, f2, f3));
              paramInt2 = 1;
            }
          }
          else
          {
            if (paramInt2 == 0)
            {
              paramArrayOfInt = new FinderPattern(f2, f1, f3);
              this.possibleCenters.add(paramArrayOfInt);
              if (this.resultPointCallback != null) {
                this.resultPointCallback.foundPossibleResultPoint(paramArrayOfInt);
              }
            }
            return true;
          }
          paramInt1 += 1;
        }
      }
    }
    return false;
  }
  
  private static final class CenterComparator
    implements Comparator<FinderPattern>, Serializable
  {
    private final float average;
    
    private CenterComparator(float paramFloat)
    {
      this.average = paramFloat;
    }
    
    public int compare(FinderPattern paramFinderPattern1, FinderPattern paramFinderPattern2)
    {
      if (paramFinderPattern2.getCount() == paramFinderPattern1.getCount())
      {
        float f1 = Math.abs(paramFinderPattern2.getEstimatedModuleSize() - this.average);
        float f2 = Math.abs(paramFinderPattern1.getEstimatedModuleSize() - this.average);
        if (f1 < f2) {
          return 1;
        }
        if (f1 == f2) {
          return 0;
        }
        return -1;
      }
      return paramFinderPattern2.getCount() - paramFinderPattern1.getCount();
    }
  }
  
  private static final class FurthestFromAverageComparator
    implements Comparator<FinderPattern>, Serializable
  {
    private final float average;
    
    private FurthestFromAverageComparator(float paramFloat)
    {
      this.average = paramFloat;
    }
    
    public int compare(FinderPattern paramFinderPattern1, FinderPattern paramFinderPattern2)
    {
      float f1 = Math.abs(paramFinderPattern2.getEstimatedModuleSize() - this.average);
      float f2 = Math.abs(paramFinderPattern1.getEstimatedModuleSize() - this.average);
      if (f1 < f2) {
        return -1;
      }
      if (f1 == f2) {
        return 0;
      }
      return 1;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\qrcode\detector\FinderPatternFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */