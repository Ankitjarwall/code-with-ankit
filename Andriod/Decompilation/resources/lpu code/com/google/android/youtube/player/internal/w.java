package com.google.android.youtube.player.internal;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class w
{
  private static IBinder a(Class<?> paramClass, IBinder paramIBinder1, IBinder paramIBinder2, IBinder paramIBinder3, boolean paramBoolean)
    throws w.a
  {
    try
    {
      paramIBinder1 = (IBinder)paramClass.getConstructor(new Class[] { IBinder.class, IBinder.class, IBinder.class, Boolean.TYPE }).newInstance(new Object[] { paramIBinder1, paramIBinder2, paramIBinder3, Boolean.valueOf(paramBoolean) });
      return paramIBinder1;
    }
    catch (NoSuchMethodException paramIBinder1)
    {
      paramClass = String.valueOf(paramClass.getName());
      if (paramClass.length() != 0) {}
      for (paramClass = "Could not find the right constructor for ".concat(paramClass);; paramClass = new String("Could not find the right constructor for ")) {
        throw new a(paramClass, paramIBinder1);
      }
    }
    catch (InvocationTargetException paramIBinder1)
    {
      paramClass = String.valueOf(paramClass.getName());
      if (paramClass.length() != 0) {}
      for (paramClass = "Exception thrown by invoked constructor in ".concat(paramClass);; paramClass = new String("Exception thrown by invoked constructor in ")) {
        throw new a(paramClass, paramIBinder1);
      }
    }
    catch (InstantiationException paramIBinder1)
    {
      paramClass = String.valueOf(paramClass.getName());
      if (paramClass.length() != 0) {}
      for (paramClass = "Unable to instantiate the dynamic class ".concat(paramClass);; paramClass = new String("Unable to instantiate the dynamic class ")) {
        throw new a(paramClass, paramIBinder1);
      }
    }
    catch (IllegalAccessException paramIBinder1)
    {
      paramClass = String.valueOf(paramClass.getName());
      if (paramClass.length() == 0) {}
    }
    for (paramClass = "Unable to call the default constructor of ".concat(paramClass);; paramClass = new String("Unable to call the default constructor of ")) {
      throw new a(paramClass, paramIBinder1);
    }
  }
  
  private static IBinder a(ClassLoader paramClassLoader, String paramString, IBinder paramIBinder1, IBinder paramIBinder2, IBinder paramIBinder3, boolean paramBoolean)
    throws w.a
  {
    try
    {
      paramClassLoader = a(paramClassLoader.loadClass(paramString), paramIBinder1, paramIBinder2, paramIBinder3, paramBoolean);
      return paramClassLoader;
    }
    catch (ClassNotFoundException paramIBinder1)
    {
      paramClassLoader = String.valueOf(paramString);
      if (paramClassLoader.length() == 0) {}
    }
    for (paramClassLoader = "Unable to find dynamic class ".concat(paramClassLoader);; paramClassLoader = new String("Unable to find dynamic class ")) {
      throw new a(paramClassLoader, paramIBinder1);
    }
  }
  
  public static d a(Activity paramActivity, IBinder paramIBinder, boolean paramBoolean)
    throws w.a
  {
    ab.a(paramActivity);
    ab.a(paramIBinder);
    Context localContext = z.b(paramActivity);
    if (localContext == null) {
      throw new a("Could not create remote context");
    }
    return d.a.a(a(localContext.getClassLoader(), "com.google.android.youtube.api.jar.client.RemoteEmbeddedPlayer", v.a(localContext).asBinder(), v.a(paramActivity).asBinder(), paramIBinder, paramBoolean));
  }
  
  public static final class a
    extends Exception
  {
    public a(String paramString)
    {
      super();
    }
    
    public a(String paramString, Throwable paramThrowable)
    {
      super(paramThrowable);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\youtube\player\internal\w.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */