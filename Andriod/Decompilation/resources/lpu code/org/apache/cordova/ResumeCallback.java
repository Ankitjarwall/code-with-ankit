package org.apache.cordova;

public class ResumeCallback
  extends CallbackContext
{
  private final String TAG = "CordovaResumeCallback";
  private PluginManager pluginManager;
  private String serviceName;
  
  public ResumeCallback(String paramString, PluginManager paramPluginManager)
  {
    super("resumecallback", null);
    this.serviceName = paramString;
    this.pluginManager = paramPluginManager;
  }
  
  /* Error */
  public void sendPluginResult(PluginResult paramPluginResult)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 33	org/apache/cordova/ResumeCallback:finished	Z
    //   6: ifeq +40 -> 46
    //   9: ldc 18
    //   11: new 35	java/lang/StringBuilder
    //   14: dup
    //   15: invokespecial 38	java/lang/StringBuilder:<init>	()V
    //   18: aload_0
    //   19: getfield 22	org/apache/cordova/ResumeCallback:serviceName	Ljava/lang/String;
    //   22: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: ldc 44
    //   27: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: aload_1
    //   31: invokevirtual 50	org/apache/cordova/PluginResult:getMessage	()Ljava/lang/String;
    //   34: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   40: invokestatic 59	org/apache/cordova/LOG:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   43: aload_0
    //   44: monitorexit
    //   45: return
    //   46: aload_0
    //   47: iconst_1
    //   48: putfield 33	org/apache/cordova/ResumeCallback:finished	Z
    //   51: aload_0
    //   52: monitorexit
    //   53: new 61	org/json/JSONObject
    //   56: dup
    //   57: invokespecial 62	org/json/JSONObject:<init>	()V
    //   60: astore_2
    //   61: new 61	org/json/JSONObject
    //   64: dup
    //   65: invokespecial 62	org/json/JSONObject:<init>	()V
    //   68: astore_3
    //   69: aload_3
    //   70: ldc 64
    //   72: aload_0
    //   73: getfield 22	org/apache/cordova/ResumeCallback:serviceName	Ljava/lang/String;
    //   76: invokevirtual 68	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   79: pop
    //   80: aload_3
    //   81: ldc 70
    //   83: getstatic 74	org/apache/cordova/PluginResult:StatusMessages	[Ljava/lang/String;
    //   86: aload_1
    //   87: invokevirtual 78	org/apache/cordova/PluginResult:getStatus	()I
    //   90: aaload
    //   91: invokevirtual 68	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   94: pop
    //   95: aload_2
    //   96: ldc 80
    //   98: ldc 82
    //   100: invokevirtual 68	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   103: pop
    //   104: aload_2
    //   105: ldc 84
    //   107: aload_3
    //   108: invokevirtual 68	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   111: pop
    //   112: new 46	org/apache/cordova/PluginResult
    //   115: dup
    //   116: getstatic 90	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
    //   119: aload_2
    //   120: invokespecial 93	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
    //   123: astore_2
    //   124: new 95	java/util/ArrayList
    //   127: dup
    //   128: invokespecial 96	java/util/ArrayList:<init>	()V
    //   131: astore_3
    //   132: aload_3
    //   133: aload_2
    //   134: invokeinterface 102 2 0
    //   139: pop
    //   140: aload_3
    //   141: aload_1
    //   142: invokeinterface 102 2 0
    //   147: pop
    //   148: aload_0
    //   149: getfield 24	org/apache/cordova/ResumeCallback:pluginManager	Lorg/apache/cordova/PluginManager;
    //   152: ldc 104
    //   154: invokevirtual 110	org/apache/cordova/PluginManager:getPlugin	(Ljava/lang/String;)Lorg/apache/cordova/CordovaPlugin;
    //   157: checkcast 112	org/apache/cordova/CoreAndroid
    //   160: new 46	org/apache/cordova/PluginResult
    //   163: dup
    //   164: getstatic 90	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
    //   167: aload_3
    //   168: invokespecial 115	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Ljava/util/List;)V
    //   171: invokevirtual 118	org/apache/cordova/CoreAndroid:sendResumeEvent	(Lorg/apache/cordova/PluginResult;)V
    //   174: return
    //   175: astore_1
    //   176: aload_0
    //   177: monitorexit
    //   178: aload_1
    //   179: athrow
    //   180: astore_3
    //   181: ldc 18
    //   183: ldc 120
    //   185: invokestatic 123	org/apache/cordova/LOG:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   188: goto -76 -> 112
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	191	0	this	ResumeCallback
    //   0	191	1	paramPluginResult	PluginResult
    //   60	74	2	localObject1	Object
    //   68	100	3	localObject2	Object
    //   180	1	3	localJSONException	org.json.JSONException
    // Exception table:
    //   from	to	target	type
    //   2	45	175	finally
    //   46	53	175	finally
    //   176	178	175	finally
    //   69	112	180	org/json/JSONException
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\ResumeCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */