package org.apache.cordova.file;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Environment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import org.apache.cordova.CordovaResourceApi;
import org.json.JSONException;
import org.json.JSONObject;

public class LocalFilesystem
  extends Filesystem
{
  private final Context context;
  
  public LocalFilesystem(String paramString, Context paramContext, CordovaResourceApi paramCordovaResourceApi, File paramFile)
  {
    super(Uri.fromFile(paramFile).buildUpon().appendEncodedPath("").build(), paramString, paramCordovaResourceApi);
    this.context = paramContext;
  }
  
  private void broadcastNewFile(Uri paramUri)
  {
    paramUri = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", paramUri);
    this.context.sendBroadcast(paramUri);
  }
  
  private void copyDirectory(Filesystem paramFilesystem, LocalFilesystemURL paramLocalFilesystemURL, File paramFile, boolean paramBoolean)
    throws IOException, NoModificationAllowedException, InvalidModificationException, FileExistsException
  {
    Object localObject;
    if (paramBoolean)
    {
      localObject = paramFilesystem.filesystemPathForURL(paramLocalFilesystemURL);
      if (localObject != null)
      {
        localObject = new File((String)localObject);
        if (paramFile.exists())
        {
          if (paramFile.list().length > 0) {
            throw new InvalidModificationException("directory is not empty");
          }
          paramFile.delete();
        }
        if (!((File)localObject).renameTo(paramFile)) {}
      }
    }
    do
    {
      return;
      if (paramFile.exists())
      {
        if (paramFile.list().length > 0) {
          throw new InvalidModificationException("directory is not empty");
        }
      }
      else if (!paramFile.mkdir()) {
        throw new NoModificationAllowedException("Couldn't create the destination directory");
      }
      localObject = paramFilesystem.listChildren(paramLocalFilesystemURL);
      int j = localObject.length;
      int i = 0;
      if (i < j)
      {
        LocalFilesystemURL localLocalFilesystemURL = localObject[i];
        File localFile = new File(paramFile, new File(localLocalFilesystemURL.path).getName());
        if (localLocalFilesystemURL.isDirectory) {
          copyDirectory(paramFilesystem, localLocalFilesystemURL, localFile, false);
        }
        for (;;)
        {
          i += 1;
          break;
          copyFile(paramFilesystem, localLocalFilesystemURL, localFile, false);
        }
      }
    } while (!paramBoolean);
    paramFilesystem.recursiveRemoveFileAtLocalURL(paramLocalFilesystemURL);
  }
  
  private void copyFile(Filesystem paramFilesystem, LocalFilesystemURL paramLocalFilesystemURL, File paramFile, boolean paramBoolean)
    throws IOException, InvalidModificationException, NoModificationAllowedException
  {
    if (paramBoolean)
    {
      String str = paramFilesystem.filesystemPathForURL(paramLocalFilesystemURL);
      if ((str == null) || (!new File(str).renameTo(paramFile))) {}
    }
    do
    {
      return;
      copyResource(this.resourceApi.openForRead(paramFilesystem.toNativeUri(paramLocalFilesystemURL)), new FileOutputStream(paramFile));
    } while (!paramBoolean);
    paramFilesystem.removeFileAtLocalURL(paramLocalFilesystemURL);
  }
  
  /* Error */
  private static void copyResource(org.apache.cordova.CordovaResourceApi.OpenForReadResult paramOpenForReadResult, java.io.OutputStream paramOutputStream)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 157	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
    //   4: astore 7
    //   6: aload 7
    //   8: instanceof 159
    //   11: ifeq +88 -> 99
    //   14: aload_1
    //   15: instanceof 141
    //   18: ifeq +81 -> 99
    //   21: aload_0
    //   22: getfield 157	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
    //   25: checkcast 159	java/io/FileInputStream
    //   28: invokevirtual 163	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   31: astore 7
    //   33: aload_1
    //   34: checkcast 141	java/io/FileOutputStream
    //   37: invokevirtual 164	java/io/FileOutputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   40: astore 8
    //   42: lconst_0
    //   43: lstore_3
    //   44: aload_0
    //   45: getfield 168	org/apache/cordova/CordovaResourceApi$OpenForReadResult:length	J
    //   48: lstore 5
    //   50: aload_0
    //   51: getfield 172	org/apache/cordova/CordovaResourceApi$OpenForReadResult:assetFd	Landroid/content/res/AssetFileDescriptor;
    //   54: ifnull +11 -> 65
    //   57: aload_0
    //   58: getfield 172	org/apache/cordova/CordovaResourceApi$OpenForReadResult:assetFd	Landroid/content/res/AssetFileDescriptor;
    //   61: invokevirtual 178	android/content/res/AssetFileDescriptor:getStartOffset	()J
    //   64: lstore_3
    //   65: aload 7
    //   67: lload_3
    //   68: invokevirtual 184	java/nio/channels/FileChannel:position	(J)Ljava/nio/channels/FileChannel;
    //   71: pop
    //   72: aload 8
    //   74: aload 7
    //   76: lconst_0
    //   77: lload 5
    //   79: invokevirtual 188	java/nio/channels/FileChannel:transferFrom	(Ljava/nio/channels/ReadableByteChannel;JJ)J
    //   82: pop2
    //   83: aload_0
    //   84: getfield 157	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
    //   87: invokevirtual 194	java/io/InputStream:close	()V
    //   90: aload_1
    //   91: ifnull +7 -> 98
    //   94: aload_1
    //   95: invokevirtual 197	java/io/OutputStream:close	()V
    //   98: return
    //   99: sipush 8192
    //   102: newarray <illegal type>
    //   104: astore 8
    //   106: aload 7
    //   108: aload 8
    //   110: iconst_0
    //   111: sipush 8192
    //   114: invokevirtual 201	java/io/InputStream:read	([BII)I
    //   117: istore_2
    //   118: iload_2
    //   119: ifle -36 -> 83
    //   122: aload_1
    //   123: aload 8
    //   125: iconst_0
    //   126: iload_2
    //   127: invokevirtual 205	java/io/OutputStream:write	([BII)V
    //   130: goto -24 -> 106
    //   133: astore 7
    //   135: aload_0
    //   136: getfield 157	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
    //   139: invokevirtual 194	java/io/InputStream:close	()V
    //   142: aload_1
    //   143: ifnull +7 -> 150
    //   146: aload_1
    //   147: invokevirtual 197	java/io/OutputStream:close	()V
    //   150: aload 7
    //   152: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	153	0	paramOpenForReadResult	org.apache.cordova.CordovaResourceApi.OpenForReadResult
    //   0	153	1	paramOutputStream	java.io.OutputStream
    //   117	10	2	i	int
    //   43	25	3	l1	long
    //   48	30	5	l2	long
    //   4	103	7	localObject1	Object
    //   133	18	7	localObject2	Object
    //   40	84	8	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   0	42	133	finally
    //   44	50	133	finally
    //   50	65	133	finally
    //   65	83	133	finally
    //   99	106	133	finally
    //   106	118	133	finally
    //   122	130	133	finally
  }
  
  private String fullPathForFilesystemPath(String paramString)
  {
    if ((paramString != null) && (paramString.startsWith(this.rootUri.getPath()))) {
      return paramString.substring(this.rootUri.getPath().length() - 1);
    }
    return null;
  }
  
  private boolean isPublicDirectory(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      File[] arrayOfFile = this.context.getExternalMediaDirs();
      int j = arrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        File localFile = arrayOfFile[i];
        if ((localFile != null) && (paramString.startsWith(localFile.getAbsolutePath()))) {
          return true;
        }
        i += 1;
      }
    }
    return paramString.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath());
  }
  
  public LocalFilesystemURL URLforFilesystemPath(String paramString)
  {
    return localUrlforFullPath(fullPathForFilesystemPath(paramString));
  }
  
  public boolean canRemoveFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
  {
    return new File(filesystemPathForURL(paramLocalFilesystemURL)).exists();
  }
  
  public JSONObject copyFileToURL(LocalFilesystemURL paramLocalFilesystemURL1, String paramString, Filesystem paramFilesystem, LocalFilesystemURL paramLocalFilesystemURL2, boolean paramBoolean)
    throws IOException, InvalidModificationException, JSONException, NoModificationAllowedException, FileExistsException
  {
    if (!new File(filesystemPathForURL(paramLocalFilesystemURL1)).exists()) {
      throw new FileNotFoundException("The source does not exist");
    }
    paramLocalFilesystemURL1 = makeDestinationURL(paramString, paramLocalFilesystemURL2, paramLocalFilesystemURL1, paramLocalFilesystemURL2.isDirectory);
    paramString = toNativeUri(paramLocalFilesystemURL1);
    Uri localUri = paramFilesystem.toNativeUri(paramLocalFilesystemURL2);
    if (paramString.equals(localUri)) {
      throw new InvalidModificationException("Can't copy onto itself");
    }
    if ((paramBoolean) && (!paramFilesystem.canRemoveFileAtLocalURL(paramLocalFilesystemURL2))) {
      throw new InvalidModificationException("Source URL is read-only (cannot move)");
    }
    File localFile = new File(paramString.getPath());
    if (localFile.exists())
    {
      if ((!paramLocalFilesystemURL2.isDirectory) && (localFile.isDirectory())) {
        throw new InvalidModificationException("Can't copy/move a file to an existing directory");
      }
      if ((paramLocalFilesystemURL2.isDirectory) && (localFile.isFile())) {
        throw new InvalidModificationException("Can't copy/move a directory to an existing file");
      }
    }
    if (paramLocalFilesystemURL2.isDirectory)
    {
      if (paramString.toString().startsWith(localUri.toString() + '/')) {
        throw new InvalidModificationException("Can't copy directory into itself");
      }
      copyDirectory(paramFilesystem, paramLocalFilesystemURL2, localFile, paramBoolean);
    }
    for (;;)
    {
      return makeEntryForURL(paramLocalFilesystemURL1);
      copyFile(paramFilesystem, paramLocalFilesystemURL2, localFile, paramBoolean);
    }
  }
  
  public boolean exists(LocalFilesystemURL paramLocalFilesystemURL)
  {
    return new File(filesystemPathForURL(paramLocalFilesystemURL)).exists();
  }
  
  public String filesystemPathForFullPath(String paramString)
  {
    return new File(this.rootUri.getPath(), paramString).toString();
  }
  
  public String filesystemPathForURL(LocalFilesystemURL paramLocalFilesystemURL)
  {
    return filesystemPathForFullPath(paramLocalFilesystemURL.path);
  }
  
  public JSONObject getFileForLocalURL(LocalFilesystemURL paramLocalFilesystemURL, String paramString, JSONObject paramJSONObject, boolean paramBoolean)
    throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException
  {
    int i = 0;
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramJSONObject != null)
    {
      boolean bool3 = paramJSONObject.optBoolean("create");
      i = bool3;
      bool1 = bool2;
      if (bool3)
      {
        bool1 = paramJSONObject.optBoolean("exclusive");
        i = bool3;
      }
    }
    if (paramString.contains(":")) {
      throw new EncodingException("This path has an invalid \":\" in it.");
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
    for (paramLocalFilesystemURL = localUrlforFullPath(normalizePath(paramJSONObject));; paramLocalFilesystemURL = localUrlforFullPath(normalizePath(paramLocalFilesystemURL.path + "/" + paramJSONObject)))
    {
      paramString = new File(filesystemPathForURL(paramLocalFilesystemURL));
      if (i == 0) {
        break label244;
      }
      if ((!bool1) || (!paramString.exists())) {
        break;
      }
      throw new FileExistsException("create/exclusive fails");
    }
    if (paramBoolean) {
      paramString.mkdir();
    }
    while (!paramString.exists())
    {
      throw new FileExistsException("create fails");
      paramString.createNewFile();
    }
    label244:
    if (!paramString.exists()) {
      throw new FileNotFoundException("path does not exist");
    }
    if (paramBoolean)
    {
      if (paramString.isFile()) {
        throw new TypeMismatchException("path doesn't exist or is file");
      }
    }
    else if (paramString.isDirectory()) {
      throw new TypeMismatchException("path doesn't exist or is directory");
    }
    return makeEntryForURL(paramLocalFilesystemURL);
  }
  
  public JSONObject getFileMetadataForLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileNotFoundException
  {
    File localFile = new File(filesystemPathForURL(paramLocalFilesystemURL));
    if (!localFile.exists()) {
      throw new FileNotFoundException("File at " + paramLocalFilesystemURL.uri + " does not exist.");
    }
    JSONObject localJSONObject = new JSONObject();
    try
    {
      if (localFile.isDirectory()) {}
      for (long l = 0L;; l = localFile.length())
      {
        localJSONObject.put("size", l);
        localJSONObject.put("type", this.resourceApi.getMimeType(Uri.fromFile(localFile)));
        localJSONObject.put("name", localFile.getName());
        localJSONObject.put("fullPath", paramLocalFilesystemURL.path);
        localJSONObject.put("lastModifiedDate", localFile.lastModified());
        return localJSONObject;
      }
      return null;
    }
    catch (JSONException paramLocalFilesystemURL) {}
  }
  
  public long getFreeSpaceInBytes()
  {
    return DirectoryManager.getFreeSpaceInBytes(this.rootUri.getPath());
  }
  
  public LocalFilesystemURL[] listChildren(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileNotFoundException
  {
    paramLocalFilesystemURL = new File(filesystemPathForURL(paramLocalFilesystemURL));
    if (!paramLocalFilesystemURL.exists()) {
      throw new FileNotFoundException();
    }
    File[] arrayOfFile = paramLocalFilesystemURL.listFiles();
    if (arrayOfFile == null)
    {
      paramLocalFilesystemURL = null;
      return paramLocalFilesystemURL;
    }
    LocalFilesystemURL[] arrayOfLocalFilesystemURL = new LocalFilesystemURL[arrayOfFile.length];
    int i = 0;
    for (;;)
    {
      paramLocalFilesystemURL = arrayOfLocalFilesystemURL;
      if (i >= arrayOfFile.length) {
        break;
      }
      arrayOfLocalFilesystemURL[i] = URLforFilesystemPath(arrayOfFile[i].getPath());
      i += 1;
    }
  }
  
  public boolean recursiveRemoveFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileExistsException
  {
    return removeDirRecursively(new File(filesystemPathForURL(paramLocalFilesystemURL)));
  }
  
  protected boolean removeDirRecursively(File paramFile)
    throws FileExistsException
  {
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      int j = arrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        removeDirRecursively(arrayOfFile[i]);
        i += 1;
      }
    }
    if (!paramFile.delete()) {
      throw new FileExistsException("could not delete: " + paramFile.getName());
    }
    return true;
  }
  
  public boolean removeFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws InvalidModificationException
  {
    paramLocalFilesystemURL = new File(filesystemPathForURL(paramLocalFilesystemURL));
    if ((paramLocalFilesystemURL.isDirectory()) && (paramLocalFilesystemURL.list().length > 0)) {
      throw new InvalidModificationException("You can't delete a directory that is not empty.");
    }
    return paramLocalFilesystemURL.delete();
  }
  
  public LocalFilesystemURL toLocalUri(Uri paramUri)
  {
    if (!"file".equals(paramUri.getScheme())) {}
    File localFile;
    do
    {
      return null;
      localFile = new File(paramUri.getPath());
      paramUri = Uri.fromFile(localFile);
      localObject = this.rootUri.getEncodedPath();
      localObject = ((String)localObject).substring(0, ((String)localObject).length() - 1);
    } while (!paramUri.getEncodedPath().startsWith((String)localObject));
    Object localObject = paramUri.getEncodedPath().substring(((String)localObject).length());
    paramUri = (Uri)localObject;
    if (!((String)localObject).isEmpty()) {
      paramUri = ((String)localObject).substring(1);
    }
    localObject = new Uri.Builder().scheme("cdvfile").authority("localhost").path(this.name);
    if (!paramUri.isEmpty()) {
      ((Uri.Builder)localObject).appendEncodedPath(paramUri);
    }
    if (localFile.isDirectory()) {
      ((Uri.Builder)localObject).appendEncodedPath("");
    }
    return LocalFilesystemURL.parse(((Uri.Builder)localObject).build());
  }
  
  public Uri toNativeUri(LocalFilesystemURL paramLocalFilesystemURL)
  {
    return nativeUriForFullPath(paramLocalFilesystemURL.path);
  }
  
  public long truncateFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, long paramLong)
    throws IOException
  {
    if (!new File(filesystemPathForURL(paramLocalFilesystemURL)).exists()) {
      throw new FileNotFoundException("File at " + paramLocalFilesystemURL.uri + " does not exist.");
    }
    paramLocalFilesystemURL = new RandomAccessFile(filesystemPathForURL(paramLocalFilesystemURL), "rw");
    try
    {
      if (paramLocalFilesystemURL.length() >= paramLong)
      {
        paramLocalFilesystemURL.getChannel().truncate(paramLong);
        return paramLong;
      }
      paramLong = paramLocalFilesystemURL.length();
      return paramLong;
    }
    finally
    {
      paramLocalFilesystemURL.close();
    }
  }
  
  /* Error */
  public long writeToFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, String paramString, int paramInt, boolean paramBoolean)
    throws IOException, NoModificationAllowedException
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: iload_3
    //   4: ifle +14 -> 18
    //   7: aload_0
    //   8: aload_1
    //   9: iload_3
    //   10: i2l
    //   11: invokevirtual 480	org/apache/cordova/file/LocalFilesystem:truncateFileAtURL	(Lorg/apache/cordova/file/LocalFilesystemURL;J)J
    //   14: pop2
    //   15: iconst_1
    //   16: istore 5
    //   18: iload 4
    //   20: ifeq +106 -> 126
    //   23: aload_2
    //   24: iconst_0
    //   25: invokestatic 486	android/util/Base64:decode	(Ljava/lang/String;I)[B
    //   28: astore_2
    //   29: new 488	java/io/ByteArrayInputStream
    //   32: dup
    //   33: aload_2
    //   34: invokespecial 491	java/io/ByteArrayInputStream:<init>	([B)V
    //   37: astore 7
    //   39: aload_2
    //   40: arraylength
    //   41: newarray <illegal type>
    //   43: astore 8
    //   45: aload_0
    //   46: aload_1
    //   47: invokevirtual 256	org/apache/cordova/file/LocalFilesystem:filesystemPathForURL	(Lorg/apache/cordova/file/LocalFilesystemURL;)Ljava/lang/String;
    //   50: astore 9
    //   52: new 141	java/io/FileOutputStream
    //   55: dup
    //   56: aload 9
    //   58: iload 5
    //   60: invokespecial 494	java/io/FileOutputStream:<init>	(Ljava/lang/String;Z)V
    //   63: astore 6
    //   65: aload 7
    //   67: aload 8
    //   69: iconst_0
    //   70: aload 8
    //   72: arraylength
    //   73: invokevirtual 495	java/io/ByteArrayInputStream:read	([BII)I
    //   76: pop
    //   77: aload 6
    //   79: aload 8
    //   81: iconst_0
    //   82: aload_2
    //   83: arraylength
    //   84: invokevirtual 496	java/io/FileOutputStream:write	([BII)V
    //   87: aload 6
    //   89: invokevirtual 499	java/io/FileOutputStream:flush	()V
    //   92: aload 6
    //   94: invokevirtual 500	java/io/FileOutputStream:close	()V
    //   97: aload_0
    //   98: aload 9
    //   100: invokespecial 502	org/apache/cordova/file/LocalFilesystem:isPublicDirectory	(Ljava/lang/String;)Z
    //   103: ifeq +19 -> 122
    //   106: aload_0
    //   107: new 67	java/io/File
    //   110: dup
    //   111: aload 9
    //   113: invokespecial 70	java/io/File:<init>	(Ljava/lang/String;)V
    //   116: invokestatic 14	android/net/Uri:fromFile	(Ljava/io/File;)Landroid/net/Uri;
    //   119: invokespecial 504	org/apache/cordova/file/LocalFilesystem:broadcastNewFile	(Landroid/net/Uri;)V
    //   122: aload_2
    //   123: arraylength
    //   124: i2l
    //   125: lreturn
    //   126: aload_2
    //   127: invokestatic 510	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   130: invokevirtual 514	java/lang/String:getBytes	(Ljava/nio/charset/Charset;)[B
    //   133: astore_2
    //   134: goto -105 -> 29
    //   137: astore_2
    //   138: aload 6
    //   140: invokevirtual 500	java/io/FileOutputStream:close	()V
    //   143: aload_2
    //   144: athrow
    //   145: astore_2
    //   146: new 57	org/apache/cordova/file/NoModificationAllowedException
    //   149: dup
    //   150: aload_1
    //   151: invokevirtual 515	org/apache/cordova/file/LocalFilesystemURL:toString	()Ljava/lang/String;
    //   154: invokespecial 94	org/apache/cordova/file/NoModificationAllowedException:<init>	(Ljava/lang/String;)V
    //   157: astore_1
    //   158: aload_1
    //   159: aload_2
    //   160: invokevirtual 519	org/apache/cordova/file/NoModificationAllowedException:initCause	(Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   163: pop
    //   164: aload_1
    //   165: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	166	0	this	LocalFilesystem
    //   0	166	1	paramLocalFilesystemURL	LocalFilesystemURL
    //   0	166	2	paramString	String
    //   0	166	3	paramInt	int
    //   0	166	4	paramBoolean	boolean
    //   1	58	5	bool	boolean
    //   63	76	6	localFileOutputStream	FileOutputStream
    //   37	29	7	localByteArrayInputStream	java.io.ByteArrayInputStream
    //   43	37	8	arrayOfByte	byte[]
    //   50	62	9	str	String
    // Exception table:
    //   from	to	target	type
    //   65	92	137	finally
    //   39	65	145	java/lang/NullPointerException
    //   92	122	145	java/lang/NullPointerException
    //   138	145	145	java/lang/NullPointerException
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\file\LocalFilesystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */