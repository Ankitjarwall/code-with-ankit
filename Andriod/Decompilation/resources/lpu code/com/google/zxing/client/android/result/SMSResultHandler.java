package com.google.zxing.client.android.result;

import android.app.Activity;
import android.telephony.PhoneNumberUtils;
import barcodescanner.xservices.nl.barcodescanner.R.string;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.SMSParsedResult;

public final class SMSResultHandler
  extends ResultHandler
{
  private static final int[] buttons = { R.string.button_sms, R.string.button_mms };
  
  public SMSResultHandler(Activity paramActivity, ParsedResult paramParsedResult)
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
    SMSParsedResult localSMSParsedResult = (SMSParsedResult)getResult();
    Object localObject = localSMSParsedResult.getNumbers();
    String[] arrayOfString = new String[localObject.length];
    int i = 0;
    while (i < localObject.length)
    {
      arrayOfString[i] = PhoneNumberUtils.formatNumber(localObject[i]);
      i += 1;
    }
    localObject = new StringBuilder(50);
    ParsedResult.maybeAppend(arrayOfString, (StringBuilder)localObject);
    ParsedResult.maybeAppend(localSMSParsedResult.getSubject(), (StringBuilder)localObject);
    ParsedResult.maybeAppend(localSMSParsedResult.getBody(), (StringBuilder)localObject);
    return ((StringBuilder)localObject).toString();
  }
  
  public int getDisplayTitle()
  {
    return R.string.result_sms;
  }
  
  public void handleButtonPress(int paramInt)
  {
    SMSParsedResult localSMSParsedResult = (SMSParsedResult)getResult();
    String str = localSMSParsedResult.getNumbers()[0];
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      sendSMS(str, localSMSParsedResult.getBody());
      return;
    }
    sendMMS(str, localSMSParsedResult.getSubject(), localSMSParsedResult.getBody());
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\SMSResultHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */