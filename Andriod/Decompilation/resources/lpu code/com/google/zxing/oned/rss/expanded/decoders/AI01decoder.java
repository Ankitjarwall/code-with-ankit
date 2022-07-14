package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

abstract class AI01decoder
  extends AbstractExpandedDecoder
{
  static final int GTIN_SIZE = 40;
  
  AI01decoder(BitArray paramBitArray)
  {
    super(paramBitArray);
  }
  
  private static void appendCheckDigit(StringBuilder paramStringBuilder, int paramInt)
  {
    int j = 0;
    int i = 0;
    while (i < 13)
    {
      int m = paramStringBuilder.charAt(i + paramInt) - '0';
      int k = m;
      if ((i & 0x1) == 0) {
        k = m * 3;
      }
      j += k;
      i += 1;
    }
    i = 10 - j % 10;
    paramInt = i;
    if (i == 10) {
      paramInt = 0;
    }
    paramStringBuilder.append(paramInt);
  }
  
  final void encodeCompressedGtin(StringBuilder paramStringBuilder, int paramInt)
  {
    paramStringBuilder.append("(01)");
    int i = paramStringBuilder.length();
    paramStringBuilder.append('9');
    encodeCompressedGtinWithoutAI(paramStringBuilder, paramInt, i);
  }
  
  final void encodeCompressedGtinWithoutAI(StringBuilder paramStringBuilder, int paramInt1, int paramInt2)
  {
    int i = 0;
    while (i < 4)
    {
      int j = getGeneralDecoder().extractNumericValueFromBitArray(i * 10 + paramInt1, 10);
      if (j / 100 == 0) {
        paramStringBuilder.append('0');
      }
      if (j / 10 == 0) {
        paramStringBuilder.append('0');
      }
      paramStringBuilder.append(j);
      i += 1;
    }
    appendCheckDigit(paramStringBuilder, paramInt2);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\rss\expanded\decoders\AI01decoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */