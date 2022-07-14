import android.content.SharedPreferences.Editor;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;

class NativeStorage$7
  implements Runnable
{
  NativeStorage$7(NativeStorage paramNativeStorage, JSONArray paramJSONArray, CallbackContext paramCallbackContext) {}
  
  public void run()
  {
    try
    {
      String str = this.val$args.getString(0);
      float f = (float)this.val$args.getDouble(1);
      NativeStorage.access$000(this.this$0).putFloat(str, f);
      if (NativeStorage.access$000(this.this$0).commit())
      {
        this.val$callbackContext.success(Float.toString(f));
        return;
      }
      this.val$callbackContext.error("Write failed");
      return;
    }
    catch (Exception localException)
    {
      Log.e("Native Storage", "PutFloat failed :", localException);
      this.val$callbackContext.error(localException.getMessage());
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\NativeStorage$7.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */