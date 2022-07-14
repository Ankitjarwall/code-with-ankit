package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.internal.zzacb;
import com.google.android.gms.internal.zzacc;
import com.google.android.gms.internal.zzacd;
import java.lang.ref.WeakReference;

public abstract class zza
{
  private boolean zzaEA = true;
  final zza zzaEu;
  protected int zzaEv = 0;
  protected int zzaEw = 0;
  protected boolean zzaEx = false;
  private boolean zzaEy = true;
  private boolean zzaEz = false;
  
  public zza(Uri paramUri, int paramInt)
  {
    this.zzaEu = new zza(paramUri);
    this.zzaEw = paramInt;
  }
  
  private Drawable zza(Context paramContext, zzacd paramzzacd, int paramInt)
  {
    return paramContext.getResources().getDrawable(paramInt);
  }
  
  protected zzacb zza(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    if (paramDrawable1 != null)
    {
      localDrawable = paramDrawable1;
      if (!(paramDrawable1 instanceof zzacb)) {}
    }
    for (Drawable localDrawable = ((zzacb)paramDrawable1).zzxs();; localDrawable = null) {
      return new zzacb(localDrawable, paramDrawable2);
    }
  }
  
  void zza(Context paramContext, Bitmap paramBitmap, boolean paramBoolean)
  {
    zzc.zzt(paramBitmap);
    zza(new BitmapDrawable(paramContext.getResources(), paramBitmap), paramBoolean, false, true);
  }
  
  void zza(Context paramContext, zzacd paramzzacd)
  {
    if (this.zzaEA) {
      zza(null, false, true, false);
    }
  }
  
  void zza(Context paramContext, zzacd paramzzacd, boolean paramBoolean)
  {
    Drawable localDrawable = null;
    if (this.zzaEw != 0) {
      localDrawable = zza(paramContext, paramzzacd, this.zzaEw);
    }
    zza(localDrawable, paramBoolean, false, false);
  }
  
  protected abstract void zza(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3);
  
  protected boolean zzc(boolean paramBoolean1, boolean paramBoolean2)
  {
    return (this.zzaEy) && (!paramBoolean2) && (!paramBoolean1);
  }
  
  public void zzcO(int paramInt)
  {
    this.zzaEw = paramInt;
  }
  
  static final class zza
  {
    public final Uri uri;
    
    public zza(Uri paramUri)
    {
      this.uri = paramUri;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof zza)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      return zzaa.equal(((zza)paramObject).uri, this.uri);
    }
    
    public int hashCode()
    {
      return zzaa.hashCode(new Object[] { this.uri });
    }
  }
  
  public static final class zzb
    extends zza
  {
    private WeakReference<ImageView> zzaEB;
    
    public zzb(ImageView paramImageView, int paramInt)
    {
      super(paramInt);
      zzc.zzt(paramImageView);
      this.zzaEB = new WeakReference(paramImageView);
    }
    
    public zzb(ImageView paramImageView, Uri paramUri)
    {
      super(0);
      zzc.zzt(paramImageView);
      this.zzaEB = new WeakReference(paramImageView);
    }
    
    private void zza(ImageView paramImageView, Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    {
      if ((!paramBoolean2) && (!paramBoolean3)) {}
      for (int i = 1; (i != 0) && ((paramImageView instanceof zzacc)); i = 0)
      {
        int j = ((zzacc)paramImageView).zzxu();
        if ((this.zzaEw == 0) || (j != this.zzaEw)) {
          break;
        }
        return;
      }
      paramBoolean1 = zzc(paramBoolean1, paramBoolean2);
      if (paramBoolean1) {
        paramDrawable = zza(paramImageView.getDrawable(), paramDrawable);
      }
      for (;;)
      {
        paramImageView.setImageDrawable(paramDrawable);
        zzacc localzzacc;
        if ((paramImageView instanceof zzacc))
        {
          localzzacc = (zzacc)paramImageView;
          if (!paramBoolean3) {
            break label149;
          }
          paramImageView = this.zzaEu.uri;
          label110:
          localzzacc.zzr(paramImageView);
          if (i == 0) {
            break label154;
          }
        }
        label149:
        label154:
        for (i = this.zzaEw;; i = 0)
        {
          localzzacc.zzcQ(i);
          if (!paramBoolean1) {
            break;
          }
          ((zzacb)paramDrawable).startTransition(250);
          return;
          paramImageView = null;
          break label110;
        }
      }
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof zzb)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      Object localObject = (zzb)paramObject;
      paramObject = (ImageView)this.zzaEB.get();
      localObject = (ImageView)((zzb)localObject).zzaEB.get();
      if ((localObject != null) && (paramObject != null) && (zzaa.equal(localObject, paramObject))) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public int hashCode()
    {
      return 0;
    }
    
    protected void zza(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    {
      ImageView localImageView = (ImageView)this.zzaEB.get();
      if (localImageView != null) {
        zza(localImageView, paramDrawable, paramBoolean1, paramBoolean2, paramBoolean3);
      }
    }
  }
  
  public static final class zzc
    extends zza
  {
    private WeakReference<ImageManager.OnImageLoadedListener> zzaEC;
    
    public zzc(ImageManager.OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri)
    {
      super(0);
      zzc.zzt(paramOnImageLoadedListener);
      this.zzaEC = new WeakReference(paramOnImageLoadedListener);
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof zzc)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      paramObject = (zzc)paramObject;
      ImageManager.OnImageLoadedListener localOnImageLoadedListener1 = (ImageManager.OnImageLoadedListener)this.zzaEC.get();
      ImageManager.OnImageLoadedListener localOnImageLoadedListener2 = (ImageManager.OnImageLoadedListener)((zzc)paramObject).zzaEC.get();
      if ((localOnImageLoadedListener2 != null) && (localOnImageLoadedListener1 != null) && (zzaa.equal(localOnImageLoadedListener2, localOnImageLoadedListener1)) && (zzaa.equal(((zzc)paramObject).zzaEu, this.zzaEu))) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public int hashCode()
    {
      return zzaa.hashCode(new Object[] { this.zzaEu });
    }
    
    protected void zza(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    {
      if (!paramBoolean2)
      {
        ImageManager.OnImageLoadedListener localOnImageLoadedListener = (ImageManager.OnImageLoadedListener)this.zzaEC.get();
        if (localOnImageLoadedListener != null) {
          localOnImageLoadedListener.onImageLoaded(this.zzaEu.uri, paramDrawable, paramBoolean3);
        }
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\images\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */