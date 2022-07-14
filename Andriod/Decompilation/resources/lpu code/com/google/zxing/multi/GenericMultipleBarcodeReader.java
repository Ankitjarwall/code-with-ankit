package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class GenericMultipleBarcodeReader
  implements MultipleBarcodeReader
{
  private static final int MAX_DEPTH = 4;
  private static final int MIN_DIMENSION_TO_RECUR = 100;
  private final Reader delegate;
  
  public GenericMultipleBarcodeReader(Reader paramReader)
  {
    this.delegate = paramReader;
  }
  
  private void doDecodeMultiple(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap, List<Result> paramList, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt3 > 4) {}
    int j;
    int k;
    float f5;
    label306:
    do
    {
      return;
      float f3;
      float f4;
      float f2;
      for (;;)
      {
        Iterator localIterator;
        try
        {
          Object localObject = this.delegate.decode(paramBinaryBitmap, paramMap);
          j = 0;
          localIterator = paramList.iterator();
          int i = j;
          if (localIterator.hasNext())
          {
            if (!((Result)localIterator.next()).getText().equals(((Result)localObject).getText())) {
              continue;
            }
            i = 1;
          }
          if (i == 0) {
            paramList.add(translateResultPoints((Result)localObject, paramInt1, paramInt2));
          }
          localObject = ((Result)localObject).getResultPoints();
          if ((localObject == null) || (localObject.length == 0)) {
            break;
          }
          j = paramBinaryBitmap.getWidth();
          k = paramBinaryBitmap.getHeight();
          f3 = j;
          f4 = k;
          f2 = 0.0F;
          f5 = 0.0F;
          int m = localObject.length;
          i = 0;
          if (i >= m) {
            break label306;
          }
          localIterator = localObject[i];
          if (localIterator == null)
          {
            f7 = f4;
            f9 = f3;
            f8 = f5;
            i += 1;
            f5 = f8;
            f3 = f9;
            f4 = f7;
            continue;
          }
          f7 = localIterator.getX();
        }
        catch (ReaderException paramBinaryBitmap)
        {
          return;
        }
        float f6 = localIterator.getY();
        float f1 = f3;
        if (f7 < f3) {
          f1 = f7;
        }
        f3 = f4;
        if (f6 < f4) {
          f3 = f6;
        }
        f4 = f2;
        if (f7 > f2) {
          f4 = f7;
        }
        f2 = f4;
        float f8 = f5;
        float f9 = f1;
        float f7 = f3;
        if (f6 > f5)
        {
          f2 = f4;
          f8 = f6;
          f9 = f1;
          f7 = f3;
        }
      }
      if (f3 > 100.0F) {
        doDecodeMultiple(paramBinaryBitmap.crop(0, 0, (int)f3, k), paramMap, paramList, paramInt1, paramInt2, paramInt3 + 1);
      }
      if (f4 > 100.0F) {
        doDecodeMultiple(paramBinaryBitmap.crop(0, 0, j, (int)f4), paramMap, paramList, paramInt1, paramInt2, paramInt3 + 1);
      }
      if (f2 < j - 100) {
        doDecodeMultiple(paramBinaryBitmap.crop((int)f2, 0, j - (int)f2, k), paramMap, paramList, paramInt1 + (int)f2, paramInt2, paramInt3 + 1);
      }
    } while (f5 >= k - 100);
    doDecodeMultiple(paramBinaryBitmap.crop(0, (int)f5, j, k - (int)f5), paramMap, paramList, paramInt1, paramInt2 + (int)f5, paramInt3 + 1);
  }
  
  private static Result translateResultPoints(Result paramResult, int paramInt1, int paramInt2)
  {
    Object localObject1 = paramResult.getResultPoints();
    if (localObject1 == null) {
      return paramResult;
    }
    ResultPoint[] arrayOfResultPoint = new ResultPoint[localObject1.length];
    int i = 0;
    while (i < localObject1.length)
    {
      Object localObject2 = localObject1[i];
      if (localObject2 != null) {
        arrayOfResultPoint[i] = new ResultPoint(((ResultPoint)localObject2).getX() + paramInt1, ((ResultPoint)localObject2).getY() + paramInt2);
      }
      i += 1;
    }
    localObject1 = new Result(paramResult.getText(), paramResult.getRawBytes(), paramResult.getNumBits(), arrayOfResultPoint, paramResult.getBarcodeFormat(), paramResult.getTimestamp());
    ((Result)localObject1).putAllMetadata(paramResult.getResultMetadata());
    return (Result)localObject1;
  }
  
  public Result[] decodeMultiple(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException
  {
    return decodeMultiple(paramBinaryBitmap, null);
  }
  
  public Result[] decodeMultiple(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException
  {
    ArrayList localArrayList = new ArrayList();
    doDecodeMultiple(paramBinaryBitmap, paramMap, localArrayList, 0, 0, 0);
    if (localArrayList.isEmpty()) {
      throw NotFoundException.getNotFoundInstance();
    }
    return (Result[])localArrayList.toArray(new Result[localArrayList.size()]);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\multi\GenericMultipleBarcodeReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */