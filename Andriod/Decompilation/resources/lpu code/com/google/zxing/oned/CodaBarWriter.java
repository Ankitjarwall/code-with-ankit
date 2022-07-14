package com.google.zxing.oned;

public final class CodaBarWriter
  extends OneDimensionalCodeWriter
{
  private static final char[] ALT_START_END_CHARS = { 84, 78, 42, 69 };
  private static final char[] CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED = { 47, 58, 43, 46 };
  private static final char DEFAULT_GUARD = START_END_CHARS[0];
  private static final char[] START_END_CHARS = { 65, 66, 67, 68 };
  
  public boolean[] encode(String paramString)
  {
    String str;
    int i;
    if (paramString.length() < 2)
    {
      str = DEFAULT_GUARD + paramString + DEFAULT_GUARD;
      i = 20;
      j = 1;
      label43:
      if (j >= str.length() - 1) {
        break label379;
      }
      if ((!Character.isDigit(str.charAt(j))) && (str.charAt(j) != '-') && (str.charAt(j) != '$')) {
        break label315;
      }
      i += 9;
    }
    boolean bool1;
    for (;;)
    {
      j += 1;
      break label43;
      char c1 = Character.toUpperCase(paramString.charAt(0));
      char c2 = Character.toUpperCase(paramString.charAt(paramString.length() - 1));
      bool1 = CodaBarReader.arrayContains(START_END_CHARS, c1);
      boolean bool2 = CodaBarReader.arrayContains(START_END_CHARS, c2);
      boolean bool3 = CodaBarReader.arrayContains(ALT_START_END_CHARS, c1);
      boolean bool4 = CodaBarReader.arrayContains(ALT_START_END_CHARS, c2);
      if (bool1)
      {
        str = paramString;
        if (bool2) {
          break;
        }
        throw new IllegalArgumentException("Invalid start/end guards: " + paramString);
      }
      if (bool3)
      {
        str = paramString;
        if (bool4) {
          break;
        }
        throw new IllegalArgumentException("Invalid start/end guards: " + paramString);
      }
      if ((bool2) || (bool4)) {
        throw new IllegalArgumentException("Invalid start/end guards: " + paramString);
      }
      str = DEFAULT_GUARD + paramString + DEFAULT_GUARD;
      break;
      label315:
      if (!CodaBarReader.arrayContains(CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED, str.charAt(j))) {
        break label341;
      }
      i += 10;
    }
    label341:
    throw new IllegalArgumentException("Cannot encode : '" + str.charAt(j) + '\'');
    label379:
    paramString = new boolean[i + (str.length() - 1)];
    int j = 0;
    int k = 0;
    while (k < str.length())
    {
      int m = Character.toUpperCase(str.charAt(k));
      if (k != 0)
      {
        i = m;
        if (k != str.length() - 1) {
          break label488;
        }
      }
      label488:
      int n;
      switch (m)
      {
      default: 
        i = m;
        int i1 = 0;
        n = 0;
        label494:
        m = i1;
        if (n < CodaBarReader.ALPHABET.length)
        {
          if (i == CodaBarReader.ALPHABET[n]) {
            m = CodaBarReader.CHARACTER_ENCODINGS[n];
          }
        }
        else
        {
          bool1 = true;
          i = 0;
          n = 0;
        }
        break;
      }
      for (;;)
      {
        label535:
        if (n >= 7) {
          break label645;
        }
        paramString[j] = bool1;
        j += 1;
        if (((m >> 6 - n & 0x1) == 0) || (i == 1))
        {
          if (!bool1) {}
          for (bool1 = true;; bool1 = false)
          {
            n += 1;
            i = 0;
            break label535;
            i = 65;
            break;
            i = 66;
            break;
            i = 67;
            break;
            i = 68;
            break;
            n += 1;
            break label494;
          }
        }
        i += 1;
      }
      label645:
      i = j;
      if (k < str.length() - 1)
      {
        paramString[j] = 0;
        i = j + 1;
      }
      k += 1;
      j = i;
    }
    return paramString;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\CodaBarWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */