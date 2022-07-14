package com.google.zxing.qrcode.decoder;

import com.google.zxing.common.BitMatrix;

 enum DataMask
{
  DATA_MASK_000,  DATA_MASK_001,  DATA_MASK_010,  DATA_MASK_011,  DATA_MASK_100,  DATA_MASK_101,  DATA_MASK_110,  DATA_MASK_111;
  
  private DataMask() {}
  
  abstract boolean isMasked(int paramInt1, int paramInt2);
  
  final void unmaskBitMatrix(BitMatrix paramBitMatrix, int paramInt)
  {
    int i = 0;
    while (i < paramInt)
    {
      int j = 0;
      while (j < paramInt)
      {
        if (isMasked(i, j)) {
          paramBitMatrix.flip(j, i);
        }
        j += 1;
      }
      i += 1;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\qrcode\decoder\DataMask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */