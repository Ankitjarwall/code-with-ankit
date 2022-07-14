package com.google.zxing;

public final class NotFoundException
  extends ReaderException
{
  private static final NotFoundException INSTANCE = new NotFoundException();
  
  static
  {
    INSTANCE.setStackTrace(NO_TRACE);
  }
  
  public static NotFoundException getNotFoundInstance()
  {
    return INSTANCE;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\NotFoundException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */