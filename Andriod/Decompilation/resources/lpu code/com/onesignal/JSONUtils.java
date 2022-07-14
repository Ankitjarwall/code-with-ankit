package com.onesignal;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JSONUtils
{
  static JSONObject generateJsonDiff(JSONObject paramJSONObject1, JSONObject paramJSONObject2, JSONObject paramJSONObject3, Set<String> paramSet)
  {
    JSONObject localJSONObject1;
    if (paramJSONObject1 == null) {
      localJSONObject1 = null;
    }
    do
    {
      return localJSONObject1;
      localJSONObject1 = paramJSONObject3;
    } while (paramJSONObject2 == null);
    Iterator localIterator = paramJSONObject2.keys();
    if (paramJSONObject3 != null) {
      localJSONObject1 = paramJSONObject3;
    }
    while (localIterator.hasNext())
    {
      String str;
      Object localObject4;
      try
      {
        str = (String)localIterator.next();
        localObject4 = paramJSONObject2.get(str);
        if (!paramJSONObject1.has(str)) {
          break label326;
        }
        if (!(localObject4 instanceof JSONObject)) {
          break label187;
        }
        JSONObject localJSONObject2 = paramJSONObject1.getJSONObject(str);
        Object localObject3 = null;
        Object localObject1 = localObject3;
        if (paramJSONObject3 != null)
        {
          localObject1 = localObject3;
          if (paramJSONObject3.has(str)) {
            localObject1 = paramJSONObject3.getJSONObject(str);
          }
        }
        localObject1 = generateJsonDiff(localJSONObject2, (JSONObject)localObject4, (JSONObject)localObject1, paramSet).toString();
        if (((String)localObject1).equals("{}")) {
          continue;
        }
        localJSONObject1.put(str, new JSONObject((String)localObject1));
      }
      catch (JSONException localJSONException)
      {
        ThrowableExtension.printStackTrace(localJSONException);
      }
      continue;
      localJSONObject1 = new JSONObject();
      continue;
      label187:
      if ((localObject4 instanceof JSONArray))
      {
        handleJsonArray(str, (JSONArray)localObject4, paramJSONObject1.getJSONArray(str), localJSONObject1);
      }
      else if ((paramSet != null) && (paramSet.contains(str)))
      {
        localJSONObject1.put(str, localObject4);
      }
      else
      {
        Object localObject2 = paramJSONObject1.get(str);
        if (!localObject4.equals(localObject2)) {
          if (((localObject2 instanceof Integer)) && (!"".equals(localObject4)))
          {
            if (((Number)localObject2).doubleValue() != ((Number)localObject4).doubleValue()) {
              localJSONObject1.put(str, localObject4);
            }
          }
          else
          {
            localJSONObject1.put(str, localObject4);
            continue;
            label326:
            if ((localObject4 instanceof JSONObject)) {
              localJSONObject1.put(str, new JSONObject(localObject4.toString()));
            } else if ((localObject4 instanceof JSONArray)) {
              handleJsonArray(str, (JSONArray)localObject4, null, localJSONObject1);
            } else {
              localJSONObject1.put(str, localObject4);
            }
          }
        }
      }
    }
    return localJSONObject1;
  }
  
  static JSONObject getJSONObjectWithoutBlankValues(JSONObject paramJSONObject, String paramString)
  {
    if (!paramJSONObject.has(paramString))
    {
      paramJSONObject = null;
      return paramJSONObject;
    }
    JSONObject localJSONObject = new JSONObject();
    paramString = paramJSONObject.optJSONObject(paramString);
    Iterator localIterator = paramString.keys();
    for (;;)
    {
      paramJSONObject = localJSONObject;
      if (!localIterator.hasNext()) {
        break;
      }
      paramJSONObject = (String)localIterator.next();
      try
      {
        Object localObject = paramString.get(paramJSONObject);
        if (!"".equals(localObject)) {
          localJSONObject.put(paramJSONObject, localObject);
        }
      }
      catch (Throwable paramJSONObject) {}
    }
  }
  
  private static void handleJsonArray(String paramString, JSONArray paramJSONArray1, JSONArray paramJSONArray2, JSONObject paramJSONObject)
    throws JSONException
  {
    if ((paramString.endsWith("_a")) || (paramString.endsWith("_d"))) {
      paramJSONObject.put(paramString, paramJSONArray1);
    }
    JSONArray localJSONArray2;
    do
    {
      return;
      String str2 = toStringNE(paramJSONArray1);
      JSONArray localJSONArray1 = new JSONArray();
      localJSONArray2 = new JSONArray();
      if (paramJSONArray2 == null) {}
      int i;
      for (String str1 = null;; str1 = toStringNE(paramJSONArray2))
      {
        i = 0;
        while (i < paramJSONArray1.length())
        {
          String str3 = (String)paramJSONArray1.get(i);
          if ((paramJSONArray2 == null) || (!str1.contains(str3))) {
            localJSONArray1.put(str3);
          }
          i += 1;
        }
      }
      if (paramJSONArray2 != null)
      {
        i = 0;
        while (i < paramJSONArray2.length())
        {
          paramJSONArray1 = paramJSONArray2.getString(i);
          if (!str2.contains(paramJSONArray1)) {
            localJSONArray2.put(paramJSONArray1);
          }
          i += 1;
        }
      }
      if (!localJSONArray1.toString().equals("[]")) {
        paramJSONObject.put(paramString + "_a", localJSONArray1);
      }
    } while (localJSONArray2.toString().equals("[]"));
    paramJSONObject.put(paramString + "_d", localJSONArray2);
  }
  
  static String toStringNE(JSONArray paramJSONArray)
  {
    localObject = "[";
    int i = 0;
    try
    {
      while (i < paramJSONArray.length())
      {
        String str = (String)localObject + "\"" + paramJSONArray.getString(i) + "\"";
        localObject = str;
        i += 1;
      }
      return (String)localObject + "]";
    }
    catch (Throwable paramJSONArray) {}
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\JSONUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */