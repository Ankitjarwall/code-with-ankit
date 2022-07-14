package com.google.zxing.common;

public final class BitSource
{
  private int bitOffset;
  private int byteOffset;
  private final byte[] bytes;
  
  public BitSource(byte[] paramArrayOfByte)
  {
    this.bytes = paramArrayOfByte;
  }
  
  public int available()
  {
    return (this.bytes.length - this.byteOffset) * 8 - this.bitOffset;
  }
  
  public int getBitOffset()
  {
    return this.bitOffset;
  }
  
  public int getByteOffset()
  {
    return this.byteOffset;
  }
  
  public int readBits(int paramInt)
  {
    if ((paramInt < 1) || (paramInt > 32) || (paramInt > available())) {
      throw new IllegalArgumentException(String.valueOf(paramInt));
    }
    int i = 0;
    int j = paramInt;
    if (this.bitOffset > 0)
    {
      j = 8 - this.bitOffset;
      if (paramInt >= j) {
        break label182;
      }
    }
    label182:
    for (i = paramInt;; i = j)
    {
      j -= i;
      int k = (this.bytes[this.byteOffset] & 255 >> 8 - i << j) >> j;
      paramInt -= i;
      this.bitOffset += i;
      i = k;
      j = paramInt;
      if (this.bitOffset == 8)
      {
        this.bitOffset = 0;
        this.byteOffset += 1;
        j = paramInt;
        i = k;
      }
      paramInt = i;
      if (j <= 0) {
        return paramInt;
      }
      while (j >= 8)
      {
        i = i << 8 | this.bytes[this.byteOffset] & 0xFF;
        this.byteOffset += 1;
        j -= 8;
      }
    }
    paramInt = i;
    if (j > 0)
    {
      paramInt = 8 - j;
      paramInt = i << j | (this.bytes[this.byteOffset] & 255 >> paramInt << paramInt) >> paramInt;
      this.bitOffset += j;
    }
    return paramInt;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\common\BitSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */