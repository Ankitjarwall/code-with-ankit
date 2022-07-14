package com.google.zxing.pdf417.decoder;

import com.google.zxing.ResultPoint;

final class DetectionResultRowIndicatorColumn
  extends DetectionResultColumn
{
  private final boolean isLeft;
  
  DetectionResultRowIndicatorColumn(BoundingBox paramBoundingBox, boolean paramBoolean)
  {
    super(paramBoundingBox);
    this.isLeft = paramBoolean;
  }
  
  private void adjustIncompleteIndicatorColumnRowNumbers(BarcodeMetadata paramBarcodeMetadata)
  {
    Object localObject2 = getBoundingBox();
    Object localObject1;
    label34:
    int k;
    int j;
    int m;
    int i;
    if (this.isLeft)
    {
      localObject1 = ((BoundingBox)localObject2).getTopLeft();
      if (!this.isLeft) {
        break label105;
      }
      localObject2 = ((BoundingBox)localObject2).getBottomLeft();
      k = imageRowToCodewordIndex((int)((ResultPoint)localObject1).getY());
      int n = imageRowToCodewordIndex((int)((ResultPoint)localObject2).getY());
      localObject1 = getCodewords();
      j = -1;
      m = 1;
      i = 0;
      label71:
      if (k >= n) {
        return;
      }
      if (localObject1[k] != null) {
        break label115;
      }
    }
    for (;;)
    {
      k += 1;
      break label71;
      localObject1 = ((BoundingBox)localObject2).getTopRight();
      break;
      label105:
      localObject2 = ((BoundingBox)localObject2).getBottomRight();
      break label34;
      label115:
      localObject2 = localObject1[k];
      ((Codeword)localObject2).setRowNumberAsRowIndicatorColumn();
      int i1 = ((Codeword)localObject2).getRowNumber() - j;
      if (i1 == 0)
      {
        i += 1;
      }
      else if (i1 == 1)
      {
        m = Math.max(m, i);
        i = 1;
        j = ((Codeword)localObject2).getRowNumber();
      }
      else if (((Codeword)localObject2).getRowNumber() >= paramBarcodeMetadata.getRowCount())
      {
        localObject1[k] = null;
      }
      else
      {
        j = ((Codeword)localObject2).getRowNumber();
        i = 1;
      }
    }
  }
  
  private void removeIncorrectCodewords(Codeword[] paramArrayOfCodeword, BarcodeMetadata paramBarcodeMetadata)
  {
    int i = 0;
    if (i < paramArrayOfCodeword.length)
    {
      Codeword localCodeword = paramArrayOfCodeword[i];
      if (paramArrayOfCodeword[i] == null) {}
      for (;;)
      {
        i += 1;
        break;
        int m = localCodeword.getValue() % 30;
        int k = localCodeword.getRowNumber();
        if (k > paramBarcodeMetadata.getRowCount())
        {
          paramArrayOfCodeword[i] = null;
        }
        else
        {
          int j = k;
          if (!this.isLeft) {
            j = k + 2;
          }
          switch (j % 3)
          {
          default: 
            break;
          case 0: 
            if (m * 3 + 1 != paramBarcodeMetadata.getRowCountUpperPart()) {
              paramArrayOfCodeword[i] = null;
            }
            break;
          case 1: 
            if ((m / 3 != paramBarcodeMetadata.getErrorCorrectionLevel()) || (m % 3 != paramBarcodeMetadata.getRowCountLowerPart())) {
              paramArrayOfCodeword[i] = null;
            }
            break;
          case 2: 
            if (m + 1 != paramBarcodeMetadata.getColumnCount()) {
              paramArrayOfCodeword[i] = null;
            }
            break;
          }
        }
      }
    }
  }
  
  private void setRowNumbers()
  {
    Codeword[] arrayOfCodeword = getCodewords();
    int j = arrayOfCodeword.length;
    int i = 0;
    while (i < j)
    {
      Codeword localCodeword = arrayOfCodeword[i];
      if (localCodeword != null) {
        localCodeword.setRowNumberAsRowIndicatorColumn();
      }
      i += 1;
    }
  }
  
  void adjustCompleteIndicatorColumnRowNumbers(BarcodeMetadata paramBarcodeMetadata)
  {
    Codeword[] arrayOfCodeword = getCodewords();
    setRowNumbers();
    removeIncorrectCodewords(arrayOfCodeword, paramBarcodeMetadata);
    Object localObject2 = getBoundingBox();
    Object localObject1;
    label51:
    int k;
    int j;
    int m;
    int n;
    label83:
    int i;
    if (this.isLeft)
    {
      localObject1 = ((BoundingBox)localObject2).getTopLeft();
      if (!this.isLeft) {
        break label123;
      }
      localObject2 = ((BoundingBox)localObject2).getBottomLeft();
      k = imageRowToCodewordIndex((int)((ResultPoint)localObject1).getY());
      int i3 = imageRowToCodewordIndex((int)((ResultPoint)localObject2).getY());
      j = -1;
      m = 1;
      n = 0;
      if (k >= i3) {
        return;
      }
      if (arrayOfCodeword[k] != null) {
        break label133;
      }
      i = n;
    }
    for (;;)
    {
      k += 1;
      n = i;
      break label83;
      localObject1 = ((BoundingBox)localObject2).getTopRight();
      break;
      label123:
      localObject2 = ((BoundingBox)localObject2).getBottomRight();
      break label51;
      label133:
      localObject1 = arrayOfCodeword[k];
      int i1 = ((Codeword)localObject1).getRowNumber() - j;
      if (i1 == 0)
      {
        i = n + 1;
      }
      else if (i1 == 1)
      {
        m = Math.max(m, n);
        i = 1;
        j = ((Codeword)localObject1).getRowNumber();
      }
      else if ((i1 < 0) || (((Codeword)localObject1).getRowNumber() >= paramBarcodeMetadata.getRowCount()) || (i1 > k))
      {
        arrayOfCodeword[k] = null;
        i = n;
      }
      else
      {
        label248:
        int i2;
        if (m > 2)
        {
          i1 = (m - 2) * i1;
          if (i1 < k) {
            break label287;
          }
          i = 1;
          i2 = 1;
          label251:
          if ((i2 > i1) || (i != 0)) {
            break label297;
          }
          if (arrayOfCodeword[(k - i2)] == null) {
            break label292;
          }
        }
        label287:
        label292:
        for (i = 1;; i = 0)
        {
          i2 += 1;
          break label251;
          break;
          i = 0;
          break label248;
        }
        label297:
        if (i != 0)
        {
          arrayOfCodeword[k] = null;
          i = n;
        }
        else
        {
          j = ((Codeword)localObject1).getRowNumber();
          i = 1;
        }
      }
    }
  }
  
  BarcodeMetadata getBarcodeMetadata()
  {
    Codeword[] arrayOfCodeword = getCodewords();
    Object localObject = new BarcodeValue();
    BarcodeValue localBarcodeValue1 = new BarcodeValue();
    BarcodeValue localBarcodeValue2 = new BarcodeValue();
    BarcodeValue localBarcodeValue3 = new BarcodeValue();
    int m = arrayOfCodeword.length;
    int i = 0;
    if (i < m)
    {
      Codeword localCodeword = arrayOfCodeword[i];
      if (localCodeword == null) {}
      for (;;)
      {
        i += 1;
        break;
        localCodeword.setRowNumberAsRowIndicatorColumn();
        int n = localCodeword.getValue() % 30;
        int k = localCodeword.getRowNumber();
        int j = k;
        if (!this.isLeft) {
          j = k + 2;
        }
        switch (j % 3)
        {
        default: 
          break;
        case 0: 
          localBarcodeValue1.setValue(n * 3 + 1);
          break;
        case 1: 
          localBarcodeValue3.setValue(n / 3);
          localBarcodeValue2.setValue(n % 3);
          break;
        case 2: 
          ((BarcodeValue)localObject).setValue(n + 1);
        }
      }
    }
    if ((((BarcodeValue)localObject).getValue().length == 0) || (localBarcodeValue1.getValue().length == 0) || (localBarcodeValue2.getValue().length == 0) || (localBarcodeValue3.getValue().length == 0) || (localObject.getValue()[0] < 1) || (localBarcodeValue1.getValue()[0] + localBarcodeValue2.getValue()[0] < 3) || (localBarcodeValue1.getValue()[0] + localBarcodeValue2.getValue()[0] > 90)) {
      return null;
    }
    localObject = new BarcodeMetadata(localObject.getValue()[0], localBarcodeValue1.getValue()[0], localBarcodeValue2.getValue()[0], localBarcodeValue3.getValue()[0]);
    removeIncorrectCodewords(arrayOfCodeword, (BarcodeMetadata)localObject);
    return (BarcodeMetadata)localObject;
  }
  
  int[] getRowHeights()
  {
    Object localObject = getBarcodeMetadata();
    if (localObject == null) {
      localObject = null;
    }
    int[] arrayOfInt;
    Codeword[] arrayOfCodeword;
    int j;
    int i;
    do
    {
      return (int[])localObject;
      adjustIncompleteIndicatorColumnRowNumbers((BarcodeMetadata)localObject);
      arrayOfInt = new int[((BarcodeMetadata)localObject).getRowCount()];
      arrayOfCodeword = getCodewords();
      j = arrayOfCodeword.length;
      i = 0;
      localObject = arrayOfInt;
    } while (i >= j);
    localObject = arrayOfCodeword[i];
    int k;
    if (localObject != null)
    {
      k = ((Codeword)localObject).getRowNumber();
      if (k < arrayOfInt.length) {
        break label84;
      }
    }
    for (;;)
    {
      i += 1;
      break;
      label84:
      arrayOfInt[k] += 1;
    }
  }
  
  boolean isLeft()
  {
    return this.isLeft;
  }
  
  public String toString()
  {
    return "IsLeft: " + this.isLeft + '\n' + super.toString();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\pdf417\decoder\DetectionResultRowIndicatorColumn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */