package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.List;

public final class VEventResultParser
  extends ResultParser
{
  private static String matchSingleVCardPrefixedField(CharSequence paramCharSequence, String paramString, boolean paramBoolean)
  {
    paramCharSequence = VCardResultParser.matchSingleVCardPrefixedField(paramCharSequence, paramString, paramBoolean, false);
    if ((paramCharSequence == null) || (paramCharSequence.isEmpty())) {
      return null;
    }
    return (String)paramCharSequence.get(0);
  }
  
  private static String[] matchVCardPrefixedField(CharSequence paramCharSequence, String paramString, boolean paramBoolean)
  {
    List localList = VCardResultParser.matchVCardPrefixedField(paramCharSequence, paramString, paramBoolean, false);
    if ((localList == null) || (localList.isEmpty()))
    {
      paramCharSequence = null;
      return paramCharSequence;
    }
    int j = localList.size();
    paramString = new String[j];
    int i = 0;
    for (;;)
    {
      paramCharSequence = paramString;
      if (i >= j) {
        break;
      }
      paramString[i] = ((String)((List)localList.get(i)).get(0));
      i += 1;
    }
  }
  
  private static String stripMailto(String paramString)
  {
    String str = paramString;
    if (paramString != null) {
      if (!paramString.startsWith("mailto:"))
      {
        str = paramString;
        if (!paramString.startsWith("MAILTO:")) {}
      }
      else
      {
        str = paramString.substring(7);
      }
    }
    return str;
  }
  
  public CalendarParsedResult parse(Result paramResult)
  {
    String str7 = getMassagedText(paramResult);
    if (str7.indexOf("BEGIN:VEVENT") < 0) {
      return null;
    }
    paramResult = matchSingleVCardPrefixedField("SUMMARY", str7, true);
    String str1 = matchSingleVCardPrefixedField("DTSTART", str7, true);
    if (str1 == null) {
      return null;
    }
    String str2 = matchSingleVCardPrefixedField("DTEND", str7, true);
    String str3 = matchSingleVCardPrefixedField("DURATION", str7, true);
    String str4 = matchSingleVCardPrefixedField("LOCATION", str7, true);
    String str5 = stripMailto(matchSingleVCardPrefixedField("ORGANIZER", str7, true));
    String[] arrayOfString = matchVCardPrefixedField("ATTENDEE", str7, true);
    int i;
    if (arrayOfString != null)
    {
      i = 0;
      while (i < arrayOfString.length)
      {
        arrayOfString[i] = stripMailto(arrayOfString[i]);
        i += 1;
      }
    }
    String str6 = matchSingleVCardPrefixedField("DESCRIPTION", str7, true);
    str7 = matchSingleVCardPrefixedField("GEO", str7, true);
    double d1;
    double d2;
    if (str7 == null)
    {
      d1 = NaN.0D;
      d2 = NaN.0D;
    }
    for (;;)
    {
      try
      {
        paramResult = new CalendarParsedResult(paramResult, str1, str2, str3, str4, str5, arrayOfString, str6, d1, d2);
        return paramResult;
      }
      catch (IllegalArgumentException paramResult)
      {
        return null;
      }
      i = str7.indexOf(';');
      if (i < 0) {
        return null;
      }
      try
      {
        d1 = Double.parseDouble(str7.substring(0, i));
        d2 = Double.parseDouble(str7.substring(i + 1));
      }
      catch (NumberFormatException paramResult) {}
    }
    return null;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\VEventResultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */