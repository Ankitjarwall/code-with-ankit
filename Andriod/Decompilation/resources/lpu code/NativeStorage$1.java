import android.content.SharedPreferences.Editor;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;

class NativeStorage$1
  implements Runnable
{
  NativeStorage$1(NativeStorage paramNativeStorage, JSONArray paramJSONArray, CallbackContext paramCallbackContext) {}
  
  public void run()
  {
    try
    {
      String str = this.val$args.getString(0);
      NativeStorage.access$000(this.this$0).remove(str);
      if (NativeStorage.access$000(this.this$0).commit())
      {
        this.val$callbackContext.success();
        return;
      }
      this.val$callbackContext.error("Remove operation failed");
      return;
    }
    catch (Exception localException)
    {
      Log.e("Native Storage", "Removing failed :", localException);
      this.val$callbackContext.error(localException.getMessage());
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\NativeStorage$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */