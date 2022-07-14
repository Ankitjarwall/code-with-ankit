package com.squareup.picasso;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import java.io.IOException;

class ResourceRequestHandler
  extends RequestHandler
{
  private final Context context;
  
  ResourceRequestHandler(Context paramContext)
  {
    this.context = paramContext;
  }
  
  private static Bitmap decodeResource(Resources paramResources, int paramInt, Request paramRequest)
  {
    BitmapFactory.Options localOptions = createBitmapOptions(paramRequest);
    if (requiresInSampleSize(localOptions))
    {
      BitmapFactory.decodeResource(paramResources, paramInt, localOptions);
      calculateInSampleSize(paramRequest.targetWidth, paramRequest.targetHeight, localOptions, paramRequest);
    }
    return BitmapFactory.decodeResource(paramResources, paramInt, localOptions);
  }
  
  public boolean canHandleRequest(Request paramRequest)
  {
    if (paramRequest.resourceId != 0) {
      return true;
    }
    return "android.resource".equals(paramRequest.uri.getScheme());
  }
  
  public RequestHandler.Result load(Request paramRequest, int paramInt)
    throws IOException
  {
    Resources localResources = Utils.getResources(this.context, paramRequest);
    return new RequestHandler.Result(decodeResource(localResources, Utils.getResourceId(localResources, paramRequest), paramRequest), Picasso.LoadedFrom.DISK);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\squareup\picasso\ResourceRequestHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */