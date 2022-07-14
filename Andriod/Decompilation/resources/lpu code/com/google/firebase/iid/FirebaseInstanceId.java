package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Map;

public class FirebaseInstanceId
{
  private static Map<String, FirebaseInstanceId> zzbhH = new ArrayMap();
  private static zze zzcll;
  private final FirebaseApp zzclm;
  private final zzd zzcln;
  private final String zzclo;
  
  private FirebaseInstanceId(FirebaseApp paramFirebaseApp, zzd paramzzd)
  {
    this.zzclm = paramFirebaseApp;
    this.zzcln = paramzzd;
    this.zzclo = zzabO();
    if (this.zzclo == null) {
      throw new IllegalStateException("IID failing to initialize, FirebaseApp is missing project ID");
    }
    FirebaseInstanceIdService.zza(this.zzclm.getApplicationContext(), this);
  }
  
  public static FirebaseInstanceId getInstance()
  {
    return getInstance(FirebaseApp.getInstance());
  }
  
  @Keep
  public static FirebaseInstanceId getInstance(@NonNull FirebaseApp paramFirebaseApp)
  {
    try
    {
      FirebaseInstanceId localFirebaseInstanceId = (FirebaseInstanceId)zzbhH.get(paramFirebaseApp.getOptions().getApplicationId());
      Object localObject = localFirebaseInstanceId;
      if (localFirebaseInstanceId == null)
      {
        localObject = zzd.zzb(paramFirebaseApp.getApplicationContext(), null);
        if (zzcll == null) {
          zzcll = new zze(((zzd)localObject).zzabS());
        }
        localObject = new FirebaseInstanceId(paramFirebaseApp, (zzd)localObject);
        zzbhH.put(paramFirebaseApp.getOptions().getApplicationId(), localObject);
      }
      return (FirebaseInstanceId)localObject;
    }
    finally {}
  }
  
  static int zzS(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext = String.valueOf(paramContext);
      Log.w("FirebaseInstanceId", String.valueOf(paramContext).length() + 23 + "Failed to find package " + paramContext);
    }
    return 0;
  }
  
  private void zzT(Bundle paramBundle)
  {
    paramBundle.putString("gmp_app_id", this.zzclm.getOptions().getApplicationId());
  }
  
  static String zza(KeyPair paramKeyPair)
  {
    paramKeyPair = paramKeyPair.getPublic().getEncoded();
    try
    {
      paramKeyPair = MessageDigest.getInstance("SHA1").digest(paramKeyPair);
      paramKeyPair[0] = ((byte)((paramKeyPair[0] & 0xF) + 112 & 0xFF));
      paramKeyPair = Base64.encodeToString(paramKeyPair, 0, 8, 11);
      return paramKeyPair;
    }
    catch (NoSuchAlgorithmException paramKeyPair)
    {
      Log.w("FirebaseInstanceId", "Unexpected error, device missing required alghorithms");
    }
    return null;
  }
  
  static void zza(Context paramContext, zzh paramzzh)
  {
    paramzzh.zzHo();
    paramzzh = new Intent();
    paramzzh.putExtra("CMD", "RST");
    zzg.zzabW().zzg(paramContext, paramzzh);
  }
  
  static String zzbx(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext = String.valueOf(paramContext);
      Log.w("FirebaseInstanceId", String.valueOf(paramContext).length() + 38 + "Never happens: can't find own package " + paramContext);
    }
    return null;
  }
  
  static void zzby(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("CMD", "SYNC");
    zzg.zzabW().zzg(paramContext, localIntent);
  }
  
  static int zzcr(Context paramContext)
  {
    return zzS(paramContext, paramContext.getPackageName());
  }
  
  static String zzv(byte[] paramArrayOfByte)
  {
    return Base64.encodeToString(paramArrayOfByte, 11);
  }
  
  public void deleteInstanceId()
    throws IOException
  {
    this.zzcln.zzb("*", "*", null);
    this.zzcln.zzHi();
  }
  
  @WorkerThread
  public void deleteToken(String paramString1, String paramString2)
    throws IOException
  {
    Bundle localBundle = new Bundle();
    zzT(localBundle);
    this.zzcln.zzb(paramString1, paramString2, localBundle);
  }
  
  public long getCreationTime()
  {
    return this.zzcln.getCreationTime();
  }
  
  public String getId()
  {
    return zza(this.zzcln.zzHh());
  }
  
  @Nullable
  public String getToken()
  {
    zzh.zza localzza = zzabP();
    if ((localzza == null) || (localzza.zzjB(zzd.zzbhN))) {
      FirebaseInstanceIdService.zzct(this.zzclm.getApplicationContext());
    }
    if (localzza != null) {
      return localzza.zzbxW;
    }
    return null;
  }
  
  @WorkerThread
  public String getToken(String paramString1, String paramString2)
    throws IOException
  {
    Bundle localBundle = new Bundle();
    zzT(localBundle);
    return this.zzcln.getToken(paramString1, paramString2, localBundle);
  }
  
  String zzabO()
  {
    Object localObject = this.zzclm.getOptions().getGcmSenderId();
    if (localObject != null) {}
    String str;
    do
    {
      do
      {
        return (String)localObject;
        str = this.zzclm.getOptions().getApplicationId();
        localObject = str;
      } while (!str.startsWith("1:"));
      localObject = str.split(":");
      if (localObject.length < 2) {
        return null;
      }
      str = localObject[1];
      localObject = str;
    } while (!str.isEmpty());
    return null;
  }
  
  @Nullable
  zzh.zza zzabP()
  {
    return this.zzcln.zzabS().zzu("", this.zzclo, "*");
  }
  
  String zzabQ()
    throws IOException
  {
    return getToken(this.zzclo, "*");
  }
  
  zze zzabR()
  {
    return zzcll;
  }
  
  public String zzc(String paramString1, String paramString2, Bundle paramBundle)
    throws IOException
  {
    zzT(paramBundle);
    return this.zzcln.zzc(paramString1, paramString2, paramBundle);
  }
  
  public void zzjt(String paramString)
  {
    zzcll.zzjt(paramString);
    FirebaseInstanceIdService.zzct(this.zzclm.getApplicationContext());
  }
  
  void zzju(String paramString)
    throws IOException
  {
    Object localObject = zzabP();
    if ((localObject == null) || (((zzh.zza)localObject).zzjB(zzd.zzbhN))) {
      throw new IOException("token not available");
    }
    Bundle localBundle = new Bundle();
    String str1 = String.valueOf("/topics/");
    String str2 = String.valueOf(paramString);
    if (str2.length() != 0)
    {
      str1 = str1.concat(str2);
      localBundle.putString("gcm.topic", str1);
      str1 = ((zzh.zza)localObject).zzbxW;
      localObject = String.valueOf("/topics/");
      paramString = String.valueOf(paramString);
      if (paramString.length() == 0) {
        break label131;
      }
    }
    label131:
    for (paramString = ((String)localObject).concat(paramString);; paramString = new String((String)localObject))
    {
      zzc(str1, paramString, localBundle);
      return;
      str1 = new String(str1);
      break;
    }
  }
  
  void zzjv(String paramString)
    throws IOException
  {
    Object localObject2 = zzabP();
    if ((localObject2 == null) || (((zzh.zza)localObject2).zzjB(zzd.zzbhN))) {
      throw new IOException("token not available");
    }
    Bundle localBundle = new Bundle();
    Object localObject1 = String.valueOf("/topics/");
    String str = String.valueOf(paramString);
    if (str.length() != 0)
    {
      localObject1 = ((String)localObject1).concat(str);
      localBundle.putString("gcm.topic", (String)localObject1);
      localObject1 = this.zzcln;
      localObject2 = ((zzh.zza)localObject2).zzbxW;
      str = String.valueOf("/topics/");
      paramString = String.valueOf(paramString);
      if (paramString.length() == 0) {
        break label137;
      }
    }
    label137:
    for (paramString = str.concat(paramString);; paramString = new String(str))
    {
      ((zzd)localObject1).zzb((String)localObject2, paramString, localBundle);
      return;
      localObject1 = new String((String)localObject1);
      break;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\iid\FirebaseInstanceId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */