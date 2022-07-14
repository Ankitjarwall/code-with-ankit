package com.google.zxing.client.android.clipboard;

import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;

public final class ClipboardInterface
{
  private static final String TAG = ClipboardInterface.class.getSimpleName();
  
  private static ClipboardManager getManager(Context paramContext)
  {
    return (ClipboardManager)paramContext.getSystemService("clipboard");
  }
  
  public static CharSequence getText(Context paramContext)
  {
    ClipData localClipData = getManager(paramContext).getPrimaryClip();
    if (hasText(paramContext)) {
      return localClipData.getItemAt(0).coerceToText(paramContext);
    }
    return null;
  }
  
  public static boolean hasText(Context paramContext)
  {
    paramContext = getManager(paramContext).getPrimaryClip();
    return (paramContext != null) && (paramContext.getItemCount() > 0);
  }
  
  public static void setText(CharSequence paramCharSequence, Context paramContext)
  {
    if (paramCharSequence != null) {}
    try
    {
      getManager(paramContext).setPrimaryClip(ClipData.newPlainText(null, paramCharSequence));
      return;
    }
    catch (NullPointerException paramCharSequence)
    {
      Log.w(TAG, "Clipboard bug", paramCharSequence);
      return;
    }
    catch (IllegalStateException paramCharSequence)
    {
      for (;;) {}
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\clipboard\ClipboardInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */