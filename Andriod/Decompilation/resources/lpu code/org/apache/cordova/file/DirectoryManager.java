package org.apache.cordova.file;

import android.os.Environment;
import android.os.StatFs;
import java.io.File;

public class DirectoryManager
{
  private static final String LOG_TAG = "DirectoryManager";
  
  private static File constructFilePaths(String paramString1, String paramString2)
  {
    if (paramString2.startsWith(paramString1)) {
      return new File(paramString2);
    }
    return new File(paramString1 + "/" + paramString2);
  }
  
  public static long getFreeExternalStorageSpace()
  {
    if (Environment.getExternalStorageState().equals("mounted")) {
      return getFreeSpaceInBytes(Environment.getExternalStorageDirectory().getPath()) / 1024L;
    }
    return -1L;
  }
  
  public static long getFreeSpaceInBytes(String paramString)
  {
    try
    {
      paramString = new StatFs(paramString);
      long l = paramString.getBlockSize();
      int i = paramString.getAvailableBlocks();
      return i * l;
    }
    catch (IllegalArgumentException paramString) {}
    return 0L;
  }
  
  public static boolean testFileExists(String paramString)
  {
    if ((testSaveLocationExists()) && (!paramString.equals(""))) {
      return constructFilePaths(Environment.getExternalStorageDirectory().toString(), paramString).exists();
    }
    return false;
  }
  
  public static boolean testSaveLocationExists()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\file\DirectoryManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */