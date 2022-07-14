package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EmailDoCoMoResultParser
  extends AbstractDoCoMoResultParser
{
  private static final Pattern ATEXT_ALPHANUMERIC = Pattern.compile("[a-zA-Z0-9@.!#$%&'*+\\-/=?^_`{|}~]+");
  
  static boolean isBasicallyValidEmailAddress(String paramString)
  {
    return (paramString != null) && (ATEXT_ALPHANUMERIC.matcher(paramString).matches()) && (paramString.indexOf('@') >= 0);
  }
  
  public EmailAddressParsedResult parse(Result paramResult)
  {
    paramResult = getMassagedText(paramResult);
    if (!paramResult.startsWith("MATMSG:")) {}
    String[] arrayOfString;
    do
    {
      return null;
      arrayOfString = matchDoCoMoPrefixedField("TO:", paramResult, true);
    } while (arrayOfString == null);
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label58;
      }
      if (!isBasicallyValidEmailAddress(arrayOfString[i])) {
        break;
      }
      i += 1;
    }
    label58:
    return new EmailAddressParsedResult(arrayOfString, null, null, matchSingleDoCoMoPrefixedField("SUB:", paramResult, false), matchSingleDoCoMoPrefixedField("BODY:", paramResult, false));
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\EmailDoCoMoResultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */