package com.google.zxing.oned.rss;

public class DataCharacter
{
  private final int checksumPortion;
  private final int value;
  
  public DataCharacter(int paramInt1, int paramInt2)
  {
    this.value = paramInt1;
    this.checksumPortion = paramInt2;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof DataCharacter)) {}
    do
    {
      return false;
      paramObject = (DataCharacter)paramObject;
    } while ((this.value != ((DataCharacter)paramObject).value) || (this.checksumPortion != ((DataCharacter)paramObject).checksumPortion));
    return true;
  }
  
  public final int getChecksumPortion()
  {
    return this.checksumPortion;
  }
  
  public final int getValue()
  {
    return this.value;
  }
  
  public final int hashCode()
  {
    return this.value ^ this.checksumPortion;
  }
  
  public final String toString()
  {
    return this.value + "(" + this.checksumPortion + ')';
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\rss\DataCharacter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */