package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.EnumMap;
import java.util.Map;

final class UPCEANExtension2Support
{
  private final int[] decodeMiddleCounters = new int[4];
  private final StringBuilder decodeRowStringBuffer = new StringBuilder();
  
  private int decodeMiddle(BitArray paramBitArray, int[] paramArrayOfInt, StringBuilder paramStringBuilder)
    throws NotFoundException
  {
    int[] arrayOfInt = this.decodeMiddleCounters;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    int i1 = paramBitArray.getSize();
    int i = paramArrayOfInt[1];
    int m = 0;
    int k = 0;
    while ((k < 2) && (i < i1))
    {
      int i2 = UPCEANReader.decodeDigit(paramBitArray, arrayOfInt, i, UPCEANReader.L_AND_G_PATTERNS);
      paramStringBuilder.append((char)(i2 % 10 + 48));
      int n = arrayOfInt.length;
      int j = 0;
      while (j < n)
      {
        i += arrayOfInt[j];
        j += 1;
      }
      n = m;
      if (i2 >= 10) {
        n = m | 1 << 1 - k;
      }
      j = i;
      if (k != 1) {
        j = paramBitArray.getNextUnset(paramBitArray.getNextSet(i));
      }
      k += 1;
      m = n;
      i = j;
    }
    if (paramStringBuilder.length() != 2) {
      throw NotFoundException.getNotFoundInstance();
    }
    if (Integer.parseInt(paramStringBuilder.toString()) % 4 != m) {
      throw NotFoundException.getNotFoundInstance();
    }
    return i;
  }
  
  private static Map<ResultMetadataType, Object> parseExtensionString(String paramString)
  {
    if (paramString.length() != 2) {
      return null;
    }
    EnumMap localEnumMap = new EnumMap(ResultMetadataType.class);
    localEnumMap.put(ResultMetadataType.ISSUE_NUMBER, Integer.valueOf(paramString));
    return localEnumMap;
  }
  
  Result decodeRow(int paramInt, BitArray paramBitArray, int[] paramArrayOfInt)
    throws NotFoundException
  {
    Object localObject = this.decodeRowStringBuffer;
    ((StringBuilder)localObject).setLength(0);
    int i = decodeMiddle(paramBitArray, paramArrayOfInt, (StringBuilder)localObject);
    localObject = ((StringBuilder)localObject).toString();
    paramBitArray = parseExtensionString((String)localObject);
    paramArrayOfInt = new ResultPoint((paramArrayOfInt[0] + paramArrayOfInt[1]) / 2.0F, paramInt);
    ResultPoint localResultPoint = new ResultPoint(i, paramInt);
    BarcodeFormat localBarcodeFormat = BarcodeFormat.UPC_EAN_EXTENSION;
    paramArrayOfInt = new Result((String)localObject, null, new ResultPoint[] { paramArrayOfInt, localResultPoint }, localBarcodeFormat);
    if (paramBitArray != null) {
      paramArrayOfInt.putAllMetadata(paramBitArray);
    }
    return paramArrayOfInt;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\UPCEANExtension2Support.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */