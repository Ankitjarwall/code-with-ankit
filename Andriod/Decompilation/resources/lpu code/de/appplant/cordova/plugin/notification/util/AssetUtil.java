package de.appplant.cordova.plugin.notification.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public final class AssetUtil
{
  private static final String STORAGE_FOLDER = "/localnotification";
  private final Context context;
  
  private AssetUtil(Context paramContext)
  {
    this.context = paramContext;
  }
  
  private void copyFile(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      paramOutputStream.write(arrayOfByte, 0, i);
    }
  }
  
  private String getBaseName(String paramString)
  {
    Object localObject2 = paramString;
    Object localObject1 = localObject2;
    if (((String)localObject2).contains("/")) {
      localObject1 = ((String)localObject2).substring(((String)localObject2).lastIndexOf('/') + 1);
    }
    localObject2 = localObject1;
    if (paramString.contains(".")) {
      localObject2 = ((String)localObject1).substring(0, ((String)localObject1).lastIndexOf('.'));
    }
    return (String)localObject2;
  }
  
  public static AssetUtil getInstance(Context paramContext)
  {
    return new AssetUtil(paramContext);
  }
  
  private String getPkgName(Resources paramResources)
  {
    if (paramResources == Resources.getSystem()) {
      return "android";
    }
    return this.context.getPackageName();
  }
  
  private int getResId(Resources paramResources, String paramString)
  {
    String str = getPkgName(paramResources);
    paramString = getBaseName(paramString);
    int j = paramResources.getIdentifier(paramString, "mipmap", str);
    int i = j;
    if (j == 0) {
      i = paramResources.getIdentifier(paramString, "drawable", str);
    }
    j = i;
    if (i == 0) {
      j = paramResources.getIdentifier(paramString, "raw", str);
    }
    return j;
  }
  
  private File getTmpFile()
  {
    return getTmpFile(UUID.randomUUID().toString());
  }
  
  private File getTmpFile(String paramString)
  {
    File localFile = this.context.getExternalCacheDir();
    Object localObject = localFile;
    if (localFile == null) {
      localObject = this.context.getCacheDir();
    }
    if (localObject == null)
    {
      Log.e("Asset", "Missing cache dir");
      return null;
    }
    localObject = ((File)localObject).toString() + "/localnotification";
    new File((String)localObject).mkdir();
    return new File((String)localObject, paramString);
  }
  
  private Uri getUriForResourcePath(String paramString)
  {
    Resources localResources = this.context.getResources();
    paramString = paramString.replaceFirst("res://", "");
    int i = getResId(paramString);
    if (i == 0)
    {
      Log.e("Asset", "File not found: " + paramString);
      return Uri.EMPTY;
    }
    return new Uri.Builder().scheme("android.resource").authority(localResources.getResourcePackageName(i)).appendPath(localResources.getResourceTypeName(i)).appendPath(localResources.getResourceEntryName(i)).build();
  }
  
  private Uri getUriFromAsset(String paramString)
  {
    paramString = paramString.replaceFirst("file:/", "www");
    Object localObject = getTmpFile(paramString.substring(paramString.lastIndexOf('/') + 1));
    if (localObject == null) {
      return Uri.EMPTY;
    }
    try
    {
      AssetManager localAssetManager = this.context.getAssets();
      FileOutputStream localFileOutputStream = new FileOutputStream((File)localObject);
      copyFile(localAssetManager.open(paramString), localFileOutputStream);
      localFileOutputStream.flush();
      localFileOutputStream.close();
      localObject = getUriFromFile((File)localObject);
      return (Uri)localObject;
    }
    catch (Exception localException)
    {
      Log.e("Asset", "File not found: assets/" + paramString);
      ThrowableExtension.printStackTrace(localException);
    }
    return Uri.EMPTY;
  }
  
  private Uri getUriFromFile(File paramFile)
  {
    try
    {
      String str = this.context.getPackageName() + ".provider";
      paramFile = AssetProvider.getUriForFile(this.context, str, paramFile);
      return paramFile;
    }
    catch (IllegalArgumentException paramFile)
    {
      ThrowableExtension.printStackTrace(paramFile);
    }
    return Uri.EMPTY;
  }
  
  private Uri getUriFromPath(String paramString)
  {
    paramString = new File(paramString.replaceFirst("file://", ""));
    if (!paramString.exists())
    {
      Log.e("Asset", "File not found: " + paramString.getAbsolutePath());
      return Uri.EMPTY;
    }
    return getUriFromFile(paramString);
  }
  
  private Uri getUriFromRemote(String paramString)
  {
    File localFile = getTmpFile();
    if (localFile == null) {
      return Uri.EMPTY;
    }
    try
    {
      paramString = (HttpURLConnection)new URL(paramString).openConnection();
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
      paramString.setRequestProperty("Connection", "close");
      paramString.setConnectTimeout(5000);
      paramString.connect();
      paramString = paramString.getInputStream();
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
      copyFile(paramString, localFileOutputStream);
      localFileOutputStream.flush();
      localFileOutputStream.close();
      paramString = getUriFromFile(localFile);
      return paramString;
    }
    catch (MalformedURLException paramString)
    {
      Log.e("Asset", "Incorrect URL");
      ThrowableExtension.printStackTrace(paramString);
      return Uri.EMPTY;
    }
    catch (FileNotFoundException paramString)
    {
      for (;;)
      {
        Log.e("Asset", "Failed to create new File from HTTP Content");
        ThrowableExtension.printStackTrace(paramString);
      }
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        Log.e("Asset", "No Input can be created from http Stream");
        ThrowableExtension.printStackTrace(paramString);
      }
    }
  }
  
  public Bitmap getIconFromUri(Uri paramUri)
    throws IOException
  {
    return BitmapFactory.decodeStream(this.context.getContentResolver().openInputStream(paramUri));
  }
  
  public int getResId(String paramString)
  {
    int j = getResId(this.context.getResources(), paramString);
    int i = j;
    if (j == 0) {
      i = getResId(Resources.getSystem(), paramString);
    }
    return i;
  }
  
  public Uri parse(String paramString)
  {
    if ((paramString == null) || (paramString.isEmpty())) {
      return Uri.EMPTY;
    }
    if (paramString.startsWith("res:")) {
      return getUriForResourcePath(paramString);
    }
    if (paramString.startsWith("file:///")) {
      return getUriFromPath(paramString);
    }
    if (paramString.startsWith("file://")) {
      return getUriFromAsset(paramString);
    }
    if (paramString.startsWith("http")) {
      return getUriFromRemote(paramString);
    }
    if (paramString.startsWith("content://")) {
      return Uri.parse(paramString);
    }
    return Uri.EMPTY;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\notification\util\AssetUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */