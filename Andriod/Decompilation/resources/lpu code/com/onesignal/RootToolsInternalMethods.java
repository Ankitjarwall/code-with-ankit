package com.onesignal;

import java.io.File;

class RootToolsInternalMethods
{
  static boolean isRooted()
  {
    String[] arrayOfString = new String[8];
    arrayOfString[0] = "/sbin/";
    arrayOfString[1] = "/system/bin/";
    arrayOfString[2] = "/system/xbin/";
    arrayOfString[3] = "/data/local/xbin/";
    arrayOfString[4] = "/data/local/bin/";
    arrayOfString[5] = "/system/sd/xbin/";
    arrayOfString[6] = "/system/bin/failsafe/";
    arrayOfString[7] = "/data/local/";
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = arrayOfString[i];
      if (new File(str + "su").exists()) {
        return true;
      }
      i += 1;
    }
    return false;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\RootToolsInternalMethods.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */