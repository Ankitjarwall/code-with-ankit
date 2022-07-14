import android.content.SharedPreferences;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;

class NativeStorage$8
  implements Runnable
{
  NativeStorage$8(NativeStorage paramNativeStorage, JSONArray paramJSONArray, CallbackContext paramCallbackContext) {}
  
  public void run()
  {
    try
    {
      String str = this.val$args.getString(0);
      float f = NativeStorage.access$100(this.this$0).getFloat(str, -1.0F);
      this.val$callbackContext.success(Float.toString(f));
      return;
    }
    catch (Exception localException)
    {
      Log.e("Native Storage", "GetFloat failed :", localException);
      this.val$callbackContext.error(localException.getMessage());
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\NativeStorage$8.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */