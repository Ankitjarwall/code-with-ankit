package com.google.zxing.datamatrix.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class Detector
{
  private final BitMatrix image;
  private final WhiteRectangleDetector rectangleDetector;
  
  public Detector(BitMatrix paramBitMatrix)
    throws NotFoundException
  {
    this.image = paramBitMatrix;
    this.rectangleDetector = new WhiteRectangleDetector(paramBitMatrix);
  }
  
  private ResultPoint correctTopRight(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4, int paramInt)
  {
    float f1 = distance(paramResultPoint1, paramResultPoint2) / paramInt;
    int i = distance(paramResultPoint3, paramResultPoint4);
    float f2 = (paramResultPoint4.getX() - paramResultPoint3.getX()) / i;
    float f3 = (paramResultPoint4.getY() - paramResultPoint3.getY()) / i;
    ResultPoint localResultPoint = new ResultPoint(paramResultPoint4.getX() + f1 * f2, paramResultPoint4.getY() + f1 * f3);
    f1 = distance(paramResultPoint1, paramResultPoint3) / paramInt;
    paramInt = distance(paramResultPoint2, paramResultPoint4);
    f2 = (paramResultPoint4.getX() - paramResultPoint2.getX()) / paramInt;
    f3 = (paramResultPoint4.getY() - paramResultPoint2.getY()) / paramInt;
    paramResultPoint4 = new ResultPoint(paramResultPoint4.getX() + f1 * f2, paramResultPoint4.getY() + f1 * f3);
    if (!isValid(localResultPoint)) {
      if (isValid(paramResultPoint4)) {
        paramResultPoint1 = paramResultPoint4;
      }
    }
    do
    {
      do
      {
        return paramResultPoint1;
        return null;
        paramResultPoint1 = localResultPoint;
      } while (!isValid(paramResultPoint4));
      paramResultPoint1 = localResultPoint;
    } while (Math.abs(transitionsBetween(paramResultPoint3, localResultPoint).getTransitions() - transitionsBetween(paramResultPoint2, localResultPoint).getTransitions()) <= Math.abs(transitionsBetween(paramResultPoint3, paramResultPoint4).getTransitions() - transitionsBetween(paramResultPoint2, paramResultPoint4).getTransitions()));
    return paramResultPoint4;
  }
  
  private ResultPoint correctTopRightRectangular(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4, int paramInt1, int paramInt2)
  {
    float f1 = distance(paramResultPoint1, paramResultPoint2) / paramInt1;
    int i = distance(paramResultPoint3, paramResultPoint4);
    float f2 = (paramResultPoint4.getX() - paramResultPoint3.getX()) / i;
    float f3 = (paramResultPoint4.getY() - paramResultPoint3.getY()) / i;
    ResultPoint localResultPoint = new ResultPoint(paramResultPoint4.getX() + f1 * f2, paramResultPoint4.getY() + f1 * f3);
    f1 = distance(paramResultPoint1, paramResultPoint3) / paramInt2;
    i = distance(paramResultPoint2, paramResultPoint4);
    f2 = (paramResultPoint4.getX() - paramResultPoint2.getX()) / i;
    f3 = (paramResultPoint4.getY() - paramResultPoint2.getY()) / i;
    paramResultPoint1 = new ResultPoint(paramResultPoint4.getX() + f1 * f2, paramResultPoint4.getY() + f1 * f3);
    if (!isValid(localResultPoint)) {
      if (!isValid(paramResultPoint1)) {}
    }
    do
    {
      return paramResultPoint1;
      return null;
      if (!isValid(paramResultPoint1)) {
        return localResultPoint;
      }
    } while (Math.abs(paramInt1 - transitionsBetween(paramResultPoint3, localResultPoint).getTransitions()) + Math.abs(paramInt2 - transitionsBetween(paramResultPoint2, localResultPoint).getTransitions()) > Math.abs(paramInt1 - transitionsBetween(paramResultPoint3, paramResultPoint1).getTransitions()) + Math.abs(paramInt2 - transitionsBetween(paramResultPoint2, paramResultPoint1).getTransitions()));
    return localResultPoint;
  }
  
  private static int distance(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2)
  {
    return MathUtils.round(ResultPoint.distance(paramResultPoint1, paramResultPoint2));
  }
  
  private static void increment(Map<ResultPoint, Integer> paramMap, ResultPoint paramResultPoint)
  {
    Integer localInteger = (Integer)paramMap.get(paramResultPoint);
    if (localInteger == null) {}
    for (int i = 1;; i = localInteger.intValue() + 1)
    {
      paramMap.put(paramResultPoint, Integer.valueOf(i));
      return;
    }
  }
  
  private boolean isValid(ResultPoint paramResultPoint)
  {
    return (paramResultPoint.getX() >= 0.0F) && (paramResultPoint.getX() < this.image.getWidth()) && (paramResultPoint.getY() > 0.0F) && (paramResultPoint.getY() < this.image.getHeight());
  }
  
  private static BitMatrix sampleGrid(BitMatrix paramBitMatrix, ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4, int paramInt1, int paramInt2)
    throws NotFoundException
  {
    return GridSampler.getInstance().sampleGrid(paramBitMatrix, paramInt1, paramInt2, 0.5F, 0.5F, paramInt1 - 0.5F, 0.5F, paramInt1 - 0.5F, paramInt2 - 0.5F, 0.5F, paramInt2 - 0.5F, paramResultPoint1.getX(), paramResultPoint1.getY(), paramResultPoint4.getX(), paramResultPoint4.getY(), paramResultPoint3.getX(), paramResultPoint3.getY(), paramResultPoint2.getX(), paramResultPoint2.getY());
  }
  
  private ResultPointsAndTransitions transitionsBetween(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2)
  {
    int i3 = (int)paramResultPoint1.getX();
    int i4 = (int)paramResultPoint1.getY();
    int k = (int)paramResultPoint2.getX();
    int m = (int)paramResultPoint2.getY();
    int n;
    int j;
    int i;
    int i2;
    int i1;
    int i7;
    int i8;
    int i5;
    label120:
    label130:
    int i6;
    BitMatrix localBitMatrix;
    label147:
    label156:
    boolean bool1;
    if (Math.abs(m - i4) > Math.abs(k - i3))
    {
      n = 1;
      j = i3;
      i = i4;
      i2 = k;
      i1 = m;
      if (n != 0)
      {
        j = i4;
        i = i3;
        i1 = k;
        i2 = m;
      }
      i7 = Math.abs(i2 - j);
      i8 = Math.abs(i1 - i);
      i5 = -i7 / 2;
      if (i >= i1) {
        break label296;
      }
      i3 = 1;
      if (j >= i2) {
        break label302;
      }
      i4 = 1;
      i6 = 0;
      localBitMatrix = this.image;
      if (n == 0) {
        break label308;
      }
      k = i;
      if (n == 0) {
        break label315;
      }
      m = j;
      bool1 = localBitMatrix.get(k, m);
      k = j;
      j = i;
      i = k;
      k = i6;
    }
    for (;;)
    {
      m = k;
      if (i != i2)
      {
        localBitMatrix = this.image;
        if (n == 0) {
          break label321;
        }
        m = j;
        label206:
        if (n == 0) {
          break label327;
        }
      }
      boolean bool2;
      label296:
      label302:
      label308:
      label315:
      label321:
      label327:
      for (i6 = i;; i6 = j)
      {
        boolean bool3 = localBitMatrix.get(m, i6);
        bool2 = bool1;
        m = k;
        if (bool3 != bool1)
        {
          m = k + 1;
          bool2 = bool3;
        }
        i5 += i8;
        k = i5;
        i6 = j;
        if (i5 <= 0) {
          break label348;
        }
        if (j != i1) {
          break label334;
        }
        return new ResultPointsAndTransitions(paramResultPoint1, paramResultPoint2, m, null);
        n = 0;
        break;
        i3 = -1;
        break label120;
        i4 = -1;
        break label130;
        k = j;
        break label147;
        m = i;
        break label156;
        m = i;
        break label206;
      }
      label334:
      i6 = j + i3;
      k = i5 - i7;
      label348:
      i += i4;
      i5 = k;
      bool1 = bool2;
      k = m;
      j = i6;
    }
  }
  
  public DetectorResult detect()
    throws NotFoundException
  {
    Object localObject1 = this.rectangleDetector.detect();
    ResultPoint localResultPoint1 = localObject1[0];
    ResultPoint localResultPoint2 = localObject1[1];
    ResultPoint localResultPoint3 = localObject1[2];
    ResultPoint localResultPoint4 = localObject1[3];
    Object localObject2 = new ArrayList(4);
    ((List)localObject2).add(transitionsBetween(localResultPoint1, localResultPoint2));
    ((List)localObject2).add(transitionsBetween(localResultPoint1, localResultPoint3));
    ((List)localObject2).add(transitionsBetween(localResultPoint2, localResultPoint4));
    ((List)localObject2).add(transitionsBetween(localResultPoint3, localResultPoint4));
    Collections.sort((List)localObject2, new ResultPointsAndTransitionsComparator(null));
    localObject1 = (ResultPointsAndTransitions)((List)localObject2).get(0);
    localObject2 = (ResultPointsAndTransitions)((List)localObject2).get(1);
    HashMap localHashMap = new HashMap();
    increment(localHashMap, ((ResultPointsAndTransitions)localObject1).getFrom());
    increment(localHashMap, ((ResultPointsAndTransitions)localObject1).getTo());
    increment(localHashMap, ((ResultPointsAndTransitions)localObject2).getFrom());
    increment(localHashMap, ((ResultPointsAndTransitions)localObject2).getTo());
    localObject2 = null;
    Object localObject4 = null;
    Object localObject3 = null;
    Iterator localIterator = localHashMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      localEntry = (Map.Entry)localIterator.next();
      localObject1 = (ResultPoint)localEntry.getKey();
      if (((Integer)localEntry.getValue()).intValue() == 2) {
        localObject4 = localObject1;
      } else if (localObject2 == null) {
        localObject2 = localObject1;
      } else {
        localObject3 = localObject1;
      }
    }
    if ((localObject2 == null) || (localObject4 == null) || (localObject3 == null)) {
      throw NotFoundException.getNotFoundInstance();
    }
    localObject1 = new ResultPoint[3];
    localObject1[0] = localObject2;
    localObject1[1] = localObject4;
    localObject1[2] = localObject3;
    ResultPoint.orderBestPatterns((ResultPoint[])localObject1);
    localObject4 = localObject1[0];
    localIterator = localObject1[1];
    Map.Entry localEntry = localObject1[2];
    int k;
    int j;
    int i;
    if (!localHashMap.containsKey(localResultPoint1))
    {
      localObject1 = localResultPoint1;
      k = transitionsBetween(localEntry, (ResultPoint)localObject1).getTransitions();
      j = transitionsBetween((ResultPoint)localObject4, (ResultPoint)localObject1).getTransitions();
      i = k;
      if ((k & 0x1) == 1) {
        i = k + 1;
      }
      k = i + 2;
      i = j;
      if ((j & 0x1) == 1) {
        i = j + 1;
      }
      i += 2;
      if ((k * 4 < i * 7) && (i * 4 < k * 7)) {
        break label632;
      }
      localObject3 = correctTopRightRectangular(localIterator, (ResultPoint)localObject4, localEntry, (ResultPoint)localObject1, k, i);
      localObject2 = localObject3;
      if (localObject3 == null) {
        localObject2 = localObject1;
      }
      j = transitionsBetween(localEntry, (ResultPoint)localObject2).getTransitions();
      k = transitionsBetween((ResultPoint)localObject4, (ResultPoint)localObject2).getTransitions();
      i = j;
      if ((j & 0x1) == 1) {
        i = j + 1;
      }
      j = k;
      if ((k & 0x1) == 1) {
        j = k + 1;
      }
    }
    for (localObject1 = sampleGrid(this.image, localEntry, localIterator, (ResultPoint)localObject4, (ResultPoint)localObject2, i, j);; localObject1 = sampleGrid(this.image, localEntry, localIterator, (ResultPoint)localObject4, (ResultPoint)localObject2, i, i))
    {
      return new DetectorResult((BitMatrix)localObject1, new ResultPoint[] { localEntry, localIterator, localObject4, localObject2 });
      if (!localHashMap.containsKey(localResultPoint2))
      {
        localObject1 = localResultPoint2;
        break;
      }
      if (!localHashMap.containsKey(localResultPoint3))
      {
        localObject1 = localResultPoint3;
        break;
      }
      localObject1 = localResultPoint4;
      break;
      label632:
      localObject3 = correctTopRight(localIterator, (ResultPoint)localObject4, localEntry, (ResultPoint)localObject1, Math.min(i, k));
      localObject2 = localObject3;
      if (localObject3 == null) {
        localObject2 = localObject1;
      }
      j = Math.max(transitionsBetween(localEntry, (ResultPoint)localObject2).getTransitions(), transitionsBetween((ResultPoint)localObject4, (ResultPoint)localObject2).getTransitions()) + 1;
      i = j;
      if ((j & 0x1) == 1) {
        i = j + 1;
      }
    }
  }
  
  private static final class ResultPointsAndTransitions
  {
    private final ResultPoint from;
    private final ResultPoint to;
    private final int transitions;
    
    private ResultPointsAndTransitions(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, int paramInt)
    {
      this.from = paramResultPoint1;
      this.to = paramResultPoint2;
      this.transitions = paramInt;
    }
    
    ResultPoint getFrom()
    {
      return this.from;
    }
    
    ResultPoint getTo()
    {
      return this.to;
    }
    
    int getTransitions()
    {
      return this.transitions;
    }
    
    public String toString()
    {
      return this.from + "/" + this.to + '/' + this.transitions;
    }
  }
  
  private static final class ResultPointsAndTransitionsComparator
    implements Comparator<Detector.ResultPointsAndTransitions>, Serializable
  {
    public int compare(Detector.ResultPointsAndTransitions paramResultPointsAndTransitions1, Detector.ResultPointsAndTransitions paramResultPointsAndTransitions2)
    {
      return paramResultPointsAndTransitions1.getTransitions() - paramResultPointsAndTransitions2.getTransitions();
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\datamatrix\detector\Detector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */