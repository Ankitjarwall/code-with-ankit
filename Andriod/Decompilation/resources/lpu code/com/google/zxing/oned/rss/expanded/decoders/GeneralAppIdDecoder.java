package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class GeneralAppIdDecoder
{
  private final StringBuilder buffer = new StringBuilder();
  private final CurrentParsingState current = new CurrentParsingState();
  private final BitArray information;
  
  GeneralAppIdDecoder(BitArray paramBitArray)
  {
    this.information = paramBitArray;
  }
  
  private DecodedChar decodeAlphanumeric(int paramInt)
  {
    int i = extractNumericValueFromBitArray(paramInt, 5);
    if (i == 15) {
      return new DecodedChar(paramInt + 5, '$');
    }
    if ((i >= 5) && (i < 15)) {
      return new DecodedChar(paramInt + 5, (char)(i + 48 - 5));
    }
    i = extractNumericValueFromBitArray(paramInt, 6);
    if ((i >= 32) && (i < 58)) {
      return new DecodedChar(paramInt + 6, (char)(i + 33));
    }
    char c;
    switch (i)
    {
    default: 
      throw new IllegalStateException("Decoding invalid alphanumeric value: " + i);
    case 58: 
      c = '*';
    }
    for (;;)
    {
      return new DecodedChar(paramInt + 6, c);
      c = ',';
      continue;
      c = '-';
      continue;
      c = '.';
      continue;
      c = '/';
    }
  }
  
  private DecodedChar decodeIsoIec646(int paramInt)
    throws FormatException
  {
    int i = extractNumericValueFromBitArray(paramInt, 5);
    if (i == 15) {
      return new DecodedChar(paramInt + 5, '$');
    }
    if ((i >= 5) && (i < 15)) {
      return new DecodedChar(paramInt + 5, (char)(i + 48 - 5));
    }
    i = extractNumericValueFromBitArray(paramInt, 7);
    if ((i >= 64) && (i < 90)) {
      return new DecodedChar(paramInt + 7, (char)(i + 1));
    }
    if ((i >= 90) && (i < 116)) {
      return new DecodedChar(paramInt + 7, (char)(i + 7));
    }
    char c;
    switch (extractNumericValueFromBitArray(paramInt, 8))
    {
    default: 
      throw FormatException.getFormatInstance();
    case 232: 
      c = '!';
    }
    for (;;)
    {
      return new DecodedChar(paramInt + 8, c);
      c = '"';
      continue;
      c = '%';
      continue;
      c = '&';
      continue;
      c = '\'';
      continue;
      c = '(';
      continue;
      c = ')';
      continue;
      c = '*';
      continue;
      c = '+';
      continue;
      c = ',';
      continue;
      c = '-';
      continue;
      c = '.';
      continue;
      c = '/';
      continue;
      c = ':';
      continue;
      c = ';';
      continue;
      c = '<';
      continue;
      c = '=';
      continue;
      c = '>';
      continue;
      c = '?';
      continue;
      c = '_';
      continue;
      c = ' ';
    }
  }
  
  private DecodedNumeric decodeNumeric(int paramInt)
    throws FormatException
  {
    if (paramInt + 7 > this.information.getSize())
    {
      paramInt = extractNumericValueFromBitArray(paramInt, 4);
      if (paramInt == 0) {
        return new DecodedNumeric(this.information.getSize(), 10, 10);
      }
      return new DecodedNumeric(this.information.getSize(), paramInt - 1, 10);
    }
    int i = extractNumericValueFromBitArray(paramInt, 7);
    return new DecodedNumeric(paramInt + 7, (i - 8) / 11, (i - 8) % 11);
  }
  
  static int extractNumericValueFromBitArray(BitArray paramBitArray, int paramInt1, int paramInt2)
  {
    int j = 0;
    int i = 0;
    while (i < paramInt2)
    {
      int k = j;
      if (paramBitArray.get(paramInt1 + i)) {
        k = j | 1 << paramInt2 - i - 1;
      }
      i += 1;
      j = k;
    }
    return j;
  }
  
  private boolean isAlphaOr646ToNumericLatch(int paramInt)
  {
    if (paramInt + 3 > this.information.getSize()) {
      return false;
    }
    int i = paramInt;
    for (;;)
    {
      if (i >= paramInt + 3) {
        break label42;
      }
      if (this.information.get(i)) {
        break;
      }
      i += 1;
    }
    label42:
    return true;
  }
  
  private boolean isAlphaTo646ToAlphaLatch(int paramInt)
  {
    if (paramInt + 1 > this.information.getSize()) {}
    int i;
    do
    {
      return false;
      i = 0;
      if ((i >= 5) || (i + paramInt >= this.information.getSize())) {
        break label75;
      }
      if (i != 2) {
        break;
      }
    } while (!this.information.get(paramInt + 2));
    while (!this.information.get(paramInt + i))
    {
      i += 1;
      break;
    }
    return false;
    label75:
    return true;
  }
  
  private boolean isNumericToAlphaNumericLatch(int paramInt)
  {
    if (paramInt + 1 > this.information.getSize()) {
      return false;
    }
    int i = 0;
    for (;;)
    {
      if ((i >= 4) || (i + paramInt >= this.information.getSize())) {
        break label55;
      }
      if (this.information.get(paramInt + i)) {
        break;
      }
      i += 1;
    }
    label55:
    return true;
  }
  
  private boolean isStillAlpha(int paramInt)
  {
    boolean bool = true;
    if (paramInt + 5 > this.information.getSize()) {}
    do
    {
      return false;
      int i = extractNumericValueFromBitArray(paramInt, 5);
      if ((i >= 5) && (i < 16)) {
        return true;
      }
    } while (paramInt + 6 > this.information.getSize());
    paramInt = extractNumericValueFromBitArray(paramInt, 6);
    if ((paramInt >= 16) && (paramInt < 63)) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  private boolean isStillIsoIec646(int paramInt)
  {
    boolean bool = true;
    if (paramInt + 5 > this.information.getSize()) {}
    do
    {
      do
      {
        return false;
        i = extractNumericValueFromBitArray(paramInt, 5);
        if ((i >= 5) && (i < 16)) {
          return true;
        }
      } while (paramInt + 7 > this.information.getSize());
      int i = extractNumericValueFromBitArray(paramInt, 7);
      if ((i >= 64) && (i < 116)) {
        return true;
      }
    } while (paramInt + 8 > this.information.getSize());
    paramInt = extractNumericValueFromBitArray(paramInt, 8);
    if ((paramInt >= 232) && (paramInt < 253)) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  private boolean isStillNumeric(int paramInt)
  {
    if (paramInt + 7 > this.information.getSize()) {
      return paramInt + 4 <= this.information.getSize();
    }
    int i = paramInt;
    for (;;)
    {
      if (i >= paramInt + 3) {
        break label58;
      }
      if (this.information.get(i)) {
        break;
      }
      i += 1;
    }
    label58:
    return this.information.get(paramInt + 3);
  }
  
  private BlockParsedResult parseAlphaBlock()
  {
    while (isStillAlpha(this.current.getPosition()))
    {
      DecodedChar localDecodedChar = decodeAlphanumeric(this.current.getPosition());
      this.current.setPosition(localDecodedChar.getNewPosition());
      if (localDecodedChar.isFNC1()) {
        return new BlockParsedResult(new DecodedInformation(this.current.getPosition(), this.buffer.toString()), true);
      }
      this.buffer.append(localDecodedChar.getValue());
    }
    if (isAlphaOr646ToNumericLatch(this.current.getPosition()))
    {
      this.current.incrementPosition(3);
      this.current.setNumeric();
    }
    while (!isAlphaTo646ToAlphaLatch(this.current.getPosition())) {
      return new BlockParsedResult(false);
    }
    if (this.current.getPosition() + 5 < this.information.getSize()) {
      this.current.incrementPosition(5);
    }
    for (;;)
    {
      this.current.setIsoIec646();
      break;
      this.current.setPosition(this.information.getSize());
    }
  }
  
  private DecodedInformation parseBlocks()
    throws FormatException
  {
    int i = this.current.getPosition();
    BlockParsedResult localBlockParsedResult;
    boolean bool;
    if (this.current.isAlpha())
    {
      localBlockParsedResult = parseAlphaBlock();
      bool = localBlockParsedResult.isFinished();
      label28:
      if (i == this.current.getPosition()) {
        break label90;
      }
      i = 1;
      label41:
      if ((i != 0) || (bool)) {
        break label95;
      }
    }
    for (;;)
    {
      return localBlockParsedResult.getDecodedInformation();
      if (this.current.isIsoIec646())
      {
        localBlockParsedResult = parseIsoIec646Block();
        bool = localBlockParsedResult.isFinished();
        break label28;
      }
      localBlockParsedResult = parseNumericBlock();
      bool = localBlockParsedResult.isFinished();
      break label28;
      label90:
      i = 0;
      break label41;
      label95:
      if (!bool) {
        break;
      }
    }
  }
  
  private BlockParsedResult parseIsoIec646Block()
    throws FormatException
  {
    while (isStillIsoIec646(this.current.getPosition()))
    {
      DecodedChar localDecodedChar = decodeIsoIec646(this.current.getPosition());
      this.current.setPosition(localDecodedChar.getNewPosition());
      if (localDecodedChar.isFNC1()) {
        return new BlockParsedResult(new DecodedInformation(this.current.getPosition(), this.buffer.toString()), true);
      }
      this.buffer.append(localDecodedChar.getValue());
    }
    if (isAlphaOr646ToNumericLatch(this.current.getPosition()))
    {
      this.current.incrementPosition(3);
      this.current.setNumeric();
    }
    while (!isAlphaTo646ToAlphaLatch(this.current.getPosition())) {
      return new BlockParsedResult(false);
    }
    if (this.current.getPosition() + 5 < this.information.getSize()) {
      this.current.incrementPosition(5);
    }
    for (;;)
    {
      this.current.setAlpha();
      break;
      this.current.setPosition(this.information.getSize());
    }
  }
  
  private BlockParsedResult parseNumericBlock()
    throws FormatException
  {
    while (isStillNumeric(this.current.getPosition()))
    {
      Object localObject = decodeNumeric(this.current.getPosition());
      this.current.setPosition(((DecodedNumeric)localObject).getNewPosition());
      if (((DecodedNumeric)localObject).isFirstDigitFNC1())
      {
        if (((DecodedNumeric)localObject).isSecondDigitFNC1()) {}
        for (localObject = new DecodedInformation(this.current.getPosition(), this.buffer.toString());; localObject = new DecodedInformation(this.current.getPosition(), this.buffer.toString(), ((DecodedNumeric)localObject).getSecondDigit())) {
          return new BlockParsedResult((DecodedInformation)localObject, true);
        }
      }
      this.buffer.append(((DecodedNumeric)localObject).getFirstDigit());
      if (((DecodedNumeric)localObject).isSecondDigitFNC1()) {
        return new BlockParsedResult(new DecodedInformation(this.current.getPosition(), this.buffer.toString()), true);
      }
      this.buffer.append(((DecodedNumeric)localObject).getSecondDigit());
    }
    if (isNumericToAlphaNumericLatch(this.current.getPosition()))
    {
      this.current.setAlpha();
      this.current.incrementPosition(4);
    }
    return new BlockParsedResult(false);
  }
  
  String decodeAllCodes(StringBuilder paramStringBuilder, int paramInt)
    throws NotFoundException, FormatException
  {
    String str = null;
    for (;;)
    {
      DecodedInformation localDecodedInformation = decodeGeneralPurposeField(paramInt, str);
      str = FieldParser.parseFieldsInGeneralPurpose(localDecodedInformation.getNewString());
      if (str != null) {
        paramStringBuilder.append(str);
      }
      if (localDecodedInformation.isRemaining()) {}
      for (str = String.valueOf(localDecodedInformation.getRemainingValue()); paramInt == localDecodedInformation.getNewPosition(); str = null) {
        return paramStringBuilder.toString();
      }
      paramInt = localDecodedInformation.getNewPosition();
    }
  }
  
  DecodedInformation decodeGeneralPurposeField(int paramInt, String paramString)
    throws FormatException
  {
    this.buffer.setLength(0);
    if (paramString != null) {
      this.buffer.append(paramString);
    }
    this.current.setPosition(paramInt);
    paramString = parseBlocks();
    if ((paramString != null) && (paramString.isRemaining())) {
      return new DecodedInformation(this.current.getPosition(), this.buffer.toString(), paramString.getRemainingValue());
    }
    return new DecodedInformation(this.current.getPosition(), this.buffer.toString());
  }
  
  int extractNumericValueFromBitArray(int paramInt1, int paramInt2)
  {
    return extractNumericValueFromBitArray(this.information, paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\rss\expanded\decoders\GeneralAppIdDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */