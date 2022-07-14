package com.google.zxing.client.android.history;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import barcodescanner.xservices.nl.barcodescanner.R.id;
import barcodescanner.xservices.nl.barcodescanner.R.layout;
import barcodescanner.xservices.nl.barcodescanner.R.string;
import com.google.zxing.Result;
import java.util.ArrayList;

final class HistoryItemAdapter
  extends ArrayAdapter<HistoryItem>
{
  private final Context activity;
  
  HistoryItemAdapter(Context paramContext)
  {
    super(paramContext, R.layout.history_list_item, new ArrayList());
    this.activity = paramContext;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject;
    if ((paramView instanceof LinearLayout))
    {
      paramViewGroup = (HistoryItem)getItem(paramInt);
      localObject = paramViewGroup.getResult();
      if (localObject == null) {
        break label89;
      }
      localObject = ((Result)localObject).getText();
    }
    for (paramViewGroup = paramViewGroup.getDisplayAndDetails();; paramViewGroup = paramViewGroup.getString(R.string.history_empty_detail))
    {
      ((TextView)paramView.findViewById(R.id.history_title)).setText((CharSequence)localObject);
      ((TextView)paramView.findViewById(R.id.history_detail)).setText(paramViewGroup);
      return paramView;
      paramView = LayoutInflater.from(this.activity).inflate(R.layout.history_list_item, paramViewGroup, false);
      break;
      label89:
      paramViewGroup = getContext().getResources();
      localObject = paramViewGroup.getString(R.string.history_empty);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\history\HistoryItemAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */