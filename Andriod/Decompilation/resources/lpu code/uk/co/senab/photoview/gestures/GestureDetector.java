package uk.co.senab.photoview.gestures;

import android.view.MotionEvent;

public abstract interface GestureDetector
{
  public abstract boolean isDragging();
  
  public abstract boolean isScaling();
  
  public abstract boolean onTouchEvent(MotionEvent paramMotionEvent);
  
  public abstract void setOnGestureListener(OnGestureListener paramOnGestureListener);
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\uk\co\senab\photoview\gestures\GestureDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */