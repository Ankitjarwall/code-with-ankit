package com.google.zxing.oned.rss.expanded;

import java.util.ArrayList;
import java.util.List;

final class ExpandedRow
{
  private final List<ExpandedPair> pairs;
  private final int rowNumber;
  private final boolean wasReversed;
  
  ExpandedRow(List<ExpandedPair> paramList, int paramInt, boolean paramBoolean)
  {
    this.pairs = new ArrayList(paramList);
    this.rowNumber = paramInt;
    this.wasReversed = paramBoolean;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof ExpandedRow)) {}
    do
    {
      return false;
      paramObject = (ExpandedRow)paramObject;
    } while ((!this.pairs.equals(((ExpandedRow)paramObject).getPairs())) || (this.wasReversed != ((ExpandedRow)paramObject).wasReversed));
    return true;
  }
  
  List<ExpandedPair> getPairs()
  {
    return this.pairs;
  }
  
  int getRowNumber()
  {
    return this.rowNumber;
  }
  
  public int hashCode()
  {
    return this.pairs.hashCode() ^ Boolean.valueOf(this.wasReversed).hashCode();
  }
  
  boolean isEquivalent(List<ExpandedPair> paramList)
  {
    return this.pairs.equals(paramList);
  }
  
  boolean isReversed()
  {
    return this.wasReversed;
  }
  
  public String toString()
  {
    return "{ " + this.pairs + " }";
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\rss\expanded\ExpandedRow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */