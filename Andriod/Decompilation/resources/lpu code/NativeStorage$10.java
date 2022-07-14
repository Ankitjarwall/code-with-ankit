import android.content.SharedPreferences;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;

class NativeStorage$10
  implements Runnable
{
  NativeStorage$10(NativeStorage paramNativeStorage, JSONArray paramJSONArray, CallbackContext paramCallbackContext) {}
  
  public void run()
  {
    try
    {
      String str = this.val$args.getString(0);
      str = NativeStorage.access$100(this.this$0).getString(str, "null");
      this.val$callbackContext.success(str);
      return;
    }
    catch (Exception localException)
    {
      Log.e("Native Storage", "GetString failed :", localException);
      this.val$callbackContext.error(localException.getMessage());
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\NativeStorage$10.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */