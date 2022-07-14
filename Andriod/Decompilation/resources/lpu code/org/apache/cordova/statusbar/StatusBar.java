package org.apache.cordova.statusbar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONException;

public class StatusBar
  extends CordovaPlugin
{
  private static final String TAG = "StatusBar";
  
  private void setStatusBarBackgroundColor(String paramString)
  {
    Window localWindow;
    if ((Build.VERSION.SDK_INT >= 21) && (paramString != null) && (!paramString.isEmpty()))
    {
      localWindow = this.cordova.getActivity().getWindow();
      localWindow.clearFlags(67108864);
      localWindow.addFlags(Integer.MIN_VALUE);
    }
    try
    {
      localWindow.getClass().getMethod("setStatusBarColor", new Class[] { Integer.TYPE }).invoke(localWindow, new Object[] { Integer.valueOf(Color.parseColor(paramString)) });
      return;
    }
    catch (IllegalArgumentException paramString)
    {
      LOG.e("StatusBar", "Invalid hexString argument, use f.i. '#999999'");
      return;
    }
    catch (Exception paramString)
    {
      LOG.w("StatusBar", "Method window.setStatusBarColor not found for SDK level " + Build.VERSION.SDK_INT);
    }
  }
  
  private void setStatusBarStyle(String paramString)
  {
    View localView;
    int i;
    if ((Build.VERSION.SDK_INT >= 23) && (paramString != null) && (!paramString.isEmpty()))
    {
      localView = this.cordova.getActivity().getWindow().getDecorView();
      i = localView.getSystemUiVisibility();
      if (Arrays.asList(new String[] { "default" }).contains(paramString.toLowerCase())) {
        localView.setSystemUiVisibility(i | 0x2000);
      }
    }
    else
    {
      return;
    }
    if (Arrays.asList(new String[] { "lightcontent", "blacktranslucent", "blackopaque" }).contains(paramString.toLowerCase()))
    {
      localView.setSystemUiVisibility(i & 0xDFFF);
      return;
    }
    LOG.e("StatusBar", "Invalid style, must be either 'default', 'lightcontent' or the deprecated 'blacktranslucent' and 'blackopaque'");
  }
  
  private void setStatusBarTransparent(boolean paramBoolean)
  {
    Window localWindow;
    if (Build.VERSION.SDK_INT >= 21)
    {
      localWindow = this.cordova.getActivity().getWindow();
      if (paramBoolean)
      {
        localWindow.getDecorView().setSystemUiVisibility(1280);
        localWindow.setStatusBarColor(0);
      }
    }
    else
    {
      return;
    }
    localWindow.getDecorView().setSystemUiVisibility(256);
  }
  
  public boolean execute(String paramString, final CordovaArgs paramCordovaArgs, CallbackContext paramCallbackContext)
    throws JSONException
  {
    boolean bool = false;
    LOG.v("StatusBar", "Executing action: " + paramString);
    final Window localWindow = this.cordova.getActivity().getWindow();
    if ("_ready".equals(paramString))
    {
      if ((localWindow.getAttributes().flags & 0x400) == 0) {
        bool = true;
      }
      paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, bool));
    }
    do
    {
      return true;
      if ("show".equals(paramString))
      {
        this.cordova.getActivity().runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (Build.VERSION.SDK_INT >= 19)
            {
              int i = localWindow.getDecorView().getSystemUiVisibility();
              localWindow.getDecorView().setSystemUiVisibility(i & 0xFBFF & 0xFFFFFFFB);
            }
            localWindow.clearFlags(1024);
          }
        });
        return true;
      }
      if ("hide".equals(paramString))
      {
        this.cordova.getActivity().runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (Build.VERSION.SDK_INT >= 19)
            {
              int i = localWindow.getDecorView().getSystemUiVisibility();
              localWindow.getDecorView().setSystemUiVisibility(i | 0x400 | 0x4);
            }
            localWindow.addFlags(1024);
          }
        });
        return true;
      }
      if ("backgroundColorByHexString".equals(paramString))
      {
        this.cordova.getActivity().runOnUiThread(new Runnable()
        {
          public void run()
          {
            try
            {
              StatusBar.this.setStatusBarBackgroundColor(paramCordovaArgs.getString(0));
              return;
            }
            catch (JSONException localJSONException)
            {
              LOG.e("StatusBar", "Invalid hexString argument, use f.i. '#777777'");
            }
          }
        });
        return true;
      }
      if (!"overlaysWebView".equals(paramString)) {
        break;
      }
      if (Build.VERSION.SDK_INT >= 21)
      {
        this.cordova.getActivity().runOnUiThread(new Runnable()
        {
          public void run()
          {
            try
            {
              StatusBar.this.setStatusBarTransparent(paramCordovaArgs.getBoolean(0));
              return;
            }
            catch (JSONException localJSONException)
            {
              LOG.e("StatusBar", "Invalid boolean argument");
            }
          }
        });
        return true;
      }
    } while (!paramCordovaArgs.getBoolean(0));
    return false;
    if ("styleDefault".equals(paramString))
    {
      this.cordova.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          StatusBar.this.setStatusBarStyle("default");
        }
      });
      return true;
    }
    if ("styleLightContent".equals(paramString))
    {
      this.cordova.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          StatusBar.this.setStatusBarStyle("lightcontent");
        }
      });
      return true;
    }
    if ("styleBlackTranslucent".equals(paramString))
    {
      this.cordova.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          StatusBar.this.setStatusBarStyle("blacktranslucent");
        }
      });
      return true;
    }
    if ("styleBlackOpaque".equals(paramString))
    {
      this.cordova.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          StatusBar.this.setStatusBarStyle("blackopaque");
        }
      });
      return true;
    }
    return false;
  }
  
  public void initialize(final CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView)
  {
    LOG.v("StatusBar", "StatusBar: initialization");
    super.initialize(paramCordovaInterface, paramCordovaWebView);
    this.cordova.getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        paramCordovaInterface.getActivity().getWindow().clearFlags(2048);
        StatusBar.this.setStatusBarBackgroundColor(StatusBar.this.preferences.getString("StatusBarBackgroundColor", "#000000"));
        StatusBar.this.setStatusBarStyle(StatusBar.this.preferences.getString("StatusBarStyle", "lightcontent"));
      }
    });
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\statusbar\StatusBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */