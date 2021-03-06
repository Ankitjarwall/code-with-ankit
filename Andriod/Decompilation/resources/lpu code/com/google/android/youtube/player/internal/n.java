package com.google.android.youtube.player.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;

public final class n
  extends FrameLayout
{
  private final ProgressBar a;
  private final TextView b;
  
  public n(Context paramContext)
  {
    super(paramContext, null, z.c(paramContext));
    m localm = new m(paramContext);
    setBackgroundColor(-16777216);
    this.a = new ProgressBar(paramContext);
    this.a.setVisibility(8);
    addView(this.a, new FrameLayout.LayoutParams(-2, -2, 17));
    int i = (int)(10.0F * paramContext.getResources().getDisplayMetrics().density + 0.5F);
    this.b = new TextView(paramContext);
    this.b.setTextAppearance(paramContext, 16973894);
    this.b.setTextColor(-1);
    this.b.setVisibility(8);
    this.b.setPadding(i, i, i, i);
    this.b.setGravity(17);
    this.b.setText(localm.a);
    addView(this.b, new FrameLayout.LayoutParams(-2, -2, 17));
  }
  
  public final void a()
  {
    this.a.setVisibility(8);
    this.b.setVisibility(8);
  }
  
  public final void b()
  {
    this.a.setVisibility(0);
    this.b.setVisibility(8);
  }
  
  public final void c()
  {
    this.a.setVisibility(8);
    this.b.setVisibility(0);
  }
  
  protected final void onMeasure(int paramInt1, int paramInt2)
  {
    int k = View.MeasureSpec.getMode(paramInt1);
    int m = View.MeasureSpec.getMode(paramInt2);
    int j = View.MeasureSpec.getSize(paramInt1);
    int i = View.MeasureSpec.getSize(paramInt2);
    if ((k == 1073741824) && (m == 1073741824)) {}
    for (;;)
    {
      paramInt1 = resolveSize(j, paramInt1);
      paramInt2 = resolveSize(i, paramInt2);
      super.onMeasure(View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824), View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824));
      return;
      if ((k == 1073741824) || ((k == Integer.MIN_VALUE) && (m == 0)))
      {
        i = (int)(j / 1.777F);
      }
      else if ((m == 1073741824) || ((m == Integer.MIN_VALUE) && (k == 0)))
      {
        j = (int)(i * 1.777F);
      }
      else if ((k == Integer.MIN_VALUE) && (m == Integer.MIN_VALUE))
      {
        if (i < j / 1.777F) {
          j = (int)(i * 1.777F);
        } else {
          i = (int)(j / 1.777F);
        }
      }
      else
      {
        i = 0;
        j = 0;
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\youtube\player\internal\n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */