package com.google.zxing.client.android.result.supplement;

import android.content.Context;
import android.widget.TextView;
import barcodescanner.xservices.nl.barcodescanner.R.string;
import com.google.zxing.client.android.HttpHelper;
import com.google.zxing.client.android.history.HistoryManager;
import com.google.zxing.client.result.URIParsedResult;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

final class URIResultInfoRetriever
  extends SupplementalInfoRetriever
{
  private static final int MAX_REDIRECTS = 5;
  private final String redirectString;
  private final URIParsedResult result;
  
  URIResultInfoRetriever(TextView paramTextView, URIParsedResult paramURIParsedResult, HistoryManager paramHistoryManager, Context paramContext)
  {
    super(paramTextView, paramHistoryManager);
    this.redirectString = paramContext.getString(R.string.msg_redirect);
    this.result = paramURIParsedResult;
  }
  
  void retrieveSupplementalInfo()
    throws IOException
  {
    try
    {
      Object localObject = new URI(this.result.getURI());
      URI localURI = HttpHelper.unredirect((URI)localObject);
      int i = 0;
      while ((i < 5) && (!((URI)localObject).equals(localURI)))
      {
        localObject = this.result.getDisplayResult();
        String str1 = this.redirectString + " : " + localURI;
        String str2 = localURI.toString();
        append((String)localObject, null, new String[] { str1 }, str2);
        localObject = localURI;
        localURI = HttpHelper.unredirect(localURI);
        i += 1;
      }
      return;
    }
    catch (URISyntaxException localURISyntaxException) {}
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\supplement\URIResultInfoRetriever.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */