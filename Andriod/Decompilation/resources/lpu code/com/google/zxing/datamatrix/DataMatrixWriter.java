package com.google.zxing.datamatrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Dimension;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.DefaultPlacement;
import com.google.zxing.datamatrix.encoder.ErrorCorrection;
import com.google.zxing.datamatrix.encoder.HighLevelEncoder;
import com.google.zxing.datamatrix.encoder.SymbolInfo;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import java.util.Map;

public final class DataMatrixWriter
  implements Writer
{
  private static BitMatrix convertByteMatrixToBitMatrix(ByteMatrix paramByteMatrix)
  {
    int k = paramByteMatrix.getWidth();
    int m = paramByteMatrix.getHeight();
    BitMatrix localBitMatrix = new BitMatrix(k, m);
    localBitMatrix.clear();
    int i = 0;
    while (i < k)
    {
      int j = 0;
      while (j < m)
      {
        if (paramByteMatrix.get(i, j) == 1) {
          localBitMatrix.set(i, j);
        }
        j += 1;
      }
      i += 1;
    }
    return localBitMatrix;
  }
  
  private static BitMatrix encodeLowLevel(DefaultPlacement paramDefaultPlacement, SymbolInfo paramSymbolInfo)
  {
    int i1 = paramSymbolInfo.getSymbolDataWidth();
    int i2 = paramSymbolInfo.getSymbolDataHeight();
    ByteMatrix localByteMatrix = new ByteMatrix(paramSymbolInfo.getSymbolWidth(), paramSymbolInfo.getSymbolHeight());
    int i = 0;
    int j = 0;
    while (j < i2)
    {
      int k = i;
      boolean bool;
      if (j % paramSymbolInfo.matrixHeight == 0)
      {
        m = 0;
        k = 0;
        if (k < paramSymbolInfo.getSymbolWidth())
        {
          if (k % 2 == 0) {}
          for (bool = true;; bool = false)
          {
            localByteMatrix.set(m, i, bool);
            m += 1;
            k += 1;
            break;
          }
        }
        k = i + 1;
      }
      i = 0;
      int m = 0;
      if (m < i1)
      {
        int n = i;
        if (m % paramSymbolInfo.matrixWidth == 0)
        {
          localByteMatrix.set(i, k, true);
          n = i + 1;
        }
        localByteMatrix.set(n, k, paramDefaultPlacement.getBit(m, j));
        n += 1;
        i = n;
        if (m % paramSymbolInfo.matrixWidth == paramSymbolInfo.matrixWidth - 1) {
          if (j % 2 != 0) {
            break label226;
          }
        }
        label226:
        for (bool = true;; bool = false)
        {
          localByteMatrix.set(n, k, bool);
          i = n + 1;
          m += 1;
          break;
        }
      }
      m = k + 1;
      i = m;
      if (j % paramSymbolInfo.matrixHeight == paramSymbolInfo.matrixHeight - 1)
      {
        k = 0;
        i = 0;
        while (i < paramSymbolInfo.getSymbolWidth())
        {
          localByteMatrix.set(k, m, true);
          k += 1;
          i += 1;
        }
        i = m + 1;
      }
      j += 1;
    }
    return convertByteMatrixToBitMatrix(localByteMatrix);
  }
  
  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2)
  {
    return encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, null);
  }
  
  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
  {
    if (paramString.isEmpty()) {
      throw new IllegalArgumentException("Found empty contents");
    }
    if (paramBarcodeFormat != BarcodeFormat.DATA_MATRIX) {
      throw new IllegalArgumentException("Can only encode DATA_MATRIX, but got " + paramBarcodeFormat);
    }
    if ((paramInt1 < 0) || (paramInt2 < 0)) {
      throw new IllegalArgumentException("Requested dimensions are too small: " + paramInt1 + 'x' + paramInt2);
    }
    paramBarcodeFormat = SymbolShapeHint.FORCE_NONE;
    Object localObject3 = null;
    Object localObject1 = null;
    Object localObject4 = null;
    Object localObject2 = localObject4;
    BarcodeFormat localBarcodeFormat = paramBarcodeFormat;
    if (paramMap != null)
    {
      localObject2 = (SymbolShapeHint)paramMap.get(EncodeHintType.DATA_MATRIX_SHAPE);
      if (localObject2 != null) {
        paramBarcodeFormat = (BarcodeFormat)localObject2;
      }
      localObject2 = (Dimension)paramMap.get(EncodeHintType.MIN_SIZE);
      if (localObject2 != null) {
        localObject1 = localObject2;
      }
      paramMap = (Dimension)paramMap.get(EncodeHintType.MAX_SIZE);
      localObject2 = localObject4;
      localObject3 = localObject1;
      localBarcodeFormat = paramBarcodeFormat;
      if (paramMap != null)
      {
        localObject2 = paramMap;
        localBarcodeFormat = paramBarcodeFormat;
        localObject3 = localObject1;
      }
    }
    paramString = HighLevelEncoder.encodeHighLevel(paramString, localBarcodeFormat, (Dimension)localObject3, (Dimension)localObject2);
    paramBarcodeFormat = SymbolInfo.lookup(paramString.length(), localBarcodeFormat, (Dimension)localObject3, (Dimension)localObject2, true);
    paramString = new DefaultPlacement(ErrorCorrection.encodeECC200(paramString, paramBarcodeFormat), paramBarcodeFormat.getSymbolDataWidth(), paramBarcodeFormat.getSymbolDataHeight());
    paramString.place();
    return encodeLowLevel(paramString, paramBarcodeFormat);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\datamatrix\DataMatrixWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */