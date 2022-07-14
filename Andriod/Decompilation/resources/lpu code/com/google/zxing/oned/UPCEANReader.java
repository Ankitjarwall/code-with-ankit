package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public abstract class UPCEANReader
  extends OneDReader
{
  static final int[] END_PATTERN;
  static final int[][] L_AND_G_PATTERNS;
  static final int[][] L_PATTERNS;
  private static final float MAX_AVG_VARIANCE = 0.48F;
  private static final float MAX_INDIVIDUAL_VARIANCE = 0.7F;
  static final int[] MIDDLE_PATTERN;
  static final int[] START_END_PATTERN = { 1, 1, 1 };
  private final StringBuilder decodeRowStringBuffer = new StringBuilder(20);
  private final EANManufacturerOrgSupport eanManSupport = new EANManufacturerOrgSupport();
  private final UPCEANExtensionSupport extensionReader = new UPCEANExtensionSupport();
  
  static
  {
    MIDDLE_PATTERN = new int[] { 1, 1, 1, 1, 1 };
    END_PATTERN = new int[] { 1, 1, 1, 1, 1, 1 };
    L_PATTERNS = new int[][] { { 3, 2, 1, 1 }, { 2, 2, 2, 1 }, { 2, 1, 2, 2 }, { 1, 4, 1, 1 }, { 1, 1, 3, 2 }, { 1, 2, 3, 1 }, { 1, 1, 1, 4 }, { 1, 3, 1, 2 }, { 1, 2, 1, 3 }, { 3, 1, 1, 2 } };
    L_AND_G_PATTERNS = new int[20][];
    System.arraycopy(L_PATTERNS, 0, L_AND_G_PATTERNS, 0, 10);
    int i = 10;
    while (i < 20)
    {
      int[] arrayOfInt1 = L_PATTERNS[(i - 10)];
      int[] arrayOfInt2 = new int[arrayOfInt1.length];
      int j = 0;
      while (j < arrayOfInt1.length)
      {
        arrayOfInt2[j] = arrayOfInt1[(arrayOfInt1.length - j - 1)];
        j += 1;
      }
      L_AND_G_PATTERNS[i] = arrayOfInt2;
      i += 1;
    }
  }
  
  static boolean checkStandardUPCEANChecksum(CharSequence paramCharSequence)
    throws FormatException
  {
    int k = paramCharSequence.length();
    if (k == 0) {}
    int j;
    do
    {
      return false;
      j = 0;
      int i = k - 2;
      while (i >= 0)
      {
        int m = paramCharSequence.charAt(i) - '0';
        if ((m < 0) || (m > 9)) {
          throw FormatException.getFormatInstance();
        }
        j += m;
        i -= 2;
      }
      j *= 3;
      i = k - 1;
      while (i >= 0)
      {
        k = paramCharSequence.charAt(i) - '0';
        if ((k < 0) || (k > 9)) {
          throw FormatException.getFormatInstance();
        }
        j += k;
        i -= 2;
      }
    } while (j % 10 != 0);
    return true;
  }
  
  static int decodeDigit(BitArray paramBitArray, int[] paramArrayOfInt, int paramInt, int[][] paramArrayOfInt1)
    throws NotFoundException
  {
    recordPattern(paramBitArray, paramInt, paramArrayOfInt);
    float f1 = 0.48F;
    int i = -1;
    int j = paramArrayOfInt1.length;
    paramInt = 0;
    while (paramInt < j)
    {
      float f3 = patternMatchVariance(paramArrayOfInt, paramArrayOfInt1[paramInt], 0.7F);
      float f2 = f1;
      if (f3 < f1)
      {
        f2 = f3;
        i = paramInt;
      }
      paramInt += 1;
      f1 = f2;
    }
    if (i >= 0) {
      return i;
    }
    throw NotFoundException.getNotFoundInstance();
  }
  
  static int[] findGuardPattern(BitArray paramBitArray, int paramInt, boolean paramBoolean, int[] paramArrayOfInt)
    throws NotFoundException
  {
    return findGuardPattern(paramBitArray, paramInt, paramBoolean, paramArrayOfInt, new int[paramArrayOfInt.length]);
  }
  
  private static int[] findGuardPattern(BitArray paramBitArray, int paramInt, boolean paramBoolean, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
    throws NotFoundException
  {
    int m = paramBitArray.getSize();
    if (paramBoolean) {}
    int i;
    int n;
    int j;
    for (paramInt = paramBitArray.getNextUnset(paramInt);; paramInt = paramBitArray.getNextSet(paramInt))
    {
      int k = 0;
      i = paramInt;
      n = paramArrayOfInt1.length;
      j = paramInt;
      paramInt = i;
      i = k;
      for (;;)
      {
        if (j >= m) {
          break label196;
        }
        if (!(paramBitArray.get(j) ^ paramBoolean)) {
          break;
        }
        paramArrayOfInt2[i] += 1;
        j += 1;
      }
    }
    if (i == n - 1)
    {
      if (patternMatchVariance(paramArrayOfInt2, paramArrayOfInt1, 0.7F) < 0.48F) {
        return new int[] { paramInt, j };
      }
      paramInt += paramArrayOfInt2[0] + paramArrayOfInt2[1];
      System.arraycopy(paramArrayOfInt2, 2, paramArrayOfInt2, 0, n - 2);
      paramArrayOfInt2[(n - 2)] = 0;
      paramArrayOfInt2[(n - 1)] = 0;
      i -= 1;
      label167:
      paramArrayOfInt2[i] = 1;
      if (paramBoolean) {
        break label191;
      }
    }
    label191:
    for (paramBoolean = true;; paramBoolean = false)
    {
      break;
      i += 1;
      break label167;
    }
    label196:
    throw NotFoundException.getNotFoundInstance();
  }
  
  static int[] findStartGuardPattern(BitArray paramBitArray)
    throws NotFoundException
  {
    boolean bool = false;
    Object localObject = null;
    int i = 0;
    int[] arrayOfInt2 = new int[START_END_PATTERN.length];
    while (!bool)
    {
      Arrays.fill(arrayOfInt2, 0, START_END_PATTERN.length, 0);
      int[] arrayOfInt1 = findGuardPattern(paramBitArray, i, false, START_END_PATTERN, arrayOfInt2);
      int k = arrayOfInt1[0];
      int j = arrayOfInt1[1];
      int m = k - (j - k);
      i = j;
      localObject = arrayOfInt1;
      if (m >= 0)
      {
        bool = paramBitArray.isRange(m, k, false);
        i = j;
        localObject = arrayOfInt1;
      }
    }
    return (int[])localObject;
  }
  
  boolean checkChecksum(String paramString)
    throws FormatException
  {
    return checkStandardUPCEANChecksum(paramString);
  }
  
  int[] decodeEnd(BitArray paramBitArray, int paramInt)
    throws NotFoundException
  {
    return findGuardPattern(paramBitArray, paramInt, false, START_END_PATTERN);
  }
  
  protected abstract int decodeMiddle(BitArray paramBitArray, int[] paramArrayOfInt, StringBuilder paramStringBuilder)
    throws NotFoundException;
  
  public Result decodeRow(int paramInt, BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException, ChecksumException, FormatException
  {
    return decodeRow(paramInt, paramBitArray, findStartGuardPattern(paramBitArray), paramMap);
  }
  
  public Result decodeRow(int paramInt, BitArray paramBitArray, int[] paramArrayOfInt, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException, ChecksumException, FormatException
  {
    if (paramMap == null) {}
    int[] arrayOfInt;
    int j;
    for (Object localObject1 = null;; localObject1 = (ResultPointCallback)paramMap.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK))
    {
      if (localObject1 != null) {
        ((ResultPointCallback)localObject1).foundPossibleResultPoint(new ResultPoint((paramArrayOfInt[0] + paramArrayOfInt[1]) / 2.0F, paramInt));
      }
      localObject2 = this.decodeRowStringBuffer;
      ((StringBuilder)localObject2).setLength(0);
      i = decodeMiddle(paramBitArray, paramArrayOfInt, (StringBuilder)localObject2);
      if (localObject1 != null) {
        ((ResultPointCallback)localObject1).foundPossibleResultPoint(new ResultPoint(i, paramInt));
      }
      arrayOfInt = decodeEnd(paramBitArray, i);
      if (localObject1 != null) {
        ((ResultPointCallback)localObject1).foundPossibleResultPoint(new ResultPoint((arrayOfInt[0] + arrayOfInt[1]) / 2.0F, paramInt));
      }
      i = arrayOfInt[1];
      j = i + (i - arrayOfInt[0]);
      if ((j < paramBitArray.getSize()) && (paramBitArray.isRange(i, j, false))) {
        break;
      }
      throw NotFoundException.getNotFoundInstance();
    }
    localObject1 = ((StringBuilder)localObject2).toString();
    if (((String)localObject1).length() < 8) {
      throw FormatException.getFormatInstance();
    }
    if (!checkChecksum((String)localObject1)) {
      throw ChecksumException.getChecksumInstance();
    }
    float f1 = (paramArrayOfInt[1] + paramArrayOfInt[0]) / 2.0F;
    float f2 = (arrayOfInt[1] + arrayOfInt[0]) / 2.0F;
    Object localObject2 = getBarcodeFormat();
    paramArrayOfInt = new Result((String)localObject1, null, new ResultPoint[] { new ResultPoint(f1, paramInt), new ResultPoint(f2, paramInt) }, (BarcodeFormat)localObject2);
    int i = 0;
    try
    {
      paramBitArray = this.extensionReader.decodeRow(paramInt, paramBitArray, arrayOfInt[1]);
      paramArrayOfInt.putMetadata(ResultMetadataType.UPC_EAN_EXTENSION, paramBitArray.getText());
      paramArrayOfInt.putAllMetadata(paramBitArray.getResultMetadata());
      paramArrayOfInt.addResultPoints(paramBitArray.getResultPoints());
      paramInt = paramBitArray.getText().length();
      int k;
      int m;
      if (paramMap == null)
      {
        paramBitArray = null;
        if (paramBitArray != null)
        {
          k = 0;
          m = paramBitArray.length;
          i = 0;
        }
      }
      else
      {
        for (;;)
        {
          j = k;
          if (i < m)
          {
            if (paramInt == paramBitArray[i]) {
              j = 1;
            }
          }
          else
          {
            if (j != 0) {
              break label432;
            }
            throw NotFoundException.getNotFoundInstance();
            paramBitArray = (int[])paramMap.get(DecodeHintType.ALLOWED_EAN_EXTENSIONS);
            break;
          }
          i += 1;
        }
      }
      label432:
      if ((localObject2 == BarcodeFormat.EAN_13) || (localObject2 == BarcodeFormat.UPC_A))
      {
        paramBitArray = this.eanManSupport.lookupCountryIdentifier((String)localObject1);
        if (paramBitArray != null) {
          paramArrayOfInt.putMetadata(ResultMetadataType.POSSIBLE_COUNTRY, paramBitArray);
        }
      }
      return paramArrayOfInt;
    }
    catch (ReaderException paramBitArray)
    {
      for (;;)
      {
        paramInt = i;
      }
    }
  }
  
  abstract BarcodeFormat getBarcodeFormat();
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\UPCEANReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */