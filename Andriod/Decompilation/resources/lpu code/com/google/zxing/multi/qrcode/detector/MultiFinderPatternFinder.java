package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.detector.FinderPattern;
import com.google.zxing.qrcode.detector.FinderPatternFinder;
import com.google.zxing.qrcode.detector.FinderPatternInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

final class MultiFinderPatternFinder
  extends FinderPatternFinder
{
  private static final float DIFF_MODSIZE_CUTOFF = 0.5F;
  private static final float DIFF_MODSIZE_CUTOFF_PERCENT = 0.05F;
  private static final FinderPatternInfo[] EMPTY_RESULT_ARRAY = new FinderPatternInfo[0];
  private static final float MAX_MODULE_COUNT_PER_EDGE = 180.0F;
  private static final float MIN_MODULE_COUNT_PER_EDGE = 9.0F;
  
  MultiFinderPatternFinder(BitMatrix paramBitMatrix)
  {
    super(paramBitMatrix);
  }
  
  MultiFinderPatternFinder(BitMatrix paramBitMatrix, ResultPointCallback paramResultPointCallback)
  {
    super(paramBitMatrix, paramResultPointCallback);
  }
  
  private FinderPattern[][] selectMutipleBestPatterns()
    throws NotFoundException
  {
    List localList = getPossibleCenters();
    int m = localList.size();
    if (m < 3) {
      throw NotFoundException.getNotFoundInstance();
    }
    if (m == 3) {
      return new FinderPattern[][] { { (FinderPattern)localList.get(0), (FinderPattern)localList.get(1), (FinderPattern)localList.get(2) } };
    }
    Collections.sort(localList, new ModuleSizeComparator(null));
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < m - 2)
    {
      FinderPattern localFinderPattern1 = (FinderPattern)localList.get(i);
      if (localFinderPattern1 == null) {}
      int j;
      FinderPattern localFinderPattern2;
      label190:
      float f1;
      do
      {
        for (;;)
        {
          i += 1;
          break;
          j = i + 1;
          while (j < m - 1)
          {
            localFinderPattern2 = (FinderPattern)localList.get(j);
            if (localFinderPattern2 != null) {
              break label190;
            }
            j += 1;
          }
        }
        f1 = (localFinderPattern1.getEstimatedModuleSize() - localFinderPattern2.getEstimatedModuleSize()) / Math.min(localFinderPattern1.getEstimatedModuleSize(), localFinderPattern2.getEstimatedModuleSize());
      } while ((Math.abs(localFinderPattern1.getEstimatedModuleSize() - localFinderPattern2.getEstimatedModuleSize()) > 0.5F) && (f1 >= 0.05F));
      int k = j + 1;
      label249:
      Object localObject;
      if (k < m)
      {
        localObject = (FinderPattern)localList.get(k);
        if (localObject != null) {
          break label284;
        }
      }
      for (;;)
      {
        k += 1;
        break label249;
        break;
        label284:
        f1 = (localFinderPattern2.getEstimatedModuleSize() - ((FinderPattern)localObject).getEstimatedModuleSize()) / Math.min(localFinderPattern2.getEstimatedModuleSize(), ((FinderPattern)localObject).getEstimatedModuleSize());
        if ((Math.abs(localFinderPattern2.getEstimatedModuleSize() - ((FinderPattern)localObject).getEstimatedModuleSize()) > 0.5F) && (f1 >= 0.05F)) {
          break;
        }
        FinderPattern[] arrayOfFinderPattern = new FinderPattern[3];
        arrayOfFinderPattern[0] = localFinderPattern1;
        arrayOfFinderPattern[1] = localFinderPattern2;
        arrayOfFinderPattern[2] = localObject;
        ResultPoint.orderBestPatterns(arrayOfFinderPattern);
        localObject = new FinderPatternInfo(arrayOfFinderPattern);
        float f2 = ResultPoint.distance(((FinderPatternInfo)localObject).getTopLeft(), ((FinderPatternInfo)localObject).getBottomLeft());
        f1 = ResultPoint.distance(((FinderPatternInfo)localObject).getTopRight(), ((FinderPatternInfo)localObject).getBottomLeft());
        float f3 = ResultPoint.distance(((FinderPatternInfo)localObject).getTopLeft(), ((FinderPatternInfo)localObject).getTopRight());
        float f4 = (f2 + f3) / (localFinderPattern1.getEstimatedModuleSize() * 2.0F);
        if ((f4 <= 180.0F) && (f4 >= 9.0F) && (Math.abs((f2 - f3) / Math.min(f2, f3)) < 0.1F))
        {
          f2 = (float)Math.sqrt(f2 * f2 + f3 * f3);
          if (Math.abs((f1 - f2) / Math.min(f1, f2)) < 0.1F) {
            localArrayList.add(arrayOfFinderPattern);
          }
        }
      }
    }
    if (!localArrayList.isEmpty()) {
      return (FinderPattern[][])localArrayList.toArray(new FinderPattern[localArrayList.size()][]);
    }
    throw NotFoundException.getNotFoundInstance();
  }
  
  public FinderPatternInfo[] findMulti(Map<DecodeHintType, ?> paramMap)
    throws NotFoundException
  {
    boolean bool;
    label37:
    int i1;
    int i2;
    int k;
    int m;
    if ((paramMap != null) && (paramMap.containsKey(DecodeHintType.TRY_HARDER)))
    {
      i = 1;
      if ((paramMap == null) || (!paramMap.containsKey(DecodeHintType.PURE_BARCODE))) {
        break label184;
      }
      bool = true;
      paramMap = getImage();
      i1 = paramMap.getHeight();
      i2 = paramMap.getWidth();
      k = (int)(i1 / 228.0F * 3.0F);
      if ((k < 3) || (i != 0)) {
        k = 3;
      }
      localObject = new int[5];
      m = k - 1;
    }
    for (;;)
    {
      if (m >= i1) {
        break label354;
      }
      localObject[0] = 0;
      localObject[1] = 0;
      localObject[2] = 0;
      localObject[3] = 0;
      localObject[4] = 0;
      i = 0;
      int n = 0;
      label127:
      if (n < i2)
      {
        if (paramMap.get(n, m))
        {
          j = i;
          if ((i & 0x1) == 1) {
            j = i + 1;
          }
          localObject[j] += 1;
          i = j;
        }
        for (;;)
        {
          n += 1;
          break label127;
          i = 0;
          break;
          label184:
          bool = false;
          break label37;
          if ((i & 0x1) == 0)
          {
            if (i == 4)
            {
              if ((foundPatternCross((int[])localObject)) && (handlePossibleCenter((int[])localObject, m, n, bool)))
              {
                i = 0;
                localObject[0] = 0;
                localObject[1] = 0;
                localObject[2] = 0;
                localObject[3] = 0;
                localObject[4] = 0;
              }
              else
              {
                localObject[0] = localObject[2];
                localObject[1] = localObject[3];
                localObject[2] = localObject[4];
                localObject[3] = 1;
                localObject[4] = 0;
                i = 3;
              }
            }
            else
            {
              i += 1;
              localObject[i] += 1;
            }
          }
          else {
            localObject[i] += 1;
          }
        }
      }
      if (foundPatternCross((int[])localObject)) {
        handlePossibleCenter((int[])localObject, m, i2, bool);
      }
      m += k;
    }
    label354:
    paramMap = selectMutipleBestPatterns();
    Object localObject = new ArrayList();
    int j = paramMap.length;
    int i = 0;
    while (i < j)
    {
      ResultPoint[] arrayOfResultPoint = paramMap[i];
      ResultPoint.orderBestPatterns(arrayOfResultPoint);
      ((List)localObject).add(new FinderPatternInfo(arrayOfResultPoint));
      i += 1;
    }
    if (((List)localObject).isEmpty()) {
      return EMPTY_RESULT_ARRAY;
    }
    return (FinderPatternInfo[])((List)localObject).toArray(new FinderPatternInfo[((List)localObject).size()]);
  }
  
  private static final class ModuleSizeComparator
    implements Comparator<FinderPattern>, Serializable
  {
    public int compare(FinderPattern paramFinderPattern1, FinderPattern paramFinderPattern2)
    {
      float f = paramFinderPattern2.getEstimatedModuleSize() - paramFinderPattern1.getEstimatedModuleSize();
      if (f < 0.0D) {
        return -1;
      }
      if (f > 0.0D) {
        return 1;
      }
      return 0;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\multi\qrcode\detector\MultiFinderPatternFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */