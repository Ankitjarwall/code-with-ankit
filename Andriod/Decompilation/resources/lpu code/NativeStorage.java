import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

public class NativeStorage
  extends CordovaPlugin
{
  public static final String TAG = "Native Storage";
  private SharedPreferences.Editor editor;
  private SharedPreferences sharedPref;
  
  public boolean execute(String paramString, final JSONArray paramJSONArray, final CallbackContext paramCallbackContext)
    throws JSONException
  {
    if ("remove".equals(paramString))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          try
          {
            String str = paramJSONArray.getString(0);
            NativeStorage.this.editor.remove(str);
            if (NativeStorage.this.editor.commit())
            {
              paramCallbackContext.success();
              return;
            }
            paramCallbackContext.error("Remove operation failed");
            return;
          }
          catch (Exception localException)
          {
            Log.e("Native Storage", "Removing failed :", localException);
            paramCallbackContext.error(localException.getMessage());
          }
        }
      });
      return true;
    }
    if ("clear".equals(paramString))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          try
          {
            NativeStorage.this.editor.clear();
            if (NativeStorage.this.editor.commit())
            {
              paramCallbackContext.success();
              return;
            }
            paramCallbackContext.error("Clear operation failed");
            return;
          }
          catch (Exception localException)
          {
            Log.e("Native Storage", "Clearing failed :", localException);
            paramCallbackContext.error(localException.getMessage());
          }
        }
      });
      return true;
    }
    if ("putBoolean".equals(paramString))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          try
          {
            String str = paramJSONArray.getString(0);
            Boolean localBoolean = Boolean.valueOf(paramJSONArray.getBoolean(1));
            NativeStorage.this.editor.putBoolean(str, localBoolean.booleanValue());
            if (NativeStorage.this.editor.commit())
            {
              paramCallbackContext.success(String.valueOf(localBoolean));
              return;
            }
            paramCallbackContext.error("Write failed");
            return;
          }
          catch (Exception localException)
          {
            Log.e("Native Storage", "PutBoolean failed :", localException);
            paramCallbackContext.error(localException.getMessage());
          }
        }
      });
      return true;
    }
    if ("getBoolean".equals(paramString))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          try
          {
            String str = paramJSONArray.getString(0);
            boolean bool = NativeStorage.this.sharedPref.getBoolean(str, false);
            paramCallbackContext.success(String.valueOf(Boolean.valueOf(bool)));
            return;
          }
          catch (Exception localException)
          {
            Log.e("Native Storage", "PutBoolean failed :", localException);
            paramCallbackContext.error(localException.getMessage());
          }
        }
      });
      return true;
    }
    if ("putInt".equals(paramString))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          try
          {
            String str = paramJSONArray.getString(0);
            int i = paramJSONArray.getInt(1);
            NativeStorage.this.editor.putInt(str, i);
            if (NativeStorage.this.editor.commit())
            {
              paramCallbackContext.success(i);
              return;
            }
            paramCallbackContext.error("Write failed");
            return;
          }
          catch (Exception localException)
          {
            Log.e("Native Storage", "PutInt failed :", localException);
            paramCallbackContext.error(localException.getMessage());
          }
        }
      });
      return true;
    }
    if ("getInt".equals(paramString))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          try
          {
            String str = paramJSONArray.getString(0);
            int i = NativeStorage.this.sharedPref.getInt(str, -1);
            paramCallbackContext.success(i);
            return;
          }
          catch (Exception localException)
          {
            Log.e("Native Storage", "GetInt failed :", localException);
            paramCallbackContext.error(localException.getMessage());
          }
        }
      });
      return true;
    }
    if ("putDouble".equals(paramString))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          try
          {
            String str = paramJSONArray.getString(0);
            float f = (float)paramJSONArray.getDouble(1);
            NativeStorage.this.editor.putFloat(str, f);
            if (NativeStorage.this.editor.commit())
            {
              paramCallbackContext.success(Float.toString(f));
              return;
            }
            paramCallbackContext.error("Write failed");
            return;
          }
          catch (Exception localException)
          {
            Log.e("Native Storage", "PutFloat failed :", localException);
            paramCallbackContext.error(localException.getMessage());
          }
        }
      });
      return true;
    }
    if ("getDouble".equals(paramString))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          try
          {
            String str = paramJSONArray.getString(0);
            float f = NativeStorage.this.sharedPref.getFloat(str, -1.0F);
            paramCallbackContext.success(Float.toString(f));
            return;
          }
          catch (Exception localException)
          {
            Log.e("Native Storage", "GetFloat failed :", localException);
            paramCallbackContext.error(localException.getMessage());
          }
        }
      });
      return true;
    }
    if ("putString".equals(paramString))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          try
          {
            String str1 = paramJSONArray.getString(0);
            String str2 = paramJSONArray.getString(1);
            NativeStorage.this.editor.putString(str1, str2);
            if (NativeStorage.this.editor.commit())
            {
              paramCallbackContext.success(str2);
              return;
            }
            paramCallbackContext.error("Write failed");
            return;
          }
          catch (Exception localException)
          {
            Log.e("Native Storage", "PutString failed :", localException);
            paramCallbackContext.error(localException.getMessage());
          }
        }
      });
      return true;
    }
    if ("getString".equals(paramString))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          try
          {
            String str = paramJSONArray.getString(0);
            str = NativeStorage.this.sharedPref.getString(str, "null");
            paramCallbackContext.success(str);
            return;
          }
          catch (Exception localException)
          {
            Log.e("Native Storage", "GetString failed :", localException);
            paramCallbackContext.error(localException.getMessage());
          }
        }
      });
      return true;
    }
    if ("setItem".equals(paramString))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          try
          {
            String str1 = paramJSONArray.getString(0);
            String str2 = paramJSONArray.getString(1);
            NativeStorage.this.editor.putString(str1, str2);
            if (NativeStorage.this.editor.commit())
            {
              paramCallbackContext.success(str2);
              return;
            }
            paramCallbackContext.error(1);
            return;
          }
          catch (Exception localException)
          {
            Log.e("Native Storage", "setItem :", localException);
            paramCallbackContext.error(localException.getMessage());
          }
        }
      });
      return true;
    }
    if ("setItemWithPassword".equals(paramString))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        /* Error */
        public void run()
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 23	NativeStorage$12:val$args	Lorg/json/JSONArray;
          //   4: iconst_0
          //   5: invokevirtual 54	org/json/JSONArray:getString	(I)Ljava/lang/String;
          //   8: astore_3
          //   9: aload_0
          //   10: getfield 23	NativeStorage$12:val$args	Lorg/json/JSONArray;
          //   13: iconst_1
          //   14: invokevirtual 54	org/json/JSONArray:getString	(I)Ljava/lang/String;
          //   17: astore 4
          //   19: aload_0
          //   20: getfield 23	NativeStorage$12:val$args	Lorg/json/JSONArray;
          //   23: iconst_2
          //   24: invokevirtual 54	org/json/JSONArray:getString	(I)Ljava/lang/String;
          //   27: astore_2
          //   28: ldc 56
          //   30: astore_1
          //   31: aload 4
          //   33: aload_2
          //   34: invokestatic 62	Crypto:encrypt	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
          //   37: astore_2
          //   38: aload_2
          //   39: astore_1
          //   40: aload_1
          //   41: ldc 56
          //   43: invokevirtual 68	java/lang/String:equals	(Ljava/lang/Object;)Z
          //   46: ifne +230 -> 276
          //   49: aload_0
          //   50: getfield 21	NativeStorage$12:this$0	LNativeStorage;
          //   53: invokestatic 72	NativeStorage:access$000	(LNativeStorage;)Landroid/content/SharedPreferences$Editor;
          //   56: aload_3
          //   57: aload_1
          //   58: invokeinterface 78 3 0
          //   63: pop
          //   64: aload_0
          //   65: getfield 21	NativeStorage$12:this$0	LNativeStorage;
          //   68: invokestatic 72	NativeStorage:access$000	(LNativeStorage;)Landroid/content/SharedPreferences$Editor;
          //   71: invokeinterface 82 1 0
          //   76: ifeq +191 -> 267
          //   79: aload_0
          //   80: getfield 25	NativeStorage$12:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   83: aload 4
          //   85: invokevirtual 88	org/apache/cordova/CallbackContext:success	(Ljava/lang/String;)V
          //   88: return
          //   89: astore_2
          //   90: aload_2
          //   91: invokestatic 94	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
          //   94: aload_0
          //   95: getfield 25	NativeStorage$12:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   98: aload_2
          //   99: invokevirtual 98	java/security/spec/InvalidKeySpecException:getMessage	()Ljava/lang/String;
          //   102: invokevirtual 101	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   105: goto -65 -> 40
          //   108: astore_1
          //   109: ldc 103
          //   111: ldc 105
          //   113: aload_1
          //   114: invokestatic 111	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   117: pop
          //   118: aload_1
          //   119: invokestatic 94	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
          //   122: aload_0
          //   123: getfield 25	NativeStorage$12:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   126: aload_1
          //   127: invokevirtual 112	java/lang/Exception:getMessage	()Ljava/lang/String;
          //   130: invokevirtual 101	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   133: return
          //   134: astore_2
          //   135: aload_2
          //   136: invokestatic 94	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
          //   139: aload_0
          //   140: getfield 25	NativeStorage$12:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   143: aload_2
          //   144: invokevirtual 113	java/security/NoSuchAlgorithmException:getMessage	()Ljava/lang/String;
          //   147: invokevirtual 101	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   150: goto -110 -> 40
          //   153: astore_2
          //   154: aload_2
          //   155: invokestatic 94	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
          //   158: aload_0
          //   159: getfield 25	NativeStorage$12:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   162: aload_2
          //   163: invokevirtual 114	javax/crypto/NoSuchPaddingException:getMessage	()Ljava/lang/String;
          //   166: invokevirtual 101	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   169: goto -129 -> 40
          //   172: astore_2
          //   173: aload_2
          //   174: invokestatic 94	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
          //   177: aload_0
          //   178: getfield 25	NativeStorage$12:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   181: aload_2
          //   182: invokevirtual 115	java/security/InvalidAlgorithmParameterException:getMessage	()Ljava/lang/String;
          //   185: invokevirtual 101	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   188: goto -148 -> 40
          //   191: astore_2
          //   192: aload_2
          //   193: invokestatic 94	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
          //   196: aload_0
          //   197: getfield 25	NativeStorage$12:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   200: aload_2
          //   201: invokevirtual 116	java/security/InvalidKeyException:getMessage	()Ljava/lang/String;
          //   204: invokevirtual 101	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   207: goto -167 -> 40
          //   210: astore_2
          //   211: aload_2
          //   212: invokestatic 94	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
          //   215: aload_0
          //   216: getfield 25	NativeStorage$12:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   219: aload_2
          //   220: invokevirtual 117	java/io/UnsupportedEncodingException:getMessage	()Ljava/lang/String;
          //   223: invokevirtual 101	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   226: goto -186 -> 40
          //   229: astore_2
          //   230: aload_2
          //   231: invokestatic 94	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
          //   234: aload_0
          //   235: getfield 25	NativeStorage$12:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   238: aload_2
          //   239: invokevirtual 118	javax/crypto/BadPaddingException:getMessage	()Ljava/lang/String;
          //   242: invokevirtual 101	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   245: goto -205 -> 40
          //   248: astore_2
          //   249: aload_2
          //   250: invokestatic 94	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
          //   253: aload_0
          //   254: getfield 25	NativeStorage$12:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   257: aload_2
          //   258: invokevirtual 119	javax/crypto/IllegalBlockSizeException:getMessage	()Ljava/lang/String;
          //   261: invokevirtual 101	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   264: goto -224 -> 40
          //   267: aload_0
          //   268: getfield 25	NativeStorage$12:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   271: iconst_1
          //   272: invokevirtual 122	org/apache/cordova/CallbackContext:error	(I)V
          //   275: return
          //   276: aload_0
          //   277: getfield 25	NativeStorage$12:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   280: ldc 124
          //   282: invokevirtual 101	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   285: return
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	286	0	this	12
          //   30	28	1	str1	String
          //   108	19	1	localException	Exception
          //   27	12	2	str2	String
          //   89	10	2	localInvalidKeySpecException	java.security.spec.InvalidKeySpecException
          //   134	10	2	localNoSuchAlgorithmException	java.security.NoSuchAlgorithmException
          //   153	10	2	localNoSuchPaddingException	javax.crypto.NoSuchPaddingException
          //   172	10	2	localInvalidAlgorithmParameterException	java.security.InvalidAlgorithmParameterException
          //   191	10	2	localInvalidKeyException	java.security.InvalidKeyException
          //   210	10	2	localUnsupportedEncodingException	java.io.UnsupportedEncodingException
          //   229	10	2	localBadPaddingException	javax.crypto.BadPaddingException
          //   248	10	2	localIllegalBlockSizeException	javax.crypto.IllegalBlockSizeException
          //   8	49	3	str3	String
          //   17	67	4	str4	String
          // Exception table:
          //   from	to	target	type
          //   31	38	89	java/security/spec/InvalidKeySpecException
          //   0	28	108	java/lang/Exception
          //   31	38	108	java/lang/Exception
          //   40	88	108	java/lang/Exception
          //   90	105	108	java/lang/Exception
          //   135	150	108	java/lang/Exception
          //   154	169	108	java/lang/Exception
          //   173	188	108	java/lang/Exception
          //   192	207	108	java/lang/Exception
          //   211	226	108	java/lang/Exception
          //   230	245	108	java/lang/Exception
          //   249	264	108	java/lang/Exception
          //   267	275	108	java/lang/Exception
          //   276	285	108	java/lang/Exception
          //   31	38	134	java/security/NoSuchAlgorithmException
          //   31	38	153	javax/crypto/NoSuchPaddingException
          //   31	38	172	java/security/InvalidAlgorithmParameterException
          //   31	38	191	java/security/InvalidKeyException
          //   31	38	210	java/io/UnsupportedEncodingException
          //   31	38	229	javax/crypto/BadPaddingException
          //   31	38	248	javax/crypto/IllegalBlockSizeException
        }
      });
      return true;
    }
    if ("getItem".equals(paramString))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          try
          {
            String str = paramJSONArray.getString(0);
            str = NativeStorage.this.sharedPref.getString(str, "nativestorage_null");
            if (str.equals("nativestorage_null"))
            {
              paramCallbackContext.error(2);
              return;
            }
            paramCallbackContext.success(str);
            return;
          }
          catch (Exception localException)
          {
            Log.e("Native Storage", "getItem failed :", localException);
            paramCallbackContext.error(localException.getMessage());
          }
        }
      });
      return true;
    }
    if ("getItemWithPassword".equals(paramString))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        /* Error */
        public void run()
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 23	NativeStorage$14:val$args	Lorg/json/JSONArray;
          //   4: iconst_0
          //   5: invokevirtual 54	org/json/JSONArray:getString	(I)Ljava/lang/String;
          //   8: astore_2
          //   9: aload_0
          //   10: getfield 23	NativeStorage$14:val$args	Lorg/json/JSONArray;
          //   13: iconst_1
          //   14: invokevirtual 54	org/json/JSONArray:getString	(I)Ljava/lang/String;
          //   17: astore_1
          //   18: aload_0
          //   19: getfield 21	NativeStorage$14:this$0	LNativeStorage;
          //   22: invokestatic 58	NativeStorage:access$100	(LNativeStorage;)Landroid/content/SharedPreferences;
          //   25: aload_2
          //   26: ldc 60
          //   28: invokeinterface 65 3 0
          //   33: astore_2
          //   34: aload_2
          //   35: ldc 60
          //   37: invokevirtual 71	java/lang/String:equals	(Ljava/lang/Object;)Z
          //   40: ifeq +12 -> 52
          //   43: aload_0
          //   44: getfield 25	NativeStorage$14:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   47: iconst_2
          //   48: invokevirtual 77	org/apache/cordova/CallbackContext:error	(I)V
          //   51: return
          //   52: aload_2
          //   53: aload_1
          //   54: invokestatic 82	Crypto:decryptPbkdf2	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
          //   57: astore_1
          //   58: aload_0
          //   59: getfield 25	NativeStorage$14:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   62: aload_1
          //   63: invokevirtual 86	org/apache/cordova/CallbackContext:success	(Ljava/lang/String;)V
          //   66: return
          //   67: astore_1
          //   68: aload_1
          //   69: invokestatic 92	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
          //   72: aload_0
          //   73: getfield 25	NativeStorage$14:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   76: aload_1
          //   77: invokevirtual 96	java/security/spec/InvalidKeySpecException:getMessage	()Ljava/lang/String;
          //   80: invokevirtual 98	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   83: return
          //   84: astore_1
          //   85: ldc 100
          //   87: ldc 102
          //   89: aload_1
          //   90: invokestatic 108	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   93: pop
          //   94: aload_0
          //   95: getfield 25	NativeStorage$14:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   98: aload_1
          //   99: invokevirtual 109	java/lang/Exception:getMessage	()Ljava/lang/String;
          //   102: invokevirtual 98	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   105: return
          //   106: astore_1
          //   107: aload_1
          //   108: invokestatic 92	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
          //   111: aload_0
          //   112: getfield 25	NativeStorage$14:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   115: aload_1
          //   116: invokevirtual 110	java/security/NoSuchAlgorithmException:getMessage	()Ljava/lang/String;
          //   119: invokevirtual 98	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   122: return
          //   123: astore_1
          //   124: aload_1
          //   125: invokestatic 92	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
          //   128: aload_0
          //   129: getfield 25	NativeStorage$14:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   132: aload_1
          //   133: invokevirtual 111	javax/crypto/NoSuchPaddingException:getMessage	()Ljava/lang/String;
          //   136: invokevirtual 98	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   139: return
          //   140: astore_1
          //   141: aload_1
          //   142: invokestatic 92	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
          //   145: aload_0
          //   146: getfield 25	NativeStorage$14:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   149: aload_1
          //   150: invokevirtual 112	java/security/InvalidAlgorithmParameterException:getMessage	()Ljava/lang/String;
          //   153: invokevirtual 98	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   156: return
          //   157: astore_1
          //   158: aload_1
          //   159: invokestatic 92	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
          //   162: aload_0
          //   163: getfield 25	NativeStorage$14:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   166: aload_1
          //   167: invokevirtual 113	java/security/InvalidKeyException:getMessage	()Ljava/lang/String;
          //   170: invokevirtual 98	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   173: return
          //   174: astore_1
          //   175: aload_1
          //   176: invokestatic 92	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
          //   179: aload_0
          //   180: getfield 25	NativeStorage$14:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   183: aload_1
          //   184: invokevirtual 114	java/io/UnsupportedEncodingException:getMessage	()Ljava/lang/String;
          //   187: invokevirtual 98	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   190: return
          //   191: astore_1
          //   192: aload_1
          //   193: invokestatic 92	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
          //   196: aload_0
          //   197: getfield 25	NativeStorage$14:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   200: aload_1
          //   201: invokevirtual 115	javax/crypto/BadPaddingException:getMessage	()Ljava/lang/String;
          //   204: invokevirtual 98	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   207: return
          //   208: astore_1
          //   209: aload_1
          //   210: invokestatic 92	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
          //   213: aload_0
          //   214: getfield 25	NativeStorage$14:val$callbackContext	Lorg/apache/cordova/CallbackContext;
          //   217: aload_1
          //   218: invokevirtual 116	javax/crypto/IllegalBlockSizeException:getMessage	()Ljava/lang/String;
          //   221: invokevirtual 98	org/apache/cordova/CallbackContext:error	(Ljava/lang/String;)V
          //   224: return
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	225	0	this	14
          //   17	46	1	str1	String
          //   67	10	1	localInvalidKeySpecException	java.security.spec.InvalidKeySpecException
          //   84	15	1	localException	Exception
          //   106	10	1	localNoSuchAlgorithmException	java.security.NoSuchAlgorithmException
          //   123	10	1	localNoSuchPaddingException	javax.crypto.NoSuchPaddingException
          //   140	10	1	localInvalidAlgorithmParameterException	java.security.InvalidAlgorithmParameterException
          //   157	10	1	localInvalidKeyException	java.security.InvalidKeyException
          //   174	10	1	localUnsupportedEncodingException	java.io.UnsupportedEncodingException
          //   191	10	1	localBadPaddingException	javax.crypto.BadPaddingException
          //   208	10	1	localIllegalBlockSizeException	javax.crypto.IllegalBlockSizeException
          //   8	45	2	str2	String
          // Exception table:
          //   from	to	target	type
          //   52	66	67	java/security/spec/InvalidKeySpecException
          //   0	51	84	java/lang/Exception
          //   52	66	84	java/lang/Exception
          //   68	83	84	java/lang/Exception
          //   107	122	84	java/lang/Exception
          //   124	139	84	java/lang/Exception
          //   141	156	84	java/lang/Exception
          //   158	173	84	java/lang/Exception
          //   175	190	84	java/lang/Exception
          //   192	207	84	java/lang/Exception
          //   209	224	84	java/lang/Exception
          //   52	66	106	java/security/NoSuchAlgorithmException
          //   52	66	123	javax/crypto/NoSuchPaddingException
          //   52	66	140	java/security/InvalidAlgorithmParameterException
          //   52	66	157	java/security/InvalidKeyException
          //   52	66	174	java/io/UnsupportedEncodingException
          //   52	66	191	javax/crypto/BadPaddingException
          //   52	66	208	javax/crypto/IllegalBlockSizeException
        }
      });
      return true;
    }
    if ("keys".equals(paramString))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          try
          {
            Map localMap = NativeStorage.this.sharedPref.getAll();
            paramCallbackContext.success(new JSONArray(localMap.keySet()));
            return;
          }
          catch (Exception localException)
          {
            Log.e("Native Storage", "Get keys failed :", localException);
            paramCallbackContext.error(localException.getMessage());
          }
        }
      });
      return true;
    }
    return false;
  }
  
  public void initialize(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView)
  {
    super.initialize(paramCordovaInterface, paramCordovaWebView);
    Log.v("Native Storage", "Init NativeStorage");
    paramCordovaWebView = this.preferences.getString("NativeStorageSharedPreferencesName", "NativeStorage");
    this.sharedPref = paramCordovaInterface.getActivity().getSharedPreferences(paramCordovaWebView, 0);
    this.editor = this.sharedPref.edit();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\NativeStorage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */