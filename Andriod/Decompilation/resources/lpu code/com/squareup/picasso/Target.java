package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public abstract interface Target
{
  public abstract void onBitmapFailed(Drawable paramDrawable);
  
  public abstract void onBitmapLoaded(Bitmap paramBitmap, Picasso.LoadedFrom paramLoadedFrom);
  
  public abstract void onPrepareLoad(Drawable paramDrawable);
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\squareup\picasso\Target.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */