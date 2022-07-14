package com.google.zxing.pdf417.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.pdf417.PDF417Common;
import com.google.zxing.pdf417.decoder.ec.ErrorCorrection;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;
import java.util.List;

public final class PDF417ScanningDecoder
{
  private static final int CODEWORD_SKEW_SIZE = 2;
  private static final int MAX_EC_CODEWORDS = 512;
  private static final int MAX_ERRORS = 3;
  private static final ErrorCorrection errorCorrection = new ErrorCorrection();
  
  private static BoundingBox adjustBoundingBox(DetectionResultRowIndicatorColumn paramDetectionResultRowIndicatorColumn)
    throws NotFoundException
  {
    if (paramDetectionResultRowIndicatorColumn == null) {}
    int[] arrayOfInt;
    do
    {
      return null;
      arrayOfInt = paramDetectionResultRowIndicatorColumn.getRowHeights();
    } while (arrayOfInt == null);
    int n = getMax(arrayOfInt);
    int i = 0;
    int m = arrayOfInt.length;
    int k = 0;
    Codeword[] arrayOfCodeword;
    for (;;)
    {
      j = i;
      if (k < m)
      {
        j = arrayOfInt[k];
        i += n - j;
        if (j > 0) {
          j = i;
        }
      }
      else
      {
        arrayOfCodeword = paramDetectionResultRowIndicatorColumn.getCodewords();
        i = 0;
        k = j;
        while ((k > 0) && (arrayOfCodeword[i] == null))
        {
          k -= 1;
          i += 1;
        }
      }
      k += 1;
    }
    int j = 0;
    m = arrayOfInt.length - 1;
    for (;;)
    {
      i = j;
      if (m >= 0)
      {
        i = j + (n - arrayOfInt[m]);
        if (arrayOfInt[m] <= 0) {}
      }
      else
      {
        j = arrayOfCodeword.length - 1;
        while ((i > 0) && (arrayOfCodeword[j] == null))
        {
          i -= 1;
          j -= 1;
        }
      }
      m -= 1;
      j = i;
    }
    return paramDetectionResultRowIndicatorColumn.getBoundingBox().addMissingRows(k, i, paramDetectionResultRowIndicatorColumn.isLeft());
  }
  
  private static void adjustCodewordCount(DetectionResult paramDetectionResult, BarcodeValue[][] paramArrayOfBarcodeValue)
    throws NotFoundException
  {
    int[] arrayOfInt = paramArrayOfBarcodeValue[0][1].getValue();
    int i = paramDetectionResult.getBarcodeColumnCount() * paramDetectionResult.getBarcodeRowCount() - getNumberOfECCodeWords(paramDetectionResult.getBarcodeECLevel());
    if (arrayOfInt.length == 0)
    {
      if ((i < 1) || (i > 928)) {
        throw NotFoundException.getNotFoundInstance();
      }
      paramArrayOfBarcodeValue[0][1].setValue(i);
    }
    while (arrayOfInt[0] == i) {
      return;
    }
    paramArrayOfBarcodeValue[0][1].setValue(i);
  }
  
  private static int adjustCodewordStartColumn(BitMatrix paramBitMatrix, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4)
  {
    int i;
    int j;
    if (paramBoolean)
    {
      i = -1;
      j = 0;
      int m = paramInt3;
      k = i;
      i = m;
      if (j >= 2) {
        break label112;
      }
      label28:
      if (!paramBoolean) {
        break label71;
      }
      if (i < paramInt1) {
        break label77;
      }
    }
    label71:
    while (i < paramInt2)
    {
      if (paramBoolean != paramBitMatrix.get(i, paramInt4)) {
        break label77;
      }
      if (Math.abs(paramInt3 - i) <= 2) {
        break label97;
      }
      return paramInt3;
      i = 1;
      break;
    }
    label77:
    int k = -k;
    if (!paramBoolean) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      j += 1;
      break;
      label97:
      i += k;
      break label28;
    }
    label112:
    return i;
  }
  
  private static boolean checkCodewordSkew(int paramInt1, int paramInt2, int paramInt3)
  {
    return (paramInt2 - 2 <= paramInt1) && (paramInt1 <= paramInt3 + 2);
  }
  
  private static int correctErrors(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
    throws ChecksumException
  {
    if (((paramArrayOfInt2 != null) && (paramArrayOfInt2.length > paramInt / 2 + 3)) || (paramInt < 0) || (paramInt > 512)) {
      throw ChecksumException.getChecksumInstance();
    }
    return errorCorrection.decode(paramArrayOfInt1, paramInt, paramArrayOfInt2);
  }
  
  private static BarcodeValue[][] createBarcodeMatrix(DetectionResult paramDetectionResult)
  {
    BarcodeValue[][] arrayOfBarcodeValue = (BarcodeValue[][])Array.newInstance(BarcodeValue.class, new int[] { paramDetectionResult.getBarcodeRowCount(), paramDetectionResult.getBarcodeColumnCount() + 2 });
    int i = 0;
    while (i < arrayOfBarcodeValue.length)
    {
      j = 0;
      while (j < arrayOfBarcodeValue[i].length)
      {
        arrayOfBarcodeValue[i][j] = new BarcodeValue();
        j += 1;
      }
      i += 1;
    }
    int j = 0;
    paramDetectionResult = paramDetectionResult.getDetectionResultColumns();
    int m = paramDetectionResult.length;
    i = 0;
    while (i < m)
    {
      Codeword[] arrayOfCodeword = paramDetectionResult[i];
      if (arrayOfCodeword != null)
      {
        arrayOfCodeword = arrayOfCodeword.getCodewords();
        int n = arrayOfCodeword.length;
        int k = 0;
        if (k < n)
        {
          Codeword localCodeword = arrayOfCodeword[k];
          int i1;
          if (localCodeword != null)
          {
            i1 = localCodeword.getRowNumber();
            if ((i1 >= 0) && (i1 < arrayOfBarcodeValue.length)) {
              break label163;
            }
          }
          for (;;)
          {
            k += 1;
            break;
            label163:
            arrayOfBarcodeValue[i1][j].setValue(localCodeword.getValue());
          }
        }
      }
      j += 1;
      i += 1;
    }
    return arrayOfBarcodeValue;
  }
  
  private static DecoderResult createDecoderResult(DetectionResult paramDetectionResult)
    throws FormatException, ChecksumException, NotFoundException
  {
    Object localObject = createBarcodeMatrix(paramDetectionResult);
    adjustCodewordCount(paramDetectionResult, (BarcodeValue[][])localObject);
    ArrayList localArrayList1 = new ArrayList();
    int[] arrayOfInt1 = new int[paramDetectionResult.getBarcodeRowCount() * paramDetectionResult.getBarcodeColumnCount()];
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    int i = 0;
    while (i < paramDetectionResult.getBarcodeRowCount())
    {
      int j = 0;
      if (j < paramDetectionResult.getBarcodeColumnCount())
      {
        int[] arrayOfInt2 = localObject[i][(j + 1)].getValue();
        int k = paramDetectionResult.getBarcodeColumnCount() * i + j;
        if (arrayOfInt2.length == 0) {
          localArrayList1.add(Integer.valueOf(k));
        }
        for (;;)
        {
          j += 1;
          break;
          if (arrayOfInt2.length == 1)
          {
            arrayOfInt1[k] = arrayOfInt2[0];
          }
          else
          {
            localArrayList3.add(Integer.valueOf(k));
            localArrayList2.add(arrayOfInt2);
          }
        }
      }
      i += 1;
    }
    localObject = new int[localArrayList2.size()][];
    i = 0;
    while (i < localObject.length)
    {
      localObject[i] = ((int[])localArrayList2.get(i));
      i += 1;
    }
    return createDecoderResultFromAmbiguousValues(paramDetectionResult.getBarcodeECLevel(), arrayOfInt1, PDF417Common.toIntArray(localArrayList1), PDF417Common.toIntArray(localArrayList3), (int[][])localObject);
  }
  
  private static DecoderResult createDecoderResultFromAmbiguousValues(int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[][] paramArrayOfInt)
    throws FormatException, ChecksumException
  {
    int[] arrayOfInt = new int[paramArrayOfInt3.length];
    int i = 100;
    if (i > 0)
    {
      int j = 0;
      while (j < arrayOfInt.length)
      {
        paramArrayOfInt1[paramArrayOfInt3[j]] = paramArrayOfInt[j][arrayOfInt[j]];
        j += 1;
      }
      try
      {
        DecoderResult localDecoderResult = decodeCodewords(paramArrayOfInt1, paramInt, paramArrayOfInt2);
        return localDecoderResult;
      }
      catch (ChecksumException localChecksumException)
      {
        if (arrayOfInt.length == 0) {
          throw ChecksumException.getChecksumInstance();
        }
        j = 0;
      }
      for (;;)
      {
        if (j < arrayOfInt.length)
        {
          if (arrayOfInt[j] < paramArrayOfInt[j].length - 1) {
            arrayOfInt[j] += 1;
          }
        }
        else
        {
          i -= 1;
          break;
        }
        arrayOfInt[j] = 0;
        if (j == arrayOfInt.length - 1) {
          throw ChecksumException.getChecksumInstance();
        }
        j += 1;
      }
    }
    throw ChecksumException.getChecksumInstance();
  }
  
  public static DecoderResult decode(BitMatrix paramBitMatrix, ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4, int paramInt1, int paramInt2)
    throws NotFoundException, FormatException, ChecksumException
  {
    BoundingBox localBoundingBox = new BoundingBox(paramBitMatrix, paramResultPoint1, paramResultPoint2, paramResultPoint3, paramResultPoint4);
    paramResultPoint4 = null;
    paramResultPoint2 = null;
    DetectionResult localDetectionResult = null;
    int i = 0;
    for (;;)
    {
      localResultPoint2 = paramResultPoint4;
      localResultPoint1 = paramResultPoint2;
      if (i >= 2) {
        break label166;
      }
      if (paramResultPoint1 != null) {
        paramResultPoint4 = getRowIndicatorColumn(paramBitMatrix, localBoundingBox, paramResultPoint1, true, paramInt1, paramInt2);
      }
      if (paramResultPoint3 != null) {
        paramResultPoint2 = getRowIndicatorColumn(paramBitMatrix, localBoundingBox, paramResultPoint3, false, paramInt1, paramInt2);
      }
      localDetectionResult = merge(paramResultPoint4, paramResultPoint2);
      if (localDetectionResult == null) {
        throw NotFoundException.getNotFoundInstance();
      }
      if ((i != 0) || (localDetectionResult.getBoundingBox() == null) || ((localDetectionResult.getBoundingBox().getMinY() >= localBoundingBox.getMinY()) && (localDetectionResult.getBoundingBox().getMaxY() <= localBoundingBox.getMaxY()))) {
        break;
      }
      localBoundingBox = localDetectionResult.getBoundingBox();
      i += 1;
    }
    localDetectionResult.setBoundingBox(localBoundingBox);
    ResultPoint localResultPoint1 = paramResultPoint2;
    ResultPoint localResultPoint2 = paramResultPoint4;
    label166:
    int i3 = localDetectionResult.getBarcodeColumnCount() + 1;
    localDetectionResult.setDetectionResultColumn(0, localResultPoint2);
    localDetectionResult.setDetectionResultColumn(i3, localResultPoint1);
    boolean bool1;
    int j;
    label211:
    int m;
    label227:
    int k;
    if (localResultPoint2 != null)
    {
      bool1 = true;
      j = 1;
      i = paramInt1;
      paramInt1 = j;
      if (paramInt1 > i3) {
        break label505;
      }
      if (!bool1) {
        break label268;
      }
      m = paramInt1;
      if (localDetectionResult.getDetectionResultColumn(m) == null) {
        break label278;
      }
      k = paramInt2;
      i1 = i;
    }
    label268:
    label278:
    boolean bool2;
    label310:
    int n;
    label328:
    do
    {
      paramInt1 += 1;
      i = i1;
      paramInt2 = k;
      break label211;
      bool1 = false;
      break;
      m = i3 - paramInt1;
      break label227;
      if ((m != 0) && (m != i3)) {
        break label415;
      }
      if (m != 0) {
        break label409;
      }
      bool2 = true;
      paramResultPoint1 = new DetectionResultRowIndicatorColumn(localBoundingBox, bool2);
      localDetectionResult.setDetectionResultColumn(m, paramResultPoint1);
      j = -1;
      n = localBoundingBox.getMinY();
      i1 = i;
      k = paramInt2;
    } while (n > localBoundingBox.getMaxY());
    int i1 = getStartColumn(localDetectionResult, m, n, bool1);
    int i2;
    if (i1 >= 0)
    {
      k = i1;
      if (i1 <= localBoundingBox.getMaxX()) {}
    }
    else if (j == -1)
    {
      i2 = paramInt2;
      i1 = i;
    }
    for (;;)
    {
      n += 1;
      i = i1;
      paramInt2 = i2;
      break label328;
      label409:
      bool2 = false;
      break;
      label415:
      paramResultPoint1 = new DetectionResultColumn(localBoundingBox);
      break label310;
      k = j;
      paramResultPoint2 = detectCodeword(paramBitMatrix, localBoundingBox.getMinX(), localBoundingBox.getMaxX(), bool1, k, n, i, paramInt2);
      i1 = i;
      i2 = paramInt2;
      if (paramResultPoint2 != null)
      {
        paramResultPoint1.setCodeword(n, paramResultPoint2);
        j = k;
        i1 = Math.min(i, paramResultPoint2.getWidth());
        i2 = Math.max(paramInt2, paramResultPoint2.getWidth());
      }
    }
    label505:
    return createDecoderResult(localDetectionResult);
  }
  
  private static DecoderResult decodeCodewords(int[] paramArrayOfInt1, int paramInt, int[] paramArrayOfInt2)
    throws FormatException, ChecksumException
  {
    if (paramArrayOfInt1.length == 0) {
      throw FormatException.getFormatInstance();
    }
    int i = 1 << paramInt + 1;
    int j = correctErrors(paramArrayOfInt1, paramArrayOfInt2, i);
    verifyCodewordCount(paramArrayOfInt1, i);
    paramArrayOfInt1 = DecodedBitStreamParser.decode(paramArrayOfInt1, String.valueOf(paramInt));
    paramArrayOfInt1.setErrorsCorrected(Integer.valueOf(j));
    paramArrayOfInt1.setErasures(Integer.valueOf(paramArrayOfInt2.length));
    return paramArrayOfInt1;
  }
  
  private static Codeword detectCodeword(BitMatrix paramBitMatrix, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    paramInt3 = adjustCodewordStartColumn(paramBitMatrix, paramInt1, paramInt2, paramBoolean, paramInt3, paramInt4);
    paramBitMatrix = getModuleBitCount(paramBitMatrix, paramInt1, paramInt2, paramBoolean, paramInt3, paramInt4);
    if (paramBitMatrix == null) {
      return null;
    }
    paramInt2 = MathUtils.sum(paramBitMatrix);
    if (paramBoolean) {
      paramInt1 = paramInt3 + paramInt2;
    }
    while (!checkCodewordSkew(paramInt2, paramInt5, paramInt6))
    {
      return null;
      paramInt1 = 0;
      while (paramInt1 < paramBitMatrix.length / 2)
      {
        paramInt4 = paramBitMatrix[paramInt1];
        paramBitMatrix[paramInt1] = paramBitMatrix[(paramBitMatrix.length - 1 - paramInt1)];
        paramBitMatrix[(paramBitMatrix.length - 1 - paramInt1)] = paramInt4;
        paramInt1 += 1;
      }
      paramInt1 = paramInt3;
      paramInt3 = paramInt1 - paramInt2;
    }
    paramInt2 = PDF417CodewordDecoder.getDecodedValue(paramBitMatrix);
    paramInt4 = PDF417Common.getCodeword(paramInt2);
    if (paramInt4 == -1) {
      return null;
    }
    return new Codeword(paramInt3, paramInt1, getCodewordBucketNumber(paramInt2), paramInt4);
  }
  
  private static BarcodeMetadata getBarcodeMetadata(DetectionResultRowIndicatorColumn paramDetectionResultRowIndicatorColumn1, DetectionResultRowIndicatorColumn paramDetectionResultRowIndicatorColumn2)
  {
    if (paramDetectionResultRowIndicatorColumn1 != null)
    {
      paramDetectionResultRowIndicatorColumn1 = paramDetectionResultRowIndicatorColumn1.getBarcodeMetadata();
      if (paramDetectionResultRowIndicatorColumn1 != null) {}
    }
    else if (paramDetectionResultRowIndicatorColumn2 != null) {}
    do
    {
      return null;
      return paramDetectionResultRowIndicatorColumn2.getBarcodeMetadata();
      if (paramDetectionResultRowIndicatorColumn2 != null)
      {
        paramDetectionResultRowIndicatorColumn2 = paramDetectionResultRowIndicatorColumn2.getBarcodeMetadata();
        if (paramDetectionResultRowIndicatorColumn2 != null) {}
      }
      else
      {
        return paramDetectionResultRowIndicatorColumn1;
      }
    } while ((paramDetectionResultRowIndicatorColumn1.getColumnCount() != paramDetectionResultRowIndicatorColumn2.getColumnCount()) && (paramDetectionResultRowIndicatorColumn1.getErrorCorrectionLevel() != paramDetectionResultRowIndicatorColumn2.getErrorCorrectionLevel()) && (paramDetectionResultRowIndicatorColumn1.getRowCount() != paramDetectionResultRowIndicatorColumn2.getRowCount()));
    return paramDetectionResultRowIndicatorColumn1;
  }
  
  private static int[] getBitCountForCodeword(int paramInt)
  {
    int[] arrayOfInt = new int[8];
    int k = 0;
    int j = arrayOfInt.length - 1;
    for (;;)
    {
      int i = j;
      int m = k;
      if ((paramInt & 0x1) != k)
      {
        m = paramInt & 0x1;
        j -= 1;
        i = j;
        if (j < 0) {
          return arrayOfInt;
        }
      }
      arrayOfInt[i] += 1;
      paramInt >>= 1;
      j = i;
      k = m;
    }
  }
  
  private static int getCodewordBucketNumber(int paramInt)
  {
    return getCodewordBucketNumber(getBitCountForCodeword(paramInt));
  }
  
  private static int getCodewordBucketNumber(int[] paramArrayOfInt)
  {
    return (paramArrayOfInt[0] - paramArrayOfInt[2] + paramArrayOfInt[4] - paramArrayOfInt[6] + 9) % 9;
  }
  
  private static int getMax(int[] paramArrayOfInt)
  {
    int j = -1;
    int k = paramArrayOfInt.length;
    int i = 0;
    while (i < k)
    {
      j = Math.max(j, paramArrayOfInt[i]);
      i += 1;
    }
    return j;
  }
  
  private static int[] getModuleBitCount(BitMatrix paramBitMatrix, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4)
  {
    int j = paramInt3;
    int[] arrayOfInt = new int[8];
    int i = 0;
    boolean bool;
    if (paramBoolean)
    {
      paramInt3 = 1;
      bool = paramBoolean;
      label23:
      if (!paramBoolean) {
        break label82;
      }
      if (j >= paramInt2) {
        break label88;
      }
    }
    label82:
    while (j >= paramInt1)
    {
      if (i >= arrayOfInt.length) {
        break label88;
      }
      if (paramBitMatrix.get(j, paramInt4) != bool) {
        break label119;
      }
      arrayOfInt[i] += 1;
      j += paramInt3;
      break label23;
      paramInt3 = -1;
      break;
    }
    label88:
    if (i != arrayOfInt.length) {
      if (!paramBoolean) {
        break label142;
      }
    }
    for (;;)
    {
      if ((j != paramInt2) || (i != arrayOfInt.length - 1)) {
        break label147;
      }
      return arrayOfInt;
      label119:
      i += 1;
      if (!bool) {}
      for (bool = true;; bool = false) {
        break;
      }
      label142:
      paramInt2 = paramInt1;
    }
    label147:
    return null;
  }
  
  private static int getNumberOfECCodeWords(int paramInt)
  {
    return 2 << paramInt;
  }
  
  private static DetectionResultRowIndicatorColumn getRowIndicatorColumn(BitMatrix paramBitMatrix, BoundingBox paramBoundingBox, ResultPoint paramResultPoint, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    DetectionResultRowIndicatorColumn localDetectionResultRowIndicatorColumn = new DetectionResultRowIndicatorColumn(paramBoundingBox, paramBoolean);
    int j = 0;
    while (j < 2)
    {
      int k;
      int m;
      label42:
      Codeword localCodeword;
      if (j == 0)
      {
        k = 1;
        i = (int)paramResultPoint.getX();
        m = (int)paramResultPoint.getY();
        if ((m > paramBoundingBox.getMaxY()) || (m < paramBoundingBox.getMinY())) {
          break label131;
        }
        localCodeword = detectCodeword(paramBitMatrix, 0, paramBitMatrix.getWidth(), paramBoolean, i, m, paramInt1, paramInt2);
        if (localCodeword != null)
        {
          localDetectionResultRowIndicatorColumn.setCodeword(m, localCodeword);
          if (!paramBoolean) {
            break label121;
          }
        }
      }
      label121:
      for (int i = localCodeword.getStartX();; i = localCodeword.getEndX())
      {
        m += k;
        break label42;
        k = -1;
        break;
      }
      label131:
      j += 1;
    }
    return localDetectionResultRowIndicatorColumn;
  }
  
  private static int getStartColumn(DetectionResult paramDetectionResult, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 1;; i = -1)
    {
      localObject1 = null;
      if (isValidBarcodeColumn(paramDetectionResult, paramInt1 - i)) {
        localObject1 = paramDetectionResult.getDetectionResultColumn(paramInt1 - i).getCodeword(paramInt2);
      }
      if (localObject1 == null) {
        break label62;
      }
      if (!paramBoolean) {
        break;
      }
      return ((Codeword)localObject1).getEndX();
    }
    return ((Codeword)localObject1).getStartX();
    label62:
    Object localObject1 = paramDetectionResult.getDetectionResultColumn(paramInt1).getCodewordNearby(paramInt2);
    if (localObject1 != null)
    {
      if (paramBoolean) {
        return ((Codeword)localObject1).getStartX();
      }
      return ((Codeword)localObject1).getEndX();
    }
    if (isValidBarcodeColumn(paramDetectionResult, paramInt1 - i)) {
      localObject1 = paramDetectionResult.getDetectionResultColumn(paramInt1 - i).getCodewordNearby(paramInt2);
    }
    if (localObject1 != null)
    {
      if (paramBoolean) {
        return ((Codeword)localObject1).getEndX();
      }
      return ((Codeword)localObject1).getStartX();
    }
    int j = 0;
    paramInt2 = paramInt1;
    paramInt1 = j;
    while (isValidBarcodeColumn(paramDetectionResult, paramInt2 - i))
    {
      j = paramInt2 - i;
      localObject1 = paramDetectionResult.getDetectionResultColumn(j).getCodewords();
      int k = localObject1.length;
      paramInt2 = 0;
      while (paramInt2 < k)
      {
        Object localObject2 = localObject1[paramInt2];
        if (localObject2 != null)
        {
          if (paramBoolean) {}
          for (paramInt2 = ((Codeword)localObject2).getEndX();; paramInt2 = ((Codeword)localObject2).getStartX()) {
            return paramInt2 + i * paramInt1 * (((Codeword)localObject2).getEndX() - ((Codeword)localObject2).getStartX());
          }
        }
        paramInt2 += 1;
      }
      paramInt1 += 1;
      paramInt2 = j;
    }
    if (paramBoolean) {
      return paramDetectionResult.getBoundingBox().getMinX();
    }
    return paramDetectionResult.getBoundingBox().getMaxX();
  }
  
  private static boolean isValidBarcodeColumn(DetectionResult paramDetectionResult, int paramInt)
  {
    return (paramInt >= 0) && (paramInt <= paramDetectionResult.getBarcodeColumnCount() + 1);
  }
  
  private static DetectionResult merge(DetectionResultRowIndicatorColumn paramDetectionResultRowIndicatorColumn1, DetectionResultRowIndicatorColumn paramDetectionResultRowIndicatorColumn2)
    throws NotFoundException
  {
    if ((paramDetectionResultRowIndicatorColumn1 == null) && (paramDetectionResultRowIndicatorColumn2 == null)) {}
    BarcodeMetadata localBarcodeMetadata;
    do
    {
      return null;
      localBarcodeMetadata = getBarcodeMetadata(paramDetectionResultRowIndicatorColumn1, paramDetectionResultRowIndicatorColumn2);
    } while (localBarcodeMetadata == null);
    return new DetectionResult(localBarcodeMetadata, BoundingBox.merge(adjustBoundingBox(paramDetectionResultRowIndicatorColumn1), adjustBoundingBox(paramDetectionResultRowIndicatorColumn2)));
  }
  
  public static String toString(BarcodeValue[][] paramArrayOfBarcodeValue)
  {
    Formatter localFormatter = new Formatter();
    int i = 0;
    while (i < paramArrayOfBarcodeValue.length)
    {
      localFormatter.format("Row %2d: ", new Object[] { Integer.valueOf(i) });
      int j = 0;
      if (j < paramArrayOfBarcodeValue[i].length)
      {
        BarcodeValue localBarcodeValue = paramArrayOfBarcodeValue[i][j];
        if (localBarcodeValue.getValue().length == 0) {
          localFormatter.format("        ", (Object[])null);
        }
        for (;;)
        {
          j += 1;
          break;
          localFormatter.format("%4d(%2d)", new Object[] { Integer.valueOf(localBarcodeValue.getValue()[0]), localBarcodeValue.getConfidence(localBarcodeValue.getValue()[0]) });
        }
      }
      localFormatter.format("%n", new Object[0]);
      i += 1;
    }
    paramArrayOfBarcodeValue = localFormatter.toString();
    localFormatter.close();
    return paramArrayOfBarcodeValue;
  }
  
  private static void verifyCodewordCount(int[] paramArrayOfInt, int paramInt)
    throws FormatException
  {
    if (paramArrayOfInt.length < 4) {
      throw FormatException.getFormatInstance();
    }
    int i = paramArrayOfInt[0];
    if (i > paramArrayOfInt.length) {
      throw FormatException.getFormatInstance();
    }
    if (i == 0)
    {
      if (paramInt < paramArrayOfInt.length) {
        paramArrayOfInt[0] = (paramArrayOfInt.length - paramInt);
      }
    }
    else {
      return;
    }
    throw FormatException.getFormatInstance();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\pdf417\decoder\PDF417ScanningDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */