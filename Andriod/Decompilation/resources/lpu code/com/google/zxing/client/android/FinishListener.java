package com.google.zxing.client.android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;

public final class FinishListener
  implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener
{
  private final Activity activityToFinish;
  
  public FinishListener(Activity paramActivity)
  {
    this.activityToFinish = paramActivity;
  }
  
  private void run()
  {
    this.activityToFinish.finish();
  }
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    run();
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    run();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\FinishListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */