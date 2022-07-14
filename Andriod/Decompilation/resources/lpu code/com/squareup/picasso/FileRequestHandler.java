package com.squareup.picasso;

import android.content.Context;
import android.media.ExifInterface;
import android.net.Uri;
import java.io.IOException;

class FileRequestHandler
  extends ContentStreamRequestHandler
{
  FileRequestHandler(Context paramContext)
  {
    super(paramContext);
  }
  
  static int getFileExifRotation(Uri paramUri)
    throws IOException
  {
    switch (new ExifInterface(paramUri.getPath()).getAttributeInt("Orientation", 1))
    {
    case 4: 
    case 5: 
    case 7: 
    default: 
      return 0;
    case 6: 
      return 90;
    case 3: 
      return 180;
    }
    return 270;
  }
  
  public boolean canHandleRequest(Request paramRequest)
  {
    return "file".equals(paramRequest.uri.getScheme());
  }
  
  public RequestHandler.Result load(Request paramRequest, int paramInt)
    throws IOException
  {
    return new RequestHandler.Result(null, getInputStream(paramRequest), Picasso.LoadedFrom.DISK, getFileExifRotation(paramRequest.uri));
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\squareup\picasso\FileRequestHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */