package org.apache.cordova.globalization;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.text.format.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Globalization
  extends CordovaPlugin
{
  public static final String CURRENCY = "currency";
  public static final String CURRENCYCODE = "currencyCode";
  public static final String DATE = "date";
  public static final String DATESTRING = "dateString";
  public static final String DATETOSTRING = "dateToString";
  public static final String DAYS = "days";
  public static final String FORMATLENGTH = "formatLength";
  public static final String FULL = "full";
  public static final String GETCURRENCYPATTERN = "getCurrencyPattern";
  public static final String GETDATENAMES = "getDateNames";
  public static final String GETDATEPATTERN = "getDatePattern";
  public static final String GETFIRSTDAYOFWEEK = "getFirstDayOfWeek";
  public static final String GETLOCALENAME = "getLocaleName";
  public static final String GETNUMBERPATTERN = "getNumberPattern";
  public static final String GETPREFERREDLANGUAGE = "getPreferredLanguage";
  public static final String ISDAYLIGHTSAVINGSTIME = "isDayLightSavingsTime";
  public static final String ITEM = "item";
  public static final String LONG = "long";
  public static final String MEDIUM = "medium";
  public static final String MONTHS = "months";
  public static final String NARROW = "narrow";
  public static final String NUMBER = "number";
  public static final String NUMBERSTRING = "numberString";
  public static final String NUMBERTOSTRING = "numberToString";
  public static final String OPTIONS = "options";
  public static final String PERCENT = "percent";
  public static final String SELECTOR = "selector";
  public static final String STRINGTODATE = "stringToDate";
  public static final String STRINGTONUMBER = "stringToNumber";
  public static final String TIME = "time";
  public static final String TYPE = "type";
  public static final String WIDE = "wide";
  
  private JSONObject getCurrencyPattern(JSONArray paramJSONArray)
    throws GlobalizationError
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      Object localObject = paramJSONArray.getJSONObject(0).getString("currencyCode");
      paramJSONArray = (DecimalFormat)DecimalFormat.getCurrencyInstance(Locale.getDefault());
      localObject = Currency.getInstance((String)localObject);
      paramJSONArray.setCurrency((Currency)localObject);
      localJSONObject.put("pattern", paramJSONArray.toPattern());
      localJSONObject.put("code", ((Currency)localObject).getCurrencyCode());
      localJSONObject.put("fraction", paramJSONArray.getMinimumFractionDigits());
      localJSONObject.put("rounding", Integer.valueOf(0));
      localJSONObject.put("decimal", String.valueOf(paramJSONArray.getDecimalFormatSymbols().getDecimalSeparator()));
      localJSONObject.put("grouping", String.valueOf(paramJSONArray.getDecimalFormatSymbols().getGroupingSeparator()));
      return localJSONObject;
    }
    catch (Exception paramJSONArray)
    {
      throw new GlobalizationError("FORMATTING_ERROR");
    }
  }
  
  @TargetApi(9)
  private JSONObject getDateNames(final JSONArray paramJSONArray)
    throws GlobalizationError
  {
    JSONObject localJSONObject = new JSONObject();
    JSONArray localJSONArray = new JSONArray();
    ArrayList localArrayList = new ArrayList();
    int k = 0;
    int n = 0;
    int m = 0;
    int j = m;
    for (;;)
    {
      try
      {
        if (paramJSONArray.getJSONObject(0).length() > 0)
        {
          i = n;
          if (!((JSONObject)paramJSONArray.getJSONObject(0).get("options")).isNull("type"))
          {
            i = n;
            if (((String)((JSONObject)paramJSONArray.getJSONObject(0).get("options")).get("type")).equalsIgnoreCase("narrow")) {
              i = 0 + 1;
            }
          }
          j = m;
          k = i;
          if (!((JSONObject)paramJSONArray.getJSONObject(0).get("options")).isNull("item"))
          {
            j = m;
            k = i;
            if (((String)((JSONObject)paramJSONArray.getJSONObject(0).get("options")).get("item")).equalsIgnoreCase("days"))
            {
              j = 0 + 10;
              k = i;
            }
          }
        }
        i = j + k;
        if (i == 1)
        {
          paramJSONArray = Calendar.getInstance().getDisplayNames(2, 1, Locale.getDefault());
          Iterator localIterator = paramJSONArray.keySet().iterator();
          if (!localIterator.hasNext()) {
            break;
          }
          localArrayList.add((String)localIterator.next());
          continue;
        }
        if (i != 10) {
          break label280;
        }
      }
      catch (Exception paramJSONArray)
      {
        throw new GlobalizationError("UNKNOWN_ERROR");
      }
      paramJSONArray = Calendar.getInstance().getDisplayNames(7, 2, Locale.getDefault());
      continue;
      label280:
      if (i == 11) {
        paramJSONArray = Calendar.getInstance().getDisplayNames(7, 1, Locale.getDefault());
      } else {
        paramJSONArray = Calendar.getInstance().getDisplayNames(2, 2, Locale.getDefault());
      }
    }
    Collections.sort(localArrayList, new Comparator()
    {
      public int compare(String paramAnonymousString1, String paramAnonymousString2)
      {
        return ((Integer)paramJSONArray.get(paramAnonymousString1)).compareTo((Integer)paramJSONArray.get(paramAnonymousString2));
      }
    });
    int i = 0;
    while (i < localArrayList.size())
    {
      localJSONArray.put(localArrayList.get(i));
      i += 1;
    }
    paramJSONArray = localJSONObject.put("value", localJSONArray);
    return paramJSONArray;
  }
  
  /* Error */
  private JSONObject getDatePattern(JSONArray paramJSONArray)
    throws GlobalizationError
  {
    // Byte code:
    //   0: new 115	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 116	org/json/JSONObject:<init>	()V
    //   7: astore 4
    //   9: aload_0
    //   10: getfield 292	org/apache/cordova/globalization/Globalization:cordova	Lorg/apache/cordova/CordovaInterface;
    //   13: invokeinterface 298 1 0
    //   18: invokestatic 304	android/text/format/DateFormat:getDateFormat	(Landroid/content/Context;)Ljava/text/DateFormat;
    //   21: checkcast 306	java/text/SimpleDateFormat
    //   24: astore_3
    //   25: aload_0
    //   26: getfield 292	org/apache/cordova/globalization/Globalization:cordova	Lorg/apache/cordova/CordovaInterface;
    //   29: invokeinterface 298 1 0
    //   34: invokestatic 309	android/text/format/DateFormat:getTimeFormat	(Landroid/content/Context;)Ljava/text/DateFormat;
    //   37: checkcast 306	java/text/SimpleDateFormat
    //   40: astore 5
    //   42: new 311	java/lang/StringBuilder
    //   45: dup
    //   46: invokespecial 312	java/lang/StringBuilder:<init>	()V
    //   49: aload_3
    //   50: invokevirtual 315	java/text/SimpleDateFormat:toLocalizedPattern	()Ljava/lang/String;
    //   53: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: ldc_w 321
    //   59: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: aload 5
    //   64: invokevirtual 315	java/text/SimpleDateFormat:toLocalizedPattern	()Ljava/lang/String;
    //   67: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: invokevirtual 324	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   73: astore_2
    //   74: aload_1
    //   75: iconst_0
    //   76: invokevirtual 122	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   79: ldc 82
    //   81: invokevirtual 327	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   84: ifeq +128 -> 212
    //   87: aload_1
    //   88: iconst_0
    //   89: invokevirtual 122	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   92: ldc 82
    //   94: invokevirtual 330	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   97: astore 6
    //   99: aload_3
    //   100: astore_1
    //   101: aload 6
    //   103: ldc 28
    //   105: invokevirtual 226	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   108: ifne +36 -> 144
    //   111: aload 6
    //   113: ldc 28
    //   115: invokevirtual 126	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   118: astore_2
    //   119: aload_2
    //   120: ldc 64
    //   122: invokevirtual 229	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   125: ifeq +177 -> 302
    //   128: aload_0
    //   129: getfield 292	org/apache/cordova/globalization/Globalization:cordova	Lorg/apache/cordova/CordovaInterface;
    //   132: invokeinterface 298 1 0
    //   137: invokestatic 333	android/text/format/DateFormat:getMediumDateFormat	(Landroid/content/Context;)Ljava/text/DateFormat;
    //   140: checkcast 306	java/text/SimpleDateFormat
    //   143: astore_1
    //   144: new 311	java/lang/StringBuilder
    //   147: dup
    //   148: invokespecial 312	java/lang/StringBuilder:<init>	()V
    //   151: aload_1
    //   152: invokevirtual 315	java/text/SimpleDateFormat:toLocalizedPattern	()Ljava/lang/String;
    //   155: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: ldc_w 321
    //   161: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: aload 5
    //   166: invokevirtual 315	java/text/SimpleDateFormat:toLocalizedPattern	()Ljava/lang/String;
    //   169: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: invokevirtual 324	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   175: astore_3
    //   176: aload_3
    //   177: astore_2
    //   178: aload 6
    //   180: ldc 88
    //   182: invokevirtual 226	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   185: ifne +27 -> 212
    //   188: aload 6
    //   190: ldc 88
    //   192: invokevirtual 126	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   195: astore 6
    //   197: aload 6
    //   199: ldc 16
    //   201: invokevirtual 229	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   204: ifeq +137 -> 341
    //   207: aload_1
    //   208: invokevirtual 315	java/text/SimpleDateFormat:toLocalizedPattern	()Ljava/lang/String;
    //   211: astore_2
    //   212: invokestatic 338	android/text/format/Time:getCurrentTimezone	()Ljava/lang/String;
    //   215: invokestatic 344	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   218: astore_1
    //   219: aload 4
    //   221: ldc -106
    //   223: aload_2
    //   224: invokevirtual 158	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   227: pop
    //   228: aload 4
    //   230: ldc_w 346
    //   233: aload_1
    //   234: aload_1
    //   235: invokestatic 234	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   238: invokevirtual 350	java/util/Calendar:getTime	()Ljava/util/Date;
    //   241: invokevirtual 354	java/util/TimeZone:inDaylightTime	(Ljava/util/Date;)Z
    //   244: iconst_0
    //   245: invokevirtual 358	java/util/TimeZone:getDisplayName	(ZI)Ljava/lang/String;
    //   248: invokevirtual 158	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   251: pop
    //   252: aload 4
    //   254: ldc_w 360
    //   257: aload_1
    //   258: invokevirtual 363	java/util/TimeZone:getID	()Ljava/lang/String;
    //   261: invokevirtual 158	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   264: pop
    //   265: aload 4
    //   267: ldc_w 365
    //   270: aload_1
    //   271: invokevirtual 368	java/util/TimeZone:getRawOffset	()I
    //   274: sipush 1000
    //   277: idiv
    //   278: invokevirtual 172	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   281: pop
    //   282: aload 4
    //   284: ldc_w 370
    //   287: aload_1
    //   288: invokevirtual 373	java/util/TimeZone:getDSTSavings	()I
    //   291: sipush 1000
    //   294: idiv
    //   295: invokevirtual 172	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   298: pop
    //   299: aload 4
    //   301: areturn
    //   302: aload_2
    //   303: ldc 61
    //   305: invokevirtual 229	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   308: ifne +14 -> 322
    //   311: aload_3
    //   312: astore_1
    //   313: aload_2
    //   314: ldc 31
    //   316: invokevirtual 229	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   319: ifeq -175 -> 144
    //   322: aload_0
    //   323: getfield 292	org/apache/cordova/globalization/Globalization:cordova	Lorg/apache/cordova/CordovaInterface;
    //   326: invokeinterface 298 1 0
    //   331: invokestatic 376	android/text/format/DateFormat:getLongDateFormat	(Landroid/content/Context;)Ljava/text/DateFormat;
    //   334: checkcast 306	java/text/SimpleDateFormat
    //   337: astore_1
    //   338: goto -194 -> 144
    //   341: aload_3
    //   342: astore_2
    //   343: aload 6
    //   345: ldc 97
    //   347: invokevirtual 229	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   350: ifeq -138 -> 212
    //   353: aload 5
    //   355: invokevirtual 315	java/text/SimpleDateFormat:toLocalizedPattern	()Ljava/lang/String;
    //   358: astore_2
    //   359: goto -147 -> 212
    //   362: astore_1
    //   363: new 111	org/apache/cordova/globalization/GlobalizationError
    //   366: dup
    //   367: ldc_w 378
    //   370: invokespecial 207	org/apache/cordova/globalization/GlobalizationError:<init>	(Ljava/lang/String;)V
    //   373: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	374	0	this	Globalization
    //   0	374	1	paramJSONArray	JSONArray
    //   73	286	2	localObject1	Object
    //   24	318	3	localObject2	Object
    //   7	293	4	localJSONObject	JSONObject
    //   40	314	5	localSimpleDateFormat	SimpleDateFormat
    //   97	247	6	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   9	74	362	java/lang/Exception
    //   74	99	362	java/lang/Exception
    //   101	144	362	java/lang/Exception
    //   144	176	362	java/lang/Exception
    //   178	212	362	java/lang/Exception
    //   212	299	362	java/lang/Exception
    //   302	311	362	java/lang/Exception
    //   313	322	362	java/lang/Exception
    //   322	338	362	java/lang/Exception
    //   343	359	362	java/lang/Exception
  }
  
  private JSONObject getDateToString(JSONArray paramJSONArray)
    throws GlobalizationError
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      Date localDate = new Date(((Long)paramJSONArray.getJSONObject(0).get("date")).longValue());
      paramJSONArray = localJSONObject.put("value", new SimpleDateFormat(getDatePattern(paramJSONArray).getString("pattern")).format(localDate));
      return paramJSONArray;
    }
    catch (Exception paramJSONArray)
    {
      throw new GlobalizationError("FORMATTING_ERROR");
    }
  }
  
  private JSONObject getFirstDayOfWeek(JSONArray paramJSONArray)
    throws GlobalizationError
  {
    paramJSONArray = new JSONObject();
    try
    {
      paramJSONArray = paramJSONArray.put("value", Calendar.getInstance(Locale.getDefault()).getFirstDayOfWeek());
      return paramJSONArray;
    }
    catch (Exception paramJSONArray)
    {
      throw new GlobalizationError("UNKNOWN_ERROR");
    }
  }
  
  private JSONObject getIsDayLightSavingsTime(JSONArray paramJSONArray)
    throws GlobalizationError
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      paramJSONArray = new Date(((Long)paramJSONArray.getJSONObject(0).get("date")).longValue());
      paramJSONArray = localJSONObject.put("dst", TimeZone.getTimeZone(Time.getCurrentTimezone()).inDaylightTime(paramJSONArray));
      return paramJSONArray;
    }
    catch (Exception paramJSONArray)
    {
      throw new GlobalizationError("UNKNOWN_ERROR");
    }
  }
  
  private JSONObject getLocaleName()
    throws GlobalizationError
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("value", toBcp47Language(Locale.getDefault()));
      return localJSONObject;
    }
    catch (Exception localException)
    {
      throw new GlobalizationError("UNKNOWN_ERROR");
    }
  }
  
  private DecimalFormat getNumberFormatInstance(JSONArray paramJSONArray)
    throws JSONException
  {
    DecimalFormat localDecimalFormat = (DecimalFormat)DecimalFormat.getInstance(Locale.getDefault());
    try
    {
      if ((paramJSONArray.getJSONObject(0).length() > 1) && (!((JSONObject)paramJSONArray.getJSONObject(0).get("options")).isNull("type")))
      {
        paramJSONArray = (String)((JSONObject)paramJSONArray.getJSONObject(0).get("options")).get("type");
        if (paramJSONArray.equalsIgnoreCase("currency")) {
          return (DecimalFormat)DecimalFormat.getCurrencyInstance(Locale.getDefault());
        }
        if (paramJSONArray.equalsIgnoreCase("percent"))
        {
          paramJSONArray = (DecimalFormat)DecimalFormat.getPercentInstance(Locale.getDefault());
          return paramJSONArray;
        }
      }
    }
    catch (JSONException paramJSONArray) {}
    return localDecimalFormat;
  }
  
  /* Error */
  private JSONObject getNumberPattern(JSONArray paramJSONArray)
    throws GlobalizationError
  {
    // Byte code:
    //   0: new 115	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 116	org/json/JSONObject:<init>	()V
    //   7: astore 7
    //   9: invokestatic 132	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   12: invokestatic 419	java/text/DecimalFormat:getInstance	(Ljava/util/Locale;)Ljava/text/NumberFormat;
    //   15: checkcast 134	java/text/DecimalFormat
    //   18: astore 5
    //   20: aload 5
    //   22: invokevirtual 186	java/text/DecimalFormat:getDecimalFormatSymbols	()Ljava/text/DecimalFormatSymbols;
    //   25: invokevirtual 192	java/text/DecimalFormatSymbols:getDecimalSeparator	()C
    //   28: invokestatic 197	java/lang/String:valueOf	(C)Ljava/lang/String;
    //   31: astore 6
    //   33: aload 5
    //   35: astore_3
    //   36: aload 6
    //   38: astore 4
    //   40: aload_1
    //   41: iconst_0
    //   42: invokevirtual 122	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   45: invokevirtual 218	org/json/JSONObject:length	()I
    //   48: ifle +81 -> 129
    //   51: aload 5
    //   53: astore_3
    //   54: aload 6
    //   56: astore 4
    //   58: aload_1
    //   59: iconst_0
    //   60: invokevirtual 122	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   63: ldc 82
    //   65: invokevirtual 222	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   68: checkcast 115	org/json/JSONObject
    //   71: ldc 100
    //   73: invokevirtual 226	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   76: ifne +53 -> 129
    //   79: aload_1
    //   80: iconst_0
    //   81: invokevirtual 122	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   84: ldc 82
    //   86: invokevirtual 222	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   89: checkcast 115	org/json/JSONObject
    //   92: ldc 100
    //   94: invokevirtual 222	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   97: checkcast 194	java/lang/String
    //   100: astore_1
    //   101: aload_1
    //   102: ldc 10
    //   104: invokevirtual 229	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   107: ifeq +134 -> 241
    //   110: invokestatic 132	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   113: invokestatic 138	java/text/DecimalFormat:getCurrencyInstance	(Ljava/util/Locale;)Ljava/text/NumberFormat;
    //   116: checkcast 134	java/text/DecimalFormat
    //   119: astore_3
    //   120: aload_3
    //   121: invokevirtual 186	java/text/DecimalFormat:getDecimalFormatSymbols	()Ljava/text/DecimalFormatSymbols;
    //   124: invokevirtual 425	java/text/DecimalFormatSymbols:getCurrencySymbol	()Ljava/lang/String;
    //   127: astore 4
    //   129: aload 7
    //   131: ldc -106
    //   133: aload_3
    //   134: invokevirtual 154	java/text/DecimalFormat:toPattern	()Ljava/lang/String;
    //   137: invokevirtual 158	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   140: pop
    //   141: aload 7
    //   143: ldc_w 427
    //   146: aload 4
    //   148: invokevirtual 158	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   151: pop
    //   152: aload 7
    //   154: ldc -91
    //   156: aload_3
    //   157: invokevirtual 169	java/text/DecimalFormat:getMinimumFractionDigits	()I
    //   160: invokevirtual 172	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   163: pop
    //   164: aload 7
    //   166: ldc -82
    //   168: iconst_0
    //   169: invokestatic 180	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   172: invokevirtual 158	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   175: pop
    //   176: aload 7
    //   178: ldc_w 429
    //   181: aload_3
    //   182: invokevirtual 432	java/text/DecimalFormat:getPositivePrefix	()Ljava/lang/String;
    //   185: invokevirtual 158	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   188: pop
    //   189: aload 7
    //   191: ldc_w 434
    //   194: aload_3
    //   195: invokevirtual 437	java/text/DecimalFormat:getNegativePrefix	()Ljava/lang/String;
    //   198: invokevirtual 158	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   201: pop
    //   202: aload 7
    //   204: ldc -74
    //   206: aload_3
    //   207: invokevirtual 186	java/text/DecimalFormat:getDecimalFormatSymbols	()Ljava/text/DecimalFormatSymbols;
    //   210: invokevirtual 192	java/text/DecimalFormatSymbols:getDecimalSeparator	()C
    //   213: invokestatic 197	java/lang/String:valueOf	(C)Ljava/lang/String;
    //   216: invokevirtual 158	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   219: pop
    //   220: aload 7
    //   222: ldc -57
    //   224: aload_3
    //   225: invokevirtual 186	java/text/DecimalFormat:getDecimalFormatSymbols	()Ljava/text/DecimalFormatSymbols;
    //   228: invokevirtual 202	java/text/DecimalFormatSymbols:getGroupingSeparator	()C
    //   231: invokestatic 197	java/lang/String:valueOf	(C)Ljava/lang/String;
    //   234: invokevirtual 158	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   237: pop
    //   238: aload 7
    //   240: areturn
    //   241: aload 5
    //   243: astore_3
    //   244: aload 6
    //   246: astore 4
    //   248: aload_1
    //   249: ldc 85
    //   251: invokevirtual 229	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   254: ifeq -125 -> 129
    //   257: invokestatic 132	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   260: invokestatic 422	java/text/DecimalFormat:getPercentInstance	(Ljava/util/Locale;)Ljava/text/NumberFormat;
    //   263: checkcast 134	java/text/DecimalFormat
    //   266: astore_3
    //   267: aload_3
    //   268: invokevirtual 186	java/text/DecimalFormat:getDecimalFormatSymbols	()Ljava/text/DecimalFormatSymbols;
    //   271: invokevirtual 440	java/text/DecimalFormatSymbols:getPercent	()C
    //   274: istore_2
    //   275: iload_2
    //   276: invokestatic 197	java/lang/String:valueOf	(C)Ljava/lang/String;
    //   279: astore 4
    //   281: goto -152 -> 129
    //   284: astore_1
    //   285: new 111	org/apache/cordova/globalization/GlobalizationError
    //   288: dup
    //   289: ldc_w 378
    //   292: invokespecial 207	org/apache/cordova/globalization/GlobalizationError:<init>	(Ljava/lang/String;)V
    //   295: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	296	0	this	Globalization
    //   0	296	1	paramJSONArray	JSONArray
    //   274	2	2	c	char
    //   35	233	3	localDecimalFormat1	DecimalFormat
    //   38	242	4	str1	String
    //   18	224	5	localDecimalFormat2	DecimalFormat
    //   31	214	6	str2	String
    //   7	232	7	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   9	33	284	java/lang/Exception
    //   40	51	284	java/lang/Exception
    //   58	129	284	java/lang/Exception
    //   129	238	284	java/lang/Exception
    //   248	275	284	java/lang/Exception
  }
  
  private JSONObject getNumberToString(JSONArray paramJSONArray)
    throws GlobalizationError
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      paramJSONArray = localJSONObject.put("value", getNumberFormatInstance(paramJSONArray).format(paramJSONArray.getJSONObject(0).get("number")));
      return paramJSONArray;
    }
    catch (Exception paramJSONArray)
    {
      throw new GlobalizationError("FORMATTING_ERROR");
    }
  }
  
  private JSONObject getPreferredLanguage()
    throws GlobalizationError
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("value", toBcp47Language(Locale.getDefault()));
      return localJSONObject;
    }
    catch (Exception localException)
    {
      throw new GlobalizationError("UNKNOWN_ERROR");
    }
  }
  
  private JSONObject getStringToNumber(JSONArray paramJSONArray)
    throws GlobalizationError
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      paramJSONArray = localJSONObject.put("value", getNumberFormatInstance(paramJSONArray).parse((String)paramJSONArray.getJSONObject(0).get("numberString")));
      return paramJSONArray;
    }
    catch (Exception paramJSONArray)
    {
      throw new GlobalizationError("PARSING_ERROR");
    }
  }
  
  private JSONObject getStringtoDate(JSONArray paramJSONArray)
    throws GlobalizationError
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      paramJSONArray = new SimpleDateFormat(getDatePattern(paramJSONArray).getString("pattern")).parse(paramJSONArray.getJSONObject(0).get("dateString").toString());
      Time localTime = new Time();
      localTime.set(paramJSONArray.getTime());
      localJSONObject.put("year", localTime.year);
      localJSONObject.put("month", localTime.month);
      localJSONObject.put("day", localTime.monthDay);
      localJSONObject.put("hour", localTime.hour);
      localJSONObject.put("minute", localTime.minute);
      localJSONObject.put("second", localTime.second);
      localJSONObject.put("millisecond", Long.valueOf(0L));
      return localJSONObject;
    }
    catch (Exception paramJSONArray)
    {
      throw new GlobalizationError("PARSING_ERROR");
    }
  }
  
  private String toBcp47Language(Locale paramLocale)
  {
    String str1 = paramLocale.getLanguage();
    String str2 = paramLocale.getCountry();
    paramLocale = paramLocale.getVariant();
    Object localObject3 = str1;
    Object localObject2 = str2;
    Object localObject1 = paramLocale;
    if (str1.equals("no"))
    {
      localObject3 = str1;
      localObject2 = str2;
      localObject1 = paramLocale;
      if (str2.equals("NO"))
      {
        localObject3 = str1;
        localObject2 = str2;
        localObject1 = paramLocale;
        if (paramLocale.equals("NY"))
        {
          localObject3 = "nn";
          localObject2 = "NO";
          localObject1 = "";
        }
      }
    }
    if ((((String)localObject3).isEmpty()) || (!((String)localObject3).matches("\\p{Alpha}{2,8}"))) {
      paramLocale = "und";
    }
    for (;;)
    {
      localObject3 = localObject2;
      if (!((String)localObject2).matches("\\p{Alpha}{2}|\\p{Digit}{3}")) {
        localObject3 = "";
      }
      localObject2 = localObject1;
      if (!((String)localObject1).matches("\\p{Alnum}{5,8}|\\p{Digit}\\p{Alnum}{3}")) {
        localObject2 = "";
      }
      paramLocale = new StringBuilder(paramLocale);
      if (!((String)localObject3).isEmpty()) {
        paramLocale.append('-').append((String)localObject3);
      }
      if (!((String)localObject2).isEmpty()) {
        paramLocale.append('-').append((String)localObject2);
      }
      return paramLocale.toString();
      if (((String)localObject3).equals("iw"))
      {
        paramLocale = "he";
      }
      else if (((String)localObject3).equals("in"))
      {
        paramLocale = "id";
      }
      else
      {
        paramLocale = (Locale)localObject3;
        if (((String)localObject3).equals("ji")) {
          paramLocale = "yi";
        }
      }
    }
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    new JSONObject();
    try
    {
      if (paramString.equals("getLocaleName")) {
        paramString = getLocaleName();
      }
      for (;;)
      {
        paramCallbackContext.success(paramString);
        break label285;
        if (paramString.equals("getPreferredLanguage"))
        {
          paramString = getPreferredLanguage();
        }
        else if (paramString.equalsIgnoreCase("dateToString"))
        {
          paramString = getDateToString(paramJSONArray);
        }
        else if (paramString.equalsIgnoreCase("stringToDate"))
        {
          paramString = getStringtoDate(paramJSONArray);
        }
        else
        {
          if (!paramString.equalsIgnoreCase("getDatePattern")) {
            break;
          }
          paramString = getDatePattern(paramJSONArray);
        }
      }
      if (paramString.equalsIgnoreCase("getDateNames")) {
        if (Build.VERSION.SDK_INT < 9) {
          throw new GlobalizationError("UNKNOWN_ERROR");
        }
      }
    }
    catch (GlobalizationError paramString)
    {
      for (;;)
      {
        paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, paramString.toJson()));
        break label285;
        paramString = getDateNames(paramJSONArray);
        continue;
        if (paramString.equalsIgnoreCase("isDayLightSavingsTime"))
        {
          paramString = getIsDayLightSavingsTime(paramJSONArray);
        }
        else if (paramString.equalsIgnoreCase("getFirstDayOfWeek"))
        {
          paramString = getFirstDayOfWeek(paramJSONArray);
        }
        else if (paramString.equalsIgnoreCase("numberToString"))
        {
          paramString = getNumberToString(paramJSONArray);
        }
        else if (paramString.equalsIgnoreCase("stringToNumber"))
        {
          paramString = getStringToNumber(paramJSONArray);
        }
        else if (paramString.equalsIgnoreCase("getNumberPattern"))
        {
          paramString = getNumberPattern(paramJSONArray);
        }
        else
        {
          if (!paramString.equalsIgnoreCase("getCurrencyPattern")) {
            break;
          }
          paramString = getCurrencyPattern(paramJSONArray);
        }
      }
      return false;
    }
    catch (Exception paramString)
    {
      paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
    }
    label285:
    return true;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\globalization\Globalization.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */