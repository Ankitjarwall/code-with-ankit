package org.apache.cordova;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import android.widget.EditText;

public class CordovaDialogsHelper
{
  private final Context context;
  private AlertDialog lastHandledDialog;
  
  public CordovaDialogsHelper(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public void destroyLastDialog()
  {
    if (this.lastHandledDialog != null) {
      this.lastHandledDialog.cancel();
    }
  }
  
  public void showAlert(String paramString, final Result paramResult)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.context);
    localBuilder.setMessage(paramString);
    localBuilder.setTitle("Alert");
    localBuilder.setCancelable(true);
    localBuilder.setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramResult.gotResult(true, null);
      }
    });
    localBuilder.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        paramResult.gotResult(false, null);
      }
    });
    localBuilder.setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        boolean bool = true;
        if (paramAnonymousInt == 4)
        {
          paramResult.gotResult(true, null);
          bool = false;
        }
        return bool;
      }
    });
    this.lastHandledDialog = localBuilder.show();
  }
  
  public void showConfirm(String paramString, final Result paramResult)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.context);
    localBuilder.setMessage(paramString);
    localBuilder.setTitle("Confirm");
    localBuilder.setCancelable(true);
    localBuilder.setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramResult.gotResult(true, null);
      }
    });
    localBuilder.setNegativeButton(17039360, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramResult.gotResult(false, null);
      }
    });
    localBuilder.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        paramResult.gotResult(false, null);
      }
    });
    localBuilder.setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        if (paramAnonymousInt == 4)
        {
          paramResult.gotResult(false, null);
          return false;
        }
        return true;
      }
    });
    this.lastHandledDialog = localBuilder.show();
  }
  
  public void showPrompt(final String paramString1, String paramString2, final Result paramResult)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.context);
    localBuilder.setMessage(paramString1);
    paramString1 = new EditText(this.context);
    if (paramString2 != null) {
      paramString1.setText(paramString2);
    }
    localBuilder.setView(paramString1);
    localBuilder.setCancelable(false);
    localBuilder.setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = paramString1.getText().toString();
        paramResult.gotResult(true, paramAnonymousDialogInterface);
      }
    });
    localBuilder.setNegativeButton(17039360, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramResult.gotResult(false, null);
      }
    });
    this.lastHandledDialog = localBuilder.show();
  }
  
  public static abstract interface Result
  {
    public abstract void gotResult(boolean paramBoolean, String paramString);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\CordovaDialogsHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */