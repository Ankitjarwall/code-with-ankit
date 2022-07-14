package com.google.zxing.client.android.result;

import android.view.View;
import android.view.View.OnClickListener;

public final class ResultButtonListener
  implements View.OnClickListener
{
  private final int index;
  private final ResultHandler resultHandler;
  
  public ResultButtonListener(ResultHandler paramResultHandler, int paramInt)
  {
    this.resultHandler = paramResultHandler;
    this.index = paramInt;
  }
  
  public void onClick(View paramView)
  {
    this.resultHandler.handleButtonPress(this.index);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\ResultButtonListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */