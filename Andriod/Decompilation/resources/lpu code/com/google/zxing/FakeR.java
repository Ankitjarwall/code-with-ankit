package com.google.zxing;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;

public class FakeR
{
  private Context context;
  private String packageName;
  
  public FakeR(Activity paramActivity)
  {
    this.context = paramActivity.getApplicationContext();
    this.packageName = this.context.getPackageName();
  }
  
  public FakeR(Context paramContext)
  {
    this.context = paramContext;
    this.packageName = paramContext.getPackageName();
  }
  
  public static int getId(Context paramContext, String paramString1, String paramString2)
  {
    return paramContext.getResources().getIdentifier(paramString2, paramString1, paramContext.getPackageName());
  }
  
  public int getId(String paramString1, String paramString2)
  {
    return this.context.getResources().getIdentifier(paramString2, paramString1, this.packageName);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\FakeR.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */