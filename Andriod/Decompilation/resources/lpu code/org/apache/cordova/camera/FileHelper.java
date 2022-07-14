package org.apache.cordova.camera;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video.Media;
import android.webkit.MimeTypeMap;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import org.apache.cordova.CordovaInterface;

public class FileHelper
{
  private static final String LOG_TAG = "FileUtils";
  private static final String _DATA = "_data";
  
  public static String getDataColumn(Context paramContext, Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    Context localContext2 = null;
    Context localContext1 = null;
    try
    {
      paramContext = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, paramString, paramArrayOfString, null);
      if (paramContext != null)
      {
        localContext1 = paramContext;
        localContext2 = paramContext;
        if (paramContext.moveToFirst())
        {
          localContext1 = paramContext;
          localContext2 = paramContext;
          paramUri = paramContext.getString(paramContext.getColumnIndexOrThrow("_data"));
          return paramUri;
        }
      }
      return null;
    }
    catch (Exception paramContext)
    {
      return null;
    }
    finally
    {
      if (localContext2 != null) {
        localContext2.close();
      }
    }
  }
  
  public static InputStream getInputStreamFromUriString(String paramString, CordovaInterface paramCordovaInterface)
    throws IOException
  {
    Object localObject;
    if (paramString.startsWith("content"))
    {
      paramString = Uri.parse(paramString);
      localObject = paramCordovaInterface.getActivity().getContentResolver().openInputStream(paramString);
    }
    for (;;)
    {
      return (InputStream)localObject;
      if (paramString.startsWith("file://"))
      {
        int i = paramString.indexOf("?");
        String str = paramString;
        if (i > -1) {
          str = paramString.substring(0, i);
        }
        if (str.startsWith("file:///android_asset/"))
        {
          paramString = Uri.parse(str).getPath().substring(15);
          return paramCordovaInterface.getActivity().getAssets().open(paramString);
        }
        try
        {
          paramString = paramCordovaInterface.getActivity().getContentResolver().openInputStream(Uri.parse(str));
          localObject = paramString;
          if (paramString == null) {
            return new FileInputStream(getRealPath(str, paramCordovaInterface));
          }
        }
        catch (Exception paramString)
        {
          for (;;)
          {
            paramString = null;
          }
        }
      }
    }
    return new FileInputStream(paramString);
  }
  
  public static String getMimeType(String paramString, CordovaInterface paramCordovaInterface)
  {
    Uri localUri = Uri.parse(paramString);
    if (paramString.startsWith("content://")) {
      return paramCordovaInterface.getActivity().getContentResolver().getType(localUri);
    }
    return getMimeTypeForExtension(localUri.getPath());
  }
  
  public static String getMimeTypeForExtension(String paramString)
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
    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramString);
  }
  
  public static String getRealPath(Uri paramUri, CordovaInterface paramCordovaInterface)
  {
    if (Build.VERSION.SDK_INT < 11) {
      return getRealPathFromURI_BelowAPI11(paramCordovaInterface.getActivity(), paramUri);
    }
    return getRealPathFromURI_API11_And_Above(paramCordovaInterface.getActivity(), paramUri);
  }
  
  public static String getRealPath(String paramString, CordovaInterface paramCordovaInterface)
  {
    return getRealPath(Uri.parse(paramString), paramCordovaInterface);
  }
  
  @SuppressLint({"NewApi"})
  public static String getRealPathFromURI_API11_And_Above(Context paramContext, Uri paramUri)
  {
    Object localObject2 = null;
    int i;
    Object localObject1;
    if (Build.VERSION.SDK_INT >= 19)
    {
      i = 1;
      if ((i == 0) || (!DocumentsContract.isDocumentUri(paramContext, paramUri))) {
        break label262;
      }
      if (!isExternalStorageDocument(paramUri)) {
        break label91;
      }
      paramContext = DocumentsContract.getDocumentId(paramUri).split(":");
      localObject1 = localObject2;
      if ("primary".equalsIgnoreCase(paramContext[0])) {
        localObject1 = Environment.getExternalStorageDirectory() + "/" + paramContext[1];
      }
    }
    label91:
    label165:
    label262:
    do
    {
      do
      {
        do
        {
          do
          {
            return (String)localObject1;
            i = 0;
            break;
            if (!isDownloadsDocument(paramUri)) {
              break label165;
            }
            paramUri = DocumentsContract.getDocumentId(paramUri);
            localObject1 = localObject2;
          } while (paramUri == null);
          localObject1 = localObject2;
        } while (paramUri.length() <= 0);
        if (paramUri.startsWith("raw:")) {
          return paramUri.replaceFirst("raw:", "");
        }
        try
        {
          paramContext = getDataColumn(paramContext, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(paramUri).longValue()), null, null);
          return paramContext;
        }
        catch (NumberFormatException paramContext)
        {
          return null;
        }
        localObject1 = localObject2;
      } while (!isMediaDocument(paramUri));
      localObject1 = DocumentsContract.getDocumentId(paramUri).split(":");
      localObject2 = localObject1[0];
      paramUri = null;
      if ("image".equals(localObject2)) {
        paramUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
      }
      for (;;)
      {
        return getDataColumn(paramContext, paramUri, "_id=?", new String[] { localObject1[1] });
        if ("video".equals(localObject2)) {
          paramUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else if ("audio".equals(localObject2)) {
          paramUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        }
      }
      if ("content".equalsIgnoreCase(paramUri.getScheme()))
      {
        if (isGooglePhotosUri(paramUri)) {
          return paramUri.getLastPathSegment();
        }
        return getDataColumn(paramContext, paramUri, null, null);
      }
      localObject1 = localObject2;
    } while (!"file".equalsIgnoreCase(paramUri.getScheme()));
    return paramUri.getPath();
  }
  
  public static String getRealPathFromURI_BelowAPI11(Context paramContext, Uri paramUri)
  {
    try
    {
      paramContext = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, null, null, null);
      int i = paramContext.getColumnIndexOrThrow("_data");
      paramContext.moveToFirst();
      paramContext = paramContext.getString(i);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static boolean isDownloadsDocument(Uri paramUri)
  {
    return "com.android.providers.downloads.documents".equals(paramUri.getAuthority());
  }
  
  public static boolean isExternalStorageDocument(Uri paramUri)
  {
    return "com.android.externalstorage.documents".equals(paramUri.getAuthority());
  }
  
  public static boolean isGooglePhotosUri(Uri paramUri)
  {
    return "com.google.android.apps.photos.content".equals(paramUri.getAuthority());
  }
  
  public static boolean isMediaDocument(Uri paramUri)
  {
    return "com.android.providers.media.documents".equals(paramUri.getAuthority());
  }
  
  public static String stripFileProtocol(String paramString)
  {
    String str = paramString;
    if (paramString.startsWith("file://")) {
      str = paramString.substring(7);
    }
    return str;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\camera\FileHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */