package cordova.plugin;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsRequest.Builder;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.SettingsApi;
import java.lang.reflect.Method;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestLocationAccuracy
  extends CordovaPlugin
  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ResultCallback<LocationSettingsResult>
{
  protected static final int ERROR_CANNOT_CHANGE_ACCURACY = 3;
  protected static final int ERROR_EXCEPTION = 2;
  protected static final int ERROR_GOOGLE_API_CONNECTION_FAILED = 5;
  protected static final int ERROR_INVALID_ACCURACY = 1;
  protected static final int ERROR_INVALID_ACTION = 0;
  protected static final int ERROR_USER_DISAGREED = 4;
  protected static final int REQUEST_CHECK_SETTINGS = 1;
  protected static final int REQUEST_PRIORITY_BALANCED_POWER_ACCURACY = 2;
  protected static final int REQUEST_PRIORITY_HIGH_ACCURACY = 3;
  protected static final int REQUEST_PRIORITY_LOW_POWER = 1;
  protected static final int REQUEST_PRIORITY_NO_POWER = 0;
  protected static final int SUCCESS_SETTINGS_SATISFIED = 0;
  protected static final int SUCCESS_USER_AGREED = 1;
  public static final String TAG = "RequestLocationAccuracy";
  protected CallbackContext context = null;
  protected GoogleApiAvailability googleApiAvailability;
  protected GoogleApiClient mGoogleApiClient = null;
  protected LocationRequest mLocationRequest;
  protected LocationSettingsRequest mLocationSettingsRequest;
  protected ConnectionResult permanentError = null;
  
  private boolean hasPermission(String paramString)
    throws Exception
  {
    try
    {
      boolean bool = ((Boolean)this.cordova.getClass().getMethod("hasPermission", new Class[] { paramString.getClass() }).invoke(this.cordova, new Object[] { paramString })).booleanValue();
      return bool;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      Log.w("RequestLocationAccuracy", "Cordova v7.1.4 does not support runtime permissions so defaulting to GRANTED for " + paramString);
    }
    return true;
  }
  
  private boolean isLocationAuthorized()
    throws Exception
  {
    boolean bool;
    StringBuilder localStringBuilder;
    if ((hasPermission("android.permission.ACCESS_FINE_LOCATION")) || (hasPermission("android.permission.ACCESS_COARSE_LOCATION")))
    {
      bool = true;
      localStringBuilder = new StringBuilder().append("Location permission is ");
      if (!bool) {
        break label61;
      }
    }
    label61:
    for (String str = "authorized";; str = "unauthorized")
    {
      Log.v("RequestLocationAccuracy", str);
      return bool;
      bool = false;
      break;
    }
  }
  
  protected void buildGoogleApiClient()
  {
    try
    {
      Log.i("RequestLocationAccuracy", "Building GoogleApiClient");
      this.mGoogleApiClient = new GoogleApiClient.Builder(this.cordova.getActivity()).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
      Log.i("RequestLocationAccuracy", "Connect Google API client");
      this.mGoogleApiClient.connect();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  protected void buildLocationSettingsRequest()
  {
    Log.i("RequestLocationAccuracy", "Build location settings request");
    LocationSettingsRequest.Builder localBuilder = new LocationSettingsRequest.Builder();
    localBuilder.addLocationRequest(this.mLocationRequest);
    localBuilder.setAlwaysShow(true);
    this.mLocationSettingsRequest = localBuilder.build();
  }
  
  public boolean canRequest()
    throws Exception
  {
    boolean bool = isLocationAuthorized();
    CallbackContext localCallbackContext = this.context;
    if (bool) {}
    for (int i = 1;; i = 0)
    {
      localCallbackContext.success(i);
      return true;
    }
  }
  
  protected void checkLocationSettings()
  {
    Log.i("RequestLocationAccuracy", "Check location settings");
    LocationServices.SettingsApi.checkLocationSettings(this.mGoogleApiClient, this.mLocationSettingsRequest).setResultCallback(this);
  }
  
  protected void createLocationRequest(int paramInt)
  {
    Log.i("RequestLocationAccuracy", "Create location request");
    this.mLocationRequest = new LocationRequest();
    this.mLocationRequest.setPriority(paramInt);
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    this.context = paramCallbackContext;
    try
    {
      if (paramString.equals("request")) {
        return request(paramJSONArray.getInt(0));
      }
      if (paramString.equals("canRequest")) {
        return canRequest();
      }
      handleError("Invalid action", 0);
      return false;
    }
    catch (Exception paramString)
    {
      handleError(paramString.getMessage(), 2);
    }
    return false;
  }
  
  protected void handleError(String paramString, int paramInt)
  {
    try
    {
      Log.e("RequestLocationAccuracy", paramString);
      if (this.context != null)
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("message", paramString);
        localJSONObject.put("code", paramInt);
        this.context.error(localJSONObject);
      }
      return;
    }
    catch (JSONException paramString)
    {
      Log.e("RequestLocationAccuracy", paramString.toString());
    }
  }
  
  protected void handleSuccess(String paramString, int paramInt)
  {
    try
    {
      Log.i("RequestLocationAccuracy", paramString);
      if (this.context != null)
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("message", paramString);
        localJSONObject.put("code", paramInt);
        this.context.success(localJSONObject);
      }
      return;
    }
    catch (JSONException paramString)
    {
      handleError(paramString.getMessage(), 2);
    }
  }
  
  public void initialize(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView)
  {
    super.initialize(paramCordovaInterface, paramCordovaWebView);
    try
    {
      this.googleApiAvailability = GoogleApiAvailability.getInstance();
      buildGoogleApiClient();
      return;
    }
    catch (Exception paramCordovaInterface)
    {
      handleError(paramCordovaInterface.getMessage(), 2);
    }
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    Log.i("RequestLocationAccuracy", "onActivityResult()");
    switch (paramInt1)
    {
    default: 
      return;
    }
    switch (paramInt2)
    {
    default: 
      return;
    case -1: 
      Log.i("RequestLocationAccuracy", "User agreed to make required location settings changes.");
      handleSuccess("User agreed to make required location settings changes.", 1);
      return;
    }
    handleError("User chose not to make required location settings changes.", 4);
  }
  
  public void onConnected(Bundle paramBundle)
  {
    Log.i("RequestLocationAccuracy", "Connected to GoogleApiClient");
  }
  
  public void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    this.permanentError = paramConnectionResult;
    switch (paramConnectionResult.getErrorCode())
    {
    case 12: 
    default: 
      paramConnectionResult = "Unknown reason";
    }
    for (;;)
    {
      handleError("Failed to connect to Google Play Services: ".concat(paramConnectionResult), 5);
      int i = this.googleApiAvailability.isGooglePlayServicesAvailable(this.cordova.getActivity().getApplicationContext());
      if (this.googleApiAvailability.isUserResolvableError(i))
      {
        paramConnectionResult = this.googleApiAvailability.getErrorDialog(this.cordova.getActivity(), i, 0);
        paramConnectionResult.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
          public void onCancel(DialogInterface paramAnonymousDialogInterface)
          {
            RequestLocationAccuracy.this.cordova.getActivity().finish();
          }
        });
        paramConnectionResult.show();
        this.permanentError = null;
      }
      return;
      paramConnectionResult = "One of the API components you attempted to connect to is not available.";
      continue;
      paramConnectionResult = "The connection was canceled.";
      continue;
      paramConnectionResult = "The application is misconfigured.";
      continue;
      paramConnectionResult = "An internal error occurred.";
      continue;
      paramConnectionResult = "An interrupt occurred while waiting for the connection complete.";
      continue;
      paramConnectionResult = "he client attempted to connect to the service with an invalid account name specified.";
      continue;
      paramConnectionResult = "The application is not licensed to the user.";
      continue;
      paramConnectionResult = "A network error occurred.";
      continue;
      paramConnectionResult = "Completing the connection requires some form of resolution.";
      continue;
      paramConnectionResult = "The installed version of Google Play services has been disabled on this device.";
      continue;
      paramConnectionResult = "The version of the Google Play services installed on this device is not authentic.";
      continue;
      paramConnectionResult = "Google Play services is missing on this device.";
      continue;
      paramConnectionResult = "Google Play service doesn't have one or more required permissions.";
      continue;
      paramConnectionResult = "Google Play service is currently being updated on this device.";
      continue;
      paramConnectionResult = "The installed version of Google Play services is out of date.";
      continue;
      paramConnectionResult = "The client attempted to connect to the service but the user is not signed in.";
      continue;
      paramConnectionResult = "The client attempted to connect to the service but the user is not signed in.";
      continue;
      paramConnectionResult = "The timeout was exceeded while waiting for the connection to complete.";
    }
  }
  
  public void onConnectionSuspended(int paramInt)
  {
    Log.i("RequestLocationAccuracy", "Connection suspended");
  }
  
  public void onDestroy()
  {
    Log.i("RequestLocationAccuracy", "On onDestroy");
    if (this.mGoogleApiClient != null)
    {
      super.onStop();
      Log.i("RequestLocationAccuracy", "Disconnect Google API client");
    }
    try
    {
      this.mGoogleApiClient.disconnect();
      return;
    }
    catch (Exception localException)
    {
      handleError(localException.getMessage(), 2);
    }
  }
  
  public void onResult(LocationSettingsResult paramLocationSettingsResult)
  {
    Log.i("RequestLocationAccuracy", "onResult()");
    paramLocationSettingsResult = paramLocationSettingsResult.getStatus();
    switch (paramLocationSettingsResult.getStatusCode())
    {
    default: 
      return;
    case 0: 
      Log.i("RequestLocationAccuracy", "All location settings are satisfied.");
      handleSuccess("All location settings are satisfied.", 0);
      return;
    case 6: 
      Log.i("RequestLocationAccuracy", "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");
      try
      {
        this.cordova.setActivityResultCallback(this);
        paramLocationSettingsResult.startResolutionForResult(this.cordova.getActivity(), 1);
        return;
      }
      catch (IntentSender.SendIntentException paramLocationSettingsResult)
      {
        handleError("PendingIntent unable to execute request: ".concat(paramLocationSettingsResult.getMessage()), 3);
        return;
      }
    }
    handleError("Location settings are inadequate, and cannot be fixed here. Dialog not created.", 3);
  }
  
  public void onStart()
  {
    Log.i("RequestLocationAccuracy", "On start");
    super.onStart();
  }
  
  public boolean request(int paramInt)
    throws Exception
  {
    if (this.permanentError != null)
    {
      onConnectionFailed(this.permanentError);
      return true;
    }
    if (this.mGoogleApiClient == null)
    {
      handleError("Google Play Services Client failed to initialize", 5);
      return true;
    }
    switch (paramInt)
    {
    default: 
      handleError("'" + paramInt + "' is not a valid accuracy constant", 1);
      return false;
    case 0: 
      paramInt = 105;
    }
    for (;;)
    {
      createLocationRequest(paramInt);
      buildLocationSettingsRequest();
      checkLocationSettings();
      return true;
      paramInt = 104;
      continue;
      paramInt = 102;
      continue;
      paramInt = 100;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\cordova\plugin\RequestLocationAccuracy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */