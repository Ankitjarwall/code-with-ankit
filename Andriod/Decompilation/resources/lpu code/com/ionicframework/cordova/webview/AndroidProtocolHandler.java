package com.ionicframework.cordova.webview;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import android.util.TypedValue;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

public class AndroidProtocolHandler
{
  private static final String TAG = "AndroidProtocolHandler";
  private Context context;
  
  static
  {
    if (!AndroidProtocolHandler.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public AndroidProtocolHandler(Context paramContext)
  {
    this.context = paramContext;
  }
  
  private static int getFieldId(Context paramContext, String paramString1, String paramString2)
    throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException
  {
    return paramContext.getClassLoader().loadClass(paramContext.getPackageName() + ".R$" + paramString1).getField(paramString2).getInt(null);
  }
  
  private static int getValueType(Context paramContext, int paramInt)
  {
    TypedValue localTypedValue = new TypedValue();
    paramContext.getResources().getValue(paramInt, localTypedValue, true);
    return localTypedValue.type;
  }
  
  public InputStream openAsset(String paramString1, String paramString2)
    throws IOException
  {
    if (paramString1.startsWith(paramString2 + "/_file_"))
    {
      if (paramString1.contains("content://"))
      {
        paramString1 = paramString1.replace(paramString2 + "/_file_/", "content://");
        return this.context.getContentResolver().openInputStream(Uri.parse(paramString1));
      }
      return new FileInputStream(new File(paramString1.replace(paramString2 + "/_file_/", "")));
    }
    return this.context.getAssets().open(paramString1, 2);
  }
  
  public InputStream openFile(String paramString)
    throws IOException
  {
    return new FileInputStream(new File(paramString));
  }
  
  public InputStream openResource(Uri paramUri)
  {
    assert (paramUri.getPath() != null);
    Object localObject = paramUri.getPathSegments();
    String str = (String)((List)localObject).get(((List)localObject).size() - 2);
    localObject = ((String)localObject.get(localObject.size() - 1)).split("\\.")[0];
    try
    {
      if (this.context.getApplicationContext() != null) {
        this.context = this.context.getApplicationContext();
      }
      int i = getFieldId(this.context, str, (String)localObject);
      if (getValueType(this.context, i) == 3) {
        return this.context.getResources().openRawResource(i);
      }
      Log.e("AndroidProtocolHandler", "Asset not of type string: " + paramUri);
      return null;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      Log.e("AndroidProtocolHandler", "Unable to open resource URL: " + paramUri, localClassNotFoundException);
      return null;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      Log.e("AndroidProtocolHandler", "Unable to open resource URL: " + paramUri, localNoSuchFieldException);
      return null;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      Log.e("AndroidProtocolHandler", "Unable to open resource URL: " + paramUri, localIllegalAccessException);
    }
    return null;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\ionicframework\cordova\webview\AndroidProtocolHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */