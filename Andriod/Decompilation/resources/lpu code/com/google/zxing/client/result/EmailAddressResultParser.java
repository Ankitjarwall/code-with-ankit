package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Map;
import java.util.regex.Pattern;

public final class EmailAddressResultParser
  extends ResultParser
{
  private static final Pattern COMMA = Pattern.compile(",");
  
  public EmailAddressParsedResult parse(Result paramResult)
  {
    Object localObject2 = getMassagedText(paramResult);
    if ((((String)localObject2).startsWith("mailto:")) || (((String)localObject2).startsWith("MAILTO:")))
    {
      Object localObject1 = ((String)localObject2).substring(7);
      int i = ((String)localObject1).indexOf('?');
      paramResult = (Result)localObject1;
      if (i >= 0) {
        paramResult = ((String)localObject1).substring(0, i);
      }
      try
      {
        localObject1 = urlDecode(paramResult);
        paramResult = null;
        if (!((String)localObject1).isEmpty()) {
          paramResult = COMMA.split((CharSequence)localObject1);
        }
        Map localMap = parseNameValuePairs((String)localObject2);
        localObject2 = null;
        Object localObject5 = null;
        Object localObject3 = null;
        Object localObject4 = null;
        String str1 = null;
        String str2 = null;
        localObject1 = paramResult;
        if (localMap != null)
        {
          localObject1 = paramResult;
          if (paramResult == null)
          {
            localObject2 = (String)localMap.get("to");
            localObject1 = paramResult;
            if (localObject2 != null) {
              localObject1 = COMMA.split((CharSequence)localObject2);
            }
          }
          localObject2 = (String)localMap.get("cc");
          paramResult = (Result)localObject5;
          if (localObject2 != null) {
            paramResult = COMMA.split((CharSequence)localObject2);
          }
          localObject3 = (String)localMap.get("bcc");
          localObject2 = localObject4;
          if (localObject3 != null) {
            localObject2 = COMMA.split((CharSequence)localObject3);
          }
          str1 = (String)localMap.get("subject");
          str2 = (String)localMap.get("body");
          localObject3 = localObject2;
          localObject2 = paramResult;
        }
        return new EmailAddressParsedResult((String[])localObject1, (String[])localObject2, (String[])localObject3, str1, str2);
      }
      catch (IllegalArgumentException paramResult)
      {
        return null;
      }
    }
    if (!EmailDoCoMoResultParser.isBasicallyValidEmailAddress((String)localObject2)) {
      return null;
    }
    return new EmailAddressParsedResult((String)localObject2);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\EmailAddressResultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */