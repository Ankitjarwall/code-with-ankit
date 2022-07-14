package com.ionicframework.cordova.webview;

import android.net.Uri;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UriMatcher
{
  private static final int EXACT = 0;
  static final Pattern PATH_SPLIT_PATTERN = Pattern.compile("/");
  private static final int REST = 2;
  private static final int TEXT = 1;
  private ArrayList<UriMatcher> mChildren;
  private Object mCode;
  private String mText;
  private int mWhich;
  
  private UriMatcher()
  {
    this.mCode = null;
    this.mWhich = -1;
    this.mChildren = new ArrayList();
    this.mText = null;
  }
  
  public UriMatcher(Object paramObject)
  {
    this.mCode = paramObject;
    this.mWhich = -1;
    this.mChildren = new ArrayList();
    this.mText = null;
  }
  
  public void addURI(String paramString1, String paramString2, String paramString3, Object paramObject)
  {
    if (paramObject == null) {
      throw new IllegalArgumentException("Code can't be null");
    }
    String[] arrayOfString = null;
    String str;
    Object localObject;
    if (paramString3 != null)
    {
      str = paramString3;
      localObject = str;
      if (paramString3.length() > 0)
      {
        localObject = str;
        if (paramString3.charAt(0) == '/') {
          localObject = paramString3.substring(1);
        }
      }
      arrayOfString = PATH_SPLIT_PATTERN.split((CharSequence)localObject);
    }
    int i;
    int j;
    label83:
    label100:
    int k;
    if (arrayOfString != null)
    {
      i = arrayOfString.length;
      paramString3 = this;
      j = -2;
      if (j >= i) {
        break label272;
      }
      if (j != -2) {
        break label215;
      }
      str = paramString1;
      ArrayList localArrayList = paramString3.mChildren;
      int m = localArrayList.size();
      k = 0;
      label116:
      localObject = paramString3;
      if (k < m)
      {
        localObject = (UriMatcher)localArrayList.get(k);
        if (!str.equals(((UriMatcher)localObject).mText)) {
          break label237;
        }
      }
      paramString3 = (String)localObject;
      if (k == m)
      {
        paramString3 = new UriMatcher();
        if (!str.equals("**")) {
          break label246;
        }
        paramString3.mWhich = 2;
      }
    }
    for (;;)
    {
      paramString3.mText = str;
      ((UriMatcher)localObject).mChildren.add(paramString3);
      j += 1;
      break label83;
      i = 0;
      break;
      label215:
      if (j == -1)
      {
        str = paramString2;
        break label100;
      }
      str = arrayOfString[j];
      break label100;
      label237:
      k += 1;
      break label116;
      label246:
      if (str.equals("*")) {
        paramString3.mWhich = 1;
      } else {
        paramString3.mWhich = 0;
      }
    }
    label272:
    paramString3.mCode = paramObject;
  }
  
  public Object match(Uri paramUri)
  {
    List localList = paramUri.getPathSegments();
    int k = localList.size();
    Object localObject2 = this;
    if ((k == 0) && (paramUri.getAuthority() == null)) {
      return this.mCode;
    }
    int i = -2;
    for (;;)
    {
      String str;
      if (i < k)
      {
        if (i != -2) {
          break label74;
        }
        str = paramUri.getScheme();
      }
      ArrayList localArrayList;
      for (;;)
      {
        localArrayList = ((UriMatcher)localObject2).mChildren;
        if (localArrayList != null) {
          break;
        }
        return ((UriMatcher)localObject2).mCode;
        label74:
        if (i == -1) {
          str = paramUri.getAuthority();
        } else {
          str = (String)localList.get(i);
        }
      }
      Object localObject1 = null;
      int m = localArrayList.size();
      int j = 0;
      for (;;)
      {
        localObject2 = localObject1;
        if (j < m)
        {
          localObject2 = (UriMatcher)localArrayList.get(j);
          switch (((UriMatcher)localObject2).mWhich)
          {
          }
        }
        while (localObject1 != null)
        {
          localObject2 = localObject1;
          if (localObject2 != null) {
            break label224;
          }
          return null;
          if (((UriMatcher)localObject2).mText.equals(str))
          {
            localObject1 = localObject2;
            continue;
            localObject1 = localObject2;
            continue;
            return ((UriMatcher)localObject2).mCode;
          }
        }
        j += 1;
      }
      label224:
      i += 1;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\ionicframework\cordova\webview\UriMatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */