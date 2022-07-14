package com.squareup.picasso;

public abstract interface Callback
{
  public abstract void onError();
  
  public abstract void onSuccess();
  
  public static class EmptyCallback
    implements Callback
  {
    public void onError() {}
    
    public void onSuccess() {}
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\squareup\picasso\Callback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */