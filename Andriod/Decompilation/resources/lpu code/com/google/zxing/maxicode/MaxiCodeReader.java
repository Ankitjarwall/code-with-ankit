package com.google.zxing.maxicode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.maxicode.decoder.Decoder;
import java.util.Map;

public final class MaxiCodeReader
  implements Reader
{
  private static final int MATRIX_HEIGHT = 33;
  private static final int MATRIX_WIDTH = 30;
  private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
  private final Decoder decoder = new Decoder();
  
  private static BitMatrix extractPureBits(BitMatrix paramBitMatrix)
    throws NotFoundException
  {
    Object localObject = paramBitMatrix.getEnclosingRectangle();
    if (localObject == null) {
      throw NotFoundException.getNotFoundInstance();
    }
    int k = localObject[0];
    int m = localObject[1];
    int n = localObject[2];
    int i1 = localObject[3];
    localObject = new BitMatrix(30, 33);
    int i = 0;
    while (i < 33)
    {
      int i2 = (i * i1 + i1 / 2) / 33;
      int j = 0;
      while (j < 30)
      {
        if (paramBitMatrix.get(k + (j * n + n / 2 + (i & 0x1) * n / 2) / 30, m + i2)) {
          ((BitMatrix)localObject).set(j, i);
        }
        j += 1;
      }
      i += 1;
    }
    return (BitMatrix)localObject;
  }
  
  public Result decode(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException, ChecksumException, FormatException
  {
    return decode(paramBinaryBitmap, null);
  }
  
  public Result decode(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException, ChecksumException, FormatException
  {
    if ((paramMap != null) && (paramMap.containsKey(DecodeHintType.PURE_BARCODE)))
    {
      paramBinaryBitmap = extractPureBits(paramBinaryBitmap.getBlackMatrix());
      paramMap = this.decoder.decode(paramBinaryBitmap, paramMap);
      paramBinaryBitmap = new Result(paramMap.getText(), paramMap.getRawBytes(), NO_POINTS, BarcodeFormat.MAXICODE);
      paramMap = paramMap.getECLevel();
      if (paramMap != null) {
        paramBinaryBitmap.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, paramMap);
      }
      return paramBinaryBitmap;
    }
    throw NotFoundException.getNotFoundInstance();
  }
  
  public void reset() {}
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\maxicode\MaxiCodeReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */