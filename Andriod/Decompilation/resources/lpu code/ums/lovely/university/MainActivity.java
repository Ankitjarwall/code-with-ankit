package ums.lovely.university;

import android.content.Intent;
import android.os.Bundle;
import org.apache.cordova.CordovaActivity;

public class MainActivity
  extends CordovaActivity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent().getExtras();
    if ((paramBundle != null) && (paramBundle.getBoolean("cdvStartInBackground", false))) {
      moveTaskToBack(true);
    }
    loadUrl(this.launchUrl);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\ums\lovely\university\MainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */