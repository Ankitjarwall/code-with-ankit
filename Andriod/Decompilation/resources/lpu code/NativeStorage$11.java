import android.content.SharedPreferences.Editor;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;

class NativeStorage$11
  implements Runnable
{
  NativeStorage$11(NativeStorage paramNativeStorage, JSONArray paramJSONArray, CallbackContext paramCallbackContext) {}
  
  public void run()
  {
    try
    {
      String str1 = this.val$args.getString(0);
      String str2 = this.val$args.getString(1);
      NativeStorage.access$000(this.this$0).putString(str1, str2);
      if (NativeStorage.access$000(this.this$0).commit())
      {
        this.val$callbackContext.success(str2);
        return;
      }
      this.val$callbackContext.error(1);
      return;
    }
    catch (Exception localException)
    {
      Log.e("Native Storage", "setItem :", localException);
      this.val$callbackContext.error(localException.getMessage());
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\NativeStorage$11.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */