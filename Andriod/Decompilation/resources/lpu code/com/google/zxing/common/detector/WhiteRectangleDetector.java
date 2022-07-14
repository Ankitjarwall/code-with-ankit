package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

public final class WhiteRectangleDetector
{
  private static final int CORR = 1;
  private static final int INIT_SIZE = 10;
  private final int downInit;
  private final int height;
  private final BitMatrix image;
  private final int leftInit;
  private final int rightInit;
  private final int upInit;
  private final int width;
  
  public WhiteRectangleDetector(BitMatrix paramBitMatrix)
    throws NotFoundException
  {
    this(paramBitMatrix, 10, paramBitMatrix.getWidth() / 2, paramBitMatrix.getHeight() / 2);
  }
  
  public WhiteRectangleDetector(BitMatrix paramBitMatrix, int paramInt1, int paramInt2, int paramInt3)
    throws NotFoundException
  {
    this.image = paramBitMatrix;
    this.height = paramBitMatrix.getHeight();
    this.width = paramBitMatrix.getWidth();
    paramInt1 /= 2;
    this.leftInit = (paramInt2 - paramInt1);
    this.rightInit = (paramInt2 + paramInt1);
    this.upInit = (paramInt3 - paramInt1);
    this.downInit = (paramInt3 + paramInt1);
    if ((this.upInit < 0) || (this.leftInit < 0) || (this.downInit >= this.height) || (this.rightInit >= this.width)) {
      throw NotFoundException.getNotFoundInstance();
    }
  }
  
  private ResultPoint[] centerEdges(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4)
  {
    float f1 = paramResultPoint1.getX();
    float f2 = paramResultPoint1.getY();
    float f3 = paramResultPoint2.getX();
    float f4 = paramResultPoint2.getY();
    float f5 = paramResultPoint3.getX();
    float f6 = paramResultPoint3.getY();
    float f7 = paramResultPoint4.getX();
    float f8 = paramResultPoint4.getY();
    if (f1 < this.width / 2.0F) {
      return new ResultPoint[] { new ResultPoint(f7 - 1.0F, 1.0F + f8), new ResultPoint(1.0F + f3, 1.0F + f4), new ResultPoint(f5 - 1.0F, f6 - 1.0F), new ResultPoint(1.0F + f1, f2 - 1.0F) };
    }
    return new ResultPoint[] { new ResultPoint(1.0F + f7, 1.0F + f8), new ResultPoint(1.0F + f3, f4 - 1.0F), new ResultPoint(f5 - 1.0F, 1.0F + f6), new ResultPoint(f1 - 1.0F, f2 - 1.0F) };
  }
  
  private boolean containsBlackPoint(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    if (paramBoolean) {
      while (paramInt1 <= paramInt2)
      {
        if (this.image.get(paramInt1, paramInt3)) {
          return true;
        }
        paramInt1 += 1;
      }
    }
    for (;;)
    {
      if (paramInt1 > paramInt2) {
        break label55;
      }
      if (this.image.get(paramInt3, paramInt1)) {
        break;
      }
      paramInt1 += 1;
    }
    label55:
    return false;
  }
  
  private ResultPoint getBlackPointOnSegment(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    int j = MathUtils.round(MathUtils.distance(paramFloat1, paramFloat2, paramFloat3, paramFloat4));
    paramFloat3 = (paramFloat3 - paramFloat1) / j;
    paramFloat4 = (paramFloat4 - paramFloat2) / j;
    int i = 0;
    while (i < j)
    {
      int k = MathUtils.round(i * paramFloat3 + paramFloat1);
      int m = MathUtils.round(i * paramFloat4 + paramFloat2);
      if (this.image.get(k, m)) {
        return new ResultPoint(k, m);
      }
      i += 1;
    }
    return null;
  }
  
  public ResultPoint[] detect()
    throws NotFoundException
  {
    int i1 = this.leftInit;
    int i3 = this.rightInit;
    int n = this.upInit;
    int i2 = this.downInit;
    int i15 = 0;
    int i13 = 1;
    int i5 = 0;
    int i11 = 0;
    int i10 = 0;
    int i7 = 0;
    int i6 = 0;
    int i4;
    int k;
    int j;
    int m;
    ResultPoint localResultPoint1;
    for (;;)
    {
      i4 = i2;
      k = i1;
      j = i3;
      i = i15;
      m = n;
      int i16;
      int i8;
      boolean bool;
      if (i13 != 0)
      {
        k = 0;
        i16 = 1;
        j = i3;
        i8 = i11;
        while (((i16 != 0) || (i8 == 0)) && (j < this.width))
        {
          bool = containsBlackPoint(n, i2, j, false);
          if (bool)
          {
            j += 1;
            k = 1;
            i8 = 1;
            i16 = bool;
          }
          else
          {
            i16 = bool;
            if (i8 == 0)
            {
              j += 1;
              i16 = bool;
            }
          }
        }
        if (j < this.width) {
          break label228;
        }
        i = 1;
        m = n;
        k = i1;
        i4 = i2;
      }
      label228:
      int i9;
      int i12;
      int i14;
      for (;;)
      {
        if ((i != 0) || (i5 == 0)) {
          break label795;
        }
        n = j - k;
        localResultPoint1 = null;
        i = 1;
        while ((localResultPoint1 == null) && (i < n))
        {
          localResultPoint1 = getBlackPointOnSegment(k, i4 - i, k + i, i4);
          i += 1;
        }
        i16 = 1;
        i = i2;
        i9 = i10;
        m = k;
        while (((i16 != 0) || (i9 == 0)) && (i < this.height))
        {
          bool = containsBlackPoint(i1, j, i, true);
          if (bool)
          {
            i += 1;
            m = 1;
            i9 = 1;
            i16 = bool;
          }
          else
          {
            i16 = bool;
            if (i9 == 0)
            {
              i += 1;
              i16 = bool;
            }
          }
        }
        if (i >= this.height)
        {
          m = 1;
          i4 = i;
          k = i1;
          i = m;
          m = n;
        }
        else
        {
          i16 = 1;
          k = i1;
          i12 = i7;
          i4 = m;
          while (((i16 != 0) || (i12 == 0)) && (k >= 0))
          {
            bool = containsBlackPoint(n, i, k, false);
            if (bool)
            {
              k -= 1;
              i4 = 1;
              i12 = 1;
              i16 = bool;
            }
            else
            {
              i16 = bool;
              if (i12 == 0)
              {
                k -= 1;
                i16 = bool;
              }
            }
          }
          if (k < 0)
          {
            m = 1;
            i4 = i;
            i = m;
            m = n;
          }
          else
          {
            i16 = 1;
            m = n;
            i14 = i6;
            while (((i16 != 0) || (i14 == 0)) && (m >= 0))
            {
              bool = containsBlackPoint(k, j, m, true);
              if (bool)
              {
                m -= 1;
                i4 = 1;
                i14 = 1;
                i16 = bool;
              }
              else
              {
                i16 = bool;
                if (i14 == 0)
                {
                  m -= 1;
                  i16 = bool;
                }
              }
            }
            if (m >= 0) {
              break;
            }
            n = 1;
            i4 = i;
            i = n;
          }
        }
      }
      i13 = i4;
      i10 = i9;
      i7 = i12;
      i11 = i8;
      i6 = i14;
      i2 = i;
      i1 = k;
      i3 = j;
      n = m;
      if (i4 != 0)
      {
        i5 = 1;
        i13 = i4;
        i10 = i9;
        i7 = i12;
        i11 = i8;
        i6 = i14;
        i2 = i;
        i1 = k;
        i3 = j;
        n = m;
      }
    }
    if (localResultPoint1 == null) {
      throw NotFoundException.getNotFoundInstance();
    }
    ResultPoint localResultPoint2 = null;
    int i = 1;
    while ((localResultPoint2 == null) && (i < n))
    {
      localResultPoint2 = getBlackPointOnSegment(k, m + i, k + i, m);
      i += 1;
    }
    if (localResultPoint2 == null) {
      throw NotFoundException.getNotFoundInstance();
    }
    ResultPoint localResultPoint3 = null;
    i = 1;
    while ((localResultPoint3 == null) && (i < n))
    {
      localResultPoint3 = getBlackPointOnSegment(j, m + i, j - i, m);
      i += 1;
    }
    if (localResultPoint3 == null) {
      throw NotFoundException.getNotFoundInstance();
    }
    ResultPoint localResultPoint4 = null;
    i = 1;
    while ((localResultPoint4 == null) && (i < n))
    {
      localResultPoint4 = getBlackPointOnSegment(j, i4 - i, j - i, i4);
      i += 1;
    }
    if (localResultPoint4 == null) {
      throw NotFoundException.getNotFoundInstance();
    }
    return centerEdges(localResultPoint4, localResultPoint1, localResultPoint3, localResultPoint2);
    label795:
    throw NotFoundException.getNotFoundInstance();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\common\detector\WhiteRectangleDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */