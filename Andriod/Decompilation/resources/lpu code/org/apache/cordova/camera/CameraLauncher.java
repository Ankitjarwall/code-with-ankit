package org.apache.cordova.camera;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import org.apache.cordova.BuildHelper;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.LOG;
import org.apache.cordova.PermissionHelper;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

public class CameraLauncher
  extends CordovaPlugin
  implements MediaScannerConnection.MediaScannerConnectionClient
{
  private static final int ALLMEDIA = 2;
  private static final int CAMERA = 1;
  private static final String CROPPED_URI_KEY = "croppedUri";
  private static final int CROP_CAMERA = 100;
  private static final int DATA_URL = 0;
  private static final int FILE_URI = 1;
  private static final String GET_All = "Get All";
  private static final String GET_PICTURE = "Get Picture";
  private static final String GET_VIDEO = "Get Video";
  private static final String IMAGE_URI_KEY = "imageUri";
  private static final int JPEG = 0;
  private static final String JPEG_EXTENSION = ".jpg";
  private static final String JPEG_MIME_TYPE = "image/jpeg";
  private static final String JPEG_TYPE = "jpg";
  private static final String LOG_TAG = "CameraLauncher";
  private static final int NATIVE_URI = 2;
  public static final int PERMISSION_DENIED_ERROR = 20;
  private static final int PHOTOLIBRARY = 0;
  private static final int PICTURE = 0;
  private static final int PNG = 1;
  private static final String PNG_EXTENSION = ".png";
  private static final String PNG_MIME_TYPE = "image/png";
  private static final String PNG_TYPE = "png";
  private static final int SAVEDPHOTOALBUM = 2;
  public static final int SAVE_TO_ALBUM_SEC = 1;
  private static final String TAKE_PICTURE_ACTION = "takePicture";
  public static final int TAKE_PIC_SEC = 0;
  private static final String TIME_FORMAT = "yyyyMMdd_HHmmss";
  private static final int VIDEO = 1;
  protected static final String[] permissions = { "android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE" };
  private boolean allowEdit;
  private String applicationId;
  public CallbackContext callbackContext;
  private MediaScannerConnection conn;
  private boolean correctOrientation;
  private Uri croppedUri;
  private int destType;
  private int encodingType;
  private ExifHelper exifData;
  private CordovaUri imageUri;
  private int mQuality;
  private int mediaType;
  private int numPics;
  private boolean orientationCorrected;
  private boolean saveToPhotoAlbum;
  private Uri scanMe;
  private int srcType;
  private int targetHeight;
  private int targetWidth;
  
  public static int calculateSampleSize(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt1 / paramInt2 > paramInt3 / paramInt4) {
      return paramInt1 / paramInt3;
    }
    return paramInt2 / paramInt4;
  }
  
  private void checkForDuplicateImage(int paramInt)
  {
    int j = 1;
    Uri localUri = whichContentStore();
    Cursor localCursor = queryImgDB(localUri);
    int k = localCursor.getCount();
    int i = j;
    if (paramInt == 1)
    {
      i = j;
      if (this.saveToPhotoAlbum) {
        i = 2;
      }
    }
    if (k - this.numPics == i)
    {
      localCursor.moveToLast();
      j = Integer.valueOf(localCursor.getString(localCursor.getColumnIndex("_id"))).intValue();
      paramInt = j;
      if (i == 2) {
        paramInt = j - 1;
      }
      localUri = Uri.parse(localUri + "/" + paramInt);
      this.cordova.getActivity().getContentResolver().delete(localUri, null, null);
      localCursor.close();
    }
  }
  
  private void cleanup(int paramInt, Uri paramUri1, Uri paramUri2, Bitmap paramBitmap)
  {
    if (paramBitmap != null) {
      paramBitmap.recycle();
    }
    new File(FileHelper.stripFileProtocol(paramUri1.toString())).delete();
    checkForDuplicateImage(paramInt);
    if ((this.saveToPhotoAlbum) && (paramUri2 != null)) {
      scanForGallery(paramUri2);
    }
    System.gc();
  }
  
  private File createCaptureFile(int paramInt)
  {
    return createCaptureFile(paramInt, "");
  }
  
  private File createCaptureFile(int paramInt, String paramString)
  {
    String str = paramString;
    if (paramString.isEmpty()) {
      str = ".Pic";
    }
    if (paramInt == 0) {}
    for (paramString = str + ".jpg";; paramString = str + ".png")
    {
      return new File(getTempDirectoryPath(), paramString);
      if (paramInt != 1) {
        break;
      }
    }
    throw new IllegalArgumentException("Invalid Encoding Type: " + paramInt);
  }
  
  private int exifToDegrees(int paramInt)
  {
    if (paramInt == 6) {
      return 90;
    }
    if (paramInt == 3) {
      return 180;
    }
    if (paramInt == 8) {
      return 270;
    }
    return 0;
  }
  
  private String getFileNameFromUri(Uri paramUri)
  {
    paramUri = paramUri.toString().split("external_files")[1];
    File localFile = Environment.getExternalStorageDirectory();
    return localFile.getAbsolutePath() + paramUri;
  }
  
  private String getMimetypeForFormat(int paramInt)
  {
    if (paramInt == 1) {
      return "image/png";
    }
    if (paramInt == 0) {
      return "image/jpeg";
    }
    return "";
  }
  
  private String getPicturesPath()
  {
    String str = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    Object localObject = new StringBuilder().append("IMG_").append(str);
    if (this.encodingType == 0) {}
    for (str = ".jpg";; str = ".png")
    {
      str = str;
      localObject = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
      ((File)localObject).mkdirs();
      return ((File)localObject).getAbsolutePath() + "/" + str;
    }
  }
  
  /* Error */
  private Bitmap getScaledAndRotatedBitmap(String paramString)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 324	org/apache/cordova/camera/CameraLauncher:targetWidth	I
    //   4: ifgt +200 -> 204
    //   7: aload_0
    //   8: getfield 326	org/apache/cordova/camera/CameraLauncher:targetHeight	I
    //   11: ifgt +193 -> 204
    //   14: aload_0
    //   15: getfield 328	org/apache/cordova/camera/CameraLauncher:correctOrientation	Z
    //   18: ifne +186 -> 204
    //   21: aconst_null
    //   22: astore 8
    //   24: aconst_null
    //   25: astore 6
    //   27: aconst_null
    //   28: astore 7
    //   30: aconst_null
    //   31: astore 10
    //   33: aload_1
    //   34: aload_0
    //   35: getfield 193	org/apache/cordova/camera/CameraLauncher:cordova	Lorg/apache/cordova/CordovaInterface;
    //   38: invokestatic 332	org/apache/cordova/camera/FileHelper:getInputStreamFromUriString	(Ljava/lang/String;Lorg/apache/cordova/CordovaInterface;)Ljava/io/InputStream;
    //   41: astore 9
    //   43: aload 9
    //   45: astore 7
    //   47: aload 9
    //   49: astore 8
    //   51: aload 9
    //   53: astore 6
    //   55: aload 9
    //   57: invokestatic 338	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   60: astore_1
    //   61: aload_1
    //   62: astore 6
    //   64: aload 6
    //   66: astore_1
    //   67: aload 9
    //   69: ifnull +11 -> 80
    //   72: aload 9
    //   74: invokevirtual 341	java/io/InputStream:close	()V
    //   77: aload 6
    //   79: astore_1
    //   80: aload_1
    //   81: areturn
    //   82: astore_1
    //   83: ldc 47
    //   85: ldc_w 343
    //   88: invokestatic 348	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   91: aload 6
    //   93: areturn
    //   94: astore_1
    //   95: aload 7
    //   97: astore 6
    //   99: aload_0
    //   100: getfield 350	org/apache/cordova/camera/CameraLauncher:callbackContext	Lorg/apache/cordova/CallbackContext;
    //   103: aload_1
    //   104: invokevirtual 353	java/lang/OutOfMemoryError:getLocalizedMessage	()Ljava/lang/String;
    //   107: invokevirtual 358	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
    //   110: aload 10
    //   112: astore_1
    //   113: aload 7
    //   115: ifnull -35 -> 80
    //   118: aload 7
    //   120: invokevirtual 341	java/io/InputStream:close	()V
    //   123: aconst_null
    //   124: areturn
    //   125: astore_1
    //   126: ldc 47
    //   128: ldc_w 343
    //   131: invokestatic 348	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   134: aconst_null
    //   135: areturn
    //   136: astore_1
    //   137: aload 8
    //   139: astore 6
    //   141: aload_0
    //   142: getfield 350	org/apache/cordova/camera/CameraLauncher:callbackContext	Lorg/apache/cordova/CallbackContext;
    //   145: aload_1
    //   146: invokevirtual 359	java/lang/Exception:getLocalizedMessage	()Ljava/lang/String;
    //   149: invokevirtual 358	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
    //   152: aload 10
    //   154: astore_1
    //   155: aload 8
    //   157: ifnull -77 -> 80
    //   160: aload 8
    //   162: invokevirtual 341	java/io/InputStream:close	()V
    //   165: aconst_null
    //   166: areturn
    //   167: astore_1
    //   168: ldc 47
    //   170: ldc_w 343
    //   173: invokestatic 348	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   176: aconst_null
    //   177: areturn
    //   178: astore_1
    //   179: aload 6
    //   181: ifnull +8 -> 189
    //   184: aload 6
    //   186: invokevirtual 341	java/io/InputStream:close	()V
    //   189: aload_1
    //   190: athrow
    //   191: astore 6
    //   193: ldc 47
    //   195: ldc_w 343
    //   198: invokestatic 348	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   201: goto -12 -> 189
    //   204: aconst_null
    //   205: astore 6
    //   207: aconst_null
    //   208: astore 7
    //   210: iconst_0
    //   211: istore_2
    //   212: iconst_0
    //   213: istore_3
    //   214: aload_1
    //   215: aload_0
    //   216: getfield 193	org/apache/cordova/camera/CameraLauncher:cordova	Lorg/apache/cordova/CordovaInterface;
    //   219: invokestatic 332	org/apache/cordova/camera/FileHelper:getInputStreamFromUriString	(Ljava/lang/String;Lorg/apache/cordova/CordovaInterface;)Ljava/io/InputStream;
    //   222: astore 8
    //   224: aload 8
    //   226: ifnull +203 -> 429
    //   229: new 292	java/text/SimpleDateFormat
    //   232: dup
    //   233: ldc 71
    //   235: invokespecial 293	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   238: new 295	java/util/Date
    //   241: dup
    //   242: invokespecial 296	java/util/Date:<init>	()V
    //   245: invokevirtual 300	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   248: astore 6
    //   250: new 166	java/lang/StringBuilder
    //   253: dup
    //   254: invokespecial 167	java/lang/StringBuilder:<init>	()V
    //   257: ldc_w 302
    //   260: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   263: aload 6
    //   265: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   268: astore 7
    //   270: aload_0
    //   271: getfield 304	org/apache/cordova/camera/CameraLauncher:encodingType	I
    //   274: ifne +238 -> 512
    //   277: ldc 38
    //   279: astore 6
    //   281: aload 7
    //   283: aload 6
    //   285: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   288: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   291: astore 6
    //   293: new 223	java/io/File
    //   296: dup
    //   297: new 166	java/lang/StringBuilder
    //   300: dup
    //   301: invokespecial 167	java/lang/StringBuilder:<init>	()V
    //   304: aload_0
    //   305: invokespecial 261	org/apache/cordova/camera/CameraLauncher:getTempDirectoryPath	()Ljava/lang/String;
    //   308: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   311: aload 6
    //   313: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   316: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   319: invokespecial 233	java/io/File:<init>	(Ljava/lang/String;)V
    //   322: astore 6
    //   324: aload 6
    //   326: invokestatic 363	android/net/Uri:fromFile	(Ljava/io/File;)Landroid/net/Uri;
    //   329: astore 7
    //   331: aload_0
    //   332: aload 8
    //   334: aload 7
    //   336: invokespecial 367	org/apache/cordova/camera/CameraLauncher:writeUncompressedImage	(Ljava/io/InputStream;Landroid/net/Uri;)V
    //   339: iload_3
    //   340: istore_2
    //   341: ldc 41
    //   343: aload_1
    //   344: invokevirtual 368	java/lang/String:toString	()Ljava/lang/String;
    //   347: aload_0
    //   348: getfield 193	org/apache/cordova/camera/CameraLauncher:cordova	Lorg/apache/cordova/CordovaInterface;
    //   351: invokestatic 372	org/apache/cordova/camera/FileHelper:getMimeType	(Ljava/lang/String;Lorg/apache/cordova/CordovaInterface;)Ljava/lang/String;
    //   354: invokevirtual 376	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   357: ifeq +72 -> 429
    //   360: aload 7
    //   362: invokevirtual 224	android/net/Uri:toString	()Ljava/lang/String;
    //   365: ldc_w 378
    //   368: ldc -6
    //   370: invokevirtual 382	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   373: astore_1
    //   374: aload_0
    //   375: new 384	org/apache/cordova/camera/ExifHelper
    //   378: dup
    //   379: invokespecial 385	org/apache/cordova/camera/ExifHelper:<init>	()V
    //   382: putfield 387	org/apache/cordova/camera/CameraLauncher:exifData	Lorg/apache/cordova/camera/ExifHelper;
    //   385: aload_0
    //   386: getfield 387	org/apache/cordova/camera/CameraLauncher:exifData	Lorg/apache/cordova/camera/ExifHelper;
    //   389: aload_1
    //   390: invokevirtual 390	org/apache/cordova/camera/ExifHelper:createInFile	(Ljava/lang/String;)V
    //   393: aload_0
    //   394: getfield 387	org/apache/cordova/camera/CameraLauncher:exifData	Lorg/apache/cordova/camera/ExifHelper;
    //   397: invokevirtual 393	org/apache/cordova/camera/ExifHelper:readExifData	()V
    //   400: iload_3
    //   401: istore_2
    //   402: aload_0
    //   403: getfield 328	org/apache/cordova/camera/CameraLauncher:correctOrientation	Z
    //   406: ifeq +23 -> 429
    //   409: aload_0
    //   410: new 395	android/media/ExifInterface
    //   413: dup
    //   414: aload_1
    //   415: invokespecial 396	android/media/ExifInterface:<init>	(Ljava/lang/String;)V
    //   418: ldc_w 398
    //   421: iconst_0
    //   422: invokevirtual 402	android/media/ExifInterface:getAttributeInt	(Ljava/lang/String;I)I
    //   425: invokespecial 404	org/apache/cordova/camera/CameraLauncher:exifToDegrees	(I)I
    //   428: istore_2
    //   429: new 406	android/graphics/BitmapFactory$Options
    //   432: dup
    //   433: invokespecial 407	android/graphics/BitmapFactory$Options:<init>	()V
    //   436: astore 9
    //   438: aload 9
    //   440: iconst_1
    //   441: putfield 410	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   444: aconst_null
    //   445: astore 8
    //   447: aload 7
    //   449: invokevirtual 224	android/net/Uri:toString	()Ljava/lang/String;
    //   452: aload_0
    //   453: getfield 193	org/apache/cordova/camera/CameraLauncher:cordova	Lorg/apache/cordova/CordovaInterface;
    //   456: invokestatic 332	org/apache/cordova/camera/FileHelper:getInputStreamFromUriString	(Ljava/lang/String;Lorg/apache/cordova/CordovaInterface;)Ljava/io/InputStream;
    //   459: astore_1
    //   460: aload_1
    //   461: astore 8
    //   463: aload_1
    //   464: aconst_null
    //   465: aload 9
    //   467: invokestatic 413	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   470: pop
    //   471: aload_1
    //   472: ifnull +7 -> 479
    //   475: aload_1
    //   476: invokevirtual 341	java/io/InputStream:close	()V
    //   479: aload 9
    //   481: getfield 416	android/graphics/BitmapFactory$Options:outWidth	I
    //   484: ifeq +13 -> 497
    //   487: aload 9
    //   489: getfield 419	android/graphics/BitmapFactory$Options:outHeight	I
    //   492: istore_3
    //   493: iload_3
    //   494: ifne +143 -> 637
    //   497: aconst_null
    //   498: astore_1
    //   499: aload 6
    //   501: ifnull -421 -> 80
    //   504: aload 6
    //   506: invokevirtual 235	java/io/File:delete	()Z
    //   509: pop
    //   510: aconst_null
    //   511: areturn
    //   512: ldc 56
    //   514: astore 6
    //   516: goto -235 -> 281
    //   519: astore_1
    //   520: ldc 47
    //   522: new 166	java/lang/StringBuilder
    //   525: dup
    //   526: invokespecial 167	java/lang/StringBuilder:<init>	()V
    //   529: ldc_w 421
    //   532: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   535: aload_1
    //   536: invokevirtual 422	java/lang/Exception:toString	()Ljava/lang/String;
    //   539: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   542: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   545: invokestatic 425	org/apache/cordova/LOG:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   548: iconst_0
    //   549: istore_2
    //   550: goto -121 -> 429
    //   553: astore_1
    //   554: ldc 47
    //   556: new 166	java/lang/StringBuilder
    //   559: dup
    //   560: invokespecial 167	java/lang/StringBuilder:<init>	()V
    //   563: ldc_w 427
    //   566: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   569: aload_1
    //   570: invokevirtual 422	java/lang/Exception:toString	()Ljava/lang/String;
    //   573: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   576: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   579: invokestatic 430	org/apache/cordova/LOG:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   582: aconst_null
    //   583: areturn
    //   584: astore 8
    //   586: ldc 47
    //   588: ldc_w 343
    //   591: invokestatic 348	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   594: goto -115 -> 479
    //   597: astore_1
    //   598: aload 6
    //   600: ifnull +9 -> 609
    //   603: aload 6
    //   605: invokevirtual 235	java/io/File:delete	()Z
    //   608: pop
    //   609: aload_1
    //   610: athrow
    //   611: astore_1
    //   612: aload 8
    //   614: ifnull +8 -> 622
    //   617: aload 8
    //   619: invokevirtual 341	java/io/InputStream:close	()V
    //   622: aload_1
    //   623: athrow
    //   624: astore 7
    //   626: ldc 47
    //   628: ldc_w 343
    //   631: invokestatic 348	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   634: goto -12 -> 622
    //   637: aload_0
    //   638: getfield 324	org/apache/cordova/camera/CameraLauncher:targetWidth	I
    //   641: ifgt +322 -> 963
    //   644: aload_0
    //   645: getfield 326	org/apache/cordova/camera/CameraLauncher:targetHeight	I
    //   648: ifgt +315 -> 963
    //   651: aload_0
    //   652: aload 9
    //   654: getfield 416	android/graphics/BitmapFactory$Options:outWidth	I
    //   657: putfield 324	org/apache/cordova/camera/CameraLauncher:targetWidth	I
    //   660: aload_0
    //   661: aload 9
    //   663: getfield 419	android/graphics/BitmapFactory$Options:outHeight	I
    //   666: putfield 326	org/apache/cordova/camera/CameraLauncher:targetHeight	I
    //   669: goto +294 -> 963
    //   672: aload 9
    //   674: getfield 419	android/graphics/BitmapFactory$Options:outHeight	I
    //   677: istore 5
    //   679: aload 9
    //   681: getfield 416	android/graphics/BitmapFactory$Options:outWidth	I
    //   684: istore 4
    //   686: iconst_1
    //   687: istore_3
    //   688: aload_0
    //   689: iload 5
    //   691: iload 4
    //   693: invokevirtual 434	org/apache/cordova/camera/CameraLauncher:calculateAspectRatio	(II)[I
    //   696: astore 8
    //   698: aload 9
    //   700: iconst_0
    //   701: putfield 410	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   704: aload 9
    //   706: iload 5
    //   708: iload 4
    //   710: aload 8
    //   712: iconst_0
    //   713: iaload
    //   714: aload 8
    //   716: iconst_1
    //   717: iaload
    //   718: invokestatic 436	org/apache/cordova/camera/CameraLauncher:calculateSampleSize	(IIII)I
    //   721: putfield 439	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   724: aload 7
    //   726: invokevirtual 224	android/net/Uri:toString	()Ljava/lang/String;
    //   729: aload_0
    //   730: getfield 193	org/apache/cordova/camera/CameraLauncher:cordova	Lorg/apache/cordova/CordovaInterface;
    //   733: invokestatic 332	org/apache/cordova/camera/FileHelper:getInputStreamFromUriString	(Ljava/lang/String;Lorg/apache/cordova/CordovaInterface;)Ljava/io/InputStream;
    //   736: astore 7
    //   738: aload 7
    //   740: astore_1
    //   741: aload 7
    //   743: aconst_null
    //   744: aload 9
    //   746: invokestatic 413	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   749: astore 9
    //   751: aload 7
    //   753: ifnull +8 -> 761
    //   756: aload 7
    //   758: invokevirtual 341	java/io/InputStream:close	()V
    //   761: aload 9
    //   763: ifnonnull +218 -> 981
    //   766: aconst_null
    //   767: astore_1
    //   768: aload 6
    //   770: ifnull -690 -> 80
    //   773: aload 6
    //   775: invokevirtual 235	java/io/File:delete	()Z
    //   778: pop
    //   779: aconst_null
    //   780: areturn
    //   781: aload 9
    //   783: getfield 416	android/graphics/BitmapFactory$Options:outWidth	I
    //   786: istore 5
    //   788: aload 9
    //   790: getfield 419	android/graphics/BitmapFactory$Options:outHeight	I
    //   793: istore 4
    //   795: goto -107 -> 688
    //   798: astore_1
    //   799: ldc 47
    //   801: ldc_w 343
    //   804: invokestatic 348	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   807: goto -46 -> 761
    //   810: astore 7
    //   812: aload_1
    //   813: ifnull +7 -> 820
    //   816: aload_1
    //   817: invokevirtual 341	java/io/InputStream:close	()V
    //   820: aload 7
    //   822: athrow
    //   823: astore_1
    //   824: ldc 47
    //   826: ldc_w 343
    //   829: invokestatic 348	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   832: goto -12 -> 820
    //   835: aload 9
    //   837: iload 4
    //   839: iload_3
    //   840: iconst_1
    //   841: invokestatic 443	android/graphics/Bitmap:createScaledBitmap	(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;
    //   844: astore 7
    //   846: aload 7
    //   848: aload 9
    //   850: if_acmpeq +8 -> 858
    //   853: aload 9
    //   855: invokevirtual 221	android/graphics/Bitmap:recycle	()V
    //   858: aload 7
    //   860: astore_1
    //   861: aload_0
    //   862: getfield 328	org/apache/cordova/camera/CameraLauncher:correctOrientation	Z
    //   865: ifeq +62 -> 927
    //   868: aload 7
    //   870: astore_1
    //   871: iload_2
    //   872: ifeq +55 -> 927
    //   875: new 445	android/graphics/Matrix
    //   878: dup
    //   879: invokespecial 446	android/graphics/Matrix:<init>	()V
    //   882: astore 8
    //   884: aload 8
    //   886: iload_2
    //   887: i2f
    //   888: invokevirtual 450	android/graphics/Matrix:setRotate	(F)V
    //   891: aload 7
    //   893: astore_1
    //   894: aload 7
    //   896: iconst_0
    //   897: iconst_0
    //   898: aload 7
    //   900: invokevirtual 453	android/graphics/Bitmap:getWidth	()I
    //   903: aload 7
    //   905: invokevirtual 456	android/graphics/Bitmap:getHeight	()I
    //   908: aload 8
    //   910: iconst_1
    //   911: invokestatic 460	android/graphics/Bitmap:createBitmap	(Landroid/graphics/Bitmap;IIIILandroid/graphics/Matrix;Z)Landroid/graphics/Bitmap;
    //   914: astore 7
    //   916: aload 7
    //   918: astore_1
    //   919: aload_0
    //   920: iconst_1
    //   921: putfield 462	org/apache/cordova/camera/CameraLauncher:orientationCorrected	Z
    //   924: aload 7
    //   926: astore_1
    //   927: aload 6
    //   929: ifnull +9 -> 938
    //   932: aload 6
    //   934: invokevirtual 235	java/io/File:delete	()Z
    //   937: pop
    //   938: aload_1
    //   939: areturn
    //   940: aload 8
    //   942: iconst_1
    //   943: iaload
    //   944: istore 4
    //   946: goto +45 -> 991
    //   949: astore 7
    //   951: aload_0
    //   952: iconst_0
    //   953: putfield 462	org/apache/cordova/camera/CameraLauncher:orientationCorrected	Z
    //   956: goto -29 -> 927
    //   959: astore_1
    //   960: goto -406 -> 554
    //   963: iconst_0
    //   964: istore_3
    //   965: iload_2
    //   966: bipush 90
    //   968: if_icmpeq -296 -> 672
    //   971: iload_2
    //   972: sipush 270
    //   975: if_icmpne -194 -> 781
    //   978: goto -306 -> 672
    //   981: iload_3
    //   982: ifne -42 -> 940
    //   985: aload 8
    //   987: iconst_0
    //   988: iaload
    //   989: istore 4
    //   991: iload_3
    //   992: ifne +11 -> 1003
    //   995: aload 8
    //   997: iconst_1
    //   998: iaload
    //   999: istore_3
    //   1000: goto -165 -> 835
    //   1003: aload 8
    //   1005: iconst_0
    //   1006: iaload
    //   1007: istore_3
    //   1008: goto -173 -> 835
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1011	0	this	CameraLauncher
    //   0	1011	1	paramString	String
    //   211	765	2	i	int
    //   213	795	3	j	int
    //   684	306	4	k	int
    //   677	110	5	m	int
    //   25	160	6	localObject1	Object
    //   191	1	6	localIOException1	IOException
    //   205	728	6	localObject2	Object
    //   28	420	7	localObject3	Object
    //   624	101	7	localIOException2	IOException
    //   736	21	7	localInputStream	InputStream
    //   810	11	7	localObject4	Object
    //   844	81	7	localBitmap	Bitmap
    //   949	1	7	localOutOfMemoryError	OutOfMemoryError
    //   22	440	8	localObject5	Object
    //   584	34	8	localIOException3	IOException
    //   696	308	8	localObject6	Object
    //   41	813	9	localObject7	Object
    //   31	122	10	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   72	77	82	java/io/IOException
    //   33	43	94	java/lang/OutOfMemoryError
    //   55	61	94	java/lang/OutOfMemoryError
    //   118	123	125	java/io/IOException
    //   33	43	136	java/lang/Exception
    //   55	61	136	java/lang/Exception
    //   160	165	167	java/io/IOException
    //   33	43	178	finally
    //   55	61	178	finally
    //   99	110	178	finally
    //   141	152	178	finally
    //   184	189	191	java/io/IOException
    //   341	400	519	java/lang/Exception
    //   402	429	519	java/lang/Exception
    //   214	224	553	java/lang/Exception
    //   229	277	553	java/lang/Exception
    //   281	324	553	java/lang/Exception
    //   475	479	584	java/io/IOException
    //   429	444	597	finally
    //   475	479	597	finally
    //   479	493	597	finally
    //   586	594	597	finally
    //   617	622	597	finally
    //   622	624	597	finally
    //   626	634	597	finally
    //   637	669	597	finally
    //   672	686	597	finally
    //   688	724	597	finally
    //   756	761	597	finally
    //   781	795	597	finally
    //   799	807	597	finally
    //   816	820	597	finally
    //   820	823	597	finally
    //   824	832	597	finally
    //   835	846	597	finally
    //   853	858	597	finally
    //   861	868	597	finally
    //   875	891	597	finally
    //   894	916	597	finally
    //   919	924	597	finally
    //   951	956	597	finally
    //   447	460	611	finally
    //   463	471	611	finally
    //   617	622	624	java/io/IOException
    //   756	761	798	java/io/IOException
    //   724	738	810	finally
    //   741	751	810	finally
    //   816	820	823	java/io/IOException
    //   894	916	949	java/lang/OutOfMemoryError
    //   919	924	949	java/lang/OutOfMemoryError
    //   324	339	959	java/lang/Exception
    //   520	548	959	java/lang/Exception
  }
  
  private String getTempDirectoryPath()
  {
    if (Environment.getExternalStorageState().equals("mounted")) {}
    for (File localFile = this.cordova.getActivity().getExternalCacheDir();; localFile = this.cordova.getActivity().getCacheDir())
    {
      localFile.mkdirs();
      return localFile.getAbsolutePath();
    }
  }
  
  private Uri getUriFromMediaStore()
  {
    Object localObject = new ContentValues();
    ((ContentValues)localObject).put("mime_type", "image/jpeg");
    try
    {
      Uri localUri = this.cordova.getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (ContentValues)localObject);
      return localUri;
    }
    catch (RuntimeException localRuntimeException2)
    {
      LOG.d("CameraLauncher", "Can't write to external media storage.");
      try
      {
        localObject = this.cordova.getActivity().getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, (ContentValues)localObject);
        return (Uri)localObject;
      }
      catch (RuntimeException localRuntimeException1)
      {
        LOG.d("CameraLauncher", "Can't write to internal media storage.");
      }
    }
    return null;
  }
  
  private String outputModifiedBitmap(Bitmap paramBitmap, Uri paramUri)
    throws IOException
  {
    paramUri = FileHelper.getRealPath(paramUri, this.cordova);
    Object localObject;
    FileOutputStream localFileOutputStream;
    if (paramUri != null)
    {
      paramUri = paramUri.substring(paramUri.lastIndexOf('/') + 1);
      new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
      localObject = getTempDirectoryPath() + "/" + paramUri;
      localFileOutputStream = new FileOutputStream((String)localObject);
      if (this.encodingType != 0) {
        break label210;
      }
    }
    label210:
    for (paramUri = Bitmap.CompressFormat.JPEG;; paramUri = Bitmap.CompressFormat.PNG)
    {
      paramBitmap.compress(paramUri, this.mQuality, localFileOutputStream);
      localFileOutputStream.close();
      if ((this.exifData != null) && (this.encodingType == 0)) {}
      try
      {
        if ((this.correctOrientation) && (this.orientationCorrected)) {
          this.exifData.resetOrientation();
        }
        this.exifData.createOutFile((String)localObject);
        this.exifData.writeExifData();
        this.exifData = null;
        return (String)localObject;
      }
      catch (IOException paramBitmap)
      {
        ThrowableExtension.printStackTrace(paramBitmap);
      }
      localObject = new StringBuilder().append("modified.");
      if (this.encodingType == 0) {}
      for (paramUri = "jpg";; paramUri = "png")
      {
        paramUri = paramUri;
        break;
      }
    }
    return (String)localObject;
  }
  
  private void performCrop(Uri paramUri, int paramInt, Intent paramIntent)
  {
    try
    {
      Intent localIntent = new Intent("com.android.camera.action.CROP");
      localIntent.setDataAndType(paramUri, "image/*");
      localIntent.putExtra("crop", "true");
      if (this.targetWidth > 0) {
        localIntent.putExtra("outputX", this.targetWidth);
      }
      if (this.targetHeight > 0) {
        localIntent.putExtra("outputY", this.targetHeight);
      }
      if ((this.targetHeight > 0) && (this.targetWidth > 0) && (this.targetWidth == this.targetHeight))
      {
        localIntent.putExtra("aspectX", 1);
        localIntent.putExtra("aspectY", 1);
      }
      this.croppedUri = Uri.fromFile(createCaptureFile(this.encodingType, System.currentTimeMillis() + ""));
      localIntent.addFlags(1);
      localIntent.addFlags(2);
      localIntent.putExtra("output", this.croppedUri);
      if (this.cordova != null) {
        this.cordova.startActivityForResult(this, localIntent, paramInt + 100);
      }
      return;
    }
    catch (ActivityNotFoundException paramUri)
    {
      LOG.e("CameraLauncher", "Crop operation not supported on this device");
      try
      {
        processResultFromCamera(paramInt, paramIntent);
        return;
      }
      catch (IOException paramUri)
      {
        ThrowableExtension.printStackTrace(paramUri);
        LOG.e("CameraLauncher", "Unable to write to file");
      }
    }
  }
  
  private void processResultFromCamera(int paramInt, Intent paramIntent)
    throws IOException
  {
    int j = 0;
    ExifHelper localExifHelper = new ExifHelper();
    int i;
    if ((this.allowEdit) && (this.croppedUri != null))
    {
      localObject1 = FileHelper.stripFileProtocol(this.croppedUri.toString());
      i = j;
      if (this.encodingType != 0) {}
    }
    for (;;)
    {
      try
      {
        localExifHelper.createInFile((String)localObject1);
        localExifHelper.readExifData();
        i = localExifHelper.getOrientation();
        localObject2 = null;
        Uri localUri = null;
        if (this.saveToPhotoAlbum)
        {
          localUri = Uri.fromFile(new File(getPicturesPath()));
          if ((this.allowEdit) && (this.croppedUri != null))
          {
            writeUncompressedImage(this.croppedUri, localUri);
            refreshGallery(localUri);
          }
        }
        else
        {
          if (paramInt != 0) {
            break label267;
          }
          localObject2 = getScaledAndRotatedBitmap((String)localObject1);
          localObject1 = localObject2;
          if (localObject2 == null) {
            localObject1 = (Bitmap)paramIntent.getExtras().get("data");
          }
          if (localObject1 != null) {
            continue;
          }
          LOG.d("CameraLauncher", "I either have a null image path or bitmap");
          failPicture("Unable to create bitmap!");
          return;
          localObject1 = this.imageUri.getFilePath();
        }
      }
      catch (IOException localIOException)
      {
        ThrowableExtension.printStackTrace(localIOException);
        i = j;
        continue;
        writeUncompressedImage(this.imageUri.getFileUri(), localIOException);
        continue;
        processPicture((Bitmap)localObject1, this.encodingType);
        paramIntent = (Intent)localObject1;
        if (!this.saveToPhotoAlbum)
        {
          checkForDuplicateImage(0);
          paramIntent = (Intent)localObject1;
        }
        cleanup(1, this.imageUri.getFileUri(), localIOException, paramIntent);
        return;
      }
      label267:
      if ((paramInt != 1) && (paramInt != 2)) {
        break label606;
      }
      if ((this.targetHeight != -1) || (this.targetWidth != -1) || (this.mQuality != 100) || (this.correctOrientation)) {
        break label436;
      }
      if (!this.saveToPhotoAlbum) {
        break label334;
      }
      this.callbackContext.success(localIOException.toString());
      paramIntent = (Intent)localObject2;
    }
    label334:
    paramIntent = Uri.fromFile(createCaptureFile(this.encodingType, System.currentTimeMillis() + ""));
    if ((this.allowEdit) && (this.croppedUri != null)) {
      writeUncompressedImage(Uri.fromFile(new File(getFileNameFromUri(this.croppedUri))), paramIntent);
    }
    for (;;)
    {
      this.callbackContext.success(paramIntent.toString());
      paramIntent = (Intent)localObject2;
      break;
      writeUncompressedImage(this.imageUri.getFileUri(), paramIntent);
    }
    label436:
    Object localObject2 = Uri.fromFile(createCaptureFile(this.encodingType, System.currentTimeMillis() + ""));
    Object localObject1 = getScaledAndRotatedBitmap((String)localObject1);
    if (localObject1 == null)
    {
      LOG.d("CameraLauncher", "I either have a null image path or bitmap");
      failPicture("Unable to create bitmap!");
      return;
    }
    OutputStream localOutputStream = this.cordova.getActivity().getContentResolver().openOutputStream((Uri)localObject2);
    if (this.encodingType == 0) {}
    for (paramIntent = Bitmap.CompressFormat.JPEG;; paramIntent = Bitmap.CompressFormat.PNG)
    {
      ((Bitmap)localObject1).compress(paramIntent, this.mQuality, localOutputStream);
      localOutputStream.close();
      if (this.encodingType == 0)
      {
        paramIntent = ((Uri)localObject2).getPath();
        if (i != 1) {
          localExifHelper.resetOrientation();
        }
        localExifHelper.createOutFile(paramIntent);
        localExifHelper.writeExifData();
      }
      this.callbackContext.success(((Uri)localObject2).toString());
      paramIntent = (Intent)localObject1;
      break;
    }
    label606:
    throw new IllegalStateException();
  }
  
  private void processResultFromGallery(int paramInt, Intent paramIntent)
  {
    Object localObject1 = paramIntent.getData();
    paramIntent = (Intent)localObject1;
    String str1;
    Object localObject2;
    String str2;
    if (localObject1 == null)
    {
      if (this.croppedUri != null) {
        paramIntent = this.croppedUri;
      }
    }
    else
    {
      str1 = FileHelper.getRealPath(paramIntent, this.cordova);
      LOG.d("CameraLauncher", "File location is: " + str1);
      localObject2 = paramIntent.toString();
      str2 = FileHelper.getMimeType((String)localObject2, this.cordova);
      if ((this.mediaType != 1) && (("image/jpeg".equalsIgnoreCase(str2)) || ("image/png".equalsIgnoreCase(str2)))) {
        break label122;
      }
      this.callbackContext.success(str1);
      return;
    }
    failPicture("null data from photo library");
    return;
    label122:
    if ((this.targetHeight == -1) && (this.targetWidth == -1) && ((paramInt == 1) || (paramInt == 2)) && (!this.correctOrientation) && (str2 != null) && (str2.equalsIgnoreCase(getMimetypeForFormat(this.encodingType))))
    {
      this.callbackContext.success((String)localObject2);
      return;
    }
    localObject1 = null;
    try
    {
      localObject2 = getScaledAndRotatedBitmap((String)localObject2);
      localObject1 = localObject2;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        ThrowableExtension.printStackTrace(localIOException);
      }
    }
    if (localObject1 == null)
    {
      LOG.d("CameraLauncher", "I either have a null image path or bitmap");
      failPicture("Unable to create bitmap!");
      return;
    }
    if (paramInt == 0) {
      processPicture((Bitmap)localObject1, this.encodingType);
    }
    for (;;)
    {
      if (localObject1 != null) {
        ((Bitmap)localObject1).recycle();
      }
      System.gc();
      return;
      if ((paramInt == 1) || (paramInt == 2)) {
        if (((this.targetHeight > 0) && (this.targetWidth > 0)) || ((this.correctOrientation) && (this.orientationCorrected)) || (!str2.equalsIgnoreCase(getMimetypeForFormat(this.encodingType)))) {
          try
          {
            paramIntent = outputModifiedBitmap((Bitmap)localObject1, paramIntent);
            this.callbackContext.success("file://" + paramIntent + "?" + System.currentTimeMillis());
          }
          catch (Exception paramIntent)
          {
            ThrowableExtension.printStackTrace(paramIntent);
            failPicture("Error retrieving image.");
          }
        } else {
          this.callbackContext.success(str1);
        }
      }
    }
  }
  
  private Cursor queryImgDB(Uri paramUri)
  {
    return this.cordova.getActivity().getContentResolver().query(paramUri, new String[] { "_id" }, null, null, null);
  }
  
  private void refreshGallery(Uri paramUri)
  {
    Intent localIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
    localIntent.setData(paramUri);
    this.cordova.getActivity().sendBroadcast(localIntent);
  }
  
  private void scanForGallery(Uri paramUri)
  {
    this.scanMe = paramUri;
    if (this.conn != null) {
      this.conn.disconnect();
    }
    this.conn = new MediaScannerConnection(this.cordova.getActivity().getApplicationContext(), this);
    this.conn.connect();
  }
  
  private Uri whichContentStore()
  {
    if (Environment.getExternalStorageState().equals("mounted")) {
      return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    }
    return MediaStore.Images.Media.INTERNAL_CONTENT_URI;
  }
  
  private void writeUncompressedImage(Uri paramUri1, Uri paramUri2)
    throws FileNotFoundException, IOException
  {
    writeUncompressedImage(new FileInputStream(FileHelper.stripFileProtocol(paramUri1.toString())), paramUri2);
  }
  
  private void writeUncompressedImage(InputStream paramInputStream, Uri paramUri)
    throws FileNotFoundException, IOException
  {
    localUri = null;
    try
    {
      paramUri = this.cordova.getActivity().getContentResolver().openOutputStream(paramUri);
      localUri = paramUri;
      byte[] arrayOfByte = new byte['က'];
      for (;;)
      {
        localUri = paramUri;
        int i = paramInputStream.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        localUri = paramUri;
        paramUri.write(arrayOfByte, 0, i);
      }
      try
      {
        localUri.close();
      }
      catch (IOException localIOException)
      {
        try
        {
          paramInputStream.close();
          throw paramUri;
          localUri = paramUri;
          paramUri.flush();
          if (paramUri != null) {}
          try
          {
            paramUri.close();
          }
          catch (IOException paramUri)
          {
            for (;;)
            {
              try
              {
                paramInputStream.close();
                return;
              }
              catch (IOException paramInputStream)
              {
                LOG.d("CameraLauncher", "Exception while closing file input stream.");
                return;
              }
              paramUri = paramUri;
              LOG.d("CameraLauncher", "Exception while closing output stream.");
            }
          }
          localIOException = localIOException;
          LOG.d("CameraLauncher", "Exception while closing output stream.");
        }
        catch (IOException paramInputStream)
        {
          for (;;)
          {
            LOG.d("CameraLauncher", "Exception while closing file input stream.");
          }
        }
      }
    }
    finally
    {
      if (localUri == null) {}
    }
  }
  
  public int[] calculateAspectRatio(int paramInt1, int paramInt2)
  {
    int j = this.targetWidth;
    int k = this.targetHeight;
    int i;
    if ((j <= 0) && (k <= 0))
    {
      j = paramInt1;
      i = paramInt2;
    }
    for (;;)
    {
      return new int[] { j, i };
      if ((j > 0) && (k <= 0))
      {
        i = (int)(j / paramInt1 * paramInt2);
      }
      else if ((j <= 0) && (k > 0))
      {
        j = (int)(k / paramInt2 * paramInt1);
        i = k;
      }
      else
      {
        double d1 = j / k;
        double d2 = paramInt1 / paramInt2;
        if (d2 > d1)
        {
          i = j * paramInt2 / paramInt1;
        }
        else
        {
          i = k;
          if (d2 < d1)
          {
            j = k * paramInt1 / paramInt2;
            i = k;
          }
        }
      }
    }
  }
  
  public void callTakePicture(int paramInt1, int paramInt2)
  {
    int i;
    if ((PermissionHelper.hasPermission(this, "android.permission.READ_EXTERNAL_STORAGE")) && (PermissionHelper.hasPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE")))
    {
      i = 1;
      bool2 = PermissionHelper.hasPermission(this, "android.permission.CAMERA");
      bool1 = bool2;
      if (!bool2) {
        bool2 = true;
      }
    }
    for (;;)
    {
      try
      {
        String[] arrayOfString = this.cordova.getActivity().getPackageManager().getPackageInfo(this.cordova.getActivity().getPackageName(), 4096).requestedPermissions;
        bool1 = bool2;
        if (arrayOfString != null)
        {
          int k = arrayOfString.length;
          j = 0;
          bool1 = bool2;
          if (j < k)
          {
            bool1 = arrayOfString[j].equals("android.permission.CAMERA");
            if (!bool1) {
              continue;
            }
            bool1 = false;
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        int j;
        bool1 = bool2;
        continue;
      }
      if ((!bool1) || (i == 0)) {
        continue;
      }
      takePicture(paramInt1, paramInt2);
      return;
      i = 0;
      break;
      j += 1;
    }
    if ((i != 0) && (!bool1))
    {
      PermissionHelper.requestPermission(this, 0, "android.permission.CAMERA");
      return;
    }
    if ((i == 0) && (bool1))
    {
      PermissionHelper.requestPermissions(this, 0, new String[] { "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE" });
      return;
    }
    PermissionHelper.requestPermissions(this, 0, permissions);
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    this.callbackContext = paramCallbackContext;
    this.applicationId = ((String)BuildHelper.getBuildConfigValue(this.cordova.getActivity(), "APPLICATION_ID"));
    this.applicationId = this.preferences.getString("applicationId", this.applicationId);
    if (paramString.equals("takePicture"))
    {
      this.srcType = 1;
      this.destType = 1;
      this.saveToPhotoAlbum = false;
      this.targetHeight = 0;
      this.targetWidth = 0;
      this.encodingType = 0;
      this.mediaType = 0;
      this.mQuality = 50;
      this.destType = paramJSONArray.getInt(1);
      this.srcType = paramJSONArray.getInt(2);
      this.mQuality = paramJSONArray.getInt(0);
      this.targetWidth = paramJSONArray.getInt(3);
      this.targetHeight = paramJSONArray.getInt(4);
      this.encodingType = paramJSONArray.getInt(5);
      this.mediaType = paramJSONArray.getInt(6);
      this.allowEdit = paramJSONArray.getBoolean(7);
      this.correctOrientation = paramJSONArray.getBoolean(8);
      this.saveToPhotoAlbum = paramJSONArray.getBoolean(9);
      if (this.targetWidth < 1) {
        this.targetWidth = -1;
      }
      if (this.targetHeight < 1) {
        this.targetHeight = -1;
      }
      if ((this.targetHeight == -1) && (this.targetWidth == -1) && (this.mQuality == 100) && (!this.correctOrientation) && (this.encodingType == 1) && (this.srcType == 1)) {
        this.encodingType = 0;
      }
      for (;;)
      {
        try
        {
          if (this.srcType == 1)
          {
            callTakePicture(this.destType, this.encodingType);
            paramString = new PluginResult(PluginResult.Status.NO_RESULT);
            paramString.setKeepCallback(true);
            paramCallbackContext.sendPluginResult(paramString);
            return true;
          }
          if ((this.srcType == 0) || (this.srcType == 2)) {
            if (!PermissionHelper.hasPermission(this, "android.permission.READ_EXTERNAL_STORAGE")) {
              PermissionHelper.requestPermission(this, 1, "android.permission.READ_EXTERNAL_STORAGE");
            } else {
              getImage(this.srcType, this.destType, this.encodingType);
            }
          }
        }
        catch (IllegalArgumentException paramString)
        {
          paramCallbackContext.error("Illegal Argument Exception");
          paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
          return true;
        }
      }
    }
    return false;
  }
  
  public void failPicture(String paramString)
  {
    this.callbackContext.error(paramString);
  }
  
  public void getImage(int paramInt1, int paramInt2, int paramInt3)
  {
    Intent localIntent = new Intent();
    String str = "Get Picture";
    this.croppedUri = null;
    if (this.mediaType == 0)
    {
      localIntent.setType("image/*");
      if (this.allowEdit)
      {
        localIntent.setAction("android.intent.action.PICK");
        localIntent.putExtra("crop", "true");
        if (this.targetWidth > 0) {
          localIntent.putExtra("outputX", this.targetWidth);
        }
        if (this.targetHeight > 0) {
          localIntent.putExtra("outputY", this.targetHeight);
        }
        if ((this.targetHeight > 0) && (this.targetWidth > 0) && (this.targetWidth == this.targetHeight))
        {
          localIntent.putExtra("aspectX", 1);
          localIntent.putExtra("aspectY", 1);
        }
        this.croppedUri = Uri.fromFile(createCaptureFile(0));
        localIntent.putExtra("output", this.croppedUri);
      }
    }
    for (;;)
    {
      if (this.cordova != null) {
        this.cordova.startActivityForResult(this, Intent.createChooser(localIntent, new String(str)), (paramInt1 + 1) * 16 + paramInt2 + 1);
      }
      return;
      localIntent.setAction("android.intent.action.GET_CONTENT");
      localIntent.addCategory("android.intent.category.OPENABLE");
      continue;
      if (this.mediaType == 1)
      {
        localIntent.setType("video/*");
        str = "Get Video";
        localIntent.setAction("android.intent.action.GET_CONTENT");
        localIntent.addCategory("android.intent.category.OPENABLE");
      }
      else if (this.mediaType == 2)
      {
        localIntent.setType("*/*");
        str = "Get All";
        localIntent.setAction("android.intent.action.GET_CONTENT");
        localIntent.addCategory("android.intent.category.OPENABLE");
      }
    }
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, final Intent paramIntent)
  {
    int i = paramInt1 / 16 - 1;
    final int j = paramInt1 % 16 - 1;
    if (paramInt1 >= 100) {
      if (paramInt2 != -1) {}
    }
    do
    {
      try
      {
        processResultFromCamera(paramInt1 - 100, paramIntent);
        return;
      }
      catch (IOException paramIntent)
      {
        ThrowableExtension.printStackTrace(paramIntent);
        LOG.e("CameraLauncher", "Unable to write to file");
        return;
      }
      if (paramInt2 == 0)
      {
        failPicture("No Image Selected");
        return;
      }
      failPicture("Did not complete!");
      return;
      if (i == 1)
      {
        if (paramInt2 == -1)
        {
          try
          {
            if (this.allowEdit)
            {
              performCrop(FileProvider.getUriForFile(this.cordova.getActivity(), this.applicationId + ".provider", createCaptureFile(this.encodingType)), j, paramIntent);
              return;
            }
          }
          catch (IOException paramIntent)
          {
            ThrowableExtension.printStackTrace(paramIntent);
            failPicture("Error capturing image.");
            return;
          }
          processResultFromCamera(j, paramIntent);
          return;
        }
        if (paramInt2 == 0)
        {
          failPicture("No Image Selected");
          return;
        }
        failPicture("Did not complete!");
        return;
      }
    } while ((i != 0) && (i != 2));
    if ((paramInt2 == -1) && (paramIntent != null))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          CameraLauncher.this.processResultFromGallery(j, paramIntent);
        }
      });
      return;
    }
    if (paramInt2 == 0)
    {
      failPicture("No Image Selected");
      return;
    }
    failPicture("Selection did not complete!");
  }
  
  public void onMediaScannerConnected()
  {
    try
    {
      this.conn.scanFile(this.scanMe.toString(), "image/*");
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      LOG.e("CameraLauncher", "Can't scan file in MediaScanner after taking picture");
    }
  }
  
  public void onRequestPermissionResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
    throws JSONException
  {
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      if (paramArrayOfInt[i] == -1)
      {
        this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, 20));
        return;
      }
      i += 1;
    }
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      takePicture(this.destType, this.encodingType);
      return;
    }
    getImage(this.srcType, this.destType, this.encodingType);
  }
  
  public void onRestoreStateForActivityResult(Bundle paramBundle, CallbackContext paramCallbackContext)
  {
    this.destType = paramBundle.getInt("destType");
    this.srcType = paramBundle.getInt("srcType");
    this.mQuality = paramBundle.getInt("mQuality");
    this.targetWidth = paramBundle.getInt("targetWidth");
    this.targetHeight = paramBundle.getInt("targetHeight");
    this.encodingType = paramBundle.getInt("encodingType");
    this.mediaType = paramBundle.getInt("mediaType");
    this.numPics = paramBundle.getInt("numPics");
    this.allowEdit = paramBundle.getBoolean("allowEdit");
    this.correctOrientation = paramBundle.getBoolean("correctOrientation");
    this.saveToPhotoAlbum = paramBundle.getBoolean("saveToPhotoAlbum");
    if (paramBundle.containsKey("croppedUri")) {
      this.croppedUri = Uri.parse(paramBundle.getString("croppedUri"));
    }
    if (paramBundle.containsKey("imageUri")) {
      this.imageUri = new CordovaUri(Uri.parse(paramBundle.getString("imageUri")));
    }
    this.callbackContext = paramCallbackContext;
  }
  
  public Bundle onSaveInstanceState()
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("destType", this.destType);
    localBundle.putInt("srcType", this.srcType);
    localBundle.putInt("mQuality", this.mQuality);
    localBundle.putInt("targetWidth", this.targetWidth);
    localBundle.putInt("targetHeight", this.targetHeight);
    localBundle.putInt("encodingType", this.encodingType);
    localBundle.putInt("mediaType", this.mediaType);
    localBundle.putInt("numPics", this.numPics);
    localBundle.putBoolean("allowEdit", this.allowEdit);
    localBundle.putBoolean("correctOrientation", this.correctOrientation);
    localBundle.putBoolean("saveToPhotoAlbum", this.saveToPhotoAlbum);
    if (this.croppedUri != null) {
      localBundle.putString("croppedUri", this.croppedUri.toString());
    }
    if (this.imageUri != null) {
      localBundle.putString("imageUri", this.imageUri.getFileUri().toString());
    }
    return localBundle;
  }
  
  public void onScanCompleted(String paramString, Uri paramUri)
  {
    this.conn.disconnect();
  }
  
  public void processPicture(Bitmap paramBitmap, int paramInt)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    Bitmap.CompressFormat localCompressFormat;
    if (paramInt == 0) {
      localCompressFormat = Bitmap.CompressFormat.JPEG;
    }
    try
    {
      for (;;)
      {
        if (paramBitmap.compress(localCompressFormat, this.mQuality, localByteArrayOutputStream))
        {
          paramBitmap = new String(Base64.encode(localByteArrayOutputStream.toByteArray(), 2));
          this.callbackContext.success(paramBitmap);
        }
        return;
        localCompressFormat = Bitmap.CompressFormat.PNG;
      }
    }
    catch (Exception paramBitmap)
    {
      for (;;)
      {
        failPicture("Error compressing image.");
      }
    }
  }
  
  public void takePicture(int paramInt1, int paramInt2)
  {
    this.numPics = queryImgDB(whichContentStore()).getCount();
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    File localFile = createCaptureFile(paramInt2);
    this.imageUri = new CordovaUri(FileProvider.getUriForFile(this.cordova.getActivity(), this.applicationId + ".provider", localFile));
    localIntent.putExtra("output", this.imageUri.getCorrectUri());
    localIntent.addFlags(2);
    if (this.cordova != null)
    {
      if (localIntent.resolveActivity(this.cordova.getActivity().getPackageManager()) != null) {
        this.cordova.startActivityForResult(this, localIntent, paramInt1 + 32 + 1);
      }
    }
    else {
      return;
    }
    LOG.d("CameraLauncher", "Error: You don't have a default camera.  Your device may not be CTS complaint.");
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\camera\CameraLauncher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */