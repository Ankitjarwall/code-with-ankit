package com.google.zxing.datamatrix.encoder;

final class Base256Encoder
  implements Encoder
{
  private static char randomize255State(char paramChar, int paramInt)
  {
    paramChar += paramInt * 149 % 255 + 1;
    if (paramChar <= 'ÿ') {
      return (char)paramChar;
    }
    return (char)(paramChar - 'Ā');
  }
  
  public void encode(EncoderContext paramEncoderContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('\000');
    while (paramEncoderContext.hasMoreCharacters())
    {
      localStringBuilder.append(paramEncoderContext.getCurrentChar());
      paramEncoderContext.pos += 1;
      i = HighLevelEncoder.lookAheadTest(paramEncoderContext.getMessage(), paramEncoderContext.pos, getEncodingMode());
      if (i != getEncodingMode()) {
        paramEncoderContext.signalEncoderChange(i);
      }
    }
    int j = localStringBuilder.length() - 1;
    int i = paramEncoderContext.getCodewordCount() + j + 1;
    paramEncoderContext.updateSymbolInfo(i);
    if (paramEncoderContext.getSymbolInfo().getDataCapacity() - i > 0)
    {
      i = 1;
      if ((paramEncoderContext.hasMoreCharacters()) || (i != 0))
      {
        if (j > 249) {
          break label178;
        }
        localStringBuilder.setCharAt(0, (char)j);
      }
    }
    for (;;)
    {
      i = 0;
      j = localStringBuilder.length();
      while (i < j)
      {
        paramEncoderContext.writeCodeword(randomize255State(localStringBuilder.charAt(i), paramEncoderContext.getCodewordCount() + 1));
        i += 1;
      }
      i = 0;
      break;
      label178:
      if (j > 1555) {
        break label217;
      }
      localStringBuilder.setCharAt(0, (char)(j / 250 + 249));
      localStringBuilder.insert(1, (char)(j % 250));
    }
    label217:
    throw new IllegalStateException("Message length not in valid ranges: " + j);
  }
  
  public int getEncodingMode()
  {
    return 5;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\datamatrix\encoder\Base256Encoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */