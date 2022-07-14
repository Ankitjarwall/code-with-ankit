package androidx.browser.browseractions;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.customtabs.R.id;
import android.support.customtabs.R.layout;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

class BrowserActionsFallbackMenuAdapter
  extends BaseAdapter
{
  private final Context mContext;
  private final List<BrowserActionItem> mMenuItems;
  
  BrowserActionsFallbackMenuAdapter(List<BrowserActionItem> paramList, Context paramContext)
  {
    this.mMenuItems = paramList;
    this.mContext = paramContext;
  }
  
  public int getCount()
  {
    return this.mMenuItems.size();
  }
  
  public Object getItem(int paramInt)
  {
    return this.mMenuItems.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject = (BrowserActionItem)this.mMenuItems.get(paramInt);
    if (paramView == null)
    {
      paramView = LayoutInflater.from(this.mContext).inflate(R.layout.browser_actions_context_menu_row, null);
      paramViewGroup = new ViewHolderItem(null);
      paramViewGroup.mIcon = ((ImageView)paramView.findViewById(R.id.browser_actions_menu_item_icon));
      paramViewGroup.mText = ((TextView)paramView.findViewById(R.id.browser_actions_menu_item_text));
      paramView.setTag(paramViewGroup);
    }
    for (;;)
    {
      paramViewGroup.mText.setText(((BrowserActionItem)localObject).getTitle());
      if (((BrowserActionItem)localObject).getIconId() == 0) {
        break;
      }
      localObject = ResourcesCompat.getDrawable(this.mContext.getResources(), ((BrowserActionItem)localObject).getIconId(), null);
      paramViewGroup.mIcon.setImageDrawable((Drawable)localObject);
      return paramView;
      paramViewGroup = (ViewHolderItem)paramView.getTag();
    }
    paramViewGroup.mIcon.setImageDrawable(null);
    return paramView;
  }
  
  private static class ViewHolderItem
  {
    ImageView mIcon;
    TextView mText;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\androidx\browser\browseractions\BrowserActionsFallbackMenuAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */