package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class SMSMMSResultParser
  extends ResultParser
{
  private static void addNumberVia(Collection<String> paramCollection1, Collection<String> paramCollection2, String paramString)
  {
    int i = paramString.indexOf(';');
    if (i < 0)
    {
      paramCollection1.add(paramString);
      paramCollection2.add(null);
      return;
    }
    paramCollection1.add(paramString.substring(0, i));
    paramCollection1 = paramString.substring(i + 1);
    if (paramCollection1.startsWith("via=")) {}
    for (paramCollection1 = paramCollection1.substring(4);; paramCollection1 = null)
    {
      paramCollection2.add(paramCollection1);
      return;
    }
  }
  
  public SMSParsedResult parse(Result paramResult)
  {
    Object localObject2 = getMassagedText(paramResult);
    if ((!((String)localObject2).startsWith("sms:")) && (!((String)localObject2).startsWith("SMS:")) && (!((String)localObject2).startsWith("mms:")) && (!((String)localObject2).startsWith("MMS:"))) {
      return null;
    }
    Map localMap = parseNameValuePairs((String)localObject2);
    String str = null;
    ArrayList localArrayList = null;
    int j = 0;
    Object localObject1 = localArrayList;
    int i = j;
    paramResult = str;
    if (localMap != null)
    {
      localObject1 = localArrayList;
      i = j;
      paramResult = str;
      if (!localMap.isEmpty())
      {
        paramResult = (String)localMap.get("subject");
        localObject1 = (String)localMap.get("body");
        i = 1;
      }
    }
    j = ((String)localObject2).indexOf('?', 4);
    if ((j < 0) || (i == 0)) {}
    for (str = ((String)localObject2).substring(4);; str = ((String)localObject2).substring(4, j))
    {
      i = -1;
      localArrayList = new ArrayList(1);
      localObject2 = new ArrayList(1);
      for (;;)
      {
        j = str.indexOf(',', i + 1);
        if (j <= i) {
          break;
        }
        addNumberVia(localArrayList, (Collection)localObject2, str.substring(i + 1, j));
        i = j;
      }
    }
    addNumberVia(localArrayList, (Collection)localObject2, str.substring(i + 1));
    return new SMSParsedResult((String[])localArrayList.toArray(new String[localArrayList.size()]), (String[])((List)localObject2).toArray(new String[((List)localObject2).size()]), paramResult, (String)localObject1);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\SMSMMSResultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */