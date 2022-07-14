package com.google.zxing.datamatrix.encoder;

final class ASCIIEncoder
  implements Encoder
{
  private static char encodeASCIIDigits(char paramChar1, char paramChar2)
  {
    if ((HighLevelEncoder.isDigit(paramChar1)) && (HighLevelEncoder.isDigit(paramChar2))) {
      return (char)((paramChar1 - '0') * 10 + (paramChar2 - '0') + 130);
    }
    throw new IllegalArgumentException("not digits: " + paramChar1 + paramChar2);
  }
  
  public void encode(EncoderContext paramEncoderContext)
  {
    if (HighLevelEncoder.determineConsecutiveDigitCount(paramEncoderContext.getMessage(), paramEncoderContext.pos) >= 2)
    {
      paramEncoderContext.writeCodeword(encodeASCIIDigits(paramEncoderContext.getMessage().charAt(paramEncoderContext.pos), paramEncoderContext.getMessage().charAt(paramEncoderContext.pos + 1)));
      paramEncoderContext.pos += 2;
      return;
    }
    char c = paramEncoderContext.getCurrentChar();
    int i = HighLevelEncoder.lookAheadTest(paramEncoderContext.getMessage(), paramEncoderContext.pos, getEncodingMode());
    if (i != getEncodingMode())
    {
      switch (i)
      {
      default: 
        throw new IllegalStateException("Illegal mode: " + i);
      case 5: 
        paramEncoderContext.writeCodeword('ç');
        paramEncoderContext.signalEncoderChange(5);
        return;
      case 1: 
        paramEncoderContext.writeCodeword('æ');
        paramEncoderContext.signalEncoderChange(1);
        return;
      case 3: 
        paramEncoderContext.writeCodeword('î');
        paramEncoderContext.signalEncoderChange(3);
        return;
      case 2: 
        paramEncoderContext.writeCodeword('ï');
        paramEncoderContext.signalEncoderChange(2);
        return;
      }
      paramEncoderContext.writeCodeword('ð');
      paramEncoderContext.signalEncoderChange(4);
      return;
    }
    if (HighLevelEncoder.isExtendedASCII(c))
    {
      paramEncoderContext.writeCodeword('ë');
      paramEncoderContext.writeCodeword((char)(c - '' + 1));
      paramEncoderContext.pos += 1;
      return;
    }
    paramEncoderContext.writeCodeword((char)(c + '\001'));
    paramEncoderContext.pos += 1;
  }
  
  public int getEncodingMode()
  {
    return 0;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\datamatrix\encoder\ASCIIEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */