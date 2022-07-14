package com.google.zxing.pdf417.encoder;

final class BarcodeRow
{
  private int currentLocation;
  private final byte[] row;
  
  BarcodeRow(int paramInt)
  {
    this.row = new byte[paramInt];
    this.currentLocation = 0;
  }
  
  private void set(int paramInt, boolean paramBoolean)
  {
    byte[] arrayOfByte = this.row;
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      arrayOfByte[paramInt] = ((byte)i);
      return;
    }
  }
  
  void addBar(boolean paramBoolean, int paramInt)
  {
    int i = 0;
    while (i < paramInt)
    {
      int j = this.currentLocation;
      this.currentLocation = (j + 1);
      set(j, paramBoolean);
      i += 1;
    }
  }
  
  byte[] getScaledRow(int paramInt)
  {
    byte[] arrayOfByte = new byte[this.row.length * paramInt];
    int i = 0;
    while (i < arrayOfByte.length)
    {
      arrayOfByte[i] = this.row[(i / paramInt)];
      i += 1;
    }
    return arrayOfByte;
  }
  
  void set(int paramInt, byte paramByte)
  {
    this.row[paramInt] = paramByte;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\pdf417\encoder\BarcodeRow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */