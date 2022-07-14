package com.initialxy.cordova.themeablebrowser;

import android.app.Dialog;
import android.content.Context;

public class ThemeableBrowserDialog
  extends Dialog
{
  Context context;
  boolean hardwareBack;
  ThemeableBrowser themeableBrowser = null;
  
  public ThemeableBrowserDialog(Context paramContext, int paramInt, boolean paramBoolean)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
    this.hardwareBack = paramBoolean;
  }
  
  public void onBackPressed()
  {
    if (this.themeableBrowser == null)
    {
      dismiss();
      return;
    }
    if ((this.hardwareBack) && (this.themeableBrowser.canGoBack()))
    {
      this.themeableBrowser.goBack();
      return;
    }
    this.themeableBrowser.closeDialog();
  }
  
  public void setThemeableBrowser(ThemeableBrowser paramThemeableBrowser)
  {
    this.themeableBrowser = paramThemeableBrowser;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\initialxy\cordova\themeablebrowser\ThemeableBrowserDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */