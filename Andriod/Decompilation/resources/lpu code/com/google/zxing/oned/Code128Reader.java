package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Code128Reader
  extends OneDReader
{
  private static final int CODE_CODE_A = 101;
  private static final int CODE_CODE_B = 100;
  private static final int CODE_CODE_C = 99;
  private static final int CODE_FNC_1 = 102;
  private static final int CODE_FNC_2 = 97;
  private static final int CODE_FNC_3 = 96;
  private static final int CODE_FNC_4_A = 101;
  private static final int CODE_FNC_4_B = 100;
  static final int[][] CODE_PATTERNS;
  private static final int CODE_SHIFT = 98;
  private static final int CODE_START_A = 103;
  private static final int CODE_START_B = 104;
  private static final int CODE_START_C = 105;
  private static final int CODE_STOP = 106;
  private static final float MAX_AVG_VARIANCE = 0.25F;
  private static final float MAX_INDIVIDUAL_VARIANCE = 0.7F;
  
  static
  {
    int[] arrayOfInt1 = { 2, 1, 2, 2, 2, 2 };
    int[] arrayOfInt2 = { 2, 2, 2, 1, 2, 2 };
    int[] arrayOfInt3 = { 2, 2, 2, 2, 2, 1 };
    int[] arrayOfInt4 = { 1, 3, 1, 2, 2, 2 };
    int[] arrayOfInt5 = { 1, 2, 2, 2, 1, 3 };
    int[] arrayOfInt6 = { 1, 2, 2, 3, 1, 2 };
    int[] arrayOfInt7 = { 1, 3, 2, 2, 1, 2 };
    int[] arrayOfInt8 = { 2, 2, 1, 2, 1, 3 };
    int[] arrayOfInt9 = { 2, 2, 1, 3, 1, 2 };
    int[] arrayOfInt10 = { 2, 3, 1, 2, 1, 2 };
    int[] arrayOfInt11 = { 1, 1, 2, 2, 3, 2 };
    int[] arrayOfInt12 = { 1, 2, 2, 1, 3, 2 };
    int[] arrayOfInt13 = { 1, 2, 2, 2, 3, 1 };
    int[] arrayOfInt14 = { 1, 1, 3, 2, 2, 2 };
    int[] arrayOfInt15 = { 1, 2, 3, 1, 2, 2 };
    int[] arrayOfInt16 = { 1, 2, 3, 2, 2, 1 };
    int[] arrayOfInt17 = { 2, 2, 3, 2, 1, 1 };
    int[] arrayOfInt18 = { 2, 2, 1, 1, 3, 2 };
    int[] arrayOfInt19 = { 2, 2, 1, 2, 3, 1 };
    int[] arrayOfInt20 = { 2, 1, 3, 2, 1, 2 };
    int[] arrayOfInt21 = { 2, 2, 3, 1, 1, 2 };
    int[] arrayOfInt22 = { 3, 1, 2, 1, 3, 1 };
    int[] arrayOfInt23 = { 3, 1, 1, 2, 2, 2 };
    int[] arrayOfInt24 = { 3, 2, 1, 1, 2, 2 };
    int[] arrayOfInt25 = { 3, 2, 1, 2, 2, 1 };
    int[] arrayOfInt26 = { 3, 1, 2, 2, 1, 2 };
    int[] arrayOfInt27 = { 3, 2, 2, 1, 1, 2 };
    int[] arrayOfInt28 = { 3, 2, 2, 2, 1, 1 };
    int[] arrayOfInt29 = { 2, 1, 2, 1, 2, 3 };
    int[] arrayOfInt30 = { 2, 1, 2, 3, 2, 1 };
    int[] arrayOfInt31 = { 1, 1, 1, 3, 2, 3 };
    int[] arrayOfInt32 = { 1, 3, 1, 1, 2, 3 };
    int[] arrayOfInt33 = { 1, 3, 1, 3, 2, 1 };
    int[] arrayOfInt34 = { 1, 1, 2, 3, 1, 3 };
    int[] arrayOfInt35 = { 1, 3, 2, 1, 1, 3 };
    int[] arrayOfInt36 = { 1, 3, 2, 3, 1, 1 };
    int[] arrayOfInt37 = { 2, 1, 1, 3, 1, 3 };
    int[] arrayOfInt38 = { 2, 3, 1, 1, 1, 3 };
    int[] arrayOfInt39 = { 2, 3, 1, 3, 1, 1 };
    int[] arrayOfInt40 = { 1, 1, 2, 1, 3, 3 };
    int[] arrayOfInt41 = { 1, 1, 2, 3, 3, 1 };
    int[] arrayOfInt42 = { 1, 3, 2, 1, 3, 1 };
    int[] arrayOfInt43 = { 1, 1, 3, 1, 2, 3 };
    int[] arrayOfInt44 = { 1, 1, 3, 3, 2, 1 };
    int[] arrayOfInt45 = { 1, 3, 3, 1, 2, 1 };
    int[] arrayOfInt46 = { 3, 1, 3, 1, 2, 1 };
    int[] arrayOfInt47 = { 2, 1, 1, 3, 3, 1 };
    int[] arrayOfInt48 = { 2, 3, 1, 1, 3, 1 };
    int[] arrayOfInt49 = { 2, 1, 3, 1, 1, 3 };
    int[] arrayOfInt50 = { 2, 1, 3, 3, 1, 1 };
    int[] arrayOfInt51 = { 2, 1, 3, 1, 3, 1 };
    int[] arrayOfInt52 = { 3, 1, 1, 1, 2, 3 };
    int[] arrayOfInt53 = { 3, 1, 1, 3, 2, 1 };
    int[] arrayOfInt54 = { 3, 3, 1, 1, 2, 1 };
    int[] arrayOfInt55 = { 3, 1, 2, 1, 1, 3 };
    int[] arrayOfInt56 = { 3, 1, 2, 3, 1, 1 };
    int[] arrayOfInt57 = { 3, 3, 2, 1, 1, 1 };
    int[] arrayOfInt58 = { 3, 1, 4, 1, 1, 1 };
    int[] arrayOfInt59 = { 2, 2, 1, 4, 1, 1 };
    int[] arrayOfInt60 = { 4, 3, 1, 1, 1, 1 };
    int[] arrayOfInt61 = { 1, 1, 1, 2, 2, 4 };
    int[] arrayOfInt62 = { 1, 1, 1, 4, 2, 2 };
    int[] arrayOfInt63 = { 1, 2, 1, 1, 2, 4 };
    int[] arrayOfInt64 = { 1, 2, 1, 4, 2, 1 };
    int[] arrayOfInt65 = { 1, 4, 1, 2, 2, 1 };
    int[] arrayOfInt66 = { 1, 1, 2, 2, 1, 4 };
    int[] arrayOfInt67 = { 1, 1, 2, 4, 1, 2 };
    int[] arrayOfInt68 = { 1, 2, 2, 1, 1, 4 };
    int[] arrayOfInt69 = { 1, 2, 2, 4, 1, 1 };
    int[] arrayOfInt70 = { 1, 4, 2, 1, 1, 2 };
    int[] arrayOfInt71 = { 1, 4, 2, 2, 1, 1 };
    int[] arrayOfInt72 = { 2, 4, 1, 2, 1, 1 };
    int[] arrayOfInt73 = { 2, 2, 1, 1, 1, 4 };
    int[] arrayOfInt74 = { 4, 1, 3, 1, 1, 1 };
    int[] arrayOfInt75 = { 2, 4, 1, 1, 1, 2 };
    int[] arrayOfInt76 = { 1, 3, 4, 1, 1, 1 };
    int[] arrayOfInt77 = { 1, 1, 1, 2, 4, 2 };
    int[] arrayOfInt78 = { 1, 2, 1, 1, 4, 2 };
    int[] arrayOfInt79 = { 1, 2, 1, 2, 4, 1 };
    int[] arrayOfInt80 = { 1, 1, 4, 2, 1, 2 };
    int[] arrayOfInt81 = { 1, 2, 4, 1, 1, 2 };
    int[] arrayOfInt82 = { 1, 2, 4, 2, 1, 1 };
    int[] arrayOfInt83 = { 4, 1, 1, 2, 1, 2 };
    int[] arrayOfInt84 = { 4, 2, 1, 1, 1, 2 };
    int[] arrayOfInt85 = { 4, 2, 1, 2, 1, 1 };
    int[] arrayOfInt86 = { 2, 1, 2, 1, 4, 1 };
    int[] arrayOfInt87 = { 2, 1, 4, 1, 2, 1 };
    int[] arrayOfInt88 = { 4, 1, 2, 1, 2, 1 };
    int[] arrayOfInt89 = { 1, 1, 1, 1, 4, 3 };
    int[] arrayOfInt90 = { 1, 3, 1, 1, 4, 1 };
    int[] arrayOfInt91 = { 1, 1, 4, 1, 1, 3 };
    int[] arrayOfInt92 = { 1, 1, 4, 3, 1, 1 };
    int[] arrayOfInt93 = { 4, 1, 1, 1, 1, 3 };
    int[] arrayOfInt94 = { 4, 1, 1, 3, 1, 1 };
    int[] arrayOfInt95 = { 1, 1, 3, 1, 4, 1 };
    int[] arrayOfInt96 = { 1, 1, 4, 1, 3, 1 };
    int[] arrayOfInt97 = { 3, 1, 1, 1, 4, 1 };
    int[] arrayOfInt98 = { 4, 1, 1, 1, 3, 1 };
    int[] arrayOfInt99 = { 2, 1, 1, 4, 1, 2 };
    int[] arrayOfInt100 = { 2, 1, 1, 2, 1, 4 };
    int[] arrayOfInt101 = { 2, 1, 1, 2, 3, 2 };
    int[] arrayOfInt102 = { 2, 3, 3, 1, 1, 1, 2 };
    CODE_PATTERNS = new int[][] { arrayOfInt1, arrayOfInt2, arrayOfInt3, { 1, 2, 1, 2, 2, 3 }, { 1, 2, 1, 3, 2, 2 }, arrayOfInt4, arrayOfInt5, arrayOfInt6, arrayOfInt7, arrayOfInt8, arrayOfInt9, arrayOfInt10, arrayOfInt11, arrayOfInt12, arrayOfInt13, arrayOfInt14, arrayOfInt15, arrayOfInt16, arrayOfInt17, arrayOfInt18, arrayOfInt19, arrayOfInt20, arrayOfInt21, arrayOfInt22, arrayOfInt23, arrayOfInt24, arrayOfInt25, arrayOfInt26, arrayOfInt27, arrayOfInt28, arrayOfInt29, arrayOfInt30, { 2, 3, 2, 1, 2, 1 }, arrayOfInt31, arrayOfInt32, arrayOfInt33, arrayOfInt34, arrayOfInt35, arrayOfInt36, arrayOfInt37, arrayOfInt38, arrayOfInt39, arrayOfInt40, arrayOfInt41, arrayOfInt42, arrayOfInt43, arrayOfInt44, arrayOfInt45, arrayOfInt46, arrayOfInt47, arrayOfInt48, arrayOfInt49, arrayOfInt50, arrayOfInt51, arrayOfInt52, arrayOfInt53, arrayOfInt54, arrayOfInt55, arrayOfInt56, arrayOfInt57, arrayOfInt58, arrayOfInt59, arrayOfInt60, arrayOfInt61, arrayOfInt62, arrayOfInt63, arrayOfInt64, { 1, 4, 1, 1, 2, 2 }, arrayOfInt65, arrayOfInt66, arrayOfInt67, arrayOfInt68, arrayOfInt69, arrayOfInt70, arrayOfInt71, arrayOfInt72, arrayOfInt73, arrayOfInt74, arrayOfInt75, arrayOfInt76, arrayOfInt77, arrayOfInt78, arrayOfInt79, arrayOfInt80, arrayOfInt81, arrayOfInt82, arrayOfInt83, arrayOfInt84, arrayOfInt85, arrayOfInt86, arrayOfInt87, arrayOfInt88, arrayOfInt89, { 1, 1, 1, 3, 4, 1 }, arrayOfInt90, arrayOfInt91, arrayOfInt92, arrayOfInt93, arrayOfInt94, arrayOfInt95, arrayOfInt96, arrayOfInt97, arrayOfInt98, arrayOfInt99, arrayOfInt100, arrayOfInt101, arrayOfInt102 };
  }
  
  private static int decodeCode(BitArray paramBitArray, int[] paramArrayOfInt, int paramInt)
    throws NotFoundException
  {
    recordPattern(paramBitArray, paramInt, paramArrayOfInt);
    float f1 = 0.25F;
    int i = -1;
    paramInt = 0;
    while (paramInt < CODE_PATTERNS.length)
    {
      float f3 = patternMatchVariance(paramArrayOfInt, CODE_PATTERNS[paramInt], 0.7F);
      float f2 = f1;
      if (f3 < f1)
      {
        f2 = f3;
        i = paramInt;
      }
      paramInt += 1;
      f1 = f2;
    }
    if (i >= 0) {
      return i;
    }
    throw NotFoundException.getNotFoundInstance();
  }
  
  private static int[] findStartPattern(BitArray paramBitArray)
    throws NotFoundException
  {
    int i2 = paramBitArray.getSize();
    int m = paramBitArray.getNextSet(0);
    int n = 0;
    int[] arrayOfInt = new int[6];
    int i = m;
    int k = 0;
    int i3 = arrayOfInt.length;
    while (m < i2)
    {
      int j;
      if ((paramBitArray.get(m) ^ k))
      {
        arrayOfInt[n] += 1;
        j = n;
        m += 1;
        n = j;
      }
      else
      {
        if (n == i3 - 1)
        {
          float f1 = 0.25F;
          int i1 = -1;
          j = 103;
          while (j <= 105)
          {
            float f3 = patternMatchVariance(arrayOfInt, CODE_PATTERNS[j], 0.7F);
            float f2 = f1;
            if (f3 < f1)
            {
              f2 = f3;
              i1 = j;
            }
            j += 1;
            f1 = f2;
          }
          if ((i1 >= 0) && (paramBitArray.isRange(Math.max(0, i - (m - i) / 2), i, false))) {
            return new int[] { i, m, i1 };
          }
          i += arrayOfInt[0] + arrayOfInt[1];
          System.arraycopy(arrayOfInt, 2, arrayOfInt, 0, i3 - 2);
          arrayOfInt[(i3 - 2)] = 0;
          arrayOfInt[(i3 - 1)] = 0;
          j = n - 1;
          label244:
          arrayOfInt[j] = 1;
          if (k != 0) {
            break label270;
          }
        }
        label270:
        for (k = 1;; k = 0)
        {
          break;
          j = n + 1;
          break label244;
        }
      }
    }
    throw NotFoundException.getNotFoundInstance();
  }
  
  public Result decodeRow(int paramInt, BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException, FormatException, ChecksumException
  {
    if ((paramMap != null) && (paramMap.containsKey(DecodeHintType.ASSUME_GS1))) {}
    int i19;
    for (int i7 = 1;; i7 = 0)
    {
      localObject2 = findStartPattern(paramBitArray);
      i19 = localObject2[2];
      paramMap = new ArrayList(20);
      paramMap.add(Byte.valueOf((byte)i19));
      switch (i19)
      {
      default: 
        throw FormatException.getFormatInstance();
      }
    }
    int i = 101;
    int i11 = 0;
    int i5 = 0;
    Object localObject1 = new StringBuilder(20);
    int i17 = localObject2[0];
    int i3 = localObject2[1];
    Object localObject3 = new int[6];
    int i18 = 0;
    int i6 = 0;
    int i16 = 0;
    int i4 = 1;
    int i8 = 0;
    int i15 = 0;
    int i2 = i;
    int i21;
    int i9;
    int i20;
    int k;
    int i12;
    int i10;
    int i13;
    int j;
    int i14;
    int m;
    int i1;
    int n;
    do
    {
      i21 = i5;
      if (i11 != 0) {
        break label1885;
      }
      i5 = 0;
      i9 = i6;
      i20 = decodeCode(paramBitArray, (int[])localObject3, i3);
      paramMap.add(Byte.valueOf((byte)i20));
      k = i4;
      if (i20 != 106) {
        k = 1;
      }
      i12 = i19;
      i10 = i16;
      if (i20 != 106)
      {
        i10 = i16 + 1;
        i12 = i19 + i10 * i20;
      }
      i13 = i3;
      j = localObject3.length;
      i = 0;
      i14 = i3;
      for (;;)
      {
        if (i < j)
        {
          i14 += localObject3[i];
          i += 1;
          continue;
          i = 100;
          break;
          i = 99;
          break;
        }
      }
      switch (i20)
      {
      default: 
        switch (i2)
        {
        default: 
          m = i8;
          i = i15;
          i1 = i5;
          n = i11;
          j = i2;
          i19 = i12;
          i6 = i20;
          i2 = j;
          i11 = n;
          i5 = i1;
          i4 = k;
          i18 = i9;
          i17 = i13;
          i16 = i10;
          i3 = i14;
          i15 = i;
          i8 = m;
        }
        break;
      }
    } while (i21 == 0);
    if (j == 101) {}
    for (i2 = 100;; i2 = 101)
    {
      i19 = i12;
      i6 = i20;
      i11 = n;
      i5 = i1;
      i4 = k;
      i18 = i9;
      i17 = i13;
      i16 = i10;
      i3 = i14;
      i15 = i;
      i8 = m;
      break;
      throw FormatException.getFormatInstance();
      if (i20 < 64)
      {
        if (i15 == i8) {
          ((StringBuilder)localObject1).append((char)(i20 + 32));
        }
        for (;;)
        {
          i = 0;
          j = i2;
          n = i11;
          i1 = i5;
          m = i8;
          break;
          ((StringBuilder)localObject1).append((char)(i20 + 32 + 128));
        }
      }
      if (i20 < 96)
      {
        if (i15 == i8) {
          ((StringBuilder)localObject1).append((char)(i20 - 64));
        }
        for (;;)
        {
          i = 0;
          j = i2;
          n = i11;
          i1 = i5;
          m = i8;
          break;
          ((StringBuilder)localObject1).append((char)(i20 + 64));
        }
      }
      i3 = k;
      if (i20 != 106) {
        i3 = 0;
      }
      j = i2;
      n = i11;
      i1 = i5;
      k = i3;
      i = i15;
      m = i8;
      switch (i20)
      {
      case 96: 
      case 97: 
      case 103: 
      case 104: 
      case 105: 
      default: 
        j = i2;
        n = i11;
        i1 = i5;
        k = i3;
        i = i15;
        m = i8;
        break;
      case 98: 
        i1 = 1;
        j = 100;
        n = i11;
        k = i3;
        i = i15;
        m = i8;
        break;
      case 102: 
        j = i2;
        n = i11;
        i1 = i5;
        k = i3;
        i = i15;
        m = i8;
        if (i7 == 0) {
          break;
        }
        if (((StringBuilder)localObject1).length() == 0)
        {
          ((StringBuilder)localObject1).append("]C1");
          j = i2;
          n = i11;
          i1 = i5;
          k = i3;
          i = i15;
          m = i8;
          break;
        }
        ((StringBuilder)localObject1).append('\035');
        j = i2;
        n = i11;
        i1 = i5;
        k = i3;
        i = i15;
        m = i8;
        break;
      case 101: 
        if ((i8 == 0) && (i15 != 0))
        {
          m = 1;
          i = 0;
          j = i2;
          n = i11;
          i1 = i5;
          k = i3;
          break;
        }
        if ((i8 != 0) && (i15 != 0))
        {
          m = 0;
          i = 0;
          j = i2;
          n = i11;
          i1 = i5;
          k = i3;
          break;
        }
        i = 1;
        j = i2;
        n = i11;
        i1 = i5;
        k = i3;
        m = i8;
        break;
      case 100: 
        j = 100;
        n = i11;
        i1 = i5;
        k = i3;
        i = i15;
        m = i8;
        break;
      case 99: 
        j = 99;
        n = i11;
        i1 = i5;
        k = i3;
        i = i15;
        m = i8;
        break;
      case 106: 
        n = 1;
        j = i2;
        i1 = i5;
        k = i3;
        i = i15;
        m = i8;
        break;
        if (i20 < 96)
        {
          if (i15 == i8) {
            ((StringBuilder)localObject1).append((char)(i20 + 32));
          }
          for (;;)
          {
            i = 0;
            j = i2;
            n = i11;
            i1 = i5;
            m = i8;
            break;
            ((StringBuilder)localObject1).append((char)(i20 + 32 + 128));
          }
        }
        i3 = k;
        if (i20 != 106) {
          i3 = 0;
        }
        j = i2;
        n = i11;
        i1 = i5;
        k = i3;
        i = i15;
        m = i8;
        switch (i20)
        {
        case 96: 
        case 97: 
        case 103: 
        case 104: 
        case 105: 
        default: 
          j = i2;
          n = i11;
          i1 = i5;
          k = i3;
          i = i15;
          m = i8;
          break;
        case 98: 
          i1 = 1;
          j = 101;
          n = i11;
          k = i3;
          i = i15;
          m = i8;
          break;
        case 102: 
          j = i2;
          n = i11;
          i1 = i5;
          k = i3;
          i = i15;
          m = i8;
          if (i7 == 0) {
            break;
          }
          if (((StringBuilder)localObject1).length() == 0)
          {
            ((StringBuilder)localObject1).append("]C1");
            j = i2;
            n = i11;
            i1 = i5;
            k = i3;
            i = i15;
            m = i8;
            break;
          }
          ((StringBuilder)localObject1).append('\035');
          j = i2;
          n = i11;
          i1 = i5;
          k = i3;
          i = i15;
          m = i8;
          break;
        case 100: 
          if ((i8 == 0) && (i15 != 0))
          {
            m = 1;
            i = 0;
            j = i2;
            n = i11;
            i1 = i5;
            k = i3;
            break;
          }
          if ((i8 != 0) && (i15 != 0))
          {
            m = 0;
            i = 0;
            j = i2;
            n = i11;
            i1 = i5;
            k = i3;
            break;
          }
          i = 1;
          j = i2;
          n = i11;
          i1 = i5;
          k = i3;
          m = i8;
          break;
        case 101: 
          j = 101;
          n = i11;
          i1 = i5;
          k = i3;
          i = i15;
          m = i8;
          break;
        case 99: 
          j = 99;
          n = i11;
          i1 = i5;
          k = i3;
          i = i15;
          m = i8;
          break;
        case 106: 
          n = 1;
          j = i2;
          i1 = i5;
          k = i3;
          i = i15;
          m = i8;
          break;
          if (i20 < 100)
          {
            if (i20 < 10) {
              ((StringBuilder)localObject1).append('0');
            }
            ((StringBuilder)localObject1).append(i20);
            j = i2;
            n = i11;
            i1 = i5;
            i = i15;
            m = i8;
            break;
          }
          i3 = k;
          if (i20 != 106) {
            i3 = 0;
          }
          switch (i20)
          {
          case 103: 
          case 104: 
          case 105: 
          default: 
            j = i2;
            n = i11;
            i1 = i5;
            k = i3;
            i = i15;
            m = i8;
            break;
          case 100: 
            j = 100;
            n = i11;
            i1 = i5;
            k = i3;
            i = i15;
            m = i8;
            break;
          case 102: 
            j = i2;
            n = i11;
            i1 = i5;
            k = i3;
            i = i15;
            m = i8;
            if (i7 == 0) {
              break;
            }
            if (((StringBuilder)localObject1).length() == 0)
            {
              ((StringBuilder)localObject1).append("]C1");
              j = i2;
              n = i11;
              i1 = i5;
              k = i3;
              i = i15;
              m = i8;
              break;
            }
            ((StringBuilder)localObject1).append('\035');
            j = i2;
            n = i11;
            i1 = i5;
            k = i3;
            i = i15;
            m = i8;
            break;
          case 101: 
            j = 101;
            n = i11;
            i1 = i5;
            k = i3;
            i = i15;
            m = i8;
            break;
          case 106: 
            n = 1;
            j = i2;
            i1 = i5;
            k = i3;
            i = i15;
            m = i8;
            break;
          }
          break;
        }
        break;
      }
    }
    label1885:
    i = paramBitArray.getNextUnset(i3);
    if (!paramBitArray.isRange(i, Math.min(paramBitArray.getSize(), (i - i17) / 2 + i), false)) {
      throw NotFoundException.getNotFoundInstance();
    }
    if ((i19 - i16 * i18) % 103 != i18) {
      throw ChecksumException.getChecksumInstance();
    }
    i = ((StringBuilder)localObject1).length();
    if (i == 0) {
      throw NotFoundException.getNotFoundInstance();
    }
    if ((i > 0) && (i4 != 0))
    {
      if (i2 != 99) {
        break label2068;
      }
      ((StringBuilder)localObject1).delete(i - 2, i);
    }
    float f1;
    float f2;
    float f3;
    for (;;)
    {
      f1 = (localObject2[1] + localObject2[0]) / 2.0F;
      f2 = i17;
      f3 = (i3 - i17) / 2.0F;
      j = paramMap.size();
      paramBitArray = new byte[j];
      i = 0;
      while (i < j)
      {
        paramBitArray[i] = ((Byte)paramMap.get(i)).byteValue();
        i += 1;
      }
      label2068:
      ((StringBuilder)localObject1).delete(i - 1, i);
    }
    paramMap = ((StringBuilder)localObject1).toString();
    localObject1 = new ResultPoint(f1, paramInt);
    Object localObject2 = new ResultPoint(f2 + f3, paramInt);
    localObject3 = BarcodeFormat.CODE_128;
    return new Result(paramMap, paramBitArray, new ResultPoint[] { localObject1, localObject2 }, (BarcodeFormat)localObject3);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\Code128Reader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */