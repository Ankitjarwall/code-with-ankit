package com.google.zxing.pdf417.decoder.ec;

final class ModulusPoly
{
  private final int[] coefficients;
  private final ModulusGF field;
  
  ModulusPoly(ModulusGF paramModulusGF, int[] paramArrayOfInt)
  {
    if (paramArrayOfInt.length == 0) {
      throw new IllegalArgumentException();
    }
    this.field = paramModulusGF;
    int j = paramArrayOfInt.length;
    if ((j > 1) && (paramArrayOfInt[0] == 0))
    {
      int i = 1;
      while ((i < j) && (paramArrayOfInt[i] == 0)) {
        i += 1;
      }
      if (i == j)
      {
        this.coefficients = new int[] { 0 };
        return;
      }
      this.coefficients = new int[j - i];
      System.arraycopy(paramArrayOfInt, i, this.coefficients, 0, this.coefficients.length);
      return;
    }
    this.coefficients = paramArrayOfInt;
  }
  
  ModulusPoly add(ModulusPoly paramModulusPoly)
  {
    if (!this.field.equals(paramModulusPoly.field)) {
      throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    }
    if (isZero()) {
      return paramModulusPoly;
    }
    if (paramModulusPoly.isZero()) {
      return this;
    }
    int[] arrayOfInt1 = this.coefficients;
    int[] arrayOfInt3 = paramModulusPoly.coefficients;
    int[] arrayOfInt2 = arrayOfInt3;
    paramModulusPoly = arrayOfInt1;
    if (arrayOfInt1.length > arrayOfInt3.length)
    {
      paramModulusPoly = arrayOfInt3;
      arrayOfInt2 = arrayOfInt1;
    }
    arrayOfInt1 = new int[arrayOfInt2.length];
    int j = arrayOfInt2.length - paramModulusPoly.length;
    System.arraycopy(arrayOfInt2, 0, arrayOfInt1, 0, j);
    int i = j;
    while (i < arrayOfInt2.length)
    {
      arrayOfInt1[i] = this.field.add(paramModulusPoly[(i - j)], arrayOfInt2[i]);
      i += 1;
    }
    return new ModulusPoly(this.field, arrayOfInt1);
  }
  
  int evaluateAt(int paramInt)
  {
    int j = 0;
    int i;
    if (paramInt == 0)
    {
      i = getCoefficient(0);
      return i;
    }
    if (paramInt == 1)
    {
      paramInt = 0;
      int[] arrayOfInt = this.coefficients;
      k = arrayOfInt.length;
      for (;;)
      {
        i = paramInt;
        if (j >= k) {
          break;
        }
        i = arrayOfInt[j];
        paramInt = this.field.add(paramInt, i);
        j += 1;
      }
    }
    j = this.coefficients[0];
    int m = this.coefficients.length;
    int k = 1;
    for (;;)
    {
      i = j;
      if (k >= m) {
        break;
      }
      j = this.field.add(this.field.multiply(paramInt, j), this.coefficients[k]);
      k += 1;
    }
  }
  
  int getCoefficient(int paramInt)
  {
    return this.coefficients[(this.coefficients.length - 1 - paramInt)];
  }
  
  int[] getCoefficients()
  {
    return this.coefficients;
  }
  
  int getDegree()
  {
    return this.coefficients.length - 1;
  }
  
  boolean isZero()
  {
    boolean bool = false;
    if (this.coefficients[0] == 0) {
      bool = true;
    }
    return bool;
  }
  
  ModulusPoly multiply(int paramInt)
  {
    if (paramInt == 0) {
      localObject = this.field.getZero();
    }
    do
    {
      return (ModulusPoly)localObject;
      localObject = this;
    } while (paramInt == 1);
    int j = this.coefficients.length;
    Object localObject = new int[j];
    int i = 0;
    while (i < j)
    {
      localObject[i] = this.field.multiply(this.coefficients[i], paramInt);
      i += 1;
    }
    return new ModulusPoly(this.field, (int[])localObject);
  }
  
  ModulusPoly multiply(ModulusPoly paramModulusPoly)
  {
    if (!this.field.equals(paramModulusPoly.field)) {
      throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    }
    if ((isZero()) || (paramModulusPoly.isZero())) {
      return this.field.getZero();
    }
    int[] arrayOfInt1 = this.coefficients;
    int k = arrayOfInt1.length;
    paramModulusPoly = paramModulusPoly.coefficients;
    int m = paramModulusPoly.length;
    int[] arrayOfInt2 = new int[k + m - 1];
    int i = 0;
    while (i < k)
    {
      int n = arrayOfInt1[i];
      int j = 0;
      while (j < m)
      {
        arrayOfInt2[(i + j)] = this.field.add(arrayOfInt2[(i + j)], this.field.multiply(n, paramModulusPoly[j]));
        j += 1;
      }
      i += 1;
    }
    return new ModulusPoly(this.field, arrayOfInt2);
  }
  
  ModulusPoly multiplyByMonomial(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0) {
      throw new IllegalArgumentException();
    }
    if (paramInt2 == 0) {
      return this.field.getZero();
    }
    int i = this.coefficients.length;
    int[] arrayOfInt = new int[i + paramInt1];
    paramInt1 = 0;
    while (paramInt1 < i)
    {
      arrayOfInt[paramInt1] = this.field.multiply(this.coefficients[paramInt1], paramInt2);
      paramInt1 += 1;
    }
    return new ModulusPoly(this.field, arrayOfInt);
  }
  
  ModulusPoly negative()
  {
    int j = this.coefficients.length;
    int[] arrayOfInt = new int[j];
    int i = 0;
    while (i < j)
    {
      arrayOfInt[i] = this.field.subtract(0, this.coefficients[i]);
      i += 1;
    }
    return new ModulusPoly(this.field, arrayOfInt);
  }
  
  ModulusPoly subtract(ModulusPoly paramModulusPoly)
  {
    if (!this.field.equals(paramModulusPoly.field)) {
      throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    }
    if (paramModulusPoly.isZero()) {
      return this;
    }
    return add(paramModulusPoly.negative());
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(getDegree() * 8);
    int i = getDegree();
    if (i >= 0)
    {
      int k = getCoefficient(i);
      int j;
      if (k != 0)
      {
        if (k >= 0) {
          break label90;
        }
        localStringBuilder.append(" - ");
        j = -k;
        label50:
        if ((i == 0) || (j != 1)) {
          localStringBuilder.append(j);
        }
        if (i != 0)
        {
          if (i != 1) {
            break label113;
          }
          localStringBuilder.append('x');
        }
      }
      for (;;)
      {
        i -= 1;
        break;
        label90:
        j = k;
        if (localStringBuilder.length() <= 0) {
          break label50;
        }
        localStringBuilder.append(" + ");
        j = k;
        break label50;
        label113:
        localStringBuilder.append("x^");
        localStringBuilder.append(i);
      }
    }
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\pdf417\decoder\ec\ModulusPoly.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */