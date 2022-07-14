package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zza;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.common.util.zzu;
import com.google.android.gms.internal.zzaac;
import com.google.android.gms.internal.zzaac.zza;
import com.google.android.gms.internal.zzbth;
import com.google.android.gms.internal.zzbti;
import com.google.android.gms.internal.zzbtj;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.GetTokenResult;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FirebaseApp
{
  public static final String DEFAULT_APP_NAME = "[DEFAULT]";
  private static final List<String> zzbWD = Arrays.asList(new String[] { "com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId" });
  private static final List<String> zzbWE = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
  private static final List<String> zzbWF = Arrays.asList(new String[] { "com.google.android.gms.measurement.AppMeasurement" });
  private static final List<String> zzbWG = Arrays.asList(new String[0]);
  private static final Set<String> zzbWH = Collections.emptySet();
  static final Map<String, FirebaseApp> zzbhH = new ArrayMap();
  private static final Object zztX = new Object();
  private final String mName;
  private final FirebaseOptions zzbWI;
  private final AtomicBoolean zzbWJ = new AtomicBoolean(false);
  private final AtomicBoolean zzbWK = new AtomicBoolean();
  private final List<zza> zzbWL = new CopyOnWriteArrayList();
  private final List<zzb> zzbWM = new CopyOnWriteArrayList();
  private final List<Object> zzbWN = new CopyOnWriteArrayList();
  private zzbti zzbWO;
  private final Context zzwi;
  
  protected FirebaseApp(Context paramContext, String paramString, FirebaseOptions paramFirebaseOptions)
  {
    this.zzwi = ((Context)zzac.zzw(paramContext));
    this.mName = zzac.zzdr(paramString);
    this.zzbWI = ((FirebaseOptions)zzac.zzw(paramFirebaseOptions));
  }
  
  public static List<FirebaseApp> getApps(Context paramContext)
  {
    zzbth localzzbth = zzbth.zzcx(paramContext);
    ArrayList localArrayList;
    synchronized (zztX)
    {
      localArrayList = new ArrayList(zzbhH.values());
      Object localObject2 = zzbth.zzaca().zzacb();
      ((Set)localObject2).removeAll(zzbhH.keySet());
      localObject2 = ((Set)localObject2).iterator();
      if (((Iterator)localObject2).hasNext())
      {
        String str = (String)((Iterator)localObject2).next();
        localzzbth.zzjC(str);
        localArrayList.add(initializeApp(paramContext, null, str));
      }
    }
    return localArrayList;
  }
  
  @Nullable
  public static FirebaseApp getInstance()
  {
    synchronized (zztX)
    {
      Object localObject2 = (FirebaseApp)zzbhH.get("[DEFAULT]");
      if (localObject2 == null)
      {
        localObject2 = String.valueOf(zzu.zzzr());
        throw new IllegalStateException(String.valueOf(localObject2).length() + 116 + "Default FirebaseApp is not initialized in this process " + (String)localObject2 + ". Make sure to call FirebaseApp.initializeApp(Context) first.");
      }
    }
    return localFirebaseApp;
  }
  
  public static FirebaseApp getInstance(@NonNull String paramString)
  {
    for (;;)
    {
      synchronized (zztX)
      {
        localObject1 = (FirebaseApp)zzbhH.get(zzis(paramString));
        if (localObject1 != null) {
          return (FirebaseApp)localObject1;
        }
        localObject1 = zzUZ();
        if (((List)localObject1).isEmpty())
        {
          localObject1 = "";
          throw new IllegalStateException(String.format("FirebaseApp with name %s doesn't exist. %s", new Object[] { paramString, localObject1 }));
        }
      }
      Object localObject1 = String.valueOf(TextUtils.join(", ", (Iterable)localObject1));
      if (((String)localObject1).length() != 0) {
        localObject1 = "Available app names: ".concat((String)localObject1);
      } else {
        localObject1 = new String("Available app names: ");
      }
    }
  }
  
  @Nullable
  public static FirebaseApp initializeApp(Context paramContext)
  {
    FirebaseOptions localFirebaseOptions;
    synchronized (zztX)
    {
      if (zzbhH.containsKey("[DEFAULT]"))
      {
        paramContext = getInstance();
        return paramContext;
      }
      localFirebaseOptions = FirebaseOptions.fromResource(paramContext);
      if (localFirebaseOptions == null) {
        return null;
      }
    }
    paramContext = initializeApp(paramContext, localFirebaseOptions);
    return paramContext;
  }
  
  public static FirebaseApp initializeApp(Context paramContext, FirebaseOptions paramFirebaseOptions)
  {
    return initializeApp(paramContext, paramFirebaseOptions, "[DEFAULT]");
  }
  
  public static FirebaseApp initializeApp(Context paramContext, FirebaseOptions paramFirebaseOptions, String paramString)
  {
    zzbth localzzbth = zzbth.zzcx(paramContext);
    zzcl(paramContext);
    paramString = zzis(paramString);
    if (paramContext.getApplicationContext() == null) {}
    synchronized (zztX)
    {
      while (!zzbhH.containsKey(paramString))
      {
        bool = true;
        zzac.zza(bool, String.valueOf(paramString).length() + 33 + "FirebaseApp name " + paramString + " already exists!");
        zzac.zzb(paramContext, "Application context cannot be null.");
        paramContext = new FirebaseApp(paramContext, paramString, paramFirebaseOptions);
        zzbhH.put(paramString, paramContext);
        localzzbth.zzg(paramContext);
        paramContext.zza(FirebaseApp.class, paramContext, zzbWD);
        if (paramContext.zzUX())
        {
          paramContext.zza(FirebaseApp.class, paramContext, zzbWE);
          paramContext.zza(Context.class, paramContext.getApplicationContext(), zzbWF);
        }
        return paramContext;
        paramContext = paramContext.getApplicationContext();
      }
      boolean bool = false;
    }
  }
  
  private void zzUW()
  {
    if (!this.zzbWK.get()) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zza(bool, "FirebaseApp was deleted");
      return;
    }
  }
  
  private static List<String> zzUZ()
  {
    zza localzza = new zza();
    synchronized (zztX)
    {
      localObject2 = zzbhH.values().iterator();
      if (((Iterator)localObject2).hasNext()) {
        localzza.add(((FirebaseApp)((Iterator)localObject2).next()).getName());
      }
    }
    Object localObject2 = zzbth.zzaca();
    if (localObject2 != null) {
      localCollection.addAll(((zzbth)localObject2).zzacb());
    }
    ??? = new ArrayList(localCollection);
    Collections.sort((List)???);
    return (List<String>)???;
  }
  
  private void zzVa()
  {
    zza(FirebaseApp.class, this, zzbWD);
    if (zzUX())
    {
      zza(FirebaseApp.class, this, zzbWE);
      zza(Context.class, this.zzwi, zzbWF);
    }
  }
  
  private <T> void zza(Class<T> paramClass, T paramT, Iterable<String> paramIterable)
  {
    boolean bool = ContextCompat.isDeviceProtectedStorage(this.zzwi);
    if (bool) {
      zzc.zzcn(this.zzwi);
    }
    Iterator localIterator = paramIterable.iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        paramIterable = (String)localIterator.next();
        if (bool) {}
        try
        {
          if (zzbWG.contains(paramIterable))
          {
            Method localMethod = Class.forName(paramIterable).getMethod("getInstance", new Class[] { paramClass });
            int i = localMethod.getModifiers();
            if ((Modifier.isPublic(i)) && (Modifier.isStatic(i))) {
              localMethod.invoke(null, new Object[] { paramT });
            }
          }
        }
        catch (ClassNotFoundException localClassNotFoundException)
        {
          if (zzbWH.contains(paramIterable)) {
            throw new IllegalStateException(String.valueOf(paramIterable).concat(" is missing, but is required. Check if it has been removed by Proguard."));
          }
          Log.d("FirebaseApp", String.valueOf(paramIterable).concat(" is not linked. Skipping initialization."));
        }
        catch (NoSuchMethodException paramClass)
        {
          throw new IllegalStateException(String.valueOf(paramIterable).concat("#getInstance has been removed by Proguard. Add keep rule to prevent it."));
        }
        catch (InvocationTargetException paramIterable)
        {
          Log.wtf("FirebaseApp", "Firebase API initialization failure.", paramIterable);
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          paramIterable = String.valueOf(paramIterable);
          if (paramIterable.length() != 0) {}
          for (paramIterable = "Failed to initialize ".concat(paramIterable);; paramIterable = new String("Failed to initialize "))
          {
            Log.wtf("FirebaseApp", paramIterable, localIllegalAccessException);
            break;
          }
        }
      }
    }
  }
  
  private void zzaV(boolean paramBoolean)
  {
    Log.d("FirebaseApp", "Notifying background state change listeners.");
    Iterator localIterator = this.zzbWM.iterator();
    while (localIterator.hasNext()) {
      ((zzb)localIterator.next()).zzas(paramBoolean);
    }
  }
  
  public static void zzas(boolean paramBoolean)
  {
    synchronized (zztX)
    {
      Iterator localIterator = new ArrayList(zzbhH.values()).iterator();
      while (localIterator.hasNext())
      {
        FirebaseApp localFirebaseApp = (FirebaseApp)localIterator.next();
        if (localFirebaseApp.zzbWJ.get()) {
          localFirebaseApp.zzaV(paramBoolean);
        }
      }
    }
  }
  
  @TargetApi(14)
  private static void zzcl(Context paramContext)
  {
    zzt.zzzg();
    if ((paramContext.getApplicationContext() instanceof Application))
    {
      zzaac.zza((Application)paramContext.getApplicationContext());
      zzaac.zzvB().zza(new zzaac.zza()
      {
        public void zzas(boolean paramAnonymousBoolean)
        {
          FirebaseApp.zzas(paramAnonymousBoolean);
        }
      });
    }
  }
  
  private static String zzis(@NonNull String paramString)
  {
    return paramString.trim();
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof FirebaseApp)) {
      return false;
    }
    return this.mName.equals(((FirebaseApp)paramObject).getName());
  }
  
  @NonNull
  public Context getApplicationContext()
  {
    zzUW();
    return this.zzwi;
  }
  
  @NonNull
  public String getName()
  {
    zzUW();
    return this.mName;
  }
  
  @NonNull
  public FirebaseOptions getOptions()
  {
    zzUW();
    return this.zzbWI;
  }
  
  public Task<GetTokenResult> getToken(boolean paramBoolean)
  {
    zzUW();
    if (this.zzbWO == null) {
      return Tasks.forException(new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode."));
    }
    return this.zzbWO.zzaW(paramBoolean);
  }
  
  public int hashCode()
  {
    return this.mName.hashCode();
  }
  
  public void setAutomaticResourceManagementEnabled(boolean paramBoolean)
  {
    zzUW();
    AtomicBoolean localAtomicBoolean = this.zzbWJ;
    boolean bool;
    if (!paramBoolean)
    {
      bool = true;
      if (localAtomicBoolean.compareAndSet(bool, paramBoolean))
      {
        bool = zzaac.zzvB().zzvC();
        if ((!paramBoolean) || (!bool)) {
          break label50;
        }
        zzaV(true);
      }
    }
    label50:
    while ((paramBoolean) || (!bool))
    {
      return;
      bool = false;
      break;
    }
    zzaV(false);
  }
  
  public String toString()
  {
    return zzaa.zzv(this).zzg("name", this.mName).zzg("options", this.zzbWI).toString();
  }
  
  public boolean zzUX()
  {
    return "[DEFAULT]".equals(getName());
  }
  
  public String zzUY()
  {
    String str1 = String.valueOf(zzc.zzs(getName().getBytes()));
    String str2 = String.valueOf(zzc.zzs(getOptions().getApplicationId().getBytes()));
    return String.valueOf(str1).length() + 1 + String.valueOf(str2).length() + str1 + "+" + str2;
  }
  
  public void zza(@NonNull zzbti paramzzbti)
  {
    this.zzbWO = ((zzbti)zzac.zzw(paramzzbti));
  }
  
  @UiThread
  public void zza(@NonNull zzbtj paramzzbtj)
  {
    Log.d("FirebaseApp", "Notifying auth state listeners.");
    Iterator localIterator = this.zzbWL.iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      ((zza)localIterator.next()).zzb(paramzzbtj);
      i += 1;
    }
    Log.d("FirebaseApp", String.format("Notified %d auth state listeners.", new Object[] { Integer.valueOf(i) }));
  }
  
  public void zza(@NonNull zza paramzza)
  {
    zzUW();
    zzac.zzw(paramzza);
    this.zzbWL.add(paramzza);
  }
  
  public void zza(zzb paramzzb)
  {
    zzUW();
    if ((this.zzbWJ.get()) && (zzaac.zzvB().zzvC())) {
      paramzzb.zzas(true);
    }
    this.zzbWM.add(paramzzb);
  }
  
  public static abstract interface zza
  {
    public abstract void zzb(@NonNull zzbtj paramzzbtj);
  }
  
  public static abstract interface zzb
  {
    public abstract void zzas(boolean paramBoolean);
  }
  
  @TargetApi(24)
  private static class zzc
    extends BroadcastReceiver
  {
    private static AtomicReference<zzc> zzbWP = new AtomicReference();
    private final Context zzwi;
    
    public zzc(Context paramContext)
    {
      this.zzwi = paramContext;
    }
    
    private static void zzcm(Context paramContext)
    {
      if (zzbWP.get() == null)
      {
        zzc localzzc = new zzc(paramContext);
        if (zzbWP.compareAndSet(null, localzzc)) {
          paramContext.registerReceiver(localzzc, new IntentFilter("android.intent.action.USER_UNLOCKED"));
        }
      }
    }
    
    public void onReceive(Context arg1, Intent paramIntent)
    {
      synchronized ()
      {
        paramIntent = FirebaseApp.zzbhH.values().iterator();
        if (paramIntent.hasNext()) {
          FirebaseApp.zza((FirebaseApp)paramIntent.next());
        }
      }
      unregister();
    }
    
    public void unregister()
    {
      this.zzwi.unregisterReceiver(this);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\FirebaseApp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */