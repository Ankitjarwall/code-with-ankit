package com.google.zxing.common.detector;

public final class MathUtils
{
  public static float distance(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramFloat1 -= paramFloat3;
    paramFloat2 -= paramFloat4;
    return (float)Math.sqrt(paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2);
  }
  
  public static float distance(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt1 -= paramInt3;
    paramInt2 -= paramInt4;
    return (float)Math.sqrt(paramInt1 * paramInt1 + paramInt2 * paramInt2);
  }
  
  public static int round(float paramFloat)
  {
    if (paramFloat < 0.0F) {}
    for (float f = -0.5F;; f = 0.5F) {
      return (int)(f + paramFloat);
    }
  }
  
  public static int sum(int[] paramArrayOfInt)
  {
    int j = 0;
    int k = paramArrayOfInt.length;
    int i = 0;
    while (i < k)
    {
      j += paramArrayOfInt[i];
      i += 1;
    }
    return j;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\common\detector\MathUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */