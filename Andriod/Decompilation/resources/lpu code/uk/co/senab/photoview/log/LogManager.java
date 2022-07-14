package uk.co.senab.photoview.log;

public final class LogManager
{
  private static Logger logger = new LoggerDefault();
  
  public static Logger getLogger()
  {
    return logger;
  }
  
  public static void setLogger(Logger paramLogger)
  {
    logger = paramLogger;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\uk\co\senab\photoview\log\LogManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */