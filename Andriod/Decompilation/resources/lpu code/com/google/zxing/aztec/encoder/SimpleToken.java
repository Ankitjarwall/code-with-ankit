package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;

final class SimpleToken
  extends Token
{
  private final short bitCount;
  private final short value;
  
  SimpleToken(Token paramToken, int paramInt1, int paramInt2)
  {
    super(paramToken);
    this.value = ((short)paramInt1);
    this.bitCount = ((short)paramInt2);
  }
  
  void appendTo(BitArray paramBitArray, byte[] paramArrayOfByte)
  {
    paramBitArray.appendBits(this.value, this.bitCount);
  }
  
  public String toString()
  {
    int i = this.value;
    int j = this.bitCount;
    int k = this.bitCount;
    return '<' + Integer.toBinaryString(1 << this.bitCount | i & (1 << j) - 1 | 1 << k).substring(1) + '>';
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\aztec\encoder\SimpleToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */