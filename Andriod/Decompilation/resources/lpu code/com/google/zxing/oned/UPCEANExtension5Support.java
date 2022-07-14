package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.EnumMap;
import java.util.Map;

final class UPCEANExtension5Support
{
  private static final int[] CHECK_DIGIT_ENCODINGS = { 24, 20, 18, 17, 12, 6, 3, 10, 9, 5 };
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
    while ((k < 5) && (i < i1))
    {
      int i2 = UPCEANReader.decodeDigit(paramBitArray, arrayOfInt, i, UPCEANReader.L_AND_G_PATTERNS);
      paramStringBuilder.append((char)(i2 % 10 + 48));
      int n = arrayOfInt.length;
      j = 0;
      while (j < n)
      {
        i += arrayOfInt[j];
        j += 1;
      }
      n = m;
      if (i2 >= 10) {
        n = m | 1 << 4 - k;
      }
      j = i;
      if (k != 4) {
        j = paramBitArray.getNextUnset(paramBitArray.getNextSet(i));
      }
      k += 1;
      m = n;
      i = j;
    }
    if (paramStringBuilder.length() != 5) {
      throw NotFoundException.getNotFoundInstance();
    }
    int j = determineCheckDigit(m);
    if (extensionChecksum(paramStringBuilder.toString()) != j) {
      throw NotFoundException.getNotFoundInstance();
    }
    return i;
  }
  
  private static int determineCheckDigit(int paramInt)
    throws NotFoundException
  {
    int i = 0;
    while (i < 10)
    {
      if (paramInt == CHECK_DIGIT_ENCODINGS[i]) {
        return i;
      }
      i += 1;
    }
    throw NotFoundException.getNotFoundInstance();
  }
  
  private static int extensionChecksum(CharSequence paramCharSequence)
  {
    int k = paramCharSequence.length();
    int j = 0;
    int i = k - 2;
    while (i >= 0)
    {
      j += paramCharSequence.charAt(i) - '0';
      i -= 2;
    }
    j *= 3;
    i = k - 1;
    while (i >= 0)
    {
      j += paramCharSequence.charAt(i) - '0';
      i -= 2;
    }
    return j * 3 % 10;
  }
  
  private static String parseExtension5String(String paramString)
  {
    String str;
    int j;
    int i;
    switch (paramString.charAt(0))
    {
    default: 
      str = "";
      j = Integer.parseInt(paramString.substring(1));
      i = j / 100;
      j %= 100;
      if (j >= 10) {
        break;
      }
    }
    for (paramString = "0" + j;; paramString = String.valueOf(j))
    {
      return str + String.valueOf(i) + '.' + paramString;
      str = "£";
      break;
      str = "$";
      break;
      if ("90000".equals(paramString)) {
        return null;
      }
      if ("99991".equals(paramString)) {
        return "0.00";
      }
      if ("99990".equals(paramString)) {
        return "Used";
      }
      str = "";
      break;
    }
  }
  
  private static Map<ResultMetadataType, Object> parseExtensionString(String paramString)
  {
    if (paramString.length() != 5) {}
    do
    {
      return null;
      paramString = parseExtension5String(paramString);
    } while (paramString == null);
    EnumMap localEnumMap = new EnumMap(ResultMetadataType.class);
    localEnumMap.put(ResultMetadataType.SUGGESTED_PRICE, paramString);
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


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\UPCEANExtension5Support.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */