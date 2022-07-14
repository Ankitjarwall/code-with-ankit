package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzarw;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class zzf
  extends zza
{
  public static final Parcelable.Creator<zzf> CREATOR = new zzg();
  public static final Comparator<zzd> zzbju = new Comparator()
  {
    public int zza(zzd paramAnonymouszzd1, zzd paramAnonymouszzd2)
    {
      int i = paramAnonymouszzd1.zzBW();
      int j = paramAnonymouszzd2.zzBW();
      if (i != j) {
        if (i >= j) {}
      }
      do
      {
        return -1;
        return 1;
        i = paramAnonymouszzd1.zzIc();
        j = paramAnonymouszzd2.zzIc();
        if (i == j) {
          return 0;
        }
      } while (i < j);
      return 1;
    }
  };
  @Nullable
  private final String mTag;
  private final List<zzd> zzbjv;
  private final List<zzarw> zzbjw;
  
  public zzf(List<zzd> paramList, @Nullable String paramString, @Nullable List<zzarw> paramList1)
  {
    zzac.zzb(paramList, "transitions can't be null");
    boolean bool;
    if (paramList.size() > 0)
    {
      bool = true;
      zzac.zzb(bool, "transitions can't be empty.");
      zzD(paramList);
      this.zzbjv = Collections.unmodifiableList(paramList);
      this.mTag = paramString;
      if (paramList1 != null) {
        break label67;
      }
    }
    label67:
    for (paramList = Collections.emptyList();; paramList = Collections.unmodifiableList(paramList1))
    {
      this.zzbjw = paramList;
      return;
      bool = false;
      break;
    }
  }
  
  private static void zzD(List<zzd> paramList)
  {
    TreeSet localTreeSet = new TreeSet(zzbju);
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      zzd localzzd = (zzd)paramList.next();
      zzac.zzb(localTreeSet.add(localzzd), String.format("Found duplicated transition: %s.", new Object[] { localzzd }));
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (zzf)paramObject;
    } while ((zzaa.equal(this.zzbjv, ((zzf)paramObject).zzbjv)) && (zzaa.equal(this.mTag, ((zzf)paramObject).mTag)) && (zzaa.equal(this.zzbjw, ((zzf)paramObject).zzbjw)));
    return false;
  }
  
  @Nullable
  public String getTag()
  {
    return this.mTag;
  }
  
  public int hashCode()
  {
    int j = 0;
    int k = this.zzbjv.hashCode();
    if (this.mTag != null) {}
    for (int i = this.mTag.hashCode();; i = 0)
    {
      if (this.zzbjw != null) {
        j = this.zzbjw.hashCode();
      }
      return (i + k * 31) * 31 + j;
    }
  }
  
  public String toString()
  {
    String str1 = String.valueOf(this.zzbjv);
    String str2 = this.mTag;
    String str3 = String.valueOf(this.zzbjw);
    return String.valueOf(str1).length() + 61 + String.valueOf(str2).length() + String.valueOf(str3).length() + "ActivityTransitionRequest [mTransitions=" + str1 + ", mTag='" + str2 + "'" + ", mClients=" + str3 + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzg.zza(this, paramParcel, paramInt);
  }
  
  public List<zzd> zzId()
  {
    return this.zzbjv;
  }
  
  public List<zzarw> zzIe()
  {
    return this.zzbjw;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\location\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */