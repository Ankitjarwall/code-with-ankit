package com.squareup.picasso;

import android.graphics.Bitmap;

class FetchAction
  extends Action<Object>
{
  private Callback callback;
  private final Object target = new Object();
  
  FetchAction(Picasso paramPicasso, Request paramRequest, int paramInt1, int paramInt2, Object paramObject, String paramString, Callback paramCallback)
  {
    super(paramPicasso, null, paramRequest, paramInt1, paramInt2, 0, null, paramString, paramObject, false);
    this.callback = paramCallback;
  }
  
  void cancel()
  {
    super.cancel();
    this.callback = null;
  }
  
  void complete(Bitmap paramBitmap, Picasso.LoadedFrom paramLoadedFrom)
  {
    if (this.callback != null) {
      this.callback.onSuccess();
    }
  }
  
  void error()
  {
    if (this.callback != null) {
      this.callback.onError();
    }
  }
  
  Object getTarget()
  {
    return this.target;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\squareup\picasso\FetchAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */