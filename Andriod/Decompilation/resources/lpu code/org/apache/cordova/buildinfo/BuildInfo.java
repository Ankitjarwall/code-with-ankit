package org.apache.cordova.buildinfo;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BuildInfo
  extends CordovaPlugin
{
  private static final String TAG = "BuildInfo";
  private static JSONObject mBuildInfoCache;
  
  private static String convertLongToDateTimeString(long paramLong)
  {
    return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(Long.valueOf(paramLong));
  }
  
  private static Field getClassField(Class paramClass, String paramString)
  {
    try
    {
      paramClass = paramClass.getField(paramString);
      return paramClass;
    }
    catch (NoSuchFieldException paramClass)
    {
      ThrowableExtension.printStackTrace(paramClass);
    }
    return null;
  }
  
  private static boolean getClassFieldBoolean(Class paramClass, String paramString, boolean paramBoolean)
  {
    paramString = getClassField(paramClass, paramString);
    boolean bool = paramBoolean;
    if (paramString != null) {}
    try
    {
      bool = paramString.getBoolean(paramClass);
      return bool;
    }
    catch (IllegalAccessException paramClass)
    {
      ThrowableExtension.printStackTrace(paramClass);
    }
    return paramBoolean;
  }
  
  private static int getClassFieldInt(Class paramClass, String paramString, int paramInt)
  {
    paramString = getClassField(paramClass, paramString);
    int i = paramInt;
    if (paramString != null) {}
    try
    {
      i = paramString.getInt(paramClass);
      return i;
    }
    catch (IllegalAccessException paramClass)
    {
      ThrowableExtension.printStackTrace(paramClass);
    }
    return paramInt;
  }
  
  private static long getClassFieldLong(Class paramClass, String paramString, long paramLong)
  {
    paramString = getClassField(paramClass, paramString);
    long l = paramLong;
    if (paramString != null) {}
    try
    {
      l = paramString.getLong(paramClass);
      return l;
    }
    catch (IllegalAccessException paramClass)
    {
      ThrowableExtension.printStackTrace(paramClass);
    }
    return paramLong;
  }
  
  private static String getClassFieldString(Class paramClass, String paramString1, String paramString2)
  {
    Field localField = getClassField(paramClass, paramString1);
    paramString1 = paramString2;
    if (localField != null) {}
    try
    {
      paramString1 = (String)localField.get(paramClass);
      return paramString1;
    }
    catch (IllegalAccessException paramClass)
    {
      ThrowableExtension.printStackTrace(paramClass);
    }
    return paramString2;
  }
  
  /* Error */
  private void init(String paramString, CallbackContext paramCallbackContext)
  {
    // Byte code:
    //   0: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   3: ifnull +11 -> 14
    //   6: aload_2
    //   7: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   10: invokevirtual 98	org/apache/cordova/CallbackContext:success	(Lorg/json/JSONObject;)V
    //   13: return
    //   14: aload_0
    //   15: getfield 102	org/apache/cordova/buildinfo/BuildInfo:cordova	Lorg/apache/cordova/CordovaInterface;
    //   18: invokeinterface 108 1 0
    //   23: astore 15
    //   25: aload 15
    //   27: invokevirtual 114	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   30: astore 13
    //   32: aload 13
    //   34: astore 11
    //   36: ldc 116
    //   38: astore 12
    //   40: lconst_0
    //   41: lstore 5
    //   43: aload 15
    //   45: invokevirtual 120	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   48: astore 14
    //   50: lload 5
    //   52: lstore_3
    //   53: aload 14
    //   55: aload 13
    //   57: iconst_1
    //   58: invokevirtual 126	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   61: astore 16
    //   63: lload 5
    //   65: lstore_3
    //   66: aload 16
    //   68: getfield 132	android/content/pm/PackageInfo:firstInstallTime	J
    //   71: lstore 5
    //   73: aload 12
    //   75: astore 10
    //   77: lload 5
    //   79: lstore 7
    //   81: lload 5
    //   83: lstore_3
    //   84: aload 16
    //   86: getfield 136	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   89: ifnull +22 -> 111
    //   92: lload 5
    //   94: lstore_3
    //   95: aload 16
    //   97: getfield 136	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   100: aload 14
    //   102: invokevirtual 142	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   105: astore 10
    //   107: lload 5
    //   109: lstore 7
    //   111: aconst_null
    //   112: astore 14
    //   114: aload_1
    //   115: astore 12
    //   117: aload_1
    //   118: ifnonnull +25 -> 143
    //   121: new 144	java/lang/StringBuilder
    //   124: dup
    //   125: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   128: aload 13
    //   130: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: ldc -105
    //   135: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   141: astore 12
    //   143: aload 12
    //   145: invokestatic 158	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   148: astore_1
    //   149: aload 11
    //   151: astore 12
    //   153: aload_1
    //   154: astore 11
    //   156: aload_1
    //   157: ifnonnull +43 -> 200
    //   160: aload 15
    //   162: invokevirtual 164	java/lang/Object:getClass	()Ljava/lang/Class;
    //   165: invokevirtual 168	java/lang/Class:getPackage	()Ljava/lang/Package;
    //   168: invokevirtual 173	java/lang/Package:getName	()Ljava/lang/String;
    //   171: astore 12
    //   173: new 144	java/lang/StringBuilder
    //   176: dup
    //   177: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   180: aload 12
    //   182: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: ldc -105
    //   187: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   193: astore_1
    //   194: aload_1
    //   195: invokestatic 158	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   198: astore 11
    //   200: new 175	org/json/JSONObject
    //   203: dup
    //   204: invokespecial 176	org/json/JSONObject:<init>	()V
    //   207: putstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   210: aload 11
    //   212: ldc -78
    //   214: iconst_0
    //   215: invokestatic 180	org/apache/cordova/buildinfo/BuildInfo:getClassFieldBoolean	(Ljava/lang/Class;Ljava/lang/String;Z)Z
    //   218: istore 9
    //   220: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   223: ldc -74
    //   225: aload 13
    //   227: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   230: pop
    //   231: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   234: ldc -68
    //   236: aload 12
    //   238: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   241: pop
    //   242: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   245: ldc -66
    //   247: aload 10
    //   249: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   252: pop
    //   253: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   256: ldc -64
    //   258: aload 10
    //   260: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   263: pop
    //   264: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   267: ldc -62
    //   269: aload 11
    //   271: ldc -60
    //   273: ldc 116
    //   275: invokestatic 198	org/apache/cordova/buildinfo/BuildInfo:getClassFieldString	(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   278: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   281: pop
    //   282: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   285: ldc -56
    //   287: aload 11
    //   289: ldc -54
    //   291: iconst_0
    //   292: invokestatic 204	org/apache/cordova/buildinfo/BuildInfo:getClassFieldInt	(Ljava/lang/Class;Ljava/lang/String;I)I
    //   295: invokevirtual 207	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   298: pop
    //   299: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   302: ldc -47
    //   304: iload 9
    //   306: invokevirtual 212	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   309: pop
    //   310: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   313: ldc -42
    //   315: aload 11
    //   317: ldc -40
    //   319: lconst_0
    //   320: invokestatic 218	org/apache/cordova/buildinfo/BuildInfo:getClassFieldLong	(Ljava/lang/Class;Ljava/lang/String;J)J
    //   323: invokestatic 220	org/apache/cordova/buildinfo/BuildInfo:convertLongToDateTimeString	(J)Ljava/lang/String;
    //   326: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   329: pop
    //   330: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   333: ldc -34
    //   335: lload 7
    //   337: invokestatic 220	org/apache/cordova/buildinfo/BuildInfo:convertLongToDateTimeString	(J)Ljava/lang/String;
    //   340: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   343: pop
    //   344: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   347: ldc -32
    //   349: aload 11
    //   351: ldc -30
    //   353: ldc 116
    //   355: invokestatic 198	org/apache/cordova/buildinfo/BuildInfo:getClassFieldString	(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   358: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   361: pop
    //   362: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   365: ldc -28
    //   367: aload 11
    //   369: ldc -26
    //   371: ldc 116
    //   373: invokestatic 198	org/apache/cordova/buildinfo/BuildInfo:getClassFieldString	(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   376: invokevirtual 186	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   379: pop
    //   380: iload 9
    //   382: ifeq +417 -> 799
    //   385: ldc 8
    //   387: new 144	java/lang/StringBuilder
    //   390: dup
    //   391: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   394: ldc -24
    //   396: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   399: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   402: ldc -74
    //   404: invokevirtual 236	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   407: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   410: ldc -18
    //   412: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   418: invokestatic 244	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   421: pop
    //   422: ldc 8
    //   424: new 144	java/lang/StringBuilder
    //   427: dup
    //   428: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   431: ldc -10
    //   433: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   436: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   439: ldc -68
    //   441: invokevirtual 236	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   444: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   447: ldc -18
    //   449: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   452: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   455: invokestatic 244	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   458: pop
    //   459: ldc 8
    //   461: new 144	java/lang/StringBuilder
    //   464: dup
    //   465: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   468: ldc -8
    //   470: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   473: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   476: ldc -66
    //   478: invokevirtual 236	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   481: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   484: ldc -18
    //   486: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   489: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   492: invokestatic 244	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   495: pop
    //   496: ldc 8
    //   498: new 144	java/lang/StringBuilder
    //   501: dup
    //   502: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   505: ldc -6
    //   507: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   510: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   513: ldc -64
    //   515: invokevirtual 236	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   518: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   521: ldc -18
    //   523: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   526: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   529: invokestatic 244	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   532: pop
    //   533: ldc 8
    //   535: new 144	java/lang/StringBuilder
    //   538: dup
    //   539: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   542: ldc -4
    //   544: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   547: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   550: ldc -62
    //   552: invokevirtual 236	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   555: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   558: ldc -18
    //   560: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   563: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   566: invokestatic 244	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   569: pop
    //   570: ldc 8
    //   572: new 144	java/lang/StringBuilder
    //   575: dup
    //   576: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   579: ldc -2
    //   581: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   584: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   587: ldc -56
    //   589: invokevirtual 257	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   592: invokevirtual 260	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   595: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   598: invokestatic 244	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   601: pop
    //   602: new 144	java/lang/StringBuilder
    //   605: dup
    //   606: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   609: ldc_w 262
    //   612: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   615: astore 10
    //   617: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   620: ldc -47
    //   622: invokevirtual 265	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   625: ifeq +228 -> 853
    //   628: ldc_w 267
    //   631: astore_1
    //   632: ldc 8
    //   634: aload 10
    //   636: aload_1
    //   637: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   640: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   643: invokestatic 244	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   646: pop
    //   647: ldc 8
    //   649: new 144	java/lang/StringBuilder
    //   652: dup
    //   653: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   656: ldc_w 269
    //   659: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   662: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   665: ldc -32
    //   667: invokevirtual 236	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   670: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   673: ldc -18
    //   675: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   678: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   681: invokestatic 244	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   684: pop
    //   685: ldc 8
    //   687: new 144	java/lang/StringBuilder
    //   690: dup
    //   691: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   694: ldc_w 271
    //   697: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   700: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   703: ldc -28
    //   705: invokevirtual 236	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   708: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   711: ldc -18
    //   713: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   716: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   719: invokestatic 244	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   722: pop
    //   723: ldc 8
    //   725: new 144	java/lang/StringBuilder
    //   728: dup
    //   729: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   732: ldc_w 273
    //   735: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   738: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   741: ldc -42
    //   743: invokevirtual 236	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   746: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   749: ldc -18
    //   751: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   754: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   757: invokestatic 244	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   760: pop
    //   761: ldc 8
    //   763: new 144	java/lang/StringBuilder
    //   766: dup
    //   767: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   770: ldc_w 275
    //   773: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   776: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   779: ldc -34
    //   781: invokevirtual 236	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   784: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   787: ldc -18
    //   789: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   792: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   795: invokestatic 244	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   798: pop
    //   799: aload_2
    //   800: getstatic 92	org/apache/cordova/buildinfo/BuildInfo:mBuildInfoCache	Lorg/json/JSONObject;
    //   803: invokevirtual 98	org/apache/cordova/CallbackContext:success	(Lorg/json/JSONObject;)V
    //   806: return
    //   807: astore 10
    //   809: aload 10
    //   811: invokestatic 50	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   814: aload 12
    //   816: astore 10
    //   818: lload_3
    //   819: lstore 7
    //   821: goto -710 -> 111
    //   824: astore_1
    //   825: aload_2
    //   826: new 144	java/lang/StringBuilder
    //   829: dup
    //   830: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   833: ldc_w 277
    //   836: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   839: aload_1
    //   840: invokevirtual 280	java/lang/ClassNotFoundException:getMessage	()Ljava/lang/String;
    //   843: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   846: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   849: invokevirtual 283	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
    //   852: return
    //   853: ldc_w 285
    //   856: astore_1
    //   857: goto -225 -> 632
    //   860: astore_1
    //   861: aload_1
    //   862: invokestatic 50	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   865: aload_2
    //   866: new 144	java/lang/StringBuilder
    //   869: dup
    //   870: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   873: ldc_w 287
    //   876: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   879: aload_1
    //   880: invokevirtual 288	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   883: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   886: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   889: invokevirtual 283	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
    //   892: return
    //   893: astore_1
    //   894: aload 14
    //   896: astore_1
    //   897: goto -748 -> 149
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	900	0	this	BuildInfo
    //   0	900	1	paramString	String
    //   0	900	2	paramCallbackContext	CallbackContext
    //   52	767	3	l1	long
    //   41	67	5	l2	long
    //   79	741	7	l3	long
    //   218	163	9	bool	boolean
    //   75	560	10	localObject1	Object
    //   807	3	10	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   816	1	10	localObject2	Object
    //   34	334	11	localObject3	Object
    //   38	777	12	localObject4	Object
    //   30	196	13	str	String
    //   48	847	14	localPackageManager	android.content.pm.PackageManager
    //   23	138	15	localActivity	android.app.Activity
    //   61	35	16	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   53	63	807	android/content/pm/PackageManager$NameNotFoundException
    //   66	73	807	android/content/pm/PackageManager$NameNotFoundException
    //   84	92	807	android/content/pm/PackageManager$NameNotFoundException
    //   95	107	807	android/content/pm/PackageManager$NameNotFoundException
    //   194	200	824	java/lang/ClassNotFoundException
    //   210	380	860	org/json/JSONException
    //   385	628	860	org/json/JSONException
    //   632	799	860	org/json/JSONException
    //   143	149	893	java/lang/ClassNotFoundException
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    if ("init".equals(paramString))
    {
      paramString = null;
      if (1 < paramJSONArray.length()) {
        paramString = paramJSONArray.getString(0);
      }
      init(paramString, paramCallbackContext);
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\buildinfo\BuildInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */