import org.apache.cordova.CallbackContext;
import org.json.JSONArray;

class NativeStorage$12
  implements Runnable
{
  NativeStorage$12(NativeStorage paramNativeStorage, JSONArray paramJSONArray, CallbackContext paramCallbackContext) {}
  
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
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\NativeStorage$12.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */