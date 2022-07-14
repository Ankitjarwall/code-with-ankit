package com.google.zxing.client.android;

import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;

final class ViewfinderResultPointCallback
  implements ResultPointCallback
{
  private final ViewfinderView viewfinderView;
  
  ViewfinderResultPointCallback(ViewfinderView paramViewfinderView)
  {
    this.viewfinderView = paramViewfinderView;
  }
  
  public void foundPossibleResultPoint(ResultPoint paramResultPoint)
  {
    this.viewfinderView.addPossibleResultPoint(paramResultPoint);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\ViewfinderResultPointCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */