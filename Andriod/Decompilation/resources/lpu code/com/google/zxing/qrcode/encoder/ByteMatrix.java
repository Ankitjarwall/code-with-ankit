package com.google.zxing.qrcode.encoder;

import java.lang.reflect.Array;

public final class ByteMatrix
{
  private final byte[][] bytes;
  private final int height;
  private final int width;
  
  public ByteMatrix(int paramInt1, int paramInt2)
  {
    this.bytes = ((byte[][])Array.newInstance(Byte.TYPE, new int[] { paramInt2, paramInt1 }));
    this.width = paramInt1;
    this.height = paramInt2;
  }
  
  public void clear(byte paramByte)
  {
    int i = 0;
    while (i < this.height)
    {
      int j = 0;
      while (j < this.width)
      {
        this.bytes[i][j] = paramByte;
        j += 1;
      }
      i += 1;
    }
  }
  
  public byte get(int paramInt1, int paramInt2)
  {
    return this.bytes[paramInt2][paramInt1];
  }
  
  public byte[][] getArray()
  {
    return this.bytes;
  }
  
  public int getHeight()
  {
    return this.height;
  }
  
  public int getWidth()
  {
    return this.width;
  }
  
  public void set(int paramInt1, int paramInt2, byte paramByte)
  {
    this.bytes[paramInt2][paramInt1] = paramByte;
  }
  
  public void set(int paramInt1, int paramInt2, int paramInt3)
  {
    this.bytes[paramInt2][paramInt1] = ((byte)paramInt3);
  }
  
  public void set(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    byte[] arrayOfByte = this.bytes[paramInt2];
    if (paramBoolean) {}
    for (paramInt2 = 1;; paramInt2 = 0)
    {
      arrayOfByte[paramInt1] = ((byte)paramInt2);
      return;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(this.width * 2 * this.height + 2);
    int i = 0;
    while (i < this.height)
    {
      int j = 0;
      if (j < this.width)
      {
        switch (this.bytes[i][j])
        {
        default: 
          localStringBuilder.append("  ");
        }
        for (;;)
        {
          j += 1;
          break;
          localStringBuilder.append(" 0");
          continue;
          localStringBuilder.append(" 1");
        }
      }
      localStringBuilder.append('\n');
      i += 1;
    }
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\qrcode\encoder\ByteMatrix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */