package org.apache.cordova.dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.widget.EditText;
import android.widget.TextView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.concurrent.ExecutorService;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Notification
  extends CordovaPlugin
{
  private static final String LOG_TAG = "Notification";
  public int confirmResult = -1;
  public ProgressDialog progressDialog = null;
  public ProgressDialog spinnerDialog = null;
  
  @SuppressLint({"NewApi"})
  private void changeTextDirection(AlertDialog.Builder paramBuilder)
  {
    int i = Build.VERSION.SDK_INT;
    paramBuilder.create();
    paramBuilder = paramBuilder.show();
    if (i >= 17) {
      ((TextView)paramBuilder.findViewById(16908299)).setTextDirection(5);
    }
  }
  
  @SuppressLint({"NewApi"})
  private AlertDialog.Builder createDialog(CordovaInterface paramCordovaInterface)
  {
    if (Build.VERSION.SDK_INT >= 11) {
      return new AlertDialog.Builder(paramCordovaInterface.getActivity(), 5);
    }
    return new AlertDialog.Builder(paramCordovaInterface.getActivity());
  }
  
  @SuppressLint({"InlinedApi"})
  private ProgressDialog createProgressDialog(CordovaInterface paramCordovaInterface)
  {
    if (Build.VERSION.SDK_INT >= 14) {
      return new ProgressDialog(paramCordovaInterface.getActivity(), 5);
    }
    return new ProgressDialog(paramCordovaInterface.getActivity());
  }
  
  public void activityStart(final String paramString1, final String paramString2)
  {
    try
    {
      if (this.spinnerDialog != null)
      {
        this.spinnerDialog.dismiss();
        this.spinnerDialog = null;
      }
      paramString1 = new Runnable()
      {
        public void run()
        {
          jdField_this.spinnerDialog = Notification.this.createProgressDialog(this.val$cordova);
          jdField_this.spinnerDialog.setTitle(paramString1);
          jdField_this.spinnerDialog.setMessage(paramString2);
          jdField_this.spinnerDialog.setCancelable(true);
          jdField_this.spinnerDialog.setIndeterminate(true);
          jdField_this.spinnerDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
          {
            public void onCancel(DialogInterface paramAnonymous2DialogInterface)
            {
              Notification.5.this.val$notification.spinnerDialog = null;
            }
          });
          jdField_this.spinnerDialog.show();
        }
      };
      this.cordova.getActivity().runOnUiThread(paramString1);
      return;
    }
    finally {}
  }
  
  public void activityStop()
  {
    try
    {
      if (this.spinnerDialog != null)
      {
        this.spinnerDialog.dismiss();
        this.spinnerDialog = null;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void alert(final String paramString1, final String paramString2, final String paramString3, final CallbackContext paramCallbackContext)
  {
    try
    {
      paramString1 = new Runnable()
      {
        public void run()
        {
          AlertDialog.Builder localBuilder = Notification.this.createDialog(this.val$cordova);
          localBuilder.setMessage(paramString1);
          localBuilder.setTitle(paramString2);
          localBuilder.setCancelable(true);
          localBuilder.setPositiveButton(paramString3, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              paramAnonymous2DialogInterface.dismiss();
              Notification.2.this.val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
            }
          });
          localBuilder.setOnCancelListener(new DialogInterface.OnCancelListener()
          {
            public void onCancel(DialogInterface paramAnonymous2DialogInterface)
            {
              paramAnonymous2DialogInterface.dismiss();
              Notification.2.this.val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
            }
          });
          Notification.this.changeTextDirection(localBuilder);
        }
      };
      this.cordova.getActivity().runOnUiThread(paramString1);
      return;
    }
    finally
    {
      paramString1 = finally;
      throw paramString1;
    }
  }
  
  public void beep(final long paramLong)
  {
    this.cordova.getThreadPool().execute(new Runnable()
    {
      public void run()
      {
        Object localObject = RingtoneManager.getDefaultUri(2);
        localObject = RingtoneManager.getRingtone(Notification.this.cordova.getActivity().getBaseContext(), (Uri)localObject);
        if (localObject != null) {
          for (long l1 = 0L; l1 < paramLong; l1 += 1L)
          {
            ((Ringtone)localObject).play();
            long l2 = 5000L;
            while ((((Ringtone)localObject).isPlaying()) && (l2 > 0L))
            {
              l2 -= 100L;
              try
              {
                Thread.sleep(100L);
              }
              catch (InterruptedException localInterruptedException)
              {
                Thread.currentThread().interrupt();
              }
            }
          }
        }
      }
    });
  }
  
  public void confirm(final String paramString1, final String paramString2, final JSONArray paramJSONArray, final CallbackContext paramCallbackContext)
  {
    try
    {
      paramString1 = new Runnable()
      {
        public void run()
        {
          AlertDialog.Builder localBuilder = Notification.this.createDialog(this.val$cordova);
          localBuilder.setMessage(paramString1);
          localBuilder.setTitle(paramString2);
          localBuilder.setCancelable(true);
          if (paramJSONArray.length() > 0) {}
          try
          {
            localBuilder.setNegativeButton(paramJSONArray.getString(0), new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                paramAnonymous2DialogInterface.dismiss();
                Notification.3.this.val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 1));
              }
            });
            if (paramJSONArray.length() <= 1) {}
          }
          catch (JSONException localJSONException2)
          {
            try
            {
              localBuilder.setNeutralButton(paramJSONArray.getString(1), new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                {
                  paramAnonymous2DialogInterface.dismiss();
                  Notification.3.this.val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 2));
                }
              });
              if (paramJSONArray.length() <= 2) {}
            }
            catch (JSONException localJSONException2)
            {
              try
              {
                for (;;)
                {
                  localBuilder.setPositiveButton(paramJSONArray.getString(2), new DialogInterface.OnClickListener()
                  {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                    {
                      paramAnonymous2DialogInterface.dismiss();
                      Notification.3.this.val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 3));
                    }
                  });
                  localBuilder.setOnCancelListener(new DialogInterface.OnCancelListener()
                  {
                    public void onCancel(DialogInterface paramAnonymous2DialogInterface)
                    {
                      paramAnonymous2DialogInterface.dismiss();
                      Notification.3.this.val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
                    }
                  });
                  Notification.this.changeTextDirection(localBuilder);
                  return;
                  localJSONException1 = localJSONException1;
                  LOG.d("Notification", "JSONException on first button.");
                }
                localJSONException2 = localJSONException2;
                LOG.d("Notification", "JSONException on second button.");
              }
              catch (JSONException localJSONException3)
              {
                for (;;)
                {
                  LOG.d("Notification", "JSONException on third button.");
                }
              }
            }
          }
        }
      };
      this.cordova.getActivity().runOnUiThread(paramString1);
      return;
    }
    finally
    {
      paramString1 = finally;
      throw paramString1;
    }
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    boolean bool = false;
    if (this.cordova.getActivity().isFinishing())
    {
      bool = true;
      return bool;
    }
    if (paramString.equals("beep")) {
      beep(paramJSONArray.getLong(0));
    }
    for (;;)
    {
      paramCallbackContext.success();
      return true;
      if (paramString.equals("alert"))
      {
        alert(paramJSONArray.getString(0), paramJSONArray.getString(1), paramJSONArray.getString(2), paramCallbackContext);
        return true;
      }
      if (paramString.equals("confirm"))
      {
        confirm(paramJSONArray.getString(0), paramJSONArray.getString(1), paramJSONArray.getJSONArray(2), paramCallbackContext);
        return true;
      }
      if (paramString.equals("prompt"))
      {
        prompt(paramJSONArray.getString(0), paramJSONArray.getString(1), paramJSONArray.getJSONArray(2), paramJSONArray.getString(3), paramCallbackContext);
        return true;
      }
      if (paramString.equals("activityStart"))
      {
        activityStart(paramJSONArray.getString(0), paramJSONArray.getString(1));
      }
      else if (paramString.equals("activityStop"))
      {
        activityStop();
      }
      else if (paramString.equals("progressStart"))
      {
        progressStart(paramJSONArray.getString(0), paramJSONArray.getString(1));
      }
      else if (paramString.equals("progressValue"))
      {
        progressValue(paramJSONArray.getInt(0));
      }
      else
      {
        if (!paramString.equals("progressStop")) {
          break;
        }
        progressStop();
      }
    }
  }
  
  public void progressStart(final String paramString1, final String paramString2)
  {
    try
    {
      if (this.progressDialog != null)
      {
        this.progressDialog.dismiss();
        this.progressDialog = null;
      }
      paramString1 = new Runnable()
      {
        public void run()
        {
          jdField_this.progressDialog = Notification.this.createProgressDialog(this.val$cordova);
          jdField_this.progressDialog.setProgressStyle(1);
          jdField_this.progressDialog.setTitle(paramString1);
          jdField_this.progressDialog.setMessage(paramString2);
          jdField_this.progressDialog.setCancelable(true);
          jdField_this.progressDialog.setMax(100);
          jdField_this.progressDialog.setProgress(0);
          jdField_this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
          {
            public void onCancel(DialogInterface paramAnonymous2DialogInterface)
            {
              Notification.6.this.val$notification.progressDialog = null;
            }
          });
          jdField_this.progressDialog.show();
        }
      };
      this.cordova.getActivity().runOnUiThread(paramString1);
      return;
    }
    finally {}
  }
  
  public void progressStop()
  {
    try
    {
      if (this.progressDialog != null)
      {
        this.progressDialog.dismiss();
        this.progressDialog = null;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void progressValue(int paramInt)
  {
    try
    {
      if (this.progressDialog != null) {
        this.progressDialog.setProgress(paramInt);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void prompt(final String paramString1, final String paramString2, final JSONArray paramJSONArray, final String paramString3, final CallbackContext paramCallbackContext)
  {
    try
    {
      paramString1 = new Runnable()
      {
        public void run()
        {
          final EditText localEditText = new EditText(this.val$cordova.getActivity());
          localEditText.setTextColor(this.val$cordova.getActivity().getResources().getColor(17170435));
          localEditText.setText(paramString3);
          AlertDialog.Builder localBuilder = Notification.this.createDialog(this.val$cordova);
          localBuilder.setMessage(paramString1);
          localBuilder.setTitle(paramString2);
          localBuilder.setCancelable(true);
          localBuilder.setView(localEditText);
          final JSONObject localJSONObject = new JSONObject();
          if (paramJSONArray.length() > 0) {}
          try
          {
            localBuilder.setNegativeButton(paramJSONArray.getString(0), new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                paramAnonymous2DialogInterface.dismiss();
                for (;;)
                {
                  try
                  {
                    localJSONObject.put("buttonIndex", 1);
                    JSONObject localJSONObject = localJSONObject;
                    if (localEditText.getText().toString().trim().length() != 0) {
                      continue;
                    }
                    paramAnonymous2DialogInterface = Notification.4.this.val$defaultText;
                    localJSONObject.put("input1", paramAnonymous2DialogInterface);
                  }
                  catch (JSONException paramAnonymous2DialogInterface)
                  {
                    LOG.d("Notification", "JSONException on first button.", paramAnonymous2DialogInterface);
                    continue;
                  }
                  Notification.4.this.val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, localJSONObject));
                  return;
                  paramAnonymous2DialogInterface = localEditText.getText();
                }
              }
            });
            if (paramJSONArray.length() <= 1) {}
          }
          catch (JSONException localJSONException2)
          {
            try
            {
              localBuilder.setNeutralButton(paramJSONArray.getString(1), new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                {
                  paramAnonymous2DialogInterface.dismiss();
                  for (;;)
                  {
                    try
                    {
                      localJSONObject.put("buttonIndex", 2);
                      JSONObject localJSONObject = localJSONObject;
                      if (localEditText.getText().toString().trim().length() != 0) {
                        continue;
                      }
                      paramAnonymous2DialogInterface = Notification.4.this.val$defaultText;
                      localJSONObject.put("input1", paramAnonymous2DialogInterface);
                    }
                    catch (JSONException paramAnonymous2DialogInterface)
                    {
                      LOG.d("Notification", "JSONException on second button.", paramAnonymous2DialogInterface);
                      continue;
                    }
                    Notification.4.this.val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, localJSONObject));
                    return;
                    paramAnonymous2DialogInterface = localEditText.getText();
                  }
                }
              });
              if (paramJSONArray.length() <= 2) {}
            }
            catch (JSONException localJSONException2)
            {
              try
              {
                for (;;)
                {
                  localBuilder.setPositiveButton(paramJSONArray.getString(2), new DialogInterface.OnClickListener()
                  {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                    {
                      paramAnonymous2DialogInterface.dismiss();
                      for (;;)
                      {
                        try
                        {
                          localJSONObject.put("buttonIndex", 3);
                          JSONObject localJSONObject = localJSONObject;
                          if (localEditText.getText().toString().trim().length() != 0) {
                            continue;
                          }
                          paramAnonymous2DialogInterface = Notification.4.this.val$defaultText;
                          localJSONObject.put("input1", paramAnonymous2DialogInterface);
                        }
                        catch (JSONException paramAnonymous2DialogInterface)
                        {
                          LOG.d("Notification", "JSONException on third button.", paramAnonymous2DialogInterface);
                          continue;
                        }
                        Notification.4.this.val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, localJSONObject));
                        return;
                        paramAnonymous2DialogInterface = localEditText.getText();
                      }
                    }
                  });
                  localBuilder.setOnCancelListener(new DialogInterface.OnCancelListener()
                  {
                    public void onCancel(DialogInterface paramAnonymous2DialogInterface)
                    {
                      paramAnonymous2DialogInterface.dismiss();
                      for (;;)
                      {
                        try
                        {
                          localJSONObject.put("buttonIndex", 0);
                          JSONObject localJSONObject = localJSONObject;
                          if (localEditText.getText().toString().trim().length() != 0) {
                            continue;
                          }
                          paramAnonymous2DialogInterface = Notification.4.this.val$defaultText;
                          localJSONObject.put("input1", paramAnonymous2DialogInterface);
                        }
                        catch (JSONException paramAnonymous2DialogInterface)
                        {
                          ThrowableExtension.printStackTrace(paramAnonymous2DialogInterface);
                          continue;
                        }
                        Notification.4.this.val$callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, localJSONObject));
                        return;
                        paramAnonymous2DialogInterface = localEditText.getText();
                      }
                    }
                  });
                  Notification.this.changeTextDirection(localBuilder);
                  return;
                  localJSONException1 = localJSONException1;
                  LOG.d("Notification", "JSONException on first button.");
                }
                localJSONException2 = localJSONException2;
                LOG.d("Notification", "JSONException on second button.");
              }
              catch (JSONException localJSONException3)
              {
                for (;;)
                {
                  LOG.d("Notification", "JSONException on third button.");
                }
              }
            }
          }
        }
      };
      this.cordova.getActivity().runOnUiThread(paramString1);
      return;
    }
    finally
    {
      paramString1 = finally;
      throw paramString1;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\dialogs\Notification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */