package com.google.android.youtube.player;

public abstract interface YouTubeThumbnailLoader
{
  public abstract void first();
  
  public abstract boolean hasNext();
  
  public abstract boolean hasPrevious();
  
  public abstract void next();
  
  public abstract void previous();
  
  public abstract void release();
  
  public abstract void setOnThumbnailLoadedListener(OnThumbnailLoadedListener paramOnThumbnailLoadedListener);
  
  public abstract void setPlaylist(String paramString);
  
  public abstract void setPlaylist(String paramString, int paramInt);
  
  public abstract void setVideo(String paramString);
  
  public static enum ErrorReason
  {
    private ErrorReason() {}
  }
  
  public static abstract interface OnThumbnailLoadedListener
  {
    public abstract void onThumbnailError(YouTubeThumbnailView paramYouTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason paramErrorReason);
    
    public abstract void onThumbnailLoaded(YouTubeThumbnailView paramYouTubeThumbnailView, String paramString);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\youtube\player\YouTubeThumbnailLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */