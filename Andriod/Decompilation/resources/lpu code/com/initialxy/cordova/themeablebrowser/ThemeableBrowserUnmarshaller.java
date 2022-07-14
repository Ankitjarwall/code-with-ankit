package com.initialxy.cordova.themeablebrowser;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ThemeableBrowserUnmarshaller
{
  private static List<?> JSONToList(JSONArray paramJSONArray, Type paramType)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramType instanceof ParameterizedType)) {}
    for (Object localObject = (Class)((ParameterizedType)paramType).getRawType();; localObject = (Class)paramType)
    {
      int j = paramJSONArray.length();
      int i = 0;
      while (i < j) {
        try
        {
          localObject = valToType(paramJSONArray.get(i), paramType);
          if (localObject != null) {
            localArrayList.add(localObject);
          }
          i += 1;
        }
        catch (JSONException paramJSONArray)
        {
          throw new ParserException(paramJSONArray);
        }
      }
    }
    return localArrayList;
  }
  
  public static <T> T JSONToObj(String paramString, Class<T> paramClass)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString != null)
    {
      localObject1 = localObject2;
      if (paramString.isEmpty()) {}
    }
    try
    {
      localObject1 = JSONToObj(new JSONObject(paramString), paramClass);
      return (T)localObject1;
    }
    catch (JSONException paramString)
    {
      throw new ParserException(paramString);
    }
  }
  
  /* Error */
  public static <T> T JSONToObj(JSONObject paramJSONObject, Class<T> paramClass)
  {
    // Byte code:
    //   0: aload_1
    //   1: iconst_0
    //   2: anewarray 33	java/lang/Class
    //   5: invokevirtual 91	java/lang/Class:getDeclaredConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   8: astore_2
    //   9: aload_2
    //   10: iconst_1
    //   11: invokevirtual 97	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   14: aload_2
    //   15: iconst_0
    //   16: anewarray 4	java/lang/Object
    //   19: invokevirtual 101	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   22: astore_2
    //   23: aload_0
    //   24: invokevirtual 105	org/json/JSONObject:keys	()Ljava/util/Iterator;
    //   27: astore_3
    //   28: aload_3
    //   29: invokeinterface 110 1 0
    //   34: ifeq +272 -> 306
    //   37: aload_3
    //   38: invokeinterface 114 1 0
    //   43: checkcast 62	java/lang/String
    //   46: astore 4
    //   48: aload_0
    //   49: aload 4
    //   51: invokevirtual 117	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   54: astore 5
    //   56: aload_1
    //   57: aload 4
    //   59: invokevirtual 121	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   62: astore 4
    //   64: aload 5
    //   66: aload 4
    //   68: invokevirtual 126	java/lang/reflect/Field:getGenericType	()Ljava/lang/reflect/Type;
    //   71: invokestatic 47	com/initialxy/cordova/themeablebrowser/ThemeableBrowserUnmarshaller:valToType	(Ljava/lang/Object;Ljava/lang/reflect/Type;)Ljava/lang/Object;
    //   74: astore 5
    //   76: aload 5
    //   78: ifnonnull +49 -> 127
    //   81: aload 4
    //   83: invokevirtual 130	java/lang/reflect/Field:getType	()Ljava/lang/Class;
    //   86: invokevirtual 133	java/lang/Class:isPrimitive	()Z
    //   89: ifne +13 -> 102
    //   92: aload 4
    //   94: aload_2
    //   95: aconst_null
    //   96: invokevirtual 137	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   99: goto -71 -> 28
    //   102: new 12	com/initialxy/cordova/themeablebrowser/ThemeableBrowserUnmarshaller$TypeMismatchException
    //   105: dup
    //   106: ldc -117
    //   108: iconst_1
    //   109: anewarray 4	java/lang/Object
    //   112: dup
    //   113: iconst_0
    //   114: aload 4
    //   116: invokevirtual 130	java/lang/reflect/Field:getType	()Ljava/lang/Class;
    //   119: aastore
    //   120: invokestatic 143	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   123: invokespecial 144	com/initialxy/cordova/themeablebrowser/ThemeableBrowserUnmarshaller$TypeMismatchException:<init>	(Ljava/lang/String;)V
    //   126: athrow
    //   127: aload 5
    //   129: instanceof 49
    //   132: ifeq +97 -> 229
    //   135: aload 4
    //   137: invokevirtual 130	java/lang/reflect/Field:getType	()Ljava/lang/Class;
    //   140: ldc 49
    //   142: invokevirtual 148	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   145: ifeq +84 -> 229
    //   148: aload 4
    //   150: aload_2
    //   151: invokevirtual 151	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   154: astore 6
    //   156: aload 6
    //   158: ifnull +50 -> 208
    //   161: aload 6
    //   163: checkcast 49	java/util/List
    //   166: invokeinterface 154 1 0
    //   171: aload 6
    //   173: invokevirtual 157	java/lang/Object:getClass	()Ljava/lang/Class;
    //   176: ldc -97
    //   178: iconst_1
    //   179: anewarray 33	java/lang/Class
    //   182: dup
    //   183: iconst_0
    //   184: ldc -95
    //   186: aastore
    //   187: invokevirtual 165	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   190: aload 6
    //   192: iconst_1
    //   193: anewarray 4	java/lang/Object
    //   196: dup
    //   197: iconst_0
    //   198: aload 5
    //   200: aastore
    //   201: invokevirtual 171	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   204: pop
    //   205: goto -177 -> 28
    //   208: aload 4
    //   210: aload_2
    //   211: aload 5
    //   213: invokevirtual 137	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   216: goto -188 -> 28
    //   219: astore_0
    //   220: new 9	com/initialxy/cordova/themeablebrowser/ThemeableBrowserUnmarshaller$ParserException
    //   223: dup
    //   224: aload_0
    //   225: invokespecial 56	com/initialxy/cordova/themeablebrowser/ThemeableBrowserUnmarshaller$ParserException:<init>	(Ljava/lang/Exception;)V
    //   228: athrow
    //   229: aload 4
    //   231: aload_2
    //   232: aload 5
    //   234: invokevirtual 137	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   237: goto -209 -> 28
    //   240: astore_0
    //   241: new 6	com/initialxy/cordova/themeablebrowser/ThemeableBrowserUnmarshaller$ClassInstantiationException
    //   244: dup
    //   245: new 173	java/lang/StringBuilder
    //   248: dup
    //   249: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   252: ldc -80
    //   254: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   257: aload_1
    //   258: invokevirtual 184	java/lang/Class:toString	()Ljava/lang/String;
    //   261: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   264: ldc -70
    //   266: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   272: invokespecial 188	com/initialxy/cordova/themeablebrowser/ThemeableBrowserUnmarshaller$ClassInstantiationException:<init>	(Ljava/lang/String;)V
    //   275: athrow
    //   276: astore_0
    //   277: new 6	com/initialxy/cordova/themeablebrowser/ThemeableBrowserUnmarshaller$ClassInstantiationException
    //   280: dup
    //   281: aload_1
    //   282: invokespecial 191	com/initialxy/cordova/themeablebrowser/ThemeableBrowserUnmarshaller$ClassInstantiationException:<init>	(Ljava/lang/Class;)V
    //   285: athrow
    //   286: astore_0
    //   287: new 6	com/initialxy/cordova/themeablebrowser/ThemeableBrowserUnmarshaller$ClassInstantiationException
    //   290: dup
    //   291: aload_1
    //   292: invokespecial 191	com/initialxy/cordova/themeablebrowser/ThemeableBrowserUnmarshaller$ClassInstantiationException:<init>	(Ljava/lang/Class;)V
    //   295: athrow
    //   296: astore_0
    //   297: new 6	com/initialxy/cordova/themeablebrowser/ThemeableBrowserUnmarshaller$ClassInstantiationException
    //   300: dup
    //   301: aload_1
    //   302: invokespecial 191	com/initialxy/cordova/themeablebrowser/ThemeableBrowserUnmarshaller$ClassInstantiationException:<init>	(Ljava/lang/Class;)V
    //   305: athrow
    //   306: aload_2
    //   307: areturn
    //   308: astore 4
    //   310: goto -282 -> 28
    //   313: astore 4
    //   315: goto -287 -> 28
    //   318: astore 4
    //   320: goto -292 -> 28
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	323	0	paramJSONObject	JSONObject
    //   0	323	1	paramClass	Class<T>
    //   8	299	2	localObject1	Object
    //   27	11	3	localIterator	Iterator
    //   46	184	4	localObject2	Object
    //   308	1	4	localNoSuchFieldException	NoSuchFieldException
    //   313	1	4	localIllegalAccessException	IllegalAccessException
    //   318	1	4	localIllegalArgumentException	IllegalArgumentException
    //   54	179	5	localObject3	Object
    //   154	37	6	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   0	28	219	org/json/JSONException
    //   28	56	219	org/json/JSONException
    //   56	76	219	org/json/JSONException
    //   81	99	219	org/json/JSONException
    //   102	127	219	org/json/JSONException
    //   127	156	219	org/json/JSONException
    //   161	205	219	org/json/JSONException
    //   208	216	219	org/json/JSONException
    //   229	237	219	org/json/JSONException
    //   0	28	240	java/lang/NoSuchMethodException
    //   28	56	240	java/lang/NoSuchMethodException
    //   56	76	240	java/lang/NoSuchMethodException
    //   81	99	240	java/lang/NoSuchMethodException
    //   102	127	240	java/lang/NoSuchMethodException
    //   127	156	240	java/lang/NoSuchMethodException
    //   161	205	240	java/lang/NoSuchMethodException
    //   208	216	240	java/lang/NoSuchMethodException
    //   229	237	240	java/lang/NoSuchMethodException
    //   0	28	276	java/lang/InstantiationException
    //   28	56	276	java/lang/InstantiationException
    //   56	76	276	java/lang/InstantiationException
    //   81	99	276	java/lang/InstantiationException
    //   102	127	276	java/lang/InstantiationException
    //   127	156	276	java/lang/InstantiationException
    //   161	205	276	java/lang/InstantiationException
    //   208	216	276	java/lang/InstantiationException
    //   229	237	276	java/lang/InstantiationException
    //   0	28	286	java/lang/IllegalAccessException
    //   28	56	286	java/lang/IllegalAccessException
    //   0	28	296	java/lang/reflect/InvocationTargetException
    //   28	56	296	java/lang/reflect/InvocationTargetException
    //   56	76	296	java/lang/reflect/InvocationTargetException
    //   81	99	296	java/lang/reflect/InvocationTargetException
    //   102	127	296	java/lang/reflect/InvocationTargetException
    //   127	156	296	java/lang/reflect/InvocationTargetException
    //   161	205	296	java/lang/reflect/InvocationTargetException
    //   208	216	296	java/lang/reflect/InvocationTargetException
    //   229	237	296	java/lang/reflect/InvocationTargetException
    //   56	76	308	java/lang/NoSuchFieldException
    //   81	99	308	java/lang/NoSuchFieldException
    //   102	127	308	java/lang/NoSuchFieldException
    //   127	156	308	java/lang/NoSuchFieldException
    //   161	205	308	java/lang/NoSuchFieldException
    //   208	216	308	java/lang/NoSuchFieldException
    //   229	237	308	java/lang/NoSuchFieldException
    //   56	76	313	java/lang/IllegalAccessException
    //   81	99	313	java/lang/IllegalAccessException
    //   102	127	313	java/lang/IllegalAccessException
    //   127	156	313	java/lang/IllegalAccessException
    //   161	205	313	java/lang/IllegalAccessException
    //   208	216	313	java/lang/IllegalAccessException
    //   229	237	313	java/lang/IllegalAccessException
    //   56	76	318	java/lang/IllegalArgumentException
    //   81	99	318	java/lang/IllegalArgumentException
    //   102	127	318	java/lang/IllegalArgumentException
    //   127	156	318	java/lang/IllegalArgumentException
    //   161	205	318	java/lang/IllegalArgumentException
    //   208	216	318	java/lang/IllegalArgumentException
    //   229	237	318	java/lang/IllegalArgumentException
  }
  
  private static Object convertToPrimitiveFieldObj(Object paramObject, Class<?> paramClass)
  {
    Object localObject1 = paramObject.getClass();
    Object localObject3 = null;
    Object localObject4 = null;
    for (;;)
    {
      try
      {
        if ((!paramClass.isAssignableFrom(Byte.class)) && (!paramClass.isAssignableFrom(Byte.TYPE))) {
          continue;
        }
        localObject1 = ((Class)localObject1).getMethod("byteValue", new Class[0]);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        throw new TypeMismatchException(String.format("Cannot convert %s to %s.", new Object[] { paramObject.getClass(), paramClass }));
        if (paramClass.isAssignableFrom(Character.class)) {
          continue;
        }
        Object localObject2 = localObject4;
        if (!paramClass.isAssignableFrom(Character.TYPE)) {
          continue;
        }
        if ((!(paramObject instanceof String)) || (((String)paramObject).length() != 1)) {
          continue;
        }
        localObject3 = Character.valueOf(((String)paramObject).charAt(0));
        localObject2 = localObject4;
        continue;
        if (!(paramObject instanceof String)) {
          continue;
        }
        throw new TypeMismatchException("Expected Character, but received String with length other than 1.");
      }
      catch (SecurityException localSecurityException)
      {
        throw new TypeMismatchException(String.format("Cannot convert %s to %s.", new Object[] { paramObject.getClass(), paramClass }));
        throw new TypeMismatchException(String.format("Expected Character, accept String, but got %s.", new Object[] { paramObject.getClass() }));
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new TypeMismatchException(String.format("Cannot convert %s to %s.", new Object[] { paramObject.getClass(), paramClass }));
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        throw new TypeMismatchException(String.format("Cannot convert %s to %s.", new Object[] { paramObject.getClass(), paramClass }));
      }
      if (localObject1 == null) {
        return localObject3;
      }
      return ((Method)localObject1).invoke(paramObject, new Object[0]);
      if ((paramClass.isAssignableFrom(Short.class)) || (paramClass.isAssignableFrom(Short.TYPE)))
      {
        localObject1 = ((Class)localObject1).getMethod("shortValue", new Class[0]);
      }
      else if ((paramClass.isAssignableFrom(Integer.class)) || (paramClass.isAssignableFrom(Integer.TYPE)))
      {
        localObject1 = ((Class)localObject1).getMethod("intValue", new Class[0]);
      }
      else if ((paramClass.isAssignableFrom(Long.class)) || (paramClass.isAssignableFrom(Long.TYPE)))
      {
        localObject1 = ((Class)localObject1).getMethod("longValue", new Class[0]);
      }
      else if ((paramClass.isAssignableFrom(Float.class)) || (paramClass.isAssignableFrom(Float.TYPE)))
      {
        localObject1 = ((Class)localObject1).getMethod("floatValue", new Class[0]);
      }
      else if ((paramClass.isAssignableFrom(Double.class)) || (paramClass.isAssignableFrom(Double.TYPE)))
      {
        localObject1 = ((Class)localObject1).getMethod("doubleValue", new Class[0]);
      }
      else
      {
        if ((!paramClass.isAssignableFrom(Boolean.class)) && (!paramClass.isAssignableFrom(Boolean.TYPE))) {
          continue;
        }
        if (!(paramObject instanceof Boolean)) {
          continue;
        }
        localObject3 = paramObject;
        localObject1 = localObject4;
      }
    }
    throw new TypeMismatchException(paramClass, paramObject.getClass());
    return localObject3;
  }
  
  private static Type getListItemType(Type paramType)
  {
    if ((paramType instanceof GenericArrayType)) {
      return ((GenericArrayType)paramType).getGenericComponentType();
    }
    if ((paramType instanceof ParameterizedType)) {
      return ((ParameterizedType)paramType).getActualTypeArguments()[0];
    }
    return ((Class)paramType).getComponentType();
  }
  
  private static boolean isPrimitive(Class<?> paramClass)
  {
    return (paramClass.isPrimitive()) || (paramClass.isAssignableFrom(Byte.class)) || (paramClass.isAssignableFrom(Short.class)) || (paramClass.isAssignableFrom(Integer.class)) || (paramClass.isAssignableFrom(Long.class)) || (paramClass.isAssignableFrom(Float.class)) || (paramClass.isAssignableFrom(Double.class)) || (paramClass.isAssignableFrom(Boolean.class)) || (paramClass.isAssignableFrom(Character.class));
  }
  
  private static Object valToType(Object paramObject, Type paramType)
  {
    Object localObject3 = null;
    int i = 0;
    Object localObject2;
    label40:
    Object localObject1;
    if ((paramType instanceof ParameterizedType))
    {
      localObject2 = (Class)((ParameterizedType)paramType).getRawType();
      if ((i == 0) && (!((Class)localObject2).isArray())) {
        break label103;
      }
      i = 1;
      localObject1 = localObject3;
      if (paramObject != null)
      {
        localObject1 = localObject3;
        if (paramObject != JSONObject.NULL)
        {
          if (!((Class)localObject2).isAssignableFrom(String.class)) {
            break label122;
          }
          if (!(paramObject instanceof String)) {
            break label108;
          }
          localObject1 = paramObject;
        }
      }
    }
    label103:
    label108:
    label122:
    label265:
    label279:
    do
    {
      do
      {
        return localObject1;
        if ((paramType instanceof GenericArrayType))
        {
          localObject2 = List.class;
          i = 1;
          break;
        }
        localObject2 = (Class)paramType;
        break;
        i = 0;
        break label40;
        throw new TypeMismatchException((Type)localObject2, paramObject.getClass());
        if (isPrimitive((Class)localObject2)) {
          return convertToPrimitiveFieldObj(paramObject, (Class)localObject2);
        }
        if ((i == 0) && (!((Class)localObject2).isAssignableFrom(List.class))) {
          break label279;
        }
        if (!(paramObject instanceof JSONArray)) {
          break label265;
        }
        localObject2 = getListItemType(paramType);
        paramObject = JSONToList((JSONArray)paramObject, (Type)localObject2);
        localObject1 = paramObject;
      } while (i == 0);
      paramType = (List)paramObject;
      if ((localObject2 instanceof ParameterizedType)) {}
      for (paramObject = (Class)((ParameterizedType)localObject2).getRawType();; paramObject = (Class)localObject2)
      {
        paramObject = Array.newInstance((Class)paramObject, paramType.size());
        i = 0;
        paramType = paramType.iterator();
        for (;;)
        {
          localObject1 = paramObject;
          if (!paramType.hasNext()) {
            break;
          }
          Array.set(paramObject, i, paramType.next());
          i += 1;
        }
      }
      throw new TypeMismatchException(JSONArray.class, paramObject.getClass());
      localObject1 = localObject3;
    } while (!(paramObject instanceof JSONObject));
    return JSONToObj((JSONObject)paramObject, (Class)localObject2);
  }
  
  public static class ClassInstantiationException
    extends RuntimeException
  {
    public ClassInstantiationException(Class<?> paramClass)
    {
      super();
    }
    
    public ClassInstantiationException(String paramString)
    {
      super();
    }
  }
  
  public static class ParserException
    extends RuntimeException
  {
    public ParserException(Exception paramException)
    {
      super();
    }
  }
  
  public static class TypeMismatchException
    extends RuntimeException
  {
    public TypeMismatchException(String paramString)
    {
      super();
    }
    
    public TypeMismatchException(Type paramType1, Type paramType2)
    {
      super();
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\initialxy\cordova\themeablebrowser\ThemeableBrowserUnmarshaller.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */