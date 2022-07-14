package com.onesignal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class TrackGooglePurchase
{
  private static Class<?> IInAppBillingServiceClass;
  private static int iapEnabled = -99;
  private Context appContext;
  private Method getPurchasesMethod;
  private Method getSkuDetailsMethod;
  private boolean isWaitingForPurchasesRequest = false;
  private Object mIInAppBillingService;
  private ServiceConnection mServiceConn;
  private boolean newAsExisting = true;
  private ArrayList<String> purchaseTokens;
  
  TrackGooglePurchase(Context paramContext)
  {
    this.appContext = paramContext;
    this.purchaseTokens = new ArrayList();
    for (;;)
    {
      try
      {
        paramContext = new JSONArray(OneSignalPrefs.getString("GTPlayerPurchases", "purchaseTokens", "[]"));
        int i = 0;
        if (i < paramContext.length())
        {
          this.purchaseTokens.add(paramContext.get(i).toString());
          i += 1;
          continue;
        }
        if (paramContext.length() != 0) {
          continue;
        }
        this.newAsExisting = bool;
        if (this.newAsExisting) {
          this.newAsExisting = OneSignalPrefs.getBool("GTPlayerPurchases", "ExistingPurchases", true);
        }
      }
      catch (JSONException paramContext)
      {
        ThrowableExtension.printStackTrace(paramContext);
        continue;
      }
      trackIAP();
      return;
      bool = false;
    }
  }
  
  static boolean CanTrack(Context paramContext)
  {
    boolean bool = false;
    if (iapEnabled == -99) {
      iapEnabled = paramContext.checkCallingOrSelfPermission("com.android.vending.BILLING");
    }
    try
    {
      if (iapEnabled == 0) {
        IInAppBillingServiceClass = Class.forName("com.android.vending.billing.IInAppBillingService");
      }
      if (iapEnabled == 0) {
        bool = true;
      }
      return bool;
    }
    catch (Throwable paramContext)
    {
      iapEnabled = 0;
    }
    return false;
  }
  
  private void QueryBoughtItems()
  {
    if (this.isWaitingForPurchasesRequest) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        TrackGooglePurchase.access$402(TrackGooglePurchase.this, true);
        try
        {
          if (TrackGooglePurchase.this.getPurchasesMethod == null)
          {
            TrackGooglePurchase.access$502(TrackGooglePurchase.this, TrackGooglePurchase.getGetPurchasesMethod(TrackGooglePurchase.IInAppBillingServiceClass));
            TrackGooglePurchase.this.getPurchasesMethod.setAccessible(true);
          }
          localObject = (Bundle)TrackGooglePurchase.this.getPurchasesMethod.invoke(TrackGooglePurchase.this.mIInAppBillingService, new Object[] { Integer.valueOf(3), TrackGooglePurchase.this.appContext.getPackageName(), "inapp", null });
          if (((Bundle)localObject).getInt("RESPONSE_CODE") != 0) {
            break label249;
          }
          localArrayList1 = new ArrayList();
          localArrayList2 = new ArrayList();
          localArrayList3 = ((Bundle)localObject).getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
          localObject = ((Bundle)localObject).getStringArrayList("INAPP_PURCHASE_DATA_LIST");
          i = 0;
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            Object localObject;
            ArrayList localArrayList1;
            ArrayList localArrayList2;
            ArrayList localArrayList3;
            int i;
            String str2;
            String str1;
            label249:
            ThrowableExtension.printStackTrace(localThrowable);
            continue;
            i += 1;
          }
        }
        if (i < ((ArrayList)localObject).size())
        {
          str2 = (String)((ArrayList)localObject).get(i);
          str1 = (String)localArrayList3.get(i);
          str2 = new JSONObject(str2).getString("purchaseToken");
          if ((!TrackGooglePurchase.this.purchaseTokens.contains(str2)) && (!localArrayList2.contains(str2)))
          {
            localArrayList2.add(str2);
            localArrayList1.add(str1);
          }
        }
        else
        {
          if (localArrayList1.size() > 0) {
            TrackGooglePurchase.this.sendPurchases(localArrayList1, localArrayList2);
          }
          for (;;)
          {
            TrackGooglePurchase.access$402(TrackGooglePurchase.this, false);
            return;
            if (((ArrayList)localObject).size() == 0)
            {
              TrackGooglePurchase.access$1102(TrackGooglePurchase.this, false);
              OneSignalPrefs.saveBool("GTPlayerPurchases", "ExistingPurchases", false);
            }
          }
        }
      }
    }).start();
  }
  
  private static Method getAsInterfaceMethod(Class paramClass)
  {
    paramClass = paramClass.getMethods();
    int j = paramClass.length;
    int i = 0;
    while (i < j)
    {
      Method localMethod = paramClass[i];
      Class[] arrayOfClass = localMethod.getParameterTypes();
      if ((arrayOfClass.length == 1) && (arrayOfClass[0] == IBinder.class)) {
        return localMethod;
      }
      i += 1;
    }
    return null;
  }
  
  private static Method getGetPurchasesMethod(Class paramClass)
  {
    paramClass = paramClass.getMethods();
    int j = paramClass.length;
    int i = 0;
    while (i < j)
    {
      Method localMethod = paramClass[i];
      Class[] arrayOfClass = localMethod.getParameterTypes();
      if ((arrayOfClass.length == 4) && (arrayOfClass[0] == Integer.TYPE) && (arrayOfClass[1] == String.class) && (arrayOfClass[2] == String.class) && (arrayOfClass[3] == String.class)) {
        return localMethod;
      }
      i += 1;
    }
    return null;
  }
  
  private static Method getGetSkuDetailsMethod(Class paramClass)
  {
    paramClass = paramClass.getMethods();
    int j = paramClass.length;
    int i = 0;
    while (i < j)
    {
      Method localMethod = paramClass[i];
      Class[] arrayOfClass = localMethod.getParameterTypes();
      Class localClass = localMethod.getReturnType();
      if ((arrayOfClass.length == 4) && (arrayOfClass[0] == Integer.TYPE) && (arrayOfClass[1] == String.class) && (arrayOfClass[2] == String.class) && (arrayOfClass[3] == Bundle.class) && (localClass == Bundle.class)) {
        return localMethod;
      }
      i += 1;
    }
    return null;
  }
  
  private void sendPurchases(ArrayList<String> paramArrayList1, final ArrayList<String> paramArrayList2)
  {
    Object localObject1;
    Object localObject2;
    Object localObject3;
    try
    {
      if (this.getSkuDetailsMethod == null)
      {
        this.getSkuDetailsMethod = getGetSkuDetailsMethod(IInAppBillingServiceClass);
        this.getSkuDetailsMethod.setAccessible(true);
      }
      localObject1 = new Bundle();
      ((Bundle)localObject1).putStringArrayList("ITEM_ID_LIST", paramArrayList1);
      localObject1 = (Bundle)this.getSkuDetailsMethod.invoke(this.mIInAppBillingService, new Object[] { Integer.valueOf(3), this.appContext.getPackageName(), "inapp", localObject1 });
      if (((Bundle)localObject1).getInt("RESPONSE_CODE") == 0)
      {
        localObject2 = ((Bundle)localObject1).getStringArrayList("DETAILS_LIST");
        localObject1 = new HashMap();
        localObject2 = ((ArrayList)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = new JSONObject((String)((Iterator)localObject2).next());
          String str = ((JSONObject)localObject3).getString("productId");
          BigDecimal localBigDecimal = new BigDecimal(((JSONObject)localObject3).getString("price_amount_micros")).divide(new BigDecimal(1000000));
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("sku", str);
          localJSONObject.put("iso", ((JSONObject)localObject3).getString("price_currency_code"));
          localJSONObject.put("amount", localBigDecimal.toString());
          ((Map)localObject1).put(str, localJSONObject);
        }
      }
      return;
    }
    catch (Throwable paramArrayList1)
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Failed to track IAP purchases", paramArrayList1);
    }
    do
    {
      localObject2 = new JSONArray();
      paramArrayList1 = paramArrayList1.iterator();
      while (paramArrayList1.hasNext())
      {
        localObject3 = (String)paramArrayList1.next();
        if (((Map)localObject1).containsKey(localObject3)) {
          ((JSONArray)localObject2).put(((Map)localObject1).get(localObject3));
        }
      }
    } while (((JSONArray)localObject2).length() <= 0);
    paramArrayList1 = new OneSignalRestClient.ResponseHandler()
    {
      public void onFailure(int paramAnonymousInt, JSONObject paramAnonymousJSONObject, Throwable paramAnonymousThrowable)
      {
        OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "HTTP sendPurchases failed to send.", paramAnonymousThrowable);
        TrackGooglePurchase.access$402(TrackGooglePurchase.this, false);
      }
      
      public void onSuccess(String paramAnonymousString)
      {
        TrackGooglePurchase.this.purchaseTokens.addAll(paramArrayList2);
        OneSignalPrefs.saveString("GTPlayerPurchases", "purchaseTokens", TrackGooglePurchase.this.purchaseTokens.toString());
        OneSignalPrefs.saveBool("GTPlayerPurchases", "ExistingPurchases", true);
        TrackGooglePurchase.access$1102(TrackGooglePurchase.this, false);
        TrackGooglePurchase.access$402(TrackGooglePurchase.this, false);
      }
    };
    OneSignal.sendPurchases((JSONArray)localObject2, this.newAsExisting, paramArrayList1);
  }
  
  void trackIAP()
  {
    if (this.mServiceConn == null)
    {
      this.mServiceConn = new ServiceConnection()
      {
        public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
        {
          try
          {
            paramAnonymousComponentName = TrackGooglePurchase.getAsInterfaceMethod(Class.forName("com.android.vending.billing.IInAppBillingService$Stub"));
            paramAnonymousComponentName.setAccessible(true);
            TrackGooglePurchase.access$102(TrackGooglePurchase.this, paramAnonymousComponentName.invoke(null, new Object[] { paramAnonymousIBinder }));
            TrackGooglePurchase.this.QueryBoughtItems();
            return;
          }
          catch (Throwable paramAnonymousComponentName)
          {
            ThrowableExtension.printStackTrace(paramAnonymousComponentName);
          }
        }
        
        public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
        {
          TrackGooglePurchase.access$002(-99);
          TrackGooglePurchase.access$102(TrackGooglePurchase.this, null);
        }
      };
      localIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
      localIntent.setPackage("com.android.vending");
      this.appContext.bindService(localIntent, this.mServiceConn, 1);
    }
    while (this.mIInAppBillingService == null)
    {
      Intent localIntent;
      return;
    }
    QueryBoughtItems();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\TrackGooglePurchase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */