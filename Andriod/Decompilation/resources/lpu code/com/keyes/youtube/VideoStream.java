package com.keyes.youtube;

import java.util.HashMap;
import java.util.Map;

public class VideoStream
{
  protected String mUrl;
  
  public VideoStream(String paramString)
  {
    paramString = paramString.split("&");
    HashMap localHashMap = new HashMap();
    int i = 0;
    for (;;)
    {
      if (i >= paramString.length)
      {
        this.mUrl = ((String)localHashMap.get("url") + "&signature=" + (String)localHashMap.get("sig"));
        return;
      }
      String[] arrayOfString = paramString[i].split("=");
      if ((arrayOfString != null) && (arrayOfString.length >= 2)) {
        localHashMap.put(arrayOfString[0], arrayOfString[1]);
      }
      i += 1;
    }
  }
  
  public String getUrl()
  {
    return this.mUrl;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\keyes\youtube\VideoStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */