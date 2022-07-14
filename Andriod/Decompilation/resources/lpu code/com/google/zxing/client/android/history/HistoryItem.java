package com.google.zxing.client.android.history;

import com.google.zxing.Result;

public final class HistoryItem
{
  private final String details;
  private final String display;
  private final Result result;
  
  HistoryItem(Result paramResult, String paramString1, String paramString2)
  {
    this.result = paramResult;
    this.display = paramString1;
    this.details = paramString2;
  }
  
  public String getDisplayAndDetails()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((this.display == null) || (this.display.isEmpty())) {
      localStringBuilder.append(this.result.getText());
    }
    for (;;)
    {
      if ((this.details != null) && (!this.details.isEmpty())) {
        localStringBuilder.append(" : ").append(this.details);
      }
      return localStringBuilder.toString();
      localStringBuilder.append(this.display);
    }
  }
  
  public Result getResult()
  {
    return this.result;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\history\HistoryItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */