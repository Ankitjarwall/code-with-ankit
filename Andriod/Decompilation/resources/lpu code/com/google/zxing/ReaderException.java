package com.google.zxing;

public abstract class ReaderException
  extends Exception
{
  protected static final StackTraceElement[] NO_TRACE;
  protected static final boolean isStackTrace;
  
  static
  {
    if (System.getProperty("surefire.test.class.path") != null) {}
    for (boolean bool = true;; bool = false)
    {
      isStackTrace = bool;
      NO_TRACE = new StackTraceElement[0];
      return;
    }
  }
  
  ReaderException() {}
  
  ReaderException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
  
  public final Throwable fillInStackTrace()
  {
    return null;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\ReaderException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */