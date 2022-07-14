package com.onesignal;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;
import org.json.JSONObject;

class OneSignalRestClient
{
  private static final String BASE_URL = "https://onesignal.com/api/v1/";
  private static final int GET_TIMEOUT = 60000;
  private static final int TIMEOUT = 120000;
  
  private static Thread callResponseHandlerOnFailure(ResponseHandler paramResponseHandler, final int paramInt, final String paramString, final Throwable paramThrowable)
  {
    if (paramResponseHandler == null) {
      return null;
    }
    paramResponseHandler = new Thread(new Runnable()
    {
      public void run()
      {
        this.val$handler.onFailure(paramInt, paramString, paramThrowable);
      }
    });
    paramResponseHandler.start();
    return paramResponseHandler;
  }
  
  private static Thread callResponseHandlerOnSuccess(ResponseHandler paramResponseHandler, final String paramString)
  {
    if (paramResponseHandler == null) {
      return null;
    }
    paramResponseHandler = new Thread(new Runnable()
    {
      public void run()
      {
        this.val$handler.onSuccess(paramString);
      }
    });
    paramResponseHandler.start();
    return paramResponseHandler;
  }
  
  static void get(String paramString, final ResponseHandler paramResponseHandler)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        OneSignalRestClient.makeRequest(this.val$url, null, null, paramResponseHandler, 60000);
      }
    }).start();
  }
  
  static void getSync(String paramString, ResponseHandler paramResponseHandler)
  {
    makeRequest(paramString, null, null, paramResponseHandler, 60000);
  }
  
  private static int getThreadTimeout(int paramInt)
  {
    return paramInt + 5000;
  }
  
  private static void makeRequest(final String paramString1, final String paramString2, final JSONObject paramJSONObject, final ResponseHandler paramResponseHandler, final int paramInt)
  {
    if ((paramString2 != null) && (OneSignal.shouldLogUserPrivacyConsentErrorMessageForMethodName(null))) {}
    for (;;)
    {
      return;
      Thread[] arrayOfThread = new Thread[1];
      paramString1 = new Thread(new Runnable()
      {
        public void run()
        {
          this.val$callbackThread[0] = OneSignalRestClient.startHTTPConnection(paramString1, paramString2, paramJSONObject, paramResponseHandler, paramInt);
        }
      }, "OS_HTTPConnection");
      paramString1.start();
      try
      {
        paramString1.join(getThreadTimeout(paramInt));
        if (paramString1.getState() != Thread.State.TERMINATED) {
          paramString1.interrupt();
        }
        if (arrayOfThread[0] != null)
        {
          arrayOfThread[0].join();
          return;
        }
      }
      catch (InterruptedException paramString1)
      {
        ThrowableExtension.printStackTrace(paramString1);
      }
    }
  }
  
  static void post(String paramString, final JSONObject paramJSONObject, final ResponseHandler paramResponseHandler)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        OneSignalRestClient.makeRequest(this.val$url, "POST", paramJSONObject, paramResponseHandler, 120000);
      }
    }).start();
  }
  
  static void postSync(String paramString, JSONObject paramJSONObject, ResponseHandler paramResponseHandler)
  {
    makeRequest(paramString, "POST", paramJSONObject, paramResponseHandler, 120000);
  }
  
  static void put(String paramString, final JSONObject paramJSONObject, final ResponseHandler paramResponseHandler)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        OneSignalRestClient.makeRequest(this.val$url, "PUT", paramJSONObject, paramResponseHandler, 120000);
      }
    }).start();
  }
  
  static void putSync(String paramString, JSONObject paramJSONObject, ResponseHandler paramResponseHandler)
  {
    makeRequest(paramString, "PUT", paramJSONObject, paramResponseHandler, 120000);
  }
  
  private static Thread startHTTPConnection(String paramString1, String paramString2, JSONObject paramJSONObject, ResponseHandler paramResponseHandler, int paramInt)
  {
    Object localObject3 = null;
    HttpURLConnection localHttpURLConnection2 = null;
    int j = -1;
    Object localObject2 = null;
    HttpURLConnection localHttpURLConnection1 = localHttpURLConnection2;
    int i = j;
    Object localObject1 = localObject3;
    for (;;)
    {
      try
      {
        OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "OneSignalRestClient: Making request to: https://onesignal.com/api/v1/" + paramString1);
        localHttpURLConnection1 = localHttpURLConnection2;
        i = j;
        localObject1 = localObject3;
        localHttpURLConnection2 = (HttpURLConnection)new URL("https://onesignal.com/api/v1/" + paramString1).openConnection();
        localHttpURLConnection1 = localHttpURLConnection2;
        i = j;
        localObject1 = localHttpURLConnection2;
        localHttpURLConnection2.setUseCaches(false);
        localHttpURLConnection1 = localHttpURLConnection2;
        i = j;
        localObject1 = localHttpURLConnection2;
        localHttpURLConnection2.setConnectTimeout(paramInt);
        localHttpURLConnection1 = localHttpURLConnection2;
        i = j;
        localObject1 = localHttpURLConnection2;
        localHttpURLConnection2.setReadTimeout(paramInt);
        if (paramJSONObject != null)
        {
          localHttpURLConnection1 = localHttpURLConnection2;
          i = j;
          localObject1 = localHttpURLConnection2;
          localHttpURLConnection2.setDoInput(true);
        }
        if (paramString2 != null)
        {
          localHttpURLConnection1 = localHttpURLConnection2;
          i = j;
          localObject1 = localHttpURLConnection2;
          localHttpURLConnection2.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
          localHttpURLConnection1 = localHttpURLConnection2;
          i = j;
          localObject1 = localHttpURLConnection2;
          localHttpURLConnection2.setRequestMethod(paramString2);
          localHttpURLConnection1 = localHttpURLConnection2;
          i = j;
          localObject1 = localHttpURLConnection2;
          localHttpURLConnection2.setDoOutput(true);
        }
        if (paramJSONObject != null)
        {
          localHttpURLConnection1 = localHttpURLConnection2;
          i = j;
          localObject1 = localHttpURLConnection2;
          paramJSONObject = paramJSONObject.toString();
          localHttpURLConnection1 = localHttpURLConnection2;
          i = j;
          localObject1 = localHttpURLConnection2;
          OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "OneSignalRestClient: " + paramString2 + " SEND JSON: " + paramJSONObject);
          localHttpURLConnection1 = localHttpURLConnection2;
          i = j;
          localObject1 = localHttpURLConnection2;
          paramJSONObject = paramJSONObject.getBytes("UTF-8");
          localHttpURLConnection1 = localHttpURLConnection2;
          i = j;
          localObject1 = localHttpURLConnection2;
          localHttpURLConnection2.setFixedLengthStreamingMode(paramJSONObject.length);
          localHttpURLConnection1 = localHttpURLConnection2;
          i = j;
          localObject1 = localHttpURLConnection2;
          localHttpURLConnection2.getOutputStream().write(paramJSONObject);
        }
        localHttpURLConnection1 = localHttpURLConnection2;
        i = j;
        localObject1 = localHttpURLConnection2;
        paramInt = localHttpURLConnection2.getResponseCode();
        localHttpURLConnection1 = localHttpURLConnection2;
        i = paramInt;
        localObject1 = localHttpURLConnection2;
        OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "OneSignalRestClient: After con.getResponseCode  to: https://onesignal.com/api/v1/" + paramString1);
        if (paramInt != 200) {
          continue;
        }
        localHttpURLConnection1 = localHttpURLConnection2;
        i = paramInt;
        localObject1 = localHttpURLConnection2;
        OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "OneSignalRestClient: Successfully finished request to: https://onesignal.com/api/v1/" + paramString1);
        localHttpURLConnection1 = localHttpURLConnection2;
        i = paramInt;
        localObject1 = localHttpURLConnection2;
        paramJSONObject = new Scanner(localHttpURLConnection2.getInputStream(), "UTF-8");
        localHttpURLConnection1 = localHttpURLConnection2;
        i = paramInt;
        localObject1 = localHttpURLConnection2;
        if (!paramJSONObject.useDelimiter("\\A").hasNext()) {
          continue;
        }
        localHttpURLConnection1 = localHttpURLConnection2;
        i = paramInt;
        localObject1 = localHttpURLConnection2;
        paramString1 = paramJSONObject.next();
        localHttpURLConnection1 = localHttpURLConnection2;
        i = paramInt;
        localObject1 = localHttpURLConnection2;
        paramJSONObject.close();
        localHttpURLConnection1 = localHttpURLConnection2;
        i = paramInt;
        localObject1 = localHttpURLConnection2;
        OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, paramString2 + " RECEIVED JSON: " + paramString1);
        localHttpURLConnection1 = localHttpURLConnection2;
        i = paramInt;
        localObject1 = localHttpURLConnection2;
        paramString1 = callResponseHandlerOnSuccess(paramResponseHandler, paramString1);
        paramString2 = paramString1;
        if (localHttpURLConnection2 != null)
        {
          localHttpURLConnection2.disconnect();
          paramString2 = paramString1;
        }
      }
      catch (Throwable paramString1)
      {
        localObject1 = localHttpURLConnection1;
        if ((paramString1 instanceof ConnectException)) {
          continue;
        }
        localObject1 = localHttpURLConnection1;
        if (!(paramString1 instanceof UnknownHostException)) {
          continue;
        }
        localObject1 = localHttpURLConnection1;
        OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "OneSignalRestClient: Could not send last request, device is offline. Throwable: " + paramString1.getClass().getName());
        localObject1 = localHttpURLConnection1;
        paramString1 = callResponseHandlerOnFailure(paramResponseHandler, i, null, paramString1);
        paramString2 = paramString1;
        if (localHttpURLConnection1 == null) {
          continue;
        }
        localHttpURLConnection1.disconnect();
        return paramString1;
        localObject1 = localHttpURLConnection1;
        OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "OneSignalRestClient: " + paramString2 + " Error thrown from network stack. ", paramString1);
        continue;
      }
      finally
      {
        if (localObject1 == null) {
          continue;
        }
        ((HttpURLConnection)localObject1).disconnect();
      }
      return paramString2;
      paramString1 = "";
    }
    localHttpURLConnection1 = localHttpURLConnection2;
    i = paramInt;
    localObject1 = localHttpURLConnection2;
    OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "OneSignalRestClient: Failed request to: https://onesignal.com/api/v1/" + paramString1);
    localHttpURLConnection1 = localHttpURLConnection2;
    i = paramInt;
    localObject1 = localHttpURLConnection2;
    paramJSONObject = localHttpURLConnection2.getErrorStream();
    paramString1 = paramJSONObject;
    if (paramJSONObject == null)
    {
      localHttpURLConnection1 = localHttpURLConnection2;
      i = paramInt;
      localObject1 = localHttpURLConnection2;
      paramString1 = localHttpURLConnection2.getInputStream();
    }
    if (paramString1 != null)
    {
      localHttpURLConnection1 = localHttpURLConnection2;
      i = paramInt;
      localObject1 = localHttpURLConnection2;
      paramJSONObject = new Scanner(paramString1, "UTF-8");
      localHttpURLConnection1 = localHttpURLConnection2;
      i = paramInt;
      localObject1 = localHttpURLConnection2;
      if (!paramJSONObject.useDelimiter("\\A").hasNext()) {
        break label1059;
      }
      localHttpURLConnection1 = localHttpURLConnection2;
      i = paramInt;
      localObject1 = localHttpURLConnection2;
    }
    label1059:
    for (paramString1 = paramJSONObject.next();; paramString1 = "")
    {
      localHttpURLConnection1 = localHttpURLConnection2;
      i = paramInt;
      localObject1 = localHttpURLConnection2;
      paramJSONObject.close();
      localHttpURLConnection1 = localHttpURLConnection2;
      i = paramInt;
      localObject1 = localHttpURLConnection2;
      OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "OneSignalRestClient: " + paramString2 + " RECEIVED JSON: " + paramString1);
      for (;;)
      {
        localHttpURLConnection1 = localHttpURLConnection2;
        i = paramInt;
        localObject1 = localHttpURLConnection2;
        paramString1 = callResponseHandlerOnFailure(paramResponseHandler, paramInt, paramString1, null);
        break;
        localHttpURLConnection1 = localHttpURLConnection2;
        i = paramInt;
        localObject1 = localHttpURLConnection2;
        OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "OneSignalRestClient: " + paramString2 + " HTTP Code: " + paramInt + " No response body!");
        paramString1 = (String)localObject2;
      }
    }
  }
  
  static class ResponseHandler
  {
    void onFailure(int paramInt, String paramString, Throwable paramThrowable) {}
    
    void onSuccess(String paramString) {}
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\OneSignalRestClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */