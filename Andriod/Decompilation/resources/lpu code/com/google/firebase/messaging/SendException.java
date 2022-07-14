package com.google.firebase.messaging;

import java.util.Locale;

public final class SendException
  extends Exception
{
  public static final int ERROR_INVALID_PARAMETERS = 1;
  public static final int ERROR_SIZE = 2;
  public static final int ERROR_TOO_MANY_MESSAGES = 4;
  public static final int ERROR_TTL_EXCEEDED = 3;
  public static final int ERROR_UNKNOWN = 0;
  private final int zzPY = zzjF(paramString);
  
  SendException(String paramString)
  {
    super(paramString);
  }
  
  private int zzjF(String paramString)
  {
    if (paramString == null) {
      return 0;
    }
    paramString = paramString.toLowerCase(Locale.US);
    int i = -1;
    switch (paramString.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return 0;
      case 0: 
      case 1: 
        return 1;
        if (paramString.equals("invalid_parameters"))
        {
          i = 0;
          continue;
          if (paramString.equals("missing_to"))
          {
            i = 1;
            continue;
            if (paramString.equals("messagetoobig"))
            {
              i = 2;
              continue;
              if (paramString.equals("service_not_available"))
              {
                i = 3;
                continue;
                if (paramString.equals("toomanymessages")) {
                  i = 4;
                }
              }
            }
          }
        }
        break;
      }
    }
    return 2;
    return 3;
    return 4;
  }
  
  public int getErrorCode()
  {
    return this.zzPY;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\messaging\SendException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */