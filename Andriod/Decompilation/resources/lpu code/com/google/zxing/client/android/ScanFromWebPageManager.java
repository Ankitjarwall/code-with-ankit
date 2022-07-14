package com.google.zxing.client.android;

import android.net.Uri;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.client.android.result.ResultHandler;
import com.google.zxing.client.result.ParsedResultType;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

final class ScanFromWebPageManager
{
  private static final CharSequence CODE_PLACEHOLDER = "{CODE}";
  private static final CharSequence FORMAT_PLACEHOLDER = "{FORMAT}";
  private static final CharSequence META_PLACEHOLDER;
  private static final CharSequence RAW_CODE_PLACEHOLDER = "{RAWCODE}";
  private static final String RAW_PARAM = "raw";
  private static final String RETURN_URL_PARAM = "ret";
  private static final CharSequence TYPE_PLACEHOLDER = "{TYPE}";
  private final boolean returnRaw;
  private final String returnUrlTemplate;
  
  static
  {
    META_PLACEHOLDER = "{META}";
  }
  
  ScanFromWebPageManager(Uri paramUri)
  {
    this.returnUrlTemplate = paramUri.getQueryParameter("ret");
    if (paramUri.getQueryParameter("raw") != null) {}
    for (boolean bool = true;; bool = false)
    {
      this.returnRaw = bool;
      return;
    }
  }
  
  private static String replace(CharSequence paramCharSequence1, CharSequence paramCharSequence2, String paramString)
  {
    if (paramCharSequence2 == null) {
      paramCharSequence2 = "";
    }
    for (;;)
    {
      try
      {
        String str = URLEncoder.encode(paramCharSequence2.toString(), "UTF-8");
        paramCharSequence2 = str;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        continue;
      }
      return paramString.replace(paramCharSequence1, paramCharSequence2);
    }
  }
  
  String buildReplyURL(Result paramResult, ResultHandler paramResultHandler)
  {
    String str = this.returnUrlTemplate;
    CharSequence localCharSequence = CODE_PLACEHOLDER;
    if (this.returnRaw) {}
    for (Object localObject = paramResult.getText();; localObject = paramResultHandler.getDisplayContents())
    {
      localObject = replace(localCharSequence, (CharSequence)localObject, str);
      localObject = replace(RAW_CODE_PLACEHOLDER, paramResult.getText(), (String)localObject);
      localObject = replace(FORMAT_PLACEHOLDER, paramResult.getBarcodeFormat().toString(), (String)localObject);
      paramResultHandler = replace(TYPE_PLACEHOLDER, paramResultHandler.getType().toString(), (String)localObject);
      return replace(META_PLACEHOLDER, String.valueOf(paramResult.getResultMetadata()), paramResultHandler);
    }
  }
  
  boolean isScanFromWebPage()
  {
    return this.returnUrlTemplate != null;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\ScanFromWebPageManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */