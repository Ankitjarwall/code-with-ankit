package com.google.android.gms.dynamite;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.zzd;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public final class DynamiteModule
{
  private static Boolean zzaRO;
  private static zza zzaRP;
  private static zzb zzaRQ;
  private static final HashMap<String, byte[]> zzaRR = new HashMap();
  private static String zzaRS;
  private static final DynamiteModule.zzb.zza zzaRT = new DynamiteModule.zzb.zza()
  {
    public int zzI(Context paramAnonymousContext, String paramAnonymousString)
    {
      return DynamiteModule.zzI(paramAnonymousContext, paramAnonymousString);
    }
    
    public int zzb(Context paramAnonymousContext, String paramAnonymousString, boolean paramAnonymousBoolean)
      throws DynamiteModule.zza
    {
      return DynamiteModule.zzb(paramAnonymousContext, paramAnonymousString, paramAnonymousBoolean);
    }
  };
  public static final zzb zzaRU = new zzb()
  {
    public DynamiteModule.zzb.zzb zza(Context paramAnonymousContext, String paramAnonymousString, DynamiteModule.zzb.zza paramAnonymouszza)
      throws DynamiteModule.zza
    {
      DynamiteModule.zzb.zzb localzzb = new DynamiteModule.zzb.zzb();
      localzzb.zzaSc = paramAnonymouszza.zzb(paramAnonymousContext, paramAnonymousString, true);
      if (localzzb.zzaSc != 0) {
        localzzb.zzaSd = 1;
      }
      do
      {
        return localzzb;
        localzzb.zzaSb = paramAnonymouszza.zzI(paramAnonymousContext, paramAnonymousString);
      } while (localzzb.zzaSb == 0);
      localzzb.zzaSd = -1;
      return localzzb;
    }
  };
  public static final zzb zzaRV = new zzb()
  {
    public DynamiteModule.zzb.zzb zza(Context paramAnonymousContext, String paramAnonymousString, DynamiteModule.zzb.zza paramAnonymouszza)
      throws DynamiteModule.zza
    {
      DynamiteModule.zzb.zzb localzzb = new DynamiteModule.zzb.zzb();
      localzzb.zzaSb = paramAnonymouszza.zzI(paramAnonymousContext, paramAnonymousString);
      if (localzzb.zzaSb != 0) {
        localzzb.zzaSd = -1;
      }
      do
      {
        return localzzb;
        localzzb.zzaSc = paramAnonymouszza.zzb(paramAnonymousContext, paramAnonymousString, true);
      } while (localzzb.zzaSc == 0);
      localzzb.zzaSd = 1;
      return localzzb;
    }
  };
  public static final zzb zzaRW = new zzb()
  {
    public DynamiteModule.zzb.zzb zza(Context paramAnonymousContext, String paramAnonymousString, DynamiteModule.zzb.zza paramAnonymouszza)
      throws DynamiteModule.zza
    {
      DynamiteModule.zzb.zzb localzzb = new DynamiteModule.zzb.zzb();
      localzzb.zzaSb = paramAnonymouszza.zzI(paramAnonymousContext, paramAnonymousString);
      localzzb.zzaSc = paramAnonymouszza.zzb(paramAnonymousContext, paramAnonymousString, true);
      if ((localzzb.zzaSb == 0) && (localzzb.zzaSc == 0))
      {
        localzzb.zzaSd = 0;
        return localzzb;
      }
      if (localzzb.zzaSb >= localzzb.zzaSc)
      {
        localzzb.zzaSd = -1;
        return localzzb;
      }
      localzzb.zzaSd = 1;
      return localzzb;
    }
  };
  public static final zzb zzaRX = new zzb()
  {
    public DynamiteModule.zzb.zzb zza(Context paramAnonymousContext, String paramAnonymousString, DynamiteModule.zzb.zza paramAnonymouszza)
      throws DynamiteModule.zza
    {
      DynamiteModule.zzb.zzb localzzb = new DynamiteModule.zzb.zzb();
      localzzb.zzaSb = paramAnonymouszza.zzI(paramAnonymousContext, paramAnonymousString);
      localzzb.zzaSc = paramAnonymouszza.zzb(paramAnonymousContext, paramAnonymousString, true);
      if ((localzzb.zzaSb == 0) && (localzzb.zzaSc == 0))
      {
        localzzb.zzaSd = 0;
        return localzzb;
      }
      if (localzzb.zzaSc >= localzzb.zzaSb)
      {
        localzzb.zzaSd = 1;
        return localzzb;
      }
      localzzb.zzaSd = -1;
      return localzzb;
    }
  };
  public static final zzb zzaRY = new zzb()
  {
    public DynamiteModule.zzb.zzb zza(Context paramAnonymousContext, String paramAnonymousString, DynamiteModule.zzb.zza paramAnonymouszza)
      throws DynamiteModule.zza
    {
      DynamiteModule.zzb.zzb localzzb = new DynamiteModule.zzb.zzb();
      localzzb.zzaSb = paramAnonymouszza.zzI(paramAnonymousContext, paramAnonymousString);
      if (localzzb.zzaSb != 0) {}
      for (localzzb.zzaSc = paramAnonymouszza.zzb(paramAnonymousContext, paramAnonymousString, false); (localzzb.zzaSb == 0) && (localzzb.zzaSc == 0); localzzb.zzaSc = paramAnonymouszza.zzb(paramAnonymousContext, paramAnonymousString, true))
      {
        localzzb.zzaSd = 0;
        return localzzb;
      }
      if (localzzb.zzaSc >= localzzb.zzaSb)
      {
        localzzb.zzaSd = 1;
        return localzzb;
      }
      localzzb.zzaSd = -1;
      return localzzb;
    }
  };
  private final Context zzaRZ;
  
  private DynamiteModule(Context paramContext)
  {
    this.zzaRZ = ((Context)zzac.zzw(paramContext));
  }
  
  private static ClassLoader zzBT()
  {
    new PathClassLoader(zzaRS, ClassLoader.getSystemClassLoader())
    {
      protected Class<?> loadClass(String paramAnonymousString, boolean paramAnonymousBoolean)
        throws ClassNotFoundException
      {
        if ((!paramAnonymousString.startsWith("java.")) && (!paramAnonymousString.startsWith("android."))) {
          try
          {
            Class localClass = findClass(paramAnonymousString);
            return localClass;
          }
          catch (ClassNotFoundException localClassNotFoundException) {}
        }
        return super.loadClass(paramAnonymousString, paramAnonymousBoolean);
      }
    };
  }
  
  public static int zzI(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getApplicationContext().getClassLoader();
      Object localObject = String.valueOf("com.google.android.gms.dynamite.descriptors.");
      String str = String.valueOf("ModuleDescriptor");
      localObject = paramContext.loadClass(String.valueOf(localObject).length() + 1 + String.valueOf(paramString).length() + String.valueOf(str).length() + (String)localObject + paramString + "." + str);
      paramContext = ((Class)localObject).getDeclaredField("MODULE_ID");
      localObject = ((Class)localObject).getDeclaredField("MODULE_VERSION");
      if (!paramContext.get(null).equals(paramString))
      {
        paramContext = String.valueOf(paramContext.get(null));
        Log.e("DynamiteModule", String.valueOf(paramContext).length() + 51 + String.valueOf(paramString).length() + "Module descriptor id '" + paramContext + "' didn't match expected id '" + paramString + "'");
        return 0;
      }
      int i = ((Field)localObject).getInt(null);
      return i;
    }
    catch (ClassNotFoundException paramContext)
    {
      Log.w("DynamiteModule", String.valueOf(paramString).length() + 45 + "Local module descriptor class for " + paramString + " not found.");
      return 0;
    }
    catch (Exception paramContext)
    {
      paramContext = String.valueOf(paramContext.getMessage());
      if (paramContext.length() == 0) {}
    }
    for (paramContext = "Failed to load module descriptor class: ".concat(paramContext);; paramContext = new String("Failed to load module descriptor class: "))
    {
      Log.e("DynamiteModule", paramContext);
      break;
    }
  }
  
  public static int zzJ(Context paramContext, String paramString)
  {
    return zzb(paramContext, paramString, false);
  }
  
  private static DynamiteModule zzK(Context paramContext, String paramString)
  {
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {}
    for (paramString = "Selected local version of ".concat(paramString);; paramString = new String("Selected local version of "))
    {
      Log.i("DynamiteModule", paramString);
      return new DynamiteModule(paramContext.getApplicationContext());
    }
  }
  
  private static Context zza(Context paramContext, String paramString, byte[] paramArrayOfByte, zzb paramzzb)
  {
    try
    {
      paramContext = (Context)zzd.zzF(paramzzb.zza(zzd.zzA(paramContext), paramString, paramArrayOfByte));
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext = String.valueOf(paramContext.toString());
      if (paramContext.length() == 0) {}
    }
    for (paramContext = "Failed to load DynamiteLoader: ".concat(paramContext);; paramContext = new String("Failed to load DynamiteLoader: "))
    {
      Log.e("DynamiteModule", paramContext);
      return null;
    }
  }
  
  public static DynamiteModule zza(Context paramContext, zzb paramzzb, String paramString)
    throws DynamiteModule.zza
  {
    DynamiteModule.zzb.zzb localzzb = paramzzb.zza(paramContext, paramString, zzaRT);
    int i = localzzb.zzaSb;
    int j = localzzb.zzaSc;
    Log.i("DynamiteModule", String.valueOf(paramString).length() + 68 + String.valueOf(paramString).length() + "Considering local module " + paramString + ":" + i + " and remote module " + paramString + ":" + j);
    if ((localzzb.zzaSd == 0) || ((localzzb.zzaSd == -1) && (localzzb.zzaSb == 0)) || ((localzzb.zzaSd == 1) && (localzzb.zzaSc == 0)))
    {
      i = localzzb.zzaSb;
      j = localzzb.zzaSc;
      throw new zza(91 + "No acceptable module found. Local version is " + i + " and remote version is " + j + ".", null);
    }
    if (localzzb.zzaSd == -1) {
      return zzK(paramContext, paramString);
    }
    if (localzzb.zzaSd == 1) {
      try
      {
        localObject = zza(paramContext, paramString, localzzb.zzaSc);
        return (DynamiteModule)localObject;
      }
      catch (zza localzza)
      {
        Object localObject = String.valueOf(localzza.getMessage());
        if (((String)localObject).length() != 0) {}
        for (localObject = "Failed to load remote module: ".concat((String)localObject);; localObject = new String("Failed to load remote module: "))
        {
          Log.w("DynamiteModule", (String)localObject);
          if ((localzzb.zzaSb == 0) || (
          {
            public int zzI(Context paramAnonymousContext, String paramAnonymousString)
            {
              return this.zzaSa;
            }
            
            public int zzb(Context paramAnonymousContext, String paramAnonymousString, boolean paramAnonymousBoolean)
            {
              return 0;
            }
          } != -1)) {
            break;
          }
          return zzK(paramContext, paramString);
        }
        throw new zza("Remote load failed. No local fallback found.", localzza, null);
      }
    }
    i = localzzb.zzaSd;
    throw new zza(47 + "VersionPolicy returned invalid code:" + i, null);
  }
  
  private static DynamiteModule zza(Context paramContext, String paramString, int paramInt)
    throws DynamiteModule.zza
  {
    Boolean localBoolean;
    try
    {
      localBoolean = zzaRO;
      if (localBoolean == null) {
        throw new zza("Failed to determine which loading route to use.", null);
      }
    }
    finally {}
    if (localBoolean.booleanValue()) {
      return zzc(paramContext, paramString, paramInt);
    }
    return zzb(paramContext, paramString, paramInt);
  }
  
  private static void zza(ClassLoader paramClassLoader)
    throws DynamiteModule.zza
  {
    try
    {
      zzaRQ = zzb.zza.zzcf((IBinder)paramClassLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]));
      return;
    }
    catch (ClassNotFoundException paramClassLoader)
    {
      throw new zza("Failed to instantiate dynamite loader", paramClassLoader, null);
    }
    catch (InstantiationException paramClassLoader)
    {
      for (;;) {}
    }
    catch (IllegalAccessException paramClassLoader)
    {
      for (;;) {}
    }
    catch (NoSuchMethodException paramClassLoader)
    {
      for (;;) {}
    }
    catch (InvocationTargetException paramClassLoader)
    {
      for (;;) {}
    }
  }
  
  /* Error */
  public static int zzb(Context paramContext, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 289	com/google/android/gms/dynamite/DynamiteModule:zzaRO	Ljava/lang/Boolean;
    //   6: astore 6
    //   8: aload 6
    //   10: astore 5
    //   12: aload 6
    //   14: ifnonnull +70 -> 84
    //   17: aload_0
    //   18: invokevirtual 116	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   21: invokevirtual 119	android/content/Context:getClassLoader	()Ljava/lang/ClassLoader;
    //   24: ldc 22
    //   26: invokevirtual 340	java/lang/Class:getName	()Ljava/lang/String;
    //   29: invokevirtual 152	java/lang/ClassLoader:loadClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   32: astore 6
    //   34: aload 6
    //   36: ldc_w 342
    //   39: invokevirtual 160	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   42: astore 5
    //   44: aload 6
    //   46: monitorenter
    //   47: aload 5
    //   49: aconst_null
    //   50: invokevirtual 167	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   53: checkcast 100	java/lang/ClassLoader
    //   56: astore 7
    //   58: aload 7
    //   60: ifnull +57 -> 117
    //   63: aload 7
    //   65: invokestatic 103	java/lang/ClassLoader:getSystemClassLoader	()Ljava/lang/ClassLoader;
    //   68: if_acmpne +36 -> 104
    //   71: getstatic 345	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   74: astore 5
    //   76: aload 6
    //   78: monitorexit
    //   79: aload 5
    //   81: putstatic 289	com/google/android/gms/dynamite/DynamiteModule:zzaRO	Ljava/lang/Boolean;
    //   84: ldc 2
    //   86: monitorexit
    //   87: aload 5
    //   89: invokevirtual 297	java/lang/Boolean:booleanValue	()Z
    //   92: ifeq +259 -> 351
    //   95: aload_0
    //   96: aload_1
    //   97: iload_2
    //   98: invokestatic 348	com/google/android/gms/dynamite/DynamiteModule:zzd	(Landroid/content/Context;Ljava/lang/String;Z)I
    //   101: istore_3
    //   102: iload_3
    //   103: ireturn
    //   104: aload 7
    //   106: invokestatic 350	com/google/android/gms/dynamite/DynamiteModule:zza	(Ljava/lang/ClassLoader;)V
    //   109: getstatic 353	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   112: astore 5
    //   114: goto -38 -> 76
    //   117: ldc_w 355
    //   120: aload_0
    //   121: invokevirtual 116	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   124: invokevirtual 358	android/content/Context:getPackageName	()Ljava/lang/String;
    //   127: invokevirtual 359	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   130: ifeq +20 -> 150
    //   133: aload 5
    //   135: aconst_null
    //   136: invokestatic 103	java/lang/ClassLoader:getSystemClassLoader	()Ljava/lang/ClassLoader;
    //   139: invokevirtual 363	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   142: getstatic 345	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   145: astore 5
    //   147: goto -71 -> 76
    //   150: aload_0
    //   151: aload_1
    //   152: iload_2
    //   153: invokestatic 348	com/google/android/gms/dynamite/DynamiteModule:zzd	(Landroid/content/Context;Ljava/lang/String;Z)I
    //   156: istore_3
    //   157: getstatic 98	com/google/android/gms/dynamite/DynamiteModule:zzaRS	Ljava/lang/String;
    //   160: ifnull +16 -> 176
    //   163: getstatic 98	com/google/android/gms/dynamite/DynamiteModule:zzaRS	Ljava/lang/String;
    //   166: invokevirtual 366	java/lang/String:isEmpty	()Z
    //   169: istore 4
    //   171: iload 4
    //   173: ifeq +17 -> 190
    //   176: aload 6
    //   178: monitorexit
    //   179: ldc 2
    //   181: monitorexit
    //   182: iload_3
    //   183: ireturn
    //   184: astore_0
    //   185: ldc 2
    //   187: monitorexit
    //   188: aload_0
    //   189: athrow
    //   190: invokestatic 368	com/google/android/gms/dynamite/DynamiteModule:zzBT	()Ljava/lang/ClassLoader;
    //   193: astore 7
    //   195: aload 7
    //   197: invokestatic 350	com/google/android/gms/dynamite/DynamiteModule:zza	(Ljava/lang/ClassLoader;)V
    //   200: aload 5
    //   202: aconst_null
    //   203: aload 7
    //   205: invokevirtual 363	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   208: getstatic 353	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   211: putstatic 289	com/google/android/gms/dynamite/DynamiteModule:zzaRO	Ljava/lang/Boolean;
    //   214: aload 6
    //   216: monitorexit
    //   217: ldc 2
    //   219: monitorexit
    //   220: iload_3
    //   221: ireturn
    //   222: astore 7
    //   224: aload 5
    //   226: aconst_null
    //   227: invokestatic 103	java/lang/ClassLoader:getSystemClassLoader	()Ljava/lang/ClassLoader;
    //   230: invokevirtual 363	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   233: getstatic 345	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   236: astore 5
    //   238: goto -162 -> 76
    //   241: astore 5
    //   243: aload 6
    //   245: monitorexit
    //   246: aload 5
    //   248: athrow
    //   249: astore 5
    //   251: aload 5
    //   253: invokestatic 127	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   256: astore 5
    //   258: ldc -83
    //   260: new 131	java/lang/StringBuilder
    //   263: dup
    //   264: aload 5
    //   266: invokestatic 127	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   269: invokevirtual 135	java/lang/String:length	()I
    //   272: bipush 30
    //   274: iadd
    //   275: invokespecial 138	java/lang/StringBuilder:<init>	(I)V
    //   278: ldc_w 370
    //   281: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   284: aload 5
    //   286: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: invokevirtual 148	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   292: invokestatic 196	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   295: pop
    //   296: getstatic 345	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   299: astore 5
    //   301: goto -222 -> 79
    //   304: astore_0
    //   305: aload_0
    //   306: invokevirtual 276	com/google/android/gms/dynamite/DynamiteModule$zza:getMessage	()Ljava/lang/String;
    //   309: invokestatic 127	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   312: astore_0
    //   313: aload_0
    //   314: invokevirtual 135	java/lang/String:length	()I
    //   317: ifeq +20 -> 337
    //   320: ldc_w 372
    //   323: aload_0
    //   324: invokevirtual 205	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   327: astore_0
    //   328: ldc -83
    //   330: aload_0
    //   331: invokestatic 196	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   334: pop
    //   335: iconst_0
    //   336: ireturn
    //   337: new 123	java/lang/String
    //   340: dup
    //   341: ldc_w 372
    //   344: invokespecial 208	java/lang/String:<init>	(Ljava/lang/String;)V
    //   347: astore_0
    //   348: goto -20 -> 328
    //   351: aload_0
    //   352: aload_1
    //   353: iload_2
    //   354: invokestatic 374	com/google/android/gms/dynamite/DynamiteModule:zzc	(Landroid/content/Context;Ljava/lang/String;Z)I
    //   357: ireturn
    //   358: astore 5
    //   360: goto -251 -> 109
    //   363: astore 5
    //   365: goto -114 -> 251
    //   368: astore 5
    //   370: goto -119 -> 251
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	373	0	paramContext	Context
    //   0	373	1	paramString	String
    //   0	373	2	paramBoolean	boolean
    //   101	120	3	i	int
    //   169	3	4	bool	boolean
    //   10	227	5	localObject1	Object
    //   241	6	5	localObject2	Object
    //   249	3	5	localClassNotFoundException	ClassNotFoundException
    //   256	44	5	localObject3	Object
    //   358	1	5	localzza1	zza
    //   363	1	5	localNoSuchFieldException	NoSuchFieldException
    //   368	1	5	localIllegalAccessException	IllegalAccessException
    //   6	238	6	localObject4	Object
    //   56	148	7	localClassLoader	ClassLoader
    //   222	1	7	localzza2	zza
    // Exception table:
    //   from	to	target	type
    //   3	8	184	finally
    //   17	47	184	finally
    //   79	84	184	finally
    //   84	87	184	finally
    //   179	182	184	finally
    //   185	188	184	finally
    //   217	220	184	finally
    //   246	249	184	finally
    //   251	301	184	finally
    //   150	171	222	com/google/android/gms/dynamite/DynamiteModule$zza
    //   190	214	222	com/google/android/gms/dynamite/DynamiteModule$zza
    //   47	58	241	finally
    //   63	76	241	finally
    //   76	79	241	finally
    //   104	109	241	finally
    //   109	114	241	finally
    //   117	147	241	finally
    //   150	171	241	finally
    //   176	179	241	finally
    //   190	214	241	finally
    //   214	217	241	finally
    //   224	238	241	finally
    //   243	246	241	finally
    //   17	47	249	java/lang/ClassNotFoundException
    //   246	249	249	java/lang/ClassNotFoundException
    //   95	102	304	com/google/android/gms/dynamite/DynamiteModule$zza
    //   104	109	358	com/google/android/gms/dynamite/DynamiteModule$zza
    //   17	47	363	java/lang/NoSuchFieldException
    //   246	249	363	java/lang/NoSuchFieldException
    //   17	47	368	java/lang/IllegalAccessException
    //   246	249	368	java/lang/IllegalAccessException
  }
  
  private static DynamiteModule zzb(Context paramContext, String paramString, int paramInt)
    throws DynamiteModule.zza
  {
    Log.i("DynamiteModule", String.valueOf(paramString).length() + 51 + "Selected remote version of " + paramString + ", version >= " + paramInt);
    zza localzza = zzbm(paramContext);
    if (localzza == null) {
      throw new zza("Failed to create IDynamiteLoader.", null);
    }
    try
    {
      paramContext = localzza.zza(zzd.zzA(paramContext), paramString, paramInt);
      if (zzd.zzF(paramContext) == null) {
        throw new zza("Failed to load remote module.", null);
      }
    }
    catch (RemoteException paramContext)
    {
      throw new zza("Failed to load remote module.", paramContext, null);
    }
    return new DynamiteModule((Context)zzd.zzF(paramContext));
  }
  
  /* Error */
  private static zza zzbm(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 395	com/google/android/gms/dynamite/DynamiteModule:zzaRP	Lcom/google/android/gms/dynamite/zza;
    //   6: ifnull +12 -> 18
    //   9: getstatic 395	com/google/android/gms/dynamite/DynamiteModule:zzaRP	Lcom/google/android/gms/dynamite/zza;
    //   12: astore_0
    //   13: ldc 2
    //   15: monitorexit
    //   16: aload_0
    //   17: areturn
    //   18: invokestatic 401	com/google/android/gms/common/zze:zzuY	()Lcom/google/android/gms/common/zze;
    //   21: aload_0
    //   22: invokevirtual 405	com/google/android/gms/common/zze:isGooglePlayServicesAvailable	(Landroid/content/Context;)I
    //   25: ifeq +8 -> 33
    //   28: ldc 2
    //   30: monitorexit
    //   31: aconst_null
    //   32: areturn
    //   33: aload_0
    //   34: ldc_w 355
    //   37: iconst_3
    //   38: invokevirtual 409	android/content/Context:createPackageContext	(Ljava/lang/String;I)Landroid/content/Context;
    //   41: invokevirtual 119	android/content/Context:getClassLoader	()Ljava/lang/ClassLoader;
    //   44: ldc_w 411
    //   47: invokevirtual 152	java/lang/ClassLoader:loadClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   50: invokevirtual 414	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   53: checkcast 325	android/os/IBinder
    //   56: invokestatic 420	com/google/android/gms/dynamite/zza$zza:zzce	(Landroid/os/IBinder;)Lcom/google/android/gms/dynamite/zza;
    //   59: astore_0
    //   60: aload_0
    //   61: ifnull +49 -> 110
    //   64: aload_0
    //   65: putstatic 395	com/google/android/gms/dynamite/DynamiteModule:zzaRP	Lcom/google/android/gms/dynamite/zza;
    //   68: ldc 2
    //   70: monitorexit
    //   71: aload_0
    //   72: areturn
    //   73: astore_0
    //   74: ldc 2
    //   76: monitorexit
    //   77: aload_0
    //   78: athrow
    //   79: astore_0
    //   80: aload_0
    //   81: invokevirtual 199	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   84: invokestatic 127	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   87: astore_0
    //   88: aload_0
    //   89: invokevirtual 135	java/lang/String:length	()I
    //   92: ifeq +23 -> 115
    //   95: ldc_w 422
    //   98: aload_0
    //   99: invokevirtual 205	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   102: astore_0
    //   103: ldc -83
    //   105: aload_0
    //   106: invokestatic 185	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   109: pop
    //   110: ldc 2
    //   112: monitorexit
    //   113: aconst_null
    //   114: areturn
    //   115: new 123	java/lang/String
    //   118: dup
    //   119: ldc_w 422
    //   122: invokespecial 208	java/lang/String:<init>	(Ljava/lang/String;)V
    //   125: astore_0
    //   126: goto -23 -> 103
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	129	0	paramContext	Context
    // Exception table:
    //   from	to	target	type
    //   3	16	73	finally
    //   18	31	73	finally
    //   33	60	73	finally
    //   64	68	73	finally
    //   68	71	73	finally
    //   74	77	73	finally
    //   80	103	73	finally
    //   103	110	73	finally
    //   110	113	73	finally
    //   115	126	73	finally
    //   33	60	79	java/lang/Exception
    //   64	68	79	java/lang/Exception
  }
  
  private static int zzc(Context paramContext, String paramString, boolean paramBoolean)
  {
    zza localzza = zzbm(paramContext);
    if (localzza == null) {
      return 0;
    }
    try
    {
      int i = localzza.zza(zzd.zzA(paramContext), paramString, paramBoolean);
      return i;
    }
    catch (RemoteException paramContext)
    {
      paramContext = String.valueOf(paramContext.getMessage());
      if (paramContext.length() == 0) {}
    }
    for (paramContext = "Failed to retrieve remote module version: ".concat(paramContext);; paramContext = new String("Failed to retrieve remote module version: "))
    {
      Log.w("DynamiteModule", paramContext);
      return 0;
    }
  }
  
  private static DynamiteModule zzc(Context paramContext, String paramString, int paramInt)
    throws DynamiteModule.zza
  {
    Log.i("DynamiteModule", String.valueOf(paramString).length() + 51 + "Selected remote version of " + paramString + ", version >= " + paramInt);
    byte[] arrayOfByte;
    zzb localzzb;
    try
    {
      arrayOfByte = (byte[])zzaRR.get(String.valueOf(paramString).length() + 12 + paramString + ":" + paramInt);
      localzzb = zzaRQ;
      if (arrayOfByte == null) {
        throw new zza("Module implementation could not be found.", null);
      }
    }
    finally {}
    if (localzzb == null) {
      throw new zza("DynamiteLoaderV2 was not cached.", null);
    }
    paramContext = zza(paramContext.getApplicationContext(), paramString, arrayOfByte, localzzb);
    if (paramContext == null) {
      throw new zza("Failed to get module context", null);
    }
    return new DynamiteModule(paramContext);
  }
  
  private static int zzd(Context paramContext, String paramString, boolean paramBoolean)
    throws DynamiteModule.zza
  {
    Object localObject2 = null;
    Object localObject1 = null;
    try
    {
      paramContext = zze(paramContext, paramString, paramBoolean);
      if (paramContext != null)
      {
        localObject1 = paramContext;
        localObject2 = paramContext;
        if (paramContext.moveToFirst()) {}
      }
      else
      {
        localObject1 = paramContext;
        localObject2 = paramContext;
        Log.w("DynamiteModule", "Failed to retrieve remote module version.");
        localObject1 = paramContext;
        localObject2 = paramContext;
        throw new zza("Failed to connect to dynamite module ContentResolver.", null);
      }
    }
    catch (Exception paramContext)
    {
      localObject2 = localObject1;
      if (!(paramContext instanceof zza)) {
        break label217;
      }
      localObject2 = localObject1;
      throw paramContext;
    }
    finally
    {
      if (localObject2 != null) {
        ((Cursor)localObject2).close();
      }
    }
    localObject1 = paramContext;
    localObject2 = paramContext;
    int i = paramContext.getInt(0);
    if (i > 0)
    {
      localObject1 = paramContext;
      localObject2 = paramContext;
    }
    try
    {
      localObject1 = Base64.decode(paramContext.getString(3), 0);
      zzaRR.put(String.valueOf(paramString).length() + 12 + paramString + ":" + i, localObject1);
      zzaRS = paramContext.getString(2);
      if (paramContext != null) {
        paramContext.close();
      }
      return i;
    }
    finally
    {
      localObject1 = paramContext;
      localObject2 = paramContext;
    }
    label217:
    localObject2 = localObject1;
    throw new zza("V2 version check failed", paramContext, null);
  }
  
  public static Cursor zze(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (String str1 = "api_force_staging";; str1 = "api")
    {
      String str2 = String.valueOf("content://com.google.android.gms.chimera/");
      paramString = Uri.parse(String.valueOf(str2).length() + 1 + String.valueOf(str1).length() + String.valueOf(paramString).length() + str2 + str1 + "/" + paramString);
      return paramContext.getContentResolver().query(paramString, null, null, null, null);
    }
  }
  
  public Context zzBS()
  {
    return this.zzaRZ;
  }
  
  public IBinder zzdT(String paramString)
    throws DynamiteModule.zza
  {
    try
    {
      IBinder localIBinder = (IBinder)this.zzaRZ.getClassLoader().loadClass(paramString).newInstance();
      return localIBinder;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      paramString = String.valueOf(paramString);
      if (paramString.length() != 0) {}
      for (paramString = "Failed to instantiate module class: ".concat(paramString);; paramString = new String("Failed to instantiate module class: ")) {
        throw new zza(paramString, localClassNotFoundException, null);
      }
    }
    catch (InstantiationException localInstantiationException)
    {
      for (;;) {}
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;) {}
    }
  }
  
  @DynamiteApi
  public static class DynamiteLoaderClassLoader
  {
    public static ClassLoader sClassLoader;
  }
  
  public static class zza
    extends Exception
  {
    private zza(String paramString)
    {
      super();
    }
    
    private zza(String paramString, Throwable paramThrowable)
    {
      super(paramThrowable);
    }
  }
  
  public static abstract interface zzb
  {
    public abstract zzb zza(Context paramContext, String paramString, zza paramzza)
      throws DynamiteModule.zza;
    
    public static abstract interface zza
    {
      public abstract int zzI(Context paramContext, String paramString);
      
      public abstract int zzb(Context paramContext, String paramString, boolean paramBoolean)
        throws DynamiteModule.zza;
    }
    
    public static class zzb
    {
      public int zzaSb = 0;
      public int zzaSc = 0;
      public int zzaSd = 0;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\dynamite\DynamiteModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */