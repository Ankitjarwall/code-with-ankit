package com.google.android.gms.internal;

import java.util.Map;

public class zzj
{
  public final byte[] data;
  public final int statusCode;
  public final boolean zzA;
  public final long zzB;
  public final Map<String, String> zzz;
  
  public zzj(int paramInt, byte[] paramArrayOfByte, Map<String, String> paramMap, boolean paramBoolean, long paramLong)
  {
    this.statusCode = paramInt;
    this.data = paramArrayOfByte;
    this.zzz = paramMap;
    this.zzA = paramBoolean;
    this.zzB = paramLong;
  }
  
  public zzj(byte[] paramArrayOfByte, Map<String, String> paramMap)
  {
    this(200, paramArrayOfByte, paramMap, false, 0L);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */