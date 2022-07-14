package com.google.android.youtube.player;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.google.android.youtube.player.internal.a;
import com.google.android.youtube.player.internal.aa;
import com.google.android.youtube.player.internal.ab;
import com.google.android.youtube.player.internal.b;
import com.google.android.youtube.player.internal.t.a;
import com.google.android.youtube.player.internal.t.b;

public final class YouTubeThumbnailView
  extends ImageView
{
  private b a;
  private a b;
  
  public YouTubeThumbnailView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public YouTubeThumbnailView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public YouTubeThumbnailView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected final void finalize()
    throws Throwable
  {
    if (this.b != null)
    {
      this.b.b();
      this.b = null;
    }
    super.finalize();
  }
  
  public final void initialize(String paramString, OnInitializedListener paramOnInitializedListener)
  {
    paramOnInitializedListener = new a(this, paramOnInitializedListener);
    this.a = aa.a().a(getContext(), paramString, paramOnInitializedListener, paramOnInitializedListener);
    this.a.e();
  }
  
  public static abstract interface OnInitializedListener
  {
    public abstract void onInitializationFailure(YouTubeThumbnailView paramYouTubeThumbnailView, YouTubeInitializationResult paramYouTubeInitializationResult);
    
    public abstract void onInitializationSuccess(YouTubeThumbnailView paramYouTubeThumbnailView, YouTubeThumbnailLoader paramYouTubeThumbnailLoader);
  }
  
  private static final class a
    implements t.a, t.b
  {
    private YouTubeThumbnailView a;
    private YouTubeThumbnailView.OnInitializedListener b;
    
    public a(YouTubeThumbnailView paramYouTubeThumbnailView, YouTubeThumbnailView.OnInitializedListener paramOnInitializedListener)
    {
      this.a = ((YouTubeThumbnailView)ab.a(paramYouTubeThumbnailView, "thumbnailView cannot be null"));
      this.b = ((YouTubeThumbnailView.OnInitializedListener)ab.a(paramOnInitializedListener, "onInitializedlistener cannot be null"));
    }
    
    private void c()
    {
      if (this.a != null)
      {
        YouTubeThumbnailView.c(this.a);
        this.a = null;
        this.b = null;
      }
    }
    
    public final void a()
    {
      if ((this.a != null) && (YouTubeThumbnailView.a(this.a) != null))
      {
        YouTubeThumbnailView.a(this.a, aa.a().a(YouTubeThumbnailView.a(this.a), this.a));
        this.b.onInitializationSuccess(this.a, YouTubeThumbnailView.b(this.a));
        c();
      }
    }
    
    public final void a(YouTubeInitializationResult paramYouTubeInitializationResult)
    {
      this.b.onInitializationFailure(this.a, paramYouTubeInitializationResult);
      c();
    }
    
    public final void b()
    {
      c();
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\youtube\player\YouTubeThumbnailView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */