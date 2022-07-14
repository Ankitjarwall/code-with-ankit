package com.google.zxing.client.android.result;

import com.google.zxing.Result;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ResultParser;

public final class ResultHandlerFactory
{
  public static ResultHandler makeResultHandler(CaptureActivity paramCaptureActivity, Result paramResult)
  {
    ParsedResult localParsedResult = parseResult(paramResult);
    switch (localParsedResult.getType())
    {
    default: 
      return new TextResultHandler(paramCaptureActivity, localParsedResult, paramResult);
    case ???: 
      return new AddressBookResultHandler(paramCaptureActivity, localParsedResult);
    case ???: 
      return new EmailAddressResultHandler(paramCaptureActivity, localParsedResult);
    case ???: 
      return new ProductResultHandler(paramCaptureActivity, localParsedResult, paramResult);
    case ???: 
      return new URIResultHandler(paramCaptureActivity, localParsedResult);
    case ???: 
      return new WifiResultHandler(paramCaptureActivity, localParsedResult);
    case ???: 
      return new GeoResultHandler(paramCaptureActivity, localParsedResult);
    case ???: 
      return new TelResultHandler(paramCaptureActivity, localParsedResult);
    case ???: 
      return new SMSResultHandler(paramCaptureActivity, localParsedResult);
    case ???: 
      return new CalendarResultHandler(paramCaptureActivity, localParsedResult);
    }
    return new ISBNResultHandler(paramCaptureActivity, localParsedResult, paramResult);
  }
  
  private static ParsedResult parseResult(Result paramResult)
  {
    return ResultParser.parseResult(paramResult);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\ResultHandlerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */