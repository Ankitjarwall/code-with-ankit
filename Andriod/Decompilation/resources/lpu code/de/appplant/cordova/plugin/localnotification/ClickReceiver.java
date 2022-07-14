package de.appplant.cordova.plugin.localnotification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import de.appplant.cordova.plugin.notification.Notification;
import de.appplant.cordova.plugin.notification.Options;
import de.appplant.cordova.plugin.notification.receiver.AbstractClickReceiver;
import org.json.JSONException;
import org.json.JSONObject;

public class ClickReceiver
  extends AbstractClickReceiver
{
  private boolean isLast()
  {
    return getIntent().getBooleanExtra("NOTIFICATION_LAST", false);
  }
  
  private void launchAppIf()
  {
    if (!getIntent().getBooleanExtra("NOTIFICATION_LAUNCH", true)) {
      return;
    }
    launchApp();
  }
  
  private void setTextInput(String paramString, JSONObject paramJSONObject)
  {
    Bundle localBundle = RemoteInput.getResultsFromIntent(getIntent());
    if (localBundle == null) {
      return;
    }
    try
    {
      paramJSONObject.put("text", localBundle.getString(paramString));
      return;
    }
    catch (JSONException paramString)
    {
      ThrowableExtension.printStackTrace(paramString);
    }
  }
  
  public void onClick(Notification paramNotification, Bundle paramBundle)
  {
    paramBundle = getAction();
    JSONObject localJSONObject = new JSONObject();
    setTextInput(paramBundle, localJSONObject);
    launchAppIf();
    LocalNotification.fireEvent(paramBundle, paramNotification, localJSONObject);
    if (paramNotification.getOptions().isSticky().booleanValue()) {
      return;
    }
    if (isLast())
    {
      paramNotification.cancel();
      return;
    }
    paramNotification.clear();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\localnotification\ClickReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */