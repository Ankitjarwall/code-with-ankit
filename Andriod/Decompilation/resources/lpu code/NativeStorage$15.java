import android.content.SharedPreferences;
import android.util.Log;
import java.util.Map;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;

class NativeStorage$15
  implements Runnable
{
  NativeStorage$15(NativeStorage paramNativeStorage, CallbackContext paramCallbackContext) {}
  
  public void run()
  {
    try
    {
      Map localMap = NativeStorage.access$100(this.this$0).getAll();
      this.val$callbackContext.success(new JSONArray(localMap.keySet()));
      return;
    }
    catch (Exception localException)
    {
      Log.e("Native Storage", "Get keys failed :", localException);
      this.val$callbackContext.error(localException.getMessage());
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\NativeStorage$15.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */