package com.google.zxing.client.android.result;

import android.app.Activity;
import barcodescanner.xservices.nl.barcodescanner.R.string;
import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;

public final class TextResultHandler
  extends ResultHandler
{
  private static final int[] buttons = { R.string.button_web_search, R.string.button_share_by_email, R.string.button_share_by_sms, R.string.button_custom_product_search };
  
  public TextResultHandler(Activity paramActivity, ParsedResult paramParsedResult, Result paramResult)
  {
    super(paramActivity, paramParsedResult, paramResult);
  }
  
  public int getButtonCount()
  {
    if (hasCustomProductSearch()) {
      return buttons.length;
    }
    return buttons.length - 1;
  }
  
  public int getButtonText(int paramInt)
  {
    return buttons[paramInt];
  }
  
  public int getDisplayTitle()
  {
    return R.string.result_text;
  }
  
  public void handleButtonPress(int paramInt)
  {
    String str = getResult().getDisplayResult();
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      webSearch(str);
      return;
    case 1: 
      shareByEmail(str);
      return;
    case 2: 
      shareBySMS(str);
      return;
    }
    openURL(fillInCustomSearchURL(str));
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\TextResultHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */