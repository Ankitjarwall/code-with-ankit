package com.google.zxing.common.reedsolomon;

public final class ReedSolomonDecoder
{
  private final GenericGF field;
  
  public ReedSolomonDecoder(GenericGF paramGenericGF)
  {
    this.field = paramGenericGF;
  }
  
  private int[] findErrorLocations(GenericGFPoly paramGenericGFPoly)
    throws ReedSolomonException
  {
    int m = paramGenericGFPoly.getDegree();
    int[] arrayOfInt;
    if (m == 1)
    {
      arrayOfInt = new int[1];
      arrayOfInt[0] = paramGenericGFPoly.getCoefficient(1);
      paramGenericGFPoly = arrayOfInt;
    }
    int j;
    do
    {
      return paramGenericGFPoly;
      arrayOfInt = new int[m];
      j = 0;
      int i = 1;
      while ((i < this.field.getSize()) && (j < m))
      {
        int k = j;
        if (paramGenericGFPoly.evaluateAt(i) == 0)
        {
          arrayOfInt[j] = this.field.inverse(i);
          k = j + 1;
        }
        i += 1;
        j = k;
      }
      paramGenericGFPoly = arrayOfInt;
    } while (j == m);
    throw new ReedSolomonException("Error locator degree does not match number of roots");
  }
  
  private int[] findErrorMagnitudes(GenericGFPoly paramGenericGFPoly, int[] paramArrayOfInt)
  {
    int n = paramArrayOfInt.length;
    int[] arrayOfInt = new int[n];
    int i = 0;
    while (i < n)
    {
      int i1 = this.field.inverse(paramArrayOfInt[i]);
      int k = 1;
      int j = 0;
      if (j < n)
      {
        int m = k;
        if (i != j)
        {
          m = this.field.multiply(paramArrayOfInt[j], i1);
          if ((m & 0x1) != 0) {
            break label107;
          }
          m |= 0x1;
        }
        for (;;)
        {
          m = this.field.multiply(k, m);
          j += 1;
          k = m;
          break;
          label107:
          m &= 0xFFFFFFFE;
        }
      }
      arrayOfInt[i] = this.field.multiply(paramGenericGFPoly.evaluateAt(i1), this.field.inverse(k));
      if (this.field.getGeneratorBase() != 0) {
        arrayOfInt[i] = this.field.multiply(arrayOfInt[i], i1);
      }
      i += 1;
    }
    return arrayOfInt;
  }
  
  private GenericGFPoly[] runEuclideanAlgorithm(GenericGFPoly paramGenericGFPoly1, GenericGFPoly paramGenericGFPoly2, int paramInt)
    throws ReedSolomonException
  {
    Object localObject = paramGenericGFPoly1;
    GenericGFPoly localGenericGFPoly1 = paramGenericGFPoly2;
    if (paramGenericGFPoly1.getDegree() < paramGenericGFPoly2.getDegree())
    {
      localGenericGFPoly1 = paramGenericGFPoly1;
      localObject = paramGenericGFPoly2;
    }
    GenericGFPoly localGenericGFPoly2 = this.field.getZero();
    paramGenericGFPoly2 = this.field.getOne();
    GenericGFPoly localGenericGFPoly3;
    do
    {
      GenericGFPoly localGenericGFPoly4 = localGenericGFPoly2;
      if (localGenericGFPoly1.getDegree() < paramInt / 2) {
        break;
      }
      localGenericGFPoly3 = localGenericGFPoly1;
      localGenericGFPoly2 = paramGenericGFPoly2;
      if (localGenericGFPoly3.isZero()) {
        throw new ReedSolomonException("r_{i-1} was zero");
      }
      paramGenericGFPoly1 = (GenericGFPoly)localObject;
      paramGenericGFPoly2 = this.field.getZero();
      int i = localGenericGFPoly3.getCoefficient(localGenericGFPoly3.getDegree());
      i = this.field.inverse(i);
      while ((paramGenericGFPoly1.getDegree() >= localGenericGFPoly3.getDegree()) && (!paramGenericGFPoly1.isZero()))
      {
        int j = paramGenericGFPoly1.getDegree() - localGenericGFPoly3.getDegree();
        int k = this.field.multiply(paramGenericGFPoly1.getCoefficient(paramGenericGFPoly1.getDegree()), i);
        paramGenericGFPoly2 = paramGenericGFPoly2.addOrSubtract(this.field.buildMonomial(j, k));
        paramGenericGFPoly1 = paramGenericGFPoly1.addOrSubtract(localGenericGFPoly3.multiplyByMonomial(j, k));
      }
      paramGenericGFPoly2 = paramGenericGFPoly2.multiply(localGenericGFPoly2).addOrSubtract(localGenericGFPoly4);
      localGenericGFPoly1 = paramGenericGFPoly1;
      localObject = localGenericGFPoly3;
    } while (paramGenericGFPoly1.getDegree() < localGenericGFPoly3.getDegree());
    throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
    paramInt = paramGenericGFPoly2.getCoefficient(0);
    if (paramInt == 0) {
      throw new ReedSolomonException("sigmaTilde(0) was zero");
    }
    paramInt = this.field.inverse(paramInt);
    return new GenericGFPoly[] { paramGenericGFPoly2.multiply(paramInt), localGenericGFPoly1.multiply(paramInt) };
  }
  
  public void decode(int[] paramArrayOfInt, int paramInt)
    throws ReedSolomonException
  {
    Object localObject1 = new GenericGFPoly(this.field, paramArrayOfInt);
    Object localObject2 = new int[paramInt];
    int j = 1;
    int i = 0;
    while (i < paramInt)
    {
      int k = ((GenericGFPoly)localObject1).evaluateAt(this.field.exp(this.field.getGeneratorBase() + i));
      localObject2[(localObject2.length - 1 - i)] = k;
      if (k != 0) {
        j = 0;
      }
      i += 1;
    }
    if (j != 0) {}
    for (;;)
    {
      return;
      localObject1 = new GenericGFPoly(this.field, (int[])localObject2);
      localObject2 = runEuclideanAlgorithm(this.field.buildMonomial(paramInt, 1), (GenericGFPoly)localObject1, paramInt);
      localObject1 = localObject2[0];
      localObject2 = localObject2[1];
      localObject1 = findErrorLocations((GenericGFPoly)localObject1);
      localObject2 = findErrorMagnitudes((GenericGFPoly)localObject2, (int[])localObject1);
      paramInt = 0;
      while (paramInt < localObject1.length)
      {
        i = paramArrayOfInt.length - 1 - this.field.log(localObject1[paramInt]);
        if (i < 0) {
          throw new ReedSolomonException("Bad error location");
        }
        paramArrayOfInt[i] = GenericGF.addOrSubtract(paramArrayOfInt[i], localObject2[paramInt]);
        paramInt += 1;
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\common\reedsolomon\ReedSolomonDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */