package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.HashMap;
import java.util.Map;

public final class ExpandedProductResultParser
  extends ResultParser
{
  private static String findAIvalue(int paramInt, String paramString)
  {
    if (paramString.charAt(paramInt) != '(') {
      return null;
    }
    paramString = paramString.substring(paramInt + 1);
    StringBuilder localStringBuilder = new StringBuilder();
    paramInt = 0;
    for (;;)
    {
      if (paramInt >= paramString.length()) {
        break label84;
      }
      char c = paramString.charAt(paramInt);
      if (c == ')') {
        return localStringBuilder.toString();
      }
      if ((c < '0') || (c > '9')) {
        break;
      }
      localStringBuilder.append(c);
      paramInt += 1;
    }
    label84:
    return localStringBuilder.toString();
  }
  
  private static String findValue(int paramInt, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramString = paramString.substring(paramInt);
    paramInt = 0;
    if (paramInt < paramString.length())
    {
      char c = paramString.charAt(paramInt);
      if (c == '(')
      {
        if (findAIvalue(paramInt, paramString) == null) {
          localStringBuilder.append('(');
        }
      }
      else {
        for (;;)
        {
          paramInt += 1;
          break;
          localStringBuilder.append(c);
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  public ExpandedProductParsedResult parse(Result paramResult)
  {
    if (paramResult.getBarcodeFormat() != BarcodeFormat.RSS_EXPANDED) {
      return null;
    }
    String str5 = getMassagedText(paramResult);
    Result localResult8 = null;
    Result localResult7 = null;
    Result localResult6 = null;
    Result localResult5 = null;
    Result localResult4 = null;
    Result localResult3 = null;
    Result localResult2 = null;
    Result localResult1 = null;
    String str2 = null;
    String str3 = null;
    Object localObject = null;
    String str1 = null;
    String str4 = null;
    HashMap localHashMap = new HashMap();
    int i = 0;
    label68:
    while (i < str5.length())
    {
      String str6 = findAIvalue(i, str5);
      if (str6 == null) {
        return null;
      }
      i += str6.length() + 2;
      paramResult = findValue(i, str5);
      int j = i + paramResult.length();
      i = -1;
      switch (str6.hashCode())
      {
      }
      for (;;)
      {
        switch (i)
        {
        default: 
          localHashMap.put(str6, paramResult);
          i = j;
          break label68;
          if (str6.equals("00"))
          {
            i = 0;
            continue;
            if (str6.equals("01"))
            {
              i = 1;
              continue;
              if (str6.equals("10"))
              {
                i = 2;
                continue;
                if (str6.equals("11"))
                {
                  i = 3;
                  continue;
                  if (str6.equals("13"))
                  {
                    i = 4;
                    continue;
                    if (str6.equals("15"))
                    {
                      i = 5;
                      continue;
                      if (str6.equals("17"))
                      {
                        i = 6;
                        continue;
                        if (str6.equals("3100"))
                        {
                          i = 7;
                          continue;
                          if (str6.equals("3101"))
                          {
                            i = 8;
                            continue;
                            if (str6.equals("3102"))
                            {
                              i = 9;
                              continue;
                              if (str6.equals("3103"))
                              {
                                i = 10;
                                continue;
                                if (str6.equals("3104"))
                                {
                                  i = 11;
                                  continue;
                                  if (str6.equals("3105"))
                                  {
                                    i = 12;
                                    continue;
                                    if (str6.equals("3106"))
                                    {
                                      i = 13;
                                      continue;
                                      if (str6.equals("3107"))
                                      {
                                        i = 14;
                                        continue;
                                        if (str6.equals("3108"))
                                        {
                                          i = 15;
                                          continue;
                                          if (str6.equals("3109"))
                                          {
                                            i = 16;
                                            continue;
                                            if (str6.equals("3200"))
                                            {
                                              i = 17;
                                              continue;
                                              if (str6.equals("3201"))
                                              {
                                                i = 18;
                                                continue;
                                                if (str6.equals("3202"))
                                                {
                                                  i = 19;
                                                  continue;
                                                  if (str6.equals("3203"))
                                                  {
                                                    i = 20;
                                                    continue;
                                                    if (str6.equals("3204"))
                                                    {
                                                      i = 21;
                                                      continue;
                                                      if (str6.equals("3205"))
                                                      {
                                                        i = 22;
                                                        continue;
                                                        if (str6.equals("3206"))
                                                        {
                                                          i = 23;
                                                          continue;
                                                          if (str6.equals("3207"))
                                                          {
                                                            i = 24;
                                                            continue;
                                                            if (str6.equals("3208"))
                                                            {
                                                              i = 25;
                                                              continue;
                                                              if (str6.equals("3209"))
                                                              {
                                                                i = 26;
                                                                continue;
                                                                if (str6.equals("3920"))
                                                                {
                                                                  i = 27;
                                                                  continue;
                                                                  if (str6.equals("3921"))
                                                                  {
                                                                    i = 28;
                                                                    continue;
                                                                    if (str6.equals("3922"))
                                                                    {
                                                                      i = 29;
                                                                      continue;
                                                                      if (str6.equals("3923"))
                                                                      {
                                                                        i = 30;
                                                                        continue;
                                                                        if (str6.equals("3930"))
                                                                        {
                                                                          i = 31;
                                                                          continue;
                                                                          if (str6.equals("3931"))
                                                                          {
                                                                            i = 32;
                                                                            continue;
                                                                            if (str6.equals("3932"))
                                                                            {
                                                                              i = 33;
                                                                              continue;
                                                                              if (str6.equals("3933")) {
                                                                                i = 34;
                                                                              }
                                                                            }
                                                                          }
                                                                        }
                                                                      }
                                                                    }
                                                                  }
                                                                }
                                                              }
                                                            }
                                                          }
                                                        }
                                                      }
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
          break;
        }
      }
      localResult7 = paramResult;
      i = j;
      continue;
      localResult8 = paramResult;
      i = j;
      continue;
      localResult6 = paramResult;
      i = j;
      continue;
      localResult5 = paramResult;
      i = j;
      continue;
      localResult4 = paramResult;
      i = j;
      continue;
      localResult3 = paramResult;
      i = j;
      continue;
      localResult2 = paramResult;
      i = j;
      continue;
      str2 = "KG";
      str3 = str6.substring(3);
      localResult1 = paramResult;
      i = j;
      continue;
      str2 = "LB";
      str3 = str6.substring(3);
      localResult1 = paramResult;
      i = j;
      continue;
      str1 = str6.substring(3);
      localObject = paramResult;
      i = j;
      continue;
      if (paramResult.length() < 4) {
        return null;
      }
      localObject = paramResult.substring(3);
      str4 = paramResult.substring(0, 3);
      str1 = str6.substring(3);
      i = j;
    }
    return new ExpandedProductParsedResult(str5, localResult8, localResult7, localResult6, localResult5, localResult4, localResult3, localResult2, localResult1, str2, str3, (String)localObject, str1, str4, localHashMap);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\ExpandedProductResultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */