package com.google.zxing.client.android.encode;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

abstract class ContactEncoder
{
  static void append(StringBuilder paramStringBuilder1, StringBuilder paramStringBuilder2, String paramString1, String paramString2, Formatter paramFormatter, char paramChar)
  {
    paramString2 = trim(paramString2);
    if (paramString2 != null)
    {
      paramStringBuilder1.append(paramString1).append(paramFormatter.format(paramString2, 0)).append(paramChar);
      paramStringBuilder2.append(paramString2).append('\n');
    }
  }
  
  static void appendUpToUnique(StringBuilder paramStringBuilder1, StringBuilder paramStringBuilder2, String paramString, List<String> paramList, int paramInt, Formatter paramFormatter1, Formatter paramFormatter2, char paramChar)
  {
    if (paramList == null) {
      return;
    }
    int j = 0;
    HashSet localHashSet = new HashSet(2);
    int i = 0;
    label21:
    String str;
    int k;
    if (i < paramList.size())
    {
      str = trim((String)paramList.get(i));
      k = j;
      if (str != null)
      {
        k = j;
        if (!str.isEmpty())
        {
          k = j;
          if (!localHashSet.contains(str))
          {
            paramStringBuilder1.append(paramString).append(paramFormatter2.format(str, i)).append(paramChar);
            if (paramFormatter1 != null) {
              break label167;
            }
          }
        }
      }
    }
    label167:
    for (Object localObject = str;; localObject = paramFormatter1.format(str, i))
    {
      paramStringBuilder2.append((CharSequence)localObject).append('\n');
      k = j + 1;
      if (k == paramInt) {
        break;
      }
      localHashSet.add(str);
      i += 1;
      j = k;
      break label21;
      break;
    }
  }
  
  static String trim(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    String str = paramString.trim();
    paramString = str;
    if (str.isEmpty()) {
      paramString = null;
    }
    return paramString;
  }
  
  abstract String[] encode(List<String> paramList1, String paramString1, List<String> paramList2, List<String> paramList3, List<String> paramList4, List<String> paramList5, List<String> paramList6, String paramString2);
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\encode\ContactEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */