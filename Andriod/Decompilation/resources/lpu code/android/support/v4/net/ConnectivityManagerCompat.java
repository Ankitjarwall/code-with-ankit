package android.support.v4.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.RestrictTo;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ConnectivityManagerCompat
{
  public static final int RESTRICT_BACKGROUND_STATUS_DISABLED = 1;
  public static final int RESTRICT_BACKGROUND_STATUS_ENABLED = 3;
  public static final int RESTRICT_BACKGROUND_STATUS_WHITELISTED = 2;
  
  @Nullable
  @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
  public static NetworkInfo getNetworkInfoFromBroadcast(@NonNull ConnectivityManager paramConnectivityManager, @NonNull Intent paramIntent)
  {
    paramIntent = (NetworkInfo)paramIntent.getParcelableExtra("networkInfo");
    if (paramIntent != null) {
      return paramConnectivityManager.getNetworkInfo(paramIntent.getType());
    }
    return null;
  }
  
  public static int getRestrictBackgroundStatus(@NonNull ConnectivityManager paramConnectivityManager)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return paramConnectivityManager.getRestrictBackgroundStatus();
    }
    return 3;
  }
  
  @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
  public static boolean isActiveNetworkMetered(@NonNull ConnectivityManager paramConnectivityManager)
  {
    boolean bool2 = true;
    if (Build.VERSION.SDK_INT >= 16) {
      bool1 = paramConnectivityManager.isActiveNetworkMetered();
    }
    do
    {
      return bool1;
      paramConnectivityManager = paramConnectivityManager.getActiveNetworkInfo();
      bool1 = bool2;
    } while (paramConnectivityManager == null);
    boolean bool1 = bool2;
    switch (paramConnectivityManager.getType())
    {
    case 0: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 8: 
    default: 
      return true;
    }
    return false;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface RestrictBackgroundStatus {}
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\net\ConnectivityManagerCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */