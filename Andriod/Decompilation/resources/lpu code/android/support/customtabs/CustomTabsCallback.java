package android.support.customtabs;

import android.net.Uri;
import android.os.Bundle;

public class CustomTabsCallback
{
  public static final int NAVIGATION_ABORTED = 4;
  public static final int NAVIGATION_FAILED = 3;
  public static final int NAVIGATION_FINISHED = 2;
  public static final int NAVIGATION_STARTED = 1;
  public static final int TAB_HIDDEN = 6;
  public static final int TAB_SHOWN = 5;
  
  public void extraCallback(String paramString, Bundle paramBundle) {}
  
  public void onMessageChannelReady(Bundle paramBundle) {}
  
  public void onNavigationEvent(int paramInt, Bundle paramBundle) {}
  
  public void onPostMessage(String paramString, Bundle paramBundle) {}
  
  public void onRelationshipValidationResult(int paramInt, Uri paramUri, boolean paramBoolean, Bundle paramBundle) {}
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\customtabs\CustomTabsCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */