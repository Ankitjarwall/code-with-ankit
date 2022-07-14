package com.google.android.gms.iid;

import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.common.internal.ReflectedParcelable;

public class MessengerCompat
  implements ReflectedParcelable
{
  public static final Parcelable.Creator<MessengerCompat> CREATOR = new Parcelable.Creator()
  {
    public MessengerCompat zzgs(Parcel paramAnonymousParcel)
    {
      paramAnonymousParcel = paramAnonymousParcel.readStrongBinder();
      if (paramAnonymousParcel != null) {
        return new MessengerCompat(paramAnonymousParcel);
      }
      return null;
    }
    
    public MessengerCompat[] zzjK(int paramAnonymousInt)
    {
      return new MessengerCompat[paramAnonymousInt];
    }
  };
  Messenger zzbhO;
  zzb zzbhP;
  
  public MessengerCompat(IBinder paramIBinder)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      this.zzbhO = new Messenger(paramIBinder);
      return;
    }
    this.zzbhP = zzb.zza.zzcZ(paramIBinder);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    try
    {
      boolean bool = getBinder().equals(((MessengerCompat)paramObject).getBinder());
      return bool;
    }
    catch (ClassCastException paramObject) {}
    return false;
  }
  
  public IBinder getBinder()
  {
    if (this.zzbhO != null) {
      return this.zzbhO.getBinder();
    }
    return this.zzbhP.asBinder();
  }
  
  public int hashCode()
  {
    return getBinder().hashCode();
  }
  
  public void send(Message paramMessage)
    throws RemoteException
  {
    if (this.zzbhO != null)
    {
      this.zzbhO.send(paramMessage);
      return;
    }
    this.zzbhP.send(paramMessage);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (this.zzbhO != null)
    {
      paramParcel.writeStrongBinder(this.zzbhO.getBinder());
      return;
    }
    paramParcel.writeStrongBinder(this.zzbhP.asBinder());
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\iid\MessengerCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */