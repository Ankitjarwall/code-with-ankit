package com.google.zxing.client.android.result;

import android.app.Activity;
import barcodescanner.xservices.nl.barcodescanner.R.string;
import com.google.zxing.Result;
import com.google.zxing.client.result.ExpandedProductParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ProductParsedResult;

public final class ProductResultHandler
  extends ResultHandler
{
  private static final int[] buttons = { R.string.button_product_search, R.string.button_web_search, R.string.button_custom_product_search };
  
  public ProductResultHandler(Activity paramActivity, ParsedResult paramParsedResult, Result paramResult)
  {
    super(paramActivity, paramParsedResult, paramResult);
  }
  
  private static String getProductIDFromResult(ParsedResult paramParsedResult)
  {
    if ((paramParsedResult instanceof ProductParsedResult)) {
      return ((ProductParsedResult)paramParsedResult).getNormalizedProductID();
    }
    if ((paramParsedResult instanceof ExpandedProductParsedResult)) {
      return ((ExpandedProductParsedResult)paramParsedResult).getRawText();
    }
    throw new IllegalArgumentException(paramParsedResult.getClass().toString());
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
    return R.string.result_product;
  }
  
  public void handleButtonPress(int paramInt)
  {
    String str = getProductIDFromResult(getResult());
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      openProductSearch(str);
      return;
    case 1: 
      webSearch(str);
      return;
    }
    openURL(fillInCustomSearchURL(str));
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\ProductResultHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */