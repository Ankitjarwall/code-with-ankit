package com.google.zxing.client.result;

public final class SMSParsedResult
  extends ParsedResult
{
  private final String body;
  private final String[] numbers;
  private final String subject;
  private final String[] vias;
  
  public SMSParsedResult(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    super(ParsedResultType.SMS);
    this.numbers = new String[] { paramString1 };
    this.vias = new String[] { paramString2 };
    this.subject = paramString3;
    this.body = paramString4;
  }
  
  public SMSParsedResult(String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString1, String paramString2)
  {
    super(ParsedResultType.SMS);
    this.numbers = paramArrayOfString1;
    this.vias = paramArrayOfString2;
    this.subject = paramString1;
    this.body = paramString2;
  }
  
  public String getBody()
  {
    return this.body;
  }
  
  public String getDisplayResult()
  {
    StringBuilder localStringBuilder = new StringBuilder(100);
    maybeAppend(this.numbers, localStringBuilder);
    maybeAppend(this.subject, localStringBuilder);
    maybeAppend(this.body, localStringBuilder);
    return localStringBuilder.toString();
  }
  
  public String[] getNumbers()
  {
    return this.numbers;
  }
  
  public String getSMSURI()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("sms:");
    int j = 1;
    int i = 0;
    if (i < this.numbers.length)
    {
      if (j != 0) {
        j = 0;
      }
      for (;;)
      {
        localStringBuilder.append(this.numbers[i]);
        if ((this.vias != null) && (this.vias[i] != null))
        {
          localStringBuilder.append(";via=");
          localStringBuilder.append(this.vias[i]);
        }
        i += 1;
        break;
        localStringBuilder.append(',');
      }
    }
    if (this.body != null)
    {
      i = 1;
      if (this.subject == null) {
        break label190;
      }
    }
    label190:
    for (j = 1;; j = 0)
    {
      if ((i != 0) || (j != 0))
      {
        localStringBuilder.append('?');
        if (i != 0)
        {
          localStringBuilder.append("body=");
          localStringBuilder.append(this.body);
        }
        if (j != 0)
        {
          if (i != 0) {
            localStringBuilder.append('&');
          }
          localStringBuilder.append("subject=");
          localStringBuilder.append(this.subject);
        }
      }
      return localStringBuilder.toString();
      i = 0;
      break;
    }
  }
  
  public String getSubject()
  {
    return this.subject;
  }
  
  public String[] getVias()
  {
    return this.vias;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\SMSParsedResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */