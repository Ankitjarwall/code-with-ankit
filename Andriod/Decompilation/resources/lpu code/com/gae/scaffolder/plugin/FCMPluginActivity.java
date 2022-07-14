package com.gae.scaffolder.plugin;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FCMPluginActivity
  extends Activity
{
  private static String TAG = "FCMPlugin";
  
  private void forceMainActivityReload()
  {
    startActivity(getPackageManager().getLaunchIntentForPackage(getApplicationContext().getPackageName()));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Log.d(TAG, "==> FCMPluginActivity onCreate");
    paramBundle = new HashMap();
    if (getIntent().getExtras() != null)
    {
      Log.d(TAG, "==> USER TAPPED NOTFICATION");
      paramBundle.put("wasTapped", Boolean.valueOf(true));
      Iterator localIterator = getIntent().getExtras().keySet().iterator();
      while (localIterator.hasNext())
      {
        String str1 = (String)localIterator.next();
        String str2 = getIntent().getExtras().getString(str1);
        Log.d(TAG, "\tKey: " + str1 + " Value: " + str2);
        paramBundle.put(str1, str2);
      }
    }
    FCMPlugin.sendPushPayload(paramBundle);
    finish();
    forceMainActivityReload();
  }
  
  protected void onResume()
  {
    super.onResume();
    Log.d(TAG, "==> FCMPluginActivity onResume");
    ((NotificationManager)getSystemService("notification")).cancelAll();
  }
  
  public void onStart()
  {
    super.onStart();
    Log.d(TAG, "==> FCMPluginActivity onStart");
  }
  
  public void onStop()
  {
    super.onStop();
    Log.d(TAG, "==> FCMPluginActivity onStop");
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\gae\scaffolder\plugin\FCMPluginActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */