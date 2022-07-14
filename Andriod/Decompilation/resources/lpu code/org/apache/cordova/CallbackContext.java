package org.apache.cordova;

import org.json.JSONArray;
import org.json.JSONObject;

public class CallbackContext
{
  private static final String LOG_TAG = "CordovaPlugin";
  private String callbackId;
  private int changingThreads;
  protected boolean finished;
  private CordovaWebView webView;
  
  public CallbackContext(String paramString, CordovaWebView paramCordovaWebView)
  {
    this.callbackId = paramString;
    this.webView = paramCordovaWebView;
  }
  
  public void error(int paramInt)
  {
    sendPluginResult(new PluginResult(PluginResult.Status.ERROR, paramInt));
  }
  
  public void error(String paramString)
  {
    sendPluginResult(new PluginResult(PluginResult.Status.ERROR, paramString));
  }
  
  public void error(JSONObject paramJSONObject)
  {
    sendPluginResult(new PluginResult(PluginResult.Status.ERROR, paramJSONObject));
  }
  
  public String getCallbackId()
  {
    return this.callbackId;
  }
  
  public boolean isChangingThreads()
  {
    return this.changingThreads > 0;
  }
  
  public boolean isFinished()
  {
    return this.finished;
  }
  
  /* Error */
  public void sendPluginResult(PluginResult paramPluginResult)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 59	org/apache/cordova/CallbackContext:finished	Z
    //   6: ifeq +45 -> 51
    //   9: ldc 8
    //   11: new 61	java/lang/StringBuilder
    //   14: dup
    //   15: invokespecial 62	java/lang/StringBuilder:<init>	()V
    //   18: ldc 64
    //   20: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: aload_0
    //   24: getfield 22	org/apache/cordova/CallbackContext:callbackId	Ljava/lang/String;
    //   27: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: ldc 70
    //   32: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: aload_1
    //   36: invokevirtual 73	org/apache/cordova/PluginResult:getMessage	()Ljava/lang/String;
    //   39: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: invokestatic 82	org/apache/cordova/LOG:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   48: aload_0
    //   49: monitorexit
    //   50: return
    //   51: aload_1
    //   52: invokevirtual 85	org/apache/cordova/PluginResult:getKeepCallback	()Z
    //   55: ifne +27 -> 82
    //   58: iconst_1
    //   59: istore_2
    //   60: aload_0
    //   61: iload_2
    //   62: putfield 59	org/apache/cordova/CallbackContext:finished	Z
    //   65: aload_0
    //   66: monitorexit
    //   67: aload_0
    //   68: getfield 24	org/apache/cordova/CallbackContext:webView	Lorg/apache/cordova/CordovaWebView;
    //   71: aload_1
    //   72: aload_0
    //   73: getfield 22	org/apache/cordova/CallbackContext:callbackId	Ljava/lang/String;
    //   76: invokeinterface 90 3 0
    //   81: return
    //   82: iconst_0
    //   83: istore_2
    //   84: goto -24 -> 60
    //   87: astore_1
    //   88: aload_0
    //   89: monitorexit
    //   90: aload_1
    //   91: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	92	0	this	CallbackContext
    //   0	92	1	paramPluginResult	PluginResult
    //   59	25	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	50	87	finally
    //   51	58	87	finally
    //   60	67	87	finally
    //   88	90	87	finally
  }
  
  public void success()
  {
    sendPluginResult(new PluginResult(PluginResult.Status.OK));
  }
  
  public void success(int paramInt)
  {
    sendPluginResult(new PluginResult(PluginResult.Status.OK, paramInt));
  }
  
  public void success(String paramString)
  {
    sendPluginResult(new PluginResult(PluginResult.Status.OK, paramString));
  }
  
  public void success(JSONArray paramJSONArray)
  {
    sendPluginResult(new PluginResult(PluginResult.Status.OK, paramJSONArray));
  }
  
  public void success(JSONObject paramJSONObject)
  {
    sendPluginResult(new PluginResult(PluginResult.Status.OK, paramJSONObject));
  }
  
  public void success(byte[] paramArrayOfByte)
  {
    sendPluginResult(new PluginResult(PluginResult.Status.OK, paramArrayOfByte));
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\CallbackContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */