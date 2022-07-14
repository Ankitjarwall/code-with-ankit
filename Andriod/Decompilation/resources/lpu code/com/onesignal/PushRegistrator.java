package com.onesignal;

import android.content.Context;

public abstract interface PushRegistrator
{
  public abstract void registerForPush(Context paramContext, String paramString, RegisteredHandler paramRegisteredHandler);
  
  public static abstract interface RegisteredHandler
  {
    public abstract void complete(String paramString, int paramInt);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\PushRegistrator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */