package com.google.zxing.datamatrix.encoder;

import com.google.zxing.Dimension;

public class SymbolInfo
{
  static final SymbolInfo[] PROD_SYMBOLS = { new SymbolInfo(false, 3, 5, 8, 8, 1), new SymbolInfo(false, 5, 7, 10, 10, 1), new SymbolInfo(true, 5, 7, 16, 6, 1), new SymbolInfo(false, 8, 10, 12, 12, 1), new SymbolInfo(true, 10, 11, 14, 6, 2), new SymbolInfo(false, 12, 12, 14, 14, 1), new SymbolInfo(true, 16, 14, 24, 10, 1), new SymbolInfo(false, 18, 14, 16, 16, 1), new SymbolInfo(false, 22, 18, 18, 18, 1), new SymbolInfo(true, 22, 18, 16, 10, 2), new SymbolInfo(false, 30, 20, 20, 20, 1), new SymbolInfo(true, 32, 24, 16, 14, 2), new SymbolInfo(false, 36, 24, 22, 22, 1), new SymbolInfo(false, 44, 28, 24, 24, 1), new SymbolInfo(true, 49, 28, 22, 14, 2), new SymbolInfo(false, 62, 36, 14, 14, 4), new SymbolInfo(false, 86, 42, 16, 16, 4), new SymbolInfo(false, 114, 48, 18, 18, 4), new SymbolInfo(false, 144, 56, 20, 20, 4), new SymbolInfo(false, 174, 68, 22, 22, 4), new SymbolInfo(false, 204, 84, 24, 24, 4, 102, 42), new SymbolInfo(false, 280, 112, 14, 14, 16, 140, 56), new SymbolInfo(false, 368, 144, 16, 16, 16, 92, 36), new SymbolInfo(false, 456, 192, 18, 18, 16, 114, 48), new SymbolInfo(false, 576, 224, 20, 20, 16, 144, 56), new SymbolInfo(false, 696, 272, 22, 22, 16, 174, 68), new SymbolInfo(false, 816, 336, 24, 24, 16, 136, 56), new SymbolInfo(false, 1050, 408, 18, 18, 36, 175, 68), new SymbolInfo(false, 1304, 496, 20, 20, 36, 163, 62), new DataMatrixSymbolInfo144() };
  private static SymbolInfo[] symbols = PROD_SYMBOLS;
  private final int dataCapacity;
  private final int dataRegions;
  private final int errorCodewords;
  public final int matrixHeight;
  public final int matrixWidth;
  private final boolean rectangular;
  private final int rsBlockData;
  private final int rsBlockError;
  
  public SymbolInfo(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    this(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt1, paramInt2);
  }
  
  SymbolInfo(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    this.rectangular = paramBoolean;
    this.dataCapacity = paramInt1;
    this.errorCodewords = paramInt2;
    this.matrixWidth = paramInt3;
    this.matrixHeight = paramInt4;
    this.dataRegions = paramInt5;
    this.rsBlockData = paramInt6;
    this.rsBlockError = paramInt7;
  }
  
  private int getHorizontalDataRegions()
  {
    switch (this.dataRegions)
    {
    default: 
      throw new IllegalStateException("Cannot handle this number of data regions");
    case 1: 
      return 1;
    case 2: 
    case 4: 
      return 2;
    case 16: 
      return 4;
    }
    return 6;
  }
  
  private int getVerticalDataRegions()
  {
    switch (this.dataRegions)
    {
    default: 
      throw new IllegalStateException("Cannot handle this number of data regions");
    case 1: 
    case 2: 
      return 1;
    case 4: 
      return 2;
    case 16: 
      return 4;
    }
    return 6;
  }
  
  public static SymbolInfo lookup(int paramInt)
  {
    return lookup(paramInt, SymbolShapeHint.FORCE_NONE, true);
  }
  
  public static SymbolInfo lookup(int paramInt, SymbolShapeHint paramSymbolShapeHint)
  {
    return lookup(paramInt, paramSymbolShapeHint, true);
  }
  
  public static SymbolInfo lookup(int paramInt, SymbolShapeHint paramSymbolShapeHint, Dimension paramDimension1, Dimension paramDimension2, boolean paramBoolean)
  {
    SymbolInfo[] arrayOfSymbolInfo = symbols;
    int j = arrayOfSymbolInfo.length;
    int i = 0;
    if (i < j)
    {
      SymbolInfo localSymbolInfo = arrayOfSymbolInfo[i];
      if ((paramSymbolShapeHint == SymbolShapeHint.FORCE_SQUARE) && (localSymbolInfo.rectangular)) {}
      while (((paramSymbolShapeHint == SymbolShapeHint.FORCE_RECTANGLE) && (!localSymbolInfo.rectangular)) || ((paramDimension1 != null) && ((localSymbolInfo.getSymbolWidth() < paramDimension1.getWidth()) || (localSymbolInfo.getSymbolHeight() < paramDimension1.getHeight()))) || ((paramDimension2 != null) && ((localSymbolInfo.getSymbolWidth() > paramDimension2.getWidth()) || (localSymbolInfo.getSymbolHeight() > paramDimension2.getHeight()))) || (paramInt > localSymbolInfo.dataCapacity))
      {
        i += 1;
        break;
      }
      return localSymbolInfo;
    }
    if (paramBoolean) {
      throw new IllegalArgumentException("Can't find a symbol arrangement that matches the message. Data codewords: " + paramInt);
    }
    return null;
  }
  
  private static SymbolInfo lookup(int paramInt, SymbolShapeHint paramSymbolShapeHint, boolean paramBoolean)
  {
    return lookup(paramInt, paramSymbolShapeHint, null, null, paramBoolean);
  }
  
  public static SymbolInfo lookup(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean1) {}
    for (SymbolShapeHint localSymbolShapeHint = SymbolShapeHint.FORCE_NONE;; localSymbolShapeHint = SymbolShapeHint.FORCE_SQUARE) {
      return lookup(paramInt, localSymbolShapeHint, paramBoolean2);
    }
  }
  
  public static void overrideSymbolSet(SymbolInfo[] paramArrayOfSymbolInfo)
  {
    symbols = paramArrayOfSymbolInfo;
  }
  
  public int getCodewordCount()
  {
    return this.dataCapacity + this.errorCodewords;
  }
  
  public final int getDataCapacity()
  {
    return this.dataCapacity;
  }
  
  public int getDataLengthForInterleavedBlock(int paramInt)
  {
    return this.rsBlockData;
  }
  
  public final int getErrorCodewords()
  {
    return this.errorCodewords;
  }
  
  public final int getErrorLengthForInterleavedBlock(int paramInt)
  {
    return this.rsBlockError;
  }
  
  public int getInterleavedBlockCount()
  {
    return this.dataCapacity / this.rsBlockData;
  }
  
  public final int getSymbolDataHeight()
  {
    return getVerticalDataRegions() * this.matrixHeight;
  }
  
  public final int getSymbolDataWidth()
  {
    return getHorizontalDataRegions() * this.matrixWidth;
  }
  
  public final int getSymbolHeight()
  {
    return getSymbolDataHeight() + getVerticalDataRegions() * 2;
  }
  
  public final int getSymbolWidth()
  {
    return getSymbolDataWidth() + getHorizontalDataRegions() * 2;
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.rectangular) {}
    for (String str = "Rectangular Symbol:";; str = "Square Symbol:") {
      return str + " data region " + this.matrixWidth + 'x' + this.matrixHeight + ", symbol size " + getSymbolWidth() + 'x' + getSymbolHeight() + ", symbol data size " + getSymbolDataWidth() + 'x' + getSymbolDataHeight() + ", codewords " + this.dataCapacity + '+' + this.errorCodewords;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\datamatrix\encoder\SymbolInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */