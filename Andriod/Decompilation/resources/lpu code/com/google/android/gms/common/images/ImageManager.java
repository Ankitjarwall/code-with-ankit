package com.google.android.gms.common.images;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.internal.zzacd;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager
{
  private static final Object zzaEf = new Object();
  private static HashSet<Uri> zzaEg = new HashSet();
  private static ImageManager zzaEh;
  private static ImageManager zzaEi;
  private final Context mContext;
  private final Handler mHandler;
  private final ExecutorService zzaEj;
  private final zza zzaEk;
  private final zzacd zzaEl;
  private final Map<zza, ImageReceiver> zzaEm;
  private final Map<Uri, ImageReceiver> zzaEn;
  private final Map<Uri, Long> zzaEo;
  
  private ImageManager(Context paramContext, boolean paramBoolean)
  {
    this.mContext = paramContext.getApplicationContext();
    this.mHandler = new Handler(Looper.getMainLooper());
    this.zzaEj = Executors.newFixedThreadPool(4);
    if (paramBoolean)
    {
      this.zzaEk = new zza(this.mContext);
      this.mContext.registerComponentCallbacks(new zzd(this.zzaEk));
    }
    for (;;)
    {
      this.zzaEl = new zzacd();
      this.zzaEm = new HashMap();
      this.zzaEn = new HashMap();
      this.zzaEo = new HashMap();
      return;
      this.zzaEk = null;
    }
  }
  
  public static ImageManager create(Context paramContext)
  {
    return zzg(paramContext, false);
  }
  
  private Bitmap zza(zza.zza paramzza)
  {
    if (this.zzaEk == null) {
      return null;
    }
    return (Bitmap)this.zzaEk.get(paramzza);
  }
  
  public static ImageManager zzg(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (zzaEi == null) {
        zzaEi = new ImageManager(paramContext, true);
      }
      return zzaEi;
    }
    if (zzaEh == null) {
      zzaEh = new ImageManager(paramContext, false);
    }
    return zzaEh;
  }
  
  public void loadImage(ImageView paramImageView, int paramInt)
  {
    zza(new zza.zzb(paramImageView, paramInt));
  }
  
  public void loadImage(ImageView paramImageView, Uri paramUri)
  {
    zza(new zza.zzb(paramImageView, paramUri));
  }
  
  public void loadImage(ImageView paramImageView, Uri paramUri, int paramInt)
  {
    paramImageView = new zza.zzb(paramImageView, paramUri);
    paramImageView.zzcO(paramInt);
    zza(paramImageView);
  }
  
  public void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri)
  {
    zza(new zza.zzc(paramOnImageLoadedListener, paramUri));
  }
  
  public void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri, int paramInt)
  {
    paramOnImageLoadedListener = new zza.zzc(paramOnImageLoadedListener, paramUri);
    paramOnImageLoadedListener.zzcO(paramInt);
    zza(paramOnImageLoadedListener);
  }
  
  public void zza(zza paramzza)
  {
    zzc.zzdj("ImageManager.loadImage() must be called in the main thread");
    new zzc(paramzza).run();
  }
  
  @KeepName
  private final class ImageReceiver
    extends ResultReceiver
  {
    private final Uri mUri;
    private final ArrayList<zza> zzaEp;
    
    ImageReceiver(Uri paramUri)
    {
      super();
      this.mUri = paramUri;
      this.zzaEp = new ArrayList();
    }
    
    public void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      paramBundle = (ParcelFileDescriptor)paramBundle.getParcelable("com.google.android.gms.extra.fileDescriptor");
      ImageManager.zzf(ImageManager.this).execute(new ImageManager.zzb(ImageManager.this, this.mUri, paramBundle));
    }
    
    public void zzb(zza paramzza)
    {
      zzc.zzdj("ImageReceiver.addImageRequest() must be called in the main thread");
      this.zzaEp.add(paramzza);
    }
    
    public void zzc(zza paramzza)
    {
      zzc.zzdj("ImageReceiver.removeImageRequest() must be called in the main thread");
      this.zzaEp.remove(paramzza);
    }
    
    public void zzxr()
    {
      Intent localIntent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
      localIntent.putExtra("com.google.android.gms.extras.uri", this.mUri);
      localIntent.putExtra("com.google.android.gms.extras.resultReceiver", this);
      localIntent.putExtra("com.google.android.gms.extras.priority", 3);
      ImageManager.zzb(ImageManager.this).sendBroadcast(localIntent);
    }
  }
  
  public static abstract interface OnImageLoadedListener
  {
    public abstract void onImageLoaded(Uri paramUri, Drawable paramDrawable, boolean paramBoolean);
  }
  
  private static final class zza
    extends LruCache<zza.zza, Bitmap>
  {
    public zza(Context paramContext)
    {
      super();
    }
    
    private static int zzaR(Context paramContext)
    {
      ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
      if ((paramContext.getApplicationInfo().flags & 0x100000) != 0)
      {
        i = 1;
        if (i == 0) {
          break label49;
        }
      }
      label49:
      for (int i = localActivityManager.getLargeMemoryClass();; i = localActivityManager.getMemoryClass())
      {
        return (int)(i * 1048576 * 0.33F);
        i = 0;
        break;
      }
    }
    
    protected int zza(zza.zza paramzza, Bitmap paramBitmap)
    {
      return paramBitmap.getHeight() * paramBitmap.getRowBytes();
    }
    
    protected void zza(boolean paramBoolean, zza.zza paramzza, Bitmap paramBitmap1, Bitmap paramBitmap2)
    {
      super.entryRemoved(paramBoolean, paramzza, paramBitmap1, paramBitmap2);
    }
  }
  
  private final class zzb
    implements Runnable
  {
    private final Uri mUri;
    private final ParcelFileDescriptor zzaEr;
    
    public zzb(Uri paramUri, ParcelFileDescriptor paramParcelFileDescriptor)
    {
      this.mUri = paramUri;
      this.zzaEr = paramParcelFileDescriptor;
    }
    
    public void run()
    {
      zzc.zzdk("LoadBitmapFromDiskRunnable can't be executed in the main thread");
      boolean bool1 = false;
      boolean bool2 = false;
      Bitmap localBitmap = null;
      CountDownLatch localCountDownLatch = null;
      if (this.zzaEr != null) {}
      try
      {
        localBitmap = BitmapFactory.decodeFileDescriptor(this.zzaEr.getFileDescriptor());
        bool1 = bool2;
        String str2;
        Object localObject;
        String str1;
        return;
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        try
        {
          for (;;)
          {
            this.zzaEr.close();
            localCountDownLatch = new CountDownLatch(1);
            ImageManager.zzg(ImageManager.this).post(new ImageManager.zze(ImageManager.this, this.mUri, localBitmap, bool1, localCountDownLatch));
            try
            {
              localCountDownLatch.await();
              return;
            }
            catch (InterruptedException localInterruptedException)
            {
              str1 = String.valueOf(this.mUri);
              Log.w("ImageManager", String.valueOf(str1).length() + 32 + "Latch interrupted while posting " + str1);
            }
            localOutOfMemoryError = localOutOfMemoryError;
            str2 = String.valueOf(this.mUri);
            Log.e("ImageManager", String.valueOf(str2).length() + 34 + "OOM while loading bitmap for uri: " + str2, localOutOfMemoryError);
            bool1 = true;
            localObject = localCountDownLatch;
          }
        }
        catch (IOException localIOException)
        {
          for (;;)
          {
            Log.e("ImageManager", "closed failed", localIOException);
          }
        }
      }
    }
  }
  
  private final class zzc
    implements Runnable
  {
    private final zza zzaEs;
    
    public zzc(zza paramzza)
    {
      this.zzaEs = paramzza;
    }
    
    public void run()
    {
      zzc.zzdj("LoadImageRunnable must be executed on the main thread");
      Object localObject1 = (ImageManager.ImageReceiver)ImageManager.zza(ImageManager.this).get(this.zzaEs);
      if (localObject1 != null)
      {
        ImageManager.zza(ImageManager.this).remove(this.zzaEs);
        ((ImageManager.ImageReceiver)localObject1).zzc(this.zzaEs);
      }
      zza.zza localzza = this.zzaEs.zzaEu;
      if (localzza.uri == null)
      {
        this.zzaEs.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this), true);
        return;
      }
      localObject1 = ImageManager.zza(ImageManager.this, localzza);
      if (localObject1 != null)
      {
        this.zzaEs.zza(ImageManager.zzb(ImageManager.this), (Bitmap)localObject1, true);
        return;
      }
      localObject1 = (Long)ImageManager.zzd(ImageManager.this).get(localzza.uri);
      if (localObject1 != null)
      {
        if (SystemClock.elapsedRealtime() - ((Long)localObject1).longValue() < 3600000L)
        {
          this.zzaEs.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this), true);
          return;
        }
        ImageManager.zzd(ImageManager.this).remove(localzza.uri);
      }
      this.zzaEs.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this));
      ??? = (ImageManager.ImageReceiver)ImageManager.zze(ImageManager.this).get(localzza.uri);
      localObject1 = ???;
      if (??? == null)
      {
        localObject1 = new ImageManager.ImageReceiver(ImageManager.this, localzza.uri);
        ImageManager.zze(ImageManager.this).put(localzza.uri, localObject1);
      }
      ((ImageManager.ImageReceiver)localObject1).zzb(this.zzaEs);
      if (!(this.zzaEs instanceof zza.zzc)) {
        ImageManager.zza(ImageManager.this).put(this.zzaEs, localObject1);
      }
      synchronized (ImageManager.zzuG())
      {
        if (!ImageManager.zzxq().contains(localzza.uri))
        {
          ImageManager.zzxq().add(localzza.uri);
          ((ImageManager.ImageReceiver)localObject1).zzxr();
        }
        return;
      }
    }
  }
  
  private static final class zzd
    implements ComponentCallbacks2
  {
    private final ImageManager.zza zzaEk;
    
    public zzd(ImageManager.zza paramzza)
    {
      this.zzaEk = paramzza;
    }
    
    public void onConfigurationChanged(Configuration paramConfiguration) {}
    
    public void onLowMemory()
    {
      this.zzaEk.evictAll();
    }
    
    public void onTrimMemory(int paramInt)
    {
      if (paramInt >= 60) {
        this.zzaEk.evictAll();
      }
      while (paramInt < 20) {
        return;
      }
      this.zzaEk.trimToSize(this.zzaEk.size() / 2);
    }
  }
  
  private final class zze
    implements Runnable
  {
    private final Bitmap mBitmap;
    private final Uri mUri;
    private boolean zzaEt;
    private final CountDownLatch zztj;
    
    public zze(Uri paramUri, Bitmap paramBitmap, boolean paramBoolean, CountDownLatch paramCountDownLatch)
    {
      this.mUri = paramUri;
      this.mBitmap = paramBitmap;
      this.zzaEt = paramBoolean;
      this.zztj = paramCountDownLatch;
    }
    
    private void zza(ImageManager.ImageReceiver paramImageReceiver, boolean paramBoolean)
    {
      paramImageReceiver = ImageManager.ImageReceiver.zza(paramImageReceiver);
      int j = paramImageReceiver.size();
      int i = 0;
      if (i < j)
      {
        zza localzza = (zza)paramImageReceiver.get(i);
        if (paramBoolean) {
          localzza.zza(ImageManager.zzb(ImageManager.this), this.mBitmap, false);
        }
        for (;;)
        {
          if (!(localzza instanceof zza.zzc)) {
            ImageManager.zza(ImageManager.this).remove(localzza);
          }
          i += 1;
          break;
          ImageManager.zzd(ImageManager.this).put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
          localzza.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this), false);
        }
      }
    }
    
    public void run()
    {
      zzc.zzdj("OnBitmapLoadedRunnable must be executed in the main thread");
      boolean bool;
      if (this.mBitmap != null) {
        bool = true;
      }
      while (ImageManager.zzh(ImageManager.this) != null) {
        if (this.zzaEt)
        {
          ImageManager.zzh(ImageManager.this).evictAll();
          System.gc();
          this.zzaEt = false;
          ImageManager.zzg(ImageManager.this).post(this);
          return;
          bool = false;
        }
        else if (bool)
        {
          ImageManager.zzh(ImageManager.this).put(new zza.zza(this.mUri), this.mBitmap);
        }
      }
      ??? = (ImageManager.ImageReceiver)ImageManager.zze(ImageManager.this).remove(this.mUri);
      if (??? != null) {
        zza((ImageManager.ImageReceiver)???, bool);
      }
      this.zztj.countDown();
      synchronized (ImageManager.zzuG())
      {
        ImageManager.zzxq().remove(this.mUri);
        return;
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\images\ImageManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */