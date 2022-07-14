package com.onesignal;

class UserStateEmail
  extends UserState
{
  UserStateEmail(String paramString, boolean paramBoolean)
  {
    super("email" + paramString, paramBoolean);
  }
  
  protected void addDependFields() {}
  
  boolean isSubscribed()
  {
    return true;
  }
  
  UserState newInstance(String paramString)
  {
    return new UserStateEmail(paramString, false);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\onesignal\UserStateEmail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */