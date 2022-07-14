package com.google.zxing.aztec;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;

public final class AztecReader
  implements Reader
{
  public Result decode(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException, FormatException
  {
    return decode(paramBinaryBitmap, null);
  }
  
  /* Error */
  public Result decode(BinaryBitmap paramBinaryBitmap, java.util.Map<com.google.zxing.DecodeHintType, ?> paramMap)
    throws NotFoundException, FormatException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 8
    //   3: aconst_null
    //   4: astore 9
    //   6: new 23	com/google/zxing/aztec/detector/Detector
    //   9: dup
    //   10: aload_1
    //   11: invokevirtual 29	com/google/zxing/BinaryBitmap:getBlackMatrix	()Lcom/google/zxing/common/BitMatrix;
    //   14: invokespecial 32	com/google/zxing/aztec/detector/Detector:<init>	(Lcom/google/zxing/common/BitMatrix;)V
    //   17: astore 11
    //   19: aconst_null
    //   20: astore 7
    //   22: aconst_null
    //   23: astore_1
    //   24: aconst_null
    //   25: astore 10
    //   27: aload_1
    //   28: astore 5
    //   30: aload 7
    //   32: astore 6
    //   34: aload 11
    //   36: iconst_0
    //   37: invokevirtual 36	com/google/zxing/aztec/detector/Detector:detect	(Z)Lcom/google/zxing/aztec/AztecDetectorResult;
    //   40: astore 12
    //   42: aload_1
    //   43: astore 5
    //   45: aload 7
    //   47: astore 6
    //   49: aload 12
    //   51: invokevirtual 42	com/google/zxing/aztec/AztecDetectorResult:getPoints	()[Lcom/google/zxing/ResultPoint;
    //   54: astore_1
    //   55: aload_1
    //   56: astore 5
    //   58: aload_1
    //   59: astore 6
    //   61: new 44	com/google/zxing/aztec/decoder/Decoder
    //   64: dup
    //   65: invokespecial 45	com/google/zxing/aztec/decoder/Decoder:<init>	()V
    //   68: aload 12
    //   70: invokevirtual 48	com/google/zxing/aztec/decoder/Decoder:decode	(Lcom/google/zxing/aztec/AztecDetectorResult;)Lcom/google/zxing/common/DecoderResult;
    //   73: astore 7
    //   75: aload 7
    //   77: astore 5
    //   79: aload 8
    //   81: astore 7
    //   83: aload 9
    //   85: astore 6
    //   87: aload_1
    //   88: astore 8
    //   90: aload 5
    //   92: astore_1
    //   93: aload 5
    //   95: ifnonnull +28 -> 123
    //   98: aload 11
    //   100: iconst_1
    //   101: invokevirtual 36	com/google/zxing/aztec/detector/Detector:detect	(Z)Lcom/google/zxing/aztec/AztecDetectorResult;
    //   104: astore_1
    //   105: aload_1
    //   106: invokevirtual 42	com/google/zxing/aztec/AztecDetectorResult:getPoints	()[Lcom/google/zxing/ResultPoint;
    //   109: astore 8
    //   111: new 44	com/google/zxing/aztec/decoder/Decoder
    //   114: dup
    //   115: invokespecial 45	com/google/zxing/aztec/decoder/Decoder:<init>	()V
    //   118: aload_1
    //   119: invokevirtual 48	com/google/zxing/aztec/decoder/Decoder:decode	(Lcom/google/zxing/aztec/AztecDetectorResult;)Lcom/google/zxing/common/DecoderResult;
    //   122: astore_1
    //   123: aload_2
    //   124: ifnull +105 -> 229
    //   127: aload_2
    //   128: getstatic 54	com/google/zxing/DecodeHintType:NEED_RESULT_POINT_CALLBACK	Lcom/google/zxing/DecodeHintType;
    //   131: invokeinterface 60 2 0
    //   136: checkcast 62	com/google/zxing/ResultPointCallback
    //   139: astore_2
    //   140: aload_2
    //   141: ifnull +88 -> 229
    //   144: aload 8
    //   146: arraylength
    //   147: istore 4
    //   149: iconst_0
    //   150: istore_3
    //   151: iload_3
    //   152: iload 4
    //   154: if_icmpge +75 -> 229
    //   157: aload_2
    //   158: aload 8
    //   160: iload_3
    //   161: aaload
    //   162: invokeinterface 66 2 0
    //   167: iload_3
    //   168: iconst_1
    //   169: iadd
    //   170: istore_3
    //   171: goto -20 -> 151
    //   174: astore 7
    //   176: aload 5
    //   178: astore_1
    //   179: aload 10
    //   181: astore 5
    //   183: aload 9
    //   185: astore 6
    //   187: goto -100 -> 87
    //   190: astore 7
    //   192: aload 6
    //   194: astore_1
    //   195: aload 10
    //   197: astore 5
    //   199: aload 7
    //   201: astore 6
    //   203: aload 8
    //   205: astore 7
    //   207: goto -120 -> 87
    //   210: astore_1
    //   211: aload 7
    //   213: ifnull +6 -> 219
    //   216: aload 7
    //   218: athrow
    //   219: aload 6
    //   221: ifnull +6 -> 227
    //   224: aload 6
    //   226: athrow
    //   227: aload_1
    //   228: athrow
    //   229: new 68	com/google/zxing/Result
    //   232: dup
    //   233: aload_1
    //   234: invokevirtual 74	com/google/zxing/common/DecoderResult:getText	()Ljava/lang/String;
    //   237: aload_1
    //   238: invokevirtual 78	com/google/zxing/common/DecoderResult:getRawBytes	()[B
    //   241: aload_1
    //   242: invokevirtual 82	com/google/zxing/common/DecoderResult:getNumBits	()I
    //   245: aload 8
    //   247: getstatic 88	com/google/zxing/BarcodeFormat:AZTEC	Lcom/google/zxing/BarcodeFormat;
    //   250: invokestatic 94	java/lang/System:currentTimeMillis	()J
    //   253: invokespecial 97	com/google/zxing/Result:<init>	(Ljava/lang/String;[BI[Lcom/google/zxing/ResultPoint;Lcom/google/zxing/BarcodeFormat;J)V
    //   256: astore_2
    //   257: aload_1
    //   258: invokevirtual 101	com/google/zxing/common/DecoderResult:getByteSegments	()Ljava/util/List;
    //   261: astore 5
    //   263: aload 5
    //   265: ifnull +12 -> 277
    //   268: aload_2
    //   269: getstatic 107	com/google/zxing/ResultMetadataType:BYTE_SEGMENTS	Lcom/google/zxing/ResultMetadataType;
    //   272: aload 5
    //   274: invokevirtual 111	com/google/zxing/Result:putMetadata	(Lcom/google/zxing/ResultMetadataType;Ljava/lang/Object;)V
    //   277: aload_1
    //   278: invokevirtual 114	com/google/zxing/common/DecoderResult:getECLevel	()Ljava/lang/String;
    //   281: astore_1
    //   282: aload_1
    //   283: ifnull +11 -> 294
    //   286: aload_2
    //   287: getstatic 117	com/google/zxing/ResultMetadataType:ERROR_CORRECTION_LEVEL	Lcom/google/zxing/ResultMetadataType;
    //   290: aload_1
    //   291: invokevirtual 111	com/google/zxing/Result:putMetadata	(Lcom/google/zxing/ResultMetadataType;Ljava/lang/Object;)V
    //   294: aload_2
    //   295: areturn
    //   296: astore_1
    //   297: goto -86 -> 211
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	300	0	this	AztecReader
    //   0	300	1	paramBinaryBitmap	BinaryBitmap
    //   0	300	2	paramMap	java.util.Map<com.google.zxing.DecodeHintType, ?>
    //   150	21	3	i	int
    //   147	8	4	j	int
    //   28	245	5	localObject1	Object
    //   32	193	6	localObject2	Object
    //   20	62	7	localObject3	Object
    //   174	1	7	localNotFoundException	NotFoundException
    //   190	10	7	localFormatException	FormatException
    //   205	12	7	localObject4	Object
    //   1	245	8	localObject5	Object
    //   4	180	9	localObject6	Object
    //   25	171	10	localObject7	Object
    //   17	82	11	localDetector	com.google.zxing.aztec.detector.Detector
    //   40	29	12	localAztecDetectorResult	AztecDetectorResult
    // Exception table:
    //   from	to	target	type
    //   34	42	174	com/google/zxing/NotFoundException
    //   49	55	174	com/google/zxing/NotFoundException
    //   61	75	174	com/google/zxing/NotFoundException
    //   34	42	190	com/google/zxing/FormatException
    //   49	55	190	com/google/zxing/FormatException
    //   61	75	190	com/google/zxing/FormatException
    //   98	123	210	com/google/zxing/NotFoundException
    //   98	123	296	com/google/zxing/FormatException
  }
  
  public void reset() {}
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\aztec\AztecReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */