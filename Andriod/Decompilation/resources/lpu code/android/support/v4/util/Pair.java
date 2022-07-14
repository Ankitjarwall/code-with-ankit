package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Pair<F, S>
{
  @Nullable
  public final F first;
  @Nullable
  public final S second;
  
  public Pair(@Nullable F paramF, @Nullable S paramS)
  {
    this.first = paramF;
    this.second = paramS;
  }
  
  @NonNull
  public static <A, B> Pair<A, B> create(@Nullable A paramA, @Nullable B paramB)
  {
    return new Pair(paramA, paramB);
  }
  
  private static boolean objectsEqual(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Pair)) {}
    do
    {
      return false;
      paramObject = (Pair)paramObject;
    } while ((!objectsEqual(((Pair)paramObject).first, this.first)) || (!objectsEqual(((Pair)paramObject).second, this.second)));
    return true;
  }
  
  public int hashCode()
  {
    int j = 0;
    int i;
    if (this.first == null)
    {
      i = 0;
      if (this.second != null) {
        break label33;
      }
    }
    for (;;)
    {
      return i ^ j;
      i = this.first.hashCode();
      break;
      label33:
      j = this.second.hashCode();
    }
  }
  
  public String toString()
  {
    return "Pair{" + String.valueOf(this.first) + " " + String.valueOf(this.second) + "}";
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\util\Pair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */