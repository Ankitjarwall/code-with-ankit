package com.google.android.gms.common.images;

public final class Size
{
  private final int zzrC;
  private final int zzrD;
  
  public Size(int paramInt1, int paramInt2)
  {
    this.zzrC = paramInt1;
    this.zzrD = paramInt2;
  }
  
  public static Size parseSize(String paramString)
    throws NumberFormatException
  {
    if (paramString == null) {
      throw new IllegalArgumentException("string must not be null");
    }
    int j = paramString.indexOf('*');
    int i = j;
    if (j < 0) {
      i = paramString.indexOf('x');
    }
    if (i < 0) {
      throw zzdi(paramString);
    }
    try
    {
      Size localSize = new Size(Integer.parseInt(paramString.substring(0, i)), Integer.parseInt(paramString.substring(i + 1)));
      return localSize;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw zzdi(paramString);
    }
  }
  
  private static NumberFormatException zzdi(String paramString)
  {
    throw new NumberFormatException(String.valueOf(paramString).length() + 16 + "Invalid Size: \"" + paramString + "\"");
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == null) {}
    do
    {
      return false;
      if (this == paramObject) {
        return true;
      }
    } while (!(paramObject instanceof Size));
    paramObject = (Size)paramObject;
    if ((this.zzrC == ((Size)paramObject).zzrC) && (this.zzrD == ((Size)paramObject).zzrD)) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  public int getHeight()
  {
    return this.zzrD;
  }
  
  public int getWidth()
  {
    return this.zzrC;
  }
  
  public int hashCode()
  {
    return this.zzrD ^ (this.zzrC << 16 | this.zzrC >>> 16);
  }
  
  public String toString()
  {
    int i = this.zzrC;
    int j = this.zzrD;
    return 23 + i + "x" + j;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\images\Size.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */