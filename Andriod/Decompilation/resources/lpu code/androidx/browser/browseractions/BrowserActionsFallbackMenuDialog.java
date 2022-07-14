package androidx.browser.browseractions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;

class BrowserActionsFallbackMenuDialog
  extends Dialog
{
  private static final long ENTER_ANIMATION_DURATION_MS = 250L;
  private static final long EXIT_ANIMATION_DURATION_MS = 150L;
  private final View mContentView;
  
  BrowserActionsFallbackMenuDialog(Context paramContext, View paramView)
  {
    super(paramContext);
    this.mContentView = paramView;
  }
  
  private void startAnimation(final boolean paramBoolean)
  {
    float f2 = 1.0F;
    float f1;
    if (paramBoolean)
    {
      f1 = 0.0F;
      if (!paramBoolean) {
        break label88;
      }
      label12:
      if (!paramBoolean) {
        break label93;
      }
    }
    label88:
    label93:
    for (long l = 250L;; l = 150L)
    {
      this.mContentView.setScaleX(f1);
      this.mContentView.setScaleY(f1);
      this.mContentView.animate().scaleX(f2).scaleY(f2).setDuration(l).setInterpolator(new LinearOutSlowInInterpolator()).setListener(new AnimatorListenerAdapter()
      {
        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          if (!paramBoolean) {
            BrowserActionsFallbackMenuDialog.this.dismiss();
          }
        }
      }).start();
      return;
      f1 = 1.0F;
      break;
      f2 = 0.0F;
      break label12;
    }
  }
  
  public void dismiss()
  {
    startAnimation(false);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 0)
    {
      dismiss();
      return true;
    }
    return false;
  }
  
  public void show()
  {
    getWindow().setBackgroundDrawable(new ColorDrawable(0));
    startAnimation(true);
    super.show();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\androidx\browser\browseractions\BrowserActionsFallbackMenuDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */