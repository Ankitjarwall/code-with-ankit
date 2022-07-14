import android.content.SharedPreferences.Editor;
import android.util.Log;
import org.apache.cordova.CallbackContext;

class NativeStorage$2
  implements Runnable
{
  NativeStorage$2(NativeStorage paramNativeStorage, CallbackContext paramCallbackContext) {}
  
  public void run()
  {
    try
    {
      NativeStorage.access$000(this.this$0).clear();
      if (NativeStorage.access$000(this.this$0).commit())
      {
        this.val$callbackContext.success();
        return;
      }
      this.val$callbackContext.error("Clear operation failed");
      return;
    }
    catch (Exception localException)
    {
      Log.e("Native Storage", "Clearing failed :", localException);
      this.val$callbackContext.error(localException.getMessage());
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\NativeStorage$2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */