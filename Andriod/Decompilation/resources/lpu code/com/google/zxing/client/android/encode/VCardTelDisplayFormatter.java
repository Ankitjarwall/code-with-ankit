package com.google.zxing.client.android.encode;

import android.telephony.PhoneNumberUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class VCardTelDisplayFormatter
  implements Formatter
{
  private final List<Map<String, Set<String>>> metadataForIndex;
  
  VCardTelDisplayFormatter()
  {
    this(null);
  }
  
  VCardTelDisplayFormatter(List<Map<String, Set<String>>> paramList)
  {
    this.metadataForIndex = paramList;
  }
  
  private static CharSequence formatMetadata(CharSequence paramCharSequence, Map<String, Set<String>> paramMap)
  {
    if ((paramMap == null) || (paramMap.isEmpty())) {
      return paramCharSequence;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Object localObject = (Set)((Map.Entry)paramMap.next()).getValue();
      if ((localObject != null) && (!((Set)localObject).isEmpty()))
      {
        localObject = ((Set)localObject).iterator();
        localStringBuilder.append((String)((Iterator)localObject).next());
        while (((Iterator)localObject).hasNext()) {
          localStringBuilder.append(',').append((String)((Iterator)localObject).next());
        }
      }
    }
    if (localStringBuilder.length() > 0) {
      localStringBuilder.append(' ');
    }
    localStringBuilder.append(paramCharSequence);
    return localStringBuilder;
  }
  
  public CharSequence format(CharSequence paramCharSequence, int paramInt)
  {
    String str = PhoneNumberUtils.formatNumber(paramCharSequence.toString());
    if ((this.metadataForIndex == null) || (this.metadataForIndex.size() <= paramInt)) {}
    for (paramCharSequence = null;; paramCharSequence = (Map)this.metadataForIndex.get(paramInt)) {
      return formatMetadata(str, paramCharSequence);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\encode\VCardTelDisplayFormatter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */