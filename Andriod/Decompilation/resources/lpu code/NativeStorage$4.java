import android.content.SharedPreferences;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;

class NativeStorage$4
  implements Runnable
{
  NativeStorage$4(NativeStorage paramNativeStorage, JSONArray paramJSONArray, CallbackContext paramCallbackContext) {}
  
  public void run()
  {
    try
    {
      String str = this.val$args.getString(0);
      boolean bool = NativeStorage.access$100(this.this$0).getBoolean(str, false);
      this.val$callbackContext.success(String.valueOf(Boolean.valueOf(bool)));
      return;
    }
    catch (Exception localException)
    {
      Log.e("Native Storage", "PutBoolean failed :", localException);
      this.val$callbackContext.error(localException.getMessage());
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\NativeStorage$4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */