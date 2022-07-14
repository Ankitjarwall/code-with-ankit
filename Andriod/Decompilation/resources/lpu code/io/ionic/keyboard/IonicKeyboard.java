package io.ionic.keyboard;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import java.util.concurrent.ExecutorService;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

public class IonicKeyboard
  extends CordovaPlugin
{
  private ViewTreeObserver.OnGlobalLayoutListener list;
  private View rootView;
  
  public boolean execute(String paramString, JSONArray paramJSONArray, final CallbackContext paramCallbackContext)
    throws JSONException
  {
    if ("hide".equals(paramString))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          InputMethodManager localInputMethodManager = (InputMethodManager)IonicKeyboard.this.cordova.getActivity().getSystemService("input_method");
          View localView = IonicKeyboard.this.cordova.getActivity().getCurrentFocus();
          if (localView == null)
          {
            paramCallbackContext.error("No current focus");
            return;
          }
          localInputMethodManager.hideSoftInputFromWindow(localView.getWindowToken(), 2);
          paramCallbackContext.success();
        }
      });
      return true;
    }
    if ("show".equals(paramString))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          ((InputMethodManager)IonicKeyboard.this.cordova.getActivity().getSystemService("input_method")).toggleSoftInput(0, 1);
          paramCallbackContext.success();
        }
      });
      return true;
    }
    if ("init".equals(paramString))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          Object localObject = new DisplayMetrics();
          IonicKeyboard.this.cordova.getActivity().getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
          final float f = ((DisplayMetrics)localObject).density;
          IonicKeyboard.access$002(IonicKeyboard.this, IonicKeyboard.this.cordova.getActivity().getWindow().getDecorView().findViewById(16908290).getRootView());
          IonicKeyboard.access$102(IonicKeyboard.this, new ViewTreeObserver.OnGlobalLayoutListener()
          {
            int previousHeightDiff = 0;
            
            public void onGlobalLayout()
            {
              Object localObject = new Rect();
              IonicKeyboard.this.rootView.getWindowVisibleDisplayFrame((Rect)localObject);
              int i = IonicKeyboard.this.rootView.getRootView().getHeight();
              int j = ((Rect)localObject).bottom;
              if (Build.VERSION.SDK_INT >= 21)
              {
                localObject = IonicKeyboard.this.cordova.getActivity().getWindowManager().getDefaultDisplay();
                Point localPoint = new Point();
                ((Display)localObject).getSize(localPoint);
                i = localPoint.y;
                i = (int)((i - j) / f);
                if ((i <= 100) || (i == this.previousHeightDiff)) {
                  break label182;
                }
                localObject = "S" + Integer.toString(i);
                localObject = new PluginResult(PluginResult.Status.OK, (String)localObject);
                ((PluginResult)localObject).setKeepCallback(true);
                IonicKeyboard.3.this.val$callbackContext.sendPluginResult((PluginResult)localObject);
              }
              for (;;)
              {
                this.previousHeightDiff = i;
                return;
                break;
                label182:
                if ((i != this.previousHeightDiff) && (this.previousHeightDiff - i > 100))
                {
                  localObject = new PluginResult(PluginResult.Status.OK, "H");
                  ((PluginResult)localObject).setKeepCallback(true);
                  IonicKeyboard.3.this.val$callbackContext.sendPluginResult((PluginResult)localObject);
                }
              }
            }
          });
          IonicKeyboard.this.rootView.getViewTreeObserver().addOnGlobalLayoutListener(IonicKeyboard.this.list);
          localObject = new PluginResult(PluginResult.Status.OK);
          ((PluginResult)localObject).setKeepCallback(true);
          paramCallbackContext.sendPluginResult((PluginResult)localObject);
        }
      });
      return true;
    }
    return false;
  }
  
  public void initialize(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView)
  {
    super.initialize(paramCordovaInterface, paramCordovaWebView);
  }
  
  public void onDestroy()
  {
    this.rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this.list);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\io\ionic\keyboard\IonicKeyboard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */