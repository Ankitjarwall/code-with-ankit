package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;

@KeepName
public final class BinderWrapper
  implements Parcelable
{
  public static final Parcelable.Creator<BinderWrapper> CREATOR = new Parcelable.Creator()
  {
    public BinderWrapper zzaR(Parcel paramAnonymousParcel)
    {
      return new BinderWrapper(paramAnonymousParcel, null);
    }
    
    public BinderWrapper[] zzcT(int paramAnonymousInt)
    {
      return new BinderWrapper[paramAnonymousInt];
    }
  };
  private IBinder zzaFz = null;
  
  public BinderWrapper() {}
  
  public BinderWrapper(IBinder paramIBinder)
  {
    this.zzaFz = paramIBinder;
  }
  
  private BinderWrapper(Parcel paramParcel)
  {
    this.zzaFz = paramParcel.readStrongBinder();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeStrongBinder(this.zzaFz);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\BinderWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */