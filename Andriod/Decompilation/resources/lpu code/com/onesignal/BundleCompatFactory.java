package com.onesignal;

import android.os.Build.VERSION;

class BundleCompatFactory
{
  static BundleCompat getInstance()
  {
    if (Build.VERSION.SDK_INT >= 26) {
      return new BundleCompatPersistableBundle();
    }
    return new BundleCompatBundle();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\BundleCompatFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */