package com.google.zxing.pdf417;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.encoder.BarcodeMatrix;
import com.google.zxing.pdf417.encoder.Compaction;
import com.google.zxing.pdf417.encoder.Dimensions;
import com.google.zxing.pdf417.encoder.PDF417;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Map;

public final class PDF417Writer
  implements Writer
{
  static final int DEFAULT_ERROR_CORRECTION_LEVEL = 2;
  static final int WHITE_SPACE = 30;
  
  private static BitMatrix bitMatrixFromEncoder(PDF417 paramPDF417, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws WriterException
  {
    paramPDF417.generateBarcodeLogic(paramString, paramInt1);
    byte[][] arrayOfByte = paramPDF417.getBarcodeMatrix().getScaledMatrix(1, 4);
    paramInt1 = 0;
    int i;
    int j;
    if (paramInt3 > paramInt2)
    {
      i = 1;
      if (arrayOfByte[0].length >= arrayOfByte.length) {
        break label122;
      }
      j = 1;
      label42:
      paramString = arrayOfByte;
      if ((i ^ j) != 0)
      {
        paramString = rotateArray(arrayOfByte);
        paramInt1 = 1;
      }
      paramInt2 /= paramString[0].length;
      paramInt3 /= paramString.length;
      if (paramInt2 >= paramInt3) {
        break label128;
      }
    }
    for (;;)
    {
      if (paramInt2 <= 1) {
        break label134;
      }
      paramString = paramPDF417.getBarcodeMatrix().getScaledMatrix(paramInt2, paramInt2 * 4);
      paramPDF417 = paramString;
      if (paramInt1 != 0) {
        paramPDF417 = rotateArray(paramString);
      }
      return bitMatrixFrombitArray(paramPDF417, paramInt4);
      i = 0;
      break;
      label122:
      j = 0;
      break label42;
      label128:
      paramInt2 = paramInt3;
    }
    label134:
    return bitMatrixFrombitArray(paramString, paramInt4);
  }
  
  private static BitMatrix bitMatrixFrombitArray(byte[][] paramArrayOfByte, int paramInt)
  {
    BitMatrix localBitMatrix = new BitMatrix(paramArrayOfByte[0].length + paramInt * 2, paramArrayOfByte.length + paramInt * 2);
    localBitMatrix.clear();
    int j = 0;
    int i = localBitMatrix.getHeight() - paramInt - 1;
    while (j < paramArrayOfByte.length)
    {
      int k = 0;
      while (k < paramArrayOfByte[0].length)
      {
        if (paramArrayOfByte[j][k] == 1) {
          localBitMatrix.set(k + paramInt, i);
        }
        k += 1;
      }
      j += 1;
      i -= 1;
    }
    return localBitMatrix;
  }
  
  private static byte[][] rotateArray(byte[][] paramArrayOfByte)
  {
    int i = paramArrayOfByte[0].length;
    int j = paramArrayOfByte.length;
    byte[][] arrayOfByte = (byte[][])Array.newInstance(Byte.TYPE, new int[] { i, j });
    i = 0;
    while (i < paramArrayOfByte.length)
    {
      int k = paramArrayOfByte.length;
      j = 0;
      while (j < paramArrayOfByte[0].length)
      {
        arrayOfByte[j][(k - i - 1)] = paramArrayOfByte[i][j];
        j += 1;
      }
      i += 1;
    }
    return arrayOfByte;
  }
  
  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2)
    throws WriterException
  {
    return encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, null);
  }
  
  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
    throws WriterException
  {
    if (paramBarcodeFormat != BarcodeFormat.PDF_417) {
      throw new IllegalArgumentException("Can only encode PDF_417, but got " + paramBarcodeFormat);
    }
    paramBarcodeFormat = new PDF417();
    int i = 30;
    int j = 2;
    int k = j;
    int m = i;
    if (paramMap != null)
    {
      if (paramMap.containsKey(EncodeHintType.PDF417_COMPACT)) {
        paramBarcodeFormat.setCompact(Boolean.valueOf(paramMap.get(EncodeHintType.PDF417_COMPACT).toString()).booleanValue());
      }
      if (paramMap.containsKey(EncodeHintType.PDF417_COMPACTION)) {
        paramBarcodeFormat.setCompaction(Compaction.valueOf(paramMap.get(EncodeHintType.PDF417_COMPACTION).toString()));
      }
      if (paramMap.containsKey(EncodeHintType.PDF417_DIMENSIONS))
      {
        Dimensions localDimensions = (Dimensions)paramMap.get(EncodeHintType.PDF417_DIMENSIONS);
        paramBarcodeFormat.setDimensions(localDimensions.getMaxCols(), localDimensions.getMinCols(), localDimensions.getMaxRows(), localDimensions.getMinRows());
      }
      if (paramMap.containsKey(EncodeHintType.MARGIN)) {
        i = Integer.parseInt(paramMap.get(EncodeHintType.MARGIN).toString());
      }
      if (paramMap.containsKey(EncodeHintType.ERROR_CORRECTION)) {
        j = Integer.parseInt(paramMap.get(EncodeHintType.ERROR_CORRECTION).toString());
      }
      k = j;
      m = i;
      if (paramMap.containsKey(EncodeHintType.CHARACTER_SET))
      {
        paramBarcodeFormat.setEncoding(Charset.forName(paramMap.get(EncodeHintType.CHARACTER_SET).toString()));
        m = i;
        k = j;
      }
    }
    return bitMatrixFromEncoder(paramBarcodeFormat, paramString, k, paramInt1, paramInt2, m);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\pdf417\PDF417Writer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */