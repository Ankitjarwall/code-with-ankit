package com.hiddentao.cordova.filepath;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video.Media;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PermissionHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FilePath
  extends CordovaPlugin
{
  private static final int GET_CLOUD_PATH_ERROR_CODE = 1;
  private static final String GET_CLOUD_PATH_ERROR_ID = "cloud";
  private static final int GET_PATH_ERROR_CODE = 0;
  private static final String GET_PATH_ERROR_ID = null;
  private static final int INVALID_ACTION_ERROR_CODE = -1;
  private static final int RC_READ_EXTERNAL_STORAGE = 5;
  public static final String READ = "android.permission.READ_EXTERNAL_STORAGE";
  public static final int READ_REQ_CODE = 0;
  private static final String TAG = "[FilePath plugin]: ";
  private static CallbackContext callback;
  private static String uriStr;
  
  private static boolean fileExists(String paramString)
  {
    return new File(paramString).exists();
  }
  
  private static String getContentFromSegments(List<String> paramList)
  {
    String str = "";
    Iterator localIterator = paramList.iterator();
    do
    {
      paramList = str;
      if (!localIterator.hasNext()) {
        break;
      }
      paramList = (String)localIterator.next();
    } while (!paramList.startsWith("content://"));
    return paramList;
  }
  
  private static String getDataColumn(Context paramContext, Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    Context localContext = null;
    try
    {
      paramContext = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, paramString, paramArrayOfString, null);
      if (paramContext != null)
      {
        localContext = paramContext;
        if (paramContext.moveToFirst())
        {
          localContext = paramContext;
          paramUri = paramContext.getString(paramContext.getColumnIndexOrThrow("_data"));
          return paramUri;
        }
      }
      return null;
    }
    finally
    {
      if (localContext != null) {
        localContext.close();
      }
    }
  }
  
  private static String getDriveFilePath(Uri paramUri, Context paramContext)
  {
    Object localObject1 = paramContext.getContentResolver().query(paramUri, null, null, null, null);
    int i = ((Cursor)localObject1).getColumnIndex("_display_name");
    int j = ((Cursor)localObject1).getColumnIndex("_size");
    ((Cursor)localObject1).moveToFirst();
    Object localObject2 = ((Cursor)localObject1).getString(i);
    Long.toString(((Cursor)localObject1).getLong(j));
    localObject1 = new File(paramContext.getCacheDir(), (String)localObject2);
    try
    {
      paramUri = paramContext.getContentResolver().openInputStream(paramUri);
      paramContext = new FileOutputStream((File)localObject1);
      localObject2 = new byte[Math.min(paramUri.available(), 1048576)];
      for (;;)
      {
        i = paramUri.read((byte[])localObject2);
        if (i == -1) {
          break;
        }
        paramContext.write((byte[])localObject2, 0, i);
      }
      return ((File)localObject1).getPath();
    }
    catch (Exception paramUri)
    {
      Log.e("Exception", paramUri.getMessage());
    }
    for (;;)
    {
      Log.e("File Size", "Size " + ((File)localObject1).length());
      paramUri.close();
      paramContext.close();
      Log.e("File Path", "Path " + ((File)localObject1).getPath());
      Log.e("File Size", "Size " + ((File)localObject1).length());
    }
  }
  
  private static String getPath(Context paramContext, Uri paramUri)
  {
    Log.d("[FilePath plugin]: ", "File - Authority: " + paramUri.getAuthority() + ", Fragment: " + paramUri.getFragment() + ", Port: " + paramUri.getPort() + ", Query: " + paramUri.getQuery() + ", Scheme: " + paramUri.getScheme() + ", Host: " + paramUri.getHost() + ", Segments: " + paramUri.getPathSegments().toString());
    int i;
    if (Build.VERSION.SDK_INT >= 19) {
      i = 1;
    }
    while ((i != 0) && (DocumentsContract.isDocumentUri(paramContext, paramUri))) {
      if (isExternalStorageDocument(paramUri))
      {
        paramContext = DocumentsContract.getDocumentId(paramUri).split(":");
        paramUri = paramContext[0];
        paramContext = getPathFromExtSD(paramContext);
        if (paramContext != "")
        {
          return paramContext;
          i = 0;
        }
        else
        {
          return null;
        }
      }
      else
      {
        if (isDownloadsDocument(paramUri))
        {
          localObject = null;
          try
          {
            localCursor = paramContext.getContentResolver().query(paramUri, new String[] { "_display_name" }, null, null, null);
            if (localCursor != null)
            {
              localObject = localCursor;
              if (localCursor.moveToFirst())
              {
                localObject = localCursor;
                String str = localCursor.getString(0);
                localObject = localCursor;
                str = Environment.getExternalStorageDirectory().toString() + "/Download/" + str;
                localObject = localCursor;
                boolean bool = TextUtils.isEmpty(str);
                if (!bool)
                {
                  if (localCursor != null) {
                    localCursor.close();
                  }
                  return str;
                }
              }
            }
            if (localCursor != null) {
              localCursor.close();
            }
            localObject = DocumentsContract.getDocumentId(paramUri);
            if (!isMediaDocument(paramUri)) {
              break label476;
            }
          }
          finally
          {
            try
            {
              paramContext = getDataColumn(paramContext, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf((String)localObject).longValue()), null, null);
              return paramContext;
            }
            catch (NumberFormatException paramContext)
            {
              return paramUri.getPath().replaceFirst("^/document/raw:", "").replaceFirst("^raw:", "");
            }
            paramContext = finally;
            if (localObject != null) {
              ((Cursor)localObject).close();
            }
          }
        }
        Object localObject = DocumentsContract.getDocumentId(paramUri).split(":");
        Cursor localCursor = localObject[0];
        paramUri = null;
        if ("image".equals(localCursor)) {
          paramUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        for (;;)
        {
          return getDataColumn(paramContext, paramUri, "_id=?", new String[] { localObject[1] });
          if ("video".equals(localCursor)) {
            paramUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
          } else if ("audio".equals(localCursor)) {
            paramUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
          }
        }
        label476:
        if (!isGoogleDriveUri(paramUri)) {
          break label580;
        }
        return getDriveFilePath(paramUri, paramContext);
      }
    }
    if ("content".equalsIgnoreCase(paramUri.getScheme()))
    {
      if (isGooglePhotosUri(paramUri))
      {
        paramUri = getContentFromSegments(paramUri.getPathSegments());
        if (paramUri != "") {
          return getPath(paramContext, Uri.parse(paramUri));
        }
        return null;
      }
      if ((isGoogleDriveUri(paramUri)) || (isOneDriveUri(paramUri))) {
        return getDriveFilePath(paramUri, paramContext);
      }
      return getDataColumn(paramContext, paramUri, null, null);
    }
    if ("file".equalsIgnoreCase(paramUri.getScheme())) {
      return paramUri.getPath();
    }
    label580:
    return null;
  }
  
  private static String getPathFromExtSD(String[] paramArrayOfString)
  {
    String str = paramArrayOfString[0];
    paramArrayOfString = "/" + paramArrayOfString[1];
    if ("primary".equalsIgnoreCase(str))
    {
      str = Environment.getExternalStorageDirectory() + paramArrayOfString;
      if (fileExists(str)) {
        return str;
      }
    }
    str = System.getenv("SECONDARY_STORAGE") + paramArrayOfString;
    if (fileExists(str)) {
      return str;
    }
    paramArrayOfString = System.getenv("EXTERNAL_STORAGE") + paramArrayOfString;
    if (fileExists(paramArrayOfString)) {
      return paramArrayOfString;
    }
    return paramArrayOfString;
  }
  
  private static boolean isDownloadsDocument(Uri paramUri)
  {
    return "com.android.providers.downloads.documents".equals(paramUri.getAuthority());
  }
  
  private static boolean isExternalStorageDocument(Uri paramUri)
  {
    return "com.android.externalstorage.documents".equals(paramUri.getAuthority());
  }
  
  private static boolean isGoogleDriveUri(Uri paramUri)
  {
    return ("com.google.android.apps.docs.storage".equals(paramUri.getAuthority())) || ("com.google.android.apps.docs.storage.legacy".equals(paramUri.getAuthority()));
  }
  
  private static boolean isGooglePhotosUri(Uri paramUri)
  {
    return ("com.google.android.apps.photos.content".equals(paramUri.getAuthority())) || ("com.google.android.apps.photos.contentprovider".equals(paramUri.getAuthority()));
  }
  
  private static boolean isMediaDocument(Uri paramUri)
  {
    return "com.android.providers.media.documents".equals(paramUri.getAuthority());
  }
  
  private static boolean isOneDriveUri(Uri paramUri)
  {
    return "com.microsoft.skydrive.content.external".equals(paramUri.getAuthority());
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    callback = paramCallbackContext;
    uriStr = paramJSONArray.getString(0);
    if (paramString.equals("resolveNativePath"))
    {
      if (PermissionHelper.hasPermission(this, "android.permission.READ_EXTERNAL_STORAGE")) {
        resolveNativePath();
      }
      for (;;)
      {
        return true;
        getReadPermission(0);
      }
    }
    paramString = new JSONObject();
    paramString.put("code", -1);
    paramString.put("message", "Invalid action.");
    paramCallbackContext.error(paramString);
    return false;
  }
  
  protected void getReadPermission(int paramInt)
  {
    PermissionHelper.requestPermission(this, paramInt, "android.permission.READ_EXTERNAL_STORAGE");
  }
  
  public void initialize(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView)
  {
    super.initialize(paramCordovaInterface, paramCordovaWebView);
  }
  
  public void onRequestPermissionResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
    throws JSONException
  {
    int j = paramArrayOfInt.length;
    int i = 0;
    if (i < j) {
      if (paramArrayOfInt[i] == -1)
      {
        paramArrayOfString = new JSONObject();
        paramArrayOfString.put("code", 3);
        paramArrayOfString.put("message", "Filesystem permission was denied.");
        callback.error(paramArrayOfString);
      }
    }
    while (paramInt != 0)
    {
      return;
      i += 1;
      break;
    }
    resolveNativePath();
  }
  
  public void resolveNativePath()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    Object localObject = Uri.parse(uriStr);
    Log.d("[FilePath plugin]: ", "URI: " + uriStr);
    localObject = getPath(this.cordova.getActivity().getApplicationContext(), (Uri)localObject);
    if (localObject == GET_PATH_ERROR_ID)
    {
      localJSONObject.put("code", 0);
      localJSONObject.put("message", "Unable to resolve filesystem path.");
      callback.error(localJSONObject);
      return;
    }
    if (((String)localObject).equals("cloud"))
    {
      localJSONObject.put("code", 1);
      localJSONObject.put("message", "Files from cloud cannot be resolved to filesystem, download is required.");
      callback.error(localJSONObject);
      return;
    }
    Log.d("[FilePath plugin]: ", "Filepath: " + (String)localObject);
    callback.success("file://" + (String)localObject);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\hiddentao\cordova\filepath\FilePath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */