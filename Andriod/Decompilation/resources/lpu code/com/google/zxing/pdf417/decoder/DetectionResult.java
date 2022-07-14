package com.google.zxing.pdf417.decoder;

import java.util.Formatter;

final class DetectionResult
{
  private static final int ADJUST_ROW_NUMBER_SKIP = 2;
  private final int barcodeColumnCount;
  private final BarcodeMetadata barcodeMetadata;
  private BoundingBox boundingBox;
  private final DetectionResultColumn[] detectionResultColumns;
  
  DetectionResult(BarcodeMetadata paramBarcodeMetadata, BoundingBox paramBoundingBox)
  {
    this.barcodeMetadata = paramBarcodeMetadata;
    this.barcodeColumnCount = paramBarcodeMetadata.getColumnCount();
    this.boundingBox = paramBoundingBox;
    this.detectionResultColumns = new DetectionResultColumn[this.barcodeColumnCount + 2];
  }
  
  private void adjustIndicatorColumnRowNumbers(DetectionResultColumn paramDetectionResultColumn)
  {
    if (paramDetectionResultColumn != null) {
      ((DetectionResultRowIndicatorColumn)paramDetectionResultColumn).adjustCompleteIndicatorColumnRowNumbers(this.barcodeMetadata);
    }
  }
  
  private static boolean adjustRowNumber(Codeword paramCodeword1, Codeword paramCodeword2)
  {
    if (paramCodeword2 == null) {}
    while ((!paramCodeword2.hasValidRowNumber()) || (paramCodeword2.getBucket() != paramCodeword1.getBucket())) {
      return false;
    }
    paramCodeword1.setRowNumber(paramCodeword2.getRowNumber());
    return true;
  }
  
  private static int adjustRowNumberIfValid(int paramInt1, int paramInt2, Codeword paramCodeword)
  {
    if (paramCodeword == null) {
      return paramInt2;
    }
    int i = paramInt2;
    if (!paramCodeword.hasValidRowNumber())
    {
      if (!paramCodeword.isValidRowNumber(paramInt1)) {
        break label32;
      }
      paramCodeword.setRowNumber(paramInt1);
    }
    label32:
    for (i = 0;; i = paramInt2 + 1) {
      return i;
    }
  }
  
  private int adjustRowNumbers()
  {
    int k = adjustRowNumbersByRow();
    int j;
    if (k == 0)
    {
      j = 0;
      return j;
    }
    int i = 1;
    for (;;)
    {
      j = k;
      if (i >= this.barcodeColumnCount + 1) {
        break;
      }
      Codeword[] arrayOfCodeword = this.detectionResultColumns[i].getCodewords();
      j = 0;
      if (j < arrayOfCodeword.length)
      {
        if (arrayOfCodeword[j] == null) {}
        for (;;)
        {
          j += 1;
          break;
          if (!arrayOfCodeword[j].hasValidRowNumber()) {
            adjustRowNumbers(i, j, arrayOfCodeword);
          }
        }
      }
      i += 1;
    }
  }
  
  private void adjustRowNumbers(int paramInt1, int paramInt2, Codeword[] paramArrayOfCodeword)
  {
    int i = 0;
    Codeword localCodeword = paramArrayOfCodeword[paramInt2];
    Codeword[] arrayOfCodeword2 = this.detectionResultColumns[(paramInt1 - 1)].getCodewords();
    Codeword[] arrayOfCodeword1 = arrayOfCodeword2;
    if (this.detectionResultColumns[(paramInt1 + 1)] != null) {
      arrayOfCodeword1 = this.detectionResultColumns[(paramInt1 + 1)].getCodewords();
    }
    Codeword[] arrayOfCodeword3 = new Codeword[14];
    arrayOfCodeword3[2] = arrayOfCodeword2[paramInt2];
    arrayOfCodeword3[3] = arrayOfCodeword1[paramInt2];
    if (paramInt2 > 0)
    {
      arrayOfCodeword3[0] = paramArrayOfCodeword[(paramInt2 - 1)];
      arrayOfCodeword3[4] = arrayOfCodeword2[(paramInt2 - 1)];
      arrayOfCodeword3[5] = arrayOfCodeword1[(paramInt2 - 1)];
    }
    if (paramInt2 > 1)
    {
      arrayOfCodeword3[8] = paramArrayOfCodeword[(paramInt2 - 2)];
      arrayOfCodeword3[10] = arrayOfCodeword2[(paramInt2 - 2)];
      arrayOfCodeword3[11] = arrayOfCodeword1[(paramInt2 - 2)];
    }
    if (paramInt2 < paramArrayOfCodeword.length - 1)
    {
      arrayOfCodeword3[1] = paramArrayOfCodeword[(paramInt2 + 1)];
      arrayOfCodeword3[6] = arrayOfCodeword2[(paramInt2 + 1)];
      arrayOfCodeword3[7] = arrayOfCodeword1[(paramInt2 + 1)];
    }
    if (paramInt2 < paramArrayOfCodeword.length - 2)
    {
      arrayOfCodeword3[9] = paramArrayOfCodeword[(paramInt2 + 2)];
      arrayOfCodeword3[12] = arrayOfCodeword2[(paramInt2 + 2)];
      arrayOfCodeword3[13] = arrayOfCodeword1[(paramInt2 + 2)];
    }
    paramInt2 = arrayOfCodeword3.length;
    paramInt1 = i;
    for (;;)
    {
      if ((paramInt1 >= paramInt2) || (adjustRowNumber(localCodeword, arrayOfCodeword3[paramInt1]))) {
        return;
      }
      paramInt1 += 1;
    }
  }
  
  private int adjustRowNumbersByRow()
  {
    adjustRowNumbersFromBothRI();
    int i = adjustRowNumbersFromLRI();
    return adjustRowNumbersFromRRI() + i;
  }
  
  private void adjustRowNumbersFromBothRI()
  {
    if ((this.detectionResultColumns[0] == null) || (this.detectionResultColumns[(this.barcodeColumnCount + 1)] == null)) {}
    for (;;)
    {
      return;
      Codeword[] arrayOfCodeword1 = this.detectionResultColumns[0].getCodewords();
      Codeword[] arrayOfCodeword2 = this.detectionResultColumns[(this.barcodeColumnCount + 1)].getCodewords();
      int i = 0;
      while (i < arrayOfCodeword1.length)
      {
        if ((arrayOfCodeword1[i] != null) && (arrayOfCodeword2[i] != null) && (arrayOfCodeword1[i].getRowNumber() == arrayOfCodeword2[i].getRowNumber()))
        {
          int j = 1;
          if (j <= this.barcodeColumnCount)
          {
            Codeword localCodeword = this.detectionResultColumns[j].getCodewords()[i];
            if (localCodeword == null) {}
            for (;;)
            {
              j += 1;
              break;
              localCodeword.setRowNumber(arrayOfCodeword1[i].getRowNumber());
              if (!localCodeword.hasValidRowNumber()) {
                this.detectionResultColumns[j].getCodewords()[i] = null;
              }
            }
          }
        }
        i += 1;
      }
    }
  }
  
  private int adjustRowNumbersFromLRI()
  {
    if (this.detectionResultColumns[0] == null)
    {
      k = 0;
      return k;
    }
    int i = 0;
    Codeword[] arrayOfCodeword = this.detectionResultColumns[0].getCodewords();
    int j = 0;
    int n;
    for (;;)
    {
      k = i;
      if (j >= arrayOfCodeword.length) {
        break;
      }
      if (arrayOfCodeword[j] != null) {
        break label57;
      }
      n = i;
      j += 1;
      i = n;
    }
    label57:
    int i2 = arrayOfCodeword[j].getRowNumber();
    int m = 0;
    int k = 1;
    for (;;)
    {
      n = i;
      if (k >= this.barcodeColumnCount + 1) {
        break;
      }
      n = i;
      if (m >= 2) {
        break;
      }
      Codeword localCodeword = this.detectionResultColumns[k].getCodewords()[j];
      n = m;
      int i1 = i;
      if (localCodeword != null)
      {
        m = adjustRowNumberIfValid(i2, m, localCodeword);
        n = m;
        i1 = i;
        if (!localCodeword.hasValidRowNumber())
        {
          i1 = i + 1;
          n = m;
        }
      }
      k += 1;
      m = n;
      i = i1;
    }
  }
  
  private int adjustRowNumbersFromRRI()
  {
    if (this.detectionResultColumns[(this.barcodeColumnCount + 1)] == null)
    {
      k = 0;
      return k;
    }
    int i = 0;
    Codeword[] arrayOfCodeword = this.detectionResultColumns[(this.barcodeColumnCount + 1)].getCodewords();
    int j = 0;
    int n;
    for (;;)
    {
      k = i;
      if (j >= arrayOfCodeword.length) {
        break;
      }
      if (arrayOfCodeword[j] != null) {
        break label67;
      }
      n = i;
      j += 1;
      i = n;
    }
    label67:
    int i2 = arrayOfCodeword[j].getRowNumber();
    int m = 0;
    int k = this.barcodeColumnCount + 1;
    for (;;)
    {
      n = i;
      if (k <= 0) {
        break;
      }
      n = i;
      if (m >= 2) {
        break;
      }
      Codeword localCodeword = this.detectionResultColumns[k].getCodewords()[j];
      n = m;
      int i1 = i;
      if (localCodeword != null)
      {
        m = adjustRowNumberIfValid(i2, m, localCodeword);
        n = m;
        i1 = i;
        if (!localCodeword.hasValidRowNumber())
        {
          i1 = i + 1;
          n = m;
        }
      }
      k -= 1;
      m = n;
      i = i1;
    }
  }
  
  int getBarcodeColumnCount()
  {
    return this.barcodeColumnCount;
  }
  
  int getBarcodeECLevel()
  {
    return this.barcodeMetadata.getErrorCorrectionLevel();
  }
  
  int getBarcodeRowCount()
  {
    return this.barcodeMetadata.getRowCount();
  }
  
  BoundingBox getBoundingBox()
  {
    return this.boundingBox;
  }
  
  DetectionResultColumn getDetectionResultColumn(int paramInt)
  {
    return this.detectionResultColumns[paramInt];
  }
  
  DetectionResultColumn[] getDetectionResultColumns()
  {
    adjustIndicatorColumnRowNumbers(this.detectionResultColumns[0]);
    adjustIndicatorColumnRowNumbers(this.detectionResultColumns[(this.barcodeColumnCount + 1)]);
    int i = 928;
    int j;
    int k;
    do
    {
      j = i;
      k = adjustRowNumbers();
      if (k <= 0) {
        break;
      }
      i = k;
    } while (k < j);
    return this.detectionResultColumns;
  }
  
  public void setBoundingBox(BoundingBox paramBoundingBox)
  {
    this.boundingBox = paramBoundingBox;
  }
  
  void setDetectionResultColumn(int paramInt, DetectionResultColumn paramDetectionResultColumn)
  {
    this.detectionResultColumns[paramInt] = paramDetectionResultColumn;
  }
  
  public String toString()
  {
    Object localObject2 = this.detectionResultColumns[0];
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = this.detectionResultColumns[(this.barcodeColumnCount + 1)];
    }
    localObject2 = new Formatter();
    int i = 0;
    while (i < ((DetectionResultColumn)localObject1).getCodewords().length)
    {
      ((Formatter)localObject2).format("CW %3d:", new Object[] { Integer.valueOf(i) });
      int j = 0;
      if (j < this.barcodeColumnCount + 2)
      {
        if (this.detectionResultColumns[j] == null) {
          ((Formatter)localObject2).format("    |   ", new Object[0]);
        }
        for (;;)
        {
          j += 1;
          break;
          Codeword localCodeword = this.detectionResultColumns[j].getCodewords()[i];
          if (localCodeword == null) {
            ((Formatter)localObject2).format("    |   ", new Object[0]);
          } else {
            ((Formatter)localObject2).format(" %3d|%3d", new Object[] { Integer.valueOf(localCodeword.getRowNumber()), Integer.valueOf(localCodeword.getValue()) });
          }
        }
      }
      ((Formatter)localObject2).format("%n", new Object[0]);
      i += 1;
    }
    localObject1 = ((Formatter)localObject2).toString();
    ((Formatter)localObject2).close();
    return (String)localObject1;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\pdf417\decoder\DetectionResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */