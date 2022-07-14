package com.keyes.youtube;

public class Format
{
  protected int mId;
  
  public Format(int paramInt)
  {
    this.mId = paramInt;
  }
  
  public Format(String paramString)
  {
    this.mId = Integer.parseInt(paramString.split("/")[0]);
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Format)) {}
    while (((Format)paramObject).mId != this.mId) {
      return false;
    }
    return true;
  }
  
  public int getId()
  {
    return this.mId;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\keyes\youtube\Format.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */