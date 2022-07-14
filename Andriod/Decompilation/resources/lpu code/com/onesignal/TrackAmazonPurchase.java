package com.onesignal;

import android.content.Context;
import com.amazon.device.iap.PurchasingListener;
import com.amazon.device.iap.PurchasingService;
import com.amazon.device.iap.model.Product;
import com.amazon.device.iap.model.ProductDataResponse;
import com.amazon.device.iap.model.PurchaseResponse;
import com.amazon.device.iap.model.PurchaseResponse.RequestStatus;
import com.amazon.device.iap.model.PurchaseUpdatesResponse;
import com.amazon.device.iap.model.Receipt;
import com.amazon.device.iap.model.RequestId;
import com.amazon.device.iap.model.UserData;
import com.amazon.device.iap.model.UserDataResponse;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

class TrackAmazonPurchase
{
  private boolean canTrack = false;
  private Context context;
  private Field listenerHandlerField;
  private Object listenerHandlerObject;
  private OSPurchasingListener osPurchasingListener;
  
  TrackAmazonPurchase(Context paramContext)
  {
    this.context = paramContext;
    try
    {
      paramContext = Class.forName("com.amazon.device.iap.internal.d");
      this.listenerHandlerObject = paramContext.getMethod("d", new Class[0]).invoke(null, new Object[0]);
      this.listenerHandlerField = paramContext.getDeclaredField("f");
      this.listenerHandlerField.setAccessible(true);
      this.osPurchasingListener = new OSPurchasingListener(null);
      this.osPurchasingListener.orgPurchasingListener = ((PurchasingListener)this.listenerHandlerField.get(this.listenerHandlerObject));
      this.canTrack = true;
      setListener();
      return;
    }
    catch (Throwable paramContext)
    {
      OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error adding Amazon IAP listener.", paramContext);
    }
  }
  
  private void setListener()
  {
    PurchasingService.registerListener(this.context, this.osPurchasingListener);
  }
  
  void checkListener()
  {
    if (!this.canTrack) {}
    for (;;)
    {
      return;
      try
      {
        PurchasingListener localPurchasingListener = (PurchasingListener)this.listenerHandlerField.get(this.listenerHandlerObject);
        if (localPurchasingListener != this.osPurchasingListener)
        {
          this.osPurchasingListener.orgPurchasingListener = localPurchasingListener;
          setListener();
          return;
        }
      }
      catch (Throwable localThrowable) {}
    }
  }
  
  private class OSPurchasingListener
    implements PurchasingListener
  {
    private String currentMarket;
    private RequestId lastRequestId;
    PurchasingListener orgPurchasingListener;
    
    private OSPurchasingListener() {}
    
    private String marketToCurrencyCode(String paramString)
    {
      int i = -1;
      switch (paramString.hashCode())
      {
      }
      for (;;)
      {
        switch (i)
        {
        default: 
          return "";
          if (paramString.equals("US"))
          {
            i = 0;
            continue;
            if (paramString.equals("GB"))
            {
              i = 1;
              continue;
              if (paramString.equals("DE"))
              {
                i = 2;
                continue;
                if (paramString.equals("FR"))
                {
                  i = 3;
                  continue;
                  if (paramString.equals("ES"))
                  {
                    i = 4;
                    continue;
                    if (paramString.equals("IT"))
                    {
                      i = 5;
                      continue;
                      if (paramString.equals("JP"))
                      {
                        i = 6;
                        continue;
                        if (paramString.equals("CA"))
                        {
                          i = 7;
                          continue;
                          if (paramString.equals("BR"))
                          {
                            i = 8;
                            continue;
                            if (paramString.equals("AU")) {
                              i = 9;
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
          break;
        }
      }
      return "USD";
      return "GBP";
      return "EUR";
      return "JPY";
      return "CDN";
      return "BRL";
      return "AUD";
    }
    
    public void onProductDataResponse(ProductDataResponse paramProductDataResponse)
    {
      if ((this.lastRequestId != null) && (this.lastRequestId.toString().equals(paramProductDataResponse.getRequestId().toString())))
      {
        try
        {
          switch (TrackAmazonPurchase.1.$SwitchMap$com$amazon$device$iap$model$ProductDataResponse$RequestStatus[paramProductDataResponse.getRequestStatus().ordinal()])
          {
          case 1: 
            JSONArray localJSONArray = new JSONArray();
            Map localMap = paramProductDataResponse.getProductData();
            Iterator localIterator = localMap.keySet().iterator();
            while (localIterator.hasNext())
            {
              paramProductDataResponse = (Product)localMap.get((String)localIterator.next());
              JSONObject localJSONObject = new JSONObject();
              localJSONObject.put("sku", paramProductDataResponse.getSku());
              localJSONObject.put("iso", marketToCurrencyCode(this.currentMarket));
              String str = paramProductDataResponse.getPrice();
              paramProductDataResponse = str;
              if (!str.matches("^[0-9]")) {
                paramProductDataResponse = str.substring(1);
              }
              localJSONObject.put("amount", paramProductDataResponse);
              localJSONArray.put(localJSONObject);
            }
            OneSignal.sendPurchases(localJSONArray, false, null);
          }
        }
        catch (Throwable paramProductDataResponse)
        {
          ThrowableExtension.printStackTrace(paramProductDataResponse);
          return;
        }
        return;
      }
      if (this.orgPurchasingListener != null)
      {
        this.orgPurchasingListener.onProductDataResponse(paramProductDataResponse);
        return;
      }
    }
    
    public void onPurchaseResponse(PurchaseResponse paramPurchaseResponse)
    {
      try
      {
        if (paramPurchaseResponse.getRequestStatus() == PurchaseResponse.RequestStatus.SUCCESSFUL)
        {
          this.currentMarket = paramPurchaseResponse.getUserData().getMarketplace();
          HashSet localHashSet = new HashSet();
          localHashSet.add(paramPurchaseResponse.getReceipt().getSku());
          this.lastRequestId = PurchasingService.getProductData(localHashSet);
        }
        if (this.orgPurchasingListener != null) {
          this.orgPurchasingListener.onPurchaseResponse(paramPurchaseResponse);
        }
        return;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          ThrowableExtension.printStackTrace(localThrowable);
        }
      }
    }
    
    public void onPurchaseUpdatesResponse(PurchaseUpdatesResponse paramPurchaseUpdatesResponse)
    {
      if (this.orgPurchasingListener != null) {
        this.orgPurchasingListener.onPurchaseUpdatesResponse(paramPurchaseUpdatesResponse);
      }
    }
    
    public void onUserDataResponse(UserDataResponse paramUserDataResponse)
    {
      if (this.orgPurchasingListener != null) {
        this.orgPurchasingListener.onUserDataResponse(paramUserDataResponse);
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\TrackAmazonPurchase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */