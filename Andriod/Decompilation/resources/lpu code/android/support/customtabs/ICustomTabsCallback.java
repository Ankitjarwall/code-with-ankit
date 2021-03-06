package android.support.customtabs;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface ICustomTabsCallback
  extends IInterface
{
  public abstract void extraCallback(String paramString, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void onMessageChannelReady(Bundle paramBundle)
    throws RemoteException;
  
  public abstract void onNavigationEvent(int paramInt, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void onPostMessage(String paramString, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void onRelationshipValidationResult(int paramInt, Uri paramUri, boolean paramBoolean, Bundle paramBundle)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements ICustomTabsCallback
  {
    private static final String DESCRIPTOR = "android.support.customtabs.ICustomTabsCallback";
    static final int TRANSACTION_extraCallback = 3;
    static final int TRANSACTION_onMessageChannelReady = 4;
    static final int TRANSACTION_onNavigationEvent = 2;
    static final int TRANSACTION_onPostMessage = 5;
    static final int TRANSACTION_onRelationshipValidationResult = 6;
    
    public Stub()
    {
      attachInterface(this, "android.support.customtabs.ICustomTabsCallback");
    }
    
    public static ICustomTabsCallback asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("android.support.customtabs.ICustomTabsCallback");
      if ((localIInterface != null) && ((localIInterface instanceof ICustomTabsCallback))) {
        return (ICustomTabsCallback)localIInterface;
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
      Object localObject;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("android.support.customtabs.ICustomTabsCallback");
        return true;
      case 2: 
        paramParcel1.enforceInterface("android.support.customtabs.ICustomTabsCallback");
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          onNavigationEvent(paramInt1, paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 3: 
        paramParcel1.enforceInterface("android.support.customtabs.ICustomTabsCallback");
        localObject = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          extraCallback((String)localObject, paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 4: 
        paramParcel1.enforceInterface("android.support.customtabs.ICustomTabsCallback");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          onMessageChannelReady(paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 5: 
        paramParcel1.enforceInterface("android.support.customtabs.ICustomTabsCallback");
        localObject = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          onPostMessage((String)localObject, paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      }
      paramParcel1.enforceInterface("android.support.customtabs.ICustomTabsCallback");
      paramInt1 = paramParcel1.readInt();
      boolean bool;
      if (paramParcel1.readInt() != 0)
      {
        localObject = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
        if (paramParcel1.readInt() == 0) {
          break label352;
        }
        bool = true;
        label310:
        if (paramParcel1.readInt() == 0) {
          break label358;
        }
      }
      label352:
      label358:
      for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        onRelationshipValidationResult(paramInt1, (Uri)localObject, bool, paramParcel1);
        paramParcel2.writeNoException();
        return true;
        localObject = null;
        break;
        bool = false;
        break label310;
      }
    }
    
    private static class Proxy
      implements ICustomTabsCallback
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
      
      /* Error */
      public void extraCallback(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 34
        //   12: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_3
        //   16: aload_1
        //   17: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   20: aload_2
        //   21: ifnull +44 -> 65
        //   24: aload_3
        //   25: iconst_1
        //   26: invokevirtual 45	android/os/Parcel:writeInt	(I)V
        //   29: aload_2
        //   30: aload_3
        //   31: iconst_0
        //   32: invokevirtual 51	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload_0
        //   36: getfield 19	android/support/customtabs/ICustomTabsCallback$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   39: iconst_3
        //   40: aload_3
        //   41: aload 4
        //   43: iconst_0
        //   44: invokeinterface 57 5 0
        //   49: pop
        //   50: aload 4
        //   52: invokevirtual 60	android/os/Parcel:readException	()V
        //   55: aload 4
        //   57: invokevirtual 63	android/os/Parcel:recycle	()V
        //   60: aload_3
        //   61: invokevirtual 63	android/os/Parcel:recycle	()V
        //   64: return
        //   65: aload_3
        //   66: iconst_0
        //   67: invokevirtual 45	android/os/Parcel:writeInt	(I)V
        //   70: goto -35 -> 35
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 63	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 63	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramString	String
        //   0	85	2	paramBundle	Bundle
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	20	73	finally
        //   24	35	73	finally
        //   35	55	73	finally
        //   65	70	73	finally
      }
      
      public String getInterfaceDescriptor()
      {
        return "android.support.customtabs.ICustomTabsCallback";
      }
      
      /* Error */
      public void onMessageChannelReady(Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 34
        //   11: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +41 -> 56
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 45	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 51	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 19	android/support/customtabs/ICustomTabsCallback$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   33: iconst_4
        //   34: aload_2
        //   35: aload_3
        //   36: iconst_0
        //   37: invokeinterface 57 5 0
        //   42: pop
        //   43: aload_3
        //   44: invokevirtual 60	android/os/Parcel:readException	()V
        //   47: aload_3
        //   48: invokevirtual 63	android/os/Parcel:recycle	()V
        //   51: aload_2
        //   52: invokevirtual 63	android/os/Parcel:recycle	()V
        //   55: return
        //   56: aload_2
        //   57: iconst_0
        //   58: invokevirtual 45	android/os/Parcel:writeInt	(I)V
        //   61: goto -32 -> 29
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 63	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 63	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	Proxy
        //   0	75	1	paramBundle	Bundle
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	29	64	finally
        //   29	47	64	finally
        //   56	61	64	finally
      }
      
      /* Error */
      public void onNavigationEvent(int paramInt, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 34
        //   12: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_3
        //   16: iload_1
        //   17: invokevirtual 45	android/os/Parcel:writeInt	(I)V
        //   20: aload_2
        //   21: ifnull +44 -> 65
        //   24: aload_3
        //   25: iconst_1
        //   26: invokevirtual 45	android/os/Parcel:writeInt	(I)V
        //   29: aload_2
        //   30: aload_3
        //   31: iconst_0
        //   32: invokevirtual 51	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload_0
        //   36: getfield 19	android/support/customtabs/ICustomTabsCallback$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   39: iconst_2
        //   40: aload_3
        //   41: aload 4
        //   43: iconst_0
        //   44: invokeinterface 57 5 0
        //   49: pop
        //   50: aload 4
        //   52: invokevirtual 60	android/os/Parcel:readException	()V
        //   55: aload 4
        //   57: invokevirtual 63	android/os/Parcel:recycle	()V
        //   60: aload_3
        //   61: invokevirtual 63	android/os/Parcel:recycle	()V
        //   64: return
        //   65: aload_3
        //   66: iconst_0
        //   67: invokevirtual 45	android/os/Parcel:writeInt	(I)V
        //   70: goto -35 -> 35
        //   73: astore_2
        //   74: aload 4
        //   76: invokevirtual 63	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 63	android/os/Parcel:recycle	()V
        //   83: aload_2
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramInt	int
        //   0	85	2	paramBundle	Bundle
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	20	73	finally
        //   24	35	73	finally
        //   35	55	73	finally
        //   65	70	73	finally
      }
      
      /* Error */
      public void onPostMessage(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 34
        //   12: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_3
        //   16: aload_1
        //   17: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   20: aload_2
        //   21: ifnull +44 -> 65
        //   24: aload_3
        //   25: iconst_1
        //   26: invokevirtual 45	android/os/Parcel:writeInt	(I)V
        //   29: aload_2
        //   30: aload_3
        //   31: iconst_0
        //   32: invokevirtual 51	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload_0
        //   36: getfield 19	android/support/customtabs/ICustomTabsCallback$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   39: iconst_5
        //   40: aload_3
        //   41: aload 4
        //   43: iconst_0
        //   44: invokeinterface 57 5 0
        //   49: pop
        //   50: aload 4
        //   52: invokevirtual 60	android/os/Parcel:readException	()V
        //   55: aload 4
        //   57: invokevirtual 63	android/os/Parcel:recycle	()V
        //   60: aload_3
        //   61: invokevirtual 63	android/os/Parcel:recycle	()V
        //   64: return
        //   65: aload_3
        //   66: iconst_0
        //   67: invokevirtual 45	android/os/Parcel:writeInt	(I)V
        //   70: goto -35 -> 35
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 63	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 63	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramString	String
        //   0	85	2	paramBundle	Bundle
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	20	73	finally
        //   24	35	73	finally
        //   35	55	73	finally
        //   65	70	73	finally
      }
      
      public void onRelationshipValidationResult(int paramInt, Uri paramUri, boolean paramBoolean, Bundle paramBundle)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("android.support.customtabs.ICustomTabsCallback");
            localParcel1.writeInt(paramInt);
            if (paramUri != null)
            {
              localParcel1.writeInt(1);
              paramUri.writeToParcel(localParcel1, 0);
              break label140;
              localParcel1.writeInt(paramInt);
              if (paramBundle != null)
              {
                localParcel1.writeInt(1);
                paramBundle.writeToParcel(localParcel1, 0);
                label71:
                this.mRemote.transact(6, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          label140:
          do
          {
            paramInt = 0;
            break;
            localParcel1.writeInt(0);
            break label71;
          } while (!paramBoolean);
          paramInt = i;
        }
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\customtabs\ICustomTabsCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */