package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatUtil
{
  private static final String CACHE_FILE_PREFIX = ".font";
  private static final String TAG = "TypefaceCompatUtil";
  
  public static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable) {}
  }
  
  @Nullable
  @RequiresApi(19)
  public static ByteBuffer copyToDirectBuffer(Context paramContext, Resources paramResources, int paramInt)
  {
    paramContext = getTempFile(paramContext);
    if (paramContext == null) {
      return null;
    }
    try
    {
      boolean bool = copyToFile(paramContext, paramResources, paramInt);
      if (!bool) {
        return null;
      }
      paramResources = mmap(paramContext);
      return paramResources;
    }
    finally
    {
      paramContext.delete();
    }
  }
  
  public static boolean copyToFile(File paramFile, Resources paramResources, int paramInt)
  {
    Resources localResources = null;
    try
    {
      paramResources = paramResources.openRawResource(paramInt);
      localResources = paramResources;
      boolean bool = copyToFile(paramFile, paramResources);
      return bool;
    }
    finally
    {
      closeQuietly(localResources);
    }
  }
  
  /* Error */
  public static boolean copyToFile(File paramFile, java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: new 66	java/io/FileOutputStream
    //   8: dup
    //   9: aload_0
    //   10: iconst_0
    //   11: invokespecial 69	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   14: astore_0
    //   15: sipush 1024
    //   18: newarray <illegal type>
    //   20: astore_3
    //   21: aload_1
    //   22: aload_3
    //   23: invokevirtual 75	java/io/InputStream:read	([B)I
    //   26: istore_2
    //   27: iload_2
    //   28: iconst_m1
    //   29: if_icmpeq +50 -> 79
    //   32: aload_0
    //   33: aload_3
    //   34: iconst_0
    //   35: iload_2
    //   36: invokevirtual 79	java/io/FileOutputStream:write	([BII)V
    //   39: goto -18 -> 21
    //   42: astore_1
    //   43: aload_0
    //   44: astore_3
    //   45: ldc 15
    //   47: new 81	java/lang/StringBuilder
    //   50: dup
    //   51: invokespecial 82	java/lang/StringBuilder:<init>	()V
    //   54: ldc 84
    //   56: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: aload_1
    //   60: invokevirtual 92	java/io/IOException:getMessage	()Ljava/lang/String;
    //   63: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   69: invokestatic 101	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   72: pop
    //   73: aload_0
    //   74: invokestatic 64	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   77: iconst_0
    //   78: ireturn
    //   79: aload_0
    //   80: invokestatic 64	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   83: iconst_1
    //   84: ireturn
    //   85: astore_0
    //   86: aload_3
    //   87: invokestatic 64	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   90: aload_0
    //   91: athrow
    //   92: astore_1
    //   93: aload_0
    //   94: astore_3
    //   95: aload_1
    //   96: astore_0
    //   97: goto -11 -> 86
    //   100: astore_1
    //   101: aload 4
    //   103: astore_0
    //   104: goto -61 -> 43
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	107	0	paramFile	File
    //   0	107	1	paramInputStream	java.io.InputStream
    //   26	10	2	i	int
    //   1	94	3	localObject1	Object
    //   3	99	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   15	21	42	java/io/IOException
    //   21	27	42	java/io/IOException
    //   32	39	42	java/io/IOException
    //   5	15	85	finally
    //   45	73	85	finally
    //   15	21	92	finally
    //   21	27	92	finally
    //   32	39	92	finally
    //   5	15	100	java/io/IOException
  }
  
  @Nullable
  public static File getTempFile(Context paramContext)
  {
    String str = ".font" + Process.myPid() + "-" + Process.myTid() + "-";
    int i = 0;
    while (i < 100)
    {
      File localFile = new File(paramContext.getCacheDir(), str + i);
      try
      {
        boolean bool = localFile.createNewFile();
        if (bool) {
          return localFile;
        }
      }
      catch (IOException localIOException)
      {
        i += 1;
      }
    }
    return null;
  }
  
  /* Error */
  @Nullable
  @RequiresApi(19)
  public static ByteBuffer mmap(Context paramContext, android.os.CancellationSignal paramCancellationSignal, android.net.Uri paramUri)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 134	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: astore_0
    //   5: aload_0
    //   6: aload_2
    //   7: ldc -120
    //   9: aload_1
    //   10: invokevirtual 142	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/os/ParcelFileDescriptor;
    //   13: astore_2
    //   14: aload_2
    //   15: ifnonnull +35 -> 50
    //   18: aload_2
    //   19: ifnull +11 -> 30
    //   22: iconst_0
    //   23: ifeq +20 -> 43
    //   26: aload_2
    //   27: invokevirtual 145	android/os/ParcelFileDescriptor:close	()V
    //   30: aconst_null
    //   31: astore_1
    //   32: aload_1
    //   33: areturn
    //   34: astore_0
    //   35: aconst_null
    //   36: aload_0
    //   37: invokestatic 151	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:addSuppressed	(Ljava/lang/Throwable;Ljava/lang/Throwable;)V
    //   40: goto -10 -> 30
    //   43: aload_2
    //   44: invokevirtual 145	android/os/ParcelFileDescriptor:close	()V
    //   47: goto -17 -> 30
    //   50: new 153	java/io/FileInputStream
    //   53: dup
    //   54: aload_2
    //   55: invokevirtual 157	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   58: invokespecial 160	java/io/FileInputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   61: astore 5
    //   63: aload 5
    //   65: invokevirtual 164	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   68: astore_0
    //   69: aload_0
    //   70: invokevirtual 170	java/nio/channels/FileChannel:size	()J
    //   73: lstore_3
    //   74: aload_0
    //   75: getstatic 176	java/nio/channels/FileChannel$MapMode:READ_ONLY	Ljava/nio/channels/FileChannel$MapMode;
    //   78: lconst_0
    //   79: lload_3
    //   80: invokevirtual 180	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
    //   83: astore_0
    //   84: aload 5
    //   86: ifnull +12 -> 98
    //   89: iconst_0
    //   90: ifeq +59 -> 149
    //   93: aload 5
    //   95: invokevirtual 181	java/io/FileInputStream:close	()V
    //   98: aload_0
    //   99: astore_1
    //   100: aload_2
    //   101: ifnull -69 -> 32
    //   104: iconst_0
    //   105: ifeq +58 -> 163
    //   108: aload_2
    //   109: invokevirtual 145	android/os/ParcelFileDescriptor:close	()V
    //   112: aload_0
    //   113: areturn
    //   114: astore_1
    //   115: aconst_null
    //   116: aload_1
    //   117: invokestatic 151	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:addSuppressed	(Ljava/lang/Throwable;Ljava/lang/Throwable;)V
    //   120: aload_0
    //   121: areturn
    //   122: astore_1
    //   123: aconst_null
    //   124: aload_1
    //   125: invokestatic 151	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:addSuppressed	(Ljava/lang/Throwable;Ljava/lang/Throwable;)V
    //   128: goto -30 -> 98
    //   131: astore_1
    //   132: aload_1
    //   133: athrow
    //   134: astore_0
    //   135: aload_2
    //   136: ifnull +11 -> 147
    //   139: aload_1
    //   140: ifnull +77 -> 217
    //   143: aload_2
    //   144: invokevirtual 145	android/os/ParcelFileDescriptor:close	()V
    //   147: aload_0
    //   148: athrow
    //   149: aload 5
    //   151: invokevirtual 181	java/io/FileInputStream:close	()V
    //   154: goto -56 -> 98
    //   157: astore_0
    //   158: aconst_null
    //   159: astore_1
    //   160: goto -25 -> 135
    //   163: aload_2
    //   164: invokevirtual 145	android/os/ParcelFileDescriptor:close	()V
    //   167: aload_0
    //   168: areturn
    //   169: astore_1
    //   170: aload_1
    //   171: athrow
    //   172: astore_0
    //   173: aload 5
    //   175: ifnull +12 -> 187
    //   178: aload_1
    //   179: ifnull +21 -> 200
    //   182: aload 5
    //   184: invokevirtual 181	java/io/FileInputStream:close	()V
    //   187: aload_0
    //   188: athrow
    //   189: astore 5
    //   191: aload_1
    //   192: aload 5
    //   194: invokestatic 151	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:addSuppressed	(Ljava/lang/Throwable;Ljava/lang/Throwable;)V
    //   197: goto -10 -> 187
    //   200: aload 5
    //   202: invokevirtual 181	java/io/FileInputStream:close	()V
    //   205: goto -18 -> 187
    //   208: astore_2
    //   209: aload_1
    //   210: aload_2
    //   211: invokestatic 151	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:addSuppressed	(Ljava/lang/Throwable;Ljava/lang/Throwable;)V
    //   214: goto -67 -> 147
    //   217: aload_2
    //   218: invokevirtual 145	android/os/ParcelFileDescriptor:close	()V
    //   221: goto -74 -> 147
    //   224: astore_0
    //   225: aconst_null
    //   226: astore_1
    //   227: goto -54 -> 173
    //   230: astore_0
    //   231: aconst_null
    //   232: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	233	0	paramContext	Context
    //   0	233	1	paramCancellationSignal	android.os.CancellationSignal
    //   0	233	2	paramUri	android.net.Uri
    //   73	7	3	l	long
    //   61	122	5	localFileInputStream	FileInputStream
    //   189	12	5	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   26	30	34	java/lang/Throwable
    //   108	112	114	java/lang/Throwable
    //   93	98	122	java/lang/Throwable
    //   50	63	131	java/lang/Throwable
    //   123	128	131	java/lang/Throwable
    //   149	154	131	java/lang/Throwable
    //   187	189	131	java/lang/Throwable
    //   191	197	131	java/lang/Throwable
    //   200	205	131	java/lang/Throwable
    //   132	134	134	finally
    //   50	63	157	finally
    //   93	98	157	finally
    //   123	128	157	finally
    //   149	154	157	finally
    //   182	187	157	finally
    //   187	189	157	finally
    //   191	197	157	finally
    //   200	205	157	finally
    //   63	84	169	java/lang/Throwable
    //   170	172	172	finally
    //   182	187	189	java/lang/Throwable
    //   143	147	208	java/lang/Throwable
    //   63	84	224	finally
    //   5	14	230	java/io/IOException
    //   26	30	230	java/io/IOException
    //   35	40	230	java/io/IOException
    //   43	47	230	java/io/IOException
    //   108	112	230	java/io/IOException
    //   115	120	230	java/io/IOException
    //   143	147	230	java/io/IOException
    //   147	149	230	java/io/IOException
    //   163	167	230	java/io/IOException
    //   209	214	230	java/io/IOException
    //   217	221	230	java/io/IOException
  }
  
  @Nullable
  @RequiresApi(19)
  private static ByteBuffer mmap(File paramFile)
  {
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(paramFile);
      try
      {
        paramFile = localFileInputStream.getChannel();
        long l = paramFile.size();
        paramFile = paramFile.map(FileChannel.MapMode.READ_ONLY, 0L, l);
        if ((localFileInputStream == null) || (0 != 0)) {
          try
          {
            localFileInputStream.close();
            return paramFile;
          }
          catch (Throwable localThrowable1)
          {
            ThrowableExtension.addSuppressed(null, localThrowable1);
            return paramFile;
          }
        }
        localFileInputStream.close();
        return paramFile;
      }
      catch (Throwable localThrowable2)
      {
        localThrowable2 = localThrowable2;
        try
        {
          throw localThrowable2;
        }
        finally
        {
          if ((localFileInputStream == null) || (localThrowable2 != null)) {
            try
            {
              localFileInputStream.close();
              throw paramFile;
            }
            catch (Throwable localThrowable3)
            {
              for (;;)
              {
                ThrowableExtension.addSuppressed(localThrowable2, localThrowable3);
              }
            }
          }
          localThrowable3.close();
        }
      }
      finally
      {
        for (;;)
        {
          paramFile = finally;
          Object localObject = null;
        }
      }
      return null;
    }
    catch (IOException paramFile) {}
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\graphics\TypefaceCompatUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */