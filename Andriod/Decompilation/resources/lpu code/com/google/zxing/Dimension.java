package com.google.zxing;

public final class Dimension
{
  private final int height;
  private final int width;
  
  public Dimension(int paramInt1, int paramInt2)
  {
    if ((paramInt1 < 0) || (paramInt2 < 0)) {
      throw new IllegalArgumentException();
    }
    this.width = paramInt1;
    this.height = paramInt2;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof Dimension))
    {
      paramObject = (Dimension)paramObject;
      bool1 = bool2;
      if (this.width == ((Dimension)paramObject).width)
      {
        bool1 = bool2;
        if (this.height == ((Dimension)paramObject).height) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public int getHeight()
  {
    return this.height;
  }
  
  public int getWidth()
  {
    return this.width;
  }
  
  public int hashCode()
  {
    return this.width * 32713 + this.height;
  }
  
  public String toString()
  {
    return this.width + "x" + this.height;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\Dimension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */