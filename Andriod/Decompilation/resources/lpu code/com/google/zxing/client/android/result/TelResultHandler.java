package com.google.zxing.client.android.result;

import android.app.Activity;
import android.telephony.PhoneNumberUtils;
import barcodescanner.xservices.nl.barcodescanner.R.string;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.TelParsedResult;

public final class TelResultHandler
  extends ResultHandler
{
  private static final int[] buttons = { R.string.button_dial, R.string.button_add_contact };
  
  public TelResultHandler(Activity paramActivity, ParsedResult paramParsedResult)
  {
    super(paramActivity, paramParsedResult);
  }
  
  public int getButtonCount()
  {
    return buttons.length;
  }
  
  public int getButtonText(int paramInt)
  {
    return buttons[paramInt];
  }
  
  public CharSequence getDisplayContents()
  {
    return PhoneNumberUtils.formatNumber(getResult().getDisplayResult().replace("\r", ""));
  }
  
  public int getDisplayTitle()
  {
    return R.string.result_tel;
  }
  
  public void handleButtonPress(int paramInt)
  {
    TelParsedResult localTelParsedResult = (TelParsedResult)getResult();
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      dialPhoneFromUri(localTelParsedResult.getTelURI());
      getActivity().finish();
      return;
    }
    addPhoneOnlyContact(new String[] { localTelParsedResult.getNumber() }, null);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\TelResultHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */