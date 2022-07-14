package com.squareup.picasso;

import android.content.Context;
import android.net.Uri;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.CacheControl.Builder;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkHttpDownloader
  implements Downloader
{
  private final OkHttpClient client;
  
  public OkHttpDownloader(Context paramContext)
  {
    this(Utils.createDefaultCacheDir(paramContext));
  }
  
  public OkHttpDownloader(Context paramContext, long paramLong)
  {
    this(Utils.createDefaultCacheDir(paramContext), paramLong);
  }
  
  public OkHttpDownloader(OkHttpClient paramOkHttpClient)
  {
    this.client = paramOkHttpClient;
  }
  
  public OkHttpDownloader(File paramFile)
  {
    this(paramFile, Utils.calculateDiskCacheSize(paramFile));
  }
  
  public OkHttpDownloader(File paramFile, long paramLong)
  {
    this(defaultOkHttpClient());
    try
    {
      this.client.setCache(new Cache(paramFile, paramLong));
      return;
    }
    catch (IOException paramFile) {}
  }
  
  private static OkHttpClient defaultOkHttpClient()
  {
    OkHttpClient localOkHttpClient = new OkHttpClient();
    localOkHttpClient.setConnectTimeout(15000L, TimeUnit.MILLISECONDS);
    localOkHttpClient.setReadTimeout(20000L, TimeUnit.MILLISECONDS);
    localOkHttpClient.setWriteTimeout(20000L, TimeUnit.MILLISECONDS);
    return localOkHttpClient;
  }
  
  protected final OkHttpClient getClient()
  {
    return this.client;
  }
  
  public Downloader.Response load(Uri paramUri, int paramInt)
    throws IOException
  {
    Object localObject = null;
    if (paramInt != 0) {
      if (!NetworkPolicy.isOfflineOnly(paramInt)) {
        break label116;
      }
    }
    for (localObject = CacheControl.FORCE_CACHE;; localObject = ((CacheControl.Builder)localObject).build())
    {
      paramUri = new Request.Builder().url(paramUri.toString());
      if (localObject != null) {
        paramUri.cacheControl((CacheControl)localObject);
      }
      paramUri = this.client.newCall(paramUri.build()).execute();
      int i = paramUri.code();
      if (i < 300) {
        break;
      }
      paramUri.body().close();
      throw new Downloader.ResponseException(i + " " + paramUri.message(), paramInt, i);
      label116:
      localObject = new CacheControl.Builder();
      if (!NetworkPolicy.shouldReadFromDiskCache(paramInt)) {
        ((CacheControl.Builder)localObject).noCache();
      }
      if (!NetworkPolicy.shouldWriteToDiskCache(paramInt)) {
        ((CacheControl.Builder)localObject).noStore();
      }
    }
    if (paramUri.cacheResponse() != null) {}
    for (boolean bool = true;; bool = false)
    {
      paramUri = paramUri.body();
      return new Downloader.Response(paramUri.byteStream(), bool, paramUri.contentLength());
    }
  }
  
  public void shutdown()
  {
    Cache localCache = this.client.getCache();
    if (localCache != null) {}
    try
    {
      localCache.close();
      return;
    }
    catch (IOException localIOException) {}
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\squareup\picasso\OkHttpDownloader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */