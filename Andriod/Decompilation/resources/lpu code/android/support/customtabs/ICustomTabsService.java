package android.support.customtabs;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import java.util.List;

public abstract interface ICustomTabsService
  extends IInterface
{
  public abstract Bundle extraCommand(String paramString, Bundle paramBundle)
    throws RemoteException;
  
  public abstract boolean mayLaunchUrl(ICustomTabsCallback paramICustomTabsCallback, Uri paramUri, Bundle paramBundle, List<Bundle> paramList)
    throws RemoteException;
  
  public abstract boolean newSession(ICustomTabsCallback paramICustomTabsCallback)
    throws RemoteException;
  
  public abstract int postMessage(ICustomTabsCallback paramICustomTabsCallback, String paramString, Bundle paramBundle)
    throws RemoteException;
  
  public abstract boolean requestPostMessageChannel(ICustomTabsCallback paramICustomTabsCallback, Uri paramUri)
    throws RemoteException;
  
  public abstract boolean updateVisuals(ICustomTabsCallback paramICustomTabsCallback, Bundle paramBundle)
    throws RemoteException;
  
  public abstract boolean validateRelationship(ICustomTabsCallback paramICustomTabsCallback, int paramInt, Uri paramUri, Bundle paramBundle)
    throws RemoteException;
  
  public abstract boolean warmup(long paramLong)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements ICustomTabsService
  {
    private static final String DESCRIPTOR = "android.support.customtabs.ICustomTabsService";
    static final int TRANSACTION_extraCommand = 5;
    static final int TRANSACTION_mayLaunchUrl = 4;
    static final int TRANSACTION_newSession = 3;
    static final int TRANSACTION_postMessage = 8;
    static final int TRANSACTION_requestPostMessageChannel = 7;
    static final int TRANSACTION_updateVisuals = 6;
    static final int TRANSACTION_validateRelationship = 9;
    static final int TRANSACTION_warmup = 2;
    
    public Stub()
    {
      attachInterface(this, "android.support.customtabs.ICustomTabsService");
    }
    
    public static ICustomTabsService asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("android.support.customtabs.ICustomTabsService");
      if ((localIInterface != null) && ((localIInterface instanceof ICustomTabsService))) {
        return (ICustomTabsService)localIInterface;
      }
      return new Proxy(paramIBinder);
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      int k = 0;
      int m = 0;
      int n = 0;
      int i1 = 0;
      int i = 0;
      int j = 0;
      boolean bool;
      Object localObject1;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("android.support.customtabs.ICustomTabsService");
        return true;
      case 2: 
        paramParcel1.enforceInterface("android.support.customtabs.ICustomTabsService");
        bool = warmup(paramParcel1.readLong());
        paramParcel2.writeNoException();
        paramInt1 = j;
        if (bool) {
          paramInt1 = 1;
        }
        paramParcel2.writeInt(paramInt1);
        return true;
      case 3: 
        paramParcel1.enforceInterface("android.support.customtabs.ICustomTabsService");
        bool = newSession(ICustomTabsCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramInt1 = k;
        if (bool) {
          paramInt1 = 1;
        }
        paramParcel2.writeInt(paramInt1);
        return true;
      case 4: 
        paramParcel1.enforceInterface("android.support.customtabs.ICustomTabsService");
        ICustomTabsCallback localICustomTabsCallback = ICustomTabsCallback.Stub.asInterface(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label298;
          }
        }
        for (localObject2 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; localObject2 = null)
        {
          bool = mayLaunchUrl(localICustomTabsCallback, (Uri)localObject1, (Bundle)localObject2, paramParcel1.createTypedArrayList(Bundle.CREATOR));
          paramParcel2.writeNoException();
          paramInt1 = m;
          if (bool) {
            paramInt1 = 1;
          }
          paramParcel2.writeInt(paramInt1);
          return true;
          localObject1 = null;
          break;
        }
      case 5: 
        paramParcel1.enforceInterface("android.support.customtabs.ICustomTabsService");
        localObject1 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          paramParcel1 = extraCommand((String)localObject1, paramParcel1);
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break;
          }
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 6: 
        paramParcel1.enforceInterface("android.support.customtabs.ICustomTabsService");
        localObject1 = ICustomTabsCallback.Stub.asInterface(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          bool = updateVisuals((ICustomTabsCallback)localObject1, paramParcel1);
          paramParcel2.writeNoException();
          paramInt1 = n;
          if (bool) {
            paramInt1 = 1;
          }
          paramParcel2.writeInt(paramInt1);
          return true;
        }
      case 7: 
        paramParcel1.enforceInterface("android.support.customtabs.ICustomTabsService");
        localObject1 = ICustomTabsCallback.Stub.asInterface(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          bool = requestPostMessageChannel((ICustomTabsCallback)localObject1, paramParcel1);
          paramParcel2.writeNoException();
          paramInt1 = i1;
          if (bool) {
            paramInt1 = 1;
          }
          paramParcel2.writeInt(paramInt1);
          return true;
        }
      case 8: 
        label298:
        paramParcel1.enforceInterface("android.support.customtabs.ICustomTabsService");
        localObject1 = ICustomTabsCallback.Stub.asInterface(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          paramInt1 = postMessage((ICustomTabsCallback)localObject1, (String)localObject2, paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        }
      }
      paramParcel1.enforceInterface("android.support.customtabs.ICustomTabsService");
      Object localObject2 = ICustomTabsCallback.Stub.asInterface(paramParcel1.readStrongBinder());
      paramInt1 = paramParcel1.readInt();
      if (paramParcel1.readInt() != 0)
      {
        localObject1 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
        if (paramParcel1.readInt() == 0) {
          break label684;
        }
      }
      label684:
      for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        bool = validateRelationship((ICustomTabsCallback)localObject2, paramInt1, (Uri)localObject1, paramParcel1);
        paramParcel2.writeNoException();
        paramInt1 = i;
        if (bool) {
          paramInt1 = 1;
        }
        paramParcel2.writeInt(paramInt1);
        return true;
        localObject1 = null;
        break;
      }
    }
    
    private static class Proxy
      implements ICustomTabsService
    {
      private IBinder mRemote;
      
      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return this.mRemote;
      }
      
      public Bundle extraCommand(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
            localParcel1.writeString(paramString);
            if (paramBundle != null)
            {
              localParcel1.writeInt(1);
              paramBundle.writeToParcel(localParcel1, 0);
              this.mRemote.transact(5, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramString = (Bundle)Bundle.CREATOR.createFromParcel(localParcel2);
                return paramString;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramString = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public String getInterfaceDescriptor()
      {
        return "android.support.customtabs.ICustomTabsService";
      }
      
      public boolean mayLaunchUrl(ICustomTabsCallback paramICustomTabsCallback, Uri paramUri, Bundle paramBundle, List<Bundle> paramList)
        throws RemoteException
      {
        boolean bool = true;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
            if (paramICustomTabsCallback != null)
            {
              paramICustomTabsCallback = paramICustomTabsCallback.asBinder();
              localParcel1.writeStrongBinder(paramICustomTabsCallback);
              if (paramUri != null)
              {
                localParcel1.writeInt(1);
                paramUri.writeToParcel(localParcel1, 0);
                if (paramBundle == null) {
                  break label151;
                }
                localParcel1.writeInt(1);
                paramBundle.writeToParcel(localParcel1, 0);
                localParcel1.writeTypedList(paramList);
                this.mRemote.transact(4, localParcel1, localParcel2, 0);
                localParcel2.readException();
                int i = localParcel2.readInt();
                if (i == 0) {
                  break label160;
                }
                return bool;
              }
            }
            else
            {
              paramICustomTabsCallback = null;
              continue;
            }
            localParcel1.writeInt(0);
            continue;
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          label151:
          continue;
          label160:
          bool = false;
        }
      }
      
      /* Error */
      public boolean newSession(ICustomTabsCallback paramICustomTabsCallback)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_3
        //   2: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 34
        //   16: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_1
        //   20: ifnull +61 -> 81
        //   23: aload_1
        //   24: invokeinterface 86 1 0
        //   29: astore_1
        //   30: aload 4
        //   32: aload_1
        //   33: invokevirtual 89	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload_0
        //   37: getfield 19	android/support/customtabs/ICustomTabsService$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   40: iconst_3
        //   41: aload 4
        //   43: aload 5
        //   45: iconst_0
        //   46: invokeinterface 57 5 0
        //   51: pop
        //   52: aload 5
        //   54: invokevirtual 60	android/os/Parcel:readException	()V
        //   57: aload 5
        //   59: invokevirtual 64	android/os/Parcel:readInt	()I
        //   62: istore_2
        //   63: iload_2
        //   64: ifeq +5 -> 69
        //   67: iconst_1
        //   68: istore_3
        //   69: aload 5
        //   71: invokevirtual 77	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 77	android/os/Parcel:recycle	()V
        //   79: iload_3
        //   80: ireturn
        //   81: aconst_null
        //   82: astore_1
        //   83: goto -53 -> 30
        //   86: astore_1
        //   87: aload 5
        //   89: invokevirtual 77	android/os/Parcel:recycle	()V
        //   92: aload 4
        //   94: invokevirtual 77	android/os/Parcel:recycle	()V
        //   97: aload_1
        //   98: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	99	0	this	Proxy
        //   0	99	1	paramICustomTabsCallback	ICustomTabsCallback
        //   62	2	2	i	int
        //   1	79	3	bool	boolean
        //   5	88	4	localParcel1	Parcel
        //   10	78	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	86	finally
        //   23	30	86	finally
        //   30	63	86	finally
      }
      
      /* Error */
      public int postMessage(ICustomTabsCallback paramICustomTabsCallback, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 34
        //   14: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +81 -> 99
        //   21: aload_1
        //   22: invokeinterface 86 1 0
        //   27: astore_1
        //   28: aload 5
        //   30: aload_1
        //   31: invokevirtual 89	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 5
        //   36: aload_2
        //   37: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload_3
        //   41: ifnull +63 -> 104
        //   44: aload 5
        //   46: iconst_1
        //   47: invokevirtual 45	android/os/Parcel:writeInt	(I)V
        //   50: aload_3
        //   51: aload 5
        //   53: iconst_0
        //   54: invokevirtual 51	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   57: aload_0
        //   58: getfield 19	android/support/customtabs/ICustomTabsService$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   61: bipush 8
        //   63: aload 5
        //   65: aload 6
        //   67: iconst_0
        //   68: invokeinterface 57 5 0
        //   73: pop
        //   74: aload 6
        //   76: invokevirtual 60	android/os/Parcel:readException	()V
        //   79: aload 6
        //   81: invokevirtual 64	android/os/Parcel:readInt	()I
        //   84: istore 4
        //   86: aload 6
        //   88: invokevirtual 77	android/os/Parcel:recycle	()V
        //   91: aload 5
        //   93: invokevirtual 77	android/os/Parcel:recycle	()V
        //   96: iload 4
        //   98: ireturn
        //   99: aconst_null
        //   100: astore_1
        //   101: goto -73 -> 28
        //   104: aload 5
        //   106: iconst_0
        //   107: invokevirtual 45	android/os/Parcel:writeInt	(I)V
        //   110: goto -53 -> 57
        //   113: astore_1
        //   114: aload 6
        //   116: invokevirtual 77	android/os/Parcel:recycle	()V
        //   119: aload 5
        //   121: invokevirtual 77	android/os/Parcel:recycle	()V
        //   124: aload_1
        //   125: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	126	0	this	Proxy
        //   0	126	1	paramICustomTabsCallback	ICustomTabsCallback
        //   0	126	2	paramString	String
        //   0	126	3	paramBundle	Bundle
        //   84	13	4	i	int
        //   3	117	5	localParcel1	Parcel
        //   8	107	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	113	finally
        //   21	28	113	finally
        //   28	40	113	finally
        //   44	57	113	finally
        //   57	86	113	finally
        //   104	110	113	finally
      }
      
      public boolean requestPostMessageChannel(ICustomTabsCallback paramICustomTabsCallback, Uri paramUri)
        throws RemoteException
      {
        boolean bool = true;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        label126:
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
            if (paramICustomTabsCallback != null)
            {
              paramICustomTabsCallback = paramICustomTabsCallback.asBinder();
              localParcel1.writeStrongBinder(paramICustomTabsCallback);
              if (paramUri != null)
              {
                localParcel1.writeInt(1);
                paramUri.writeToParcel(localParcel1, 0);
                this.mRemote.transact(7, localParcel1, localParcel2, 0);
                localParcel2.readException();
                int i = localParcel2.readInt();
                if (i == 0) {
                  break label126;
                }
                return bool;
              }
            }
            else
            {
              paramICustomTabsCallback = null;
              continue;
            }
            localParcel1.writeInt(0);
            continue;
            bool = false;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public boolean updateVisuals(ICustomTabsCallback paramICustomTabsCallback, Bundle paramBundle)
        throws RemoteException
      {
        boolean bool = true;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        label126:
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
            if (paramICustomTabsCallback != null)
            {
              paramICustomTabsCallback = paramICustomTabsCallback.asBinder();
              localParcel1.writeStrongBinder(paramICustomTabsCallback);
              if (paramBundle != null)
              {
                localParcel1.writeInt(1);
                paramBundle.writeToParcel(localParcel1, 0);
                this.mRemote.transact(6, localParcel1, localParcel2, 0);
                localParcel2.readException();
                int i = localParcel2.readInt();
                if (i == 0) {
                  break label126;
                }
                return bool;
              }
            }
            else
            {
              paramICustomTabsCallback = null;
              continue;
            }
            localParcel1.writeInt(0);
            continue;
            bool = false;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public boolean validateRelationship(ICustomTabsCallback paramICustomTabsCallback, int paramInt, Uri paramUri, Bundle paramBundle)
        throws RemoteException
      {
        boolean bool = true;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
            if (paramICustomTabsCallback != null)
            {
              paramICustomTabsCallback = paramICustomTabsCallback.asBinder();
              localParcel1.writeStrongBinder(paramICustomTabsCallback);
              localParcel1.writeInt(paramInt);
              if (paramUri != null)
              {
                localParcel1.writeInt(1);
                paramUri.writeToParcel(localParcel1, 0);
                if (paramBundle == null) {
                  break label151;
                }
                localParcel1.writeInt(1);
                paramBundle.writeToParcel(localParcel1, 0);
                this.mRemote.transact(9, localParcel1, localParcel2, 0);
                localParcel2.readException();
                paramInt = localParcel2.readInt();
                if (paramInt == 0) {
                  break label160;
                }
                return bool;
              }
            }
            else
            {
              paramICustomTabsCallback = null;
              continue;
            }
            localParcel1.writeInt(0);
            continue;
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          label151:
          continue;
          label160:
          bool = false;
        }
      }
      
      public boolean warmup(long paramLong)
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
          localParcel1.writeLong(paramLong);
          this.mRemote.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0) {
            bool = true;
          }
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\customtabs\ICustomTabsService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */