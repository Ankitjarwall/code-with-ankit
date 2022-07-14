package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

final class BitMatrixParser
{
  private final BitMatrix mappingBitMatrix;
  private final BitMatrix readMappingMatrix;
  private final Version version;
  
  BitMatrixParser(BitMatrix paramBitMatrix)
    throws FormatException
  {
    int i = paramBitMatrix.getHeight();
    if ((i < 8) || (i > 144) || ((i & 0x1) != 0)) {
      throw FormatException.getFormatInstance();
    }
    this.version = readVersion(paramBitMatrix);
    this.mappingBitMatrix = extractDataRegion(paramBitMatrix);
    this.readMappingMatrix = new BitMatrix(this.mappingBitMatrix.getWidth(), this.mappingBitMatrix.getHeight());
  }
  
  private BitMatrix extractDataRegion(BitMatrix paramBitMatrix)
  {
    int j = this.version.getSymbolSizeRows();
    int i = this.version.getSymbolSizeColumns();
    if (paramBitMatrix.getHeight() != j) {
      throw new IllegalArgumentException("Dimension of bitMarix must match the version size");
    }
    int n = this.version.getDataRegionSizeRows();
    int i1 = this.version.getDataRegionSizeColumns();
    int i2 = j / n;
    int i3 = i / i1;
    BitMatrix localBitMatrix = new BitMatrix(i3 * i1, i2 * n);
    i = 0;
    while (i < i2)
    {
      j = 0;
      while (j < i3)
      {
        int k = 0;
        while (k < n)
        {
          int m = 0;
          while (m < i1)
          {
            if (paramBitMatrix.get((i1 + 2) * j + 1 + m, (n + 2) * i + 1 + k)) {
              localBitMatrix.set(j * i1 + m, i * n + k);
            }
            m += 1;
          }
          k += 1;
        }
        j += 1;
      }
      i += 1;
    }
    return localBitMatrix;
  }
  
  private int readCorner1(int paramInt1, int paramInt2)
  {
    int i = 0;
    if (readModule(paramInt1 - 1, 0, paramInt1, paramInt2)) {
      i = 0x0 | 0x1;
    }
    int j = i << 1;
    i = j;
    if (readModule(paramInt1 - 1, 1, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(paramInt1 - 1, 2, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(0, paramInt2 - 2, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(0, paramInt2 - 1, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(1, paramInt2 - 1, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(2, paramInt2 - 1, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(3, paramInt2 - 1, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    return i;
  }
  
  private int readCorner2(int paramInt1, int paramInt2)
  {
    int i = 0;
    if (readModule(paramInt1 - 3, 0, paramInt1, paramInt2)) {
      i = 0x0 | 0x1;
    }
    int j = i << 1;
    i = j;
    if (readModule(paramInt1 - 2, 0, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(paramInt1 - 1, 0, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(0, paramInt2 - 4, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(0, paramInt2 - 3, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(0, paramInt2 - 2, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(0, paramInt2 - 1, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(1, paramInt2 - 1, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    return i;
  }
  
  private int readCorner3(int paramInt1, int paramInt2)
  {
    int i = 0;
    if (readModule(paramInt1 - 1, 0, paramInt1, paramInt2)) {
      i = 0x0 | 0x1;
    }
    int j = i << 1;
    i = j;
    if (readModule(paramInt1 - 1, paramInt2 - 1, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(0, paramInt2 - 3, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(0, paramInt2 - 2, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(0, paramInt2 - 1, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(1, paramInt2 - 3, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(1, paramInt2 - 2, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(1, paramInt2 - 1, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    return i;
  }
  
  private int readCorner4(int paramInt1, int paramInt2)
  {
    int i = 0;
    if (readModule(paramInt1 - 3, 0, paramInt1, paramInt2)) {
      i = 0x0 | 0x1;
    }
    int j = i << 1;
    i = j;
    if (readModule(paramInt1 - 2, 0, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(paramInt1 - 1, 0, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(0, paramInt2 - 2, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(0, paramInt2 - 1, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(1, paramInt2 - 1, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(2, paramInt2 - 1, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(3, paramInt2 - 1, paramInt1, paramInt2)) {
      i = j | 0x1;
    }
    return i;
  }
  
  private boolean readModule(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = paramInt1;
    int j = paramInt2;
    if (paramInt1 < 0)
    {
      i = paramInt1 + paramInt3;
      j = paramInt2 + (4 - (paramInt3 + 4 & 0x7));
    }
    paramInt2 = i;
    paramInt1 = j;
    if (j < 0)
    {
      paramInt1 = j + paramInt4;
      paramInt2 = i + (4 - (paramInt4 + 4 & 0x7));
    }
    this.readMappingMatrix.set(paramInt1, paramInt2);
    return this.mappingBitMatrix.get(paramInt1, paramInt2);
  }
  
  private int readUtah(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = 0;
    if (readModule(paramInt1 - 2, paramInt2 - 2, paramInt3, paramInt4)) {
      i = 0x0 | 0x1;
    }
    int j = i << 1;
    i = j;
    if (readModule(paramInt1 - 2, paramInt2 - 1, paramInt3, paramInt4)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(paramInt1 - 1, paramInt2 - 2, paramInt3, paramInt4)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(paramInt1 - 1, paramInt2 - 1, paramInt3, paramInt4)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(paramInt1 - 1, paramInt2, paramInt3, paramInt4)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(paramInt1, paramInt2 - 2, paramInt3, paramInt4)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(paramInt1, paramInt2 - 1, paramInt3, paramInt4)) {
      i = j | 0x1;
    }
    j = i << 1;
    i = j;
    if (readModule(paramInt1, paramInt2, paramInt3, paramInt4)) {
      i = j | 0x1;
    }
    return i;
  }
  
  private static Version readVersion(BitMatrix paramBitMatrix)
    throws FormatException
  {
    return Version.getVersionForDimensions(paramBitMatrix.getHeight(), paramBitMatrix.getWidth());
  }
  
  Version getVersion()
  {
    return this.version;
  }
  
  byte[] readCodewords()
    throws FormatException
  {
    byte[] arrayOfByte = new byte[this.version.getTotalCodewords()];
    int j = 4;
    int i = 0;
    int i6 = this.mappingBitMatrix.getHeight();
    int i7 = this.mappingBitMatrix.getWidth();
    int i3 = 0;
    int i2 = 0;
    int i1 = 0;
    int n = 0;
    int k = 0;
    int m;
    if ((j == i6) && (i == 0) && (i3 == 0))
    {
      m = k + 1;
      arrayOfByte[k] = ((byte)readCorner1(i6, i7));
      j -= 2;
      i += 2;
      i3 = 1;
      k = m;
    }
    label94:
    int i5;
    int i4;
    for (;;)
    {
      if ((j >= i6) && (i >= i7)) {
        if (k != this.version.getTotalCodewords())
        {
          throw FormatException.getFormatInstance();
          if ((j == i6 - 2) && (i == 0) && ((i7 & 0x3) != 0) && (i2 == 0))
          {
            m = k + 1;
            arrayOfByte[k] = ((byte)readCorner2(i6, i7));
            j -= 2;
            i += 2;
            i2 = 1;
            k = m;
            continue;
          }
          if ((j == i6 + 4) && (i == 2) && ((i7 & 0x7) == 0) && (i1 == 0))
          {
            m = k + 1;
            arrayOfByte[k] = ((byte)readCorner3(i6, i7));
            j -= 2;
            i += 2;
            i1 = 1;
            k = m;
            continue;
          }
          i5 = i;
          m = k;
          i4 = j;
          if (j == i6 - 2)
          {
            i5 = i;
            m = k;
            i4 = j;
            if (i == 0)
            {
              i5 = i;
              m = k;
              i4 = j;
              if ((i7 & 0x7) == 4)
              {
                i5 = i;
                m = k;
                i4 = j;
                if (n == 0)
                {
                  m = k + 1;
                  arrayOfByte[k] = ((byte)readCorner4(i6, i7));
                  j -= 2;
                  i += 2;
                  n = 1;
                  k = m;
                  continue;
                  label338:
                  m = i;
                }
              }
            }
          }
          if ((i4 >= i6) || (i5 < 0) || (this.readMappingMatrix.get(i5, i4))) {
            break label524;
          }
          i = m + 1;
          arrayOfByte[m] = ((byte)readUtah(i4, i5, i6, i7));
        }
      }
    }
    for (;;)
    {
      i4 -= 2;
      i5 += 2;
      if ((i4 >= 0) && (i5 < i7)) {
        break label338;
      }
      m = i4 + 1;
      j = i5 + 3;
      label425:
      if ((m >= 0) && (j < i7) && (!this.readMappingMatrix.get(j, m)))
      {
        k = i + 1;
        arrayOfByte[i] = ((byte)readUtah(m, j, i6, i7));
      }
      for (;;)
      {
        m += 2;
        i4 = j - 2;
        if ((m >= i6) || (i4 < 0))
        {
          j = m + 3;
          i = i4 + 1;
          break label94;
          return arrayOfByte;
          break;
        }
        i = k;
        j = i4;
        break label425;
        k = i;
      }
      label524:
      i = m;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\datamatrix\decoder\BitMatrixParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */