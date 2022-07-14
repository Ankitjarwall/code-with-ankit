package com.google.zxing.client.android.result;

import android.app.Activity;
import barcodescanner.xservices.nl.barcodescanner.R.string;
import com.google.zxing.client.android.LocaleManager;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.URIParsedResult;
import java.util.Locale;

public final class URIResultHandler
  extends ResultHandler
{
  private static final String[] SECURE_PROTOCOLS = { "otpauth:" };
  private static final int[] buttons = { R.string.button_open_browser, R.string.button_share_by_email, R.string.button_share_by_sms, R.string.button_search_book_contents };
  
  public URIResultHandler(Activity paramActivity, ParsedResult paramParsedResult)
  {
    super(paramActivity, paramParsedResult);
  }
  
  public boolean areContentsSecure()
  {
    boolean bool2 = false;
    String str = ((URIParsedResult)getResult()).getURI().toLowerCase(Locale.ENGLISH);
    String[] arrayOfString = SECURE_PROTOCOLS;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (str.startsWith(arrayOfString[i])) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public int getButtonCount()
  {
    if (LocaleManager.isBookSearchUrl(((URIParsedResult)getResult()).getURI())) {
      return buttons.length;
    }
    return buttons.length - 1;
  }
  
  public int getButtonText(int paramInt)
  {
    return buttons[paramInt];
  }
  
  public Integer getDefaultButtonID()
  {
    return Integer.valueOf(0);
  }
  
  public int getDisplayTitle()
  {
    return R.string.result_uri;
  }
  
  public void handleButtonPress(int paramInt)
  {
    String str = ((URIParsedResult)getResult()).getURI();
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      openURL(str);
      return;
    case 1: 
      shareByEmail(str);
      return;
    case 2: 
      shareBySMS(str);
      return;
    }
    searchBookContents(str);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\URIResultHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */