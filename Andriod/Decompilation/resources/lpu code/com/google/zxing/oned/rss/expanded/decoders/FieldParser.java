package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;

final class FieldParser
{
  private static final Object[][] FOUR_DIGIT_DATA_LENGTH;
  private static final Object[][] THREE_DIGIT_DATA_LENGTH;
  private static final Object[][] THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH;
  private static final Object[][] TWO_DIGIT_DATA_LENGTH;
  private static final Object VARIABLE_LENGTH = new Object();
  
  static
  {
    Object localObject1 = VARIABLE_LENGTH;
    Object[] arrayOfObject1 = { "12", Integer.valueOf(6) };
    Object localObject2 = VARIABLE_LENGTH;
    Object localObject3 = VARIABLE_LENGTH;
    Object[] arrayOfObject2 = { "30", VARIABLE_LENGTH, Integer.valueOf(8) };
    Object localObject4 = VARIABLE_LENGTH;
    Object localObject5 = VARIABLE_LENGTH;
    Object[] arrayOfObject3 = { "91", VARIABLE_LENGTH, Integer.valueOf(30) };
    Object localObject6 = VARIABLE_LENGTH;
    Object localObject7 = VARIABLE_LENGTH;
    Object localObject8 = VARIABLE_LENGTH;
    Object localObject9 = VARIABLE_LENGTH;
    Object[] arrayOfObject4 = { "96", VARIABLE_LENGTH, Integer.valueOf(30) };
    Object localObject10 = VARIABLE_LENGTH;
    Object[] arrayOfObject5 = { "98", VARIABLE_LENGTH, Integer.valueOf(30) };
    Object localObject11 = VARIABLE_LENGTH;
    TWO_DIGIT_DATA_LENGTH = new Object[][] { { "00", Integer.valueOf(18) }, { "01", Integer.valueOf(14) }, { "02", Integer.valueOf(14) }, { "10", localObject1, Integer.valueOf(20) }, { "11", Integer.valueOf(6) }, arrayOfObject1, { "13", Integer.valueOf(6) }, { "15", Integer.valueOf(6) }, { "17", Integer.valueOf(6) }, { "20", Integer.valueOf(2) }, { "21", localObject2, Integer.valueOf(20) }, { "22", localObject3, Integer.valueOf(29) }, arrayOfObject2, { "37", localObject4, Integer.valueOf(8) }, { "90", localObject5, Integer.valueOf(30) }, arrayOfObject3, { "92", localObject6, Integer.valueOf(30) }, { "93", localObject7, Integer.valueOf(30) }, { "94", localObject8, Integer.valueOf(30) }, { "95", localObject9, Integer.valueOf(30) }, arrayOfObject4, { "97", localObject10, Integer.valueOf(30) }, arrayOfObject5, { "99", localObject11, Integer.valueOf(30) } };
    localObject1 = VARIABLE_LENGTH;
    localObject2 = VARIABLE_LENGTH;
    localObject5 = new Object[] { "242", VARIABLE_LENGTH, Integer.valueOf(6) };
    localObject6 = new Object[] { "250", VARIABLE_LENGTH, Integer.valueOf(30) };
    localObject7 = new Object[] { "251", VARIABLE_LENGTH, Integer.valueOf(30) };
    localObject8 = new Object[] { "253", VARIABLE_LENGTH, Integer.valueOf(17) };
    localObject3 = VARIABLE_LENGTH;
    localObject9 = new Object[] { "400", VARIABLE_LENGTH, Integer.valueOf(30) };
    localObject10 = new Object[] { "401", VARIABLE_LENGTH, Integer.valueOf(30) };
    localObject11 = new Object[] { "403", VARIABLE_LENGTH, Integer.valueOf(30) };
    arrayOfObject1 = new Object[] { "411", Integer.valueOf(13) };
    arrayOfObject2 = new Object[] { "412", Integer.valueOf(13) };
    arrayOfObject3 = new Object[] { "414", Integer.valueOf(13) };
    arrayOfObject4 = new Object[] { "420", VARIABLE_LENGTH, Integer.valueOf(20) };
    arrayOfObject5 = new Object[] { "421", VARIABLE_LENGTH, Integer.valueOf(15) };
    Object[] arrayOfObject6 = { "422", Integer.valueOf(3) };
    localObject4 = VARIABLE_LENGTH;
    Object[] arrayOfObject7 = { "424", Integer.valueOf(3) };
    THREE_DIGIT_DATA_LENGTH = new Object[][] { { "240", localObject1, Integer.valueOf(30) }, { "241", localObject2, Integer.valueOf(30) }, localObject5, localObject6, localObject7, localObject8, { "254", localObject3, Integer.valueOf(20) }, localObject9, localObject10, { "402", Integer.valueOf(17) }, localObject11, { "410", Integer.valueOf(13) }, arrayOfObject1, arrayOfObject2, { "413", Integer.valueOf(13) }, arrayOfObject3, arrayOfObject4, arrayOfObject5, arrayOfObject6, { "423", localObject4, Integer.valueOf(15) }, arrayOfObject7, { "425", Integer.valueOf(3) }, { "426", Integer.valueOf(3) } };
    localObject1 = new Object[] { "310", Integer.valueOf(6) };
    localObject2 = new Object[] { "311", Integer.valueOf(6) };
    localObject3 = new Object[] { "312", Integer.valueOf(6) };
    localObject4 = new Object[] { "313", Integer.valueOf(6) };
    localObject5 = new Object[] { "314", Integer.valueOf(6) };
    localObject6 = new Object[] { "315", Integer.valueOf(6) };
    localObject7 = new Object[] { "320", Integer.valueOf(6) };
    localObject8 = new Object[] { "322", Integer.valueOf(6) };
    localObject9 = new Object[] { "323", Integer.valueOf(6) };
    localObject10 = new Object[] { "324", Integer.valueOf(6) };
    localObject11 = new Object[] { "325", Integer.valueOf(6) };
    arrayOfObject1 = new Object[] { "326", Integer.valueOf(6) };
    arrayOfObject2 = new Object[] { "327", Integer.valueOf(6) };
    arrayOfObject3 = new Object[] { "328", Integer.valueOf(6) };
    arrayOfObject4 = new Object[] { "330", Integer.valueOf(6) };
    arrayOfObject5 = new Object[] { "331", Integer.valueOf(6) };
    arrayOfObject6 = new Object[] { "332", Integer.valueOf(6) };
    arrayOfObject7 = new Object[] { "333", Integer.valueOf(6) };
    Object[] arrayOfObject8 = { "334", Integer.valueOf(6) };
    Object[] arrayOfObject9 = { "336", Integer.valueOf(6) };
    Object[] arrayOfObject10 = { "340", Integer.valueOf(6) };
    Object[] arrayOfObject11 = { "341", Integer.valueOf(6) };
    Object[] arrayOfObject12 = { "342", Integer.valueOf(6) };
    Object[] arrayOfObject13 = { "343", Integer.valueOf(6) };
    Object[] arrayOfObject14 = { "344", Integer.valueOf(6) };
    Object[] arrayOfObject15 = { "345", Integer.valueOf(6) };
    Object[] arrayOfObject16 = { "346", Integer.valueOf(6) };
    Object[] arrayOfObject17 = { "347", Integer.valueOf(6) };
    Object[] arrayOfObject18 = { "348", Integer.valueOf(6) };
    Object[] arrayOfObject19 = { "349", Integer.valueOf(6) };
    Object[] arrayOfObject20 = { "350", Integer.valueOf(6) };
    Object[] arrayOfObject21 = { "351", Integer.valueOf(6) };
    Object[] arrayOfObject22 = { "352", Integer.valueOf(6) };
    Object[] arrayOfObject23 = { "353", Integer.valueOf(6) };
    Object[] arrayOfObject24 = { "354", Integer.valueOf(6) };
    Object[] arrayOfObject25 = { "355", Integer.valueOf(6) };
    Object[] arrayOfObject26 = { "356", Integer.valueOf(6) };
    Object[] arrayOfObject27 = { "357", Integer.valueOf(6) };
    Object[] arrayOfObject28 = { "360", Integer.valueOf(6) };
    Object[] arrayOfObject29 = { "361", Integer.valueOf(6) };
    Object[] arrayOfObject30 = { "362", Integer.valueOf(6) };
    Object[] arrayOfObject31 = { "363", Integer.valueOf(6) };
    Object[] arrayOfObject32 = { "364", Integer.valueOf(6) };
    Object[] arrayOfObject33 = { "365", Integer.valueOf(6) };
    Object[] arrayOfObject34 = { "367", Integer.valueOf(6) };
    Object[] arrayOfObject35 = { "368", Integer.valueOf(6) };
    Object[] arrayOfObject36 = { "369", Integer.valueOf(6) };
    Object[] arrayOfObject37 = { "390", VARIABLE_LENGTH, Integer.valueOf(15) };
    Object[] arrayOfObject38 = { "391", VARIABLE_LENGTH, Integer.valueOf(18) };
    Object[] arrayOfObject39 = { "392", VARIABLE_LENGTH, Integer.valueOf(15) };
    Object[] arrayOfObject40 = { "393", VARIABLE_LENGTH, Integer.valueOf(18) };
    Object[] arrayOfObject41 = { "703", VARIABLE_LENGTH, Integer.valueOf(30) };
    THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH = new Object[][] { localObject1, localObject2, localObject3, localObject4, localObject5, localObject6, { "316", Integer.valueOf(6) }, localObject7, { "321", Integer.valueOf(6) }, localObject8, localObject9, localObject10, localObject11, arrayOfObject1, arrayOfObject2, arrayOfObject3, { "329", Integer.valueOf(6) }, arrayOfObject4, arrayOfObject5, arrayOfObject6, arrayOfObject7, arrayOfObject8, { "335", Integer.valueOf(6) }, arrayOfObject9, arrayOfObject10, arrayOfObject11, arrayOfObject12, arrayOfObject13, arrayOfObject14, arrayOfObject15, arrayOfObject16, arrayOfObject17, arrayOfObject18, arrayOfObject19, arrayOfObject20, arrayOfObject21, arrayOfObject22, arrayOfObject23, arrayOfObject24, arrayOfObject25, arrayOfObject26, arrayOfObject27, arrayOfObject28, arrayOfObject29, arrayOfObject30, arrayOfObject31, arrayOfObject32, arrayOfObject33, { "366", Integer.valueOf(6) }, arrayOfObject34, arrayOfObject35, arrayOfObject36, arrayOfObject37, arrayOfObject38, arrayOfObject39, arrayOfObject40, arrayOfObject41 };
    localObject1 = VARIABLE_LENGTH;
    localObject2 = VARIABLE_LENGTH;
    localObject3 = VARIABLE_LENGTH;
    localObject4 = VARIABLE_LENGTH;
    localObject5 = VARIABLE_LENGTH;
    localObject6 = VARIABLE_LENGTH;
    localObject7 = VARIABLE_LENGTH;
    localObject10 = new Object[] { "8102", Integer.valueOf(2) };
    localObject8 = VARIABLE_LENGTH;
    localObject9 = VARIABLE_LENGTH;
    FOUR_DIGIT_DATA_LENGTH = new Object[][] { { "7001", Integer.valueOf(13) }, { "7002", localObject1, Integer.valueOf(30) }, { "7003", Integer.valueOf(10) }, { "8001", Integer.valueOf(14) }, { "8002", localObject2, Integer.valueOf(20) }, { "8003", localObject3, Integer.valueOf(30) }, { "8004", localObject4, Integer.valueOf(30) }, { "8005", Integer.valueOf(6) }, { "8006", Integer.valueOf(18) }, { "8007", localObject5, Integer.valueOf(30) }, { "8008", localObject6, Integer.valueOf(12) }, { "8018", Integer.valueOf(18) }, { "8020", localObject7, Integer.valueOf(25) }, { "8100", Integer.valueOf(6) }, { "8101", Integer.valueOf(10) }, localObject10, { "8110", localObject8, Integer.valueOf(70) }, { "8200", localObject9, Integer.valueOf(70) } };
  }
  
  static String parseFieldsInGeneralPurpose(String paramString)
    throws NotFoundException
  {
    if (paramString.isEmpty()) {
      return null;
    }
    if (paramString.length() < 2) {
      throw NotFoundException.getNotFoundInstance();
    }
    String str = paramString.substring(0, 2);
    Object[][] arrayOfObject = TWO_DIGIT_DATA_LENGTH;
    int j = arrayOfObject.length;
    int i = 0;
    Object[] arrayOfObject1;
    while (i < j)
    {
      arrayOfObject1 = arrayOfObject[i];
      if (arrayOfObject1[0].equals(str))
      {
        if (arrayOfObject1[1] == VARIABLE_LENGTH) {
          return processVariableAI(2, ((Integer)arrayOfObject1[2]).intValue(), paramString);
        }
        return processFixedAI(2, ((Integer)arrayOfObject1[1]).intValue(), paramString);
      }
      i += 1;
    }
    if (paramString.length() < 3) {
      throw NotFoundException.getNotFoundInstance();
    }
    str = paramString.substring(0, 3);
    arrayOfObject = THREE_DIGIT_DATA_LENGTH;
    j = arrayOfObject.length;
    i = 0;
    while (i < j)
    {
      arrayOfObject1 = arrayOfObject[i];
      if (arrayOfObject1[0].equals(str))
      {
        if (arrayOfObject1[1] == VARIABLE_LENGTH) {
          return processVariableAI(3, ((Integer)arrayOfObject1[2]).intValue(), paramString);
        }
        return processFixedAI(3, ((Integer)arrayOfObject1[1]).intValue(), paramString);
      }
      i += 1;
    }
    arrayOfObject = THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH;
    j = arrayOfObject.length;
    i = 0;
    while (i < j)
    {
      arrayOfObject1 = arrayOfObject[i];
      if (arrayOfObject1[0].equals(str))
      {
        if (arrayOfObject1[1] == VARIABLE_LENGTH) {
          return processVariableAI(4, ((Integer)arrayOfObject1[2]).intValue(), paramString);
        }
        return processFixedAI(4, ((Integer)arrayOfObject1[1]).intValue(), paramString);
      }
      i += 1;
    }
    if (paramString.length() < 4) {
      throw NotFoundException.getNotFoundInstance();
    }
    str = paramString.substring(0, 4);
    arrayOfObject = FOUR_DIGIT_DATA_LENGTH;
    j = arrayOfObject.length;
    i = 0;
    while (i < j)
    {
      arrayOfObject1 = arrayOfObject[i];
      if (arrayOfObject1[0].equals(str))
      {
        if (arrayOfObject1[1] == VARIABLE_LENGTH) {
          return processVariableAI(4, ((Integer)arrayOfObject1[2]).intValue(), paramString);
        }
        return processFixedAI(4, ((Integer)arrayOfObject1[1]).intValue(), paramString);
      }
      i += 1;
    }
    throw NotFoundException.getNotFoundInstance();
  }
  
  private static String processFixedAI(int paramInt1, int paramInt2, String paramString)
    throws NotFoundException
  {
    if (paramString.length() < paramInt1) {
      throw NotFoundException.getNotFoundInstance();
    }
    String str1 = paramString.substring(0, paramInt1);
    if (paramString.length() < paramInt1 + paramInt2) {
      throw NotFoundException.getNotFoundInstance();
    }
    String str2 = paramString.substring(paramInt1, paramInt1 + paramInt2);
    paramString = paramString.substring(paramInt1 + paramInt2);
    str1 = '(' + str1 + ')' + str2;
    paramString = parseFieldsInGeneralPurpose(paramString);
    if (paramString == null) {
      return str1;
    }
    return str1 + paramString;
  }
  
  private static String processVariableAI(int paramInt1, int paramInt2, String paramString)
    throws NotFoundException
  {
    String str1 = paramString.substring(0, paramInt1);
    if (paramString.length() < paramInt1 + paramInt2) {}
    for (paramInt2 = paramString.length();; paramInt2 = paramInt1 + paramInt2)
    {
      String str2 = paramString.substring(paramInt1, paramInt2);
      paramString = paramString.substring(paramInt2);
      str1 = '(' + str1 + ')' + str2;
      paramString = parseFieldsInGeneralPurpose(paramString);
      if (paramString != null) {
        break;
      }
      return str1;
    }
    return str1 + paramString;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\rss\expanded\decoders\FieldParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */