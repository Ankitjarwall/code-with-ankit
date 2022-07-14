package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class URIResultParser
  extends ResultParser
{
  private static final Pattern URL_WITHOUT_PROTOCOL_PATTERN = Pattern.compile("([a-zA-Z0-9\\-]+\\.){1,6}[a-zA-Z]{2,}(:\\d{1,5})?(/|\\?|$)");
  private static final Pattern URL_WITH_PROTOCOL_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9+-.]+:");
  
  static boolean isBasicallyValidURI(String paramString)
  {
    boolean bool2 = true;
    boolean bool1;
    if (paramString.contains(" ")) {
      bool1 = false;
    }
    do
    {
      Matcher localMatcher;
      do
      {
        return bool1;
        localMatcher = URL_WITH_PROTOCOL_PATTERN.matcher(paramString);
        if (!localMatcher.find()) {
          break;
        }
        bool1 = bool2;
      } while (localMatcher.start() == 0);
      paramString = URL_WITHOUT_PROTOCOL_PATTERN.matcher(paramString);
      if (!paramString.find()) {
        break;
      }
      bool1 = bool2;
    } while (paramString.start() == 0);
    return false;
  }
  
  public URIParsedResult parse(Result paramResult)
  {
    paramResult = getMassagedText(paramResult);
    if ((paramResult.startsWith("URL:")) || (paramResult.startsWith("URI:"))) {
      return new URIParsedResult(paramResult.substring(4).trim(), null);
    }
    paramResult = paramResult.trim();
    if (isBasicallyValidURI(paramResult)) {
      return new URIParsedResult(paramResult, null);
    }
    return null;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\URIResultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */