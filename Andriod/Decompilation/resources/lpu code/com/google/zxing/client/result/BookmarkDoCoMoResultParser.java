package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class BookmarkDoCoMoResultParser
  extends AbstractDoCoMoResultParser
{
  public URIParsedResult parse(Result paramResult)
  {
    Object localObject = paramResult.getText();
    if (!((String)localObject).startsWith("MEBKM:")) {}
    do
    {
      do
      {
        return null;
        paramResult = matchSingleDoCoMoPrefixedField("TITLE:", (String)localObject, true);
        localObject = matchDoCoMoPrefixedField("URL:", (String)localObject, true);
      } while (localObject == null);
      localObject = localObject[0];
    } while (!URIResultParser.isBasicallyValidURI((String)localObject));
    return new URIParsedResult((String)localObject, paramResult);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\BookmarkDoCoMoResultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */