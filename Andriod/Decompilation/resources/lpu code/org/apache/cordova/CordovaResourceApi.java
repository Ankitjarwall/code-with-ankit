package org.apache.cordova;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Looper;
import android.util.Base64;
import android.webkit.MimeTypeMap;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.Locale;

public class CordovaResourceApi
{
  private static final String[] LOCAL_FILE_PROJECTION = { "_data" };
  private static final String LOG_TAG = "CordovaResourceApi";
  public static final String PLUGIN_URI_SCHEME = "cdvplugin";
  public static final int URI_TYPE_ASSET = 1;
  public static final int URI_TYPE_CONTENT = 2;
  public static final int URI_TYPE_DATA = 4;
  public static final int URI_TYPE_FILE = 0;
  public static final int URI_TYPE_HTTP = 5;
  public static final int URI_TYPE_HTTPS = 6;
  public static final int URI_TYPE_PLUGIN = 7;
  public static final int URI_TYPE_RESOURCE = 3;
  public static final int URI_TYPE_UNKNOWN = -1;
  public static Thread jsThread;
  private final AssetManager assetManager;
  private final ContentResolver contentResolver;
  private final PluginManager pluginManager;
  private boolean threadCheckingEnabled = true;
  
  public CordovaResourceApi(Context paramContext, PluginManager paramPluginManager)
  {
    this.contentResolver = paramContext.getContentResolver();
    this.assetManager = paramContext.getAssets();
    this.pluginManager = paramPluginManager;
  }
  
  private void assertBackgroundThread()
  {
    if (this.threadCheckingEnabled)
    {
      Thread localThread = Thread.currentThread();
      if (localThread == Looper.getMainLooper().getThread()) {
        throw new IllegalStateException("Do not perform IO operations on the UI thread. Use CordovaInterface.getThreadPool() instead.");
      }
      if (localThread == jsThread) {
        throw new IllegalStateException("Tried to perform an IO operation on the WebCore thread. Use CordovaInterface.getThreadPool() instead.");
      }
    }
  }
  
  private static void assertNonRelative(Uri paramUri)
  {
    if (!paramUri.isAbsolute()) {
      throw new IllegalArgumentException("Relative URIs are not supported.");
    }
  }
  
  private String getDataUriMimeType(Uri paramUri)
  {
    paramUri = paramUri.getSchemeSpecificPart();
    int i = paramUri.indexOf(',');
    if (i == -1) {}
    do
    {
      return null;
      paramUri = paramUri.substring(0, i).split(";");
    } while (paramUri.length <= 0);
    return paramUri[0];
  }
  
  private String getMimeTypeFromPath(String paramString)
  {
    int i = paramString.lastIndexOf('.');
    String str = paramString;
    if (i != -1) {
      str = paramString.substring(i + 1);
    }
    paramString = str.toLowerCase(Locale.getDefault());
    if (paramString.equals("3ga")) {
      return "audio/3gpp";
    }
    if (paramString.equals("js")) {
      return "text/javascript";
    }
    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramString);
  }
  
  public static int getUriType(Uri paramUri)
  {
    assertNonRelative(paramUri);
    String str = paramUri.getScheme();
    if ("content".equalsIgnoreCase(str)) {
      return 2;
    }
    if ("android.resource".equalsIgnoreCase(str)) {
      return 3;
    }
    if ("file".equalsIgnoreCase(str))
    {
      if (paramUri.getPath().startsWith("/android_asset/")) {
        return 1;
      }
      return 0;
    }
    if ("data".equalsIgnoreCase(str)) {
      return 4;
    }
    if ("http".equalsIgnoreCase(str)) {
      return 5;
    }
    if ("https".equalsIgnoreCase(str)) {
      return 6;
    }
    if ("cdvplugin".equalsIgnoreCase(str)) {
      return 7;
    }
    return -1;
  }
  
  private OpenForReadResult readDataUri(Uri paramUri)
  {
    Object localObject1 = paramUri.getSchemeSpecificPart();
    int k = ((String)localObject1).indexOf(',');
    if (k == -1) {
      return null;
    }
    Object localObject2 = ((String)localObject1).substring(0, k).split(";");
    String str = null;
    int j = 0;
    if (localObject2.length > 0) {
      str = localObject2[0];
    }
    int i = 1;
    while (i < localObject2.length)
    {
      if ("base64".equalsIgnoreCase(localObject2[i])) {
        j = 1;
      }
      i += 1;
    }
    localObject2 = ((String)localObject1).substring(k + 1);
    if (j != 0) {
      localObject1 = Base64.decode((String)localObject2, 0);
    }
    for (;;)
    {
      return new OpenForReadResult(paramUri, new ByteArrayInputStream((byte[])localObject1), str, localObject1.length, null);
      try
      {
        localObject1 = ((String)localObject2).getBytes("UTF-8");
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        byte[] arrayOfByte = ((String)localObject2).getBytes();
      }
    }
  }
  
  public void copyResource(Uri paramUri1, Uri paramUri2)
    throws IOException
  {
    copyResource(openForRead(paramUri1), openOutputStream(paramUri2));
  }
  
  public void copyResource(Uri paramUri, OutputStream paramOutputStream)
    throws IOException
  {
    copyResource(openForRead(paramUri), paramOutputStream);
  }
  
  /* Error */
  public void copyResource(OpenForReadResult paramOpenForReadResult, OutputStream paramOutputStream)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 253	org/apache/cordova/CordovaResourceApi:assertBackgroundThread	()V
    //   4: aload_1
    //   5: getfield 257	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
    //   8: astore 8
    //   10: aload 8
    //   12: instanceof 259
    //   15: ifeq +91 -> 106
    //   18: aload_2
    //   19: instanceof 261
    //   22: ifeq +84 -> 106
    //   25: aload_1
    //   26: getfield 257	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
    //   29: checkcast 259	java/io/FileInputStream
    //   32: invokevirtual 265	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   35: astore 8
    //   37: aload_2
    //   38: checkcast 261	java/io/FileOutputStream
    //   41: invokevirtual 266	java/io/FileOutputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   44: astore 9
    //   46: lconst_0
    //   47: lstore 4
    //   49: aload_1
    //   50: getfield 270	org/apache/cordova/CordovaResourceApi$OpenForReadResult:length	J
    //   53: lstore 6
    //   55: aload_1
    //   56: getfield 274	org/apache/cordova/CordovaResourceApi$OpenForReadResult:assetFd	Landroid/content/res/AssetFileDescriptor;
    //   59: ifnull +12 -> 71
    //   62: aload_1
    //   63: getfield 274	org/apache/cordova/CordovaResourceApi$OpenForReadResult:assetFd	Landroid/content/res/AssetFileDescriptor;
    //   66: invokevirtual 280	android/content/res/AssetFileDescriptor:getStartOffset	()J
    //   69: lstore 4
    //   71: aload 8
    //   73: lload 4
    //   75: invokevirtual 286	java/nio/channels/FileChannel:position	(J)Ljava/nio/channels/FileChannel;
    //   78: pop
    //   79: aload 9
    //   81: aload 8
    //   83: lconst_0
    //   84: lload 6
    //   86: invokevirtual 290	java/nio/channels/FileChannel:transferFrom	(Ljava/nio/channels/ReadableByteChannel;JJ)J
    //   89: pop2
    //   90: aload_1
    //   91: getfield 257	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
    //   94: invokevirtual 295	java/io/InputStream:close	()V
    //   97: aload_2
    //   98: ifnull +7 -> 105
    //   101: aload_2
    //   102: invokevirtual 298	java/io/OutputStream:close	()V
    //   105: return
    //   106: sipush 8192
    //   109: newarray <illegal type>
    //   111: astore 9
    //   113: aload 8
    //   115: aload 9
    //   117: iconst_0
    //   118: sipush 8192
    //   121: invokevirtual 302	java/io/InputStream:read	([BII)I
    //   124: istore_3
    //   125: iload_3
    //   126: ifle -36 -> 90
    //   129: aload_2
    //   130: aload 9
    //   132: iconst_0
    //   133: iload_3
    //   134: invokevirtual 306	java/io/OutputStream:write	([BII)V
    //   137: goto -24 -> 113
    //   140: astore 8
    //   142: aload_1
    //   143: getfield 257	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
    //   146: invokevirtual 295	java/io/InputStream:close	()V
    //   149: aload_2
    //   150: ifnull +7 -> 157
    //   153: aload_2
    //   154: invokevirtual 298	java/io/OutputStream:close	()V
    //   157: aload 8
    //   159: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	160	0	this	CordovaResourceApi
    //   0	160	1	paramOpenForReadResult	OpenForReadResult
    //   0	160	2	paramOutputStream	OutputStream
    //   124	10	3	i	int
    //   47	27	4	l1	long
    //   53	32	6	l2	long
    //   8	106	8	localObject1	Object
    //   140	18	8	localObject2	Object
    //   44	87	9	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   4	46	140	finally
    //   49	55	140	finally
    //   55	71	140	finally
    //   71	90	140	finally
    //   106	113	140	finally
    //   113	125	140	finally
    //   129	137	140	finally
  }
  
  public HttpURLConnection createHttpConnection(Uri paramUri)
    throws IOException
  {
    assertBackgroundThread();
    return (HttpURLConnection)new URL(paramUri.toString()).openConnection();
  }
  
  public String getMimeType(Uri paramUri)
  {
    switch (getUriType(paramUri))
    {
    }
    for (;;)
    {
      paramUri = null;
      for (;;)
      {
        return paramUri;
        return getMimeTypeFromPath(paramUri.getPath());
        return this.contentResolver.getType(paramUri);
        return getDataUriMimeType(paramUri);
        try
        {
          paramUri = (HttpURLConnection)new URL(paramUri.toString()).openConnection();
          paramUri.setDoInput(false);
          paramUri.setRequestMethod("HEAD");
          String str = paramUri.getHeaderField("Content-Type");
          paramUri = str;
          if (str != null)
          {
            paramUri = str.split(";")[0];
            return paramUri;
          }
        }
        catch (IOException paramUri) {}
      }
    }
  }
  
  public boolean isThreadCheckingEnabled()
  {
    return this.threadCheckingEnabled;
  }
  
  public File mapUriToFile(Uri paramUri)
  {
    assertBackgroundThread();
    switch (getUriType(paramUri))
    {
    }
    do
    {
      return null;
      return new File(paramUri.getPath());
      paramUri = this.contentResolver.query(paramUri, LOCAL_FILE_PROJECTION, null, null, null);
    } while (paramUri == null);
    try
    {
      int i = paramUri.getColumnIndex(LOCAL_FILE_PROJECTION[0]);
      if ((i != -1) && (paramUri.getCount() > 0))
      {
        paramUri.moveToFirst();
        Object localObject1 = paramUri.getString(i);
        if (localObject1 != null)
        {
          localObject1 = new File((String)localObject1);
          return (File)localObject1;
        }
      }
      return null;
    }
    finally
    {
      paramUri.close();
    }
  }
  
  public OpenForReadResult openForRead(Uri paramUri)
    throws IOException
  {
    return openForRead(paramUri, false);
  }
  
  public OpenForReadResult openForRead(Uri paramUri, boolean paramBoolean)
    throws IOException
  {
    if (!paramBoolean) {
      assertBackgroundThread();
    }
    Object localObject4;
    Object localObject3;
    switch (getUriType(paramUri))
    {
    default: 
    case 0: 
    case 1: 
    case 2: 
    case 3: 
    case 4: 
      do
      {
        throw new FileNotFoundException("URI not supported by CordovaResourceApi: " + paramUri);
        localObject1 = new FileInputStream(paramUri.getPath());
        return new OpenForReadResult(paramUri, (InputStream)localObject1, getMimeTypeFromPath(paramUri.getPath()), ((FileInputStream)localObject1).getChannel().size(), null);
        String str = paramUri.getPath().substring(15);
        localObject1 = null;
        try
        {
          localObject2 = this.assetManager.openFd(str);
          localObject1 = localObject2;
          localObject4 = ((AssetFileDescriptor)localObject2).createInputStream();
          localObject1 = localObject2;
          l = ((AssetFileDescriptor)localObject2).getLength();
          localObject1 = localObject2;
          localObject2 = localObject4;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          for (;;)
          {
            Object localObject2;
            localObject3 = this.assetManager.open(str);
            long l = ((InputStream)localObject3).available();
          }
        }
        return new OpenForReadResult(paramUri, (InputStream)localObject2, getMimeTypeFromPath(str), l, (AssetFileDescriptor)localObject1);
        localObject1 = this.contentResolver.getType(paramUri);
        localObject3 = this.contentResolver.openAssetFileDescriptor(paramUri, "r");
        return new OpenForReadResult(paramUri, ((AssetFileDescriptor)localObject3).createInputStream(), (String)localObject1, ((AssetFileDescriptor)localObject3).getLength(), (AssetFileDescriptor)localObject3);
        localObject1 = readDataUri(paramUri);
      } while (localObject1 == null);
      return (OpenForReadResult)localObject1;
    case 5: 
    case 6: 
      localObject4 = (HttpURLConnection)new URL(paramUri.toString()).openConnection();
      ((HttpURLConnection)localObject4).setDoInput(true);
      localObject3 = ((HttpURLConnection)localObject4).getHeaderField("Content-Type");
      localObject1 = localObject3;
      if (localObject3 != null) {
        localObject1 = localObject3.split(";")[0];
      }
      int i = ((HttpURLConnection)localObject4).getContentLength();
      return new OpenForReadResult(paramUri, ((HttpURLConnection)localObject4).getInputStream(), (String)localObject1, i, null);
    }
    Object localObject1 = paramUri.getHost();
    localObject1 = this.pluginManager.getPlugin((String)localObject1);
    if (localObject1 == null) {
      throw new FileNotFoundException("Invalid plugin ID in URI: " + paramUri);
    }
    return ((CordovaPlugin)localObject1).handleOpenForRead(paramUri);
  }
  
  public OutputStream openOutputStream(Uri paramUri)
    throws IOException
  {
    return openOutputStream(paramUri, false);
  }
  
  public OutputStream openOutputStream(Uri paramUri, boolean paramBoolean)
    throws IOException
  {
    assertBackgroundThread();
    switch (getUriType(paramUri))
    {
    case 1: 
    default: 
      throw new FileNotFoundException("URI not supported by CordovaResourceApi: " + paramUri);
    case 0: 
      paramUri = new File(paramUri.getPath());
      localObject = paramUri.getParentFile();
      if (localObject != null) {
        ((File)localObject).mkdirs();
      }
      return new FileOutputStream(paramUri, paramBoolean);
    }
    ContentResolver localContentResolver = this.contentResolver;
    if (paramBoolean) {}
    for (Object localObject = "wa";; localObject = "w") {
      return localContentResolver.openAssetFileDescriptor(paramUri, (String)localObject).createOutputStream();
    }
  }
  
  public String remapPath(String paramString)
  {
    return remapUri(Uri.fromFile(new File(paramString))).getPath();
  }
  
  public Uri remapUri(Uri paramUri)
  {
    assertNonRelative(paramUri);
    Uri localUri = this.pluginManager.remapUri(paramUri);
    if (localUri != null) {
      return localUri;
    }
    return paramUri;
  }
  
  public void setThreadCheckingEnabled(boolean paramBoolean)
  {
    this.threadCheckingEnabled = paramBoolean;
  }
  
  public static final class OpenForReadResult
  {
    public final AssetFileDescriptor assetFd;
    public final InputStream inputStream;
    public final long length;
    public final String mimeType;
    public final Uri uri;
    
    public OpenForReadResult(Uri paramUri, InputStream paramInputStream, String paramString, long paramLong, AssetFileDescriptor paramAssetFileDescriptor)
    {
      this.uri = paramUri;
      this.inputStream = paramInputStream;
      this.mimeType = paramString;
      this.length = paramLong;
      this.assetFd = paramAssetFileDescriptor;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\CordovaResourceApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */