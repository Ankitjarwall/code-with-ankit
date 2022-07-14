package com.google.zxing.client.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.google.zxing.DecodeHintType;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

final class DecodeHintManager
{
  private static final Pattern COMMA = Pattern.compile(",");
  private static final String TAG = DecodeHintManager.class.getSimpleName();
  
  static Map<DecodeHintType, Object> parseDecodeHints(Intent paramIntent)
  {
    paramIntent = paramIntent.getExtras();
    if ((paramIntent == null) || (paramIntent.isEmpty())) {
      return null;
    }
    EnumMap localEnumMap = new EnumMap(DecodeHintType.class);
    DecodeHintType[] arrayOfDecodeHintType = DecodeHintType.values();
    int j = arrayOfDecodeHintType.length;
    int i = 0;
    if (i < j)
    {
      DecodeHintType localDecodeHintType = arrayOfDecodeHintType[i];
      if ((localDecodeHintType == DecodeHintType.CHARACTER_SET) || (localDecodeHintType == DecodeHintType.NEED_RESULT_POINT_CALLBACK) || (localDecodeHintType == DecodeHintType.POSSIBLE_FORMATS)) {}
      for (;;)
      {
        i += 1;
        break;
        Object localObject = localDecodeHintType.name();
        if (paramIntent.containsKey((String)localObject)) {
          if (localDecodeHintType.getValueType().equals(Void.class))
          {
            localEnumMap.put(localDecodeHintType, Boolean.TRUE);
          }
          else
          {
            localObject = paramIntent.get((String)localObject);
            if (localDecodeHintType.getValueType().isInstance(localObject)) {
              localEnumMap.put(localDecodeHintType, localObject);
            } else {
              Log.w(TAG, "Ignoring hint " + localDecodeHintType + " because it is not assignable from " + localObject);
            }
          }
        }
      }
    }
    Log.i(TAG, "Hints from the Intent: " + localEnumMap);
    return localEnumMap;
  }
  
  static Map<DecodeHintType, ?> parseDecodeHints(Uri paramUri)
  {
    paramUri = paramUri.getEncodedQuery();
    if ((paramUri == null) || (paramUri.isEmpty())) {
      return null;
    }
    Map localMap = splitQuery(paramUri);
    EnumMap localEnumMap = new EnumMap(DecodeHintType.class);
    DecodeHintType[] arrayOfDecodeHintType = DecodeHintType.values();
    int k = arrayOfDecodeHintType.length;
    int i = 0;
    if (i < k)
    {
      DecodeHintType localDecodeHintType = arrayOfDecodeHintType[i];
      if ((localDecodeHintType == DecodeHintType.CHARACTER_SET) || (localDecodeHintType == DecodeHintType.NEED_RESULT_POINT_CALLBACK) || (localDecodeHintType == DecodeHintType.POSSIBLE_FORMATS)) {}
      for (;;)
      {
        i += 1;
        break;
        Object localObject = (String)localMap.get(localDecodeHintType.name());
        if (localObject != null) {
          if (localDecodeHintType.getValueType().equals(Object.class))
          {
            localEnumMap.put(localDecodeHintType, localObject);
          }
          else if (localDecodeHintType.getValueType().equals(Void.class))
          {
            localEnumMap.put(localDecodeHintType, Boolean.TRUE);
          }
          else if (localDecodeHintType.getValueType().equals(String.class))
          {
            localEnumMap.put(localDecodeHintType, localObject);
          }
          else if (localDecodeHintType.getValueType().equals(Boolean.class))
          {
            if (((String)localObject).isEmpty()) {
              localEnumMap.put(localDecodeHintType, Boolean.TRUE);
            } else if (("0".equals(localObject)) || ("false".equalsIgnoreCase((String)localObject)) || ("no".equalsIgnoreCase((String)localObject))) {
              localEnumMap.put(localDecodeHintType, Boolean.FALSE);
            } else {
              localEnumMap.put(localDecodeHintType, Boolean.TRUE);
            }
          }
          else if (localDecodeHintType.getValueType().equals(int[].class))
          {
            paramUri = (Uri)localObject;
            if (!((String)localObject).isEmpty())
            {
              paramUri = (Uri)localObject;
              if (((String)localObject).charAt(((String)localObject).length() - 1) == ',') {
                paramUri = ((String)localObject).substring(0, ((String)localObject).length() - 1);
              }
            }
            String[] arrayOfString = COMMA.split(paramUri);
            localObject = new int[arrayOfString.length];
            int j = 0;
            for (;;)
            {
              paramUri = (Uri)localObject;
              if (j < arrayOfString.length) {
                try
                {
                  localObject[j] = Integer.parseInt(arrayOfString[j]);
                  j += 1;
                }
                catch (NumberFormatException paramUri)
                {
                  Log.w(TAG, "Skipping array of integers hint " + localDecodeHintType + " due to invalid numeric value: '" + arrayOfString[j] + '\'');
                  paramUri = null;
                }
              }
            }
            if (paramUri != null) {
              localEnumMap.put(localDecodeHintType, paramUri);
            }
          }
          else
          {
            Log.w(TAG, "Unsupported hint type '" + localDecodeHintType + "' of type " + localDecodeHintType.getValueType());
          }
        }
      }
    }
    Log.i(TAG, "Hints from the URI: " + localEnumMap);
    return localEnumMap;
  }
  
  private static Map<String, String> splitQuery(String paramString)
  {
    HashMap localHashMap = new HashMap();
    int i = 0;
    for (;;)
    {
      int j;
      int k;
      if (i < paramString.length())
      {
        if (paramString.charAt(i) == '&')
        {
          i += 1;
          continue;
        }
        j = paramString.indexOf('&', i);
        k = paramString.indexOf('=', i);
        if (j >= 0) {
          break label152;
        }
        if (k >= 0) {
          break label105;
        }
        paramString = Uri.decode(paramString.substring(i).replace('+', ' '));
      }
      label105:
      String str;
      for (Object localObject = "";; localObject = str)
      {
        if (!localHashMap.containsKey(paramString)) {
          localHashMap.put(paramString, localObject);
        }
        return localHashMap;
        localObject = Uri.decode(paramString.substring(i, k).replace('+', ' '));
        str = Uri.decode(paramString.substring(k + 1).replace('+', ' '));
        paramString = (String)localObject;
      }
      label152:
      if ((k < 0) || (k > j))
      {
        localObject = Uri.decode(paramString.substring(i, j).replace('+', ' '));
        if (!localHashMap.containsKey(localObject)) {
          localHashMap.put(localObject, "");
        }
        i = j + 1;
      }
      else
      {
        localObject = Uri.decode(paramString.substring(i, k).replace('+', ' '));
        str = Uri.decode(paramString.substring(k + 1, j).replace('+', ' '));
        if (!localHashMap.containsKey(localObject)) {
          localHashMap.put(localObject, str);
        }
        i = j + 1;
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\DecodeHintManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */