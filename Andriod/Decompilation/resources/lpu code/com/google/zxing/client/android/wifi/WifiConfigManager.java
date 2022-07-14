package com.google.zxing.client.android.wifi;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;
import com.google.zxing.client.result.WifiParsedResult;
import java.util.BitSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WifiConfigManager
  extends AsyncTask<WifiParsedResult, Object, Object>
{
  private static final Pattern HEX_DIGITS = Pattern.compile("[0-9A-Fa-f]+");
  private static final String TAG = WifiConfigManager.class.getSimpleName();
  private final WifiManager wifiManager;
  
  public WifiConfigManager(WifiManager paramWifiManager)
  {
    this.wifiManager = paramWifiManager;
  }
  
  private static WifiConfiguration changeNetworkCommon(WifiParsedResult paramWifiParsedResult)
  {
    WifiConfiguration localWifiConfiguration = new WifiConfiguration();
    localWifiConfiguration.allowedAuthAlgorithms.clear();
    localWifiConfiguration.allowedGroupCiphers.clear();
    localWifiConfiguration.allowedKeyManagement.clear();
    localWifiConfiguration.allowedPairwiseCiphers.clear();
    localWifiConfiguration.allowedProtocols.clear();
    localWifiConfiguration.SSID = quoteNonHex(paramWifiParsedResult.getSsid(), new int[0]);
    localWifiConfiguration.hiddenSSID = paramWifiParsedResult.isHidden();
    return localWifiConfiguration;
  }
  
  private static void changeNetworkUnEncrypted(WifiManager paramWifiManager, WifiParsedResult paramWifiParsedResult)
  {
    paramWifiParsedResult = changeNetworkCommon(paramWifiParsedResult);
    paramWifiParsedResult.allowedKeyManagement.set(0);
    updateNetwork(paramWifiManager, paramWifiParsedResult);
  }
  
  private static void changeNetworkWEP(WifiManager paramWifiManager, WifiParsedResult paramWifiParsedResult)
  {
    WifiConfiguration localWifiConfiguration = changeNetworkCommon(paramWifiParsedResult);
    localWifiConfiguration.wepKeys[0] = quoteNonHex(paramWifiParsedResult.getPassword(), new int[] { 10, 26, 58 });
    localWifiConfiguration.wepTxKeyIndex = 0;
    localWifiConfiguration.allowedAuthAlgorithms.set(1);
    localWifiConfiguration.allowedKeyManagement.set(0);
    localWifiConfiguration.allowedGroupCiphers.set(2);
    localWifiConfiguration.allowedGroupCiphers.set(3);
    localWifiConfiguration.allowedGroupCiphers.set(0);
    localWifiConfiguration.allowedGroupCiphers.set(1);
    updateNetwork(paramWifiManager, localWifiConfiguration);
  }
  
  private static void changeNetworkWPA(WifiManager paramWifiManager, WifiParsedResult paramWifiParsedResult)
  {
    WifiConfiguration localWifiConfiguration = changeNetworkCommon(paramWifiParsedResult);
    localWifiConfiguration.preSharedKey = quoteNonHex(paramWifiParsedResult.getPassword(), new int[] { 64 });
    localWifiConfiguration.allowedAuthAlgorithms.set(0);
    localWifiConfiguration.allowedProtocols.set(0);
    localWifiConfiguration.allowedProtocols.set(1);
    localWifiConfiguration.allowedKeyManagement.set(1);
    localWifiConfiguration.allowedKeyManagement.set(2);
    localWifiConfiguration.allowedPairwiseCiphers.set(1);
    localWifiConfiguration.allowedPairwiseCiphers.set(2);
    localWifiConfiguration.allowedGroupCiphers.set(2);
    localWifiConfiguration.allowedGroupCiphers.set(3);
    updateNetwork(paramWifiManager, localWifiConfiguration);
  }
  
  private static String convertToQuotedString(String paramString)
  {
    String str;
    if ((paramString == null) || (paramString.isEmpty())) {
      str = null;
    }
    do
    {
      return str;
      if (paramString.charAt(0) != '"') {
        break;
      }
      str = paramString;
    } while (paramString.charAt(paramString.length() - 1) == '"');
    return '"' + paramString + '"';
  }
  
  private static Integer findNetworkInExistingConfig(WifiManager paramWifiManager, String paramString)
  {
    paramWifiManager = paramWifiManager.getConfiguredNetworks();
    if (paramWifiManager != null)
    {
      paramWifiManager = paramWifiManager.iterator();
      while (paramWifiManager.hasNext())
      {
        WifiConfiguration localWifiConfiguration = (WifiConfiguration)paramWifiManager.next();
        String str = localWifiConfiguration.SSID;
        if ((str != null) && (str.equals(paramString))) {
          return Integer.valueOf(localWifiConfiguration.networkId);
        }
      }
    }
    return null;
  }
  
  private static boolean isHexOfLength(CharSequence paramCharSequence, int... paramVarArgs)
  {
    boolean bool2 = true;
    boolean bool1;
    if ((paramCharSequence == null) || (!HEX_DIGITS.matcher(paramCharSequence).matches())) {
      bool1 = false;
    }
    do
    {
      return bool1;
      bool1 = bool2;
    } while (paramVarArgs.length == 0);
    int j = paramVarArgs.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label72;
      }
      int k = paramVarArgs[i];
      bool1 = bool2;
      if (paramCharSequence.length() == k) {
        break;
      }
      i += 1;
    }
    label72:
    return false;
  }
  
  private static String quoteNonHex(String paramString, int... paramVarArgs)
  {
    if (isHexOfLength(paramString, paramVarArgs)) {
      return paramString;
    }
    return convertToQuotedString(paramString);
  }
  
  private static void updateNetwork(WifiManager paramWifiManager, WifiConfiguration paramWifiConfiguration)
  {
    Integer localInteger = findNetworkInExistingConfig(paramWifiManager, paramWifiConfiguration.SSID);
    if (localInteger != null)
    {
      Log.i(TAG, "Removing old configuration for network " + paramWifiConfiguration.SSID);
      paramWifiManager.removeNetwork(localInteger.intValue());
      paramWifiManager.saveConfiguration();
    }
    int i = paramWifiManager.addNetwork(paramWifiConfiguration);
    if (i >= 0)
    {
      if (paramWifiManager.enableNetwork(i, true))
      {
        Log.i(TAG, "Associating to network " + paramWifiConfiguration.SSID);
        paramWifiManager.saveConfiguration();
        return;
      }
      Log.w(TAG, "Failed to enable network " + paramWifiConfiguration.SSID);
      return;
    }
    Log.w(TAG, "Unable to add network " + paramWifiConfiguration.SSID);
  }
  
  protected Object doInBackground(WifiParsedResult... paramVarArgs)
  {
    paramVarArgs = paramVarArgs[0];
    int i;
    if (!this.wifiManager.isWifiEnabled())
    {
      Log.i(TAG, "Enabling wi-fi...");
      if (this.wifiManager.setWifiEnabled(true))
      {
        Log.i(TAG, "Wi-fi enabled");
        i = 0;
      }
    }
    for (;;)
    {
      if (!this.wifiManager.isWifiEnabled())
      {
        if (i >= 10)
        {
          Log.i(TAG, "Took too long to enable wi-fi, quitting");
          label70:
          return null;
          Log.w(TAG, "Wi-fi could not be enabled!");
          return null;
        }
        Log.i(TAG, "Still waiting for wi-fi to enable...");
      }
      try
      {
        Thread.sleep(1000L);
        i += 1;
        continue;
        String str = paramVarArgs.getNetworkEncryption();
        NetworkType localNetworkType;
        try
        {
          localNetworkType = NetworkType.forIntentValue(str);
          if (localNetworkType == NetworkType.NO_PASSWORD)
          {
            changeNetworkUnEncrypted(this.wifiManager, paramVarArgs);
            return null;
          }
        }
        catch (IllegalArgumentException paramVarArgs)
        {
          Log.w(TAG, "Bad network type; see NetworkType values: " + str);
          return null;
        }
        str = paramVarArgs.getPassword();
        if ((str == null) || (str.isEmpty())) {
          break label70;
        }
        if (localNetworkType == NetworkType.WEP)
        {
          changeNetworkWEP(this.wifiManager, paramVarArgs);
          return null;
        }
        if (localNetworkType != NetworkType.WPA) {
          break label70;
        }
        changeNetworkWPA(this.wifiManager, paramVarArgs);
        return null;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\wifi\WifiConfigManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */