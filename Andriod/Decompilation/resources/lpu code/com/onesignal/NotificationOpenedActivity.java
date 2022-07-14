package com.onesignal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class NotificationOpenedActivity
  extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    NotificationOpenedProcessor.processFromContext(this, getIntent());
    finish();
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    NotificationOpenedProcessor.processFromContext(this, getIntent());
    finish();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\NotificationOpenedActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */