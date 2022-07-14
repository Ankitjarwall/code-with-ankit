package com.google.zxing.client.android.book;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.google.zxing.client.android.LocaleManager;
import java.util.List;

final class BrowseBookListener
  implements AdapterView.OnItemClickListener
{
  private final SearchBookContentsActivity activity;
  private final List<SearchBookContentsResult> items;
  
  BrowseBookListener(SearchBookContentsActivity paramSearchBookContentsActivity, List<SearchBookContentsResult> paramList)
  {
    this.activity = paramSearchBookContentsActivity;
    this.items = paramList;
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (paramInt < 1) {}
    do
    {
      do
      {
        return;
        paramInt -= 1;
      } while (paramInt >= this.items.size());
      paramAdapterView = ((SearchBookContentsResult)this.items.get(paramInt)).getPageId();
      paramView = SearchBookContentsResult.getQuery();
    } while ((!LocaleManager.isBookSearchUrl(this.activity.getISBN())) || (paramAdapterView.isEmpty()));
    String str = this.activity.getISBN();
    str = str.substring(str.indexOf('=') + 1);
    paramAdapterView = new Intent("android.intent.action.VIEW", Uri.parse("http://books.google." + LocaleManager.getBookSearchCountryTLD(this.activity) + "/books?id=" + str + "&pg=" + paramAdapterView + "&vq=" + paramView));
    paramAdapterView.addFlags(524288);
    this.activity.startActivity(paramAdapterView);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\book\BrowseBookListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */