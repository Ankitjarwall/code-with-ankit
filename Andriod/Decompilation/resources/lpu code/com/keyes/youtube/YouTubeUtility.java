package com.keyes.youtube;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class YouTubeUtility
{
  public static String calculateYouTubeUrl(String paramString1, boolean paramBoolean, String paramString2)
    throws IOException, ClientProtocolException, UnsupportedEncodingException
  {
    Object localObject1 = null;
    paramString2 = new DefaultHttpClient().execute(new HttpGet("http://www.youtube.com/get_video_info?&video_id=" + paramString2));
    Object localObject2 = new ByteArrayOutputStream();
    paramString2.getEntity().writeTo((OutputStream)localObject2);
    localObject2 = new String(((ByteArrayOutputStream)localObject2).toString("UTF-8")).split("&");
    paramString2 = new HashMap();
    int i = 0;
    Object localObject3;
    int j;
    if (i >= localObject2.length)
    {
      localObject3 = URLDecoder.decode((String)paramString2.get("fmt_list"));
      localObject2 = new ArrayList();
      if (localObject3 != null)
      {
        localObject3 = ((String)localObject3).split(",");
        j = localObject3.length;
        i = 0;
        label145:
        if (i < j) {
          break label303;
        }
      }
      localObject3 = (String)paramString2.get("url_encoded_fmt_stream_map");
      paramString2 = (String)localObject1;
      if (localObject3 != null)
      {
        paramString2 = ((String)localObject3).split(",");
        localObject3 = new ArrayList();
        j = paramString2.length;
        i = 0;
        label195:
        if (i < j) {
          break label327;
        }
      }
    }
    for (paramString1 = new Format(Integer.parseInt(paramString1));; paramString1 = new Format(j))
    {
      if ((((ArrayList)localObject2).contains(paramString1)) || (!paramBoolean)) {}
      label303:
      label327:
      do
      {
        i = ((ArrayList)localObject2).indexOf(paramString1);
        paramString2 = (String)localObject1;
        if (i >= 0) {
          paramString2 = ((VideoStream)((ArrayList)localObject3).get(i)).getUrl();
        }
        return paramString2;
        localObject3 = localObject2[i].split("=");
        if ((localObject3 != null) && (localObject3.length >= 2)) {
          paramString2.put(localObject3[0], URLDecoder.decode(localObject3[1]));
        }
        i += 1;
        break;
        ((ArrayList)localObject2).add(new Format(localObject3[i]));
        i += 1;
        break label145;
        ((ArrayList)localObject3).add(new VideoStream(paramString2[i]));
        i += 1;
        break label195;
        i = paramString1.getId();
        j = getSupportedFallbackId(i);
      } while (i == j);
    }
  }
  
  public static int getSupportedFallbackId(int paramInt)
  {
    int[] arrayOfInt = new int[5];
    int[] tmp7_5 = arrayOfInt;
    tmp7_5[0] = 13;
    int[] tmp12_7 = tmp7_5;
    tmp12_7[1] = 17;
    int[] tmp17_12 = tmp12_7;
    tmp17_12[2] = 18;
    int[] tmp22_17 = tmp17_12;
    tmp22_17[3] = 22;
    int[] tmp27_22 = tmp22_17;
    tmp27_22[4] = 37;
    tmp27_22;
    int j = paramInt;
    int i = arrayOfInt.length - 1;
    for (;;)
    {
      if (i < 0) {
        return j;
      }
      int k = j;
      if (paramInt == arrayOfInt[i])
      {
        k = j;
        if (i > 0) {
          k = arrayOfInt[(i - 1)];
        }
      }
      i -= 1;
      j = k;
    }
  }
  
  public static boolean hasVideoBeenViewed(Context paramContext, String paramString)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).getString("com.keyes.screebl.lastViewedVideoIds", null);
    if (paramContext == null) {}
    for (;;)
    {
      return false;
      paramContext = paramContext.split(";");
      if ((paramContext != null) && (paramContext.length != 0))
      {
        int i = 0;
        while (i < paramContext.length)
        {
          if ((paramContext[i] != null) && (paramContext[i].equals(paramString))) {
            return true;
          }
          i += 1;
        }
      }
    }
  }
  
  public static void markVideoAsViewed(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    if (paramString == null) {
      return;
    }
    Object localObject = localSharedPreferences.getString("com.keyes.screebl.lastViewedVideoIds", null);
    paramContext = (Context)localObject;
    if (localObject == null) {
      paramContext = "";
    }
    localObject = paramContext.split(";");
    paramContext = (Context)localObject;
    if (localObject == null) {
      paramContext = new String[0];
    }
    localObject = new HashMap();
    int i = 0;
    if (i >= paramContext.length)
    {
      paramContext = "";
      localObject = ((Map)localObject).keySet().iterator();
    }
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        paramContext = paramContext + paramString + ";";
        paramString = localSharedPreferences.edit();
        paramString.putString("com.keyes.screebl.lastViewedVideoIds", paramContext);
        paramString.commit();
        return;
        ((Map)localObject).put(paramContext[i], paramContext[i]);
        i += 1;
        break;
      }
      String str = (String)((Iterator)localObject).next();
      if (!str.trim().equals("")) {
        paramContext = paramContext + str + ";";
      }
    }
  }
  
  /* Error */
  public static String queryLatestPlaylistVideo(PlaylistId paramPlaylistId)
    throws IOException, ClientProtocolException, javax.xml.parsers.FactoryConfigurationError
  {
    // Byte code:
    //   0: new 19	org/apache/http/impl/client/DefaultHttpClient
    //   3: dup
    //   4: invokespecial 20	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   7: new 22	org/apache/http/client/methods/HttpGet
    //   10: dup
    //   11: new 24	java/lang/StringBuilder
    //   14: dup
    //   15: ldc -38
    //   17: invokespecial 29	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   20: aload_0
    //   21: invokevirtual 222	com/keyes/youtube/PlaylistId:getId	()Ljava/lang/String;
    //   24: invokevirtual 33	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: ldc -32
    //   29: invokevirtual 33	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: invokespecial 38	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   38: invokeinterface 44 2 0
    //   43: astore_0
    //   44: new 46	java/io/ByteArrayOutputStream
    //   47: dup
    //   48: invokespecial 47	java/io/ByteArrayOutputStream:<init>	()V
    //   51: astore_2
    //   52: aload_0
    //   53: invokeinterface 53 1 0
    //   58: aload_2
    //   59: invokeinterface 59 2 0
    //   64: new 226	org/json/JSONObject
    //   67: dup
    //   68: aload_2
    //   69: ldc 63
    //   71: invokevirtual 66	java/io/ByteArrayOutputStream:toString	(Ljava/lang/String;)Ljava/lang/String;
    //   74: invokespecial 227	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   77: astore_0
    //   78: aload_0
    //   79: ldc -27
    //   81: invokevirtual 233	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   84: ldc -21
    //   86: invokevirtual 239	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   89: astore_0
    //   90: aload_0
    //   91: aload_0
    //   92: invokevirtual 244	org/json/JSONArray:length	()I
    //   95: iconst_1
    //   96: isub
    //   97: invokevirtual 247	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   100: ldc -7
    //   102: invokevirtual 239	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   105: astore_0
    //   106: iconst_0
    //   107: istore_1
    //   108: iload_1
    //   109: aload_0
    //   110: invokevirtual 244	org/json/JSONArray:length	()I
    //   113: if_icmplt +5 -> 118
    //   116: aconst_null
    //   117: areturn
    //   118: aload_0
    //   119: iload_1
    //   120: invokevirtual 247	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   123: astore_2
    //   124: aload_2
    //   125: ldc -5
    //   127: aconst_null
    //   128: invokevirtual 254	org/json/JSONObject:optString	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   131: astore_3
    //   132: aload_3
    //   133: ifnull +33 -> 166
    //   136: aload_3
    //   137: ldc_w 256
    //   140: invokevirtual 164	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   143: ifeq +23 -> 166
    //   146: aload_2
    //   147: ldc_w 258
    //   150: aconst_null
    //   151: invokevirtual 254	org/json/JSONObject:optString	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   154: invokestatic 264	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   157: ldc_w 266
    //   160: invokevirtual 269	android/net/Uri:getQueryParameter	(Ljava/lang/String;)Ljava/lang/String;
    //   163: astore_0
    //   164: aload_0
    //   165: areturn
    //   166: iload_1
    //   167: iconst_1
    //   168: iadd
    //   169: istore_1
    //   170: goto -62 -> 108
    //   173: astore_0
    //   174: ldc 2
    //   176: invokevirtual 274	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   179: ldc_w 276
    //   182: aload_0
    //   183: invokestatic 282	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   186: pop
    //   187: aconst_null
    //   188: areturn
    //   189: astore_0
    //   190: ldc 2
    //   192: invokevirtual 274	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   195: ldc_w 276
    //   198: aload_0
    //   199: invokestatic 282	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   202: pop
    //   203: aconst_null
    //   204: areturn
    //   205: astore_0
    //   206: ldc 2
    //   208: invokevirtual 274	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   211: ldc_w 276
    //   214: aload_0
    //   215: invokestatic 282	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   218: pop
    //   219: aconst_null
    //   220: areturn
    //   221: astore_0
    //   222: goto -16 -> 206
    //   225: astore_0
    //   226: goto -36 -> 190
    //   229: astore_0
    //   230: goto -56 -> 174
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	233	0	paramPlaylistId	PlaylistId
    //   107	63	1	i	int
    //   51	96	2	localObject	Object
    //   131	6	3	str	String
    // Exception table:
    //   from	to	target	type
    //   52	78	173	java/lang/IllegalStateException
    //   52	78	189	java/io/IOException
    //   52	78	205	org/json/JSONException
    //   78	106	221	org/json/JSONException
    //   108	116	221	org/json/JSONException
    //   118	132	221	org/json/JSONException
    //   136	164	221	org/json/JSONException
    //   78	106	225	java/io/IOException
    //   108	116	225	java/io/IOException
    //   118	132	225	java/io/IOException
    //   136	164	225	java/io/IOException
    //   78	106	229	java/lang/IllegalStateException
    //   108	116	229	java/lang/IllegalStateException
    //   118	132	229	java/lang/IllegalStateException
    //   136	164	229	java/lang/IllegalStateException
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\keyes\youtube\YouTubeUtility.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */