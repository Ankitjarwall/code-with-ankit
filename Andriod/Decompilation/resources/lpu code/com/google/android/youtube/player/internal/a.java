package com.google.android.youtube.player.internal;

import android.graphics.Bitmap;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailLoader.ErrorReason;
import com.google.android.youtube.player.YouTubeThumbnailLoader.OnThumbnailLoadedListener;
import com.google.android.youtube.player.YouTubeThumbnailView;
import java.lang.ref.WeakReference;
import java.util.NoSuchElementException;

public abstract class a
  implements YouTubeThumbnailLoader
{
  private final WeakReference<YouTubeThumbnailView> a;
  private YouTubeThumbnailLoader.OnThumbnailLoadedListener b;
  private boolean c;
  private boolean d;
  
  public a(YouTubeThumbnailView paramYouTubeThumbnailView)
  {
    this.a = new WeakReference(ab.a(paramYouTubeThumbnailView));
  }
  
  private void i()
  {
    if (!a()) {
      throw new IllegalStateException("This YouTubeThumbnailLoader has been released");
    }
  }
  
  public final void a(Bitmap paramBitmap, String paramString)
  {
    YouTubeThumbnailView localYouTubeThumbnailView = (YouTubeThumbnailView)this.a.get();
    if ((a()) && (localYouTubeThumbnailView != null))
    {
      localYouTubeThumbnailView.setImageBitmap(paramBitmap);
      if (this.b != null) {
        this.b.onThumbnailLoaded(localYouTubeThumbnailView, paramString);
      }
    }
  }
  
  public abstract void a(String paramString);
  
  public abstract void a(String paramString, int paramInt);
  
  protected boolean a()
  {
    return !this.d;
  }
  
  public final void b()
  {
    if (a())
    {
      y.a("The finalize() method for a YouTubeThumbnailLoader has work to do. You should have called release().", new Object[0]);
      release();
    }
  }
  
  public final void b(String paramString)
  {
    YouTubeThumbnailView localYouTubeThumbnailView = (YouTubeThumbnailView)this.a.get();
    if ((a()) && (this.b != null) && (localYouTubeThumbnailView != null)) {}
    try
    {
      paramString = YouTubeThumbnailLoader.ErrorReason.valueOf(paramString);
      this.b.onThumbnailError(localYouTubeThumbnailView, paramString);
      return;
    }
    catch (IllegalArgumentException paramString)
    {
      for (;;)
      {
        paramString = YouTubeThumbnailLoader.ErrorReason.UNKNOWN;
      }
    }
    catch (NullPointerException paramString)
    {
      for (;;)
      {
        paramString = YouTubeThumbnailLoader.ErrorReason.UNKNOWN;
      }
    }
  }
  
  public abstract void c();
  
  public abstract void d();
  
  public abstract void e();
  
  public abstract boolean f();
  
  public final void first()
  {
    i();
    if (!this.c) {
      throw new IllegalStateException("Must call setPlaylist first");
    }
    e();
  }
  
  public abstract boolean g();
  
  public abstract void h();
  
  public final boolean hasNext()
  {
    i();
    return f();
  }
  
  public final boolean hasPrevious()
  {
    i();
    return g();
  }
  
  public final void next()
  {
    i();
    if (!this.c) {
      throw new IllegalStateException("Must call setPlaylist first");
    }
    if (!f()) {
      throw new NoSuchElementException("Called next at end of playlist");
    }
    c();
  }
  
  public final void previous()
  {
    i();
    if (!this.c) {
      throw new IllegalStateException("Must call setPlaylist first");
    }
    if (!g()) {
      throw new NoSuchElementException("Called previous at start of playlist");
    }
    d();
  }
  
  public final void release()
  {
    if (a())
    {
      this.d = true;
      this.b = null;
      h();
    }
  }
  
  public final void setOnThumbnailLoadedListener(YouTubeThumbnailLoader.OnThumbnailLoadedListener paramOnThumbnailLoadedListener)
  {
    i();
    this.b = paramOnThumbnailLoadedListener;
  }
  
  public final void setPlaylist(String paramString)
  {
    setPlaylist(paramString, 0);
  }
  
  public final void setPlaylist(String paramString, int paramInt)
  {
    i();
    this.c = true;
    a(paramString, paramInt);
  }
  
  public final void setVideo(String paramString)
  {
    i();
    this.c = false;
    a(paramString);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\youtube\player\internal\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */