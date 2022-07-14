package com.google.devtools.build.android.desugar.runtime;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public final class ThrowableExtension
{
  private static final String ANDROID_OS_BUILD_VERSION = "android.os.Build$VERSION";
  static final AbstractDesugaringStrategy STRATEGY;
  public static final String SYSTEM_PROPERTY_TWR_DISABLE_MIMIC = "com.google.devtools.build.android.desugar.runtime.twr_disable_mimic";
  
  static
  {
    for (;;)
    {
      try
      {
        localObject = readApiLevelFromBuildVersion();
        if ((localObject == null) || (((Integer)localObject).intValue() < 19)) {
          continue;
        }
        localObject = new ReuseDesugaringStrategy();
      }
      catch (Throwable localThrowable)
      {
        Object localObject;
        System.err.println("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy " + NullDesugaringStrategy.class.getName() + "will be used. The error is: ");
        localThrowable.printStackTrace(System.err);
        NullDesugaringStrategy localNullDesugaringStrategy = new NullDesugaringStrategy();
        continue;
      }
      STRATEGY = (AbstractDesugaringStrategy)localObject;
      return;
      if (useMimicStrategy()) {
        localObject = new NullDesugaringStrategy();
      } else {
        localObject = new NullDesugaringStrategy();
      }
    }
  }
  
  public static void addSuppressed(Throwable paramThrowable1, Throwable paramThrowable2)
  {
    STRATEGY.addSuppressed(paramThrowable1, paramThrowable2);
  }
  
  public static AbstractDesugaringStrategy getStrategy()
  {
    return STRATEGY;
  }
  
  public static Throwable[] getSuppressed(Throwable paramThrowable)
  {
    return STRATEGY.getSuppressed(paramThrowable);
  }
  
  public static void printStackTrace(Throwable paramThrowable)
  {
    STRATEGY.printStackTrace(paramThrowable);
  }
  
  public static void printStackTrace(Throwable paramThrowable, PrintStream paramPrintStream)
  {
    STRATEGY.printStackTrace(paramThrowable, paramPrintStream);
  }
  
  public static void printStackTrace(Throwable paramThrowable, PrintWriter paramPrintWriter)
  {
    STRATEGY.printStackTrace(paramThrowable, paramPrintWriter);
  }
  
  private static Integer readApiLevelFromBuildVersion()
  {
    try
    {
      Integer localInteger = (Integer)Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
      return localInteger;
    }
    catch (Exception localException)
    {
      System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
      localException.printStackTrace(System.err);
    }
    return null;
  }
  
  private static boolean useMimicStrategy()
  {
    return !Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic");
  }
  
  static abstract class AbstractDesugaringStrategy
  {
    protected static final Throwable[] EMPTY_THROWABLE_ARRAY = new Throwable[0];
    
    public abstract void addSuppressed(Throwable paramThrowable1, Throwable paramThrowable2);
    
    public abstract Throwable[] getSuppressed(Throwable paramThrowable);
    
    public abstract void printStackTrace(Throwable paramThrowable);
    
    public abstract void printStackTrace(Throwable paramThrowable, PrintStream paramPrintStream);
    
    public abstract void printStackTrace(Throwable paramThrowable, PrintWriter paramPrintWriter);
  }
  
  static final class ConcurrentWeakIdentityHashMap
  {
    private final ConcurrentHashMap<WeakKey, List<Throwable>> map = new ConcurrentHashMap(16, 0.75F, 10);
    private final ReferenceQueue<Throwable> referenceQueue = new ReferenceQueue();
    
    void deleteEmptyKeys()
    {
      for (Reference localReference = this.referenceQueue.poll(); localReference != null; localReference = this.referenceQueue.poll()) {
        this.map.remove(localReference);
      }
    }
    
    public List<Throwable> get(Throwable paramThrowable, boolean paramBoolean)
    {
      deleteEmptyKeys();
      Object localObject = new WeakKey(paramThrowable, null);
      localObject = (List)this.map.get(localObject);
      if (!paramBoolean) {
        return (List<Throwable>)localObject;
      }
      if (localObject != null) {
        return (List<Throwable>)localObject;
      }
      localObject = new Vector(2);
      paramThrowable = (List)this.map.putIfAbsent(new WeakKey(paramThrowable, this.referenceQueue), localObject);
      if (paramThrowable == null) {
        paramThrowable = (Throwable)localObject;
      }
      for (;;)
      {
        return paramThrowable;
      }
    }
    
    int size()
    {
      return this.map.size();
    }
    
    private static final class WeakKey
      extends WeakReference<Throwable>
    {
      private final int hash;
      
      public WeakKey(Throwable paramThrowable, ReferenceQueue<Throwable> paramReferenceQueue)
      {
        super(paramReferenceQueue);
        if (paramThrowable == null) {
          throw new NullPointerException("The referent cannot be null");
        }
        this.hash = System.identityHashCode(paramThrowable);
      }
      
      public boolean equals(Object paramObject)
      {
        boolean bool2 = true;
        boolean bool1;
        if ((paramObject == null) || (paramObject.getClass() != getClass())) {
          bool1 = false;
        }
        do
        {
          do
          {
            return bool1;
            bool1 = bool2;
          } while (this == paramObject);
          paramObject = (WeakKey)paramObject;
          if (this.hash != ((WeakKey)paramObject).hash) {
            break;
          }
          bool1 = bool2;
        } while (get() == ((WeakKey)paramObject).get());
        return false;
      }
      
      public int hashCode()
      {
        return this.hash;
      }
    }
  }
  
  static final class MimicDesugaringStrategy
    extends ThrowableExtension.AbstractDesugaringStrategy
  {
    static final String SUPPRESSED_PREFIX = "Suppressed: ";
    private final ThrowableExtension.ConcurrentWeakIdentityHashMap map = new ThrowableExtension.ConcurrentWeakIdentityHashMap();
    
    public void addSuppressed(Throwable paramThrowable1, Throwable paramThrowable2)
    {
      if (paramThrowable2 == paramThrowable1) {
        throw new IllegalArgumentException("Self suppression is not allowed.", paramThrowable2);
      }
      if (paramThrowable2 == null) {
        throw new NullPointerException("The suppressed exception cannot be null.");
      }
      this.map.get(paramThrowable1, true).add(paramThrowable2);
    }
    
    public Throwable[] getSuppressed(Throwable paramThrowable)
    {
      paramThrowable = this.map.get(paramThrowable, false);
      if ((paramThrowable == null) || (paramThrowable.isEmpty())) {
        return EMPTY_THROWABLE_ARRAY;
      }
      return (Throwable[])paramThrowable.toArray(EMPTY_THROWABLE_ARRAY);
    }
    
    public void printStackTrace(Throwable paramThrowable)
    {
      paramThrowable.printStackTrace();
      paramThrowable = this.map.get(paramThrowable, false);
      if (paramThrowable == null) {
        return;
      }
      try
      {
        Iterator localIterator = paramThrowable.iterator();
        while (localIterator.hasNext())
        {
          Throwable localThrowable = (Throwable)localIterator.next();
          System.err.print("Suppressed: ");
          localThrowable.printStackTrace();
        }
      }
      finally {}
    }
    
    public void printStackTrace(Throwable paramThrowable, PrintStream paramPrintStream)
    {
      paramThrowable.printStackTrace(paramPrintStream);
      paramThrowable = this.map.get(paramThrowable, false);
      if (paramThrowable == null) {
        return;
      }
      try
      {
        Iterator localIterator = paramThrowable.iterator();
        while (localIterator.hasNext())
        {
          Throwable localThrowable = (Throwable)localIterator.next();
          paramPrintStream.print("Suppressed: ");
          localThrowable.printStackTrace(paramPrintStream);
        }
      }
      finally {}
    }
    
    public void printStackTrace(Throwable paramThrowable, PrintWriter paramPrintWriter)
    {
      paramThrowable.printStackTrace(paramPrintWriter);
      paramThrowable = this.map.get(paramThrowable, false);
      if (paramThrowable == null) {
        return;
      }
      try
      {
        Iterator localIterator = paramThrowable.iterator();
        while (localIterator.hasNext())
        {
          Throwable localThrowable = (Throwable)localIterator.next();
          paramPrintWriter.print("Suppressed: ");
          localThrowable.printStackTrace(paramPrintWriter);
        }
      }
      finally {}
    }
  }
  
  static final class NullDesugaringStrategy
    extends ThrowableExtension.AbstractDesugaringStrategy
  {
    public void addSuppressed(Throwable paramThrowable1, Throwable paramThrowable2) {}
    
    public Throwable[] getSuppressed(Throwable paramThrowable)
    {
      return EMPTY_THROWABLE_ARRAY;
    }
    
    public void printStackTrace(Throwable paramThrowable)
    {
      paramThrowable.printStackTrace();
    }
    
    public void printStackTrace(Throwable paramThrowable, PrintStream paramPrintStream)
    {
      paramThrowable.printStackTrace(paramPrintStream);
    }
    
    public void printStackTrace(Throwable paramThrowable, PrintWriter paramPrintWriter)
    {
      paramThrowable.printStackTrace(paramPrintWriter);
    }
  }
  
  static final class ReuseDesugaringStrategy
    extends ThrowableExtension.AbstractDesugaringStrategy
  {
    public void addSuppressed(Throwable paramThrowable1, Throwable paramThrowable2)
    {
      paramThrowable1.addSuppressed(paramThrowable2);
    }
    
    public Throwable[] getSuppressed(Throwable paramThrowable)
    {
      return paramThrowable.getSuppressed();
    }
    
    public void printStackTrace(Throwable paramThrowable)
    {
      paramThrowable.printStackTrace();
    }
    
    public void printStackTrace(Throwable paramThrowable, PrintStream paramPrintStream)
    {
      paramThrowable.printStackTrace(paramPrintStream);
    }
    
    public void printStackTrace(Throwable paramThrowable, PrintWriter paramPrintWriter)
    {
      paramThrowable.printStackTrace(paramPrintWriter);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\devtools\build\android\desugar\runtime\ThrowableExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */