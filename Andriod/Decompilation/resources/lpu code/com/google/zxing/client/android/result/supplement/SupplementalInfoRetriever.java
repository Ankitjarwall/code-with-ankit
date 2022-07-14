package com.google.zxing.client.android.result.supplement;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.widget.TextView;
import com.google.zxing.client.android.history.HistoryManager;
import com.google.zxing.client.result.ISBNParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ProductParsedResult;
import com.google.zxing.client.result.URIParsedResult;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.RejectedExecutionException;

public abstract class SupplementalInfoRetriever
  extends AsyncTask<Object, Object, Object>
{
  private static final String TAG = "SupplementalInfo";
  private final WeakReference<HistoryManager> historyManagerRef;
  private final Collection<Spannable> newContents;
  private final Collection<String[]> newHistories;
  private final WeakReference<TextView> textViewRef;
  
  SupplementalInfoRetriever(TextView paramTextView, HistoryManager paramHistoryManager)
  {
    this.textViewRef = new WeakReference(paramTextView);
    this.historyManagerRef = new WeakReference(paramHistoryManager);
    this.newContents = new ArrayList();
    this.newHistories = new ArrayList();
  }
  
  static void maybeAddText(String paramString, Collection<String> paramCollection)
  {
    if ((paramString != null) && (!paramString.isEmpty())) {
      paramCollection.add(paramString);
    }
  }
  
  static void maybeAddTextSeries(Collection<String> paramCollection1, Collection<String> paramCollection2)
  {
    if ((paramCollection1 != null) && (!paramCollection1.isEmpty()))
    {
      int i = 1;
      StringBuilder localStringBuilder = new StringBuilder();
      paramCollection1 = paramCollection1.iterator();
      if (paramCollection1.hasNext())
      {
        String str = (String)paramCollection1.next();
        if (i != 0) {
          i = 0;
        }
        for (;;)
        {
          localStringBuilder.append(str);
          break;
          localStringBuilder.append(", ");
        }
      }
      paramCollection2.add(localStringBuilder.toString());
    }
  }
  
  public static void maybeInvokeRetrieval(TextView paramTextView, ParsedResult paramParsedResult, HistoryManager paramHistoryManager, Context paramContext)
  {
    try
    {
      if ((paramParsedResult instanceof URIParsedResult))
      {
        new URIResultInfoRetriever(paramTextView, (URIParsedResult)paramParsedResult, paramHistoryManager, paramContext).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
        new TitleRetriever(paramTextView, (URIParsedResult)paramParsedResult, paramHistoryManager).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
        return;
      }
      if ((paramParsedResult instanceof ProductParsedResult))
      {
        new ProductResultInfoRetriever(paramTextView, ((ProductParsedResult)paramParsedResult).getProductID(), paramHistoryManager, paramContext).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
        return;
      }
      if ((paramParsedResult instanceof ISBNParsedResult))
      {
        paramParsedResult = ((ISBNParsedResult)paramParsedResult).getISBN();
        new ProductResultInfoRetriever(paramTextView, paramParsedResult, paramHistoryManager, paramContext).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
        new BookResultInfoRetriever(paramTextView, paramParsedResult, paramHistoryManager, paramContext).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
      }
      return;
    }
    catch (RejectedExecutionException paramTextView) {}
  }
  
  final void append(String paramString1, String paramString2, String[] paramArrayOfString, String paramString3)
  {
    Object localObject = new StringBuilder();
    if (paramString2 != null) {
      ((StringBuilder)localObject).append(paramString2).append(' ');
    }
    int k = ((StringBuilder)localObject).length();
    int j = 1;
    int m = paramArrayOfString.length;
    int i = 0;
    if (i < m)
    {
      paramString2 = paramArrayOfString[i];
      if (j != 0)
      {
        ((StringBuilder)localObject).append(paramString2);
        j = 0;
      }
      for (;;)
      {
        i += 1;
        break;
        ((StringBuilder)localObject).append(" [");
        ((StringBuilder)localObject).append(paramString2);
        ((StringBuilder)localObject).append(']');
      }
    }
    i = ((StringBuilder)localObject).length();
    paramArrayOfString = ((StringBuilder)localObject).toString();
    localObject = new SpannableString(paramArrayOfString + "\n\n");
    if (paramString3 != null)
    {
      if (!paramString3.startsWith("HTTP://")) {
        break label241;
      }
      paramString2 = "http" + paramString3.substring(4);
    }
    for (;;)
    {
      ((Spannable)localObject).setSpan(new URLSpan(paramString2), k, i, 33);
      this.newContents.add(localObject);
      this.newHistories.add(new String[] { paramString1, paramArrayOfString });
      return;
      label241:
      paramString2 = paramString3;
      if (paramString3.startsWith("HTTPS://")) {
        paramString2 = "https" + paramString3.substring(5);
      }
    }
  }
  
  public final Object doInBackground(Object... paramVarArgs)
  {
    try
    {
      retrieveSupplementalInfo();
      return null;
    }
    catch (IOException paramVarArgs)
    {
      for (;;)
      {
        Log.w("SupplementalInfo", paramVarArgs);
      }
    }
  }
  
  protected final void onPostExecute(Object paramObject)
  {
    paramObject = (TextView)this.textViewRef.get();
    Iterator localIterator;
    if (paramObject != null)
    {
      localIterator = this.newContents.iterator();
      while (localIterator.hasNext()) {
        ((TextView)paramObject).append((CharSequence)localIterator.next());
      }
      ((TextView)paramObject).setMovementMethod(LinkMovementMethod.getInstance());
    }
    paramObject = (HistoryManager)this.historyManagerRef.get();
    if (paramObject != null)
    {
      localIterator = this.newHistories.iterator();
      while (localIterator.hasNext())
      {
        String[] arrayOfString = (String[])localIterator.next();
        ((HistoryManager)paramObject).addHistoryItemDetails(arrayOfString[0], arrayOfString[1]);
      }
    }
  }
  
  abstract void retrieveSupplementalInfo()
    throws IOException;
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\supplement\SupplementalInfoRetriever.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */