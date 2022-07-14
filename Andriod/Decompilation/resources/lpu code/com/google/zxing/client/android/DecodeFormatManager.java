package com.google.zxing.client.android;

import android.content.Intent;
import android.net.Uri;
import com.google.zxing.BarcodeFormat;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

final class DecodeFormatManager
{
  static final Set<BarcodeFormat> AZTEC_FORMATS;
  private static final Pattern COMMA_PATTERN = Pattern.compile(",");
  static final Set<BarcodeFormat> DATA_MATRIX_FORMATS;
  private static final Map<String, Set<BarcodeFormat>> FORMATS_FOR_MODE;
  static final Set<BarcodeFormat> INDUSTRIAL_FORMATS;
  private static final Set<BarcodeFormat> ONE_D_FORMATS;
  static final Set<BarcodeFormat> PDF417_FORMATS;
  static final Set<BarcodeFormat> PRODUCT_FORMATS;
  static final Set<BarcodeFormat> QR_CODE_FORMATS = EnumSet.of(BarcodeFormat.QR_CODE);
  
  static
  {
    DATA_MATRIX_FORMATS = EnumSet.of(BarcodeFormat.DATA_MATRIX);
    AZTEC_FORMATS = EnumSet.of(BarcodeFormat.AZTEC);
    PDF417_FORMATS = EnumSet.of(BarcodeFormat.PDF_417);
    PRODUCT_FORMATS = EnumSet.of(BarcodeFormat.UPC_A, new BarcodeFormat[] { BarcodeFormat.UPC_E, BarcodeFormat.EAN_13, BarcodeFormat.EAN_8, BarcodeFormat.RSS_14, BarcodeFormat.RSS_EXPANDED });
    INDUSTRIAL_FORMATS = EnumSet.of(BarcodeFormat.CODE_39, BarcodeFormat.CODE_93, BarcodeFormat.CODE_128, BarcodeFormat.ITF, BarcodeFormat.CODABAR);
    ONE_D_FORMATS = EnumSet.copyOf(PRODUCT_FORMATS);
    ONE_D_FORMATS.addAll(INDUSTRIAL_FORMATS);
    FORMATS_FOR_MODE = new HashMap();
    FORMATS_FOR_MODE.put("ONE_D_MODE", ONE_D_FORMATS);
    FORMATS_FOR_MODE.put("PRODUCT_MODE", PRODUCT_FORMATS);
    FORMATS_FOR_MODE.put("QR_CODE_MODE", QR_CODE_FORMATS);
    FORMATS_FOR_MODE.put("DATA_MATRIX_MODE", DATA_MATRIX_FORMATS);
    FORMATS_FOR_MODE.put("AZTEC_MODE", AZTEC_FORMATS);
    FORMATS_FOR_MODE.put("PDF417_MODE", PDF417_FORMATS);
  }
  
  static Set<BarcodeFormat> parseDecodeFormats(Intent paramIntent)
  {
    List localList = null;
    String str = paramIntent.getStringExtra("SCAN_FORMATS");
    if (str != null) {
      localList = Arrays.asList(COMMA_PATTERN.split(str));
    }
    return parseDecodeFormats(localList, paramIntent.getStringExtra("SCAN_MODE"));
  }
  
  static Set<BarcodeFormat> parseDecodeFormats(Uri paramUri)
  {
    List localList2 = paramUri.getQueryParameters("SCAN_FORMATS");
    List localList1 = localList2;
    if (localList2 != null)
    {
      localList1 = localList2;
      if (localList2.size() == 1)
      {
        localList1 = localList2;
        if (localList2.get(0) != null) {
          localList1 = Arrays.asList(COMMA_PATTERN.split((CharSequence)localList2.get(0)));
        }
      }
    }
    return parseDecodeFormats(localList1, paramUri.getQueryParameter("SCAN_MODE"));
  }
  
  private static Set<BarcodeFormat> parseDecodeFormats(Iterable<String> paramIterable, String paramString)
  {
    if (paramIterable != null)
    {
      EnumSet localEnumSet = EnumSet.noneOf(BarcodeFormat.class);
      try
      {
        Iterator localIterator = paramIterable.iterator();
        for (;;)
        {
          paramIterable = localEnumSet;
          if (!localIterator.hasNext()) {
            break;
          }
          localEnumSet.add(BarcodeFormat.valueOf((String)localIterator.next()));
        }
        if (paramString == null) {
          break label70;
        }
      }
      catch (IllegalArgumentException paramIterable) {}
    }
    else
    {
      paramIterable = (Set)FORMATS_FOR_MODE.get(paramString);
    }
    return paramIterable;
    label70:
    return null;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\DecodeFormatManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */