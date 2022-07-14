package com.google.zxing.datamatrix.decoder;

final class DataBlock
{
  private final byte[] codewords;
  private final int numDataCodewords;
  
  private DataBlock(int paramInt, byte[] paramArrayOfByte)
  {
    this.numDataCodewords = paramInt;
    this.codewords = paramArrayOfByte;
  }
  
  static DataBlock[] getDataBlocks(byte[] paramArrayOfByte, Version paramVersion)
  {
    Version.ECBlocks localECBlocks = paramVersion.getECBlocks();
    int j = 0;
    Version.ECB[] arrayOfECB = localECBlocks.getECBlocks();
    int k = arrayOfECB.length;
    int i = 0;
    while (i < k)
    {
      j += arrayOfECB[i].getCount();
      i += 1;
    }
    DataBlock[] arrayOfDataBlock = new DataBlock[j];
    j = 0;
    int m = arrayOfECB.length;
    i = 0;
    int n;
    while (i < m)
    {
      Version.ECB localECB = arrayOfECB[i];
      k = 0;
      while (k < localECB.getCount())
      {
        n = localECB.getDataCodewords();
        arrayOfDataBlock[j] = new DataBlock(n, new byte[localECBlocks.getECCodewords() + n]);
        k += 1;
        j += 1;
      }
      i += 1;
    }
    int i1 = arrayOfDataBlock[0].codewords.length - localECBlocks.getECCodewords();
    i = 0;
    k = 0;
    while (k < i1 - 1)
    {
      m = 0;
      while (m < j)
      {
        arrayOfDataBlock[m].codewords[k] = paramArrayOfByte[i];
        m += 1;
        i += 1;
      }
      k += 1;
    }
    if (paramVersion.getVersionNumber() == 24)
    {
      n = 1;
      if (n == 0) {
        break label276;
      }
    }
    label276:
    for (k = 8;; k = j)
    {
      m = 0;
      while (m < k)
      {
        arrayOfDataBlock[m].codewords[(i1 - 1)] = paramArrayOfByte[i];
        m += 1;
        i += 1;
      }
      n = 0;
      break;
    }
    int i3 = arrayOfDataBlock[0].codewords.length;
    m = i1;
    k = i;
    i = m;
    while (i < i3)
    {
      m = 0;
      if (m < j)
      {
        if (n != 0)
        {
          i1 = (m + 8) % j;
          label331:
          if ((n == 0) || (i1 <= 7)) {
            break label385;
          }
        }
        label385:
        for (int i2 = i - 1;; i2 = i)
        {
          arrayOfDataBlock[i1].codewords[i2] = paramArrayOfByte[k];
          m += 1;
          k += 1;
          break;
          i1 = m;
          break label331;
        }
      }
      i += 1;
    }
    if (k != paramArrayOfByte.length) {
      throw new IllegalArgumentException();
    }
    return arrayOfDataBlock;
  }
  
  byte[] getCodewords()
  {
    return this.codewords;
  }
  
  int getNumDataCodewords()
  {
    return this.numDataCodewords;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\datamatrix\decoder\DataBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */