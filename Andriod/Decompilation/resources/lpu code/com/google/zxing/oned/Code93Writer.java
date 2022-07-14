package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public class Code93Writer
  extends OneDimensionalCodeWriter
{
  protected static int appendPattern(boolean[] paramArrayOfBoolean, int paramInt, int[] paramArrayOfInt, boolean paramBoolean)
  {
    int j = paramArrayOfInt.length;
    int i = 0;
    if (i < j)
    {
      if (paramArrayOfInt[i] != 0) {}
      for (paramBoolean = true;; paramBoolean = false)
      {
        paramArrayOfBoolean[paramInt] = paramBoolean;
        i += 1;
        paramInt += 1;
        break;
      }
    }
    return 9;
  }
  
  private static int computeChecksumIndex(String paramString, int paramInt)
  {
    int i = 1;
    int k = 0;
    int j = paramString.length() - 1;
    while (j >= 0)
    {
      k += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(paramString.charAt(j)) * i;
      int m = i + 1;
      i = m;
      if (m > paramInt) {
        i = 1;
      }
      j -= 1;
    }
    return k % 47;
  }
  
  private static void toIntArray(int paramInt, int[] paramArrayOfInt)
  {
    int i = 0;
    if (i < 9)
    {
      if ((paramInt & 1 << 8 - i) == 0) {}
      for (int j = 0;; j = 1)
      {
        paramArrayOfInt[i] = j;
        i += 1;
        break;
      }
    }
  }
  
  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
    throws WriterException
  {
    if (paramBarcodeFormat != BarcodeFormat.CODE_93) {
      throw new IllegalArgumentException("Can only encode CODE_93, but got " + paramBarcodeFormat);
    }
    return super.encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, paramMap);
  }
  
  public boolean[] encode(String paramString)
  {
    int k = paramString.length();
    if (k > 80) {
      throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + k);
    }
    int[] arrayOfInt = new int[9];
    boolean[] arrayOfBoolean = new boolean[(paramString.length() + 2 + 2) * 9 + 1];
    toIntArray(Code93Reader.CHARACTER_ENCODINGS[47], arrayOfInt);
    int i = appendPattern(arrayOfBoolean, 0, arrayOfInt, true);
    int j = 0;
    while (j < k)
    {
      int m = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(paramString.charAt(j));
      toIntArray(Code93Reader.CHARACTER_ENCODINGS[m], arrayOfInt);
      i += appendPattern(arrayOfBoolean, i, arrayOfInt, true);
      j += 1;
    }
    j = computeChecksumIndex(paramString, 20);
    toIntArray(Code93Reader.CHARACTER_ENCODINGS[j], arrayOfInt);
    i += appendPattern(arrayOfBoolean, i, arrayOfInt, true);
    j = computeChecksumIndex(paramString + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".charAt(j), 15);
    toIntArray(Code93Reader.CHARACTER_ENCODINGS[j], arrayOfInt);
    i += appendPattern(arrayOfBoolean, i, arrayOfInt, true);
    toIntArray(Code93Reader.CHARACTER_ENCODINGS[47], arrayOfInt);
    arrayOfBoolean[(i + appendPattern(arrayOfBoolean, i, arrayOfInt, true))] = true;
    return arrayOfBoolean;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\Code93Writer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */