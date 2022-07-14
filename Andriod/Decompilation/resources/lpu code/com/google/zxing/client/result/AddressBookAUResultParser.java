package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.List;

public final class AddressBookAUResultParser
  extends ResultParser
{
  private static String[] matchMultipleValuePrefix(String paramString1, int paramInt, String paramString2, boolean paramBoolean)
  {
    Object localObject1 = null;
    int i = 1;
    for (;;)
    {
      String str;
      if (i <= paramInt)
      {
        str = matchSinglePrefixedField(paramString1 + i + ':', paramString2, '\r', paramBoolean);
        if (str != null) {}
      }
      else
      {
        if (localObject1 != null) {
          break;
        }
        return null;
      }
      Object localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = new ArrayList(paramInt);
      }
      ((List)localObject2).add(str);
      i += 1;
      localObject1 = localObject2;
    }
    return (String[])((List)localObject1).toArray(new String[((List)localObject1).size()]);
  }
  
  public AddressBookParsedResult parse(Result paramResult)
  {
    paramResult = getMassagedText(paramResult);
    if ((!paramResult.contains("MEMORY")) || (!paramResult.contains("\r\n"))) {
      return null;
    }
    String str1 = matchSinglePrefixedField("NAME1:", paramResult, '\r', true);
    String str2 = matchSinglePrefixedField("NAME2:", paramResult, '\r', true);
    String[] arrayOfString1 = matchMultipleValuePrefix("TEL", 3, paramResult, true);
    String[] arrayOfString2 = matchMultipleValuePrefix("MAIL", 3, paramResult, true);
    String str3 = matchSinglePrefixedField("MEMORY:", paramResult, '\r', false);
    String str4 = matchSinglePrefixedField("ADD:", paramResult, '\r', true);
    if (str4 == null) {
      paramResult = null;
    }
    for (;;)
    {
      return new AddressBookParsedResult(maybeWrap(str1), null, str2, arrayOfString1, null, arrayOfString2, null, null, str3, paramResult, null, null, null, null, null, null);
      paramResult = new String[1];
      paramResult[0] = str4;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\AddressBookAUResultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */