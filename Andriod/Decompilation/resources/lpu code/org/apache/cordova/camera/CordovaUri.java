package org.apache.cordova.camera;

import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import java.io.File;

public class CordovaUri
{
  private Uri androidUri;
  private String fileName;
  private Uri fileUri;
  
  CordovaUri(Uri paramUri)
  {
    if (paramUri.getScheme().equals("content"))
    {
      this.androidUri = paramUri;
      this.fileName = getFileNameFromUri(this.androidUri);
      this.fileUri = Uri.parse("file://" + this.fileName);
      return;
    }
    this.fileUri = paramUri;
    this.fileName = FileHelper.stripFileProtocol(paramUri.toString());
  }
  
  private String getFileNameFromUri(Uri paramUri)
  {
    paramUri = paramUri.toString().split("external_files")[1];
    File localFile = Environment.getExternalStorageDirectory();
    return localFile.getAbsolutePath() + paramUri;
  }
  
  public Uri getCorrectUri()
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return this.androidUri;
    }
    return this.fileUri;
  }
  
  public String getFilePath()
  {
    return this.fileName;
  }
  
  public Uri getFileUri()
  {
    return this.fileUri;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\camera\CordovaUri.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */