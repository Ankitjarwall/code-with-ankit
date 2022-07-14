package com.phonegap.plugins.barcodescanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.zxing.client.android.encode.EncodeActivity;
import java.util.concurrent.ExecutorService;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PermissionHelper;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BarcodeScanner
  extends CordovaPlugin
{
  private static final String CANCELLED = "cancelled";
  private static final String DATA = "data";
  private static final String DISABLE_BEEP = "disableSuccessBeep";
  private static final String EMAIL_TYPE = "EMAIL_TYPE";
  private static final String ENCODE = "encode";
  private static final String FORMAT = "format";
  private static final String FORMATS = "formats";
  private static final String LOG_TAG = "BarcodeScanner";
  private static final String ORIENTATION = "orientation";
  private static final String PHONE_TYPE = "PHONE_TYPE";
  private static final String PREFER_FRONTCAMERA = "preferFrontCamera";
  private static final String PROMPT = "prompt";
  public static final int REQUEST_CODE = 47740;
  private static final String RESULTDISPLAY_DURATION = "resultDisplayDuration";
  private static final String SAVE_HISTORY = "saveHistory";
  private static final String SCAN = "scan";
  private static final String SHOW_FLIP_CAMERA_BUTTON = "showFlipCameraButton";
  private static final String SHOW_TORCH_BUTTON = "showTorchButton";
  private static final String SMS_TYPE = "SMS_TYPE";
  private static final String TEXT = "text";
  private static final String TEXT_TYPE = "TEXT_TYPE";
  private static final String TORCH_ON = "torchOn";
  private static final String TYPE = "type";
  private CallbackContext callbackContext;
  private String[] permissions = { "android.permission.CAMERA" };
  private JSONArray requestArgs;
  
  public void encode(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent(this.cordova.getActivity().getBaseContext(), EncodeActivity.class);
    localIntent.setAction("com.google.zxing.client.android.ENCODE");
    localIntent.putExtra("ENCODE_TYPE", paramString1);
    localIntent.putExtra("ENCODE_DATA", paramString2);
    localIntent.setPackage(this.cordova.getActivity().getApplicationContext().getPackageName());
    this.cordova.getActivity().startActivity(localIntent);
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    this.callbackContext = paramCallbackContext;
    this.requestArgs = paramJSONArray;
    if (paramString.equals("encode"))
    {
      paramString = paramJSONArray.optJSONObject(0);
      if (paramString != null)
      {
        paramJSONArray = paramString.optString("type");
        String str = paramString.optString("data");
        paramString = paramJSONArray;
        if (paramJSONArray == null) {
          paramString = "TEXT_TYPE";
        }
        if (str == null)
        {
          paramCallbackContext.error("User did not specify data to encode");
          return true;
        }
        encode(paramString, str);
        return true;
      }
      paramCallbackContext.error("User did not specify data to encode");
      return true;
    }
    if (paramString.equals("scan"))
    {
      if (!hasPermisssion())
      {
        requestPermissions(0);
        return true;
      }
      scan(paramJSONArray);
      return true;
    }
    return false;
  }
  
  public boolean hasPermisssion()
  {
    String[] arrayOfString = this.permissions;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (!PermissionHelper.hasPermission(this, arrayOfString[i])) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    JSONObject localJSONObject;
    if ((paramInt1 == 47740) && (this.callbackContext != null))
    {
      if (paramInt2 == -1) {
        localJSONObject = new JSONObject();
      }
    }
    else {
      try
      {
        localJSONObject.put("text", paramIntent.getStringExtra("SCAN_RESULT"));
        localJSONObject.put("format", paramIntent.getStringExtra("SCAN_RESULT_FORMAT"));
        localJSONObject.put("cancelled", false);
        this.callbackContext.success(localJSONObject);
        return;
      }
      catch (JSONException paramIntent)
      {
        for (;;)
        {
          Log.d("BarcodeScanner", "This should never happen");
        }
      }
    }
    if (paramInt2 == 0)
    {
      paramIntent = new JSONObject();
      try
      {
        paramIntent.put("text", "");
        paramIntent.put("format", "");
        paramIntent.put("cancelled", true);
        this.callbackContext.success(paramIntent);
        return;
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          Log.d("BarcodeScanner", "This should never happen");
        }
      }
    }
    this.callbackContext.error("Unexpected error");
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
        Log.d("BarcodeScanner", "Permission Denied!");
        paramArrayOfString = new PluginResult(PluginResult.Status.ILLEGAL_ACCESS_EXCEPTION);
        this.callbackContext.sendPluginResult(paramArrayOfString);
        return;
      }
      i += 1;
    }
    switch (paramInt)
    {
    default: 
      return;
    }
    scan(this.requestArgs);
  }
  
  public void onRestoreStateForActivityResult(Bundle paramBundle, CallbackContext paramCallbackContext)
  {
    this.callbackContext = paramCallbackContext;
  }
  
  public void requestPermissions(int paramInt)
  {
    PermissionHelper.requestPermissions(this, paramInt, this.permissions);
  }
  
  public void scan(final JSONArray paramJSONArray)
  {
    this.cordova.getThreadPool().execute(new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: new 34	android/content/Intent
        //   3: dup
        //   4: aload_0
        //   5: getfield 23	com/phonegap/plugins/barcodescanner/BarcodeScanner$1:val$that	Lorg/apache/cordova/CordovaPlugin;
        //   8: getfield 40	org/apache/cordova/CordovaPlugin:cordova	Lorg/apache/cordova/CordovaInterface;
        //   11: invokeinterface 46 1 0
        //   16: invokevirtual 52	android/app/Activity:getBaseContext	()Landroid/content/Context;
        //   19: ldc 54
        //   21: invokespecial 57	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
        //   24: astore 4
        //   26: aload 4
        //   28: ldc 59
        //   30: invokevirtual 63	android/content/Intent:setAction	(Ljava/lang/String;)Landroid/content/Intent;
        //   33: pop
        //   34: aload 4
        //   36: ldc 65
        //   38: invokevirtual 68	android/content/Intent:addCategory	(Ljava/lang/String;)Landroid/content/Intent;
        //   41: pop
        //   42: aload_0
        //   43: getfield 25	com/phonegap/plugins/barcodescanner/BarcodeScanner$1:val$args	Lorg/json/JSONArray;
        //   46: invokevirtual 74	org/json/JSONArray:length	()I
        //   49: ifle +388 -> 437
        //   52: iconst_0
        //   53: istore_1
        //   54: iload_1
        //   55: aload_0
        //   56: getfield 25	com/phonegap/plugins/barcodescanner/BarcodeScanner$1:val$args	Lorg/json/JSONArray;
        //   59: invokevirtual 74	org/json/JSONArray:length	()I
        //   62: if_icmpge +375 -> 437
        //   65: aload_0
        //   66: getfield 25	com/phonegap/plugins/barcodescanner/BarcodeScanner$1:val$args	Lorg/json/JSONArray;
        //   69: iload_1
        //   70: invokevirtual 78	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
        //   73: astore 5
        //   75: aload 5
        //   77: invokevirtual 84	org/json/JSONObject:names	()Lorg/json/JSONArray;
        //   80: astore 6
        //   82: iconst_0
        //   83: istore_2
        //   84: iload_2
        //   85: aload 6
        //   87: invokevirtual 74	org/json/JSONArray:length	()I
        //   90: if_icmpge +108 -> 198
        //   93: aload 6
        //   95: iload_2
        //   96: invokevirtual 88	org/json/JSONArray:getString	(I)Ljava/lang/String;
        //   99: astore 7
        //   101: aload 5
        //   103: aload 7
        //   105: invokevirtual 92	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
        //   108: astore 8
        //   110: aload 8
        //   112: instanceof 94
        //   115: ifeq +43 -> 158
        //   118: aload 4
        //   120: aload 7
        //   122: aload 8
        //   124: checkcast 94	java/lang/Integer
        //   127: invokevirtual 98	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
        //   130: pop
        //   131: iload_2
        //   132: iconst_1
        //   133: iadd
        //   134: istore_2
        //   135: goto -51 -> 84
        //   138: astore 5
        //   140: ldc 100
        //   142: aload 5
        //   144: invokevirtual 104	org/json/JSONException:getLocalizedMessage	()Ljava/lang/String;
        //   147: invokestatic 110	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
        //   150: pop
        //   151: iload_1
        //   152: iconst_1
        //   153: iadd
        //   154: istore_1
        //   155: goto -101 -> 54
        //   158: aload 8
        //   160: instanceof 112
        //   163: ifeq -32 -> 131
        //   166: aload 4
        //   168: aload 7
        //   170: aload 8
        //   172: checkcast 112	java/lang/String
        //   175: invokevirtual 115	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   178: pop
        //   179: goto -48 -> 131
        //   182: astore 7
        //   184: ldc 100
        //   186: aload 7
        //   188: invokevirtual 104	org/json/JSONException:getLocalizedMessage	()Ljava/lang/String;
        //   191: invokestatic 110	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
        //   194: pop
        //   195: goto -64 -> 131
        //   198: aload 5
        //   200: ldc 117
        //   202: iconst_0
        //   203: invokevirtual 121	org/json/JSONObject:optBoolean	(Ljava/lang/String;Z)Z
        //   206: ifeq +221 -> 427
        //   209: iconst_1
        //   210: istore_2
        //   211: aload 4
        //   213: ldc 123
        //   215: iload_2
        //   216: invokevirtual 126	android/content/Intent:putExtra	(Ljava/lang/String;I)Landroid/content/Intent;
        //   219: pop
        //   220: aload 4
        //   222: ldc -128
        //   224: aload 5
        //   226: ldc -126
        //   228: iconst_0
        //   229: invokevirtual 121	org/json/JSONObject:optBoolean	(Ljava/lang/String;Z)Z
        //   232: invokevirtual 133	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
        //   235: pop
        //   236: aload 4
        //   238: ldc -121
        //   240: aload 5
        //   242: ldc -119
        //   244: iconst_0
        //   245: invokevirtual 121	org/json/JSONObject:optBoolean	(Ljava/lang/String;Z)Z
        //   248: invokevirtual 133	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
        //   251: pop
        //   252: aload 4
        //   254: ldc -117
        //   256: aload 5
        //   258: ldc -115
        //   260: iconst_0
        //   261: invokevirtual 121	org/json/JSONObject:optBoolean	(Ljava/lang/String;Z)Z
        //   264: invokevirtual 133	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
        //   267: pop
        //   268: aload 4
        //   270: ldc -113
        //   272: aload 5
        //   274: ldc -111
        //   276: iconst_0
        //   277: invokevirtual 121	org/json/JSONObject:optBoolean	(Ljava/lang/String;Z)Z
        //   280: invokevirtual 133	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
        //   283: pop
        //   284: aload 5
        //   286: ldc -109
        //   288: iconst_0
        //   289: invokevirtual 121	org/json/JSONObject:optBoolean	(Ljava/lang/String;Z)Z
        //   292: ifne +140 -> 432
        //   295: iconst_1
        //   296: istore_3
        //   297: aload 4
        //   299: ldc -107
        //   301: iload_3
        //   302: invokevirtual 133	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
        //   305: pop
        //   306: aload 5
        //   308: ldc -105
        //   310: invokevirtual 155	org/json/JSONObject:has	(Ljava/lang/String;)Z
        //   313: ifeq +36 -> 349
        //   316: aload 4
        //   318: ldc -99
        //   320: new 159	java/lang/StringBuilder
        //   323: dup
        //   324: invokespecial 160	java/lang/StringBuilder:<init>	()V
        //   327: ldc -94
        //   329: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   332: aload 5
        //   334: ldc -105
        //   336: invokevirtual 170	org/json/JSONObject:optLong	(Ljava/lang/String;)J
        //   339: invokevirtual 173	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
        //   342: invokevirtual 176	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   345: invokevirtual 115	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   348: pop
        //   349: aload 5
        //   351: ldc -78
        //   353: invokevirtual 155	org/json/JSONObject:has	(Ljava/lang/String;)Z
        //   356: ifeq +18 -> 374
        //   359: aload 4
        //   361: ldc -76
        //   363: aload 5
        //   365: ldc -78
        //   367: invokevirtual 184	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
        //   370: invokevirtual 115	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   373: pop
        //   374: aload 5
        //   376: ldc -70
        //   378: invokevirtual 155	org/json/JSONObject:has	(Ljava/lang/String;)Z
        //   381: ifeq +18 -> 399
        //   384: aload 4
        //   386: ldc -68
        //   388: aload 5
        //   390: ldc -70
        //   392: invokevirtual 184	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
        //   395: invokevirtual 115	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   398: pop
        //   399: aload 5
        //   401: ldc -66
        //   403: invokevirtual 155	org/json/JSONObject:has	(Ljava/lang/String;)Z
        //   406: ifeq -255 -> 151
        //   409: aload 4
        //   411: ldc -64
        //   413: aload 5
        //   415: ldc -66
        //   417: invokevirtual 184	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
        //   420: invokevirtual 115	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   423: pop
        //   424: goto -273 -> 151
        //   427: iconst_0
        //   428: istore_2
        //   429: goto -218 -> 211
        //   432: iconst_0
        //   433: istore_3
        //   434: goto -137 -> 297
        //   437: aload 4
        //   439: aload_0
        //   440: getfield 23	com/phonegap/plugins/barcodescanner/BarcodeScanner$1:val$that	Lorg/apache/cordova/CordovaPlugin;
        //   443: getfield 40	org/apache/cordova/CordovaPlugin:cordova	Lorg/apache/cordova/CordovaInterface;
        //   446: invokeinterface 46 1 0
        //   451: invokevirtual 195	android/app/Activity:getApplicationContext	()Landroid/content/Context;
        //   454: invokevirtual 200	android/content/Context:getPackageName	()Ljava/lang/String;
        //   457: invokevirtual 203	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
        //   460: pop
        //   461: aload_0
        //   462: getfield 23	com/phonegap/plugins/barcodescanner/BarcodeScanner$1:val$that	Lorg/apache/cordova/CordovaPlugin;
        //   465: getfield 40	org/apache/cordova/CordovaPlugin:cordova	Lorg/apache/cordova/CordovaInterface;
        //   468: aload_0
        //   469: getfield 23	com/phonegap/plugins/barcodescanner/BarcodeScanner$1:val$that	Lorg/apache/cordova/CordovaPlugin;
        //   472: aload 4
        //   474: ldc -52
        //   476: invokeinterface 208 4 0
        //   481: return
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	482	0	this	1
        //   53	102	1	i	int
        //   83	346	2	j	int
        //   296	138	3	bool	boolean
        //   24	449	4	localIntent	Intent
        //   73	29	5	localJSONObject	JSONObject
        //   138	276	5	localJSONException1	JSONException
        //   80	14	6	localJSONArray	JSONArray
        //   99	70	7	str	String
        //   182	5	7	localJSONException2	JSONException
        //   108	63	8	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   65	75	138	org/json/JSONException
        //   93	131	182	org/json/JSONException
        //   158	179	182	org/json/JSONException
      }
    });
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\phonegap\plugins\barcodescanner\BarcodeScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */