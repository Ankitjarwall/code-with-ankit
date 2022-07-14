package android.support.v13.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;

public final class InputConnectionCompat
{
  private static final String COMMIT_CONTENT_ACTION = "android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT";
  private static final String COMMIT_CONTENT_CONTENT_URI_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI";
  private static final String COMMIT_CONTENT_DESCRIPTION_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
  private static final String COMMIT_CONTENT_FLAGS_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
  private static final String COMMIT_CONTENT_LINK_URI_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
  private static final String COMMIT_CONTENT_OPTS_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
  private static final String COMMIT_CONTENT_RESULT_RECEIVER = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER";
  public static int INPUT_CONTENT_GRANT_READ_URI_PERMISSION = 1;
  
  public static boolean commitContent(@NonNull InputConnection paramInputConnection, @NonNull EditorInfo paramEditorInfo, @NonNull InputContentInfoCompat paramInputContentInfoCompat, int paramInt, @Nullable Bundle paramBundle)
  {
    ClipDescription localClipDescription = paramInputContentInfoCompat.getDescription();
    int k = 0;
    paramEditorInfo = EditorInfoCompat.getContentMimeTypes(paramEditorInfo);
    int m = paramEditorInfo.length;
    int i = 0;
    for (;;)
    {
      int j = k;
      if (i < m)
      {
        if (localClipDescription.hasMimeType(paramEditorInfo[i])) {
          j = 1;
        }
      }
      else
      {
        if (j != 0) {
          break;
        }
        return false;
      }
      i += 1;
    }
    if (Build.VERSION.SDK_INT >= 25) {
      return paramInputConnection.commitContent((InputContentInfo)paramInputContentInfoCompat.unwrap(), paramInt, paramBundle);
    }
    paramEditorInfo = new Bundle();
    paramEditorInfo.putParcelable("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI", paramInputContentInfoCompat.getContentUri());
    paramEditorInfo.putParcelable("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION", paramInputContentInfoCompat.getDescription());
    paramEditorInfo.putParcelable("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI", paramInputContentInfoCompat.getLinkUri());
    paramEditorInfo.putInt("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS", paramInt);
    paramEditorInfo.putParcelable("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS", paramBundle);
    return paramInputConnection.performPrivateCommand("android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", paramEditorInfo);
  }
  
  @NonNull
  public static InputConnection createWrapper(@NonNull InputConnection paramInputConnection, @NonNull EditorInfo paramEditorInfo, @NonNull final OnCommitContentListener paramOnCommitContentListener)
  {
    if (paramInputConnection == null) {
      throw new IllegalArgumentException("inputConnection must be non-null");
    }
    if (paramEditorInfo == null) {
      throw new IllegalArgumentException("editorInfo must be non-null");
    }
    if (paramOnCommitContentListener == null) {
      throw new IllegalArgumentException("onCommitContentListener must be non-null");
    }
    Object localObject;
    if (Build.VERSION.SDK_INT >= 25) {
      localObject = new InputConnectionWrapper(paramInputConnection, false)
      {
        public boolean commitContent(InputContentInfo paramAnonymousInputContentInfo, int paramAnonymousInt, Bundle paramAnonymousBundle)
        {
          if (paramOnCommitContentListener.onCommitContent(InputContentInfoCompat.wrap(paramAnonymousInputContentInfo), paramAnonymousInt, paramAnonymousBundle)) {
            return true;
          }
          return super.commitContent(paramAnonymousInputContentInfo, paramAnonymousInt, paramAnonymousBundle);
        }
      };
    }
    do
    {
      return (InputConnection)localObject;
      localObject = paramInputConnection;
    } while (EditorInfoCompat.getContentMimeTypes(paramEditorInfo).length == 0);
    new InputConnectionWrapper(paramInputConnection, false)
    {
      public boolean performPrivateCommand(String paramAnonymousString, Bundle paramAnonymousBundle)
      {
        if (InputConnectionCompat.handlePerformPrivateCommand(paramAnonymousString, paramAnonymousBundle, paramOnCommitContentListener)) {
          return true;
        }
        return super.performPrivateCommand(paramAnonymousString, paramAnonymousBundle);
      }
    };
  }
  
  static boolean handlePerformPrivateCommand(@Nullable String paramString, @NonNull Bundle paramBundle, @NonNull OnCommitContentListener paramOnCommitContentListener)
  {
    int i = 1;
    if (!TextUtils.equals("android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", paramString)) {}
    while (paramBundle == null) {
      return false;
    }
    paramString = null;
    try
    {
      ResultReceiver localResultReceiver = (ResultReceiver)paramBundle.getParcelable("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER");
      paramString = localResultReceiver;
      Uri localUri1 = (Uri)paramBundle.getParcelable("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI");
      paramString = localResultReceiver;
      ClipDescription localClipDescription = (ClipDescription)paramBundle.getParcelable("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION");
      paramString = localResultReceiver;
      Uri localUri2 = (Uri)paramBundle.getParcelable("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI");
      paramString = localResultReceiver;
      int j = paramBundle.getInt("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS");
      paramString = localResultReceiver;
      paramBundle = (Bundle)paramBundle.getParcelable("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS");
      paramString = localResultReceiver;
      boolean bool = paramOnCommitContentListener.onCommitContent(new InputContentInfoCompat(localUri1, localClipDescription, localUri2), j, paramBundle);
      if (localResultReceiver != null) {
        if (!bool) {
          break label145;
        }
      }
      label145:
      for (i = 1;; i = 0)
      {
        localResultReceiver.send(i, null);
        return bool;
      }
      if (0 == 0) {}
    }
    finally
    {
      if (paramString == null) {}
    }
    for (;;)
    {
      paramString.send(i, null);
      throw paramBundle;
      i = 0;
    }
  }
  
  public static abstract interface OnCommitContentListener
  {
    public abstract boolean onCommitContent(InputContentInfoCompat paramInputContentInfoCompat, int paramInt, Bundle paramBundle);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v13\view\inputmethod\InputConnectionCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */