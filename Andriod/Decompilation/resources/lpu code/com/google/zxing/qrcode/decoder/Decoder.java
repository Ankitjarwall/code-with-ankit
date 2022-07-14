package com.google.zxing.qrcode.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import java.util.Map;

public final class Decoder
{
  private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.QR_CODE_FIELD_256);
  
  private void correctErrors(byte[] paramArrayOfByte, int paramInt)
    throws ChecksumException
  {
    int j = paramArrayOfByte.length;
    int[] arrayOfInt = new int[j];
    int i = 0;
    while (i < j)
    {
      paramArrayOfByte[i] &= 0xFF;
      i += 1;
    }
    try
    {
      this.rsDecoder.decode(arrayOfInt, paramArrayOfByte.length - paramInt);
      i = 0;
      while (i < paramInt)
      {
        paramArrayOfByte[i] = ((byte)arrayOfInt[i]);
        i += 1;
      }
      return;
    }
    catch (ReedSolomonException paramArrayOfByte)
    {
      throw ChecksumException.getChecksumInstance();
    }
  }
  
  private DecoderResult decode(BitMatrixParser paramBitMatrixParser, Map<DecodeHintType, ?> paramMap)
    throws FormatException, ChecksumException
  {
    Version localVersion = paramBitMatrixParser.readVersion();
    ErrorCorrectionLevel localErrorCorrectionLevel = paramBitMatrixParser.readFormatInformation().getErrorCorrectionLevel();
    paramBitMatrixParser = DataBlock.getDataBlocks(paramBitMatrixParser.readCodewords(), localVersion, localErrorCorrectionLevel);
    int j = 0;
    int k = paramBitMatrixParser.length;
    int i = 0;
    while (i < k)
    {
      j += paramBitMatrixParser[i].getNumDataCodewords();
      i += 1;
    }
    byte[] arrayOfByte1 = new byte[j];
    i = 0;
    int m = paramBitMatrixParser.length;
    j = 0;
    while (j < m)
    {
      Object localObject = paramBitMatrixParser[j];
      byte[] arrayOfByte2 = ((DataBlock)localObject).getCodewords();
      int n = ((DataBlock)localObject).getNumDataCodewords();
      correctErrors(arrayOfByte2, n);
      k = 0;
      while (k < n)
      {
        arrayOfByte1[i] = arrayOfByte2[k];
        k += 1;
        i += 1;
      }
      j += 1;
    }
    return DecodedBitStreamParser.decode(arrayOfByte1, localVersion, localErrorCorrectionLevel, paramMap);
  }
  
  public DecoderResult decode(BitMatrix paramBitMatrix)
    throws ChecksumException, FormatException
  {
    return decode(paramBitMatrix, null);
  }
  
  /* Error */
  public DecoderResult decode(BitMatrix paramBitMatrix, Map<DecodeHintType, ?> paramMap)
    throws FormatException, ChecksumException
  {
    // Byte code:
    //   0: new 44	com/google/zxing/qrcode/decoder/BitMatrixParser
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 91	com/google/zxing/qrcode/decoder/BitMatrixParser:<init>	(Lcom/google/zxing/common/BitMatrix;)V
    //   8: astore 4
    //   10: aconst_null
    //   11: astore_3
    //   12: aconst_null
    //   13: astore_1
    //   14: aload_0
    //   15: aload 4
    //   17: aload_2
    //   18: invokespecial 93	com/google/zxing/qrcode/decoder/Decoder:decode	(Lcom/google/zxing/qrcode/decoder/BitMatrixParser;Ljava/util/Map;)Lcom/google/zxing/common/DecoderResult;
    //   21: astore 5
    //   23: aload 5
    //   25: areturn
    //   26: astore_3
    //   27: aload 4
    //   29: invokevirtual 96	com/google/zxing/qrcode/decoder/BitMatrixParser:remask	()V
    //   32: aload 4
    //   34: iconst_1
    //   35: invokevirtual 100	com/google/zxing/qrcode/decoder/BitMatrixParser:setMirror	(Z)V
    //   38: aload 4
    //   40: invokevirtual 48	com/google/zxing/qrcode/decoder/BitMatrixParser:readVersion	()Lcom/google/zxing/qrcode/decoder/Version;
    //   43: pop
    //   44: aload 4
    //   46: invokevirtual 52	com/google/zxing/qrcode/decoder/BitMatrixParser:readFormatInformation	()Lcom/google/zxing/qrcode/decoder/FormatInformation;
    //   49: pop
    //   50: aload 4
    //   52: invokevirtual 103	com/google/zxing/qrcode/decoder/BitMatrixParser:mirror	()V
    //   55: aload_0
    //   56: aload 4
    //   58: aload_2
    //   59: invokespecial 93	com/google/zxing/qrcode/decoder/Decoder:decode	(Lcom/google/zxing/qrcode/decoder/BitMatrixParser;Ljava/util/Map;)Lcom/google/zxing/common/DecoderResult;
    //   62: astore_2
    //   63: aload_2
    //   64: new 105	com/google/zxing/qrcode/decoder/QRCodeDecoderMetaData
    //   67: dup
    //   68: iconst_1
    //   69: invokespecial 107	com/google/zxing/qrcode/decoder/QRCodeDecoderMetaData:<init>	(Z)V
    //   72: invokevirtual 113	com/google/zxing/common/DecoderResult:setOther	(Ljava/lang/Object;)V
    //   75: aload_2
    //   76: areturn
    //   77: astore_2
    //   78: aload_3
    //   79: ifnull +9 -> 88
    //   82: aload_3
    //   83: athrow
    //   84: astore_1
    //   85: goto -58 -> 27
    //   88: aload_1
    //   89: ifnull +5 -> 94
    //   92: aload_1
    //   93: athrow
    //   94: aload_2
    //   95: athrow
    //   96: astore_2
    //   97: goto -19 -> 78
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	100	0	this	Decoder
    //   0	100	1	paramBitMatrix	BitMatrix
    //   0	100	2	paramMap	Map<DecodeHintType, ?>
    //   11	1	3	localObject	Object
    //   26	57	3	localFormatException	FormatException
    //   8	49	4	localBitMatrixParser	BitMatrixParser
    //   21	3	5	localDecoderResult	DecoderResult
    // Exception table:
    //   from	to	target	type
    //   14	23	26	com/google/zxing/FormatException
    //   27	75	77	com/google/zxing/FormatException
    //   14	23	84	com/google/zxing/ChecksumException
    //   27	75	96	com/google/zxing/ChecksumException
  }
  
  public DecoderResult decode(boolean[][] paramArrayOfBoolean)
    throws ChecksumException, FormatException
  {
    return decode(paramArrayOfBoolean, null);
  }
  
  public DecoderResult decode(boolean[][] paramArrayOfBoolean, Map<DecodeHintType, ?> paramMap)
    throws ChecksumException, FormatException
  {
    int k = paramArrayOfBoolean.length;
    BitMatrix localBitMatrix = new BitMatrix(k);
    int i = 0;
    while (i < k)
    {
      int j = 0;
      while (j < k)
      {
        if (paramArrayOfBoolean[i][j] != 0) {
          localBitMatrix.set(j, i);
        }
        j += 1;
      }
      i += 1;
    }
    return decode(localBitMatrix, paramMap);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\qrcode\decoder\Decoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */