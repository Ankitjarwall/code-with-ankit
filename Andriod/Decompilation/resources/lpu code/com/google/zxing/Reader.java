package com.google.zxing;

import java.util.Map;

public abstract interface Reader
{
  public abstract Result decode(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException, ChecksumException, FormatException;
  
  public abstract Result decode(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException, ChecksumException, FormatException;
  
  public abstract void reset();
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\Reader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */