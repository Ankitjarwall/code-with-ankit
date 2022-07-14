package org.apache.cordova;

import android.annotation.SuppressLint;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.security.SecureRandom;
import org.json.JSONArray;
import org.json.JSONException;

public class CordovaBridge
{
  private static final String LOG_TAG = "CordovaBridge";
  private volatile int expectedBridgeSecret = -1;
  private NativeToJsMessageQueue jsMessageQueue;
  private PluginManager pluginManager;
  
  public CordovaBridge(PluginManager paramPluginManager, NativeToJsMessageQueue paramNativeToJsMessageQueue)
  {
    this.pluginManager = paramPluginManager;
    this.jsMessageQueue = paramNativeToJsMessageQueue;
  }
  
  private boolean verifySecret(String paramString, int paramInt)
    throws IllegalAccessException
  {
    if (!this.jsMessageQueue.isBridgeEnabled())
    {
      if (paramInt == -1) {
        LOG.d("CordovaBridge", paramString + " call made before bridge was enabled.");
      }
      for (;;)
      {
        return false;
        LOG.d("CordovaBridge", "Ignoring " + paramString + " from previous page load.");
      }
    }
    if ((this.expectedBridgeSecret < 0) || (paramInt != this.expectedBridgeSecret))
    {
      LOG.e("CordovaBridge", "Bridge access attempt with wrong secret token, possibly from malicious code. Disabling exec() bridge!");
      clearBridgeSecret();
      throw new IllegalAccessException();
    }
    return true;
  }
  
  void clearBridgeSecret()
  {
    this.expectedBridgeSecret = -1;
  }
  
  @SuppressLint({"TrulyRandom"})
  int generateBridgeSecret()
  {
    this.expectedBridgeSecret = new SecureRandom().nextInt(Integer.MAX_VALUE);
    return this.expectedBridgeSecret;
  }
  
  public boolean isSecretEstablished()
  {
    return this.expectedBridgeSecret != -1;
  }
  
  public String jsExec(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
    throws JSONException, IllegalAccessException
  {
    if (!verifySecret("exec()", paramInt)) {
      return null;
    }
    if (paramString4 == null) {
      return "@Null arguments.";
    }
    this.jsMessageQueue.setPaused(true);
    try
    {
      CordovaResourceApi.jsThread = Thread.currentThread();
      this.pluginManager.exec(paramString1, paramString2, paramString3, paramString4);
      paramString1 = this.jsMessageQueue.popAndEncode(false);
      return paramString1;
    }
    catch (Throwable paramString1)
    {
      ThrowableExtension.printStackTrace(paramString1);
      return "";
    }
    finally
    {
      this.jsMessageQueue.setPaused(false);
    }
  }
  
  public String jsRetrieveJsMessages(int paramInt, boolean paramBoolean)
    throws IllegalAccessException
  {
    if (!verifySecret("retrieveJsMessages()", paramInt)) {
      return null;
    }
    return this.jsMessageQueue.popAndEncode(paramBoolean);
  }
  
  public void jsSetNativeToJsBridgeMode(int paramInt1, int paramInt2)
    throws IllegalAccessException
  {
    if (!verifySecret("setNativeToJsBridgeMode()", paramInt1)) {
      return;
    }
    this.jsMessageQueue.setBridgeMode(paramInt2);
  }
  
  public String promptOnJsPrompt(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString3 != null) && (paramString3.length() > 3) && (paramString3.startsWith("gap:"))) {}
    int i;
    for (;;)
    {
      try
      {
        paramString1 = new JSONArray(paramString3.substring(4));
        paramString2 = jsExec(paramString1.getInt(0), paramString1.getString(1), paramString1.getString(2), paramString1.getString(3), paramString2);
        paramString1 = paramString2;
        if (paramString2 == null) {
          paramString1 = "";
        }
        return paramString1;
      }
      catch (JSONException paramString1)
      {
        ThrowableExtension.printStackTrace(paramString1);
        return "";
      }
      catch (IllegalAccessException paramString1)
      {
        ThrowableExtension.printStackTrace(paramString1);
        continue;
      }
      if ((paramString3 != null) && (paramString3.startsWith("gap_bridge_mode:"))) {
        try
        {
          jsSetNativeToJsBridgeMode(Integer.parseInt(paramString3.substring(16)), Integer.parseInt(paramString2));
          return "";
        }
        catch (NumberFormatException paramString1)
        {
          for (;;)
          {
            ThrowableExtension.printStackTrace(paramString1);
          }
        }
        catch (IllegalAccessException paramString1)
        {
          for (;;)
          {
            ThrowableExtension.printStackTrace(paramString1);
          }
        }
      }
      if ((paramString3 != null) && (paramString3.startsWith("gap_poll:")))
      {
        i = Integer.parseInt(paramString3.substring(9));
        try
        {
          paramString2 = jsRetrieveJsMessages(i, "1".equals(paramString2));
          paramString1 = paramString2;
          if (paramString2 == null) {
            return "";
          }
        }
        catch (IllegalAccessException paramString1)
        {
          ThrowableExtension.printStackTrace(paramString1);
          return "";
        }
      }
    }
    if ((paramString3 != null) && (paramString3.startsWith("gap_init:")))
    {
      if (this.pluginManager.shouldAllowBridgeAccess(paramString1))
      {
        i = Integer.parseInt(paramString3.substring(9));
        this.jsMessageQueue.setBridgeMode(i);
        i = generateBridgeSecret();
        return "" + i;
      }
      LOG.e("CordovaBridge", "gap_init called from restricted origin: " + paramString1);
      return "";
    }
    return null;
  }
  
  public void reset()
  {
    this.jsMessageQueue.reset();
    clearBridgeSecret();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\CordovaBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */