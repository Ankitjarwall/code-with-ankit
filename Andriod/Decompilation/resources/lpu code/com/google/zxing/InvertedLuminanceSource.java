package com.google.zxing;

public final class InvertedLuminanceSource
  extends LuminanceSource
{
  private final LuminanceSource delegate;
  
  public InvertedLuminanceSource(LuminanceSource paramLuminanceSource)
  {
    super(paramLuminanceSource.getWidth(), paramLuminanceSource.getHeight());
    this.delegate = paramLuminanceSource;
  }
  
  public LuminanceSource crop(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return new InvertedLuminanceSource(this.delegate.crop(paramInt1, paramInt2, paramInt3, paramInt4));
  }
  
  public byte[] getMatrix()
  {
    byte[] arrayOfByte1 = this.delegate.getMatrix();
    int j = getWidth() * getHeight();
    byte[] arrayOfByte2 = new byte[j];
    int i = 0;
    while (i < j)
    {
      arrayOfByte2[i] = ((byte)(255 - (arrayOfByte1[i] & 0xFF)));
      i += 1;
    }
    return arrayOfByte2;
  }
  
  public byte[] getRow(int paramInt, byte[] paramArrayOfByte)
  {
    paramArrayOfByte = this.delegate.getRow(paramInt, paramArrayOfByte);
    int i = getWidth();
    paramInt = 0;
    while (paramInt < i)
    {
      paramArrayOfByte[paramInt] = ((byte)(255 - (paramArrayOfByte[paramInt] & 0xFF)));
      paramInt += 1;
    }
    return paramArrayOfByte;
  }
  
  public LuminanceSource invert()
  {
    return this.delegate;
  }
  
  public boolean isCropSupported()
  {
    return this.delegate.isCropSupported();
  }
  
  public boolean isRotateSupported()
  {
    return this.delegate.isRotateSupported();
  }
  
  public LuminanceSource rotateCounterClockwise()
  {
    return new InvertedLuminanceSource(this.delegate.rotateCounterClockwise());
  }
  
  public LuminanceSource rotateCounterClockwise45()
  {
    return new InvertedLuminanceSource(this.delegate.rotateCounterClockwise45());
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\InvertedLuminanceSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */