package com.google.zxing.qrcode.decoder;

final class DataBlock
{
  private final byte[] codewords;
  private final int numDataCodewords;
  
  private DataBlock(int paramInt, byte[] paramArrayOfByte)
  {
    this.numDataCodewords = paramInt;
    this.codewords = paramArrayOfByte;
  }
  
  static DataBlock[] getDataBlocks(byte[] paramArrayOfByte, Version paramVersion, ErrorCorrectionLevel paramErrorCorrectionLevel)
  {
    if (paramArrayOfByte.length != paramVersion.getTotalCodewords()) {
      throw new IllegalArgumentException();
    }
    paramVersion = paramVersion.getECBlocksForLevel(paramErrorCorrectionLevel);
    int j = 0;
    paramErrorCorrectionLevel = paramVersion.getECBlocks();
    int k = paramErrorCorrectionLevel.length;
    int i = 0;
    while (i < k)
    {
      j += paramErrorCorrectionLevel[i].getCount();
      i += 1;
    }
    DataBlock[] arrayOfDataBlock = new DataBlock[j];
    k = 0;
    int m = paramErrorCorrectionLevel.length;
    i = 0;
    int n;
    while (i < m)
    {
      Object localObject = paramErrorCorrectionLevel[i];
      j = 0;
      while (j < ((Version.ECB)localObject).getCount())
      {
        n = ((Version.ECB)localObject).getDataCodewords();
        arrayOfDataBlock[k] = new DataBlock(n, new byte[paramVersion.getECCodewordsPerBlock() + n]);
        j += 1;
        k += 1;
      }
      i += 1;
    }
    j = arrayOfDataBlock[0].codewords.length;
    i = arrayOfDataBlock.length - 1;
    int i1;
    if ((i < 0) || (arrayOfDataBlock[i].codewords.length == j))
    {
      i1 = i + 1;
      n = j - paramVersion.getECCodewordsPerBlock();
      i = 0;
      j = 0;
    }
    for (;;)
    {
      if (j >= n) {
        break label265;
      }
      m = 0;
      for (;;)
      {
        if (m < k)
        {
          arrayOfDataBlock[m].codewords[j] = paramArrayOfByte[i];
          m += 1;
          i += 1;
          continue;
          i -= 1;
          break;
        }
      }
      j += 1;
    }
    label265:
    j = i1;
    while (j < k)
    {
      arrayOfDataBlock[j].codewords[n] = paramArrayOfByte[i];
      j += 1;
      i += 1;
    }
    int i2 = arrayOfDataBlock[0].codewords.length;
    m = n;
    j = i;
    i = m;
    while (i < i2)
    {
      m = 0;
      if (m < k)
      {
        if (m < i1) {}
        for (n = i;; n = i + 1)
        {
          arrayOfDataBlock[m].codewords[n] = paramArrayOfByte[j];
          m += 1;
          j += 1;
          break;
        }
      }
      i += 1;
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


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\qrcode\decoder\DataBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */