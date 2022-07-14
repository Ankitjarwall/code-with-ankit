package com.google.zxing.qrcode.decoder;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

final class DecodedBitStreamParser
{
  private static final char[] ALPHANUMERIC_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".toCharArray();
  private static final int GB2312_SUBSET = 1;
  
  static DecoderResult decode(byte[] paramArrayOfByte, Version paramVersion, ErrorCorrectionLevel paramErrorCorrectionLevel, Map<DecodeHintType, ?> paramMap)
    throws FormatException
  {
    BitSource localBitSource = new BitSource(paramArrayOfByte);
    StringBuilder localStringBuilder = new StringBuilder(50);
    ArrayList localArrayList = new ArrayList(1);
    int j = -1;
    int i = -1;
    Object localObject1 = null;
    boolean bool1 = false;
    for (;;)
    {
      Mode localMode1;
      try
      {
        if (localBitSource.available() < 4)
        {
          localMode1 = Mode.TERMINATOR;
          localObject2 = localObject1;
          k = j;
          m = i;
          bool2 = bool1;
          if (localMode1 != Mode.TERMINATOR)
          {
            if (localMode1 == Mode.FNC1_FIRST_POSITION) {
              break label542;
            }
            if (localMode1 == Mode.FNC1_SECOND_POSITION) {
              break label542;
            }
          }
          else
          {
            Mode localMode2 = Mode.TERMINATOR;
            localObject1 = localObject2;
            j = k;
            i = m;
            bool1 = bool2;
            if (localMode1 != localMode2) {
              continue;
            }
            paramMap = localStringBuilder.toString();
            if (!localArrayList.isEmpty()) {
              break label528;
            }
            paramVersion = null;
            if (paramErrorCorrectionLevel != null) {
              break label534;
            }
            paramErrorCorrectionLevel = null;
            return new DecoderResult(paramArrayOfByte, paramMap, paramVersion, paramErrorCorrectionLevel, k, m);
          }
        }
        else
        {
          localMode1 = Mode.forBits(localBitSource.readBits(4));
          continue;
        }
        if (localMode1 != Mode.STRUCTURED_APPEND) {
          break label238;
        }
        if (localBitSource.available() < 16) {
          throw FormatException.getFormatInstance();
        }
      }
      catch (IllegalArgumentException paramArrayOfByte)
      {
        throw FormatException.getFormatInstance();
      }
      int k = localBitSource.readBits(8);
      int m = localBitSource.readBits(8);
      Object localObject2 = localObject1;
      boolean bool2 = bool1;
      continue;
      label238:
      if (localMode1 == Mode.ECI)
      {
        localObject1 = CharacterSetECI.getCharacterSetECIByValue(parseECIValue(localBitSource));
        localObject2 = localObject1;
        k = j;
        m = i;
        bool2 = bool1;
        if (localObject1 == null) {
          throw FormatException.getFormatInstance();
        }
      }
      else if (localMode1 == Mode.HANZI)
      {
        int n = localBitSource.readBits(4);
        int i1 = localBitSource.readBits(localMode1.getCharacterCountBits(paramVersion));
        localObject2 = localObject1;
        k = j;
        m = i;
        bool2 = bool1;
        if (n == 1)
        {
          decodeHanziSegment(localBitSource, localStringBuilder, i1);
          localObject2 = localObject1;
          k = j;
          m = i;
          bool2 = bool1;
        }
      }
      else
      {
        k = localBitSource.readBits(localMode1.getCharacterCountBits(paramVersion));
        if (localMode1 == Mode.NUMERIC)
        {
          decodeNumericSegment(localBitSource, localStringBuilder, k);
          localObject2 = localObject1;
          k = j;
          m = i;
          bool2 = bool1;
        }
        else if (localMode1 == Mode.ALPHANUMERIC)
        {
          decodeAlphanumericSegment(localBitSource, localStringBuilder, k, bool1);
          localObject2 = localObject1;
          k = j;
          m = i;
          bool2 = bool1;
        }
        else if (localMode1 == Mode.BYTE)
        {
          decodeByteSegment(localBitSource, localStringBuilder, k, (CharacterSetECI)localObject1, localArrayList, paramMap);
          localObject2 = localObject1;
          k = j;
          m = i;
          bool2 = bool1;
        }
        else if (localMode1 == Mode.KANJI)
        {
          decodeKanjiSegment(localBitSource, localStringBuilder, k);
          localObject2 = localObject1;
          k = j;
          m = i;
          bool2 = bool1;
        }
        else
        {
          throw FormatException.getFormatInstance();
          label528:
          paramVersion = localArrayList;
          continue;
          label534:
          paramErrorCorrectionLevel = paramErrorCorrectionLevel.toString();
          continue;
          label542:
          bool2 = true;
          localObject2 = localObject1;
          k = j;
          m = i;
        }
      }
    }
  }
  
  private static void decodeAlphanumericSegment(BitSource paramBitSource, StringBuilder paramStringBuilder, int paramInt, boolean paramBoolean)
    throws FormatException
  {
    int i = paramStringBuilder.length();
    while (paramInt > 1)
    {
      if (paramBitSource.available() < 11) {
        throw FormatException.getFormatInstance();
      }
      int j = paramBitSource.readBits(11);
      paramStringBuilder.append(toAlphaNumericChar(j / 45));
      paramStringBuilder.append(toAlphaNumericChar(j % 45));
      paramInt -= 2;
    }
    if (paramInt == 1)
    {
      if (paramBitSource.available() < 6) {
        throw FormatException.getFormatInstance();
      }
      paramStringBuilder.append(toAlphaNumericChar(paramBitSource.readBits(6)));
    }
    if (paramBoolean)
    {
      paramInt = i;
      if (paramInt < paramStringBuilder.length())
      {
        if (paramStringBuilder.charAt(paramInt) == '%')
        {
          if ((paramInt >= paramStringBuilder.length() - 1) || (paramStringBuilder.charAt(paramInt + 1) != '%')) {
            break label159;
          }
          paramStringBuilder.deleteCharAt(paramInt + 1);
        }
        for (;;)
        {
          paramInt += 1;
          break;
          label159:
          paramStringBuilder.setCharAt(paramInt, '\035');
        }
      }
    }
  }
  
  private static void decodeByteSegment(BitSource paramBitSource, StringBuilder paramStringBuilder, int paramInt, CharacterSetECI paramCharacterSetECI, Collection<byte[]> paramCollection, Map<DecodeHintType, ?> paramMap)
    throws FormatException
  {
    if (paramInt * 8 > paramBitSource.available()) {
      throw FormatException.getFormatInstance();
    }
    byte[] arrayOfByte = new byte[paramInt];
    int i = 0;
    while (i < paramInt)
    {
      arrayOfByte[i] = ((byte)paramBitSource.readBits(8));
      i += 1;
    }
    if (paramCharacterSetECI == null) {}
    for (paramBitSource = StringUtils.guessEncoding(arrayOfByte, paramMap);; paramBitSource = paramCharacterSetECI.name()) {
      try
      {
        paramStringBuilder.append(new String(arrayOfByte, paramBitSource));
        paramCollection.add(arrayOfByte);
        return;
      }
      catch (UnsupportedEncodingException paramBitSource)
      {
        throw FormatException.getFormatInstance();
      }
    }
  }
  
  private static void decodeHanziSegment(BitSource paramBitSource, StringBuilder paramStringBuilder, int paramInt)
    throws FormatException
  {
    if (paramInt * 13 > paramBitSource.available()) {
      throw FormatException.getFormatInstance();
    }
    byte[] arrayOfByte = new byte[paramInt * 2];
    int i = 0;
    if (paramInt > 0)
    {
      int j = paramBitSource.readBits(13);
      j = j / 96 << 8 | j % 96;
      if (j < 959) {
        j += 41377;
      }
      for (;;)
      {
        arrayOfByte[i] = ((byte)(j >> 8 & 0xFF));
        arrayOfByte[(i + 1)] = ((byte)(j & 0xFF));
        i += 2;
        paramInt -= 1;
        break;
        j += 42657;
      }
    }
    try
    {
      paramStringBuilder.append(new String(arrayOfByte, "GB2312"));
      return;
    }
    catch (UnsupportedEncodingException paramBitSource)
    {
      throw FormatException.getFormatInstance();
    }
  }
  
  private static void decodeKanjiSegment(BitSource paramBitSource, StringBuilder paramStringBuilder, int paramInt)
    throws FormatException
  {
    if (paramInt * 13 > paramBitSource.available()) {
      throw FormatException.getFormatInstance();
    }
    byte[] arrayOfByte = new byte[paramInt * 2];
    int i = 0;
    if (paramInt > 0)
    {
      int j = paramBitSource.readBits(13);
      j = j / 192 << 8 | j % 192;
      if (j < 7936) {
        j += 33088;
      }
      for (;;)
      {
        arrayOfByte[i] = ((byte)(j >> 8));
        arrayOfByte[(i + 1)] = ((byte)j);
        i += 2;
        paramInt -= 1;
        break;
        j += 49472;
      }
    }
    try
    {
      paramStringBuilder.append(new String(arrayOfByte, "SJIS"));
      return;
    }
    catch (UnsupportedEncodingException paramBitSource)
    {
      throw FormatException.getFormatInstance();
    }
  }
  
  private static void decodeNumericSegment(BitSource paramBitSource, StringBuilder paramStringBuilder, int paramInt)
    throws FormatException
  {
    while (paramInt >= 3)
    {
      if (paramBitSource.available() < 10) {
        throw FormatException.getFormatInstance();
      }
      int i = paramBitSource.readBits(10);
      if (i >= 1000) {
        throw FormatException.getFormatInstance();
      }
      paramStringBuilder.append(toAlphaNumericChar(i / 100));
      paramStringBuilder.append(toAlphaNumericChar(i / 10 % 10));
      paramStringBuilder.append(toAlphaNumericChar(i % 10));
      paramInt -= 3;
    }
    if (paramInt == 2)
    {
      if (paramBitSource.available() < 7) {
        throw FormatException.getFormatInstance();
      }
      paramInt = paramBitSource.readBits(7);
      if (paramInt >= 100) {
        throw FormatException.getFormatInstance();
      }
      paramStringBuilder.append(toAlphaNumericChar(paramInt / 10));
      paramStringBuilder.append(toAlphaNumericChar(paramInt % 10));
    }
    while (paramInt != 1) {
      return;
    }
    if (paramBitSource.available() < 4) {
      throw FormatException.getFormatInstance();
    }
    paramInt = paramBitSource.readBits(4);
    if (paramInt >= 10) {
      throw FormatException.getFormatInstance();
    }
    paramStringBuilder.append(toAlphaNumericChar(paramInt));
  }
  
  private static int parseECIValue(BitSource paramBitSource)
    throws FormatException
  {
    int i = paramBitSource.readBits(8);
    if ((i & 0x80) == 0) {
      return i & 0x7F;
    }
    if ((i & 0xC0) == 128) {
      return (i & 0x3F) << 8 | paramBitSource.readBits(8);
    }
    if ((i & 0xE0) == 192) {
      return (i & 0x1F) << 16 | paramBitSource.readBits(16);
    }
    throw FormatException.getFormatInstance();
  }
  
  private static char toAlphaNumericChar(int paramInt)
    throws FormatException
  {
    if (paramInt >= ALPHANUMERIC_CHARS.length) {
      throw FormatException.getFormatInstance();
    }
    return ALPHANUMERIC_CHARS[paramInt];
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\qrcode\decoder\DecodedBitStreamParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */