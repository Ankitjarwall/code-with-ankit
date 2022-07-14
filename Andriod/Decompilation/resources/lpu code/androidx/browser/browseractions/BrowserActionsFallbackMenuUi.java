package androidx.browser.browseractions;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.net.Uri;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.customtabs.R.id;
import android.support.customtabs.R.layout;
import android.support.v4.widget.TextViewCompat;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

class BrowserActionsFallbackMenuUi
  implements AdapterView.OnItemClickListener
{
  private static final String TAG = "BrowserActionskMenuUi";
  private BrowserActionsFallbackMenuDialog mBrowserActionsDialog;
  private final Context mContext;
  private final List<BrowserActionItem> mMenuItems;
  private BrowserActionsFallMenuUiListener mMenuUiListener;
  private final Uri mUri;
  
  BrowserActionsFallbackMenuUi(Context paramContext, Uri paramUri, List<BrowserActionItem> paramList)
  {
    this.mContext = paramContext;
    this.mUri = paramUri;
    this.mMenuItems = paramList;
  }
  
  private BrowserActionsFallbackMenuView initMenuView(View paramView)
  {
    BrowserActionsFallbackMenuView localBrowserActionsFallbackMenuView = (BrowserActionsFallbackMenuView)paramView.findViewById(R.id.browser_actions_menu_view);
    final TextView localTextView = (TextView)paramView.findViewById(R.id.browser_actions_header_text);
    localTextView.setText(this.mUri.toString());
    localTextView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (TextViewCompat.getMaxLines(localTextView) == Integer.MAX_VALUE)
        {
          localTextView.setMaxLines(1);
          localTextView.setEllipsize(TextUtils.TruncateAt.END);
          return;
        }
        localTextView.setMaxLines(Integer.MAX_VALUE);
        localTextView.setEllipsize(null);
      }
    });
    paramView = (ListView)paramView.findViewById(R.id.browser_actions_menu_items);
    paramView.setAdapter(new BrowserActionsFallbackMenuAdapter(this.mMenuItems, this.mContext));
    paramView.setOnItemClickListener(this);
    return localBrowserActionsFallbackMenuView;
  }
  
  public void displayMenu()
  {
    final View localView = LayoutInflater.from(this.mContext).inflate(R.layout.browser_actions_context_menu_page, null);
    this.mBrowserActionsDialog = new BrowserActionsFallbackMenuDialog(this.mContext, initMenuView(localView));
    this.mBrowserActionsDialog.setContentView(localView);
    if (this.mMenuUiListener != null) {
      this.mBrowserActionsDialog.setOnShowListener(new DialogInterface.OnShowListener()
      {
        public void onShow(DialogInterface paramAnonymousDialogInterface)
        {
          BrowserActionsFallbackMenuUi.this.mMenuUiListener.onMenuShown(localView);
        }
      });
    }
    this.mBrowserActionsDialog.show();
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = ((BrowserActionItem)this.mMenuItems.get(paramInt)).getAction();
    try
    {
      paramAdapterView.send();
      this.mBrowserActionsDialog.dismiss();
      return;
    }
    catch (PendingIntent.CanceledException paramAdapterView)
    {
      Log.e("BrowserActionskMenuUi", "Failed to send custom item action", paramAdapterView);
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  @VisibleForTesting
  void setMenuUiListener(BrowserActionsFallMenuUiListener paramBrowserActionsFallMenuUiListener)
  {
    this.mMenuUiListener = paramBrowserActionsFallMenuUiListener;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  @VisibleForTesting
  static abstract interface BrowserActionsFallMenuUiListener
  {
    public abstract void onMenuShown(View paramView);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\androidx\browser\browseractions\BrowserActionsFallbackMenuUi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */