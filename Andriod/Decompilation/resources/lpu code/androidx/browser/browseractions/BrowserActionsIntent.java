package androidx.browser.browseractions;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrowserActionsIntent
{
  public static final String ACTION_BROWSER_ACTIONS_OPEN = "androidx.browser.browseractions.browser_action_open";
  public static final String EXTRA_APP_ID = "androidx.browser.browseractions.APP_ID";
  public static final String EXTRA_MENU_ITEMS = "androidx.browser.browseractions.extra.MENU_ITEMS";
  public static final String EXTRA_SELECTED_ACTION_PENDING_INTENT = "androidx.browser.browseractions.extra.SELECTED_ACTION_PENDING_INTENT";
  public static final String EXTRA_TYPE = "androidx.browser.browseractions.extra.TYPE";
  public static final int ITEM_COPY = 3;
  public static final int ITEM_DOWNLOAD = 2;
  public static final int ITEM_INVALID_ITEM = -1;
  public static final int ITEM_OPEN_IN_INCOGNITO = 1;
  public static final int ITEM_OPEN_IN_NEW_TAB = 0;
  public static final int ITEM_SHARE = 4;
  public static final String KEY_ACTION = "androidx.browser.browseractions.ACTION";
  public static final String KEY_ICON_ID = "androidx.browser.browseractions.ICON_ID";
  public static final String KEY_TITLE = "androidx.browser.browseractions.TITLE";
  public static final int MAX_CUSTOM_ITEMS = 5;
  private static final String TAG = "BrowserActions";
  private static final String TEST_URL = "https://www.example.com";
  public static final int URL_TYPE_AUDIO = 3;
  public static final int URL_TYPE_FILE = 4;
  public static final int URL_TYPE_IMAGE = 1;
  public static final int URL_TYPE_NONE = 0;
  public static final int URL_TYPE_PLUGIN = 5;
  public static final int URL_TYPE_VIDEO = 2;
  private static BrowserActionsFallDialogListener sDialogListenter;
  @NonNull
  private final Intent mIntent;
  
  private BrowserActionsIntent(@NonNull Intent paramIntent)
  {
    this.mIntent = paramIntent;
  }
  
  private static List<ResolveInfo> getBrowserActionsIntentHandlers(Context paramContext)
  {
    Intent localIntent = new Intent("androidx.browser.browseractions.browser_action_open", Uri.parse("https://www.example.com"));
    return paramContext.getPackageManager().queryIntentActivities(localIntent, 131072);
  }
  
  public static String getCreatorPackageName(Intent paramIntent)
  {
    paramIntent = (PendingIntent)paramIntent.getParcelableExtra("androidx.browser.browseractions.APP_ID");
    if (paramIntent != null)
    {
      if (Build.VERSION.SDK_INT >= 17) {
        return paramIntent.getCreatorPackage();
      }
      return paramIntent.getTargetPackage();
    }
    return null;
  }
  
  public static void launchIntent(Context paramContext, Intent paramIntent)
  {
    launchIntent(paramContext, paramIntent, getBrowserActionsIntentHandlers(paramContext));
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  @VisibleForTesting
  static void launchIntent(Context paramContext, Intent paramIntent, List<ResolveInfo> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0))
    {
      openFallbackBrowserActionsMenu(paramContext, paramIntent);
      return;
    }
    if (paramList.size() == 1) {
      paramIntent.setPackage(((ResolveInfo)paramList.get(0)).activityInfo.packageName);
    }
    label152:
    for (;;)
    {
      ContextCompat.startActivity(paramContext, paramIntent, null);
      return;
      Object localObject = new Intent("android.intent.action.VIEW", Uri.parse("https://www.example.com"));
      localObject = paramContext.getPackageManager().resolveActivity((Intent)localObject, 65536);
      if (localObject != null)
      {
        localObject = ((ResolveInfo)localObject).activityInfo.packageName;
        int i = 0;
        for (;;)
        {
          if (i >= paramList.size()) {
            break label152;
          }
          if (((String)localObject).equals(((ResolveInfo)paramList.get(i)).activityInfo.packageName))
          {
            paramIntent.setPackage((String)localObject);
            break;
          }
          i += 1;
        }
      }
    }
  }
  
  public static void openBrowserAction(Context paramContext, Uri paramUri)
  {
    launchIntent(paramContext, new Builder(paramContext, paramUri).build().getIntent());
  }
  
  public static void openBrowserAction(Context paramContext, Uri paramUri, int paramInt, ArrayList<BrowserActionItem> paramArrayList, PendingIntent paramPendingIntent)
  {
    launchIntent(paramContext, new Builder(paramContext, paramUri).setUrlType(paramInt).setCustomItems(paramArrayList).setOnItemSelectedAction(paramPendingIntent).build().getIntent());
  }
  
  private static void openFallbackBrowserActionsMenu(Context paramContext, Intent paramIntent)
  {
    Uri localUri = paramIntent.getData();
    int i = paramIntent.getIntExtra("androidx.browser.browseractions.extra.TYPE", 0);
    paramIntent = paramIntent.getParcelableArrayListExtra("androidx.browser.browseractions.extra.MENU_ITEMS");
    if (paramIntent != null) {}
    for (paramIntent = parseBrowserActionItems(paramIntent);; paramIntent = null)
    {
      openFallbackBrowserActionsMenu(paramContext, localUri, i, paramIntent);
      return;
    }
  }
  
  private static void openFallbackBrowserActionsMenu(Context paramContext, Uri paramUri, int paramInt, List<BrowserActionItem> paramList)
  {
    new BrowserActionsFallbackMenuUi(paramContext, paramUri, paramList).displayMenu();
    if (sDialogListenter != null) {
      sDialogListenter.onDialogShown();
    }
  }
  
  public static List<BrowserActionItem> parseBrowserActionItems(ArrayList<Bundle> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramArrayList.size())
    {
      Bundle localBundle = (Bundle)paramArrayList.get(i);
      String str = localBundle.getString("androidx.browser.browseractions.TITLE");
      PendingIntent localPendingIntent = (PendingIntent)localBundle.getParcelable("androidx.browser.browseractions.ACTION");
      int j = localBundle.getInt("androidx.browser.browseractions.ICON_ID");
      if ((TextUtils.isEmpty(str)) || (localPendingIntent == null)) {
        throw new IllegalArgumentException("Custom item should contain a non-empty title and non-null intent.");
      }
      localArrayList.add(new BrowserActionItem(str, localPendingIntent, j));
      i += 1;
    }
    return localArrayList;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  @VisibleForTesting
  static void setDialogShownListenter(BrowserActionsFallDialogListener paramBrowserActionsFallDialogListener)
  {
    sDialogListenter = paramBrowserActionsFallDialogListener;
  }
  
  @NonNull
  public Intent getIntent()
  {
    return this.mIntent;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  @VisibleForTesting
  static abstract interface BrowserActionsFallDialogListener
  {
    public abstract void onDialogShown();
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface BrowserActionsItemId {}
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface BrowserActionsUrlType {}
  
  public static final class Builder
  {
    private Context mContext;
    private final Intent mIntent = new Intent("androidx.browser.browseractions.browser_action_open");
    private ArrayList<Bundle> mMenuItems = null;
    private PendingIntent mOnItemSelectedPendingIntent = null;
    private int mType;
    private Uri mUri;
    
    public Builder(Context paramContext, Uri paramUri)
    {
      this.mContext = paramContext;
      this.mUri = paramUri;
      this.mType = 0;
      this.mMenuItems = new ArrayList();
    }
    
    private Bundle getBundleFromItem(BrowserActionItem paramBrowserActionItem)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("androidx.browser.browseractions.TITLE", paramBrowserActionItem.getTitle());
      localBundle.putParcelable("androidx.browser.browseractions.ACTION", paramBrowserActionItem.getAction());
      if (paramBrowserActionItem.getIconId() != 0) {
        localBundle.putInt("androidx.browser.browseractions.ICON_ID", paramBrowserActionItem.getIconId());
      }
      return localBundle;
    }
    
    public BrowserActionsIntent build()
    {
      this.mIntent.setData(this.mUri);
      this.mIntent.putExtra("androidx.browser.browseractions.extra.TYPE", this.mType);
      this.mIntent.putParcelableArrayListExtra("androidx.browser.browseractions.extra.MENU_ITEMS", this.mMenuItems);
      PendingIntent localPendingIntent = PendingIntent.getActivity(this.mContext, 0, new Intent(), 0);
      this.mIntent.putExtra("androidx.browser.browseractions.APP_ID", localPendingIntent);
      if (this.mOnItemSelectedPendingIntent != null) {
        this.mIntent.putExtra("androidx.browser.browseractions.extra.SELECTED_ACTION_PENDING_INTENT", this.mOnItemSelectedPendingIntent);
      }
      return new BrowserActionsIntent(this.mIntent, null);
    }
    
    public Builder setCustomItems(ArrayList<BrowserActionItem> paramArrayList)
    {
      if (paramArrayList.size() > 5) {
        throw new IllegalStateException("Exceeded maximum toolbar item count of 5");
      }
      int i = 0;
      while (i < paramArrayList.size())
      {
        if ((TextUtils.isEmpty(((BrowserActionItem)paramArrayList.get(i)).getTitle())) || (((BrowserActionItem)paramArrayList.get(i)).getAction() == null)) {
          throw new IllegalArgumentException("Custom item should contain a non-empty title and non-null intent.");
        }
        this.mMenuItems.add(getBundleFromItem((BrowserActionItem)paramArrayList.get(i)));
        i += 1;
      }
      return this;
    }
    
    public Builder setCustomItems(BrowserActionItem... paramVarArgs)
    {
      return setCustomItems(new ArrayList(Arrays.asList(paramVarArgs)));
    }
    
    public Builder setOnItemSelectedAction(PendingIntent paramPendingIntent)
    {
      this.mOnItemSelectedPendingIntent = paramPendingIntent;
      return this;
    }
    
    public Builder setUrlType(int paramInt)
    {
      this.mType = paramInt;
      return this;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\androidx\browser\browseractions\BrowserActionsIntent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */