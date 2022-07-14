package com.google.zxing.client.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class LocaleManager
{
  private static final String DEFAULT_COUNTRY = "US";
  private static final String DEFAULT_LANGUAGE = "en";
  private static final String DEFAULT_TLD = "com";
  private static final Map<String, String> GOOGLE_BOOK_SEARCH_COUNTRY_TLD = GOOGLE_COUNTRY_TLD;
  private static final Map<String, String> GOOGLE_COUNTRY_TLD = new HashMap();
  private static final Map<String, String> GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD;
  private static final Collection<String> TRANSLATED_HELP_ASSET_LANGUAGES = Arrays.asList(new String[] { "de", "en", "es", "fr", "it", "ja", "ko", "nl", "pt", "ru", "uk", "zh-rCN", "zh-rTW", "zh-rHK" });
  
  static
  {
    GOOGLE_COUNTRY_TLD.put("AR", "com.ar");
    GOOGLE_COUNTRY_TLD.put("AU", "com.au");
    GOOGLE_COUNTRY_TLD.put("BR", "com.br");
    GOOGLE_COUNTRY_TLD.put("BG", "bg");
    GOOGLE_COUNTRY_TLD.put(Locale.CANADA.getCountry(), "ca");
    GOOGLE_COUNTRY_TLD.put(Locale.CHINA.getCountry(), "cn");
    GOOGLE_COUNTRY_TLD.put("CZ", "cz");
    GOOGLE_COUNTRY_TLD.put("DK", "dk");
    GOOGLE_COUNTRY_TLD.put("FI", "fi");
    GOOGLE_COUNTRY_TLD.put(Locale.FRANCE.getCountry(), "fr");
    GOOGLE_COUNTRY_TLD.put(Locale.GERMANY.getCountry(), "de");
    GOOGLE_COUNTRY_TLD.put("GR", "gr");
    GOOGLE_COUNTRY_TLD.put("HU", "hu");
    GOOGLE_COUNTRY_TLD.put("ID", "co.id");
    GOOGLE_COUNTRY_TLD.put("IL", "co.il");
    GOOGLE_COUNTRY_TLD.put(Locale.ITALY.getCountry(), "it");
    GOOGLE_COUNTRY_TLD.put(Locale.JAPAN.getCountry(), "co.jp");
    GOOGLE_COUNTRY_TLD.put(Locale.KOREA.getCountry(), "co.kr");
    GOOGLE_COUNTRY_TLD.put("NL", "nl");
    GOOGLE_COUNTRY_TLD.put("PL", "pl");
    GOOGLE_COUNTRY_TLD.put("PT", "pt");
    GOOGLE_COUNTRY_TLD.put("RO", "ro");
    GOOGLE_COUNTRY_TLD.put("RU", "ru");
    GOOGLE_COUNTRY_TLD.put("SK", "sk");
    GOOGLE_COUNTRY_TLD.put("SI", "si");
    GOOGLE_COUNTRY_TLD.put("ES", "es");
    GOOGLE_COUNTRY_TLD.put("SE", "se");
    GOOGLE_COUNTRY_TLD.put("CH", "ch");
    GOOGLE_COUNTRY_TLD.put(Locale.TAIWAN.getCountry(), "tw");
    GOOGLE_COUNTRY_TLD.put("TR", "com.tr");
    GOOGLE_COUNTRY_TLD.put("UA", "com.ua");
    GOOGLE_COUNTRY_TLD.put(Locale.UK.getCountry(), "co.uk");
    GOOGLE_COUNTRY_TLD.put(Locale.US.getCountry(), "com");
    GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD = new HashMap();
    GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD.put("AU", "com.au");
    GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD.put(Locale.FRANCE.getCountry(), "fr");
    GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD.put(Locale.GERMANY.getCountry(), "de");
    GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD.put(Locale.ITALY.getCountry(), "it");
    GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD.put(Locale.JAPAN.getCountry(), "co.jp");
    GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD.put("NL", "nl");
    GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD.put("ES", "es");
    GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD.put("CH", "ch");
    GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD.put(Locale.UK.getCountry(), "co.uk");
    GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD.put(Locale.US.getCountry(), "com");
  }
  
  private static String doGetTLD(Map<String, String> paramMap, Context paramContext)
  {
    paramContext = (String)paramMap.get(getCountry(paramContext));
    paramMap = paramContext;
    if (paramContext == null) {
      paramMap = "com";
    }
    return paramMap;
  }
  
  public static String getBookSearchCountryTLD(Context paramContext)
  {
    return doGetTLD(GOOGLE_BOOK_SEARCH_COUNTRY_TLD, paramContext);
  }
  
  private static String getCountry(Context paramContext)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).getString("preferences_search_country", "-");
    if ((paramContext != null) && (!paramContext.isEmpty()) && (!"-".equals(paramContext))) {
      return paramContext;
    }
    return getSystemCountry();
  }
  
  public static String getCountryTLD(Context paramContext)
  {
    return doGetTLD(GOOGLE_COUNTRY_TLD, paramContext);
  }
  
  public static String getProductSearchCountryTLD(Context paramContext)
  {
    return doGetTLD(GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD, paramContext);
  }
  
  private static String getSystemCountry()
  {
    Locale localLocale = Locale.getDefault();
    if (localLocale == null) {
      return "US";
    }
    return localLocale.getCountry();
  }
  
  private static String getSystemLanguage()
  {
    Object localObject = Locale.getDefault();
    if (localObject == null) {
      localObject = "en";
    }
    String str;
    do
    {
      return (String)localObject;
      str = ((Locale)localObject).getLanguage();
      localObject = str;
    } while (!Locale.SIMPLIFIED_CHINESE.getLanguage().equals(str));
    return str + "-r" + getSystemCountry();
  }
  
  static String getTranslatedAssetLanguage()
  {
    String str = getSystemLanguage();
    if (TRANSLATED_HELP_ASSET_LANGUAGES.contains(str)) {
      return str;
    }
    return "en";
  }
  
  public static boolean isBookSearchUrl(String paramString)
  {
    return (paramString.startsWith("http://google.com/books")) || (paramString.startsWith("http://books.google."));
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\LocaleManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */