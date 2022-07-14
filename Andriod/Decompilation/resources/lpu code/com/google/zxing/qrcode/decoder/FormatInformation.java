package com.google.zxing.qrcode.decoder;

final class FormatInformation
{
  private static final int[][] FORMAT_INFO_DECODE_LOOKUP;
  private static final int FORMAT_INFO_MASK_QR = 21522;
  private final byte dataMask;
  private final ErrorCorrectionLevel errorCorrectionLevel;
  
  static
  {
    int[] arrayOfInt1 = { 23371, 3 };
    int[] arrayOfInt2 = { 26159, 12 };
    int[] arrayOfInt3 = { 16177, 26 };
    FORMAT_INFO_DECODE_LOOKUP = new int[][] { { 21522, 0 }, { 20773, 1 }, { 24188, 2 }, arrayOfInt1, { 17913, 4 }, { 16590, 5 }, { 20375, 6 }, { 19104, 7 }, { 30660, 8 }, { 29427, 9 }, { 32170, 10 }, { 30877, 11 }, arrayOfInt2, { 25368, 13 }, { 27713, 14 }, { 26998, 15 }, { 5769, 16 }, { 5054, 17 }, { 7399, 18 }, { 6608, 19 }, { 1890, 20 }, { 597, 21 }, { 3340, 22 }, { 2107, 23 }, { 13663, 24 }, { 12392, 25 }, arrayOfInt3, { 14854, 27 }, { 9396, 28 }, { 8579, 29 }, { 11994, 30 }, { 11245, 31 } };
  }
  
  private FormatInformation(int paramInt)
  {
    this.errorCorrectionLevel = ErrorCorrectionLevel.forBits(paramInt >> 3 & 0x3);
    this.dataMask = ((byte)(paramInt & 0x7));
  }
  
  static FormatInformation decodeFormatInformation(int paramInt1, int paramInt2)
  {
    FormatInformation localFormatInformation = doDecodeFormatInformation(paramInt1, paramInt2);
    if (localFormatInformation != null) {
      return localFormatInformation;
    }
    return doDecodeFormatInformation(paramInt1 ^ 0x5412, paramInt2 ^ 0x5412);
  }
  
  private static FormatInformation doDecodeFormatInformation(int paramInt1, int paramInt2)
  {
    int m = Integer.MAX_VALUE;
    int j = 0;
    int[][] arrayOfInt = FORMAT_INFO_DECODE_LOOKUP;
    int i2 = arrayOfInt.length;
    int n = 0;
    while (n < i2)
    {
      int[] arrayOfInt1 = arrayOfInt[n];
      int i1 = arrayOfInt1[0];
      if ((i1 == paramInt1) || (i1 == paramInt2)) {
        return new FormatInformation(arrayOfInt1[1]);
      }
      int k = numBitsDiffering(paramInt1, i1);
      int i = m;
      if (k < m)
      {
        j = arrayOfInt1[1];
        i = k;
      }
      m = i;
      k = j;
      if (paramInt1 != paramInt2)
      {
        i1 = numBitsDiffering(paramInt2, i1);
        m = i;
        k = j;
        if (i1 < i)
        {
          k = arrayOfInt1[1];
          m = i1;
        }
      }
      n += 1;
      j = k;
    }
    if (m <= 3) {
      return new FormatInformation(j);
    }
    return null;
  }
  
  static int numBitsDiffering(int paramInt1, int paramInt2)
  {
    return Integer.bitCount(paramInt1 ^ paramInt2);
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof FormatInformation)) {}
    do
    {
      return false;
      paramObject = (FormatInformation)paramObject;
    } while ((this.errorCorrectionLevel != ((FormatInformation)paramObject).errorCorrectionLevel) || (this.dataMask != ((FormatInformation)paramObject).dataMask));
    return true;
  }
  
  byte getDataMask()
  {
    return this.dataMask;
  }
  
  ErrorCorrectionLevel getErrorCorrectionLevel()
  {
    return this.errorCorrectionLevel;
  }
  
  public int hashCode()
  {
    return this.errorCorrectionLevel.ordinal() << 3 | this.dataMask;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\qrcode\decoder\FormatInformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */