package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class HighLevelEncoder
{
  private static final int[][] CHAR_MAP;
  static final int[][] LATCH_TABLE;
  static final int MODE_DIGIT = 2;
  static final int MODE_LOWER = 1;
  static final int MODE_MIXED = 3;
  static final String[] MODE_NAMES = { "UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT" };
  static final int MODE_PUNCT = 4;
  static final int MODE_UPPER = 0;
  static final int[][] SHIFT_TABLE;
  private final byte[] text;
  
  static
  {
    Object localObject = { 262158, 590300, 0, 590301, 932798 };
    int[] arrayOfInt1 = { 327709, 327708, 656318, 0, 327710 };
    int[] arrayOfInt2 = { 327711, 656380, 656382, 656381, 0 };
    LATCH_TABLE = new int[][] { { 0, 327708, 327710, 327709, 656318 }, { 590318, 0, 327710, 327709, 656318 }, localObject, arrayOfInt1, arrayOfInt2 };
    CHAR_MAP = (int[][])Array.newInstance(Integer.TYPE, new int[] { 5, 256 });
    CHAR_MAP[0][32] = 1;
    int i = 65;
    while (i <= 90)
    {
      CHAR_MAP[0][i] = (i - 65 + 2);
      i += 1;
    }
    CHAR_MAP[1][32] = 1;
    i = 97;
    while (i <= 122)
    {
      CHAR_MAP[1][i] = (i - 97 + 2);
      i += 1;
    }
    CHAR_MAP[2][32] = 1;
    i = 48;
    while (i <= 57)
    {
      CHAR_MAP[2][i] = (i - 48 + 2);
      i += 1;
    }
    CHAR_MAP[2][44] = 12;
    CHAR_MAP[2][46] = 13;
    localObject = new int[28];
    Object tmp362_361 = localObject;
    tmp362_361[0] = 0;
    Object tmp366_362 = tmp362_361;
    tmp366_362[1] = 32;
    Object tmp371_366 = tmp366_362;
    tmp371_366[2] = 1;
    Object tmp375_371 = tmp371_366;
    tmp375_371[3] = 2;
    Object tmp379_375 = tmp375_371;
    tmp379_375[4] = 3;
    Object tmp383_379 = tmp379_375;
    tmp383_379[5] = 4;
    Object tmp387_383 = tmp383_379;
    tmp387_383[6] = 5;
    Object tmp392_387 = tmp387_383;
    tmp392_387[7] = 6;
    Object tmp398_392 = tmp392_387;
    tmp398_392[8] = 7;
    Object tmp404_398 = tmp398_392;
    tmp404_398[9] = 8;
    Object tmp410_404 = tmp404_398;
    tmp410_404[10] = 9;
    Object tmp416_410 = tmp410_404;
    tmp416_410[11] = 10;
    Object tmp422_416 = tmp416_410;
    tmp422_416[12] = 11;
    Object tmp428_422 = tmp422_416;
    tmp428_422[13] = 12;
    Object tmp434_428 = tmp428_422;
    tmp434_428[14] = 13;
    Object tmp440_434 = tmp434_428;
    tmp440_434[15] = 27;
    Object tmp446_440 = tmp440_434;
    tmp446_440[16] = 28;
    Object tmp452_446 = tmp446_440;
    tmp452_446[17] = 29;
    Object tmp458_452 = tmp452_446;
    tmp458_452[18] = 30;
    Object tmp464_458 = tmp458_452;
    tmp464_458[19] = 31;
    Object tmp470_464 = tmp464_458;
    tmp470_464[20] = 64;
    Object tmp476_470 = tmp470_464;
    tmp476_470[21] = 92;
    Object tmp482_476 = tmp476_470;
    tmp482_476[22] = 94;
    Object tmp488_482 = tmp482_476;
    tmp488_482[23] = 95;
    Object tmp494_488 = tmp488_482;
    tmp494_488[24] = 96;
    Object tmp500_494 = tmp494_488;
    tmp500_494[25] = 124;
    Object tmp506_500 = tmp500_494;
    tmp506_500[26] = 126;
    Object tmp512_506 = tmp506_500;
    tmp512_506[27] = 127;
    tmp512_506;
    i = 0;
    while (i < localObject.length)
    {
      CHAR_MAP[3][localObject[i]] = i;
      i += 1;
    }
    localObject = new int[31];
    Object tmp550_549 = localObject;
    tmp550_549[0] = 0;
    Object tmp554_550 = tmp550_549;
    tmp554_550[1] = 13;
    Object tmp559_554 = tmp554_550;
    tmp559_554[2] = 0;
    Object tmp563_559 = tmp559_554;
    tmp563_559[3] = 0;
    Object tmp567_563 = tmp563_559;
    tmp567_563[4] = 0;
    Object tmp571_567 = tmp567_563;
    tmp571_567[5] = 0;
    Object tmp575_571 = tmp571_567;
    tmp575_571[6] = 33;
    Object tmp581_575 = tmp575_571;
    tmp581_575[7] = 39;
    Object tmp587_581 = tmp581_575;
    tmp587_581[8] = 35;
    Object tmp593_587 = tmp587_581;
    tmp593_587[9] = 36;
    Object tmp599_593 = tmp593_587;
    tmp599_593[10] = 37;
    Object tmp605_599 = tmp599_593;
    tmp605_599[11] = 38;
    Object tmp611_605 = tmp605_599;
    tmp611_605[12] = 39;
    Object tmp617_611 = tmp611_605;
    tmp617_611[13] = 40;
    Object tmp623_617 = tmp617_611;
    tmp623_617[14] = 41;
    Object tmp629_623 = tmp623_617;
    tmp629_623[15] = 42;
    Object tmp635_629 = tmp629_623;
    tmp635_629[16] = 43;
    Object tmp641_635 = tmp635_629;
    tmp641_635[17] = 44;
    Object tmp647_641 = tmp641_635;
    tmp647_641[18] = 45;
    Object tmp653_647 = tmp647_641;
    tmp653_647[19] = 46;
    Object tmp659_653 = tmp653_647;
    tmp659_653[20] = 47;
    Object tmp665_659 = tmp659_653;
    tmp665_659[21] = 58;
    Object tmp671_665 = tmp665_659;
    tmp671_665[22] = 59;
    Object tmp677_671 = tmp671_665;
    tmp677_671[23] = 60;
    Object tmp683_677 = tmp677_671;
    tmp683_677[24] = 61;
    Object tmp689_683 = tmp683_677;
    tmp689_683[25] = 62;
    Object tmp695_689 = tmp689_683;
    tmp695_689[26] = 63;
    Object tmp701_695 = tmp695_689;
    tmp701_695[27] = 91;
    Object tmp707_701 = tmp701_695;
    tmp707_701[28] = 93;
    Object tmp713_707 = tmp707_701;
    tmp713_707[29] = 123;
    Object tmp719_713 = tmp713_707;
    tmp719_713[30] = 125;
    tmp719_713;
    i = 0;
    while (i < localObject.length)
    {
      if (localObject[i] > 0) {
        CHAR_MAP[4][localObject[i]] = i;
      }
      i += 1;
    }
    SHIFT_TABLE = (int[][])Array.newInstance(Integer.TYPE, new int[] { 6, 6 });
    localObject = SHIFT_TABLE;
    int j = localObject.length;
    i = 0;
    while (i < j)
    {
      Arrays.fill(localObject[i], -1);
      i += 1;
    }
    SHIFT_TABLE[0][4] = 0;
    SHIFT_TABLE[1][4] = 0;
    SHIFT_TABLE[1][0] = 28;
    SHIFT_TABLE[3][4] = 0;
    SHIFT_TABLE[2][4] = 0;
    SHIFT_TABLE[2][0] = 15;
  }
  
  public HighLevelEncoder(byte[] paramArrayOfByte)
  {
    this.text = paramArrayOfByte;
  }
  
  private static Collection<State> simplifyStates(Iterable<State> paramIterable)
  {
    LinkedList localLinkedList = new LinkedList();
    paramIterable = paramIterable.iterator();
    if (paramIterable.hasNext())
    {
      State localState1 = (State)paramIterable.next();
      int j = 1;
      Iterator localIterator = localLinkedList.iterator();
      for (;;)
      {
        int i = j;
        State localState2;
        if (localIterator.hasNext())
        {
          localState2 = (State)localIterator.next();
          if (localState2.isBetterThanOrEqualTo(localState1)) {
            i = 0;
          }
        }
        else
        {
          if (i == 0) {
            break;
          }
          localLinkedList.add(localState1);
          break;
        }
        if (localState1.isBetterThanOrEqualTo(localState2)) {
          localIterator.remove();
        }
      }
    }
    return localLinkedList;
  }
  
  private void updateStateForChar(State paramState, int paramInt, Collection<State> paramCollection)
  {
    int k = (char)(this.text[paramInt] & 0xFF);
    if (CHAR_MAP[paramState.getMode()][k] > 0) {}
    for (int i = 1;; i = 0)
    {
      Object localObject1 = null;
      int j = 0;
      while (j <= 4)
      {
        int m = CHAR_MAP[j][k];
        Object localObject3 = localObject1;
        if (m > 0)
        {
          Object localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = paramState.endBinaryShift(paramInt);
          }
          if ((i == 0) || (j == paramState.getMode()) || (j == 2)) {
            paramCollection.add(((State)localObject2).latchAndAppend(j, m));
          }
          localObject3 = localObject2;
          if (i == 0)
          {
            localObject3 = localObject2;
            if (SHIFT_TABLE[paramState.getMode()][j] >= 0)
            {
              paramCollection.add(((State)localObject2).shiftAndAppend(j, m));
              localObject3 = localObject2;
            }
          }
        }
        j += 1;
        localObject1 = localObject3;
      }
    }
    if ((paramState.getBinaryShiftByteCount() > 0) || (CHAR_MAP[paramState.getMode()][k] == 0)) {
      paramCollection.add(paramState.addBinaryShiftChar(paramInt));
    }
  }
  
  private static void updateStateForPair(State paramState, int paramInt1, int paramInt2, Collection<State> paramCollection)
  {
    State localState = paramState.endBinaryShift(paramInt1);
    paramCollection.add(localState.latchAndAppend(4, paramInt2));
    if (paramState.getMode() != 4) {
      paramCollection.add(localState.shiftAndAppend(4, paramInt2));
    }
    if ((paramInt2 == 3) || (paramInt2 == 4)) {
      paramCollection.add(localState.latchAndAppend(2, 16 - paramInt2).latchAndAppend(2, 1));
    }
    if (paramState.getBinaryShiftByteCount() > 0) {
      paramCollection.add(paramState.addBinaryShiftChar(paramInt1).addBinaryShiftChar(paramInt1 + 1));
    }
  }
  
  private Collection<State> updateStateListForChar(Iterable<State> paramIterable, int paramInt)
  {
    LinkedList localLinkedList = new LinkedList();
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext()) {
      updateStateForChar((State)paramIterable.next(), paramInt, localLinkedList);
    }
    return simplifyStates(localLinkedList);
  }
  
  private static Collection<State> updateStateListForPair(Iterable<State> paramIterable, int paramInt1, int paramInt2)
  {
    LinkedList localLinkedList = new LinkedList();
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext()) {
      updateStateForPair((State)paramIterable.next(), paramInt1, paramInt2, localLinkedList);
    }
    return simplifyStates(localLinkedList);
  }
  
  public BitArray encode()
  {
    Object localObject = Collections.singletonList(State.INITIAL_STATE);
    int j = 0;
    if (j < this.text.length)
    {
      int i;
      if (j + 1 < this.text.length)
      {
        i = this.text[(j + 1)];
        label38:
        switch (this.text[j])
        {
        default: 
          i = 0;
          if (i > 0)
          {
            localObject = updateStateListForPair((Iterable)localObject, j, i);
            j += 1;
          }
          break;
        }
      }
      for (;;)
      {
        j += 1;
        break;
        i = 0;
        break label38;
        if (i == 10) {}
        for (i = 2;; i = 0) {
          break;
        }
        if (i == 32) {}
        for (i = 3;; i = 0) {
          break;
        }
        if (i == 32) {}
        for (i = 4;; i = 0) {
          break;
        }
        if (i == 32) {}
        for (i = 5;; i = 0) {
          break;
        }
        localObject = updateStateListForChar((Iterable)localObject, j);
      }
    }
    ((State)Collections.min((Collection)localObject, new Comparator()
    {
      public int compare(State paramAnonymousState1, State paramAnonymousState2)
      {
        return paramAnonymousState1.getBitCount() - paramAnonymousState2.getBitCount();
      }
    })).toBitArray(this.text);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\aztec\encoder\HighLevelEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */