package com.google.firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzam;
import com.google.android.gms.common.util.zzw;

public final class FirebaseOptions
{
  private final String zzamX;
  private final String zzbWQ;
  private final String zzbWR;
  private final String zzbWS;
  private final String zzbWT;
  private final String zzbWU;
  
  private FirebaseOptions(@NonNull String paramString1, @NonNull String paramString2, @Nullable String paramString3, @Nullable String paramString4, @Nullable String paramString5, @Nullable String paramString6)
  {
    if (!zzw.zzdz(paramString1)) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zza(bool, "ApplicationId must be set.");
      this.zzamX = paramString1;
      this.zzbWQ = paramString2;
      this.zzbWR = paramString3;
      this.zzbWS = paramString4;
      this.zzbWT = paramString5;
      this.zzbWU = paramString6;
      return;
    }
  }
  
  public static FirebaseOptions fromResource(Context paramContext)
  {
    paramContext = new zzam(paramContext);
    String str = paramContext.getString("google_app_id");
    if (TextUtils.isEmpty(str)) {
      return null;
    }
    return new FirebaseOptions(str, paramContext.getString("google_api_key"), paramContext.getString("firebase_database_url"), paramContext.getString("ga_trackingId"), paramContext.getString("gcm_defaultSenderId"), paramContext.getString("google_storage_bucket"));
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof FirebaseOptions)) {}
    do
    {
      return false;
      paramObject = (FirebaseOptions)paramObject;
    } while ((!zzaa.equal(this.zzamX, ((FirebaseOptions)paramObject).zzamX)) || (!zzaa.equal(this.zzbWQ, ((FirebaseOptions)paramObject).zzbWQ)) || (!zzaa.equal(this.zzbWR, ((FirebaseOptions)paramObject).zzbWR)) || (!zzaa.equal(this.zzbWS, ((FirebaseOptions)paramObject).zzbWS)) || (!zzaa.equal(this.zzbWT, ((FirebaseOptions)paramObject).zzbWT)) || (!zzaa.equal(this.zzbWU, ((FirebaseOptions)paramObject).zzbWU)));
    return true;
  }
  
  public String getApiKey()
  {
    return this.zzbWQ;
  }
  
  public String getApplicationId()
  {
    return this.zzamX;
  }
  
  public String getDatabaseUrl()
  {
    return this.zzbWR;
  }
  
  public String getGcmSenderId()
  {
    return this.zzbWT;
  }
  
  public String getStorageBucket()
  {
    return this.zzbWU;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { this.zzamX, this.zzbWQ, this.zzbWR, this.zzbWS, this.zzbWT, this.zzbWU });
  }
  
  public String toString()
  {
    return zzaa.zzv(this).zzg("applicationId", this.zzamX).zzg("apiKey", this.zzbWQ).zzg("databaseUrl", this.zzbWR).zzg("gcmSenderId", this.zzbWT).zzg("storageBucket", this.zzbWU).toString();
  }
  
  public static final class Builder
  {
    private String zzamX;
    private String zzbWQ;
    private String zzbWR;
    private String zzbWS;
    private String zzbWT;
    private String zzbWU;
    
    public Builder() {}
    
    public Builder(FirebaseOptions paramFirebaseOptions)
    {
      this.zzamX = FirebaseOptions.zza(paramFirebaseOptions);
      this.zzbWQ = FirebaseOptions.zzb(paramFirebaseOptions);
      this.zzbWR = FirebaseOptions.zzc(paramFirebaseOptions);
      this.zzbWS = FirebaseOptions.zzd(paramFirebaseOptions);
      this.zzbWT = FirebaseOptions.zze(paramFirebaseOptions);
      this.zzbWU = FirebaseOptions.zzf(paramFirebaseOptions);
    }
    
    public FirebaseOptions build()
    {
      return new FirebaseOptions(this.zzamX, this.zzbWQ, this.zzbWR, this.zzbWS, this.zzbWT, this.zzbWU, null);
    }
    
    public Builder setApiKey(@NonNull String paramString)
    {
      this.zzbWQ = zzac.zzh(paramString, "ApiKey must be set.");
      return this;
    }
    
    public Builder setApplicationId(@NonNull String paramString)
    {
      this.zzamX = zzac.zzh(paramString, "ApplicationId must be set.");
      return this;
    }
    
    public Builder setDatabaseUrl(@Nullable String paramString)
    {
      this.zzbWR = paramString;
      return this;
    }
    
    public Builder setGcmSenderId(@Nullable String paramString)
    {
      this.zzbWT = paramString;
      return this;
    }
    
    public Builder setStorageBucket(@Nullable String paramString)
    {
      this.zzbWU = paramString;
      return this;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\FirebaseOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */