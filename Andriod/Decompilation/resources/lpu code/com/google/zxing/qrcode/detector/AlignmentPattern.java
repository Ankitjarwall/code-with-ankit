package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

public final class AlignmentPattern
  extends ResultPoint
{
  private final float estimatedModuleSize;
  
  AlignmentPattern(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    super(paramFloat1, paramFloat2);
    this.estimatedModuleSize = paramFloat3;
  }
  
  boolean aboutEquals(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (Math.abs(paramFloat2 - getY()) <= paramFloat1)
    {
      bool1 = bool2;
      if (Math.abs(paramFloat3 - getX()) <= paramFloat1)
      {
        paramFloat1 = Math.abs(paramFloat1 - this.estimatedModuleSize);
        if (paramFloat1 > 1.0F)
        {
          bool1 = bool2;
          if (paramFloat1 > this.estimatedModuleSize) {}
        }
        else
        {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  AlignmentPattern combineEstimate(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return new AlignmentPattern((getX() + paramFloat2) / 2.0F, (getY() + paramFloat1) / 2.0F, (this.estimatedModuleSize + paramFloat3) / 2.0F);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\qrcode\detector\AlignmentPattern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */