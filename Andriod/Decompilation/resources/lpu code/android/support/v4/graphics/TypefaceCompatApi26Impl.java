package android.support.v4.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

@RequiresApi(26)
@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatApi26Impl
  extends TypefaceCompatApi21Impl
{
  private static final String ABORT_CREATION_METHOD = "abortCreation";
  private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
  private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
  private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
  private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
  private static final String FREEZE_METHOD = "freeze";
  private static final int RESOLVE_BY_FONT_TABLE = -1;
  private static final String TAG = "TypefaceCompatApi26Impl";
  private static final Method sAbortCreation;
  private static final Method sAddFontFromAssetManager;
  private static final Method sAddFontFromBuffer;
  private static final Method sCreateFromFamiliesWithDefault;
  private static final Class sFontFamily;
  private static final Constructor sFontFamilyCtor;
  private static final Method sFreeze;
  
  static
  {
    try
    {
      localClass = Class.forName("android.graphics.FontFamily");
      localConstructor = localClass.getConstructor(new Class[0]);
      localMethod2 = localClass.getMethod("addFontFromAssetManager", new Class[] { AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class });
      localMethod3 = localClass.getMethod("addFontFromBuffer", new Class[] { ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE });
      localMethod5 = localClass.getMethod("freeze", new Class[0]);
      Method localMethod1 = localClass.getMethod("abortCreation", new Class[0]);
      localMethod4 = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", new Class[] { Array.newInstance(localClass, 1).getClass(), Integer.TYPE, Integer.TYPE });
      localMethod4.setAccessible(true);
      sFontFamilyCtor = localConstructor;
      sFontFamily = localClass;
      sAddFontFromAssetManager = localMethod2;
      sAddFontFromBuffer = localMethod3;
      sFreeze = localMethod5;
      sAbortCreation = localMethod1;
      sCreateFromFamiliesWithDefault = localMethod4;
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;)
      {
        Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class " + localClassNotFoundException.getClass().getName(), localClassNotFoundException);
        Class localClass = null;
        Constructor localConstructor = null;
        Method localMethod2 = null;
        Method localMethod3 = null;
        Method localMethod5 = null;
        Object localObject = null;
        Method localMethod4 = null;
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;) {}
    }
  }
  
  private static void abortCreation(Object paramObject)
  {
    try
    {
      sAbortCreation.invoke(paramObject, new Object[0]);
      return;
    }
    catch (IllegalAccessException paramObject)
    {
      throw new RuntimeException((Throwable)paramObject);
    }
    catch (InvocationTargetException paramObject)
    {
      for (;;) {}
    }
  }
  
  private static boolean addFontFromAssetManager(Context paramContext, Object paramObject, String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      boolean bool = ((Boolean)sAddFontFromAssetManager.invoke(paramObject, new Object[] { paramContext.getAssets(), paramString, Integer.valueOf(0), Boolean.valueOf(false), Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), null })).booleanValue();
      return bool;
    }
    catch (IllegalAccessException paramContext)
    {
      throw new RuntimeException(paramContext);
    }
    catch (InvocationTargetException paramContext)
    {
      for (;;) {}
    }
  }
  
  private static boolean addFontFromBuffer(Object paramObject, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      boolean bool = ((Boolean)sAddFontFromBuffer.invoke(paramObject, new Object[] { paramByteBuffer, Integer.valueOf(paramInt1), null, Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) })).booleanValue();
      return bool;
    }
    catch (IllegalAccessException paramObject)
    {
      throw new RuntimeException((Throwable)paramObject);
    }
    catch (InvocationTargetException paramObject)
    {
      for (;;) {}
    }
  }
  
  private static Typeface createFromFamiliesWithDefault(Object paramObject)
  {
    try
    {
      Object localObject = Array.newInstance(sFontFamily, 1);
      Array.set(localObject, 0, paramObject);
      paramObject = (Typeface)sCreateFromFamiliesWithDefault.invoke(null, new Object[] { localObject, Integer.valueOf(-1), Integer.valueOf(-1) });
      return (Typeface)paramObject;
    }
    catch (IllegalAccessException paramObject)
    {
      throw new RuntimeException((Throwable)paramObject);
    }
    catch (InvocationTargetException paramObject)
    {
      for (;;) {}
    }
  }
  
  private static boolean freeze(Object paramObject)
  {
    try
    {
      boolean bool = ((Boolean)sFreeze.invoke(paramObject, new Object[0])).booleanValue();
      return bool;
    }
    catch (IllegalAccessException paramObject)
    {
      throw new RuntimeException((Throwable)paramObject);
    }
    catch (InvocationTargetException paramObject)
    {
      for (;;) {}
    }
  }
  
  private static boolean isFontFamilyPrivateAPIAvailable()
  {
    if (sAddFontFromAssetManager == null) {
      Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
    }
    return sAddFontFromAssetManager != null;
  }
  
  private static Object newFamily()
  {
    try
    {
      Object localObject = sFontFamilyCtor.newInstance(new Object[0]);
      return localObject;
    }
    catch (InstantiationException localInstantiationException)
    {
      throw new RuntimeException(localInstantiationException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;) {}
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;) {}
    }
  }
  
  public Typeface createFromFontFamilyFilesResourceEntry(Context paramContext, FontResourcesParserCompat.FontFamilyFilesResourceEntry paramFontFamilyFilesResourceEntry, Resources paramResources, int paramInt)
  {
    if (!isFontFamilyPrivateAPIAvailable()) {
      return super.createFromFontFamilyFilesResourceEntry(paramContext, paramFontFamilyFilesResourceEntry, paramResources, paramInt);
    }
    paramResources = newFamily();
    paramFontFamilyFilesResourceEntry = paramFontFamilyFilesResourceEntry.getEntries();
    int j = paramFontFamilyFilesResourceEntry.length;
    paramInt = 0;
    while (paramInt < j)
    {
      Object localObject = paramFontFamilyFilesResourceEntry[paramInt];
      String str = ((FontResourcesParserCompat.FontFileResourceEntry)localObject).getFileName();
      int k = ((FontResourcesParserCompat.FontFileResourceEntry)localObject).getWeight();
      if (((FontResourcesParserCompat.FontFileResourceEntry)localObject).isItalic()) {}
      for (int i = 1; !addFontFromAssetManager(paramContext, paramResources, str, 0, k, i); i = 0)
      {
        abortCreation(paramResources);
        return null;
      }
      paramInt += 1;
    }
    if (!freeze(paramResources)) {
      return null;
    }
    return createFromFamiliesWithDefault(paramResources);
  }
  
  /* Error */
  public Typeface createFromFontInfo(Context paramContext, @Nullable android.os.CancellationSignal paramCancellationSignal, @android.support.annotation.NonNull android.support.v4.provider.FontsContractCompat.FontInfo[] paramArrayOfFontInfo, int paramInt)
  {
    // Byte code:
    //   0: aload_3
    //   1: arraylength
    //   2: iconst_1
    //   3: if_icmpge +7 -> 10
    //   6: aconst_null
    //   7: astore_1
    //   8: aload_1
    //   9: areturn
    //   10: invokestatic 203	android/support/v4/graphics/TypefaceCompatApi26Impl:isFontFamilyPrivateAPIAvailable	()Z
    //   13: ifne +164 -> 177
    //   16: aload_0
    //   17: aload_3
    //   18: iload 4
    //   20: invokevirtual 245	android/support/v4/graphics/TypefaceCompatApi26Impl:findBestInfo	([Landroid/support/v4/provider/FontsContractCompat$FontInfo;I)Landroid/support/v4/provider/FontsContractCompat$FontInfo;
    //   23: astore 10
    //   25: aload_1
    //   26: invokevirtual 249	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   29: astore_1
    //   30: aload_1
    //   31: aload 10
    //   33: invokevirtual 255	android/support/v4/provider/FontsContractCompat$FontInfo:getUri	()Landroid/net/Uri;
    //   36: ldc_w 257
    //   39: aload_2
    //   40: invokevirtual 263	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/os/ParcelFileDescriptor;
    //   43: astore_3
    //   44: aconst_null
    //   45: astore_2
    //   46: aload_3
    //   47: ifnonnull +33 -> 80
    //   50: aconst_null
    //   51: astore_1
    //   52: aload_3
    //   53: ifnull -45 -> 8
    //   56: iconst_0
    //   57: ifeq +17 -> 74
    //   60: aload_3
    //   61: invokevirtual 268	android/os/ParcelFileDescriptor:close	()V
    //   64: aconst_null
    //   65: areturn
    //   66: astore_1
    //   67: aconst_null
    //   68: aload_1
    //   69: invokestatic 274	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:addSuppressed	(Ljava/lang/Throwable;Ljava/lang/Throwable;)V
    //   72: aconst_null
    //   73: areturn
    //   74: aload_3
    //   75: invokevirtual 268	android/os/ParcelFileDescriptor:close	()V
    //   78: aconst_null
    //   79: areturn
    //   80: new 276	android/graphics/Typeface$Builder
    //   83: dup
    //   84: aload_3
    //   85: invokevirtual 280	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   88: invokespecial 283	android/graphics/Typeface$Builder:<init>	(Ljava/io/FileDescriptor;)V
    //   91: aload 10
    //   93: invokevirtual 284	android/support/v4/provider/FontsContractCompat$FontInfo:getWeight	()I
    //   96: invokevirtual 288	android/graphics/Typeface$Builder:setWeight	(I)Landroid/graphics/Typeface$Builder;
    //   99: aload 10
    //   101: invokevirtual 289	android/support/v4/provider/FontsContractCompat$FontInfo:isItalic	()Z
    //   104: invokevirtual 293	android/graphics/Typeface$Builder:setItalic	(Z)Landroid/graphics/Typeface$Builder;
    //   107: invokevirtual 297	android/graphics/Typeface$Builder:build	()Landroid/graphics/Typeface;
    //   110: astore_1
    //   111: aload_1
    //   112: astore_2
    //   113: aload_2
    //   114: astore_1
    //   115: aload_3
    //   116: ifnull -108 -> 8
    //   119: iconst_0
    //   120: ifeq +17 -> 137
    //   123: aload_3
    //   124: invokevirtual 268	android/os/ParcelFileDescriptor:close	()V
    //   127: aload_2
    //   128: areturn
    //   129: astore_1
    //   130: aconst_null
    //   131: aload_1
    //   132: invokestatic 274	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:addSuppressed	(Ljava/lang/Throwable;Ljava/lang/Throwable;)V
    //   135: aload_2
    //   136: areturn
    //   137: aload_3
    //   138: invokevirtual 268	android/os/ParcelFileDescriptor:close	()V
    //   141: aload_2
    //   142: areturn
    //   143: astore_2
    //   144: aload_2
    //   145: athrow
    //   146: astore_1
    //   147: aload_3
    //   148: ifnull +11 -> 159
    //   151: aload_2
    //   152: ifnull +18 -> 170
    //   155: aload_3
    //   156: invokevirtual 268	android/os/ParcelFileDescriptor:close	()V
    //   159: aload_1
    //   160: athrow
    //   161: astore_3
    //   162: aload_2
    //   163: aload_3
    //   164: invokestatic 274	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:addSuppressed	(Ljava/lang/Throwable;Ljava/lang/Throwable;)V
    //   167: goto -8 -> 159
    //   170: aload_3
    //   171: invokevirtual 268	android/os/ParcelFileDescriptor:close	()V
    //   174: goto -15 -> 159
    //   177: aload_1
    //   178: aload_3
    //   179: aload_2
    //   180: invokestatic 303	android/support/v4/provider/FontsContractCompat:prepareFontData	(Landroid/content/Context;[Landroid/support/v4/provider/FontsContractCompat$FontInfo;Landroid/os/CancellationSignal;)Ljava/util/Map;
    //   183: astore_1
    //   184: invokestatic 207	android/support/v4/graphics/TypefaceCompatApi26Impl:newFamily	()Ljava/lang/Object;
    //   187: astore_2
    //   188: iconst_0
    //   189: istore 6
    //   191: aload_3
    //   192: arraylength
    //   193: istore 7
    //   195: iconst_0
    //   196: istore 5
    //   198: iload 5
    //   200: iload 7
    //   202: if_icmpge +97 -> 299
    //   205: aload_3
    //   206: iload 5
    //   208: aaload
    //   209: astore 10
    //   211: aload_1
    //   212: aload 10
    //   214: invokevirtual 255	android/support/v4/provider/FontsContractCompat$FontInfo:getUri	()Landroid/net/Uri;
    //   217: invokeinterface 309 2 0
    //   222: checkcast 81	java/nio/ByteBuffer
    //   225: astore 11
    //   227: aload 11
    //   229: ifnonnull +12 -> 241
    //   232: iload 5
    //   234: iconst_1
    //   235: iadd
    //   236: istore 5
    //   238: goto -40 -> 198
    //   241: aload 10
    //   243: invokevirtual 312	android/support/v4/provider/FontsContractCompat$FontInfo:getTtcIndex	()I
    //   246: istore 8
    //   248: aload 10
    //   250: invokevirtual 284	android/support/v4/provider/FontsContractCompat$FontInfo:getWeight	()I
    //   253: istore 9
    //   255: aload 10
    //   257: invokevirtual 289	android/support/v4/provider/FontsContractCompat$FontInfo:isItalic	()Z
    //   260: ifeq +27 -> 287
    //   263: iconst_1
    //   264: istore 6
    //   266: aload_2
    //   267: aload 11
    //   269: iload 8
    //   271: iload 9
    //   273: iload 6
    //   275: invokestatic 314	android/support/v4/graphics/TypefaceCompatApi26Impl:addFontFromBuffer	(Ljava/lang/Object;Ljava/nio/ByteBuffer;III)Z
    //   278: ifne +15 -> 293
    //   281: aload_2
    //   282: invokestatic 229	android/support/v4/graphics/TypefaceCompatApi26Impl:abortCreation	(Ljava/lang/Object;)V
    //   285: aconst_null
    //   286: areturn
    //   287: iconst_0
    //   288: istore 6
    //   290: goto -24 -> 266
    //   293: iconst_1
    //   294: istore 6
    //   296: goto -64 -> 232
    //   299: iload 6
    //   301: ifne +9 -> 310
    //   304: aload_2
    //   305: invokestatic 229	android/support/v4/graphics/TypefaceCompatApi26Impl:abortCreation	(Ljava/lang/Object;)V
    //   308: aconst_null
    //   309: areturn
    //   310: aload_2
    //   311: invokestatic 231	android/support/v4/graphics/TypefaceCompatApi26Impl:freeze	(Ljava/lang/Object;)Z
    //   314: ifne +5 -> 319
    //   317: aconst_null
    //   318: areturn
    //   319: aload_2
    //   320: invokestatic 233	android/support/v4/graphics/TypefaceCompatApi26Impl:createFromFamiliesWithDefault	(Ljava/lang/Object;)Landroid/graphics/Typeface;
    //   323: iload 4
    //   325: invokestatic 318	android/graphics/Typeface:create	(Landroid/graphics/Typeface;I)Landroid/graphics/Typeface;
    //   328: areturn
    //   329: astore_1
    //   330: goto -183 -> 147
    //   333: astore_1
    //   334: aconst_null
    //   335: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	336	0	this	TypefaceCompatApi26Impl
    //   0	336	1	paramContext	Context
    //   0	336	2	paramCancellationSignal	android.os.CancellationSignal
    //   0	336	3	paramArrayOfFontInfo	android.support.v4.provider.FontsContractCompat.FontInfo[]
    //   0	336	4	paramInt	int
    //   196	41	5	i	int
    //   189	111	6	j	int
    //   193	10	7	k	int
    //   246	24	8	m	int
    //   253	19	9	n	int
    //   23	233	10	localFontInfo	android.support.v4.provider.FontsContractCompat.FontInfo
    //   225	43	11	localByteBuffer	ByteBuffer
    // Exception table:
    //   from	to	target	type
    //   60	64	66	java/lang/Throwable
    //   123	127	129	java/lang/Throwable
    //   80	111	143	java/lang/Throwable
    //   144	146	146	finally
    //   155	159	161	java/lang/Throwable
    //   80	111	329	finally
    //   30	44	333	java/io/IOException
    //   60	64	333	java/io/IOException
    //   67	72	333	java/io/IOException
    //   74	78	333	java/io/IOException
    //   123	127	333	java/io/IOException
    //   130	135	333	java/io/IOException
    //   137	141	333	java/io/IOException
    //   155	159	333	java/io/IOException
    //   159	161	333	java/io/IOException
    //   162	167	333	java/io/IOException
    //   170	174	333	java/io/IOException
  }
  
  @Nullable
  public Typeface createFromResourcesFontFile(Context paramContext, Resources paramResources, int paramInt1, String paramString, int paramInt2)
  {
    if (!isFontFamilyPrivateAPIAvailable()) {
      return super.createFromResourcesFontFile(paramContext, paramResources, paramInt1, paramString, paramInt2);
    }
    paramResources = newFamily();
    if (!addFontFromAssetManager(paramContext, paramResources, paramString, 0, -1, -1))
    {
      abortCreation(paramResources);
      return null;
    }
    if (!freeze(paramResources)) {
      return null;
    }
    return createFromFamiliesWithDefault(paramResources);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\graphics\TypefaceCompatApi26Impl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */