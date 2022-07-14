package com.squareup.picasso;

public enum NetworkPolicy
{
  NO_CACHE(1),  NO_STORE(2),  OFFLINE(4);
  
  final int index;
  
  private NetworkPolicy(int paramInt)
  {
    this.index = paramInt;
  }
  
  public static boolean isOfflineOnly(int paramInt)
  {
    return (OFFLINE.index & paramInt) != 0;
  }
  
  public static boolean shouldReadFromDiskCache(int paramInt)
  {
    return (NO_CACHE.index & paramInt) == 0;
  }
  
  public static boolean shouldWriteToDiskCache(int paramInt)
  {
    return (NO_STORE.index & paramInt) == 0;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\squareup\picasso\NetworkPolicy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */