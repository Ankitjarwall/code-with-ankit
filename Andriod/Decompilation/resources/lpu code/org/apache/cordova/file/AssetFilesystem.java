package org.apache.cordova.file;

import android.content.res.AssetManager;
import android.net.Uri;
import android.net.Uri.Builder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaResourceApi.OpenForReadResult;
import org.apache.cordova.LOG;
import org.json.JSONException;
import org.json.JSONObject;

public class AssetFilesystem
  extends Filesystem
{
  private static final String LOG_TAG = "AssetFilesystem";
  private static Map<String, Long> lengthCache;
  private static Map<String, String[]> listCache;
  private static boolean listCacheFromFile;
  private static Object listCacheLock = new Object();
  private final AssetManager assetManager;
  
  public AssetFilesystem(AssetManager paramAssetManager, CordovaResourceApi paramCordovaResourceApi)
  {
    super(Uri.parse("file:///android_asset/"), "assets", paramCordovaResourceApi);
    this.assetManager = paramAssetManager;
  }
  
  private long getAssetSize(String paramString)
    throws FileNotFoundException
  {
    Object localObject2 = paramString;
    if (paramString.startsWith("/")) {
      localObject2 = paramString.substring(1);
    }
    lazyInitCaches();
    long l2;
    if (lengthCache != null)
    {
      paramString = (Long)lengthCache.get(localObject2);
      if (paramString == null) {
        throw new FileNotFoundException("Asset not found: " + (String)localObject2);
      }
      l2 = paramString.longValue();
    }
    for (;;)
    {
      return l2;
      localObject1 = null;
      paramString = null;
      try
      {
        CordovaResourceApi.OpenForReadResult localOpenForReadResult = this.resourceApi.openForRead(nativeUriForFullPath((String)localObject2));
        paramString = localOpenForReadResult;
        localObject1 = localOpenForReadResult;
        l2 = localOpenForReadResult.length;
        long l1 = l2;
        if (l2 < 0L)
        {
          paramString = localOpenForReadResult;
          localObject1 = localOpenForReadResult;
          int i = localOpenForReadResult.inputStream.available();
          l1 = i;
        }
        l2 = l1;
        if (localOpenForReadResult == null) {
          continue;
        }
        try
        {
          localOpenForReadResult.inputStream.close();
          return l1;
        }
        catch (IOException paramString)
        {
          LOG.d("AssetFilesystem", paramString.getLocalizedMessage());
          return l1;
        }
        try
        {
          ((CordovaResourceApi.OpenForReadResult)localObject1).inputStream.close();
          throw paramString;
        }
        catch (IOException localIOException1)
        {
          for (;;)
          {
            LOG.d("AssetFilesystem", localIOException1.getLocalizedMessage());
          }
        }
      }
      catch (IOException localIOException2)
      {
        localObject1 = paramString;
        localObject2 = new FileNotFoundException("File not found: " + (String)localObject2);
        localObject1 = paramString;
        ((FileNotFoundException)localObject2).initCause(localIOException2);
        localObject1 = paramString;
        throw ((Throwable)localObject2);
      }
      finally
      {
        if (localObject1 == null) {}
      }
    }
  }
  
  private boolean isDirectory(String paramString)
  {
    boolean bool = false;
    try
    {
      int i = listAssets(paramString).length;
      if (i != 0) {
        bool = true;
      }
      return bool;
    }
    catch (IOException paramString) {}
    return false;
  }
  
  /* Error */
  private void lazyInitCaches()
  {
    // Byte code:
    //   0: getstatic 28	org/apache/cordova/file/AssetFilesystem:listCacheLock	Ljava/lang/Object;
    //   3: astore 5
    //   5: aload 5
    //   7: monitorenter
    //   8: getstatic 153	org/apache/cordova/file/AssetFilesystem:listCache	Ljava/util/Map;
    //   11: astore_1
    //   12: aload_1
    //   13: ifnonnull +82 -> 95
    //   16: aconst_null
    //   17: astore 4
    //   19: aconst_null
    //   20: astore_1
    //   21: aconst_null
    //   22: astore_3
    //   23: new 155	java/io/ObjectInputStream
    //   26: dup
    //   27: aload_0
    //   28: getfield 45	org/apache/cordova/file/AssetFilesystem:assetManager	Landroid/content/res/AssetManager;
    //   31: ldc -99
    //   33: invokevirtual 163	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   36: invokespecial 166	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   39: astore_2
    //   40: aload_2
    //   41: invokevirtual 170	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   44: checkcast 70	java/util/Map
    //   47: putstatic 153	org/apache/cordova/file/AssetFilesystem:listCache	Ljava/util/Map;
    //   50: aload_2
    //   51: invokevirtual 170	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   54: checkcast 70	java/util/Map
    //   57: putstatic 68	org/apache/cordova/file/AssetFilesystem:lengthCache	Ljava/util/Map;
    //   60: iconst_1
    //   61: putstatic 172	org/apache/cordova/file/AssetFilesystem:listCacheFromFile	Z
    //   64: aload_2
    //   65: ifnull +158 -> 223
    //   68: aload_2
    //   69: invokevirtual 173	java/io/ObjectInputStream:close	()V
    //   72: getstatic 153	org/apache/cordova/file/AssetFilesystem:listCache	Ljava/util/Map;
    //   75: ifnonnull +20 -> 95
    //   78: ldc 8
    //   80: ldc -81
    //   82: invokestatic 178	org/apache/cordova/LOG:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   85: new 180	java/util/HashMap
    //   88: dup
    //   89: invokespecial 181	java/util/HashMap:<init>	()V
    //   92: putstatic 153	org/apache/cordova/file/AssetFilesystem:listCache	Ljava/util/Map;
    //   95: aload 5
    //   97: monitorexit
    //   98: return
    //   99: astore_1
    //   100: ldc 8
    //   102: aload_1
    //   103: invokevirtual 131	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   106: invokestatic 137	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   109: goto -37 -> 72
    //   112: astore_1
    //   113: aload_3
    //   114: astore_2
    //   115: aload_1
    //   116: astore_3
    //   117: aload_2
    //   118: astore_1
    //   119: aload_3
    //   120: invokestatic 187	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   123: aload_2
    //   124: ifnull -52 -> 72
    //   127: aload_2
    //   128: invokevirtual 173	java/io/ObjectInputStream:close	()V
    //   131: goto -59 -> 72
    //   134: astore_1
    //   135: ldc 8
    //   137: aload_1
    //   138: invokevirtual 131	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   141: invokestatic 137	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   144: goto -72 -> 72
    //   147: astore_1
    //   148: aload 5
    //   150: monitorexit
    //   151: aload_1
    //   152: athrow
    //   153: astore_1
    //   154: aload 4
    //   156: astore_1
    //   157: aload_1
    //   158: ifnull -86 -> 72
    //   161: aload_1
    //   162: invokevirtual 173	java/io/ObjectInputStream:close	()V
    //   165: goto -93 -> 72
    //   168: astore_1
    //   169: ldc 8
    //   171: aload_1
    //   172: invokevirtual 131	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   175: invokestatic 137	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   178: goto -106 -> 72
    //   181: astore_2
    //   182: aload_1
    //   183: ifnull +7 -> 190
    //   186: aload_1
    //   187: invokevirtual 173	java/io/ObjectInputStream:close	()V
    //   190: aload_2
    //   191: athrow
    //   192: astore_1
    //   193: ldc 8
    //   195: aload_1
    //   196: invokevirtual 131	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   199: invokestatic 137	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   202: goto -12 -> 190
    //   205: astore_3
    //   206: aload_2
    //   207: astore_1
    //   208: aload_3
    //   209: astore_2
    //   210: goto -28 -> 182
    //   213: astore_1
    //   214: aload_2
    //   215: astore_1
    //   216: goto -59 -> 157
    //   219: astore_3
    //   220: goto -103 -> 117
    //   223: goto -151 -> 72
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	226	0	this	AssetFilesystem
    //   11	10	1	localMap	Map
    //   99	4	1	localIOException1	IOException
    //   112	4	1	localClassNotFoundException1	ClassNotFoundException
    //   118	1	1	localObject1	Object
    //   134	4	1	localIOException2	IOException
    //   147	5	1	localObject2	Object
    //   153	1	1	localIOException3	IOException
    //   156	6	1	localObject3	Object
    //   168	19	1	localIOException4	IOException
    //   192	4	1	localIOException5	IOException
    //   207	1	1	localObject4	Object
    //   213	1	1	localIOException6	IOException
    //   215	1	1	localObject5	Object
    //   39	89	2	localObject6	Object
    //   181	26	2	localObject7	Object
    //   209	6	2	localObject8	Object
    //   22	98	3	localClassNotFoundException2	ClassNotFoundException
    //   205	4	3	localObject9	Object
    //   219	1	3	localClassNotFoundException3	ClassNotFoundException
    //   17	138	4	localObject10	Object
    //   3	146	5	localObject11	Object
    // Exception table:
    //   from	to	target	type
    //   68	72	99	java/io/IOException
    //   23	40	112	java/lang/ClassNotFoundException
    //   127	131	134	java/io/IOException
    //   8	12	147	finally
    //   68	72	147	finally
    //   72	95	147	finally
    //   95	98	147	finally
    //   100	109	147	finally
    //   127	131	147	finally
    //   135	144	147	finally
    //   148	151	147	finally
    //   161	165	147	finally
    //   169	178	147	finally
    //   186	190	147	finally
    //   190	192	147	finally
    //   193	202	147	finally
    //   23	40	153	java/io/IOException
    //   161	165	168	java/io/IOException
    //   23	40	181	finally
    //   119	123	181	finally
    //   186	190	192	java/io/IOException
    //   40	64	205	finally
    //   40	64	213	java/io/IOException
    //   40	64	219	java/lang/ClassNotFoundException
  }
  
  private String[] listAssets(String paramString)
    throws IOException
  {
    Object localObject = paramString;
    if (paramString.startsWith("/")) {
      localObject = paramString.substring(1);
    }
    paramString = (String)localObject;
    if (((String)localObject).endsWith("/")) {
      paramString = ((String)localObject).substring(0, ((String)localObject).length() - 1);
    }
    lazyInitCaches();
    String[] arrayOfString = (String[])listCache.get(paramString);
    localObject = arrayOfString;
    if (arrayOfString == null)
    {
      if (listCacheFromFile) {
        localObject = new String[0];
      }
    }
    else {
      return (String[])localObject;
    }
    localObject = this.assetManager.list(paramString);
    listCache.put(paramString, localObject);
    return (String[])localObject;
  }
  
  LocalFilesystemURL URLforFilesystemPath(String paramString)
  {
    return null;
  }
  
  public boolean canRemoveFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
  {
    return false;
  }
  
  String filesystemPathForURL(LocalFilesystemURL paramLocalFilesystemURL)
  {
    return new File(this.rootUri.getPath(), paramLocalFilesystemURL.path).toString();
  }
  
  public JSONObject getFileForLocalURL(LocalFilesystemURL paramLocalFilesystemURL, String paramString, JSONObject paramJSONObject, boolean paramBoolean)
    throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException
  {
    if ((paramJSONObject != null) && (paramJSONObject.optBoolean("create"))) {
      throw new UnsupportedOperationException("Assets are read-only");
    }
    paramJSONObject = paramString;
    if (paramBoolean)
    {
      paramJSONObject = paramString;
      if (!paramString.endsWith("/")) {
        paramJSONObject = paramString + "/";
      }
    }
    if (paramJSONObject.startsWith("/")) {}
    boolean bool;
    for (paramLocalFilesystemURL = localUrlforFullPath(normalizePath(paramJSONObject));; paramLocalFilesystemURL = localUrlforFullPath(normalizePath(paramLocalFilesystemURL.path + "/" + paramJSONObject)))
    {
      getFileMetadataForLocalURL(paramLocalFilesystemURL);
      bool = isDirectory(paramLocalFilesystemURL.path);
      if ((!paramBoolean) || (bool)) {
        break;
      }
      throw new TypeMismatchException("path doesn't exist or is file");
    }
    if ((!paramBoolean) && (bool)) {
      throw new TypeMismatchException("path doesn't exist or is directory");
    }
    return makeEntryForURL(paramLocalFilesystemURL);
  }
  
  public JSONObject getFileMetadataForLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileNotFoundException
  {
    JSONObject localJSONObject = new JSONObject();
    long l;
    if (paramLocalFilesystemURL.isDirectory) {
      l = 0L;
    }
    try
    {
      localJSONObject.put("size", l);
      if (paramLocalFilesystemURL.isDirectory) {}
      for (String str = "text/directory";; str = this.resourceApi.getMimeType(toNativeUri(paramLocalFilesystemURL)))
      {
        localJSONObject.put("type", str);
        localJSONObject.put("name", new File(paramLocalFilesystemURL.path).getName());
        localJSONObject.put("fullPath", paramLocalFilesystemURL.path);
        localJSONObject.put("lastModifiedDate", 0);
        return localJSONObject;
        l = getAssetSize(paramLocalFilesystemURL.path);
        break;
      }
      return null;
    }
    catch (JSONException paramLocalFilesystemURL) {}
  }
  
  public LocalFilesystemURL[] listChildren(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileNotFoundException
  {
    localObject2 = paramLocalFilesystemURL.path.substring(1);
    Object localObject1 = localObject2;
    if (((String)localObject2).endsWith("/")) {
      localObject1 = ((String)localObject2).substring(0, ((String)localObject2).length() - 1);
    }
    try
    {
      localObject1 = listAssets((String)localObject1);
      localObject2 = new LocalFilesystemURL[localObject1.length];
      int i = 0;
      while (i < localObject1.length)
      {
        localObject2[i] = localUrlforFullPath(new File(paramLocalFilesystemURL.path, localObject1[i]).getPath());
        i += 1;
      }
      return (LocalFilesystemURL[])localObject2;
    }
    catch (IOException paramLocalFilesystemURL)
    {
      localObject1 = new FileNotFoundException();
      ((FileNotFoundException)localObject1).initCause(paramLocalFilesystemURL);
      throw ((Throwable)localObject1);
    }
  }
  
  boolean recursiveRemoveFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws NoModificationAllowedException
  {
    throw new NoModificationAllowedException("Assets are read-only");
  }
  
  boolean removeFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws InvalidModificationException, NoModificationAllowedException
  {
    throw new NoModificationAllowedException("Assets are read-only");
  }
  
  public LocalFilesystemURL toLocalUri(Uri paramUri)
  {
    if (!"file".equals(paramUri.getScheme())) {}
    do
    {
      return null;
      localObject1 = Uri.fromFile(new File(paramUri.getPath()));
      localObject2 = this.rootUri.getEncodedPath();
      localObject2 = ((String)localObject2).substring(0, ((String)localObject2).length() - 1);
    } while (!((Uri)localObject1).getEncodedPath().startsWith((String)localObject2));
    Object localObject2 = ((Uri)localObject1).getEncodedPath().substring(((String)localObject2).length());
    Object localObject1 = localObject2;
    if (!((String)localObject2).isEmpty()) {
      localObject1 = ((String)localObject2).substring(1);
    }
    localObject2 = new Uri.Builder().scheme("cdvfile").authority("localhost").path(this.name);
    if (!((String)localObject1).isEmpty()) {
      ((Uri.Builder)localObject2).appendEncodedPath((String)localObject1);
    }
    if ((isDirectory((String)localObject1)) || (paramUri.getPath().endsWith("/"))) {
      ((Uri.Builder)localObject2).appendEncodedPath("");
    }
    return LocalFilesystemURL.parse(((Uri.Builder)localObject2).build());
  }
  
  public Uri toNativeUri(LocalFilesystemURL paramLocalFilesystemURL)
  {
    return nativeUriForFullPath(paramLocalFilesystemURL.path);
  }
  
  long truncateFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, long paramLong)
    throws IOException, NoModificationAllowedException
  {
    throw new NoModificationAllowedException("Assets are read-only");
  }
  
  long writeToFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, String paramString, int paramInt, boolean paramBoolean)
    throws NoModificationAllowedException, IOException
  {
    throw new NoModificationAllowedException("Assets are read-only");
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\file\AssetFilesystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */