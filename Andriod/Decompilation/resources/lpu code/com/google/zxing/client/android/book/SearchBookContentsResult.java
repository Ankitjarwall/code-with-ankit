package com.google.zxing.client.android.book;

final class SearchBookContentsResult
{
  private static String query = null;
  private final String pageId;
  private final String pageNumber;
  private final String snippet;
  private final boolean validSnippet;
  
  SearchBookContentsResult(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    this.pageId = paramString1;
    this.pageNumber = paramString2;
    this.snippet = paramString3;
    this.validSnippet = paramBoolean;
  }
  
  public static String getQuery()
  {
    return query;
  }
  
  public static void setQuery(String paramString)
  {
    query = paramString;
  }
  
  public String getPageId()
  {
    return this.pageId;
  }
  
  public String getPageNumber()
  {
    return this.pageNumber;
  }
  
  public String getSnippet()
  {
    return this.snippet;
  }
  
  public boolean getValidSnippet()
  {
    return this.validSnippet;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\book\SearchBookContentsResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */