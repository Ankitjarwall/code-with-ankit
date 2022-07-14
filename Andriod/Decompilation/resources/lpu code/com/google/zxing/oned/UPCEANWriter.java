package com.google.zxing.oned;

public abstract class UPCEANWriter
  extends OneDimensionalCodeWriter
{
  public int getDefaultMargin()
  {
    return UPCEANReader.START_END_PATTERN.length;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\UPCEANWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */