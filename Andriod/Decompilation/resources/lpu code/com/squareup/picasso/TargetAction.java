package com.squareup.picasso;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

final class TargetAction
  extends Action<Target>
{
  TargetAction(Picasso paramPicasso, Target paramTarget, Request paramRequest, int paramInt1, int paramInt2, Drawable paramDrawable, String paramString, Object paramObject, int paramInt3)
  {
    super(paramPicasso, paramTarget, paramRequest, paramInt1, paramInt2, paramInt3, paramDrawable, paramString, paramObject, false);
  }
  
  void complete(Bitmap paramBitmap, Picasso.LoadedFrom paramLoadedFrom)
  {
    if (paramBitmap == null) {
      throw new AssertionError(String.format("Attempted to complete action with no result!\n%s", new Object[] { this }));
    }
    Target localTarget = (Target)getTarget();
    if (localTarget != null)
    {
      localTarget.onBitmapLoaded(paramBitmap, paramLoadedFrom);
      if (paramBitmap.isRecycled()) {
        throw new IllegalStateException("Target callback must not recycle bitmap!");
      }
    }
  }
  
  void error()
  {
    Target localTarget = (Target)getTarget();
    if (localTarget != null)
    {
      if (this.errorResId != 0) {
        localTarget.onBitmapFailed(this.picasso.context.getResources().getDrawable(this.errorResId));
      }
    }
    else {
      return;
    }
    localTarget.onBitmapFailed(this.errorDrawable);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\squareup\picasso\TargetAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */