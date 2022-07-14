package com.google.zxing.qrcode.encoder;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import com.google.zxing.qrcode.decoder.Version.ECBlocks;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public final class Encoder
{
  private static final int[] ALPHANUMERIC_TABLE = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1 };
  static final String DEFAULT_BYTE_MODE_ENCODING = "ISO-8859-1";
  
  static void append8BitBytes(String paramString1, BitArray paramBitArray, String paramString2)
    throws WriterException
  {
    try
    {
      paramString1 = paramString1.getBytes(paramString2);
      int j = paramString1.length;
      int i = 0;
      while (i < j)
      {
        paramBitArray.appendBits(paramString1[i], 8);
        i += 1;
      }
      return;
    }
    catch (UnsupportedEncodingException paramString1)
    {
      throw new WriterException(paramString1);
    }
  }
  
  static void appendAlphanumericBytes(CharSequence paramCharSequence, BitArray paramBitArray)
    throws WriterException
  {
    int j = paramCharSequence.length();
    int i = 0;
    while (i < j)
    {
      int k = getAlphanumericCode(paramCharSequence.charAt(i));
      if (k == -1) {
        throw new WriterException();
      }
      if (i + 1 < j)
      {
        int m = getAlphanumericCode(paramCharSequence.charAt(i + 1));
        if (m == -1) {
          throw new WriterException();
        }
        paramBitArray.appendBits(k * 45 + m, 11);
        i += 2;
      }
      else
      {
        paramBitArray.appendBits(k, 6);
        i += 1;
      }
    }
  }
  
  static void appendBytes(String paramString1, Mode paramMode, BitArray paramBitArray, String paramString2)
    throws WriterException
  {
    switch (paramMode)
    {
    default: 
      throw new WriterException("Invalid mode: " + paramMode);
    case ???: 
      appendNumericBytes(paramString1, paramBitArray);
      return;
    case ???: 
      appendAlphanumericBytes(paramString1, paramBitArray);
      return;
    case ???: 
      append8BitBytes(paramString1, paramBitArray, paramString2);
      return;
    }
    appendKanjiBytes(paramString1, paramBitArray);
  }
  
  private static void appendECI(CharacterSetECI paramCharacterSetECI, BitArray paramBitArray)
  {
    paramBitArray.appendBits(Mode.ECI.getBits(), 4);
    paramBitArray.appendBits(paramCharacterSetECI.getValue(), 8);
  }
  
  static void appendKanjiBytes(String paramString, BitArray paramBitArray)
    throws WriterException
  {
    for (;;)
    {
      int j;
      int n;
      int k;
      try
      {
        paramString = paramString.getBytes("Shift_JIS");
        int m = paramString.length;
        j = 0;
        if (j >= m) {
          break;
        }
        n = (paramString[j] & 0xFF) << 8 | paramString[(j + 1)] & 0xFF;
        k = -1;
        if ((n >= 33088) && (n <= 40956))
        {
          i = n - 33088;
          if (i != -1) {
            break label118;
          }
          throw new WriterException("Invalid byte sequence");
        }
      }
      catch (UnsupportedEncodingException paramString)
      {
        throw new WriterException(paramString);
      }
      int i = k;
      if (n >= 57408)
      {
        i = k;
        if (n <= 60351)
        {
          i = n - 49472;
          continue;
          label118:
          paramBitArray.appendBits((i >> 8) * 192 + (i & 0xFF), 13);
          j += 2;
        }
      }
    }
  }
  
  static void appendLengthInfo(int paramInt, Version paramVersion, Mode paramMode, BitArray paramBitArray)
    throws WriterException
  {
    int i = paramMode.getCharacterCountBits(paramVersion);
    if (paramInt >= 1 << i) {
      throw new WriterException(paramInt + " is bigger than " + ((1 << i) - 1));
    }
    paramBitArray.appendBits(paramInt, i);
  }
  
  static void appendModeInfo(Mode paramMode, BitArray paramBitArray)
  {
    paramBitArray.appendBits(paramMode.getBits(), 4);
  }
  
  static void appendNumericBytes(CharSequence paramCharSequence, BitArray paramBitArray)
  {
    int j = paramCharSequence.length();
    int i = 0;
    while (i < j)
    {
      int k = paramCharSequence.charAt(i) - '0';
      if (i + 2 < j)
      {
        paramBitArray.appendBits(k * 100 + (paramCharSequence.charAt(i + 1) - '0') * 10 + (paramCharSequence.charAt(i + 2) - '0'), 10);
        i += 3;
      }
      else if (i + 1 < j)
      {
        paramBitArray.appendBits(k * 10 + (paramCharSequence.charAt(i + 1) - '0'), 7);
        i += 2;
      }
      else
      {
        paramBitArray.appendBits(k, 4);
        i += 1;
      }
    }
  }
  
  private static int calculateBitsNeeded(Mode paramMode, BitArray paramBitArray1, BitArray paramBitArray2, Version paramVersion)
  {
    return paramBitArray1.getSize() + paramMode.getCharacterCountBits(paramVersion) + paramBitArray2.getSize();
  }
  
  private static int calculateMaskPenalty(ByteMatrix paramByteMatrix)
  {
    return MaskUtil.applyMaskPenaltyRule1(paramByteMatrix) + MaskUtil.applyMaskPenaltyRule2(paramByteMatrix) + MaskUtil.applyMaskPenaltyRule3(paramByteMatrix) + MaskUtil.applyMaskPenaltyRule4(paramByteMatrix);
  }
  
  private static int chooseMaskPattern(BitArray paramBitArray, ErrorCorrectionLevel paramErrorCorrectionLevel, Version paramVersion, ByteMatrix paramByteMatrix)
    throws WriterException
  {
    int j = Integer.MAX_VALUE;
    int m = -1;
    int i = 0;
    while (i < 8)
    {
      MatrixUtil.buildMatrix(paramBitArray, paramErrorCorrectionLevel, paramVersion, i, paramByteMatrix);
      int n = calculateMaskPenalty(paramByteMatrix);
      int k = j;
      if (n < j)
      {
        k = n;
        m = i;
      }
      i += 1;
      j = k;
    }
    return m;
  }
  
  public static Mode chooseMode(String paramString)
  {
    return chooseMode(paramString, null);
  }
  
  private static Mode chooseMode(String paramString1, String paramString2)
  {
    if (("Shift_JIS".equals(paramString2)) && (isOnlyDoubleByteKanji(paramString1))) {
      return Mode.KANJI;
    }
    int j = 0;
    int k = 0;
    int i = 0;
    if (i < paramString1.length())
    {
      int m = paramString1.charAt(i);
      if ((m >= 48) && (m <= 57)) {
        j = 1;
      }
      for (;;)
      {
        i += 1;
        break;
        if (getAlphanumericCode(m) == -1) {
          break label80;
        }
        k = 1;
      }
      label80:
      return Mode.BYTE;
    }
    if (k != 0) {
      return Mode.ALPHANUMERIC;
    }
    if (j != 0) {
      return Mode.NUMERIC;
    }
    return Mode.BYTE;
  }
  
  private static Version chooseVersion(int paramInt, ErrorCorrectionLevel paramErrorCorrectionLevel)
    throws WriterException
  {
    int i = 1;
    while (i <= 40)
    {
      Version localVersion = Version.getVersionForNumber(i);
      if (willFit(paramInt, localVersion, paramErrorCorrectionLevel)) {
        return localVersion;
      }
      i += 1;
    }
    throw new WriterException("Data too big");
  }
  
  public static QRCode encode(String paramString, ErrorCorrectionLevel paramErrorCorrectionLevel)
    throws WriterException
  {
    return encode(paramString, paramErrorCorrectionLevel, null);
  }
  
  public static QRCode encode(String paramString, ErrorCorrectionLevel paramErrorCorrectionLevel, Map<EncodeHintType, ?> paramMap)
    throws WriterException
  {
    Object localObject2 = "ISO-8859-1";
    Object localObject1 = localObject2;
    if (paramMap != null)
    {
      localObject1 = localObject2;
      if (paramMap.containsKey(EncodeHintType.CHARACTER_SET)) {
        localObject1 = paramMap.get(EncodeHintType.CHARACTER_SET).toString();
      }
    }
    localObject2 = chooseMode(paramString, (String)localObject1);
    BitArray localBitArray = new BitArray();
    if ((localObject2 == Mode.BYTE) && (!"ISO-8859-1".equals(localObject1)))
    {
      localObject3 = CharacterSetECI.getCharacterSetECIByName((String)localObject1);
      if (localObject3 != null) {
        appendECI((CharacterSetECI)localObject3, localBitArray);
      }
    }
    appendModeInfo((Mode)localObject2, localBitArray);
    Object localObject3 = new BitArray();
    appendBytes(paramString, (Mode)localObject2, (BitArray)localObject3, (String)localObject1);
    if ((paramMap != null) && (paramMap.containsKey(EncodeHintType.QR_VERSION)))
    {
      localObject1 = Version.getVersionForNumber(Integer.parseInt(paramMap.get(EncodeHintType.QR_VERSION).toString()));
      paramMap = (Map<EncodeHintType, ?>)localObject1;
      if (!willFit(calculateBitsNeeded((Mode)localObject2, localBitArray, (BitArray)localObject3, (Version)localObject1), (Version)localObject1, paramErrorCorrectionLevel)) {
        throw new WriterException("Data too big for requested version");
      }
    }
    else
    {
      paramMap = recommendVersion(paramErrorCorrectionLevel, (Mode)localObject2, localBitArray, (BitArray)localObject3);
    }
    localObject1 = new BitArray();
    ((BitArray)localObject1).appendBitArray(localBitArray);
    if (localObject2 == Mode.BYTE) {}
    for (int i = ((BitArray)localObject3).getSizeInBytes();; i = paramString.length())
    {
      appendLengthInfo(i, paramMap, (Mode)localObject2, (BitArray)localObject1);
      ((BitArray)localObject1).appendBitArray((BitArray)localObject3);
      paramString = paramMap.getECBlocksForLevel(paramErrorCorrectionLevel);
      i = paramMap.getTotalCodewords() - paramString.getTotalECCodewords();
      terminateBits(i, (BitArray)localObject1);
      paramString = interleaveWithECBytes((BitArray)localObject1, paramMap.getTotalCodewords(), i, paramString.getNumBlocks());
      localObject1 = new QRCode();
      ((QRCode)localObject1).setECLevel(paramErrorCorrectionLevel);
      ((QRCode)localObject1).setMode((Mode)localObject2);
      ((QRCode)localObject1).setVersion(paramMap);
      i = paramMap.getDimensionForVersion();
      localObject2 = new ByteMatrix(i, i);
      i = chooseMaskPattern(paramString, paramErrorCorrectionLevel, paramMap, (ByteMatrix)localObject2);
      ((QRCode)localObject1).setMaskPattern(i);
      MatrixUtil.buildMatrix(paramString, paramErrorCorrectionLevel, paramMap, i, (ByteMatrix)localObject2);
      ((QRCode)localObject1).setMatrix((ByteMatrix)localObject2);
      return (QRCode)localObject1;
    }
  }
  
  static byte[] generateECBytes(byte[] paramArrayOfByte, int paramInt)
  {
    int j = paramArrayOfByte.length;
    int[] arrayOfInt = new int[j + paramInt];
    int i = 0;
    while (i < j)
    {
      paramArrayOfByte[i] &= 0xFF;
      i += 1;
    }
    new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256).encode(arrayOfInt, paramInt);
    paramArrayOfByte = new byte[paramInt];
    i = 0;
    while (i < paramInt)
    {
      paramArrayOfByte[i] = ((byte)arrayOfInt[(j + i)]);
      i += 1;
    }
    return paramArrayOfByte;
  }
  
  static int getAlphanumericCode(int paramInt)
  {
    if (paramInt < ALPHANUMERIC_TABLE.length) {
      return ALPHANUMERIC_TABLE[paramInt];
    }
    return -1;
  }
  
  static void getNumDataBytesAndNumECBytesForBlockID(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
    throws WriterException
  {
    if (paramInt4 >= paramInt3) {
      throw new WriterException("Block ID too large");
    }
    int i = paramInt1 % paramInt3;
    int j = paramInt3 - i;
    int k = paramInt1 / paramInt3;
    paramInt2 /= paramInt3;
    int m = paramInt2 + 1;
    int n = k - paramInt2;
    k = k + 1 - m;
    if (n != k) {
      throw new WriterException("EC bytes mismatch");
    }
    if (paramInt3 != j + i) {
      throw new WriterException("RS blocks mismatch");
    }
    if (paramInt1 != (paramInt2 + n) * j + (m + k) * i) {
      throw new WriterException("Total bytes mismatch");
    }
    if (paramInt4 < j)
    {
      paramArrayOfInt1[0] = paramInt2;
      paramArrayOfInt2[0] = n;
      return;
    }
    paramArrayOfInt1[0] = m;
    paramArrayOfInt2[0] = k;
  }
  
  static BitArray interleaveWithECBytes(BitArray paramBitArray, int paramInt1, int paramInt2, int paramInt3)
    throws WriterException
  {
    if (paramBitArray.getSizeInBytes() != paramInt2) {
      throw new WriterException("Number of bits and data bytes does not match");
    }
    int m = 0;
    int j = 0;
    int i = 0;
    ArrayList localArrayList = new ArrayList(paramInt3);
    int k = 0;
    Object localObject1;
    byte[] arrayOfByte;
    while (k < paramInt3)
    {
      localObject1 = new int[1];
      Object localObject2 = new int[1];
      getNumDataBytesAndNumECBytesForBlockID(paramInt1, paramInt2, paramInt3, k, (int[])localObject1, (int[])localObject2);
      int n = localObject1[0];
      arrayOfByte = new byte[n];
      paramBitArray.toBytes(m * 8, arrayOfByte, 0, n);
      localObject2 = generateECBytes(arrayOfByte, localObject2[0]);
      localArrayList.add(new BlockPair(arrayOfByte, (byte[])localObject2));
      j = Math.max(j, n);
      i = Math.max(i, localObject2.length);
      m += localObject1[0];
      k += 1;
    }
    if (paramInt2 != m) {
      throw new WriterException("Data bytes does not match offset");
    }
    paramBitArray = new BitArray();
    paramInt2 = 0;
    while (paramInt2 < j)
    {
      localObject1 = localArrayList.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        arrayOfByte = ((BlockPair)((Iterator)localObject1).next()).getDataBytes();
        if (paramInt2 < arrayOfByte.length) {
          paramBitArray.appendBits(arrayOfByte[paramInt2], 8);
        }
      }
      paramInt2 += 1;
    }
    paramInt2 = 0;
    while (paramInt2 < i)
    {
      localObject1 = localArrayList.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        arrayOfByte = ((BlockPair)((Iterator)localObject1).next()).getErrorCorrectionBytes();
        if (paramInt2 < arrayOfByte.length) {
          paramBitArray.appendBits(arrayOfByte[paramInt2], 8);
        }
      }
      paramInt2 += 1;
    }
    if (paramInt1 != paramBitArray.getSizeInBytes()) {
      throw new WriterException("Interleaving error: " + paramInt1 + " and " + paramBitArray.getSizeInBytes() + " differ.");
    }
    return paramBitArray;
  }
  
  private static boolean isOnlyDoubleByteKanji(String paramString)
  {
    int j;
    try
    {
      paramString = paramString.getBytes("Shift_JIS");
      j = paramString.length;
      if (j % 2 != 0) {
        return false;
      }
    }
    catch (UnsupportedEncodingException paramString)
    {
      return false;
    }
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label71;
      }
      int k = paramString[i] & 0xFF;
      if (((k < 129) || (k > 159)) && ((k < 224) || (k > 235))) {
        break;
      }
      i += 2;
    }
    label71:
    return true;
  }
  
  private static Version recommendVersion(ErrorCorrectionLevel paramErrorCorrectionLevel, Mode paramMode, BitArray paramBitArray1, BitArray paramBitArray2)
    throws WriterException
  {
    return chooseVersion(calculateBitsNeeded(paramMode, paramBitArray1, paramBitArray2, chooseVersion(calculateBitsNeeded(paramMode, paramBitArray1, paramBitArray2, Version.getVersionForNumber(1)), paramErrorCorrectionLevel)), paramErrorCorrectionLevel);
  }
  
  static void terminateBits(int paramInt, BitArray paramBitArray)
    throws WriterException
  {
    int k = paramInt * 8;
    if (paramBitArray.getSize() > k) {
      throw new WriterException("data bits cannot fit in the QR Code" + paramBitArray.getSize() + " > " + k);
    }
    int i = 0;
    while ((i < 4) && (paramBitArray.getSize() < k))
    {
      paramBitArray.appendBit(false);
      i += 1;
    }
    i = paramBitArray.getSize() & 0x7;
    if (i > 0) {
      while (i < 8)
      {
        paramBitArray.appendBit(false);
        i += 1;
      }
    }
    int m = paramBitArray.getSizeInBytes();
    i = 0;
    if (i < paramInt - m)
    {
      if ((i & 0x1) == 0) {}
      for (int j = 236;; j = 17)
      {
        paramBitArray.appendBits(j, 8);
        i += 1;
        break;
      }
    }
    if (paramBitArray.getSize() != k) {
      throw new WriterException("Bits size does not equal capacity");
    }
  }
  
  private static boolean willFit(int paramInt, Version paramVersion, ErrorCorrectionLevel paramErrorCorrectionLevel)
  {
    return paramVersion.getTotalCodewords() - paramVersion.getECBlocksForLevel(paramErrorCorrectionLevel).getTotalECCodewords() >= (paramInt + 7) / 8;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\qrcode\encoder\Encoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */