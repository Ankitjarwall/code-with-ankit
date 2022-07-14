package android.support.v4.media;

import android.media.AudioAttributes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
class AudioAttributesCompatApi21
{
  private static final String TAG = "AudioAttributesCompat";
  private static Method sAudioAttributesToLegacyStreamType;
  
  public static int toLegacyStreamType(Wrapper paramWrapper)
  {
    paramWrapper = paramWrapper.unwrap();
    try
    {
      if (sAudioAttributesToLegacyStreamType == null) {
        sAudioAttributesToLegacyStreamType = AudioAttributes.class.getMethod("toLegacyStreamType", new Class[] { AudioAttributes.class });
      }
      int i = ((Integer)sAudioAttributesToLegacyStreamType.invoke(null, new Object[] { paramWrapper })).intValue();
      return i;
    }
    catch (ClassCastException paramWrapper)
    {
      Log.w("AudioAttributesCompat", "getLegacyStreamType() failed on API21+", paramWrapper);
      return -1;
    }
    catch (IllegalAccessException paramWrapper)
    {
      for (;;) {}
    }
    catch (NoSuchMethodException paramWrapper)
    {
      for (;;) {}
    }
    catch (InvocationTargetException paramWrapper)
    {
      for (;;) {}
    }
  }
  
  static final class Wrapper
  {
    private AudioAttributes mWrapped;
    
    private Wrapper(AudioAttributes paramAudioAttributes)
    {
      this.mWrapped = paramAudioAttributes;
    }
    
    public static Wrapper wrap(@NonNull AudioAttributes paramAudioAttributes)
    {
      if (paramAudioAttributes == null) {
        throw new IllegalArgumentException("AudioAttributesApi21.Wrapper cannot wrap null");
      }
      return new Wrapper(paramAudioAttributes);
    }
    
    public AudioAttributes unwrap()
    {
      return this.mWrapped;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\media\AudioAttributesCompatApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */