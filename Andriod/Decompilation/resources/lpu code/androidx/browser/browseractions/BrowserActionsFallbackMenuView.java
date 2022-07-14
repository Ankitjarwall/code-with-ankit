package androidx.browser.browseractions;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.RestrictTo;
import android.support.customtabs.R.dimen;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class BrowserActionsFallbackMenuView
  extends LinearLayout
{
  private final int mBrowserActionsMenuMaxWidthPx = getResources().getDimensionPixelOffset(R.dimen.browser_actions_context_menu_max_width);
  private final int mBrowserActionsMenuMinPaddingPx = getResources().getDimensionPixelOffset(R.dimen.browser_actions_context_menu_min_padding);
  
  public BrowserActionsFallbackMenuView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(getResources().getDisplayMetrics().widthPixels - this.mBrowserActionsMenuMinPaddingPx * 2, this.mBrowserActionsMenuMaxWidthPx), 1073741824), paramInt2);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\androidx\browser\browseractions\BrowserActionsFallbackMenuView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */