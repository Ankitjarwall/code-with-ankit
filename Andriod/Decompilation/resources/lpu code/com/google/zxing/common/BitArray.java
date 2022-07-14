package com.google.zxing.common;

import java.util.Arrays;

public final class BitArray
  implements Cloneable
{
  private int[] bits;
  private int size;
  
  public BitArray()
  {
    this.size = 0;
    this.bits = new int[1];
  }
  
  public BitArray(int paramInt)
  {
    this.size = paramInt;
    this.bits = makeArray(paramInt);
  }
  
  BitArray(int[] paramArrayOfInt, int paramInt)
  {
    this.bits = paramArrayOfInt;
    this.size = paramInt;
  }
  
  private void ensureCapacity(int paramInt)
  {
    if (paramInt > this.bits.length * 32)
    {
      int[] arrayOfInt = makeArray(paramInt);
      System.arraycopy(this.bits, 0, arrayOfInt, 0, this.bits.length);
      this.bits = arrayOfInt;
    }
  }
  
  private static int[] makeArray(int paramInt)
  {
    return new int[(paramInt + 31) / 32];
  }
  
  public void appendBit(boolean paramBoolean)
  {
    ensureCapacity(this.size + 1);
    if (paramBoolean)
    {
      int[] arrayOfInt = this.bits;
      int i = this.size / 32;
      arrayOfInt[i] |= 1 << (this.size & 0x1F);
    }
    this.size += 1;
  }
  
  public void appendBitArray(BitArray paramBitArray)
  {
    int j = paramBitArray.size;
    ensureCapacity(this.size + j);
    int i = 0;
    while (i < j)
    {
      appendBit(paramBitArray.get(i));
      i += 1;
    }
  }
  
  public void appendBits(int paramInt1, int paramInt2)
  {
    if ((paramInt2 < 0) || (paramInt2 > 32)) {
      throw new IllegalArgumentException("Num bits must be between 0 and 32");
    }
    ensureCapacity(this.size + paramInt2);
    if (paramInt2 > 0)
    {
      if ((paramInt1 >> paramInt2 - 1 & 0x1) == 1) {}
      for (boolean bool = true;; bool = false)
      {
        appendBit(bool);
        paramInt2 -= 1;
        break;
      }
    }
  }
  
  public void clear()
  {
    int j = this.bits.length;
    int i = 0;
    while (i < j)
    {
      this.bits[i] = 0;
      i += 1;
    }
  }
  
  public BitArray clone()
  {
    return new BitArray((int[])this.bits.clone(), this.size);
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof BitArray)) {}
    do
    {
      return false;
      paramObject = (BitArray)paramObject;
    } while ((this.size != ((BitArray)paramObject).size) || (!Arrays.equals(this.bits, ((BitArray)paramObject).bits)));
    return true;
  }
  
  public void flip(int paramInt)
  {
    int[] arrayOfInt = this.bits;
    int i = paramInt / 32;
    arrayOfInt[i] ^= 1 << (paramInt & 0x1F);
  }
  
  public boolean get(int paramInt)
  {
    return (this.bits[(paramInt / 32)] & 1 << (paramInt & 0x1F)) != 0;
  }
  
  public int[] getBitArray()
  {
    return this.bits;
  }
  
  public int getNextSet(int paramInt)
  {
    if (paramInt >= this.size) {
      paramInt = this.size;
    }
    int i;
    do
    {
      return paramInt;
      int j = paramInt / 32;
      i = this.bits[j] & ((1 << (paramInt & 0x1F)) - 1 ^ 0xFFFFFFFF);
      paramInt = j;
      while (i == 0)
      {
        paramInt += 1;
        if (paramInt == this.bits.length) {
          return this.size;
        }
        i = this.bits[paramInt];
      }
      i = paramInt * 32 + Integer.numberOfTrailingZeros(i);
      paramInt = i;
    } while (i <= this.size);
    return this.size;
  }
  
  public int getNextUnset(int paramInt)
  {
    if (paramInt >= this.size) {
      paramInt = this.size;
    }
    int i;
    do
    {
      return paramInt;
      int j = paramInt / 32;
      i = (this.bits[j] ^ 0xFFFFFFFF) & ((1 << (paramInt & 0x1F)) - 1 ^ 0xFFFFFFFF);
      paramInt = j;
      while (i == 0)
      {
        paramInt += 1;
        if (paramInt == this.bits.length) {
          return this.size;
        }
        i = this.bits[paramInt] ^ 0xFFFFFFFF;
      }
      i = paramInt * 32 + Integer.numberOfTrailingZeros(i);
      paramInt = i;
    } while (i <= this.size);
    return this.size;
  }
  
  public int getSize()
  {
    return this.size;
  }
  
  public int getSizeInBytes()
  {
    return (this.size + 7) / 8;
  }
  
  public int hashCode()
  {
    return this.size * 31 + Arrays.hashCode(this.bits);
  }
  
  public boolean isRange(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if ((paramInt2 < paramInt1) || (paramInt1 < 0) || (paramInt2 > this.size)) {
      throw new IllegalArgumentException();
    }
    if (paramInt2 == paramInt1) {}
    for (;;)
    {
      return true;
      int m = paramInt2 - 1;
      int k = paramInt1 / 32;
      int n = m / 32;
      int i = k;
      while (i <= n)
      {
        int j;
        label81:
        int i1;
        if (i > k)
        {
          paramInt2 = 0;
          if (i >= n) {
            break label126;
          }
          j = 31;
          j = (2 << j) - (1 << paramInt2);
          i1 = this.bits[i];
          if (!paramBoolean) {
            break label136;
          }
        }
        label126:
        label136:
        for (paramInt2 = j;; paramInt2 = 0)
        {
          if ((i1 & j) == paramInt2) {
            break label141;
          }
          return false;
          paramInt2 = paramInt1 & 0x1F;
          break;
          j = m & 0x1F;
          break label81;
        }
        label141:
        i += 1;
      }
    }
  }
  
  public void reverse()
  {
    int[] arrayOfInt = new int[this.bits.length];
    int j = (this.size - 1) / 32;
    int k = j + 1;
    int i = 0;
    while (i < k)
    {
      long l = this.bits[i];
      l = l >> 1 & 0x55555555 | (0x55555555 & l) << 1;
      l = l >> 2 & 0x33333333 | (0x33333333 & l) << 2;
      l = l >> 4 & 0xF0F0F0F | (0xF0F0F0F & l) << 4;
      l = l >> 8 & 0xFF00FF | (0xFF00FF & l) << 8;
      arrayOfInt[(j - i)] = ((int)(l >> 16 & 0xFFFF | (0xFFFF & l) << 16));
      i += 1;
    }
    if (this.size != k * 32)
    {
      int m = k * 32 - this.size;
      j = arrayOfInt[0] >>> m;
      i = 1;
      while (i < k)
      {
        int n = arrayOfInt[i];
        arrayOfInt[(i - 1)] = (j | n << 32 - m);
        j = n >>> m;
        i += 1;
      }
      arrayOfInt[(k - 1)] = j;
    }
    this.bits = arrayOfInt;
  }
  
  public void set(int paramInt)
  {
    int[] arrayOfInt = this.bits;
    int i = paramInt / 32;
    arrayOfInt[i] |= 1 << (paramInt & 0x1F);
  }
  
  public void setBulk(int paramInt1, int paramInt2)
  {
    this.bits[(paramInt1 / 32)] = paramInt2;
  }
  
  public void setRange(int paramInt1, int paramInt2)
  {
    if ((paramInt2 < paramInt1) || (paramInt1 < 0) || (paramInt2 > this.size)) {
      throw new IllegalArgumentException();
    }
    if (paramInt2 == paramInt1) {
      return;
    }
    int m = paramInt2 - 1;
    int k = paramInt1 / 32;
    int n = m / 32;
    paramInt2 = k;
    label52:
    int i;
    if (paramInt2 <= n)
    {
      if (paramInt2 <= k) {
        break label106;
      }
      i = 0;
      label66:
      if (paramInt2 >= n) {
        break label114;
      }
    }
    label106:
    label114:
    for (int j = 31;; j = m & 0x1F)
    {
      int[] arrayOfInt = this.bits;
      arrayOfInt[paramInt2] |= (2 << j) - (1 << i);
      paramInt2 += 1;
      break label52;
      break;
      i = paramInt1 & 0x1F;
      break label66;
    }
  }
  
  public void toBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    int j = 0;
    int i = paramInt1;
    paramInt1 = j;
    while (paramInt1 < paramInt3)
    {
      int k = 0;
      j = 0;
      while (j < 8)
      {
        int m = k;
        if (get(i)) {
          m = k | 1 << 7 - j;
        }
        i += 1;
        j += 1;
        k = m;
      }
      paramArrayOfByte[(paramInt2 + paramInt1)] = ((byte)k);
      paramInt1 += 1;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(this.size);
    int i = 0;
    if (i < this.size)
    {
      if ((i & 0x7) == 0) {
        localStringBuilder.append(' ');
      }
      if (get(i)) {}
      for (char c = 'X';; c = '.')
      {
        localStringBuilder.append(c);
        i += 1;
        break;
      }
    }
    return localStringBuilder.toString();
  }
  
  public void xor(BitArray paramBitArray)
  {
    if (this.size != paramBitArray.size) {
      throw new IllegalArgumentException("Sizes don't match");
    }
    int i = 0;
    while (i < this.bits.length)
    {
      int[] arrayOfInt = this.bits;
      arrayOfInt[i] ^= paramBitArray.bits[i];
      i += 1;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\common\BitArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */