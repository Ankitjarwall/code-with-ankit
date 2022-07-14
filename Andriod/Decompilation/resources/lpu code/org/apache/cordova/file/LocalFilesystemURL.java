package org.apache.cordova.file;

import android.net.Uri;

public class LocalFilesystemURL
{
  public static final String FILESYSTEM_PROTOCOL = "cdvfile";
  public final String fsName;
  public final boolean isDirectory;
  public final String path;
  public final Uri uri;
  
  private LocalFilesystemURL(Uri paramUri, String paramString1, String paramString2, boolean paramBoolean)
  {
    this.uri = paramUri;
    this.fsName = paramString1;
    this.path = paramString2;
    this.isDirectory = paramBoolean;
  }
  
  public static LocalFilesystemURL parse(Uri paramUri)
  {
    boolean bool = true;
    if (!"cdvfile".equals(paramUri.getScheme())) {}
    int i;
    do
    {
      do
      {
        return null;
        str2 = paramUri.getPath();
      } while (str2.length() < 1);
      i = str2.indexOf('/', 1);
    } while (i < 0);
    String str1 = str2.substring(1, i);
    String str2 = str2.substring(i);
    if (str2.charAt(str2.length() - 1) == '/') {}
    for (;;)
    {
      return new LocalFilesystemURL(paramUri, str1, str2, bool);
      bool = false;
    }
  }
  
  public static LocalFilesystemURL parse(String paramString)
  {
    return parse(Uri.parse(paramString));
  }
  
  public String toString()
  {
    return this.uri.toString();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\file\LocalFilesystemURL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */