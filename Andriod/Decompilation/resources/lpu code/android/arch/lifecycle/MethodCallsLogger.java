package android.arch.lifecycle;

import android.support.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class MethodCallsLogger
{
  private Map<String, Integer> mCalledMethods = new HashMap();
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public boolean approveCall(String paramString, int paramInt)
  {
    Integer localInteger = (Integer)this.mCalledMethods.get(paramString);
    int i;
    if (localInteger != null)
    {
      i = localInteger.intValue();
      if ((i & paramInt) == 0) {
        break label64;
      }
    }
    label64:
    for (int j = 1;; j = 0)
    {
      this.mCalledMethods.put(paramString, Integer.valueOf(i | paramInt));
      if (j != 0) {
        break label70;
      }
      return true;
      i = 0;
      break;
    }
    label70:
    return false;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\arch\lifecycle\MethodCallsLogger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */