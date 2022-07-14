package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract interface zzt
  extends IInterface
{
  public abstract IObjectWrapper zzva()
    throws RemoteException;
  
  public abstract int zzvb()
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzt
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.common.internal.ICertData");
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.common.internal.ICertData");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.ICertData");
        paramParcel1 = zzva();
        paramParcel2.writeNoException();
        if (paramParcel1 != null) {}
        for (paramParcel1 = paramParcel1.asBinder();; paramParcel1 = null)
        {
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.common.internal.ICertData");
      paramInt1 = zzvb();
      paramParcel2.writeNoException();
      paramParcel2.writeInt(paramInt1);
      return true;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\zzt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */