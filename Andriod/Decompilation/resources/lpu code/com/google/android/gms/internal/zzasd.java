package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface zzasd
  extends IInterface
{
  public abstract void zza(int paramInt, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zza(int paramInt, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void zzb(int paramInt, String[] paramArrayOfString)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzasd
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.location.internal.IGeofencerCallbacks");
    }
    
    public static zzasd zzdh(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
      if ((localIInterface != null) && ((localIInterface instanceof zzasd))) {
        return (zzasd)localIInterface;
      }
      return new zza(paramIBinder);
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
        paramParcel2.writeString("com.google.android.gms.location.internal.IGeofencerCallbacks");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
        zza(paramParcel1.readInt(), paramParcel1.createStringArray());
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
        zzb(paramParcel1.readInt(), paramParcel1.createStringArray());
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
      paramInt1 = paramParcel1.readInt();
      if (paramParcel1.readInt() != 0) {}
      for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        zza(paramInt1, paramParcel1);
        return true;
      }
    }
    
    private static class zza
      implements zzasd
    {
      private IBinder zzrk;
      
      zza(IBinder paramIBinder)
      {
        this.zzrk = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return this.zzrk;
      }
      
      /* Error */
      public void zza(int paramInt, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: aload_3
        //   5: ldc 32
        //   7: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   10: aload_3
        //   11: iload_1
        //   12: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   15: aload_2
        //   16: ifnull +33 -> 49
        //   19: aload_3
        //   20: iconst_1
        //   21: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   24: aload_2
        //   25: aload_3
        //   26: iconst_0
        //   27: invokevirtual 46	android/app/PendingIntent:writeToParcel	(Landroid/os/Parcel;I)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/internal/zzasd$zza$zza:zzrk	Landroid/os/IBinder;
        //   34: iconst_3
        //   35: aload_3
        //   36: aconst_null
        //   37: iconst_1
        //   38: invokeinterface 52 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 55	android/os/Parcel:recycle	()V
        //   48: return
        //   49: aload_3
        //   50: iconst_0
        //   51: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   54: goto -24 -> 30
        //   57: astore_2
        //   58: aload_3
        //   59: invokevirtual 55	android/os/Parcel:recycle	()V
        //   62: aload_2
        //   63: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	64	0	this	zza
        //   0	64	1	paramInt	int
        //   0	64	2	paramPendingIntent	PendingIntent
        //   3	56	3	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   4	15	57	finally
        //   19	30	57	finally
        //   30	44	57	finally
        //   49	54	57	finally
      }
      
      public void zza(int paramInt, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.location.internal.IGeofencerCallbacks");
          localParcel.writeInt(paramInt);
          localParcel.writeStringArray(paramArrayOfString);
          this.zzrk.transact(1, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void zzb(int paramInt, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.location.internal.IGeofencerCallbacks");
          localParcel.writeInt(paramInt);
          localParcel.writeStringArray(paramArrayOfString);
          this.zzrk.transact(2, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\zzasd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */