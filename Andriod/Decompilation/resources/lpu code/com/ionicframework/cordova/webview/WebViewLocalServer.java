package com.ionicframework.cordova.webview;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.WebResourceResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.cordova.ConfigXmlParser;

public class WebViewLocalServer
{
  private static String TAG = "WebViewAssetServer";
  private static final String httpScheme = "http";
  private static final String httpsScheme = "https";
  public static final String knownUnusedAuthority = "capacitorapp.net";
  private final String authority;
  private String basePath;
  private final boolean html5mode;
  private boolean isAsset;
  private final boolean isLocal;
  private ConfigXmlParser parser;
  private final AndroidProtocolHandler protocolHandler;
  private final UriMatcher uriMatcher = new UriMatcher(null);
  
  WebViewLocalServer(Context paramContext, String paramString, boolean paramBoolean, ConfigXmlParser paramConfigXmlParser)
  {
    this.html5mode = paramBoolean;
    this.parser = paramConfigXmlParser;
    this.protocolHandler = new AndroidProtocolHandler(paramContext.getApplicationContext());
    if (paramString != null)
    {
      this.authority = paramString;
      if (paramString.startsWith("localhost"))
      {
        this.isLocal = true;
        return;
      }
      this.isLocal = false;
      return;
    }
    this.isLocal = true;
    this.authority = (UUID.randomUUID().toString() + "" + "capacitorapp.net");
  }
  
  private static WebResourceResponse createWebResourceResponse(String paramString1, String paramString2, int paramInt, String paramString3, Map<String, String> paramMap, InputStream paramInputStream)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return new WebResourceResponse(paramString1, paramString2, paramInt, paramString3, paramMap, paramInputStream);
    }
    return new WebResourceResponse(paramString1, paramString2, paramInputStream);
  }
  
  private String getMimeType(String paramString, InputStream paramInputStream)
  {
    Object localObject = null;
    try
    {
      String str = URLConnection.guessContentTypeFromName(paramString);
      if (str != null)
      {
        localObject = str;
        if (paramString.endsWith(".js"))
        {
          localObject = str;
          if (str.equals("image/x-icon"))
          {
            localObject = str;
            Log.d("IonicWebViewEngine", "We shouldn't be here");
          }
        }
      }
      localObject = str;
      if (str == null)
      {
        localObject = str;
        if (paramString.endsWith(".js")) {
          return "application/javascript";
        }
        localObject = str;
        paramInputStream = URLConnection.guessContentTypeFromStream(paramInputStream);
        return paramInputStream;
      }
    }
    catch (Exception paramInputStream)
    {
      Log.e(TAG, "Unable to get mime type" + paramString, paramInputStream);
    }
    return (String)localObject;
  }
  
  private WebResourceResponse handleLocalRequest(Uri paramUri, PathHandler paramPathHandler)
  {
    String str = paramUri.getPath();
    if ((str.equals("/")) || ((!paramUri.getLastPathSegment().contains(".")) && (this.html5mode)))
    {
      paramUri = this.parser.getLaunchUrl();
      str = paramUri.substring(paramUri.lastIndexOf("/") + 1, paramUri.length());
      try
      {
        paramUri = this.basePath + "/" + str;
        if (this.isAsset) {}
        for (paramUri = this.protocolHandler.openAsset(paramUri, "");; paramUri = this.protocolHandler.openFile(paramUri)) {
          return createWebResourceResponse("text/html", paramPathHandler.getEncoding(), paramPathHandler.getStatusCode(), paramPathHandler.getReasonPhrase(), paramPathHandler.getResponseHeaders(), paramUri);
        }
        if (str.lastIndexOf(".") < 0) {
          break label229;
        }
      }
      catch (IOException paramUri)
      {
        Log.e(TAG, "Unable to open " + str, paramUri);
        return null;
      }
    }
    str.substring(str.lastIndexOf("."), str.length());
    paramUri = new LollipopLazyInputStream(paramPathHandler, paramUri);
    return createWebResourceResponse(getMimeType(str, paramUri), paramPathHandler.getEncoding(), paramPathHandler.getStatusCode(), paramPathHandler.getReasonPhrase(), paramPathHandler.getResponseHeaders(), paramUri);
    label229:
    return null;
  }
  
  private WebResourceResponse handleProxyRequest(Uri paramUri, PathHandler paramPathHandler)
  {
    try
    {
      String str = paramUri.getPath();
      Object localObject = (HttpURLConnection)new URL(paramUri.toString()).openConnection();
      ((HttpURLConnection)localObject).setRequestMethod("GET");
      ((HttpURLConnection)localObject).setReadTimeout(30000);
      ((HttpURLConnection)localObject).setConnectTimeout(30000);
      localObject = ((HttpURLConnection)localObject).getInputStream();
      if ((str.equals("/")) || ((!paramUri.getLastPathSegment().contains(".")) && (this.html5mode))) {
        return createWebResourceResponse("text/html", paramPathHandler.getEncoding(), paramPathHandler.getStatusCode(), paramPathHandler.getReasonPhrase(), paramPathHandler.getResponseHeaders(), (InputStream)localObject);
      }
      if (str.lastIndexOf(".") >= 0)
      {
        if (str.substring(str.lastIndexOf("."), str.length()).equals(".html")) {}
        return createWebResourceResponse(getMimeType(str, (InputStream)localObject), paramPathHandler.getEncoding(), paramPathHandler.getStatusCode(), paramPathHandler.getReasonPhrase(), paramPathHandler.getResponseHeaders(), (InputStream)localObject);
      }
      paramUri = createWebResourceResponse("", paramPathHandler.getEncoding(), paramPathHandler.getStatusCode(), paramPathHandler.getReasonPhrase(), paramPathHandler.getResponseHeaders(), (InputStream)localObject);
      return paramUri;
    }
    catch (Exception paramUri)
    {
      return null;
    }
    catch (SocketTimeoutException paramUri)
    {
      for (;;) {}
    }
  }
  
  private static Uri parseAndVerifyUrl(String paramString)
  {
    Uri localUri;
    if (paramString == null) {
      localUri = null;
    }
    String str;
    do
    {
      return localUri;
      localUri = Uri.parse(paramString);
      if (localUri == null)
      {
        Log.e(TAG, "Malformed URL: " + paramString);
        return null;
      }
      str = localUri.getPath();
    } while ((str != null) && (str.length() != 0));
    Log.e(TAG, "URL does not have a path: " + paramString);
    return null;
  }
  
  public String getAuthority()
  {
    return this.authority;
  }
  
  public String getBasePath()
  {
    return this.basePath;
  }
  
  public AssetHostingDetails hostAssets(String paramString)
  {
    return hostAssets(this.authority, paramString, "", true, true);
  }
  
  public AssetHostingDetails hostAssets(String paramString1, final String paramString2, final String paramString3, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.isAsset = true;
    this.basePath = paramString2;
    Uri.Builder localBuilder = new Uri.Builder();
    localBuilder.scheme("http");
    localBuilder.authority(paramString1);
    localBuilder.path(paramString3);
    if (paramString2.indexOf('*') != -1) {
      throw new IllegalArgumentException("assetPath cannot contain the '*' character.");
    }
    if (paramString3.indexOf('*') != -1) {
      throw new IllegalArgumentException("virtualAssetPath cannot contain the '*' character.");
    }
    paramString1 = null;
    Object localObject = null;
    paramString3 = new PathHandler()
    {
      public InputStream handle(Uri paramAnonymousUri)
      {
        Object localObject = paramAnonymousUri.getPath().replaceFirst(paramString3, paramString2);
        try
        {
          localObject = WebViewLocalServer.this.protocolHandler.openAsset((String)localObject, paramString2);
          return (InputStream)localObject;
        }
        catch (IOException localIOException)
        {
          Log.e(WebViewLocalServer.TAG, "Unable to open asset URL: " + paramAnonymousUri);
        }
        return null;
      }
    };
    if (paramBoolean1)
    {
      paramString1 = localBuilder.build();
      register(Uri.withAppendedPath(paramString1, "/"), paramString3);
      register(Uri.withAppendedPath(paramString1, "**"), paramString3);
    }
    paramString2 = (String)localObject;
    if (paramBoolean2)
    {
      localBuilder.scheme("https");
      paramString2 = localBuilder.build();
      register(Uri.withAppendedPath(paramString2, "/"), paramString3);
      register(Uri.withAppendedPath(paramString2, "**"), paramString3);
    }
    return new AssetHostingDetails(paramString1, paramString2);
  }
  
  public AssetHostingDetails hostAssets(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    return hostAssets(this.authority, paramString1, paramString2, paramBoolean1, paramBoolean2);
  }
  
  public AssetHostingDetails hostFiles(String paramString)
  {
    return hostFiles(paramString, true, true);
  }
  
  public AssetHostingDetails hostFiles(final String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.isAsset = false;
    this.basePath = paramString;
    Uri.Builder localBuilder = new Uri.Builder();
    localBuilder.scheme("http");
    localBuilder.authority(this.authority);
    localBuilder.path("");
    Object localObject = null;
    Uri localUri = null;
    PathHandler local3 = new PathHandler()
    {
      public InputStream handle(Uri paramAnonymousUri)
      {
        for (;;)
        {
          try
          {
            if (paramAnonymousUri.getPath().startsWith("/_file_/")) {
              localInputStream = WebViewLocalServer.this.protocolHandler.openFile(paramAnonymousUri.getPath().replace("/_file_/", ""));
            }
          }
          catch (IOException localIOException)
          {
            InputStream localInputStream;
            Log.e(WebViewLocalServer.TAG, "Unable to open asset URL: " + paramAnonymousUri);
            return null;
          }
          try
          {
            URLConnection.guessContentTypeFromStream(localInputStream);
            return localInputStream;
          }
          catch (Exception localException)
          {
            Log.e(WebViewLocalServer.TAG, "Unable to get mime type" + paramAnonymousUri);
          }
          localInputStream = WebViewLocalServer.this.protocolHandler.openFile(paramString + paramAnonymousUri.getPath());
        }
        return localIOException;
      }
    };
    paramString = (String)localObject;
    if (paramBoolean1)
    {
      paramString = localBuilder.build();
      register(Uri.withAppendedPath(paramString, "/"), local3);
      register(Uri.withAppendedPath(paramString, "**"), local3);
    }
    if (paramBoolean2)
    {
      localBuilder.scheme("https");
      localUri = localBuilder.build();
      register(Uri.withAppendedPath(localUri, "/"), local3);
      register(Uri.withAppendedPath(localUri, "**"), local3);
    }
    return new AssetHostingDetails(paramString, localUri);
  }
  
  public AssetHostingDetails hostResources()
  {
    return hostResources(this.authority, "/res", true, true);
  }
  
  public AssetHostingDetails hostResources(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramString2.indexOf('*') != -1) {
      throw new IllegalArgumentException("virtualResourcesPath cannot contain the '*' character.");
    }
    Uri.Builder localBuilder = new Uri.Builder();
    localBuilder.scheme("http");
    localBuilder.authority(paramString1);
    localBuilder.path(paramString2);
    paramString1 = null;
    paramString2 = null;
    PathHandler local2 = new PathHandler()
    {
      public InputStream handle(Uri paramAnonymousUri)
      {
        InputStream localInputStream = WebViewLocalServer.this.protocolHandler.openResource(paramAnonymousUri);
        try
        {
          URLConnection.guessContentTypeFromStream(localInputStream);
          return localInputStream;
        }
        catch (Exception localException)
        {
          Log.e(WebViewLocalServer.TAG, "Unable to get mime type" + paramAnonymousUri);
        }
        return localInputStream;
      }
    };
    if (paramBoolean1)
    {
      paramString1 = localBuilder.build();
      register(Uri.withAppendedPath(paramString1, "**"), local2);
    }
    if (paramBoolean2)
    {
      localBuilder.scheme("https");
      paramString2 = localBuilder.build();
      register(Uri.withAppendedPath(paramString2, "**"), local2);
    }
    return new AssetHostingDetails(paramString1, paramString2);
  }
  
  public AssetHostingDetails hostResources(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    return hostResources(this.authority, paramString, paramBoolean1, paramBoolean2);
  }
  
  void register(Uri paramUri, PathHandler paramPathHandler)
  {
    synchronized (this.uriMatcher)
    {
      this.uriMatcher.addURI(paramUri.getScheme(), paramUri.getAuthority(), paramUri.getPath(), paramPathHandler);
      return;
    }
  }
  
  public WebResourceResponse shouldInterceptRequest(Uri paramUri)
  {
    PathHandler localPathHandler;
    synchronized (this.uriMatcher)
    {
      localPathHandler = (PathHandler)this.uriMatcher.match(paramUri);
      if (localPathHandler == null) {
        return null;
      }
    }
    if (this.isLocal)
    {
      Log.d("SERVER", "Handling local request: " + paramUri.toString());
      return handleLocalRequest(paramUri, localPathHandler);
    }
    return handleProxyRequest(paramUri, localPathHandler);
  }
  
  public static class AssetHostingDetails
  {
    private Uri httpPrefix;
    private Uri httpsPrefix;
    
    AssetHostingDetails(Uri paramUri1, Uri paramUri2)
    {
      this.httpPrefix = paramUri1;
      this.httpsPrefix = paramUri2;
    }
    
    public Uri getHttpPrefix()
    {
      return this.httpPrefix;
    }
    
    public Uri getHttpsPrefix()
    {
      return this.httpsPrefix;
    }
  }
  
  private static abstract class LazyInputStream
    extends InputStream
  {
    protected final WebViewLocalServer.PathHandler handler;
    private InputStream is = null;
    
    public LazyInputStream(WebViewLocalServer.PathHandler paramPathHandler)
    {
      this.handler = paramPathHandler;
    }
    
    private InputStream getInputStream()
    {
      if (this.is == null) {
        this.is = handle();
      }
      return this.is;
    }
    
    public int available()
      throws IOException
    {
      InputStream localInputStream = getInputStream();
      if (localInputStream != null) {
        return localInputStream.available();
      }
      return 0;
    }
    
    protected abstract InputStream handle();
    
    public int read()
      throws IOException
    {
      InputStream localInputStream = getInputStream();
      if (localInputStream != null) {
        return localInputStream.read();
      }
      return -1;
    }
    
    public int read(byte[] paramArrayOfByte)
      throws IOException
    {
      InputStream localInputStream = getInputStream();
      if (localInputStream != null) {
        return localInputStream.read(paramArrayOfByte);
      }
      return -1;
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      InputStream localInputStream = getInputStream();
      if (localInputStream != null) {
        return localInputStream.read(paramArrayOfByte, paramInt1, paramInt2);
      }
      return -1;
    }
    
    public long skip(long paramLong)
      throws IOException
    {
      InputStream localInputStream = getInputStream();
      if (localInputStream != null) {
        return localInputStream.skip(paramLong);
      }
      return 0L;
    }
  }
  
  private static class LollipopLazyInputStream
    extends WebViewLocalServer.LazyInputStream
  {
    private InputStream is;
    private Uri uri;
    
    public LollipopLazyInputStream(WebViewLocalServer.PathHandler paramPathHandler, Uri paramUri)
    {
      super();
      this.uri = paramUri;
    }
    
    protected InputStream handle()
    {
      return this.handler.handle(this.uri);
    }
  }
  
  public static abstract class PathHandler
  {
    private String charset;
    private String encoding;
    protected String mimeType;
    private String reasonPhrase;
    private Map<String, String> responseHeaders;
    private int statusCode;
    
    public PathHandler()
    {
      this(null, null, 200, "OK", null);
    }
    
    public PathHandler(String paramString1, String paramString2, int paramInt, String paramString3, Map<String, String> paramMap)
    {
      this.encoding = paramString1;
      this.charset = paramString2;
      this.statusCode = paramInt;
      this.reasonPhrase = paramString3;
      if (paramMap == null) {}
      for (paramString1 = new HashMap();; paramString1 = paramMap)
      {
        paramString1.put("Cache-Control", "no-cache");
        this.responseHeaders = paramString1;
        return;
      }
    }
    
    public String getCharset()
    {
      return this.charset;
    }
    
    public String getEncoding()
    {
      return this.encoding;
    }
    
    public String getReasonPhrase()
    {
      return this.reasonPhrase;
    }
    
    public Map<String, String> getResponseHeaders()
    {
      return this.responseHeaders;
    }
    
    public int getStatusCode()
    {
      return this.statusCode;
    }
    
    public abstract InputStream handle(Uri paramUri);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\ionicframework\cordova\webview\WebViewLocalServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */