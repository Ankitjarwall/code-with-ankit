package org.apache.cordova.file;

import android.net.Uri;
import android.net.Uri.Builder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaResourceApi.OpenForReadResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Filesystem
{
  public final String name;
  protected final CordovaResourceApi resourceApi;
  private JSONObject rootEntry;
  protected final Uri rootUri;
  
  public Filesystem(Uri paramUri, String paramString, CordovaResourceApi paramCordovaResourceApi)
  {
    this.rootUri = paramUri;
    this.name = paramString;
    this.resourceApi = paramCordovaResourceApi;
  }
  
  /* Error */
  public static JSONObject makeEntryForURL(LocalFilesystemURL paramLocalFilesystemURL, Uri paramUri)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_0
    //   3: getfield 39	org/apache/cordova/file/LocalFilesystemURL:path	Ljava/lang/String;
    //   6: astore 5
    //   8: aload 5
    //   10: ldc 41
    //   12: invokevirtual 47	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   15: ifeq +193 -> 208
    //   18: iconst_1
    //   19: istore_2
    //   20: aload 5
    //   22: iconst_0
    //   23: aload 5
    //   25: invokevirtual 51	java/lang/String:length	()I
    //   28: iload_2
    //   29: isub
    //   30: invokevirtual 55	java/lang/String:substring	(II)Ljava/lang/String;
    //   33: ldc 57
    //   35: invokevirtual 61	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   38: astore 6
    //   40: aload 6
    //   42: aload 6
    //   44: arraylength
    //   45: iconst_1
    //   46: isub
    //   47: aaload
    //   48: astore 7
    //   50: new 63	org/json/JSONObject
    //   53: dup
    //   54: invokespecial 64	org/json/JSONObject:<init>	()V
    //   57: astore 6
    //   59: aload_0
    //   60: getfield 68	org/apache/cordova/file/LocalFilesystemURL:isDirectory	Z
    //   63: ifne +150 -> 213
    //   66: iconst_1
    //   67: istore 4
    //   69: aload 6
    //   71: ldc 70
    //   73: iload 4
    //   75: invokevirtual 74	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   78: pop
    //   79: aload 6
    //   81: ldc 75
    //   83: aload_0
    //   84: getfield 68	org/apache/cordova/file/LocalFilesystemURL:isDirectory	Z
    //   87: invokevirtual 74	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   90: pop
    //   91: aload 6
    //   93: ldc 76
    //   95: aload 7
    //   97: invokevirtual 79	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   100: pop
    //   101: aload 6
    //   103: ldc 81
    //   105: aload 5
    //   107: invokevirtual 79	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   110: pop
    //   111: aload 6
    //   113: ldc 83
    //   115: aload_0
    //   116: getfield 86	org/apache/cordova/file/LocalFilesystemURL:fsName	Ljava/lang/String;
    //   119: invokevirtual 79	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   122: pop
    //   123: ldc 88
    //   125: aload_0
    //   126: getfield 86	org/apache/cordova/file/LocalFilesystemURL:fsName	Ljava/lang/String;
    //   129: invokevirtual 92	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   132: ifeq +87 -> 219
    //   135: iload_3
    //   136: istore_2
    //   137: aload 6
    //   139: ldc 94
    //   141: iload_2
    //   142: invokevirtual 97	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   145: pop
    //   146: aload_1
    //   147: invokevirtual 103	android/net/Uri:toString	()Ljava/lang/String;
    //   150: astore 5
    //   152: aload 5
    //   154: astore_1
    //   155: aload_0
    //   156: getfield 68	org/apache/cordova/file/LocalFilesystemURL:isDirectory	Z
    //   159: ifeq +37 -> 196
    //   162: aload 5
    //   164: astore_1
    //   165: aload 5
    //   167: ldc 41
    //   169: invokevirtual 47	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   172: ifne +24 -> 196
    //   175: new 105	java/lang/StringBuilder
    //   178: dup
    //   179: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   182: aload 5
    //   184: invokevirtual 110	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: ldc 41
    //   189: invokevirtual 110	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   192: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   195: astore_1
    //   196: aload 6
    //   198: ldc 113
    //   200: aload_1
    //   201: invokevirtual 79	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   204: pop
    //   205: aload 6
    //   207: areturn
    //   208: iconst_0
    //   209: istore_2
    //   210: goto -190 -> 20
    //   213: iconst_0
    //   214: istore 4
    //   216: goto -147 -> 69
    //   219: iconst_1
    //   220: istore_2
    //   221: goto -84 -> 137
    //   224: astore_0
    //   225: aload_0
    //   226: invokestatic 119	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   229: new 121	java/lang/RuntimeException
    //   232: dup
    //   233: aload_0
    //   234: invokespecial 123	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   237: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	238	0	paramLocalFilesystemURL	LocalFilesystemURL
    //   0	238	1	paramUri	Uri
    //   19	202	2	i	int
    //   1	135	3	j	int
    //   67	148	4	bool	boolean
    //   6	177	5	str	String
    //   38	168	6	localObject1	Object
    //   48	48	7	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   2	18	224	org/json/JSONException
    //   20	66	224	org/json/JSONException
    //   69	135	224	org/json/JSONException
    //   137	152	224	org/json/JSONException
    //   155	162	224	org/json/JSONException
    //   165	196	224	org/json/JSONException
    //   196	205	224	org/json/JSONException
  }
  
  protected static String normalizePath(String paramString)
  {
    boolean bool = paramString.startsWith("/");
    Object localObject = paramString;
    if (bool) {
      localObject = paramString.replaceFirst("/+", "");
    }
    localObject = new ArrayList(Arrays.asList(((String)localObject).split("/+")));
    int j;
    for (int i = 0; i < ((ArrayList)localObject).size(); i = j + 1)
    {
      j = i;
      if (((String)((ArrayList)localObject).get(i)).equals(".."))
      {
        ((ArrayList)localObject).remove(i);
        j = i;
        if (i > 0)
        {
          ((ArrayList)localObject).remove(i - 1);
          j = i - 1;
        }
      }
    }
    paramString = new StringBuilder();
    localObject = ((ArrayList)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      paramString.append("/");
      paramString.append(str);
    }
    if (bool) {
      return paramString.toString();
    }
    return paramString.toString().substring(1);
  }
  
  abstract LocalFilesystemURL URLforFilesystemPath(String paramString);
  
  abstract boolean canRemoveFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL);
  
  public JSONObject copyFileToURL(LocalFilesystemURL paramLocalFilesystemURL1, String paramString, Filesystem paramFilesystem, LocalFilesystemURL paramLocalFilesystemURL2, boolean paramBoolean)
    throws IOException, InvalidModificationException, JSONException, NoModificationAllowedException, FileExistsException
  {
    if ((paramBoolean) && (!paramFilesystem.canRemoveFileAtLocalURL(paramLocalFilesystemURL2))) {
      throw new NoModificationAllowedException("Cannot move file at source URL");
    }
    paramLocalFilesystemURL1 = makeDestinationURL(paramString, paramLocalFilesystemURL2, paramLocalFilesystemURL1, paramLocalFilesystemURL2.isDirectory);
    paramString = paramFilesystem.toNativeUri(paramLocalFilesystemURL2);
    paramString = this.resourceApi.openForRead(paramString);
    try
    {
      OutputStream localOutputStream = getOutputStreamForURL(paramLocalFilesystemURL1);
      this.resourceApi.copyResource(paramString, localOutputStream);
      if (paramBoolean) {
        paramFilesystem.removeFileAtLocalURL(paramLocalFilesystemURL2);
      }
      return getEntryForLocalURL(paramLocalFilesystemURL1);
    }
    catch (IOException paramLocalFilesystemURL1)
    {
      paramString.inputStream.close();
      throw paramLocalFilesystemURL1;
    }
  }
  
  public boolean exists(LocalFilesystemURL paramLocalFilesystemURL)
  {
    try
    {
      getFileMetadataForLocalURL(paramLocalFilesystemURL);
      return true;
    }
    catch (FileNotFoundException paramLocalFilesystemURL) {}
    return false;
  }
  
  abstract String filesystemPathForURL(LocalFilesystemURL paramLocalFilesystemURL);
  
  public JSONObject getEntryForLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws IOException
  {
    return makeEntryForURL(paramLocalFilesystemURL);
  }
  
  abstract JSONObject getFileForLocalURL(LocalFilesystemURL paramLocalFilesystemURL, String paramString, JSONObject paramJSONObject, boolean paramBoolean)
    throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException;
  
  abstract JSONObject getFileMetadataForLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileNotFoundException;
  
  public long getFreeSpaceInBytes()
  {
    return 0L;
  }
  
  public OutputStream getOutputStreamForURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws IOException
  {
    return this.resourceApi.openOutputStream(toNativeUri(paramLocalFilesystemURL));
  }
  
  public JSONObject getParentForLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws IOException
  {
    Uri localUri = paramLocalFilesystemURL.uri;
    String str = new File(paramLocalFilesystemURL.uri.getPath()).getParent();
    if (!"/".equals(str)) {
      localUri = paramLocalFilesystemURL.uri.buildUpon().path(str + '/').build();
    }
    return getEntryForLocalURL(LocalFilesystemURL.parse(localUri));
  }
  
  public JSONObject getRootEntry()
  {
    if (this.rootEntry == null) {
      this.rootEntry = makeEntryForNativeUri(this.rootUri);
    }
    return this.rootEntry;
  }
  
  public Uri getRootUri()
  {
    return this.rootUri;
  }
  
  abstract LocalFilesystemURL[] listChildren(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileNotFoundException;
  
  public LocalFilesystemURL localUrlforFullPath(String paramString)
  {
    paramString = nativeUriForFullPath(paramString);
    if (paramString != null) {
      return toLocalUri(paramString);
    }
    return null;
  }
  
  protected LocalFilesystemURL makeDestinationURL(String paramString, LocalFilesystemURL paramLocalFilesystemURL1, LocalFilesystemURL paramLocalFilesystemURL2, boolean paramBoolean)
  {
    String str;
    if (!"null".equals(paramString))
    {
      str = paramString;
      if (!"".equals(paramString)) {}
    }
    else
    {
      str = paramLocalFilesystemURL1.uri.getLastPathSegment();
    }
    paramString = paramLocalFilesystemURL2.uri.toString();
    if (paramString.endsWith("/")) {}
    for (paramString = paramString + str;; paramString = paramString + "/" + str)
    {
      paramLocalFilesystemURL1 = paramString;
      if (paramBoolean) {
        paramLocalFilesystemURL1 = paramString + '/';
      }
      return LocalFilesystemURL.parse(paramLocalFilesystemURL1);
    }
  }
  
  public JSONObject makeEntryForFile(File paramFile)
  {
    return makeEntryForNativeUri(Uri.fromFile(paramFile));
  }
  
  public JSONObject makeEntryForNativeUri(Uri paramUri)
  {
    LocalFilesystemURL localLocalFilesystemURL = toLocalUri(paramUri);
    if (localLocalFilesystemURL == null) {
      return null;
    }
    return makeEntryForURL(localLocalFilesystemURL, paramUri);
  }
  
  public JSONObject makeEntryForURL(LocalFilesystemURL paramLocalFilesystemURL)
  {
    Uri localUri = toNativeUri(paramLocalFilesystemURL);
    if (localUri == null) {
      return null;
    }
    return makeEntryForURL(paramLocalFilesystemURL, localUri);
  }
  
  public Uri nativeUriForFullPath(String paramString)
  {
    Object localObject = null;
    if (paramString != null)
    {
      localObject = Uri.fromFile(new File(paramString)).getEncodedPath();
      paramString = (String)localObject;
      if (((String)localObject).startsWith("/")) {
        paramString = ((String)localObject).substring(1);
      }
      localObject = this.rootUri.buildUpon().appendEncodedPath(paramString).build();
    }
    return (Uri)localObject;
  }
  
  public final JSONArray readEntriesAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileNotFoundException
  {
    paramLocalFilesystemURL = listChildren(paramLocalFilesystemURL);
    JSONArray localJSONArray = new JSONArray();
    if (paramLocalFilesystemURL != null)
    {
      int j = paramLocalFilesystemURL.length;
      int i = 0;
      while (i < j)
      {
        localJSONArray.put(makeEntryForURL(paramLocalFilesystemURL[i]));
        i += 1;
      }
    }
    return localJSONArray;
  }
  
  public void readFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, long paramLong1, long paramLong2, ReadFileCallback paramReadFileCallback)
    throws IOException
  {
    CordovaResourceApi.OpenForReadResult localOpenForReadResult = this.resourceApi.openForRead(toNativeUri(paramLocalFilesystemURL));
    long l = paramLong2;
    if (paramLong2 < 0L) {
      l = localOpenForReadResult.length;
    }
    if (paramLong1 > 0L) {}
    try
    {
      localOpenForReadResult.inputStream.skip(paramLong1);
      InputStream localInputStream = localOpenForReadResult.inputStream;
      paramLocalFilesystemURL = localInputStream;
      if (l < localOpenForReadResult.length) {
        paramLocalFilesystemURL = new LimitedInputStream(localInputStream, l - paramLong1);
      }
      paramReadFileCallback.handleData(paramLocalFilesystemURL, localOpenForReadResult.mimeType);
      return;
    }
    finally
    {
      localOpenForReadResult.inputStream.close();
    }
  }
  
  abstract boolean recursiveRemoveFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileExistsException, NoModificationAllowedException;
  
  abstract boolean removeFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws InvalidModificationException, NoModificationAllowedException;
  
  public abstract LocalFilesystemURL toLocalUri(Uri paramUri);
  
  public abstract Uri toNativeUri(LocalFilesystemURL paramLocalFilesystemURL);
  
  abstract long truncateFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, long paramLong)
    throws IOException, NoModificationAllowedException;
  
  abstract long writeToFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, String paramString, int paramInt, boolean paramBoolean)
    throws NoModificationAllowedException, IOException;
  
  protected class LimitedInputStream
    extends FilterInputStream
  {
    long numBytesToRead;
    
    public LimitedInputStream(InputStream paramInputStream, long paramLong)
    {
      super();
      this.numBytesToRead = paramLong;
    }
    
    public int read()
      throws IOException
    {
      if (this.numBytesToRead <= 0L) {
        return -1;
      }
      this.numBytesToRead -= 1L;
      return this.in.read();
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      if (this.numBytesToRead <= 0L) {
        return -1;
      }
      int i = paramInt2;
      if (paramInt2 > this.numBytesToRead) {
        i = (int)this.numBytesToRead;
      }
      paramInt1 = this.in.read(paramArrayOfByte, paramInt1, i);
      this.numBytesToRead -= paramInt1;
      return paramInt1;
    }
  }
  
  public static abstract interface ReadFileCallback
  {
    public abstract void handleData(InputStream paramInputStream, String paramString)
      throws IOException;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\file\Filesystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */