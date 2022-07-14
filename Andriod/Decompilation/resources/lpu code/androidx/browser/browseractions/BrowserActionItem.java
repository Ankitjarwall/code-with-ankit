package androidx.browser.browseractions;

import android.app.PendingIntent;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

public class BrowserActionItem
{
  private final PendingIntent mAction;
  @DrawableRes
  private final int mIconId;
  private final String mTitle;
  
  public BrowserActionItem(@NonNull String paramString, @NonNull PendingIntent paramPendingIntent)
  {
    this(paramString, paramPendingIntent, 0);
  }
  
  public BrowserActionItem(@NonNull String paramString, @NonNull PendingIntent paramPendingIntent, @DrawableRes int paramInt)
  {
    this.mTitle = paramString;
    this.mAction = paramPendingIntent;
    this.mIconId = paramInt;
  }
  
  public PendingIntent getAction()
  {
    return this.mAction;
  }
  
  public int getIconId()
  {
    return this.mIconId;
  }
  
  public String getTitle()
  {
    return this.mTitle;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\androidx\browser\browseractions\BrowserActionItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */