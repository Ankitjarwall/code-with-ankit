package com.google.zxing.client.android.result;

import android.app.Activity;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import barcodescanner.xservices.nl.barcodescanner.R.string;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.wifi.WifiConfigManager;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.WifiParsedResult;

public final class WifiResultHandler
  extends ResultHandler
{
  private static final String TAG = WifiResultHandler.class.getSimpleName();
  private final CaptureActivity parent;
  
  public WifiResultHandler(CaptureActivity paramCaptureActivity, ParsedResult paramParsedResult)
  {
    super(paramCaptureActivity, paramParsedResult);
    this.parent = paramCaptureActivity;
  }
  
  public int getButtonCount()
  {
    return 1;
  }
  
  public int getButtonText(int paramInt)
  {
    return R.string.button_wifi;
  }
  
  public CharSequence getDisplayContents()
  {
    WifiParsedResult localWifiParsedResult = (WifiParsedResult)getResult();
    return localWifiParsedResult.getSsid() + " (" + localWifiParsedResult.getNetworkEncryption() + ')';
  }
  
  public int getDisplayTitle()
  {
    return R.string.result_wifi;
  }
  
  public void handleButtonPress(int paramInt)
  {
    WifiParsedResult localWifiParsedResult;
    WifiManager localWifiManager;
    if (paramInt == 0)
    {
      localWifiParsedResult = (WifiParsedResult)getResult();
      localWifiManager = (WifiManager)getActivity().getSystemService("wifi");
      if (localWifiManager == null) {
        Log.w(TAG, "No WifiManager available from device");
      }
    }
    else
    {
      return;
    }
    final Activity localActivity = getActivity();
    localActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(localActivity.getApplicationContext(), R.string.wifi_changing_network, 0).show();
      }
    });
    new WifiConfigManager(localWifiManager).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new WifiParsedResult[] { localWifiParsedResult });
    this.parent.restartPreviewAfterDelay(0L);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\WifiResultHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */