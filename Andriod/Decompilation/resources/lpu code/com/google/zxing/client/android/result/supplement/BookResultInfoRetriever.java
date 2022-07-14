package com.google.zxing.client.android.result.supplement;

import android.content.Context;
import android.widget.TextView;
import barcodescanner.xservices.nl.barcodescanner.R.string;
import com.google.zxing.client.android.history.HistoryManager;

final class BookResultInfoRetriever
  extends SupplementalInfoRetriever
{
  private final Context context;
  private final String isbn;
  private final String source;
  
  BookResultInfoRetriever(TextView paramTextView, String paramString, HistoryManager paramHistoryManager, Context paramContext)
  {
    super(paramTextView, paramHistoryManager);
    this.isbn = paramString;
    this.source = paramContext.getString(R.string.msg_google_books);
    this.context = paramContext;
  }
  
  /* Error */
  void retrieveSupplementalInfo()
    throws java.io.IOException
  {
    // Byte code:
    //   0: new 41	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   7: ldc 45
    //   9: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   12: aload_0
    //   13: getfield 16	com/google/zxing/client/android/result/supplement/BookResultInfoRetriever:isbn	Ljava/lang/String;
    //   16: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   22: getstatic 59	com/google/zxing/client/android/HttpHelper$ContentType:JSON	Lcom/google/zxing/client/android/HttpHelper$ContentType;
    //   25: invokestatic 65	com/google/zxing/client/android/HttpHelper:downloadViaHttp	(Ljava/lang/String;Lcom/google/zxing/client/android/HttpHelper$ContentType;)Ljava/lang/CharSequence;
    //   28: astore_2
    //   29: aload_2
    //   30: invokeinterface 71 1 0
    //   35: ifne +4 -> 39
    //   38: return
    //   39: aconst_null
    //   40: astore_3
    //   41: new 73	org/json/JSONTokener
    //   44: dup
    //   45: aload_2
    //   46: invokeinterface 74 1 0
    //   51: invokespecial 77	org/json/JSONTokener:<init>	(Ljava/lang/String;)V
    //   54: invokevirtual 81	org/json/JSONTokener:nextValue	()Ljava/lang/Object;
    //   57: checkcast 83	org/json/JSONObject
    //   60: ldc 85
    //   62: invokevirtual 89	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   65: astore_2
    //   66: aload_2
    //   67: ifnull -29 -> 38
    //   70: aload_2
    //   71: iconst_0
    //   72: invokevirtual 95	org/json/JSONArray:isNull	(I)Z
    //   75: ifne -37 -> 38
    //   78: aload_2
    //   79: iconst_0
    //   80: invokevirtual 99	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   83: checkcast 83	org/json/JSONObject
    //   86: ldc 101
    //   88: invokevirtual 105	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   91: astore_2
    //   92: aload_2
    //   93: ifnull -55 -> 38
    //   96: aload_2
    //   97: ldc 107
    //   99: invokevirtual 111	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   102: astore 4
    //   104: aload_2
    //   105: ldc 113
    //   107: invokevirtual 111	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   110: astore 5
    //   112: aload_2
    //   113: ldc 115
    //   115: invokevirtual 89	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   118: astore 6
    //   120: aload_3
    //   121: astore_2
    //   122: aload 6
    //   124: ifnull +68 -> 192
    //   127: aload_3
    //   128: astore_2
    //   129: aload 6
    //   131: iconst_0
    //   132: invokevirtual 95	org/json/JSONArray:isNull	(I)Z
    //   135: ifne +57 -> 192
    //   138: new 117	java/util/ArrayList
    //   141: dup
    //   142: aload 6
    //   144: invokevirtual 118	org/json/JSONArray:length	()I
    //   147: invokespecial 121	java/util/ArrayList:<init>	(I)V
    //   150: astore_2
    //   151: iconst_0
    //   152: istore_1
    //   153: iload_1
    //   154: aload 6
    //   156: invokevirtual 118	org/json/JSONArray:length	()I
    //   159: if_icmpge +33 -> 192
    //   162: aload_2
    //   163: aload 6
    //   165: iload_1
    //   166: invokevirtual 122	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   169: invokeinterface 128 2 0
    //   174: pop
    //   175: iload_1
    //   176: iconst_1
    //   177: iadd
    //   178: istore_1
    //   179: goto -26 -> 153
    //   182: astore_2
    //   183: new 37	java/io/IOException
    //   186: dup
    //   187: aload_2
    //   188: invokespecial 131	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   191: athrow
    //   192: new 117	java/util/ArrayList
    //   195: dup
    //   196: invokespecial 132	java/util/ArrayList:<init>	()V
    //   199: astore_3
    //   200: aload 4
    //   202: aload_3
    //   203: invokestatic 136	com/google/zxing/client/android/result/supplement/BookResultInfoRetriever:maybeAddText	(Ljava/lang/String;Ljava/util/Collection;)V
    //   206: aload_2
    //   207: aload_3
    //   208: invokestatic 140	com/google/zxing/client/android/result/supplement/BookResultInfoRetriever:maybeAddTextSeries	(Ljava/util/Collection;Ljava/util/Collection;)V
    //   211: aload 5
    //   213: ifnull +11 -> 224
    //   216: aload 5
    //   218: invokevirtual 146	java/lang/String:isEmpty	()Z
    //   221: ifeq +93 -> 314
    //   224: aconst_null
    //   225: astore_2
    //   226: aload_2
    //   227: aload_3
    //   228: invokestatic 136	com/google/zxing/client/android/result/supplement/BookResultInfoRetriever:maybeAddText	(Ljava/lang/String;Ljava/util/Collection;)V
    //   231: new 41	java/lang/StringBuilder
    //   234: dup
    //   235: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   238: ldc -108
    //   240: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: aload_0
    //   244: getfield 32	com/google/zxing/client/android/result/supplement/BookResultInfoRetriever:context	Landroid/content/Context;
    //   247: invokestatic 154	com/google/zxing/client/android/LocaleManager:getBookSearchCountryTLD	(Landroid/content/Context;)Ljava/lang/String;
    //   250: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   253: ldc -100
    //   255: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   261: astore_2
    //   262: aload_0
    //   263: aload_0
    //   264: getfield 16	com/google/zxing/client/android/result/supplement/BookResultInfoRetriever:isbn	Ljava/lang/String;
    //   267: aload_0
    //   268: getfield 30	com/google/zxing/client/android/result/supplement/BookResultInfoRetriever:source	Ljava/lang/String;
    //   271: aload_3
    //   272: aload_3
    //   273: invokeinterface 159 1 0
    //   278: anewarray 142	java/lang/String
    //   281: invokeinterface 163 2 0
    //   286: checkcast 165	[Ljava/lang/String;
    //   289: new 41	java/lang/StringBuilder
    //   292: dup
    //   293: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   296: aload_2
    //   297: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   300: aload_0
    //   301: getfield 16	com/google/zxing/client/android/result/supplement/BookResultInfoRetriever:isbn	Ljava/lang/String;
    //   304: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   307: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   310: invokevirtual 168	com/google/zxing/client/android/result/supplement/BookResultInfoRetriever:append	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)V
    //   313: return
    //   314: new 41	java/lang/StringBuilder
    //   317: dup
    //   318: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   321: aload 5
    //   323: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   326: ldc -86
    //   328: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   331: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   334: astore_2
    //   335: goto -109 -> 226
    //   338: astore_2
    //   339: goto -156 -> 183
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	342	0	this	BookResultInfoRetriever
    //   152	27	1	i	int
    //   28	135	2	localObject	Object
    //   182	25	2	localJSONException1	org.json.JSONException
    //   225	110	2	str1	String
    //   338	1	2	localJSONException2	org.json.JSONException
    //   40	233	3	localArrayList	java.util.ArrayList
    //   102	99	4	str2	String
    //   110	212	5	str3	String
    //   118	46	6	localJSONArray	org.json.JSONArray
    // Exception table:
    //   from	to	target	type
    //   41	66	182	org/json/JSONException
    //   70	92	182	org/json/JSONException
    //   96	120	182	org/json/JSONException
    //   129	151	182	org/json/JSONException
    //   153	175	338	org/json/JSONException
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\supplement\BookResultInfoRetriever.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */