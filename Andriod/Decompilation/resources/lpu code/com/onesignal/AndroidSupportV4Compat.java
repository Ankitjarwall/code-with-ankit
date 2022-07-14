package com.onesignal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Process;
import android.support.annotation.NonNull;
import android.util.Log;

class AndroidSupportV4Compat
{
  static class ActivityCompat
  {
    static void requestPermissions(@NonNull Activity paramActivity, @NonNull String[] paramArrayOfString, int paramInt)
    {
      AndroidSupportV4Compat.ActivityCompatApi23.requestPermissions(paramActivity, paramArrayOfString, paramInt);
    }
  }
  
  @TargetApi(23)
  static class ActivityCompatApi23
  {
    static void requestPermissions(Activity paramActivity, String[] paramArrayOfString, int paramInt)
    {
      if ((paramActivity instanceof AndroidSupportV4Compat.RequestPermissionsRequestCodeValidator)) {
        ((AndroidSupportV4Compat.RequestPermissionsRequestCodeValidator)paramActivity).validateRequestPermissionsRequestCode(paramInt);
      }
      paramActivity.requestPermissions(paramArrayOfString, paramInt);
    }
  }
  
  static class ContextCompat
  {
    static int checkSelfPermission(@NonNull Context paramContext, @NonNull String paramString)
    {
      try
      {
        int i = paramContext.checkPermission(paramString, Process.myPid(), Process.myUid());
        return i;
      }
      catch (Throwable paramContext)
      {
        Log.e("OneSignal", "checkSelfPermission failed, returning PERMISSION_DENIED");
      }
      return -1;
    }
    
    static int getColor(Context paramContext, int paramInt)
    {
      if (Build.VERSION.SDK_INT > 22) {
        return paramContext.getColor(paramInt);
      }
      return paramContext.getResources().getColor(paramInt);
    }
  }
  
  static abstract interface RequestPermissionsRequestCodeValidator
  {
    public abstract void validateRequestPermissionsRequestCode(int paramInt);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\AndroidSupportV4Compat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */