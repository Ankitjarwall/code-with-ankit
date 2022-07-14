package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.net.NetworkInfo;
import java.io.IOException;
import java.io.InputStream;

public abstract class RequestHandler
{
  static void calculateInSampleSize(int paramInt1, int paramInt2, int paramInt3, int paramInt4, BitmapFactory.Options paramOptions, Request paramRequest)
  {
    int i = 1;
    if ((paramInt4 > paramInt2) || (paramInt3 > paramInt1)) {
      if (paramInt2 != 0) {
        break label43;
      }
    }
    for (i = (int)Math.floor(paramInt3 / paramInt1);; i = (int)Math.floor(paramInt4 / paramInt2))
    {
      paramOptions.inSampleSize = i;
      paramOptions.inJustDecodeBounds = false;
      return;
      label43:
      if (paramInt1 != 0) {
        break;
      }
    }
    paramInt2 = (int)Math.floor(paramInt4 / paramInt2);
    paramInt1 = (int)Math.floor(paramInt3 / paramInt1);
    if (paramRequest.centerInside) {}
    for (i = Math.max(paramInt2, paramInt1);; i = Math.min(paramInt2, paramInt1)) {
      break;
    }
  }
  
  static void calculateInSampleSize(int paramInt1, int paramInt2, BitmapFactory.Options paramOptions, Request paramRequest)
  {
    calculateInSampleSize(paramInt1, paramInt2, paramOptions.outWidth, paramOptions.outHeight, paramOptions, paramRequest);
  }
  
  static BitmapFactory.Options createBitmapOptions(Request paramRequest)
  {
    boolean bool = paramRequest.hasSize();
    if (paramRequest.config != null) {}
    for (int i = 1;; i = 0)
    {
      Object localObject = null;
      if ((bool) || (i != 0))
      {
        BitmapFactory.Options localOptions = new BitmapFactory.Options();
        localOptions.inJustDecodeBounds = bool;
        localObject = localOptions;
        if (i != 0)
        {
          localOptions.inPreferredConfig = paramRequest.config;
          localObject = localOptions;
        }
      }
      return (BitmapFactory.Options)localObject;
    }
  }
  
  static boolean requiresInSampleSize(BitmapFactory.Options paramOptions)
  {
    return (paramOptions != null) && (paramOptions.inJustDecodeBounds);
  }
  
  public abstract boolean canHandleRequest(Request paramRequest);
  
  int getRetryCount()
  {
    return 0;
  }
  
  public abstract Result load(Request paramRequest, int paramInt)
    throws IOException;
  
  boolean shouldRetry(boolean paramBoolean, NetworkInfo paramNetworkInfo)
  {
    return false;
  }
  
  boolean supportsReplay()
  {
    return false;
  }
  
  public static final class Result
  {
    private final Bitmap bitmap;
    private final int exifOrientation;
    private final Picasso.LoadedFrom loadedFrom;
    private final InputStream stream;
    
    public Result(Bitmap paramBitmap, Picasso.LoadedFrom paramLoadedFrom)
    {
      this((Bitmap)Utils.checkNotNull(paramBitmap, "bitmap == null"), null, paramLoadedFrom, 0);
    }
    
    Result(Bitmap paramBitmap, InputStream paramInputStream, Picasso.LoadedFrom paramLoadedFrom, int paramInt)
    {
      int i;
      if (paramBitmap != null)
      {
        i = 1;
        if (paramInputStream == null) {
          break label40;
        }
      }
      for (;;)
      {
        if ((j ^ i) != 0) {
          break label46;
        }
        throw new AssertionError();
        i = 0;
        break;
        label40:
        j = 0;
      }
      label46:
      this.bitmap = paramBitmap;
      this.stream = paramInputStream;
      this.loadedFrom = ((Picasso.LoadedFrom)Utils.checkNotNull(paramLoadedFrom, "loadedFrom == null"));
      this.exifOrientation = paramInt;
    }
    
    public Result(InputStream paramInputStream, Picasso.LoadedFrom paramLoadedFrom)
    {
      this(null, (InputStream)Utils.checkNotNull(paramInputStream, "stream == null"), paramLoadedFrom, 0);
    }
    
    public Bitmap getBitmap()
    {
      return this.bitmap;
    }
    
    int getExifOrientation()
    {
      return this.exifOrientation;
    }
    
    public Picasso.LoadedFrom getLoadedFrom()
    {
      return this.loadedFrom;
    }
    
    public InputStream getStream()
    {
      return this.stream;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\squareup\picasso\RequestHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */