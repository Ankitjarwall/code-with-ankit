package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.PerspectiveTransform;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.qrcode.decoder.Version;
import java.util.Map;

public class Detector
{
  private final BitMatrix image;
  private ResultPointCallback resultPointCallback;
  
  public Detector(BitMatrix paramBitMatrix)
  {
    this.image = paramBitMatrix;
  }
  
  private float calculateModuleSizeOneWay(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2)
  {
    float f1 = sizeOfBlackWhiteBlackRunBothWays((int)paramResultPoint1.getX(), (int)paramResultPoint1.getY(), (int)paramResultPoint2.getX(), (int)paramResultPoint2.getY());
    float f2 = sizeOfBlackWhiteBlackRunBothWays((int)paramResultPoint2.getX(), (int)paramResultPoint2.getY(), (int)paramResultPoint1.getX(), (int)paramResultPoint1.getY());
    if (Float.isNaN(f1)) {
      return f2 / 7.0F;
    }
    if (Float.isNaN(f2)) {
      return f1 / 7.0F;
    }
    return (f1 + f2) / 14.0F;
  }
  
  private static int computeDimension(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, float paramFloat)
    throws NotFoundException
  {
    int i = (MathUtils.round(ResultPoint.distance(paramResultPoint1, paramResultPoint2) / paramFloat) + MathUtils.round(ResultPoint.distance(paramResultPoint1, paramResultPoint3) / paramFloat)) / 2 + 7;
    switch (i & 0x3)
    {
    case 1: 
    default: 
      return i;
    case 0: 
      return i + 1;
    case 2: 
      return i - 1;
    }
    throw NotFoundException.getNotFoundInstance();
  }
  
  private static PerspectiveTransform createTransform(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4, int paramInt)
  {
    float f1 = paramInt - 3.5F;
    float f4;
    float f5;
    float f2;
    if (paramResultPoint4 != null)
    {
      f4 = paramResultPoint4.getX();
      f5 = paramResultPoint4.getY();
      f2 = f1 - 3.0F;
    }
    for (float f3 = f2;; f3 = f1)
    {
      return PerspectiveTransform.quadrilateralToQuadrilateral(3.5F, 3.5F, f1, 3.5F, f2, f3, 3.5F, f1, paramResultPoint1.getX(), paramResultPoint1.getY(), paramResultPoint2.getX(), paramResultPoint2.getY(), f4, f5, paramResultPoint3.getX(), paramResultPoint3.getY());
      f4 = paramResultPoint2.getX() - paramResultPoint1.getX() + paramResultPoint3.getX();
      f5 = paramResultPoint2.getY() - paramResultPoint1.getY() + paramResultPoint3.getY();
      f2 = f1;
    }
  }
  
  private static BitMatrix sampleGrid(BitMatrix paramBitMatrix, PerspectiveTransform paramPerspectiveTransform, int paramInt)
    throws NotFoundException
  {
    return GridSampler.getInstance().sampleGrid(paramBitMatrix, paramInt, paramInt, paramPerspectiveTransform);
  }
  
  private float sizeOfBlackWhiteBlackRun(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int k;
    int j;
    int i;
    int n;
    int m;
    int i6;
    int i7;
    int i3;
    int i1;
    label87:
    int i2;
    if (Math.abs(paramInt4 - paramInt2) > Math.abs(paramInt3 - paramInt1))
    {
      k = 1;
      j = paramInt1;
      i = paramInt2;
      n = paramInt3;
      m = paramInt4;
      if (k != 0)
      {
        m = paramInt3;
        n = paramInt4;
        i = paramInt1;
        j = paramInt2;
      }
      i6 = Math.abs(n - j);
      i7 = Math.abs(m - i);
      i3 = -i6 / 2;
      if (j >= n) {
        break label181;
      }
      i1 = 1;
      if (i >= m) {
        break label187;
      }
      i2 = 1;
      label97:
      paramInt3 = 0;
      paramInt1 = j;
    }
    int i4;
    for (paramInt2 = i;; paramInt2 = i4)
    {
      paramInt4 = paramInt3;
      if (paramInt1 != n + i1)
      {
        label125:
        int i5;
        if (k != 0)
        {
          i4 = paramInt2;
          if (k == 0) {
            break label199;
          }
          i5 = paramInt1;
          label133:
          if (paramInt3 != 1) {
            break label205;
          }
        }
        label181:
        label187:
        label199:
        label205:
        for (int i8 = 1;; i8 = 0)
        {
          paramInt4 = paramInt3;
          if (i8 != this.image.get(i4, i5)) {
            break label216;
          }
          if (paramInt3 != 2) {
            break label211;
          }
          return MathUtils.distance(paramInt1, paramInt2, j, i);
          k = 0;
          break;
          i1 = -1;
          break label87;
          i2 = -1;
          break label97;
          i4 = paramInt1;
          break label125;
          i5 = paramInt2;
          break label133;
        }
        label211:
        paramInt4 = paramInt3 + 1;
        label216:
        i3 += i7;
        paramInt3 = i3;
        i4 = paramInt2;
        if (i3 <= 0) {
          break label273;
        }
        if (paramInt2 != m) {}
      }
      else
      {
        if (paramInt4 != 2) {
          break label290;
        }
        return MathUtils.distance(n + i1, m, j, i);
      }
      i4 = paramInt2 + i2;
      paramInt3 = i3 - i6;
      label273:
      paramInt1 += i1;
      i3 = paramInt3;
      paramInt3 = paramInt4;
    }
    label290:
    return NaN.0F;
  }
  
  private float sizeOfBlackWhiteBlackRunBothWays(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    float f2 = sizeOfBlackWhiteBlackRun(paramInt1, paramInt2, paramInt3, paramInt4);
    float f1 = 1.0F;
    int i = paramInt1 - (paramInt3 - paramInt1);
    if (i < 0)
    {
      f1 = paramInt1 / (paramInt1 - i);
      paramInt3 = 0;
      i = (int)(paramInt2 - (paramInt4 - paramInt2) * f1);
      f1 = 1.0F;
      if (i >= 0) {
        break label146;
      }
      f1 = paramInt2 / (paramInt2 - i);
      paramInt4 = 0;
    }
    for (;;)
    {
      return f2 + sizeOfBlackWhiteBlackRun(paramInt1, paramInt2, (int)(paramInt1 + (paramInt3 - paramInt1) * f1), paramInt4) - 1.0F;
      paramInt3 = i;
      if (i < this.image.getWidth()) {
        break;
      }
      f1 = (this.image.getWidth() - 1 - paramInt1) / (i - paramInt1);
      paramInt3 = this.image.getWidth() - 1;
      break;
      label146:
      paramInt4 = i;
      if (i >= this.image.getHeight())
      {
        f1 = (this.image.getHeight() - 1 - paramInt2) / (i - paramInt2);
        paramInt4 = this.image.getHeight() - 1;
      }
    }
  }
  
  protected final float calculateModuleSize(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3)
  {
    return (calculateModuleSizeOneWay(paramResultPoint1, paramResultPoint2) + calculateModuleSizeOneWay(paramResultPoint1, paramResultPoint3)) / 2.0F;
  }
  
  public DetectorResult detect()
    throws NotFoundException, FormatException
  {
    return detect(null);
  }
  
  public final DetectorResult detect(Map<DecodeHintType, ?> paramMap)
    throws NotFoundException, FormatException
  {
    if (paramMap == null) {}
    for (ResultPointCallback localResultPointCallback = null;; localResultPointCallback = (ResultPointCallback)paramMap.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK))
    {
      this.resultPointCallback = localResultPointCallback;
      return processFinderPatternInfo(new FinderPatternFinder(this.image, this.resultPointCallback).find(paramMap));
    }
  }
  
  protected final AlignmentPattern findAlignmentInRegion(float paramFloat1, int paramInt1, int paramInt2, float paramFloat2)
    throws NotFoundException
  {
    int j = (int)(paramFloat2 * paramFloat1);
    int i = Math.max(0, paramInt1 - j);
    paramInt1 = Math.min(this.image.getWidth() - 1, paramInt1 + j);
    if (paramInt1 - i < paramFloat1 * 3.0F) {
      throw NotFoundException.getNotFoundInstance();
    }
    int k = Math.max(0, paramInt2 - j);
    paramInt2 = Math.min(this.image.getHeight() - 1, paramInt2 + j);
    if (paramInt2 - k < paramFloat1 * 3.0F) {
      throw NotFoundException.getNotFoundInstance();
    }
    return new AlignmentPatternFinder(this.image, i, k, paramInt1 - i, paramInt2 - k, paramFloat1, this.resultPointCallback).find();
  }
  
  protected final BitMatrix getImage()
  {
    return this.image;
  }
  
  protected final ResultPointCallback getResultPointCallback()
  {
    return this.resultPointCallback;
  }
  
  protected final DetectorResult processFinderPatternInfo(FinderPatternInfo paramFinderPatternInfo)
    throws NotFoundException, FormatException
  {
    FinderPattern localFinderPattern1 = paramFinderPatternInfo.getTopLeft();
    FinderPattern localFinderPattern2 = paramFinderPatternInfo.getTopRight();
    FinderPattern localFinderPattern3 = paramFinderPatternInfo.getBottomLeft();
    float f1 = calculateModuleSize(localFinderPattern1, localFinderPattern2, localFinderPattern3);
    if (f1 < 1.0F) {
      throw NotFoundException.getNotFoundInstance();
    }
    int j = computeDimension(localFinderPattern1, localFinderPattern2, localFinderPattern3, f1);
    Object localObject2 = Version.getProvisionalVersionForDimension(j);
    int i = ((Version)localObject2).getDimensionForVersion();
    Object localObject1 = null;
    paramFinderPatternInfo = (FinderPatternInfo)localObject1;
    float f2;
    int k;
    int m;
    if (((Version)localObject2).getAlignmentPatternCenters().length > 0)
    {
      f2 = localFinderPattern2.getX();
      float f3 = localFinderPattern1.getX();
      float f4 = localFinderPattern3.getX();
      float f5 = localFinderPattern2.getY();
      float f6 = localFinderPattern1.getY();
      float f7 = localFinderPattern3.getY();
      float f8 = 1.0F - 3.0F / (i - 7);
      k = (int)(localFinderPattern1.getX() + (f2 - f3 + f4 - localFinderPattern1.getX()) * f8);
      m = (int)(localFinderPattern1.getY() + (f5 - f6 + f7 - localFinderPattern1.getY()) * f8);
      i = 4;
      paramFinderPatternInfo = (FinderPatternInfo)localObject1;
      if (i <= 16) {
        f2 = i;
      }
    }
    for (;;)
    {
      try
      {
        paramFinderPatternInfo = findAlignmentInRegion(f1, k, m, f2);
        localObject1 = createTransform(localFinderPattern1, localFinderPattern2, localFinderPattern3, paramFinderPatternInfo, j);
        localObject2 = sampleGrid(this.image, (PerspectiveTransform)localObject1, j);
        if (paramFinderPatternInfo != null) {
          break label285;
        }
        paramFinderPatternInfo = new ResultPoint[3];
        paramFinderPatternInfo[0] = localFinderPattern3;
        paramFinderPatternInfo[1] = localFinderPattern1;
        paramFinderPatternInfo[2] = localFinderPattern2;
        return new DetectorResult((BitMatrix)localObject2, paramFinderPatternInfo);
      }
      catch (NotFoundException paramFinderPatternInfo)
      {
        i <<= 1;
      }
      break;
      label285:
      localObject1 = new ResultPoint[4];
      localObject1[0] = localFinderPattern3;
      localObject1[1] = localFinderPattern1;
      localObject1[2] = localFinderPattern2;
      localObject1[3] = paramFinderPatternInfo;
      paramFinderPatternInfo = (FinderPatternInfo)localObject1;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\qrcode\detector\Detector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */