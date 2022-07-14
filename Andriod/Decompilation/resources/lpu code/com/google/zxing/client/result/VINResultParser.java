package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VINResultParser
  extends ResultParser
{
  private static final Pattern AZ09 = Pattern.compile("[A-Z0-9]{17}");
  private static final Pattern IOQ = Pattern.compile("[IOQ]");
  
  private static char checkChar(int paramInt)
  {
    if (paramInt < 10) {
      return (char)(paramInt + 48);
    }
    if (paramInt == 10) {
      return 'X';
    }
    throw new IllegalArgumentException();
  }
  
  private static boolean checkChecksum(CharSequence paramCharSequence)
  {
    int j = 0;
    int i = 0;
    while (i < paramCharSequence.length())
    {
      j += vinPositionWeight(i + 1) * vinCharValue(paramCharSequence.charAt(i));
      i += 1;
    }
    return paramCharSequence.charAt(8) == checkChar(j % 11);
  }
  
  private static String countryCode(CharSequence paramCharSequence)
  {
    int i = paramCharSequence.charAt(0);
    int j = paramCharSequence.charAt(1);
    switch (i)
    {
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      return null;
                      return "US";
                      return "CA";
                    } while ((j < 65) || (j > 87));
                    return "MX";
                  } while (((j < 65) || (j > 69)) && ((j < 51) || (j > 57)));
                  return "BR";
                } while ((j < 65) || (j > 84));
                return "JP";
              } while ((j < 76) || (j > 82));
              return "KO";
              return "CN";
            } while ((j < 65) || (j > 69));
            return "IN";
            if ((j >= 65) && (j <= 77)) {
              return "UK";
            }
          } while ((j < 78) || (j > 84));
          return "DE";
          if ((j >= 70) && (j <= 82)) {
            return "FR";
          }
        } while ((j < 83) || (j > 87));
        return "ES";
        return "DE";
      } while ((j != 48) && ((j < 51) || (j > 57)));
      return "RU";
    } while ((j < 65) || (j > 82));
    return "IT";
  }
  
  private static int modelYear(char paramChar)
  {
    if ((paramChar >= 'E') && (paramChar <= 'H')) {
      return paramChar - 'E' + 1984;
    }
    if ((paramChar >= 'J') && (paramChar <= 'N')) {
      return paramChar - 'J' + 1988;
    }
    if (paramChar == 'P') {
      return 1993;
    }
    if ((paramChar >= 'R') && (paramChar <= 'T')) {
      return paramChar - 'R' + 1994;
    }
    if ((paramChar >= 'V') && (paramChar <= 'Y')) {
      return paramChar - 'V' + 1997;
    }
    if ((paramChar >= '1') && (paramChar <= '9')) {
      return paramChar - '1' + 2001;
    }
    if ((paramChar >= 'A') && (paramChar <= 'D')) {
      return paramChar - 'A' + 2010;
    }
    throw new IllegalArgumentException();
  }
  
  private static int vinCharValue(char paramChar)
  {
    if ((paramChar >= 'A') && (paramChar <= 'I')) {
      return paramChar - 'A' + 1;
    }
    if ((paramChar >= 'J') && (paramChar <= 'R')) {
      return paramChar - 'J' + 1;
    }
    if ((paramChar >= 'S') && (paramChar <= 'Z')) {
      return paramChar - 'S' + 2;
    }
    if ((paramChar >= '0') && (paramChar <= '9')) {
      return paramChar - '0';
    }
    throw new IllegalArgumentException();
  }
  
  private static int vinPositionWeight(int paramInt)
  {
    int i = 10;
    if ((paramInt >= 1) && (paramInt <= 7)) {
      i = 9 - paramInt;
    }
    while (paramInt == 8) {
      return i;
    }
    if (paramInt == 9) {
      return 0;
    }
    if ((paramInt >= 10) && (paramInt <= 17)) {
      return 19 - paramInt;
    }
    throw new IllegalArgumentException();
  }
  
  public VINParsedResult parse(Result paramResult)
  {
    if (paramResult.getBarcodeFormat() != BarcodeFormat.CODE_39) {
      return null;
    }
    paramResult = paramResult.getText();
    paramResult = IOQ.matcher(paramResult).replaceAll("").trim();
    if (!AZ09.matcher(paramResult).matches()) {
      return null;
    }
    try
    {
      if (!checkChecksum(paramResult)) {
        return null;
      }
      String str = paramResult.substring(0, 3);
      paramResult = new VINParsedResult(paramResult, str, paramResult.substring(3, 9), paramResult.substring(9, 17), countryCode(str), paramResult.substring(3, 8), modelYear(paramResult.charAt(9)), paramResult.charAt(10), paramResult.substring(11));
      return paramResult;
    }
    catch (IllegalArgumentException paramResult) {}
    return null;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\VINResultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */