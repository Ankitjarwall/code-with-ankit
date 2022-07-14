package com.google.zxing.oned.rss;

public final class RSSUtils
{
  private static int combins(int paramInt1, int paramInt2)
  {
    int i;
    if (paramInt1 - paramInt2 > paramInt2) {
      i = paramInt2;
    }
    int m;
    int n;
    for (int j = paramInt1 - paramInt2;; j = paramInt2)
    {
      paramInt2 = 1;
      m = 1;
      int k = paramInt1;
      for (paramInt1 = m;; paramInt1 = m)
      {
        m = paramInt1;
        n = paramInt2;
        if (k <= j) {
          break;
        }
        n = paramInt2 * k;
        m = paramInt1;
        paramInt2 = n;
        if (paramInt1 <= i)
        {
          paramInt2 = n / paramInt1;
          m = paramInt1 + 1;
        }
        k -= 1;
      }
      i = paramInt1 - paramInt2;
    }
    while (m <= i)
    {
      n /= m;
      m += 1;
    }
    return n;
  }
  
  public static int getRSSvalue(int[] paramArrayOfInt, int paramInt, boolean paramBoolean)
  {
    int i = 0;
    int k = paramArrayOfInt.length;
    int j = 0;
    while (j < k)
    {
      i += paramArrayOfInt[j];
      j += 1;
    }
    int i1 = 0;
    j = 0;
    int i4 = paramArrayOfInt.length;
    int m = 0;
    int n = i;
    while (m < i4 - 1)
    {
      int i2 = 1;
      i = j | 1 << m;
      if (i2 < paramArrayOfInt[m])
      {
        k = combins(n - i2 - 1, i4 - m - 2);
        j = k;
        if (paramBoolean)
        {
          j = k;
          if (i == 0)
          {
            j = k;
            if (n - i2 - (i4 - m - 1) >= i4 - m - 1) {
              j = k - combins(n - i2 - (i4 - m), i4 - m - 2);
            }
          }
        }
        if (i4 - m - 1 > 1)
        {
          int i3 = 0;
          k = n - i2 - (i4 - m - 2);
          while (k > paramInt)
          {
            i3 += combins(n - i2 - k - 1, i4 - m - 3);
            k -= 1;
          }
          k = j - (i4 - 1 - m) * i3;
        }
        for (;;)
        {
          i1 += k;
          i2 += 1;
          i &= (1 << m ^ 0xFFFFFFFF);
          break;
          k = j;
          if (n - i2 > paramInt) {
            k = j - 1;
          }
        }
      }
      n -= i2;
      m += 1;
      j = i;
    }
    return i1;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\rss\RSSUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */