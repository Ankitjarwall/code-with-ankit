package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

final class State
{
  static final State INITIAL_STATE = new State(Token.EMPTY, 0, 0, 0);
  private final int binaryShiftByteCount;
  private final int bitCount;
  private final int mode;
  private final Token token;
  
  private State(Token paramToken, int paramInt1, int paramInt2, int paramInt3)
  {
    this.token = paramToken;
    this.mode = paramInt1;
    this.binaryShiftByteCount = paramInt2;
    this.bitCount = paramInt3;
  }
  
  State addBinaryShiftChar(int paramInt)
  {
    Object localObject2 = this.token;
    int m = this.mode;
    int i = this.bitCount;
    int k;
    int j;
    Object localObject1;
    if (this.mode != 4)
    {
      k = i;
      j = m;
      localObject1 = localObject2;
      if (this.mode != 2) {}
    }
    else
    {
      j = HighLevelEncoder.LATCH_TABLE[m][0];
      localObject1 = ((Token)localObject2).add(0xFFFF & j, j >> 16);
      k = i + (j >> 16);
      j = 0;
    }
    if ((this.binaryShiftByteCount == 0) || (this.binaryShiftByteCount == 31)) {
      i = 18;
    }
    for (;;)
    {
      localObject2 = new State((Token)localObject1, j, this.binaryShiftByteCount + 1, k + i);
      localObject1 = localObject2;
      if (((State)localObject2).binaryShiftByteCount == 2078) {
        localObject1 = ((State)localObject2).endBinaryShift(paramInt + 1);
      }
      return (State)localObject1;
      if (this.binaryShiftByteCount == 62) {
        i = 9;
      } else {
        i = 8;
      }
    }
  }
  
  State endBinaryShift(int paramInt)
  {
    if (this.binaryShiftByteCount == 0) {
      return this;
    }
    return new State(this.token.addBinaryShift(paramInt - this.binaryShiftByteCount, this.binaryShiftByteCount), this.mode, 0, this.bitCount);
  }
  
  int getBinaryShiftByteCount()
  {
    return this.binaryShiftByteCount;
  }
  
  int getBitCount()
  {
    return this.bitCount;
  }
  
  int getMode()
  {
    return this.mode;
  }
  
  Token getToken()
  {
    return this.token;
  }
  
  boolean isBetterThanOrEqualTo(State paramState)
  {
    int j = this.bitCount + (HighLevelEncoder.LATCH_TABLE[this.mode][paramState.mode] >> 16);
    int i = j;
    if (paramState.binaryShiftByteCount > 0) {
      if (this.binaryShiftByteCount != 0)
      {
        i = j;
        if (this.binaryShiftByteCount <= paramState.binaryShiftByteCount) {}
      }
      else
      {
        i = j + 10;
      }
    }
    return i <= paramState.bitCount;
  }
  
  State latchAndAppend(int paramInt1, int paramInt2)
  {
    int j = this.bitCount;
    Token localToken2 = this.token;
    int i = j;
    Token localToken1 = localToken2;
    if (paramInt1 != this.mode)
    {
      i = HighLevelEncoder.LATCH_TABLE[this.mode][paramInt1];
      localToken1 = localToken2.add(0xFFFF & i, i >> 16);
      i = j + (i >> 16);
    }
    if (paramInt1 == 2) {}
    for (j = 4;; j = 5) {
      return new State(localToken1.add(paramInt2, j), paramInt1, 0, i + j);
    }
  }
  
  State shiftAndAppend(int paramInt1, int paramInt2)
  {
    Token localToken = this.token;
    if (this.mode == 2) {}
    for (int i = 4;; i = 5) {
      return new State(localToken.add(HighLevelEncoder.SHIFT_TABLE[this.mode][paramInt1], i).add(paramInt2, 5), this.mode, 0, this.bitCount + i + 5);
    }
  }
  
  BitArray toBitArray(byte[] paramArrayOfByte)
  {
    Object localObject2 = new LinkedList();
    for (Object localObject1 = endBinaryShift(paramArrayOfByte.length).token; localObject1 != null; localObject1 = ((Token)localObject1).getPrevious()) {
      ((Deque)localObject2).addFirst(localObject1);
    }
    localObject1 = new BitArray();
    localObject2 = ((Deque)localObject2).iterator();
    while (((Iterator)localObject2).hasNext()) {
      ((Token)((Iterator)localObject2).next()).appendTo((BitArray)localObject1, paramArrayOfByte);
    }
    return (BitArray)localObject1;
  }
  
  public String toString()
  {
    return String.format("%s bits=%d bytes=%d", new Object[] { HighLevelEncoder.MODE_NAMES[this.mode], Integer.valueOf(this.bitCount), Integer.valueOf(this.binaryShiftByteCount) });
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\aztec\encoder\State.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */