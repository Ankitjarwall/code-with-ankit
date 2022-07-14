package com.google.zxing.client.android.encode;

import android.telephony.PhoneNumberUtils;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class MECARDContactEncoder
  extends ContactEncoder
{
  private static final char TERMINATOR = ';';
  
  public String[] encode(List<String> paramList1, String paramString1, List<String> paramList2, List<String> paramList3, List<String> paramList4, List<String> paramList5, List<String> paramList6, String paramString2)
  {
    paramList4 = new StringBuilder(100);
    paramList4.append("MECARD:");
    StringBuilder localStringBuilder = new StringBuilder(100);
    MECARDFieldFormatter localMECARDFieldFormatter = new MECARDFieldFormatter(null);
    appendUpToUnique(paramList4, localStringBuilder, "N", paramList1, 1, new MECARDNameDisplayFormatter(null), localMECARDFieldFormatter, ';');
    append(paramList4, localStringBuilder, "ORG", paramString1, localMECARDFieldFormatter, ';');
    appendUpToUnique(paramList4, localStringBuilder, "ADR", paramList2, 1, null, localMECARDFieldFormatter, ';');
    appendUpToUnique(paramList4, localStringBuilder, "TEL", paramList3, Integer.MAX_VALUE, new MECARDTelDisplayFormatter(null), localMECARDFieldFormatter, ';');
    appendUpToUnique(paramList4, localStringBuilder, "EMAIL", paramList5, Integer.MAX_VALUE, null, localMECARDFieldFormatter, ';');
    appendUpToUnique(paramList4, localStringBuilder, "URL", paramList6, Integer.MAX_VALUE, null, localMECARDFieldFormatter, ';');
    append(paramList4, localStringBuilder, "NOTE", paramString2, localMECARDFieldFormatter, ';');
    paramList4.append(';');
    return new String[] { paramList4.toString(), localStringBuilder.toString() };
  }
  
  private static class MECARDFieldFormatter
    implements Formatter
  {
    private static final Pattern NEWLINE = Pattern.compile("\\n");
    private static final Pattern RESERVED_MECARD_CHARS = Pattern.compile("([\\\\:;])");
    
    public CharSequence format(CharSequence paramCharSequence, int paramInt)
    {
      return ':' + NEWLINE.matcher(RESERVED_MECARD_CHARS.matcher(paramCharSequence).replaceAll("\\\\$1")).replaceAll("");
    }
  }
  
  private static class MECARDNameDisplayFormatter
    implements Formatter
  {
    private static final Pattern COMMA = Pattern.compile(",");
    
    public CharSequence format(CharSequence paramCharSequence, int paramInt)
    {
      return COMMA.matcher(paramCharSequence).replaceAll("");
    }
  }
  
  private static class MECARDTelDisplayFormatter
    implements Formatter
  {
    private static final Pattern NOT_DIGITS = Pattern.compile("[^0-9]+");
    
    public CharSequence format(CharSequence paramCharSequence, int paramInt)
    {
      return NOT_DIGITS.matcher(PhoneNumberUtils.formatNumber(paramCharSequence.toString())).replaceAll("");
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\encode\MECARDContactEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */