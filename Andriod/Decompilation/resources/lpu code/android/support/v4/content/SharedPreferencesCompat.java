package android.support.v4.content;

import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;

@Deprecated
public final class SharedPreferencesCompat
{
  @Deprecated
  public static final class EditorCompat
  {
    private static EditorCompat sInstance;
    private final Helper mHelper = new Helper();
    
    @Deprecated
    public static EditorCompat getInstance()
    {
      if (sInstance == null) {
        sInstance = new EditorCompat();
      }
      return sInstance;
    }
    
    @Deprecated
    public void apply(@NonNull SharedPreferences.Editor paramEditor)
    {
      this.mHelper.apply(paramEditor);
    }
    
    private static class Helper
    {
      public void apply(@NonNull SharedPreferences.Editor paramEditor)
      {
        try
        {
          paramEditor.apply();
          return;
        }
        catch (AbstractMethodError localAbstractMethodError)
        {
          paramEditor.commit();
        }
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\content\SharedPreferencesCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */